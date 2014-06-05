package com.deppon.crm.module.recompense.server.service;

import java.util.List;

import com.deppon.crm.module.recompense.shared.domain.Claim;
import com.deppon.crm.module.recompense.shared.domain.ClaimMessage;
import com.deppon.crm.module.recompense.shared.domain.ClaimOperationLog;
import com.deppon.crm.module.recompense.shared.domain.ClaimSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.Waybill;

/**
 * 
 * <p>
 * Description:理赔服务接口<br />
 * </p>
 * 
 * @title IClaimService.java
 * @package com.deppon.crm.module.recompense.server.service
 * @author roy
 * @version 0.1 2013-3-1
 */
public interface IClaimService {
	/**
	 * 
	 * <p>
	 * Description:保存索赔录入<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claim
	 * @return Claim
	 */
	Claim saveClaim(Claim claim);

	/**
	 * 
	 * <p>
	 * Description:根据id获取索赔信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param ClaimId
	 * @return Claim
	 */
	Claim getClaimById(String claimId);

	/**
	 * 
	 * <p>
	 * Description:更新索赔信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claim
	 * @return Claim
	 */
	Claim updateClaim(Claim claim);

	/**
	 * 
	 * <p>
	 * Description:根据条件查询索赔列表<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claimSearchCondition
	 * @return List<Claim>
	 */
	List<Claim> searchClaimsByCondition(
			ClaimSearchCondition claimSearchCondition);

	/**
	 * 
	 * <p>
	 * Description:根据条件统计索赔数<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claimSearchCondition
	 * @return Integer
	 */
	Integer countClaimsByCondition(ClaimSearchCondition claimSearchCondition);


	/**
	 * 
	 * <p>
	 * Description:根据凭证号查询操作日志<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param VoucherNo
	 * @return List<ClaimOperationLog>
	 */
	List<ClaimOperationLog> getOperationLogListByClaimId(String claimId);

	/**
	 * 
	 * <p>
	 * Description根据凭证号查询索赔跟进消息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param voucherNo
	 * @return List<ClaimMessage>
	 */
	List<ClaimMessage> getClaimMessageListByClaimId(String claimId);




	/**
	 * 
	 * <p>
	 * Description:增加操作日志<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claimOperationLog
	 * @return ClaimOperationLog
	 */
	ClaimOperationLog addOperationLog(ClaimOperationLog claimOperationLog);

	/**
	 * 
	 * <p>
	 * Description:增加跟进消息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claimMessage
	 * @return ClaimMessage
	 */
	ClaimMessage addFollowClaimMessage(ClaimMessage claimMessage);
	/**
	 * 
	 * <p>
	 * Description:删除索赔<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-5
	 * @param id
	 * void
	 */
	 void deleteClaimById(String id);
		/**
		 * 
		 * <p>
		 * Description:根据id获取索赔信息<br />
		 * </p>
		 * 
		 * @author roy
		 * @version 0.1 2013-3-4
		 * @param ClaimId
		 * @return Claim
		 */
		Claim getClaimByWaybillNumber(String num);


}
