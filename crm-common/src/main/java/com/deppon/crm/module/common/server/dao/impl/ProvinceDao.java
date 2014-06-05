/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProvinceDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.server.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.common.server.dao.IProvinceDao;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProvinceDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */

public class ProvinceDao extends iBatis3DaoImpl implements IProvinceDao {
	
	private static final String NAMESPACE_PROVINCE = "com.deppon.crm.module.common.shared.domain.Province.";
	private static final String INSERT_PROVINCE="insertProvince";
	private static final String UPDATE_PROVINCE="updateProvince";
	private static final String DELETE_PROVINCE="deleteProvince";
	private static final String QUERY_PROVINCE="queryProvinceByID";
	private static final String SEARCH_PROVINCES="searchProvincesByName";
	private static final String SEARCH_PROVINCE_ONE="searchProvinceByName";
	private static final String SEARCH_PROVINCES_COUNT="searchProvincesCountByName";
	private static final String GET_PROVINCE_BY_CITYID="getProvinceByCityId";
	// 根据编码获取有效的省份信息
	private static final String QUERY_PROVINCE_NUMBER="queryProvinceByNum";
	/**
	 * @作者：罗典
	 * @描述：根据编码查询省份信息
	 * @时间：2013-01-31
	 * @参数：编码
	 * @返回：省份信息
	 * */
	public Province queryVaildProvinceByNum(String number){
		return (Province)this.getSqlSession().selectOne(NAMESPACE_PROVINCE
				+QUERY_PROVINCE_NUMBER, number);
	}
	/** 
	 * @description 获取所有省份
	 * @author 毛建强
	 * @2012-3-15
	 * @return 省份集合
	 */
	@SuppressWarnings("unchecked")
	public List<Province> getAllProvince() {
		return this.getSqlSession().selectList("com.deppon.crm.module.common.shared.domain.Province.getAllProvince");
	}
	
	public void insertProvince(Province province){
		this.getSqlSession().insert(NAMESPACE_PROVINCE+INSERT_PROVINCE, province);
	}

	@Override
	public void deleteProvince(Province province) {
		// TODO Auto-generated method stub
		this.getSqlSession().delete(NAMESPACE_PROVINCE+DELETE_PROVINCE, province);
	}

	@Override
	public Province getProvinceById(BigDecimal provinceId) {
		// TODO Auto-generated method stub
		return (Province) this.getSqlSession().selectOne(
				NAMESPACE_PROVINCE + QUERY_PROVINCE, provinceId);
	}

	@Override
	public void updateProvince(Province province) {
		// TODO Auto-generated method stub
		this.getSqlSession().update(NAMESPACE_PROVINCE+UPDATE_PROVINCE, province);
	}

	@Override
	public List<Province> searchProvincesByName(AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		List<Province> list = null;
		if (null ==condition.getLimit() || 1> condition.getLimit()) {
			list = this.getSqlSession()
					.selectList(NAMESPACE_PROVINCE
							+ SEARCH_PROVINCES,
							condition);
		} else {
			RowBounds bounds = new RowBounds(condition.getStart(),
					condition.getLimit());
			list = this.getSqlSession()
					.selectList(NAMESPACE_PROVINCE
							+ SEARCH_PROVINCES,
							condition, bounds);
		}
		return list;
	}

	@Override
	public Integer searchProvincesCountByName(
			AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		Integer count = (Integer) this
				.getSqlSession()
				.selectOne(NAMESPACE_PROVINCE+SEARCH_PROVINCES_COUNT,
						condition);
		return count;
	}
	public Province getProvincesByCityId(Integer cityId){
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("cityId", cityId);
		Province province = (Province) this
				.getSqlSession()
				.selectOne(NAMESPACE_PROVINCE+GET_PROVINCE_BY_CITYID,
					map);
		return province;
	}
	@Override
	public Province searchProvinceByName(AreaSearchCondition condition) {
				Province province = null;
				province = (Province)this.getSqlSession().selectOne(NAMESPACE_PROVINCE
									+ SEARCH_PROVINCE_ONE,
									condition);

				return province;
	}
	
}
