
package com.deppon.oa.goodDelay;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SQLException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SQLException">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SQLState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nextException" type="{http://sql.java}SQLException" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SQLException", namespace = "http://sql.java", propOrder = {
    "sqlState",
    "errorCode",
    "nextException"
})
public class SQLException {

    @XmlElementRef(name = "SQLState", namespace = "http://sql.java", type = JAXBElement.class)
    protected JAXBElement<String> sqlState;
    protected Integer errorCode;
    @XmlElementRef(name = "nextException", namespace = "http://sql.java", type = JAXBElement.class)
    protected JAXBElement<SQLException> nextException;

    /**
     * Gets the value of the sqlState property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSQLState() {
        return sqlState;
    }

    /**
     * Sets the value of the sqlState property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSQLState(JAXBElement<String> value) {
        this.sqlState = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setErrorCode(Integer value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the nextException property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SQLException }{@code >}
     *     
     */
    public JAXBElement<SQLException> getNextException() {
        return nextException;
    }

    /**
     * Sets the value of the nextException property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SQLException }{@code >}
     *     
     */
    public void setNextException(JAXBElement<SQLException> value) {
        this.nextException = ((JAXBElement<SQLException> ) value);
    }

}
