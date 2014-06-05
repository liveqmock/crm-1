package com.deppon.crm.module.authorization.server.dao;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.EhrPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualMapEhrPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPositionRole;
import com.deppon.crm.module.authorization.shared.domain.VirtualPositionRoleVo;

/**
 * 
 * <p>
 * 虚拟岗位管理dao<br />
 * </p>
 * @title IVirtualPositionService.java
 * @package com.deppon.crm.module.authorization.server.dao 
 * @author suyujun
 * @version 0.1 2013-11-26
 */
public interface IVirtualPositionDao {
	VirtualPosition insert(VirtualPosition vp);
	
	void updateByPrimaryKeySelective(VirtualPosition vp);
	
	void deleteByPrimaryKey(String id);
	
	VirtualPosition selectByPrimaryKey(String id);

	boolean isExistVirtualPositionName(String positionName,String vpId);

	List<VirtualPositionRole> getRolesByVpId(String vpId);

	List<EhrPosition> getStaPositionHaveMappedWithVP(String vpId);

	int countStaPositionHaveMappedWithVP(String vpId);

	List<EhrPosition> getStaPositionWaitToMapWithVP(String vpId, String ehrPositionName);

	List<VirtualPosition> queryVirtualPosition(String positionName,String ehrPositionName, int start,
			int limit);

	int countVirtualPosition(String positionName, String ehrPositionName);

	void deleteVirtualMapStandard(VirtualMapEhrPosition vms);

	void addVirtualMapStandard(VirtualMapEhrPosition vms);

	List<VirtualPositionRoleVo> queryVirPositionRole(
			String virtualPositionName, String roleName, int start, int limit);

	int countQueryVirPositionRole(String virtualPositionName, String roleName);

	List<VirtualPosition> queryVirtualPositionByName(
			String virtualPositionName, int start, int limit);

	int countQueryVirtualPositionByName(String virtualPositionName);

	List<VirtualPositionRole> queryVirtualPositonRoleByvpId(
			String virtualPositionId);

	void deleteVirtualPostionRole(VirtualPositionRole vpr);

	void addVirtualPostionRole(VirtualPositionRole vpr);

	String refreshUserRole(String virtualPositionId);
}
