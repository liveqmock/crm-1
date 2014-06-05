/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProviderFor360Manager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author Administrator
 * @version 0.1 2012-4-24
 */
package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.marketing.server.manager.IProviderFor360Manager;
import com.deppon.crm.module.marketing.server.service.IProviderFor360Service;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProviderFor360Manager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author 苏玉军
 * @version 0.1 2012-4-24
 */

public class ProviderFor360Manager implements IProviderFor360Manager {
	//360service封装
	private IProviderFor360Service providerFor360Service;
	
	/**
	 * @return providerFor360Service : return the property providerFor360Service.
	 */
	public IProviderFor360Service getProviderFor360Service() {
		return providerFor360Service;
	}

	/**
	 * @param providerFor360Service : set the property providerFor360Service.
	 */
	public void setProviderFor360Service(
			IProviderFor360Service providerFor360Service) {
		this.providerFor360Service = providerFor360Service;
	}

	/**
	 * 
	 * <p>
	 * 根据客户ID获得客户最近一次客户维护信息（客户维护回访信息）<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-24
	 * @param custId
	 * @return List<ReturnVisit>
	 * @see com.deppon.crm.module.marketing.server.manager.IProviderFor360Manager#getLastestMaintainInfo(java.lang.String)
	 */
	@Override
	public ReturnVisit getLastestMaintainInfo(String custId) {
		//回访对象
 		ReturnVisit rv = new ReturnVisit();
 		//客户id不为空，查询
		if(StringUtils.isNotEmpty(custId)){
			//查询最近一次的回访信息
			rv = providerFor360Service.getLastestMaintainInfo(custId);
		}
		//返回
		return rv;
	}

	/**
	 * 
	 * <p>
	 * 根据客户id获得客户开发/日程综合信息列表<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-24
	 * @param custId
	 * @return
	 * List<ReturnVisit>
	 * @see com.deppon.crm.module.marketing.server.manager.IProviderFor360Manager#getPlanScheduleDetail(java.lang.String)
	 */
	@Override
	public List<ReturnVisit> getPlanScheduleDetail(String custId) {
		//结果List定义
		List<ReturnVisit> rvList = new ArrayList<ReturnVisit>();
		//客户Id不为空
		if(StringUtils.isNotEmpty(custId)){
			//查询
			rvList = providerFor360Service.getPlanScheduleDetail(custId);
		}
		//返回结果
		return rvList;
	}

	/**
	 * 
	 * <p>
	 * 根据客户编码获得客户维护综合信息列表（即客户维护回访信息）<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-24
	 * @param custId
	 * @return
	 * List<ReturnVisit>
	 * @see com.deppon.crm.module.marketing.server.manager.IProviderFor360Manager#getVisitRecords(java.lang.String)
	 */
	@Override
	public List<ReturnVisit> getVisitRecords(String custId) {
		//结果定义
		List<ReturnVisit> rvList = new ArrayList<ReturnVisit>();
		//客户ID不为空
		if(StringUtils.isNotEmpty(custId)){
			//查询
			rvList = providerFor360Service.getVisitRecords(custId);
		}
		//返回结果
		return rvList;
	}

}
