
package com.cjhxfund.autocode.wesklake.model.xsd.base;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DocumentElementType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DocumentElementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tetfmsysparam" type="{}tetfmsysparamType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentElementType", propOrder = {
    "tetfmsysparam"
})
public class DocumentElementType {

    protected List<TetfmsysparamType> tetfmsysparam;

    /**
     * Gets the value of the tetfmsysparam property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tetfmsysparam property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTetfmsysparam().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TetfmsysparamType }
     * 
     * 
     */
    public List<TetfmsysparamType> getTetfmsysparam() {
        if (tetfmsysparam == null) {
            tetfmsysparam = new ArrayList<TetfmsysparamType>();
        }
        return this.tetfmsysparam;
    }

}
