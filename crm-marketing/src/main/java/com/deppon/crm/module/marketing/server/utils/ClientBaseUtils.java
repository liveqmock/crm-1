package com.deppon.crm.module.marketing.server.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityConstance;

public class ClientBaseUtils {
	/**
	 * 
	 * 
	 * <p>
	 * 筛选二级行业<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-21
	 * @param
	 * @return
	 */
	public static List<Detail> choiceSecondTreade(List<Detail> secondTrade) {
		// 筛选数据字段失效期未过的二级行业
		List<Detail> resultLists = new ArrayList<Detail>();
		if (null != secondTrade && 0 < secondTrade.size()) {
			for (Detail d : secondTrade) {
				ClientBaseValidator.checkSecondTreade(d);
				if (d.getStatus()) {
					resultLists.add(d);
				} 
//				else if (d.getInvaliTime().after(new Date())) {
//					resultLists.add(d);
//				}
			}
		}

		return resultLists;

	}


}
