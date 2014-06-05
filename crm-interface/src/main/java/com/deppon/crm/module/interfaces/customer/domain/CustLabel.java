package com.deppon.crm.module.interfaces.customer.domain;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * 标签类型实体
 * </p>
 * @title CustLabel.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @author 唐亮
 * @version 0.1 2013-4-11
 */
public class CustLabel extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	//标签类型
	private String custType;
	//客户Id
	private String custId;
	//标签
	private Label label;
	/**
	 * 
	 * Description:custType<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-28
	 */
	public String getCustType() {
		return custType;
	}
	/**
	 * 
	 * Description:custType<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-28
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
	/**
	 * 
	 * Description:custId<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-28
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * 
	 * Description:custId<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-28
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * 
	 * Description:label<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-28
	 */
	public Label getLabel() {
		return label;
	}
	/**
	 * 
	 * Description:label<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-28
	 */
	public void setLabel(Label label) {
		this.label = label;
	}
	/**
	 * 
	 * Description:serialversionuid<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-28
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
