package com.deppon.crm.module.marketing.bpsworkflow.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpmsapi.module.client.util.BPMSConstant;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;

public class BpsUtils {
	
	private static Logger log = Logger.getLogger(BpsUtils.class);

	/**
	 *	Description: 获取上级部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-6
	 * @param department
	 * @return
	 * BpmsParticipant[]
	 */
	public static BpmsParticipant[] queryParentBpmsParticipant(Department department) {
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		if (department != null) {
			BpmsParticipant bp = new BpmsParticipant();
			WebApplicationContext wac = WebApplicationContextHolder
					.getWebApplicationContext();
			IDepartmentService departmentService = (IDepartmentService) wac
					.getBean("departmentService");
			Department parentDept=departmentService.getDepartmentById(department.getParentCode().getId());
			bp.setId(parentDept.getStandardCode());
			bp.setName(parentDept.getDeptName());
			bp.setTypeCode(BPMSConstant.PART_MANAGER);
			list.add(bp);
			log.error("------------------------------：下一节点审批（部门标杆编码）："
					+ parentDept.getStandardCode());
			return list.toArray(new BpmsParticipant[list.size()]);
		}
		return null;
	}
	/**
	 * <p>
	 *	Description: 获取当前部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-6
	 * @param department
	 * @return
	 * BpmsParticipant[]
	 */
	public static BpmsParticipant[] queryBpmsParticipant(Department department) {
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		if (department != null) {
			BpmsParticipant bp = new BpmsParticipant();
			bp.setId(department.getStandardCode());
			bp.setName(department.getDeptName());
			bp.setTypeCode(BPMSConstant.PART_MANAGER);
			list.add(bp);
			log.error("------------------------------：下一节点审批（部门标杆编码）："
					+ department.getStandardCode());
			return list.toArray(new BpmsParticipant[list.size()]);
		}
		return null;
	}
	/**
	 * 
	 * @Title: queryBusiBpmsParticipant 
	 * @Description: 获取事业部编码
	 * @author LiuY 
	 * @param department
	 * @return BpmsParticipant[]
	 * @throws
	 */
	public static BpmsParticipant[] queryBusiBpmsParticipant(Department department,String perContant) {
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		if (department != null) {
			BpmsParticipant bp = new BpmsParticipant();
			//事业部营销管理组负责人
			if(BpsConstant.MARKETMANAGER.equals(perContant)){
				perContant = BpsConstant.WFS_DIVISION_MARKET_MANAGER;
			}
			//事业部办公室主任
			else if(BpsConstant.DIVISIONOFFICE.equals(perContant)){
				perContant = BpsConstant.AREAUB_MANAGER_BY_DIVISION;
			}
			//营运办公室主任
			else if(BpsConstant.MARKETOFFICE.equals(perContant)){
				perContant = BpsConstant.WFS_MARKET_OFFICE;
			}
			//事业部后勤管理组负责人
			else if(BpsConstant.MARKETLMS.equals(perContant)){
				perContant = BpsConstant.WFS_MARKET_LMS_MANAGER;
			}
			//事业部后勤管理组（采购工程师）
			else if(BpsConstant.MARKETLMSSTAFF.equals(perContant)){
				perContant = BpsConstant.WFS_MARKET_LMS_STAFF;
			}
			//事业部仓管组
			else if(BpsConstant.WAREHOUSE.equals(perContant)){
				perContant = BpsConstant.WAREHOUSE_BY_DIVISION;
			}
			bp.setId(department.getStandardCode()+"#"+perContant);
			bp.setName(department.getDeptName());
			bp.setTypeCode(BPMSConstant.PART_BY_MAPPING);
			list.add(bp);
			log.error("------------------------------：下一节点审批（部门标杆编码）："
					+ department.getStandardCode());
			return list.toArray(new BpmsParticipant[list.size()]);
		}
		return null;
	}
	
}
