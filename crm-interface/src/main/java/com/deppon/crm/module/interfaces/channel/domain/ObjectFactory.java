
package com.deppon.crm.module.interfaces.channel.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.deppon.crm.module.client.common.exception.CommonExceptionInfo;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.crm.module.interfaces.channel.domain package. 
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

    private final static QName _CommonExceptionInfo_QNAME = new QName("http://www.deppon.com/crm/exception", "commonExceptionInfo");
    private final static QName _UpdateWayBillNumRequest_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "updateWayBillNumRequest");
    private final static QName _UpdateWayBillNumResponse_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "updateWayBillNumResponse");
    private final static QName _EsbHeader_QNAME = new QName("http://www.deppon.com/esb/header", "esbHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.crm.module.interfaces.channel.domain
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
     * Create an instance of {@link UpdateWayBillNumResponse }
     * 
     */
    public UpdateWayBillNumResponse createUpdateWayBillNumResponse() {
        return new UpdateWayBillNumResponse();
    }

    /**
     * Create an instance of {@link UpdateWayBillNumRequest }
     * 
     */
    public UpdateWayBillNumRequest createUpdateWayBillNumRequest() {
        return new UpdateWayBillNumRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommonExceptionInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/crm/exception", name = "commonExceptionInfo")
    public JAXBElement<CommonExceptionInfo> createCommonExceptionInfo(CommonExceptionInfo value) {
        return new JAXBElement<CommonExceptionInfo>(_CommonExceptionInfo_QNAME, CommonExceptionInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateWayBillNumRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "updateWayBillNumRequest")
    public JAXBElement<UpdateWayBillNumRequest> createUpdateWayBillNumRequest(UpdateWayBillNumRequest value) {
        return new JAXBElement<UpdateWayBillNumRequest>(_UpdateWayBillNumRequest_QNAME, UpdateWayBillNumRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateWayBillNumResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "updateWayBillNumResponse")
    public JAXBElement<UpdateWayBillNumResponse> createUpdateWayBillNumResponse(UpdateWayBillNumResponse value) {
        return new JAXBElement<UpdateWayBillNumResponse>(_UpdateWayBillNumResponse_QNAME, UpdateWayBillNumResponse.class, null, value);
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
