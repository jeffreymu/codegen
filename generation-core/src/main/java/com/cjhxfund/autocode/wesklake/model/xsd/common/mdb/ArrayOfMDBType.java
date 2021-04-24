
package com.cjhxfund.autocode.wesklake.model.xsd.common.mdb;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfMDBType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMDBType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MDB" type="{}MDBType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMDBType", propOrder = {
    "mdb"
})
@XmlRootElement(name = "ArrayOfMDB")
public class ArrayOfMDBType {

    @XmlElement(name = "MDB")
    protected List<MDBType> mdb;

    /**
     * Gets the value of the mdb property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mdb property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMDB().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDBType }
     * 
     * 
     */
    public List<MDBType> getMDB() {
        if (mdb == null) {
            mdb = new ArrayList<MDBType>();
        }
        return this.mdb;
    }

}
