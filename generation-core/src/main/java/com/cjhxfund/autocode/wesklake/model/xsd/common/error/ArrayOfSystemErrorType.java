
package com.cjhxfund.autocode.wesklake.model.xsd.common.error;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfSystemErrorType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSystemErrorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SystemError" type="{}SystemErrorType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSystemErrorType", propOrder = {
    "systemError"
})
@XmlRootElement(name = "ArrayOfSystemError")
public class ArrayOfSystemErrorType {

    @XmlElement(name = "SystemError")
    protected List<SystemErrorType> systemError;

    /**
     * Gets the value of the systemError property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the systemError property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSystemError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SystemErrorType }
     * 
     * 
     */
    public List<SystemErrorType> getSystemError() {
        if (systemError == null) {
            systemError = new ArrayList<SystemErrorType>();
        }
        return this.systemError;
    }

}
