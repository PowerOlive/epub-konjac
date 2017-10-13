//package net.sharplab.epub.konjac.domain.service;
//
//import net.sharplab.epub.konjac.domain.config.DomainConfig;
//import net.sharplab.epub.konjac.domain.repository.TranslatorOption;
//import net.sharplab.epub.konjac.domain.xml.XmlUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.w3c.dom.Document;
//
//import java.io.File;
//
///**
// * Integration test for TranslatorServiceImpl
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ContextConfiguration(classes = DomainConfig.class)
//public class TranslatorServiceImplIntegrationTest {
//
//    @Autowired
//    private TranslatorServiceImpl target;
//
//    @Test
//    public void translateDocument_test1() throws Exception{
//        File testFile = new ClassPathResource("/FileFixtures/apache-cloudstack-docs/developer_guide.xhtml").getFile();
//
//        Document document = XmlUtils.load(testFile);
//        Document translated = target.translateDocument(document, new TranslatorOption("en", "ja"));
//    }
//
//}
