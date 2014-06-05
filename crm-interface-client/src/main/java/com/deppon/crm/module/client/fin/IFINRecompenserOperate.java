package com.deppon.crm.module.client.fin;

import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.fin.domain.CashierAccountInfo;
import com.deppon.crm.module.client.fin.domain.NoBillingRecompenseInfo;

public interface IFINRecompenserOperate {
	/**
	 * @作者：罗典
	 * @描述：根据员工编号获取出纳银行账号
	 * @时间：2013-01-13
	 * @参数：出纳员工工号
	 * @返回：出纳账号集合
	 * */
	public List<CashierAccountInfo> searchCashierAccount(String personNumber)throws CrmBusinessException;
	
	/**
	 * @作者：罗典
	 * @描述：未开单理赔付款申请(费控)
	 * @时间：2013-01-13
	 * @参数：付款信息
	 * @返回：是否成功
	 * */
	public String createNoBillingRecompense(NoBillingRecompenseInfo info) throws CrmBusinessException;
}
