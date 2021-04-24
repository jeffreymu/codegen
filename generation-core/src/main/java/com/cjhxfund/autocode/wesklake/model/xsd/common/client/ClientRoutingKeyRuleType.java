
package com.cjhxfund.autocode.wesklake.model.xsd.common.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>ClientRoutingKeyRuleType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ClientRoutingKeyRuleType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="SubsystemId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ModuleChName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ServiceEnName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RoutingKey" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClientRoutingKeyRuleType", propOrder = {
    "value"
})
public class ClientRoutingKeyRuleType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "SubsystemId")
    protected String subsystemId;
    @XmlAttribute(name = "ModuleChName")
    protected String moduleChName;
    @XmlAttribute(name = "ServiceEnName")
    protected String serviceEnName;
    @XmlAttribute(name = "RoutingKey")
    protected String routingKey;
    @XmlAttribute(name = "CreationDate")
    protected String creationDate;

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
     * ��ȡsubsystemId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsystemId() {
        return subsystemId;
    }

    /**
     * ����subsystemId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsystemId(String value) {
        this.subsystemId = value;
    }

    /**
     * ��ȡmoduleChName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModuleChName() {
        return moduleChName;
    }

    /**
     * ����moduleChName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModuleChName(String value) {
        this.moduleChName = value;
    }

    /**
     * ��ȡserviceEnName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceEnName() {
        return serviceEnName;
    }

    /**
     * ����serviceEnName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceEnName(String value) {
        this.serviceEnName = value;
    }

    /**
     * ��ȡroutingKey���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoutingKey() {
        return routingKey;
    }

    /**
     * ����routingKey���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoutingKey(String value) {
        this.routingKey = value;
    }

    /**
     * ��ȡcreationDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * ����creationDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationDate(String value) {
        this.creationDate = value;
    }

}
