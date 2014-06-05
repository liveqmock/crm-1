/**
 * @description
 * @author 赵斌
 * @2012-4-24
 * @return
 */
package com.deppon.crm.module.customer.server.manager;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactCondition;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.customer.shared.domain.Member;

/**
 * @author 赵斌
 *
 */
public interface IContactManager 
{
	/**
	 * 
	 * @根据联系人编码获得联系人信息
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	public ContactView getContactByNumber(String number);
	/**
	 * 
	 * <p>
	 * 根据联系编码查询联系id<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-8
	 * @param number
	 * @return
	 * String
	 */
	public String getContactIdByContactNumber(String number);
	
	/**
	 * 
	 * @绑定联系人
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	public boolean boundContactForWeb(String orderSource, String channelCustId,String contactNumber,String mobile);
	/**
	 * 
	 * <p>
	 * 得到联系人对应的会员id<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-27
	 * @param contactId
	 * @return
	 * String
	 */
	public String getContactMemberId(String contactId);
	/**
	 * 
	 * <p>
	 * 得到会员其下的联系人<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-27
	 * @param memberId
	 * @return
	 * List<Contact>
	 */
	public List<Contact> getContactByMemberId(String memberId);
	/**
	 * 
	 * <p>
	 * 得到会员其下的联系人id<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-3
	 * @param memberId
	 * @return
	 * List<String>
	 */
	public List<String> getContactIdListByMemberId(String memberId);
	/**
	 * 
	 * <p>
	 * 联系人变更<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-28
	 * @param fromContact
	 * @param member
	 * void
	 */
	public void varyContact(Contact fromContact, Member member);
	
	/**
	 * 
	 * <p>
	 * 组合查询联系人<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-3
	 * @param condition
	 * @return
	 * List<Contact>
	 */
	public List<Contact> searchContactByCondition(ContactCondition condition);
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-9-12
	 * @param contact
	 * @param user
	 * @param registerId
	 * @throws CrmBusinessException
	 * void
	 */
	void sendSmsToContact(Contact contact, User user, String registerId) throws CrmBusinessException;

	/**
	 * @发送短信验证码
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 * @throws CrmBusinessException
	 * @status 绑定业务变更，废弃
	 */
	public boolean sendValidatorMsg(String contactNumber,
			String orderContactMobile);
	
	/**
	 * @description 解绑联系人
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param linkmanId:联系人id
	 *@date 2012-6-29
	 * @return void
	 * @update 2013-6-19 上午8:49:16
	 */
	public void unboundContact(RegisterInfo registerInfo);
	
	/**
	 * @description 官网解绑/解绑联系人联系人
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param linkmanId:联系人id
	 * @param userName:官网用户名
	 * @param createUser:创建人
	 * @param operateType:操作类型("1":绑定，"0":解绑)
	 *@date 2012-6-29
	 * @return void
	 * @update 2013-6-19 上午8:49:16
	 */
	void boundContactForOnline(RegisterInfo registerInfo, String operateType);
	/**
	 * @description 根据条件查询渠道用户信息
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param linkmanId：联系人id
	 *@date 2012-6-29
	 * @return 官网用户名
	 * @update 2013-6-19 上午8:49:16
	 */
	List<RegisterInfo> queryRegisterInfo(RegisterInfo registerInfo);
	/**
	 * 
	* @Title: searchContactList
	* @Description: 根据联系方式查找联系人（有效和审批中的）
	* @author chenaichun 
	* @param @param phone
	* @param @param tel
	* @param @param name
	* @param @return    设定文件
	* @date 2014-3-4 下午4:59:17
	* @return List<Contact>    返回类型
	* @throws
	* @update 2014-3-4 下午4:59:17
	 */
	List<Contact> searchContactList(String phone, String tel, String name);
	
	/**
	 * 
	* @Title: searchContactByPhone
	* @Description: 根据手机号查找有效和审批中的联系人
	* @author chenaichun 
	* @param @param phone
	* @param @return    设定文件
	* @date 2014-3-4 下午5:00:28
	* @return List<Contact>    返回类型
	* @throws
	* @update 2014-3-4 下午5:00:28
	 */
	List<Contact> searchContactByPhone(String phone);
	
	/**
	 * 
	* @Title: searchContactByTelName
	* @Description: 根据固定电话和姓名查找有效和审批中的联系人
	* @author chenaichun 
	* @param @param tel
	* @param @param name
	* @param @return    设定文件
	* @date 2014-3-4 下午5:01:12
	* @return List<Contact>    返回类型
	* @throws
	* @update 2014-3-4 下午5:01:12
	 */
	List<Contact> searchContactByTelName(String tel, String name);
}
