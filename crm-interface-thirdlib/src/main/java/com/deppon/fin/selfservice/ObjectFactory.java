
package com.deppon.fin.selfservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.crm.module.client.fin1 package. 
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

    private final static QName _GenerateClaimsapbillRequest_QNAME = new QName("http://www.deppon.com/fssc/remote/crm/domain", "generate_claimsapbillRequest");
    private final static QName _QueryCashieraccountRequest_QNAME = new QName("http://www.deppon.com/fssc/remote/crm/domain", "query_cashieraccountRequest");
    private final static QName _GenerateClaimsapbillResponse_QNAME = new QName("http://www.deppon.com/fssc/remote/crm/domain", "generate_claimsapbillResponse");
    private final static QName _QueryCashieraccountResponse_QNAME = new QName("http://www.deppon.com/fssc/remote/crm/domain", "query_cashieraccountResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.crm.module.client.fin1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GenerateClaimsapbillRequest }
     * 
     */
    public GenerateClaimsapbillRequest createGenerateClaimsapbillRequest() {
        return new GenerateClaimsapbillRequest();
    }

    /**
     * Create an instance of {@link QueryCashieraccountRequest }
     * 
     */
    public QueryCashieraccountRequest createQueryCashieraccountRequest() {
        return new QueryCashieraccountRequest();
    }

    /**
     * Create an instance of {@link GenerateClaimsapbillResponse }
     * 
     */
    public GenerateClaimsapbillResponse createGenerateClaimsapbillResponse() {
        return new GenerateClaimsapbillResponse();
    }

    /**
     * Create an instance of {@link QueryCashieraccountResponse }
     * 
     */
    public QueryCashieraccountResponse createQueryCashieraccountResponse() {
        return new QueryCashieraccountResponse();
    }

    /**
     * Create an instance of {@link CostDetailsType }
     * 
     */
    public CostDetailsType createCostDetailsType() {
        return new CostDetailsType();
    }

    /**
     * Create an instance of {@link CashierAccount }
     * 
     */
    public CashierAccount createCashierAccount() {
        return new CashierAccount();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateClaimsapbillRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/fssc/remote/crm/domain", name = "generate_claimsapbillRequest")
    public JAXBElement<GenerateClaimsapbillRequest> createGenerateClaimsapbillRequest(GenerateClaimsapbillRequest value) {
        return new JAXBElement<GenerateClaimsapbillRequest>(_GenerateClaimsapbillRequest_QNAME, GenerateClaimsapbillRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryCashieraccountRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/fssc/remote/crm/domain", name = "query_cashieraccountRequest")
    public JAXBElement<QueryCashieraccountRequest> createQueryCashieraccountRequest(QueryCashieraccountRequest value) {
        return new JAXBElement<QueryCashieraccountRequest>(_QueryCashieraccountRequest_QNAME, QueryCashieraccountRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateClaimsapbillResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/fssc/remote/crm/domain", name = "generate_claimsapbillResponse")
    public JAXBElement<GenerateClaimsapbillResponse> createGenerateClaimsapbillResponse(GenerateClaimsapbillResponse value) {
        return new JAXBElement<GenerateClaimsapbillResponse>(_GenerateClaimsapbillResponse_QNAME, GenerateClaimsapbillResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryCashieraccountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/fssc/remote/crm/domain", name = "query_cashieraccountResponse")
    public JAXBElement<QueryCashieraccountResponse> createQueryCashieraccountResponse(QueryCashieraccountResponse value) {
        return new JAXBElement<QueryCashieraccountResponse>(_QueryCashieraccountResponse_QNAME, QueryCashieraccountResponse.class, null, value);
    }

}
