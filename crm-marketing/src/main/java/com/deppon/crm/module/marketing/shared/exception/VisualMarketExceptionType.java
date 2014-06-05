/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */
package com.deppon.crm.module.marketing.shared.exception;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title VisualMarketExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author ZhouYuan
 * @version 2013-04-19
 */

public enum VisualMarketExceptionType {
	
	mustSelectDept("i18n.returnVisit.mustSelectDept"),
	//查询条件不能为空
	conditionMustNotNull("i18n.returnVisit.conditionMustNotNull"),
	//客户类型不能为空
	custTypeMustNotNull("i18n.returnVisit.custTypeMustNotNull"),
	//地图标记为空
	markStatusMustNotNull("i18n.returnVisit.markStatusMustNotNull"),
	//网点表中数据错误
	ladingstationDataNotNull("i18n.returnVisit.ladingstationDataNotNull"),
	//部门ID不能为空
	deptIdMustNotNull("i18n.returnVisit.deptIdMustNotNull"),
	//客户ID不能为空
	custIdNotNull("i18n.visualMarket.custIdNotNull"),
	//客户类型不能为空
	custTypeNotNull("i18n.visualMarket.custTypeNotNull"),
	//创建结束时间大于当前时间
	createEndTimeBiggerThanCurrentTime("i18n.visualMarket.createEndTimeBiggerThanCurrentTime"),
	//开始创建时间不能为空
	createBeginTimeIsNull("i18n.visualMarket.createBeginTimeIsNull"),
	//结束创建时间不能为空
	createEndTimeIsNull("i18n.visualMarket.createEndTimeIsNull"),
	//创建开始时间大于创建结束时间
	createBeginTimeBiggerThanEndTime("i18n.visualMarket.createBeginTimeBiggerThanEndTime"),
	//潜散客查询时，查询的创建时间范围不能超过3个月
	createTimeCanNotExceedThreeMonth("i18n.visualMarket.createTimeCanNotExceedThreeMonth");
	
	private String errCode;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	private VisualMarketExceptionType(String errorCode) {
		this.errCode = errorCode;
	}

}
