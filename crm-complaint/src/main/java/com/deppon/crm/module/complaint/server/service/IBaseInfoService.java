package com.deppon.crm.module.complaint.server.service;

import java.math.BigDecimal; 
import java.util.List;
 
import com.deppon.crm.module.complaint.shared.domain.BaseInfo;
import com.deppon.crm.module.complaint.shared.domain.BaseInfoSearchCondition;


public interface IBaseInfoService {
	
	
	
	
	
	/**
	 * 
	 * <p>
	 * Description:基础资料-保存业务项、业务范围、业务场景
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo
	 * void
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
	 * void
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
	 * void
	 */
	public boolean saveBaseScene(BaseInfo baseInfo);
	
	/**
	 * 
	 * <p>
	 * Description:基础资料-修改业务项、业务范围、业务场景
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo
	 * void
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
	 * void
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
	 * void
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
}
