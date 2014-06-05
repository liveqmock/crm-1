package com.deppon.crm.module.recompense.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.shared.domain.Claim;
import com.deppon.crm.module.recompense.shared.domain.ClaimMessage;
import com.deppon.crm.module.recompense.shared.domain.ClaimOperationLog;
import com.deppon.crm.module.recompense.shared.domain.ClaimSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.Waybill;

/**
 * 
 * <p>
 * Description:索赔管理接口<br />
 * </p>
 * @title IClaimManager.java
 * @package com.deppon.crm.module.recompense.server.manager 
 * @author roy
 * @version 0.1 2013-3-1
 */
public interface IClaimManager {
	
	Claim getWayBillAndType(String voucherNo,String recompenseType,User user);
	/**
	 * 
	 * <p>
	 * Description:保存索赔录入<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claim
	 * @return
	 * Claim
	 */
	Claim saveClaim(Claim claim,User user);
	/**
	 * 
	 * <p>
	 * Description:根据id获取<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-6
	 * @param claimId
	 * @return
	 * Claim
	 */
	Claim getClaimById(String claimId);
	/**
	 * 
	 * <p>
	 * Description:根据条件查询索赔列表<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claimSearchCondition
	 * @return
	 * List<Claim>
	 */
	Map<String,Object> searchClaimsByCondition(ClaimSearchCondition claimSearchCondition,User user);

	/**
	 * 
	 * <p>
	 * Description:根据凭证号查询操作日志<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param VoucherNo
	 * @return
	 * List<ClaimOperationLog>
	 */
	List<ClaimOperationLog> getOperationLogListByClaimId(String claimId);
	/**
	 * 
	 * <p>
	 * Description根据凭证号查询索赔跟进消息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param voucherNo
	 * @return
	 * List<ClaimMessage>
	 */
	List<ClaimMessage> getClaimMessageListByClaimId(String claimId);
	/**
	 * 
	 * <p>
	 * Description:发送到对方部门<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claim
	 * void
	 */
	void sendToAnotherDept(String claimId,String reason,User user);
	/**
	 * 
	 * <p>
	 * Description:根据id进行免赔操作<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claimId
	 * void
	 */
	void remitClaimByClaimId(String claimId,User user);
	/**
	 * 
	 * <p>
	 * Description:增加操作日志<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claimOperationLog
	 * @return
	 * ClaimOperationLog
	 */
	ClaimOperationLog addOperationLog(String content,String claimId,User user);
	/**
	 * 
	 * <p>
	 * Description:增加跟进消息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claimMessage
	 * @return
	 * ClaimMessage
	 */
	ClaimMessage addFollowClaimMessage(ClaimMessage claimMessage,User user);
	/**
	 * 
	 * <p>
	 * Description:免赔解冻<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claimId
	 * void
	 * @return 
	 */
	Claim cancelRemitClaim(String claimId,User user);
	/**
	 * 
	 * <p>
	 * Description:同意受理<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-7
	 * @param claimId
	 * @param user
	 * void
	 * @return 
	 */
	Claim acceptClaim(Claim claim,User user);
	
	
	
}
