/**
 * @description
 * @author 赵斌
 * @2012-4-24
 * @return
 */
package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @author 赵斌
 * @短信验证码
 *
 */
public class RandomNumber extends BaseEntity 
{
	private static final long serialVersionUID = 1769887157901885423L;
	//联系人手机号
	private String mobile;
	//随机验证码
	private String radomnumber;
	//失效时间
	private Date effecttime;
	/**
	 * <p>
	 * Description:mobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * <p>
	 * Description:mobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * <p>
	 * Description:radomnumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRadomnumber() {
		return radomnumber;
	}
	/**
	 * <p>
	 * Description:radomnumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRadomnumber(String radomnumber) {
		this.radomnumber = radomnumber;
	}
	/**
	 * <p>
	 * Description:effecttime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getEffecttime() {
		return effecttime;
	}
	/**
	 * <p>
	 * Description:effecttime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setEffecttime(Date effecttime) {
		this.effecttime = effecttime;
	}
	/**
	 * <p>
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
