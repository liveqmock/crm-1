package com.deppon.crm.module.keycustomer.server.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.keycustomer.server.manager.impl.KeyCustomerManager;
import com.deppon.crm.module.keycustomer.shared.domain.Constant;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomer;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerSearchCondition;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerWorkflowInfo;
import com.deppon.crm.module.keycustomer.shared.domain.KeyStatusVO;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * 
 * <p>
 * Description:大客户资格管理Action<br />
 * </p>
 * 
 * @title KeyCustomerAction.java
 * @package com.deppon.crm.module.keycustomer.server.action
 * @author 106138
 * @version 0.1 2014-3-4
 */
public class KeyCustomerAction extends AbstractAction {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 858884073049046091L;
	/**
	 * @fields serialVersionUID
	 */

	// 大客户managersd
	private KeyCustomerManager keyCustomerManager;
	private List<KeyCustomer> keycustomerList;
	private KeyCustomerSearchCondition condition;
	// 客户id
	private String custId;
	// 部门ID
	private String deptId;
	// 会员信息列表
	private List<MemberResult> memberResultList = new ArrayList<MemberResult>();
	private IAlterMemberManager alterMemberManager;// 弹出框查会员manager
	// 会员信息弹出框查询条件
	private MemberCondition searchCustCondition = new MemberCondition();
	private KeyCustomerWorkflowInfo info;
	private List<KeyCustomerWorkflowInfo> keycustWorkflowList;
	// 业务工作流号
	private String bizCode;
	//开始
	private int start;
	//限制
	private int limit;
	//总数
	private long totalCount;
	// 状态
	private KeyStatusVO keyStatusVO;

	/**
	 * 根据客户ID生成一个工作流数据到前台,不生成工作流
	 */
	@JSON
	public String createKeyCustomerWorkflowInfoByCustId() {
		info = keyCustomerManager.findCustomerInfo(custId);
		keyStatusVO = keyCustomerManager.checkKeyStatusVO(custId);
		return SUCCESS;
	}

	/**
	 * Description:状态校验<br />
	 */
	public String verifyKeyStatus() {
		keyStatusVO = keyCustomerManager.checkKeyStatusVO(custId);
		return SUCCESS;
	}

	/**
	 * Description:大客户审批管理界面大客户工作流列表查询<br />
	 */
	@JSON
	public String searchKeycustWorkflowList() {
		if (custId == null || "".equals(custId)) {
			keycustWorkflowList = keyCustomerManager
					.searchWorkflowList(condition);
			totalCount = keyCustomerManager.countSearchWorkflowList(condition);
		} else {
			keycustWorkflowList = keyCustomerManager.findWorkflowListByCustId(
					custId, start, limit);
			totalCount = keyCustomerManager
					.countFindWorkflowListByCustId(custId);
		}
		return SUCCESS;
	}

	/**
	 * Description:申请工作流<br />
	 */
	@JSON
	public String applyWorlflow() {
		bizCode = keyCustomerManager.processWorkFlow(info);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:F7 会员信息弹出框查询合同使用<br />
	 * </p>
	 * 
	 * @author 潘光均
	 * @version 0.2 2012-4-1
	 * @return String
	 */
	@JSON
	public String searchMemberInfoListWithAuth() {
		searchCustCondition.setLimit(-1);// -1标识不用分页
		// 添加数据权限
		if(StringUtil.isNotEmpty(searchCustCondition.getDeptId())){
			List<String> deptIds = new ArrayList<String>();
			deptIds.add(searchCustCondition.getDeptId());
			searchCustCondition.setDeptIds(deptIds);
		}
		memberResultList = alterMemberManager
				.searchMemberWithAuth(searchCustCondition);
		// 大客户审批管理客户查询到的客户必须为非大客户
		searchCustCondition.setFisKeyCustomer(Constant.NUMZERO);
		for (int i = 0; i < memberResultList.size(); i++) {
			// 会员编码
			String custNum = memberResultList.get(i).getCustNum();
			// 会员名称
			String custName = memberResultList.get(i).getCustName();
			// 会员等级 数据字典转换
			String custGrade = DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.MEMBER_GRADE, memberResultList.get(i)
							.getCustGrade());
			if (custGrade == null) {
				custGrade = "";
			}
			memberResultList.get(i).setCustGroup(
					getCustGroup(custNum, custName, custGrade));
		}
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:会员管理界面的custGrade生成<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-8
	 * @param custNum
	 * @param custName
	 * @param custGrade
	 * @return String
	 */
	private String getCustGroup(String custNum, String custName,
			String custGrade) {
		String custNumView = "客户编码:" + custNum;
		String custGradeView = "客户等级:" + getNbsp(custGrade, 15);
		String custNameView = "客户名称:" + custName;
		return custNumView + "," + custGradeView + "," + custNameView;
	}

	private String getNbsp(String name, int length) {
		return StringUtils.rightPad(name, length, ' ')
				.replaceAll(" ", "&nbsp;");
	}

	public String checkIsKeyCustomerInApprove() {
		info = keyCustomerManager.findCustomerInfo(custId);
		return SUCCESS;

	}

	/**
	 * <p>
	 * Description:根据条件查询大客户<br />
	 * </p>
	 * 
	 * @param condition
	 * @return keycustomerList
	 */
	@JSON
	public String searchKeycustList() {
		keycustomerList = keyCustomerManager.listKeyCustomerList(condition);
		totalCount = keyCustomerManager.countListKeyCustomerList(condition);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户id对大客户进行退出处理<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 *            void
	 */
	@JSON
	public String exitHadlle() {
		keyCustomerManager.exitHadlle(custId, deptId);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public KeyCustomerWorkflowInfo getInfo() {
		return info;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public void setInfo(KeyCustomerWorkflowInfo info) {
		this.info = info;
	}/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */

	public void setKeyCustomerManager(KeyCustomerManager keyCustomerManager) {
		this.keyCustomerManager = keyCustomerManager;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public List<KeyCustomer> getKeycustomerList() {
		return keycustomerList;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public void setKeycustomerList(List<KeyCustomer> keycustomerList) {
		this.keycustomerList = keycustomerList;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public KeyCustomerSearchCondition getCondition() {
		return condition;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public void setCondition(KeyCustomerSearchCondition condition) {
		this.condition = condition;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public List<KeyCustomerWorkflowInfo> getKeycustWorkflowList() {
		return keycustWorkflowList;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public void setKeycustWorkflowList(
			List<KeyCustomerWorkflowInfo> keycustWorkflowList) {
		this.keycustWorkflowList = keycustWorkflowList;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public String getBizCode() {
		return bizCode;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public KeyStatusVO getKeyStatusVO() {
		return keyStatusVO;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public void setKeyStatusVO(KeyStatusVO keyStatusVO) {
		this.keyStatusVO = keyStatusVO;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public Long getTotalCount() {
		return totalCount;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public List<MemberResult> getMemberResultList() {
		return memberResultList;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public void setMemberResultList(List<MemberResult> memberResultList) {
		this.memberResultList = memberResultList;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public MemberCondition getSearchCustCondition() {
		return searchCustCondition;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * String
	 */
	public void setSearchCustCondition(MemberCondition searchCustCondition) {
		this.searchCustCondition = searchCustCondition;
	}

}
