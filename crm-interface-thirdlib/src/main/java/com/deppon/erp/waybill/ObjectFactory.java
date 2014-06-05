
package com.deppon.erp.waybill;

import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.erp.waybill package. 
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

    private final static QName _QueryWayBillCustomer_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryWayBillCustomer");
    private final static QName _QueryNear3MonthAmount_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryNear3MonthAmount");
    private final static QName _CustMobile_QNAME = new QName("http://service.cms.ws.deppon.com/", "custMobile");
    private final static QName _Cust_QNAME = new QName("http://service.cms.ws.deppon.com/", "cust");
    private final static QName _QueryWayBillNotSign_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryWayBillNotSign");
    private final static QName _QueryWayBillResponse_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryWayBillResponse");
    private final static QName _WayBillNumAfter_QNAME = new QName("http://service.cms.ws.deppon.com/", "wayBillNumAfter");
    private final static QName _QueryWayBillCustomerResponse_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryWayBillCustomerResponse");
    private final static QName _OrderBillNum_QNAME = new QName("http://service.cms.ws.deppon.com/", "orderBillNum");
    private final static QName _LinkWayBillAndOrderBillResponse_QNAME = new QName("http://service.cms.ws.deppon.com/", "linkWayBillAndOrderBillResponse");
    private final static QName _QueryWayBillRecompenseResponse_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryWayBillRecompenseResponse");
    private final static QName _QueryCustAmountResponse_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryCustAmountResponse");
    private final static QName _QueryWayBillSimpleResponse_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryWayBillSimpleResponse");
    private final static QName _EndDate_QNAME = new QName("http://service.cms.ws.deppon.com/", "endDate");
    private final static QName _QueryWayBillRecompense_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryWayBillRecompense");
    private final static QName _CustPhone_QNAME = new QName("http://service.cms.ws.deppon.com/", "custPhone");
    private final static QName _WayBillNumBeofre_QNAME = new QName("http://service.cms.ws.deppon.com/", "wayBillNumBeofre");
    private final static QName _StartDate_QNAME = new QName("http://service.cms.ws.deppon.com/", "startDate");
    private final static QName _QueryWayBill_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryWayBill");
    private final static QName _ERPBusinessFault_QNAME = new QName("http://service.cms.ws.deppon.com/", "ERPBusinessFault");
    private final static QName _QueryWayBillNotSignResponse_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryWayBillNotSignResponse");
    private final static QName _LinkManNum_QNAME = new QName("http://service.cms.ws.deppon.com/", "linkManNum");
    private final static QName _QueryNear3MonthAmountResponse_QNAME = new QName("http://service.cms.ws.deppon.com/", "queryNear3MonthAmountResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.erp.waybill
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WayBill }
     * 
     */
    public WayBill createWayBill() {
        return new WayBill();
    }

    /**
     * Create an instance of {@link WayBillRecompense }
     * 
     */
    public WayBillRecompense createWayBillRecompense() {
        return new WayBillRecompense();
    }

    /**
     * Create an instance of {@link WayBillSimpleArray }
     * 
     */
    public WayBillSimpleArray createWayBillSimpleArray() {
        return new WayBillSimpleArray();
    }

    /**
     * Create an instance of {@link ERPBusinessException }
     * 
     */
    public ERPBusinessException createERPBusinessException() {
        return new ERPBusinessException();
    }

    /**
     * Create an instance of {@link CustAmountArray }
     * 
     */
    public CustAmountArray createCustAmountArray() {
        return new CustAmountArray();
    }

    /**
     * Create an instance of {@link WayBillNotSign }
     * 
     */
    public WayBillNotSign createWayBillNotSign() {
        return new WayBillNotSign();
    }

    /**
     * Create an instance of {@link WayBillCustomer }
     * 
     */
    public WayBillCustomer createWayBillCustomer() {
        return new WayBillCustomer();
    }

    /**
     * Create an instance of {@link CustAmount }
     * 
     */
    public CustAmount createCustAmount() {
        return new CustAmount();
    }

    /**
     * Create an instance of {@link WayBillSimple }
     * 
     */
    public WayBillSimple createWayBillSimple() {
        return new WayBillSimple();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryWayBillCustomer")
    public JAXBElement<String> createQueryWayBillCustomer(String value) {
        return new JAXBElement<String>(_QueryWayBillCustomer_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryNear3MonthAmount")
    public JAXBElement<String> createQueryNear3MonthAmount(String value) {
        return new JAXBElement<String>(_QueryNear3MonthAmount_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "custMobile")
    public JAXBElement<String> createCustMobile(String value) {
        return new JAXBElement<String>(_CustMobile_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "cust")
    public JAXBElement<String> createCust(String value) {
        return new JAXBElement<String>(_Cust_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryWayBillNotSign")
    public JAXBElement<String> createQueryWayBillNotSign(String value) {
        return new JAXBElement<String>(_QueryWayBillNotSign_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WayBill }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryWayBillResponse")
    public JAXBElement<WayBill> createQueryWayBillResponse(WayBill value) {
        return new JAXBElement<WayBill>(_QueryWayBillResponse_QNAME, WayBill.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "wayBillNumAfter")
    public JAXBElement<String> createWayBillNumAfter(String value) {
        return new JAXBElement<String>(_WayBillNumAfter_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WayBillCustomer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryWayBillCustomerResponse")
    public JAXBElement<WayBillCustomer> createQueryWayBillCustomerResponse(WayBillCustomer value) {
        return new JAXBElement<WayBillCustomer>(_QueryWayBillCustomerResponse_QNAME, WayBillCustomer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "orderBillNum")
    public JAXBElement<String> createOrderBillNum(String value) {
        return new JAXBElement<String>(_OrderBillNum_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "linkWayBillAndOrderBillResponse")
    public JAXBElement<Boolean> createLinkWayBillAndOrderBillResponse(Boolean value) {
        return new JAXBElement<Boolean>(_LinkWayBillAndOrderBillResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WayBillRecompense }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryWayBillRecompenseResponse")
    public JAXBElement<WayBillRecompense> createQueryWayBillRecompenseResponse(WayBillRecompense value) {
        return new JAXBElement<WayBillRecompense>(_QueryWayBillRecompenseResponse_QNAME, WayBillRecompense.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustAmountArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryCustAmountResponse")
    public JAXBElement<CustAmountArray> createQueryCustAmountResponse(CustAmountArray value) {
        return new JAXBElement<CustAmountArray>(_QueryCustAmountResponse_QNAME, CustAmountArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WayBillSimpleArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryWayBillSimpleResponse")
    public JAXBElement<WayBillSimpleArray> createQueryWayBillSimpleResponse(WayBillSimpleArray value) {
        return new JAXBElement<WayBillSimpleArray>(_QueryWayBillSimpleResponse_QNAME, WayBillSimpleArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Date }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "endDate")
    @XmlJavaTypeAdapter(Adapter1 .class)
    public JAXBElement<Date> createEndDate(Date value) {
        return new JAXBElement<Date>(_EndDate_QNAME, Date.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryWayBillRecompense")
    public JAXBElement<String> createQueryWayBillRecompense(String value) {
        return new JAXBElement<String>(_QueryWayBillRecompense_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "custPhone")
    public JAXBElement<String> createCustPhone(String value) {
        return new JAXBElement<String>(_CustPhone_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "wayBillNumBeofre")
    public JAXBElement<String> createWayBillNumBeofre(String value) {
        return new JAXBElement<String>(_WayBillNumBeofre_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Date }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "startDate")
    @XmlJavaTypeAdapter(Adapter1 .class)
    public JAXBElement<Date> createStartDate(Date value) {
        return new JAXBElement<Date>(_StartDate_QNAME, Date.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryWayBill")
    public JAXBElement<String> createQueryWayBill(String value) {
        return new JAXBElement<String>(_QueryWayBill_QNAME, String.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link WayBillNotSign }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryWayBillNotSignResponse")
    public JAXBElement<WayBillNotSign> createQueryWayBillNotSignResponse(WayBillNotSign value) {
        return new JAXBElement<WayBillNotSign>(_QueryWayBillNotSignResponse_QNAME, WayBillNotSign.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "linkManNum")
    public JAXBElement<String> createLinkManNum(String value) {
        return new JAXBElement<String>(_LinkManNum_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cms.ws.deppon.com/", name = "queryNear3MonthAmountResponse")
    public JAXBElement<BigDecimal> createQueryNear3MonthAmountResponse(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_QueryNear3MonthAmountResponse_QNAME, BigDecimal.class, null, value);
    }

}
