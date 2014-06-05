/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title VehicleHistory.java
 * @package com.deppon.crm.module.order.shared.domain 
 * @author Administrator
 * @version 0.1 2012-10-16
 */
package com.deppon.crm.module.order.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * 约车历史实体<br />
 * </p>
 * @title VehicleHistory.java
 * @package com.deppon.crm.module.order.shared.domain 
 * @author 苏玉军
 * @version 0.1 2012-10-16
 */

public class VehicleHistory extends BaseEntity {
	private static final long serialVersionUID = -7478116993027298954L;
	//用车部门名称
	private String useVehicleDeptName;
	//用车部门编码
	private String userVehicleDeptCode;
	//车队名称
	private String vehicleTeamName;
	//车队编码
	private String vehicleTeamCode;
	/**
	 *@return  useVehicleDeptName;
	 */
	public String getUseVehicleDeptName() {
		return useVehicleDeptName;
	}
	/**
	 * @param useVehicleDeptName : set the property useVehicleDeptName.
	 */
	public void setUseVehicleDeptName(String useVehicleDeptName) {
		this.useVehicleDeptName = useVehicleDeptName;
	}
	/**
	 *@return  userVehicleDeptCode;
	 */
	public String getUserVehicleDeptCode() {
		return userVehicleDeptCode;
	}
	/**
	 * @param userVehicleDeptCode : set the property userVehicleDeptCode.
	 */
	public void setUserVehicleDeptCode(String userVehicleDeptCode) {
		this.userVehicleDeptCode = userVehicleDeptCode;
	}
	/**
	 *@return  vehicleTeamName;
	 */
	public String getVehicleTeamName() {
		return vehicleTeamName;
	}
	/**
	 * @param vehicleTeamName : set the property vehicleTeamName.
	 */
	public void setVehicleTeamName(String vehicleTeamName) {
		this.vehicleTeamName = vehicleTeamName;
	}
	/**
	 *@return  vehicleTeamCode;
	 */
	public String getVehicleTeamCode() {
		return vehicleTeamCode;
	}
	/**
	 * @param vehicleTeamCode : set the property vehicleTeamCode.
	 */
	public void setVehicleTeamCode(String vehicleTeamCode) {
		this.vehicleTeamCode = vehicleTeamCode;
	}
	
}
