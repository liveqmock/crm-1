package com.deppon.crm.module.interfaces.uums;

import com.deppon.crm.module.interfaces.uums.jms.SendAdminOrgRequest;
import com.deppon.crm.module.interfaces.uums.jms.SendAdminOrgResponse;
import com.deppon.crm.module.interfaces.uums.jms.SendEmployeeRequest;
import com.deppon.crm.module.interfaces.uums.jms.SendEmployeeResponse;
import com.deppon.crm.module.interfaces.uums.jms.SendPositionRequest;
import com.deppon.crm.module.interfaces.uums.jms.SendPositionResponse;
import com.deppon.crm.module.interfaces.uums.jms.SendUserInfoDeptAllRequest;
import com.deppon.crm.module.interfaces.uums.jms.SendUserInfoDeptAllResponse;
import com.deppon.crm.module.interfaces.uums.ws.CommException;
/**   
 * @Description:同步UUMS 职位信息，用户信息，职员信息,行政组织<br />
 * @title IUumsSyncService.java
 * @package com.deppon.crm.module.interfaces.uums 
 * @author CoCo
 * @version 0.1 2013-11-25
 */
public interface UumsToCrmSyncService {

	/**
	 * @Description:同步职位信息<br />
	 * @author CoCo
	 * @version 0.1 2013-11-25
	 * @param sendPositionRequest
	 * @return
	 * SendPositionResponse
	 */
	public SendPositionResponse syncPositionInfo(SendPositionRequest sendPositionRequest) throws CommException;
	
	
	/**
	 * @Description:同步职员信息<br />
	 * @author CoCo
	 * @version 0.1 2013-11-25
	 * @param sendEmployeeRequest
	 * @return sendEmployeeResponse
	 */
	public SendEmployeeResponse syncEmployeeInfo(SendEmployeeRequest sendEmployeeRequest) throws CommException;

	/**
	 * @Description:同步用户信息<br />
	 * @author CoCo
	 * @version 0.1 2013-11-25
	 * @param sendUserInfoDeptAllRequest
	 * @return sendUserInfoDeptAllResponse
	 */
	public SendUserInfoDeptAllResponse syncUserInfoDeptAll(SendUserInfoDeptAllRequest sendUserInfoDeptAllRequest) throws CommException;

	
	
	/**
	 * @Description:同步行政组织信息<br />
	 * @author CoCo
	 * @version 0.1 2013-11-25
	 * @param sendAdminOrgRequest
	 * @return SendPositionResponse
	 */
	public SendAdminOrgResponse syncAdminOrgInfo(SendAdminOrgRequest sendAdminOrgRequest) throws CommException;

}
