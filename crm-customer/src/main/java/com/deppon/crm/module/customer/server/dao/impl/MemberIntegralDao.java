package com.deppon.crm.module.customer.server.dao.impl;


import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.deppon.crm.module.customer.server.dao.IMemberIntegralDao;
import com.deppon.crm.module.customer.server.utils.SqlUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralSearchCondition;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 *
 * <p>
 * Description:会员积分dao<br />
 * </p>
 * @title MemberIntegralDao.java
 * @package com.deppon.crm.module.customer.server.dao.impl 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class MemberIntegralDao extends iBatis3DaoImpl implements IMemberIntegralDao{
	/**
	 * 命名空间
	 */
	private static final String NAME_SPACE = "com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral.";
	/**
	 * 
	 * <p>
	 * Description:插入<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param memberIntegral
	 *
	 */
	@Override
	public void insertMemberIntegral(MemberIntegral memberIntegral) {
		//执行
		this.getSqlSession().insert(NAME_SPACE+"insertMemberIntegral",memberIntegral);
	}
	/**
	 * 
	 * <p>
	 * Description:更新<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param memberIntegral
	 *
	 */
	@Override
	public void updateMemberIntegral(MemberIntegral memberIntegral) {
		//更新
		this.getSqlSession().update(NAME_SPACE+"updateMemberIntegral",memberIntegral);
	}
	/**
	 * 
	 * <p>
	 * Description:根据id得到<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * @return
	 *
	 */
	@Override
	public MemberIntegral getMemberIntegralById(String id) {
		//封装条件
		MemberIntegral memberIntegral = new MemberIntegral();
		//设置条件
		memberIntegral.setId(id);
		//查询
		List<MemberIntegral> list = searchMemberIntegrals(memberIntegral,0,1);
		//校验结果是否我空
		if(ValidateUtil.objectIsEmpty(list)){
			return null;
		}else{
			//获得第一条
			return list.get(0);
		}
	}
	/**
	 * 
	 * <p>
	 * Description:查询<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param memberIntegral
	 * @param start
	 * @param limit
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberIntegral> searchMemberIntegrals(MemberIntegral memberIntegral,int start,int limit) {
		//设置分页
		RowBounds rowBounds = new RowBounds(start,limit);
		//查询
		return this.getSqlSession().selectList(NAME_SPACE+"searchMemberIntegrals", memberIntegral,rowBounds);
	}
	/**
	 * 
	 * <p>
	 * Description:删除<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 *
	 */
	@Override
	public void deleteMemberIntegral(String id) {
		/**
		 * 根据id删除
		 */
		this.getSqlSession().delete(NAME_SPACE+"deleteMemberIntegral",id);
	}
	/**
	 * 
	 * <p>
	 * Description:统计条数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param memberIntegral
	 * @return
	 *
	 */
	@Override
	public long countSearchMemberIntegrals(MemberIntegral memberIntegral) {
		//查询统计条数
		return (Long) this.getSqlSession().selectOne(NAME_SPACE+"countSearchMemberIntegrals", memberIntegral);
	}
	/**
	 * 
	 * <p>
	 * Description:查询<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param memberIntegCondition
	 * @param start
	 * @param limit
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberIntegral> searchMemberIntegralsForCondition(
			IntegralSearchCondition memberIntegCondition, int start, int limit) {
		//设置分页
		RowBounds rowBounds = new RowBounds(start,limit);
		/**
		 * 封装查询条件
		 */
		memberIntegCondition.setContactName(SqlUtil.getLikeField(memberIntegCondition.getContactName()));
		/**
		 * 封装查询条件
		 */
		memberIntegCondition.setMemberName(SqlUtil.getLikeField(memberIntegCondition.getMemberName()));
		//查询
		return this.getSqlSession().selectList(NAME_SPACE+"searchMemberIntegralsForCondition", memberIntegCondition,rowBounds);
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param memberIntegCondition
	 * @return
	 *
	 */
	@Override
	public long countSearchMemberIntegralsForCondition(
			IntegralSearchCondition memberIntegCondition) {
		/**
		 * 封装查询条件
		 */
		memberIntegCondition.setContactName(SqlUtil.getLikeField(memberIntegCondition.getContactName()));
		/**
		 * 封装查询条件
		 */
		memberIntegCondition.setMemberName(SqlUtil.getLikeField(memberIntegCondition.getMemberName()));
		//查询统计
		return (Long) this.getSqlSession().selectOne(NAME_SPACE+"countSearchMemberIntegralsForCondition", memberIntegCondition);
	}
	/**
	 * 
	 * <p>
	 * Description:根据会员id删除<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param memberId
	 *
	 */
	@Override
	public void deleteMemberIntegralByMemberId(String memberId) {
		/**
		 * 根据会员id删除
		 */
		getSqlSession().update(NAME_SPACE + "deleteMemberIntegralByMemberId", memberId);
	
	}
}
