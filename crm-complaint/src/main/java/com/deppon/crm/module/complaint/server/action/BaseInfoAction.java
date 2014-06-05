package com.deppon.crm.module.complaint.server.action;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.TreeNode;
import com.deppon.crm.module.complaint.server.manager.IBaseInfoManager;
import com.deppon.crm.module.complaint.shared.domain.BaseInfo;
import com.deppon.crm.module.complaint.shared.domain.BaseInfoSearchCondition;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;


/**
 * <p>
 * 工单基础资料action<br />
 * </p>
 * 
 * @title ComplaintBaseInfoAction.java
 * @package com.deppon.crm.module.complaint.server.action
 * @author 侯斌飞
 * @version 0.1 2013-09-12
 */

public class BaseInfoAction extends AbstractAction {
	//基础资料 manager
	private IBaseInfoManager baseInfoManager;
	/**
	 * 设置基础资料 manger
	 * @param baseInfoManager
	 */
	public void setBaseInfoManager(IBaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}
	
	
	private List<TreeNode<BaseInfo>> nodes;
	
	
	// 要展开节点的id
	private String node;
	
	private List<BaseInfo> list;
	
	private Integer id;
	
	//查询条件
	private String queryName;
	
	private BaseInfo baseInfo;
	
	private BaseInfoSearchCondition condition;
	
	
	public List<TreeNode<BaseInfo>> getNodes() {
		return nodes;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public List<BaseInfo> getList() {
		return list;
	}

	
	public void setList(List<BaseInfo> list) {
		this.list = list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setBaseInfo(BaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	
	public BaseInfoSearchCondition getCondition() {
		return condition;
	}

	public void setCondition(BaseInfoSearchCondition condition) {
		this.condition = condition;
	}

	/**
	 * 
	 * <p>
	 * Description:基础资料树形菜单<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-9-23
	 * @return
	 * String
	 */
	@JSON
	public String loadTree(){
		nodes=baseInfoManager.searchTreeChildNodeList(node);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:基础资料列表<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-9-23
	 * @return
	 * String
	 */
	@JSON
	public String findBaseInfoList(){
		BaseInfoSearchCondition bi = new BaseInfoSearchCondition();
		bi.setParentId(id);
		bi.setBaseInfo(queryName);
		list=baseInfoManager.searchChildBaseInfoByParentId(bi);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:添加基础资料<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-9-25
	 * @return
	 * String
	 */
	@JSON
	public String addBaseInfo(){
		baseInfoManager.saveBaseInfo(baseInfo);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:修改基础资料<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-9-25
	 * @return
	 * String
	 */
	@JSON
	public String updateBaseInfo(){
		baseInfoManager.updateBaseInfo(baseInfo);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:删除基础资料<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-9-25
	 * @return
	 * String
	 */
	@JSON
	public String deleteBaseInfo(){
		baseInfoManager.deleteBaseInfo(list);
		return SUCCESS;
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:基础资料五级联动<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-10-08
	 * @return
	 * String
	 */
	@JSON
	public String findBaseInfoLevelList(){
		list=baseInfoManager.searchChildBaseInfoByParentId(condition);
		return SUCCESS;
	}
	
	
}

