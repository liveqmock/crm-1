/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title HardWareTokenDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2012-7-11
 */
package com.deppon.crm.module.common.server.dao.impl;

import com.deppon.crm.module.common.server.dao.IHardWareTokenDao;
import com.deppon.crm.module.common.shared.domain.HardWareToken;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description: 硬件信息Token 信息处理Dao<br />
 * </p>
 * @title HardWareTokenDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2012-7-11
 */

public class HardWareTokenDao extends iBatis3DaoImpl implements IHardWareTokenDao {
	private static final String NAMESPACE_HARDWARE 	= 	"com.deppon.crm.module.common.shared.domain.LoginToken.";
	private static final String SAVE_TOKEN_INFO 	= 	"saveTokenInfo";
	private static final String GET_TOKEN_BY_TOKEN 	= 	"getTokenByToken";
	private static final String GET_TOKEN_BY_ID 	= 	"getTokenById";
	private static final String UPDATE_TOKEN_INFO 	= 	"updTokenInfo";

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.IHardWareTokenDao#save(com.deppon.crm.module.common.shared.domain.HardWareToken)
	 */
	@Override
	public HardWareToken save(HardWareToken token) {
		this.getSqlSession().insert(NAMESPACE_HARDWARE + SAVE_TOKEN_INFO,token);
		return token;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.IHardWareTokenDao#getHardWareTokenByToken(java.lang.String)
	 */
	@Override
	public HardWareToken getHardWareTokenByToken(String token) {
		return (HardWareToken) this.getSqlSession().selectOne(
				NAMESPACE_HARDWARE + GET_TOKEN_BY_TOKEN, token);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.IHardWareTokenDao#getHardWareTokenById(java.lang.String)
	 */
	@Override
	public HardWareToken getHardWareTokenById(String id) {
		return (HardWareToken) this.getSqlSession().selectOne(
				NAMESPACE_HARDWARE + GET_TOKEN_BY_ID, id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.IHardWareTokenDao#update(com.deppon.crm.module.common.shared.domain.HardWareToken)
	 */
	@Override
	public boolean update(HardWareToken token) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update(
				NAMESPACE_HARDWARE + UPDATE_TOKEN_INFO, token) > 0 ? true
				: false;
	}

}
