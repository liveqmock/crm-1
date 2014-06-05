package com.deppon.crm.module.marketing.server.dao.impl;

import com.deppon.crm.module.marketing.server.dao.IMaterializedViewPS;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**   
* <p>
* Description:潜散客户汇总信息刷新Dao接口实现类<br />
* </p>
* @title MaterializedViewPSImpl.java
* @package com.deppon.crm.module.marketing.server.dao.impl
* @author ZHANGZW
* @version 2013-04-26
*/
public class MaterializedViewPSImpl extends iBatis3DaoImpl implements IMaterializedViewPS{
	//存储过程所属路径
	private static final String PROCEDURE_UPDATPSDEALDATEBYDAY="UpdatePsCustomer";
	private static final String STATEMENT="com.deppon.crm.module.marketing.shared.domain.";
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
	public int reFreshMVPSCustomerDaily() {
		//更新散客中间表数据
		getSqlSession().selectOne(STATEMENT+PROCEDURE_UPDATPSDEALDATEBYDAY);
		return 0;
	}
}
