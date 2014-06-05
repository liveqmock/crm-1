package com.deppon.crm.module.bps.server.service;

import java.util.List;
import com.deppon.crm.module.bps.server.util.AmountConfigEntity;


/**
 * 
 *<pre>
 *功能: 审批权限金额配置业务逻辑层接口
 *作者：andy
 *日期：2013-8-12 下午16:24:14
 *</pre>
 */
public interface IAmountConfigService {

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
	public AmountConfigEntity queryForBranch(
			AmountConfigEntity amountConfigEntity);
	/**
	 * 获取业务编码6位数字
	 * @return
	 */
	int getNextSuffix();

	void updateNoSuffix(int nextSuffix);
	
	void insertNoSuffix(int nextSuffix);
	
	int getProcNextSuffix();
	
}
