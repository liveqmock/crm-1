package com.deppon.crm.module.complaint.server.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.sms.ISmsInfoSender;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.complaint.shared.domain.Bulletin;
import com.deppon.crm.module.complaint.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.MessageContent;

/**
 * 发送短信的工具方法
 * @author 邢悦
 *
 */
public class MessageSendingUtil {

		
	private static final String MSG_CONTENT_UNUSUAL="您好，%s，您有一条%s工单待处理，工单编号：%s,单号：%s，来电人：%s,反馈时限%s分钟，处理时限%s小时，请及时处理";
	private static final String MSG_CONTENT = "【投诉协助】您好，%s，您有一条%s任务工单待处理，工单编号：%s，单号：%s，" +
			"业务项：%s，业务范围：%s，业务类型：%s，业务场景：%s，场景原因：%s，任务部门：%s，来电人%s，反馈时限%s分钟，处理时限%s小时，请及时处理！感谢您的配合与协作！";

	private static final String STANDARD_DEPARTMENT_CODE="DP00053";// 呼叫中心

	private static final Pattern pattern = Pattern.compile("[0-9]*");
	
	
/*	@Override
	public void run() {
			try{
				System.out.println("***************开始发送短信********************");
				smsSender.sendSms(convertToSmsInfos(bulletinList));
				System.out.println("***************短信发送结束********************");
			}catch(CrmBusinessException exception){
				exception.printStackTrace();
			}

	}*/

	/**
	 * <p>
	 * Description: 把从页面传入的bulletinList变为临时存储的cellphoneMessageInfo<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-5
	 * @param complaint			工单示例
	 * @param bulletinList		通知对象
	 * @param feedbackTimeLimit	反馈对象
	 * @param solveTimeLimit	解决时限
	 * @param senderCode		发送人工号
	 * @return
	 * List<CellphoneMessageInfo>
	 */
	public static List<CellphoneMessageInfo> convertToMsgInfo(
			Complaint complaint, List<Bulletin> bulletinList,
			String feedbackTimeLimit, String solveTimeLimit, String senderCode) {
		// 通知对象短信通知临时表
		List<CellphoneMessageInfo> msgInfos=new ArrayList<CellphoneMessageInfo>();
		
		MessageContent content = new MessageContent();
		// 处理编号
		content.setDealbill(complaint.getDealbill());
		 // 单号，即 凭证号
		content.setFbill(complaint.getBill());
		//来电人
		content.setCustomer(complaint.getCompman());
		//业务项
		content.setBusItemName(complaint.getBusItemName());
		//业务范围
		content.setBusScopeName(complaint.getBusScopeName());
		//业务类型
		content.setBusTypeName(complaint.getBusTypeName());
		//业务场景
		content.setBusSceneName(complaint.getBusSceneName());
		//场景原因
		content.setSceneRessonName(complaint.getSceneRessonName());
	
		
		for(Bulletin bulletin:bulletinList){
			//过滤掉手机号为空的通知对象，否则可能引起接口报错
			if(bulletin.getBulletinTel()!=null && pattern.matcher(bulletin.getBulletinTel().trim()).matches()){
				String type="投诉";
				if("UNUSUAL".equals(complaint.getReporttype())){
					type="异常";
				}
				// 通知对象短信通知临时表
				CellphoneMessageInfo info=new CellphoneMessageInfo();
				info.setPhoneNumber(bulletin.getBulletinTel());
				// 发送人所属部门（标杆编码）
				info.setSendStandardDeptCode(STANDARD_DEPARTMENT_CODE);		
				info.setSenderEmpCode(senderCode);
				
				//上报类型
				content.setReportTypeName(type);
				//任务部门
				content.setTaskDeptName(bulletin.getBulletinDeptName());
				//用户名
				content.setUserName(bulletin.getBulletinname());
				
				if(
					null==bulletin.getJobCode() || "".equals(bulletin.getJobCode())
					|| null == bulletin.getFeedtimelimit() || 0 == bulletin.getFeedtimelimit()
					|| null == bulletin.getProcesstimelimit() || 0 == bulletin.getProcesstimelimit()
				){
					bulletin.setFeedtimelimit(Integer.parseInt(feedbackTimeLimit));
					bulletin.setProcesstimelimit(Integer.parseInt(solveTimeLimit));
				}
				
				content.setFeedback(bulletin.getFeedtimelimit().toString());
				content.setSolve(bulletin.getProcesstimelimit().toString());
				
				info.setMsgContent(returnContent(complaint.getReporttype(),content));
				
				msgInfos.add(info);
			}
		}		
		return msgInfos;
	}

	/**
	 * 把数据库临时存储的cellphonemessageinfo转化成为短信接口需要的SmsInformation对象
	 * @param cellphoneMsgInfos
	 * @return
	 */
    @Deprecated
	public static List<SmsInformation> convertToSmsInformation(List<CellphoneMessageInfo> cellphoneMsgInfos){
		List<SmsInformation> smsInfos=new ArrayList<SmsInformation>();
		
		for(CellphoneMessageInfo msgInfo:cellphoneMsgInfos){
			SmsInformation info=new SmsInformation();
			// 收信人电话
			info.setMobile(msgInfo.getPhoneNumber());
			// 消息内容
			info.setMsgContent(msgInfo.getMsgContent());
			// 业务类型
			info.setMsgType(Constant.SMS_COMPlAINT_CODE);
			// 短信发送人
			info.setSender(msgInfo.getSenderEmpCode());
			// 短信发送部门(标杆编码)			
			info.setSendDept(STANDARD_DEPARTMENT_CODE);
			smsInfos.add(info);
		}
		
		return smsInfos;
		
	}
	/**
	 * 把数据库临时存储的cellphonemessageinfo转化成为短信接口需要的SmsInformation对象
	 * @param cellphoneMsgInfos
	 * @return
	 */
	public static List<SmsInformation> convertToSmsInformation(CellphoneMessageInfo[] cellphoneMsgInfos){
		List<SmsInformation> smsInfos=new ArrayList<SmsInformation>();
		
		for(CellphoneMessageInfo msgInfo:cellphoneMsgInfos){
			SmsInformation info=new SmsInformation();
			// 收信人电话
			info.setMobile(msgInfo.getPhoneNumber());
			// 消息内容
			info.setMsgContent(msgInfo.getMsgContent());
			// 业务类型
			info.setMsgType(Constant.SMS_COMPlAINT_CODE);
			// 短信发送人
			info.setSender(msgInfo.getSenderEmpCode());
			// 短信发送部门(标杆编码)			
			info.setSendDept(STANDARD_DEPARTMENT_CODE);
			smsInfos.add(info);
		}
		
		return smsInfos;
		
	}
	
	
	/**
	 * 把数据库临时存储的cellphonemessageinfo转化成为短信接口需要的smsinfo对象
	 * @param cellphoneMsgInfos
	 * @return
	 */
    @Deprecated
	public static  List<SmsInformation> convertToSmsInfos(List<CellphoneMessageInfo> cellphoneMsgInfos){
		List<SmsInformation> smsInfos=new ArrayList<SmsInformation>();
		// 通知对象短信通知临时表
		for(CellphoneMessageInfo msgInfo:cellphoneMsgInfos){
			SmsInformation info=new SmsInformation();
			info.setMobile(msgInfo.getPhoneNumber());
			info.setSendDept(STANDARD_DEPARTMENT_CODE);
			info.setSender(msgInfo.getSenderEmpCode());
			info.setMsgContent(msgInfo.getMsgContent());
			smsInfos.add(info);
		}
		
		return smsInfos;
	}

	/**
	 * 发送短信
	 * @throws CrmBusinessException 
	 */
	public static void sendMessage(ISmsInfoSender smsSender,List<SmsInformation> list) throws CrmBusinessException{
		smsSender.sendSms(list);
	}
	
	/**
	 * <p>
	 * Description: 生成消息内容<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-12-26
	 * @param userName
	 * @param dealbill
	 * @param fbill
	 * @param busItemName
	 * @param busScopeName
	 * @param busTypeName
	 * @param customer
	 * @param feedback
	 * @param solve
	 * @param complaintType
	 * @param taskDeptName 任务部门
	 * @return String
	 */
//	private static String returnContent(String userName, 
//			String dealbill, 
//			String fbill, 
//			String busItemName, 
//			String busScopeName, 
//			String busTypeName, 
//			String customer, 
//			String feedback, 
//			String solve, 
//			String complaintType, 
//			String taskDeptName,
//			String reportType){
//		if(reportType != null && "UNUSUAL".equalsIgnoreCase(reportType)) {
//			return String.format(MSG_CONTENT_UNUSUAL, userName,  complaintType, dealbill, fbill, customer, feedback, solve);
//		}else {
//			return String.format(MSG_CONTENT, userName,  complaintType, dealbill, fbill, busItemName, busScopeName, busTypeName,
//					taskDeptName, customer, feedback, solve);
//		}
//	}
	
	/**
	 * 工单短信内容生成
	 * @param reportType 上报类型
	 * @param msgContent 消息内容模型
	 * @return 返回消息
	 */
	private static String returnContent(String reportType,MessageContent content){
		int msgMaxLength = 300;
		String message = "";
		if(reportType != null && "UNUSUAL".equalsIgnoreCase(reportType)) {
			message = String.format(MSG_CONTENT_UNUSUAL, 
					content.getUserName(),  content.getReportTypeName(), content.getDealbill(),content.getFbill(),
					content.getCustomer(), content.getFeedback(), content.getSolve()
			);
		}else {
			message = String.format(MSG_CONTENT,
					content.getUserName(),  content.getReportTypeName(), content.getDealbill(),content.getFbill(),
					content.getBusItemName(),content.getBusScopeName(),content.getBusTypeName(),content.getBusSceneName(),content.getSceneRessonName(),
					content.getTaskDeptName(),content.getCustomer(),content.getFeedback(),content.getSolve()
			);
		}
		
		//若 短信内容长度 大于 最大长度容量 则进行截取
		if(message.length() > msgMaxLength){
			message = message.substring(0, msgMaxLength);
		}
		return message.trim();
	}
}
