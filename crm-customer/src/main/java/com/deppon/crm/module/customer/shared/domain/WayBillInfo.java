package com.deppon.crm.module.customer.shared.domain;

public class WayBillInfo {
	//序号
	private String id;
	//运单号
	private String wayBillNumber;
	//金额  = 预付金额 + 到达金额 - 劳务费 - 代收货款
	private double money;
	//系数  出发为1，到达为 2/3
	private double ratio;
	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getId() {
		return id;
	}
	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * <p>
	 * Description:wayBillNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getWayBillNumber() {
		return wayBillNumber;
	}
	/**
	 * <p>
	 * Description:wayBillNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
	}
	/**
	 * <p>
	 * Description:money<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public double getMoney() {
		return money;
	}
	/**
	 * <p>
	 * Description:money<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMoney(double money) {
		this.money = money;
	}
	/**
	 * <p>
	 * Description:ratio<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public double getRatio() {
		return ratio;
	}
	/**
	 * <p>
	 * Description:ratio<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
}
