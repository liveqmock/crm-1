package com.deppon.crm.module.duty.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.duty.server.dao.IDutyResultDao;
import com.deppon.crm.module.duty.shared.domain.DutyResult;
import com.deppon.crm.module.duty.shared.domain.SearchDutyResultVO;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class DutyResultDao extends iBatis3DaoImpl implements IDutyResultDao {
	
	private static final String NAMESPACE_DUTYRESULT = "com.deppon.crm.module.duty.shared.domain.DutyResult.";

	//根据条件查询对象
	private static final String DUTYRESULT_SEARCHDUTYRESULT = "searchDutyResult";
	//查询单个对象根据ID
	private static final String DUTYRESULT_SEARCHDUTYRESULT_FID = "selectDutyResultByPrimaryKey";
	//修改对象
	private static final String DUTYRESULT_UPDATEDUTYRESULT = "updateDutyResult";
	//插入通知对象
	private static final String DUTYRESULT_INSERT = "insertDutyResult";
	//批量删除方案信息
	private static final String DUTYRESULT_DELETE = "deleteDutyResults";
	//批量删除根据责任ID 
	private static final String DUTYRESULT_DELETE_DUTYID="deleteDutyResultsByDutyId";
	//批量删除方案信息
	private static final String DUTYRESULT_DELETE_STATUS = "deleteDutyResultsStatus";
	//批量删除根据责任ID 
	private static final String DUTYRESULT_DELETE_DUTYID_STATUS="deleteDutyResultsByDutyIdStatus";
    //修改责任划分状态DUTYSTATUS
	private static final String UPDATEDUTYSTATUS = "updateDutySatatus";
	//责任反馈主查询
	private static final String DUTYRESULT_FEEDBACKMAIN= "searchDutyFeedBackMain";
	//责任反馈主查询count
	private static final String DUTYRESULT_FEEDBACKMAINCOUNT= "searchDutyFeedBackMainCount";
	
	/**
	 * <p>
	 * Description:根据条件查询划分结果<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2013-3-6
	 * @param dutyResult
	 * @return List<DutyResult>
	 */
	@Override
	public List<DutyResult> searchDutyResult(DutyResult dutyResult) {
		return (List<DutyResult>)this.getSqlSession().selectList(NAMESPACE_DUTYRESULT+DUTYRESULT_SEARCHDUTYRESULT,dutyResult);
	}
	/**
	 * <p>
	 * Description:根据ID查询划分结果<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fid
	 * @return List
	 */
	@Override
	public DutyResult selectDutyResultByPrimaryKey(String fid) {
		Map map = new HashMap();
		map.put("id", fid);
		return (DutyResult)this.getSqlSession().selectOne(NAMESPACE_DUTYRESULT+DUTYRESULT_SEARCHDUTYRESULT_FID,map);
	}
	/**
	 * <p>
	 * Description:修改划分结果<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param dutyResult
	 * @return void
	 */
	@Override
	public void updateDutyResult(DutyResult dutyResult) {
		this.getSqlSession().update(NAMESPACE_DUTYRESULT+DUTYRESULT_UPDATEDUTYRESULT,dutyResult);
	}
	/**
	 * <p>
	 * Description:批量删除划分结果<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fids
	 * @return void
	 */
	@Override
	public void deleteDutyResults(List<String> fids) {
		Map map = new HashMap();
		map.put("ids", fids);
		this.getSqlSession().delete(NAMESPACE_DUTYRESULT+DUTYRESULT_DELETE, map);
	}
	/**
	 * <p>
	 * Description:根据责任ID删除划分结果<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param dutyId
	 * @return void
	 */
	@Override
	public void deleteDutyResultsByDutyId(String dutyId) {
		Map map = new HashMap();
		map.put("dutyId", dutyId);
		this.getSqlSession().delete(NAMESPACE_DUTYRESULT+DUTYRESULT_DELETE_DUTYID, map);
	}
	/**
	 * <p>
	 * Description:插入划分结果<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param dutyResult
	 * @return void
	 */
	@Override
	public void insertDutyResult(DutyResult dutyResult) {
		this.getSqlSession().insert(NAMESPACE_DUTYRESULT+DUTYRESULT_INSERT, dutyResult);
	}
	/**
	 * <p>
	 * Description:批量删除划分结果（逻辑）<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fids
	 * @return void
	 */
	@Override
	public void deleteDutyResultsStatus(List<String> fids) {
		Map map = new HashMap();
		map.put("ids", fids);
		this.getSqlSession().delete(NAMESPACE_DUTYRESULT+DUTYRESULT_DELETE_STATUS, map);
	}
	/**
	 * <p>
	 * Description:根据责任ID删除划分结果(逻辑)<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param dutyId
	 * @return void
	 */
	@Override
	public void deleteDutyResultsByDutyIdStatus(String dutyId) {
		Map map = new HashMap();
		map.put("dutyId", dutyId);
		this.getSqlSession().delete(NAMESPACE_DUTYRESULT+DUTYRESULT_DELETE_DUTYID_STATUS, map);
	}
	/**
	 * <p>
	 * Description:插入划分结果<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param dutyResult
	 * @return void
	 */
	@Override
	public void updateDutySatatus(DutyResult dutyResult) {
		this.getSqlSession().update(NAMESPACE_DUTYRESULT+UPDATEDUTYSTATUS,dutyResult);
	}

	/**
	 * 
	 * <p>
	 * Description:责任反馈主查询（当前登录用户被划分的责任）<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-23
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SearchDutyResultVO> searchDutyFeedBackMain(User user,int start,int limit) {
		RowBounds bounds = new RowBounds(start,limit);
		Map map = new HashMap();
		map.put("empId", user.getEmpCode().getId());
		map.put("deptId", user.getEmpCode().getDeptId().getId());
		return (List<SearchDutyResultVO>)this.getSqlSession().selectList(NAMESPACE_DUTYRESULT+DUTYRESULT_FEEDBACKMAIN,map,bounds);
	}
	/**
	 * 
	 * <p>
	 * Description:责任反馈主查询（当前登录用户被划分的责任）个数<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-23
	 */
	@Override
	public Integer searchDutyFeedBackMainCount(User user) {
		Map map = new HashMap();
		map.put("empId", user.getEmpCode().getId());
		map.put("deptId", user.getEmpCode().getDeptId().getId());
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_DUTYRESULT+DUTYRESULT_FEEDBACKMAINCOUNT,map);
	}
}
