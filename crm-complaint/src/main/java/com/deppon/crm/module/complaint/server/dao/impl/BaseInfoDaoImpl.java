package com.deppon.crm.module.complaint.server.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.server.dao.IBaseInfoDao;
import com.deppon.crm.module.complaint.shared.domain.BaseInfo;
import com.deppon.crm.module.complaint.shared.domain.BaseInfoSearchCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class BaseInfoDaoImpl extends iBatis3DaoImpl implements IBaseInfoDao {

	//基础资料namespace
	private static final String NAMESPACE_BASEINFO="com.deppon.crm.module.complaint.shared.domain.BaseInfo.";
	//根据父节点id查询子节点的基础资料
	private static final String SELECTCHILDSBASEINFO="selectChildsBaseInfo";
	//保存业务项、业务范围、业务场景
	private static final String INSERTBASEINFO="insertBaseInfo";
	//保存业务类型
	private static final String INSERTBASETYPE= "insertBaseType";
	//保存场景原因
	private static final String INSERTBASESCENE="insertBaseScene";
	//添加基础资料时，讲其父节点的ifLeaf字段改为0（即存在子节点）
	private static final String UPDATEBASEINFOFORINSERT="updateBaseInfoForInsert";
	//修改业务项、业务范围、业务场景
	private static final String UPDATEBASEINFO="updateBaseInfo";
	//修改业务类型
	private static final String UPDATEBASETYPE="updateBaseType";
	//修改场景原因
	private static final String UPDATEBASESCENE="updateBaseScene";
	//删除业务资料
	private static final String DELETEBASEINFO="deleteBaseInfo";
	//删除业务类型
	private static final String DELETEBASETYPE="deleteBaseType";
	//删除场景原因
	private static final String DELETEBASESCENE="deleteBaseScene";
	//在删除后判断删除对象的父节点是否还有子节点
	private static final String DELETEBASEINFOFORCHECK="deleteBaseInfoForCheck";
	/**
	 * 
	 * <p>
	 * Description:基础资料-保存业务项、业务范围、业务场景
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo
	 * 
	 */
	@Override
	public boolean saveBaseInfo(BaseInfo baseInfo) {
		this.getSqlSession().insert(NAMESPACE_BASEINFO+INSERTBASEINFO, baseInfo);
		if(baseInfo.getLevel() != 1){
			this.getSqlSession().update(NAMESPACE_BASEINFO+UPDATEBASEINFOFORINSERT, baseInfo);
		}
		return true;
	}
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
	@Override
	public boolean saveBaseType(BaseInfo baseInfo) {
		this.getSqlSession().insert(NAMESPACE_BASEINFO+INSERTBASETYPE, baseInfo);
		return true;
	}
	/**
	 * 
	 * <p>
	 * Description:基础资料-保存业务场景
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo
	 * void
	 */
	@Override
	public boolean saveBaseScene(BaseInfo baseInfo) {
		this.getSqlSession().insert(NAMESPACE_BASEINFO+INSERTBASESCENE, baseInfo);
		return true;
	}
//	/**
//	 * 添加基础资料时，讲其父节点的ifLeaf字段改为0（即存在子节点）
//	 * @param baseInfo
//	 * @return
//	 */
//	@Override
//	public boolean updateBaseInfoForInsert(BaseInfo baseInfo) {
//		
//		return true;
//	}
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
	@Override
	public boolean updateBaseInfo(BaseInfo baseInfo) {
		this.getSqlSession().update(NAMESPACE_BASEINFO+UPDATEBASEINFO, baseInfo);
		return false;
	}
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
	@Override
	public boolean updateBaseType(BaseInfo baseInfo) {
		this.getSqlSession().update(NAMESPACE_BASEINFO+UPDATEBASETYPE, baseInfo);
		return false;
	}
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
	@Override
	public boolean updateBaseScene(BaseInfo baseInfo) {
		this.getSqlSession().update(NAMESPACE_BASEINFO+UPDATEBASESCENE, baseInfo);
		return false;
	}
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
	@Override
	public List<BaseInfo> searchChildBaseInfoByParentId(
			BaseInfoSearchCondition baseInfoSearchCondition) {
		return  this.getSqlSession().selectList(NAMESPACE_BASEINFO+SELECTCHILDSBASEINFO, baseInfoSearchCondition);
	}
	
	/**
	 * 基础资料-删除业务项、业务范围、业务场景
	 * @param baseInfoList 
	 * @return
	 */
	@Override
	public boolean deleteBaseInfo(List<BaseInfo> baseInfoList) {
		this.getSqlSession().delete(NAMESPACE_BASEINFO+DELETEBASEINFO, baseInfoList);
		this.getSqlSession().update(NAMESPACE_BASEINFO+DELETEBASEINFOFORCHECK, baseInfoList.get(0));
		return true;
	}
	/**
	 * 基础资料-删除业务类型
	 * @param baseInfoList
	 * @return
	 */
	@Override
	public boolean deleteBaseType(List<BaseInfo> baseInfoList) {
		this.getSqlSession().delete(NAMESPACE_BASEINFO+DELETEBASETYPE, baseInfoList);
		return true;
	}
	/**
	 * 基础资料-删除场景原因
	 * @param baseInfoList
	 * @return
	 */
	@Override
	public boolean deleteBaseScene(List<BaseInfo> baseInfoList) {
		this.getSqlSession().delete(NAMESPACE_BASEINFO+DELETEBASESCENE, baseInfoList);
		return true;
	}
	/**
	 * 删除业务资料时判断删除父节点是否还存在子集，如果不存在则将父节点修改为叶子节点
	 * @param baseInfo
	 * @return
	 */
	@Override
	public boolean deleteBaseInfoForCheck(BaseInfo baseInfo) {
		this.getSqlSession().update(NAMESPACE_BASEINFO+DELETEBASEINFOFORCHECK, baseInfo);
		return true;
	}

	
	
}