package com.deppon.crm.module.recompense.server.service.impl;

import java.util.List;

import com.deppon.crm.module.recompense.server.dao.IClaimDao;
import com.deppon.crm.module.recompense.server.service.IClaimService;
import com.deppon.crm.module.recompense.shared.domain.Claim;
import com.deppon.crm.module.recompense.shared.domain.ClaimMessage;
import com.deppon.crm.module.recompense.shared.domain.ClaimOperationLog;
import com.deppon.crm.module.recompense.shared.domain.ClaimSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.Waybill;

/**
 * 
 * <p>
 * Description:理赔服务类<br />
 * </p>
 * 
 * @title ClaimService.java
 * @package com.deppon.crm.module.recompense.server.service.impl
 * @author roy
 * @version 0.1 2013-3-1
 */
public class ClaimService implements IClaimService {
	// 索赔Dao
	private IClaimDao claimDao;

	/**
	 * <p>
	 * Description:claimDao<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public IClaimDao getClaimDao() {
		return claimDao;
	}

	/**
	 * <p>
	 * Description:claimDao<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public void setClaimDao(IClaimDao claimDao) {
		this.claimDao = claimDao;
	}

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
	public Claim saveClaim(Claim claim) {
		return claimDao.saveClaim(claim);
	}

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
	public Claim getClaimById(String claimId) {
		return claimDao.getClaimById(claimId);
	}

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
	public Claim updateClaim(Claim claim) {
		return claimDao.updateClaim(claim);
	}

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
	public List<Claim> searchClaimsByCondition(
			ClaimSearchCondition claimSearchCondition) {
		return claimDao.searchClaimsByCondition(claimSearchCondition);
	}

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
	public Integer countClaimsByCondition(
			ClaimSearchCondition claimSearchCondition) {
		return claimDao.countClaimsByCondition(claimSearchCondition);
	}

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
	public List<ClaimOperationLog> getOperationLogListByClaimId(String claimId) {
		return claimDao.getOperationLogListByClaimId(claimId);
	}

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
	public List<ClaimMessage> getClaimMessageListByClaimId(String claimId) {
		return claimDao.getClaimMessageListByClaimId(claimId);
	}

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
	public ClaimOperationLog addOperationLog(ClaimOperationLog claimOperationLog) {
		return claimDao.addOperationLog(claimOperationLog);
	}

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
	public ClaimMessage addFollowClaimMessage(ClaimMessage claimMessage) {
		return claimDao.addFollowClaimMessage(claimMessage);
	}

	/**
	 * 
	 * <p>
	 * Description:删除索赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5
	 * @param id
	 *            void
	 */
	public void deleteClaimById(String id) {
		claimDao.deleteClaimById(id);
	}
	/**
	 * 
	 * <p>
	 * Description:根据运单号查询理赔<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-5
	 * @param num
	 * @return
	 *
	 */
	@Override
	public Claim getClaimByWaybillNumber(String num) {
		return claimDao.getClaimByWaybillNumber(num);
	}

}
