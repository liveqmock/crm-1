package com.deppon.crm.module.customer.server.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;

public class MemberUtil {
	
	/**
	 * 
	* @Title: StringFilter
	* @Description: 手机号、电话号去除特殊字符只保留数字（用于生成联系人编码）
	* @author chenaichun 
	* @param @param str
	* @param @return
	* @param @throws PatternSyntaxException    设定文件
	* @date 2014年4月8日 上午8:43:08
	* @return String    返回类型
	* @throws
	* @update 2014年4月8日 上午8:43:08
	 */
	 public static String stringFilter(String str) throws PatternSyntaxException {      
         // 只允许字母和数字        
         // String   regEx  =  "[^a-zA-Z0-9]";                      
            // 清除掉所有特殊字符   
		 String regEx="[^0-9]";   
		 Pattern p = Pattern.compile(regEx);      
		 Matcher m = p.matcher(str);      
		 return m.replaceAll("").trim();      
   }
	 /**
	  * 
	 * @Title: produceContactNum
	 * @Description: 生成联系人编码
	 * @author chenaichun 
	 * @param @param contact    设定文件
	 * @date 2014年4月8日 下午6:39:36
	 * @return void    返回类型
	 * @throws
	 * @update 2014年4月8日 下午6:39:36
	  */
	public static void produceContactNum (Contact contact){
		String phone = contact.getMobilePhone();
		String tel = contact.getTelPhone();
		String name = contact.getName();
		if (ValidateUtil.objectIsEmpty(name)||(ValidateUtil.objectIsEmpty(phone)
				&&ValidateUtil.objectIsEmpty(tel))) {
			throw new IllegalArgumentException("手机，姓名，电话,值不全,参数调用不合法");
		}
		if (!ValidateUtil.objectIsEmpty(phone)) {
			contact.setNumber(
					StringUtils.lowerCase(new FirstCharGetUtil().getFirstChar(name))+stringFilter(phone));
			return ;
		}
		if (!ValidateUtil.objectIsEmpty(tel)) {
			contact.setNumber(
					StringUtils.lowerCase(new FirstCharGetUtil().getFirstChar(name))+stringFilter(tel));
			return;
		}
	}
	/**
	 * 
	* @Title: isCCContactListEquals
	* @Description: 判断CC联系人列表关键信息是否相同
	* @author chenaichun 
	* @param @param pContactList CRM客户的
	* @param @param mContactList CC传的
	* @param @return    设定文件
	* @date 2014年4月4日 上午11:57:12
	* @return Boolean    返回类型
	* @throws
	* @update 2014年4月4日 上午11:57:12
	 */
	public static Boolean isCCContactListEquals(List<Contact> pContactList,List<Contact> mContactList){
		//循环遍历新联系人信息列表
		for (Contact mContact : mContactList) {
			//如果新的列表里面的联系人ID为空，说明是新增，
			if(ValidateUtil.objectIsEmpty(mContact.getId())){
				return false;
			}
			//不为空，为修改
			for (Contact pContact : pContactList) {
				if(mContact.getId().equals(pContact.getId())&&!isContactEquals(pContact, mContact)){
					return false;
				}
				
			}
		}
		return true;
	}
	/**
	 * 
	* @Title: isContactEquals
	* @Description: 判断两个联系人是否一致(官网不用，CC使用,只比较关键信息是否相同，CC修改时不会传传真，email等，所以不用比较）
	* @author chenaichun 
	* @param @param pCon
	* @param @param memContact
	* @param @return    设定文件
	* @date 2014-3-12 下午4:41:45
	* @return Boolean    返回类型
	* @throws
	* @update 2014-3-12 下午4:41:45
	 */
	public static Boolean isContactEquals(Contact pCon,Contact memContact){
		if(!StringUtils.equals(pCon.getMobilePhone(), memContact.getMobilePhone())){
			return false;
		}
		if(!StringUtils.equals(pCon.getTelPhone(), memContact.getTelPhone())){
			return false;
		}
		if(!StringUtils.equals(pCon.getName(), memContact.getName())){
			return false;
		}
//		if(!StringUtils.equals(pCon.getFfax(), memContact.getFfax())){
//			return false;
//		}
//		if(!StringUtils.equals(pCon.getEmail(), memContact.getEmail())){
//			return false;
//		}
//		if(pCon.getIsMainLinkMan()!=memContact.getIsMainLinkMan()){
//			return false;
//		}
		return true;
	}
	/**
	 * 
	* @Title: isPreferAddressListEquals
	* @Description: 比较联系人偏好地址是否相同
	* @author chenaichun 
	* @param @param pPreferAddList
	* @param @param mPreferAddList
	* @param @return    设定文件
	* @date 2014-3-12 下午6:17:37
	* @return Boolean    返回类型
	* @throws
	* @update 2014-3-12 下午6:17:37
	 */
	public static Boolean isPreferAddressListEquals(List<PreferenceAddress> pPreferAddList,List<PreferenceAddress> mPreferAddList){
		if(pPreferAddList!=null&&pPreferAddList.size()>0&&
				mPreferAddList!=null&&mPreferAddList.size()>0){
				for (PreferenceAddress mPreferAdd : mPreferAddList) {
					for (PreferenceAddress pPreferAdd : pPreferAddList) {
						if(!isPreferAddressEquals(pPreferAdd,mPreferAdd)){
							return false;
						}
					}
				}
				return true;
		}
		return false;
	}
	/**
	 * 
	* @Title: isPreferAddressEquals
	* @Description: 判断两个偏好地址对象是否一致
	* @author chenaichun 
	* @param @param pPreferAdd
	* @param @param mPreferAdd
	* @param @return    设定文件
	* @date 2014-3-12 下午4:40:41
	* @return Boolean    返回类型
	* @throws
	* @update 2014-3-12 下午4:40:41
	 */
	public static Boolean isPreferAddressEquals(PreferenceAddress pPreferAdd,PreferenceAddress mPreferAdd){
		if(!StringUtils.equals(pPreferAdd.getAddress(), mPreferAdd.getAddress())){
			return false;
		}
		if(!StringUtils.equals(pPreferAdd.getAddressType(), mPreferAdd.getAddressType())){
			return false;
		}
		if(pPreferAdd.getIsMainAddress() != mPreferAdd.getIsMainAddress()){
			return false;
		}
		if(pPreferAdd.getIsSendToFloor()!= mPreferAdd.getIsSendToFloor()){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * <p>
	 * Description:生产approveData<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2014-3-8
	 * @param oldValue
	 * @param newValue
	 * @param classId
	 * @param className
	 * @return ApproveData
	 */
	public static  ApproveData proApproveData(String oldValue, String newValue,
			String classId, String className,String fieldName) {
		ApproveData data = new ApproveData();
		data.setClassName(className);
		data.setClassId(classId);
		data.setFieldName(fieldName);
		data.setOldValue(oldValue);
		data.setNewValue(newValue);
		data.setHandleType(Constant.APPROVEDATA_UPDATE);
		return data;
	}
	/**
	 * 
	* @Title: createContactApproveData
	* @Description: 创建单个联系人操作的appdata
	* @author chenaichun 
	* @param @param pCon
	* @param @param memContact
	* @param @return    设定文件
	* @date 2014年4月3日 下午7:31:54
	* @return List<ApproveData>    返回类型
	* @throws
	* @update 2014年4月3日 下午7:31:54
	 */
	public static List<ApproveData> createContactApproveData(Contact pCon,Contact memContact){
		List<ApproveData> updateContactDataAllList = new ArrayList<ApproveData>();
		
			if(!StringUtils.equals(pCon.getMobilePhone(), memContact.getMobilePhone())){
				updateContactDataAllList.add(proApproveData(pCon.getMobilePhone(),
						memContact.getMobilePhone(), pCon.getId(),
						Contact.class.getSimpleName(), "mobilePhone"));
			}
			if(!StringUtils.equals(pCon.getTelPhone(), memContact.getTelPhone())){
				updateContactDataAllList.add(proApproveData(pCon.getTelPhone(),
						memContact.getTelPhone(), pCon.getId(),
						Contact.class.getSimpleName(), "telPhone"));
			}
			if(!StringUtils.equals(pCon.getName(), memContact.getName())){
				updateContactDataAllList.add(proApproveData(pCon.getName(),
						memContact.getName(), pCon.getId(),
						Contact.class.getSimpleName(), "name"));
			}
			//TODO CC会丢弃我们传过去的传真，email 因此修改CC客户的时候 对于已存在的联系人的传真号不作比较，以CRM为准
//			if(!StringUtils.equals(pCon.getFfax(), memContact.getFfax())){
//				updateContactDataAllList.add(proApproveData(pCon.getFfax(),
//						memContact.getFfax(), pCon.getId(),
//						Contact.class.getSimpleName(), "ffax"));
//			}
//			if(!StringUtils.equals(pCon.getEmail(), memContact.getEmail())){
//				updateContactDataAllList.add(proApproveData(pCon.getEmail(),
//						memContact.getEmail(), pCon.getId(),
//						Contact.class.getSimpleName(), "email"));
//			}
			
		return	updateContactDataAllList;
	}
	/**
	 * 
	* @Title: createContactListApproveData
	* @Description: 创建联系人列表的appdata
	* @author chenaichun 
	* @param @param pContactList 旧的列表（客户的联系人列表）
	* @param @param memContactList  新的列表（CC）
	* @param @return    设定文件
	* @date 2014年4月3日 下午7:32:41
	* @return List<ApproveData>    返回类型
	* @throws
	* @update 2014年4月3日 下午7:32:41
	 */
	public static List<ApproveData> createContactListApproveData(List<Contact> pContactList,List<Contact> memContactList){
		List<ApproveData> updateContactDataAllList = new ArrayList<ApproveData>();
		//循环遍历新联系人信息列表
		for (Contact mContact : memContactList) {
			//如果新的列表里面的联系人ID为空，说明是新增，则不放入updateContactDataAllList中，而是在manager层中直接存到Addpojo中
			//不为空，为修改则保存到updateContactDataAllList中
			//如果新的列表无，旧的列表有，则说明是删除，不作处理，即在CC删除联系人不会再CRM中删
			if(!StringUtils.isEmpty(mContact.getId())){
				for (Contact pContact : pContactList) {
					//联系人ID相等 
					if(mContact.getId().equals(pContact.getId())){
							//有修改 构造修改数据
							if(!isContactEquals(pContact, mContact)){
								updateContactDataAllList.addAll(createContactApproveData(pContact, mContact));
						
							}
							//无修改也跳出 比较下一个联系人
							break;//跳出内循环
					}
					
				}
			}
		}
		return	updateContactDataAllList;
	}
	
	/**
	 * <p>
	 * Description:是否只修改临欠额度或者只新增账号<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-5-4
	 * @param appDatas
	 * @return
	 * boolean 
	 */
	public static boolean isEmergencyUpdate(List<ApproveData> appDatas) {
		if (CollectionUtils.isEmpty(appDatas)) {
			return true;
		}
		for (int i = 0; i < appDatas.size(); i++) {
			if (appDatas.get(i).getClassName()
						.equals(Member.class.getSimpleName())
				&& appDatas.get(i).getFieldName().equals("procRedit")) {
				continue;
			}
			if (appDatas.get(i).getHandleType()
					.equals(Constant.APPROVEDATA_INSERT)
					&& appDatas.get(i).getClassName()
							.equals(Account.class.getSimpleName())) {
				continue;
			}
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Description:是否修改了账号信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-5-4
	 * @param appDatas
	 * @return
	 * boolean 
	 */
	public static boolean isModifyAccount(List<ApproveData> appDatas) {
		if (CollectionUtils.isEmpty(appDatas)) {
			return false;
		}
		for (int i = 0; i < appDatas.size(); i++) {
			if (appDatas.get(i).getClassName()
							.equals(Account.class.getSimpleName())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * <p>
	 * Description:是否仅仅修改了临欠额度<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-5-4
	 * @param appDatas
	 * @return
	 * boolean 
	 */
	public static boolean isOnlyModifyProcRedit(List<ApproveData> appDatas) {
		if (CollectionUtils.isEmpty(appDatas)) {
			return false;
		}
		if (1 != appDatas.size()) {
			return false;
		}
		if (appDatas.get(0).getClassName()
				.equals(Member.class.getSimpleName())
				&& appDatas.get(0).getFieldName().equals("procRedit")) {
			return true;
		}
		return false;
	}
	/**
	 * <p>
	 * Description:是否仅仅修改了临欠额度<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-5-4
	 * @param appDatas
	 * @return
	 * boolean 
	 */
	public static boolean isModifyProcRedit(List<ApproveData> appDatas) {
		if (CollectionUtils.isEmpty(appDatas)) {
			return false;
		}
		if (appDatas.get(0).getClassName()
				.equals(Member.class.getSimpleName())
				&& appDatas.get(0).getFieldName().equals("procRedit")) {
			return true;
		}
		return false;
	}
}
