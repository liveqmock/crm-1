package com.deppon.crm.module.uums.shared.domain;
import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @description 接口传输参数子公司类
 * @author zzw
 * @version 0.1 2013-11-25
 */
public class CompanyInfo extends BaseUUEntity implements Serializable{
	private static final long serialVersionUID = 5882233558362824527L;
		//公司名称
	    private String companyName;
	    //公司�?��
	    private String shortName;
	    //公司标准编码
	    private String companyStandCode;
	    //是否建档
	    private String isHasAccount;
	    //联系�?
	    private String linkMan1;
	    //传真
	    private String fax1;
	    //电话
	    private String phone1;
	    // 邮编
	    private String zipCode;
	    //通讯地址
	    private String postAddress;
	    //营业地址
	    private String saleAddress;
	    //公司�?��
	    private String briefintro;
	    //�?��权份�?
	    private Double ownerShareRate;
	    //是否经营单位
	    private boolean isWorkingUnit;
	    //是否封存
	    private boolean isSeal;
	    //法人代表
	    private String legalPerson;
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public String getShortName() {
			return shortName;
		}
		public void setShortName(String shortName) {
			this.shortName = shortName;
		}
		public String getCompanyStandCode() {
			return companyStandCode;
		}
		public void setCompanyStandCode(String companyStandCode) {
			this.companyStandCode = companyStandCode;
		}
		public String getIsHasAccount() {
			return isHasAccount;
		}
		public void setIsHasAccount(String isHasAccount) {
			this.isHasAccount = isHasAccount;
		}
		public String getLinkMan1() {
			return linkMan1;
		}
		public void setLinkMan1(String linkMan1) {
			this.linkMan1 = linkMan1;
		}
		public String getFax1() {
			return fax1;
		}
		public void setFax1(String fax1) {
			this.fax1 = fax1;
		}
		public String getPhone1() {
			return phone1;
		}
		public void setPhone1(String phone1) {
			this.phone1 = phone1;
		}
		public String getZipCode() {
			return zipCode;
		}
		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}
		public String getPostAddress() {
			return postAddress;
		}
		public void setPostAddress(String postAddress) {
			this.postAddress = postAddress;
		}
		public String getSaleAddress() {
			return saleAddress;
		}
		public void setSaleAddress(String saleAddress) {
			this.saleAddress = saleAddress;
		}
		public String getBriefintro() {
			return briefintro;
		}
		public void setBriefintro(String briefintro) {
			this.briefintro = briefintro;
		}
		public Double getOwnerShareRate() {
			return ownerShareRate;
		}
		public void setOwnerShareRate(Double ownerShareRate) {
			this.ownerShareRate = ownerShareRate;
		}
		public boolean isWorkingUnit() {
			return isWorkingUnit;
		}
		public void setWorkingUnit(boolean isWorkingUnit) {
			this.isWorkingUnit = isWorkingUnit;
		}
		public boolean isSeal() {
			return isSeal;
		}
		public void setSeal(boolean isSeal) {
			this.isSeal = isSeal;
		}
		public String getLegalPerson() {
			return legalPerson;
		}
		public void setLegalPerson(String legalPerson) {
			this.legalPerson = legalPerson;
		}
		public String getChangeType() {
			return changeType;
		}
		public void setChangeType(String changeType) {
			this.changeType = changeType;
		}
		public Date getChangeDate() {
			return changeDate;
		}
		public void setChangeDate(Date changeDate) {
			this.changeDate = changeDate;
		}
		
	    
}
