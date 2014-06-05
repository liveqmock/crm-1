/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AreaDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.common.server.dao.IAbCityDao;
import com.deppon.crm.module.common.shared.domain.AbCity;
import com.deppon.crm.module.common.shared.domain.AbCitySearchCondition;
import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AbCityDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author ouy
 * @version 0.1 2012-5-8
 */

public class AbCityDao extends iBatis3DaoImpl implements IAbCityDao {
	private static final String NAMESPACE = "com.deppon.crm.module.common.shared.domain.AbCity.";
	private static final String GET_ABCITY="getAbCity";
	private static final String GET_ABCITY_COUNT="getAbCityCount";
	@Override
	public List<AbCity> getAbCity(AbCitySearchCondition condition) {
		// TODO Auto-generated method stub
		List<AbCity> abcityList=null;
		if(null==condition.getLimit() || 0==condition.getLimit()){
			abcityList=this.getSqlSession().selectList(NAMESPACE+GET_ABCITY, condition);
		}else{
			RowBounds rb=new RowBounds(condition.getStart(),condition.getLimit());
			abcityList=this.getSqlSession().selectList(NAMESPACE+GET_ABCITY, condition,rb);
		}
		return abcityList;
	}
	@Override
	public Long getAbCityCount(AbCitySearchCondition condition) {
		// TODO Auto-generated method stub
		Long num=(Long)this.getSqlSession().selectOne(NAMESPACE+GET_ABCITY_COUNT, condition);
		return num;
	}

	
}
