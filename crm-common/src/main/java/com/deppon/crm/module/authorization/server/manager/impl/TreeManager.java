/**   
 * Description:这里写描述<br />
 * @title AreaAddressManager.java
 * @package com.deppon.crm.module.common.server.manager.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.authorization.server.manager.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.server.manager.ITreeManager;
import com.deppon.crm.module.authorization.server.service.ITreeService;
import com.deppon.crm.module.authorization.shared.domain.Tree;
import com.deppon.crm.module.authorization.shared.domain.User;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BankInfoManager.java
 * @package com.deppon.crm.module.common.server.manager.impl 
 * @author ouy
 * @version 0.1 2012-3-31
 */
@Transactional
public class TreeManager implements ITreeManager {
	private ITreeService treeService;
	
	@Override
	@Transactional(readOnly=true)
	public List<Tree> getTreeByUser(User user) {
		// TODO Auto-generated method stub
		List<Tree> treeList=treeService.getTreeByUserId(Integer.parseInt(user.getId()));
		return treeList;
	}
	
	@Override
	public void savaOrUpdateTree(List<Tree> treeList, User user) {
		treeService.deleteTreeByUser(Integer.parseInt(user.getId()));
		if(null !=treeList && treeList.size()>0){
			for (Tree tree : treeList) {
				tree.setCreateUserId(Integer.parseInt(user.getId()));
				tree.setUserid(Integer.parseInt(user.getId()));
				treeService.saveTree(tree);
			}
		}
	}
	public ITreeService getTreeService() {
		return treeService;
	}
	
	public void setTreeService(ITreeService treeService) {
		this.treeService = treeService;
	}
	
}