
package com.deppon.crm.module.interfaces.fin.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;



/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.crm.module.interfaces.fin package. 
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

    private final static QName _NotifyClaimsStateResponse_QNAME = new QName("http://www.deppon.com/fssc/remote/crm/domain/entity", "notify_claims_stateResponse");
    private final static QName _NotifyClaimsStateRequest_QNAME = new QName("http://www.deppon.com/fssc/remote/crm/domain/entity", "notify_claims_stateRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.crm.module.interfaces.fin
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NotifyClaimsStateResponse }
     * 
     */
    public NotifyClaimsStateResponse createNotifyClaimsStateResponse() {
        return new NotifyClaimsStateResponse();
    }

    /**
     * Create an instance of {@link NotifyClaimsStateRequest }
     * 
     */
    public NotifyClaimsStateRequest createNotifyClaimsStateRequest() {
        return new NotifyClaimsStateRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotifyClaimsStateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/fssc/remote/crm/domain/entity", name = "notify_claims_stateResponse")
    public JAXBElement<NotifyClaimsStateResponse> createNotifyClaimsStateResponse(NotifyClaimsStateResponse value) {
        return new JAXBElement<NotifyClaimsStateResponse>(_NotifyClaimsStateResponse_QNAME, NotifyClaimsStateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotifyClaimsStateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/fssc/remote/crm/domain/entity", name = "notify_claims_stateRequest")
    public JAXBElement<NotifyClaimsStateRequest> createNotifyClaimsStateRequest(NotifyClaimsStateRequest value) {
        return new JAXBElement<NotifyClaimsStateRequest>(_NotifyClaimsStateRequest_QNAME, NotifyClaimsStateRequest.class, null, value);
    }

}
