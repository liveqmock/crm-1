package com.deppon.crm.module.recompense.shared.domain;
/**
 * 
 * <p>
 * Description:信息提醒<br />
 * </p>
 * @title TodoReminder.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class TodoReminder {

	// 用户Id
	private String userId;
	// 数量
	private int todoNum;
	/**
	 * <p>
	 * Description:userId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * <p>
	 * Description:userId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * <p>
	 * Description:todoNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public int getTodoNum() {
		return todoNum;
	}
	/**
	 * <p>
	 * Description:todoNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setTodoNum(int todoNum) {
		this.todoNum = todoNum;
	}



}
