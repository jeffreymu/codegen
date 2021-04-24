
package com.cjhxfund.autocode.wesklake.model.xsd.rpc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cjhxfund.autocode.wesklake.model.xsd.rpc package. 
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

    private final static QName _ServiceInterface_QNAME = new QName("", "ServiceInterface");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cjhxfund.autocode.wesklake.model.xsd.rpc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceInterfaceType }
     * 
     */
    public ServiceInterfaceType createServiceInterfaceType() {
        return new ServiceInterfaceType();
    }

    /**
     * Create an instance of {@link SummaryType }
     * 
     */
    public SummaryType createSummaryType() {
        return new SummaryType();
    }

    /**
     * Create an instance of {@link ParamFieldsType }
     * 
     */
    public ParamFieldsType createParamFieldsType() {
        return new ParamFieldsType();
    }

    /**
     * Create an instance of {@link InParamsType }
     * 
     */
    public InParamsType createInParamsType() {
        return new InParamsType();
    }

    /**
     * Create an instance of {@link IntfParamFieldType }
     * 
     */
    public IntfParamFieldType createIntfParamFieldType() {
        return new IntfParamFieldType();
    }

    /**
     * Create an instance of {@link IntfParamType }
     * 
     */
    public IntfParamType createIntfParamType() {
        return new IntfParamType();
    }

    /**
     * Create an instance of {@link OutParamsType }
     * 
     */
    public OutParamsType createOutParamsType() {
        return new OutParamsType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceInterfaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ServiceInterface")
    public JAXBElement<ServiceInterfaceType> createServiceInterface(ServiceInterfaceType value) {
        return new JAXBElement<ServiceInterfaceType>(_ServiceInterface_QNAME, ServiceInterfaceType.class, null, value);
    }

}
