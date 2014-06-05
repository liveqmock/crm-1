package com.deppon.crm.module.marketing.server.manager.impl;

import com.deppon.crm.module.marketing.server.manager.IMarketAssessJobManager;

public class MarketAssessJobManagerImpl implements IMarketAssessJobManager{
	private MarketAssessManagerImpl MarketAssessManager;
	/**
	 * 
	 * <p>
	 * 每天生成营销报表<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-01-21
	 */
	@Override
	public void autoCreateMarketReport() {
		MarketAssessManager.autoCreateMarketReport();
		
	}
	public void setMarketAssessManager(MarketAssessManagerImpl marketAssessManager) {
		MarketAssessManager = marketAssessManager;
	}

}
