
package com.deppon.interfaces.esb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.interfaces.esb package. 
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

    private final static QName _ReturnContractApplyResultResponse_QNAME = new QName("http://workflow.interfaces.module.crm.deppon.com/", "returnContractApplyResultResponse");
    private final static QName _NormalResultInfo_QNAME = new QName("http://workflow.interfaces.module.crm.deppon.com/", "normalResultInfo");
    private final static QName _ReturnMuchRecompenseResultResponse_QNAME = new QName("http://workflow.interfaces.module.crm.deppon.com/", "returnMuchRecompenseResultResponse");
    private final static QName _MuchResultInfo_QNAME = new QName("http://workflow.interfaces.module.crm.deppon.com/", "muchResultInfo");
    private final static QName _ReturnNormalRecompenseResultResponse_QNAME = new QName("http://workflow.interfaces.module.crm.deppon.com/", "returnNormalRecompenseResultResponse");
    private final static QName _CrmBusiness_QNAME = new QName("http://exception.common.client.module.crm.deppon.com", "CrmBusiness");
    private final static QName _ContractResultInfo_QNAME = new QName("http://workflow.interfaces.module.crm.deppon.com/", "ContractResultInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.interfaces.esb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WorkflowApplyResultInfo }
     * 
     */
    public WorkflowApplyResultInfo createWorkflowApplyResultInfo() {
        return new WorkflowApplyResultInfo();
    }

    /**
     * Create an instance of {@link CrmBusiness }
     * 
     */
    public CrmBusiness createCrmBusiness() {
        return new CrmBusiness();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://workflow.interfaces.module.crm.deppon.com/", name = "returnContractApplyResultResponse")
    public JAXBElement<Boolean> createReturnContractApplyResultResponse(Boolean value) {
        return new JAXBElement<Boolean>(_ReturnContractApplyResultResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WorkflowApplyResultInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://workflow.interfaces.module.crm.deppon.com/", name = "normalResultInfo")
    public JAXBElement<WorkflowApplyResultInfo> createNormalResultInfo(WorkflowApplyResultInfo value) {
        return new JAXBElement<WorkflowApplyResultInfo>(_NormalResultInfo_QNAME, WorkflowApplyResultInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://workflow.interfaces.module.crm.deppon.com/", name = "returnMuchRecompenseResultResponse")
    public JAXBElement<Boolean> createReturnMuchRecompenseResultResponse(Boolean value) {
        return new JAXBElement<Boolean>(_ReturnMuchRecompenseResultResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WorkflowApplyResultInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://workflow.interfaces.module.crm.deppon.com/", name = "muchResultInfo")
    public JAXBElement<WorkflowApplyResultInfo> createMuchResultInfo(WorkflowApplyResultInfo value) {
        return new JAXBElement<WorkflowApplyResultInfo>(_MuchResultInfo_QNAME, WorkflowApplyResultInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://workflow.interfaces.module.crm.deppon.com/", name = "returnNormalRecompenseResultResponse")
    public JAXBElement<Boolean> createReturnNormalRecompenseResultResponse(Boolean value) {
        return new JAXBElement<Boolean>(_ReturnNormalRecompenseResultResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrmBusiness }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exception.common.client.module.crm.deppon.com", name = "CrmBusiness")
    public JAXBElement<CrmBusiness> createCrmBusiness(CrmBusiness value) {
        return new JAXBElement<CrmBusiness>(_CrmBusiness_QNAME, CrmBusiness.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WorkflowApplyResultInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://workflow.interfaces.module.crm.deppon.com/", name = "ContractResultInfo")
    public JAXBElement<WorkflowApplyResultInfo> createContractResultInfo(WorkflowApplyResultInfo value) {
        return new JAXBElement<WorkflowApplyResultInfo>(_ContractResultInfo_QNAME, WorkflowApplyResultInfo.class, null, value);
    }

}
