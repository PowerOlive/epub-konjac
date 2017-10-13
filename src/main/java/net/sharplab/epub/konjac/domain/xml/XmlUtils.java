package net.sharplab.epub.konjac.domain.xml;

import net.sharplab.epub.konjac.domain.exception.EPubContentHandlingException;
import nu.validator.htmlparser.dom.HtmlDocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;

/**
 * Utility for Xml handling
 */
public class XmlUtils {

    public static LSSerializer getLsSerializer(Document document) {
        DOMImplementationLS domImplementation = (DOMImplementationLS) document.getImplementation();
        LSSerializer lsSerializer = domImplementation.createLSSerializer();
        lsSerializer.getDomConfig().setParameter("xml-declaration", false);
        return lsSerializer;
    }

    public static String serialize(Node node){
        Document document = node instanceof Document ? (Document) node : node.getOwnerDocument();
        return getLsSerializer(document).writeToString(node);
    }

    public static Document load(File testFile) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(testFile);
    }

    public static Document parseXmlStringToDocument(String xmlString) {
        InputSource inputSource = new InputSource(new StringReader(xmlString));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(inputSource);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException(e);
        } catch (SAXException e) {
            throw new EPubContentHandlingException(e);
        }
    }


    /**
     * Parse xml string and return as {@link DocumentFragment}.
     * @param document document that the new {@link DocumentFragment} belongs
     * @param xmlString xml string
     * @return {@link DocumentFragment} represents the xml string
     */
    public static DocumentFragment parseXmlStringToDocumentFragment(Document document, String xmlString){
        try {
            HtmlDocumentBuilder builder = new HtmlDocumentBuilder();
            DocumentFragment fragment = builder.parseFragment(new InputSource(new StringReader(xmlString)), "");
            return (DocumentFragment) document.adoptNode(fragment);
        } catch (SAXException e) {
            throw new EPubContentHandlingException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
