/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CityDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.authorization.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.server.dao.ITreeDao;
import com.deppon.crm.module.authorization.shared.domain.Tree;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TreeDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author ouy
 * @version 0.1 2012-5-7
 */

public class TreeDao extends iBatis3DaoImpl implements ITreeDao {
	private static final String NAMESPACE = "com.deppon.crm.module.authorization.shared.domain.Tree.";
	private static final String GET_TREE_BY_USERID="getTreeByUser";
	private static final String SAVE_TREE="saveTree";
	private static final String DELETE_TREE_BY_USER="deleteByUserId";
	private static final String DELETE_TREE_BY_ID="deleteById";
	private static final String SELECT_TREE_BY_ID="selectTreeById";
	@Override
	public List<Tree> getTreeByUserId(Integer userid) {
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("userid", userid);
		List<Tree> treeList=this.getSqlSession().selectList(NAMESPACE+GET_TREE_BY_USERID,map);;
		return treeList;
	}

	@Override
	public Integer saveTree(Tree tree) {
		this.getSqlSession().insert(NAMESPACE+SAVE_TREE,tree);
		return Integer.parseInt(tree.getId());
	}

	@Override
	public Integer deleteTreeByUser(Integer userid) {
		// TODO Auto-generated method stub
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("userid", userid);
		Integer fid=(Integer)this.getSqlSession().delete(NAMESPACE+DELETE_TREE_BY_USER,map);
		return fid;
	}

	@Override
	public Integer deleteTreeById(Integer id) {
		// TODO Auto-generated method stub
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("id", id);
		Integer fid=(Integer)this.getSqlSession().delete(NAMESPACE+DELETE_TREE_BY_ID,map);
		return fid;
	}

	@Override
	public Tree getTreeById(Integer id) {
		// TODO Auto-generated method stub
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("id", id);
		Tree tree=(Tree)this.getSqlSession().selectOne(NAMESPACE+SELECT_TREE_BY_ID,map);
		return tree;
	}
	
}
