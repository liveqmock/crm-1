
package com.deppon.crm.module.interfaces.order;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.crm.module.interfaces.order package. 
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

    private final static QName _CreateOrder_QNAME = new QName("http://order.interfaces.module.crm.deppon.com/", "createOrder");
    private final static QName _UpdateOrderStatusResponse_QNAME = new QName("http://order.interfaces.module.crm.deppon.com/", "updateOrderStatusResponse");
    private final static QName _OrderStateInfo_QNAME = new QName("http://order.interfaces.module.crm.deppon.com/", "orderStateInfo");
    private final static QName _CreateOrderResponse_QNAME = new QName("http://order.interfaces.module.crm.deppon.com/", "createOrderResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.crm.module.interfaces.order
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OrderStatusInfo }
     * 
     */
    public OrderStatusInfo createOrderStatusInfo() {
        return new OrderStatusInfo();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link OrderOperationLog }
     * 
     */
    public OrderOperationLog createOrderOperationLog() {
        return new OrderOperationLog();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Order }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.interfaces.module.crm.deppon.com/", name = "createOrder")
    public JAXBElement<Order> createCreateOrder(Order value) {
        return new JAXBElement<Order>(_CreateOrder_QNAME, Order.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.interfaces.module.crm.deppon.com/", name = "updateOrderStatusResponse")
    public JAXBElement<Boolean> createUpdateOrderStatusResponse(Boolean value) {
        return new JAXBElement<Boolean>(_UpdateOrderStatusResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderStatusInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.interfaces.module.crm.deppon.com/", name = "orderStateInfo")
    public JAXBElement<OrderStatusInfo> createOrderStateInfo(OrderStatusInfo value) {
        return new JAXBElement<OrderStatusInfo>(_OrderStateInfo_QNAME, OrderStatusInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.interfaces.module.crm.deppon.com/", name = "createOrderResponse")
    public JAXBElement<Boolean> createCreateOrderResponse(Boolean value) {
        return new JAXBElement<Boolean>(_CreateOrderResponse_QNAME, Boolean.class, null, value);
    }

}
