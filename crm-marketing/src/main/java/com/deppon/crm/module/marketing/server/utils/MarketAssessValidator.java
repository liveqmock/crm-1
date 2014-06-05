package com.deppon.crm.module.marketing.server.utils;

import java.util.List;
import java.util.Set;

import com.deppon.crm.module.marketing.shared.domain.AssessDept;
/**   
 * <p>
 * Description:营销效果评估报表校验<br />
 * </p>
 * @title MarketAssessValidator.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author ZhouYuan
 * @version 2013-01-17
 */
public class MarketAssessValidator {
	/**
	 * 
	 * <p>
	 * 校验部门列表是否为空<br/>
	 * </p>
	 * @param depts 部门列表
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 */
	public static boolean assessDepts(List<AssessDept> depts){
		//如果部门列表为空
		if( depts == null){
			return false;
		}
		return true;
	}
	/**
	 * 
	 * <p>
	 * 校验用户权限是否为空<br/>
	 * </p>
	 * @param auths 用户权限
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 */
	public static boolean assessAuths(Set<String> auths){
		//如果权限为空
		if(auths == null){
			return false;
		}
		return true;
	}
}
