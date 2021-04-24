
package com.cjhxfund.autocode.wesklake.model.xsd.common.dict;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfDictType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDictType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Dict" type="{}DictType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDictType", propOrder = {
    "dict"
})
@XmlRootElement(name = "ArrayOfDict")
public class ArrayOfDictType {

    @XmlElement(name = "Dict")
    protected List<DictType> dict;

    /**
     * Gets the value of the dict property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dict property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDict().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DictType }
     * 
     * 
     */
    public List<DictType> getDict() {
        if (dict == null) {
            dict = new ArrayList<DictType>();
        }
        return this.dict;
    }

}
