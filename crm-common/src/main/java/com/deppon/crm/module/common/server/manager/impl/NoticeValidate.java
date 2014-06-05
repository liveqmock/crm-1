package com.deppon.crm.module.common.server.manager.impl;

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
			if(notice.getTitle()!=null&&!"".equals(notice.getTitle())&&notice.getContent()!=null&&!"".equals(notice.getContent())){
				return true;
			}
			else{
				return false;
			}
		}
		else return false;
		
		
		
		
	}
}
