package com.deppon.crm.module.authorization.server.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.server.manager.IVirtualPositionManager;
import com.deppon.crm.module.authorization.server.service.IRoleService;
import com.deppon.crm.module.authorization.server.service.IVirtualPositionService;
import com.deppon.crm.module.authorization.server.util.VirtualPositionExceptionUtils;
import com.deppon.crm.module.authorization.server.util.VirtualPositionUtils;
import com.deppon.crm.module.authorization.shared.domain.EhrPosition;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.VirtualMapEhrPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPositionRole;
import com.deppon.crm.module.authorization.shared.domain.VirtualPositionRoleVo;
import com.deppon.crm.module.authorization.shared.exception.VirtualPositionExceptionType;
/**
 * 
 * <p>
 * 虚拟岗位管理<br />
 * </p>
 * @title VirtualPositionManager.java
 * @package com.deppon.crm.module.authorization.server.manager.impl 
 * @author suyujun
 * @version 0.1 2013-11-26
 */
public class VirtualPositionManager implements IVirtualPositionManager {
	private IVirtualPositionService virtualPositionService;
	private IRoleService roleService;
	/**
	 * @return virtualPositionService : return the property virtualPositionService.
	 */
	public IVirtualPositionService getVirtualPositionService() {
		return virtualPositionService;
	}

	/**
	 * @param virtualPositionService : set the property virtualPositionService.
	 */
	public void setVirtualPositionService(
			IVirtualPositionService virtualPositionService) {
		this.virtualPositionService = virtualPositionService;
	}
	
	/**
	 * @return roleService : return the property roleService.
	 */
	public IRoleService getRoleService() {
		return roleService;
	}

	/**
	 * @param roleService : set the property roleService.
	 */
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

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
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public VirtualPosition addVirtualPosition(VirtualPosition vp,List<String> newList) {
		String vpId = "";
		// 校验是否已经存在
		isExistVirtualPositionName(vp.getVirtualPositionName(),vpId);
		//其他数据设置
		vp.setCreateUser(VirtualPositionUtils.getCurrentUserId());
		vp.setCreateDate(new Date());
		vp.setModifyUser(VirtualPositionUtils.getCurrentUserId());
		vp.setModifyDate(new Date());
		vp.setVirtualPositionName(vp.getVirtualPositionName().trim());
		vp = virtualPositionService.addVirtualPosition(vp);
		VirtualMapEhrPosition vmp = null;
		String vpid = vp.getId();
		for(String ehrId:newList){
			vmp = new VirtualMapEhrPosition();
			vmp.setSpid(ehrId);
			vmp.setVpid(vpid);
			virtualPositionService.addVirtualMapStandard(vmp);
		}
		 return vp;
	}
	
	/**
	 * 
	 * <p>
	 * 更新虚拟岗位及对应的标准岗位映射关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-26
	 * @param newList 标准岗位的id集合
	 * @return
	 * VirtualPosition
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void updateVirtualPosition(VirtualPosition vp,List<String> newList) {
		List<String> orgStdList = new ArrayList<String>();
		String vpId = vp.getId();
		String virtualPositionName = vp.getVirtualPositionName();
		List<EhrPosition> ehrList = virtualPositionService.getStaPositionHaveMappedWithVP(vpId);
		for(EhrPosition ep : ehrList){
			orgStdList.add(ep.getPkEhrPosition());
		}
		List<String> toAdd = VirtualPositionUtils.toAddList(newList, orgStdList);
		List<String> toDelete = VirtualPositionUtils.toDeleteList(newList, orgStdList);
		//1.更新虚拟岗位
		this.isExistVirtualPositionName(virtualPositionName, vpId);
		vp.setModifyUser(VirtualPositionUtils.getCurrentUserId());
		virtualPositionService.updateVirtualPosition(vp);
		//2.删除映射关系
		this.deleteVirtualMapStandard(vp, toDelete);
		//3.新增映射关系
		this.addVirtualMapStandard(vp, toAdd);
	}
	
	/**
	 * 
	 * <p>
	 * 删除虚拟岗位，标准岗位映射关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param vp
	 * @param toDelete
	 * void
	 */
	private void deleteVirtualMapStandard(VirtualPosition vp,List<String> toDelete) {
		VirtualMapEhrPosition vms = null;
		String vpid = vp.getId();
		for(String spid : toDelete){ 
			vms = new VirtualMapEhrPosition();
			vms.setSpid(spid);
			vms.setVpid(vpid);
			virtualPositionService.deleteVirtualMapStandard(vms);
		}
	}
	
	/**
	 * 
	 * <p>
	 * 新增虚拟岗位，标准岗位映射关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param vp
	 * @param toAdd
	 * void
	 */
	private void addVirtualMapStandard(VirtualPosition vp,List<String> toAdd){
		VirtualMapEhrPosition vms = null;
		String vpid = vp.getId();
		for(String spid : toAdd){ 
			vms = new VirtualMapEhrPosition();
			vms.setSpid(spid);
			vms.setVpid(vpid);
			virtualPositionService.addVirtualMapStandard(vms);
		}		
	}
	
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
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteVirtualPositionById(VirtualPosition vp) {
		String vpId = vp.getId();
		//删除操作
		List<VirtualPositionRole> list = virtualPositionService.getRolesByVpId(vpId);
		//校验
		if(list!=null && CollectionUtils.isNotEmpty(list)){
			VirtualPositionExceptionUtils.createMailException(
					VirtualPositionExceptionType.HasRelationForVirtualPositionRole, new Object[]{});
		}
		virtualPositionService.deleteVirtualPositionById(vpId);
	}

	/**
	 * 判断是否可以删除虚拟岗位
	 * true:可以
	 * false：不可以
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public boolean canDeleteVirtualPosition(VirtualPosition vp) {
		String vpId = vp.getId();
		List<VirtualPositionRole> list = virtualPositionService.getRolesByVpId(vpId);
		if(list!=null && CollectionUtils.isNotEmpty(list)){
			return false;
		}
		return true;
	}

	
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
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public VirtualPosition selectVirtualPositionById(String id) {
		return virtualPositionService.selectVirtualPositionById(id);
	}

	/**
	 * 虚拟职位”为文本框，可手动输入，输入完毕后进行自动校验，
	 * 若与现有虚拟职位名称重复则提示“已存在该职位，请核实”
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public void isExistVirtualPositionName(String positionName,String vpId) {
		if(StringUtils.isNotBlank(positionName)){
			//true 存在，false 不存在
			boolean b = virtualPositionService.isExistVirtualPositionName(positionName.trim(),vpId);
			if(b){
				VirtualPositionExceptionUtils.createMailException(
						VirtualPositionExceptionType.ExistVirtualPostionName, new Object[]{});
			}
		}else{
			VirtualPositionExceptionUtils.createMailException(
					VirtualPositionExceptionType.VirtualPostionNameIsNull, new Object[]{});
		}
	}
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
	 * Map<String,Object>
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<EhrPosition> getStaPositionHaveMappedWithVP(String vpId) {
//		Map<String,Object> result = new HashMap<String, Object>();
		List<EhrPosition> list = virtualPositionService.getStaPositionHaveMappedWithVP(vpId);
//		long count = virtualPositionService.countStaPositionHaveMappedWithVP(vpId);
//		result.put("list", list);
//		result.put("count", count);
//		return result;
		return list;
	}

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
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<EhrPosition> getStaPositionWaitToMapWithVP(String vpId,String ehrPositionName) {
		return virtualPositionService.getStaPositionWaitToMapWithVP(vpId,ehrPositionName);
	}

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
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public Map<String,Object> queryVirtualPosition(String positionName,String ehrPositionName,int start,int limit) {
		Map<String,Object> result = new HashMap<String, Object>();
		List<VirtualPosition> list = virtualPositionService.queryVirtualPosition(positionName,ehrPositionName,start,limit);
		long count = virtualPositionService.countVirtualPosition(positionName,ehrPositionName);
		result.put("list", list);
		result.put("count", count);
		return result;
	}

	/**
	 * 
	 * <p>
	 * 虚拟岗位角色查询<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param positionName
	 * @return
	 * Map<String, Object>
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public Map<String, Object> queryVirPositionRole(String virtualPositionName,
			String roleName, int start, int limit) {
		Map<String,Object> result = new HashMap<String, Object>();

		//1.根据条件查询职位-角色对应关系
		List<VirtualPositionRoleVo> list = virtualPositionService.queryVirPositionRole(virtualPositionName,roleName,start,limit);
		long count = virtualPositionService.countQueryVirPositionRole(virtualPositionName,roleName);
		result.put("list", list);
		result.put("count", count);
		return result;
	}

	/**
	 * 
	 * <p>
	 * 根据岗位名称模糊查询未映射任何角色的虚拟岗位<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param virtualPositionName
	 * @param start
	 * @param limit
	 * @return
	 * Map<String,Object>
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public Map<String,Object> queryVirtualPositionByName(
			String virtualPositionName, int start, int limit) {
		Map<String,Object> result = new HashMap<String, Object>();
		List<VirtualPosition> vpList = virtualPositionService.queryVirtualPositionByName(virtualPositionName,start,limit);
		long count = virtualPositionService.countQueryVirtualPositionByName(virtualPositionName);
		result.put("result", vpList);
		result.put("count", count);
		return result;
	}
	
	/**
	 * 
	 * <p>
	 * 根据角色名称模糊查询角色<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param roleName
	 * @param isMap  NO 未映射   YES已映射
	 * @return
	 * Map<String,Object>
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<Role> queryRoleByVirtualPositionName(
			String virtualPositionId, String roleName, String isMap) {
		List<Role> roleList = roleService.queryRoleByVirtualPositionName(virtualPositionId,roleName,isMap);
		return roleList;
	}
	/**
	 * 
	 * <p>
	 * 新增虚拟岗位 角色 关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-30
	 * @param virtualPositionId
	 * @param roleIds
	 * void
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void saveVirtualPostionRole(String virtualPositionId,
			List<String> roleIds) {
		if(StringUtils.isEmpty(virtualPositionId)){
			VirtualPositionExceptionUtils.createMailException(VirtualPositionExceptionType.NotSelectVirtualPostion, new Object[]{});
		}
		//1.查询出与该虚拟岗位对应的所有角色Id
		List<VirtualPositionRole> orginalRoles =virtualPositionService.queryVirtualPositonRoleByvpId(virtualPositionId);
		List<String> orgIds = new ArrayList<String>();
		for(VirtualPositionRole role : orginalRoles){
			orgIds.add(role.getRoleId());
		}
		List<String> toDelete = VirtualPositionUtils.toDeleteList(roleIds, orgIds);
		List<String> toAdd = VirtualPositionUtils.toAddList(roleIds, orgIds);
		//2.删除
		this.deleteVirtualPositionRole(virtualPositionId, toDelete);
		//3.新增
		this.addVirtualPostionRole(virtualPositionId, toAdd);
	}
	/**
	 * 
	 * <p>
	 * 删除虚拟岗位与角色对应关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-30	
	 * @param virtualPositionId
	 * @param toDelete
	 * void
	 */
	private void deleteVirtualPositionRole(String virtualPositionId,List<String> toDelete){
		VirtualPositionRole vpr = null;
		for(String roleId : toDelete){
			vpr = new VirtualPositionRole();
			vpr.setVpId(virtualPositionId);
			vpr.setRoleId(roleId);
			virtualPositionService.deleteVirtualPostionRole(vpr);
		}
	}
	/**
	 * 
	 * <p>
	 * 新增虚拟岗位与角色对应关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-30
	 * @param virtualPositionId
	 * @param toDelete
	 * void
	 */
	private void addVirtualPostionRole(String virtualPositionId,List<String> toAdd){
		VirtualPositionRole vpr = null;
		for(String roleId : toAdd){
			vpr = new VirtualPositionRole();
			vpr.setVpId(virtualPositionId);
			vpr.setRoleId(roleId);
			virtualPositionService.addVirtualPostionRole(vpr);
		}
	}
	/**
	 * 
	 * <p>
	 * 删除映射关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-30
	 * @param virtualPositionId
	 * void
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteVirtualPositionRole(String virtualPositionId) {
		List<VirtualPositionRole> orginalRoles =virtualPositionService.queryVirtualPositonRoleByvpId(virtualPositionId);
		List<String> toDelete = new ArrayList<String>();
		for(VirtualPositionRole vpr : orginalRoles){
			toDelete.add(vpr.getRoleId());
		}
		this.deleteVirtualPositionRole(virtualPositionId, toDelete);
	}
	/**
	 * 
	 * <p>
	 * 人员角色刷新<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-30
	 * @param virtualPositionId
	 * void
	 */
	@Override
	public String refreshUserRole(String virtualPositionId) {
		/**
		 * 人员角色刷新
		 *若虚拟职位的对应角色有修改，勾选该条信息，点击“人员角色刷新”，赋予该虚拟职位对应的标准职位下的所有人员相应的角色，同时取消相应人员原来的角色信息。
		 * 数据量太大，用存储过程处理该过程
		 */
		//1.取消该虚拟职位对应的标准职位下的所有人员相应的角色
		//2.查询该虚拟岗位对应的所有人员（虚拟岗位对应的）
		/**
		 * delete from t_auth_userauthrole userrole
			 where userrole.fuserid in
			       (select users.fid
			          from t_auth_user users
			          join t_org_employee_sync employee
			            on users.fempcode = employee.empcode
			          join t_uums_ehrposition ehrp
			            on employee.pkehrposition = ehrp.pkehrposition
			          join t_crm_virtualstandardposition vsp
			            on ehrp.pkehrposition = vsp.fspid
			          join t_crm_virtualpostion vp
			            on vsp.fvpid = vp.fid
			         where vp.fid = #{});
		 */
		String result = "";
		try{
			result = virtualPositionService.refreshUserRole(virtualPositionId);			
		}catch(Exception e){
			VirtualPositionExceptionUtils.createMailException(
					VirtualPositionExceptionType.FailureRefresh, new Object[]{});				
		}
		return result;
	}
}
