package com.deppon.crm.module.common.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class HardWareInfo extends BaseEntity {

	
	private String  HostName;
	private String  CPUNumber;
	private String MACAddress;
	public String getHostName() {
		return HostName;
	}
	public void setHostName(String hostName) {
		HostName = hostName;
	}
	public String getCPUNumber() {
		return CPUNumber;
	}
	public void setCPUNumber(String cPUNumber) {
		CPUNumber = cPUNumber;
	}
	public String getMACAddress() {
		return MACAddress;
	}
	public void setMACAddress(String mACAddress) {
		MACAddress = mACAddress;
	}
	
	private boolean falg;
	
	public boolean isFalg() {
		return falg;
	}
	public void setFalg(boolean falg) {
		this.falg = falg;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.HostName);
		sb.append(this.CPUNumber);
		sb.append(this.MACAddress);
		sb.append(this.falg);
		return sb.toString();
	}
	
}
