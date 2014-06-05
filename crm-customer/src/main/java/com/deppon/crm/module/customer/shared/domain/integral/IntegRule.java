package com.deppon.crm.module.customer.shared.domain.integral;


import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class IntegRule extends BaseEntity{
	
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;
	//积分率
	private Double fraction;
	//积分描述
	private String pointdesc;
	//积分编号 
	private String number;
	//积分生效日期
	private Date pointbegintime;
	//积分结束日期
	private Date pointendtime;
	/**
	 * <p>
	 * Description:fraction<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getFraction() {
		return fraction;
	}
	/**
	 * <p>
	 * Description:fraction<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFraction(Double fraction) {
		this.fraction = fraction;
	}
	/**
	 * <p>
	 * Description:pointdesc<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getPointdesc() {
		return pointdesc;
	}
	/**
	 * <p>
	 * Description:pointdesc<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPointdesc(String pointdesc) {
		this.pointdesc = pointdesc;
	}
	/**
	 * <p>
	 * Description:number<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * <p>
	 * Description:number<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * <p>
	 * Description:pointbegintime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getPointbegintime() {
		return pointbegintime;
	}
	/**
	 * <p>
	 * Description:pointbegintime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPointbegintime(Date pointbegintime) {
		this.pointbegintime = pointbegintime;
	}
	/**
	 * <p>
	 * Description:pointendtime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getPointendtime() {
		return pointendtime;
	}
	/**
	 * <p>
	 * Description:pointendtime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPointendtime(Date pointendtime) {
		this.pointendtime = pointendtime;
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
