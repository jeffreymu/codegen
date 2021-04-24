
package com.cjhxfund.autocode.wesklake.model.xsd.common.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CommonModuleType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CommonModuleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SubModules">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CommonModule" type="{}CommonModuleType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Files" type="{}FilesType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SubsystemId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommonModuleType", propOrder = {
    "subModules",
    "files"
})
public class CommonModuleType {

    @XmlElement(name = "SubModules", required = true)
    protected CommonModuleType.SubModules subModules;
    @XmlElement(name = "Files", required = true)
    protected FilesType files;
    @XmlAttribute(name = "SubsystemId")
    protected String subsystemId;
    @XmlAttribute(name = "Name")
    protected String name;

    /**
     * ��ȡsubModules���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CommonModuleType.SubModules }
     *     
     */
    public CommonModuleType.SubModules getSubModules() {
        return subModules;
    }

    /**
     * ����subModules���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CommonModuleType.SubModules }
     *     
     */
    public void setSubModules(CommonModuleType.SubModules value) {
        this.subModules = value;
    }

    /**
     * ��ȡfiles���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link FilesType }
     *     
     */
    public FilesType getFiles() {
        return files;
    }

    /**
     * ����files���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link FilesType }
     *     
     */
    public void setFiles(FilesType value) {
        this.files = value;
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="CommonModule" type="{}CommonModuleType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "content"
    })
    public static class SubModules {

        @XmlElementRef(name = "CommonModule", type = JAXBElement.class, required = false)
        @XmlMixed
        protected List<Serializable> content;

        /**
         * Gets the value of the content property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link CommonModuleType }{@code >}
         * {@link String }
         * 
         * 
         */
        public List<Serializable> getContent() {
            if (content == null) {
                content = new ArrayList<Serializable>();
            }
            return this.content;
        }

    }

}
