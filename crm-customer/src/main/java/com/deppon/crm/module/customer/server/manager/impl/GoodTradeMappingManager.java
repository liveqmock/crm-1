/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title GoodTradeMappingManager.java
 * @package com.deppon.crm.module.customer.server.manager.impl 
 * @author 106138
 * @version 0.1 2014-3-18
 */
package com.deppon.crm.module.customer.server.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.manager.IGoodTradeMappingManager;
import com.deppon.crm.module.customer.server.service.IGoodTradeMappingService;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMapping;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMappingSearchCondition;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;
import com.deppon.crm.module.keycustomer.server.service.IKeyCustomerService;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.sso.util.StringUtil;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title GoodTradeMappingManager.java
 * @package com.deppon.crm.module.customer.server.manager.impl
 * @author 106138
 * @version 0.1 2014-3-18
 */ 

public class GoodTradeMappingManager implements IGoodTradeMappingManager {
	private IGoodTradeMappingService goodTradeMappingService;
	private IKeyCustomerService  keyCustomerService;

	/**
	 * 
	 * <p>
	 * Description:查询行业与品名映射<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-18
	 * @param condition
	 * @return Map<String,Object>
	 */
	public Map<String, Object> searchGoodTradeMappingByCondition(
			GoodTradeMappingSearchCondition condition) {
		// 根据条件查询行业品名信息
		if (condition.getAssociatedStatus().equals(Constant.ALL_STATUS)) {
			condition.setAssociatedStatus("");
	}	
		List<GoodTradeMapping> list = goodTradeMappingService
					.searchGoodTradeMappingByCondition(condition);
		// 根据条件查询条数
		condition.setLimit(999999);
		condition.setStart(0);
		int total = goodTradeMappingService
				.countGoodTradeMappingByCondition(condition);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}


	/**
	 * 
	 * <p>
	 * Description:更新行业与品名映射关系<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-18
	 * @param goodTradeMapping
	 *            void
	 */
	public void updateGoodTradeMapping(GoodTradeMapping goodTradeMapping) {
		//映射关系为空
		if (null == goodTradeMapping
				//无一级行业
				|| StringUtil.isNull(goodTradeMapping.getFirstTradeCode())
				//无二级行业
				|| StringUtil.isNull(goodTradeMapping.getSecondTradeCode())) {
			throw new MemberException(
					MemberExceptionType.NULLTRADE);
		}
		//最后更新时间
		goodTradeMapping.setModifyDate(new Date());
		//获得当前用户
		User user=(User) UserContext.getCurrentUser();
		//最后修改人
		goodTradeMapping.setModifyUser(user.getEmpCode().getId());
		//关联人
		goodTradeMapping.setAssociatedPersonId(Integer.parseInt(user.getEmpCode().getId()));
		//关联时间
		goodTradeMapping.setAssociatedTime(new Date());
		goodTradeMapping.setAssociatedStatus(Constant.HAS_ASSOCITED);
		//更新操作
		goodTradeMappingService.updateGoodTradeMapping(goodTradeMapping);
	}
	/**
	 * 
	 * <p>
	 * Description:系统更新客户行业信息 每月一次<<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月10日
	 * void
	 */
	public void updateCustomerTradeMonth(){
		keyCustomerService.callStoredProcedure("SP_CRM_GOODTRADEMAPPINGMONTH_C");
	}
	/**
	 * 
	 * <p>
	 * Description:系统更新没有行业信息的客户的行业信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月10日
	 * void
	 */
	public void updateCustomerTradeWeek(){
		keyCustomerService.callStoredProcedure("SP_CRM_GOODTRADEMAPPINGWEEK_C");
	}

	/**
	 * <p>
	 * Description:goodTradeMappingService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public IGoodTradeMappingService getGoodTradeMappingService() {
		return goodTradeMappingService;
	}

	/**
	 * <p>
	 * Description:goodTradeMappingService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public void setGoodTradeMappingService(IGoodTradeMappingService goodTradeMappingService) {
		this.goodTradeMappingService = goodTradeMappingService;
	}

	/**
	 * <p>
	 * Description:keyCustomerService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public IKeyCustomerService getKeyCustomerService() {
		return keyCustomerService;
	}

	/**
	 * <p>
	 * Description:keyCustomerService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public void setKeyCustomerService(IKeyCustomerService keyCustomerService) {
		this.keyCustomerService = keyCustomerService;
	}

}
