package net.sharplab.epub.konjac.domain.service;

import net.sharplab.epub.konjac.domain.config.TestDomainConfig;
import net.sharplab.epub.konjac.domain.repository.TranslatorOption;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Integration test for EPubFileServiceImpl
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("local-with-services")
@SpringBootTest(classes = TestDomainConfig.class)
@AutoConfigureWebClient
public class EPubFileServiceImplIntegrationTest {

    @Autowired
    private EPubFileService target;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void translateEPubFile_test1() throws Exception{

        File testFile = new ClassPathResource("/FileFixtures/apache-cloudstack-docs/ApacheCloudStack.epub").getFile();
        TranslatorOption translatorOption = new TranslatorOption("en", "ja");
        //When
        target.translateEPubFile(testFile, new ByteArrayOutputStream(), translatorOption);
    }

}
