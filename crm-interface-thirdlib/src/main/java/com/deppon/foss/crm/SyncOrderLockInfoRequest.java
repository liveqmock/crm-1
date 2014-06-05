package com.deppon.foss.crm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * 推送消息：CRM将有未受理订单的部门的信息发送给FOSS，由FOSS对界面进行相应控制(批量)
 * 
 * 
 * <p>
 * Java class for SyncOrderLockInfoRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SyncOrderLockInfoRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SyncOrderLockInfo" type="{http://www.deppon.com/esb/inteface/domain/crm}SyncOrderLockInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncOrderLockInfoRequest", propOrder = { "syncOrderLockInfo" })
public class SyncOrderLockInfoRequest {

	@XmlElement(name = "SyncOrderLockInfo", required = true)
	protected List<SyncOrderLockInfo> syncOrderLockInfo;

	/**
	 * Gets the value of the syncOrderLockInfo property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the syncOrderLockInfo property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSyncOrderLockInfo().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link SyncOrderLockInfo }
	 * 
	 * 
	 */
	public List<SyncOrderLockInfo> getSyncOrderLockInfo() {
		if (syncOrderLockInfo == null) {
			syncOrderLockInfo = new ArrayList<SyncOrderLockInfo>();
		}
		return this.syncOrderLockInfo;
	}

}
