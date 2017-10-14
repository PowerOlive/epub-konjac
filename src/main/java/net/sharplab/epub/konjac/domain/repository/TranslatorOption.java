package net.sharplab.epub.konjac.domain.repository;

import lombok.Data;

/**
 * TranslatorOption
 */
@Data
public class TranslatorOption {

    public TranslatorOption(String srcLang, String dstLang){
        this.srcLang = srcLang;
        this.dstLang = dstLang;
    }

    private String srcLang;
    private String dstLang;

}