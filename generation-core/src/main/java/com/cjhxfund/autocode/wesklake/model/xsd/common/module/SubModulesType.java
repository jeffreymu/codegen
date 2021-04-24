
package com.cjhxfund.autocode.wesklake.model.xsd.common.module;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SubModulesType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="SubModulesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CommonModule" type="{}CommonModuleType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubModulesType", propOrder = {
    "commonModule"
})
public class SubModulesType {

    @XmlElement(name = "CommonModule")
    protected List<CommonModuleType> commonModule;

    /**
     * Gets the value of the commonModule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commonModule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommonModule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommonModuleType }
     * 
     * 
     */
    public List<CommonModuleType> getCommonModule() {
        if (commonModule == null) {
            commonModule = new ArrayList<CommonModuleType>();
        }
        return this.commonModule;
    }

}
