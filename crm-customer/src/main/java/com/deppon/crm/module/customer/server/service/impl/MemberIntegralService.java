package com.deppon.crm.module.customer.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.deppon.crm.module.customer.server.dao.IMemberIntegralDao;
import com.deppon.crm.module.customer.server.service.IMemberIntegralService;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralSearchCondition;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;

public class MemberIntegralService implements IMemberIntegralService{
	
	private IMemberIntegralDao memberIntegralDao;
	
	public void setMemberIntegralDao(IMemberIntegralDao memberIntegralDao) {
		this.memberIntegralDao = memberIntegralDao;
	}

	@Override
	public void insertMemberIntegral(MemberIntegral memberIntegral) {
		memberIntegralDao.insertMemberIntegral(memberIntegral);
	}

	@Override
	public void updateMemberIntegral(MemberIntegral memberIntegral) {
		memberIntegralDao.updateMemberIntegral(memberIntegral);
	}

	@Override
	public MemberIntegral getMemberIntegralById(String id) {
		return memberIntegralDao.getMemberIntegralById(id);
	}

	@Override
	public long countSearchMemberIntegrals(MemberIntegral memberIntegral) {
		return memberIntegralDao.countSearchMemberIntegrals(memberIntegral);
	}

	@Override
	public void deleteMemberIntegral(String id) {
		memberIntegralDao.deleteMemberIntegral(id);
	}

	@Override
	public List<MemberIntegral> searchMemberIntegrals(
			MemberIntegral memberIntegral, int start, int limit) {
		return memberIntegralDao.searchMemberIntegrals(memberIntegral, start, limit);
	}

	@Override
	public MemberIntegral getMemberIntegralByMemberId(String memberId) {
		if(ValidateUtil.objectIsEmpty(memberId)){
			return null;
		}
		
		MemberIntegral memberIntegral = new MemberIntegral();
		memberIntegral.getMember().setId(memberId);
		List<MemberIntegral> list = memberIntegralDao.searchMemberIntegrals(memberIntegral, 0, 1);
		if(list.isEmpty()){
			memberIntegralDao.insertMemberIntegral(memberIntegral);
			return memberIntegralDao.searchMemberIntegrals(memberIntegral, 0, 1).get(0);
		}else{
			return list.get(0);
		}
	}
	/**************************************李志国添加****************************************************/
	/**
	 * 
	 * <p>根据会员积分条件，得到会员积分列表<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param memberIntegCondition
	 * @return
	 * List<MemberIntegral>
	 */
	public List<MemberIntegral> searchMemberIntegralsForCondition(IntegralSearchCondition memberIntegCondition,int start,int limit){
		return memberIntegralDao.searchMemberIntegralsForCondition(memberIntegCondition, start, limit);
	}

	@Override
	//TODO 待优化
	public List<MemberIntegral> getMemberIntegralByMemberIds(
			Set<String> memberIds) {
		List<MemberIntegral> list = new ArrayList<MemberIntegral>();
		for (String memberId : memberIds) {
			list.add(getMemberIntegralByMemberId(memberId));
		}
		return list;
	}

	@Override
	public long countSearchMemberIntegralsForCondition(
			IntegralSearchCondition memberIntegCondition) {
		return memberIntegralDao.countSearchMemberIntegralsForCondition(memberIntegCondition);
	}
}
