package com.deppon.crm.module.organization.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.common.server.manager.impl.LadingstationDepartmentManager;
import com.deppon.crm.module.common.shared.domain.TreeNode;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.BizDept;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 
 作 者：张斌 最后修改时间：2011年10月20日 描 述：
 * T_ORG_DEPARTMENT部门信息ACTION层（通过node，调用loadTree方法展开树
 * ，通过所要展开节点的ID信息，获取该节点的子节点，并进行过滤,通过快速定位查询相应部门的部门序列）
 */
public class DepartmentAction extends AbstractAction {

	//所有事业部的父ID
	private static final String PREPARENTID = "265"; 

	// 要展开节点的id
	private String node;

	// 节点对象数组
	@SuppressWarnings("rawtypes")
	private List<TreeNode> nodes;

	// 部门名称，用来模糊查询其所在部门
	private String deptName;

	// 前台传来的deptSeq的字符串连接
	private String seqAll;
	
	//部门Id，用来模糊查询其所在的部门
	private String deptId;
	
	private int start;
	
	private int limit;
	
	//集中结算部门List（新）
	List<Map<String, Object>> deptList = new ArrayList<Map<String,Object>>();;
	
	public List<Map<String, Object>> getDeptList() {
		return deptList;
	}

	public void setFocusDeptList(List<Map<String, String>> focusDeptList) {
		this.focusDeptList = focusDeptList;
	}

	//所有事业部LIst
	private List<BizDept> bizDeptList;
	//集中结算部门LIst
	private List<Map<String,String>> focusDeptList = new ArrayList<Map<String,String>>();
	// 注入DepartmentService
	private IDepartmentService departmentService;
	//注入LadingstationDepartmentManager（新）
	private LadingstationDepartmentManager ladingstationDepartmentManager;
	/**
	 * 通过node，调用loadTree方法展开树 loadDepartmentTree
	 * 
	 * @since JDK1.6
	 */
	@JSON
	public String loadDepartmentTree() {
		Department deptment = new Department();
		Department deptmentPa = new Department();
		deptmentPa.setId(node);
		deptment.setParentCode(deptmentPa);
		loadTree(deptment);
		return SUCCESS;
	}

	public String queryAllBusiness(){
		bizDeptList = departmentService.getAllBizDept(PREPARENTID);
		return SUCCESS;
	}

	/**
	 * 通过所要展开节点的ID信息，获取该节点的子节点，并进行过滤 loadTree
	 * 
	 * @param department
	 * @return void
	 * @since JDK1.6
	 */
	@SuppressWarnings("rawtypes")
	private void loadTree(Department department) {
		List<Department> departmentList = departmentService
				.queryDirectChildDepts(department);
		/**
		 * 获取前台传来的deptSeq集合的字符串，将其通过“,”分割开，获取快速查询部门的父部门ID 并将数据放到HashSet中
		 */
		if (seqAll != null && !seqAll.equals("[")) {
			Set<String> set = new HashSet<String>();
			String[] stringL = seqAll.split(",");
			for (String id : stringL) {
				set.add(id);
			}
			/**
			 * 将得到的子部门进行过滤，将要查找部门的兄弟部门过滤掉，只剩下其父部门
			 */
			for (int i = 0; i < departmentList.size(); i++) {
				if (!set.contains(departmentList.get(i).getId())) {
					departmentList.remove(i);
					i--;
				}
			}
		}
		nodes = new ArrayList<TreeNode>();
		for (Department dept : departmentList) {
			TreeNode<Department> treeNode = new TreeNode<Department>();
			treeNode.setId(dept.getId());
			treeNode.setText(dept.getDeptName());
			if (dept.getLeaf()) {
				treeNode.setLeaf(true);
			} else {
				treeNode.setLeaf(false);
			}
			if (dept.getParentCode() != null) {
				treeNode.setParentId(dept.getParentCode().getId());
			} else {
				treeNode.setParentId(null);
			}
			treeNode.setEntity(dept);
			nodes.add(treeNode);
		}

	}

	/**
	 * 通过快速定位查询相应部门的部门序列,并做过重复数据的滤处理 querySeq
	 * 
	 * @since JDK1.6
	 */
	@JSON
	public String querySeq() {
		if (deptName == null || deptName.equals("")) {
			deptName = "德邦物流查询全部ALL";
		}
		List<String> seqList = departmentService.querySeq(deptName);
		Set<String> set = new HashSet<String>();
		for (String seq : seqList) {
			String[] stringL = seq.split("\\.");
			for (String id : stringL) {
				set.add(id);
			}
		}
		seqAll = set.toString();
		seqAll = seqAll.replaceAll(" ", "");
		seqAll = seqAll.replaceAll("]", "");
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * 获取集中结算部门<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-5-16
	 */
	public String acquireDeptByName() {

		int count;
//		for(Department department:departmentService.getDepartmentByDeptName(deptName)){
//			Map<String,String> focusDept = new HashMap<String,String>();
//			focusDept.put("id", department.getId());
//			focusDept.put("deptName", department.getDeptName());
//			focusDeptList.add(focusDept);
//		}	
		if("" !=deptName){deptList = ladingstationDepartmentManager.getBusDeptByDeptIdOrDeptName(deptId, deptName, 0, Integer.MAX_VALUE);
		Map<String,String> focusDept = null;
		for (Map<String,Object> map : deptList) {
			focusDept = new HashMap<String,String>();
			focusDept.put("id", String.valueOf(map.get("DEPTID")));
			focusDept.put("deptName", (String) map.get("DEPTNAME"));
			focusDept.put("provinceName", (String) map.get("PROVINCENAME"));
			focusDeptList.add(focusDept);
		}
		count = ladingstationDepartmentManager.countBusDeptByDeptIdOrDeptName(deptId, deptName);
		}else{
			deptList = null;
			count = 0;
		}
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * 获取营业部<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-5-16
	 */
	public String acquireBusinessDeptByName() {
		
		int count;
//		for(Department department:departmentService.getDepartmentByDeptName(deptName)){
//			Map<String,String> focusDept = new HashMap<String,String>();
//			focusDept.put("id", department.getId());
//			focusDept.put("deptName", department.getDeptName());
//			focusDeptList.add(focusDept);
//		}	
		if("" !=deptName){deptList = ladingstationDepartmentManager.getAllBusDeptByDeptIdOrDeptName(deptId, deptName, 0, Integer.MAX_VALUE);
		Map<String,String> focusDept = null;
		for (Map<String,Object> map : deptList) {
			focusDept = new HashMap<String,String>();
			focusDept.put("id", String.valueOf(map.get("DEPTID")));
			focusDept.put("deptName", (String) map.get("DEPTNAME"));
			focusDept.put("provinceName", (String) map.get("PROVINCENAME"));
			focusDeptList.add(focusDept);
		}
		count = ladingstationDepartmentManager.countAllBusDeptByDeptIdOrDeptName(deptId, deptName);
		}else{
			deptList = null;
			count = 0;
		}
		return SUCCESS;
	}
	/*--------------------------------元素get/set方法----------------------------------------------------------*/
	public String getNode() {
		return node;
	}

	public void setDeptList(List<Map<String, Object>> deptList) {
		this.deptList = deptList;
	}

	public void setNode(String node) {
		this.node = node;
	}

	@SuppressWarnings("rawtypes")
	public List<TreeNode> getNodes() {
		return nodes;
	}

	@SuppressWarnings("rawtypes")
	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getSeqAll() {
		return seqAll;
	}

	public void setSeqAll(String seqAll) {
		this.seqAll = seqAll;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public List<BizDept> getBizDeptList() {
		return bizDeptList;
	}

	public List<Map<String, String>> getFocusDeptList() {
		return focusDeptList;
	}

	public void setLadingstationDepartmentManager(
			LadingstationDepartmentManager ladingstationDepartmentManager) {
		this.ladingstationDepartmentManager = ladingstationDepartmentManager;
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
