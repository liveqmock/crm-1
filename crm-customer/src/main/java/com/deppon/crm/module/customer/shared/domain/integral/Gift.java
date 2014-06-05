package com.deppon.crm.module.customer.shared.domain.integral;

import java.util.Date;

import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * 礼品<br />
 * </p>
 * @title GiftConvertRule.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author bxj
 * @version 0.2 2012-4-21
 */
public class Gift extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//礼品名称
	private String giftName;
	//所需积分
	private Integer needPoints;
	//库存数量
	private Integer inventNumber;
	//礼品价值
	private Double giftValue;
	//实际价值
	private Double realValue;
	//礼品描述
	private String giftDesc;
	//礼品编码
	private String giftNumber;	
	//兑换起始时间
	private Date beginTime;
	//兑换截止时间
	private Date endTime;
	//兑换区域
	private Department department;
	//是否启动兑换
	private Boolean isStart;
	//创建人
	private String cname;
	//修改人
	private String mname;
	/**
	 * <p>
	 * Description:giftName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getGiftName() {
		return giftName;
	}
	/**
	 * <p>
	 * Description:giftName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	/**
	 * <p>
	 * Description:needPoints<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getNeedPoints() {
		return needPoints;
	}
	/**
	 * <p>
	 * Description:needPoints<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setNeedPoints(Integer needPoints) {
		this.needPoints = needPoints;
	}
	/**
	 * <p>
	 * Description:inventNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getInventNumber() {
		return inventNumber;
	}
	/**
	 * <p>
	 * Description:inventNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setInventNumber(Integer inventNumber) {
		this.inventNumber = inventNumber;
	}
	/**
	 * <p>
	 * Description:giftValue<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getGiftValue() {
		return giftValue;
	}
	/**
	 * <p>
	 * Description:giftValue<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setGiftValue(Double giftValue) {
		this.giftValue = giftValue;
	}
	/**
	 * <p>
	 * Description:realValue<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getRealValue() {
		return realValue;
	}
	/**
	 * <p>
	 * Description:realValue<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRealValue(Double realValue) {
		this.realValue = realValue;
	}
	/**
	 * <p>
	 * Description:giftDesc<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getGiftDesc() {
		return giftDesc;
	}
	/**
	 * <p>
	 * Description:giftDesc<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setGiftDesc(String giftDesc) {
		this.giftDesc = giftDesc;
	}
	/**
	 * <p>
	 * Description:giftNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getGiftNumber() {
		return giftNumber;
	}
	/**
	 * <p>
	 * Description:giftNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setGiftNumber(String giftNumber) {
		this.giftNumber = giftNumber;
	}
	/**
	 * <p>
	 * Description:beginTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getBeginTime() {
		return beginTime;
	}
	/**
	 * <p>
	 * Description:beginTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * <p>
	 * Description:endTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * <p>
	 * Description:endTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * <p>
	 * Description:department<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * <p>
	 * Description:department<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * <p>
	 * Description:isStart<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsStart() {
		return isStart;
	}
	/**
	 * <p>
	 * Description:isStart<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsStart(Boolean isStart) {
		this.isStart = isStart;
	}
	/**
	 * <p>
	 * Description:cname<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * <p>
	 * Description:cname<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * <p>
	 * Description:mname<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMname() {
		return mname;
	}
	/**
	 * <p>
	 * Description:mname<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMname(String mname) {
		this.mname = mname;
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
