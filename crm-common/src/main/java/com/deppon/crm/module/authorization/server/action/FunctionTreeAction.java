package com.deppon.crm.module.authorization.server.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.crm.module.authorization.server.service.IFunctionService;
import com.deppon.crm.module.authorization.shared.domain.Function;
import com.deppon.crm.module.common.shared.domain.TreeNode;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

@Controller
@Scope("prototype")
public class FunctionTreeAction extends AbstractAction {

	private static final long serialVersionUID = -1724199381817725388L;

	/*
	 * 注入functionService
	 */
	private IFunctionService functionService;

	/*
	 * 上传的父节点id，也对应功能的代码(functionCode)
	 */
	private String node;
	
	/*
	 * 参数对象
	 */
	private Function function = new Function();

	/*
	 * 节点对象数组
	 */
	@SuppressWarnings("rawtypes")
	private List<TreeNode> nodes;
	
	/*
	 * 返回节点对象
	 */
	@SuppressWarnings("rawtypes")
	private TreeNode treeNode;
	
	/*
	 * 页面上传角色ID
	 */
	private String roleId;

	/*
	 * 信息提示
	 */
	private String message;

	/**
	 * 得到功能对象，通过功能编码
	 * @return
	 */
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@JSON
//	public String findTreeNodeByCode(){
//		Function function = functionService.queryByFunctionCode(node);
//		treeNode = new TreeNode();
//		treeNode.setEntity(function);
//		return "success";
//	}
	
	/**
	 * 查询功能类型不为页面元素的指定父编码的功能对象，并转换成树结构
	 * 
	 * @return
	 */
	@JSON
	public String loadManagerFunctionTree() {
		//设置父功能编码
		Function parnetFun = new Function();
		parnetFun.setFunctionCode(node);
		function.setParentCode(parnetFun);
		//设置功能类型
		function.setFunctionType((byte) 4);
		loadTree(function,false,(byte)3);
		return SUCCESS;
	}
	
	/**
	 * 查询功能有效的指定父编码的功能对象，并转换成可可选择树结构
	 * 
	 * @return
	 */
	@JSON
	public String loadFunctionChooesTree() {
		//设置查询有效的功能
		function.setValidFlag(true);
		//设置查询需要权限检查的功能
		function.setCheck(true);
		//设置父功能编码
		Function parnetFun = new Function();
		parnetFun.setFunctionCode(node);
		function.setParentCode(parnetFun);
		loadTree(function,true,(byte)4);
		return SUCCESS;
	}

	/**
	 * 根据条件得到树节点对象
	 * @param function 查询条件
	 * @param checked 是否选择框
	 * @param leafLever 叶子节点的等级
	 * 			3 : 功能类型为3的为叶子节点
	 * 			4 : 功能类型为4的为叶子节点
	 */
	@SuppressWarnings("rawtypes")
	private void loadTree(Function function,Boolean checked,Byte leafLever) {
		List<Function> functionList = functionService.queryDirectChildFunctions(function);
		List<String> functionIds = new ArrayList<String>();
		//判断是否为选择树
		if(checked){
			if(roleId!=null){
				functionIds = functionService.queryAllFunctionIdByRoleId(roleId);				
			}
		}
		nodes = new ArrayList<TreeNode>();
		for(Function fun : functionList){
			TreeNode<Function> treeNode = new TreeNode<Function>();
			treeNode.setId(fun.getFunctionCode());
			treeNode.setText(fun.getFunctionName());
			if(fun.getFunctionType()==leafLever){
				treeNode.setLeaf(true);
			}else{
				treeNode.setLeaf(false);
			}
			if (fun.getParentCode()!=null) {
				treeNode.setParentId(fun.getParentCode().getFunctionCode());
			} else {
				treeNode.setParentId(null);
			}
			if(checked){
				boolean flag = false;
				if(functionIds!=null&&functionIds.size()>0){
					if(functionIds.contains(fun.getId())){
						flag = true;
					}
				}
				treeNode.setChecked(flag);					
			}
			treeNode.setEntity(fun);
			nodes.add(treeNode);
		}
	}

	public String getNode() {
		return node;
	}

	@SuppressWarnings("rawtypes")
	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@SuppressWarnings("rawtypes")
	public List<TreeNode> getNodes() {
		return nodes;
	}

	public String getMessage() {
		return message;
	}

	public void setNode(String node) {
		this.node = node;
	}

	@SuppressWarnings("rawtypes")
	public TreeNode getTreeNode() {
		return treeNode;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setFunctionService(IFunctionService functionService) {
		this.functionService = functionService;
	}
	/**
	 * 仅仅显示其拥有的功能，构成的树形结构
	 * @author 张斌
	 * @时间：2013-07-11:40
	 */
	@JSON
	public String searchInfoFunctionChooesTree() {
		//设置查询有效的功能
		function.setValidFlag(true);
		//设置查询需要权限检查的功能
		function.setCheck(true);
		//设置父功能编码
		Function parnetFun = new Function();
		parnetFun.setFunctionCode(node);
		function.setParentCode(parnetFun);
		loadTreeOnlySelect(function,true,(byte)4);
		return SUCCESS;
	}

	/**
	 * 根据条件得到树节点对象(仅仅有该角色拥有的功能)
	 * @param function 查询条件
	 * @param checked 是否选择框
	 * @param leafLever 叶子节点的等级
	 * 			3 : 功能类型为3的为叶子节点
	 * 			4 : 功能类型为4的为叶子节点
	 */
	@SuppressWarnings("rawtypes")
	private void loadTreeOnlySelect(Function function,Boolean checked,Byte leafLever) {
		List<Function> functionList = functionService.queryDirectChildFunctions(function);
		List<String> functionIds = new ArrayList<String>();
		//判断是否为选择树
		if(checked){
			if(roleId!=null){
				functionIds = functionService.queryAllFunctionIdByRoleId(roleId);				
			}
		}
		nodes = new ArrayList<TreeNode>();
		for(Function fun : functionList){
			TreeNode<Function> treeNode = new TreeNode<Function>();
			treeNode.setId(fun.getFunctionCode());
			treeNode.setText(fun.getFunctionName());
			if(fun.getFunctionType()==leafLever){
				treeNode.setLeaf(true);
			}else{
				treeNode.setLeaf(false);
			}
			if (fun.getParentCode()!=null) {
				treeNode.setParentId(fun.getParentCode().getFunctionCode());
			} else {
				treeNode.setParentId(null);
			}
			if(checked){
				boolean flag = false;
				if(functionIds!=null&&functionIds.size()>0){
					if(functionIds.contains(fun.getId())){
						flag = true;
					}
				}
				if(!flag){//如果标记是否选中为没有选中，则不讲该节点传给前台而直接跳过
					continue;
				}					
			}
			treeNode.setChecked(null);//不显示复选框
			treeNode.setEntity(fun);
			nodes.add(treeNode);
		}
	}
}
