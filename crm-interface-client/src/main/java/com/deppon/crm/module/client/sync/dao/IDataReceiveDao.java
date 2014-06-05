package com.deppon.crm.module.client.sync.dao;

import com.deppon.crm.module.client.sync.domain.OrderRightInfo;
import com.deppon.crm.module.client.sync.domain.SiteReceiveRequest;

/**
 * @作者：罗典
 * @时间：2012-11-10
 * @描述：接收同步数据DAO层接口
 * */
public interface IDataReceiveDao {
	
	/**
	 * @作者：罗典
	 * @描述：新增开单组权限
	 * @时间：2012-11-10
	 * @参数：开单组织权限参数信息
	 * @返回值：无
	 * */
	public void saveOrderRight(OrderRightInfo orderRightInfo);
	
	/**
	 * @作者：罗典
	 * @描述：删除开单组权限
	 * @时间：2012-11-10
	 * @参数：作废组织权限参数信息
	 * @返回值：无
	 * */
	public void cancelOrderRight(OrderRightInfo orderRightInfo);
	
	/**
	 * @作者：罗典
	 * @描述：新增网点信息
	 * @时间：2012-11-10
	 * @参数：网点参数信息
	 * @返回值：无
	 * */
	public void saveSiteReceive(SiteReceiveRequest siteReceiveInfo);
}
