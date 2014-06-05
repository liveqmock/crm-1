package com.deppon.crm.module.marketing.server.dao.impl;

import com.deppon.crm.module.marketing.server.dao.IMaterializedView;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**   
 * <p>
 * materializedViewImpl:固定客户物化视图刷新接口实现类<br />
 * </p>
 * @title materializedViewImpl.java
 * @package com.deppon.crm.module.marketing.server.dao.impl
 * @author ZHANGZw
 * @version 2013-04-26
 */
public class MaterializedViewImpl extends iBatis3DaoImpl implements IMaterializedView {
	//信息汇总表，每天进行更新
	private static final String UPDATESQL="CallProductorUpdateCust";
	private static final String STATEMENT="com.deppon.crm.module.marketing.shared.domain.";
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
	public int reFreshMVCuststomerDaily() {
		this.getSqlSession().selectOne(STATEMENT+UPDATESQL);
		return 0;
	}
}
