/**   
 * @title ContractHandleType.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @description what to do
 * @author 潘光均
 * @update 2012-6-28 下午4:10:29
 * @version V1.0   
 */
package com.deppon.crm.module.customer.shared.domain;

/**
 * @description 合同操作类型  
 * @author 潘光均
 * @version 0.1 2012-6-28
 *@date 2012-6-28
 */

public enum ContractHandleType {
	//新增
	INSERT("insert"),
	//绑定
	BINDING("bingding"),
	//解绑
	UNBINDING("unbinding"),
	//作废
	OBSOLETE("obsolete"),
	//变更归属部门
	CHANGEBELONGDEPT("changeBelongDept"),
	//改签
	CHANGESIGN("changeSign"),
	//修改月发月送的运费折扣
	MODIFYMONTHSENDRATE("modifyMonthMendRate"),
	//修改
	UPDATE("update"),
	//修改月结天数
	UPDATEMONTHENDDAY("updateMonthEndDay");
	//操作类型
	private String handleType;

	/**
	 * constructer
	 * @param handleType
	 */
	private  ContractHandleType(String handleType) {
		this.handleType = handleType;
	}

	/**
	 * <p>
	 * Description:handleType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getHandleType() {
		return handleType;
	}

	/**
	 * <p>
	 * Description:handleType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}
}
