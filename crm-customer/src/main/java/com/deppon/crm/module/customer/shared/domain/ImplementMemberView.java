package com.deppon.crm.module.customer.shared.domain;


/**
 * 
 * <p>
 * 实施会员试图<br />
 * </p>
 * @title ImplementMemberView.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author bxj
 * @version 0.2 2012-3-29
 */
public class ImplementMemberView {
	//资格校验结果
	private QualificationView qualificationView;
	//会员信息
	private Member member;
	/**
	 * <p>
	 * Description:qualificationView<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public QualificationView getQualificationView() {
		return qualificationView;
	}
	/**
	 * <p>
	 * Description:qualificationView<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setQualificationView(QualificationView qualificationView) {
		this.qualificationView = qualificationView;
	}
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
}
