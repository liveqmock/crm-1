package com.deppon.crm.module.client.esb.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * ɢ����Ϣ
 * 
 * <p>
 * ScatterCustInfo complex type�� Java �ࡣ
 * 
 * <p>
 * ����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ScatterCustInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fscatterid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fscattertype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fprocredit" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fbank" type="{http://www.deppon.com/esb/inteface/domain/crm}ScatterCustBankInfo" maxOccurs="unbounded"/>
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
@XmlType(name = "ScatterCustInfo", propOrder = {
    "fscatterid",
    "fscattertype",
    "fprocredit",
    "fbank",
    "operateType",
    "custName",
    "custCode",
    "custNumber",
    "contactName",
    "mobilePhone",
    "telephone",
    "contactAddress",
    "custStatus",
    "lastModifyTime",
    "deptStandardcode",
    "financeCancel"
})
/**
 * @作者：罗典
 * @描述：散客信息
 * @时间：2012-11-7
 * */
public class ScatterCustInfo {

	// 散客ID
	@XmlElement(required = true)
	protected String fscatterid;
	// 散客类型
	@XmlElement(required = true)
	protected String fscattertype;
	// 临欠额度
	@XmlElement(required = true)
	protected BigDecimal fprocredit;
	// 客户开户银行
	@XmlElement(required = true)
	protected List<ScatterCustBankInfo> fbank;
	// 操作类别：整数类型，1、新增；2、修改；3、删除；
	@XmlElement(required = false)
	protected int operateType;
	// 客户名称
	@XmlElement(required = true)
	protected String custName;
	// 客户编码
	@XmlElement(required = true)
	protected String custCode;
	// 会员号
	@XmlElement(required = false)
	protected String custNumber;
	// 联系人姓名
	@XmlElement(required = true)
	protected String contactName;
	// 手机号码
	@XmlElement(required = true)
	protected String mobilePhone;
	// 电话号码
	@XmlElement(required = false)
	protected String telephone;
	// 联系人地址
	@XmlElement(required = true)
	protected String contactAddress;
	// 散客状态：0-有效，2-无效
	@XmlElement(required = false)
	protected int custStatus;
	@XmlElement(required = true)
	@XmlSchemaType(name = "dateTime")
	protected Date lastModifyTime;
	//临客所属部门标杆编码
	@XmlElement(required = true)
	protected String deptStandardcode;
	//财务作废
	@XmlElement(required = true)
	protected boolean financeCancel;
	
	public boolean isFinanceCancel() {
		return financeCancel;
	}

	public void setFinanceCancel(boolean financeCancel) {
		this.financeCancel = financeCancel;
	}
	
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public int getCustStatus() {
		return custStatus;
	}

	/**
	 * ��ȡfscatterid���Ե�ֵ��
	 * 
	 * @return possible object is {@link String }
	 * 
	 */

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public void setCustStatus(int custStatus) {
		this.custStatus = custStatus;
	}

	public void setFbank(List<ScatterCustBankInfo> fbank) {
		this.fbank = fbank;
	}

	/**
	 * ����fscatterid���Ե�ֵ��
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public String getFscatterid() {
		return fscatterid;
	}

	public void setFscatterid(String value) {
		this.fscatterid = value;
	}

	/**
	 * ��ȡfscattertype���Ե�ֵ��
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFscattertype() {
		return fscattertype;
	}

	/**
	 * ����fscattertype���Ե�ֵ��
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFscattertype(String value) {
		this.fscattertype = value;
	}

	/**
	 * ��ȡfprocredit���Ե�ֵ��
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getFprocredit() {
		return fprocredit;
	}

	/**
	 * ����fprocredit���Ե�ֵ��
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setFprocredit(BigDecimal value) {
		this.fprocredit = value;
	}
	
	/**
	 *@return  deptStandardcode;
	 */
	public String getDeptStandardcode() {
		return deptStandardcode;
	}

	/**
	 * @param deptStandardcode : set the property deptStandardcode.
	 */
	public void setDeptStandardcode(String deptStandardcode) {
		this.deptStandardcode = deptStandardcode;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	/**
	 * Gets the value of the fbank property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the fbank property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getFbank().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ScatterCustBankInfo }
	 * 
	 * 
	 */
	public List<ScatterCustBankInfo> getFbank() {
		if (fbank == null) {
			fbank = new ArrayList<ScatterCustBankInfo>();
		}
		return this.fbank;
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
