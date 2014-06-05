/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IProblemService.java
 * @package com.deppon.crm.module.common.server.service 
 * @author Administrator
 * @version 0.1 2012-8-1
 */
package com.deppon.crm.module.common.server.service;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.Problem;

/**   
 * <p>
 * 查询问题反馈列表<br />
 * </p>
 * @title IProblemService.java
 * @package com.deppon.crm.module.common.server.service 
 * @author 苏玉军
 * @version 0.1 2012-8-1
 */

public interface IProblemService {
	public List<Problem> queryAllFeedBack();
}
