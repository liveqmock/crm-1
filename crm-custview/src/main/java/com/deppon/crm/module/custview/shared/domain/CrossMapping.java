/**   
 * <p>
 * Description:
 * </p>
 * @title CrossMapping.java
 * @package com.deppon.crm.module.custview.shared.domain 
 * @author 106143
 * @version 0.1 2014-4-23下午2:46:54
 */
package com.deppon.crm.module.custview.shared.domain;

import java.util.Date;

/**   
 * <p>
 * Description:交叉映射实体
 * </p>
 * @title CrossMapping.java
 * @package com.deppon.crm.module.custview.shared.domain 
 * @author 106143
 * @version 0.1 2014-4-23下午2:46:54
 */

public class CrossMapping {
	//映射时间
	private Date createTime;
	//来源渠道
	private String orderSource;
	//外部系统ID
	private String userName;
	/**
	*@return  createTime;
	*/
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime : set the property createTime.
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	*@return  orderSource;
	*/
	public String getOrderSource() {
		return orderSource;
	}
	/**
	 * @param orderSource : set the property orderSource.
	 */
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	/**
	*@return  userName;
	*/
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName : set the property userName.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
