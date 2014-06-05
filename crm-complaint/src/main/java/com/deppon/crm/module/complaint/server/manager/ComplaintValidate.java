package com.deppon.crm.module.complaint.server.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.deppon.crm.module.complaint.server.util.Constants;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.exception.ComplaintException;
import com.deppon.crm.module.complaint.shared.exception.ComplaintExceptionType;
import com.deppon.crm.module.complaint.shared.exception.ComplaintValidatorException;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * <p>
 * Description:工单验证<br />
 * </p>
 * @author ouy
 * @version 0.1 2012-6-15
 * @param 
 * @return
 */
public class ComplaintValidate {
	/**
	 * 
	 * <p>
	 * Description:是否单号相同<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-15
	 * @param owNum
	 * @param complaint
	 * @return
	 * boolean
	 */
	public boolean isSameOwNumber(String owNum,Complaint complaint){
		// 检查工单单号
		if((null==owNum || "".equals(owNum.trim()))&&(null==complaint.getBill() ||"".equals(complaint.getBill().trim()) )){
			// 为空返回true
			return true;
		}
		// 是否相等
		if(!owNum.equals(complaint.getBill())){
			//工单号不相等
			ComplaintException e = new ComplaintException(ComplaintExceptionType.BILL_NOTEQUERL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 结果返回
		return true;
	}
	/**
	 * 
	 * <p>
	 * Description:上报类型是否相同<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-15
	 * @param complaintType
	 * @param complaint
	 * @return
	 * boolean
	 */
	public boolean isSameComplaitType(String complaintType,Complaint complaint){
		//上报类型不相等
		if(!complaintType.equals(complaint.getReporttype())){
			//上报类型不相等
			ComplaintException e = new ComplaintException(ComplaintExceptionType.REPORTTYPE_NOTEQUERL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 结果返回
		return true;
	}
	/**
	 * 
	 * <p>
	 * Description:工单待处理<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-15
	 * @param complaintType
	 * @param complaint
	 * @return
	 * boolean
	 */
	public boolean isPendingComplaint(Complaint complaint) {
		// 工单处理状态
		String currentStatus = complaint.getProstatus();
		// 工单待处理
		if (currentStatus.equals(Constants.COMPLAINT_STATUS_PENDING)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 
	 * <p>
	 * Description:任务部门已退回<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-15
	 * @param complaintType
	 * @param complaint
	 * @return
	 * boolean
	 */
	public boolean isAllTaskReturned(List<Result> resultList,BigDecimal resultId){
		// 是否全部返回 标志
		boolean isAllReturned=true;
		// 处理结果不为空的话
		if(resultList!=null&&resultList.size()>0){
			// 迭代
			Iterator iter=resultList.iterator();
			while(iter.hasNext()){
				Result result=(Result)iter.next();
				if(!result.getFid().equals(resultId)&&(result.getStat()==null
						// 任务部门已退回
						||!result.getStat().equals(Constants.COMPLAINT_TASK_STATUS_RETURNED))){
					return false;
				}
			}
		}
		return isAllReturned;
	}
	
	/**
	* @Title: 		isReturnableComplaintInTask
	* @Description: TODO(判断任务部门是否可退回此工单)
	* @param 		@param complaint
	* @param 		@return    设定文件
	* @return 		boolean    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public boolean isReturnableComplaintInTask(Complaint complaint){
		boolean flag=false;
		List<String> postponeStatus=new ArrayList<String>();
		// 部分部门退回，从处理过来的
		postponeStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS);
		// 部分部门退回，从审批过来的
		postponeStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL);
		// 部分部门退回，从升级过来的
		postponeStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE);
		// 待反馈
		postponeStatus.add(Constants.COMPLAINT_STATUS_WAITE_RESPONSE);
		if (postponeStatus.contains(complaint.getProstatus())){
			flag=true;
		}
		else{
			// 不可退回处理人，不在可执行状态
			ComplaintException e = new ComplaintException(ComplaintExceptionType.TASK_CAN_NOT_BE_RETURNED);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};	
		}
		return flag;
	}
	
	/**
	* @Title: 		isFeedbackableComplaint
	* @Description: TODO(判断任务部门是否可反馈登记此工单)
	* @param 		@param complaint
	* @param 		@return    设定文件
	* @return 		boolean    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public boolean isFeedbackableComplaint(Complaint complaint){
		boolean flag=false;
		// 待反馈
		if(complaint.getProstatus().equals(Constants.COMPLAINT_STATUS_WAITE_RESPONSE)
				// 部分部门退回，从处理过来的
				||complaint.getProstatus().equals(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS)
				// 部分部门退回，从审批过来的
				||complaint.getProstatus().equals(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL)
				// 部分部门退回，从审批过来的
				||complaint.getProstatus().equals(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE)
				//反馈未解决
				||complaint.getProstatus().equals(Constants.COMPLAINT_PROCESS_STATUS_UNRESOVLE)
				// 反馈已解决
				||complaint.getProstatus().equals(Constants.COMPLAINT_PROCESS_STATUS_RESOVLE)){
			flag=true;
		}
		else{
			// 反馈已解决
			ComplaintException e = new ComplaintException(ComplaintExceptionType.TASK_CAN_NOT_BE_FEEDBACKABLE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};	
		}
		return flag;
	}
	
	/**
	* @Title: 		isDelayableComplaint
	* @Description: TODO(判断任务部门是否可申请延时此工单)
	* @param 		@param complaint
	* @param 		@return    设定文件
	* @return 		boolean    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public boolean isDelayableComplaint(Complaint complaint){
		boolean flag=false;
		// 待反馈
		if(complaint.getProstatus().equals(Constants.COMPLAINT_STATUS_WAITE_RESPONSE)
				// 部分部门退回，从处理过来的
				||complaint.getProstatus().equals(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS)
				// 部分部门退回，从审批过来的
				||complaint.getProstatus().equals(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL)
				// 部分部门退回，从升级过来的
				||complaint.getProstatus().equals(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE)
				// 反馈未解决
				||complaint.getProstatus().equals(Constants.COMPLAINT_PROCESS_STATUS_UNRESOVLE)
				// 反馈已解决
				||complaint.getProstatus().equals(Constants.COMPLAINT_PROCESS_STATUS_RESOVLE)){
			flag=true;
		}
		else{
			// 不可申请延时，不在可执行状态
			ComplaintException e = new ComplaintException(ComplaintExceptionType.TASK_CAN_NOT_APPLY_DELAY);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};	
		}
		return flag;
	}
	
	/**
	* @Title: 		isExistComplaint
	* @Description: TODO(是否已存在工单)
	* @param 		@param complaint
	* @param 		@return    设定文件
	* @return 		boolean    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public boolean isExistComplaint(Complaint complaint){
		boolean flag=true;
		if(complaint==null){
			// 工单不存在
			ComplaintException e = new ComplaintException(ComplaintExceptionType.COMPLAINT_NOT_EXIST);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};	
		}
		return flag;
	}
	
	/**
	* @Title: 		isExistResult
	* @Description: TODO(是否已存在任务部门处理结果)
	* @param 		@param result
	* @param 		@return    设定文件
	* @return 		boolean    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public boolean isExistResult(Result result){
		boolean flag=true;
		if(result==null){
			// 工单不存在
			ComplaintException e = new ComplaintException(ComplaintExceptionType.RESULT_NOT_EXIST);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};	
		}
		return flag;
	}
	
	/**
	 * <p>
	 * Description:判断部门是否可以做延时申请（延时申请审批后，不能再次申请）<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-6-6
	 * @param result
	 * @return
	 * boolean
	 */
	public boolean isNotDuplicatedDelay(Result result) {
		if (Constants.IF_DELAY_APPLICATION_EFFECTIVE_YES.equals(result.getDelay())
				|| Constants.IF_DELAY_APPLICATION_EFFECTIVE_REFUSE.equals(result.getDelay())) {
			// 任务部门已做过延时申请
			ComplaintException e = new ComplaintException(ComplaintExceptionType.RESULT_DUPLICATE_DELAY);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {};			
		}
		return true;
	}
	
	/**
	 * <p>
	 * Description:检查工单是否可进行完成处理操作<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-20
	 * @param dbComplaint
	 * @return
	 * boolean
	 */
	public static boolean canFinishProccessComplaint(Complaint dbComplaint){
		// 投诉待审批
		if(Constants.COMPLAINT_STATUS_WAIT_APPROVAL.equals(dbComplaint.getProstatus())
				// 投诉已升级
				||Constants.COMPLAINT_STATUS_UPGRADED.equals(dbComplaint.getProstatus()) 
				// 投诉升级退回
				||Constants.COMPLAINT_STATUS_UPGRADED_RETURNED.equals(dbComplaint.getProstatus())
				// 工单待处理
				|| Constants.COMPLAINT_STATUS_PENDING.equals(dbComplaint.getProstatus())
				// 投诉审批退回
				|| Constants.COMPLAINT_STATUS_APPROVAL_RETURNED.equals(dbComplaint.getProstatus())
				// 部分部门退回，从处理过来的
				|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS.equals(dbComplaint.getProstatus())
				// 部分部门退回，从审批过来的
				|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL.equals(dbComplaint.getProstatus())
				// 部分部门退回，从升级过来的
				|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE.equals(dbComplaint.getProstatus())
				//所有部门退回，从处理过来的
				|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS_ALL.equals(dbComplaint.getProstatus())
				// 所有部门退回，从审批过来的
				|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL_ALL.equals(dbComplaint.getProstatus())
				// 所有部门退回，从升级过来的
				|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE_ALL.equals(dbComplaint.getProstatus())
				// 反馈未解决
				|| Constants.COMPLAINT_PROCESS_STATUS_UNRESOVLE.equals(dbComplaint.getProstatus())
				// 反馈已解决
				|| Constants.COMPLAINT_PROCESS_STATUS_RESOVLE.equals(dbComplaint.getProstatus())
				){ 
			return true;
		}else{
			throw new ComplaintValidatorException(ComplaintExceptionType.currentComplaintCannotBeFinishProccess);
		}
	}
	
	/**
	 * <p>
	 * Description:检查当前工单是否能进行延时审批<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-22
	 * @param complaint
	 * @return
	 * boolean
	 */
	public static boolean canApplyMoreTimeApproval(Complaint complaint){
		List<String> postponeStatus=new ArrayList<String>();
		//工单申请延时,工单来自处理
		postponeStatus.add(Constants.COMPLAINT_APPLY_DELAY_TO_PROCESS);
		//工单申请延时,工单来自审批
		postponeStatus.add(Constants.COMPLAINT_APPLY_DELAY_TO_APPROVAL);
		//工单申请延时,工单来自升级
		postponeStatus.add(Constants.COMPLAINT_APPLY_DELAY_TO_UPGRADE);
		//反馈未解决
		postponeStatus.add(Constants.COMPLAINT_PROCESS_STATUS_UNRESOVLE);
		//反馈已解决
		postponeStatus.add(Constants.COMPLAINT_PROCESS_STATUS_RESOVLE);
		if(!postponeStatus.contains(complaint.getProstatus())){
			throw new ComplaintValidatorException(ComplaintExceptionType.cannotMoreTimeApproval);
		}
		return true;
	}
	
	/**
	 * <p>
	 * Description:检查当前工单是否勾选最终反馈<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-23
	 * @param complaint
	 * @return
	 * boolean
	 */
	public static boolean isFinalFeedback(Complaint complaint) {
		if (Constants.IF_FEED_BACK_YES.equals(complaint.getFeedback())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description: 检查当前工单状态是否为待审批状态<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-24
	 * @param complaint
	 * @return
	 * boolean
	 */
	public static boolean isWaitApproval(Complaint complaint) {
		// 投诉待审批
		if (Constants.COMPLAINT_STATUS_WAIT_APPROVAL.equals(complaint.getProstatus())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * <p>
	 * Description: 检查当前工单状态是否为已升级状态<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-24
	 * @param complaint
	 * @return
	 * boolean
	 */
	public static boolean isUpgrade(Complaint complaint) {
		//投诉已升级
		if (Constants.COMPLAINT_STATUS_UPGRADED.equals(complaint.getProstatus())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * <p>
	 * Description:检查待办工单是否可进行提交审批或投诉升级操作<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-5-3
	 * @param dbComplaint
	 * @return
	 * boolean
	 */
	public static boolean canSubmitApprovalComplaint(Complaint dbComplaint){
		//投诉升级退回
		if(Constants.COMPLAINT_STATUS_UPGRADED_RETURNED.equals(dbComplaint.getProstatus())
				//部分部门退回，从处理过来的
				|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS.equals(dbComplaint.getProstatus())
				//部分部门退回，从审批过来的
				|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL.equals(dbComplaint.getProstatus())
				//部分部门退回，从升级过来的
				|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE.equals(dbComplaint.getProstatus())
				// 所有部门退回，从处理过来的
				|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS_ALL.equals(dbComplaint.getProstatus())
				// 所有部门退回，从审批过来的
				|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL_ALL.equals(dbComplaint.getProstatus())
				//所有部门退回，从升级过来的
				|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE_ALL.equals(dbComplaint.getProstatus())
				//投诉审批退回
				|| Constants.COMPLAINT_STATUS_APPROVAL_RETURNED.equals(dbComplaint.getProstatus())
				//工单待处理
				|| Constants.COMPLAINT_STATUS_PENDING.equals(dbComplaint.getProstatus()) 
				){ 			
			return true;			
		}else{
			throw new ComplaintValidatorException(ComplaintExceptionType.currentComplaintCannotBeSubmitApproval);
		}
	}
	/**
	 * 
	 * <p>
	 * Description:是否可以绑定（单号相同，上报类型相同可以绑定）<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-5-10
	 * @param dbComplaint
	 * @return
	 * boolean
	 */
	public static boolean ifComplainBinding(Complaint complaintMain,Complaint complaintSecond){
		// 上报类型相等
		if(complaintMain.getReporttype().trim().equals(complaintSecond.getReporttype().trim())){
			// 工单号为空
			if ((null == complaintMain.getBill() || "".equals(complaintMain.getBill().trim()))
					&& (null == complaintSecond.getBill() || "".equals(complaintSecond.getBill().trim()))) {
				return true;
			}else if (stringComparasion(complaintMain.getBill(),complaintSecond.getBill())){
				return true;
			}else{
				// 工单校验异常
				ComplaintValidatorException re = new ComplaintValidatorException(ComplaintExceptionType.connotComplainBinding);
				throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
						new Object[] {}) {
				};
//				throw new ComplaintValidatorException(ComplaintExceptionType.connotComplainBinding);
			}
		}else{
			// 工单校验异常
			ComplaintValidatorException re = new ComplaintValidatorException(ComplaintExceptionType.connotComplainBinding);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
//			throw new ComplaintValidatorException(ComplaintExceptionType.connotComplainBinding);
		}
	}
	/**
	 * 
	 * <p>
	 * Description:工单是否可以退回（反馈过的工单不能退回）<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-15
	 * @param complaint
	 * @return
	 * boolean
	 */
	public static boolean canComplainStatuCanReturn(Complaint complaint){
		// 反馈未解决
		// 反馈已解决
		if(null !=complaint.getProstatus() && (Constants.COMPLAINT_PROCESS_STATUS_UNRESOVLE.equals(complaint.getProstatus().trim()) 
				                           || Constants.COMPLAINT_PROCESS_STATUS_RESOVLE.equals(complaint.getProstatus().trim()))){
//			throw new ComplaintValidatorException(ComplaintExceptionType.feedbackResolvedComplainConnotReturn);
			// 工单校验异常
			ComplaintValidatorException re = new ComplaintValidatorException(
					ComplaintExceptionType.feedbackResolvedComplainConnotReturn);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		return true;
	}
	
	/**
	 * 字符串比较，包含过滤null功能
	 * @param compare
	 * @param beCompared
	 * @return
	 */
	public static boolean stringComparasion(String compare,String beCompared){
		// 字符串为空
		if((compare==null || "".equals(compare)) 
				&& (beCompared == null || "".equals(beCompared))){
			// 返回true
				return true;
		}else{
			// 返回真是比较值
			return compare.equals(beCompared);
		}
	}
	
	/**
	 * <p>
	 * Description: 反馈已解决的工单，超出处理时间不能再进行反馈，但状态不会变成已到期<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-10-10
	 * @param complaint
	 * @return
	 * boolean
	 */
	public static boolean canPassFeedback(Result result ){
		//处理时间
		Date now = new Date();
		String unit = "hour";
		// 处理时限
		boolean overTime = ComplaintValidate.isOverTime(now,
				result.getDealtime(), result.getProcesstimelimit(), unit);
		// 反馈已解决
		if (overTime){
			// 超出处理时间
			ComplaintException e = new ComplaintException(ComplaintExceptionType.CANT_OVERTIME_FEEDBACK_RESOLVED);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
			new Object[] {}) {
			};	
		}
/*		if (complaint.getProstatus().equals(Constants.COMPLAINT_PROCESS_STATUS_RESOVLE)){
			ComplaintException e = new ComplaintException(ComplaintExceptionType.CANT_REPEAT_FEEDBACK_RESOLVED);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}*/
		return true;
	}
	

	/**
	 * <p>
	 * Description: 处理时限<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-10-10
	 * @param complaint
	 * @return
	 * boolean
	 */
	public static boolean isOverTime(Date now, Date dealtime,
			BigDecimal processtimelimit, String unit) {
/*		Double process=Double.parseDouble(processtimelimit+"");
		Long restTime =new Long(0);*/
		double restTime =0.0;
		double timeVar=0.0;
		if(unit.equals("hour")){
			timeVar=1000*60*60.0;
		}else if(unit.equals("min")){
			timeVar=1000*60.0;
		}else if(unit.equals("sec")){
			timeVar=1000.0;
		}
		if(null != dealtime){
			restTime=( now.getTime()-dealtime.getTime())/timeVar;
		}
		if(restTime>new Double(processtimelimit+"")){
			return true;
		}else{
			return false;
		}
	}
}
