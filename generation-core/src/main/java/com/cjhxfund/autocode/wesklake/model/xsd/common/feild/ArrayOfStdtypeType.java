
package com.cjhxfund.autocode.wesklake.model.xsd.common.feild;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfStdtypeType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfStdtypeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Stdtype" type="{}StdtypeType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfStdtypeType", propOrder = {
    "stdtype"
})
@XmlRootElement(name = "ArrayOfStdtype")
public class ArrayOfStdtypeType {

    @XmlElement(name = "Stdtype")
    protected List<StdtypeType> stdtype;

    /**
     * Gets the value of the stdtype property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stdtype property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStdtype().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StdtypeType }
     * 
     * 
     */
    public List<StdtypeType> getStdtype() {
        if (stdtype == null) {
            stdtype = new ArrayList<StdtypeType>();
        }
        return this.stdtype;
    }

}
