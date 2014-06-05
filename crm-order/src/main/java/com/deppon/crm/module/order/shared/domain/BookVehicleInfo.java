/**   
 * @title VehicleInfo.java
 * @package com.deppon.crm.module.order.shared.domain
 * @description 约车信息  
 * @author 潘光均
 * @update 2012-3-16 下午3:26:41
 * @version V1.0   
 */
package com.deppon.crm.module.order.shared.domain;


/**
 * @description 约车信息  
 * @author 潘光均
 * @version 0.1 2012-3-16
 *@date 2012-3-16
 */

public class BookVehicleInfo {
	//车队
	private String vehicleTeam;
	//用车部门编码
	private String useVehicleDept;// 编码
	//车辆类型
	private String vehicleType;
	//是否..
	private Boolean isTailBoard;
	//约车号
	private String bookVehicelNumber;
	//ERP Id
	private String erpId;
	/**
	 *@return  vehicleTeam;
	 */
	public String getVehicleTeam() {
		return vehicleTeam;
	}
	/**
	 * @param vehicleTeam : set the property vehicleTeam.
	 */
	public void setVehicleTeam(String vehicleTeam) {
		this.vehicleTeam = vehicleTeam;
	}
	/**
	 *@return  useVehicleDept;
	 */
	public String getUseVehicleDept() {
		return useVehicleDept;
	}
	/**
	 * @param useVehicleDept : set the property useVehicleDept.
	 */
	public void setUseVehicleDept(String useVehicleDept) {
		this.useVehicleDept = useVehicleDept;
	}
	/**
	 *@return  vehicleType;
	 */
	public String getVehicleType() {
		return vehicleType;
	}
	/**
	 * @param vehicleType : set the property vehicleType.
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	/**
	 *@return  isTailBoard;
	 */
	public Boolean getIsTailBoard() {
		return isTailBoard;
	}
	/**
	 * @param isTailBoard : set the property isTailBoard.
	 */
	public void setIsTailBoard(Boolean isTailBoard) {
		this.isTailBoard = isTailBoard;
	}
	/**
	 *@return  bookVehicelNumber;
	 */
	public String getBookVehicelNumber() {
		return bookVehicelNumber;
	}
	/**
	 * @param bookVehicelNumber : set the property bookVehicelNumber.
	 */
	public void setBookVehicelNumber(String bookVehicelNumber) {
		this.bookVehicelNumber = bookVehicelNumber;
	}
	/**
	 *@return  erpId;
	 */
	public String getErpId() {
		return erpId;
	}
	/**
	 * @param erpId : set the property erpId.
	 */
	public void setErpId(String erpId) {
		this.erpId = erpId;
	}
	
}
