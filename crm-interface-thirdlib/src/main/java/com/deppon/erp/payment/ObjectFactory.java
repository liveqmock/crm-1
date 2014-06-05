
package com.deppon.erp.payment;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.erp.payment package. 
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

    private final static QName _InsertCrmMsgResponse_QNAME = new QName("service.amsonline.ws.deppon.com", "insertCrmMsgResponse");
    private final static QName _InsertCrmMsg_QNAME = new QName("service.amsonline.ws.deppon.com", "insertCrmMsg");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.erp.payment
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsertCrmMsg }
     * 
     */
    public InsertCrmMsg createInsertCrmMsg() {
        return new InsertCrmMsg();
    }

    /**
     * Create an instance of {@link Msg }
     * 
     */
    public Msg createMsg() {
        return new Msg();
    }

    /**
     * Create an instance of {@link InsertCrmMsgResponse }
     * 
     */
    public InsertCrmMsgResponse createInsertCrmMsgResponse() {
        return new InsertCrmMsgResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertCrmMsgResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "service.amsonline.ws.deppon.com", name = "insertCrmMsgResponse")
    public JAXBElement<InsertCrmMsgResponse> createInsertCrmMsgResponse(InsertCrmMsgResponse value) {
        return new JAXBElement<InsertCrmMsgResponse>(_InsertCrmMsgResponse_QNAME, InsertCrmMsgResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertCrmMsg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "service.amsonline.ws.deppon.com", name = "insertCrmMsg")
    public JAXBElement<InsertCrmMsg> createInsertCrmMsg(InsertCrmMsg value) {
        return new JAXBElement<InsertCrmMsg>(_InsertCrmMsg_QNAME, InsertCrmMsg.class, null, value);
    }

}
