package com.deppon.map;

import java.util.List;

import org.junit.Test;

import com.deppon.map.baidu.DeptByDistance;
import com.deppon.map.baidu.IMap2CrmService;
import com.deppon.map.baidu.Map2CrmServiceImplService;
import com.deppon.map.baidu.MapWsException;

public class Map2CrmServiceTest {

	@Test
	public void test1(){
		IMap2CrmService map2CrmService = new Map2CrmServiceImplService().getMap2CrmServiceImplPort();
//		try {
////			List<DeptByDistance> dbds = map2CrmService.getFiveKmDepts("上海市青浦区徐泾镇");
////			System.out.println(dbds.size());
//		} catch (MapWsException e) {
//			e.printStackTrace();
//		}
//		
	}
}
