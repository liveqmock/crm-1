/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CCQueryMarketingInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 043260
 * @version 0.1 2014年4月6日
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.List;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CCQueryMarketingInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 043260
 * @version 0.1 2014年4月6日
 */

public class CCQueryMarketingInfo {
	//营销记录
	private List<CCQueryMarketingRecord> records;
	//总记录数据
	private int count;
	/**
	 * @return records : return the property records.  
	 */
	public List<CCQueryMarketingRecord> getRecords() {
		return records;
	}
	/**
	 * @param records : set the property records. 
	 */
	public void setRecords(List<CCQueryMarketingRecord> records) {
		this.records = records;
	}
	/**
	 * @return count : return the property count.  
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count : set the property count. 
	 */
	public void setCount(int count) {
		this.count = count;
	}
}
