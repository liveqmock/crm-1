package com.deppon.crm.module.customer.shared.domain.integral;

import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * Description:GiftConvertHoldRecode<br />
 * </p>
 * @title GiftConvertHoldRecode.java
 * @package com.deppon.crm.module.customer.shared.domain.integral 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class GiftConvertHoldRecode extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 联系人
	private Contact contact = null;
	// 申请礼物
	private Gift gift;
	// 兑换数量
	private Integer convertNumber;
	//创建部门
	private Department createDept;
	//所需分数--view
	private Integer score = 0;
	//状态（正常，积分不够，库存不够）--view
	private String status = "";
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
	 * Description:gift<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Gift getGift() {
		return gift;
	}
	/**
	 * <p>
	 * Description:gift<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setGift(Gift gift) {
		this.gift = gift;
	}
	/**
	 * <p>
	 * Description:convertNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getConvertNumber() {
		return convertNumber;
	}
	/**
	 * <p>
	 * Description:convertNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setConvertNumber(Integer convertNumber) {
		this.convertNumber = convertNumber;
	}
	/**
	 * <p>
	 * Description:createDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Department getCreateDept() {
		return createDept;
	}
	/**
	 * <p>
	 * Description:createDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCreateDept(Department createDept) {
		this.createDept = createDept;
	}
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
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStatus(String status) {
		this.status = status;
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
