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
 * @title RegionPartitionExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author ZhouYuan
 * @version 2013-04-19
 */

public enum RegionPartitionExceptionType {
	//查询实体不能为空
	searchDeptConditionDeptNotNull("i18n.regionPartition.searchDeptCondition.notNull"),
	//该用户没有相应的权限
	searchDeptConditionDeptCharacterNotNull("i18n.regionPartition.searchDeptCondition.deptCharacterNotNull"),
	//查询部门ID不合法
	searchDeptConditionDeptIdIllegal("i18n.regionPartition.searchDeptCondition.deptIdIllegal"),
	//所划区域已存在
	regionIsAlreadyExist("i18n.regionPartition.regionIsAlreadyExist"),
	//该用户没有权限进行区域划分
	regionPartitionWithoutAuth("i18n.regionPartition.WithoutAuth"),
	//该区域未划分
	regionNotPartition("i18n.regionPartition.showErrorMessage.regionNotPartition");
	private String errCode;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	private RegionPartitionExceptionType(String errorCode) {
		this.errCode = errorCode;
	}

}
