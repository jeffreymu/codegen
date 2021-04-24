
package com.cjhxfund.autocode.wesklake.model.xsd.common.mdb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cjhxfund.autocode.wesklake.model.xsd.common.mdb package.
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

    private final static QName _ArrayOfMDB_QNAME = new QName("", "ArrayOfMDB");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cjhxfund.autocode.wesklake.model.xsd.common.mdb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfMDBType }
     * 
     */
    public ArrayOfMDBType createArrayOfMDBType() {
        return new ArrayOfMDBType();
    }

    /**
     * Create an instance of {@link MDBType }
     * 
     */
    public MDBType createMDBType() {
        return new MDBType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMDBType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ArrayOfMDB")
    public JAXBElement<ArrayOfMDBType> createArrayOfMDB(ArrayOfMDBType value) {
        return new JAXBElement<ArrayOfMDBType>(_ArrayOfMDB_QNAME, ArrayOfMDBType.class, null, value);
    }

}
