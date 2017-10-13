package net.sharplab.epub.konjac.domain.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * TranslateArrayResponseクラス
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ArrayOfTranslateArrayResponse", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.MT.Web.Service.V2")
public class ArrayOfTranslateArrayResponse {

    @XmlElement(name = "TranslateArrayResponse", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.MT.Web.Service.V2")
    private TranslateArrayResponse[] translateArrayResponses;
}
