/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AreaAddressAction.java
 * @package com.deppon.crm.module.common.server.action 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.common.server.manager.IAreaAddressManager;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**   
 * Description:查询省市区Action<br />
 * @title AreaAddressAction.java
 * @package com.deppon.crm.module.common.server.action 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */

public class AreaAddressAction extends AbstractAction {
	//注入Manager
	private IAreaAddressManager areaAddressManager;
	//省份集合
	private List<Province> provinceList;
	//城市集合
	private List<City> cityList;
	//区域集合
	private List<Area> areaList;
	//查询条件
	private String param;
	
	/**
	 * Description:获取默认城市<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-15
	 * @param 
	 * @return success标志位
	 */
	public String searchCommonCities(){
		cityList = areaAddressManager.getCommonCities();
		return SUCCESS;
	}
	
	/**
	 * Description:获取默认城市<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-15
	 * @param 
	 * @return success标志位
	 */
	public String searchCitiesByName(){
		cityList = areaAddressManager.searchCityByName(param);
		return SUCCESS;
	}
	
	/**
	 * Description:获取默认城市<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-15
	 * @param 
	 * @return success标志位
	 */
	public String searchAreaByCity(){
		if(param!=null){
			String[] params = param.split("-");
			param = params[params.length-1];
		}
		areaList = areaAddressManager.getAreaByCity(param);
		return SUCCESS;
	}
	
	/**
	 * 
	 * Description:获取所有省份<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-19
	 * @param 
	 * @return
	 */
	public String searchAllProvinceList(){
		provinceList = areaAddressManager.getAllProvinceList();
		return SUCCESS;
	}
	/**
	 * 
	 * Description:获取对应省份的城市<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-19
	 * @param 
	 * @return
	 */
	public String searchCityByProvince(){
		if(param!=null){
			String[] params = param.split("-");
			param = params[params.length-1];
		}
		cityList = areaAddressManager.getCityByProvince(param);
		return SUCCESS;
	}
	/**
	 * 
	 * Description:根据id查询对应城市<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-27
	 * @param 
	 * @return
	 */
	public String searchCityById(){
		cityList = this.areaAddressManager.searchCityById(param);
		return SUCCESS;
	}
	/**
	 * 
	 * Description:根据id查询对应省份<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-27
	 * @param 
	 * @return
	 */
	public String searchProvinceById(){
		provinceList = this.areaAddressManager.searchProvinceById(param);
		return SUCCESS;
	}
	/**
	 * Description:根据id查询对应区域<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-27
	 * @param 
	 * @return
	 */
	public String searchAreaById(){
		areaList = this.areaAddressManager.searchAreaById(param);
		return SUCCESS;
	}
		
	/**
	 * Description:通过该方法访问html文件<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-23
	 * @param 
	 * @return
	 */
	public String areaAddressIndex(){
		return SUCCESS;
	}
	
	public List<Province> getProvinceList() {
		return provinceList;
	}
	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}
	public IAreaAddressManager getAreaAddressManager() {
		return areaAddressManager;
	}
	public void setAreaAddressManager(IAreaAddressManager areaAddressManager) {
		this.areaAddressManager = areaAddressManager;
	}
	public List<City> getCityList() {
		return cityList;
	}
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
	public List<Area> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}

}
