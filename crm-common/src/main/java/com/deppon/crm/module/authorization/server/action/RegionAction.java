package com.deppon.crm.module.authorization.server.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.IAreaAddressManager;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * 省市区WEB接入层
 * 
 */
public class RegionAction extends AbstractAction {
	private static final long serialVersionUID = -4992864527052506934L;
	//分页信息
	//private Integer start;
	//private Integer limit;
	private Long totalCount;
	//省事区 manager
	private IAreaAddressManager  areaAddressManager = null;
	
	private AreaSearchCondition searchCondition  = new AreaSearchCondition();
	//省份集合
	private List<Province> provinceList = null;
	//省份
	private Province province = null;
	
	//城市集合
	private List<City> cityList = null;
	//城市
	private City city = null;
	//区县集合
	private List<Area> areaList = null;
	//区县
	private Area area = null;
	//查询参数
	private Map<String,Object> parameterMap=null;
	/**
	 * 省份-查询集合
	 * @author hpf
	 * @return
	 */
	public String searchProvinceList_pager() {
		searchCondition.setLimit(limit);
		searchCondition.setStart(start);
		Map addressManager = areaAddressManager.searchProvincesByName(searchCondition);
		this.provinceList = (List<Province>)addressManager.get("provinces");
		this.totalCount = Long.valueOf(addressManager.get("count").toString());
		return SUCCESS;
	}
	/**
	 * 城市-查询集合
	 * @author hpf
	 * @return
	 */
	public String searchCityList_pager() {
		searchCondition.setLimit(limit);
		searchCondition.setStart(start);
		Map addressManager = areaAddressManager.searchCitysByName(searchCondition);
		this.cityList = (List<City>)addressManager.get("citys");
		this.totalCount = Long.valueOf(addressManager.get("count").toString());
		return SUCCESS;
	}
	/**
	 * 区县-查询集合
	 * @author hpf
	 * @return
	 */
	public String searchAreaList_pager() {
		searchCondition.setLimit(limit);
		searchCondition.setStart(start);
		Map addressManager = areaAddressManager.searchAreasByName(searchCondition);
		this.areaList = (List<Area>)addressManager.get("areas");
		this.totalCount = Long.valueOf(addressManager.get("count").toString());
		return SUCCESS;
	}
	
	/**
	 * 省份-保存或修改
	 * @author hpf
	 * @return
	 */
	public String saveOrUpdateProvince() {
		User user=(User)UserContext.getCurrentUser();
		province.setCreateUser(user.getEmpCode().getId());
		province.setModifyUser(user.getEmpCode().getId());
		province.setStatus("1");
		areaAddressManager.saveOrUpdateProvince(province);
		return SUCCESS;
	}
	
	
	/**
	 * 城市-保存或修改
	 * @author hpf
	 * @return
	 */
	public String saveOrUpdateCity() {
		User user=(User)UserContext.getCurrentUser();
		city.setCreateUser(user.getEmpCode().getId());
		city.setModifyUser(user.getEmpCode().getId());
		city.setStatus("1");
		areaAddressManager.saveOrUpdateCity(city);
		return SUCCESS;
	}
	/**
	 * 区县-保存或修改
	 * @author hpf
	 * @return
	 */
	public String saveOrUpdateArea() {
		User user=(User)UserContext.getCurrentUser();
		area.setCreateUser(user.getEmpCode().getId());
		area.setModifyUser(user.getEmpCode().getId());
		areaAddressManager.saveOrUpdateArea(area);
		return SUCCESS;
	}
	

	/**
	 * 省份-删除
	 * @author hpf
	 * @return
	 */
	public String deleteProvinceList() {
		areaAddressManager.deleteProvinces(provinceList);
		return SUCCESS;
	}
	
	/**
	 * 城市-删除
	 * @author hpf
	 * @return
	 */
	public String deleteCityList() {
		areaAddressManager.deleteCitys(cityList);
		return SUCCESS;
	}
	
	/**
	 * 区县-删除
	 * @author hpf
	 * @return
	 */
	public String deleteAreaList() {
		areaAddressManager.deleteAreas(areaList);
		return SUCCESS;
	}
	
	/**
	 * 省份-集合查询-不分页
	 * @author hpf
	 * @return
	 */
	public String searchProvinceList() {
		this.provinceList = areaAddressManager.getAllProvince();
		return SUCCESS;
	}
	
	/**
	 * 城市-集合查询-不分页
	 * @author hpf
	 * @return
	 */
	public String searchCityListById() {
		String provId = (String)this.parameterMap.get("provId");
		this.cityList = areaAddressManager.getCityByProvinceId(new Integer(provId));
		return SUCCESS;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public IAreaAddressManager getAreaAddressManager() {
		return areaAddressManager;
	}
	public void setAreaAddressManager(IAreaAddressManager areaAddressManager) {
		this.areaAddressManager = areaAddressManager;
	}
	public List<Province> getProvinceList() {
		return provinceList;
	}
	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public List<City> getCityList() {
		return cityList;
	}
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public List<Area> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}
	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}
	public AreaSearchCondition getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(AreaSearchCondition searchCondition) {
		this.searchCondition = searchCondition;
	}
	
}
