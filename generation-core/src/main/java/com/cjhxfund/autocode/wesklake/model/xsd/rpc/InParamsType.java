
package com.cjhxfund.autocode.wesklake.model.xsd.rpc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>InParamsType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="InParamsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IntfParam" type="{}IntfParamType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InParamsType", propOrder = {
    "intfParam"
})
public class InParamsType {

    @XmlElement(name = "IntfParam")
    protected List<IntfParamType> intfParam;

    /**
     * Gets the value of the intfParam property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the intfParam property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIntfParam().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IntfParamType }
     * 
     * 
     */
    public List<IntfParamType> getIntfParam() {
        if (intfParam == null) {
            intfParam = new ArrayList<IntfParamType>();
        }
        return this.intfParam;
    }

}
