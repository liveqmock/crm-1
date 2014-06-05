package com.deppon.crm.module.recompense.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.recompense.server.dao.IClaimDao;
import com.deppon.crm.module.recompense.shared.domain.Claim;
import com.deppon.crm.module.recompense.shared.domain.ClaimMessage;
import com.deppon.crm.module.recompense.shared.domain.ClaimOperationLog;
import com.deppon.crm.module.recompense.shared.domain.ClaimSearchCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * <p>
 * Description:索赔Dao<br />
 * </p>
 * 
 * @title ClaimDao.java
 * @package com.deppon.crm.module.recompense.server.dao.impl
 * @author roy
 * @version 0.1 2013-3-1
 */
public class ClaimDao extends iBatis3DaoImpl implements IClaimDao {
	private static final String NAMESPACE = "com.deppon.crm.module.recompense.shared.domain.Claim.";

	public static final String INSERT_CLAIM = "insertClaim";
	public static final String UPDATE_CLAIM = "updateClaim";
	public static final String DELETE_CLAIM_BY_ID = "deleteClaimById";
	public static final String GET_CLAIM_BY_ID = "getClaimById";
	public static final String COUNT_CLAIM_BY_CONDITION = "countClaimByCondition";
	public static final String SEARCH_CLAIM_BY_CONDITION = "searchClaimByCondition";
	public static final String INSERT_OPERATIONLOG = "insertOperationLog";
	public static final String INSERT_CLAIMMESSAGE = "insertClaimMessage";
	public static final String SEARCH_CLAIMOPERATIONLOG_BY_CLAIMID = "searchClaimOperationLogByClaimId";
	public static final String SEARCH_CLAIMMESSAGE_BY_CLAIMID = "searchClaimMessageByClaimId";
	public static final String GET_CLAIM_BY_WAYBILLNUMBER = "getClaimByWaybillNumber";

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
		if (null != claim) {
			this.getSqlSession().insert(NAMESPACE + INSERT_CLAIM, claim);
			return claim;
		}
		return null;
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
		if (null != claimId && !"".equals(claimId)) {
			return (Claim) this.getSqlSession().selectOne(
					NAMESPACE + GET_CLAIM_BY_ID, claimId);
		}
		return null;
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
	public Claim getClaimByWaybillNumber(String num) {
		if (null == num) {
			return null;
		}
		return (Claim) this.getSqlSession().selectOne(
				NAMESPACE + GET_CLAIM_BY_WAYBILLNUMBER, num);

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
		if (null != claim) {
			getSqlSession().update(NAMESPACE + UPDATE_CLAIM, claim);
			return claim;
		}
		return null;
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
		RowBounds bounds = new RowBounds(claimSearchCondition.getStart(),
				claimSearchCondition.getLimit());
		List<Claim> list = (List<Claim>) getSqlSession().selectList(
				NAMESPACE + SEARCH_CLAIM_BY_CONDITION, claimSearchCondition,
				bounds);

		return list;
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
		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE + COUNT_CLAIM_BY_CONDITION, claimSearchCondition);
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
		if (null != claimId) {
			List<ClaimOperationLog> list = (List<ClaimOperationLog>) getSqlSession()
					.selectList(
							NAMESPACE + SEARCH_CLAIMOPERATIONLOG_BY_CLAIMID,
							claimId);

			return list;
		}
		return null;
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
		if (null != claimId) {
			List<ClaimMessage> list = (List<ClaimMessage>) getSqlSession()
					.selectList(NAMESPACE + SEARCH_CLAIMMESSAGE_BY_CLAIMID,
							claimId);

			return list;
		}
		return null;
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
		if (null != claimOperationLog) {
			this.getSqlSession().insert(NAMESPACE + INSERT_OPERATIONLOG,
					claimOperationLog);
			return claimOperationLog;
		}
		return null;
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
		if (null != claimMessage) {
			this.getSqlSession().insert(NAMESPACE + INSERT_CLAIMMESSAGE,
					claimMessage);
			return claimMessage;
		}
		return null;
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

		this.getSqlSession().delete(NAMESPACE + DELETE_CLAIM_BY_ID, id);

	}
}
