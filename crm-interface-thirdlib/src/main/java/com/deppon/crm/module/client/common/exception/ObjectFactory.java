
package com.deppon.crm.module.client.common.exception;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.crm.module.client.common.exception package. 
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

    private final static QName _CrmBusiness_QNAME = new QName("http://exception.common.client.module.crm.deppon.com", "CrmBusiness");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.crm.module.client.common.exception
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CrmBusiness }
     * 
     */
    public CrmBusiness createCrmBusiness() {
        return new CrmBusiness();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrmBusiness }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exception.common.client.module.crm.deppon.com", name = "CrmBusiness")
    public JAXBElement<CrmBusiness> createCrmBusiness(CrmBusiness value) {
        return new JAXBElement<CrmBusiness>(_CrmBusiness_QNAME, CrmBusiness.class, null, value);
    }

}
