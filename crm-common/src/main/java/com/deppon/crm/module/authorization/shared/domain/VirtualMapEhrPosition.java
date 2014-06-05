package com.deppon.crm.module.authorization.shared.domain;

/**
 * 
 * <p>
 * 虚拟岗位与标准岗位的映射关系<br />
 * </p>
 * @title VirtualMapStandard.java
 * @package com.deppon.crm.module.authorization.shared.domain 
 * @author suyujun
 * @version 0.1 2013-11-27
 */
public class VirtualMapEhrPosition {
	private String id;
	private String vpid;
	private String spid;
	/**
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return vpid : return the property vpid.
	 */
	public String getVpid() {
		return vpid;
	}
	/**
	 * @param vpid : set the property vpid.
	 */
	public void setVpid(String vpid) {
		this.vpid = vpid;
	}
	/**
	 * @return spid : return the property spid.
	 */
	public String getSpid() {
		return spid;
	}
	/**
	 * @param spid : set the property spid.
	 */
	public void setSpid(String spid) {
		this.spid = spid;
	}
	
}
