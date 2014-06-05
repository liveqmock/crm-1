package com.deppon.crm.module.client.sync.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**   
 * @Description:合同发票标记子公司<br />
 * @title TCustContractTax.java
 * @package com.deppon.crm.module.client.sync.domain.dto 
 * @author CoCo
 * @version 0.1 2013-11-15
 */
public class TCustContractTax implements Serializable{

	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 1953697124260662025L;
	
	//主键ID
    private BigDecimal fid;
    //创建时间
    private Date fcreatetime;
    //创建人
    private BigDecimal fcreateuserid;
    //创建时间
    private Date flastupdatetime;
    //修改人
    private BigDecimal flastmodifyuserid;
    //合同ID
    private BigDecimal fcontractid;
    //开始时间
    private Date fbegintime;
    ///结束时间
    private Date fendtime;
    //发票标记
    private String finvoiceType;
    //签署子公司
    private String signCompany;
	/**
	 * Description:fid<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public BigDecimal getFid() {
		return fid;
	}
	/**
	 * Description:fid<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public void setFid(BigDecimal fid) {
		this.fid = fid;
	}
	/**
	 * Description:fcreatetime<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public Date getFcreatetime() {
		return fcreatetime;
	}
	/**
	 * Description:fcreatetime<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public void setFcreatetime(Date fcreatetime) {
		this.fcreatetime = fcreatetime;
	}
	/**
	 * Description:fcreateuserid<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public BigDecimal getFcreateuserid() {
		return fcreateuserid;
	}
	/**
	 * Description:fcreateuserid<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public void setFcreateuserid(BigDecimal fcreateuserid) {
		this.fcreateuserid = fcreateuserid;
	}
	/**
	 * Description:flastupdatetime<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public Date getFlastupdatetime() {
		return flastupdatetime;
	}
	/**
	 * Description:flastupdatetime<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public void setFlastupdatetime(Date flastupdatetime) {
		this.flastupdatetime = flastupdatetime;
	}
	/**
	 * Description:flastmodifyuserid<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public BigDecimal getFlastmodifyuserid() {
		return flastmodifyuserid;
	}
	/**
	 * Description:flastmodifyuserid<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public void setFlastmodifyuserid(BigDecimal flastmodifyuserid) {
		this.flastmodifyuserid = flastmodifyuserid;
	}
	/**
	 * Description:fcontractid<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public BigDecimal getFcontractid() {
		return fcontractid;
	}
	/**
	 * Description:fcontractid<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public void setFcontractid(BigDecimal fcontractid) {
		this.fcontractid = fcontractid;
	}
	/**
	 * Description:fbegintime<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public Date getFbegintime() {
		return fbegintime;
	}
	/**
	 * Description:fbegintime<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public void setFbegintime(Date fbegintime) {
		this.fbegintime = fbegintime;
	}
	/**
	 * Description:fendtime<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public Date getFendtime() {
		return fendtime;
	}
	/**
	 * Description:fendtime<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public void setFendtime(Date fendtime) {
		this.fendtime = fendtime;
	}
	/**
	 * Description:finvoiceType<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public String getFinvoiceType() {
		return finvoiceType;
	}
	/**
	 * Description:finvoiceType<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public void setFinvoiceType(String finvoiceType) {
		this.finvoiceType = finvoiceType;
	}
	/**
	 * Description:signCompany<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public String getSignCompany() {
		return signCompany;
	}
	/**
	 * Description:signCompany<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public void setSignCompany(String signCompany) {
		this.signCompany = signCompany;
	}
    
}
