
package com.cjhxfund.autocode.wesklake.model.xsd.common.mdb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>MDBTableType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="MDBTableType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="DatabaseName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ChName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="UseRedo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="LockType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Redo2DB" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PushUpdate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="LockFields" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PartitionField" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="GroupId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Remark" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MDBTableType", propOrder = {
    "value"
})
public class MDBTableType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "DatabaseName")
    protected String databaseName;
    @XmlAttribute(name = "Id")
    protected String id;
    @XmlAttribute(name = "ChName")
    protected String chName;
    @XmlAttribute(name = "UseRedo")
    protected String useRedo;
    @XmlAttribute(name = "LockType")
    protected String lockType;
    @XmlAttribute(name = "Redo2DB")
    protected String redo2DB;
    @XmlAttribute(name = "PushUpdate")
    protected String pushUpdate;
    @XmlAttribute(name = "LockFields")
    protected String lockFields;
    @XmlAttribute(name = "PartitionField")
    protected String partitionField;
    @XmlAttribute(name = "GroupId")
    protected String groupId;
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
     * ��ȡdatabaseName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * ����databaseName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatabaseName(String value) {
        this.databaseName = value;
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
     * ��ȡuseRedo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseRedo() {
        return useRedo;
    }

    /**
     * ����useRedo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseRedo(String value) {
        this.useRedo = value;
    }

    /**
     * ��ȡlockType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLockType() {
        return lockType;
    }

    /**
     * ����lockType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLockType(String value) {
        this.lockType = value;
    }

    /**
     * ��ȡredo2DB���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedo2DB() {
        return redo2DB;
    }

    /**
     * ����redo2DB���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedo2DB(String value) {
        this.redo2DB = value;
    }

    /**
     * ��ȡpushUpdate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPushUpdate() {
        return pushUpdate;
    }

    /**
     * ����pushUpdate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPushUpdate(String value) {
        this.pushUpdate = value;
    }

    /**
     * ��ȡlockFields���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLockFields() {
        return lockFields;
    }

    /**
     * ����lockFields���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLockFields(String value) {
        this.lockFields = value;
    }

    /**
     * ��ȡpartitionField���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartitionField() {
        return partitionField;
    }

    /**
     * ����partitionField���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartitionField(String value) {
        this.partitionField = value;
    }

    /**
     * ��ȡgroupId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * ����groupId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupId(String value) {
        this.groupId = value;
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
