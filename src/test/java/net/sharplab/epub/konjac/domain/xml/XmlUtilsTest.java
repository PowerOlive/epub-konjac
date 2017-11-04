package net.sharplab.epub.konjac.domain.xml;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for XmlUtils
 */
public class XmlUtilsTest {

    @Test
    public void parseXmlStringToDocumentFragment_test1() throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document = documentBuilderFactory.newDocumentBuilder().newDocument();

        //When
        DocumentFragment documentFragment = XmlUtils.parseXmlStringToDocumentFragment(document, "sample string");

        //Then
        assertThat(documentFragment.getFirstChild().getNodeType()).isEqualTo(Node.TEXT_NODE);
        assertThat(documentFragment.getFirstChild().getTextContent()).isEqualTo("sample string");
    }

    @Test
    public void parseXmlStringToDocumentFragment_test2() throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document = documentBuilderFactory.newDocumentBuilder().newDocument();

        //When
        DocumentFragment documentFragment = XmlUtils.parseXmlStringToDocumentFragment(document, "<div>sample string</div>");

        //Then
        assertThat(documentFragment.getFirstChild().getNodeType()).isEqualTo(Node.ELEMENT_NODE);
        assertThat(documentFragment.getFirstChild().getTextContent()).isEqualTo("sample string");
    }

    @Test
    public void parseXmlStringToDocumentFragment_broken_tag_will_not_raise_exception_test() throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document = documentBuilderFactory.newDocumentBuilder().newDocument();

        //When
        XmlUtils.parseXmlStringToDocumentFragment(document, "<div>sample string</broken_tag>");

        //Then

    }
}
