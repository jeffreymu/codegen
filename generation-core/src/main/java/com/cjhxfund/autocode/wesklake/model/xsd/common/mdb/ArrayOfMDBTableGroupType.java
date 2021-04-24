
package com.cjhxfund.autocode.wesklake.model.xsd.common.mdb;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfMDBTableGroupType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMDBTableGroupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MDBTableGroup" type="{}MDBTableGroupType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMDBTableGroupType", propOrder = {
    "mdbTableGroup"
})
@XmlRootElement(name = "ArrayOfMDBTableGroup")
public class ArrayOfMDBTableGroupType {

    @XmlElement(name = "MDBTableGroup")
    protected List<MDBTableGroupType> mdbTableGroup;

    /**
     * Gets the value of the mdbTableGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mdbTableGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMDBTableGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDBTableGroupType }
     * 
     * 
     */
    public List<MDBTableGroupType> getMDBTableGroup() {
        if (mdbTableGroup == null) {
            mdbTableGroup = new ArrayList<MDBTableGroupType>();
        }
        return this.mdbTableGroup;
    }

}
