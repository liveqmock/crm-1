package com.deppon.crm.module.sysmail.server.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.sysmail.shared.domain.MailAccount;
import com.deppon.crm.module.sysmail.shared.domain.MailGroup;
import com.deppon.crm.module.sysmail.shared.exception.MailExceptionType;

public class ValidateMailUtils {
	//邮件格式
	private static String regex  = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";//"\\w+@(\\w+.)+[a-z]{2,3}";
	//特殊字符 匹配由数字、26个英文字母或者下划线组成的字符串
	private static String specialChar = "^\\w+$";
	//http://www.myexception.cn/program/602029.html
	
	/**
	 * 
	 * <p>
	 * 验证账号信息<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-18
	 * @param ma
	 * void
	 */
	public static void validateAccount(MailAccount ma){
		//1.ma不能为空
		validateMailAccountObject(ma);
		//2.接收人名称不能为空
		validateReceiverName(ma.getReceiverName());
		//3.邮件地址不能为空并且格式正确
		validateMailAddress(ma.getEmailAddress());
	}
	
	

	/**
	 * 
	 * <p>
	 * 验证邮件组属性<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param mg
	 * void
	 */
	public static void validateGroup(MailGroup mg) {
		//1.mg不能为空
		validateMailGroupObject(mg);
		//2.邮件组名称不能为空
		validateMailGroupName(mg.getGroupName());
		//3.邮件组编码不能为空
		validateMailGroupCode(mg.getGroupCode());
		//4.邮件组编码不能包含特殊字符
		validateSpecialChars(mg.getGroupCode());
	}
	
	/**
	 * 
	 * <p>
	 * 邮件组编码不能为空<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param groupCode
	 * void
	 */
	static void validateMailGroupCode(String groupCode){
		if(StringUtils.isEmpty(groupCode)){
			MailExceptionUtils.createMailException(MailExceptionType.MailGroupCodeIsNull, new Object[]{});
		}
	}
	/**
	 * 
	 * <p>
	 * 邮件组名称不能为空<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param groupName
	 * void
	 */
	static void validateMailGroupName(String groupName){
		if(StringUtils.isEmpty(groupName)){
			MailExceptionUtils.createMailException(MailExceptionType.MailGroupNameIsNull, new Object[]{});
		}
	}
	/**
	 * 
	 * <p>
	 * 邮件组对象不能为空<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param mg
	 * void
	 */
	static void validateMailGroupObject(MailGroup mg){
		if(mg==null){
			MailExceptionUtils.createMailException(MailExceptionType.MailGroupIsNull, new Object[]{});
		}
	}
	/**
	 * 
	 * <p>
	 * 接收人对象不能为空<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-18
	 * @param ma
	 * void
	 */
	static void validateMailAccountObject(MailAccount ma){
		if(ma == null){
			MailExceptionUtils.createMailException(MailExceptionType.MailAccountIsNull, new Object[]{});
		}
	}
	/**
	 * 
	 * <p>
	 * 验证接收人名称不能为空<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-18
	 * @param receiverName
	 */
	 static void validateReceiverName(String receiverName){
		if(StringUtils.isEmpty(receiverName)){
			MailExceptionUtils.createMailException(MailExceptionType.MailReceiverNameIsNull, new Object[]{});
		}
	}
	 
	 /**
	  * 
	  * <p>
	  * 验证邮件地址<br />
	  * </p>
	  * @author suyujun
	  * @version 0.1 2013-9-18
	  * @param mailAddress
	  * void
	  */
	static void validateMailAddress(String mailAddress){
		if(StringUtils.isEmpty(mailAddress)){
			MailExceptionUtils.createMailException(MailExceptionType.MailAddressIsNull, new Object[]{});
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mailAddress);
		if(!m.matches()){
			MailExceptionUtils.createMailException(MailExceptionType.MailAddressIsInvalid, new Object[]{});
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-24
	 * @param list
	 * @param ma
	 * void
	 */
	public static void validateRepeatAddress(List<MailAccount> list,MailAccount ma) {
		if(StringUtils.isNotEmpty(ma.getId())){
			for(int i = 0;i < list.size();i++){
				if(ma.getId().equals(list.get(i).getId())){
					list.remove(i);
				}
			}			
		}
		
		String newAddress = ma.getEmailAddress();
		for(MailAccount nma:list){
			if(nma.getEmailAddress().equals(newAddress)){
				MailExceptionUtils.createMailException(MailExceptionType.SameMailAddressException, new Object[]{});
			}
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-24
	 * @param emailAddress
	 * void
	 */
	static void validateSpecialChars(String toCheckString){
		Pattern p = Pattern.compile(specialChar);
		Matcher m = p.matcher(toCheckString);
		if(!m.matches()){
			MailExceptionUtils.createMailException(MailExceptionType.IncludeSpecialChar, new Object[]{});
		}
	}



	/**
	 * 
	 * <p>
	 * 修改，新增时验证重复<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-25
	 * @param mgs
	 * @param mg
	 * void
	 */
	public static void validateRepeatGroup(List<MailGroup> mgs, MailGroup mg) {
		
		if(StringUtils.isNotEmpty(mg.getId())){
			for(int i = 0;i < mgs.size();i++){
				if(mg.getId().equals(mgs.get(i).getId())){
					mgs.remove(i);
				}
			}			
		}
		
		for(MailGroup nmg:mgs){
			if(nmg.getGroupName().equals(mg.getGroupName())){
				MailExceptionUtils.createMailException(MailExceptionType.SameMailGroupName, new Object[]{});
			}
			
			if(nmg.getGroupCode().equals(mg.getGroupCode())){
				MailExceptionUtils.createMailException(MailExceptionType.SameMailGroupCode, new Object[]{});
			}
		}
	}
}
