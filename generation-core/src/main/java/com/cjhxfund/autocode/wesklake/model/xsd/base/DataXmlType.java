
package com.cjhxfund.autocode.wesklake.model.xsd.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DataXmlType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DataXmlType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DocumentElement" type="{}DocumentElementType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataXmlType", propOrder = {
    "documentElement"
})
public class DataXmlType {

    @XmlElement(name = "DocumentElement", required = true)
    protected DocumentElementType documentElement;

    /**
     * 获取documentElement属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DocumentElementType }
     *     
     */
    public DocumentElementType getDocumentElement() {
        return documentElement;
    }

    /**
     * 设置documentElement属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentElementType }
     *     
     */
    public void setDocumentElement(DocumentElementType value) {
        this.documentElement = value;
    }

}
