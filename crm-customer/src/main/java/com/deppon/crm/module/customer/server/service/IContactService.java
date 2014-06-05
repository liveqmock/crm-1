package com.deppon.crm.module.customer.server.service;

import java.util.List;

import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactCondition;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.customer.shared.domain.Order;
import com.deppon.crm.module.customer.shared.domain.RandomNumber;

public interface IContactService {
	
	/**
	 * <p>
	 * 根据手机，电话，名称查询联系人<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-21
	 * @param phone 联系人手机
	 * @param tel 联系人
	 * @param name
	 * @return
	 * Contact
	 */
	@Deprecated
	public List<Contact> getContact(String phone,String tel,String name);
	
	/**
	 * <p>
	 * 查询联系人<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-27
	 * @param contact
	 * @return
	 * List<Contact> 注意返回的Contact对象没有Member对象
	 */
	List<Contact> searchContacts(Contact contact);
	
	/**
	 * <p>
	 * 根据idCard查询联系人<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-22
	 * @param idCard
	 * @return
	 * Contact
	 */
	public int getContactCountByIdCard(String idCard);
	
	/**
	 * <p>
	 * Description:联系人中查询 电话号码和联系人姓名 两个条件相同的 总数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param tel
	 * @param name
	 * @return
	 * int
	 */
	public int getContactCountByTelAndName(String tel, String name);
	
	/**
	 * <p>
	 * Description:联系人中查询 手机号码相同的 总数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param phone
	 * @return
	 * int
	 */
	public int getContactCountByPhone(String phone);
	
	/**
	 * <p>
	 * 根据会员号查询联系人信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-30
	 * @param memberId
	 * @return
	 * List<Contact>
	 */
	public List<Contact> getContactsByMemberId(String memberId);
	
	/**
	 * <p>
	 * 根据会员编码查询联系人<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-5
	 * @param memberNumber
	 * @return
	 * List<Contact>
	 */
	@Deprecated
	public List<Contact> getContactsByMemberNumber(String memberNumber);
	
	/**
	 * @根据联系人编码获得联系人信息
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	public ContactView getContactByNumber(String number);
	
	/**
	 * @保存随机编码
	 * @author 赵斌
	 * @2012-4-24
	 * @return 
	 * @param phone 手机号
	 * @param number 随机码
	 */
	public void saveRandomPhone(String phone,String number);
	
	/**
	 * @根据手机号得到验证码
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	public List<RandomNumber> getRandomNumberByPhone(String phone);
	
	/**
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
	 * @param registerInfo 
	 * @2012-4-24
	 * @return
	 */
	public boolean boundContact(Contact contact, RegisterInfo registerInfo);
	
	/**
	 * @根据渠道订单号查询订单信息
	 * @author 赵斌
	 * @2012-4-25
	 * @return
	 */
	public Order queryOrderByChannelNumber(String channelNumber);
	
	/**
	 * @根据德邦订单号查询订单信息
	 * @author 赵斌
	 * @2012-4-25
	 * @return
	 */
	public Order getOrderByNumber(String number);
	
	/**
	 * <p>
	 * 修改联系人<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-28
	 * @param updateContact
	 * void
	 */
	public void updateContact(Contact updateContact);
	
	/**
	 * <p>
	 * Description:根据 联系人积分查询实体条件 查询 联系人信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param condition
	 * @return
	 * List<Contact>
	 */
	public List<Contact> searchContactByCondition(ContactCondition condition);
	
	/**
	 * @description 根据手机号删除以前的验证码.  
	 * @author 潘光均
	 * @version 0.1 2012-6-29
	 * @param 
	 *@date 2012-6-29
	 * @return void
	 * @update 2012-6-29 上午8:48:27
	 */
	public void deleteRandomCodeByMobile(String conPhone);

	/**
	 * @description 解绑联系人
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param 
	 *@date 2012-6-29
	 * @return void
	 * @update 2013-6-19 上午8:49:16
	 */
	public void unboundContactOnlineNum(RegisterInfo registerInfo);
	
	
	/**
	 * @description 根据联系人id查询官网用户名
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param userName：官网用户名
	 *@date 2012-6-29
	 * @return 联系人id
	 * @update 2013-6-19 上午8:49:16
	 */
	public String queryLinkmanIdByOnlineNum(String linkmanId);

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
	public List<RegisterInfo> searchRegisterInfo(
			RegisterInfo registerInfo);
	
	/**
	 * 
	* @Title: searchContactList
	* @Description: 根据联系方式查找联系人（有效和审批中）
	* @author chenaichun 
	* @param @param contact
	* @param @return    设定文件
	* @date 2014-3-4 下午5:11:29
	* @return List<Contact>    返回类型
	* @throws
	* @update 2014-3-4 下午5:11:29
	 */
	public List<Contact> searchContactList(Contact contact);

}
