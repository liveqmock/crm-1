package com.deppon.crm.module.recompense.server.dao.impl;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.recompense.server.dao.IPaymentDao;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**作者：zouming
 *创建时间：2013-1-7
 *最后修改时间：2013-1-7
 *描述：
 */
public class PaymentDao extends iBatis3DaoImpl implements IPaymentDao{
	private static final String NAMESPACE = "com.deppon.crm.recompense.domain.impl.PaymentDao.";
	
	/**
	 * 
	 * <p>
	 * Description:模糊匹配查询开户银行信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-26上午11:14:11
	 * @param name
	 * @return
	 * List<AccountBank>
	 * @update 2013-1-26上午11:14:11
	 */
	@SuppressWarnings("unchecked")
	public List<AccountBank> searchBankListByName(String name){
		return (List<AccountBank>)this.getSqlSession().selectList(
				NAMESPACE + "searchBankListByName", name);
	}
}
