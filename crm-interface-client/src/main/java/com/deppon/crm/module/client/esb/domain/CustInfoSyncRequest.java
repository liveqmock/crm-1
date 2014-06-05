
package com.deppon.crm.module.client.esb.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * FOSS���ͻ���ϵͳ�еĿ�������FOSS������ϵͳ�е��˴��ջ����������Ҫ�ͻ����ͻ�������Ϣ������Ϣ�洢��CRMϵͳ�У�Ϊ����FOSSϵͳ������ģ����ڸò�����Ϣ�������轫�ͻ����ͻ�������Ϣͬ����FOSSϵͳ
 * 			
 * 
 * <p>CustInfoSyncRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CustInfoSyncRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="custBaseInfo" type="{http://www.deppon.com/esb/inteface/domain/crm}CustBaseInfo"/>
 *         &lt;element name="custBankInfoList" type="{http://www.deppon.com/esb/inteface/domain/crm}CustBankInfo" maxOccurs="unbounded"/>
 *         &lt;element name="linkManInfoList" type="{http://www.deppon.com/esb/inteface/domain/crm}LinkManInfo" maxOccurs="unbounded"/>
 *         &lt;element name="contractInfoList" type="{http://www.deppon.com/esb/inteface/domain/crm}ContractInfo" maxOccurs="unbounded"/>
 *         &lt;element name="custAddressInfoList" type="{http://www.deppon.com/esb/inteface/domain/crm}CustAddressInfo" maxOccurs="unbounded"/>
 *         &lt;element name="departmentInfoList" type="{http://www.deppon.com/esb/inteface/domain/crm}DepartmentInfo" maxOccurs="unbounded"/>
 *         &lt;element name="preferentialInfoList" type="{http://www.deppon.com/esb/inteface/domain/crm}PreferentialInfo" maxOccurs="unbounded"/>
 *         &lt;element name="linkManAddressInfoList" type="{http://www.deppon.com/esb/inteface/domain/crm}LinkManAddresInfo" maxOccurs="unbounded"/>
 *         &lt;element name="operateType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustInfoSyncRequest", propOrder = {
    "custBaseInfo",
    "custBankInfoList",
    "linkManInfoList",
    "contractInfoList",
    "custAddressInfoList",
    "departmentInfoList",
    "preferentialInfoList",
    "linkManAddressInfoList",
    "operateType"
})
public class CustInfoSyncRequest {

    @XmlElement(required = true)
    protected CustBaseInfo custBaseInfo;
    @XmlElement(required = true)
    protected List<CustBankInfo> custBankInfoList;
    @XmlElement(required = true)
    protected List<LinkManInfo> linkManInfoList;
    @XmlElement(required = true)
    protected List<ContractInfo> contractInfoList;
    @XmlElement(required = true)
    protected List<CustAddressInfo> custAddressInfoList;
    @XmlElement(required = true)
    protected List<DepartmentInfo> departmentInfoList;
    @XmlElement(required = true)
    protected List<PreferentialInfo> preferentialInfoList;
    @XmlElement(required = true)
    protected List<LinkManAddresInfo> linkManAddressInfoList;
    protected int operateType;

    /**
     * ��ȡcustBaseInfo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CustBaseInfo }
     *     
     */
    public CustBaseInfo getCustBaseInfo() {
        return custBaseInfo;
    }

    /**
     * ����custBaseInfo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CustBaseInfo }
     *     
     */
    public void setCustBaseInfo(CustBaseInfo value) {
        this.custBaseInfo = value;
    }

    /**
     * Gets the value of the custBankInfoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the custBankInfoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustBankInfoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustBankInfo }
     * 
     * 
     */
    public List<CustBankInfo> getCustBankInfoList() {
        if (custBankInfoList == null) {
            custBankInfoList = new ArrayList<CustBankInfo>();
        }
        return this.custBankInfoList;
    }

    /**
     * Gets the value of the linkManInfoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkManInfoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkManInfoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkManInfo }
     * 
     * 
     */
    public List<LinkManInfo> getLinkManInfoList() {
        if (linkManInfoList == null) {
            linkManInfoList = new ArrayList<LinkManInfo>();
        }
        return this.linkManInfoList;
    }

    /**
     * Gets the value of the contractInfoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contractInfoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContractInfoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContractInfo }
     * 
     * 
     */
    public List<ContractInfo> getContractInfoList() {
        if (contractInfoList == null) {
            contractInfoList = new ArrayList<ContractInfo>();
        }
        return this.contractInfoList;
    }

    /**
     * Gets the value of the custAddressInfoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the custAddressInfoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustAddressInfoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustAddressInfo }
     * 
     * 
     */
    public List<CustAddressInfo> getCustAddressInfoList() {
        if (custAddressInfoList == null) {
            custAddressInfoList = new ArrayList<CustAddressInfo>();
        }
        return this.custAddressInfoList;
    }

    /**
     * Gets the value of the departmentInfoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the departmentInfoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDepartmentInfoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DepartmentInfo }
     * 
     * 
     */
    public List<DepartmentInfo> getDepartmentInfoList() {
        if (departmentInfoList == null) {
            departmentInfoList = new ArrayList<DepartmentInfo>();
        }
        return this.departmentInfoList;
    }

    /**
     * Gets the value of the preferentialInfoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preferentialInfoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreferentialInfoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PreferentialInfo }
     * 
     * 
     */
    public List<PreferentialInfo> getPreferentialInfoList() {
        if (preferentialInfoList == null) {
            preferentialInfoList = new ArrayList<PreferentialInfo>();
        }
        return this.preferentialInfoList;
    }

    /**
     * Gets the value of the linkManAddressInfoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkManAddressInfoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkManAddressInfoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkManAddresInfo }
     * 
     * 
     */
    public List<LinkManAddresInfo> getLinkManAddressInfoList() {
        if (linkManAddressInfoList == null) {
            linkManAddressInfoList = new ArrayList<LinkManAddresInfo>();
        }
        return this.linkManAddressInfoList;
    }

    /**
     * ��ȡoperateType���Ե�ֵ��
     * 
     */
    public int getOperateType() {
        return operateType;
    }

    /**
     * ����operateType���Ե�ֵ��
     * 
     */
    public void setOperateType(int value) {
        this.operateType = value;
    }

}
