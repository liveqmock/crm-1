
package com.deppon.esb.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.esb.ws package. 
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

    private final static QName _Response_QNAME = new QName("http://common.esb.deppon.com", "response");
    private final static QName _Message_QNAME = new QName("http://common.esb.deppon.com", "message");
    private final static QName _ServiceCode_QNAME = new QName("http://common.esb.deppon.com", "serviceCode");
    private final static QName _JsonProcess_QNAME = new QName("http://client.common.esb.deppon.com/", "jsonProcess");
    private final static QName _Stacktrace_QNAME = new QName("http://common.esb.deppon.com", "stacktrace");
    private final static QName _JsonProcessResponse_QNAME = new QName("http://client.common.esb.deppon.com/", "jsonProcessResponse");
    private final static QName _ResponseTime_QNAME = new QName("http://common.esb.deppon.com", "responseTime");
    private final static QName _Exception_QNAME = new QName("http://client.common.esb.deppon.com/", "Exception");
    private final static QName _Status_QNAME = new QName("http://common.esb.deppon.com", "status");
    private final static QName _RequestId_QNAME = new QName("http://common.esb.deppon.com", "requestId");
    private final static QName _RequestTime_QNAME = new QName("http://common.esb.deppon.com", "requestTime");
    private final static QName _SystemName_QNAME = new QName("http://common.esb.deppon.com", "systemName");
    private final static QName _ProcessResponse_QNAME = new QName("http://client.common.esb.deppon.com/", "processResponse");
    private final static QName _Process_QNAME = new QName("http://client.common.esb.deppon.com/", "process");
    private final static QName _ResponseClassName_QNAME = new QName("http://common.esb.deppon.com", "responseClassName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.esb.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EsbRequest }
     * 
     */
    public EsbRequest createEsbRequest() {
        return new EsbRequest();
    }

    /**
     * Create an instance of {@link EsbJsonRequest }
     * 
     */
    public EsbJsonRequest createEsbJsonRequest() {
        return new EsbJsonRequest();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link JsonProcessResponse }
     * 
     */
    public JsonProcessResponse createJsonProcessResponse() {
        return new JsonProcessResponse();
    }

    /**
     * Create an instance of {@link EsbResponse }
     * 
     */
    public EsbResponse createEsbResponse() {
        return new EsbResponse();
    }

    /**
     * Create an instance of {@link ProcessResponse }
     * 
     */
    public ProcessResponse createProcessResponse() {
        return new ProcessResponse();
    }

    /**
     * Create an instance of {@link JsonProcess }
     * 
     */
    public JsonProcess createJsonProcess() {
        return new JsonProcess();
    }

    /**
     * Create an instance of {@link Process }
     * 
     */
    public Process createProcess() {
        return new Process();
    }

    /**
     * Create an instance of {@link EsbJsonResponse }
     * 
     */
    public EsbJsonResponse createEsbJsonResponse() {
        return new EsbJsonResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.esb.deppon.com", name = "response")
    public JAXBElement<String> createResponse(String value) {
        return new JAXBElement<String>(_Response_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.esb.deppon.com", name = "message")
    public JAXBElement<String> createMessage(String value) {
        return new JAXBElement<String>(_Message_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.esb.deppon.com", name = "serviceCode")
    public JAXBElement<String> createServiceCode(String value) {
        return new JAXBElement<String>(_ServiceCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JsonProcess }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.common.esb.deppon.com/", name = "jsonProcess")
    public JAXBElement<JsonProcess> createJsonProcess(JsonProcess value) {
        return new JAXBElement<JsonProcess>(_JsonProcess_QNAME, JsonProcess.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.esb.deppon.com", name = "stacktrace")
    public JAXBElement<String> createStacktrace(String value) {
        return new JAXBElement<String>(_Stacktrace_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JsonProcessResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.common.esb.deppon.com/", name = "jsonProcessResponse")
    public JAXBElement<JsonProcessResponse> createJsonProcessResponse(JsonProcessResponse value) {
        return new JAXBElement<JsonProcessResponse>(_JsonProcessResponse_QNAME, JsonProcessResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.esb.deppon.com", name = "responseTime")
    public JAXBElement<XMLGregorianCalendar> createResponseTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ResponseTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.common.esb.deppon.com/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.esb.deppon.com", name = "status")
    public JAXBElement<String> createStatus(String value) {
        return new JAXBElement<String>(_Status_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.esb.deppon.com", name = "requestId")
    public JAXBElement<String> createRequestId(String value) {
        return new JAXBElement<String>(_RequestId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.esb.deppon.com", name = "requestTime")
    public JAXBElement<XMLGregorianCalendar> createRequestTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_RequestTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.esb.deppon.com", name = "systemName")
    public JAXBElement<String> createSystemName(String value) {
        return new JAXBElement<String>(_SystemName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.common.esb.deppon.com/", name = "processResponse")
    public JAXBElement<ProcessResponse> createProcessResponse(ProcessResponse value) {
        return new JAXBElement<ProcessResponse>(_ProcessResponse_QNAME, ProcessResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Process }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.common.esb.deppon.com/", name = "process")
    public JAXBElement<Process> createProcess(Process value) {
        return new JAXBElement<Process>(_Process_QNAME, Process.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.esb.deppon.com", name = "responseClassName")
    public JAXBElement<String> createResponseClassName(String value) {
        return new JAXBElement<String>(_ResponseClassName_QNAME, String.class, null, value);
    }

}
