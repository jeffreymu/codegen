
package com.cjhxfund.autocode.wesklake.model.xsd.common.system;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cjhxfund.autocode.wesklake.model.xsd.common.system package.
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

    private final static QName _ArrayOfSubsystem_QNAME = new QName("", "ArrayOfSubsystem");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cjhxfund.autocode.wesklake.model.xsd.common.system
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfSubsystemType }
     * 
     */
    public ArrayOfSubsystemType createArrayOfSubsystemType() {
        return new ArrayOfSubsystemType();
    }

    /**
     * Create an instance of {@link SubsystemType }
     * 
     */
    public SubsystemType createSubsystemType() {
        return new SubsystemType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSubsystemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ArrayOfSubsystem")
    public JAXBElement<ArrayOfSubsystemType> createArrayOfSubsystem(ArrayOfSubsystemType value) {
        return new JAXBElement<ArrayOfSubsystemType>(_ArrayOfSubsystem_QNAME, ArrayOfSubsystemType.class, null, value);
    }

}
