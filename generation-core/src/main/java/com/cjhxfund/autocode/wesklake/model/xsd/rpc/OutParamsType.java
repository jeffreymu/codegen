
package com.cjhxfund.autocode.wesklake.model.xsd.rpc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>OutParamsType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="OutParamsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IntfParam" type="{}IntfParamType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OutParamsType", propOrder = {
    "intfParam"
})
public class OutParamsType {

    @XmlElement(name = "IntfParam", required = true)
    protected IntfParamType intfParam;

    /**
     * ��ȡintfParam���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link IntfParamType }
     *     
     */
    public IntfParamType getIntfParam() {
        return intfParam;
    }

    /**
     * ����intfParam���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link IntfParamType }
     *     
     */
    public void setIntfParam(IntfParamType value) {
        this.intfParam = value;
    }

}
