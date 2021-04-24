
package com.cjhxfund.autocode.wesklake.model.xsd.rpc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SummaryType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡserviceType���Ե�ֵ��
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
     * ����serviceType���Ե�ֵ��
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
     * ��ȡuseMdb���Ե�ֵ��
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
     * ����useMdb���Ե�ֵ��
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
     * ��ȡgenMdbServiceAlso���Ե�ֵ��
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
     * ����genMdbServiceAlso���Ե�ֵ��
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
     * ��ȡgenMdbPagedService���Ե�ֵ��
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
     * ����genMdbPagedService���Ե�ֵ��
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
     * ��ȡmdbLockType���Ե�ֵ��
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
     * ����mdbLockType���Ե�ֵ��
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
     * ��ȡmdbLockKeys���Ե�ֵ��
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
     * ����mdbLockKeys���Ե�ֵ��
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
     * ��ȡloadType���Ե�ֵ��
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
     * ����loadType���Ե�ֵ��
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
     * ��ȡtable���Ե�ֵ��
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
     * ����table���Ե�ֵ��
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
     * ��ȡfTable���Ե�ֵ��
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
     * ����fTable���Ե�ֵ��
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
     * ��ȡforeignKeys���Ե�ֵ��
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
     * ����foreignKeys���Ե�ֵ��
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
     * ��ȡhandWrite���Ե�ֵ��
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
     * ����handWrite���Ե�ֵ��
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
     * ��ȡinterfaceMD5���Ե�ֵ��
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
     * ����interfaceMD5���Ե�ֵ��
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
     * ��ȡgenJavaCallerCode���Ե�ֵ��
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
     * ����genJavaCallerCode���Ե�ֵ��
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
     * ��ȡisThirdParty���Ե�ֵ��
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
     * ����isThirdParty���Ե�ֵ��
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
     * ��ȡisClient���Ե�ֵ��
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
     * ����isClient���Ե�ֵ��
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
     * ��ȡjavaControllerType���Ե�ֵ��
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
     * ����javaControllerType���Ե�ֵ��
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
     * ��ȡintfLogMode���Ե�ֵ��
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
     * ����intfLogMode���Ե�ֵ��
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
