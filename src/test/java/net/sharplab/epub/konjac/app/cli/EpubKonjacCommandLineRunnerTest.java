package net.sharplab.epub.konjac.app.cli;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Test for EpubKonjacShell
 */
public class EpubKonjacCommandLineRunnerTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private EpubKonjacCommandLineRunner epubKonjacCommandLineRunner = new EpubKonjacCommandLineRunner();

    @Test
    public void createDstFileFromSrcFile_with_period_including_file_test() throws Exception{
        File src = temporaryFolder.newFile("FileNameWithExtension.epub");
        File dst = epubKonjacCommandLineRunner.createDstFileFromSrcFile(src, "ja");
        assertThat(dst.getName()).isEqualTo("FileNameWithExtension.ja.epub");
    }

    @Test
    public void createDstFileFromSrcFile_with_period_not_including_file_test() throws Exception{
        File src = temporaryFolder.newFile("FileNameWithoutExtension");
        File dst = epubKonjacCommandLineRunner.createDstFileFromSrcFile(src, "ja");
        assertThat(dst.getName()).isEqualTo("FileNameWithoutExtension.ja");
    }

    @Test
    public void createDstFileFromSrcFile_with_multiple_period_including_file_test() throws Exception{
        File src = temporaryFolder.newFile("FileName.With.Period.epub");
        File dst = epubKonjacCommandLineRunner.createDstFileFromSrcFile(src, "ja");
        assertThat(dst.getName()).isEqualTo("FileName.With.Period.ja.epub");
    }
}
