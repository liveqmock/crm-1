package com.deppon.crm.module.workflow.server.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.workflow.server.dao.IAmountConfigDao;
import com.deppon.crm.module.workflow.server.util.AmountConfigEntity;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 *<pre>
 *功能:审批权限金额配置DAO实现
 *作者：andy
 *日期：2013-8-13 下午6:02:03
 *</pre>
 */
public class AmountConfigDaoImpl extends iBatis3DaoImpl implements IAmountConfigDao {
	//命名空间
	private static final String NAMESPACE = "com.deppon.crm.module.workflow.server.dao.impl.AmountConfigDaoImpl.";
	
	private static final String QUERY_FOR_BRANCH = "queryForBranch";
	private static final String QUERY_ALL_BRANCH = "queryAllBranch";
	private static final String GET_COUNT_BRANCH = "getCountBranch";
	private static final String INSERT_BRANCH    = "insertBranch";
	private static final String UPDATE_BRANCH    = "updateBranch";
	private static final String DELETE_BRANCH    = "deleteBranch";
	private static final String VALIDBRANCH      = "validBranch";
		
	/**
	 * 
	 *<pre>
	 * 方法体说明：根据条件查询金额配置
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:55:08
	 * @param amountConfigEntity
	 * @return
	 *</pre>
	 */
	@Override
	public AmountConfigEntity queryForBranch(
			AmountConfigEntity amountConfigEntity) {
		return (AmountConfigEntity)this.getSqlSession().selectOne(NAMESPACE + QUERY_FOR_BRANCH, amountConfigEntity);
	}
	/**
	 * 
	 *<pre>
	 * 方法体说明：查询金额配置列表
	 * 作者： andy
	 * 日期： 2013-8-13 下午5:53:54
	 * @param amountConfigEntity 查询参数
	 * @param start 分页信息，页的起始行
	 * @param limit 分页信息，页的行数
	 * @return 指定条件的金额配置列表
	 *</pre>
	 */
	public List<AmountConfigEntity> queryAllBranch(
			AmountConfigEntity amountConfigEntity,int start,int limit){
		RowBounds bounds = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAMESPACE + QUERY_ALL_BRANCH, amountConfigEntity,bounds);
	}

	/**
	 * 
	 *<pre>
	 * 方法体说明：查询金额配置列表的结果集总数
	 * 作者： andy
	 * 日期： 2013-8-13 下午5:56:03
	 * @param amountConfigEntity 查询参数
	 * @return 金额配置列表的结果集总数
	 *</pre>
	 */
	@Override
	public Long getBranchCount(AmountConfigEntity amountConfigEntity) {
		return (Long)this.getSqlSession().selectOne(NAMESPACE + GET_COUNT_BRANCH, amountConfigEntity);
	}

	/**
	 * 
	 *<pre>
	 * 方法体说明：根据ID删除金额配置信息
	 * 作者： andy
	 * 日期： 2013-8-13 下午5:59:50
	 * @param id 配置ID
	 *</pre>
	 */
	@Override
	public void deleteById(String id) {
		this.getSqlSession().delete(NAMESPACE + DELETE_BRANCH, id);
	}

	/**
	 * 
	 *<pre>
	 * 方法体说明：检查业务约束
	 * 1、工作流名称，当前审批环节相同时，金额范围不能存在交集。（如：常规理赔申请-区域经理：1000-5000，则下一条常规理赔申请-区域经理，金额上限必须小于等于1000，金额下限必须大于5000）
	 * 2、目标审批环节和当前审批环节不能相同。
	 * 3、不能存在2条工作流名称，当前审批环节，目标审批环节相同的配置。
	 * 作者： andy
	 * 日期： 2013-8-13 下午5:56:03
	 * @param amountConfigEntity 查询参数
	 * @return 是否符合业务规则
	 *</pre>
	 */
	@Override
	public boolean isValid(AmountConfigEntity amountConfigEntity) {
		Long count = (Long) this.getSqlSession().selectOne(NAMESPACE + VALIDBRANCH, amountConfigEntity);
		if(count > 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 *<pre>
	 * 方法体说明：增加金额配置
	 * 作者： andy
	 * 日期： 2013-8-13 下午5:53:54
	 * @param amountConfigEntity 查询参数
	 * @param start 分页信息，页的起始行
	 * @param limit 分页信息，页的行数
	 * @return 指定条件的金额配置列表
	 *</pre>
	 */
	@Override
	public void insertBranch(AmountConfigEntity amountConfigEntity) {
		this.getSqlSession().insert(NAMESPACE + INSERT_BRANCH, amountConfigEntity);
	}
	
	/**
	 * 
	 *<pre>
	 * 方法体说明：修改金额配置
	 * 作者： andy
	 * 日期： 2013-8-13 下午5:53:54
	 * @param amountConfigEntity 查询参数
	 * @param start 分页信息，页的起始行
	 * @param limit 分页信息，页的行数
	 * @return 指定条件的金额配置列表
	 *</pre>
	 */
	@Override
	public void updateBranch(AmountConfigEntity amountConfigEntity) {
		this.getSqlSession().insert(NAMESPACE + UPDATE_BRANCH, amountConfigEntity);
	}

}
