package com.deppon.crm.module.duty.server.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.duty.server.dao.IInformUserDao;
import com.deppon.crm.module.duty.shared.domain.InformUser;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class InformUserDao extends iBatis3DaoImpl implements IInformUserDao {
	
	private static final String NAMESPACE_INFORMUSER = "com.deppon.crm.module.duty.shared.domain.InformUser.";

	//根据条件查询对象
	private static final String INFORMUSER_SEARCHINFORMUSER = "searchInformUser";
	//查询单个对象根据ID
	private static final String INFORMUSER_SEARCHINFORMUSER_FID = "selectInformUserByPrimaryKey";
	//修改对象
	private static final String INFORMUSER_UPDATEINFORMUSER = "updateInformUser";
	//插入通知对象
	private static final String INFORMUSER_INSERT = "insertInformUser";
	//批量删除方案信息
	private static final String INFORMUSER_DELETE = "deleteInformUsers";
	//批量删除根据责任ID 
	private static final String INFORMUSER_DELETE_DUTYID="deleteInformUsersByDutyId";
	/**
	 * <p>
	 * Description:根据条件查询通知对象<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2013-3-6
	 * @param informUser
	 * @return List<InformUser>
	 */
	@Override
	public List<InformUser> searchInformUser(InformUser informUser) {
		return (List<InformUser>)this.getSqlSession().selectList(NAMESPACE_INFORMUSER+INFORMUSER_SEARCHINFORMUSER,informUser);
	}
	/**
	 * <p>
	 * Description:根据ID查询通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fid
	 * @return List
	 */
	@Override
	public InformUser selectInformUserByPrimaryKey(BigDecimal fid) {
		Map map = new HashMap();
		map.put("id", fid);
		return (InformUser)this.getSqlSession().selectOne(NAMESPACE_INFORMUSER+INFORMUSER_SEARCHINFORMUSER_FID,map);
	}
	/**
	 * <p>
	 * Description:修改通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param informUser
	 * @return void
	 */
	@Override
	public void updateInformUser(InformUser informUser) {
		this.getSqlSession().update(NAMESPACE_INFORMUSER+INFORMUSER_UPDATEINFORMUSER,informUser);
	}
	/**
	 * <p>
	 * Description:批量删除通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fids
	 * @return void
	 */
	@Override
	public void deleteInformUsers(List<String> fids) {
		Map map = new HashMap();
		map.put("ids", fids);
		this.getSqlSession().delete(NAMESPACE_INFORMUSER+INFORMUSER_DELETE, map);
	}
	/**
	 * <p>
	 * Description:根据责任ID删除通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param dutyId
	 * @return void
	 */
	@Override
	public void deleteInformUsersByDutyId(String dutyId) {
		Map map = new HashMap();
		map.put("dutyId", dutyId);
		this.getSqlSession().delete(NAMESPACE_INFORMUSER+INFORMUSER_DELETE_DUTYID, map);
	}
	/**
	 * <p>
	 * Description:插入通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param informUser
	 * @return void
	 */
	@Override
	public void insertInformUser(InformUser informUser) {
		this.getSqlSession().insert(NAMESPACE_INFORMUSER+INFORMUSER_INSERT, informUser);
	}

}
