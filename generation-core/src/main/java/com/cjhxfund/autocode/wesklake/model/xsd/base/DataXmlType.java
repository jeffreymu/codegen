
package com.cjhxfund.autocode.wesklake.model.xsd.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DataXmlType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡdocumentElement���Ե�ֵ��
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
     * ����documentElement���Ե�ֵ��
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
