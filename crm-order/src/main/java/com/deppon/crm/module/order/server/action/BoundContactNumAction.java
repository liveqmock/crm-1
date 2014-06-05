package com.deppon.crm.module.order.server.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IContactManager;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.crm.module.order.server.manager.IUnboundContactNumManager;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;

/**
 * <p>
 * 积分 绑定联系人编码action<br />
 * </p>
 * 
 * @title BoundContactNumAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-4-21
 */

public class BoundContactNumAction extends AbstractAction {
	// 联系人manager
	private IContactManager contactManager;
	// 订单manager
	private IOrderManager orderManager;
	//unboundContactNumManager
	private IUnboundContactNumManager unboundContactNumManager;
	//alterMemberManager
	private IAlterMemberManager alterMemberManager;

	// 订单号
	private String orderNumber;
	// 联系人编码
	private String contactNumber;
	// 通过联系人编码查询联系人信息
	// private List<Contact> contactInfoList = new ArrayList<Contact>();
	private List<ContactView> contactInfoList = new ArrayList<ContactView>();
	// 用户Id
	private String custId;
	// 发送验证短信是否成功
	private boolean operateSuccess;
	// 用户注册信息
	private RegisterInfo registerInfo;
	
	// 订单信息
	private Order order;
	
	//联系人
	private ContactView contactView;
	
	//RegisterInfo列表
	private List<RegisterInfo> listRegisterInfo;
	
	// 官网注册人手机号
	private String registMobilePhone;

	// 订单来源
	private String orderSource;

	// 提示信息
	private String message;
	
	// BEAN获得国际化对象
	private IMessageBundle messageBundle;
	
	/**
	 *@return  registerInfo;
	 */
	public RegisterInfo getRegisterInfo() {
		return registerInfo;
	}
	/**
	 *@return  order;
	 */
	public Order getOrder() {
		return order;
	}
	/**
	 *@return  contactView;
	 */
	public ContactView getContactView() {
		return contactView;
	}
	
	/**
	 * @param contactView : set the property contactView.
	 */
	public void setContactView(ContactView contactView) {
		this.contactView = contactView;
	}
	
	/**
	 *@return  listRegisterInfo;
	 */
	public List<RegisterInfo> getListRegisterInfo() {
		return listRegisterInfo;
	}
	/**
	 * @param listRegisterInfo : set the property listRegisterInfo.
	 */
	public void setListRegisterInfo(List<RegisterInfo> listRegisterInfo) {
		this.listRegisterInfo = listRegisterInfo;
	}

	/**
	 * @param registMobilePhone : set the property registMobilePhone.
	 */
	public void setRegistMobilePhone(String registMobilePhone) {
		this.registMobilePhone = registMobilePhone;
	}
	/**
	 * @param orderSource : set the property orderSource.
	 */
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	/**
	 *@return  message;
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param messageBundle : set the property messageBundle.
	 */
	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}

	/**
	 * 
	 * <p>
	 * 网上信息搜索<br />
	 * </p>
	 * 
	 * @author 李学兴
	 * @version 0.2 2012-4-28
	 * @return String
	 */
	public String searchWebInfo() {
		@SuppressWarnings("rawtypes")
		Map map = orderManager.getOrderAndRegInfoByOrderNum(orderNumber.trim());
		order = (Order) map.get("order");
		registerInfo = (RegisterInfo) map.get("registerInfo");
		String isBound = (String) map.get("isBound");
		if (registerInfo != null && registerInfo.getCusCode() != null
				&& !"".equals(registerInfo.getCusCode().trim())&&
						isBound != null && isBound.equals("YES")) {
				Contact contact = alterMemberManager.getContact(registerInfo.getCusCode());
				message = messageBundle.getMessage(getLocale(),
						"i18n.order.thisOrderAndConnect")
						+ contact.getNumber()
						+ messageBundle.getMessage(getLocale(),
								"i18n.order.hasBound");
		}
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * 联系人信息搜索<br />
	 * </p>
	 * 
	 * @author 李学兴
	 * @version 0.2 2012-4-28
	 * @return String
	 */
	public String searchContactInfo() {
		ContactView contactView = contactManager
				.getContactByNumber(contactNumber);
		if (contactView != null) {
			contactInfoList.add(contactView);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * 发送验证短信<br />
	 * </p>
	 * 
	 * @author 李学兴
	 * @version 0.2 2012-4-28
	 * @return String
	 */
	public String sendCheckMessage() {
		operateSuccess = contactManager.sendValidatorMsg(contactNumber,
				registMobilePhone);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * 绑定联系人编码<br />
	 * </p>
	 * 
	 * @author 李学兴
	 * @version 0.2 2012-4-28
	 * @return String
	 */
	public String boundContactNum() {
		Map map = orderManager.getOrderAndRegInfoByOrderNum(orderNumber);
		RegisterInfo info = (RegisterInfo) map.get("registerInfo");
		operateSuccess = contactManager.boundContactForWeb(orderSource, custId,contactNumber,info.getTelephone());
		if (operateSuccess) {
			ContactView contactView = contactManager.getContactByNumber(contactNumber);
			if (contactView != null) {
				order = orderManager.getOrderByNumber(orderNumber.trim());
				Order ord = new Order();
				ord.setId(order.getId());
				//客户ID
				ord.setShipperId(contactView.getContact().getCustId());
				//联系人id
				ord.setContactManId(contactView.getContact().getId());
				//客户名字
				ord.setShipperName(contactView.getCustName());
				//客户编码
				ord.setShipperNumber(contactView.getCustNumber());
				orderManager.updateOrderNoValidate(ord);
			}
		}
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * 联系人编码解除绑定 <br />
	 * </p>
	 * 
	 * @author 张登
	 * @version 0.2 2012-4-28
	 * @return String
	 */
	public String unboundContactNumber(){
		unboundContactNumManager.unboundContactNumber(contactNumber, custId,orderSource);
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * <p>
	 * 联系人编码解除绑定 查询用户<br />
	 * </p>
	 * 
	 * @author 张登
	 * @version 0.2 2012-4-28
	 * @return String
	 */
	public String boundInfos(){
		listRegisterInfo = unboundContactNumManager.boundInfos(custId);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * 联系人编码解除绑定 查询联系人<br />
	 * </p>
	 * 
	 * @author 张登
	 * @version 0.2 2012-4-28
	 * @return String
	 */
	public String queryContactViewByNum(){
		contactView = unboundContactNumManager.queryContactViewByNum(contactNumber);
		return SUCCESS;
	}
	
	/**
	 * @param contactManager : set the property contactManager.
	 */
	public void setContactManager(IContactManager contactManager) {
		this.contactManager = contactManager;
	}

	/**
	 * @param orderNumber : set the property orderNumber.
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @param contactNumber : set the property contactNumber.
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @param custId : set the property custId.
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 * @param orderManager : set the property orderManager.
	 */
	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}
	
	/**
	 *@return  operateSuccess;
	 */
	public boolean isOperateSuccess() {
		return operateSuccess;
	}
	
	/**
	 *@return  contactInfoList;
	 */
	public List<ContactView> getContactInfoList() {
		return contactInfoList;
	}
	
	/**
	 * @param unboundContactNumManager : set the property unboundContactNumManager.
	 */
	public void setUnboundContactNumManager(
			IUnboundContactNumManager unboundContactNumManager) {
		this.unboundContactNumManager = unboundContactNumManager;
	}

	/**
	 * @param alterMemberManager : set the property alterMemberManager.
	 */
	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}
	
}
