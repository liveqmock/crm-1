package com.deppon.crm.module.authorization.server.manager;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.Tree;
import com.deppon.crm.module.authorization.shared.domain.User;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ITreeManager.java
 * @package com.deppon.crm.module.common.server.manager 
 * @author ouy
 * @version 0.1 2012-5-7
 */
public interface ITreeManager {
	public List<Tree> getTreeByUser(User user);
	public void savaOrUpdateTree(List<Tree> treeList,User user);
}
