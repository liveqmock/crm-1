/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerMarktingDept.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author pgj
 * @version 0.1 2014-4-9
 */
package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerMarktingDept.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author pgj
 * @version 0.1 2014-4-9
 */

public class CustomerMarktingDept extends BaseEntity{
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -5562948815944515395L;
	//归属部门
	private String belongDept;
	//营销权限部门
	private String marktingDept;
	//客户id
	private String custId;
	/**
	 *@user pgj
	 *2014-4-9下午5:17:56
	 * @return belongDept : return the property belongDept.
	 */
	public String getBelongDept() {
		return belongDept;
	}
	/**
	 * @param belongDept : set the property belongDept.
	 */
	public void setBelongDept(String belongDept) {
		this.belongDept = belongDept;
	}
	/**
	 *@user pgj
	 *2014-4-9下午5:17:56
	 * @return marktingDept : return the property marktingDept.
	 */
	public String getMarktingDept() {
		return marktingDept;
	}
	/**
	 * @param marktingDept : set the property marktingDept.
	 */
	public void setMarktingDept(String marktingDept) {
		this.marktingDept = marktingDept;
	}
	/**
	 *@user pgj
	 *2014-4-9下午5:19:51
	 * @return custId : return the property custId.
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId : set the property custId.
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
}
