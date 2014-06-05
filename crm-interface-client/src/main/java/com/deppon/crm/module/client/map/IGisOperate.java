package com.deppon.crm.module.client.map;

import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.map.domain.CoordinateDetail;
import com.deppon.esb.esbtogis.QueryDeptCoordinateRequest;

public interface IGisOperate {

	/**
	 * @description 查询部门坐标接口
	 * @author wugenbin_吴根斌
	 * @version 2013-4-28
	 * @param 部门标杆编码
	 * @return 坐标信息
	 */
	public List<CoordinateDetail> queryCoordinate(List<String> deptCode) throws CrmBusinessException;
}
