package com.deppon.crm.module.bps.server.dao;

import java.util.List;
import com.deppon.crm.module.bps.server.util.AmountConfigEntity;

/**
 * 
 * <pre>
 * 功能:审批权限金额配置交互接口 用于操作T_CRM_AMOUNT_CONFIG 表信息
 * 作者：andy
 * 日期：2013-4-11下午5:49:03
 * </pre>
 */
public interface IAmountConfigDao {
	/**
	 * 
	 * <pre>
	 * 方法体说明：根据条件查询金额配置
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:55:08
	 * @param amountConfigEntity
	 * @return
	 * </pre>
	 */
	public AmountConfigEntity queryForBranch(
			AmountConfigEntity amountConfigEntity);

	/**
	 * 获取业务编码6位数字
	 * 
	 * @return
	 */
	public int getNextSuffix();

	void updateClaimNoSuffix(int nextSuffix);

	void insertClaimNoSuffix(int nextSuffix);
	
	int getProcNextSuffix();
	
}
