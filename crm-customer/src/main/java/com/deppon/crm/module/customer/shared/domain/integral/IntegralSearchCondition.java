/**   
 * @title IngegralSearchCondition.java
 * @package com.deppon.crm.module.customer.shared.domain.integral
 * @description what to do
 * @author 潘光均
 * @update 2012-4-25 下午7:09:20
 * @version V1.0   
 */
package com.deppon.crm.module.customer.shared.domain.integral;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description 积分查询条件  
 * @author 潘光均
 * @version 0.1 2012-4-25
 *@date 2012-4-25
 */

public class IntegralSearchCondition{
	//会员积分id
	private String memberId;
	//所属部门id
	private String deptId;
	//数据权限部门id s
	private List<String> deptIds = new ArrayList<String>();
	//联系人编码
	private String contactId;
	//会员名称
	private String memberName;
	//联系人名称
	private String contactName;
	//会员编码
	private String memberNum;
	//运单开始日期
	private Date waybillStartDate;
	//运单结束日期
	private Date waybillEndDate;
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
	 * Description:contactId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactId() {
		return contactId;
	}
	/**
	 * <p>
	 * Description:contactId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	/**
	 * <p>
	 * Description:memberName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMemberName() {
		return memberName;
	}
	/**
	 * <p>
	 * Description:memberName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * <p>
	 * Description:memberNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMemberNum() {
		return memberNum;
	}
	/**
	 * <p>
	 * Description:memberNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}
	/**
	 * <p>
	 * Description:waybillStartDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getWaybillStartDate() {
		return waybillStartDate;
	}
	/**
	 * <p>
	 * Description:waybillStartDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWaybillStartDate(Date waybillStartDate) {
		this.waybillStartDate = waybillStartDate;
	}
	/**
	 * <p>
	 * Description:waybillEndDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getWaybillEndDate() {
		return waybillEndDate;
	}
	/**
	 * <p>
	 * Description:waybillEndDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWaybillEndDate(Date waybillEndDate) {
		this.waybillEndDate = waybillEndDate;
	}
	
}
