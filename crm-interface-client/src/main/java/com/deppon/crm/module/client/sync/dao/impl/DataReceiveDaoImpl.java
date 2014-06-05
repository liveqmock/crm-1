package com.deppon.crm.module.client.sync.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.deppon.crm.module.client.sync.dao.IDataReceiveDao;
import com.deppon.crm.module.client.sync.domain.OrderRightInfo;
import com.deppon.crm.module.client.sync.domain.SiteReceiveRequest;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class DataReceiveDaoImpl extends iBatis3DaoImpl implements
		IDataReceiveDao {
	private final String NAMESPACE = "com.deppon.crm.module.client.sync.domain.OrderRightInfo.";
	// 保存开单组织权限
	private final String INSERT_ORDERRIGHT = "insertOrderRightInfo";
	// 作废开的组织权限
	private final String CANCEL_ORDERRIGHT = "cancelOrderRightInfo";
	// 保存网点信息
	private final String INSERT_SITERECEIVE = "insertSiteReceiveRequest";

	/**
	 * @作者：罗典
	 * @描述：新增开单组权限同步
	 * @时间：2012-11-10
	 * @参数：开单组织权限参数信息
	 * @返回值：无
	 * */
	@Override
	public void saveOrderRight(OrderRightInfo orderRightInfo) {
		this.getSqlSession().insert(NAMESPACE+INSERT_ORDERRIGHT, orderRightInfo);
	}

	/**
	 * @作者：罗典
	 * @描述：作废开单组权限
	 * @时间：2012-11-10
	 * @参数：开单组织权限参数信息
	 * @返回值：无
	 * */
	@Override
	public void cancelOrderRight(OrderRightInfo orderRightInfo) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("operateTime", orderRightInfo.getOperateTime());
		map.put("orderTeam", orderRightInfo.getOrderTeam());
		map.put("department", orderRightInfo.getDepartment());
		this.getSqlSession().update(NAMESPACE+CANCEL_ORDERRIGHT, map);		
	}

	/**
	 * @作者：罗典
	 * @描述：新增网点信息
	 * @时间：2012-11-10
	 * @参数：网点参数信息
	 * @返回值：无
	 * */
	@Override
	public void saveSiteReceive(SiteReceiveRequest siteReceiveInfo) {
		/* 未保存字段：
		 * 部门编码deptCode，
		 * 是否AB货isAB(这个信息根据城市来判断的)，
		 * 所属区域superiorArea(数据库中无值)
		 */
		this.getSqlSession().insert(NAMESPACE+INSERT_SITERECEIVE, siteReceiveInfo);
	}

}
