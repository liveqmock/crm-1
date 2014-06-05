package com.deppon.crm.module.gis.server.dao.impl;

import com.deppon.crm.module.gis.server.dao.IPolygonDao;
import com.deppon.crm.module.gis.shared.domain.PolygonEntity;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class PolygonDao extends iBatis3DaoImpl implements IPolygonDao {
	private static final String NAMESPACE = "com.deppon.crm.module.gis.shared.domain.PolygonEntity.";

	@Override
	public PolygonEntity savePolygon(PolygonEntity polygonEntity) {
		getSqlSession().insert(NAMESPACE + "savePolygon", polygonEntity);
		if(polygonEntity.getPolygonID()==null|| "".equals(polygonEntity.getPolygonID())){
			polygonEntity.setPolygonID(polygonEntity.getId());
		}
		return polygonEntity;
	}

	@Override
	public String deletePolygonById(String id) {
		getSqlSession().update(NAMESPACE + "deletePolygonById", id);
		return id;
	}

	@Override
	public PolygonEntity selectPolygonById(String id) {
		return (PolygonEntity) getSqlSession().selectOne(
				NAMESPACE + "selectPolygonById", id);
	}

	@Override
	public void updatePolygonBaiduById(PolygonEntity polygon) {
		getSqlSession().update(NAMESPACE + "updatePolygonBaiduById", polygon);		
	}

}
