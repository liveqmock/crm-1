 
package com.deppon.foss.crm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.foss.crm package. 
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

    private final static QName _QueryMoneyResponse_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "queryMoneyResponse");
    private final static QName _QueryMoneyRequest_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "queryMoneyRequest");
    private final static QName _ServiceChargeStatusUpdateRequest_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "serviceChargeStatusUpdateRequest");
    private final static QName _ClaimsPayBillGenerateRequest_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "claimsPayBillGenerateRequest");
    private final static QName _BindOrderRequest_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "bindOrderRequest");
    private final static QName _GoodsBillReceiveResponse_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "goodsBillReceiveResponse");
    private final static QName _CommonExceptionInfo_QNAME = new QName("http://www.deppon.com/esb/exception", "commonExceptionInfo");
    private final static QName _GoodsBillReceiveRequest_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "goodsBillReceiveRequest");
    private final static QName _IsCustomerBlankOutResponse_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "isCustomerBlankOutResponse");
    private final static QName _BindOrderResponse_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "bindOrderResponse");
    private final static QName _ClaimsPayBillGenerateResponse_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "claimsPayBillGenerateResponse");
    private final static QName _IsCustomerBlankOutRequest_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "isCustomerBlankOutRequest");
    private final static QName _ServiceChargeStatusUpdateResponse_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "serviceChargeStatusUpdateResponse");
    private final static QName _EsbHeader_QNAME = new QName("http://www.deppon.com/esb/header", "esbHeader");
	private final static QName _SyncOrderLockInfoRequest_QNAME = new QName(
			"http://www.deppon.com/esb/inteface/domain/crm",
			"SyncOrderLockInfoRequest");
    
	private final static QName _SyncOrderLockInfoResponse_QNAME = new QName(
			"http://www.deppon.com/esb/inteface/domain/crm",
			"SyncOrderLockInfoResponse");
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.foss.crm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceChargeStatusUpdateResponse }
     * 
     */
    public ServiceChargeStatusUpdateResponse createServiceChargeStatusUpdateResponse() {
        return new ServiceChargeStatusUpdateResponse();
    }

    /**
     * Create an instance of {@link IsCustomerBlankOutRequest }
     * 
     */
    public IsCustomerBlankOutRequest createIsCustomerBlankOutRequest() {
        return new IsCustomerBlankOutRequest();
    }

    /**
     * Create an instance of {@link ClaimsPayBillGenerateRequest }
     * 
     */
    public ClaimsPayBillGenerateRequest createClaimsPayBillGenerateRequest() {
        return new ClaimsPayBillGenerateRequest();
    }

    /**
     * Create an instance of {@link BindOrderRequest }
     * 
     */
    public BindOrderRequest createBindOrderRequest() {
        return new BindOrderRequest();
    }

    /**
     * Create an instance of {@link GoodsBillReceiveResponse }
     * 
     */
    public GoodsBillReceiveResponse createGoodsBillReceiveResponse() {
        return new GoodsBillReceiveResponse();
    }

    /**
     * Create an instance of {@link IsCustomerBlankOutResponse }
     * 
     */
    public IsCustomerBlankOutResponse createIsCustomerBlankOutResponse() {
        return new IsCustomerBlankOutResponse();
    }

    /**
     * Create an instance of {@link BindOrderResponse }
     * 
     */
    public BindOrderResponse createBindOrderResponse() {
        return new BindOrderResponse();
    }

    /**
     * Create an instance of {@link ClaimsPayBillGenerateResponse }
     * 
     */
    public ClaimsPayBillGenerateResponse createClaimsPayBillGenerateResponse() {
        return new ClaimsPayBillGenerateResponse();
    }

    /**
     * Create an instance of {@link ServiceChargeStatusUpdateRequest }
     * 
     */
    public ServiceChargeStatusUpdateRequest createServiceChargeStatusUpdateRequest() {
        return new ServiceChargeStatusUpdateRequest();
    }

    /**
     * Create an instance of {@link QueryMoneyResponse }
     * 
     */
    public QueryMoneyResponse createQueryMoneyResponse() {
        return new QueryMoneyResponse();
    }

    /**
     * Create an instance of {@link QueryMoneyRequest }
     * 
     */
    public QueryMoneyRequest createQueryMoneyRequest() {
        return new QueryMoneyRequest();
    }

    /**
     * Create an instance of {@link GoodsBillReceiveRequest }
     * 
     */
    public GoodsBillReceiveRequest createGoodsBillReceiveRequest() {
        return new GoodsBillReceiveRequest();
    }

    /**
     * Create an instance of {@link BankPayInfo }
     * 
     */
    public BankPayInfo createBankPayInfo() {
        return new BankPayInfo();
    }

    /**
     * Create an instance of {@link AmountInfo }
     * 
     */
    public AmountInfo createAmountInfo() {
        return new AmountInfo();
    }

    /**
     * Create an instance of {@link IsCustomerBlankOutList }
     * 
     */
    public IsCustomerBlankOutList createIsCustomerBlankOutList() {
        return new IsCustomerBlankOutList();
    }

    /**
     * Create an instance of {@link ResponsibilityInfo }
     * 
     */
    public ResponsibilityInfo createResponsibilityInfo() {
        return new ResponsibilityInfo();
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
     * Create an instance of {@link CommonExceptionInfo }
     * 
     */
    public CommonExceptionInfo createCommonExceptionInfo() {
        return new CommonExceptionInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryMoneyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "queryMoneyResponse")
    public JAXBElement<QueryMoneyResponse> createQueryMoneyResponse(QueryMoneyResponse value) {
        return new JAXBElement<QueryMoneyResponse>(_QueryMoneyResponse_QNAME, QueryMoneyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryMoneyRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "queryMoneyRequest")
    public JAXBElement<QueryMoneyRequest> createQueryMoneyRequest(QueryMoneyRequest value) {
        return new JAXBElement<QueryMoneyRequest>(_QueryMoneyRequest_QNAME, QueryMoneyRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceChargeStatusUpdateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "serviceChargeStatusUpdateRequest")
    public JAXBElement<ServiceChargeStatusUpdateRequest> createServiceChargeStatusUpdateRequest(ServiceChargeStatusUpdateRequest value) {
        return new JAXBElement<ServiceChargeStatusUpdateRequest>(_ServiceChargeStatusUpdateRequest_QNAME, ServiceChargeStatusUpdateRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClaimsPayBillGenerateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "claimsPayBillGenerateRequest")
    public JAXBElement<ClaimsPayBillGenerateRequest> createClaimsPayBillGenerateRequest(ClaimsPayBillGenerateRequest value) {
        return new JAXBElement<ClaimsPayBillGenerateRequest>(_ClaimsPayBillGenerateRequest_QNAME, ClaimsPayBillGenerateRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BindOrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "bindOrderRequest")
    public JAXBElement<BindOrderRequest> createBindOrderRequest(BindOrderRequest value) {
        return new JAXBElement<BindOrderRequest>(_BindOrderRequest_QNAME, BindOrderRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GoodsBillReceiveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "goodsBillReceiveResponse")
    public JAXBElement<GoodsBillReceiveResponse> createGoodsBillReceiveResponse(GoodsBillReceiveResponse value) {
        return new JAXBElement<GoodsBillReceiveResponse>(_GoodsBillReceiveResponse_QNAME, GoodsBillReceiveResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GoodsBillReceiveRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "goodsBillReceiveRequest")
    public JAXBElement<GoodsBillReceiveRequest> createGoodsBillReceiveRequest(GoodsBillReceiveRequest value) {
        return new JAXBElement<GoodsBillReceiveRequest>(_GoodsBillReceiveRequest_QNAME, GoodsBillReceiveRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsCustomerBlankOutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "isCustomerBlankOutResponse")
    public JAXBElement<IsCustomerBlankOutResponse> createIsCustomerBlankOutResponse(IsCustomerBlankOutResponse value) {
        return new JAXBElement<IsCustomerBlankOutResponse>(_IsCustomerBlankOutResponse_QNAME, IsCustomerBlankOutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BindOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "bindOrderResponse")
    public JAXBElement<BindOrderResponse> createBindOrderResponse(BindOrderResponse value) {
        return new JAXBElement<BindOrderResponse>(_BindOrderResponse_QNAME, BindOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClaimsPayBillGenerateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "claimsPayBillGenerateResponse")
    public JAXBElement<ClaimsPayBillGenerateResponse> createClaimsPayBillGenerateResponse(ClaimsPayBillGenerateResponse value) {
        return new JAXBElement<ClaimsPayBillGenerateResponse>(_ClaimsPayBillGenerateResponse_QNAME, ClaimsPayBillGenerateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsCustomerBlankOutRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "isCustomerBlankOutRequest")
    public JAXBElement<IsCustomerBlankOutRequest> createIsCustomerBlankOutRequest(IsCustomerBlankOutRequest value) {
        return new JAXBElement<IsCustomerBlankOutRequest>(_IsCustomerBlankOutRequest_QNAME, IsCustomerBlankOutRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceChargeStatusUpdateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "serviceChargeStatusUpdateResponse")
    public JAXBElement<ServiceChargeStatusUpdateResponse> createServiceChargeStatusUpdateResponse(ServiceChargeStatusUpdateResponse value) {
        return new JAXBElement<ServiceChargeStatusUpdateResponse>(_ServiceChargeStatusUpdateResponse_QNAME, ServiceChargeStatusUpdateResponse.class, null, value);
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
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SyncOrderLockInfoRequest }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "SyncOrderLockInfoRequest")
	public JAXBElement<SyncOrderLockInfoRequest> createSyncOrderLockInfoRequest(
			SyncOrderLockInfoRequest value) {
		return new JAXBElement<SyncOrderLockInfoRequest>(
				_SyncOrderLockInfoRequest_QNAME,
				SyncOrderLockInfoRequest.class, null, value);
	}
	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SyncOrderLockInfoResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "SyncOrderLockInfoResponse")
	public JAXBElement<SyncOrderLockInfoResponse> createSyncOrderLockInfoResponse(
			SyncOrderLockInfoResponse value) {
		return new JAXBElement<SyncOrderLockInfoResponse>(
				_SyncOrderLockInfoResponse_QNAME,
				SyncOrderLockInfoResponse.class, null, value);
	}
}
