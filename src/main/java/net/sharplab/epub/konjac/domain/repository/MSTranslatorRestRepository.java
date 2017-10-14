package net.sharplab.epub.konjac.domain.repository;


import net.sharplab.epub.konjac.domain.model.AccessToken;
import net.sharplab.epub.konjac.domain.xml.TranslateArrayRequest;
import net.sharplab.epub.konjac.domain.xml.TranslateArrayResponse;

/**
 * RestRepository for Microsoft Translation API
 */
public interface MSTranslatorRestRepository {

    AccessToken getAccessToken();

    TranslateArrayResponse[] translate(TranslateArrayRequest translateArrayRequest);

}
