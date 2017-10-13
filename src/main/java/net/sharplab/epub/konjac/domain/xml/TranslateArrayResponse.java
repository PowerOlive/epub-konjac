package net.sharplab.epub.konjac.domain.xml;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * TranslateArrayResponseクラス
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TranslateArrayResponse", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.MT.Web.Service.V2")
public class TranslateArrayResponse implements Serializable {

    private static final long serialVersionUID = 6559167693439342617L;

    @XmlElement(name = "From", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.MT.Web.Service.V2")
    private String from;

    @XmlElementWrapper(name="OriginalTextSentenceLengths", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.MT.Web.Service.V2")
    @XmlElement(name = "int", namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays")
    private int[] originalTextSentenceLengths;

    @XmlElement(name ="TranslatedText", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.MT.Web.Service.V2")
    private String translatedText;

    @XmlElementWrapper(name="TranslatedTextSentenceLengths", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.MT.Web.Service.V2")
    @XmlElement(name = "int", namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays")
    private int[] translatedTextSentenceLengths;
}
