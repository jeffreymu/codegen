
package com.cjhxfund.autocode.wesklake.model.xsd.base;

import javax.xml.bind.annotation.*;


/**
 * <p>BasicDataType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="BasicDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataXml" type="{}DataXmlType"/>
 *         &lt;element name="Remark" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RelationTableName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="UpdateMode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MakeClentDataFile" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BindDict" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BindDictId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BindFieldForDictItemId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BindFieldForDictItemChName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BindFieldForDictItemEnName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasicDataType", propOrder = {
    "dataXml",
    "remark"
})
@XmlRootElement(name = "BasicData")
public class BasicDataType {

    @XmlElement(name = "DataXml", required = true)
    protected DataXmlType dataXml;
    @XmlElement(name = "Remark", required = true)
    protected String remark;
    @XmlAttribute(name = "RelationTableName")
    protected String relationTableName;
    @XmlAttribute(name = "UpdateMode")
    protected String updateMode;
    @XmlAttribute(name = "MakeClentDataFile")
    protected String makeClentDataFile;
    @XmlAttribute(name = "BindDict")
    protected String bindDict;
    @XmlAttribute(name = "BindDictId")
    protected String bindDictId;
    @XmlAttribute(name = "BindFieldForDictItemId")
    protected String bindFieldForDictItemId;
    @XmlAttribute(name = "BindFieldForDictItemChName")
    protected String bindFieldForDictItemChName;
    @XmlAttribute(name = "BindFieldForDictItemEnName")
    protected String bindFieldForDictItemEnName;

    /**
     * ��ȡdataXml���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link DataXmlType }
     *     
     */
    public DataXmlType getDataXml() {
        return dataXml;
    }

    /**
     * ����dataXml���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link DataXmlType }
     *     
     */
    public void setDataXml(DataXmlType value) {
        this.dataXml = value;
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

    /**
     * ��ȡrelationTableName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationTableName() {
        return relationTableName;
    }

    /**
     * ����relationTableName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationTableName(String value) {
        this.relationTableName = value;
    }

    /**
     * ��ȡupdateMode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateMode() {
        return updateMode;
    }

    /**
     * ����updateMode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateMode(String value) {
        this.updateMode = value;
    }

    /**
     * ��ȡmakeClentDataFile���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMakeClentDataFile() {
        return makeClentDataFile;
    }

    /**
     * ����makeClentDataFile���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMakeClentDataFile(String value) {
        this.makeClentDataFile = value;
    }

    /**
     * ��ȡbindDict���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindDict() {
        return bindDict;
    }

    /**
     * ����bindDict���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindDict(String value) {
        this.bindDict = value;
    }

    /**
     * ��ȡbindDictId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindDictId() {
        return bindDictId;
    }

    /**
     * ����bindDictId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindDictId(String value) {
        this.bindDictId = value;
    }

    /**
     * ��ȡbindFieldForDictItemId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindFieldForDictItemId() {
        return bindFieldForDictItemId;
    }

    /**
     * ����bindFieldForDictItemId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindFieldForDictItemId(String value) {
        this.bindFieldForDictItemId = value;
    }

    /**
     * ��ȡbindFieldForDictItemChName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindFieldForDictItemChName() {
        return bindFieldForDictItemChName;
    }

    /**
     * ����bindFieldForDictItemChName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindFieldForDictItemChName(String value) {
        this.bindFieldForDictItemChName = value;
    }

    /**
     * ��ȡbindFieldForDictItemEnName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindFieldForDictItemEnName() {
        return bindFieldForDictItemEnName;
    }

    /**
     * ����bindFieldForDictItemEnName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindFieldForDictItemEnName(String value) {
        this.bindFieldForDictItemEnName = value;
    }

}
