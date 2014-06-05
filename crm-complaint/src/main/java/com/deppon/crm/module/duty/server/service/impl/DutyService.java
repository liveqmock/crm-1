package com.deppon.crm.module.duty.server.service.impl;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.duty.server.dao.IDutyDao;
import com.deppon.crm.module.duty.server.service.IDutyService;
import com.deppon.crm.module.duty.shared.domain.Duty;
import com.deppon.crm.module.duty.shared.domain.DutyDealProcess;
import com.deppon.crm.module.duty.shared.domain.DutyQueryResult;
import com.deppon.crm.module.duty.shared.domain.DutyResult;
import com.deppon.crm.module.duty.shared.domain.QueryDutyCondition;
import com.deppon.crm.module.duty.shared.domain.SearchDutyCondition;

/**
 * 
 * <p>
 * Description::工单责任  Service 实现<br />
 * </p>
 * @title DutyService.java
 * @package com.deppon.crm.module.duty.server.service.impl 
 * @author  侯斌飞
 * @version 0.1 2013-2-26
 */
@Transactional
public class DutyService implements IDutyService {
	private Logger log = Logger.getLogger(DutyService.class);
	//责任 Dao
	private IDutyDao dutyDao;
	/**
	 * @param dutyDao : set the property dutyDao.
	 */
	public void setDutyDao(IDutyDao dutyDao) {
		this.dutyDao = dutyDao;
	}
	/**
	 * 
	 * <p>
	 * Description:工单责任-查询<br />
	 * </p>
	 * @title queryDutyList.java
	 * @author 钟琼
	 * @version 0.1 2013-2-26
	 */
	@Override
	public List<DutyQueryResult> queryDutyList(QueryDutyCondition queryDutyCondition,int start,int limit) {
		return dutyDao.queryDutyList(queryDutyCondition,start,limit);
		
	}
	/**
	 * 
	 * <p>
	 * Description:工单责任-查询条数<br />
	 * </p>
	 * @title queryDutyList.java
	 * @author 钟琼
	 * @version 0.1 2013-2-26
	 */
	@Override
	public int queryDutyListCount(QueryDutyCondition queryDutyCondition) {
		return dutyDao.queryDutyListCount(queryDutyCondition);
	}
	/**
	 * <p>
	 * Description:接入工单查询查询工单责任---无分页<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-7
	 * @param SearchDutyCondition
	 * @return Duty
	 */
	public List<Duty> searchDutyByCondition(SearchDutyCondition sdc){
		return dutyDao.searchDutyByCondition(sdc);
	}
	/**
	 * <p>
	 * Description:根据条件查询查询工单责任---有分页<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-7
	 * @param SearchDutyCondition
	 * @param rowBounds
	 * @return List<Duty>
	 */
	public List<Duty> searchDutyByCondition(SearchDutyCondition sdc,int start,int limit){
		RowBounds rowBounds = new RowBounds(start,limit);
		return dutyDao.searchDutyByCondition(sdc, rowBounds);
	}
	/**
	 * <p>
	 * Description:根据条件计算查询工单责任数<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-7
	 * @param SearchDutyCondition
	 * @return List<Duty>
	 */
	public int countDutyByCondition(SearchDutyCondition sdc){
		return dutyDao.countDutyByCondition(sdc);
	}
	/**
	 * <p>
	 * Description:根据ID更新共担责任信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-7
	 * @param Duty
	 * @return int
	 */
	public int updateDutyById(Duty duty){
		return dutyDao.updateDutyById(duty);
	}
	/**
	 * <p>
	 * 查询工单审批列表<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-12
	 * @param queryDutyCondition
	 * @param start
	 * @param limit
	 * @return
	 * List<DutyQueryResult>
	 */
	@Override
	public List<DutyQueryResult> queryDutyApprovalList(
			QueryDutyCondition queryDutyCondition, int start, int limit) {
		return dutyDao.queryDutyApprovalList(queryDutyCondition,start,limit);
	}
	/**
	 * <p>
	 * 统计工单审批总数<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-12
	 * @param queryDutyCondition
	 * @return
	 * int
	 */
	@Override
	public int countQueryDutyApprovalList(QueryDutyCondition queryDutyCondition) {
		return dutyDao.countQueryDutyApprovalList(queryDutyCondition);
	}
	/**
	 * <p>
	 * Description:工单责任 -查询-工单责任详情<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	@Override
	public Duty getDutyDetail(String dutyId) {
		return dutyDao.getDutyDetail(dutyId);
	}
	/**
	 * <p>
	 * Description:工单责任 -查询-处理经过详情<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	@Override
	public List<DutyDealProcess> getDutyDealProcess(String dutyId) {
		return dutyDao.getDutyDealProcess(dutyId);
	}
	/**
	 * <p>
	 * Description:工单责任 -查询-划分结果<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	@Override
	public List<DutyResult> getDutyResult(String dutyId) {
		return dutyDao.getDutyResult(dutyId);
	}
	/**
	 * <p>
	 * Description:工单责任 -查询-通知对象<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	@Override
	public List<String> getuserNameList(String dutyId) {
		return dutyDao.getuserNameList(dutyId);
	}
	/**
	 * <p>
	 * Description: 工单查询-展开表格<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-14
	 */
	@Override
	public List<DutyQueryResult> queryDutyDetailList(String dutyResultId) {
		return dutyDao.queryDutyDetailList(dutyResultId);
	}
	/**
	 * <p>
	 * Description: 插入责任数据<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-14
	 */
	@Override
	public void complaintsInsert(Complaint complaint) {
		dutyDao.complaintsInsert(complaint);
		
	}
	/**
	 * <p>
	 * Description: 修改责任数据<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-14
	 */
	@Override
	public void updateComplaint(Complaint complaint) {
		dutyDao.updateComplaint(complaint);
		
	}
	/**
	 * <p>
	 * Description: 修改处理经过<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	@Override
	public void updateProcessPass(String processPass, String modifyUser,String dutyId) {
		dutyDao.updateProcessPass(processPass, modifyUser,dutyId);
	}
	/**
	 * <p>
	 * Description: 修改状态<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	@Override
	public void updateStatus(String status, String modifyUser, String dutyId) {
		dutyDao.updateStatus(status, modifyUser,dutyId);
	}
	/**
	 * <p>
	 * Description: 修改调查状态<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	@Override
	public void updateSurveyResult(String surveyResult, String modifyUser,
			String dutyId) {
		dutyDao.updateSurveyResult(surveyResult, modifyUser,dutyId);
	}
	
	@Override
	public List<String> getAllParentDeptId(String detailId) {
		return dutyDao.getAllParentDeptId(detailId);
	}
}