
package com.cjhxfund.autocode.wesklake.model.xsd.common.db;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cjhxfund.autocode.wesklake.model.xsd.common.db package.
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

    private final static QName _ModuleProperty_QNAME = new QName("", "ModuleProperty");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cjhxfund.autocode.wesklake.model.xsd.common.db
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ModulePropertyType }
     * 
     */
    public ModulePropertyType createModulePropertyType() {
        return new ModulePropertyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModulePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ModuleProperty")
    public JAXBElement<ModulePropertyType> createModuleProperty(ModulePropertyType value) {
        return new JAXBElement<ModulePropertyType>(_ModuleProperty_QNAME, ModulePropertyType.class, null, value);
    }

}
