package com.deppon.crm.module.authorization.server.service;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.EhrPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualMapEhrPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPositionRole;
import com.deppon.crm.module.authorization.shared.domain.VirtualPositionRoleVo;

public interface IVirtualPositionService {
	/**
	 * 
	 * <p>
	 * 新增虚拟岗位<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-26
	 * @param vp
	 * @return
	 * VirtualPosition
	 */
	VirtualPosition addVirtualPosition(VirtualPosition vp);
	
	/**
	 * 
	 * <p>
	 * 更新虚拟岗位<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-26
	 * @param vp
	 * void
	 */
	void updateVirtualPosition(VirtualPosition vp);
	
	/**
	 * 
	 * <p>
	 * 删除虚拟岗位<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-26
	 * @param id
	 * void
	 */
	void deleteVirtualPositionById(String id);
	
	/**
	 * 
	 * <p>
	 * 根据ID查询虚拟岗位<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-26
	 * @param id
	 * @return
	 * VirtualPosition
	 */
	VirtualPosition selectVirtualPositionById(String id);

	/**
	 * 
	 * <p>
	 * 查询所有虚拟岗位名称<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-26
	 * @return
	 * boolean
	 */
	boolean isExistVirtualPositionName(String positionName,String vpId);

	/**
	 * 
	 * <p>
	 * 根据虚拟岗位查询角色<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-26
	 * @param vpId
	 * @return
	 * List<VirtualPositionRole>
	 */
	List<VirtualPositionRole> getRolesByVpId(String vpId);

	/**
	 * 
	 * <p>
	 * 分页查询已经映射的标准岗位<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param vpId
	 * @param start
	 * @param limit
	 * @return
	 * Map<String,List<EhrPosition>>
	 */
	List<EhrPosition> getStaPositionHaveMappedWithVP(String vpId);

	/**
	 * 
	 * <p>
	 * 分页查询已经映射的标准岗位 计数 <br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param vpId
	 * @return
	 * int
	 */
	int countStaPositionHaveMappedWithVP(String vpId);
	/**
	 * 
	 * <p>
	 * 查询与该虚拟岗位未映射的标准岗位<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param vpId
	 * @return
	 * List<EhrPosition>
	 */
	List<EhrPosition> getStaPositionWaitToMapWithVP(String vpId,String ehrPositionName);
	/**
	 * 
	 * <p>
	 * 虚拟岗位查询<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param positionName
	 * @return
	 * List<VirtualPosition>
	 */
	List<VirtualPosition> queryVirtualPosition(String positionName,String ehrPositionName, int start,
			int limit);

	/**
	 * 
	 * <p>
	 * 虚拟岗位查询 计数<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param positionName
	 * @param ehrPositionName
	 * @return
	 * int
	 */
	int countVirtualPosition(String positionName, String ehrPositionName);

	/**
	 * 
	 * <p>
	 * 删除虚拟岗位与标准岗位映射关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param vms
	 * void
	 */
	void deleteVirtualMapStandard(VirtualMapEhrPosition vms);

	/**
	 * 
	 * <p>
	 * 新增虚拟岗位与标准岗位映射关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param vms
	 * void
	 */
	void addVirtualMapStandard(VirtualMapEhrPosition vms);

	/**
	 * 
	 * <p>
	 * 查询虚拟岗位对应的角色<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param params
	 * @param start
	 * @param limit
	 * @return
	 * List<VirtualPositionRoleVo>
	 */
	List<VirtualPositionRoleVo> queryVirPositionRole(
			String virtualPositionName, String roleName, int start, int limit);

	/**
	 * 
	 * <p>
	 * 查询虚拟岗位对应的角色,计数<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param virtualPositionName
	 * @param roleName
	 * @return
	 * int
	 */
	int countQueryVirPositionRole(String virtualPositionName, String roleName);
	/**
	 * 
	 * <p>
	 * 根据虚拟岗位名称模糊查询虚拟岗位<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param virtualPositionName
	 * @param start
	 * @param limit
	 * @return
	 * List<VirtualPosition>
	 */
	List<VirtualPosition> queryVirtualPositionByName(
			String virtualPositionName, int start, int limit);
	/**
	 * 
	 * <p>
	 * 根据虚拟岗位名称模糊查询虚拟岗位——计数<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param virtualPositionName
	 * @param start
	 * @param limit
	 * @return
	 * List<VirtualPosition>
	 */
	int countQueryVirtualPositionByName(String virtualPositionName);

	/**
	 * 
	 * <p>
	 * 根据虚拟岗位ID查询映射的角色<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-30
	 * @param virtualPositionId
	 * @return
	 * List<VirtualPositionRole>
	 */
	List<VirtualPositionRole> queryVirtualPositonRoleByvpId(
			String virtualPositionId);

	/**
	 * 
	 * <p>
	 * 删除虚拟岗位角色对应关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-30
	 * @param vpr
	 * void
	 */
	void deleteVirtualPostionRole(VirtualPositionRole vpr);

	/**
	 * 
	 * <p>
	 * 新增虚拟岗位角色对应关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-30
	 * @param vpr
	 * void
	 */
	void addVirtualPostionRole(VirtualPositionRole vpr);

	/**
	 * 
	 * <p>
	 * 人员角色刷新<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-12-2
	 * @param virtualPositionId
	 * void
	 * @return 
	 */
	String refreshUserRole(String virtualPositionId);
}
