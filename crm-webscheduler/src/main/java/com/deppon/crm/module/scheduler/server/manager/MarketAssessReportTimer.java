package com.deppon.crm.module.scheduler.server.manager;

import java.util.Date;

import com.deppon.crm.module.marketing.server.manager.IMarketAssessJobManager;

/**
 * <p>
 * Description:营销效果评估报表-定时器<br />
 * </p>
 * 
 * @package com.deppon.crm.module.scheduler.server.manager
 * @author 钟琼
 * @version 0.1 2013-01-25
 */
public class MarketAssessReportTimer {
	
	private IMarketAssessJobManager marketAssessJobManager;

	public void autoCreateMarketReport(){
		System.out.println("marketAssessJobManager---->START"+new Date());
		marketAssessJobManager.autoCreateMarketReport();
		System.out.println("marketAssessJobManager---->END"+new Date());
	}
	
	public void setMarketAssessJobManager(
			IMarketAssessJobManager marketAssessJobManager) {
		this.marketAssessJobManager = marketAssessJobManager;
	}
}
