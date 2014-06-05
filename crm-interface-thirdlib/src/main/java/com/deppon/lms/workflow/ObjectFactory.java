
package com.deppon.lms.workflow;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.lms.workflow package. 
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

    private final static QName _ApplyGiftResponseOut1_QNAME = new QName("http://www.primeton.com/IApplyGiftService", "out1");
    private final static QName _ApplyGiftIn0_QNAME = new QName("http://www.primeton.com/IApplyGiftService", "in0");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.lms.workflow
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ApplyGiftResponse }
     * 
     */
    public ApplyGiftResponse createApplyGiftResponse() {
        return new ApplyGiftResponse();
    }

    /**
     * Create an instance of {@link ApplyGift }
     * 
     */
    public ApplyGift createApplyGift() {
        return new ApplyGift();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/IApplyGiftService", name = "out1", scope = ApplyGiftResponse.class)
    public JAXBElement<String> createApplyGiftResponseOut1(String value) {
        return new JAXBElement<String>(_ApplyGiftResponseOut1_QNAME, String.class, ApplyGiftResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/IApplyGiftService", name = "in0", scope = ApplyGift.class)
    public JAXBElement<String> createApplyGiftIn0(String value) {
        return new JAXBElement<String>(_ApplyGiftIn0_QNAME, String.class, ApplyGift.class, value);
    }

}
