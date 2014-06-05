package com.deppon.crm.module.complaint.server.manager;

import java.math.BigDecimal;
import java.util.List; 

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.TreeNode;
import com.deppon.crm.module.complaint.shared.domain.BaseInfo;
import com.deppon.crm.module.complaint.shared.domain.BaseInfoSearchCondition;

/**
 * <p>
 * Description:工单业务规则<br />
 * </p>
 * @title IComplaintBaseInfoManager.java
 * @package com.deppon.crm.module.complaint.server.manager
 * @author 侯斌飞
 * @version 0.1 2013-09-12
 */
public interface IBaseInfoManager {
	
	/**
	 * 
	 * <p>
	 * Description:基础资料---新增
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo  
	 * @param user
	 * @return
	 * boolean
	 */
	public boolean saveBaseInfo(BaseInfo baseInfo);
	/**
	 * 
	 * <p>
	 * Description:基础资料---修改
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo  
	 * @param user
	 * @return
	 * boolean
	 */
	public boolean updateBaseInfo(BaseInfo baseInfo);
	
	/**
	 * 
	 * <p>
	 * Description:基础资料---删除
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfoList
	 * @param user
	 * @return
	 * boolean
	 */
	public boolean deleteBaseInfo(List<BaseInfo> baseInfoList);
	
	/**
	 * 
	 * <p>
	 * Description:基础资料-根据父节点id查询子节点的基础资料
	 * </p>
	 * @author LiuY 
	 * @version 0.1 2013-9-23
	 * @param baseInfoSearchCondition 查询条件
	 * @return
	 * List<BaseInfoSearchCondition> 子类型的基础资料集合
	 */
	public List<BaseInfo> searchChildBaseInfoByParentId(BaseInfoSearchCondition baseInfoSearchCondition);
	
	/**
	 * 
	 * <p>
	 * Description:treeNodeList 基础资料-根据父节点id查询子节点的基础资料集合
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param node 父节点id
	 * @return
	 * List<BaseInfoSearchCondition> 子类型的基础资料集合
	 */
	public List<TreeNode<BaseInfo>> searchTreeChildNodeList(String node);
	
}
