
package com.cjhxfund.autocode.wesklake.model.xsd.common.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cjhxfund.autocode.wesklake.model.xsd.common.client package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ArrayOfClientRoutingKeyRule_QNAME = new QName("", "ArrayOfClientRoutingKeyRule");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cjhxfund.autocode.wesklake.model.xsd.common.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfClientRoutingKeyRuleType }
     * 
     */
    public ArrayOfClientRoutingKeyRuleType createArrayOfClientRoutingKeyRuleType() {
        return new ArrayOfClientRoutingKeyRuleType();
    }

    /**
     * Create an instance of {@link ClientRoutingKeyRuleType }
     * 
     */
    public ClientRoutingKeyRuleType createClientRoutingKeyRuleType() {
        return new ClientRoutingKeyRuleType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfClientRoutingKeyRuleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ArrayOfClientRoutingKeyRule")
    public JAXBElement<ArrayOfClientRoutingKeyRuleType> createArrayOfClientRoutingKeyRule(ArrayOfClientRoutingKeyRuleType value) {
        return new JAXBElement<ArrayOfClientRoutingKeyRuleType>(_ArrayOfClientRoutingKeyRule_QNAME, ArrayOfClientRoutingKeyRuleType.class, null, value);
    }

}
