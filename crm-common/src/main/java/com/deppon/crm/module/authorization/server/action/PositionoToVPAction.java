package com.deppon.crm.module.authorization.server.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.server.manager.IVirtualPositionManager;
import com.deppon.crm.module.authorization.shared.domain.EhrPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPosition;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class PositionoToVPAction extends AbstractAction{
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	//虚拟岗位列表
	private List<VirtualPosition> vpositionList = null;
	//标准岗位列表
	private List<EhrPosition> ehrPositionList = null;
	//虚拟职位id
	private String vpID = null;
	//虚拟职位名称
	private String virtualPositionName = null;
	//标准职位名称
	private String positionName = null;
	//虚拟职位信息
	private VirtualPosition virtualPosition = null;
	//标准职位id信息
	private String[] positionIDs = null;
	
	//manage 层对象
	private IVirtualPositionManager virtualPositionManager;
	/**
	 * 
	 * <p>
	 * Description:查询所有的虚拟职位信息<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-26
	 * @return
	 * String
	 */
	@JSON
	public String searchAllVPosition(){
		//传递两个参数  如果都为空则查询全部
		Map<String,Object> map = null;
		map = virtualPositionManager.queryVirtualPosition(virtualPositionName, positionName, start, limit);
		vpositionList = (List<VirtualPosition>) map.get("list");
		totalCount = (Long) map.get("count");
	    //如果totalCount为0则返回1，用于前端显示页数
	    totalCount = totalCount==0?1:totalCount;
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:查询未分配标准职位<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-27
	 * @return
	 * String
	 */
	@JSON
	public String staPositionWaitToMapWithVP(){
		ehrPositionList = virtualPositionManager.getStaPositionWaitToMapWithVP(vpID,positionName);
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:查询已分配的标准职位信息<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-27
	 * @return
	 * String
	 */
	@JSON
	public String staPositionHaveMappedWithVP(){
		ehrPositionList = virtualPositionManager.getStaPositionHaveMappedWithVP(vpID);
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:新增虚拟岗位<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-28
	 * @return
	 * String
	 */
	@JSON
	public String addVirtualPosition(){
		if(null == positionIDs){
			List<String> list = new ArrayList<String>();
			virtualPositionManager.addVirtualPosition(virtualPosition, list);
		}else{
			virtualPositionManager.addVirtualPosition(virtualPosition, Arrays.asList(positionIDs));
		}
		return SUCCESS; 
	}
	/**
	 * 
	 * <p>
	 * Description:修改虚拟岗位<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-30
	 * @return
	 * String
	 */
	@JSON
	public String updateVirtualPosition(){
		if(null == positionIDs){
			List<String> list = new ArrayList<String>();
			virtualPositionManager.updateVirtualPosition(virtualPosition, list);
		}else{
			virtualPositionManager.updateVirtualPosition(virtualPosition, Arrays.asList(positionIDs));
		}
		
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:删除虚拟岗位信息<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-28
	 * @return
	 * String
	 */
	@JSON
	public String deleteVirtualPositionById(){
		virtualPositionManager.deleteVirtualPositionById(virtualPosition);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:判断是否可以删除虚拟岗位<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-28
	 * @return
	 * String
	 */
	@JSON
	public String canDeleteVirtualPosition(){
		virtualPositionManager.canDeleteVirtualPosition(virtualPosition);
		return SUCCESS;
	}
	/**
	 * 虚拟职位名称是否重复
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-28
	 * @return
	 * String
	 */
	@JSON
	public String isExistVirtualPositionName(){
		virtualPositionManager.isExistVirtualPositionName(virtualPositionName,vpID);
		return SUCCESS;
	}
	
	
	
	
	
	
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-26
	 */
	public List<VirtualPosition> getVpositionList() {
		return vpositionList;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-26
	 * @param vpositionList the vpositionList to set
	 */
	public void setVpositionList(List<VirtualPosition> vpositionList) {
		this.vpositionList = vpositionList;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-27
	 */
	public List<EhrPosition> getEhrPositionList() {
		return ehrPositionList;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-27
	 * @param ehrPositionList the ehrPositionList to set
	 */
	public void setEhrPositionList(List<EhrPosition> ehrPositionList) {
		this.ehrPositionList = ehrPositionList;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-27
	 */
	public String getVpID() {
		return vpID;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-27
	 * @param vpID the vpID to set
	 */
	public void setVpID(String vpID) {
		this.vpID = vpID;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-28
	 */
	public String[] getPositionIDs() {
		return positionIDs;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-28
	 * @param positionIDs the positionIDs to set
	 */
	public void setPositionIDs(String[] positionIDs) {
		this.positionIDs = positionIDs;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-28
	 */
	public VirtualPosition getVirtualPosition() {
		return virtualPosition;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-28
	 * @param virtualPosition the virtualPosition to set
	 */
	public void setVirtualPosition(VirtualPosition virtualPosition) {
		this.virtualPosition = virtualPosition;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-28
	 */
	public String getVirtualPositionName() {
		return virtualPositionName;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-28
	 * @param virtualPositionName the virtualPositionName to set
	 */
	public void setVirtualPositionName(String virtualPositionName) {
		this.virtualPositionName = virtualPositionName;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-29
	 */
	public String getPositionName() {
		return positionName;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-29
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-11-30
	 * @param virtualPositionManager the virtualPositionManager to set
	 */
	public void setVirtualPositionManager(
			IVirtualPositionManager virtualPositionManager) {
		this.virtualPositionManager = virtualPositionManager;
	}

}