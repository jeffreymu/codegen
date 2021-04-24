
package com.cjhxfund.autocode.wesklake.model.xsd.table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>IndexsType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="IndexsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TableIndex" type="{}TableIndexType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndexsType", propOrder = {
    "tableIndex"
})
public class IndexsType {

    @XmlElement(name = "TableIndex")
    protected List<TableIndexType> tableIndex;

    /**
     * Gets the value of the tableIndex property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tableIndex property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTableIndex().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TableIndexType }
     * 
     * 
     */
    public List<TableIndexType> getTableIndex() {
        if (tableIndex == null) {
            tableIndex = new ArrayList<TableIndexType>();
        }
        return this.tableIndex;
    }

}
