package com.deppon.crm.module.customer.server.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.ICustomerOperate;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.customer.server.dao.IAlterMemberDao;
import com.deppon.crm.module.customer.server.dao.IContactDao;
import com.deppon.crm.module.customer.server.dao.IMemberDao;
import com.deppon.crm.module.customer.server.dao.IMemberIntegralDao;
import com.deppon.crm.module.customer.server.manager.CustomerRule;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.server.utils.ApproveDataUtil;
import com.deppon.crm.module.customer.server.utils.Assert;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Member4All;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberExtend;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.MyToDoWorkFlow;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.CustomerExceptionType;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;
import com.deppon.crm.module.custrepeat.server.dao.IRepeatedCustDao;

/**
 * @作者：罗典
 * @时间：2012-3-24
 * @描述：修改会员模块service层
 * */
public class AlterMemberService implements IAlterMemberService {
	private IAlterMemberDao alterMemberDao;
	private IMemberDao memberDao;
	private IMemberIntegralDao memberIntegralDao;
	private ICustomerOperate customerOperate;
	private IContactDao contactDao;
	private IRepeatedCustDao repeatedCustDao;
	/**
	 *@author chenaichun
	 * @date 2014年4月10日 下午2:43:10 
	 * @param repeatedCustDao the repeatedCustDao to set
	 */
	public void setRepeatedCustDao(IRepeatedCustDao repeatedCustDao) {
		this.repeatedCustDao = repeatedCustDao;
	}
	/**
	 * @作者：罗典
	 * @时间：2012-10-11
	 * @描述：根据联系人编码查询联系人信息
	 * @参数：number 联系人编码
	 * @返回值：Contact 联系人信息
	 * */
	public Contact getContactByNum(String number){
		return alterMemberDao.getContactByNum(number);
	}
	/**
	 * @作者：罗典
	 * @时间：2012-4-19
	 * @描述：根据联系人ID查询会员信息,及是否为（月结）合同客户(存入是否允许联系人兑换积分字段中返回)
	 * @参数：linkmanId 联系人Id,部门ID
	 * @返回值：Member 包含联系人信息的会员信息
	 * */
	public Member getMemberBylinkmanId(String linkmanId, String deptId) {
		return alterMemberDao.getMemberBylinkmanId(linkmanId, deptId);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：保存审批数据信息
	 * @参数：ApproveDataList 审批数据集合
	 * @返回值：boolean 是否保存成功
	 * */
	public void saveApproveData(List<ApproveData> approveDataList, String logId) {
		for (ApproveData approveData : approveDataList) {
			approveData.setMemberOperationLogId(logId);
		}
		alterMemberDao.saveApproveData(approveDataList);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据类名和字段名查询审批类型
	 * @参数：className 类名 fieldName 字段名
	 * @返回值： String 工作流审批类型
	 * */
	public String getApproveType(String className, String fieldName) {
		return alterMemberDao.getApproveType(className, fieldName);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：工作流号查询审批数据
	 * @参数：workFlowId 工作流ID
	 * @返回值： List<ApproveData> 审批数据集合
	 * */
	public List<ApproveData> searchApproveData(String workFlowId) {
		return alterMemberDao.searchApproveData(workFlowId);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据会员ID修改会员状态
	 * @参数 id 会员ID,status 会员状态
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean alterMemberStatus(String id, String status) {
		return alterMemberDao.alterMemberStatus(id, status);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据条件查询会员信息
	 * @参数 无
	 * @返回值 会员集合
	 * */
	public List<MemberResult> searchMember(MemberCondition condition) {
		return alterMemberDao.searchMember(condition);
	}
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据条件查询会员信息
	 * @参数 无
	 * @返回值 会员集合
	 * */
	@Override
	public List<MemberResult> searchArriveScatterMember(MemberCondition condition) {
		return alterMemberDao.queryArriveScatterMember(condition);
	}
	public List<MemberResult> searchMemberListFor360(MemberCondition condition) {
		Assert.notNull(condition, "condition must not null");
		return alterMemberDao.searchMemberListFor360(condition);
	}
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据条件查询会员信息
	 * @参数 无
	 * @返回值 会员集合
	 * */
	@Override
	public List<MemberResult> searchMemberWithAuth(MemberCondition condition) {
		return alterMemberDao.searchMemberWithAuth(condition);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据审批数据修改会员相关信息
	 * @参数： 审批数据集合 isNeedUpdate 是否修改数据 true 需修改，false为还原
	 * @返回值：是否修改成功
	 * */
	public boolean updateMemberInfo(List<ApproveData> approveDataList,
			boolean isNeedUpdate) throws MemberException {
		if (approveDataList.size() <= 0) {
			return false;
		}
		// 需修改的审批数据
		List<ApproveData> updateApproveDataList = new ArrayList<ApproveData>();
		// 需新增的审批数据
		List<ApproveData> addApproveDataList = new ArrayList<ApproveData>();
		// 需删除的审批数据
		List<ApproveData> delteApproveDataList = new ArrayList<ApproveData>();
		// 过滤审批数据
		for (ApproveData ad : approveDataList) {
			if (ad.getHandleType().equals(Constant.APPROVEDATA_INSERT)) {
				addApproveDataList.add(ad);
			} else if (ad.getHandleType().equals(Constant.APPROVEDATA_DELETE)) {
				delteApproveDataList.add(ad);
			} else if (ad.getHandleType().equals(Constant.APPROVEDATA_UPDATE)) {
				updateApproveDataList.add(ad);
			} else {
				throw new MemberException(
						MemberExceptionType.ApproveDataHandTypeError);
			}
		}
		Member member = this.getMemberByApproveDataList(approveDataList);
		// 修改数据
		if (isNeedUpdate && updateApproveDataList.size() > 0) {
			this.updatePojoInfo(updateApproveDataList);
			//修改的信息包括疑重被标记的客户则去掉疑重标记
			if(isUpdateRepeatedInfo(member,updateApproveDataList)){
				repeatedCustDao.deleteMarkCustByCustId(member.getId());
			}
		}
		// 新增数据
		if (addApproveDataList.size() > 0) {
			this.updatePojoStatus(addApproveDataList, isNeedUpdate ? "0" : "2");
			//修改的信息包括疑重被标记的客户则去掉疑重标记
			if(isUpdateRepeatedInfo(member,addApproveDataList)){
				repeatedCustDao.deleteMarkCustByCustId(member.getId());
			}
		}
		// 失效数据
		if (isNeedUpdate && delteApproveDataList.size() > 0) {
			this.updatePojoStatus(delteApproveDataList, "2");
			//修改的信息包括疑重被标记的客户则去掉疑重标记
			if(isUpdateRepeatedInfo(member,delteApproveDataList)){
				repeatedCustDao.deleteMarkCustByCustId(member.getId());
			}
		}
		return true;
	}
	/**
	 * 
	* @Title: isUpdateRepeatedInfo
	* @Description: 判断是否修改了疑重的关键信息，企业客户名称、固定电话 、银行账号(即判断审批数据中是否有这些关键信息)
	* @author chenaichun 
	* @param @param member
	* @param @param approveDataList
	* @param @return    设定文件
	* @date 2014年4月10日 下午2:40:26
	* @return boolean    返回类型
	* @throws
	* @update 2014年4月10日 下午2:40:26
	 */
	private boolean isUpdateRepeatedInfo(Member member,List<ApproveData> approveDataList){
		//判断是否修改了疑重的关键信息，企业客户名称、固定电话 、银行账号(即判断审批数据中是否有这些关键信息)
		for(ApproveData appData:approveDataList){
			if(member.getCustType().equals(Constant.Enterprise_Member)
					&&appData.getFieldName().equals("custName")){
				return true;
			}
			if(appData.getFieldName().equals("telPhone")){
				return true;
			}
			if(appData.getFieldName().equals("bankAccount")){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	* @Title: getMemberByApproveDataList
	* @Description: 根据审批数据获得修改的客户实体
	* @author chenaichun 
	* @param @param approveDataList
	* @param @return    设定文件
	* @date 2014年4月10日 下午2:16:02
	* @return Member    返回类型
	* @throws
	* @update 2014年4月10日 下午2:16:02
	 */
	private Member getMemberByApproveDataList(List<ApproveData> approveDataList){
		ApproveData ad = approveDataList.get(0);
		String memberId = "";
		Member mem = null;
		if (ad.getClassName().equals(Member.class.getSimpleName())) {
			memberId = ad.getClassId();
			mem = alterMemberDao.getMember(memberId);
			return mem;
		} else if (ad.getClassName().equals(Contact.class.getSimpleName())) {
			Contact contact = alterMemberDao.getContact(ad.getClassId());
			memberId = contact.getCustId();
			mem = alterMemberDao.getMember(memberId);
			return mem;
		} else if (ad.getClassName().equals(
				ShuttleAddress.class.getSimpleName())) {
			ShuttleAddress shuttleAddress = alterMemberDao.getShuttleAddress(ad.getClassId());
			memberId = shuttleAddress.getMemberId();
			mem = alterMemberDao.getMember(memberId);
			return mem;
		} else if (ad.getClassName().equals(
				PreferenceAddress.class.getSimpleName())) {
			PreferenceAddress pa = alterMemberDao.getPreferenceAddress(ad
					.getClassId());
			Contact contact = alterMemberDao.getContact(pa.getLinkManId());
			memberId = contact.getCustId();
			mem = alterMemberDao.getMember(memberId);
			return mem;
		} else if (ad.getClassName().equals(Account.class.getSimpleName())) {
			Account account = alterMemberDao.getAccount(ad.getClassId());
			memberId = account.getBelongcustom();
			mem = alterMemberDao.getMember(memberId);
			return mem;
		}else if (ad.getClassName().equals(MemberExtend.class.getSimpleName())) {
			MemberExtend memberExtend = alterMemberDao.getMemberExtendInfo(ad.getClassId());
			memberId = memberExtend.getCustId();
			mem = alterMemberDao.getMember(memberId);
			return mem;
		}
		return null;
	}
	/**
	 * @作者：罗典
	 * @时间：2012-3-28
	 * @描述：根据需删除审批数据作废实体数据
	 * @参数：approveDataList 需启用或作废的审批数据
	 * */
	private void updatePojoStatus(List<ApproveData> approveDataList,
			String status) throws MemberException {
		//TODO 待优化 此方法是根据需删除审批数据作废实体数据，只需作废或者删除一次就可。不必总是循环approveDataList 去操作，
		//多了很多次没必要的循环
		for (ApproveData ad : approveDataList) {
			// 联系人信息
			if (ad.getClassName().equals(Contact.class.getSimpleName())) {
				alterMemberDao.alterContactStatus(ad.getClassId(), status);
				Contact contact = alterMemberDao
						.getContact(ad.getClassId());
				// 新增联系人时需反馈信息到潜散客信息中
				// 当状态为0 时 联系人才创建成功 才会反写 对应的潜散客数据
				if (status.equals("0")) {
					//回写会员里面的联系人id
					if(contact.getIsMainLinkMan()){
						Member member = new Member();
						member.setId(contact.getCustId());
						member.setContactId(contact.getId());
						alterMemberDao.updateMember(member);
					}
				}  else if (Constant.Status_NoEfficacy.equals(status)) {
					try {
					customerOperate.bindToContact(contact.getId(), null);
					contactDao.unboundContactOnlineNum(contact.getId());
					} catch (CrmBusinessException e) {
						// 确定的提示某个联系人编码con.getNumber()解绑时报错
						throw new CustomerException(CustomerExceptionType.ContactUnBoundError,new Object[]{contact.getNumber()});
					}
				}			
			}
			// 接送货地址信息
			else if (ad.getClassName().equals(
					ShuttleAddress.class.getSimpleName())) {
				alterMemberDao.alterShuttleAddressStatus(ad.getClassId(),
						status);
			}
			// 账号信息
			else if (ad.getClassName().equals(Account.class.getSimpleName())) {
				alterMemberDao.alterAccountStatus(ad.getClassId(), status);
			}
			// 偏好地址信息
			else if (ad.getClassName().equals(
					PreferenceAddress.class.getSimpleName())) {
				alterMemberDao.alterPreferenceAddressStatus(ad.getClassId(),
						status);
				//扩展信息没有状态的改变，所以直接跳出本次循环就ok
			} else if(ad.getClassName().equals(
					MemberExtend.class.getSimpleName())){
				continue;
			}else{
				throw new MemberException(MemberExceptionType.HandlePojoError);
			}

		}
	}

	@Override
	public void deleteMember(Member member) {
		if(ValidateUtil.objectIsEmpty(member)||StringUtils.isEmpty(member.getId())){
			throw new CustomerException(CustomerExceptionType.IdIsNull);
		}
		// 作废联系人（原来的方法会得到所有的联系人（包括作废的）），在manager层删除时校验了客户不为审批中
		//该查询只会得到有效和审批中的
		List<Contact> contactList = alterMemberDao.queryContactAndPrefByMemberId(member.getId());
		if (contactList != null && contactList.size() > 0) {
			for (Contact con : contactList) {
				//manager层（delete方法）将审批中的过滤了，所以再排除掉无效的就可以，TODO 这个无效的校验可以去掉了
				if (!Constant.Status_NoEfficacy.equals(con.getStatus())) {
					operateDateChange(alterMemberDao.alterContactStatus(con.getId(), Constant.Status_NoEfficacy,-1));
					/**
					 * @descrition
					 * @author pgj
					 * @data 2013-07-03
					 */
					contactDao.unboundContactOnlineNum(con.getId());
					// 解绑联系人编码
					try {
						customerOperate.bindToContact(con.getId(), null);
					} catch (CrmBusinessException e) {
						
						// 确定的提示某个联系人编码con.getNumber()解绑时报错
						throw new CustomerException(CustomerExceptionType.ContactUnBoundError,new Object[]{con.getNumber()});
					}
				}
				//作废联系人偏好地址
				if(!CollectionUtils.isEmpty(con.getPreferenceAddressList())){
					for(PreferenceAddress pre : con.getPreferenceAddressList()){
						if (!Constant.Status_NoEfficacy.equals(pre.getStatus())) {
							operateDateChange(alterMemberDao.alterPreferenceAddressStatus(pre.getId(), Constant.Status_NoEfficacy,-1));
						}
					}
				}
			}
		}
		// 作废接送货地址
		List<ShuttleAddress> shuttleAddressList = alterMemberDao.searchShuttleAddressByMemberId(member.getId());
		if (shuttleAddressList != null && shuttleAddressList.size() > 0) {
			for(ShuttleAddress address : shuttleAddressList){
				if (!Constant.Status_NoEfficacy.equals(address.getStatus())) {
					operateDateChange(alterMemberDao.alterShuttleAddressStatus(address.getId(), Constant.Status_NoEfficacy,-1));
				}
			}
		}
		// 作废会员
		operateDateChange(alterMemberDao.alterMemberStatus(member.getId(), Constant.Status_NoEfficacy,member.getVersionNumber()));
	}
	private void operateDateChange(boolean flag){
		if(!flag){
			throw new CustomerException(CustomerExceptionType.OperateDateChange);
		}
	}
	/**
	 * @作者：罗典
	 * @时间：2012-3-28
	 * @描述：根据审批数据修改数据
	 * @参数：approveDataList 需修改的审批数据
	 * */
	private void updatePojoInfo(List<ApproveData> approveDataList)
			throws MemberException {
		// 会员信息
		Map<String, Member> memberMap = new HashMap<String, Member>();
		// 账号信息
		Map<String, Account> accountMap = new HashMap<String, Account>();
		// 联系人信息
		Map<String, Contact> contactMap = new HashMap<String, Contact>();
		// 偏好地址信息
		Map<String, PreferenceAddress> preferenceAddressMap = new HashMap<String, PreferenceAddress>();
		// 接送货地址信息
		Map<String, ShuttleAddress> shuttleAddressMap = new HashMap<String, ShuttleAddress>();
		//客户扩展信息
		Map<String, MemberExtend> memberExtendMap = new HashMap<String, MemberExtend>();
		//是否修改客户类型
		boolean isAlterCustType = false;
		//是否修改 是否是主联系人 字段
		boolean isAlterIsMainLinkMan = false;
		// 通过反射将值注入到各实体中
		for (ApproveData ad : approveDataList) {
			// 会员信息封装
			if (ad.getClassName().equals("Member")) {
				putPojoToMap(memberMap, ad);
			}
			// 联系人信息封装
			else if (ad.getClassName().equals("Contact")) {
				putPojoToMap(contactMap, ad);
			}// 账号信息封装
			else if (ad.getClassName().equals("Account")) {
				putPojoToMap(accountMap, ad);
			}// 接送货地址信息封装
			else if (ad.getClassName().equals("ShuttleAddress")) {
				putPojoToMap(shuttleAddressMap, ad);
			}// 偏好地址信息封装
			else if (ad.getClassName().equals("PreferenceAddress")) {
				putPojoToMap(preferenceAddressMap, ad);
			}else if(ad.getClassName().equals("MemberExtend")){
				putPojoToMap(memberExtendMap, ad);
			}
			
			//如果修改字段为custType，表示会员修改对应的客户类型
			if("custType".equals(ad.getFieldName())){
				isAlterCustType = true;
			}
			//如果修改了是否为主联系人,联系人的客户属性 字段应跟会员客户属性一样
			if ("isMainLinkMan".equals(ad.getFieldName())) {
				isAlterIsMainLinkMan = true;
			}
		}
		// 修改会员信息
		for (Member member : memberMap.values()) {
			// 设置会员状态为正常
			member.setCustStatus("0");
			alterMemberDao.updateMemberAllInfo(member);
			
			/**
			 * @author 李盛
			 * @since 2012-9-19
			 * @描述：联系人未修改时，会员修改时维护对应联系人的所属客户的客户类型
			 */
			if(isAlterCustType && (contactMap.values() == null || contactMap.values().size() == 0)){
				alterMemberDao.updateLinkmanCustType(member);
			}
		}
		// 修改联系人信息
		for (Contact contact : contactMap.values()) {
			/**
			 * @author 李国文
			 * @since 2012-12-26
			 * @描述：会员修改时维护对应zhu联系人的所属客户的客户类型
			 */
			if (isAlterIsMainLinkMan && contact.getIsMainLinkMan() ) {
				contact.setBelongCustType(contact.getMember().getCustType());
			}
			alterMemberDao.updateContactAllInfo(contact);
			// 当修改联系人手机或固话时需反馈信息至潜散客信息中
//			String phone = contact.getMobilePhone();
//			String tel = contact.getTelPhone();
//			String name = contact.getName();
//
//			if (!ValidateUtil.objectIsEmpty(phone)
//					|| !ValidateUtil.objectIsEmpty(tel)) {
//				Contact oldContact = alterMemberDao.getContact(contact.getId());
//				String custId = oldContact.getCustId();
//				if (!ValidateUtil.objectIsEmpty(tel)
//						&& ValidateUtil.objectIsEmpty(name)) {
//					name = oldContact.getName();
//				}
//
//				// 反馈状态到潜散客信息中
//				scatterCustomerService.memberUpgradeWriteBackCustomer(phone,
//						tel, name, custId, Constant.Upgrade_Member,
//						Constant.Customer_Upgrade_Member);
//			}
			//回写会员里面的联系人id
			if(null!=contact.getIsMainLinkMan()&&contact.getIsMainLinkMan()){
				Contact oldContact = alterMemberDao.getContact(contact.getId());
				String custId = oldContact.getCustId();
				Member member = new Member();
				member.setId(custId);
				member.setContactId(contact.getId());
				alterMemberDao.updateMember(member);
			}
			}
		// 修改接送货地址信息
		for (ShuttleAddress shuttleAddress : shuttleAddressMap.values()) {
			alterMemberDao.updateShuttleAddressAllInfo(shuttleAddress);
		}
		// 修改账号信息
		for (Account account : accountMap.values()) {
			alterMemberDao.updateAccountAllInfo(account);
		}
		// 修改偏好地址
		for (PreferenceAddress preferenceAddress : preferenceAddressMap
				.values()) {
			alterMemberDao.updatePreferenceAddressAllInfo(preferenceAddress);
		}
		for(MemberExtend memberExtend :memberExtendMap.values()){
			memberDao.updateMemberExtend(memberExtend);
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-28
	 * @描述：将封装好的实体放入对应的Map集合中
	 * @参数：
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void putPojoToMap(Map mapObj, ApproveData ad)
			throws MemberException {
		Object obj = null;
		if (mapObj.containsKey(ad.getClassId())) {
			obj = mapObj.get(ad.getClassId());
			mapObj.remove(ad.getClassId());
		} else {
			// 实例化寻找对应的实体
			obj = returnPojo(ad.getClassName(),ad.getClassId());
			// 向实体存入ID
			setValueToPojo(obj, "id", ad.getClassId());
		}
		setValueToPojo(obj, ad.getFieldName(), ad.getNewValue());
		mapObj.put(ad.getClassId(), obj);
	}

	/**
	 * @param id 
	 * @作者：罗典
	 * @时间：2012-3-28
	 * @描述：通过传入的值返回相应的实体对象
	 * @参数：pojoName 实体名
	 * */
	public  Object returnPojo(String pojoName, String id) throws MemberException {
		if (pojoName.equals(Member.class.getSimpleName())) {
			return alterMemberDao.getMember(id);
		} else if (pojoName.equals(Account.class.getSimpleName())) {
			return alterMemberDao.getAccount(id);
		} else if (pojoName.equals(Contact.class.getSimpleName())) {
			return alterMemberDao.getContact(id);
		} else if (pojoName.equals(ShuttleAddress.class.getSimpleName())) {
			return alterMemberDao.getShuttleAddress(id);
		} else if (pojoName.equals(PreferenceAddress.class.getSimpleName())) {
			return alterMemberDao.getPreferenceAddress(id);
		}else if(pojoName.equals(MemberExtend.class.getSimpleName())){
			return alterMemberDao.getMemberExtendInfo(id);
		}else {
			throw new MemberException(MemberExceptionType.HandlePojoError);
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-28
	 * @描述：通过反射将值通过字段名写入实体中
	 * @参数：obj 实体 fieldName 字段名 value 值
	 * */
	private void setValueToPojo(Object obj, String fieldName, String value) {
		ApproveDataUtil.setValueToPojo(obj, fieldName, value);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-31
	 * @描述：将需作废或新增的实体转换为审批数据，并存入审批数据集合中
	 * @参数：obj 实体对象，objId 实体ID，adList 审批数据集合，handleType 操作类型 1为新增，2为修改,3为删除
	 * @返回值：无
	 * */
	private void changePojoToApproveData(Object obj, String objId,
			List<ApproveData> adList, int handleType) throws MemberException {
		try {
			Field[] fieldList = this.objectFields(obj.getClass());
			for (Field field : fieldList) {
				field.setAccessible(true);
				if (field.getName().equals("serialVersionUID")
						|| field.get(obj) == null) {
					continue;
				}
				ApproveData ad = new ApproveData();
				ad.setClassId(objId);
				ad.setClassName(obj.getClass().getSimpleName());
				ad.setFieldName(field.getName());
				if (handleType == 1) {
					ad.setNewValue(validataNull(field.get(obj)));
				} else if (handleType == 3 || handleType == 2) {
					ad.setOldValue(validataNull(field.get(obj)));
				} else {
					throw new MemberException(
							MemberExceptionType.ApproveDataHandTypeError);
				}
				ad.setHandleType(handleType);
				adList.add(ad);
			}
		} catch (IllegalArgumentException e) {
			throw new MemberException(e, MemberExceptionType.JavaReflexError);
		} catch (IllegalAccessException e) {
			throw new MemberException(e, MemberExceptionType.JavaReflexError);
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-31
	 * @功能描述：保存需删除的数据并返回ID封装到审批数据集合中
	 * @参数：approveDataList审批数据集合，deleteDataList需删除的数据 handleType操作类型操作类型
	 *                                                1为新增，2为修改,3为删除
	 * @返回值：无
	 * */
	public void changeCancelDataToApproveList(List<ApproveData> deleteDataList,
			List<ApproveData> approveDataList) throws MemberException {
		for (ApproveData ad : deleteDataList) {
			// 需获取旧数据的会员消息
			if (ad.getClassName().equals(Member.class.getSimpleName())) {
				Member member = alterMemberDao.getMember(ad.getClassId());
				this.changePojoToApproveData(member, ad.getClassId(),
						approveDataList, 3);
			}
			// 需获取旧数据的联系人信息
			else if (ad.getClassName().equals(Contact.class.getSimpleName())) {
				Contact contact = alterMemberDao.getContact(ad.getClassId());
				this.changePojoToApproveData(contact, ad.getClassId(),
						approveDataList, 3);
			}
			// 需获取旧数据的接送货地址信息
			else if (ad.getClassName().equals(
					ShuttleAddress.class.getSimpleName())) {
				ShuttleAddress sa = alterMemberDao.getShuttleAddress(ad
						.getClassId());
				this.changePojoToApproveData(sa, ad.getClassId(),
						approveDataList, 3);
			}
			// 需获取旧数据的账号信息
			else if (ad.getClassName().equals(Account.class.getSimpleName())) {
				Account account = alterMemberDao.getAccount(ad.getClassId());
				this.changePojoToApproveData(account, ad.getClassId(),
						approveDataList, 3);
			}
			// 需获取旧数据的偏好地址信息
			else if (ad.getClassName().equals(
					PreferenceAddress.class.getSimpleName())) {
				PreferenceAddress pa = alterMemberDao.getPreferenceAddress(ad
						.getClassId());
				this.changePojoToApproveData(pa, ad.getClassId(),
						approveDataList, 3);
			}
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @功能描述：将需修改的审批数据的的旧值存入审批数据集合中
	 * @参数：updateApproveDataList需修改数据 handleType操作类型操作类型 1为新增，2为修改,3为删除
	 * @返回值：无
	 * */
	public void saveOldValueToApproveData(
			List<ApproveData> updateApproveDataList,Member dataBaseMember) throws MemberException {
		Member member = dataBaseMember;//dataBaseMember是根据客户ID查出来的旧的数据信息
		Contact contact = new Contact();
		MemberExtend memberExtend = new MemberExtend();
		ShuttleAddress shuttleAddress = new ShuttleAddress();
		Account account = new Account();
		PreferenceAddress preferenceAddress = new PreferenceAddress();
		for (ApproveData ad : updateApproveDataList) {
			// 需获取旧数据的会员消息
			if (ad.getClassName().equals(Member.class.getSimpleName())&&ad.getClassId().equals(member.getId())) {
				//扩展信息处理
				//判断修改的数据是否包含客户潜力类型（货量潜力），如果包含则进行相应的处理
				
				if(StringUtils.equals(ad.getFieldName(),"custPotentialType")){
					ad.setClassId(member.getId());
					ad.setClassName(MemberExtend.class.getSimpleName());
					ad.setFieldName("volumePotential");
					//如果查出来的扩展信息为空，那么就是新增货量潜力,插入一条扩展信息
					if(ValidateUtil.objectIsEmpty(member.getMemberExtend())){
						memberExtend.setCustId(member.getId());
						memberExtend.setVolumePotential(ad.getNewValue());
						memberDao.insertMemberExtendInfo(memberExtend);
						member.setMemberExtend(memberExtend);
						ad.setOldValue(null);
						ad.setHandleType(Constant.APPROVEDATA_INSERT);
					}else{//否则就是修改扩展信息表
						ad.setOldValue(member.getMemberExtend().getVolumePotential());
						putOldValueToApproveData(member.getMemberExtend(), ad);
					}
					
				}else{
					putOldValueToApproveData(member, ad);
				}
			}
			// 需获取旧数据的联系人信息
			else if (ad.getClassName().equals(Contact.class.getSimpleName())) {
				if (contact.getId() == null
						|| !ad.getClassId().equals(contact.getId())) {
					contact = null;
					if (null!=member&&null!=member.getContactList()&&0<member.getContactList().size()) {
						for (int i = 0; i < member.getContactList().size(); i++) {
							if ( member.getContactList().get(i).getId().equals(ad.getClassId())) {
								contact = member.getContactList().get(i);
							}
						}
					}
				}
				putOldValueToApproveData(contact, ad);
			}
			// 需获取旧数据的接送货地址信息
			else if (ad.getClassName().equals(
					ShuttleAddress.class.getSimpleName())) {
				if (shuttleAddress.getId() == null
						|| !ad.getClassId().equals(shuttleAddress.getId())) {
					shuttleAddress = null;
					if (null!=member&&!CollectionUtils.isEmpty(member.getShuttleAddressList())) {
						for (int i = 0; i < member.getShuttleAddressList().size(); i++) {
							if ( member.getShuttleAddressList().get(i).getId().equals(ad.getClassId())) {
								shuttleAddress = member.getShuttleAddressList().get(i);
							}
						}
					}
				}
				putOldValueToApproveData(shuttleAddress, ad);
			}
			// 需获取旧数据的账号信息
			else if (ad.getClassName().equals(Account.class.getSimpleName())) {
				if (account.getId() == null
						|| !ad.getClassId().equals(account.getId())) {
					account = null;
					if (null!=member&&!CollectionUtils.isEmpty(member.getAccountList())) {
						for (int i = 0; i < member.getAccountList().size(); i++) {
							if ( member.getAccountList().get(i).getId().equals(ad.getClassId())) {
								account = member.getAccountList().get(i);
							}
						}
					}
				}
				putOldValueToApproveData(account, ad);
			}
			// 需获取旧数据的偏好地址信息
			else if (ad.getClassName().equals(
					PreferenceAddress.class.getSimpleName())) {
				if (account.getId() == null
						|| !ad.getClassId().equals(account.getId())) {
					preferenceAddress = null;
					if (null!=member&&!CollectionUtils.isEmpty(member.getContactList())) {
						for (int i = 0; i < member.getContactList().size(); i++) {
							if (!CollectionUtils.isEmpty(member.getContactList().get(i).getPreferenceAddressList())) {
								for (int j = 0; j < member.getContactList().get(i).getPreferenceAddressList().size(); j++) {
									if (member.getContactList().get(i).getPreferenceAddressList().get(j).getId().equals(ad.getClassId())) {
										preferenceAddress = member.getContactList().get(i).getPreferenceAddressList().get(j);
									}
								}
							}
						}
					}
				}
				putOldValueToApproveData(preferenceAddress, ad);
			}
		}
	}

	// 将与传入实体字段值相应地传入审批数据中
	@SuppressWarnings("rawtypes")
	private void putOldValueToApproveData(Object obj, ApproveData approveData)
			throws MemberException {
		try {
			Class clazz = obj.getClass();
			Field[] fields = this.objectFields(clazz);
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.getName().equals(approveData.getFieldName())) {
					approveData.setOldValue(validataNull(field.get(obj)));
					approveData.setHandleType(2);
					break;
				}
			}
		} catch (IllegalArgumentException e) {
			throw new MemberException(e, MemberExceptionType.JavaReflexError);
		} catch (IllegalAccessException e) {
			throw new MemberException(e, MemberExceptionType.JavaReflexError);
		}
	}

	private String validataNull(Object obj) {
		if (obj == null) {
			return "";
		}
		if (obj instanceof Double) {
			BigDecimal b = new BigDecimal((Double)obj);
			return b.toString();
		}
		return obj.toString();
	}

	// 获得对象的所有字段
	@SuppressWarnings("rawtypes")
	private Field[] objectFields(Class clazz) {
		return clazz.getDeclaredFields();
	}

	/**
	 * @throws Exception
	 * @作者：罗典
	 * @时间：2012-3-31
	 * @功能描述：保存需新增的数据并返回ID封装到审批数据集合中
	 * @参数：addPojoMap 需保存的数据集合，approveDataList将保存后的数据封装到审批数据中
	 * @返回值：无
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void savePojoInfo(Map<String, List> addPojoMap,
			List<ApproveData> approveDataList, Member member, String memberId)
			throws MemberException {
		// 是否包含联系人信息
		boolean hasContact = false;
		// 是否包含偏好地址信息
		boolean hasPreferenceAddress = false;
		// 是否包含接送货地址信息
		boolean hasShuttleAddress = false;
		// 是否包含账号信息
		boolean hasAccount = false;
		if (null != addPojoMap.get(Contact.class.getSimpleName())
				&& 0 < addPojoMap.get(Contact.class.getSimpleName()).size()) {
			hasContact = true;
		}
		if (null != addPojoMap.get(PreferenceAddress.class.getSimpleName())
				&& 0 < addPojoMap.get(PreferenceAddress.class.getSimpleName())
						.size()) {
			hasPreferenceAddress = true;
		}
		if (null != addPojoMap.get(ShuttleAddress.class.getSimpleName())
				&& 0 < addPojoMap.get(ShuttleAddress.class.getSimpleName())
						.size()) {
			hasShuttleAddress = true;
		}
		if (null != addPojoMap.get(Account.class.getSimpleName())
				&& 0 < addPojoMap.get(Account.class.getSimpleName()).size()) {
			hasAccount = true;
		}


		List<ShuttleAddress> shuttleAddress = member.getShuttleAddressList();

		List<Contact> contacts = member.getContactList();

		List<PreferenceAddress> preAddress = new ArrayList<PreferenceAddress>();

		List<Account> accounts = member.getAccountList();

		for (Contact contact : contacts) {
			preAddress.addAll(contact.getPreferenceAddressList());
		}

		// 此会员所有的接送货地址信息
		List<ShuttleAddress> shuttleAddressList = new ArrayList<ShuttleAddress>();
		/***************** 1，存接送货地址信息 **********************/
		// 保存接送货地址信息
		if (hasShuttleAddress) {
			for (Object obj : addPojoMap.get(ShuttleAddress.class
					.getSimpleName())) {
				ShuttleAddress sa = (ShuttleAddress) obj;
				sa.setStatus("2");// 暂为无效状态
				sa.setMemberId(memberId);

				// 会员接送货地址
				ShuttleAddress add = null;
				// 如果新增的接送货地址与会员中接送货地址
				for (ShuttleAddress shutAdd : shuttleAddress) {
					if (shutAdd.equals(sa)) {
						add = shutAdd;
					}
				}
				memberDao.saveShuttleAddress(sa);
				// 则设置会员中接送货地址的id
				if (null != add) {
					String oldId = add.getId();
					String newId = sa.getId();
					
					replaceRealValues(approveDataList,"PreferenceAddress","shuttleAddressId",oldId,newId);
					
					add.setId(sa.getId());
					
				}
				// 将新增的数据存入审批数据表中
				this.changePojoToApproveData(sa, sa.getId(), approveDataList, 1);
			}
		}
		/***************** 2，存联系人信息 **********************/
		// 保存联系人信息
		if (hasContact) {
			for (Object obj : addPojoMap.get(Contact.class.getSimpleName())) {
				Contact contact = (Contact) obj;
				contact.setStatus("2");// 暂为无效个状态
				contact.setCustId(memberId);
				/**
				 * 维护主联系人客户类型字段
				 */
				if (contact.getIsMainLinkMan()) {
					contact.setBelongCustType(member.getCustType());
				}
				Contact memContact = null;
				// 获得会员中新增的联系人信息
				for (Contact cont : contacts) {
					if (cont.equals(contact)) {
						memContact = cont;
					}
				}
				memberDao.saveContact(contact);

				if (null != memContact) {
					String oldId = memContact.getId();
					String newId = contact.getId();
					//覆盖新数据字段
					replaceRealValues(approveDataList,"Account","financeLinkmanId",oldId,newId);
					
					memContact.setId(contact.getId());
				}

				// 将新增的数据存入审批数据表中
				this.changePojoToApproveData(contact, contact.getId(),
						approveDataList, 1);
				// 保存此联系人下的偏好地址信息
				for (PreferenceAddress pa : contact.getPreferenceAddressList()) {
					if (shuttleAddressList.size() == 0) {
						shuttleAddressList = member.getShuttleAddressList();
					}

					PreferenceAddress memberPreAdd = null;

					for (PreferenceAddress preAdd : preAddress) {
						if (preAdd.equals(pa)) {
							memberPreAdd = preAdd;
						}
					}

					// 为此偏好地址存入相应联系人信息ID
					pa.setLinkManId(contact.getId());
					// 为此偏好地址存入相应的接送货地址信息ID
					pa.setShuttleAddressId(CustomerRule.getShuttelAddress(
							shuttleAddressList, pa).getId());
					pa.setStatus("2");// 暂为无效个状态
					memberDao.savePreferenceAddress(pa);

					if (null != memberPreAdd) {
						memberPreAdd.setId(pa.getId());
					}

					// 将新增的数据存入审批数据表中
					this.changePojoToApproveData(pa, pa.getId(),
							approveDataList, 1);
				}
			}
		}
		/***************** 3，存账户信息联系人信息 **********************/
		// 保存账号信息
		if (hasAccount) {
			// 此会员客户所有的联系人信息
			List<Contact> contactList = member.getContactList();
			for (Object obj1 : addPojoMap.get(Account.class.getSimpleName())) {
				Account account = (Account) obj1;

				Account acc = null;

				for (Account accout : accounts) {
					if (accout.equals(account)) {
						acc = account;
					}
				}

				// 存入联系人ID
				account.setFinanceLinkmanId(CustomerRule.getContact(
						contactList, account).getId());
				account.setStatus("2");// 暂为无效个状态
				account.setBelongcustom(memberId);
				memberDao.saveAccount(account);

				if (null != acc) {
					acc.setId(account.getId());
				}
				// 将新增的数据存入审批数据表中
				this.changePojoToApproveData(account, account.getId(),
						approveDataList, 1);
			}
		}

		/***************** 4，先联系人偏好地址信息 **********************/
		// 保存偏好地址信息
		if (hasPreferenceAddress) {

			List<PreferenceAddress> contactPreAdd = addPojoMap
					.get(PreferenceAddress.class.getSimpleName());

			// 去掉已经保存过的联系人偏好地址，如果有联系人，那么已经保存过联系人偏好地址了
			// 如果没有修改联系人信息，那
			if (hasContact) {
				contactPreAdd.removeAll(preAddress);
			}

			for (Object obj : addPojoMap.get(PreferenceAddress.class
					.getSimpleName())) {
				PreferenceAddress pa = (PreferenceAddress) obj;
				if (shuttleAddressList.size() == 0) {
					shuttleAddressList = member.getShuttleAddressList();
				}
				// 为此偏好地址存入相应的接送货地址信息
				pa.setShuttleAddressId(CustomerRule.getShuttelAddress(
						shuttleAddressList, pa).getId());
				pa.setStatus("2");// 暂为无效个状态
				memberDao.savePreferenceAddress(pa);
				// 将新增的数据存入审批数据表中
				this.changePojoToApproveData(pa, pa.getId(), approveDataList, 1);
			}
		}

		

	}
	/**
	 * 
	 * <p>
	 * Description:把approveDateList里面对应的className的和 fieldName对应的newValue与oldId相同的值 替换成 newId<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-29
	 * @param approveDataList
	 * @param className
	 * @param fieldName
	 * @param oldId
	 * @param newId
	 * void
	 */
	private void replaceRealValues(List<ApproveData> approveDataList, String className, String fieldName, String oldId,
			String newId) {
		Assert.notNull(className, "className must not null");
		Assert.notNull(fieldName, "fieldName must not null");
		Assert.notNull(oldId, "oldId must not null");

		for (ApproveData approveData : approveDataList) {
			if( className.equals(approveData.getClassName())
				&& fieldName.equals(approveData.getFieldName())
				&& oldId.equals(approveData.getNewValue())
					){
				approveData.setNewValue(newId);
			}
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-6
	 * @功能描述：根据会员ID查询会员相关所有信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	@Override
	public Member getMemberAllById(String id) {
		Member mem = alterMemberDao.getMemberAllById(id);
		if(null!=mem&&!ValidateUtil.objectIsEmpty(mem.getMemberExtend())
				&&!StringUtils.isEmpty(mem.getMemberExtend().getVolumePotential())){
			mem.setCustPotentialType(mem.getMemberExtend().getVolumePotential());
		}
		return mem;
	}
	/**
	 * (非 Javadoc)
	* <p>Title: getMemberAllByIdNEW</p>
	* <p>Description:根据会员ID查询会员相关所有信息(包括作废的客户)
	* @author chenaichun 
	* @param id
	* @return
	* @see com.deppon.crm.module.customer.server.service.IAlterMemberService#getMemberAllByIdNEW(java.lang.String)
	 */
	@Override
	public Member getMemberAllByIdNEW(String id) {
		Member mem = alterMemberDao.getMemberAllByIdNEW(id);
		if(!ValidateUtil.objectIsEmpty(mem.getMemberExtend())
				&&!StringUtils.isEmpty(mem.getMemberExtend().getVolumePotential())){
			mem.setCustPotentialType(mem.getMemberExtend().getVolumePotential());
		}
		return mem;
	}
	/**
	 * @作者：李学兴
	 * @时间：2012-4-6
	 * @功能描述：根据会员ID查询作废会员相关所有信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	@Override
	public Member getInvalidMemberAllById(String memberId) {
		return alterMemberDao.getInvalidMemberAllById(memberId);
	}
	@Override
	public Member4All queryMember4AllById(String id) {
		return alterMemberDao.queryMember4AllById(id);
	}
	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据条件查询会员信息总数
	 * @参数: 会员查询条件
	 * @返回值：满足条件的总数
	 * */
	public int countMemberByCondition(MemberCondition condition) {
		return alterMemberDao.countMemberByCondition(condition);
	}

	public IAlterMemberDao getAlterMemberDao() {
		return alterMemberDao;
	}

	public void setAlterMemberDao(IAlterMemberDao alterMemberDao) {
		this.alterMemberDao = alterMemberDao;
	}

	public IMemberDao getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(IMemberDao memberDao) {
		this.memberDao = memberDao;
	}
	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据审批数据将会员状态修改为正常
	 * @参数: 审批数据
	 * @返回值：无
	 * */
	@Override
	public void changeMemberStatus(List<ApproveData> adList) {
		ApproveData ad = adList.get(0);
		String memberId = "";
		Member mem = null;
		if (ad.getClassName().equals(Member.class.getSimpleName())) {
			memberId = ad.getClassId();
			//修改账号信息如果客户是作废的则保持原状态（迁移数据数据字典不照即使不改基本信息也会走该路线）
			mem = alterMemberDao.getMember(memberId);
		} else if (ad.getClassName().equals(Contact.class.getSimpleName())) {
			Contact contact = alterMemberDao.getContact(ad.getClassId());
			memberId = contact.getCustId();
		} else if (ad.getClassName().equals(
				ShuttleAddress.class.getSimpleName())) {
			ShuttleAddress shuttleAddress = alterMemberDao.getShuttleAddress(ad
					.getClassId());
			memberId = shuttleAddress.getMemberId();
		} else if (ad.getClassName().equals(
				PreferenceAddress.class.getSimpleName())) {
			PreferenceAddress pa = alterMemberDao.getPreferenceAddress(ad
					.getClassId());
			Contact contact = alterMemberDao.getContact(pa.getLinkManId());
			memberId = contact.getCustId();
		} else if (ad.getClassName().equals(Account.class.getSimpleName())) {
			Account account = alterMemberDao.getAccount(ad.getClassId());
			memberId = account.getBelongcustom();
			//修改账号信息如果客户是作废的则保持原状态
			mem = alterMemberDao.getMember(memberId);
		}
		/*
		 * @作者：李学兴
		 * @时间：2012-10-19
		 * @描述：作废的会员可以进行账号信息修改，
		 * 			审批后会员状态仍然为作废状态
		 * */
		String custStatus = Constant.Status_Normal;
		if(mem != null &&mem.getMainContact()!=null&& Constant.Status_NoEfficacy.equals(mem.getMainContact().getStatus())){
			custStatus = Constant.Status_NoEfficacy;
		}
		// 修改会员状态为正常
		this.alterMemberDao.alterMemberStatus(memberId, custStatus);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据工作流条件查询工作流信息
	 * @参数：toDoWorkflowCondition 工作流信息条件
	 * @返回值：工作流信息
	 * */
	public List<TodoWorkflow> searchWorkflowByCondition(
			WorkFlowCondition toDoWorkflowCondition, int start, int limit) {
		return this.alterMemberDao.searchWorkflowByCondition(
				toDoWorkflowCondition, start, limit);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-6
	 * @功能描述：根据会员ID查询会员基本信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员基本信息
	 * */
	public Member getMemberById(String id) {
		return this.alterMemberDao.getMember(id);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据工作流条件查询工作流信息
	 * @参数：toDoWorkflowCondition 工作流信息条件
	 * @返回值：工作流信息总数
	 * */
	@Override
	public int countWorkflowByCondition(WorkFlowCondition toDoWorkflowCondition) {
		return this.alterMemberDao
				.countWorkflowByCondition(toDoWorkflowCondition);
	}

	@Override
	public List<Member> searchMemberByCondition(MemberCondition condition) {
		List<Member> members = this.alterMemberDao.searchMemberByCondition(condition);
		if (CollectionUtils.isNotEmpty(members)) {
			for (int i = 0; i < members.size(); i++) {
				if(!ValidateUtil.objectIsEmpty(members.get(i).getMemberExtend())
						&&!StringUtils.isEmpty(members.get(i).getMemberExtend().getVolumePotential())){
					members.get(i).setCustPotentialType(members.get(i).getMemberExtend().getVolumePotential());
				}
			}
		}
		return members;
	}

	@Override
	public List<Contact> searchContactByMemberId(String memberId) {
		return this.alterMemberDao.searchContactByMemberId(memberId);
	}

	/*
	 * 根据联系人id查询联系人详细信息
	 * 
	 * @see com.deppon.crm.module.customer.server.service.IAlterMemberService#
	 * getContactDetailInfoById(java.lang.String)
	 */
	@Override
	public Contact getContactDetailInfoById(String id) {
		return alterMemberDao.getContactDetailInfoById(id);
	}

	/**
	 * 
	 * <p>
	 * 根据会员Id查想会员相关银行账号信息<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param memberId
	 *            客户Id
	 * @return List<Account>
	 * @see com.deppon.crm.module.customer.server.service.IAlterMemberService#getAccountsByMemberId(java.lang.String)
	 */
	@Override
	public List<Account> getAccountsByMemberId(String memberId) {
		return alterMemberDao.getAccountsByMemberId(memberId);
	}

	/**
	 * 
	 * <p>
	 * 根据账户id查询账户信息<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param id
	 *            账户Id
	 * @return Account
	 * @see com.deppon.crm.module.customer.server.service.IAlterMemberService#getAccountById(java.lang.String)
	 */
	@Override
	public Account getAccountById(String id) {
		return alterMemberDao.getAccount(id);
	}

	@Override
	public Contact getContact(String id) {
		return alterMemberDao.getContact(id);
	}

	@Override
	public boolean updateContact(Contact contact) {
		return alterMemberDao.updateContact(contact);
	}

	@Override
	public void losePreferenceAddressByContactId(String contactId) {
		alterMemberDao.losePreferenceAddressByContactId(contactId);
	}

	@Override
	public boolean updateMember(Member member) {
		return alterMemberDao.updateMember(member);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.service.IAlterMemberService#
	 * searchPreferAddByContactId(java.lang.String)
	 */
	@Override
	public List<PreferenceAddress> searchPreferAddByContactId(String contactId,
			String addressType) {
		return alterMemberDao.getPreferAddByContactId(contactId, addressType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.service.IAlterMemberService#
	 * searchMyWorkflowByCondition
	 * (com.deppon.crm.module.customer.shared.domain.WorkFlowCondition, int,
	 * int)
	 */
	@Override
	public List<MyToDoWorkFlow> searchMyWorkflowByCondition(
			WorkFlowCondition workflowCondition, int start, int limit) {
		if (null == workflowCondition) {
			workflowCondition = new WorkFlowCondition();
			workflowCondition.setOwnerRoleIds(ContextUtil
					.getCurrentUserRoleIds());
		}else  if(null == workflowCondition.getDeptId()) {
			workflowCondition.setOwnerRoleIds(ContextUtil
					.getCurrentUserRoleIds());
		}
		return alterMemberDao.searchMyWorkflowByCondition(workflowCondition,
				start, limit);
	}

	@Override
	public void saveMemberOperationLog(MemberOperationLog log) {
		alterMemberDao.insertMemberOperationLog(log);
	}

	@Override
	public void updateMemberOperationLog(MemberOperationLog log) {
		 alterMemberDao.updateMemberOperationLog(log);
	}

	@Override
	public List<ApproveData> searchApproveDataByLogId(String logId) {
		return alterMemberDao.searchApproveDataByLogId(logId);
	}

	@Override
	public void insertCustWorkflow(String custId, String workflowId) {
		alterMemberDao.insertCustWorkflow(custId, workflowId);
	}

	@Override
	public boolean updateCustWorkflow(String custId, String workflowId) {
		return alterMemberDao.updateCustWorkflow(custId, workflowId);
	}

	@Override
	public String getCustWorkflow(String custId) {
		return alterMemberDao.getCustWorkflow(custId);
	}

	public IMemberIntegralDao getMemberIntegralDao() {
		return memberIntegralDao;
	}

	public void setMemberIntegralDao(IMemberIntegralDao memberIntegralDao) {
		this.memberIntegralDao = memberIntegralDao;
	}
	public ICustomerOperate getCustomerOperate() {
		return customerOperate;
	}
	public void setCustomerOperate(ICustomerOperate customerOperate) {
		this.customerOperate = customerOperate;
	}

	/**
	 * 
	 * @Title: modifyPrefrenceAddress
	 *  <p>
	 * @Description: 修改联系人接送货主偏好地址
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-10
	 * @return void
	 * @throws
	 */
	@Override
	public void modifyPreferenceAddress(PreferenceAddress preferenceAddress) {
		alterMemberDao.updatePreferenceAddress(preferenceAddress);
	}
	/**
	 * 
	 * @Title: modifyMemberShuttleAddress
	 *  <p>
	 * @Description: 修改固定客户接送货地址
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-10
	 * @return void
	 * @throws
	 */
	@Override
	public void modifyMemberShuttleAddress(ShuttleAddress shuttleAddress) {
		alterMemberDao.updateShuttleAddress(shuttleAddress);
	}
	/**
	 * 
	 * @Title: queryPreferenceAddrByContactId
	 *  <p>
	 * @Description: 通过联系人Id查询联系人偏好地址
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-10
	 * @return void
	 * @throws
	 */
	@Override
	public List<PreferenceAddress> queryPreferenceAddrByContactId(String contactId) {
		return alterMemberDao.queryPreferenceAddressByContactId(contactId);
		
	}
	/**
	 * 
	 * @Title: savePreferenceAddress
	 *  <p>
	 * @Description: 保存联系人偏好地址
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-23
	 * @return void
	 * @throws
	 */
	@Override
	public void savePreferenceAddress(PreferenceAddress preferenceAddress) {
		memberDao.savePreferenceAddress(preferenceAddress);
	}
	/**
	 * 
	 * @Title: saveShuttleAddress
	 *  <p>
	 * @Description: 保存接送货地址
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-23
	 * @return void
	 * @throws
	 */
	@Override
	public void saveShuttleAddress(ShuttleAddress shuttleAddress) {
		memberDao.saveShuttleAddress(shuttleAddress);
	}
	@Override
	public boolean updateContactForCustCoordinates(Contact contact) {
		return alterMemberDao.updateContactForCustCoordinates(contact);
	}
	@Override
	public List<String> findInvalidCustNumber() {
		return alterMemberDao.queryInvalidCust();
	}
	@Override
	public void updateMemberFinStatus(List<String> updateCustStatus,boolean status) {
		if (null==updateCustStatus) {
			return;
		}
		if (0>=updateCustStatus.size()){
			return;
		}
		
		List<String> updateCustIds = alterMemberDao.queryCustIdByNumber(updateCustStatus);
		
		alterMemberDao.invalidMemberFinStatus(updateCustStatus,updateCustIds,status);
		
	}
	@Override
	public Member searchMember4WorkflowById(String memberId){
		return alterMemberDao.queryMember4WorkflowById(memberId);
	}
	/**
	 * @param contactDao : set the property contactDao.
	 */
	public void setContactDao(IContactDao contactDao) {
		this.contactDao = contactDao;
	}
	/**
	 * 
	 * <p>
	 * Description:根据客户编码查询会员基本信息<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-2
	 * @param custNum
	 * @return
	 * MemberResult
	 */
	@Override
	public List<MemberResult> queryBasicMemberByCustNum(String custNum) {
		return alterMemberDao.queryBasicMemberByCustNum(custNum);
	}
	@Override
	public List<ShuttleAddress> queryShuttleAddressForDetial(String custId) {
		return alterMemberDao.queryShuttleAddressForDetial(custId);
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IAlterMemberService#physicalDeleteMember(com.deppon.crm.module.customer.shared.domain.Member)
	 */
	@Override
	public void physicalDeleteMember(Member member) {
		if (null==member) {
			return ;
		}
		if (null!=member.getMemberExtend()) {
			alterMemberDao.deleteMemberExtend(member.getId());
		}
		if (null!=member.getContactList()&&0<member.getContactList().size()) {
			for (int i = 0; i < member.getContactList().size(); i++) {
					alterMemberDao.deletePreferenceAddress(member.getContactList().get(i).getId());
			}
			alterMemberDao.deleteContact(member.getId());
		}
		if (null!=member.getShuttleAddressList()&&0<member.getShuttleAddressList().size()) {
			alterMemberDao.deleteShuttleAddress(member.getId());
		}
		if (null!=member.getAccountList()&&0<member.getAccountList().size()) {
			alterMemberDao.deleteAccount(member.getId());
		}
		alterMemberDao.deleteMember(member.getId());
	}
	/**
	 * <p>
	 * Description:根据客户ID查询客户变更历史信息
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.customer.server.service.IAlterMemberService#searchMemberOperationLogList(java.lang.String)
	 * @version 0.1 2014-4-1下午3:45:37
	 * @param custId
	 * @return
	 * @update 	2014-4-1下午3:45:37
	 */
	@Override
	public List<MemberOperationLog> searchMemberOperationLogList(String custId) {
		return alterMemberDao.searchMemberOperationLogList(custId);
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IAlterMemberService#modifyScatterMarktingDeptByDay(int)
	 */
	@Override
	public void modifyScatterMarktingDeptByDay(int day,String deptId) {
		alterMemberDao.upadteScatterMarktingDeptByDay(day,deptId);
	}
	@Override
	public boolean isExtistUnCloseBusiness(String memberId) {
		return alterMemberDao.isExtistUnCloseBusiness(memberId);
	}
	@Override
	public Member4All queryMember4AllByIdNEW(String memberId) {
		return alterMemberDao.queryMember4AllByIdNEW(memberId);
	}
	
	@Override
	public boolean upadteUnCloseBustiness(String newMemberId,String beforeMemberId) {
		return alterMemberDao.upadteUnCloseBustiness(newMemberId, beforeMemberId);
	}
	@Override
	public ShuttleAddress getShuttleAddress(String classId) {
		return alterMemberDao.getShuttleAddress(classId);
	}
	@Override
	public PreferenceAddress getPreferenceAddress(String classId) {
		return alterMemberDao.getPreferenceAddress(classId);
	}
	@Override
	public MemberOperationLog searchMemberOperationLogByWorkflowNum(String num) {
		return alterMemberDao.searchMemberOperationLogByWorkflowNum(num);
	}
}
