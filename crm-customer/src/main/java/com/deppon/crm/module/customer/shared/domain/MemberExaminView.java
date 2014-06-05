package com.deppon.crm.module.customer.shared.domain;

import java.util.List;

public class MemberExaminView {
	//审批的会员对象
	private Member member ;
	//审批数据修改对象
	private List<ApproveData> appList ;
	//审批历史记录
	private List<ExamineRecord> examineRecordList ;
	//审批人
	private String currentExaminer;
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
	 * Description:appList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<ApproveData> getAppList() {
		return appList;
	}
	/**
	 * <p>
	 * Description:appList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAppList(List<ApproveData> appList) {
		this.appList = appList;
	}
	/**
	 * <p>
	 * Description:examineRecordList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<ExamineRecord> getExamineRecordList() {
		return examineRecordList;
	}
	/**
	 * <p>
	 * Description:examineRecordList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setExamineRecordList(List<ExamineRecord> examineRecordList) {
		this.examineRecordList = examineRecordList;
	}
	/**
	 * <p>
	 * Description:currentExaminer<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCurrentExaminer() {
		return currentExaminer;
	}
	/**
	 * <p>
	 * Description:currentExaminer<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCurrentExaminer(String currentExaminer) {
		this.currentExaminer = currentExaminer;
	}
}
