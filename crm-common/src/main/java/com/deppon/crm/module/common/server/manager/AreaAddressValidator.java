/**
 * Filename:	AreaAddressValidator.java
 * Description:
 * Copyright:	Copyright (c)2011
 * Company:		IBM1
 * @author:		
 * @version:	
 * create at:	2012-5-9 上午10:17:12
 *
 * Modification History:
 * Date			Author			Version			Description
 * ------------------------------------------------------------------------
 * 2012-5-9	    
 */

package com.deppon.crm.module.common.server.manager;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;


public class AreaAddressValidator {
	
	public boolean isDeleteableCity(City city){
		boolean flag=true;
		List<Area> areaList=city.getAreaList();
		if(areaList!=null&&areaList.size()>0){
			flag=false;
		}
		return flag;
	}
	
	public boolean isDeleteableProvince(Province province){
		boolean flag=true;
		List<City> cityList=province.getCityList();
		if(cityList!=null&&cityList.size()>0){
			flag=false;
		}
		return flag;
	}

}
