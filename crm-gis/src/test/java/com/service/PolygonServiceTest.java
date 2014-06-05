package com.service;

import junit.framework.Assert;

import org.junit.Test;

import com.deppon.crm.module.gis.server.service.IPolygonService;
import com.deppon.crm.module.gis.server.service.impl.PolygonService;
import com.deppon.crm.module.gis.shared.domain.PolygonEntity;
import com.util.SpringTestHelper;

public class PolygonServiceTest {


	IPolygonService polygonService = null;
	@Test
	public void test(){
		polygonService =  SpringTestHelper.get().getBean(PolygonService.class);
		PolygonEntity polygonEntity = new PolygonEntity();
		polygonEntity.setDescription("13132");
		polygonEntity.setGoogle("121.32545471191406,31.186077693237873");
		polygonEntity.setName("内幕");
		polygonEntity.setType("test");
		//新增范围
		polygonEntity =  polygonService.savePolygon(polygonEntity);
		System.out.println("新增："+polygonEntity.getPolygonID());
		Assert.assertNotNull(polygonEntity.getPolygonID());
		String id =polygonEntity.getPolygonID();
		//查询范围
		polygonEntity = polygonService.selectPolygonById(id);
		System.out.println("查询："+polygonEntity.getPolygonID());
		Assert.assertNotNull(polygonEntity.getPolygonID());
		//更新范围
		polygonEntity=polygonService.updatePolygon(polygonEntity);
		System.out.println("更新："+polygonEntity.getPolygonID());
		Assert.assertNotNull(polygonEntity.getPolygonID());
		
		//删除范围
		polygonService.deletePolygonById(id);
		System.out.println("删除："+polygonEntity.getPolygonID());
		Assert.assertNull( polygonService.selectPolygonById(id));
	}
	
	
}
