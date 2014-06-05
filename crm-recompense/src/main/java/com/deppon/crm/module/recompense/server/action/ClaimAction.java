package com.deppon.crm.module.recompense.server.action;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.recompense.server.manager.IClaimManager;
import com.deppon.crm.module.recompense.shared.domain.Claim;
import com.deppon.crm.module.recompense.shared.domain.ClaimMessage;
import com.deppon.crm.module.recompense.shared.domain.ClaimOperationLog;
import com.deppon.crm.module.recompense.shared.domain.ClaimSearchCondition;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 
 * <p>
 * Description:索赔Action<br />
 * </p>
 * 
 * @title ClaimAction.java
 * @package com.deppon.crm.module.recompense.server.action
 * @author roy
 * @version 0.1 2013-3-1
 */
public class ClaimAction extends AbstractAction {

	private Long totalCount;// 索赔总数
	private int limit;
	private int start;
	private String claimId;
	private List<Claim> claimList;// 索赔列表
	private Claim claim;
	private ClaimSearchCondition claimCondition;

	// ****set*****/
	private String voucherNo;// 凭证号
	private String recompenseMethod;// 理赔类型

	/**
	 * <p>
	 * Description:recompenseMethod<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-11
	 */
	public void setRecompenseMethod(String recompenseMethod) {
		this.recompenseMethod = recompenseMethod;
	}

	/**
	 * @param voucherNo
	 *            : set the property voucherNo.
	 */
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	/**
	 * @param limit
	 *            : set the property limit.
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @param start
	 *            : set the property start.
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @param claimId
	 *            : set the property claimId.
	 */
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	private String reason;// 发送原因

	/**
	 * @param reason
	 *            : set the property reason.
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	private ClaimMessage claimMessage;// 跟进消息

	/**
	 * @param claimMessage
	 *            : set the property claimMessage.
	 */
	public void setClaimMessage(ClaimMessage claimMessage) {
		this.claimMessage = claimMessage;
	}

	// **get *****/
	/**
	 * @return totalCount;
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * @return claimList;
	 */
	public List<Claim> getClaimList() {
		return claimList;
	}

	// manager
	private IClaimManager claimManager;

	/**
	 * @return claimManager;
	 */
	public void setClaimManager(IClaimManager claimManager) {
		this.claimManager = claimManager;
	}

	// 操作日志
	List<ClaimOperationLog> claimOperationLogList;

	/**
	 * @return claimOperationLogList;
	 */
	public List<ClaimOperationLog> getClaimOperationLogList() {
		return claimOperationLogList;
	}

	// 跟进信息
	List<ClaimMessage> claimMessageList;

	/**
	 * @return claimMessageList;
	 */
	public List<ClaimMessage> getClaimMessageList() {
		return claimMessageList;
	}

	// //*****get set ****/
	/**
	 * @return claim;
	 */
	public Claim getClaim() {
		return claim;
	}

	/**
	 * @param claim
	 *            : set the property claim.
	 */
	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	/**
	 * @param claimCondition
	 *            : set the property claimCondition.
	 */
	public void setClaimCondition(ClaimSearchCondition claimCondition) {
		this.claimCondition = claimCondition;
	}

	/**
	 * @return claimCondition;
	 */
	public ClaimSearchCondition getClaimCondition() {
		return claimCondition;
	}

	/**
	 * 
	 * <p>
	 * Description:保存索赔<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-3-4下午3:15:03
	 * @return String
	 * @update 2013-3-4下午3:15:03
	 */
	@JSON
	public String saveClaim() {
		User user = (User) UserContext.getCurrentUser();
		claimManager.saveClaim(claim, user);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:根据条件查询索赔列表<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-3-4下午2:35:52
	 * @return String
	 * @update 2013-3-4下午2:35:52
	 */
	@JSON
	public String searchClaimByCondition() {
		claimCondition.setStart(start);
		claimCondition.setLimit(limit);
		User user = (User) UserContext.getCurrentUser();
		Map<String,Object> map = claimManager.searchClaimsByCondition(claimCondition, user);
		claimList = (List<Claim>) map.get("claimList");
		totalCount=Long.valueOf(map.get("count").toString());
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:根据索赔ID查询索赔信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-3-4下午3:06:57
	 * @return String
	 * @update 2013-3-4下午3:06:57
	 */
	@JSON
	public String searchClaimById() {
		claim = claimManager.getClaimById(claimId);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:获取运单信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-3-8下午3:50:12
	 * @return String
	 * @update 2013-3-8下午3:50:12
	 */
	@JSON
	public String searchWayBillAndType() {
		User user = (User) UserContext.getCurrentUser();
		claim = claimManager.getWayBillAndType(voucherNo, recompenseMethod,
				user);
		return SUCCESS;
	}

	public String remitClaimByClaimId() {
		User user = (User) UserContext.getCurrentUser();
		claimManager.remitClaimByClaimId(claimId, user);
		return SUCCESS;
	}

	public String cancelRemitClaim() {
		User user = (User) UserContext.getCurrentUser();
		claimManager.cancelRemitClaim(claimId, user);
		return SUCCESS;
	}

	public String searchOperationLogListByClaimId() {
		claimOperationLogList = claimManager
				.getOperationLogListByClaimId(claimId);
		return SUCCESS;
	}

	public String searchClaimMessageListByClaimId() {
		claimMessageList = claimManager.getClaimMessageListByClaimId(claimId);
		return SUCCESS;
	}

	public String sendToAnotherDept() {
		User user = (User) UserContext.getCurrentUser();
		claimManager.sendToAnotherDept(claimId, reason, user);
		return SUCCESS;
	}

	public String addFollowClaimMessage() {
		User user = (User) UserContext.getCurrentUser();
		claimManager.addFollowClaimMessage(claimMessage, user);
		return SUCCESS;
	}

	public String acceptClaim() {
		User user = (User) UserContext.getCurrentUser();
		claim=claimManager.acceptClaim(claim, user);
		return SUCCESS;
	}

}
