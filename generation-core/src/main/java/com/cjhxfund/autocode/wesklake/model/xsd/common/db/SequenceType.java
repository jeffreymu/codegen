
package com.cjhxfund.autocode.wesklake.model.xsd.common.db;

import javax.xml.bind.annotation.*;


/**
 * <p>SequenceType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="SequenceType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="EnName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ChName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="StartValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DailyReset" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsRebootContinue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ForOracle" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ForMDB" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MDBList" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RecoveryFrom" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RecoverySqlOracle" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RecoverySqlMySql" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Remark" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SequenceType", propOrder = {
    "value"
})
public class SequenceType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "Id")
    protected String id;
    @XmlAttribute(name = "EnName")
    protected String enName;
    @XmlAttribute(name = "ChName")
    protected String chName;
    @XmlAttribute(name = "StartValue")
    protected String startValue;
    @XmlAttribute(name = "DailyReset")
    protected String dailyReset;
    @XmlAttribute(name = "IsRebootContinue")
    protected String isRebootContinue;
    @XmlAttribute(name = "ForOracle")
    protected String forOracle;
    @XmlAttribute(name = "ForMDB")
    protected String forMDB;
    @XmlAttribute(name = "MDBList")
    protected String mdbList;
    @XmlAttribute(name = "RecoveryFrom")
    protected String recoveryFrom;
    @XmlAttribute(name = "RecoverySqlOracle")
    protected String recoverySqlOracle;
    @XmlAttribute(name = "RecoverySqlMySql")
    protected String recoverySqlMySql;
    @XmlAttribute(name = "Remark")
    protected String remark;

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
     * ��ȡid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * ��ȡenName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnName() {
        return enName;
    }

    /**
     * ����enName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnName(String value) {
        this.enName = value;
    }

    /**
     * ��ȡchName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChName() {
        return chName;
    }

    /**
     * ����chName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChName(String value) {
        this.chName = value;
    }

    /**
     * ��ȡstartValue���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartValue() {
        return startValue;
    }

    /**
     * ����startValue���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartValue(String value) {
        this.startValue = value;
    }

    /**
     * ��ȡdailyReset���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDailyReset() {
        return dailyReset;
    }

    /**
     * ����dailyReset���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDailyReset(String value) {
        this.dailyReset = value;
    }

    /**
     * ��ȡisRebootContinue���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRebootContinue() {
        return isRebootContinue;
    }

    /**
     * ����isRebootContinue���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRebootContinue(String value) {
        this.isRebootContinue = value;
    }

    /**
     * ��ȡforOracle���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForOracle() {
        return forOracle;
    }

    /**
     * ����forOracle���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForOracle(String value) {
        this.forOracle = value;
    }

    /**
     * ��ȡforMDB���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForMDB() {
        return forMDB;
    }

    /**
     * ����forMDB���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForMDB(String value) {
        this.forMDB = value;
    }

    /**
     * ��ȡmdbList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMDBList() {
        return mdbList;
    }

    /**
     * ����mdbList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMDBList(String value) {
        this.mdbList = value;
    }

    /**
     * ��ȡrecoveryFrom���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecoveryFrom() {
        return recoveryFrom;
    }

    /**
     * ����recoveryFrom���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecoveryFrom(String value) {
        this.recoveryFrom = value;
    }

    /**
     * ��ȡrecoverySqlOracle���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecoverySqlOracle() {
        return recoverySqlOracle;
    }

    /**
     * ����recoverySqlOracle���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecoverySqlOracle(String value) {
        this.recoverySqlOracle = value;
    }

    /**
     * ��ȡrecoverySqlMySql���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecoverySqlMySql() {
        return recoverySqlMySql;
    }

    /**
     * ����recoverySqlMySql���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecoverySqlMySql(String value) {
        this.recoverySqlMySql = value;
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
