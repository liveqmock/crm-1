package com.deppon.crm.module.authorization.server.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.deppon.crm.module.authorization.server.manager.ITreeManager;
import com.deppon.crm.module.authorization.shared.domain.Function;
import com.deppon.crm.module.authorization.shared.domain.Tree;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.TreeNode;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.entity.IFunction;
import com.deppon.foss.framework.entity.IRole;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * 常用菜单WEB接入层
 * 
 * @author justin.xu
 * @date :2012-05-07
 */
public class UsualFunctionAction extends AbstractAction {
	
	private static final long serialVersionUID = -7002028275518010396L;
	
	// 注入treeManager
	private ITreeManager treeManager;

	// 注入国际化类messageBundle
//	private IMessageBundle messageBundle;

	// 分页最大记录数
	private int limit;

	// 分页开始记录数
	private int start;

	// 总记录数
	private Long totalCount;

	// 提示信息
	private String message;

	// 已选择的菜单信息集合
	private List<Tree> chooesUsualFunctons;
	private String isIndex;
	public String getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(String isIndex) {
		this.isIndex = isIndex;
	}

	//功能树节点集合
	@SuppressWarnings("rawtypes")
	private List<TreeNode> nodes;
	/**
	 * 
	 * @description 保存常用菜单
	 * @version 1.0
	 * @author justin
	 * @update 2012-05-07
	 */
	public String saveUsualFunction() {
		User currentUser = (User) (UserContext.getCurrentUser());
		treeManager.savaOrUpdateTree(chooesUsualFunctons, currentUser);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 查询当前授权用户给用户已经分配的角色
	 * @version 1.0
	 * @author justin
	 * @update 2012-05-07
	 */
	public String findAuthedUsualFunction() {
		User currentUser = (User) (UserContext.getCurrentUser());
		chooesUsualFunctons = treeManager.getTreeByUser(currentUser);
		validateUseHaveAuth(chooesUsualFunctons);
		return SUCCESS;
	}

	/**
	 * @description 查询常用功能树的节点
	 * @version 1.0
	 * @author justin.xu
	 */
	@SuppressWarnings("rawtypes")
	public String loadUsualFunctionTree() {
		nodes = new ArrayList<TreeNode>();
		User currentUser = (User) (UserContext.getCurrentUser());
		List<Tree> chooesUsualFunctons = treeManager.getTreeByUser(currentUser);
		//验证用户是否拥有此权限
		validateUseHaveAuth(chooesUsualFunctons);
		
		//添加标题
		Function fun=new Function();
//		fun.setFunctionName("常用功能管理");
		
		TreeNode<Function> treeNode = new TreeNode<Function>();
		if(null!=isIndex && isIndex.equals("1")){
			treeNode.setText("常用功能管理"+"<img src='../images/login/set.png' width='145' onclick='javascript:showUsualFunctionWindow();' id='setpic' height='126' style='cursor:pointer;'>");	
		}else{
			treeNode.setText("常用功能管理");
		}
		treeNode.setCls("nav");
		treeNode.setLeaf(false);
		treeNode.setParentId(null);
		
		treeNode.setEntity(fun);
		nodes.add(treeNode);
		if(null!=chooesUsualFunctons && chooesUsualFunctons.size()>0){
			ICache<String, IFunction> functionCache = CacheManager.getInstance().getCache(IFunction.class.getName());
			for (Tree subTree : chooesUsualFunctons) {
				//设置functionCode
				for (IFunction func : functionCache.get().values()) {
					Function function = (Function) func;
					if(null!=function.getId() && function.getId().equals(subTree.getNoteid()+"")){
						subTree.setFunctionCode(function.getFunctionCode());
						break;
					}
				}
				
				Function subfun=new Function();
				subfun.setId(subTree.getNoteid()+"");
//				subfun.setFunctionCode("index001");
				subfun.setFunctionCode(subTree.getFunctionCode());
				subfun.setFunctionName(subTree.getNotename());
				subfun.setUri(subTree.getNoteurl());
				
				TreeNode<Function> subTreeNode = new TreeNode<Function>();
				subTreeNode.setId(subTree.getNoteid()+"");
				subTreeNode.setText(subTree.getNotename());
				subTreeNode.setCls("nav_sub");
				subTreeNode.setLeaf(true);
				subTreeNode.setParentId(null);
				
				subTreeNode.setEntity(subfun);
				nodes.add(subTreeNode);
			}
		}
		return SUCCESS;
	}
	/**
	 * 验证用户是否拥有此权限
	 * @param chooesUsualFunctons
	 */
	private void validateUseHaveAuth(List<Tree> chooesUsualFunctons) {
		//添加权限控制，即常用菜单中的功能必须是用户所拥有的菜单
		if(null!=chooesUsualFunctons && chooesUsualFunctons.size()>0){
			IUser user = UserContext.getCurrentUser();
			ICache<String, IRole> roleCache = CacheManager.getInstance().getCache(IRole.class.getName());
			Set<String> functionIds = new HashSet<String>();
			for(String roleId : ((User) user).getRoleids()){
				IRole role = roleCache.get(roleId);
				if(!CollectionUtils.isEmpty(role.getFunctionIds())){
					functionIds.addAll(role.getFunctionIds());
				}
			}
			for(int i=0;i<chooesUsualFunctons.size();i++){
				Tree subTree=chooesUsualFunctons.get(i);
				if(!functionIds.contains(subTree.getNoteid().toString())){
					chooesUsualFunctons.remove(i);
				}
			}
		}
	}
	
//	public void setMessageBundle(IMessageBundle messageBundle) {
//		this.messageBundle = messageBundle;
//	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Tree> getChooesUsualFunctons() {
		return chooesUsualFunctons;
	}

	public void setChooesUsualFunctons(List<Tree> chooesUsualFunctons) {
		this.chooesUsualFunctons = chooesUsualFunctons;
	}

	public void setTreeManager(ITreeManager treeManager) {
		this.treeManager = treeManager;
	}

	@SuppressWarnings("rawtypes")
	public List<TreeNode> getNodes() {
		return nodes;
	}
}
