/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CheckHardWareDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2012-7-11
 */
package com.deppon.crm.module.common.server.dao.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.server.dao.ICheckHardWareDao;
import com.deppon.crm.module.common.shared.domain.HardWareInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CheckHardWareDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2012-7-11
 */

public class CheckHardWareDao extends iBatis3DaoImpl implements ICheckHardWareDao {
	private static final String NAMESPACE_HARDWARE 	= "com.deppon.crm.module.common.shared.domain.Hardware.";
	private static final String GET_HARDWARD_INFO 	= "getHardwardInfo";
	private static final String GET_ALL_HARDWARD_INFO 	= "getAllHardwardInfo";
	private static final String GET_LAST_MODIFYTIME 	= "getLastModifyTime";
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.ICheckHardWareDao#getHardWareInfo(com.deppon.crm.module.common.shared.domain.HardWareInfo)
	 */
	@Override
	public HardWareInfo getHardWareInfo(HardWareInfo info) {
		List<HardWareInfo> list = this.getSqlSession().selectList(
				NAMESPACE_HARDWARE + GET_HARDWARD_INFO, info);
		return list.size() > 0 ? list.get(0) : null ;
	}

    @Override
    public List<HardWareInfo> getAllHardWareInfo() {
        return this.getSqlSession().selectList(NAMESPACE_HARDWARE + GET_ALL_HARDWARD_INFO);
    }

    @Override
    public Date getLastModifyTime() {
        return (Date) this.getSqlSession().selectOne(NAMESPACE_HARDWARE + GET_LAST_MODIFYTIME);
    }

}
