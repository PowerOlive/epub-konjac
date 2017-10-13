package net.sharplab.epub.konjac.domain.service;

import net.sharplab.epub.konjac.domain.repository.TranslatorOption;
import org.w3c.dom.Document;

/**
 * 翻訳サービス
 */
public interface TranslatorService {

    /**
     * XML文字列を翻訳する
     * @param xmlString 翻訳元のXML文字列
     * @return 翻訳後のXML文字列
     */
    String translateString(String xmlString, TranslatorOption translatorOption);

    /**
     * XML文書を翻訳する
     * @param document 翻訳元のXML文書
     * @return 翻訳後のXML文書
     */
    Document translateDocument(Document document, TranslatorOption translatorOption);


}
