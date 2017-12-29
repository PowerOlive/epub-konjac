package net.sharplab.epub.konjac.domain.xml;

import net.sharplab.epub.konjac.domain.model.Options;
import net.sharplab.epub.konjac.domain.model.Text;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.Serializable;
import java.util.List;

/**
 * TranslateArrayRequestクラス
 */
public class TranslateArrayRequest implements Serializable {

    private static final long serialVersionUID = -243454571262060205L;

    private String appId;
    private String from;
    private String to;
    private Options options;
    private List<Text> texts;

    public TranslateArrayRequest(String appId, String from, String to, Options options, List<Text> texts){
        this.appId = appId;
        this.from = from;
        this.to = to;
        this.options = options;
        this.texts = texts;
    }

    public String toXmlString() {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootNode = doc.createElement("TranslateArrayRequest");
            rootNode.appendChild(doc.createElement("AppId")).appendChild(doc.createTextNode(appId));
            rootNode.appendChild(doc.createElement("From")).appendChild(doc.createTextNode(from));
            Element optionsElement = doc.createElement("Options");
            if(options.getCategory() != null){
                optionsElement.appendChild(doc.createElementNS("http://schemas.datacontract.org/2004/07/Microsoft.MT.Web.Service.V2","Category")).appendChild(doc.createTextNode(options.getCategory()));
            }
            if(options.getContentType() != null){
                optionsElement.appendChild(doc.createElementNS("http://schemas.datacontract.org/2004/07/Microsoft.MT.Web.Service.V2","ContentType")).appendChild(doc.createTextNode(options.getContentType()));
            }
            rootNode.appendChild(optionsElement);
            Element textsElement = doc.createElement("Texts");
            texts.forEach(text -> textsElement.appendChild(doc.createElementNS("http://schemas.microsoft.com/2003/10/Serialization/Arrays","string")).appendChild(doc.createTextNode(text.getValue())));
            rootNode.appendChild(textsElement);
            rootNode.appendChild(doc.createElement("To")).appendChild(doc.createTextNode(to));
            rootNode.appendChild(doc.createElement("MaxTranslations")).appendChild(doc.createTextNode("3"));
            doc.appendChild(rootNode);
            return XmlUtils.serialize(doc);
        } catch (ParserConfigurationException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public String toString(){
        return toXmlString();
    }
}
