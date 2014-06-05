/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AmountInfo.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
package com.deppon.crm.module.coupon.shared.domain;

import java.math.BigDecimal;

/**   
 * <p>
 * Description: 运单金额明细<br />
 * </p>
 * @title AmountInfo.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */

public class AmountInfo {
	// 计价条目代码
    protected String valuationCode;
    // 计价金额
    protected BigDecimal valuationAmonut;
    
	/**
	 * @return valuationCode : return the property valuationCode.
	 */
	public String getValuationCode() {
		return valuationCode;
	}
	/**
	 * @param valuationCode : set the property valuationCode.
	 */
	public void setValuationCode(String valuationCode) {
		this.valuationCode = valuationCode;
	}
	/**
	 * @return valuationAmonut : return the property valuationAmonut.
	 */
	public BigDecimal getValuationAmonut() {
		return valuationAmonut;
	}
	/**
	 * @param valuationAmonut : set the property valuationAmonut.
	 */
	public void setValuationAmonut(BigDecimal valuationAmonut) {
		this.valuationAmonut = valuationAmonut;
	}
    
    
}
