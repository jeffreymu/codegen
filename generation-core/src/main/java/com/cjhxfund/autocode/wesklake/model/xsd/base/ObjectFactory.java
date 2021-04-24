
package com.cjhxfund.autocode.wesklake.model.xsd.base;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cjhxfund.autocode.wesklake.model.xsd.base package. 
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

    private final static QName _BasicData_QNAME = new QName("", "BasicData");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cjhxfund.autocode.wesklake.model.xsd.base
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BasicDataType }
     * 
     */
    public BasicDataType createBasicDataType() {
        return new BasicDataType();
    }

    /**
     * Create an instance of {@link TetfmsysparamType }
     * 
     */
    public TetfmsysparamType createTetfmsysparamType() {
        return new TetfmsysparamType();
    }

    /**
     * Create an instance of {@link DocumentElementType }
     * 
     */
    public DocumentElementType createDocumentElementType() {
        return new DocumentElementType();
    }

    /**
     * Create an instance of {@link DataXmlType }
     * 
     */
    public DataXmlType createDataXmlType() {
        return new DataXmlType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BasicDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "BasicData")
    public JAXBElement<BasicDataType> createBasicData(BasicDataType value) {
        return new JAXBElement<BasicDataType>(_BasicData_QNAME, BasicDataType.class, null, value);
    }

}
