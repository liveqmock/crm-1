package com.deppon.crm.module.client.esb.domain;

import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * �ͻ���������
 * 
 * <p>
 * ScatterCustBankInfo complex type�� Java �ࡣ
 * 
 * <p>
 * ����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ScatterCustBankInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fsubbankname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fbankaccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fcountname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fbankcityid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fbankprovinceid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fbankid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flinkmanmobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="frelation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fsubbanknameid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fdesciption" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fisdefaultaccount" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScatterCustBankInfo", propOrder = {
    "fid",
    "accountType",
    "fsubbankname",
    "fbankaccount",
    "status",
    "fcountname",
    "bankcityCode",
    "bankCityName",
    "bankProvinceCode",
    "bankProvinceName",
    "bankCode",
    "bankName",
    "flinkmanmobile",
    "frelation",
    "subbankCode",
    "subbankName",
    "fdesciption",
    "fisdefaultaccount",
    "lastModifyTime"
})
/**
 * @作者：罗典
 * @描述：散客银行账号信息
 * @时间：2012-11-7
 * */
public class ScatterCustBankInfo {
	// 账户ID
    @XmlElement(required = true)
	protected BigInteger fid;
	// 其他支行名称（手动输入）
	@XmlElement(required = true)
	protected String fsubbankname;
	// 开户帐号
	@XmlElement(required = true)
	protected String fbankaccount;
	// 状态 正常：0；  审批中：1  ；无效 ：2；
    @XmlElement(required = true)
    protected String status;
	// 开户人姓名
	@XmlElement(required = true)
	protected String fcountname;
	// 开户行所在城市编码
	@XmlElement(required = true)
	protected String bankcityCode;
	// 开户行所在城市名称
	@XmlElement(required = true)
	protected String bankCityName;
	// 开户行省份编码
	@XmlElement(required = true)
	protected String bankProvinceCode;
	// 开户行省份名称
	@XmlElement(required = true)
	protected String bankProvinceName;
	// 开户行编码
	@XmlElement(required = true)
	protected String bankCode;
	// 开户行名称
	@XmlElement(required = true)
	protected String bankName;
	// 手机号码
	@XmlElement(required = true)
	protected String flinkmanmobile;
	// 帐号与客户关系
	@XmlElement(required = true)
	protected String frelation;
	// 支行编码
	@XmlElement(required = true)
	protected String subbankCode;
	// 支行名称
	@XmlElement(required = true)
	protected String subbankName;
	// 备注
	@XmlElement(required = true)
	protected String fdesciption;
	// 是否默认帐号
	@XmlElement(required = false)
	protected boolean fisdefaultaccount;
	//账号性质
	@XmlElement(required = true)
	protected String accountType;
	// 最近修改时间
	@XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date lastModifyTime;
	public BigInteger getFid() {
		return fid;
	}
	public void setFid(BigInteger fid) {
		this.fid = fid;
	}
	public String getFsubbankname() {
		return fsubbankname;
	}
	public void setFsubbankname(String fsubbankname) {
		this.fsubbankname = fsubbankname;
	}
	public String getFbankaccount() {
		return fbankaccount;
	}
	public void setFbankaccount(String fbankaccount) {
		this.fbankaccount = fbankaccount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFcountname() {
		return fcountname;
	}
	public void setFcountname(String fcountname) {
		this.fcountname = fcountname;
	}
	public String getBankcityCode() {
		return bankcityCode;
	}
	public void setBankcityCode(String bankcityCode) {
		this.bankcityCode = bankcityCode;
	}
	public String getBankCityName() {
		return bankCityName;
	}
	public void setBankCityName(String bankCityName) {
		this.bankCityName = bankCityName;
	}
	public String getBankProvinceCode() {
		return bankProvinceCode;
	}
	public void setBankProvinceCode(String bankProvinceCode) {
		this.bankProvinceCode = bankProvinceCode;
	}
	public String getBankProvinceName() {
		return bankProvinceName;
	}
	public void setBankProvinceName(String bankProvinceName) {
		this.bankProvinceName = bankProvinceName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getFlinkmanmobile() {
		return flinkmanmobile;
	}
	public void setFlinkmanmobile(String flinkmanmobile) {
		this.flinkmanmobile = flinkmanmobile;
	}
	public String getFrelation() {
		return frelation;
	}
	public void setFrelation(String frelation) {
		this.frelation = frelation;
	}
	public String getSubbankCode() {
		return subbankCode;
	}
	public void setSubbankCode(String subbankCode) {
		this.subbankCode = subbankCode;
	}
	public String getSubbankName() {
		return subbankName;
	}
	public void setSubbankName(String subbankName) {
		this.subbankName = subbankName;
	}
	public String getFdesciption() {
		return fdesciption;
	}
	public void setFdesciption(String fdesciption) {
		this.fdesciption = fdesciption;
	}
	public boolean isFisdefaultaccount() {
		return fisdefaultaccount;
	}
	public void setFisdefaultaccount(boolean fisdefaultaccount) {
		this.fisdefaultaccount = fisdefaultaccount;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	

	
}
