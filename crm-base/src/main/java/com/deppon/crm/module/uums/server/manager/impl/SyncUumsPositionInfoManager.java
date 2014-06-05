
package com.deppon.crm.module.uums.server.manager.impl;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.sync.shared.SyncException;
import com.deppon.crm.module.uums.server.manager.ISyncUumsDataManager;
import com.deppon.crm.module.uums.server.service.IPositionService;
import com.deppon.crm.module.uums.shared.domain.BaseUUEntity;
import com.deppon.crm.module.uums.shared.domain.PostionInfo;
/**
 * 
 * @description CRM同步uums 职位数据实现类
 * @author zzw
 * @version 0.1 2013-11-25
 */
public class SyncUumsPositionInfoManager implements ISyncUumsDataManager {
	private IPositionService positionService;
/*	postion  1、已撤销 2、未撤销
*/	//已撤销
	public static final String DELETED="1";
	//未撤销
	public static final String EXIST="2";
	public IPositionService getPositionService() {
		return positionService;
	}
	public void setPositionService(IPositionService positionService) {
		this.positionService = positionService;
	}
	/**
	 * 标准岗位数据新增
	 * @param BaseUUEntity 
	 * 
	 */
	public int insert(BaseUUEntity entity) {
		PostionInfo orgInfo=(PostionInfo)entity;
	    int res=positionService.insert(orgInfo);
		return res;
	}
	/**
	 * 标准岗位数据更新
	 * @param BaseUUEntity 
	 * 
	 */
	public int update(BaseUUEntity entity) {
		String positionId=entity.getTheMainId();
		PostionInfo orgInfo=(PostionInfo)entity;
	    int res=positionService.updateById(orgInfo, positionId);
		return res;

	}
	/**
	 * 标准岗位数据删除
	 * @param BaseUUEntity 
	 * 
	 */
	public int delete(BaseUUEntity entity) {
		String positionId=entity.getTheMainId();
	    int res=positionService.deleteById(positionId);
		return res;

	}
	/**
	 * 数据校验	
	 * @param BaseUUEntity 
	 * 
	 */
	@Transactional
	public int check(BaseUUEntity entity) {
		String positionId=entity.getTheMainId();
		String changeType=entity.getChangeType();
		PostionInfo positionInfo=positionService.searchById(positionId);
		int res=0;
		if(positionInfo!=null){
			res=1;
		}
		positionInfo=(PostionInfo)entity;
		if(DELETED.equals(changeType)){
			if(res>0){
				res=positionService.deleteById(positionId);
			}else{	
				throw new SyncException("SyncUumsPositionInfoManager.DELETED the id "+positionId +" nofount");
			}
		}else{
			if(res>0){
				res=positionService.updateById(positionInfo, positionId);
			}else{
				res=positionService.insert(positionInfo);
			}
		}
		return SUCCESS;
	}
}
