package com.deppon.crm.module.complaint.server.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.server.dao.IBaseInfoDao;
import com.deppon.crm.module.complaint.server.service.IBaseInfoService;
import com.deppon.crm.module.complaint.shared.domain.BaseInfo;
import com.deppon.crm.module.complaint.shared.domain.BaseInfoSearchCondition;


public class BaseInfoServiceImpl implements IBaseInfoService{
	private IBaseInfoDao baseInfoDao;

	public void setBaseInfoDao(IBaseInfoDao baseInfoDao) {
		this.baseInfoDao = baseInfoDao;
	}

	
	
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
	@Override
	public boolean saveBaseInfo(BaseInfo baseInfo) {
		baseInfoDao.saveBaseInfo(baseInfo);
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
		baseInfoDao.saveBaseInfo(baseInfo);
		baseInfoDao.saveBaseType(baseInfo);
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
		baseInfoDao.saveBaseInfo(baseInfo);
		baseInfoDao.saveBaseScene(baseInfo);
		return true;
	}



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
		baseInfoDao.updateBaseInfo(baseInfo);
		return true;
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
		baseInfoDao.updateBaseInfo(baseInfo);
		baseInfoDao.updateBaseType(baseInfo);
		return true;
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
		baseInfoDao.updateBaseInfo(baseInfo);
		baseInfoDao.updateBaseScene(baseInfo);
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:基础资料-根据父节点id查询子节点的基础资料
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfoSearchCondition 父节点id
	 * @return
	 * List<BaseInfo> 子类型的基础资料集合
	 */
	@Override
	public List<BaseInfo> searchChildBaseInfoByParentId(
			BaseInfoSearchCondition baseInfoSearchCondition) {
		return baseInfoDao.searchChildBaseInfoByParentId(baseInfoSearchCondition);
	}


	/**
	 * 基础资料-删除业务项、业务范围、业务场景
	 * @param baseInfoList 
	 * @return
	 */
	@Override
	public boolean deleteBaseInfo(List<BaseInfo> baseInfoList) {
		baseInfoDao.deleteBaseInfo(baseInfoList);
//		baseInfoDao.deleteBaseInfoForCheck(baseInfoList.get(0));
		return true;
	}


	/**
	 * 基础资料-删除业务类型
	 * @param baseInfoList
	 * @return
	 */
	@Override
	public boolean deleteBaseType(List<BaseInfo> baseInfoList) {
		baseInfoDao.deleteBaseType(baseInfoList);
		baseInfoDao.deleteBaseInfo(baseInfoList);
//		baseInfoDao.deleteBaseInfoForCheck(baseInfoList.get(0));
		return true;
	}


	/**
	 * 基础资料-删除场景原因
	 * @param baseInfoList
	 * @return
	 */
	@Override
	public boolean deleteBaseScene(List<BaseInfo> baseInfoList) {
		baseInfoDao.deleteBaseScene(baseInfoList);
		baseInfoDao.deleteBaseInfo(baseInfoList);
//		baseInfoDao.deleteBaseInfoForCheck(baseInfoList.get(0));
		return true;
	}
	
}
