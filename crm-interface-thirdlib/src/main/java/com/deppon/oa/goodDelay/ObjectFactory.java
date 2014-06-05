
package com.deppon.oa.goodDelay;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.oa.goodDelay package. 
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

    private final static QName _QueryGoodDelayException_QNAME = new QName("http://www.primeton.com/OAErrorForCRMImplService", "queryGoodDelay_Exception");
    private final static QName _QueryGoodDelaySQLException_QNAME = new QName("http://www.primeton.com/OAErrorForCRMImplService", "queryGoodDelay_SQLException");
    private final static QName _SQLExceptionNextException_QNAME = new QName("http://sql.java", "nextException");
    private final static QName _SQLExceptionSQLState_QNAME = new QName("http://sql.java", "SQLState");
    private final static QName _QueryGoodDelayIn0_QNAME = new QName("http://www.primeton.com/OAErrorForCRMImplService", "in0");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.oa.goodDelay
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryGoodDelay }
     * 
     */
    public QueryGoodDelay createQueryGoodDelay() {
        return new QueryGoodDelay();
    }

    /**
     * Create an instance of {@link SQLException }
     * 
     */
    public SQLException createSQLException() {
        return new SQLException();
    }

    /**
     * Create an instance of {@link QueryGoodDelayResponse }
     * 
     */
    public QueryGoodDelayResponse createQueryGoodDelayResponse() {
        return new QueryGoodDelayResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/OAErrorForCRMImplService", name = "queryGoodDelay_Exception")
    public JAXBElement<Exception> createQueryGoodDelayException(Exception value) {
        return new JAXBElement<Exception>(_QueryGoodDelayException_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SQLException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/OAErrorForCRMImplService", name = "queryGoodDelay_SQLException")
    public JAXBElement<SQLException> createQueryGoodDelaySQLException(SQLException value) {
        return new JAXBElement<SQLException>(_QueryGoodDelaySQLException_QNAME, SQLException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SQLException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sql.java", name = "nextException", scope = SQLException.class)
    public JAXBElement<SQLException> createSQLExceptionNextException(SQLException value) {
        return new JAXBElement<SQLException>(_SQLExceptionNextException_QNAME, SQLException.class, SQLException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sql.java", name = "SQLState", scope = SQLException.class)
    public JAXBElement<String> createSQLExceptionSQLState(String value) {
        return new JAXBElement<String>(_SQLExceptionSQLState_QNAME, String.class, SQLException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/OAErrorForCRMImplService", name = "in0", scope = QueryGoodDelay.class)
    public JAXBElement<String> createQueryGoodDelayIn0(String value) {
        return new JAXBElement<String>(_QueryGoodDelayIn0_QNAME, String.class, QueryGoodDelay.class, value);
    }

}
