/**
 * <p>
 * Description:<br />
 * </p>
 * @title VPositonToRoleAction.java
 * @package com.deppon.crm.module.authorization.server.action
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-11-28
 */
package com.deppon.crm.module.authorization.server.action;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.server.manager.IVirtualPositionManager;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.VirtualPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPositionRoleVo;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * <p>
 * Description:虚拟岗位-角色之间对应关系的操作action<br />
 * </p>
 * @title VPositonToRoleAction.java
 * @package com.deppon.crm.module.authorization.server.action
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-11-28
 */
public class VPositonToRoleAction  extends AbstractAction {
	private static final long serialVersionUID = 1L;
	// 每页展示记录条数
	private int limit;	
	// 分页开始记录数
	private int start;	
	// 总记录数
	private Long totalCount;
	//虚拟职位管理的manager
	private IVirtualPositionManager virtualPositionManager;
	//虚拟职位名称
	private String virtualPositionName;
	//虚拟职位列表
	private List<VirtualPosition> vpositionList;
	//角色id列表
	private List<String> roleIds;
	//角色名称
	private String roleName;
	//虚拟岗位与角色的vo列表
	private List<VirtualPositionRoleVo> virtualPositionRoleList;
	//虚拟职位id
	private String virtualPositionId;
	//是否映射过,YES表示查询已分配角色列表，NO表示查询未分配角色列表
	private String isMap;
	//角色列表
	private List<Role> roleList;
	/**
	 * 查询虚拟岗位与对应角色列表
	 * xiaohongye
	 * 20131202
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchVirtualPositionToRoleList() {
		Map<String, Object> map = virtualPositionManager.queryVirPositionRole(virtualPositionName, roleName, start, limit);
		virtualPositionRoleList = (List<VirtualPositionRoleVo>) map.get("list");
		//虚拟岗位对应的角色列表所转化成的字符串
//		String roleDesc = "";
//		if(virtualPositionRoleList != null){
//			for(int i = 0;i < virtualPositionRoleList.size();i++){
//				if(virtualPositionRoleList.get(i).getRoles() != null){
//					for(int j = 0;j < virtualPositionRoleList.get(i).getRoles().size();j++){
//						roleDesc = roleDesc + virtualPositionRoleList.get(i).getRoles().get(j).toString() + ";";
//					}
//					virtualPositionRoleList.get(i).setRoleDesc(roleDesc);
//					//赋值完毕后为roleDesc设置初始值
//					roleDesc = "";
//				}
//			}
//		}
		totalCount = Long.valueOf(map.get("count").toString());
		totalCount = totalCount==0?1:totalCount;
		return SUCCESS;
	}

	/**
	 * 根据条件查询待选角色列表和已分配角色列表
	 * xiaohongye
	 * 20131202
	 * @return
	 */
	@JSON
	public String searchRoleInfoList() {
		roleList = virtualPositionManager.queryRoleByVirtualPositionName(virtualPositionId, roleName, isMap);
		return SUCCESS;
	}
	/**
	 * 保存虚拟职位与角色对应关系映射
	 * xiaohongye
	 * 20131202
	 * @return
	 */
	@JSON
	public String saveVPositionAndRoleRelationship() {
		virtualPositionManager.saveVirtualPostionRole(virtualPositionId, roleIds);
		return SUCCESS;
	}
	
	/**
	 * 删除虚拟职位与角色对应关系映射
	 * xiaohongye
	 * 20131202
	 * @return
	 */
	@JSON
	public String deleteVPositionAndRoleRelationship() {
		virtualPositionManager.deleteVirtualPositionRole(virtualPositionId);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:刷新虚拟岗位所对应下面的所有用户的用户角色<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2013-12-2
	 */
	private String refreshResult;
	
	/**
	 * @param refreshResult the refreshResult to get
	 */
	public String getRefreshResult() {
		return refreshResult;
	}

	@JSON
	public String refreshUserRole(){
		refreshResult = virtualPositionManager.refreshUserRole(virtualPositionId);
		return SUCCESS;
	}

	/**
	 * 新增虚拟职位与角色映射关系时查询未被映射过的虚拟职位
	 * xiaohongye
	 * 20131202
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchUnmappedVPositionList() {
		Map<String,Object> map = virtualPositionManager.queryVirtualPositionByName(virtualPositionName, start, limit);
		vpositionList = (List<VirtualPosition>) map.get("result");
		totalCount = Long.valueOf(map.get("count").toString());
		return SUCCESS;
	}
	
	/**
	 * @param limit the limit to get
	 */
	public int getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @param start the start to get
	 */
	public int getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * @param totalCount the totalCount to get
	 */
	public Long getTotalCount() {
		return totalCount;
	}
	/**
	 * @param vpositionList the vpositionList to get
	 */
	public List<VirtualPosition> getVpositionList() {
		return vpositionList;
	}

	/**
	 * @param vpositionList the vpositionList to set
	 */
	public void setVpositionList(List<VirtualPosition> vpositionList) {
		this.vpositionList = vpositionList;
	}

	/**
	 * @param virtualPositionName the virtualPositionName to set
	 */
	public void setVirtualPositionName(String virtualPositionName) {
		this.virtualPositionName = virtualPositionName;
	}
	/**
	 * @param roleIds the roleIds to set
	 */
	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
	/**
	 * @param virtualPositionManager the virtualPositionManager to set
	 */
	public void setVirtualPositionManager(
			IVirtualPositionManager virtualPositionManager) {
		this.virtualPositionManager = virtualPositionManager;
	}
	/**
	 * @param virtualPositionRoleList the virtualPositionRoleList to get
	 */
	public List<VirtualPositionRoleVo> getVirtualPositionRoleList() {
		return virtualPositionRoleList;
	}
	/**
	 * @param virtualPositionRoleList the virtualPositionRoleList to set
	 */
	public void setVirtualPositionRoleList(
			List<VirtualPositionRoleVo> virtualPositionRoleList) {
		this.virtualPositionRoleList = virtualPositionRoleList;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @param virtualPositionId the virtualPositionId to set
	 */
	public void setVirtualPositionId(String virtualPositionId) {
		this.virtualPositionId = virtualPositionId;
	}
	/**
	 * @param roleList the roleList to get
	 */
	public List<Role> getRoleList() {
		return roleList;
	}
	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	/**
	 * @param isMap the isMap to set
	 */
	public void setIsMap(String isMap) {
		this.isMap = isMap;
	}
}
