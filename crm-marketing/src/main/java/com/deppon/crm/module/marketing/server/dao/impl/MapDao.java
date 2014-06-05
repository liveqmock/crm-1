/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MapDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 Mar 28, 2012
 */
package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.marketing.server.dao.IMapDao;
import com.deppon.crm.module.marketing.shared.domain.MapQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.CustomerLocation;
import com.deppon.crm.module.marketing.shared.domain.MapView;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:五公里地图<br />
 * </p>
 * @title MapDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 Mar 28, 2012
 */

public class MapDao extends iBatis3DaoImpl implements IMapDao {

	//namespace
	private static final String NAMESPACE_RETURNVISIT="com.deppon.crm.module.marketing.shared.domain.MapView.";
	// 五公里地图-查询用户
	private static final String SELECT_USERS="searchUsers";
	//计数
	private static final String SELECT_USERSCOUNT="searchUsersCount";
	//判断客户遍及是否存在
	private static final String IS_CUSTOMERLOCATION_EXIST = "isCustomerLocationExist";
	//新增位置
	private static final String INSERT_CUSTOMERLOCATION = "insertCustomerLocation";
	//更新位置
	private static final String UPDATE_CUSTOMERLOCATION = "updateCustomerLocation";
	
	/**
	 * 分页查询客户标注信息
	 * @see com.deppon.crm.module.marketing.server.dao.IMapDao#searchUsers(com.deppon.crm.module.marketing.shared.domain.MapQueryCondition, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MapView> searchUsers(MapQueryCondition condition, int start,
			int limit) {
		//分页参数
		RowBounds rowBounds = new RowBounds(start, limit);
		//查询
		return getSqlSession().selectList(NAMESPACE_RETURNVISIT + SELECT_USERS, condition, rowBounds);
	}

	/**
	 * 判断客户位置是否存在
	 * @see com.deppon.crm.module.marketing.server.dao.IMapDao#isCustomerLocationExist(com.deppon.crm.module.marketing.shared.domain.CustomerLocation)
	 */
	@Override
	public boolean isCustomerLocationExist(CustomerLocation customerLocation) {
		//查询
		return (Integer) getSqlSession().selectOne(NAMESPACE_RETURNVISIT + IS_CUSTOMERLOCATION_EXIST, customerLocation) > 0;
	}

	/**
	 * 新增客户标记
	 * @see com.deppon.crm.module.marketing.server.dao.IMapDao#insertCustomerLocation(com.deppon.crm.module.marketing.shared.domain.CustomerLocation)
	 */
	@Override
	public boolean insertCustomerLocation(CustomerLocation customerLocation) {
		//查询
		return getSqlSession().insert(NAMESPACE_RETURNVISIT + INSERT_CUSTOMERLOCATION, customerLocation) > 0;
	}

	/**
	 * 更新客户标记
	 * @see com.deppon.crm.module.marketing.server.dao.IMapDao#updateCustomerLocation(com.deppon.crm.module.marketing.shared.domain.CustomerLocation)
	 */
	@Override
	public boolean updateCustomerLocation(CustomerLocation customerLocation) {
		//查询
		return getSqlSession().update(NAMESPACE_RETURNVISIT + UPDATE_CUSTOMERLOCATION, customerLocation) > 0;
	}

	/**
	 * 查询总数
	 * @see com.deppon.crm.module.marketing.server.dao.IMapDao#searchUsersCount(com.deppon.crm.module.marketing.shared.domain.MapQueryCondition)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int searchUsersCount(MapQueryCondition condition) {
		//查询总数
		List<Integer> counts = getSqlSession().selectList(NAMESPACE_RETURNVISIT + SELECT_USERSCOUNT, condition );
		//综合
		int sum = 0;
		//叠加
		for (Integer integer : counts) {
			//叠加
			sum += integer;
		}
		return sum;
	}

	
	
	
	
}
