package com.deppon.crm.module.workflow.server.service.impl;

import java.util.List;
import com.deppon.crm.module.workflow.server.dao.IAmountConfigDao;
import com.deppon.crm.module.workflow.server.service.IAmountConfigService;
import com.deppon.crm.module.workflow.server.util.AmountConfigEntity;

/**
 * 
 *<pre>
 *功能: 审批金额配置业务层的实现类
 *作者：andy
 *日期：2013-8-12下午5:30:09
 *</pre>
 */
public class AmountConfigServiceImpl implements IAmountConfigService {

	//审批权限金额配置DAO层对象
	private IAmountConfigDao amountConfigDao;

	public IAmountConfigDao getAmountConfigDao() {
		return amountConfigDao;
	}

	public void setAmountConfigDao(IAmountConfigDao amountConfigDao) {
		this.amountConfigDao = amountConfigDao;
	}

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
			AmountConfigEntity amountConfigEntity){
		return amountConfigDao.queryForBranch(amountConfigEntity);
	}
	
	/**
	 * 
	 *<pre>
	 * 方法体说明：查询金额配置列表
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:53:54
	 * @param amountConfigEntity 查询参数
	 * @return 指定条件的金额配置列表
	 *</pre>
	 */
	@Override
	public List<AmountConfigEntity> queryAllBranch(
			AmountConfigEntity amountConfigEntity,int start,int limit){
		return amountConfigDao.queryAllBranch(amountConfigEntity,start,limit);
	}

	/**
	 * 
	 *<pre>
	 * 方法体说明：查询金额配置列表的结果集总数
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:56:03
	 * @param amountConfigEntity 查询参数
	 * @return 金额配置列表的结果集总数
	 *</pre>
	 */
	@Override
	public Long getBranchCount(AmountConfigEntity amountConfigEntity) {
		return amountConfigDao.getBranchCount(amountConfigEntity);
	}
	
	
	/**
	 * 
	 *<pre>
	 * 方法体说明：更新金额配置
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:57:25
	 * @param amountConfigEntity
	 *</pre>
	 */
	@Override
	public void insertBranch(AmountConfigEntity amountConfigEntity) {
		 amountConfigDao.insertBranch(amountConfigEntity);
	}
	
	/**
	 * 
	 *<pre>
	 * 方法体说明：更新金额配置
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:57:25
	 * @param amountConfigEntity
	 *</pre>
	 */
	@Override
	public void updateBranch(AmountConfigEntity amountConfigEntity) {
		 amountConfigDao.updateBranch(amountConfigEntity);
	}

	
	/**
	 * 
	 *<pre>
	 * 方法体说明：根据ID删除金额配置信息
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:59:50
	 * @param id 配置ID
	 *</pre>
	 */
	@Override
	public void deleteById(String id) {
		amountConfigDao.deleteById(id);
	}

	/**
	 * 
	 *<pre>
	 * 方法体说明：检查业务约束
	 * 1、工作流名称，当前审批环节相同时，金额范围不能存在交集。（如：常规理赔申请-区域经理：1000-5000，则下一条常规理赔申请-区域经理，金额上限必须小于等于1000，金额下限必须大于5000）
	 * 2、目标审批环节和当前审批环节不能相同。
	 * 3、不能存在2条工作流名称，当前审批环节，目标审批环节相同的配置。
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:56:03
	 * @param amountConfigEntity 查询参数
	 * @return 是否符合业务规则
	 *</pre>
	 */
	@Override
	public boolean isValid(AmountConfigEntity amountConfigEntity) {
		return amountConfigDao.isValid(amountConfigEntity);
	}
	
}
