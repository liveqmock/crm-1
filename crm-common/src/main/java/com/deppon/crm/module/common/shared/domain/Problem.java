/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title Problem.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author Administrator
 * @version 0.1 2012-8-1
 */
package com.deppon.crm.module.common.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title Problem.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author 苏玉军
 * @version 0.1 2012-8-1
 */

public class Problem {

	//id
	private int id;
	//标题
	private String title;
	//描述
	private String description;
	//状态
	private String status;
	//url
	private String weburl;
	
	public String getWeburl() {
		return weburl;
	}
	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
