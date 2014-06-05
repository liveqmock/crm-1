package com.deppon.crm.module.marketing.server.utils;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.deppon.crm.module.marketing.shared.domain.MessageSendConstance;
import com.deppon.crm.module.marketing.shared.domain.MessageSendTask;
import com.deppon.crm.module.marketing.shared.exception.MessageSendException;
import com.deppon.crm.module.marketing.shared.exception.MessageSendExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

public class MessageSendValidator {
	/**
	 * 
	 * <p>
	 * 校验手机号是否合法<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @param String phoneNum
	 * @return String
	 */
	public static String checkPhoneNumber(String phoneNum){
		//如果字符串为空
		if(StringUtils.isEmpty(phoneNum)){
			//手机号不合法
			return MessageSendConstance.PHONE_VALIDATE_INVALID;
		}
		//如果手机号是11位或8位数字则
		if(phoneNum.matches("(^1\\d{10}$)|(^\\d{8}$)")){
			//校验合法
			return MessageSendConstance.PHONE_VALIDATE_VALIDITY;
		}
		//校验合法
		return MessageSendConstance.PHONE_VALIDATE_INVALID;
	}
	/**
	 * 
	 * <p>
	 * 营销短信发送任务是否合法<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @param String phoneNum
	 * @return String
	 */
	@SuppressWarnings("serial")
	public static void checkMessageSendTask(MessageSendTask mst){
		//如果实体为空则
		if( mst == null ){
			MessageSendException e1 = new MessageSendException(MessageSendExceptionType.messageSendTaskIsNull);
			//抛出异常
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}
		//文件名不能为空 
		if( StringUtils.isEmpty(mst.getFileName())){
			MessageSendException e1 = new MessageSendException(MessageSendExceptionType.messageSendTaskFileNameIsNull);
			//抛出异常
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}		
		File file = new File(mst.getFilePath());
		//文件路径错误
		if( !file.exists()){
			MessageSendException e1 = new MessageSendException(MessageSendExceptionType.messageSendTaskFilePathError);
			//抛出异常
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}
		//如果任务状态不为0123则
		if( StringUtils.isEmpty(mst.getStatus()) || !mst.getStatus().matches("^[0123]$")){
			MessageSendException e1 = new MessageSendException(MessageSendExceptionType.messageSendTaskStatusError);
			//抛出异常
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}
		//如果发送部门或员工信息为不为空
		if( StringUtils.isEmpty(mst.getSendDept())||StringUtils.isEmpty(mst.getSendUser())){
			MessageSendException e1 = new MessageSendException(MessageSendExceptionType.messageSendTaskStatusError);
			//抛出异常
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}
		//如果短信内容为空或者短信内容大于140个字
		if( StringUtils.isEmpty(mst.getMsgContent()) || 
				mst.getMsgContent().length()>MessageSendConstance.MAX_MSGCONTENT_LENGTH){
			MessageSendException e1 = new MessageSendException(MessageSendExceptionType.messageSendMaxMsgContentError);
			//抛出异常
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}
	}
}
