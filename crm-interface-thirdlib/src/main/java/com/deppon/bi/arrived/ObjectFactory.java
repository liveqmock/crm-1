
package com.deppon.bi.arrived;

import java.util.Date;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.bi.arrived package. 
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

    private final static QName _NewOperationIn1_QNAME = new QName("", "in1");
    private final static QName _NewOperationIn0_QNAME = new QName("", "in0");
    private final static QName _NewOperationResponseOut1_QNAME = new QName("", "out1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.bi.arrived
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NewOperation }
     * 
     */
    public NewOperation createNewOperation() {
        return new NewOperation();
    }

    /**
     * Create an instance of {@link NewOperationResponse }
     * 
     */
    public NewOperationResponse createNewOperationResponse() {
        return new NewOperationResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Date }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "in1", scope = NewOperation.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    public JAXBElement<Date> createNewOperationIn1(Date value) {
        return new JAXBElement<Date>(_NewOperationIn1_QNAME, Date.class, NewOperation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "in0", scope = NewOperation.class)
    public JAXBElement<String> createNewOperationIn0(String value) {
        return new JAXBElement<String>(_NewOperationIn0_QNAME, String.class, NewOperation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "out1", scope = NewOperationResponse.class)
    public JAXBElement<String> createNewOperationResponseOut1(String value) {
        return new JAXBElement<String>(_NewOperationResponseOut1_QNAME, String.class, NewOperationResponse.class, value);
    }

}
