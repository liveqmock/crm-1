package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactCondition;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.customer.shared.domain.Order;
import com.deppon.crm.module.customer.shared.domain.RandomNumber;

/**   
 * <p>
 * Description:联系人接口dao<br />
 * </p>
 * @title IContactDao.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author 李国文
 * @version 0.1 2013-2-26
 */
public interface IContactDao {
	
	
	/**
	 * <p>
	 * Description:根据联系人的信息 查询联系人<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param contact
	 * @return
	 * List<Contact>
	 */
	List<Contact> searchContacts(Contact contact);
	
	/**
	 * <p>
	 * Description:查询联系人中 身份中相同的 总数  --不是无效的<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param idCard
	 * @return
	 * int
	 */
	int getContactCountByIdCard(String idCard);

	/**
	 * <p>
	 * Description:查询手机号码相同的联系人<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param phone
	 * @return
	 * List<Contact>
	 */
	List<Contact> getContactByPhone(String phone);

	/**
	 * <p>
	 * Description:通过电话号码和联系人姓名 去联系人查询信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param tel
	 * @param name
	 * @return
	 * List<Contact>
	 */
	List<Contact> getContactByTelAndName(String tel, String name);

	/**
	 * <p>
	 * Description:联系人中查询 手机号码相同的 总数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param phone
	 * @return
	 * int
	 */
	int getContactCountByPhone(String phone);

	/**
	 * <p>
	 * Description:联系人中查询 电话号码和联系人姓名 两个条件相同的 总数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param tel
	 * @param name
	 * @return
	 * int
	 */
	int getContactCountByTelAndName(String tel, String name);

	/**
	 * <p>
	 * Description:通过会员ID 删除 联系人<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param memberId
	 * void
	 */
	void deleteContactByMemberId(String memberId);
	
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
	 * @保存随机编码
	 * @author 赵斌
	 * @2012-4-24
	 * @return 
	 * @param phone 手机号
	 * @param number 随机码
	 */
	public void saveRandomPhone(String phone,String number);
	
	/**
	 * 
	 * @根据手机号得到验证码
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	public List<RandomNumber> getRandomNumberByPhone(String phone);
	
	/**
	 * 
	 * @保存联系人-绑定联系人
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	public boolean saveContact(Contact contact);
	
	/**
	 * 
	 * @绑定联系人
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	public boolean boundContact(Contact contact);
	
	/**
	 * 
	 * @根据渠道订单号查询订单信息
	 * @author 赵斌
	 * @2012-4-25
	 * @return
	 */
	public Order queryOrderByChannelNumber(String channelNumber);
	
	/**
	 * 
	 * @根据渠道订单号查询订单信息
	 * @author 赵斌
	 * @2012-4-25
	 * @return
	 */
	public Order getOrderByNumber(String number);

	/**
	 * 
	 * <p>
	 * 修改联系人<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-28
	 * @param updateContact
	 * void
	 */
	void updateContact(Contact updateContact);

	/**
	 * <p>
	 * Description:根据 联系人积分查询实体条件 查询 联系人信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @return
	 * List<Contact>
	 */
	List<Contact> searchContactByCondition(ContactCondition condition);

	/**
	 * @description 根据手机号删除以前的验证码.  .  
	 * @author 潘光均
	 * @version 0.1 2012-6-29
	 * @param 
	 *@date 2012-6-29
	 * @return void
	 * @update 2012-6-29 上午8:49:16
	 */
	void deleteRandomCodeByMobile(String conPhone);
	/**
	 * @description 根据联系人id获取官网用户名  .  
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param 
	 *@date 2012-6-29
	 * @return void
	 * @update 2013-6-19 上午8:49:16
	 */
	String getOnlineLinkmanId(String linkManId);
	
	/**
	 * @description 新增官网用户名 ，联系人id对于关系  
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param 
	 *@date 2012-6-29
	 * @return void
	 * @update 2013-6-19 上午8:49:16
	 */

	void insertContactOnlineNum(RegisterInfo info);
	
	/**
	 * @description 更新官网用户名 ，联系人id对应关系  
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param 
	 *@date 2012-6-29
	 * @return void
	 * @update 2013-6-19 上午8:49:16
	 */
	void updateContactOnlineNum(RegisterInfo registerInfo);
	
	/**
	 * @description 根据联系人渠道用户名和客户来源解绑联系人
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param contactId:联系人id
	 *@date 2012-6-29
	 * @return void
	 * @update 2013-6-19 上午8:49:16
	 */
	 public void unboundContactOnlineNum(RegisterInfo registerInfo);
	/**
	 * @description 根据联系人id解绑联系人
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param contactId:联系人id
	 *@date 2012-6-29
	 * @return void
	 * @update 2013-6-19 上午8:49:16
	 */
	void unboundContactOnlineNum(String contactId);
	/**
	 * @description 将联系人表中的官网id置空
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param linkmanId：联系人id
	 *@date 2012-6-29
	 * @return void
	 * @update 2013-6-19 上午8:49:16
	 */
	void changeContactOnlineNum(RegisterInfo registerInfo);

	/**
	 * <p>
	 * 根据条件查询渠道用户信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-7-8
	 * @param registerInfo
	 * @return 
	 * List<RegisterInfo>
	 */
	List<RegisterInfo> getRegisterInfo(RegisterInfo registerInfo);

	/**
	 * 
	* @Title: searchContactList
	* @Description: 根据联系方式查询有效和审批中的联系人信息
	* @author chenaichun 
	* @param @param contact
	* @param @return    设定文件
	* @date 2014-3-4 下午5:15:37
	* @return List<Contact>    返回类型
	* @throws
	* @update 2014-3-4 下午5:15:37
	 */
	List<Contact> searchContactList(Contact contact);
}
