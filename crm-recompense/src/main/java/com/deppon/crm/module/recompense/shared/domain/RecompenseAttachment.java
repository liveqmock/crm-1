package com.deppon.crm.module.recompense.shared.domain;

/**
 * 
 * <p>
 * Description:附件<br />
 * </p>
 * @title RecompenseAttachment.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class RecompenseAttachment {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4520198405336022741L;
	//编号
	private String id;
	
	// 附件名称
	private String attachName;

	// 附件地址
	private String attachAddress;

	// 理赔单关联Id
	private String recompenseId;

	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getId() {
		return id;
	}

	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Description:attachName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getAttachName() {
		return attachName;
	}

	/**
	 * <p>
	 * Description:attachName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	/**
	 * <p>
	 * Description:attachAddress<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getAttachAddress() {
		return attachAddress;
	}

	/**
	 * <p>
	 * Description:attachAddress<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAttachAddress(String attachAddress) {
		this.attachAddress = attachAddress;
	}

	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseId() {
		return recompenseId;
	}

	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseId(String recompenseId) {
		this.recompenseId = recompenseId;
	}

	

	
}
