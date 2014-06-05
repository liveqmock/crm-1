package com.deppon.crm.module.marketing.server.dao;
/**   
 * <p>
 * Description:潜散客户汇总信息刷新Dao接口<br />
 * </p>
 * @title IMaterializedViewPS.java
 * @package com.deppon.crm.module.marketing.server.dao
 * @author ZHANGZW
 * @version 2013-04-26
 */
public interface IMaterializedViewPS {
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
}
