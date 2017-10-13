package net.sharplab.epub.konjac.domain.model;

import net.sharplab.epub.konjac.domain.provider.epub.DefaultEPubContentFileProvider;
import net.sharplab.epub.konjac.domain.provider.epub.EPubChapterProvider;
import net.sharplab.epub.konjac.domain.provider.epub.EPubContentFileProvider;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for EPubFile
 */
public class EPubFileTest {

    private List<EPubContentFileProvider> ePubContentFileProviders;

    @Before
    public void setup(){
        ePubContentFileProviders = Arrays.asList(new DefaultEPubContentFileProvider(), new EPubChapterProvider());
    }

    @Test
    public void getContentFiles_test1() throws Exception{
        File testFile = new ClassPathResource("/FileFixtures/apache-cloudstack-docs/ApacheCloudStack.epub").getFile();
        EPubFile testEPubFile = new EPubFile(testFile);
        List<EPubContentFile> contentFiles = testEPubFile.getContentFiles(ePubContentFileProviders);
        assertThat(contentFiles).size().isEqualTo(118);
    }

}
