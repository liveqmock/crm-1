package com.deppon.crm.module.gis.server.service;

import java.util.List;

import com.deppon.crm.module.gis.shared.domain.PointEntity;

public interface IDeptQueryService {

	List<PointEntity> deptQueryByStanderCode(List<String> deptCode) ;
}
