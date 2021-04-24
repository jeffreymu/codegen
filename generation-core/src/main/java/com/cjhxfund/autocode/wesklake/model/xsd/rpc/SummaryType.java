
package com.cjhxfund.autocode.wesklake.model.xsd.rpc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SummaryType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SummaryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Desciption" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UseMdb" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GenMdbServiceAlso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GenMdbPagedService" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MDBLockType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MDBLockKeys" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LoadType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Table" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FTable" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ForeignKeys" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HandWrite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InterfaceMD5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GenJavaCallerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsThirdParty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsClient" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="JavaControllerType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IntfLogMode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SummaryType", propOrder = {
    "name",
    "desciption",
    "serviceType",
    "useMdb",
    "genMdbServiceAlso",
    "genMdbPagedService",
    "mdbLockType",
    "mdbLockKeys",
    "loadType",
    "table",
    "fTable",
    "foreignKeys",
    "handWrite",
    "interfaceMD5",
    "genJavaCallerCode",
    "isThirdParty",
    "isClient",
    "javaControllerType",
    "intfLogMode"
})
public class SummaryType {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Desciption", required = true)
    protected String desciption;
    @XmlElement(name = "ServiceType", required = true)
    protected String serviceType;
    @XmlElement(name = "UseMdb", required = true)
    protected String useMdb;
    @XmlElement(name = "GenMdbServiceAlso", required = true)
    protected String genMdbServiceAlso;
    @XmlElement(name = "GenMdbPagedService", required = true)
    protected String genMdbPagedService;
    @XmlElement(name = "MDBLockType", required = true)
    protected String mdbLockType;
    @XmlElement(name = "MDBLockKeys", required = true)
    protected String mdbLockKeys;
    @XmlElement(name = "LoadType", required = true)
    protected String loadType;
    @XmlElement(name = "Table", required = true)
    protected String table;
    @XmlElement(name = "FTable", required = true)
    protected String fTable;
    @XmlElement(name = "ForeignKeys", required = true)
    protected String foreignKeys;
    @XmlElement(name = "HandWrite", required = true)
    protected String handWrite;
    @XmlElement(name = "InterfaceMD5", required = true)
    protected String interfaceMD5;
    @XmlElement(name = "GenJavaCallerCode", required = true)
    protected String genJavaCallerCode;
    @XmlElement(name = "IsThirdParty", required = true)
    protected String isThirdParty;
    @XmlElement(name = "IsClient", required = true)
    protected String isClient;
    @XmlElement(name = "JavaControllerType", required = true)
    protected String javaControllerType;
    @XmlElement(name = "IntfLogMode", required = true)
    protected String intfLogMode;

    /**
     * 获取name属性的值。
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
     * 设置name属性的值。
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
     * 获取desciption属性的值。
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
     * 设置desciption属性的值。
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
     * 获取serviceType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * 设置serviceType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceType(String value) {
        this.serviceType = value;
    }

    /**
     * 获取useMdb属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseMdb() {
        return useMdb;
    }

    /**
     * 设置useMdb属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseMdb(String value) {
        this.useMdb = value;
    }

    /**
     * 获取genMdbServiceAlso属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenMdbServiceAlso() {
        return genMdbServiceAlso;
    }

    /**
     * 设置genMdbServiceAlso属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenMdbServiceAlso(String value) {
        this.genMdbServiceAlso = value;
    }

    /**
     * 获取genMdbPagedService属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenMdbPagedService() {
        return genMdbPagedService;
    }

    /**
     * 设置genMdbPagedService属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenMdbPagedService(String value) {
        this.genMdbPagedService = value;
    }

    /**
     * 获取mdbLockType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMDBLockType() {
        return mdbLockType;
    }

    /**
     * 设置mdbLockType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMDBLockType(String value) {
        this.mdbLockType = value;
    }

    /**
     * 获取mdbLockKeys属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMDBLockKeys() {
        return mdbLockKeys;
    }

    /**
     * 设置mdbLockKeys属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMDBLockKeys(String value) {
        this.mdbLockKeys = value;
    }

    /**
     * 获取loadType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoadType() {
        return loadType;
    }

    /**
     * 设置loadType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoadType(String value) {
        this.loadType = value;
    }

    /**
     * 获取table属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTable() {
        return table;
    }

    /**
     * 设置table属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTable(String value) {
        this.table = value;
    }

    /**
     * 获取fTable属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFTable() {
        return fTable;
    }

    /**
     * 设置fTable属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFTable(String value) {
        this.fTable = value;
    }

    /**
     * 获取foreignKeys属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForeignKeys() {
        return foreignKeys;
    }

    /**
     * 设置foreignKeys属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForeignKeys(String value) {
        this.foreignKeys = value;
    }

    /**
     * 获取handWrite属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandWrite() {
        return handWrite;
    }

    /**
     * 设置handWrite属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandWrite(String value) {
        this.handWrite = value;
    }

    /**
     * 获取interfaceMD5属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterfaceMD5() {
        return interfaceMD5;
    }

    /**
     * 设置interfaceMD5属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterfaceMD5(String value) {
        this.interfaceMD5 = value;
    }

    /**
     * 获取genJavaCallerCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenJavaCallerCode() {
        return genJavaCallerCode;
    }

    /**
     * 设置genJavaCallerCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenJavaCallerCode(String value) {
        this.genJavaCallerCode = value;
    }

    /**
     * 获取isThirdParty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsThirdParty() {
        return isThirdParty;
    }

    /**
     * 设置isThirdParty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsThirdParty(String value) {
        this.isThirdParty = value;
    }

    /**
     * 获取isClient属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsClient() {
        return isClient;
    }

    /**
     * 设置isClient属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsClient(String value) {
        this.isClient = value;
    }

    /**
     * 获取javaControllerType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJavaControllerType() {
        return javaControllerType;
    }

    /**
     * 设置javaControllerType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJavaControllerType(String value) {
        this.javaControllerType = value;
    }

    /**
     * 获取intfLogMode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntfLogMode() {
        return intfLogMode;
    }

    /**
     * 设置intfLogMode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntfLogMode(String value) {
        this.intfLogMode = value;
    }

}
