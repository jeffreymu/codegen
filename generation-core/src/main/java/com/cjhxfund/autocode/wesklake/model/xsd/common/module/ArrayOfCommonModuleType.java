
package com.cjhxfund.autocode.wesklake.model.xsd.common.module;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfCommonModuleType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCommonModuleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CommonModule" type="{}CommonModuleType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCommonModuleType", propOrder = {
    "commonModule"
})
public class ArrayOfCommonModuleType {

    @XmlElement(name = "CommonModule", required = true)
    protected CommonModuleType commonModule;

    /**
     * ��ȡcommonModule���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CommonModuleType }
     *     
     */
    public CommonModuleType getCommonModule() {
        return commonModule;
    }

    /**
     * ����commonModule���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CommonModuleType }
     *     
     */
    public void setCommonModule(CommonModuleType value) {
        this.commonModule = value;
    }

}
