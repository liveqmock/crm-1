package com.deppon.crm.module.scheduler.server.manager;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.sync.impl.CustomerInfoLog;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.MemberDemotionList;
import com.deppon.crm.module.customer.shared.domain.MemberUpgradeList;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

public class TimerMemberCustomer {
	
	private IMemberManager memberManager;
	
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	/**
	 * 
	 * <p>
	 * 会员升级定时器，每个月执行一次，每月最后1天执行<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-30
	 * void
	 */
	public void timerMemberUpgrade(){
		timerMemberUpgrade(new Date());
	}
	/**
	 * 
	 * <p>
	 * 会员升级定时器，每个月执行一次，每月最后1天执行<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-6
	 * void
	 */
	public void timerMemberUpgrade(Date date){
		UserUtil.setUserToAdmin();
		String statisticsTime = DateUtil.getMoth(date);
		List<MemberUpgradeList> upgradeList = memberManager.getMemberUpgrage(statisticsTime);
		CustomerInfoLog.init();
		memberManager.upgrageMember(upgradeList);
		CustomerInfoLog.commit();
	}
	/**
	 * 
	 * <p>
	 * 会员降级定时器，每年执行一次，每年<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-30
	 * void
	 */
	public void timerMemberDemotion(){
		timerMemberDemotion(new Date());
	}
	/**
	 * 
	 * <p>
	 * 会员降级定时器，每年执行一次，每年<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-6
	 * void
	 */
	public void timerMemberDemotion(Date date){
		UserUtil.setUserToAdmin();
		String statisticsTime = DateUtil.getYear(date);
		List<MemberDemotionList> demotionList = memberManager.getDemotionMemberByStatisticsTime(statisticsTime);
		CustomerInfoLog.init();
		memberManager.demotionMember(demotionList);
		CustomerInfoLog.commit();
	}
	
 }
