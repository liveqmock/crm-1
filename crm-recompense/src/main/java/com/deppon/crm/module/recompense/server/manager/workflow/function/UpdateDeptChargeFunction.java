package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;

/**
 * 
 * <p>
 * Description:在线理赔数据权限优化--更新入部门费用<br />
 * </p>
 * 
 * @title UpdateDeptChargeFunction.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.function
 * @author roy
 * @version 0.1 2012-12-10
 */


public class UpdateDeptChargeFunction implements FunctionProvider {

	private RecompenseService recompenseService;

	public RecompenseService getRecompenseService() {
		return recompenseService;
	}

	public void setRecompenseService(RecompenseService recompenseService) {
		this.recompenseService = recompenseService;
	}
	/**
	 * 
	 * <p>
	 * Description:更新在线理赔入部门费用Fucntion<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2012-12-10
	 * @param transientVars
	 * @param args
	 * @param ps
	 * void
	 */
	public void execute(Map transientVars, Map args, PropertySet ps) {
		//获得理赔实体
		RecompenseApplication recompense = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
		//获得入部门费用map
		Map<String, List<DeptCharge>> deptChargeMap = (Map<String, List<DeptCharge>>) transientVars
				.get(Constants.RECOMPENSE_DEPTCHARGE_MAP);
		//调用service更新
		recompenseService.updateOnlineDeptCharge(recompense, deptChargeMap);

	}

}
