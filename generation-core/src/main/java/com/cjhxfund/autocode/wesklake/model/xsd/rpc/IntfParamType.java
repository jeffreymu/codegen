
package com.cjhxfund.autocode.wesklake.model.xsd.rpc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>IntfParamType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="IntfParamType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ParamFields" type="{}ParamFieldsType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="IsDefault" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsRepeat" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Desciption" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsTableRelation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RelationTable" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IntfParamType", propOrder = {
    "paramFields"
})
public class IntfParamType {

    @XmlElement(name = "ParamFields", required = true)
    protected ParamFieldsType paramFields;
    @XmlAttribute(name = "IsDefault")
    protected String isDefault;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "IsRepeat")
    protected String isRepeat;
    @XmlAttribute(name = "Desciption")
    protected String desciption;
    @XmlAttribute(name = "IsTableRelation")
    protected String isTableRelation;
    @XmlAttribute(name = "RelationTable")
    protected String relationTable;

    /**
     * ��ȡparamFields���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ParamFieldsType }
     *     
     */
    public ParamFieldsType getParamFields() {
        return paramFields;
    }

    /**
     * ����paramFields���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ParamFieldsType }
     *     
     */
    public void setParamFields(ParamFieldsType value) {
        this.paramFields = value;
    }

    /**
     * ��ȡisDefault���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * ����isDefault���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsDefault(String value) {
        this.isDefault = value;
    }

    /**
     * ��ȡname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * ����name���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * ��ȡisRepeat���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRepeat() {
        return isRepeat;
    }

    /**
     * ����isRepeat���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRepeat(String value) {
        this.isRepeat = value;
    }

    /**
     * ��ȡdesciption���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesciption() {
        return desciption;
    }

    /**
     * ����desciption���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesciption(String value) {
        this.desciption = value;
    }

    /**
     * ��ȡisTableRelation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsTableRelation() {
        return isTableRelation;
    }

    /**
     * ����isTableRelation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsTableRelation(String value) {
        this.isTableRelation = value;
    }

    /**
     * ��ȡrelationTable���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationTable() {
        return relationTable;
    }

    /**
     * ����relationTable���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationTable(String value) {
        this.relationTable = value;
    }

}
