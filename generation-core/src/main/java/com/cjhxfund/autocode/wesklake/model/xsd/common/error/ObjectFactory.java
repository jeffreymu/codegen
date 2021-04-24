
package com.cjhxfund.autocode.wesklake.model.xsd.common.error;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cjhxfund.autocode.wesklake.model.xsd.common.error package.
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

    private final static QName _ArrayOfSystemError_QNAME = new QName("", "ArrayOfSystemError");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cjhxfund.autocode.wesklake.model.xsd.common.error
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfSystemErrorType }
     * 
     */
    public ArrayOfSystemErrorType createArrayOfSystemErrorType() {
        return new ArrayOfSystemErrorType();
    }

    /**
     * Create an instance of {@link SystemErrorType }
     * 
     */
    public SystemErrorType createSystemErrorType() {
        return new SystemErrorType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSystemErrorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ArrayOfSystemError")
    public JAXBElement<ArrayOfSystemErrorType> createArrayOfSystemError(ArrayOfSystemErrorType value) {
        return new JAXBElement<ArrayOfSystemErrorType>(_ArrayOfSystemError_QNAME, ArrayOfSystemErrorType.class, null, value);
    }

}
