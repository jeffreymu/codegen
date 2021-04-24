
package com.cjhxfund.autocode.wesklake.model.xsd.mysql.init;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>dataType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="dataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="row" type="{}rowType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="upgrade_mode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="enname" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="chname" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataType", propOrder = {
    "row"
})
@XmlRootElement(name = "data")
public class DataType {

    protected List<RowType> row;
    @XmlAttribute(name = "upgrade_mode")
    protected String upgradeMode;
    @XmlAttribute(name = "enname")
    protected String enname;
    @XmlAttribute(name = "chname")
    protected String chname;

    /**
     * Gets the value of the row property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the row property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RowType }
     * 
     * 
     */
    public List<RowType> getRow() {
        if (row == null) {
            row = new ArrayList<RowType>();
        }
        return this.row;
    }

    /**
     * set rows
     * @param row
     */
    public void setRow(List<RowType> row) {
        this.row = row;
    }

    /**
     * ��ȡupgradeMode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpgradeMode() {
        return upgradeMode;
    }

    /**
     * ����upgradeMode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpgradeMode(String value) {
        this.upgradeMode = value;
    }

    /**
     * ��ȡenname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnname() {
        return enname;
    }

    /**
     * ����enname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnname(String value) {
        this.enname = value;
    }

    /**
     * ��ȡchname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChname() {
        return chname;
    }

    /**
     * ����chname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChname(String value) {
        this.chname = value;
    }

}
