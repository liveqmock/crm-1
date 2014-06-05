package com.deppon.crm.module.duty.server.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.duty.server.dao.IDutyDeptDao;
import com.deppon.crm.module.duty.server.dao.IDutyResultDao;
import com.deppon.crm.module.duty.server.service.IDutyResultService;
import com.deppon.crm.module.duty.server.util.DutyConstants;
import com.deppon.crm.module.duty.shared.domain.DutyResult;
import com.deppon.crm.module.duty.shared.domain.SearchDutyResultVO;
import com.deppon.crm.module.organization.shared.domain.Department;
@Transactional
public class DutyResultServiceImpl implements IDutyResultService {
	
    private IDutyResultDao dutyResultDao;
    private IDutyDeptDao deptDao;
	/**
	 * @param dutyResultDao the dutyResultDao to set
	 */
	public void setDutyResultDao(IDutyResultDao dutyResultDao) {
		this.dutyResultDao = dutyResultDao;
	}
	/**
	 * <p>
	 * Description:根据条件查询通知对象<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2013-3-6
	 * @param dutyResult
	 * @return List<DutyResult>
	 */
	@Override
	public List<DutyResult> searchDutyResult(DutyResult dutyResult) {
		return dutyResultDao.searchDutyResult(dutyResult);
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
	public DutyResult selectDutyResultByPrimaryKey(String fid) {
		return dutyResultDao.selectDutyResultByPrimaryKey(fid);
	}
	/**
	 * <p>
	 * Description:修改通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param dutyResult
	 * @return void
	 */
	@Override
	public void updateDutyResult(DutyResult dutyResult) {
		dutyResultDao.updateDutyResult(dutyResult);
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
	public void deleteDutyResults(List<String> fids) {
		dutyResultDao.deleteDutyResults(fids);
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
	public void deleteDutyResultsByDutyId(String dutyId) {
		dutyResultDao.deleteDutyResultsByDutyId(dutyId);
	}
	/**
	 * <p>
	 * Description:插入通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param dutyResult
	 * @return void
	 */
	@Override
	public void insertDutyResult(DutyResult dutyResult) {
		dutyResultDao.insertDutyResult(dutyResult);
	}
	
	
	/**
	 * <p>
	 * Description:批量删除通知对象（逻辑）<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fids
	 * @return void
	 */
	@Override
	public void deleteDutyResultsStatus(List<String> fids) {
		dutyResultDao.deleteDutyResultsStatus(fids);
		
	}
	/**
	 * <p>
	 * Description:根据责任ID删除通知对象(逻辑)<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param dutyId
	 * @return void
	 */
	@Override
	public void deleteDutyResultsByDutyIdStatus(String dutyId) {
		dutyResultDao.deleteDutyResultsByDutyIdStatus(dutyId);
		
	}
	/**
	 * <p>
	 * Description:修改划分结果状态<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-20
	 * @param dutyId
	 * @return void
	 * where条件是dutyId(一个工单下的责任划分批量)
	 */
	@Override
	public void updateDutySatatus(DutyResult dutyResult) {
		dutyResultDao.updateDutySatatus(dutyResult);
	}
	/**
	 * 
	 * <p>
	 * Description:责任反馈主查询（当前登录用户被划分的责任）<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-23
	 */

	@Override
	public List<SearchDutyResultVO> searchDutyFeedBackMain(User user,int start,int limit) {
		return dutyResultDao.searchDutyFeedBackMain(user,start,limit);
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
		return dutyResultDao.searchDutyFeedBackMainCount(user);
	}
	/**
	 * @param deptDao the deptDao to set
	 */
	public void setDeptDao(IDutyDeptDao deptDao) {
		this.deptDao = deptDao;
	}

}
