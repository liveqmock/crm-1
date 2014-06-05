
package com.deppon.crm.module.interfaces.uums.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.crm.module.interfaces.uums.domain package. 
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

    private final static QName _SendCompanyInfoResponse_QNAME = new QName("http://www.deppon.com/crm/inteface/domain/companymanagement", "SendCompanyInfoResponse");
    private final static QName _SendCompanyInfoRequest_QNAME = new QName("http://www.deppon.com/crm/inteface/domain/companymanagement", "SendCompanyInfoRequest");
    private final static QName _CommonExceptionInfo_QNAME = new QName("http://www.deppon.com/esb/exception", "commonExceptionInfo");
    private final static QName _EsbHeader_QNAME = new QName("http://www.deppon.com/esb/header", "esbHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.crm.module.interfaces.uums.domain
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendCompanyInfoRequest }
     * 
     */
    public SendCompanyInfoRequest createSendCompanyInfoRequest() {
        return new SendCompanyInfoRequest();
    }

    /**
     * Create an instance of {@link SendCompanyInfoResponse }
     * 
     */
    public SendCompanyInfoResponse createSendCompanyInfoResponse() {
        return new SendCompanyInfoResponse();
    }

    /**
     * Create an instance of {@link CompanyInfo }
     * 
     */
    public CompanyInfo createCompanyInfo() {
        return new CompanyInfo();
    }

    /**
     * Create an instance of {@link SendCompanyInfoProcessReult }
     * 
     */
    public SendCompanyInfoProcessReult createSendCompanyInfoProcessReult() {
        return new SendCompanyInfoProcessReult();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SendCompanyInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/crm/inteface/domain/companymanagement", name = "SendCompanyInfoResponse")
    public JAXBElement<SendCompanyInfoResponse> createSendCompanyInfoResponse(SendCompanyInfoResponse value) {
        return new JAXBElement<SendCompanyInfoResponse>(_SendCompanyInfoResponse_QNAME, SendCompanyInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendCompanyInfoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/crm/inteface/domain/companymanagement", name = "SendCompanyInfoRequest")
    public JAXBElement<SendCompanyInfoRequest> createSendCompanyInfoRequest(SendCompanyInfoRequest value) {
        return new JAXBElement<SendCompanyInfoRequest>(_SendCompanyInfoRequest_QNAME, SendCompanyInfoRequest.class, null, value);
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
