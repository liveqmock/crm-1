
package com.deppon.oa.task;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.oa.task package. 
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

    private final static QName _SendMessageToPortalResponseOut1_QNAME = new QName("http://www.primeton.com/TaskService", "out1");
    private final static QName _SendUnReadTaskIn1_QNAME = new QName("http://www.primeton.com/TaskService", "in1");
    private final static QName _SendUnReadTaskIn0_QNAME = new QName("http://www.primeton.com/TaskService", "in0");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.oa.task
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendMessageToPortalResponse }
     * 
     */
    public SendMessageToPortalResponse createSendMessageToPortalResponse() {
        return new SendMessageToPortalResponse();
    }

    /**
     * Create an instance of {@link SendUnReadTask }
     * 
     */
    public SendUnReadTask createSendUnReadTask() {
        return new SendUnReadTask();
    }

    /**
     * Create an instance of {@link SendReadTask }
     * 
     */
    public SendReadTask createSendReadTask() {
        return new SendReadTask();
    }

    /**
     * Create an instance of {@link SendMessageToPortal }
     * 
     */
    public SendMessageToPortal createSendMessageToPortal() {
        return new SendMessageToPortal();
    }

    /**
     * Create an instance of {@link SendReadTaskResponse }
     * 
     */
    public SendReadTaskResponse createSendReadTaskResponse() {
        return new SendReadTaskResponse();
    }

    /**
     * Create an instance of {@link SendUnReadTaskResponse }
     * 
     */
    public SendUnReadTaskResponse createSendUnReadTaskResponse() {
        return new SendUnReadTaskResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/TaskService", name = "out1", scope = SendMessageToPortalResponse.class)
    public JAXBElement<String> createSendMessageToPortalResponseOut1(String value) {
        return new JAXBElement<String>(_SendMessageToPortalResponseOut1_QNAME, String.class, SendMessageToPortalResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/TaskService", name = "in1", scope = SendUnReadTask.class)
    public JAXBElement<String> createSendUnReadTaskIn1(String value) {
        return new JAXBElement<String>(_SendUnReadTaskIn1_QNAME, String.class, SendUnReadTask.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/TaskService", name = "in0", scope = SendUnReadTask.class)
    public JAXBElement<String> createSendUnReadTaskIn0(String value) {
        return new JAXBElement<String>(_SendUnReadTaskIn0_QNAME, String.class, SendUnReadTask.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/TaskService", name = "in1", scope = SendReadTask.class)
    public JAXBElement<String> createSendReadTaskIn1(String value) {
        return new JAXBElement<String>(_SendUnReadTaskIn1_QNAME, String.class, SendReadTask.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/TaskService", name = "in0", scope = SendReadTask.class)
    public JAXBElement<String> createSendReadTaskIn0(String value) {
        return new JAXBElement<String>(_SendUnReadTaskIn0_QNAME, String.class, SendReadTask.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/TaskService", name = "in1", scope = SendMessageToPortal.class)
    public JAXBElement<String> createSendMessageToPortalIn1(String value) {
        return new JAXBElement<String>(_SendUnReadTaskIn1_QNAME, String.class, SendMessageToPortal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/TaskService", name = "in0", scope = SendMessageToPortal.class)
    public JAXBElement<String> createSendMessageToPortalIn0(String value) {
        return new JAXBElement<String>(_SendUnReadTaskIn0_QNAME, String.class, SendMessageToPortal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/TaskService", name = "out1", scope = SendReadTaskResponse.class)
    public JAXBElement<String> createSendReadTaskResponseOut1(String value) {
        return new JAXBElement<String>(_SendMessageToPortalResponseOut1_QNAME, String.class, SendReadTaskResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/TaskService", name = "out1", scope = SendUnReadTaskResponse.class)
    public JAXBElement<String> createSendUnReadTaskResponseOut1(String value) {
        return new JAXBElement<String>(_SendMessageToPortalResponseOut1_QNAME, String.class, SendUnReadTaskResponse.class, value);
    }

}
