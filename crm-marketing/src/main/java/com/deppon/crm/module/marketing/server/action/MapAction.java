package com.deppon.crm.module.marketing.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.marketing.server.manager.IMapManager;
import com.deppon.crm.module.marketing.shared.domain.CustomerLocation;
import com.deppon.crm.module.marketing.shared.domain.MapQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.MapView;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
/**
 * Description:五公里地图
 * @title MapAction.java
 * @package com.deppon.crm.module.marketing.server.action 
 * @author 毛建强
 * @version 0.1 2012-4-16
 */
public class MapAction extends AbstractAction {
	//查询条件
	private MapQueryCondition condition;
	//manager接口
	private IMapManager manager;
	//分页要用参数
	private int start;
	//每页条数
	private int limit;
	//总行数
	private Long totalCount;
	//返回客户信息列表
	private List<MapView> mapViewList ;
	//客户坐标
	private CustomerLocation location;
	
	/**
	 * Description:查询客户列表
	 * @author 毛建强
	 * @version 0.1 2012-4-16
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchCustomerList(){
		//结果内容
		Map<String, Object> result = new HashMap<String, Object>();
		com.deppon.crm.module.authorization.shared.domain.User user = (com.deppon.crm.module.authorization.shared.domain.User) UserContext.getCurrentUser();
		//分页查询
		result = manager.getCustomerList(condition, start, limit, user);
		//get
		mapViewList = (List<MapView>) result.get("data");
		//get
		totalCount = Long.valueOf( result.get("count").toString());
		return SUCCESS;
	}
	
	/**
	 * Description:保存标注
	 * @author 毛建强
	 * @version 0.1 2012-4-16
	 * @param 
	 * @return
	 */
	@JSON
	public String markCustomerLocation(){
		//客户位置
		List<CustomerLocation> customerLocationList = new ArrayList<CustomerLocation>();
		//添加位置
		customerLocationList.add(location);
		//标注客户位置
		manager.markCustomerLocation(customerLocationList,this.getCurrentUser());
		//返回结果
		return SUCCESS;
	}
	
	//get和set方法
	public void setCondition(MapQueryCondition condition) {
		this.condition = condition;
	}
	
	// get property : return the property condition
	public MapQueryCondition getCondition() {
		return condition;
	}
	// get manager : return the property manager
	public void setManager(IMapManager manager) {
		this.manager = manager;
	}
	// set start : set the property start
	public void setStart(int start) {
		this.start = start;
	}
	// set start : set the property start
	public void setLimit(int limit) {
		this.limit = limit;
	}	
	// set start : set the property start
	public Long getTotalCount() {
		return totalCount;
	}
	// set totalCount : set the property totalCount
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	// get mapViewList : get the property mapViewList
	public List<MapView> getMapViewList() {
		return mapViewList;
	}
	// get location : get the property location
	public CustomerLocation getLocation() {
		return location;
	}
	// set location : set the property location
	public void setLocation(CustomerLocation location) {
		this.location = location;
	}
	/**
	 * 
	 * <p>
	 * 获取当前用户<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-30
	 * @return
	 * com.deppon.crm.module.authorization.shared.domain.User
	 */
	private com.deppon.crm.module.authorization.shared.domain.User getCurrentUser(){
		//返回当前用户
		return (com.deppon.crm.module.authorization.shared.domain.User) UserContext.getCurrentUser();
	}
}
