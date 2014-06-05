package com.deppon.crm.module.common.server.validate;

import com.deppon.crm.module.common.shared.domain.Notice;

/**
 * Description: NoticeValidate.java Create on 2012-10-9 下午6:22:36
 * 
 * @author xhl
 * @version 1.0
 */
public class NoticeValidate {
	public  static boolean  validate(Notice notice){
		if(notice!=null){
			if(notice.getContent()!=null&&notice.getContent().length()>10){
				return true;
			}
			else{
				return false;
			}
		}
		else return false;
		
		
		
		
	}
}
