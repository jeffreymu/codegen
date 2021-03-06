
package com.cjhxfund.autocode.wesklake.model.xsd.common.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfClientRoutingKeyRuleType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfClientRoutingKeyRuleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClientRoutingKeyRule" type="{}ClientRoutingKeyRuleType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfClientRoutingKeyRuleType", propOrder = {
    "clientRoutingKeyRule"
})
public class ArrayOfClientRoutingKeyRuleType {

    @XmlElement(name = "ClientRoutingKeyRule")
    protected List<ClientRoutingKeyRuleType> clientRoutingKeyRule;

    /**
     * Gets the value of the clientRoutingKeyRule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clientRoutingKeyRule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClientRoutingKeyRule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClientRoutingKeyRuleType }
     * 
     * 
     */
    public List<ClientRoutingKeyRuleType> getClientRoutingKeyRule() {
        if (clientRoutingKeyRule == null) {
            clientRoutingKeyRule = new ArrayList<ClientRoutingKeyRuleType>();
        }
        return this.clientRoutingKeyRule;
    }

}
