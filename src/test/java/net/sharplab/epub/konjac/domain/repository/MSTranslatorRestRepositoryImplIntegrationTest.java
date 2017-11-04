package net.sharplab.epub.konjac.domain.repository;

import net.sharplab.epub.konjac.domain.model.AccessToken;
import net.sharplab.epub.konjac.domain.model.EPubChapter;
import net.sharplab.epub.konjac.domain.model.Options;
import net.sharplab.epub.konjac.domain.model.Text;
import net.sharplab.epub.konjac.domain.xml.TranslateArrayRequest;
import net.sharplab.epub.konjac.domain.xml.TranslateArrayResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test for MSTranslatorRestRepositoryImpl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("local-with-services")
@SpringBootTest
@AutoConfigureWebClient
public class MSTranslatorRestRepositoryImplIntegrationTest {

    @Autowired
    MSTranslatorRestRepositoryImpl target;

    @Test
    public void getAccessToken_test1(){
        AccessToken accessToken = target.getAccessToken();
    }

    @Test
    public void translate_test1(){
        Options options = new Options();
        options.setContentType("text/plain");
        TranslateArrayRequest translateArrayRequest = new TranslateArrayRequest("", "es", "en", options, Collections.singletonList(new Text("a veces los errores son divertidos")));
        TranslateArrayResponse[] translateArrayResponses = target.translate(translateArrayRequest);
        assertThat(translateArrayResponses.length).isEqualTo(1);
        TranslateArrayResponse translateArrayResponse = translateArrayResponses[0];
        assertThat(translateArrayResponse.getFrom()).isEqualTo("es");
        assertThat(translateArrayResponse.getOriginalTextSentenceLengths()).containsExactly(34);
        assertThat(translateArrayResponse.getTranslatedText()).isInstanceOf(String.class);
        assertThat(translateArrayResponse.getTranslatedTextSentenceLengths()).isInstanceOf(int[].class);
    }

    @Test
    public void translate_test2() throws Exception{
        File testFile = new ClassPathResource("/FileFixtures/repository/MSTranslatorRestRepository/spring-security-saml-reference-ch01.xhtml").getFile();
        EPubChapter ePubChapter = new EPubChapter(testFile);

        Options options = new Options();
        options.setCategory("generalnn");
        options.setContentType("text/html");
        TranslateArrayRequest translateArrayRequest = new TranslateArrayRequest("", "en", "ja", options, Collections.singletonList(new Text(ePubChapter.getContents())));
        TranslateArrayResponse[] translateArrayResponses = target.translate(translateArrayRequest);
    }



}
