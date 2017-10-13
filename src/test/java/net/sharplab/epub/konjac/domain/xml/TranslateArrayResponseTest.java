package net.sharplab.epub.konjac.domain.xml;

import org.junit.Test;

import javax.xml.bind.JAXB;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for TranslateArrayResponse
 */
public class TranslateArrayResponseTest {

    @Test
    public void test_marshal(){
        TranslateArrayResponse translateArrayResponse = new TranslateArrayResponse();
        translateArrayResponse.setFrom("en");
        translateArrayResponse.setOriginalTextSentenceLengths(new int[]{34});
        translateArrayResponse.setTranslatedText("これはペンです。");
        translateArrayResponse.setTranslatedTextSentenceLengths(new int[]{8});

        JAXB.marshal(translateArrayResponse, System.out);
        //TODO: Assert
    }

    @Test
    public void test_unmarshal(){
        TranslateArrayResponse expected = new TranslateArrayResponse();
        expected.setFrom("en");
        expected.setOriginalTextSentenceLengths(new int[]{34});
        expected.setTranslatedText("これはペンです。");
        expected.setTranslatedTextSentenceLengths(new int[]{8});

        String source = "<translateArrayResponse \n" +
                "    xmlns=\"http://schemas.datacontract.org/2004/07/Microsoft.MT.Web.Service.V2\" \n" +
                "    xmlns:i=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n" +
                "    <From>en</From>\n" +
                "    <OriginalTextSentenceLengths>\n" +
                "        <i:int>34</i:int>\n" +
                "    </OriginalTextSentenceLengths>\n" +
                "    <TranslatedText>これはペンです。</TranslatedText>\n" +
                "    <TranslatedTextSentenceLengths>\n" +
                "        <i:int>8</i:int>\n" +
                "    </TranslatedTextSentenceLengths>\n" +
                "</translateArrayResponse>";

        StringReader reader = new StringReader(source);
        TranslateArrayResponse translateArrayResponse = JAXB.unmarshal(reader, TranslateArrayResponse.class);
        assertThat(translateArrayResponse).isEqualToComparingFieldByField(expected);
    }

}
