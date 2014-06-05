package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.IntegralSearchCondition;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;

public interface IMemberIntegralDao {

	/**
	 * <p>
	 * Description:保存会员积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param memberIntegral
	 * void
	 */
	public void insertMemberIntegral(MemberIntegral memberIntegral);
	
	/**
	 * <p>
	 * Description:通过ID修改会员积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param memberIntegral
	 * void
	 */
	public void updateMemberIntegral(MemberIntegral memberIntegral);	

	/**
	 * <p>
	 * Description:通过ID查询会员积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param id
	 * @return
	 * MemberIntegral
	 */
	public MemberIntegral getMemberIntegralById(String id);
	
	/**
	 * <p>
	 * Description:通过会员积分实体条件 查询 统计其对应的数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param memberIntegral
	 * @return
	 * long
	 */
	public long countSearchMemberIntegrals(MemberIntegral memberIntegral);
	
	/**
	 * <p>
	 * Description:通过ID删除会员积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param id
	 * void
	 */
	public void deleteMemberIntegral(String id);

	/**
	 * <p>
	 * Description:通过会员积分实体条件 分页查询 会员积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param memberIntegral
	 * @param start
	 * @param limit
	 * @return
	 * List<MemberIntegral>
	 */
	List<MemberIntegral> searchMemberIntegrals(MemberIntegral memberIntegral,
			int start, int limit);
	
	/**
	 * <p>
	 * Description:通过积分查询条件  分页查询 会员积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param memberIntegCondition
	 * @param start
	 * @param limit
	 * @return
	 * List<MemberIntegral>
	 */
	public List<MemberIntegral> searchMemberIntegralsForCondition(IntegralSearchCondition memberIntegCondition,
			int start, int limit);

	/**
	 * <p>
	 * Description:通过积分查询条件 统计积分信息数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param memberIntegCondition
	 * @return
	 * long
	 */
	public long countSearchMemberIntegralsForCondition(
			IntegralSearchCondition memberIntegCondition);

	/**
	 * <p>
	 * Description:通过会员ID删除会员积分<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param memberId
	 * void
	 */
	public void deleteMemberIntegralByMemberId(
			String memberId);

}
