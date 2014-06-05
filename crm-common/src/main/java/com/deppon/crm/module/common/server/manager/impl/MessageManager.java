package com.deppon.crm.module.common.server.manager.impl;

import java.util.List;

import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.server.service.IMessageService;
import com.deppon.crm.module.common.server.util.Constant;
import com.deppon.crm.module.common.shared.domain.Message;

public class MessageManager implements IMessageManager {

	private IMessageService messageService;

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	@Override
	public List<Message> getMeaasge(String userid) {

		return messageService.getMeaasge(userid);
	}

	public void addMessage(Message message) {
		messageService.addMessage(message);
	}

	public void addSuperMessage(String superMsg) {
		messageService.addSuperMessage(superMsg,
				Constant.TASK_TYPE_SUPER_MESSAGE);
	}

	@Override
	public List<Message> getMeaasgeByUserAndDept(String userid, String deptId) {
		return messageService.getMeaasgeByUserAndDept(userid, deptId);
	}

	@Override
	public void deleteMessage(String id) {
		messageService.deleteMessage(id);
	}

	@Override
	public void deleteMessageByType(String type) {
		messageService.deleteMessageByType(type);
	}

	@Override
	public void deleteMessageByInvalid(String type) {
		messageService.deleteMessageByInvalid(type);
	}

	@Override
	public void addMessageList(List<Message> messageList) {
		messageService.addMessageList(messageList);
	}

	// ---------------------------新--------------------------------------

	@Override
	public List<Message> getMeaasgeByUserAndDeptExceptMessage(String userid,
			String deptId) {
		return messageService.getMeaasgeByUserAndDeptExceptMessage(userid,
				deptId);
	}

	@Override
	public List<Message> getMeaasgeByUserAndDeptOnlyOrder(String userid,
			String deptId) {
		return messageService.getMeaasgeByUserAndDeptOnlyOrder(userid,
				deptId);
	}

	@Override
	public List<Message> getMeaasgeByUserAndDeptOnlyMessage(String userid,
			String deptId, int start, int limit) {
		return messageService.getMeaasgeByUserAndDeptOnlyMessage(userid,
				deptId, start, limit);
	}

	@Override
	public Integer getCountMessage(String userid, String deptId) {
		return messageService.getCountMessage(userid, deptId);
	}
	
	@Override
	public void deleteMessages(List<String> messageIds) {
		messageService.deleteMessages(messageIds);
	}

	@Override
	public List<Message> getMessageForErp() {
		return messageService.getMessageForErp();
	}
	@Override
	public Long seacherExceptOrderCount(String userid, String deptId) {
		return messageService.seacherExceptOrderCount(userid, deptId);
	}

	@Override
	public Long seacherMsgInfoCount(String userid, String deptId) {
		return messageService.seacherMsgInfoCount(userid, deptId);
	}

	@Override
	public Long seacherOrderCount(String userid, String deptId) {
		return messageService.seacherOrderCount(userid, deptId);
	}

}
