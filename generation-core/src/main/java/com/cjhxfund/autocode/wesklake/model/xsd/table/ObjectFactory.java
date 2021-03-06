
package com.cjhxfund.autocode.wesklake.model.xsd.table;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cjhxfund.autocode.wesklake.model.xsd.table package.
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

    private final static QName _Table_QNAME = new QName("", "Table");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cjhxfund.autocode.wesklake.model.xsd.table
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TableType }
     * 
     */
    public TableType createTableType() {
        return new TableType();
    }

    /**
     * Create an instance of {@link TableFieldType }
     * 
     */
    public TableFieldType createTableFieldType() {
        return new TableFieldType();
    }

    /**
     * Create an instance of {@link FieldsType }
     * 
     */
    public FieldsType createFieldsType() {
        return new FieldsType();
    }

    /**
     * Create an instance of {@link TableIndexType }
     * 
     */
    public TableIndexType createTableIndexType() {
        return new TableIndexType();
    }

    /**
     * Create an instance of {@link IndexsType }
     * 
     */
    public IndexsType createIndexsType() {
        return new IndexsType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TableType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Table")
    public JAXBElement<TableType> createTable(TableType value) {
        return new JAXBElement<TableType>(_Table_QNAME, TableType.class, null, value);
    }

}
