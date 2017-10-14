package net.sharplab.epub.konjac.app.cli;

import net.sharplab.epub.konjac.domain.service.EPubFileService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Test for EpubKonjacShell
 */
public class EpubKonjacCommandLineRunnerTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    private final EpubKonjacCommandLineRunner epubKonjacCommandLineRunner = new EpubKonjacCommandLineRunner(mock(EPubFileService.class));

    @Test
    public void createDstFileFromSrcFile_with_period_including_file_test() throws Exception{
        File src = temporaryFolder.newFile("FileNameWithExtension.epub");
        File dst = epubKonjacCommandLineRunner.createDstFileFromSrcFile(src, "ja");
        assertThat(dst.getName()).isEqualTo("FileNameWithExtension.ja.epub");
        assertThat(dst.getParent()).isEqualTo(src.getParent());
    }

    @Test
    public void createDstFileFromSrcFile_with_period_not_including_file_test() throws Exception{
        File src = temporaryFolder.newFile("FileNameWithoutExtension");
        File dst = epubKonjacCommandLineRunner.createDstFileFromSrcFile(src, "ja");
        assertThat(dst.getName()).isEqualTo("FileNameWithoutExtension.ja");
        assertThat(dst.getParent()).isEqualTo(src.getParent());
    }

    @Test
    public void createDstFileFromSrcFile_with_multiple_period_including_file_test() throws Exception{
        File src = temporaryFolder.newFile("FileName.With.Period.epub");
        File dst = epubKonjacCommandLineRunner.createDstFileFromSrcFile(src, "ja");
        assertThat(dst.getName()).isEqualTo("FileName.With.Period.ja.epub");
        assertThat(dst.getParent()).isEqualTo(src.getParent());
    }
}
