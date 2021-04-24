
package com.cjhxfund.autocode.wesklake.model.xsd.common.mdb;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfMDBTableType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMDBTableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MDBTable" type="{}MDBTableType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMDBTableType", propOrder = {
    "mdbTable"
})
@XmlRootElement(name = "ArrayOfMDBTable")
public class ArrayOfMDBTableType {

    @XmlElement(name = "MDBTable")
    protected List<MDBTableType> mdbTable;

    /**
     * Gets the value of the mdbTable property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mdbTable property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMDBTable().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDBTableType }
     * 
     * 
     */
    public List<MDBTableType> getMDBTable() {
        if (mdbTable == null) {
            mdbTable = new ArrayList<MDBTableType>();
        }
        return this.mdbTable;
    }

}
