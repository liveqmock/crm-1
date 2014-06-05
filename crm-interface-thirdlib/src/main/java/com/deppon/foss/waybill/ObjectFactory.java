
package com.deppon.foss.waybill;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.foss.waybill package. 
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

    private final static QName _QueryDetailRequest_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/waybillService", "QueryDetailRequest");
    private final static QName _QueryDetailResponse_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/waybillService", "QueryDetailResponse");
    private final static QName _CommonExceptionInfo_QNAME = new QName("http://www.deppon.com/esb/exception", "commonExceptionInfo");
    private final static QName _EsbHeader_QNAME = new QName("http://www.deppon.com/esb/header", "esbHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.foss.waybill
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link QueryDetailRequest }
     * 
     */
    public QueryDetailRequest createQueryDetailRequest() {
        return new QueryDetailRequest();
    }

    /**
     * Create an instance of {@link QueryDetailResponse }
     * 
     */
    public QueryDetailResponse createQueryDetailResponse() {
        return new QueryDetailResponse();
    }

    /**
     * Create an instance of {@link WayBillDetail }
     * 
     */
    public WayBillDetail createWayBillDetail() {
        return new WayBillDetail();
    }

    /**
     * Create an instance of {@link WaybillCostInfo }
     * 
     */
    public WaybillCostInfo createWaybillCostInfo() {
        return new WaybillCostInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryDetailRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/waybillService", name = "QueryDetailRequest")
    public JAXBElement<QueryDetailRequest> createQueryDetailRequest(QueryDetailRequest value) {
        return new JAXBElement<QueryDetailRequest>(_QueryDetailRequest_QNAME, QueryDetailRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryDetailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/waybillService", name = "QueryDetailResponse")
    public JAXBElement<QueryDetailResponse> createQueryDetailResponse(QueryDetailResponse value) {
        return new JAXBElement<QueryDetailResponse>(_QueryDetailResponse_QNAME, QueryDetailResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ESBHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/header", name = "esbHeader")
    public JAXBElement<ESBHeader> createEsbHeader(ESBHeader value) {
        return new JAXBElement<ESBHeader>(_EsbHeader_QNAME, ESBHeader.class, null, value);
    }

}
