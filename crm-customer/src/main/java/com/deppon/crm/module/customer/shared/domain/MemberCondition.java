package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;
import java.util.List;

/**
 * @作者：罗典
 * @时间：2012-3-30
 * @描述：会员查询实体
 * */
public class MemberCondition {
	//会员id
	private String memberId;
	// 会员所属部门ID
	private String deptId;
	// 会员客户编码
	private String custNumber;
	// 客户名称（企业或个人）
	private String custName;
	// 客户等级
	private String custGrad;
	// 联系人编码
	private String linkManNumber;
	// 联系人姓名
	private String linkManName;
	// 手机号码(联系人手机号)
	private String mobile;
	//固定电话(联系人固定电话)
	private String telePhone;
	//身份证号码
	private String idCard;
	//起始值
	private int start;
	//数据权限部门
	private List<String> deptIds;
	//长度    值为-1时不分页查询
	private int limit = -1;
	// 税务登记号
	private String taxregNumber;
	// 版本号
	private Integer versionNumber;
	//客户状态
	private String custStatus;
	//客户来源
	private String custSource;
	//是否大客户
	private String fisKeyCustomer;
	//客户性质（潜散客、固客）
	private String custGroup;
	//客户属性
	private String custNature;
	//客户类别（零担快递）
	private String custCategory;
	//创建时间
	private Date createDate;
	//行业
    private String tradeId;
    //二级行业
    private String secondTrade;
	
	
	/**
	 *@author chenaichun
	 * @date 2014年5月15日 上午9:46:43 
	 *@return the tradeId
	 */
	public String getTradeId() {
		return tradeId;
	}

	/**
	 *@author chenaichun
	 * @date 2014年5月15日 上午9:46:43 
	 * @param tradeId the tradeId to set
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	/**
	 *@author chenaichun
	 * @date 2014年5月15日 上午9:46:43 
	 *@return the secondTrade
	 */
	public String getSecondTrade() {
		return secondTrade;
	}

	/**
	 *@author chenaichun
	 * @date 2014年5月15日 上午9:46:43 
	 * @param secondTrade the secondTrade to set
	 */
	public void setSecondTrade(String secondTrade) {
		this.secondTrade = secondTrade;
	}

	/**
	 *@author chenaichun
	 * @date 2014年4月17日 下午5:05:27 
	 *@return the custCategory
	 */
	public String getCustCategory() {
		return custCategory;
	}

	/**
	 *@author chenaichun
	 * @date 2014年4月17日 下午5:05:27 
	 * @param custCategory the custCategory to set
	 */
	public void setCustCategory(String custCategory) {
		this.custCategory = custCategory;
	}

	/**
	 *@author chenaichun
	 * @date 2014年3月29日 下午5:13:45 
	 *@return the fisKeyCustomer
	 */
	public String getFisKeyCustomer() {
		return fisKeyCustomer;
	}

	/**
	 *@author chenaichun
	 * @date 2014年3月29日 下午5:13:45 
	 * @param fisKeyCustomer the fisKeyCustomer to set
	 */
	public void setFisKeyCustomer(String fisKeyCustomer) {
		this.fisKeyCustomer = fisKeyCustomer;
	}

	
	/**
	 *@author chenaichun
	 * @date 2014年3月29日 上午11:22:08 
	 *@return the custGroup
	 */
	public String getCustGroup() {
		return custGroup;
	}

	/**
	 *@author chenaichun
	 * @date 2014年3月29日 上午11:22:08 
	 * @param custGroup the custGroup to set
	 */
	public void setCustGroup(String custGroup) {
		this.custGroup = custGroup;
	}

	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}

	public String getCustStatus() {
		return custStatus;
	}
	
	/**
	 * <p>
	 * Description:memberId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * <p>
	 * Description:memberId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * <p>
	 * Description:custNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustNumber() {
		return custNumber;
	}

	/**
	 * <p>
	 * Description:custNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * <p>
	 * Description:custGrad<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustGrad() {
		return custGrad;
	}

	/**
	 * <p>
	 * Description:custGrad<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustGrad(String custGrad) {
		this.custGrad = custGrad;
	}

	/**
	 * <p>
	 * Description:linkManNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLinkManNumber() {
		return linkManNumber;
	}

	/**
	 * <p>
	 * Description:linkManNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLinkManNumber(String linkManNumber) {
		this.linkManNumber = linkManNumber;
	}

	/**
	 * <p>
	 * Description:linkManName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLinkManName() {
		return linkManName;
	}

	/**
	 * <p>
	 * Description:linkManName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	/**
	 * <p>
	 * Description:mobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * <p>
	 * Description:mobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * <p>
	 * Description:telePhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTelePhone() {
		return telePhone;
	}

	/**
	 * <p>
	 * Description:telePhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	/**
	 * <p>
	 * Description:idCard<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * <p>
	 * Description:idCard<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * <p>
	 * Description:start<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public int getStart() {
		return start;
	}

	/**
	 * <p>
	 * Description:start<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * <p>
	 * Description:deptIds<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<String> getDeptIds() {
		return deptIds;
	}

	/**
	 * <p>
	 * Description:deptIds<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}

	/**
	 * <p>
	 * Description:limit<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * <p>
	 * Description:limit<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * <p>
	 * Description:taxregNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTaxregNumber() {
		return taxregNumber;
	}

	/**
	 * <p>
	 * Description:taxregNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTaxregNumber(String taxregNumber) {
		this.taxregNumber = taxregNumber;
	}

	/**
	 * <p>
	 * Description:versionNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getVersionNumber() {
		return versionNumber;
	}

	/**
	 * <p>
	 * Description:versionNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((custGrad == null) ? 0 : custGrad.hashCode());
		result = prime * result
				+ ((custName == null) ? 0 : custName.hashCode());
		result = prime * result
				+ ((custNumber == null) ? 0 : custNumber.hashCode());
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result + ((deptIds == null) ? 0 : deptIds.hashCode());
		result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
		result = prime * result + limit;
		result = prime * result
				+ ((linkManName == null) ? 0 : linkManName.hashCode());
		result = prime * result
				+ ((linkManNumber == null) ? 0 : linkManNumber.hashCode());
		result = prime * result
				+ ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + start;
		result = prime * result
				+ ((taxregNumber == null) ? 0 : taxregNumber.hashCode());
		result = prime * result
				+ ((telePhone == null) ? 0 : telePhone.hashCode());
		result = prime * result
				+ ((versionNumber == null) ? 0 : versionNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		MemberCondition other = (MemberCondition) obj;
		if (custGrad == null) {
			if (other.custGrad != null){
				return false;
			}
		} else if (!custGrad.equals(other.custGrad)){
			return false;
		}
		if (custName == null) {
			if (other.custName != null) {
				return false;
			}
		} else if (!custName.equals(other.custName)){
			return false;
		}
		if (custNumber == null) {
			if (other.custNumber != null){
				return false;
			}
		} else if (!custNumber.equals(other.custNumber)){
			return false;
		}
		if (deptId == null) {
			if (other.deptId != null){
				return false;
			}
		} else if (!deptId.equals(other.deptId)){
			return false;
		}
		if (deptIds == null) {
			if (other.deptIds != null){
				return false;
			}
		} else if (!deptIds.equals(other.deptIds)){
			return false;
		}
		if (idCard == null) {
			if (other.idCard != null){
				return false;
			}
		} else if (!idCard.equals(other.idCard)){
			return false;
		}
		if (limit != other.limit){
			return false;
		}
		if (linkManName == null) {
			if (other.linkManName != null){
				return false;
			}
		} else if (!linkManName.equals(other.linkManName)){
			return false;
		}
		if (linkManNumber == null) {
			if (other.linkManNumber != null){
				return false;
			}
		} else if (!linkManNumber.equals(other.linkManNumber)){
			return false;
		}
		if (memberId == null) {
			if (other.memberId != null){
				return false;
			}
		} else if (!memberId.equals(other.memberId)){
			return false;
		}
		if (mobile == null) {
			if (other.mobile != null){
				return false;
			}
		} else if (!mobile.equals(other.mobile)){
			return false;
		}
		if (start != other.start){
			return false;
		}
		if (taxregNumber == null) {
			if (other.taxregNumber != null){
				return false;
			}
		} else if (!taxregNumber.equals(other.taxregNumber)){
			return false;
		}
		if (telePhone == null) {
			if (other.telePhone != null){
				return false;
			}
		} else if (!telePhone.equals(other.telePhone)){
			return false;
		}
		if (versionNumber == null) {
			if (other.versionNumber != null){
				return false;
			}
		} else if (!versionNumber.equals(other.versionNumber)){
			return false;
		}
		return true;
	}

	/**
	 *@user pgj
	 *2013-8-2下午2:21:00
	 * @return potenSource : return the property potenSource.
	 */
	public String getCustSource() {
		return custSource;
	}

	/**
	 * @param potenSource : set the property potenSource.
	 */
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}

	/**
	 *@user pgj
	 *2014-4-10上午8:51:34
	 * @return custNature : return the property custNature.
	 */
	public String getCustNature() {
		return custNature;
	}

	/**
	 * @param custNature : set the property custNature.
	 */
	public void setCustNature(String custNature) {
		this.custNature = custNature;
	}

	/**
	 *@user pgj
	 *2014-5-6下午3:26:14
	 * @return createDate : return the property createDate.
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate : set the property createDate.
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
