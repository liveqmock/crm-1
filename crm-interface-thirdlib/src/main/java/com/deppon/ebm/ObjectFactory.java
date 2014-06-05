
package com.deppon.ebm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.ebm package. 
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

    private final static QName _QueryOrderStatus_QNAME = new QName("http://ws.shared.aliOrder.module.alibaba.deppon.com/", "queryOrderStatus");
    private final static QName _QueryOrderStatusResponse_QNAME = new QName("http://ws.shared.aliOrder.module.alibaba.deppon.com/", "queryOrderStatusResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.ebm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OrderStatusTraceInfo }
     * 
     */
    public OrderStatusTraceInfo createOrderStatusTraceInfo() {
        return new OrderStatusTraceInfo();
    }

    /**
     * Create an instance of {@link ResultInfo }
     * 
     */
    public ResultInfo createResultInfo() {
        return new ResultInfo();
    }

    /**
     * Create an instance of {@link QueryOrderStatus }
     * 
     */
    public QueryOrderStatus createQueryOrderStatus() {
        return new QueryOrderStatus();
    }

    /**
     * Create an instance of {@link QueryOrderStatusResponse }
     * 
     */
    public QueryOrderStatusResponse createQueryOrderStatusResponse() {
        return new QueryOrderStatusResponse();
    }

    /**
     * Create an instance of {@link OrderStatusReqInfo }
     * 
     */
    public OrderStatusReqInfo createOrderStatusReqInfo() {
        return new OrderStatusReqInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryOrderStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.shared.aliOrder.module.alibaba.deppon.com/", name = "queryOrderStatus")
    public JAXBElement<QueryOrderStatus> createQueryOrderStatus(QueryOrderStatus value) {
        return new JAXBElement<QueryOrderStatus>(_QueryOrderStatus_QNAME, QueryOrderStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryOrderStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.shared.aliOrder.module.alibaba.deppon.com/", name = "queryOrderStatusResponse")
    public JAXBElement<QueryOrderStatusResponse> createQueryOrderStatusResponse(QueryOrderStatusResponse value) {
        return new JAXBElement<QueryOrderStatusResponse>(_QueryOrderStatusResponse_QNAME, QueryOrderStatusResponse.class, null, value);
    }

}
