
package com.cjhxfund.autocode.wesklake.model.xsd.mysql;

import javax.xml.bind.annotation.*;


/**
 * <p>tableType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="tableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="columns" type="{}columnsType"/>
 *         &lt;element name="indexs" type="{}indexsType"/>
 *       &lt;/sequence>
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
@XmlType(name = "tableType", propOrder = {
    "columns",
    "indexs"
})
@XmlRootElement(name = "table")
public class TableType {

    @XmlElement(required = true)
    protected ColumnsType columns;
    @XmlElement(required = true)
    protected IndexsType indexs;
    @XmlAttribute(name = "enname")
    protected String enname;
    @XmlAttribute(name = "chname")
    protected String chname;

    /**
     * ��ȡcolumns���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ColumnsType }
     *     
     */
    public ColumnsType getColumns() {
        return columns;
    }

    /**
     * ����columns���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ColumnsType }
     *     
     */
    public void setColumns(ColumnsType value) {
        this.columns = value;
    }

    /**
     * ��ȡindexs���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link IndexsType }
     *     
     */
    public IndexsType getIndexs() {
        return indexs;
    }

    /**
     * ����indexs���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link IndexsType }
     *     
     */
    public void setIndexs(IndexsType value) {
        this.indexs = value;
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
