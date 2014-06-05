package com.deppon.crm.module.marketing.shared.domain.activity;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @author lvchongxin
 *	客户群信息多选存储表
 */
public class Multiple extends BaseEntity {
	//关联ID
	private String conditionId;
	//关联类型
	private String conditionType;
	//类型（1、2、3、4 ........）
	private String type;
	//值（一级行业、二级行业、客户类型、货量潜力.....）
	private String value;
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getConditionId() {
		return conditionId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param conditionId the conditionId to set
	 */
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getConditionType() {
		return conditionType;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param conditionType the conditionType to set
	 */
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getType() {
		return type;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getValue() {
		return value;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
