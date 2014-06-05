package com.deppon.crm.module.marketing.server.manager.impl;

import com.deppon.crm.module.marketing.server.manager.IMaterializedViewManager;
import com.deppon.crm.module.marketing.server.service.IMaterviewService;
/**   
 * <p>
 * Description:固定客户散客客户数据的更新接口实现类<br />
 * </p>
 * @title MaterViewManagerImpl.java
 * @package com.deppon.crm.module.marketing.server.manager.impl
 * @author ZHANGZW
 * @version 0.1 2013-05-26
 */
public class MaterViewManagerImpl implements IMaterializedViewManager{
    private IMaterviewService   mvServeice;
    private static final int FAILD=-1;
    /**
	 * 
	 * <p>
	 * 潜散客户信息需要每天更新一次视图T_CRM_PSVISUALMARKET<br/>
	 * </p>
	 * 
	 * @author ZHANGZW
	 * @version 0.1 2013-04-26
	 * @param endDate 
	 * @param beginDate 
	 * @return int
	 */
	public int reFreshMVPSCustomerDaily() {
		//散客信息每天需要更新的视图数据
		return mvServeice.reFreshMVCuststomerDaily();
	}
	/**
	 * 
	 * <p>
	 * 固定客户信息需要每天更新一次视图T_CRM_CUSTVISUALMARKET<br/>
	 * 在执行之前，需要执行reFreshMVCuststomerMonthly 方法，保证数据的一致性
	 * </p>
	 * 
	 * @author ZHANGZW
	 * @version 0.1 2013-04-26
	 * @param endDate 
	 * @param beginDate 
	 * @return int
	 */
	public int reFreshMVCuststomerDaily() {
		//首先执行每月需要更新的视图数据，每月初进行更新
		return mvServeice.reFreshMVPSCustomerDaily();
	}
	/*
	 * getMvServeice()方法
	 * */
	public IMaterviewService getMvServeice() {
		return mvServeice;
	}
	/*
	 * setMvServeice()方法
	 * */
	public void setMvServeice(IMaterviewService mvServeice) {
		this.mvServeice = mvServeice;
	}
}
