
package com.deppon.erp.custAndOrder;

import java.util.Date;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.erp.custAndOrder package. 
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

    private final static QName _QueryExceptionOrdersResponse_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryExceptionOrdersResponse");
    private final static QName _EndTime_QNAME = new QName("http://service.cms.ws.deppon.com/", "endTime");
    private final static QName _QueryExceptionCustResponse_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryExceptionCustResponse");
    private final static QName _ERPBusinessFault_QNAME = new QName("http://service.cms.ws.deppon.com/", "ERPBusinessFault");
    private final static QName _StartTime_QNAME = new QName("http://service.cms.ws.deppon.com/", "startTime");
    private final static QName _QueryExceptionCust_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryExceptionCust");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.erp.custAndOrder
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ContractDept }
     * 
     */
    public ContractDept createContractDept() {
        return new ContractDept();
    }

    /**
     * Create an instance of {@link StringArray }
     * 
     */
    public StringArray createStringArray() {
        return new StringArray();
    }

    /**
     * Create an instance of {@link Contract }
     * 
     */
    public Contract createContract() {
        return new Contract();
    }

    /**
     * Create an instance of {@link ExceptionCustArray }
     * 
     */
    public ExceptionCustArray createExceptionCustArray() {
        return new ExceptionCustArray();
    }

    /**
     * Create an instance of {@link ExceptionOrder }
     * 
     */
    public ExceptionOrder createExceptionOrder() {
        return new ExceptionOrder();
    }

    /**
     * Create an instance of {@link ExceptionCust }
     * 
     */
    public ExceptionCust createExceptionCust() {
        return new ExceptionCust();
    }

    /**
     * Create an instance of {@link ExceptionOrderArray }
     * 
     */
    public ExceptionOrderArray createExceptionOrderArray() {
        return new ExceptionOrderArray();
    }

    /**
     * Create an instance of {@link ERPBusinessException }
     * 
     */
    public ERPBusinessException createERPBusinessException() {
        return new ERPBusinessException();
    }

    /**
     * Create an instance of {@link LinkMan }
     * 
     */
    public LinkMan createLinkMan() {
        return new LinkMan();
    }

    /**
     * Create an instance of {@link OpenBank }
     * 
     */
    public OpenBank createOpenBank() {
        return new OpenBank();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExceptionOrderArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryExceptionOrdersResponse")
    public JAXBElement<ExceptionOrderArray> createQueryExceptionOrdersResponse(ExceptionOrderArray value) {
        return new JAXBElement<ExceptionOrderArray>(_QueryExceptionOrdersResponse_QNAME, ExceptionOrderArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Date }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "endTime")
    @XmlJavaTypeAdapter(Adapter1 .class)
    public JAXBElement<Date> createEndTime(Date value) {
        return new JAXBElement<Date>(_EndTime_QNAME, Date.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExceptionCustArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryExceptionCustResponse")
    public JAXBElement<ExceptionCustArray> createQueryExceptionCustResponse(ExceptionCustArray value) {
        return new JAXBElement<ExceptionCustArray>(_QueryExceptionCustResponse_QNAME, ExceptionCustArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ERPBusinessException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "ERPBusinessFault")
    public JAXBElement<ERPBusinessException> createERPBusinessFault(ERPBusinessException value) {
        return new JAXBElement<ERPBusinessException>(_ERPBusinessFault_QNAME, ERPBusinessException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Date }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "startTime")
    @XmlJavaTypeAdapter(Adapter1 .class)
    public JAXBElement<Date> createStartTime(Date value) {
        return new JAXBElement<Date>(_StartTime_QNAME, Date.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryExceptionCust")
    public JAXBElement<StringArray> createQueryExceptionCust(StringArray value) {
        return new JAXBElement<StringArray>(_QueryExceptionCust_QNAME, StringArray.class, null, value);
    }

}
