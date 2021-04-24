
package com.cjhxfund.autocode.wesklake.model.xsd.common.feild;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfStdfieldType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfStdfieldType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Stdfield" type="{}StdfieldType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfStdfieldType", propOrder = {
    "stdfield"
})
@XmlRootElement(name = "ArrayOfStdfield")
public class ArrayOfStdfieldType {

    @XmlElement(name = "Stdfield")
    protected List<StdfieldType> stdfield;

    /**
     * Gets the value of the stdfield property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stdfield property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStdfield().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StdfieldType }
     * 
     * 
     */
    public List<StdfieldType> getStdfield() {
        if (stdfield == null) {
            stdfield = new ArrayList<StdfieldType>();
        }
        return this.stdfield;
    }

}
