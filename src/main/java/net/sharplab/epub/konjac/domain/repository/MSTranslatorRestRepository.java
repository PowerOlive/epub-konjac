package net.sharplab.epub.konjac.domain.repository;


import net.sharplab.epub.konjac.domain.model.AccessToken;
import net.sharplab.epub.konjac.domain.xml.TranslateArrayRequest;
import net.sharplab.epub.konjac.domain.xml.TranslateArrayResponse;

/**
 * Created by ynojima on 2017/04/23.
 */
public interface MSTranslatorRestRepository {

    AccessToken getAccessToken();

    TranslateArrayResponse[] translate(TranslateArrayRequest translateArrayRequest);

}
