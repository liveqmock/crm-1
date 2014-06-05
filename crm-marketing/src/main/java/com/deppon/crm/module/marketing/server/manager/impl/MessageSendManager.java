package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.sms.ISmsInfoSender;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.marketing.server.manager.IMessageSendManager;
import com.deppon.crm.module.marketing.server.service.IMessageSendService;
import com.deppon.crm.module.marketing.server.utils.MessageSendUtils;
import com.deppon.crm.module.marketing.server.utils.MessageSendValidator;
import com.deppon.crm.module.marketing.shared.domain.MessageSendConstance;
import com.deppon.crm.module.marketing.shared.domain.MessageSendPhone;
import com.deppon.crm.module.marketing.shared.domain.MessageSendTask;

public class MessageSendManager implements IMessageSendManager {
	private IMessageSendService messageSendService;
	//短信发送接口调用
	private ISmsInfoSender smsSender;
	/**
	 * 
	 * <p>
	 * 将上传的文件名和路径保存<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @param MessageSend mst
	 * @return int
	 */
	@Override
	public int saveMessageFile(MessageSendTask mst,User user) {
		//设置任务状态为未执行和设置读取行数为第一行(跳过表头)
		mst = MessageSendUtils.setStatusAndStartRowToMessageTask(mst,user);
		//校验营销短信发送实体字段合法性
		MessageSendValidator.checkMessageSendTask(mst);
		return messageSendService.saveMessageSendTask(mst);
	}
	/**
	 * 
	 * <p>
	 * 解析EXCEL文件并发送短信<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @param MessageSend mst
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void sendMessageFromExcel() {
		//查询一条未执行的短信任务
		MessageSendTask mst = messageSendService.searchMessageSendTaskForSend();
		//创建用于更新的短信任务实体
		MessageSendTask updateMst = null;		
		if( mst != null && !StringUtils.isEmpty(mst.getId())){
			
			updateMst = new MessageSendTask();
			//设置任务ID
			updateMst.setId(mst.getId());
			updateMst.setBeginDate(new Date());
			//设置任务状态为执行中
			updateMst.setStatus(MessageSendConstance.TASK_STATUS_STARTED);
			//设置接口状态为正常
			updateMst.setIntStatus(MessageSendConstance.IF_INTERFACE_ON);
			//更新任务为执行中
			messageSendService.updateMessageSendTask(updateMst);
			//新建map用于存放解析Excel返回值
			Map<String,Object> map = null;
			//设置第一次读取为真
			boolean firstRead = true;
			//设置是否最后一行为假
			boolean ifLast = false;
			//设置开始行数为0
			int startRow = 0;
			//上一次读取的行数
			int beforeRow = 0;
			//新建详情实体
			List<MessageSendPhone> phoneList = null;
			//设置短信发送实体
			List<SmsInformation> smsList =null;
			//文件路径
			String filePath = mst.getFilePath();
			try{
				//如果不为最后一行
				while(!ifLast){
					//读取Excel文件
					map = MessageSendUtils.readMessagePhoneFile(
						filePath, mst.getStartRow(), firstRead, 
						mst.getId(), MessageSendConstance.READ_MAX_COUNT);
					//设置是否第一次读取为假
					firstRead = false;
					//设置是否为最后一次读取
					ifLast = (Boolean)map.get("ifLast");
					//设置开始行数
					startRow = (Integer)map.get("startRow");
					//保存前一次读取的行数
					beforeRow = mst.getStartRow();
					//重新设置开始行数
					mst.setStartRow(startRow);
					//设置文件路径
					filePath = (String)map.get("filePath");
					//得到读取的号码
					phoneList = (List<MessageSendPhone>)map.get("phoneList");
					//将读取的任务详情实体转换为smsInfomation
					smsList = MessageSendUtils.
							convertMessageSendPhoneToSmsInformation(phoneList, mst);
					try{
						//发送短信
						smsSender.sendSms(smsList);
					}catch(Exception e){
						e.printStackTrace();
						updateMst = new MessageSendTask();
						updateMst.setId(mst.getId());
						updateMst.setStatus(MessageSendConstance.TASK_STATUS_STARTED);
						updateMst.setIntStatus(MessageSendConstance.IF_INTERFACE_OFF);
						updateMst.setFilePath(filePath);
						updateMst.setStartRow(beforeRow);
						messageSendService.updateMessageSendTask(updateMst);
						return;
					}
					//将任务详情插入详情表
					for( MessageSendPhone msp : phoneList){
						messageSendService.saveMessageSendDetail(msp);
					}
					//设置读取行数
					updateMst.setStartRow(mst.getStartRow());
					//设置开始时间为空
					updateMst.setBeginDate(null);
					//更新文件路径
					updateMst.setFilePath(filePath);
					//更新读取行数
					messageSendService.updateMessageSendTask(updateMst);
				}
				updateMst = new MessageSendTask();
				updateMst.setId(mst.getId());
				updateMst.setEndDate(new Date());
				updateMst.setStartRow(startRow);
				updateMst.setStatus(MessageSendConstance.TASK_STATUS_SUCCESS);
				updateMst.setFilePath(filePath);
				messageSendService.updateMessageSendTask(updateMst);
			}catch(Exception e){
				e.printStackTrace();
				updateMst = new MessageSendTask();
				updateMst.setId(mst.getId());
				updateMst.setStatus(MessageSendConstance.TASK_STATUS_FAIL);
				updateMst.setIntStatus(MessageSendConstance.IF_INTERFACE_ON);
				updateMst.setEndDate(new Date());
				messageSendService.updateMessageSendTask(updateMst);
			}
		}
	}
	 /**
	 * 
	 * <p>
	 * 查询全部短信发送任务<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @return List<MessageSendTask>
	 */
	 @Override
	 public Map<String,Object> searchMessageSendTaskAll(int start,int limit){
		 List<MessageSendTask> tasklist = messageSendService.searchMessageSendTaskAll(start, limit);
		 int totalCount = messageSendService.countMessageSendTaskAll();
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("taskList", tasklist);
		 map.put("totalCount", totalCount);
		 return map;
	 }
	 /**
	 * 
	 * <p>
	 * 根据任务ID查询任务详情<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @return List<MessageSendTask>
	 */
	 @Override
	 public Map<String,Object> searchMessageSendDetailByTaskId(String id,int start,int limit){
		 List<MessageSendPhone> phoneList = messageSendService.searchMessageSendDetailByTaskId(id, start, limit);
		 int totalCount = messageSendService.countMessageSendDetailByTaskId(id);
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("phoneList", phoneList);
		 map.put("totalCount", totalCount);
		 return map;
	 }
	 
	 
	 
	public void setMessageSendService(IMessageSendService messageSendService) {
		this.messageSendService = messageSendService;
	}
	public IMessageSendService getMessageSendService() {
		return messageSendService;
	}
	public void setSmsSender(ISmsInfoSender smsSender) {
		this.smsSender = smsSender;
	}
	public ISmsInfoSender getSmsSender() {
		return smsSender;
	}
	
}
