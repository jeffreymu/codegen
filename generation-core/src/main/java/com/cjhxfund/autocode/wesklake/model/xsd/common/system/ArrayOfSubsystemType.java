
package com.cjhxfund.autocode.wesklake.model.xsd.common.system;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfSubsystemType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSubsystemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Subsystem" type="{}SubsystemType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSubsystemType", propOrder = {
    "subsystem"
})
@XmlRootElement(name = "ArrayOfSubsystem")
public class ArrayOfSubsystemType {

    @XmlElement(name = "Subsystem")
    protected List<SubsystemType> subsystem;

    /**
     * Gets the value of the subsystem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subsystem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubsystem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubsystemType }
     * 
     * 
     */
    public List<SubsystemType> getSubsystem() {
        if (subsystem == null) {
            subsystem = new ArrayList<SubsystemType>();
        }
        return this.subsystem;
    }

}
