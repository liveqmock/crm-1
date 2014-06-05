package com.deppon.crm.module.bpsworkflow.server.util;


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
	public static String convertWorkFowType(String type) {
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
	public static String convertSearchWorkFowType(String type) {
		/**
		 * 零担新签
		 */
		if (BpsConstant.CONTRACT_LTT_NEW_SERACH.equals(type)) {
			return BpsConstant.LTT_NEW_PROCESSNAME;
			/**
			 * 快递新签
			 */
		} else if (BpsConstant.CONTRACT_EX_NEW_SERACH.equals(type)) {
			return BpsConstant.EX_NEW_PROCESSNAME;
			/**
			 * 零担修改
			 */
		} else if (BpsConstant.CONTRACT_LTT_UPDATE_SERACH.equals(type)) {
			return BpsConstant.LTT_UPDATE_PROCESSNAME;
			/**
			 * 快递修改
			 */
		} else if (BpsConstant.CONTRACT_EX_UPDATE_SERACHE.equals(type)) {
			return BpsConstant.EX_UPDATE_PROCESSNAME;
			/**
			 * 合同终止
			 */
		} else if (BpsConstant.CONTRACT_CANCEL_SEARCH.equals(type)) {
			return BpsConstant.CANCEL_PROCESSNAME;
			/**
			 * 常规理赔
			 */
		}else if(BpsConstant.RECOMPENSE_APPLY_SEARCH.equals(type)){
			return BpsConstant.RECOMPENSE_PROCESS_DEFINITION_NAME;
			/**
			 * 服务补救
			 */
		}else if(BpsConstant.RECOVERY_APPLY_SEARCH.equals(type)){
			return BpsConstant.SERVICE_PROCESS_DEFINITION_NAME;
			/**
			 * 多赔
			 */
		}else if(BpsConstant.OVERPAY_APPLY_SEARCH.equals(type)){
			return BpsConstant.OVERPAY_PROCESS_DEFINITION_NAME;
			/**
			 * 大客户准入
			 */
		}else if(BpsConstant.KEYCUST_APPLY_SEARCH.equals(type)){
			return BpsConstant.KEYCUST_PROCESSNAME;
			/**
			 * 疑似重复客户处理
			 */
		}else if(BpsConstant.CUSTREPEAT_PROCESS_SEARCH.equals(type)){
			return BpsConstant.CUSTREPEAT_PROCESSNAME;
			/**
			 * 区域营销活动
			 */
		}else if(BpsConstant.AREA_MARKETING_SEARCH.equals(type)){
			return BpsConstant.MARKETING_PROCESSNAME;
		}else {
			return "";
		}
	}
	/**
	 * <p>
	 *	Description: 查询参与者规则
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-6
	 * @param department
	 * @return
	 * BpmsParticipant[]
	 */
	public static BpmsParticipant[] queryBpmsParticipant(Department department) {
		//定义一个新的参与者规则list
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		//判断部门是否为空
		if (department != null) {
			//new一个参与者对象
			BpmsParticipant bp = new BpmsParticipant();
			//设置部门标杆编码
			bp.setId(department.getStandardCode());
			//设置部门名称
			bp.setName(department.getDeptName());
			//设置方式为查询部门负责人
			bp.setTypeCode(BPMSConstant.PART_MANAGER);
			//将参与者对象添加到参与者List中
			list.add(bp);
			//输出下一个节点的部门标杆编码
			log.error("------------------------------：下一节点审批（部门标杆编码）："
					+ department.getStandardCode());
			//返回参与者规则数组
			return list.toArray(new BpmsParticipant[list.size()]);
		}
		return null;
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
		//定义一个新的参与者规则list
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		//判断部门是否为空
		if (department != null) {
			//new一个参与者对象
			BpmsParticipant bp = new BpmsParticipant();
			//创建WebApplicationContext
			WebApplicationContext wac = WebApplicationContextHolder
					.getWebApplicationContext();
			//获取departmentService对象
			IDepartmentService departmentService = (IDepartmentService) wac
					.getBean("departmentService");
			//查询出下一审批节点的部门
			Department parentDept=departmentService.getDepartmentById(department.getParentCode().getId());
			//设置部门标杆编码
			bp.setId(parentDept.getStandardCode());
			//设置部门名称
			bp.setName(parentDept.getDeptName());
			//设置方式为查询部门负责人
			bp.setTypeCode(BPMSConstant.PART_MANAGER);
			//将参与者对象添加到参与者List中
			list.add(bp);
			//输出下一个节点的部门标杆编码
			log.error("------------------------------：下一节点审批（部门标杆编码）："
					+ parentDept.getStandardCode());
			//返回参与者规则数组
			return list.toArray(new BpmsParticipant[list.size()]);
		}
		return null;
	}
}
