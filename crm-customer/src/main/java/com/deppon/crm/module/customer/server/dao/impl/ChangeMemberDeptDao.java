package com.deppon.crm.module.customer.server.dao.impl;

import com.deppon.crm.module.customer.server.dao.IChangeMemberDeptDao;
import com.deppon.crm.module.customer.shared.domain.ChangeMemberDept;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 * 
 * <p>
 * Description:ChangeMemberDeptDao<br />
 * </p>
 * @title ChangeMemberDeptDao.java
 * @package com.deppon.crm.module.customer.server.dao.impl 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class ChangeMemberDeptDao extends iBatis3DaoImpl implements IChangeMemberDeptDao{
	
	//命名空间
	private final static String NAME_SPACE = "com.deppon.crm.module.customer.shared.domain.ChangeMemberDept.";
	
	/**
	 * 
	 * <p>
	 * Description:获得会员归属变更<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public ChangeMemberDept getChangeMemberDeptByWorkFlowId(String workFLowId) {
		//getChangeMemberDeptByWorkFlowId
		return (ChangeMemberDept) this.getSqlSession().selectOne(NAME_SPACE+"getChangeMemberDeptByWorkFlowId",workFLowId);
	}
	
	/**
	 * 
	 * <p>
	 * Description:保存会员归属部门变更信息<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void insertChangeMemberDept(ChangeMemberDept changeMemberDept) {
		//保存会员归属部门变更信息
		this.getSqlSession().insert(NAME_SPACE+"insertChangeMemberDept",changeMemberDept);
	}
	
}
