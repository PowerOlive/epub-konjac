package net.sharplab.epub.konjac.domain.repository;

import net.sharplab.epub.konjac.domain.model.AccessToken;
import net.sharplab.epub.konjac.domain.xml.ArrayOfTranslateArrayResponse;
import net.sharplab.epub.konjac.domain.xml.TranslateArrayRequest;
import net.sharplab.epub.konjac.domain.xml.TranslateArrayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXB;
import java.io.StringReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * MSTranslatorRestRepository
 */
@Repository
public class MSTranslatorRestRepositoryImpl implements MSTranslatorRestRepository {


    private static final String TOKEN_ENDPOINT = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";

    private static final String TRANSLATOR_ENDPOINT = "http://api.microsofttranslator.com/V2/Http.svc";

    RestTemplate restTemplate;

    @Value("${msTranslator.client.subscriptionKey}")
    String subscriptionKey;

    AccessToken cachedAccessToken = null;
    LocalDateTime cachedAccessTokenRenewDateTime = null;

    public MSTranslatorRestRepositoryImpl(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.build();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    public AccessToken getAccessToken() {

        if (cachedAccessTokenRenewDateTime != null && cachedAccessTokenRenewDateTime.isAfter(LocalDateTime.now())) {
            return cachedAccessToken;
        }

        LinkedMultiValueMap headers = new LinkedMultiValueMap();
        headers.add("Ocp-Apim-Subscription-Key", subscriptionKey);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                TOKEN_ENDPOINT,
                new HttpEntity<>(null, headers),
                String.class);
        AccessToken accessToken = new AccessToken(responseEntity.getBody());
        cachedAccessToken = accessToken;
        cachedAccessTokenRenewDateTime = LocalDateTime.now().plus(Duration.ofMinutes(8));
        return accessToken;
    }

    @Cacheable("translations")
    public TranslateArrayResponse[] translate(TranslateArrayRequest translateArrayRequest) {
        AccessToken accessToken = getAccessToken();
        String bearerToken = "Bearer " + accessToken.getValue();
        URI url = URI.create(TRANSLATOR_ENDPOINT + "/TranslateArray");
        String requestXmlString = translateArrayRequest.toXmlString();

        RequestEntity<String> requestEntity = RequestEntity
                .post(url)
                .header("Authorization", bearerToken)
                .contentType(MediaType.TEXT_XML)
                .body(requestXmlString);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        String xmlString = responseEntity.getBody();
        return buildTranslateArrayResponses(xmlString);
    }

    TranslateArrayResponse[] buildTranslateArrayResponses(String xmlString){
        StringReader reader = new StringReader(xmlString);
        return JAXB.unmarshal(reader, ArrayOfTranslateArrayResponse.class).getTranslateArrayResponses();
    }
}