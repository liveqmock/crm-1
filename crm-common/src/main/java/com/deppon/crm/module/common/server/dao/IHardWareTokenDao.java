/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IHardWareTokenDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-7-11
 */
package com.deppon.crm.module.common.server.dao;

import com.deppon.crm.module.common.shared.domain.HardWareToken;

/**   
 * <p>
 * Description: 硬件信息Token 信息处理Dao<br />
 * </p>
 * @title IHardWareTokenDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-7-11
 */

public interface IHardWareTokenDao {
	/**
	 * <p>
	 * Description: 保存Token信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-7-11
	 * @param token
	 * @return
	 * HardWareToken
	 */
	public HardWareToken save(HardWareToken token);
	
	/**
	 * <p>
	 * Description: 根据token查信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-7-11
	 * @param token
	 * @return
	 * HardWareToken
	 */
	public HardWareToken getHardWareTokenByToken(String token);
	
	/**
	 * <p>
	 * Description: 根据token ID查信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-7-11
	 * @param id
	 * @return
	 * HardWareToken
	 */
	public HardWareToken getHardWareTokenById(String id);
	
	/**
	 * <p>
	 * Description: 更新token信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-7-11
	 * @param token
	 * @return
	 * boolean
	 */
	public boolean update(HardWareToken token);
}
