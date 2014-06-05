package com.deppon.crm.module.gis.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.map.IGisOperate;
import com.deppon.crm.module.client.map.domain.CoordinateDetail;
import com.deppon.crm.module.gis.server.service.IDeptQueryService;
import com.deppon.crm.module.gis.shared.domain.PointEntity;

public class DeptQueryService implements IDeptQueryService{
	private IGisOperate gisOperate;

	public IGisOperate getGisOperate() {
		return gisOperate;
	}

	public void setGisOperate(IGisOperate gisOperate) {
		this.gisOperate = gisOperate;
	}

	@Override
	public List<PointEntity> deptQueryByStanderCode(List<String> deptCodeList)  {
		List<CoordinateDetail> resultList=new ArrayList<CoordinateDetail>();
		List<PointEntity> pointList=new ArrayList<PointEntity>();
		try {
			resultList =gisOperate.queryCoordinate(deptCodeList);
			if(resultList.size()>0){
				for (int i = 0; i < resultList.size(); i++) {
					PointEntity p = new PointEntity();
					p.setAddress(resultList.get(i).getAddress());
					p.setBaiduLat(resultList.get(i).getBaiduLat());
					p.setBaiduLng(resultList.get(i).getBaiduLng());
					p.setDesc(resultList.get(i).getDesc());
					p.setGoogleLat(resultList.get(i).getGoogleLat());
					p.setGoogleLng(resultList.get(i).getGoogleLng());
					p.setType(resultList.get(i).getType());
					p.setName(resultList.get(i).getDeptName());
					pointList.add(p);
				}
			}
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
		return pointList;
	}

}
