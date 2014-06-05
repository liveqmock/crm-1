package com.deppon.crm.module.bps.server.service.impl;
import com.deppon.crm.module.bps.server.dao.IAmountConfigDao;
import com.deppon.crm.module.bps.server.service.IAmountConfigService;
import com.deppon.crm.module.bps.server.util.AmountConfigEntity;
import com.deppon.foss.framework.shared.util.string.StringUtil;

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
	 * 获取业务编码6位数字
	 * @return
	 */
	@Override
	public int getNextSuffix() {
		return amountConfigDao.getNextSuffix();
	}



	@Override
	public void updateNoSuffix(int nextSuffix) {
		amountConfigDao.updateClaimNoSuffix(nextSuffix);
	}



	@Override
	public void insertNoSuffix(int nextSuffix) {
		amountConfigDao.insertClaimNoSuffix(nextSuffix);
	}

	@Override
	public int getProcNextSuffix() {
		return amountConfigDao.getProcNextSuffix();
	}
}
