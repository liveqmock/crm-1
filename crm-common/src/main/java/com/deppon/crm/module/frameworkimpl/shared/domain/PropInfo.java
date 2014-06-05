/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PropInfo.java
 * @package com.deppon.crm.module.frameworkimpl.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */
package com.deppon.crm.module.frameworkimpl.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:配置信息实体<br />
 * </p>
 * @title PropInfo.java
 * @package com.deppon.crm.module.frameworkimpl.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */

public class PropInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// key
	private String key;
	// value
	private String value;
	/**
	 * @return key : return the property key.
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key : set the property key.
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return value : return the property value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value : set the property value.
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
