package com.deppon.crm.module.marketing.server.dao;
/**   
 * <p>
 * Description:固定客户汇总信息刷新DAO接口<br />
 * </p>
 * @title ImaterializedView.java
 * @package com.deppon.crm.module.marketing.server.dao
 * @author ZHANGZW
 * @version 2013-04-26
 */
public interface IMaterializedView {
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
