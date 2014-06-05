package com.deppon.crm.module.customer.shared.domain.integral;

import java.util.Date;

import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>会员积分<br />
 * </p>
 * @title MemberIntegral.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author Administrator
 * @version 0.1 2012-4-23
 */
public class MemberIntegral extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private Member member = new Member();//会员
	private int currentUsableScore;//本期可用积分
	private int currentToUsesScore;//本期已用积分
	private int currentTotalScore;//本期累计积分
	private int totalScore;//累计积分
	private int totalToUsesScore;//累计已用积分
	private Date lastDateScore;//最后积分时间
	/**
	 * <p>
	 * Description:member<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Member getMember() {
		return member;
	}
	/**
	 * <p>
	 * Description:member<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMember(Member member) {
		this.member = member;
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
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
