
package com.cjhxfund.autocode.wesklake.model.xsd.rpc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>IntfParamFieldType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="IntfParamFieldType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsNeedEdit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Remark" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CheckWay" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CheckRange" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CheckExtendRange" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IntfParamFieldType", propOrder = {
    "value"
})
public class IntfParamFieldType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "IsNeedEdit")
    protected String isNeedEdit;
    @XmlAttribute(name = "Remark")
    protected String remark;
    @XmlAttribute(name = "CheckWay")
    protected String checkWay;
    @XmlAttribute(name = "CheckRange")
    protected String checkRange;
    @XmlAttribute(name = "CheckExtendRange")
    protected String checkExtendRange;

    /**
     * ��ȡvalue���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * ����value���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
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
     * ��ȡisNeedEdit���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsNeedEdit() {
        return isNeedEdit;
    }

    /**
     * ����isNeedEdit���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsNeedEdit(String value) {
        this.isNeedEdit = value;
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
     * ��ȡcheckWay���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckWay() {
        return checkWay;
    }

    /**
     * ����checkWay���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckWay(String value) {
        this.checkWay = value;
    }

    /**
     * ��ȡcheckRange���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckRange() {
        return checkRange;
    }

    /**
     * ����checkRange���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckRange(String value) {
        this.checkRange = value;
    }

    /**
     * ��ȡcheckExtendRange���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckExtendRange() {
        return checkExtendRange;
    }

    /**
     * ����checkExtendRange���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckExtendRange(String value) {
        this.checkExtendRange = value;
    }

}
