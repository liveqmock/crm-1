package com.deppon.crm.module.customer.shared.domain.integral;

import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * Description:积分<br />
 * </p>
 * @title Integral.java
 * @package com.deppon.crm.module.customer.shared.domain.integral 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class Integral extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Integer score = 0;//分数
	private String type = null;//积分类型
	private Contact contact = null;//联系人
	/**
	 * <p>
	 * Description:score<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * <p>
	 * Description:score<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/**
	 * <p>
	 * Description:type<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getType() {
		return type;
	}
	/**
	 * <p>
	 * Description:type<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * <p>
	 * Description:contact<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Contact getContact() {
		return contact;
	}
	/**
	 * <p>
	 * Description:contact<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
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
