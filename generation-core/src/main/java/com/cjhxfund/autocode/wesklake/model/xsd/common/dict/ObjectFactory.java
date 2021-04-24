
package com.cjhxfund.autocode.wesklake.model.xsd.common.dict;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cjhxfund.autocode.wesklake.model.xsd.common.dict package.
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

    private final static QName _ArrayOfDict_QNAME = new QName("", "ArrayOfDict");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cjhxfund.autocode.wesklake.model.xsd.common.dict
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfDictType }
     * 
     */
    public ArrayOfDictType createArrayOfDictType() {
        return new ArrayOfDictType();
    }

    /**
     * Create an instance of {@link DictItemType }
     * 
     */
    public DictItemType createDictItemType() {
        return new DictItemType();
    }

    /**
     * Create an instance of {@link ItemsType }
     * 
     */
    public ItemsType createItemsType() {
        return new ItemsType();
    }

    /**
     * Create an instance of {@link DictType }
     * 
     */
    public DictType createDictType() {
        return new DictType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDictType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ArrayOfDict")
    public JAXBElement<ArrayOfDictType> createArrayOfDict(ArrayOfDictType value) {
        return new JAXBElement<ArrayOfDictType>(_ArrayOfDict_QNAME, ArrayOfDictType.class, null, value);
    }

}
