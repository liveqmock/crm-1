package com.deppon.crm.module.duty.server.manager;
 
import java.util.Date;
import java.util.List;
import java.util.Map;
 
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.duty.shared.domain.CommitDutyResultVO;
import com.deppon.crm.module.duty.shared.domain.Duty;
import com.deppon.crm.module.duty.shared.domain.DutyDealProcess;
import com.deppon.crm.module.duty.shared.domain.DutyDept;
import com.deppon.crm.module.duty.shared.domain.DutyFeedback;
import com.deppon.crm.module.duty.shared.domain.DutyQueryResult;
import com.deppon.crm.module.duty.shared.domain.DutyResult;
import com.deppon.crm.module.duty.shared.domain.FeedAttach;
import com.deppon.crm.module.duty.shared.domain.InformUser;
import com.deppon.crm.module.duty.shared.domain.QueryDutyCondition;
import com.deppon.crm.module.duty.shared.domain.SearchDutyCondition;
import com.deppon.crm.module.organization.shared.domain.Department;
 
/**
 * 
 * <p>
 * Description:工单责任 Manager<br />
 * </p>
 * @title IDutyManager.java
 * @package com.deppon.crm.module.duty.server.manager 
 * @author 侯斌飞
 * @version 0.1 2013-2-26
 */
/**
 * <p>
 * Description:<br />
 * </p>
 * @title IDutyManager.java
 * @package com.deppon.crm.module.duty.server.manager
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-3-14
 */
public interface IDutyManager {
	/**
	 * 
	 * <p>
	 * Description:工单责任-查询<br />
	 * </p>
	 * @title queryDutyList.java
	 * @author 钟琼
	 * @version 0.1 2013-2-26
	 */
	Map<String,Object> queryDutyList(QueryDutyCondition queryDutyCondition,int start,int limit);
	/**
	 * 
	 * <p>
	 * Description:工单责任接入责任<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 * @return duty
	 */
	public Map<String,Object> receiveDuty(User user,Integer type);
	/**
	 * 
	 * <p>
	 * Description:工单责任初始化员工接入页面<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 * @return map
	 */
	public Map<String,Integer> countUnreceiveDuty( User user);
	/**
	 * 
	 * <p>
	 * Description:工单责任查询责任---有分页<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 * @return duty
	 */
	public Map<String,Object> searchDutyByCondition(User user,SearchDutyCondition sdc ,int start,int limit);
	/**
	 * 
	 * <p>
	 * Description:工单责任--初始化工单责任接入页面<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 * @return Map<String,Object>
	 */
	public Map<String,Object> initReceiveDuty(User user);
	/**
	 * <p>
	 * Description:工单责任 -查询-工单责任详情<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	Map<String,Object> getDutyDetail(String dutyId);
	
	/**
	 * <p>
	 * Description:工单责任 -查询-工单责任详情<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-11
	 */
	Duty searchDutyById(String dutyId);
	
	void getDutyById(String dutyId, boolean temporary, boolean responsibility, boolean submit);
	
	/**
	 * <p>
	 * Description:根据工单责任ID，查询责任反馈记录列表<br />
	 * @author xiaohongye
	 * @param searchParam
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-14
	 */
	public List<DutyFeedback> searchDutyFeedbackByDutyId(Map<String,Object> searchParam);
	
	/**
	 * 
	 * <p>
	 * Description:根据工单责任ID查询工单责任划分结果
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-4-27下午2:34:10
	 * @param dutyId
	 * @return
	 * List<DutyResult>
	 * @update 2013-4-27下午2:34:10
	 */
	public List<DutyResult>searchDutyResult(String dutyId);
	/**
	 * <p>
	 * Description:质检员审批同意：根据工单责任反馈ID和工单责任划分ID更新工单责任反馈记录和工单责任划分结果<br />
	 * @author xiaohongye
	 * @param detailId
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-18
	 */
	public void callerAgreeOfApproval(DutyFeedback dutyFeedback,User user);
	
	/**
	 * <p>
	 * Description:质检员审批退回：根据工单责任反馈ID和工单责任划分ID更新工单责任反馈记录和工单责任划分结果<br />
	 * @author xiaohongye
	 * @param detailId
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-18
	 */
	public void callerDisagreeOfApproval(DutyFeedback dutyFeedback,User user);
	
	/**
	 * <p>
	 * Description:统计员审批操作：根据工单责任反馈ID和工单责任划分ID更新工单责任反馈记录和工单责任划分结果<br />
	 * @author xiaohongye
	 * @param detailId
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-18
	 */
	public void statisOperationOfApproval(DutyFeedback dutyFeedback,String approvalType,User user);
	
	/**
	 * <p>
	 * 工单责任审批列表<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-11
	 * @param queryDutyCondition
	 * @param start
	 * @param limit
	 * @param user
	 * @return
	 * Map<String,Object>
	 */
	Map<String, Object> queryDutyApprovalList(
			QueryDutyCondition queryDutyCondition, int start, int limit,
			User user);
	/**
	 * <p>
	 * Description: 工单查询-展开表格<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-14
	 */
	List<DutyQueryResult> queryDutyDetailList(String dutyResultId);
	
	/**
	 * 
	 * <p>
	 * Description:提交责任划分结果<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-18
	 */
	void commitDutyResults(CommitDutyResultVO commitDutyResultVO, User user);
	
	/**
	 * 
	 * <p>
	 * Description:暂存责任划分结果<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-19
	 */
	void temporaryDutyResults(CommitDutyResultVO commitDutyResultVO, User user);
	
	/**
	 * 
	 * <p>
	 * Description:查询划分结果<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-19
	 */
	List<DutyResult> searchDutyResults(String dutyId);
	
	/**
	 * 
	 * <p>
	 * Description:查询通知对象<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-19
	 */
	List<InformUser> searchInformUsers(String dutyId);
	/**
	 * 
	 * <p>
	 * Description:查询处理经过<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-19
	 */
	List<DutyDealProcess> searchDutyDealProcess(String dutyId);
	/**
	 * 
	 * <p>
	 * Description:确认无责<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	void sureNoResponsibility(Duty dutyDetail, User user);
	
	/**
	 * 
	 * <p>
	 * Description:查询最近的处理经过<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	DutyDealProcess selectMaxDutyDealProcess(String dutyId);
	
	/**
	 * 
	 * <p>
	 * Description:责任认领<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-22
	 */
	void dutyClaim(String dutyId,String rsId,User user);
	
	/**
	 * 
	 * <p>
	 * Description:责任反馈<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-22
	 */
	void dutyFeedBack(String dutyId,DutyFeedback dutyFeedback, User user,List<FeedAttach> fileInfoList);
	
	/**
	 * 
	 * <p>
	 * Description:反馈超期操作<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-23
	 */
	void feedBackExtended();
	
	/**
	 * 
	 * <p>
	 * Description:审批超期操作<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-4-8
	 */
	void feedBackApprovalExtended();
	/**
	 * 
	 * <p>
	 * Description:责任反馈主查询（当前登录用户被划分的责任）<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-23
	 */
	Map searchDutyFeedBackMain(User user,int start,int limit);
	
	/**
	 * 
	 * <p>
	 * Description:查询附件信息<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-27
	 */
	List<FeedAttach> searchFeedBackFileInfo(String feedBackId);
	/**
	 * <p>
	 * Description:批量删除附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-27
	 * @param fids
	 * @return void
	 */
	void deleteFeedAttachs(List<String> feedAttachIds);
	
	/**
	 * <p>
	 * Description:查询统计员部门<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-28
	 * @param feedAttach
	 * @return void
	 */
	public List<Department> searchStatisticiansDept();
	/**
	 * <p>
	 * Description:工单责任 -查询-工单责任详情<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	Duty getDutyDomainDetail(String dutyId);
	/**
	 * 
	 * <p>
	 * 得到反馈超期日期<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-4-1
	 * @param date
	 * @return
	 * Date
	 */
	public Date feedBackExtendedTime(int day);
	/**
	 * <p>
	 * Description:查询统计员所在部门<br />
	 * @author 张斌
	 * @param feedbackId
	 * @return
	 * @version V0.1 
	 * @Date 2013-6-8
	 */
	String searchStatDeptName(String feedbackId);
	
	/**
	 * <p>
	 *	Description: 查询工单特殊责任部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-9
	 * @return
	 * List<DutyDept>
	 */
	public List<DutyDept> searchDutyDepts();
	/**
	 * <p>
	 *	Description: 添加工单特殊责任部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-10
	 * @param dutyDept
	 * void
	 */
	public void saveDutyDept(DutyDept dutyDept,User user);
	/**
	 * <p>
	 *	Description: 根据Id查询部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-10
	 * @param deptId
	 * @return 是否存在 0不存在  1存在
	 */
	public int searchDutyDeptById(String deptId);
	
	/**
	 * <p>
	 *	Description: 删除工单特殊责任部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-10
	 * @param dutyDeptList
	 * void
	 */
	public void deleteDutyDept(List<DutyDept> dutyDeptList);
	
	/**
	 * <p>
	 * Description:工单责任升级改造：责任审批同意后操作<br />
	 * @author andy
	 * @version V0.1 
	 * @Date 2014-1-13
	 * </p>
	 */
	public void commitApprovalAgree(CommitDutyResultVO commitDutyResultVO, User user);
	
	/**
	 * <p>
	 *	Description: 查询多个部门是否为同一个事业部
	 * </p> 
	 * @author hpf
	 * @date 2014-1-16
	 * @return
	 * List<DutyDept>
	 */
	boolean isSameDepartmentByDeptIds(DutyResult reuslt,User user);
}
