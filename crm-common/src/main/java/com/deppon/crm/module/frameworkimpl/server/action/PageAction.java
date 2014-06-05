package com.deppon.crm.module.frameworkimpl.server.action;

import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * 
 * <p>
 * Description:分页Action <br />
 * </p>
 * @title PageAction.java
 * @package com.deppon.crm.module.frameworkimpl.server.action 
 * @author Weill
 * @version 0.1 2012-4-12
 */
public class PageAction extends AbstractAction {
	
	//开始记录数
	private int start = 0;
	//结束记录数
	private int limit = 0;
	//返回前端总记录行数
	private Long totalCount = 0L;
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
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	} 
}
