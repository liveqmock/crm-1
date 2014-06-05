package com.deppon.crm.module.login.server.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.deppon.crm.module.authorization.shared.domain.Function;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.TreeNode;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.define.FunctionType;
import com.deppon.foss.framework.entity.IFunction;
import com.deppon.foss.framework.entity.IRole;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.exception.security.UserNotLoginException;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * 功能菜单WEB接入层
 * @author Administrator
 *
 */
public class MenuAction extends AbstractAction{

	private static final long serialVersionUID = 1851855354424501485L;

	//子功能信息集合
	private List<Function> functions;
	
	//父功能编码
	private String node;
	
	public String getNode() {
		return node;
	}

	//子系统信息集合
	private List<Function> subSystemNodes;
	
	//功能树节点集合
	@SuppressWarnings("rawtypes")
	private List<TreeNode> nodes;

	/**
	 * 得到用户拥有的子系统功能信息
	 * @return 
	 */
	public String loadSubSystem() {
		subSystemNodes=findFunction(FunctionType.SUBSYSTEM,null);
		return SUCCESS;
	}

	/**
	 * 查询1级菜单动态构造tabPanl
	 * @return
	 */
	public String loadSubSystemByTab(){
		subSystemNodes=findFunction(FunctionType.MODULE,null);
		return SUCCESS;
	}
	
	//查询功能方法
	@SuppressWarnings("unchecked")
	private List<Function> findFunction(String type,String parentCode) {
		IUser user = UserContext.getCurrentUser();
		if (user == null) {
			throw new UserNotLoginException();
		}
		ICache<String, IFunction> functionCache = CacheManager.getInstance().getCache(IFunction.class.getName());
		//修改时间 :2012-03-20 21:42:00
		//修改人：ztjie 
		//修改说明：
		//修改之前：用户功能通过直接从用户对象得到功能权限信息，但是这个就会有一个问题，
		//		         当角色进行修改，那么用户缓存并不会更新，那么修改后的角色数据没有办法更新
		//修改之后：用户功能通过角色进行加载，那么修改角色后，只要角色缓存数据更新，那么用户的功能权限就更新了
		ICache<String, IRole> roleCache = CacheManager.getInstance().getCache(IRole.class.getName());
		Set<String> functionIds = new HashSet<String>();
		for(String roleId : ((User) user).getRoleids()){
			IRole role = roleCache.get(roleId);
			if(null!=role&&!CollectionUtils.isEmpty(role.getFunctionIds())){
				functionIds.addAll(role.getFunctionIds());
			}
			
		}
		List<Function> functionNodes = new ArrayList<Function>();
		for (IFunction func : functionCache.get().values()) {
			//修改时间 :2012-03-20 21:42:00
			//修改人：ztjie 
			//修改说明：
			//修改之前：没有对功能进行是否启用的判断
			//修改之后：增加是否启用的判断
			//update time 2012-04-01 
			//author zhangdeng 
			//dis 修改了功能启用为空不能登录的问题
			if(func.getValidFlag()!=null && !func.getValidFlag())
				continue;
			Byte functionType = ((Function)func).getFunctionType();
			if(functionType==null||functionType.toString().equals(FunctionType.BUTTON)){
				continue;
			}
			//如果当前用户有该功能，则加入用户功能集合中
			if(type!=null){
				if(functionType.toString().equals(type)){
					if(functionIds.contains(func.getId())){
						functionNodes.add((Function) func);					
					}
				}	
			}else{
				if(functionIds.contains(func.getId())){
					Function parentFunction = ((Function) func).getParentCode();
					if(parentFunction==null){
						continue;
					}
					String functionCode = parentFunction.getFunctionCode();					
					if(functionCode.equals(parentCode)){
						functionNodes.add((Function) func);									
					}
				}
			}
		}
		//对子系统功能进行排序
		Collections.sort(functionNodes,new Comparator<Function>() {

			public int compare(Function o1, Function o2) {
				return o1.getDisplayOrder().compareTo(o2.getDisplayOrder());
			}
			
		});
		return functionNodes;
	}
//	/**
//	 * 
//	 * @description 根据标签页查询对应的工作空间
//	 * @version 1.0
//	 * @author patern
//	 * @update 2011-10-12 下午01:16:50
//	 */
	public String showWorkSpace(){
		IUser user = UserContext.getCurrentUser();
		if (user == null) {
//			throw new UserNotLoginException();
			return "logout";
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @description 查询树的节点
	 * @version 1.0
	 * @author patern
	 * @update 2011-10-12 下午01:16:50
	 */
	@SuppressWarnings("rawtypes")
	public String loadTree() {
		List<Function> functions=findFunction(null,node);
		nodes = new ArrayList<TreeNode>();
		for(Function fun : functions){
			TreeNode<Function> treeNode = new TreeNode<Function>();
			treeNode.setId(fun.getFunctionCode());
			treeNode.setText(fun.getFunctionName());
//				if(fun.getFunctionType()==3){
//					treeNode.setLeaf(true);
//					treeNode.setIconCls("treeNodeLeafIcon");
//				}else{
//					treeNode.setIconCls("treeNodePackageIcon");
//					treeNode.setLeaf(false);
//				}
			if (fun.getParentCode()!=null) {
				treeNode.setParentId(fun.getParentCode().getFunctionCode());
			} else {
				treeNode.setParentId(null);
			}
			if(fun.getFunctionType()==3){//类型是菜单的
				if(fun.getFunctionLevel()==4){//如果是第二级菜单
					List<Function> f=findFunction(null,fun.getFunctionCode());
					if(f.size()>0){//说明有子节点
						treeNode.setCls("nav_sub_more");
						treeNode.setLeaf(false);
					}else{
						treeNode.setCls("nav_sub");
						treeNode.setLeaf(true);
					}
				}else if(fun.getFunctionLevel()==5){//如果是三级菜单
					treeNode.setCls("nav_sub_sub");
					treeNode.setLeaf(true);
				}
			}
			treeNode.setEntity(fun);
			nodes.add(treeNode);
		}
		return SUCCESS;
	}
	
	/**
	 * 保持会话
	 */
	public String keepSessionOnLine(){
		return SUCCESS;
	}

	public void setNode(String node) {
		this.node = node;
	}

	@SuppressWarnings("rawtypes")
	public List<TreeNode> getNodes() {
		return nodes;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public List<Function> getSubSystemNodes() {
		return subSystemNodes;
	}
	
	
}
