package net.sharplab.epub.konjac.app.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import net.sharplab.epub.konjac.domain.model.EPubFile;
import net.sharplab.epub.konjac.domain.repository.TranslatorOption;
import net.sharplab.epub.konjac.domain.service.EPubFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * CommandLineRunner for epub-konjac
 */
@Configuration
public class EpubKonjacCommandLineRunner implements CommandLineRunner {

    private final EPubFileService ePubFileService;

    @Value("${msTranslator.language.source}")
    private String defaultSrcLang;

    @Value("${msTranslator.language.destination}")
    private String defaultDstLang;

    @Autowired
    public EpubKonjacCommandLineRunner(EPubFileService ePubFileService) {
        this.ePubFileService = ePubFileService;
    }

    /**
     * entry point of {@link EpubKonjacCommandLineRunner}
     * @param args arguments
     */
    @Override
    public void run(String... args) {
        CliArgs cliArgs = parseArgs(args);
        if(cliArgs == null){
            return;
        }
        translate(cliArgs);
    }

    /**
     * translates an epub file based on {@link TranslatorOption}
     * @param cliArgs command line arguments
     */
    void translate(CliArgs cliArgs){
        try (FileOutputStream fileOutputStream = new FileOutputStream(cliArgs.getDst())){
            ePubFileService.translateEPubFile(new EPubFile(cliArgs.getSrc()), fileOutputStream, new TranslatorOption(cliArgs.getSrcLang(), cliArgs.getDstLang()));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    CliArgs parseArgs(String[] args){
        CliArgs cliArgs = new CliArgs();
        JCommander jCommander =
                JCommander.newBuilder()
                .addObject(cliArgs)
                .build();
        try{
            jCommander.parse(args);
        }
        catch (ParameterException ex){
            System.out.println(ex.getMessage());
            ex.usage();
            return null;
        }
        if(cliArgs.isHelp()){
            jCommander.usage();
            return null;
        }

        if(cliArgs.getSrcLang() == null){
            cliArgs.setSrcLang(defaultSrcLang);
        }
        if(cliArgs.getDstLang() == null){
            cliArgs.setDstLang(defaultDstLang);
        }
        if(cliArgs.getDst() == null){
            File dst = createDstFileFromSrcFile(cliArgs.getSrc(), cliArgs.getDstLang());
            cliArgs.setDst(dst);
        }
        return cliArgs;
    }

    File createDstFileFromSrcFile(File src, String dstLang){
        String srcFileName = src.getName();
        String dstFileName;
        if(!srcFileName.contains(".")) {
            dstFileName = srcFileName + "." + dstLang;
        }
        else {
            dstFileName = srcFileName.substring(0, srcFileName.lastIndexOf(".")) + "." + dstLang + srcFileName.substring(srcFileName.lastIndexOf("."), srcFileName.length());
        }
        return new File(src.getParent(), dstFileName);
    }
}
