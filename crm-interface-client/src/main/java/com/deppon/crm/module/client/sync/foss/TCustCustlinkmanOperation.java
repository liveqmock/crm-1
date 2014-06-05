package com.deppon.crm.module.client.sync.foss;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;

public class TCustCustlinkmanOperation implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -2830736431186006242L;

	public OperationFlg getOperationFlg() {
		return operationFlg;
	}

	public void setOperationFlg(OperationFlg operationFlg) {
		this.operationFlg = operationFlg;
	}

	private OperationFlg operationFlg;

	private BigDecimal fid;

	private BigDecimal facceptdeptid;

	private String falid;

	private Timestamp fborndate;

	private BigDecimal fcid;

	private Timestamp fcreatetime;

	private BigDecimal fcreateuserid;

	private BigDecimal fcustid;

	private String fdecisionright;

	private BigDecimal fdefaultid;

	private String fduty;

	private String fdutydept;

	private String femail;

	private Timestamp ferplinkmancreatetime;

	private String ferplmfid;

	private String ffolk;

	private String fgainave;

	private String fidcard;

	private String fismainlinkman;

	private Timestamp flastupdatetime;

	private BigDecimal flastupdateuserid;

	private String flinkmanaddress;

	private String flinkmantype;

	private String flovegift;

	private String fmobiletel;

	private String fmsn;

	private String fname;

	private String fnativeplace;

	private String fnumber;

	private String foffertel;

	private String fonlinebusinessid;

	private String fpersonlove;

	private String fqqnumber;

	private String fsex;

	private String fstatus;

	private String ftaobid;

	private String fversionid;

	private String fww;

	private String fyoushangid;
	//传真
	private String fax;

	public BigDecimal getFid() {
		return this.fid;
	}

	public void setFid(BigDecimal fid) {
		this.fid = fid;
	}

	public BigDecimal getFacceptdeptid() {
		return this.facceptdeptid;
	}

	public void setFacceptdeptid(BigDecimal facceptdeptid) {
		this.facceptdeptid = facceptdeptid;
	}

	public String getFalid() {
		return this.falid;
	}

	public void setFalid(String falid) {
		this.falid = falid;
	}

	public Timestamp getFborndate() {
		return this.fborndate;
	}

	public void setFborndate(Timestamp fborndate) {
		this.fborndate = fborndate;
	}

	public BigDecimal getFcid() {
		return this.fcid;
	}

	public void setFcid(BigDecimal fcid) {
		this.fcid = fcid;
	}

	public Timestamp getFcreatetime() {
		return this.fcreatetime;
	}

	public void setFcreatetime(Timestamp fcreatetime) {
		this.fcreatetime = fcreatetime;
	}

	public BigDecimal getFcreateuserid() {
		return this.fcreateuserid;
	}

	public void setFcreateuserid(BigDecimal fcreateuserid) {
		this.fcreateuserid = fcreateuserid;
	}

	public BigDecimal getFcustid() {
		return this.fcustid;
	}

	public void setFcustid(BigDecimal fcustid) {
		this.fcustid = fcustid;
	}

	public String getFdecisionright() {
		return this.fdecisionright;
	}

	public void setFdecisionright(String fdecisionright) {
		this.fdecisionright = fdecisionright;
	}

	public BigDecimal getFdefaultid() {
		return this.fdefaultid;
	}

	public void setFdefaultid(BigDecimal fdefaultid) {
		this.fdefaultid = fdefaultid;
	}

	public String getFduty() {
		return this.fduty;
	}

	public void setFduty(String fduty) {
		this.fduty = fduty;
	}

	public String getFdutydept() {
		return this.fdutydept;
	}

	public void setFdutydept(String fdutydept) {
		this.fdutydept = fdutydept;
	}

	public String getFemail() {
		return this.femail;
	}

	public void setFemail(String femail) {
		this.femail = femail;
	}

	public Timestamp getFerplinkmancreatetime() {
		return this.ferplinkmancreatetime;
	}

	public void setFerplinkmancreatetime(Timestamp ferplinkmancreatetime) {
		this.ferplinkmancreatetime = ferplinkmancreatetime;
	}

	public String getFerplmfid() {
		return this.ferplmfid;
	}

	public void setFerplmfid(String ferplmfid) {
		this.ferplmfid = ferplmfid;
	}

	public String getFfolk() {
		return this.ffolk;
	}

	public void setFfolk(String ffolk) {
		this.ffolk = ffolk;
	}

	public String getFgainave() {
		return this.fgainave;
	}

	public void setFgainave(String fgainave) {
		this.fgainave = fgainave;
	}

	public String getFidcard() {
		return this.fidcard;
	}

	public void setFidcard(String fidcard) {
		this.fidcard = fidcard;
	}

	public String getFismainlinkman() {
		return this.fismainlinkman;
	}

	public void setFismainlinkman(String fismainlinkman) {
		this.fismainlinkman = fismainlinkman;
	}

	public Timestamp getFlastupdatetime() {
		return this.flastupdatetime;
	}

	public void setFlastupdatetime(Timestamp flastupdatetime) {
		this.flastupdatetime = flastupdatetime;
	}

	public BigDecimal getFlastupdateuserid() {
		return this.flastupdateuserid;
	}

	public void setFlastupdateuserid(BigDecimal flastupdateuserid) {
		this.flastupdateuserid = flastupdateuserid;
	}

	public String getFlinkmanaddress() {
		return this.flinkmanaddress;
	}

	public void setFlinkmanaddress(String flinkmanaddress) {
		this.flinkmanaddress = flinkmanaddress;
	}

	public String getFlinkmantype() {
		return this.flinkmantype;
	}

	public void setFlinkmantype(String flinkmantype) {
		this.flinkmantype = flinkmantype;
	}

	public String getFlovegift() {
		return this.flovegift;
	}

	public void setFlovegift(String flovegift) {
		this.flovegift = flovegift;
	}

	public String getFmobiletel() {
		return this.fmobiletel;
	}

	public void setFmobiletel(String fmobiletel) {
		this.fmobiletel = fmobiletel;
	}

	public String getFmsn() {
		return this.fmsn;
	}

	public void setFmsn(String fmsn) {
		this.fmsn = fmsn;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFnativeplace() {
		return this.fnativeplace;
	}

	public void setFnativeplace(String fnativeplace) {
		this.fnativeplace = fnativeplace;
	}

	public String getFnumber() {
		return this.fnumber;
	}

	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}

	public String getFoffertel() {
		return this.foffertel;
	}

	public void setFoffertel(String foffertel) {
		this.foffertel = foffertel;
	}

	public String getFonlinebusinessid() {
		return this.fonlinebusinessid;
	}

	public void setFonlinebusinessid(String fonlinebusinessid) {
		this.fonlinebusinessid = fonlinebusinessid;
	}

	public String getFpersonlove() {
		return this.fpersonlove;
	}

	public void setFpersonlove(String fpersonlove) {
		this.fpersonlove = fpersonlove;
	}

	public String getFqqnumber() {
		return this.fqqnumber;
	}

	public void setFqqnumber(String fqqnumber) {
		this.fqqnumber = fqqnumber;
	}

	public String getFsex() {
		return this.fsex;
	}

	public void setFsex(String fsex) {
		this.fsex = fsex;
	}

	public String getFstatus() {
		return this.fstatus;
	}

	public void setFstatus(String fstatus) {
		this.fstatus = fstatus;
	}

	public String getFtaobid() {
		return this.ftaobid;
	}

	public void setFtaobid(String ftaobid) {
		this.ftaobid = ftaobid;
	}

	public String getFversionid() {
		return this.fversionid;
	}

	public void setFversionid(String fversionid) {
		this.fversionid = fversionid;
	}

	public String getFww() {
		return this.fww;
	}

	public void setFww(String fww) {
		this.fww = fww;
	}

	public String getFyoushangid() {
		return this.fyoushangid;
	}

	public void setFyoushangid(String fyoushangid) {
		this.fyoushangid = fyoushangid;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}



}
