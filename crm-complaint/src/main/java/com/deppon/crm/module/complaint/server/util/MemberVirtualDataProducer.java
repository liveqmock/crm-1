/**
 * Filename:	VirtualDataProducer.java
 * Description:
 * Copyright:	Copyright (c)2011
 * Company:		IBM1
 * @author:		
 * @version:	
 * create at:	2012-4-21 下午2:24:48
 *
 * Modification History:
 * Date			Author			Version			Description
 * ------------------------------------------------------------------------
 * 2012-4-21	    
 */

package com.deppon.crm.module.complaint.server.util;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.manager.impl.MemberManager;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.order.shared.domain.Order;


public class MemberVirtualDataProducer extends MemberManager implements IMemberManager{
	/**
	 * 
	 * <p>
	 * 根据会员编码查询会员信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @param custNumber
	 * @return
	 * Member
	 */
	public Member getMemberByCustNumber(String custNumber){
		Member member=new Member();
		
		return member;
	}
	
	public List<Member> searchMemberByTel(String tel){
		Member member1=new Member();
		Member member2=new Member();
		List list=new ArrayList();
		list.add(member1);
		list.add(member2);
		return list;
	}
	
	
}
