
package com.deppon.oa.oaWorkflow;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.oa.oaWorkflow package. 
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

    private final static QName _DrawbackWorkFlowIn0_QNAME = new QName("http://www.primeton.com/WorkFlowService", "in0");
    private final static QName _CreateWorkFlowWorkFlowException_QNAME = new QName("http://www.primeton.com/WorkFlowService", "createWorkFlow_WorkFlowException");
    private final static QName _DrawbackWorkFlowWorkFlowException_QNAME = new QName("http://www.primeton.com/WorkFlowService", "drawbackWorkFlow_WorkFlowException");
    private final static QName _DeleteWorkFlowWorkFlowException_QNAME = new QName("http://www.primeton.com/WorkFlowService", "deleteWorkFlow_WorkFlowException");
    private final static QName _CreateWorkFlowResponseOut1_QNAME = new QName("http://www.primeton.com/WorkFlowService", "out1");
    private final static QName _WorkFlowExceptionErrorMessage_QNAME = new QName("http://common.workflow.dip", "errorMessage");
    private final static QName _WorkFlowExceptionErrorCode_QNAME = new QName("http://common.workflow.dip", "errorCode");
    private final static QName _DeleteWorkFlowIn1_QNAME = new QName("http://www.primeton.com/WorkFlowService", "in1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.oa.oaWorkflow
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DrawbackWorkFlow }
     * 
     */
    public DrawbackWorkFlow createDrawbackWorkFlow() {
        return new DrawbackWorkFlow();
    }

    /**
     * Create an instance of {@link CreateWorkFlowResponse }
     * 
     */
    public CreateWorkFlowResponse createCreateWorkFlowResponse() {
        return new CreateWorkFlowResponse();
    }

    /**
     * Create an instance of {@link DeleteWorkFlowResponse }
     * 
     */
    public DeleteWorkFlowResponse createDeleteWorkFlowResponse() {
        return new DeleteWorkFlowResponse();
    }

    /**
     * Create an instance of {@link WorkFlowException }
     * 
     */
    public WorkFlowException createWorkFlowException() {
        return new WorkFlowException();
    }

    /**
     * Create an instance of {@link DeleteWorkFlow }
     * 
     */
    public DeleteWorkFlow createDeleteWorkFlow() {
        return new DeleteWorkFlow();
    }

    /**
     * Create an instance of {@link CreateWorkFlow }
     * 
     */
    public CreateWorkFlow createCreateWorkFlow() {
        return new CreateWorkFlow();
    }

    /**
     * Create an instance of {@link DrawbackWorkFlowResponse }
     * 
     */
    public DrawbackWorkFlowResponse createDrawbackWorkFlowResponse() {
        return new DrawbackWorkFlowResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/WorkFlowService", name = "in0", scope = DrawbackWorkFlow.class)
    public JAXBElement<String> createDrawbackWorkFlowIn0(String value) {
        return new JAXBElement<String>(_DrawbackWorkFlowIn0_QNAME, String.class, DrawbackWorkFlow.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WorkFlowException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/WorkFlowService", name = "createWorkFlow_WorkFlowException")
    public JAXBElement<WorkFlowException> createCreateWorkFlowWorkFlowException(WorkFlowException value) {
        return new JAXBElement<WorkFlowException>(_CreateWorkFlowWorkFlowException_QNAME, WorkFlowException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WorkFlowException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/WorkFlowService", name = "drawbackWorkFlow_WorkFlowException")
    public JAXBElement<WorkFlowException> createDrawbackWorkFlowWorkFlowException(WorkFlowException value) {
        return new JAXBElement<WorkFlowException>(_DrawbackWorkFlowWorkFlowException_QNAME, WorkFlowException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WorkFlowException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/WorkFlowService", name = "deleteWorkFlow_WorkFlowException")
    public JAXBElement<WorkFlowException> createDeleteWorkFlowWorkFlowException(WorkFlowException value) {
        return new JAXBElement<WorkFlowException>(_DeleteWorkFlowWorkFlowException_QNAME, WorkFlowException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/WorkFlowService", name = "out1", scope = CreateWorkFlowResponse.class)
    public JAXBElement<String> createCreateWorkFlowResponseOut1(String value) {
        return new JAXBElement<String>(_CreateWorkFlowResponseOut1_QNAME, String.class, CreateWorkFlowResponse.class, value);
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

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/WorkFlowService", name = "in1", scope = DeleteWorkFlow.class)
    public JAXBElement<String> createDeleteWorkFlowIn1(String value) {
        return new JAXBElement<String>(_DeleteWorkFlowIn1_QNAME, String.class, DeleteWorkFlow.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/WorkFlowService", name = "in0", scope = DeleteWorkFlow.class)
    public JAXBElement<String> createDeleteWorkFlowIn0(String value) {
        return new JAXBElement<String>(_DrawbackWorkFlowIn0_QNAME, String.class, DeleteWorkFlow.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/WorkFlowService", name = "in1", scope = CreateWorkFlow.class)
    public JAXBElement<String> createCreateWorkFlowIn1(String value) {
        return new JAXBElement<String>(_DeleteWorkFlowIn1_QNAME, String.class, CreateWorkFlow.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/WorkFlowService", name = "in0", scope = CreateWorkFlow.class)
    public JAXBElement<String> createCreateWorkFlowIn0(String value) {
        return new JAXBElement<String>(_DrawbackWorkFlowIn0_QNAME, String.class, CreateWorkFlow.class, value);
    }

}
