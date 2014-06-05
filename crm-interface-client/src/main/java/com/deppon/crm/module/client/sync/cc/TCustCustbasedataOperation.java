package com.deppon.crm.module.client.sync.cc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;

public class TCustCustbasedataOperation implements Serializable{

	private static final long serialVersionUID = -2679523421231629538L;

	private BigDecimal fid;

	private String faddrtype;

	private BigDecimal fareaid;

	private Timestamp fbecomememtime;

	private String fbilltitle;

	private String fbrandworth;

	private String fbusstype;

	private String fchannel;

	private String fchannelsource;

	private String fcity;

	private BigDecimal fcityid;

	private String fcompaddr;

	private String fcompanyproperty;

	private String fcompanyscop;

	private BigDecimal fcontactid;

	private Timestamp fcreatetime;

	private BigDecimal fcreateuserid;

	private String fcreditrate;

	private String fcustname;

	private String fcustnature;

	private Object fcustnumber;

	private String fcustpotentialtype;

	private String fcuststatus;

	private String fcusttype;

	private String fdegree;

	private BigDecimal fdeptid;

	private String ffocusdeptid;

	private String ffocusdeptname;

	private BigDecimal fisacceptmarket;

	private String fisfocuspay;

	private String fisimportcustor;

	private String fisparentcompany;

	private String fisredeempoints;

	private String fisspecial;

	private String fistrangoods;

	private Timestamp flastchangtime;

	private BigDecimal flastmodifyuserid;

	private Timestamp flastupdatetime;

	private BigDecimal flastyearincome;

	private BigDecimal flastyearprofit;

	private String fnextlevel;

	private BigDecimal fparentcompanyid;

	private String fparentnumber;

	private String fpreferenceservice;

	private BigDecimal fprocredit;

	private String fprovince;

	private BigDecimal fprovinceid;

	private String frecommendcust;

	private String fregistaddress;

	private BigDecimal fregisterfund;

	private String fremark;

	private BigDecimal fresponsibillity;

	private String fsimplename;

	private String ftaxregnumber;

	private String ftradeid;

	private String fupgradesource;

	private BigDecimal fversionnumber;
	/*******************CRM二期cc新增字段****************************/
	//二级行业
	private String secondTrade;
	//是否大客户
    private boolean ifBigCustomer;
    /*
    PC_CUSTOMER	潜客
    SC_CUSTOMER	散客
    RC_CUSTOMER	固定客户
    */
    //客户类型
    private String custGroup;
    //外系统id
    private String fossId;
    //客户类别
    private String fcustcategory;
    //客户营销权限部门
    private String marketingDept;
    //客户营销权限分配时间
    private Date marketingAllotTime;
    //客户营销权限创建时间
    private Date marketingCreateTime;
    //客户营销权限创建人
    private String marketingCreateUser;
    /*******************CRM二期cc新增字段****************************/

	/**
	 * 财务作废
	 */
	private boolean financeCancel;

	public boolean isFinanceCancel() {
		return financeCancel;
	}

	public void setFinanceCancel(boolean financeCancel) {
		this.financeCancel = financeCancel;
	}

	// 会员所属部门的标杆编码
	private String fdeptidStandardcode;

    public String getFdeptidStandardcode() {
		return fdeptidStandardcode;
	}

	public void setFdeptidStandardcode(String fdeptidStandardcode) {
		this.fdeptidStandardcode = fdeptidStandardcode;
	}



	public BigDecimal getFid() {
		return this.fid;
	}

	public void setFid(BigDecimal fid) {
		this.fid = fid;
	}

	public String getFaddrtype() {
		return this.faddrtype;
	}

	public void setFaddrtype(String faddrtype) {
		this.faddrtype = faddrtype;
	}

	public BigDecimal getFareaid() {
		return this.fareaid;
	}

	public void setFareaid(BigDecimal fareaid) {
		this.fareaid = fareaid;
	}

	public Timestamp getFbecomememtime() {
		return this.fbecomememtime;
	}

	public void setFbecomememtime(Timestamp fbecomememtime) {
		this.fbecomememtime = fbecomememtime;
	}

	public String getFbilltitle() {
		return this.fbilltitle;
	}

	public void setFbilltitle(String fbilltitle) {
		this.fbilltitle = fbilltitle;
	}

	public String getFbrandworth() {
		return this.fbrandworth;
	}

	public void setFbrandworth(String fbrandworth) {
		this.fbrandworth = fbrandworth;
	}

	public String getFbusstype() {
		return this.fbusstype;
	}

	public void setFbusstype(String fbusstype) {
		this.fbusstype = fbusstype;
	}

	public String getFchannel() {
		return this.fchannel;
	}

	public void setFchannel(String fchannel) {
		this.fchannel = fchannel;
	}

	public String getFchannelsource() {
		return this.fchannelsource;
	}

	public void setFchannelsource(String fchannelsource) {
		this.fchannelsource = fchannelsource;
	}

	public String getFcity() {
		return this.fcity;
	}

	public void setFcity(String fcity) {
		this.fcity = fcity;
	}

	public BigDecimal getFcityid() {
		return this.fcityid;
	}

	public void setFcityid(BigDecimal fcityid) {
		this.fcityid = fcityid;
	}

	public String getFcompaddr() {
		return this.fcompaddr;
	}

	public void setFcompaddr(String fcompaddr) {
		this.fcompaddr = fcompaddr;
	}

	public String getFcompanyproperty() {
		return this.fcompanyproperty;
	}

	public void setFcompanyproperty(String fcompanyproperty) {
		this.fcompanyproperty = fcompanyproperty;
	}

	public String getFcompanyscop() {
		return this.fcompanyscop;
	}

	public void setFcompanyscop(String fcompanyscop) {
		this.fcompanyscop = fcompanyscop;
	}

	public BigDecimal getFcontactid() {
		return this.fcontactid;
	}

	public void setFcontactid(BigDecimal fcontactid) {
		this.fcontactid = fcontactid;
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

	public String getFcreditrate() {
		return this.fcreditrate;
	}

	public void setFcreditrate(String fcreditrate) {
		this.fcreditrate = fcreditrate;
	}

	public String getFcustname() {
		return this.fcustname;
	}

	public void setFcustname(String fcustname) {
		this.fcustname = fcustname;
	}

	public String getFcustnature() {
		return this.fcustnature;
	}

	public void setFcustnature(String fcustnature) {
		this.fcustnature = fcustnature;
	}

	public Object getFcustnumber() {
		return this.fcustnumber;
	}

	public void setFcustnumber(Object fcustnumber) {
		this.fcustnumber = fcustnumber;
	}

	public String getFcustpotentialtype() {
		return this.fcustpotentialtype;
	}

	public void setFcustpotentialtype(String fcustpotentialtype) {
		this.fcustpotentialtype = fcustpotentialtype;
	}

	public String getFcuststatus() {
		return this.fcuststatus;
	}

	public void setFcuststatus(String fcuststatus) {
		this.fcuststatus = fcuststatus;
	}

	public String getFcusttype() {
		return this.fcusttype;
	}

	public void setFcusttype(String fcusttype) {
		this.fcusttype = fcusttype;
	}

	public String getFdegree() {
		return this.fdegree;
	}

	public void setFdegree(String fdegree) {
		this.fdegree = fdegree;
	}

	public BigDecimal getFdeptid() {
		return this.fdeptid;
	}

	public void setFdeptid(BigDecimal fdeptid) {
		this.fdeptid = fdeptid;
	}

	public String getFfocusdeptid() {
		return this.ffocusdeptid;
	}

	public void setFfocusdeptid(String ffocusdeptid) {
		this.ffocusdeptid = ffocusdeptid;
	}

	public String getFfocusdeptname() {
		return this.ffocusdeptname;
	}

	public void setFfocusdeptname(String ffocusdeptname) {
		this.ffocusdeptname = ffocusdeptname;
	}

	public BigDecimal getFisacceptmarket() {
		return this.fisacceptmarket;
	}

	public void setFisacceptmarket(BigDecimal fisacceptmarket) {
		this.fisacceptmarket = fisacceptmarket;
	}

	public String getFisfocuspay() {
		return this.fisfocuspay;
	}

	public void setFisfocuspay(String fisfocuspay) {
		this.fisfocuspay = fisfocuspay;
	}

	public String getFisimportcustor() {
		return this.fisimportcustor;
	}

	public void setFisimportcustor(String fisimportcustor) {
		this.fisimportcustor = fisimportcustor;
	}

	public String getFisparentcompany() {
		return this.fisparentcompany;
	}

	public void setFisparentcompany(String fisparentcompany) {
		this.fisparentcompany = fisparentcompany;
	}

	public String getFisredeempoints() {
		return this.fisredeempoints;
	}

	public void setFisredeempoints(String fisredeempoints) {
		this.fisredeempoints = fisredeempoints;
	}

	public String getFisspecial() {
		return this.fisspecial;
	}

	public void setFisspecial(String fisspecial) {
		this.fisspecial = fisspecial;
	}

	public String getFistrangoods() {
		return this.fistrangoods;
	}

	public void setFistrangoods(String fistrangoods) {
		this.fistrangoods = fistrangoods;
	}

	public Timestamp getFlastchangtime() {
		return this.flastchangtime;
	}

	public void setFlastchangtime(Timestamp flastchangtime) {
		this.flastchangtime = flastchangtime;
	}

	public BigDecimal getFlastmodifyuserid() {
		return this.flastmodifyuserid;
	}

	public void setFlastmodifyuserid(BigDecimal flastmodifyuserid) {
		this.flastmodifyuserid = flastmodifyuserid;
	}

	public Timestamp getFlastupdatetime() {
		return this.flastupdatetime;
	}

	public void setFlastupdatetime(Timestamp flastupdatetime) {
		this.flastupdatetime = flastupdatetime;
	}

	public BigDecimal getFlastyearincome() {
		return this.flastyearincome;
	}

	public void setFlastyearincome(BigDecimal flastyearincome) {
		this.flastyearincome = flastyearincome;
	}

	public BigDecimal getFlastyearprofit() {
		return this.flastyearprofit;
	}

	public void setFlastyearprofit(BigDecimal flastyearprofit) {
		this.flastyearprofit = flastyearprofit;
	}

	public String getFnextlevel() {
		return this.fnextlevel;
	}

	public void setFnextlevel(String fnextlevel) {
		this.fnextlevel = fnextlevel;
	}

	public BigDecimal getFparentcompanyid() {
		return this.fparentcompanyid;
	}

	public void setFparentcompanyid(BigDecimal fparentcompanyid) {
		this.fparentcompanyid = fparentcompanyid;
	}

	public String getFparentnumber() {
		return this.fparentnumber;
	}

	public void setFparentnumber(String fparentnumber) {
		this.fparentnumber = fparentnumber;
	}

	public String getFpreferenceservice() {
		return this.fpreferenceservice;
	}

	public void setFpreferenceservice(String fpreferenceservice) {
		this.fpreferenceservice = fpreferenceservice;
	}

	public BigDecimal getFprocredit() {
		return this.fprocredit;
	}

	public void setFprocredit(BigDecimal fprocredit) {
		this.fprocredit = fprocredit;
	}

	public String getFprovince() {
		return this.fprovince;
	}

	public void setFprovince(String fprovince) {
		this.fprovince = fprovince;
	}

	public BigDecimal getFprovinceid() {
		return this.fprovinceid;
	}

	public void setFprovinceid(BigDecimal fprovinceid) {
		this.fprovinceid = fprovinceid;
	}

	public String getFrecommendcust() {
		return this.frecommendcust;
	}

	public void setFrecommendcust(String frecommendcust) {
		this.frecommendcust = frecommendcust;
	}

	public String getFregistaddress() {
		return this.fregistaddress;
	}

	public void setFregistaddress(String fregistaddress) {
		this.fregistaddress = fregistaddress;
	}

	public BigDecimal getFregisterfund() {
		return this.fregisterfund;
	}

	public void setFregisterfund(BigDecimal fregisterfund) {
		this.fregisterfund = fregisterfund;
	}

	public String getFremark() {
		return this.fremark;
	}

	public void setFremark(String fremark) {
		this.fremark = fremark;
	}

	public BigDecimal getFresponsibillity() {
		return this.fresponsibillity;
	}

	public void setFresponsibillity(BigDecimal fresponsibillity) {
		this.fresponsibillity = fresponsibillity;
	}

	public String getFsimplename() {
		return this.fsimplename;
	}

	public void setFsimplename(String fsimplename) {
		this.fsimplename = fsimplename;
	}

	public String getFtaxregnumber() {
		return this.ftaxregnumber;
	}

	public void setFtaxregnumber(String ftaxregnumber) {
		this.ftaxregnumber = ftaxregnumber;
	}

	public String getFtradeid() {
		return this.ftradeid;
	}

	public void setFtradeid(String ftradeid) {
		this.ftradeid = ftradeid;
	}

	public String getFupgradesource() {
		return this.fupgradesource;
	}

	public void setFupgradesource(String fupgradesource) {
		this.fupgradesource = fupgradesource;
	}

	public BigDecimal getFversionnumber() {
		return this.fversionnumber;
	}

	public void setFversionnumber(BigDecimal fversionnumber) {
		this.fversionnumber = fversionnumber;
	}



	public OperationFlg getOperationFlg() {
		return operationFlg;
	}

	public void setOperationFlg(OperationFlg operationFlg) {
		this.operationFlg = operationFlg;
	}

	private OperationFlg operationFlg;

	public boolean isIfBigCustomer() {
		return ifBigCustomer;
	}

	public void setIfBigCustomer(boolean ifBigCustomer) {
		this.ifBigCustomer = ifBigCustomer;
	}

	public String getCustGroup() {
		return custGroup;
	}

	public void setCustGroup(String custGroup) {
		this.custGroup = custGroup;
	}

	public String getFcustcategory() {
		return fcustcategory;
	}

	public void setFcustcategory(String fcustcategory) {
		this.fcustcategory = fcustcategory;
	}

	public String getMarketingDept() {
		return marketingDept;
	}

	public void setMarketingDept(String marketingDept) {
		this.marketingDept = marketingDept;
	}

	public Date getMarketingAllotTime() {
		return marketingAllotTime;
	}

	public void setMarketingAllotTime(Date marketingAllotTime) {
		this.marketingAllotTime = marketingAllotTime;
	}

	public Date getMarketingCreateTime() {
		return marketingCreateTime;
	}

	public void setMarketingCreateTime(Date marketingCreateTime) {
		this.marketingCreateTime = marketingCreateTime;
	}

	public String getMarketingCreateUser() {
		return marketingCreateUser;
	}

	public void setMarketingCreateUser(String marketingCreateUser) {
		this.marketingCreateUser = marketingCreateUser;
	}

	public String getFossId() {
		return fossId;
	}

	public void setFossId(String fossId) {
		this.fossId = fossId;
	}

	/**
	 * @return secondTrade : return the property secondTrade.
	 */
	public String getSecondTrade() {
		return secondTrade;
	}

	/**
	 * @param secondTrade : set the property secondTrade.
	 */
	public void setSecondTrade(String secondTrade) {
		this.secondTrade = secondTrade;
	}

}
