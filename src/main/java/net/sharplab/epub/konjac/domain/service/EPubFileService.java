package net.sharplab.epub.konjac.domain.service;


import net.sharplab.epub.konjac.domain.model.EPubFile;
import net.sharplab.epub.konjac.domain.repository.TranslatorOption;

import java.io.OutputStream;

/**
 * EPubファイルサービス
 */
public interface EPubFileService {

    void translateEPubFile(EPubFile source, OutputStream outputStream, TranslatorOption translatorOption);

}
