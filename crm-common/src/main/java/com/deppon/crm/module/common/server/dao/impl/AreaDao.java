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

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.common.server.dao.IAreaDao;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AreaDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */

public class AreaDao extends iBatis3DaoImpl implements IAreaDao {
	
	private static final String NAMESPACE_AREA = "com.deppon.crm.module.common.shared.domain.Area.";
	private static final String INSERT_AREA="insertArea";
	private static final String UPDATE_AREA="updateArea";
	private static final String DELETE_AREA="deleteArea";
	private static final String QUERY_AREA="queryAreaByID";
	private static final String SEARCH_AREA="searchAreasByName";
	private static final String SEARCH_AREA_ONE="searchAreaByName";
	private static final String SEARCH_AREA_COUNT="searchAreasCountByName";
	// 根据编码查询区域
	private static final String QUERY_AREA_NUM="queryAreaByNum";
	/**
	 * @作者：罗典
	 * @描述：根据编码查询有效的区域信息
	 * @时间：2013-01-31
	 * @参数：编码
	 * @返回：区域信息
	 * */
	public Area queryVaildAreaByNum(String number){
		return (Area)this.getSqlSession().selectOne(NAMESPACE_AREA+QUERY_AREA_NUM,number);
	}
	/** 
	 * @description 获取对应城市的区域
	 * @author 毛建强
	 * @2012-3-15
	 * @return 返回区域集合
	 */
	@SuppressWarnings("unchecked")
	public List<Area> getAreaByCity(String cityId) {
		List<Area> list = null;
		list =  this.getSqlSession().selectList("com.deppon.crm.module.common.shared.domain.Area.getAreaByCity",cityId);
		return list;
	}

	/** 
	 * @description 根据id查询对应城市
	 * @author 毛建强
	 * @2012-3-15
	 * @return 返回区域集合
	 */
	@SuppressWarnings("unchecked")
	public List<Area> getAreaById(String id) {
		List<Area> list = null;
		list =  this.getSqlSession().selectList("com.deppon.crm.module.common.shared.domain.Area.getAreaById",id);
		return list;
	}

	@Override
	public void insertArea(Area area) {
		// TODO Auto-generated method stub
		this.getSqlSession().insert(NAMESPACE_AREA+INSERT_AREA, area);
	}

	@Override
	public void deleteArea(Area area) {
		// TODO Auto-generated method stub
		this.getSqlSession().delete(NAMESPACE_AREA+DELETE_AREA, area);
	}

	@Override
	public void updateArea(Area area) {
		// TODO Auto-generated method stub
		this.getSqlSession().update(NAMESPACE_AREA+UPDATE_AREA, area);
	}

	@Override
	public Area getAreaById(BigDecimal areaId) {
		// TODO Auto-generated method stub
		return (Area)this.getSqlSession().selectOne(NAMESPACE_AREA+QUERY_AREA, areaId);
	}

	@Override
	public List<Area> searchAreasByName(AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		List<Area> list = null;
		if (null ==condition.getLimit() || 1> condition.getLimit()) {
			list = this.getSqlSession()
					.selectList(NAMESPACE_AREA
							+ SEARCH_AREA,
							condition);
		} else {
			RowBounds bounds = new RowBounds(condition.getStart(),
					condition.getLimit());
			list = this.getSqlSession()
					.selectList(NAMESPACE_AREA
							+ SEARCH_AREA,
							condition, bounds);
		}
		return list;
	}

	@Override
	public Integer searchAreasCountByName(AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		Integer count = (Integer) this
				.getSqlSession()
				.selectOne(NAMESPACE_AREA+SEARCH_AREA_COUNT,
						condition);
		return count;
	}
	@Override
	public Area searchAreaByName(AreaSearchCondition condition) {
		Area area = null;
		area = (Area)this.getSqlSession()
					.selectOne(NAMESPACE_AREA
							+ SEARCH_AREA_ONE,
							condition);
		return area;
	}

}
