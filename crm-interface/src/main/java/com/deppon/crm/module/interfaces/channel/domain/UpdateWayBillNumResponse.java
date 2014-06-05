
package com.deppon.crm.module.interfaces.channel.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>UpdateWayBillNumResponse complex type
 * <p>
 * 
 * <pre>
 * &lt;complexType name="UpdateWayBillNumResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateWayBillNumResponse", namespace = "http://www.deppon.com/esb/inteface/domain/crm", propOrder = {
    "result"
})
public class UpdateWayBillNumResponse {

    protected boolean result;

    /**
     */
    public boolean isResult() {
        return result;
    }

    /**
     */
    public void setResult(boolean value) {
        this.result = value;
    }

}
