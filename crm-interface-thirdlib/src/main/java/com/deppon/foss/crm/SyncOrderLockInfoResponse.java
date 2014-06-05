package com.deppon.foss.crm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * 处理明细：FOSS对有未受理订单的部门处理的详细结果（批量）
 * 
 * 
 * <p>
 * Java class for SyncOrderLockInfoResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SyncOrderLockInfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultInfo" type="{http://www.deppon.com/esb/inteface/domain/crm}ResultDetal" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncOrderLockInfoResponse", propOrder = { "resultInfo" })
public class SyncOrderLockInfoResponse {

	@XmlElement(name = "ResultInfo", required = true)
	protected List<ResultDetal> resultInfo;

	/**
	 * Gets the value of the resultInfo property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the resultInfo property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getResultInfo().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ResultDetal }
	 * 
	 * 
	 */
	public List<ResultDetal> getResultInfo() {
		if (resultInfo == null) {
			resultInfo = new ArrayList<ResultDetal>();
		}
		return this.resultInfo;
	}

}
