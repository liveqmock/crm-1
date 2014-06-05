/**
 * @Title: DevelopmentLog.java
 * @Package com.deppon.crm.module.customer.shared.domain
 * @Description:{todo}
 * @author: 唐亮
 * @date: 2014-5-7 下午5:28:23
 * @version V1.0
 */
package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * <p>
 * TODO<br />
 * </p>
 * @title DevelopmentLog.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @author 唐亮
 * @version 0.1 2014-5-7
 */
public class DevelopmentLog extends BaseEntity{

	/**
	* @Fields serialVersionUID 
	*/
	private static final long serialVersionUID = 1L;

	//当前阶段
	private String currentStage;
	
	//下一阶段
	private String nextStage;
	
	//客户ID
	private String custId;

	/**
	 * 
	 * Description:currentStage<br />
	 * @author 唐亮
	 * @version 0.1 2014-5-7
	 */
	public String getCurrentStage() {
		return currentStage;
	}

	/**
	 * 
	 * Description:currentStage<br />
	 * @author 唐亮
	 * @version 0.1 2014-5-7
	 */
	public void setCurrentStage(String currentStage) {
		this.currentStage = currentStage;
	}

	/**
	 * 
	 * Description:nextStage<br />
	 * @author 唐亮
	 * @version 0.1 2014-5-7
	 */
	public String getNextStage() {
		return nextStage;
	}

	/**
	 * 
	 * Description:nextStage<br />
	 * @author 唐亮
	 * @version 0.1 2014-5-7
	 */
	public void setNextStage(String nextStage) {
		this.nextStage = nextStage;
	}

	/**
	 * 
	 * Description:custId<br />
	 * @author 唐亮
	 * @version 0.1 2014-5-7
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * 
	 * Description:custId<br />
	 * @author 唐亮
	 * @version 0.1 2014-5-7
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
}
