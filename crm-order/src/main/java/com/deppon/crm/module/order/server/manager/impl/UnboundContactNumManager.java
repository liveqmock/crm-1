package com.deppon.crm.module.order.server.manager.impl;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.client.sms.ISmsInfoSender;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IContactManager;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.order.server.manager.IUnboundContactNumManager;
import com.deppon.crm.module.order.server.manager.UnboundContactNumValidator;
import com.deppon.crm.module.order.server.service.IUnboundContactNumService;
import com.deppon.crm.module.order.server.util.Constant;
import com.deppon.crm.module.order.shared.exception.UnboundException;
import com.deppon.crm.module.order.shared.exception.UnboundExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * 
 * @description 联系人编码解绑操作实现类
 * @title UnboundContactNumManager.java
 * @package com.deppon.crm.module.order.server.manager.impl
 * @author 苏玉军
 * @version 0.1 2012-9-8
 */

public class UnboundContactNumManager implements IUnboundContactNumManager {
	// 查询绑定列表失败
	private static final String ERROR_MSG_QUERY = "查询绑定列表失败@UnboundContactNumManager:";
	// 执行解绑操作失败
	private static final String ERROR_MSG_UNBOUND = "执行解绑操作失败@UnboundContactNumManager:";
	// 发送短信抛出异常
	private static final String ERROR_SEND_SMS = "发送短信抛出异常@UnboundContactNumManager";
	// 解绑接口出现异常
	private static final String ERROR_UNBOUND_FAIL = "解绑接口出现异常@UnboundContactNumManager";
	// logger
	private static Logger logger = Logger
			.getLogger(UnboundContactNumManager.class);
	// 解绑提示信息
	private static final String /* smsContent */SMS_CONTENT = "尊敬的德邦客户，您好！"
			+ "您的网上用户名【{0}】与联系人编码【{1}】，" + "在{2}解除了绑定，请核实！"
			+ "若有疑问请致电营业部【{3}】，电话号码【{4}】。";
	// 未绑定合同service
	private IUnboundContactNumService unboundContactNumService;
	// 合同manager
	private IContactManager contactManager;
	// 短信接口
	private ISmsInfoSender smsSender;
	// 客户manager
	private IAlterMemberManager alterMemberManager;

	/**
	 * @return alterMemberManager;
	 */
	public IAlterMemberManager getAlterMemberManager() {
		return alterMemberManager;
	}

	/**
	 * @param alterMemberManager
	 *            : set the property alterMemberManager.
	 */
	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}

	/**
	 * @return unboundContactNumService;
	 */
	public IUnboundContactNumService getUnboundContactNumService() {
		return unboundContactNumService;
	}

	/**
	 * @param unboundContactNumService
	 *            : set the property unboundContactNumService.
	 */
	public void setUnboundContactNumService(
			IUnboundContactNumService unboundContactNumService) {
		this.unboundContactNumService = unboundContactNumService;
	}

	/**
	 * @return contactManager;
	 */
	public IContactManager getContactManager() {
		return contactManager;
	}

	/**
	 * @param contactManager
	 *            : set the property contactManager.
	 */
	public void setContactManager(IContactManager contactManager) {
		this.contactManager = contactManager;
	}

	/**
	 * @return smsSender;
	 */
	public ISmsInfoSender getSmsSender() {
		return smsSender;
	}

	/**
	 * @param smsSender
	 *            : set the property smsSender.
	 */
	public void setSmsSender(ISmsInfoSender smsSender) {
		this.smsSender = smsSender;
	}

	/**
	 * 
	 * @description 根据联系人编码查询联系人及联系人编码绑定信息
	 * @author 苏玉军
	 * @version 0.1 2012-9-8
	 * @param contactNumber
	 *            联系人编码
	 * @return Map<String,Object> 封装查询结果 给Action
	 * @see com.deppon.crm.module.order.server.manager.IUnboundContactNumManager#queryContactAndBoundInfo(java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public Map<String, Object> queryContactAndBoundInfo(String contactNumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		ContactView contactView = new ContactView();
		List<RegisterInfo> boundInfos = new ArrayList<RegisterInfo>();

		// 验证联系人编码是否输入
		UnboundContactNumValidator.isContactNumberNull(contactNumber);

		// 验证联系人是否存在
		contactView = contactManager.getContactByNumber(contactNumber.trim());
		/**
		 * @description :绑定信息在官网查询改为调用客户模块信息查询
		 * @author  kuang
		 * @date 2013-7-9
		 */		
		RegisterInfo registerInfo = new RegisterInfo();
		registerInfo.setCusCode(contactView.getContact().getId());
		UnboundContactNumValidator.isContactViewNull(contactView);
		// 放入map
		map.put("contactView", contactView);
		// 查询绑定列表
		String contactId = contactView.getContact().getId();
		boundInfos=contactManager.queryRegisterInfo(registerInfo);
		// 放入map
		map.put("boundInfos", boundInfos);
		return map;
	}

	/**
	 * 
	 * @Description:绑定信息<br />
	 * @author 苏玉军
	 * @version 0.1 2012-9-11
	 * @return List<RegisterInfo>
	 * @see com.deppon.crm.module.order.server.manager.IUnboundContactNumManager#boundInfos(java.lang.String)
	 */
	@Override
	public List<RegisterInfo> boundInfos(String contactId) {
		/**
		 * @description :绑定用户列表信息在官网查询改为调用客户模块信息查询
		 * @author  kuang
		 * @date 2013-7-9
		 */
			RegisterInfo registerInfo = new RegisterInfo();
			registerInfo.setCusCode(contactId);
			return contactManager.queryRegisterInfo(registerInfo);
	}

	/**
	 * 
	 * @description 根据联系人编码查询联系人信息
	 * @author 苏玉军
	 * @version 0.1 2012-9-11
	 * @param contactNum
	 * @return ContactView
	 * @see com.deppon.crm.module.order.server.manager.IUnboundContactNumManager#queryContactViewByNum(java.lang.String)
	 */
	@Override
	public ContactView queryContactViewByNum(String contactNumber) {
		ContactView contactView = new ContactView();
		// 验证联系人编码是否输入
		UnboundContactNumValidator.isContactNumberNull(contactNumber);
		contactView = contactManager.getContactByNumber(contactNumber);
		// 验证联系人是否存在
		UnboundContactNumValidator.isContactViewNull(contactView);
		// 客户信息
		Member member = alterMemberManager.getMemberById(contactView
				.getContact().getCustId());
		contactView.getContact().setMember(member);
		return contactView;
	}

	/**
	 * 
	 * @description 解绑操作
	 * @author 苏玉军
	 * @version 0.1 2012-9-8
	 * @param contact
	 *            联系人
	 * @param channelId
	 *            渠道用户Id
	 * @param source
	 *            订单来源，渠道Id来源
	 * @return boolean 解绑结果
	 * @see com.deppon.crm.module.order.server.manager.IUnboundContactNumManager#unboundContactNumber(java.lang.String,
	 *      java.lang.String)
	 */
	@Transactional
	@Override
	public boolean unboundContactNumber(String contactNumber,
			String registerId, String source) {
		ContactView cv = unboundContactNumService
				.queryContactViewByContactNum(contactNumber);
		// 联系人
		Contact toContact = cv.getContact();
		// 检查联系人对象是否为空
		UnboundContactNumValidator.isContactNull(toContact);
		// 检查手机号
		UnboundContactNumValidator.isNullContactMobilePhone(toContact);
		// 检查用户名
		UnboundContactNumValidator.isRegisterIdNull(registerId);
		// toContact.setId(contact.getId());
		toContact = this.setChannelSourceToNull(toContact, source);
		/**
		 * @description :解绑官网的用户，同步到官网
		 * @author  kuang
		 * @date 2013-7-9
		 */
		RegisterInfo registerInfo=new RegisterInfo();
		registerInfo.setCusCode(cv.getContact().getId());
		registerInfo.setCustsource(source);
		registerInfo.setUserName(registerId);		
		
		try {
			//调用客户模块方法解绑
			contactManager.unboundContact(registerInfo);
			//传id 官网解绑
			//contactManager.unboundContact(registerInfoList);
			if(Constant.ORDER_SOURCE_ONLINE.equals(source)){
				boolean isSuccess = unboundContactNumService.unboundContactNum(
					toContact, registerId);		
			if (!isSuccess) {
				// 定义异常 “未绑定”
				UnboundException e = new UnboundException(
						UnboundExceptionType.UNBOUND_FAIL);
				// 日志记录异常信息
				logger.info(ERROR_UNBOUND_FAIL, e);
				// 抛出异常信息
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
					private static final long serialVersionUID = -7224125280775351584L;
				};
			}
			}
			// 发送短信
			User user = (User) UserContext.getCurrentUser();
			sendSmsToContact(toContact, user, registerId);
		} catch (CrmBusinessException e) {
			// 抛出异常
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
				private static final long serialVersionUID = -7224125280775351584L;
			};
		}
		return true;
	}

	/**
	 * 
	 * @Description 设置渠道为null
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @param contact
	 *            联系人
	 * @param source
	 *            订单来源
	 * @return Contact
	 */
	private Contact setChannelSourceToNull(Contact contact, String source) {
		// 验证合同与来源
		if (contact == null || StringUtils.isEmpty(source)) {
			return null;
		}
		
		return contact;
	}

	/**
	 * 
	 * 
	 * @descption 解绑成功发送短信
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @param mobilePhone
	 *            void
	 * @throws CrmBusinessException
	 */
	@Override
	public void sendSmsToContact(Contact contact, User user, String registerId)
			throws CrmBusinessException {
		// 日期格式
		String formatString = "yyyy年MM月dd日HH:mm";
		// 定义转换格式
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
		Date date = new Date();
		// 日期格式转换
		String formatDateStr = dateFormat.format(date);
		// 登录用户的部门名称
		String deptName = user.getEmpCode().getDeptId().getDeptName();
		// 合同号
		String contactNum = contact.getNumber();
		// 登录用户的电话
		String deptPhone = user.getEmpCode().getDeptId().getPhone();
		// 信息内容
		String content = MessageFormat.format(SMS_CONTENT, new Object[] {
				registerId, contactNum, formatDateStr, deptName, deptPhone });

		// 合同手机电话验证
		UnboundContactNumValidator.isNullContactMobilePhone(contact);
		SmsInformation smsInfo = new SmsInformation();
		// 部门标杆编码获得
		String deptStandardCode = user.getEmpCode().getDeptId()
				.getStandardCode();
		// 发送短信人员功耗
		String empCode = user.getEmpCode().getEmpCode();
		// 设置手机号码
		smsInfo.setMobile(contact.getMobilePhone());
		// 设置短信内容
		smsInfo.setMsgContent(content);
		// 设置标杆编码
		smsInfo.setSendDept(deptStandardCode);
		// 设置发送人员工号
		smsInfo.setSender(empCode);
		// 设置业务类型
		smsInfo.setMsgType(com.deppon.crm.module.client.common.util.Constant.SMS_CONTACT_CODE);
		// 发送
		smsSender.sendSms(smsInfo);
	}
}
