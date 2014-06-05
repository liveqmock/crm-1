/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IAreaDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.authorization.server.dao;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.deppon.crm.module.authorization.shared.domain.Tree;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ITreeDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author ouy
 * @version 0.1 2012-5-7
 */
public interface ITreeDao {
	public List<Tree> getTreeByUserId(Integer userid);
	public Tree getTreeById(Integer userid);
	public  Integer saveTree(Tree tree);
	public Integer deleteTreeByUser(Integer id);
	public Integer deleteTreeById(Integer id);
}
