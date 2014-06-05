package com.deppon.crm.module.authorization.server.service.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.server.dao.IVirtualPositionDao;
import com.deppon.crm.module.authorization.server.service.IVirtualPositionService;
import com.deppon.crm.module.authorization.shared.domain.EhrPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualMapEhrPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPositionRole;
import com.deppon.crm.module.authorization.shared.domain.VirtualPositionRoleVo;

/**
 * 
 * <p>
 * 虚拟岗位管理service封装<br />
 * </p>
 * @title VirtualPositionService.java
 * @package com.deppon.crm.module.authorization.server.service.impl 
 * @author suyujun
 * @version 0.1 2013-11-26
 */
public class VirtualPositionService implements IVirtualPositionService {
	private IVirtualPositionDao virtualPositionDao; 
	/**
	 * @return virtualPositionDao : return the property virtualPositionDao.
	 */
	public IVirtualPositionDao getVirtualPositionDao() {
		return virtualPositionDao;
	}
	
	/**
	 * @param virtualPositionDao : set the property virtualPositionDao.
	 */
	public void setVirtualPositionDao(IVirtualPositionDao virtualPositionDao) {
		this.virtualPositionDao = virtualPositionDao;
	}
	@Override
	public VirtualPosition addVirtualPosition(VirtualPosition vp) {
		return virtualPositionDao.insert(vp);
	}

	@Override
	public void updateVirtualPosition(VirtualPosition vp) {
		virtualPositionDao.updateByPrimaryKeySelective(vp);
	}

	@Override
	public void deleteVirtualPositionById(String id) {
		virtualPositionDao.deleteByPrimaryKey(id);
	}

	@Override
	public VirtualPosition selectVirtualPositionById(String id) {
		return virtualPositionDao.selectByPrimaryKey(id);
	}


	@Override
	public boolean isExistVirtualPositionName(String positionName,String vpId){
		return virtualPositionDao.isExistVirtualPositionName(positionName,vpId);
	}

	@Override
	public List<VirtualPositionRole> getRolesByVpId(String vpId) {
		return virtualPositionDao.getRolesByVpId(vpId);
	}

	@Override
	public List<EhrPosition> getStaPositionHaveMappedWithVP(String vpId) {
		return virtualPositionDao.getStaPositionHaveMappedWithVP(vpId);
	}

	@Override
	public int countStaPositionHaveMappedWithVP(String vpId) {
		return virtualPositionDao.countStaPositionHaveMappedWithVP(vpId);
	}

	@Override
	public List<EhrPosition> getStaPositionWaitToMapWithVP(String vpId,String ehrPositionName) {
		return virtualPositionDao.getStaPositionWaitToMapWithVP(vpId,ehrPositionName);
	}

	@Override
	public List<VirtualPosition> queryVirtualPosition(String positionName,String ehrPositionName,int start,int limit) {
		return virtualPositionDao.queryVirtualPosition(positionName,ehrPositionName,start,limit);
	}

	@Override
	public int countVirtualPosition(String positionName, String ehrPositionName) {
		return  virtualPositionDao.countVirtualPosition(positionName,ehrPositionName);
	}

	@Override
	public void deleteVirtualMapStandard(VirtualMapEhrPosition vms) {
		virtualPositionDao.deleteVirtualMapStandard(vms);
	}

	@Override
	public void addVirtualMapStandard(VirtualMapEhrPosition vms) {
		virtualPositionDao.addVirtualMapStandard(vms);
	}

	@Override
	public List<VirtualPositionRoleVo> queryVirPositionRole(
			String virtualPositionName, String roleName, int start, int limit) {
		return virtualPositionDao.queryVirPositionRole(virtualPositionName,roleName,start,limit);
	}

	@Override
	public int countQueryVirPositionRole(String virtualPositionName,
			String roleName) {
		return virtualPositionDao.countQueryVirPositionRole(virtualPositionName,roleName);
	}

	@Override
	public List<VirtualPosition> queryVirtualPositionByName(
			String virtualPositionName, int start, int limit) {
		return virtualPositionDao.queryVirtualPositionByName(virtualPositionName,start,limit);
	}

	@Override
	public int countQueryVirtualPositionByName(String virtualPositionName) {
		return virtualPositionDao.countQueryVirtualPositionByName(virtualPositionName);
	}

	@Override
	public List<VirtualPositionRole> queryVirtualPositonRoleByvpId(
			String virtualPositionId) {
		return virtualPositionDao.queryVirtualPositonRoleByvpId(virtualPositionId);
	}

	@Override
	public void deleteVirtualPostionRole(VirtualPositionRole vpr) {
		virtualPositionDao.deleteVirtualPostionRole(vpr);
	}

	@Override
	public void addVirtualPostionRole(VirtualPositionRole vpr) {
		virtualPositionDao.addVirtualPostionRole(vpr);
	}

	@Override
	public String refreshUserRole(String virtualPositionId) {
		return virtualPositionDao.refreshUserRole(virtualPositionId);
	}

}
