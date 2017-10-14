package net.sharplab.epub.konjac.domain.service;

import net.sharplab.epub.konjac.domain.model.EPubChapter;
import net.sharplab.epub.konjac.domain.model.EPubContentFile;
import net.sharplab.epub.konjac.domain.model.EPubFile;
import net.sharplab.epub.konjac.domain.provider.epub.EPubContentFileProvider;
import net.sharplab.epub.konjac.domain.repository.TranslatorOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * EPubファイルサービス
 */
@Service
public class EPubFileServiceImpl implements EPubFileService {

    private final Logger logger = LoggerFactory.getLogger(EPubFileServiceImpl.class);

    private final TranslatorService translatorService;

    private final List<EPubContentFileProvider> ePubContentFileProviders;

    @Autowired
    public EPubFileServiceImpl(TranslatorService translatorService, List<EPubContentFileProvider> ePubContentFileProviders) {
        this.translatorService = translatorService;
        this.ePubContentFileProviders = ePubContentFileProviders;
    }

    @Override
    public void translateEPubFile(EPubFile source, OutputStream outputStream, TranslatorOption translatorOption){
        List<EPubContentFile> contentFiles = source.getContentFiles(ePubContentFileProviders);

        contentFiles = contentFiles.stream().map( contentFile -> {
            if(contentFile instanceof EPubChapter){
                EPubChapter ePubChapter = ((EPubChapter) contentFile).translate(translatorService, translatorOption);
                logger.info("{} is translated.", ePubChapter.getName());
                return ePubChapter;
            }
            else {
                return contentFile;
            }
        }).collect(Collectors.toList());

        try(ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)){
            for(EPubContentFile contentFile : contentFiles){
                ZipEntry zipEntry = new ZipEntry(contentFile.getName());
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(contentFile.getData());
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

}
