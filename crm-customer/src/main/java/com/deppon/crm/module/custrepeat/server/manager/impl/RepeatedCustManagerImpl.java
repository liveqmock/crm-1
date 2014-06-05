package com.deppon.crm.module.custrepeat.server.manager.impl;

/***********************************************************************
 * Module:  RepeatedCustManagerImpl.java
 * Author:  120750
 * Purpose: Defines the Class RepeatedCustManagerImpl
 ***********************************************************************/

import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.customer.bpsworkflow.util.BpsConstant;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.server.service.IContractService;
import com.deppon.crm.module.customer.server.service.IMemberService;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.custrepeat.server.service.IRepeatedCustService;
import com.deppon.crm.module.custrepeat.shared.domain.CustRepeatConstants;
import com.deppon.crm.module.custrepeat.shared.domain.CustRepeatValidator;
import com.deppon.crm.module.custrepeat.shared.domain.RepeatedCustomer;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.custrepeat.shared.domain.SearchCondition;
import com.deppon.crm.module.custrepeat.shared.exception.CustRepeatException;
import com.deppon.crm.module.custrepeat.shared.exception.CustRepeatExceptionType;
import com.deppon.crm.module.keycustomer.shared.domain.Constant;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

/** @pdOid f561cacb-3528-4dfe-8a3e-798fb02a63a0 */
@Transactional
public class RepeatedCustManagerImpl implements IRepeatedCustManager {
	/**
	 * 疑似重复service
	 */
	private IRepeatedCustService repeatedCustService;
	/**
	 * 部门service
	 */
	private IDepartmentService departmentService;
	/**
	 * 合同service
	 */
	public IContractService contractService;
	/**
	 * 工作流manager
	 */
	private IBpsWorkflowManager bpsWorkflowManager;
	/**
	 * 修改客户功能service
	 */
	private IAlterMemberService alterMemberService;
	/**
	 * 客户service
	 */
	private IMemberService memberService;
	
	/**
	 * @param user 当前登录人员
	 * @param searchCondition
	 * @pdOid 6c44e0a8-bdce-4e62-9942-0b0a5dc5308e
	 */
	@Override
	public Map<String,Object> searchMainCustomerMap(SearchCondition searchCondition) {
		if(null == searchCondition){ return null; }
		//获取当前用户
		User user = (User) (UserContext.getCurrentUser());
		//验证部门编号
		CustRepeatValidator.validDeptId(searchCondition);
		//根据部门编号查询部门信息
		Department dept = departmentService.getDepartmentById(searchCondition.getDeptId());
		//验证所选部门合法
		CustRepeatValidator.validDepartment(user,dept);
		//设置部门名称
		searchCondition.setDeptName(dept.getDeptName());
		//主客户
		searchCondition.setIsMainCust(1);
		//设置客户等级
		searchCondition.setDeptLevel(CustRepeatValidator.getDeptLevel(dept));
		Map<String,Object> map = new HashMap<String, Object>();
		//将查询出来的疑似重复list 赋值到map 返回至前台显示
		map.put("repeatCustList", repeatedCustService.searchRepeatCustList(searchCondition));
		//疑似重复个数
		map.put("totalCount", repeatedCustService.searchRepeatCustCount(searchCondition));
		return map;
	}

	/**
	 * 查询疑似客户集合 通过主客户信息
	 * 
	 * @param searchCondition 主客户ID
	 * @pdOid 75b7e151-3ccd-439f-b64c-f935db29852b
	 */
	@Override
	public List<RepeatedCustomer> searchRepeatedCustList(SearchCondition searchCondition) {
		//验证主客户编号
		CustRepeatValidator.validMainCustId(searchCondition);
		return repeatedCustService.searchRepeatCustList(searchCondition);
	}

	/**
	 * @param repeatedCustService the repeatedCustService to set
	 */
	public void setRepeatedCustService(IRepeatedCustService repeatedCustService) {
		this.repeatedCustService = repeatedCustService;
	}

	/**
	 * @param departmentService the departmentService to set
	 */
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * 确定疑似重复客户
	 * 
	 * @param user 当前登录人
	 * @param custList 全部的客户集合
	 * @param disposeOpinion 处理意见
	 */
	@Override
	public void confirmRepeat(User user, List<RepeatedCustomer> custList,String disposeOpinion) {
		//如果处理的疑似重复客户，小于2个，则返回。需处理2个及以上的疑似重复客户
		if(null == custList || custList.size() < 2){return;}
		//获取当前部门id
		Department dept = user.getEmpCode().getDeptId();
		//验证列表是否存在本部门客户
		CustRepeatValidator.validExistCurrentDept(dept.getDeptName(),custList);
		
		//newCustList用于存储新增疑似重复客户
		List<RepeatedCustomer> newCustList = this.getNewRepeatCust(custList);
		//若存在新加入的疑似客户
		if(null != newCustList && newCustList.size() > 0 ){
			this.validNewCust(newCustList);
		} 
		RepeatedCustomer mainCust = this.getMainRepeatCust(custList);
		
		if(null == dept.getId() || !dept.getId().equals(mainCust.getDeptId())){
			//本部门客户优先级低于其他部门客户优先级，无法确定主客户，请核实
			throw new CustRepeatException(CustRepeatExceptionType.CUST_CURRENTDEPT_CUSTLEVEL_LOW.getErrCode(),new Object[]{});
		}
		
		//主客户标记
		for (RepeatedCustomer cust : custList) {
			//设置当前客户为主客户
			cust.setIsMainCust(cust.getCustId().equals(mainCust.getCustId())?1:0);
			//设置主客户ID
			cust.setMainCustId(mainCust.getCustId());
		}
		//生成工作流
		this.bactchCreateWorkflow(dept.getDeptName().trim(),custList,disposeOpinion);
		
		//新增的疑似客户先添加至疑似重复表
		repeatedCustService.bactchAddRepeatCust(newCustList);
		
		//更新疑似重复的主客户ID 和 设置主客户
		repeatedCustService.bactchUpdateRepeatCustStatus(custList);
		
		//标记疑似客户
		repeatedCustService.batchMarkCustRepeat(custList);
				
		//把已标记的疑似重复数据添加至历史表
		repeatedCustService.bactchAddRepeatCustToHistory(custList);
		
		//删除疑似客户表想相关数据
		repeatedCustService.deleteRepeatCustList(custList);
	}
	
	/**
	 * 验证新增客户是否符合要求
	 * @param custList 传进来的参数
	 * @param newCustList 要进行验证的数据
	 */
	private void validNewCust(List<RepeatedCustomer> newCustList){
		List<RepeatedCustomer> tempList = null;
		SearchCondition cont = new SearchCondition();
		cont.setCustIdList(newCustList);
		//根据新增的疑似重复客户的条件在疑似表中查询
		tempList = repeatedCustService.searchRepeatCustList(cont);
		if(null != tempList && tempList.size() > 0){
			//您输入的客户存在于其他疑似重复列表中，无法处理，请核实
			throw new CustRepeatException(CustRepeatExceptionType.CUST_ISEXIST_REPEAT.getErrCode(),getMessageArg(tempList));
		}
		
//		cont.setSearchStatus(CustRepeatConstants.search_status_approve);
		cont.setCustStatus(1);//审批中的客户
		tempList = repeatedCustService.searchCustomerList(cont);
		if(null != tempList && tempList.size() > 0){
			//您输入的客户存在未审批\审批中的工作流，无法处理，请核实{0}
			throw new CustRepeatException(CustRepeatExceptionType.CUST_ISEXIST_APPEOVAL.getErrCode(),getMessageArg(tempList));
		}
		
		cont.setSearchStatus(CustRepeatConstants.search_status_mobile);
		cont.setCustStatus(0);//有效的客户
		tempList = repeatedCustService.searchCustomerList(cont);
		if(null != tempList && tempList.size() > 0){
			//您输入的客户处于流失预警/商机/开发计划中/维护计划中/开发日程中/维护日程中，无法处理，请核实
			throw new CustRepeatException(CustRepeatExceptionType.CUST_ISEXIST_SCHEDULE.getErrCode(),getMessageArg(tempList));
		}
	}
	
	/**
	 * 得到异常现实参数
	 * @param custList
	 * @return
	 */
	private String getMessageArg(List<RepeatedCustomer> custList){
		String msgArg = "<br/>";
		int size = custList.size();
		for (int i = 0; i < size; i++) {
			msgArg = msgArg + custList.get(i).getCustCode() +',';
			if(i != 0 && i < size-1 && i/5 == 0){
				msgArg = msgArg + "<br/>";
			}
		}
		return msgArg.substring(0,msgArg.length()-1);
	}
	/**
	 * 检索出本部门的主客户
	 * @param custList 必须为本部门客户
	 * @return
	 */
	private RepeatedCustomer getMainRepeatCust(List<RepeatedCustomer> custList){
		//本部门客户
		List<RepeatedCustomer> bigAndContractCustList = new ArrayList<RepeatedCustomer>();
		for (RepeatedCustomer cust : custList) {
			if(
				(null != cust.getIsBigcustomer() && 1 == cust.getIsBigcustomer())
				||//大客户或合同客户
				(null != cust.getIsContractCust() && 0 < cust.getIsContractCust())
			){bigAndContractCustList.add(cust);}
		}
		
		if(null != bigAndContractCustList && 1 < bigAndContractCustList.size()){
			//若存在多个（大客户/者合同客户）
			//存在多个大客户/合同客户，无法确定主客户，请核实
			throw new CustRepeatException(CustRepeatExceptionType.CUST_EXISTMOREKEYCUST.getErrCode(),new Object[]{});
		}
		bigAndContractCustList = null;
		//固定客户list
		List<RepeatedCustomer> gdCust = new ArrayList<RepeatedCustomer>();
		//潜客list
		List<RepeatedCustomer> qianCust = new ArrayList<RepeatedCustomer>();
		//散客list
		List<RepeatedCustomer> sanCust = new ArrayList<RepeatedCustomer>();
		for (RepeatedCustomer cust : custList) {
			if(
				cust.getIsBigcustomer() == 1 //大客户
				||
				cust.getIsContractCust() > 0 //合同
			){return cust;}
			//固定客户
			if(CustRepeatConstants.RC_CUSTOMER.equals(cust.getCustGroup())){
				gdCust.add(cust);continue;
			}
			//散客
			if(CustRepeatConstants.SC_CUSTOMER.equals(cust.getCustGroup())){
				sanCust.add(cust);continue;
			}
			//潜客户
			if(CustRepeatConstants.PC_CUSTOMER.equals(cust.getCustGroup())){
				qianCust.add(cust);
			}
		}
		//若固定客户存在
		if(gdCust.size() == 1){
			return gdCust.get(0);
		}
		//若有多个固定客户则进行等级对比
		if(gdCust.size() > 1){
			return this.custLevelContrast(gdCust);
		}
		//固定客户不存在，散客只存在一个
		if(sanCust.size() == 1){
			return sanCust.get(0);
		}
		//固定客户不存在，，若有多个散客则进行金额对比
		if(sanCust.size() > 1){
			return this.custMoneyContrast(sanCust);
		}
		//走到这里，全是潜客
		return this.custMoneyContrast(qianCust);
	}
	/**
	 * <p>
	 *	Description: 客户级别对比，寻找主客户
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-13
	 * @param custList
	 * @return
	 * RepeatedCustomer 主客户
	 */
	private RepeatedCustomer custLevelContrast(List<RepeatedCustomer> custList){
		//数据不存在返回空
		if(null == custList || 0 == custList.size()){return null;}
		//钻石客户集合
		List<RepeatedCustomer> diamondCustList = new ArrayList<RepeatedCustomer>();
		//铂金客户集合
		List<RepeatedCustomer> platinumCustList = new ArrayList<RepeatedCustomer>();
		//黄金客户集合
		List<RepeatedCustomer> goldCustList = new ArrayList<RepeatedCustomer>();
		for(RepeatedCustomer cust : custList){
			//钻石客户
			if(CustRepeatConstants.DIAMOND.equals(cust.getCustLevel())){
				diamondCustList.add(cust);
				//铂金客户
			}else if(CustRepeatConstants.PLATINUM.equals(cust.getCustLevel())){
				platinumCustList.add(cust);
				//黄金客户
			}else if(CustRepeatConstants.GOLD.equals(cust.getCustLevel())){
				goldCustList.add(cust);
			} 
		}
		//若钻石客户存在且仅存在一个，则返回为主客户
		if(diamondCustList.size()==1){
			return diamondCustList.get(0);
		}
		//若存在多个钻石客户则进行金额比较
		if(diamondCustList.size() > 1){
			return this.custMoneyContrast(diamondCustList);
		}
		//若铂金客户存在且仅存在一个，则返回为主客户
		if(platinumCustList.size()==1){
			return platinumCustList.get(0);
		}
		//若存在多个铂金客户则进行金额比较
		if(platinumCustList.size() > 1){
			return this.custMoneyContrast(platinumCustList);
		}
		//若黄金客户存在且仅存在一个，则返回为主客户
		if(goldCustList.size()==1){
			return goldCustList.get(0);
		}
		//若存在多个黄金客户则进行金额比较
		if(goldCustList.size() > 1){
			return this.custMoneyContrast(goldCustList);
		}
		//若上面都不走，表明custList都是普通固定客户，则进行金额比较
		return this.custMoneyContrast(custList);
	}	
	/**
	 * 金额对比，寻找主客户
	 * @param custList
	 * @return
	 */
	private RepeatedCustomer custMoneyContrast(List<RepeatedCustomer> custList){
		if(null == custList || 0 == custList.size()){return null;}
		//默认最大的时间数
		double maxMoney = custList.get(0).getDeliveryMoneyTotal();
		int maxIndex = 0; //默认最大的索引
		//保存最大的相同的金额的客户集合
		List<RepeatedCustomer> maxSameMoneyCustList = new ArrayList<RepeatedCustomer>();
		
		for (int i = 0; i < custList.size(); i++) {
			double temp  = custList.get(i).getDeliveryMoneyTotal();
			if(temp > maxMoney){
				maxIndex = i;
				//若最大值的索引被替换，则清空数据
				maxSameMoneyCustList.clear();
				//添加一条数据
				maxSameMoneyCustList.add(custList.get(i));
			}
			if(temp == maxMoney){
				//相同的话继续添加
				maxSameMoneyCustList.add(custList.get(i));
			}
		}
		if(maxSameMoneyCustList.size() > 1){
			return this.custTimeContrast(maxSameMoneyCustList);
		}
		//若类型相同 ，返回金额最大的客户
		return custList.get(maxIndex);
	}
	
	/**
	 * 时间对比，寻找主客户
	 * @param custList
	 * @return
	 */
	private RepeatedCustomer custTimeContrast(List<RepeatedCustomer> custList){
		if(custList == null || custList.size() == 0){return null;}
		//默认最小的时间数
		long minTime = custList.get(0).getCustCreateTime().getTime();
		int minIndex = 0; //默认最小的索引
		for (int i = 1; i < custList.size(); i++) {
			long temp  = custList.get(i).getCustCreateTime().getTime();
			if(minTime > temp){
				minIndex = i;
				minTime = temp; 
			}
		}
		//若类型相同 ，返回创建时间最早（时间小）的客户
		return custList.get(minIndex);
	}
	/**
	 * 确认不重复
	 * 
	 * @param user 登录用户
	 * @param custList 所有客户集合
	 */
	@Override
	public void confirmNotRepeat(List<RepeatedCustomer> custList) {
		if(null == custList || custList.size() < 2){return;}
		
		//标记疑似客户
		repeatedCustService.batchMarkCustRepeat(custList);
				
		//删除疑似客户表想相关数据
		repeatedCustService.deleteRepeatCustList(custList);
	}
	
	/**
	 * 批量生成工作流
	 * @param custList
	 */
	private void bactchCreateWorkflow(String deptName,List<RepeatedCustomer> custList,String disposeOpinion){
		if(null == custList){return;}
		//不是派送中心
		for (RepeatedCustomer cust : custList) {
			if(
				(null != cust.getIsMainCust() && 1 == cust.getIsMainCust())
				|| cust.getIsBigcustomer() > 0 || cust.getIsContractCust() > 0 // 主客户，大客户，合同客户 不能生成工作流
			){
				continue;//主客户跳过
			}
			if(
				(	//当前登录人所在部门为派送中心
					deptName.indexOf(CustRepeatConstants.DEPTNAME_SENDDEPT_CENTER) != -1 &&
					deptName.equals(cust.getDeptName().trim()) && //与登录人所在部门一致
					!CustRepeatConstants.DIAMOND.equals(cust.getCustLevel())	&&	// 不是钻石
					!CustRepeatConstants.PLATINUM.equals(cust.getCustLevel())		// 不是铂金
				)  || (
					//当前登录人所在部门为400销售管理组
					deptName.indexOf(CustRepeatConstants.DEPTNAME_400SELL) != -1 &&
					deptName.equals(cust.getDeptName().trim())  //与登录人所在部门一致
				)
			){
				//若当前处理人所在部门 是 派送中心
				Member member = alterMemberService.getMemberAllById(cust.getCustId());
				//作废客户
				alterMemberService.deleteMember(member);
				continue;
			}
			RepetitiveCustWorkFlowInfo info = new RepetitiveCustWorkFlowInfo();
			//设置客户编码
			info.setCustomerCode(cust.getCustCode());
			//设置客户名称
			info.setCustomerName(cust.getCustName());
			//设置电话号码
			info.setTelphoneNum(cust.getTelephone());
			//设置部门id
			info.setDeptId(cust.getDeptId());
			//设置部门名称
			info.setDeptName(cust.getDeptName());
			//设置所属小区名称
			info.setAreaName(cust.getSmallRegionDeptName());
			//设置所属大区名称
			info.setRegionName(cust.getBigRegionDeptName());
			//设置所有事业部名称
			info.setBussDeptName(cust.getBussDeptName());
			//根据客户状态设置其是否是大客户
			info.setBigCust(cust.getIsBigcustomer().toString());
			//是否存在有效合同信息的合同客户
			info.setContract(cust.getIsExistValidContract().toString());
			//历史合同客户
			info.setHistryContract(cust.getIsExistHistoryContract().toString());
			//近三个月发货金额
			info.setThreeMonMoney(cust.getDeliveryMoneyTotal());
			//银行卡号
			info.setBankCard(cust.getBankAccount());
			//客户等级
			info.setCustomerLevel(cust.getCustLevel());
			//客户类型
			info.setCustomerGroup(cust.getCustGroup());
			//处理意见
			info.setDisposeOpinion(disposeOpinion);
			//客户id
			info.setMemberId(cust.getCustId());
			//调用生成工作流方法
			this.processWorkFlow(info);
		}
	}
	
	
	/**
	 * 找出新增的疑似客户
	 * @param custList
	 * @return
	 */
	private List<RepeatedCustomer> getNewRepeatCust(List<RepeatedCustomer> custList){
		List<RepeatedCustomer> newCustList = new ArrayList<RepeatedCustomer>();
		//主客户ID
		String mainCustId = "";
		for (RepeatedCustomer cust : custList) {
			if(null == cust.getIsAdd() || cust.getIsAdd() == 0){
				if("".equals(mainCustId)){mainCustId = cust.getMainCustId();}
				continue;
			}
			//设置新加的客户的主客户ID
			cust.setMainCustId(mainCustId);
			//isAdd = 1 new客户
			newCustList.add(cust);
		}
		return newCustList;
	}
	
	/**
	 * 
	* @Title: processWorkFlow 
	* @Description:生成工作流
	* @author LiuY 
	* @param info  待生成工作流的疑似重复客户信息
	* @return String 工作流号
	* @throws
	 */
	private String processWorkFlow(RepetitiveCustWorkFlowInfo info){
		//验证工作流信息
		CustRepeatValidator.validWorkFlowInfo(info);
		//生成工作流号
		String bizCode = bpsWorkflowManager.bizCode();
		//获取当前客户
		User user = (User) UserContext.getCurrentUser();
		//将工作流号设置在疑似重复客户信息实体中
		info.setRepetitveCustWorkFlowId(bizCode);
		//工作流状态为已提交
		info.setRepetitveCustWorkFlowStatus(Constant.WORKFLOW_STATUS_IN_APPROVE);
		//设置起草人
		info.setProposer(user.getEmpCode().getId());
		//保存工作流详情
		repeatedCustService.saveWorkFlowInfo(info);
		//生成工作流
		Map<String, String> map = bpsWorkflowManager.createWorkflow(
				BpsConstant.REPEATEDCUST_PROCESSNAME,
				BpsConstant.PROCESS_REPEATEDCUST_DESTORY + bizCode,
				BpsConstant.PROCESS_REPEATEDCUST_DESTORY, bizCode);
		//将客户状态更新为审批中
		memberService.updateMemberStauts(Constant.NUMONE, info.getMemberId(), user.getEmpCode().getId());;
		if (null != map) {
			return map.get("bizCode");
		}
		return "";
	}

	/**
	 * @return the bpsWorkflowManager
	 */
	public IBpsWorkflowManager getBpsWorkflowManager() {
		return bpsWorkflowManager;
	}

	/**
	 * @param bpsWorkflowManager the bpsWorkflowManager to set
	 */
	public void setBpsWorkflowManager(IBpsWorkflowManager bpsWorkflowManager) {
		this.bpsWorkflowManager = bpsWorkflowManager;
	}

	/**
	 * 查询客户信息
	 */
	@Override
	public List<RepeatedCustomer> searchCustomerList(
			SearchCondition searchCondition) {
		//验证查询客户的查询条件
		CustRepeatValidator.validCustomer(searchCondition);
		return repeatedCustService.searchCustomerList(searchCondition);
	}

	/**
	 * @param contractService the contractService to set
	 */
	public void setContractService(IContractService contractService) {
		this.contractService = contractService;
	}

	@Override
	public void disposeRepeatCustData() {
		// TODO Auto-generated method stub
		this.repeatedCustService.disposeRepeatCustData();
	}

	@Override
	public void clearALLRepeatCustMark() {
		// TODO Auto-generated method stub
		this.repeatedCustService.clearALLRepeatCustMark();
	}
	
	@Override
	public RepetitiveCustWorkFlowInfo findRepetitiveCustWorkFlowInfoByWorkNo(
			String bizCode, String processDefName) {
		return repeatedCustService.findRepetitiveCustWorkFlowInfoByWorkNo(bizCode);
	}
	/**
	 * 疑似重复工作流审批
	 */
	@Override
	public void workflowApprove(String busino,Boolean wkStatus,
			String wkMan,Date approveDate,String approvalSuggestin){
		//根据工作流号，查询工作流信息
		RepetitiveCustWorkFlowInfo info = repeatedCustService.findRepetitiveCustWorkFlowInfoByWorkNo(busino);
		//校验工作流信息是否为空
		CustRepeatValidator.validWorkFlowInfo(info);
		//wkStatus == true 则设置工作流状态为已同意
		if(wkStatus){
			info.setRepetitveCustWorkFlowStatus(CustRepeatConstants.WORKFLOW_STATUS_AGREED);
		}else{
			//wkStatus == false 则设置工作流状态为已同意
			info.setRepetitveCustWorkFlowStatus(CustRepeatConstants.WORKFLOW_STATUS_UNAGREE);
		}
		//设置最后操作日期
		info.setModifyDate(approveDate);
		info.setModifyUser(wkMan);
		//将疑似重复工作流状态更新为对应审批状态
		repeatedCustService.updateRepetitiveCustWorkFlowInfo(info);
		if(wkStatus){
			//调用客户模块作废客户方法
			List<MemberResult> memberList= alterMemberService.queryBasicMemberByCustNum(info.getCustomerCode());
			if(null != memberList &&memberList.size()>0){
				//根据客户Id查询出客户信息
				Member member = alterMemberService.getMemberAllById(memberList.get(0).getCustId());
				//作废客户
				alterMemberService.deleteMember(member);
			}
		}else{
			//审批不同意，将客户状态更新回有效状态
			List<MemberResult> memberList= alterMemberService.queryBasicMemberByCustNum(info.getCustomerCode());
			if(null != memberList &&memberList.size()>0){
				//将客户设置为有效
				memberService.updateMemberStauts(Constant.NUMZERO, memberList.get(0).getCustId(), wkMan);
			}
		}
	}

	/**
	 * @return the alterMemberService
	 */
	public IAlterMemberService getAlterMemberService() {
		return alterMemberService;
	}

	/**
	 * @param alterMemberService the alterMemberService to set
	 */
	public void setAlterMemberService(IAlterMemberService alterMemberService) {
		this.alterMemberService = alterMemberService;
	}

	/**
	 * <p>
	 * Description:根据客户id查询客户是否处于疑似重复列表<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-8
	 * @return
	 * boolean
	 */
	@Override
	public boolean isCustExistsRepeat(String memberId) {
		return this.repeatedCustService.isCustExistsRepeat(memberId);
	}

	@Override
	public int deleteMarkCustByCustId(String custId) {
		//根本客户编号，删除已标记的疑似客户
		return repeatedCustService.deleteMarkCustByCustId(custId);
	}
	
	/**
	 * 验证部门是否有效
	 * @param deptName
	 * @return
	 */
	private boolean isValidDept(String deptName){
		if(
			deptName.endsWith(CustRepeatConstants.DEPTNAME_SALESDEPT)
			|| 
			deptName.endsWith(CustRepeatConstants.DEPTNAME_SENDDEPT)
			|| 
			deptName.endsWith(CustRepeatConstants.DEPTNAME_SENDDEPT_CENTER)
			||
			deptName.endsWith(CustRepeatConstants.DEPTNAME_AREADEPT)
			||
			deptName.endsWith(CustRepeatConstants.DEPTNAME_REGIONDEPT)
			||
			deptName.endsWith(CustRepeatConstants.DEPTNAME_BUSS)
			||
			deptName.endsWith(CustRepeatConstants.DEPTNAME_400SELL)
	    ){return true;}
		
		return false;
	}
	
	/**
	 * 判断当前登录人职位 
	 * 点部经理、分部高级经理、快递大区总经理 查询映射数据
	 * @return
	 */
	private boolean isExpressManager(String position){
		if(position == null) {return false;}
		position = position.trim();
		if(// 点部经理、分部高级经理、快递大区总经理 查询映射数据
			CustRepeatConstants.position_manager.equals(position)
			||
			CustRepeatConstants.position_highManager.equals(position)
			||
			CustRepeatConstants.position_bossManager.equals(position)
		){return true;}
		
		return false;
	}
	/**
	 * 查询当前登录人 的 部门数据
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 */
	@Override
	public List<Department> searchRepeatCustDeptList(String deptName,
			int start, int limit) {
		Employee emp = ContextUtil.getCurrentUser().getEmpCode();
		SearchCondition sc = new SearchCondition();
		sc.setStart(start);
		sc.setLimit(limit);
		sc.setDeptName(null==deptName?"":deptName);
		//当前登录人所在部门
		sc.setCurrtDeptName(emp.getDeptId().getDeptName());
		
		if(isExpressManager(emp.getPosition())){
			//快递（点部经理、分部高级经理、快递大区总经理）
			sc.setDeptType(CustRepeatConstants.DEPT_EXPRESS);
		}else if(isValidDept(sc.getCurrtDeptName())){
			//特殊的（营业部、派送部、营业区、大区、事业部、400销售管理组,派送中心）
			sc.setDeptType(CustRepeatConstants.DEPT_SPECIAL);
		}else {
			// 除去 以上 的部门剩下的
			sc.setDeptType(CustRepeatConstants.DEPT_ORDINARY);
			sc.setCurrtDeptName(CustRepeatConstants.DEPTNAME_BUSS);
		}
		return repeatedCustService.searchRepeatCustDeptList(sc);
	}
	/**
	 * 查询当前登录人 的 部门数据 count
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 */
	@Override
	public int searchRepeatCustDeptCount(String deptName) {
		Employee emp = ContextUtil.getCurrentUser().getEmpCode();
		SearchCondition sc = new SearchCondition();
		sc.setDeptName(null==deptName?"":deptName);
		//当前登录人所在部门
		sc.setCurrtDeptName(emp.getDeptId().getDeptName());
		
		if(isExpressManager(emp.getPosition())){
			//快递（点部经理、分部高级经理、快递大区总经理）
			sc.setDeptType(CustRepeatConstants.DEPT_EXPRESS);
		}else if(isValidDept(sc.getCurrtDeptName())){
			//特殊的（营业部、派送部、营业区、大区、事业部、400销售管理组、派送中心）
			sc.setDeptType(CustRepeatConstants.DEPT_SPECIAL);
		}else {
			// 除去 以上 的部门剩下的
			sc.setDeptType(CustRepeatConstants.DEPT_ORDINARY);
			sc.setCurrtDeptName(CustRepeatConstants.DEPTNAME_BUSS);
		}
		return repeatedCustService.searchRepeatCustDeptCount(sc);
	}

	public IMemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}
	
	/**
	 * 查询客户是否存在疑似重复列表中，
	 * 若存在返回其主客户所在部门
	 * @param custCode 客户编码（主客户，此客户）
	 * @return 部门名称，不存在返回 "",存在返回所在主客户部门名称
	 */
	@Override
	public String searchMainCustDeptName(String custCode) {
		//验证客户编码不为空
		if(null == custCode || "".equals(custCode.trim())){
			throw new CustRepeatException(CustRepeatExceptionType.CUSTCODE_ISNULL.getErrCode(),new Object[]{});
		}
		SearchCondition searchCondition = new SearchCondition();
		searchCondition.setCustCode(custCode);
		//查询 客户编码对应的主客户信息
		List<RepeatedCustomer> list = repeatedCustService.searchRepeatCustList(searchCondition);
		//若客户不存在，返回 ""
		if(list == null || list.size()==0){return "";}
		// 若存在，返回其主客户所在部门名称
		return list.get(0).getDeptName();
	}
	
	@Override
	public String searchWorkFlowNoByCustId(String custCode){
		if(null == custCode || "".equals(custCode.trim())){
			throw new CustRepeatException(CustRepeatExceptionType.CUSTCODE_ISNULL.getErrCode(),new Object[]{});
		}
		return repeatedCustService.searchWorkFlowNoByCustId(custCode);
	}
}