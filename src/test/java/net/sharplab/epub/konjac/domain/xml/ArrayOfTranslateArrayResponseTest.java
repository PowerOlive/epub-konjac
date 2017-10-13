package net.sharplab.epub.konjac.domain.xml;

import org.junit.Test;

import javax.xml.bind.JAXB;

/**
 * Test for ArrayOfTranslateArrayResponse
 */
public class ArrayOfTranslateArrayResponseTest {
    @Test
    public void test_marshal(){
        TranslateArrayResponse translateArrayResponse = new TranslateArrayResponse();
        translateArrayResponse.setFrom("en");
        translateArrayResponse.setOriginalTextSentenceLengths(new int[]{34});
        translateArrayResponse.setTranslatedText("これはペンです。");
        translateArrayResponse.setTranslatedTextSentenceLengths(new int[]{8});
        ArrayOfTranslateArrayResponse arrayOfTranslateArrayResponse = new ArrayOfTranslateArrayResponse();
        arrayOfTranslateArrayResponse.setTranslateArrayResponses(new TranslateArrayResponse[]{translateArrayResponse});

        JAXB.marshal(arrayOfTranslateArrayResponse, System.out);
    }
}
