package com.deppon.crm.module.customer.shared.domain.integral;

import java.util.List;

/**
 * 
 * <p>
 * <br />
 * </p>
 * @title IntegralConvertGiftCondition.java
 * @package com.deppon.crm.module.customer.shared.domain.integral 
 * @author bxj
 * @version 0.2 2012-5-3
 */
public class IntegralConvertGiftCondition {
	//客户编码
	private String memberNum;
	//联系人编码
	private String contactNum;
	//发送状态
	private String sendStatus;
	//部门数据权限
	private List<String> deptIds;
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
	 * Description:contactNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactNum() {
		return contactNum;
	}
	/**
	 * <p>
	 * Description:contactNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	/**
	 * <p>
	 * Description:sendStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getSendStatus() {
		return sendStatus;
	}
	/**
	 * <p>
	 * Description:sendStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
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
	
}
