package com.deppon.crm.module.customer.shared.domain;

import java.util.List;
/**
 * 
 * <p>
 * Description:实时校验信息<br />
 * </p>
 * @title QualificationView.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author 106138
 * @version 0.1 2014年4月18日
 */
public class QualificationView {
	
	//运单信息
	private List<WayBillInfo> wayBillList;
	//合计金额
	private Double totalMoney ;
	//合格
	private boolean qualified;
	//客户级别
	private String custLevel;
	/**
	 * <p>
	 * Description:wayBillList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<WayBillInfo> getWayBillList() {
		return wayBillList;
	}
	/**
	 * <p>
	 * Description:wayBillList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWayBillList(List<WayBillInfo> wayBillList) {
		this.wayBillList = wayBillList;
	}
	/**
	 * <p>
	 * Description:totalMoney<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getTotalMoney() {
		return totalMoney;
	}
	/**
	 * <p>
	 * Description:totalMoney<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	/**
	 * <p>
	 * Description:qualified<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public boolean isQualified() {
		return qualified;
	}
	/**
	 * <p>
	 * Description:qualified<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setQualified(boolean qualified) {
		this.qualified = qualified;
	}
	/**
	 * <p>
	 * Description:custLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustLevel() {
		return custLevel;
	}
	/**
	 * <p>
	 * Description:custLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
}
