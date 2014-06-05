package com.deppon.crm.module.authorization.server.service.impl;

import java.util.List;

import com.deppon.crm.module.authorization.server.dao.ITreeDao;
import com.deppon.crm.module.authorization.server.service.ITreeService;
import com.deppon.crm.module.authorization.shared.domain.Tree;
import com.deppon.crm.module.authorization.shared.domain.User;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ITreeService.java
 * @package com.deppon.crm.module.common.server.service.impl 
 * @author ouy
 * @version 0.1 2012-5-7
 */
public class TreeService implements ITreeService {
	private ITreeDao treeDao;
	@Override
	public List<Tree> getTreeByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return treeDao.getTreeByUserId(userId);
	}
	@Override
	public Integer saveTree(Tree tree) {
		// TODO Auto-generated method stub
		return treeDao.saveTree(tree);
	}
	
	@Override
	public Integer deleteTreeByUser(Integer id) {
		// TODO Auto-generated method stub
		return treeDao.deleteTreeByUser(id);
	}
	public ITreeDao getTreeDao() {
		return treeDao;
	}
	public void setTreeDao(ITreeDao treeDao) {
		this.treeDao = treeDao;
	}

	
}
