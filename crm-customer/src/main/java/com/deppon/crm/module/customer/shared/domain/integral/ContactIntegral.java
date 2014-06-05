package com.deppon.crm.module.customer.shared.domain.integral;


import java.util.Date;

import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>联系人对应积分信息<br />
 * </p>
 * @title ContactIntegral.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author Administrator
 * @version 0.1 2012-4-23
 */
@SuppressWarnings("serial")
public class ContactIntegral extends BaseEntity {
	private Contact contact = new Contact();//联系人
	private int currentUsableScore;//本期可用积分--可以使用的积分
	private int currentToUsesScore;//本期已用积分--指兑换礼品使用积分
	private int currentTotalScore;//本期累计积分--联系人获得的积分
	private int totalScore;//累计积分--联系人获得的积分
	private int totalToUsesScore;//累计已用积分
	private Date lastDateScore;//最后积分时间
	//剩余可用积分=联系人可用积分+可调用积分-兑换清单中已扣积分
	private int tempScore;
	/**
	 * <p>
	 * Description:contact<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Contact getContact() {
		return contact;
	}
	/**
	 * <p>
	 * Description:contact<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	/**
	 * <p>
	 * Description:currentUsableScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public int getCurrentUsableScore() {
		return currentUsableScore;
	}
	/**
	 * <p>
	 * Description:currentUsableScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCurrentUsableScore(int currentUsableScore) {
		this.currentUsableScore = currentUsableScore;
	}
	/**
	 * <p>
	 * Description:currentToUsesScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public int getCurrentToUsesScore() {
		return currentToUsesScore;
	}
	/**
	 * <p>
	 * Description:currentToUsesScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCurrentToUsesScore(int currentToUsesScore) {
		this.currentToUsesScore = currentToUsesScore;
	}
	/**
	 * <p>
	 * Description:currentTotalScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public int getCurrentTotalScore() {
		return currentTotalScore;
	}
	/**
	 * <p>
	 * Description:currentTotalScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCurrentTotalScore(int currentTotalScore) {
		this.currentTotalScore = currentTotalScore;
	}
	/**
	 * <p>
	 * Description:totalScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public int getTotalScore() {
		return totalScore;
	}
	/**
	 * <p>
	 * Description:totalScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	/**
	 * <p>
	 * Description:totalToUsesScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public int getTotalToUsesScore() {
		return totalToUsesScore;
	}
	/**
	 * <p>
	 * Description:totalToUsesScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTotalToUsesScore(int totalToUsesScore) {
		this.totalToUsesScore = totalToUsesScore;
	}
	/**
	 * <p>
	 * Description:lastDateScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getLastDateScore() {
		return lastDateScore;
	}
	/**
	 * <p>
	 * Description:lastDateScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLastDateScore(Date lastDateScore) {
		this.lastDateScore = lastDateScore;
	}
	/**
	 * <p>
	 * Description:tempScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public int getTempScore() {
		return tempScore;
	}
	/**
	 * <p>
	 * Description:tempScore<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTempScore(int tempScore) {
		this.tempScore = tempScore;
	}
}
