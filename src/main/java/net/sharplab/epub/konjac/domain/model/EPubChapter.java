package net.sharplab.epub.konjac.domain.model;

import net.sharplab.epub.konjac.domain.repository.TranslatorOption;
import net.sharplab.epub.konjac.domain.service.TranslatorService;

import java.io.File;
import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;

/**
 * EPubChapter
 */
public class EPubChapter extends EPubContentFile {

    public EPubChapter(String name, byte[] data){
        super(name, data);
    }

    public EPubChapter(File file){
        this(file.getName(), readFile(file));
    }

    public EPubChapter(String name, String contents){
        super(name, new byte[]{});
        try {
            data = contents.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public EPubChapter translate(TranslatorService translatorService, TranslatorOption translatorOption){
        String translatedContents = translatorService.translateString(this.getContents(), translatorOption);
        return new EPubChapter(this.getName(), translatedContents);
    }


}
