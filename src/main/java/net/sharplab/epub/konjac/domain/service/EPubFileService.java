package net.sharplab.epub.konjac.domain.service;


import net.sharplab.epub.konjac.domain.repository.TranslatorOption;

import java.io.File;
import java.io.OutputStream;

/**
 * EPubファイルサービス
 */
public interface EPubFileService {

    void translateEPubFile(File source, OutputStream outputStream, TranslatorOption translatorOption);

}
