package net.sharplab.epub.konjac.domain.service;

import net.sharplab.epub.konjac.domain.dto.TranslateRequest;
import net.sharplab.epub.konjac.domain.dto.TranslateRequestChunk;
import net.sharplab.epub.konjac.domain.repository.MSTranslatorRestRepository;
import net.sharplab.epub.konjac.domain.xml.XmlUtils;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for TranslatorServiceImpl
 */
public class TranslatorServiceImplTest {

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @InjectMocks
    private TranslatorServiceImpl target;

    @Mock
    private MSTranslatorRestRepository msTranslatorRestRepository;

    @Test
    public void generateTranslateRequests_test1(){
        Document document = XmlUtils.parseXmlStringToDocument("<html>\n" +
                "  <head>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <h1>espresso</h1>\n" +
                "    <p>\n" +
                "      Yet another spring boot/cloud playground\n" +
                "    </p>\n" +
                "  </body>\n" +
                "</html>");
        List<TranslateRequest> translateRequests = target.generateTranslateRequests(document);
        assertThat(translateRequests.get(0).getSourceXmlString().trim()).isEqualTo("espresso");
        assertThat(translateRequests.get(1).getSourceXmlString().trim()).isEqualTo("Yet another spring boot/cloud playground");
    }

    @Test
    public void generateTranslateRequests_test2() throws Exception{
        String xmlString = "<p>Source code for the project is maintained on <a class=\"ulink\" href=\"https://github.com/SpringSource/spring-security-saml\" target=\"_top\">Github</a>.</p>";
        Document document = XmlUtils.parseXmlStringToDocument(xmlString);

        //When
        List<TranslateRequest> translateRequests = target.generateTranslateRequests(document);

        //Then
        assertThat(translateRequests.get(0).getSourceXmlString()).isEqualTo("Source code for the project is maintained on <a class=\"ulink\" href=\"https://github.com/SpringSource/spring-security-saml\" target=\"_top\">Github</a>.");

    }

    @Test
    public void generateTranslateRequests_test3() throws Exception{
        String xmlString = "<p><h3>caption</h3><pre  class=\"programlisting\">var sample = \"Yet another sample code\";</pre></p>";
        Document document = XmlUtils.parseXmlStringToDocument(xmlString);

        //When
        List<TranslateRequest> translateRequests = target.generateTranslateRequests(document);

        //Then
        assertThat(translateRequests.get(0).getSourceXmlString()).isEqualTo("caption");
        assertThat(translateRequests).size().isEqualTo(1);
    }

    @Test
    public void generateTranslateRequests_test4() throws Exception{
        String xmlString = "<li><a href=\"ch01.xhtml\">1. Introduction</a></li>";

        Document document = XmlUtils.parseXmlStringToDocument(xmlString);

        //When
        List<TranslateRequest> translateRequests = target.generateTranslateRequests(document);

        //Then
        assertThat(translateRequests.get(0).getSourceXmlString()).isEqualTo("<a href=\"ch01.xhtml\">1. Introduction</a>");
    }

    @Test
    public void formTranslateRequestChunks_test1(){
        TranslateRequest translateRequest1 = mock(TranslateRequest.class);
        TranslateRequest translateRequest2 = mock(TranslateRequest.class);
        TranslateRequest translateRequest3 = mock(TranslateRequest.class);
        TranslateRequest translateRequest4 = mock(TranslateRequest.class);
        TranslateRequest translateRequest5 = mock(TranslateRequest.class);

        when(translateRequest1.getSourceXmlString()).thenReturn(String.format("%1000d",  0));
        when(translateRequest2.getSourceXmlString()).thenReturn(String.format("%4000d",  0));
        when(translateRequest3.getSourceXmlString()).thenReturn(String.format("%240d",   0));
        when(translateRequest4.getSourceXmlString()).thenReturn(String.format("%1000d",  0));
        when(translateRequest5.getSourceXmlString()).thenReturn(String.format("%10000d", 0));

        List<TranslateRequest> translateRequests = Arrays.asList(translateRequest1, translateRequest2, translateRequest3, translateRequest4, translateRequest5);

        List<TranslateRequestChunk> translateRequestChunks = target.formTranslateRequestChunks(translateRequests);
        assertThat(translateRequestChunks.get(0).getTranslateRequests()).containsExactly(translateRequest1, translateRequest2);
        assertThat(translateRequestChunks.get(1).getTranslateRequests()).containsExactly(translateRequest3, translateRequest4);
        assertThat(translateRequestChunks.get(2).getTranslateRequests()).containsExactly(translateRequest5);
    }

    /**
     * [Given]
     * None
     * [When]
     * Div node is passed as an argument.
     * [Then]
     * false
     */
    @Test
    public void isTranslationTargetNode_test1(){

        //When
        Node node = createDocument().createElement("div");

        //Then
        assertThat(target.isTranslationTargetNode(node)).isFalse();
    }

    /**
     * [Given]
     * None
     * [When]
     * Span node is passed as an argument.
     * [Then]
     * true
     */
    @Test
    public void isTranslationTargetNode_test2(){

        //When
        Node node = createDocument().createElement("span");

        //Then
        assertThat(target.isTranslationTargetNode(node)).isTrue();
    }

    @Test
    public void generateTranslateRequests_with_real_file_test1() throws Exception{
        File testFile = new ClassPathResource("/FileFixtures/apache-cloudstack-docs/dev.xhtml").getFile();

        Document document = XmlUtils.load(testFile);
        List<TranslateRequest> translateRequests = target.generateTranslateRequests(document);
        assertThat(translateRequests).isNotEmpty();
    }

    @Test
    public void generateTranslateRequests_with_real_file_test2() throws Exception{
        File testFile = new ClassPathResource("/FileFixtures/apache-cloudstack-docs/developer_guide.xhtml").getFile();

        Document document = XmlUtils.load(testFile);
        List<TranslateRequest> translateRequests = target.generateTranslateRequests(document);
        assertThat(translateRequests).isNotEmpty();
    }

    @Test
    public void generateTranslateRequests_with_real_file_test3() throws Exception{
        File testFile = new ClassPathResource("/FileFixtures/apache-cloudstack-docs/administration_guide.xhtml").getFile();

        Document document = XmlUtils.load(testFile);
        List<TranslateRequest> translateRequests = target.generateTranslateRequests(document);
        assertThat(translateRequests).isNotEmpty();
    }

    private Document createDocument() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.newDocument();
        } catch (ParserConfigurationException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
