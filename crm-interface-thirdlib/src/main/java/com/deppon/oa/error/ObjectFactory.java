
package com.deppon.oa.error;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.oa.error package. 
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

    private final static QName _ReturnErrosInforIn0_QNAME = new QName("http://www.primeton.com/ErrorService", "in0");
    private final static QName _ReturnErrosInforResponseOut1_QNAME = new QName("http://www.primeton.com/ErrorService", "out1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.oa.error
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReturnErrosInfor }
     * 
     */
    public ReturnErrosInfor createReturnErrosInfor() {
        return new ReturnErrosInfor();
    }

    /**
     * Create an instance of {@link ReturnErrosInforResponse }
     * 
     */
    public ReturnErrosInforResponse createReturnErrosInforResponse() {
        return new ReturnErrosInforResponse();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link GetErrorInfo }
     * 
     */
    public GetErrorInfo createGetErrorInfo() {
        return new GetErrorInfo();
    }

    /**
     * Create an instance of {@link GetErrorInfoResponse }
     * 
     */
    public GetErrorInfoResponse createGetErrorInfoResponse() {
        return new GetErrorInfoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/ErrorService", name = "in0", scope = ReturnErrosInfor.class)
    public JAXBElement<String> createReturnErrosInforIn0(String value) {
        return new JAXBElement<String>(_ReturnErrosInforIn0_QNAME, String.class, ReturnErrosInfor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/ErrorService", name = "out1", scope = ReturnErrosInforResponse.class)
    public JAXBElement<ArrayOfString> createReturnErrosInforResponseOut1(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_ReturnErrosInforResponseOut1_QNAME, ArrayOfString.class, ReturnErrosInforResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/ErrorService", name = "in0", scope = GetErrorInfo.class)
    public JAXBElement<String> createGetErrorInfoIn0(String value) {
        return new JAXBElement<String>(_ReturnErrosInforIn0_QNAME, String.class, GetErrorInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/ErrorService", name = "out1", scope = GetErrorInfoResponse.class)
    public JAXBElement<ArrayOfString> createGetErrorInfoResponseOut1(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_ReturnErrosInforResponseOut1_QNAME, ArrayOfString.class, GetErrorInfoResponse.class, value);
    }

}
