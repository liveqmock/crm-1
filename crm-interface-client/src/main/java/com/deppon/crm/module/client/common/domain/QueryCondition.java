package com.deppon.crm.module.client.common.domain;

import java.util.Date;
/**
 * 与外围系统交互关于查询相关的交互接口的查询条件的基类
 * @author davidcun @2012-3-21
 */
public class QueryCondition {

	//起始时间
	private Date startDate;
	//结束时间
	private Date endDate;
	//当前分页，用来处理查询量大的时候进行分页的,默认值是-1，表示不需要分页
	private int currentPage=-1;
	
	//分页大小，每页的条数
	private int pageSize;
	
	
	public Date getStartDate() {
		return startDate;
	}
	public QueryCondition setStartDate(Date startDate) {
		this.startDate = startDate;
		return this;
	}
	public Date getEndDate() {
		return endDate;
	}
	public QueryCondition setEndDate(Date endDate) {
		this.endDate = endDate;
		return this;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public QueryCondition setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		return this;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
