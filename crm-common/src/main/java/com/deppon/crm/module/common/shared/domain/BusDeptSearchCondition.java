/**   
 * @title BusDeptSearchView.java
 * @package com.deppon.crm.module.common.shared.domain
 * @description 营业部门查询视图 
 * @author 潘光均
 * @update 2012-3-24 下午7:41:04
 * @version V1.0   
 */
package com.deppon.crm.module.common.shared.domain;


/**
 * @description 营业部门查询视图  
 * @author 潘光均
 * @version 0.1 2012-3-24
 *@date 2012-3-24
 */

public class BusDeptSearchCondition {
	//始发车队
	private Boolean ifVehicleTem;
	//是否到达
	private Boolean ifArrive;
	//是否始发
	private Boolean ifLeave;
	// 是否外发
	private Boolean ifOutward;
	//部门名称
	private String deptName;
	//总页数
	private long totalCount;
	//开始条数
	private int start;
	//查询条数
	private int limit;
	
	public Boolean getIfVehicleTem() {
		return ifVehicleTem;
	}
	public void setIfVehicleTem(Boolean ifVehicleTem) {
		this.ifVehicleTem = ifVehicleTem;
	}
	public Boolean getIfArrive() {
		return ifArrive;
	}
	public void setIfArrive(Boolean ifArrive) {
		this.ifArrive = ifArrive;
	}
	public Boolean getIfLeave() {
		return ifLeave;
	}
	public void setIfLeave(Boolean ifLeave) {
		this.ifLeave = ifLeave;
	}

	public Boolean getIfOutward() {
		return ifOutward;
	}

	public void setIfOutward(Boolean ifOutward) {
		this.ifOutward = ifOutward;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
