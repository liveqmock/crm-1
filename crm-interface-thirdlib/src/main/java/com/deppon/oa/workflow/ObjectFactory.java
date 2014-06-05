
package com.deppon.oa.workflow;

import java.util.Date;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.oa.workflow package. 
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

    private final static QName _GetWorkFlowApproveStateWorkFlowException_QNAME = new QName("http://www.primeton.com/QueryWorkFlowStateService", "getWorkFlowApproveState_WorkFlowException");
    private final static QName _GetWorkFlowApproveStateIn1_QNAME = new QName("http://www.primeton.com/QueryWorkFlowStateService", "in1");
    private final static QName _GetWorkFlowApproveStateIn2_QNAME = new QName("http://www.primeton.com/QueryWorkFlowStateService", "in2");
    private final static QName _GetWorkFlowApproveStateIn0_QNAME = new QName("http://www.primeton.com/QueryWorkFlowStateService", "in0");
    private final static QName _GetWorkFlowApproveStateResponseOut1_QNAME = new QName("http://www.primeton.com/QueryWorkFlowStateService", "out1");
    private final static QName _WorkFlowExceptionErrorMessage_QNAME = new QName("http://common.workflow.dip", "errorMessage");
    private final static QName _WorkFlowExceptionErrorCode_QNAME = new QName("http://common.workflow.dip", "errorCode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.oa.workflow
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetWorkFlowApproveState }
     * 
     */
    public GetWorkFlowApproveState createGetWorkFlowApproveState() {
        return new GetWorkFlowApproveState();
    }

    /**
     * Create an instance of {@link GetWorkFlowApproveStateResponse }
     * 
     */
    public GetWorkFlowApproveStateResponse createGetWorkFlowApproveStateResponse() {
        return new GetWorkFlowApproveStateResponse();
    }

    /**
     * Create an instance of {@link WorkFlowException }
     * 
     */
    public WorkFlowException createWorkFlowException() {
        return new WorkFlowException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WorkFlowException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/QueryWorkFlowStateService", name = "getWorkFlowApproveState_WorkFlowException")
    public JAXBElement<WorkFlowException> createGetWorkFlowApproveStateWorkFlowException(WorkFlowException value) {
        return new JAXBElement<WorkFlowException>(_GetWorkFlowApproveStateWorkFlowException_QNAME, WorkFlowException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Date }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/QueryWorkFlowStateService", name = "in1", scope = GetWorkFlowApproveState.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    public JAXBElement<Date> createGetWorkFlowApproveStateIn1(Date value) {
        return new JAXBElement<Date>(_GetWorkFlowApproveStateIn1_QNAME, Date.class, GetWorkFlowApproveState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/QueryWorkFlowStateService", name = "in2", scope = GetWorkFlowApproveState.class)
    public JAXBElement<String> createGetWorkFlowApproveStateIn2(String value) {
        return new JAXBElement<String>(_GetWorkFlowApproveStateIn2_QNAME, String.class, GetWorkFlowApproveState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Date }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/QueryWorkFlowStateService", name = "in0", scope = GetWorkFlowApproveState.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    public JAXBElement<Date> createGetWorkFlowApproveStateIn0(Date value) {
        return new JAXBElement<Date>(_GetWorkFlowApproveStateIn0_QNAME, Date.class, GetWorkFlowApproveState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/QueryWorkFlowStateService", name = "out1", scope = GetWorkFlowApproveStateResponse.class)
    public JAXBElement<String> createGetWorkFlowApproveStateResponseOut1(String value) {
        return new JAXBElement<String>(_GetWorkFlowApproveStateResponseOut1_QNAME, String.class, GetWorkFlowApproveStateResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.workflow.dip", name = "errorMessage", scope = WorkFlowException.class)
    public JAXBElement<String> createWorkFlowExceptionErrorMessage(String value) {
        return new JAXBElement<String>(_WorkFlowExceptionErrorMessage_QNAME, String.class, WorkFlowException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.workflow.dip", name = "errorCode", scope = WorkFlowException.class)
    public JAXBElement<String> createWorkFlowExceptionErrorCode(String value) {
        return new JAXBElement<String>(_WorkFlowExceptionErrorCode_QNAME, String.class, WorkFlowException.class, value);
    }

}
