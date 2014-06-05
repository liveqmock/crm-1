package com.deppon.crm.module.gis.server.action;

import java.util.List;

import com.deppon.crm.module.gis.server.service.IDeptQueryService;
import com.deppon.crm.module.gis.shared.domain.PointEntity;
import com.deppon.foss.framework.server.web.action.AbstractAction;

public class DeptAction extends AbstractAction{

	
	private List<String> standercodeList;
	private List<PointEntity> pointEntityList;
	private IDeptQueryService deptQueryService;
	

	/**
	 * 
	 * 查询营业部信息 
	 * @author 073102-foss-wuzhizhong
	 * @date 2013-4-20 下午06:09:36
	 * @return
	 * @see
	 */
	public String selectDeptByStanderCode() {
		pointEntityList = deptQueryService.deptQueryByStanderCode(standercodeList);
		return SUCCESS;

	}
	public IDeptQueryService getDeptQueryService() {
		return deptQueryService;
	}
	public void setDeptQueryService(IDeptQueryService deptQueryService) {
		this.deptQueryService = deptQueryService;
	}
	public List<String> getStandercodeList() {
		return standercodeList;
	}
	public void setStandercodeList(List<String> standercodeList) {
		this.standercodeList = standercodeList;
	}
	public List<PointEntity> getPointEntityList() {
		return pointEntityList;
	}
	public void setPointEntityList(List<PointEntity> pointEntityList) {
		this.pointEntityList = pointEntityList;
	}
	
}
