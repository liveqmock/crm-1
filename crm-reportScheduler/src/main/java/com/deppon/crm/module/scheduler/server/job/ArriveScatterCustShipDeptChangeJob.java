package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.map.IMapOperate;
import com.deppon.crm.module.client.map.impl.MapOperateImpl;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.manager.impl.MemberManager;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.CustomerMarktingDept;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.impl.DepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * <p>
 * Description: 派送部纯到达散客：</br>
	派送部纯到达散客定义：</br>
（1）归属部门为派送部；</br>
（2）客户性质为到达客户；</br>
（3）客户类型为散客；</br>
1、	使用GIS匹配的客户信息中营销权限部门必须为空；</br>
2、	客户地址信息为空，则不根据地质匹配；</br>
3、	客户地质输入不标准，则不匹配，地址信息标准：同GIS标准地址信息；</br>
4、	使用GIS匹配客户地址信息，按照直线距离最近的规则匹配最近的出发部门，若匹配成功；</br>
则将客户的营销权限部门字段填充为匹配到的营业部；</br>
 * </p>
 * @title CustCreditBadInsertJob
 * @package com.deppon.crm.module.scheduler.server.job
 * @author pgj
 * @version 1.0
 * @date 2014-04-09
 *
 */
public class ArriveScatterCustShipDeptChangeJob extends GridJob{
	private IMemberManager memberManager;
	private IDepartmentService departmentService;
	private IMapOperate mapOperate;
	private IBaseDataManager baseDataManager;
	private static String marktingDeptId = "408217";
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		memberManager = getBean("memberManager", MemberManager.class);
		departmentService = getBean("departmentService", DepartmentService.class);
		Log logger = LogFactory.getLog(RecoverScatterCustMarktingDeptJob.class);
		logger.info("ArriveScatterCustShipDeptChangeJob_开始时间：" + new Date());
		//查询公司所有派送部
		List<Department> depts =  departmentService.getDepartmentListByLikeDeptName("派送部");
		if (CollectionUtils.isEmpty(depts)) {
			logger.info("Company has not Delivery Department：" + new Date());
			return;
		}
		//每个派送部分别处理
		for (int i = 0; i < depts.size(); i++) {
			//查询纯到达散客，只查询有地址的出到达散客
			List<MemberResult> members = memberManager.searchArrivalScatterCustByDept(depts.get(i).getId());
			if (CollectionUtils.isEmpty(members)) {
				logger.info("Delivery Department：" +depts.get(i).getDeptName()+"has not arrvieCustomer"+ new Date());
			}
			for (int j = 0; j < members.size(); j++) {
				String deptId = getDeptId(members.get(j).getAddress());
				if (StringUtils.isEmpty(deptId)) {
					deptId = marktingDeptId;
				}
				CustomerMarktingDept dept = new CustomerMarktingDept();
				dept.setCustId(members.get(j).getCustId());
				dept.setMarktingDept(deptId);
				memberManager.updateCustomerMarktingDept(dept);
			}
		}
		logger.info("ArriveScatterCustShipDeptChangeJob_结束时间：" + new Date());
	}
	
	/**
	 * 
	 * <p>根据地址，得到部门ID<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-5-29
	 * @param address
	 * @return
	 * String
	 */
	private String getDeptId(String address){
		Log logger = LogFactory.getLog(RecoverScatterCustMarktingDeptJob.class);
		mapOperate = getBean("mapOperate", MapOperateImpl.class);
		baseDataManager = getBean("baseDataManager", IBaseDataManager.class);
		try {
			String code = mapOperate.findDeptCodeByAddress(address);
			if(ValidateUtil.objectIsEmpty(code)){
				return null;
			}
			Department dept  = baseDataManager.getDeptByStandardCode(code);
			if(ValidateUtil.objectIsEmpty(dept)){
				return null;
			}
			return  dept.getId();
		} catch (CrmBusinessException e) {
			logger.info(e.getMessage());
		} catch(RuntimeException e){
			logger.info(e.getMessage());
		}
		return null;
	}
}