
package com.cjhxfund.autocode.wesklake.model.xsd.common.module;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cjhxfund.autocode.wesklake.model.xsd.common.module package.
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

    private final static QName _ArrayOfCommonModule_QNAME = new QName("", "ArrayOfCommonModule");
    private final static QName _CommonModuleTypeSubModulesCommonModule_QNAME = new QName("", "CommonModule");
    private final static QName _FilesTypeCommonFile_QNAME = new QName("", "CommonFile");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cjhxfund.autocode.wesklake.model.xsd.common.module
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CommonModuleType }
     * 
     */
    public CommonModuleType createCommonModuleType() {
        return new CommonModuleType();
    }

    /**
     * Create an instance of {@link ArrayOfCommonModuleType }
     * 
     */
    public ArrayOfCommonModuleType createArrayOfCommonModuleType() {
        return new ArrayOfCommonModuleType();
    }

    /**
     * Create an instance of {@link CommonFileType }
     * 
     */
    public CommonFileType createCommonFileType() {
        return new CommonFileType();
    }

    /**
     * Create an instance of {@link SubModulesType }
     * 
     */
    public SubModulesType createSubModulesType() {
        return new SubModulesType();
    }

    /**
     * Create an instance of {@link FilesType }
     * 
     */
    public FilesType createFilesType() {
        return new FilesType();
    }

    /**
     * Create an instance of {@link CommonModuleType.SubModules }
     * 
     */
    public CommonModuleType.SubModules createCommonModuleTypeSubModules() {
        return new CommonModuleType.SubModules();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCommonModuleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ArrayOfCommonModule")
    public JAXBElement<ArrayOfCommonModuleType> createArrayOfCommonModule(ArrayOfCommonModuleType value) {
        return new JAXBElement<ArrayOfCommonModuleType>(_ArrayOfCommonModule_QNAME, ArrayOfCommonModuleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommonModuleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "CommonModule", scope = CommonModuleType.SubModules.class)
    public JAXBElement<CommonModuleType> createCommonModuleTypeSubModulesCommonModule(CommonModuleType value) {
        return new JAXBElement<CommonModuleType>(_CommonModuleTypeSubModulesCommonModule_QNAME, CommonModuleType.class, CommonModuleType.SubModules.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommonFileType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "CommonFile", scope = FilesType.class)
    public JAXBElement<CommonFileType> createFilesTypeCommonFile(CommonFileType value) {
        return new JAXBElement<CommonFileType>(_FilesTypeCommonFile_QNAME, CommonFileType.class, FilesType.class, value);
    }

}
