
package com.deppon.esb.esbtogis;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.esb.esbtogis package. 
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

    private final static QName _QueryDeptCoordinateResponse_QNAME = new QName("http://www.deppon.com/deppon/gis/inteface/domain", "queryDeptCoordinateResponse");
    private final static QName _QueryDeptCoordinateRequest_QNAME = new QName("http://www.deppon.com/deppon/gis/inteface/domain", "queryDeptCoordinateRequest");
    private final static QName _CommonExceptionInfo_QNAME = new QName("http://www.deppon.com/esb/exception", "commonExceptionInfo");
    private final static QName _EsbHeader_QNAME = new QName("http://www.deppon.com/esb/header", "esbHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.esb.esbtogis
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
     * Create an instance of {@link QueryDeptCoordinateResponse }
     * 
     */
    public QueryDeptCoordinateResponse createQueryDeptCoordinateResponse() {
        return new QueryDeptCoordinateResponse();
    }

    /**
     * Create an instance of {@link QueryDeptCoordinateRequest }
     * 
     */
    public QueryDeptCoordinateRequest createQueryDeptCoordinateRequest() {
        return new QueryDeptCoordinateRequest();
    }

    /**
     * Create an instance of {@link CoordinateInfo }
     * 
     */
    public CoordinateInfo createCoordinateInfo() {
        return new CoordinateInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryDeptCoordinateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/deppon/gis/inteface/domain", name = "queryDeptCoordinateResponse")
    public JAXBElement<QueryDeptCoordinateResponse> createQueryDeptCoordinateResponse(QueryDeptCoordinateResponse value) {
        return new JAXBElement<QueryDeptCoordinateResponse>(_QueryDeptCoordinateResponse_QNAME, QueryDeptCoordinateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryDeptCoordinateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/deppon/gis/inteface/domain", name = "queryDeptCoordinateRequest")
    public JAXBElement<QueryDeptCoordinateRequest> createQueryDeptCoordinateRequest(QueryDeptCoordinateRequest value) {
        return new JAXBElement<QueryDeptCoordinateRequest>(_QueryDeptCoordinateRequest_QNAME, QueryDeptCoordinateRequest.class, null, value);
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
