package com.deppon.crm.module.duty.server.util;
import java.util.HashMap;
import java.util.Map;

public class DutyConstants {
	/**
	 * 审批退回待办事项文本(统计员)
	 */
	public static final String DUTY_STA_TODO_WORD= "责任反馈无效";
	/**
	 * 审批退回待办事项文本(质检员)
	 */
	public static final String DUTY_INS_TODO_WORD= "责任反馈无效";
	/**
	 * 任务类型(统计员)
	 */
	public static final String DUTY_TODO_STA_RETURNBACK = "STATISTICIANS_RETURNBACK";
	/**
	 * 任务类型(质检员)
	 */
	public static final String DUTY_TODO_INS_RETURNBACK = "QUALITY_INSPECTOR_RETURNBACK";
	/**
	 * 质检员同意 
	 */
	public static final String INSPECTOR_AGREE = "INSPECTOR_AGREE";
	/**
	 * 质检员退回
	 */
	public static final String INSPECTOR_DISAGREE = "INSPECTOR_DISAGREE";
	/**
	 * “退回营业部重新反馈”勾选框的对话框
	 */
	public static final boolean SELECTED = true;
	public static final boolean NOT_SELECTED  = false;
	/** 常量DUTY **/
	public static final String DUTY = "DUTY";

	/** 常量0**/
	public static final Integer ZERO = 0;
	
	/** 系统管理员USERID**/
	public static final String SYS_USERID = "1";
	
	/** 划分结果的生效和失效**/
	public static final String FAIL = "FAIL";//失效
	public static final String EFFECTIVE = "EFFECTIVE";//生效
	
	/** 处理经过状态（人为，自动）**/
	public static final String TREATSTATUS_PERSON = "PERSON";//人为
	public static final String TREATSTATUS_SYS = "SYS";//自动
	
	/** 工单责任划分类型 **/
	//部门
	public static final String DEVIDETYPE_DEPT = "department";
	//职员
	public static final String DEVIDETYPE_EMP = "employee";
	
	/** 新增，修改，删除 **/
	//新增
	public static final String STSTUS_ADD = "ADD";
	//修改
	public static final String STSTUS_UPDATE = "UPDATE";
	//删除
	public static final String STSTUS_DELETE = "DELETE";
	
	
	/**
	 * 大区统计员以及质检员的角色ID
	 * 
	 */
	//统计员角色
	public static final String STATISTICS_ROLE_ID="400000165";
	//质检员角色
	public static final String QUALITYINSPECTOR_ROLE_ID="400000185";
	//事业部质检员角色
	public static final String BUSQUALITYINSPECTOR_ROLE_ID="400000600";
	
	//查询条件最大查询天数 31 
	public static final int MAX_QUERY_DAYS = 31;
	/**
	 * 查询条件
	 */
	//处理编号
	public static final String DEALNUMBER = "dealbill";
	//凭证号
	public static final String VOUCHERNUMBER = "bill";
	//重复工单码
	public static final String REBINDNO = "recomcode";
	/**
	 * 接入工单数
	 */
	public static final String RECEIVENUM = "1";
	/**
	 * 工单责任最大接入数
	 */
	public static final int MAXRECEIVENUM = 10;
	/**
	 * 工单是否被接入
	 */
	//未被接入
	public static final String UNRECEIVE = "UNRECEIVE";
	//已接入
	public static final String RECEIVED= "RECEIVED";
	/**
	 * 工单责任处理状态
	 */
	//未划分
	public static final String UNALLOCAT = "UNALLOCAT";
	//已划分
	public static final String ALLOCATED = "ALLOCATED";
	//暂存
	public static final String TEMPORARY = "TEMPORARY";

	
	/**
	 * 工单责任的上报类型 (投诉,异常)
	 */
	//投诉
	public static final String DUTYREPORTTYPE_COMPLAINT = "COMPLAINT";
	//异常
	public static final String DUTYREPORTTYPE_UNUSUAL = "UNUSUAL";
	 //工单责任查询
	@SuppressWarnings("serial")
	public static final Map<String,String> DUTY_QUERY= new  HashMap<String,String>() {
		{	// 处理编号
			put("001", "DUTY.FDEALNUMBER" );
			// 凭证号
			put("002", "DUTY.FVOUCHERNUMBER" );
	     }
	};
	/**
	 * 工单责任接入权限
	 */
	//接入投诉员工
	public static final String RECEIVE_AUTH_COMPLAINTEMP = "49";
	//查询投诉经理
	public static final String RECEIVE_AUTH_COMPLAINTMAN = "76";
	//接入异常员工
	public static final String RECEIVE_AUTH_UNUSUALEMP = "51";
	//接入异常经理
	public static final String RECEIVE_AUTH_UNUSUALMAN = "77";
	/**
	 * 调查结果
	 */
	//待调查
	public static final String DUTY_SURVEYRESULT_LIBRAMS = "LIBRAMS";
	//调查中
	public static final String DUTY_SURVEYRESULT_INNER = "INNER";
	//调查完成（无责）
	public static final String DUTY_SURVEYRESULT_NORESPONSIBILITY = "NORESPONSIBILITY";
	//（总）调查完成
	public static final String  DUTY_SURVEYRESULT_COMPLETION = "COMPLETION";
	/**
	 * 工单责任状态
	 */
	//1 责任待反馈
	public static final String DUTY_STATUS_WAITING_FEEDBACK = "WAITING_FEEDBACK";
	//2 责任待审批
	public static final String DUTY_STATUS_WAITING_APPROVAL = "WAITING_APPROVAL";
	//3 反馈审批中
	public static final String DUTY_STATUS_APPROVALING = "APPROVALING";
	//4 统计员退回
	public static final String DUTY_STATUS_TRUNING_BACK = "STATIS_TRUNING_BACK";
	//5质检员退回
	public static final String DUTY_STATUS_INSPECTOR_TRUNING_BACK = "INSPECTOR_TRUNING_BACK";
	//5 责任反馈超期
	public static final String DUTY_STATUS_FEEDBACK_EXCEED = "FEEDBACK_EXCEED";
	//6 责任认领
	public static final String DUTY_STATUS_DUTY_ACCEPT = "DUTY_ACCEPT";
	//7 审批超期
	public static final String DUTY_STATUS_APPROVAL_EXCEED = "APPROVAL_EXCEED";
	//8 反馈有效
	public static final String DUTY_STATUS_FEEDBACK_VALID = "FEEDBACK_VALID";
	//9 反馈无效
	public static final String DUTY_STATUS_FEEDBACK_INVALID = "FEEDBACK_INVALID";
	//10 确认无责
	public static final String DUTY_STATUS_NO_DUTY = "NO_DUTY";
	
	
	//责任退回
	public static final String DUTY_STATUS_DUTY_TRUNING_BACK  = "DUTY_TRUNING_BACK";
	
	/**
	 * 工单责任调查结果
	 */
	//成立
	public static final String DUTY_SURVEYRESULT_RIGHT = "DUTY_ESTABLISH";
	//不成立
	public static final String DUTY_SURVEYRESULT_WHRONG = "DUTY_NOT_ESTABLISH";
	//调查中
	public static final String DUTY_SURVEYRESULT_DOING = "DUTY_SURVEYING";
	
	/**
	 * 审批常量
	 */
	//统计员审批同意
	public static final String STATISTICS_APPROVAL_AGREE = "STATAGREE";
	//统计员审批退回
	public static final String STATISTICS_APPROVAL_DISAGREE = "STATDISAGREE";
	public static final String  STANDARD_DEPARTMENT_CODE="DP00053";// 呼叫中心
	/**
	 * 是否第一次进入经理查询页面
	 */
	//第一次进入页面
	public static final int FIRST_LOAD_IN_MANAGER = 0;
	//不是第一次进入页面
	public static final int NOT_FIRST_LOAD_IN_MANAGER = 1;
	
	
	//责任代办提醒数据字典（责任提示消息）
	public static final String DUTY_MESSAGE = "DUTY_MESSAGE";
	//中文“责任认领”
	public static final String DUTY_CLAIMC_CH =	"责任认领";
	
	
	public static final String SPECIALDUTYDEPT  = "1";   //特殊责任部门
	public static final String ORDINARYDUTYDEPT = "0";   //经营本部下的普通责任部门
	public static final String NOTORDINARYDUTYDEPT = "-1";  //非经营本部下的部门or人员
	
	public static final String HONGKONGAREA = "DP12410"; //香港大区
	public static final String OFFICEOFHONGKONGDISTRICT = "DP12411"; //香港大区办公室标杆编码
}
