
package com.cjhxfund.autocode.wesklake.model.xsd.table;

import javax.xml.bind.annotation.*;


/**
 * <p>TableType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="TableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Fields" type="{}FieldsType"/>
 *         &lt;element name="Indexs" type="{}IndexsType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Enname" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="HasHistoryTable" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="NeedMakeScript" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="UpdatePushMode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CheckMode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Remark" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableType", propOrder = {
    "fields",
    "indexs"
})
@XmlRootElement(name = "Table")
public class TableType {

    @XmlElement(name = "Fields", required = true)
    protected FieldsType fields;
    @XmlElement(name = "Indexs", required = true)
    protected IndexsType indexs;
    @XmlAttribute(name = "Enname")
    protected String enname;
    @XmlAttribute(name = "HasHistoryTable")
    protected String hasHistoryTable;
    @XmlAttribute(name = "NeedMakeScript")
    protected String needMakeScript;
    @XmlAttribute(name = "UpdatePushMode")
    protected String updatePushMode;
    @XmlAttribute(name = "CheckMode")
    protected String checkMode;
    @XmlAttribute(name = "Remark")
    protected String remark;

    /**
     * ��ȡfields���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link FieldsType }
     *     
     */
    public FieldsType getFields() {
        return fields;
    }

    /**
     * ����fields���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link FieldsType }
     *     
     */
    public void setFields(FieldsType value) {
        this.fields = value;
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
     * ��ȡhasHistoryTable���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHasHistoryTable() {
        return hasHistoryTable;
    }

    /**
     * ����hasHistoryTable���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHasHistoryTable(String value) {
        this.hasHistoryTable = value;
    }

    /**
     * ��ȡneedMakeScript���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNeedMakeScript() {
        return needMakeScript;
    }

    /**
     * ����needMakeScript���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNeedMakeScript(String value) {
        this.needMakeScript = value;
    }

    /**
     * ��ȡupdatePushMode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatePushMode() {
        return updatePushMode;
    }

    /**
     * ����updatePushMode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatePushMode(String value) {
        this.updatePushMode = value;
    }

    /**
     * ��ȡcheckMode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckMode() {
        return checkMode;
    }

    /**
     * ����checkMode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckMode(String value) {
        this.checkMode = value;
    }

    /**
     * ��ȡremark���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * ����remark���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

}
