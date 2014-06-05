package com.deppon.crm.module.complaint.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * Description:退回原因/反馈记录<br />
 * </p>
 * @title FeedBackReasion.java
 * @package com.deppon.crm.module.complaint.shared.domain 
 * @author ouy
 * @version 0.1 2012-4-18
 */
public class FeedBackReasion extends BaseEntity{
	
	//主键ID
	private BigDecimal fid;
	// 工单id
	private BigDecimal complaintid;
	//凭单号
	private String wailbillnunber;
	//内容（解决情况）
	private String wailbillcontent;
	//名字（反馈人）
	private String recordman;
	//部门（组织）
	private BigDecimal recordpartermentid;
	private String recordpartmentname;
	//时间
	private Date recordtime;
	//类型
	private String recordtype;
	// 子类型 (退回类型补充)
	private String subtype;
	// 新加字段
	//回访结果
	private String revisitresult;
	//客户满意度
	private String custsatisfy;
	/**
	 * set get 方法
	 */
	//complaintid get方法
	public BigDecimal getComplaintid() {
		return complaintid;
	}
	//fid get方法
	public BigDecimal getFid() {
		return fid;
	}
	//fid set方法
	public void setFid(BigDecimal fid) {
		this.fid = fid;
	}
	//complaintid get方法
	public void setComplaintid(BigDecimal complaintid) {
		this.complaintid = complaintid;
	}
	//wailbillnunber get方法
	public String getWailbillnunber() {
		return wailbillnunber;
	}
	//wailbillnunber set方法
	public void setWailbillnunber(String wailbillnunber) {
		this.wailbillnunber = wailbillnunber;
	}
	//wailbillcontent get方法
	public String getWailbillcontent() {
		return wailbillcontent;
	}
	//wailbillcontent set方法
	public void setWailbillcontent(String wailbillcontent) {
		this.wailbillcontent = wailbillcontent;
	}
	//recordman get方法
	public String getRecordman() {
		return recordman;
	}
	//recordman set方法
	public void setRecordman(String recordman) {
		this.recordman = recordman;
	}
	//recordpartermentid get方法
	public BigDecimal getRecordpartermentid() {
		return recordpartermentid;
	}
	//recordpartermentid set方法
	public void setRecordpartermentid(BigDecimal recordpartermentid) {
		this.recordpartermentid = recordpartermentid;
	}
	//recordpartmentname get方法
	public String getRecordpartmentname() {
		return recordpartmentname;
	}
	//recordpartmentname set方法
	public void setRecordpartmentname(String recordpartmentname) {
		this.recordpartmentname = recordpartmentname;
	}
	//recordtime get方法
	public Date getRecordtime() {
		return recordtime;
	}
	//recordtime set方法
	public void setRecordtime(Date recordtime) {
		this.recordtime = recordtime;
	}
	//recordtype get方法
	public String getRecordtype() {
		return recordtype;
	}
	//recordtype set方法
	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}
	//revisitresult get方法
	public String getRevisitresult() {
		return revisitresult;
	}
	//revisitresult set方法
	public void setRevisitresult(String revisitresult) {
		this.revisitresult = revisitresult;
	}
	//custsatisfy get方法
	public String getCustsatisfy() {
		return custsatisfy;
	}
	//custsatisfy set方法
	public void setCustsatisfy(String custsatisfy) {
		this.custsatisfy = custsatisfy;
	}
	//subtype get方法
	public String getSubtype() {
		return subtype;
	}
	//subtype set方法
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
}
