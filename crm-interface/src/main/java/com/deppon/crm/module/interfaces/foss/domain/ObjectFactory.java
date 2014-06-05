
package com.deppon.crm.module.interfaces.foss.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.foss.test package. 
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

    private final static QName _ValidateCouponRequest_QNAME = new QName("http://www.deppon.com/crm/inteface/foss/domain", "validateCouponRequest");
    private final static QName _QueryOrderListResponse_QNAME = new QName("http://www.deppon.com/crm/inteface/foss/domain", "queryOrderListResponse");
    private final static QName _ValidateCouponResponse_QNAME = new QName("http://www.deppon.com/crm/inteface/foss/domain", "validateCouponResponse");
    private final static QName _SearchOrderResponse_QNAME = new QName("http://www.deppon.com/crm/inteface/foss/domain", "searchOrderResponse");
    private final static QName _BackFreightCheckResponse_QNAME = new QName("http://www.deppon.com/crm/inteface/foss/domain", "backFreightCheckResponse");
    private final static QName _CouponStateResponse_QNAME = new QName("http://www.deppon.com/crm/inteface/foss/domain", "couponStateResponse");
    private final static QName _QueryOrderListRequest_QNAME = new QName("http://www.deppon.com/crm/inteface/foss/domain", "queryOrderListRequest");
    private final static QName _SearchOrderRequest_QNAME = new QName("http://www.deppon.com/crm/inteface/foss/domain", "searchOrderRequest");
    private final static QName _CommonExceptionInfo_QNAME = new QName("http://www.deppon.com/esb/exception", "commonExceptionInfo");
    private final static QName _CouponStateRequest_QNAME = new QName("http://www.deppon.com/crm/inteface/foss/domain", "couponStateRequest");
    private final static QName _EsbHeader_QNAME = new QName("http://www.deppon.com/esb/header", "esbHeader");
    private final static QName _BackFreightCheckRequest_QNAME = new QName("http://www.deppon.com/crm/inteface/foss/domain", "backFreightCheckRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.foss.test
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BackFreightCheckResponse }
     * 
     */
    public BackFreightCheckResponse createBackFreightCheckResponse() {
        return new BackFreightCheckResponse();
    }

    /**
     * Create an instance of {@link CouponStateResponse }
     * 
     */
    public CouponStateResponse createCouponStateResponse() {
        return new CouponStateResponse();
    }

    /**
     * Create an instance of {@link SearchOrderRequest }
     * 
     */
    public SearchOrderRequest createSearchOrderRequest() {
        return new SearchOrderRequest();
    }

    /**
     * Create an instance of {@link QueryOrderListRequest }
     * 
     */
    public QueryOrderListRequest createQueryOrderListRequest() {
        return new QueryOrderListRequest();
    }

    /**
     * Create an instance of {@link CouponStateRequest }
     * 
     */
    public CouponStateRequest createCouponStateRequest() {
        return new CouponStateRequest();
    }

    /**
     * Create an instance of {@link BackFreightCheckRequest }
     * 
     */
    public BackFreightCheckRequest createBackFreightCheckRequest() {
        return new BackFreightCheckRequest();
    }

    /**
     * Create an instance of {@link ValidateCouponRequest }
     * 
     */
    public ValidateCouponRequest createValidateCouponRequest() {
        return new ValidateCouponRequest();
    }

    /**
     * Create an instance of {@link QueryOrderListResponse }
     * 
     */
    public QueryOrderListResponse createQueryOrderListResponse() {
        return new QueryOrderListResponse();
    }

    /**
     * Create an instance of {@link ValidateCouponResponse }
     * 
     */
    public ValidateCouponResponse createValidateCouponResponse() {
        return new ValidateCouponResponse();
    }

    /**
     * Create an instance of {@link SearchOrderResponse }
     * 
     */
    public SearchOrderResponse createSearchOrderResponse() {
        return new SearchOrderResponse();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link AmountInfo }
     * 
     */
    public AmountInfo createAmountInfo() {
        return new AmountInfo();
    }

    /**
     * Create an instance of {@link OrderInfo }
     * 
     */
    public OrderInfo createOrderInfo() {
        return new OrderInfo();
    }

    /**
     * Create an instance of {@link CouponWaybillInfo }
     * 
     */
    public CouponWaybillInfo createCouponWaybillInfo() {
        return new CouponWaybillInfo();
    }

    /**
     * Create an instance of {@link CommonExceptionInfo }
     * 
     */
    public CommonExceptionInfo createCommonExceptionInfo() {
        return new CommonExceptionInfo();
    }

    /**
     * Create an instance of {@link ESBHeader }
     * 
     */
    public ESBHeader createESBHeader() {
        return new ESBHeader();
    }

    /**
     * Create an instance of {@link StatusInfo }
     * 
     */
    public StatusInfo createStatusInfo() {
        return new StatusInfo();
    }

    /**
     * Create an instance of {@link AuthInfo }
     * 
     */
    public AuthInfo createAuthInfo() {
        return new AuthInfo();
    }

    /**
     * Create an instance of {@link StatusList }
     * 
     */
    public StatusList createStatusList() {
        return new StatusList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateCouponRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/crm/inteface/foss/domain", name = "validateCouponRequest")
    public JAXBElement<ValidateCouponRequest> createValidateCouponRequest(ValidateCouponRequest value) {
        return new JAXBElement<ValidateCouponRequest>(_ValidateCouponRequest_QNAME, ValidateCouponRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryOrderListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/crm/inteface/foss/domain", name = "queryOrderListResponse")
    public JAXBElement<QueryOrderListResponse> createQueryOrderListResponse(QueryOrderListResponse value) {
        return new JAXBElement<QueryOrderListResponse>(_QueryOrderListResponse_QNAME, QueryOrderListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateCouponResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/crm/inteface/foss/domain", name = "validateCouponResponse")
    public JAXBElement<ValidateCouponResponse> createValidateCouponResponse(ValidateCouponResponse value) {
        return new JAXBElement<ValidateCouponResponse>(_ValidateCouponResponse_QNAME, ValidateCouponResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/crm/inteface/foss/domain", name = "searchOrderResponse")
    public JAXBElement<SearchOrderResponse> createSearchOrderResponse(SearchOrderResponse value) {
        return new JAXBElement<SearchOrderResponse>(_SearchOrderResponse_QNAME, SearchOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BackFreightCheckResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/crm/inteface/foss/domain", name = "backFreightCheckResponse")
    public JAXBElement<BackFreightCheckResponse> createBackFreightCheckResponse(BackFreightCheckResponse value) {
        return new JAXBElement<BackFreightCheckResponse>(_BackFreightCheckResponse_QNAME, BackFreightCheckResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CouponStateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/crm/inteface/foss/domain", name = "couponStateResponse")
    public JAXBElement<CouponStateResponse> createCouponStateResponse(CouponStateResponse value) {
        return new JAXBElement<CouponStateResponse>(_CouponStateResponse_QNAME, CouponStateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryOrderListRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/crm/inteface/foss/domain", name = "queryOrderListRequest")
    public JAXBElement<QueryOrderListRequest> createQueryOrderListRequest(QueryOrderListRequest value) {
        return new JAXBElement<QueryOrderListRequest>(_QueryOrderListRequest_QNAME, QueryOrderListRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchOrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/crm/inteface/foss/domain", name = "searchOrderRequest")
    public JAXBElement<SearchOrderRequest> createSearchOrderRequest(SearchOrderRequest value) {
        return new JAXBElement<SearchOrderRequest>(_SearchOrderRequest_QNAME, SearchOrderRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommonExceptionInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/exception", name = "commonExceptionInfo")
    public JAXBElement<CommonExceptionInfo> createCommonExceptionInfo(CommonExceptionInfo value) {
        return new JAXBElement<CommonExceptionInfo>(_CommonExceptionInfo_QNAME, CommonExceptionInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CouponStateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/crm/inteface/foss/domain", name = "couponStateRequest")
    public JAXBElement<CouponStateRequest> createCouponStateRequest(CouponStateRequest value) {
        return new JAXBElement<CouponStateRequest>(_CouponStateRequest_QNAME, CouponStateRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ESBHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/header", name = "esbHeader")
    public JAXBElement<ESBHeader> createEsbHeader(ESBHeader value) {
        return new JAXBElement<ESBHeader>(_EsbHeader_QNAME, ESBHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BackFreightCheckRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/crm/inteface/foss/domain", name = "backFreightCheckRequest")
    public JAXBElement<BackFreightCheckRequest> createBackFreightCheckRequest(BackFreightCheckRequest value) {
        return new JAXBElement<BackFreightCheckRequest>(_BackFreightCheckRequest_QNAME, BackFreightCheckRequest.class, null, value);
    }

}
