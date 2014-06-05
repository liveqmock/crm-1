package com.deppon.crm.module.duty.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.duty.server.dao.IFeedAttachDao;
import com.deppon.crm.module.duty.server.util.DutyConstants;
import com.deppon.crm.module.duty.shared.domain.FeedAttach;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class FeedAttachDao extends iBatis3DaoImpl implements IFeedAttachDao {
	
	private static final String NAMESPACE_FEEDATTACH = "com.deppon.crm.module.duty.shared.domain.FeedAttach.";

	//根据条件查询对象
	private static final String FEEDATTACH_SEARCHFEEDATTACH = "searchFeedAttach";
	//查询单个对象根据ID
	private static final String FEEDATTACH_SEARCHFEEDATTACH_FID = "selectFeedAttachByPrimaryKey";
	//修改对象
	private static final String FEEDATTACH_UPDATEFEEDATTACH = "updateFeedAttach";
	//插入通知对象
	private static final String FEEDATTACH_INSERT = "insertFeedAttach";
	//批量删除方案信息
	private static final String FEEDATTACH_DELETE = "deleteFeedAttachs";
	//批量删除根据责任ID 
	private static final String FEEDATTACH_DELETE_DUTYID="deleteFeedAttachsByFeedId";
	//查询统计员所在部门的信息NAMESPACE
	private static final String STATISCIANSDEPT_FEEDATTACH = "com.deppon.crm.module.organization.shared.domain.StatisticiansDeptMap.";
	//查询统计员所在部门的信息
	private static final String SEARCH_STATISTICIANSDEPT="searchStatisticiansDept";
	/**
	 * <p>
	 * Description:根据条件查询附件信息<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2013-3-6
	 * @param feedAttach
	 * @return List<FeedAttach>
	 */
	@Override
	public List<FeedAttach> searchFeedAttach(FeedAttach feedAttach) {
		return (List<FeedAttach>)this.getSqlSession().selectList(NAMESPACE_FEEDATTACH+FEEDATTACH_SEARCHFEEDATTACH,feedAttach);
	}
	/**
	 * <p>
	 * Description:根据ID查询附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fid
	 * @return List
	 */
	@Override
	public FeedAttach selectFeedAttachByPrimaryKey(String fid) {
		Map map = new HashMap();
		map.put("id", fid);
		return (FeedAttach)this.getSqlSession().selectOne(NAMESPACE_FEEDATTACH+FEEDATTACH_SEARCHFEEDATTACH_FID,map);
	}
	/**
	 * <p>
	 * Description:修改附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param feedAttach
	 * @return void
	 */
	@Override
	public void updateFeedAttach(FeedAttach feedAttach) {
		this.getSqlSession().update(NAMESPACE_FEEDATTACH+FEEDATTACH_UPDATEFEEDATTACH,feedAttach);
	}
	/**
	 * <p>
	 * Description:批量删除附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fids
	 * @return void
	 */
	@Override
	public void deleteFeedAttachs(List<String> fids) {
		Map map = new HashMap();
		map.put("ids", fids);
		this.getSqlSession().delete(NAMESPACE_FEEDATTACH+FEEDATTACH_DELETE, map);
	}
	/**
	 * <p>
	 * Description:根据责任ID删除附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param feedId
	 * @return void
	 */
	@Override
	public void deleteFeedAttachsByFeedId(String feedId) {
		Map map = new HashMap();
		map.put("feedId", feedId);
		this.getSqlSession().delete(NAMESPACE_FEEDATTACH+FEEDATTACH_DELETE_DUTYID, map);
	}
	/**
	 * <p>
	 * Description:插入附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param feedAttach
	 * @return void
	 */
	@Override
	public void insertFeedAttach(FeedAttach feedAttach) {
		this.getSqlSession().insert(NAMESPACE_FEEDATTACH+FEEDATTACH_INSERT, feedAttach);
	}
	/**
	 * <p>
	 * Description:查询统计员部门<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-28
	 * @param feedAttach
	 * @return void
	 */
	@Override
	public List<Department> searchStatisticiansDept() {
		Map map = new HashMap();
		map.put("roleId", DutyConstants.STATISTICS_ROLE_ID);
		return  (List<Department>)this.getSqlSession().selectList(STATISCIANSDEPT_FEEDATTACH+SEARCH_STATISTICIANSDEPT,map);
	}

}
