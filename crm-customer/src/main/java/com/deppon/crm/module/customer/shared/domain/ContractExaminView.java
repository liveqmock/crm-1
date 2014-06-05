package com.deppon.crm.module.customer.shared.domain;

import java.util.List;

public class ContractExaminView {
	//合同显示试图
	private ContractView contractView;
	//审批历史记录
	private List<ExamineRecord> examineRecordList ;
	//审批人
	private String currentExaminer;
	/**
	 * <p>
	 * Description:contractView<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public ContractView getContractView() {
		return contractView;
	}
	/**
	 * <p>
	 * Description:contractView<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractView(ContractView contractView) {
		this.contractView = contractView;
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
