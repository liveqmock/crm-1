//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.5-2 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2012.11.28 时间 08:46:05 PM CST 
//


package com.deppon.crm.test.client.esb.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.deppon.crm.module.client.esb.domain.OrigCustSyncRequest;
import com.deppon.crm.module.client.esb.domain.OrigCustSyncRequestProcessDetail;
import com.deppon.crm.module.client.esb.domain.OrigCustSyncResponse;
import com.deppon.crm.module.client.esb.domain.ScatterCustBankInfo;
import com.deppon.crm.module.client.esb.domain.ScatterCustInfo;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.esb.inteface.domain.crm package. 
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

    private final static QName _OrigCustSyncResponse_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "OrigCustSyncResponse");
    private final static QName _OrigCustSyncRequest_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "OrigCustSyncRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.esb.inteface.domain.crm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OrigCustSyncRequest }
     * 
     */
    public OrigCustSyncRequest createOrigCustSyncRequest() {
        return new OrigCustSyncRequest();
    }

    /**
     * Create an instance of {@link OrigCustSyncResponse }
     * 
     */
    public OrigCustSyncResponse createOrigCustSyncResponse() {
        return new OrigCustSyncResponse();
    }

    /**
     * Create an instance of {@link OrigCustSyncRequestProcessDetail }
     * 
     */
    public OrigCustSyncRequestProcessDetail createOrigCustSyncRequestProcessDetail() {
        return new OrigCustSyncRequestProcessDetail();
    }

    /**
     * Create an instance of {@link ScatterCustBankInfo }
     * 
     */
    public ScatterCustBankInfo createScatterCustBankInfo() {
        return new ScatterCustBankInfo();
    }

    /**
     * Create an instance of {@link ScatterCustInfo }
     * 
     */
    public ScatterCustInfo createScatterCustInfo() {
        return new ScatterCustInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrigCustSyncResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "OrigCustSyncResponse")
    public JAXBElement<OrigCustSyncResponse> createOrigCustSyncResponse(OrigCustSyncResponse value) {
        return new JAXBElement<OrigCustSyncResponse>(_OrigCustSyncResponse_QNAME, OrigCustSyncResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrigCustSyncRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/inteface/domain/crm", name = "OrigCustSyncRequest")
    public JAXBElement<OrigCustSyncRequest> createOrigCustSyncRequest(OrigCustSyncRequest value) {
        return new JAXBElement<OrigCustSyncRequest>(_OrigCustSyncRequest_QNAME, OrigCustSyncRequest.class, null, value);
    }

}
