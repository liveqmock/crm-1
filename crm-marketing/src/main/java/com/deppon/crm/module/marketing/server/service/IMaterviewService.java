package com.deppon.crm.module.marketing.server.service;
/**   
 * <p>
 * Description:客户数据的SERVICE更新接口<br />
 * </p>
 * @title IMaterviewService.java
 * @package com.deppon.crm.module.marketing.server.service 
 * @author ZHANGZW
 * @version 0.1 2013-05-26
 */
public interface IMaterviewService {
	/**
	 * 
	 * <p>
	 * 潜散客户信息需要每天更新一次视图T_CRM_PSVISUALMARKET<br/>
	 * </p>
	 * 
	 * @author ZHANGZW
	 * @version 0.1 2013-04-26
	 * @param endDate 
	 * @param beginDate 
	 * @return int
	 */
	public int reFreshMVPSCustomerDaily();
	/**
	 * 
	 * <p>
	 * 固定客户信息需要每天更新一次视图T_CRM_CUSTVISUALMARKET<br/>
	 * </p>
	 * 
	 * @author ZHANGZW
	 * @version 0.1 2013-04-26
	 * @param endDate 
	 * @param beginDate 
	 * @return int
	 */
	public int reFreshMVCuststomerDaily();
}
