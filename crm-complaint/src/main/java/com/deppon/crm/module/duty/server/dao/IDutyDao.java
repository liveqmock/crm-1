package com.deppon.crm.module.duty.server.dao;


import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.duty.shared.domain.Duty;
import com.deppon.crm.module.duty.shared.domain.DutyDealProcess;
import com.deppon.crm.module.duty.shared.domain.DutyQueryResult;
import com.deppon.crm.module.duty.shared.domain.DutyResult;
import com.deppon.crm.module.duty.shared.domain.QueryDutyCondition;
import com.deppon.crm.module.duty.shared.domain.SearchDutyCondition;

 
/**
 * 
 * <p>
 * Description:工单责任 Dao<br />
 * </p>
 * @title IDutyDao.java
 * @package com.deppon.crm.module.duty.server.dao 
 * @author 侯斌飞
 * @version 0.1 2013-2-26
 */
public interface IDutyDao {

	/**
	 * <p>
	 * Description:接入工单查询查询工单责任---无分页<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-7
	 * @param SearchDutyCondition
	 * @return Duty
	 */
	public List<Duty> searchDutyByCondition(SearchDutyCondition sdc);
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
	public List<Duty> searchDutyByCondition(SearchDutyCondition sdc,RowBounds rowBounds);

	/**
	 * 
	 * <p>
	 * Description:工单责任-查询<br />
	 * </p>
	 * @title queryDutyList.java
	 * @author 钟琼
	 * @version 0.1 2013-2-26
	 */
	List<DutyQueryResult> queryDutyList(QueryDutyCondition queryDutyCondition,int start,int limit);
	/**
	 * 
	 * <p>
	 * Description:工单责任-查询条数<br />
	 * </p>
	 * @title queryDutyList.java
	 * @author 钟琼
	 * @version 0.1 2013-2-26
	 */
	int queryDutyListCount(QueryDutyCondition queryDutyCondition);
	/**
	 * <p>
	 * Description:根据条件计算查询工单责任数<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-7
	 * @param SearchDutyCondition
	 * @return List<Duty>
	 */
	public int countDutyByCondition(SearchDutyCondition sdc);
	/**
	 * <p>
	 * Description:根据ID更新共担责任信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-7
	 * @param Duty
	 * @return int
	 */
	public int updateDutyById(Duty duty);
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
	public int countQueryDutyApprovalList(QueryDutyCondition queryDutyCondition);
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
	public List<DutyQueryResult> queryDutyApprovalList(
			QueryDutyCondition queryDutyCondition, int start, int limit);
	/**
	 * <p>
	 * Description:工单责任 -查询-工单责任详情<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	public Duty getDutyDetail(String dutyId);
	/**
	 * <p>
	 * Description:工单责任 -查询-处理经过详情<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	public List<DutyDealProcess> getDutyDealProcess(String dutyId);
	/**
	 * <p>
	 * Description:工单责任 -查询-划分结果<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	public List<DutyResult> getDutyResult(String dutyId);
	/**
	 * <p>
	 * Description:工单责任 -查询-通知对象<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	public List<String> getuserNameList(String dutyId);
	/**
	 * <p>
	 * Description: 工单查询-展开表格<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-14
	 */
	public List<DutyQueryResult> queryDutyDetailList(String dutyResultId);
	
	
	/**
	 * <p>
	 * Description: 插入责任数据<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-14
	 */
	public void complaintsInsert(Complaint complaint);
	/**
	 * <p>
	 * Description: 修改责任数据<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-14
	 */
	public void updateComplaint(Complaint complaint);
	/**
	 * <p>
	 * Description: 修改处理经过<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	public void updateProcessPass(String processPass,String modifyUser,String dutyId);
	/**
	 * <p>
	 * Description: 修改状态<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	public void updateStatus(String status,String modifyUser,String dutyId);
	/**
	 * <p>
	 * Description: 修改调查状态<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	void updateSurveyResult(String surveyResult, String modifyUser,
			String dutyId);
	
	List<String> getAllParentDeptId(String detailId);

}
