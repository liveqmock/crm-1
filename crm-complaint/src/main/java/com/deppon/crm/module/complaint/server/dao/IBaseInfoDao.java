package com.deppon.crm.module.complaint.server.dao;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.shared.domain.BaseInfo;
import com.deppon.crm.module.complaint.shared.domain.BaseInfoSearchCondition;

public interface IBaseInfoDao  {
	
	/**
	 * 
	 * <p>
	 * Description:基础资料-保存业务项、业务范围、业务场景
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo
	 * @return
	 */
	public boolean saveBaseInfo(BaseInfo baseInfo);
	/**
	 * 
	 * <p>
	 * Description:基础资料-保存业务类型
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo
	 * @return 
	 */
	public boolean saveBaseType(BaseInfo baseInfo);
	/**
	 * 
	 * <p>
	 * Description:基础资料-保存场景原因
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo
	 * @return
	 */
	public boolean saveBaseScene(BaseInfo baseInfo);
//	/**
//	 * 添加基础资料时，讲其父节点的ifLeaf字段改为0（即存在子节点）
//	 * @param baseInfo
//	 * @return
//	 */
//	public boolean updateBaseInfoForInsert(BaseInfo baseInfo);
	/**
	 * 
	 * <p>
	 * Description:基础资料-修改业务项、业务范围、业务场景
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo
	 * @return
	 */
	public boolean updateBaseInfo(BaseInfo baseInfo);
	/**
	 * 
	 * <p>
	 * Description:基础资料-修改业务类型
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo
	 * @return
	 */
	public boolean updateBaseType(BaseInfo baseInfo);
	/**
	 * 
	 * <p>
	 * Description:基础资料-修改场景原因
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo
	 * @return
	 */
	public boolean updateBaseScene(BaseInfo baseInfo);
	/**
	 * 
	 * <p>
	 * Description:基础资料-根据父节点id查询子节点的基础资料
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param parentId 父节点id
	 * @return
	 * List<BaseInfoSearchCondition> 子类型的基础资料集合
	 */
	public List<BaseInfo> searchChildBaseInfoByParentId(BaseInfoSearchCondition baseInfoSearchCondition);
	
	/**
	 * 基础资料-删除业务项、业务范围、业务场景
	 * @param baseInfoList 
	 * @return
	 */
	public boolean deleteBaseInfo(List<BaseInfo> baseInfoList);
	/**
	 * 基础资料-删除业务类型
	 * @param baseInfoList
	 * @return
	 */
	public boolean deleteBaseType(List<BaseInfo> baseInfoList);
	/**
	 * 基础资料-删除场景原因
	 * @param baseInfoList
	 * @return
	 */
	public boolean deleteBaseScene(List<BaseInfo> baseInfoList);
	/**
	 * 删除业务资料时判断删除父节点是否还存在子集，如果不存在则将父节点修改为叶子节点
	 * @param baseInfo
	 * @return
	 */
	public boolean deleteBaseInfoForCheck(BaseInfo baseInfo);
}
