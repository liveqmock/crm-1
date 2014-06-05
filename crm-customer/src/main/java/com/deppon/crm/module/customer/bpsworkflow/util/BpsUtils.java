package com.deppon.crm.module.customer.bpsworkflow.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpmsapi.module.client.util.BPMSConstant;
import com.deppon.crm.module.client.workflow.domain.ContractApplyType;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;

/**
 * 
 * <p>
 * Description:Bps工具类<br />
 * </p>
 * 
 * @title BpsUtils.java
 * @package com.deppon.crm.module.customer.bpsworkflow.util
 * @author royxhl
 * @version 0.1 2013-11-26
 */
public class BpsUtils {
	private static Logger log = Logger.getLogger(BpsUtils.class);

	/**
	 * 废弃
	 * <p>
	 * Description:把工作流类型转换为对应的流程实例<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-26
	 * @param type
	 * @return String
	 */
	public static String convertContractWorkFowType(String type) {
		/**
		 * 零担新签
		 */
		if (ContractApplyType.NEW.equals(type)) {
			return BpsConstant.LTT_NEW_PROCESSNAME;
			/**
			 * 快递新签
			 */
		} else if (ContractApplyType.EX_NEW.equals(type)) {
			return BpsConstant.EX_NEW_PROCESSNAME;
			/**
			 * 零担修改
			 */
		} else if (ContractApplyType.UPDATE.equals(type)) {
			return BpsConstant.LTT_UPDATE_PROCESSNAME;
			/**
			 * 快递修改
			 */
		} else if (ContractApplyType.EX_UPDATE.equals(type)) {
			return BpsConstant.EX_UPDATE_PROCESSNAME;
			/**
			 * 合同终止
			 */
		}else if (ContractApplyType.CANCEL.equals(type)||ContractApplyType.EX_CANCEL.equals(type)) {
			return BpsConstant.CANCEL_PROCESSNAME;

		} else {
			return "";
		}
	}

	/**
	 * 
	 * <p>
	 * Description:把工作流类型转换为对应的流程实例<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-26
	 * @param type
	 * @return String
	 */
	public static String convertSearchContractWorkFowType(String type) {
		/**
		 * 零担新签
		 */
		if (BpsConstant.LTT_NEW_SERACH.equals(type)) {
			return BpsConstant.LTT_NEW_PROCESSNAME;
			/**
			 * 快递新签
			 */
		} else if (BpsConstant.EX_NEW_SERACH.equals(type)) {
			return BpsConstant.EX_NEW_PROCESSNAME;
			/**
			 * 零担修改
			 */
		} else if (BpsConstant.LTT_UPDATE_SERACH.equals(type)) {
			return BpsConstant.LTT_UPDATE_PROCESSNAME;
			/**
			 * 快递修改
			 */
		} else if (BpsConstant.EX_UPDATE_SERACHE.equals(type)) {
			return BpsConstant.EX_UPDATE_PROCESSNAME;
			/**
			 * 合同终止
			 */
		} else if (BpsConstant.CANCEL_SEARCH.equals(type)) {
			return BpsConstant.CANCEL_PROCESSNAME;

		}else {
			return "";
		}
	}
	
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
	 *	Description: TODO
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

}
