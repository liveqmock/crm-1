package com.deppon.crm.module.customer.server.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractDept;
import com.deppon.crm.module.customer.shared.domain.ContractDeptView;
import com.deppon.crm.module.customer.shared.domain.ContractDetailView;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.customer.shared.domain.ContractTax;
import com.deppon.crm.module.customer.shared.domain.ContractView;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowView;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;

/**
 * 
 * <p>
 * Description:合同工具类<br />
 * </p>
 * @title ContractUtil.java
 * @package com.deppon.crm.module.customer.server.utils 
 * @author 106138
 * @version 0.1 2014年4月18日
 */
public class ContractUtil {
	
	
	
	/**
	 * 
	 * <p>
	 * 设置月结额度查询时间----除本月外的近3个月<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-6-4
	 * @param years
	 * @param months
	 *            void
	 */
	public static void setSearchTime(List<String> years, List<String> months,int monthCount) {
		for (; monthCount >0 ; monthCount--) {
			// 添加近monthCount个月时间
			addSearchTime(years, months, addMonth(new Date(), -monthCount));
		}
	}
	/**
	 * 
	 * <p>
	 * Description:增加查询时间<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param years
	 * @param months
	 * @param date
	 * void
	 */
	public static void addSearchTime(List<String> years, List<String> months,
			Date date) {
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		String year = yearFormat.format(date);
		if (!years.contains(year)) {
			years.add(year);
		}
		String month = monthFormat.format(date);
		if (!months.contains(month)) {
			months.add(month);
		}
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:增加一个月<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param date
	 * @param month
	 * @return
	 * Date
	 */
	@SuppressWarnings("deprecation")
	private static Date addMonth(Date date, int month) {
		date.setDate(1);
		date.setMonth(date.getMonth() + month);
		return date;
	}
	/**
	 * 
	 * <p>
	 * 把合同对象转换成合同view对象<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-8-3
	 * @param contract
	 * @return
	 * ContractView
	 */
	public static ContractView createContractView(Contract contract) {
		ContractView contractView = new ContractView();
		//查询出历史操作记录信息
		ContractDetailView deView = ContractUtil.changeContractToContractDetailView(contract);
		contractView.setContract(deView);
		//设置会员基本信息
		Member member = contract.getMember();
		contractView.setCustName(member.getCustName());
		contractView.setCustNum(member.getCustNumber());
		contractView.setTrade(member.getTradeId());
		contractView.setSecondTrade(member.getSecondTrade());
		contractView.setCustGrade(member.getDegree());
		if (null!=member.getMainContact()) {
			contractView.setContactName(member.getMainContact().getName());
			contractView.setMobileNum(member.getMainContact().getMobilePhone());
			contractView.setTelNum(member.getMainContact().getTelPhone());
			contractView.setIdCard(member.getMainContact().getIdCard());
		}
		contractView.setCustType(member.getCustType());
		contractView.setTaxregNum(member.getTaxregNumber());
		contractView.setCityId(member.getCityId());
		contractView.setCityName(member.getCity());
		contractView.setAddress(member.getRegistAddress());
		contractView.setIfForeignGoods(contract.getIfForeignGoods());
		contractView.setDunningDeptCode(contract.getDunningDeptCode());
		contractView.setDunningDeptName(contract.getDunningDeptName());
		return contractView;
	}
	
	/**
	 * 
	 * <p>
	 * Description:转换合同到表单<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param contract
	 * @return
	 * ContractDetailView
	 */
	public static ContractDetailView changeContractToContractDetailView(Contract contract){
		if(contract == null){
			return null;
		}
		ContractDetailView view = new ContractDetailView();
		changeContractToContractDetailView_BaseDate(contract,view);
		changeContractToContractDetailView_ContractDeptView(contract,view);
		changeContractToContractDetailView_ContractWorkflowView(contract,view);
		changeContractToContractDetailView_FileInfo(contract,view);
		return view;
	}
	/**
	 * <br>合同附件是历史操作记录的全部附件总集</br>
	 * @param logs
	 * @param view
	 */
	private static void changeContractToContractDetailView_FileInfo(Contract contract,
			 ContractDetailView view) {
			List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
			if (null != contract && null != contract.getContractWorkflowList()
					&& 0 < contract.getContractWorkflowList().size()) {
				for (ContractOperatorLog contractOperatorLog : contract.getContractWorkflowList()) {
					if (null != contractOperatorLog && null != contractOperatorLog.getFileInfos()
							&& 0< contractOperatorLog.getFileInfos().size()) {
						fileInfoList.addAll(contractOperatorLog.getFileInfos());
					}
				}
			}
			view.setFileInfoList(fileInfoList);
	}
	/**
	 * 
	 * <p>
	 * Description:转换工作流合同详情<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param contract
	 * @param view
	 * void
	 */
	private static void changeContractToContractDetailView_ContractWorkflowView(
			Contract contract, ContractDetailView view) {
		List<ContractOperatorLog> objList = contract.getContractWorkflowList();
		List<ContractWorkflowView> viewList = new ArrayList<ContractWorkflowView>();
		for (ContractOperatorLog contractOperatorLog : objList) {
			//历史操作记录中有工作流的部分，才转换成工作流信息
			if(!ValidateUtil.objectIsEmpty(contractOperatorLog.getWorkflowId())){
				ContractWorkflowView viewChild = changeContractOperatorLogToContractWorkflowView(contractOperatorLog);
				viewList.add(viewChild);
			}
		}
		view.setContractWorkflowList(viewList);
	}

	/**
	 * 
	 * <p>
	 * Description:操作日志转换<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param log
	 * @return
	 * ContractWorkflowView
	 */
	private static ContractWorkflowView changeContractOperatorLogToContractWorkflowView(
			ContractOperatorLog log) {
		if(log == null){
			return null;
		}

		ContractWorkflowView view = new ContractWorkflowView();
		view.setId(log.getId());
		view.setCreateDate(log.getCreateDate());
		view.setCreateUser(log.getCreateUser());
		view.setModifyDate(log.getModifyDate());
		view.setModifyUser(log.getModifyUser());
		view.setOperatorType(log.getOperatorType());
		view.setWorkflowId(log.getWorkflowId());
		view.setApprovalState(log.getApprovalState());
		view.setCreateDate(log.getCreateDate());
		view.setModifyDate(log.getModifyDate());
		view.setApprovalMan(log.getApprovalMan());
		if(null!=log.getOperatorDept()){
			view.setDeptName(log.getOperatorDept().getDeptName());
			view.setContractDeptId(log.getOperatorDept().getId());
		}
		
		return view;
	}
	/**
	 * 
	 * <p>
	 * Description:合同部门<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param contract
	 * @param view
	 * void
	 */
	private static void changeContractToContractDetailView_ContractDeptView(
			Contract contract, ContractDetailView view) {
		List<ContractDept> objList = contract.getContractDepartList();
		
		List<ContractDeptView> viewList = new ArrayList<ContractDeptView>();
		for (ContractDept contractDept : objList) {
			ContractDeptView viewChild = changeContractDeptToContractDeptView(contractDept);
			viewList.add(viewChild);
		}
		view.setContractDepartList(viewList);
	}
	/**
	 * 
	 * <p>
	 * Description:合同部门<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param contractDept
	 * @return
	 * ContractDeptView
	 */
	private static ContractDeptView changeContractDeptToContractDeptView(
			ContractDept contractDept) {
		if(contractDept == null){
			return null;
		}

		ContractDeptView view = new ContractDeptView();
		view.setId(contractDept.getId());
		view.setCreateDate(contractDept.getCreateDate());
		view.setCreateUser(contractDept.getCreateUser());
		view.setModifyDate(contractDept.getModifyDate());
		view.setModifyUser(contractDept.getModifyUser());
		view.setDeptName(contractDept.getContractDept().getDeptName());
		view.setBoundTime(contractDept.getBoundTime());
		view.setRemoveTime(contractDept.getRemoveTime());
		view.setState(contractDept.isState());
		view.setContractDeptId(contractDept.getContractDept().getId());
		view.setDept(contractDept.isDept());
		view.setVersionNumber(contractDept.getVersionNumber());
		return view;
	}
    /**
     * 
    * @Title: changeListToContractTax
    * @Description: 获取有效的税务信息（将税务信息list 转换成 contracttax实体）
    * @author chenaichun 
    * @param @param contract
    * @param @return    设定文件
    * @date 2013-11-26 下午2:01:53
    * @return ContractTax    返回类型
    * @throws
    * @update 2013-11-26 下午2:01:53
     */
	public  static ContractTax changeListToContractTax(Contract contract){
		if(ValidateUtil.objectIsEmpty(contract)){
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		List<ContractTax> contractTaxList = contract.getContractTaxList();
		if (null==contractTaxList || 0 == contractTaxList.size()) {
			return null;
		}
		if(contractTaxList.size()==1){
			return contractTaxList.get(0);
		}
		if(contractTaxList.size()==2){
			//合同的结束时间与税务的结束时间保持一致
			if(isContractTaxEndTimeEquals(contract)){
				for(int i = 0;i<contractTaxList.size();i++){
					//当前时间 在合同结束后 或者 下月1号信息之后，取得税务时间和合同结束时间一致的那条税务信息
					if(contract.getContractendDate().equals(contractTaxList.get(i).getEndTime())){
						if(contractTaxList.get(i).getBeginTime().before(new Date())){
							return contractTaxList.get(i);
						}
					}
					else{//当前时间在下月1号前  返回第一条信息（税务信息为旧合同信息）
						if(contractTaxList.get(i).getEndTime().after(new Date())){
							return contractTaxList.get(i);
						}
					}
				}
			}
			// 合同的结束时间变化了(主要针对旧合同的结束时间的修改 和合同作废 对税务的时间进行修改 
			else{
				for (int i = 0; i<contractTaxList.size(); i++) {
					//合同的结束时间 在税务结束时间之后 返回  结束时间大的那条
					if(contract.getContractendDate().after(contractTaxList.get(i).getEndTime())){
						if(contractTaxList.get(1-i).getEndTime().before(contractTaxList.get(i).getBeginTime())){
							return contractTaxList.get(i);
						}
						return contractTaxList.get(1-i);
					}
				}
				for (int i = 0; i<contractTaxList.size(); i++) {
					if(contract.getContractendDate().before(contractTaxList.get(i).getEndTime())){
						if(contractTaxList.get(i).getEndTime().before(contractTaxList.get(1-i).getBeginTime())){
							return contractTaxList.get(i);
						}
						return contractTaxList.get(1-i);
					}
				}
			}
		}
		return null;
	}
	/**
	 * 
	* @Title: isContractTaxEndTimeEquals
	* @Description: 判断税务的时间是否和合同的时间一致
	* @author chenaichun 
	* @param @param contract
	* @param @return    设定文件
	* @date 2013-12-5 上午11:09:54
	* @return boolean    返回类型
	* @throws
	* @update 2013-12-5 上午11:09:54
	 */
	public static boolean isContractTaxEndTimeEquals(Contract contract){
		if(contract.getContractTaxList().size()!=2){
				throw new ContractException(ContractExceptionType.CONTRACT_CONTRACTTAX_NULL);
		}
		
		for (int i = 0; i <contract.getContractTaxList().size(); i++) {
			if(contract.getContractendDate().equals(contract.getContractTaxList().get(i).getEndTime())){
				return true;
			}
		}
		return false;
		
	}
	/**
	 * 
	* @Title: findAnotherContractTax
	* @Description: 根据合同的税务list 和正在使用的contractTax 得到另外一条税务信息
	* @author chenaichun 
	* @param @param contract
	* @param @param contractTax
	* @param @return    设定文件
	* @date 2013-11-30 上午10:25:07
	* @return ContractTax    返回类型
	* @throws
	* @update 2013-11-30 上午10:25:07
	 */
	public static ContractTax findAnotherContractTax(Contract contract,ContractTax contractTax){
		if(ValidateUtil.objectIsEmpty(contract)){
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		if(ValidateUtil.objectIsEmpty(contractTax)){
			throw new ContractException(ContractExceptionType.CONTRACT_CONTRACTTAX_NULL);
		}
		if(contract.getContractTaxList().size()!=2){
			return null;
		}
		for (int i = 0; i < contract.getContractTaxList().size(); i++) {
			if(contractTax.getId().equals(contract.getContractTaxList().get(i).getId())){
				return contract.getContractTaxList().get(1-i);
			}
		}
		return null;
	}
	/**
	 * 
	 * <p>
	 * Description:基础数据<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param contract
	 * @param view
	 * void
	 */
	private static void changeContractToContractDetailView_BaseDate(Contract contract,
			ContractDetailView view) {
		view.setId(contract.getId());
		view.setCreateDate(contract.getCreateDate());
		view.setCreateUser(contract.getCreateUser());
		view.setModifyDate(contract.getModifyDate());
		view.setModifyUser(contract.getModifyUser());
		view.setPayWay(contract.getPayWay());
		view.setArrearaMount(contract.getArrearaMount());
		view.setLinkManName(contract.getLinkManName());
		view.setLinkManPhone(contract.getLinkManPhone());
		view.setLinkManMobile(contract.getLinkManMobile());
		view.setLinkManAddress(contract.getLinkManAddress());
		view.setReconDate(contract.getReconDate());
		view.setInvoicDate(contract.getInvoicDate());
		view.setResultDate(contract.getResultDate());
		view.setContractBeginDate(contract.getContractBeginDate());
		view.setContractendDate(contract.getContractendDate());
		view.setApplication(contract.getApplication());
		view.setCustId(contract.getMember().getId());
		view.setIddisCount(contract.getIddisCount());
		if (null != contract.getContractBeginDate()
				&& Constant.CONTRACT_STATUS_EFFECT.equals(contract
						.getContractStatus())
				&& contract.getContractBeginDate().after(new Date())) {
			view.setContractStatus(Constant.CONTRACT_STATUS_WAIT_EFFECT);
		} else {
			view.setContractStatus(contract.getContractStatus());
		}
		view.setContractSubject(contract.getContractSubject());
		view.setRegicapital(contract.getRegicapital());
		view.setBeforeContractNum(contract.getBeforeContractNum());
		view.setContractNum(contract.getContractNum());
		view.setGoodsName(contract.getGoodsName());
		view.setCustCompany(contract.getCustCompany());
		view.setContactId(contract.getContactId());
		view.setPreferentialType(contract.getPreferentialType());
		view.setPreferential(contract.getPreferential());
		//快递近三月发货金额
		view.setExArrAmount(contract.getExArrAmount());
		//快递折扣信息
		view.setExPreferential(contract.getExPreferential());
		//快递结款方式
		view.setExPayWay(contract.getExPayWay());
		//快递折扣类型
		view.setExPreferentialType(contract.getExPreferentialType());
		//快递合同价格版本
		view.setExPriceVersionDate(contract.getExPriceVersionDate());
		view.setArrAmount(contract.getArrAmount());
		/**
		 * 李国文添加
		 */
		view.setDebtDays(contract.getDebtDays());
		view.setUseableArrearAmount(contract.getUseableArrearAmount());
		
		view.setIfForeignGoods(contract.getIfForeignGoods());
		view.setDunningDeptCode(contract.getDunningDeptCode());
		view.setDunningDeptName(contract.getDunningDeptName());
		view.setPriceVersionDate(contract.getPriceVersionDate());
		Department dept = contract.getDept();
		if(dept != null){
			view.setDeptId(dept.getId());
			view.setDeptName(dept.getDeptName());
		}
		view.setLastModifyDept(contract.getDept());
		
		/**
		 * 添加发票标记字段
		 * @author chenaichun
		 */
		ContractTax contractTax = changeListToContractTax(contract);
		if (null!=contractTax) {
			view.setSignCompany(contractTax.getSignCompany());
			view.setInvoiceType(contractTax.getInvoiceType());
		}
	}
	/**
	 * <br>前台传过来的合同view对象里面只包含</br>
	 * <br>使用java的引用对象操作，对专递过来的contract,fileList进行赋值</br>
	 * @param view
	 * @return
	 */
	public static Contract changeContractDetailViewToContract(ContractDetailView view){
		if(view == null) {
			return null;
		}
		Contract contract = new Contract();
		changeContractDetailViewToContract_BaseData(view,contract);
		//@chenaichun  将前台传过来的view 信息 封装到contract中去
		changeContractDetailViewToContract_ContractTax(view,contract);
		changeContractDetailViewToContract_ContractDept(view,contract);
		return contract;
	}
	/**
	 * 
	 * <p>
	 * Description:基本数据<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param view
	 * @param contract
	 * void
	 */
	private static void changeContractDetailViewToContract_BaseData(
			ContractDetailView view, Contract contract) {
		contract.setId(view.getId());
		contract.setCreateDate(view.getCreateDate());
		contract.setCreateUser(view.getCreateUser());
		contract.setModifyDate(view.getModifyDate());
		contract.setModifyUser(view.getModifyUser());
		contract.setPayWay(view.getPayWay());
		contract.setArrearaMount(view.getArrearaMount());
		contract.setLinkManName(view.getLinkManName());
		contract.setLinkManPhone(view.getLinkManPhone());
		contract.setLinkManMobile(view.getLinkManMobile());
		contract.setLinkManAddress(view.getLinkManAddress());
		contract.setReconDate(view.getReconDate());
		contract.setInvoicDate(view.getInvoicDate());
		contract.setResultDate(view.getResultDate());
		contract.setContractBeginDate(view.getContractBeginDate());
		contract.setContractendDate(view.getContractendDate());
		contract.setApplication(view.getApplication());
		contract.setIddisCount(view.getIddisCount());
		contract.setContractStatus(view.getContractStatus());
		contract.setContractSubject(view.getContractSubject());
		contract.setRegicapital(view.getRegicapital());
		contract.setBeforeContractNum(view.getBeforeContractNum());
		contract.setContractNum(view.getContractNum());
		contract.setGoodsName(view.getGoodsName());
		contract.setCustCompany(view.getCustCompany());
		contract.setContactId(view.getContactId());
		contract.setPreferentialType(view.getPreferentialType());
		contract.setPreferential(view.getPreferential());
		contract.setArrAmount(view.getArrAmount());
		contract.setFileInfoList(view.getFileInfoList());
		
		contract.setDebtDays(view.getDebtDays());
		contract.setUseableArrearAmount(view.getUseableArrearAmount());
		
		contract.setIfForeignGoods(view.getIfForeignGoods());
		contract.setDunningDeptCode(view.getDunningDeptCode());
		contract.setDunningDeptName(view.getDunningDeptName());
		contract.setPriceVersionDate(view.getPriceVersionDate());
		//快递近三月发货金额
		contract.setExArrAmount(view.getExArrAmount());
		//快递折扣信息
		contract.setExPreferential(view.getExPreferential());
		//快递结款方式
		contract.setExPayWay(view.getExPayWay());
		//快递折扣类型
		contract.setExPreferentialType(view.getExPreferentialType());
		//快递合同价格版本
		contract.setExPriceVersionDate(view.getExPriceVersionDate());
		
		
		
		Member member = new Member();
		member.setId(view.getCustId());
		contract.setMember(member);
		 
		Department department = new Department();
		
		department.setId(view.getDeptId());
		department.setDeptName(view.getDeptName());
		contract.setDept(department);
	}
	/**
	 * 
	* @Title: changeContractDetailViewToContract_ContractTax
	* @Description: 将前台传过来的view 信息 封装到contract中去
	* @author chenaichun 
	* @param @param view
	* @param @param contract    设定文件
	* @date 2013-11-26 下午2:03:51
	* @return void    返回类型
	* @throws
	* @update 2013-11-26 下午2:03:51
	 */
	private static void changeContractDetailViewToContract_ContractTax(ContractDetailView view,Contract contract){
		List<ContractTax> contractTaxList = new ArrayList<ContractTax>(); 
		ContractTax contractTax = new ContractTax();
		contractTax.setSignCompany(view.getSignCompany());
		contractTax.setInvoiceType(view.getInvoiceType());
		contractTaxList.add(contractTax);
		contract.setContractTaxList(contractTaxList);
	}
	private static void changeContractDetailViewToContract_ContractDept(
			 ContractDetailView view,Contract contract) {
		 List<ContractDeptView> objList = null == view.getContractDepartList()?new ArrayList<ContractDeptView>():view.getContractDepartList();
		
		List<ContractDept> deptList = new ArrayList<ContractDept>();
		for (ContractDeptView contractDept : objList) {
			ContractDept deptChild = changeContractViewDeptToContractDept(contractDept);
			deptList.add(deptChild);
		}
		contract.setContractDepartList(deptList);
	}
	private static ContractDept  changeContractViewDeptToContractDept(
			ContractDeptView view) {
		if(view == null) {
			return null;
		}

		ContractDept dept = new ContractDept();
		Department contractDept = new Department();
		contractDept.setDeptName(view.getDeptName());
		contractDept.setId(view.getContractDeptId());
		dept.setId(view.getId());
		dept.setCreateDate(view.getCreateDate());
		dept.setCreateUser(view.getCreateUser());
		dept.setModifyDate(view.getModifyDate());
		dept.setModifyUser(view.getModifyUser());
		dept.setContractDept(contractDept);
		dept.setBoundTime(view.getBoundTime());
		dept.setRemoveTime(view.getRemoveTime());
		dept.setState(view.isState());
		dept.setDept(view.isDept());
		dept.setVersionNumber(view.getVersionNumber());
		return dept;
	}
	/**
	 * 
	 * <p>
	 * 得到用户期望的合同状态--待完成<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-9-3
	 * @param contractStatus 系统合同状态
	 * @return
	 * String 用户期望合同状态
	 */
	public static String createCustContractStatus(String contractStatus){
		return contractStatus;
	}
	/**
	 * 
	 * <p>
	 * 得到合同近3个月的发货情况<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-9-3
	 * @param livers
	 * @return
	 * String
	 */
	public static String getArrearaMount(List<Map<String, String>> livers) {
		Assert.notNull(livers, "livers must not be null");
		StringBuffer arrearaMount = new StringBuffer();
		arrearaMount.append(getLiverMoneyByLatelyMonth(3,livers)+"\\");
		arrearaMount.append(getLiverMoneyByLatelyMonth(2,livers)+"\\");
		arrearaMount.append(getLiverMoneyByLatelyMonth(1,livers));
		return arrearaMount.toString();
	}
	/**
	 * 
	 * <p>
	 * 得到合同近month个月的发货情况<br />
	 * </p>
	 * @author wmm
	 * @version 0.2 2012-9-3
	 * @param livers
	 * @return
	 * String
	 */
	public static List<Double> getArrearaMount(List<Map<String, String>> livers,int month) {
		Assert.notNull(livers, "livers must not be null");
		List<Double> araMountList = new ArrayList<Double>();
        while(month>=0){
		  araMountList.add(new BigDecimal(getLiverMoneyByLatelyMonth(month--,livers)).doubleValue());
        }
		return araMountList;
	}
	
	
	private static String getLiverMoneyByLatelyMonth(int latelyMonth, List<Map<String, String>> livers) {
		List<String> years = new ArrayList<String>();
		List<String> months = new ArrayList<String>();
		//找到最近latelyMonth的年月
		addSearchTime(years, months, addMonth(new Date(), -latelyMonth));
		
		/**
		 * 鲍鱼挖坑，查出来的月份是没有06,07,08的，直接就是6,7,8
		 */
		String year = years.get(0);
		String month = months.get(0);
		if (month.startsWith("0")) {
			month = months.get(0).replaceFirst("0", "");
		}
		
		String lives = "0";
		
		for (Map<String, String> liver : livers) {
			//数据库查出来的默认是BigDecimal，先转成Object，然后调用toString()方法
			if(year.equals(String.valueOf((Object)liver.get(Constant.year))) && month.equals(String.valueOf((Object)liver.get(Constant.month)))){
				lives = String.valueOf((Object)liver.get(Constant.deliverMoney));
			}
		}
		return lives;
	}

	/**
	 * @description 获得三个月发货最大值.  
	 * @author 潘光均
	 * @version 0.1 2012-9-3
	 * @param 
	 *@date 2012-9-3
	 * @return Double
	 * @update 2012-9-3 下午3:12:49
	 */
	public static Double getMaxAmount(List<Double> moneys) {
		Assert.notNull(moneys,"List is null");
		return Collections.max(moneys);
	}
	/**
	 * 
	 * @Title: getNowTime
	 *  <p>
	 * @Description: 用来获取当前时间并设为零时零分零秒/将传入的时间设为零时零分零秒<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-14
	 * @return Date
	 * @throws
	 */
	 public static Date getNowTime(Date date){
		 Calendar ca = Calendar.getInstance();
		 if (ValidateUtil.objectIsEmpty(date)) {
			 ca.setTime(new Date());
		}else {
			ca.setTime(date);
		}
		 ca.set(Calendar.HOUR_OF_DAY, 0);
		 ca.set(Calendar.SECOND, 0);
		 ca.set(Calendar.MINUTE, 0);
		 ca.set(Calendar.MILLISECOND, 0);
		 return ca.getTime();
	 }
	 /**
	  * 
	  * @Title: getEndTime
	  *  <p>
	  * @Description: 设置默认的基础资料失效时间<br />
	  * </p>
	  * @author 唐亮
	  * @version 0.1 2013-3-15
	  * @return Date
	  * @throws
	  */
	 public static Date getEndTime(Date date){
		 if (!ValidateUtil.objectIsEmpty(date)) {
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(date);
			 calendar.set(Calendar.HOUR_OF_DAY, 23);
			 calendar.set(Calendar.SECOND, 59);
			 calendar.set(Calendar.MINUTE, 59);
			 return calendar.getTime();
		}
		 return null;
	 }
//	 /**
//	  * 
//	  * <p>
//	  * Description:是否只修改了快递信息<br/>
//	  * </p>
//	  * @author pgj
//	  * @version 0.1 2013-9-26
//	  * @param newContract
//	  * @param oldContract
//	  * @return
//	  * boolean
//	  */
//	 public static boolean onlyModifyExpress(Contract newContract,Contract oldContract){
//		if (null == newContract || null == oldContract) {
//			throw new CustomerException(CustomerExceptionType.DataError);
//		}
//		boolean common = modifyContractCommon(newContract, oldContract,false);
//		boolean ex =modifyExpressContract(newContract, oldContract);
//		boolean ltt =  modifyLTTContract(newContract, oldContract);
//		return !common&&ex&&!ltt;
//	 }
	 /**
	  * 
	  * <p>
	  * Description:修改零担条款<br />
	  * </p> 
	  * @author pgj
	  * @version 0.1 2013-9-26
	  * @param newContract
	  * @param oldContract
	  * @return
	  * boolean
	  */
	public static boolean modifyLTTContract(Contract newContract,
			Contract oldContract) {
		if (null==oldContract) {
			return true;
		}
		if (!compare(newContract.getPayWay(),oldContract.getPayWay())) {
			return true; 
		}
		if (!compare(newContract.getPreferentialType(),oldContract.getPreferentialType())) {
			return true; 
		}
		//都是价格折扣，则两者折扣不一致，则修改过零担信息
		if (Constant.CONTRACT_PREFERENTIALTYPE_PRICE_REBATE.equals(newContract
				.getPreferentialType())
				&& Constant.CONTRACT_PREFERENTIALTYPE_PRICE_REBATE
				.equals(oldContract.getPreferentialType())
				&& !newContract.getPreferential().equals(
						oldContract.getPreferential())) {
			return true;
		}
		//都是越发越送，则两者保价和代码不一样，则修改过零担信息
		if (Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(newContract
				.getPreferentialType())
				&& Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND
				.equals(oldContract.getPreferentialType())
				&& !(oldContract
						.getPreferential()
						.getAgentgathRate()
						.equals(newContract.getPreferential()
								.getAgentgathRate()) && (oldContract
										.getPreferential().getInsuredPriceRate()
										.equals(newContract.getPreferential()
												.getInsuredPriceRate())))) {
			return true;
		}
		
		return false;
	}
	/**
	 * 
	 * <p>
	 * Description:修改快递条款<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-26
	 * @param newContract
	 * @param oldContract
	 * @return
	 * boolean
	 */
	public static boolean modifyExpressContract(Contract newContract,
			Contract oldContract) {
		if (null==oldContract) {
			return true;
		}
		if (!compare(newContract.getExPayWay(),oldContract.getExPayWay())) {
			return true; 
		}
		if (!compare(newContract.getExPreferentialType(),oldContract.getExPreferentialType())) {
			return true; 
		}
		
		//都是价格折扣，则两者折扣不一致，则修改过零担信息
		if (Constant.CONTRACT_PREFERENTIALTYPE_PRICE_REBATE.equals(newContract
				.getExPreferentialType())
				&& Constant.CONTRACT_PREFERENTIALTYPE_PRICE_REBATE
				.equals(oldContract.getExPreferentialType())
				&& !newContract.getExPreferential().equals(
						oldContract.getExPreferential())) {
			return true;
		}
		//都是越发越送，则两者保价和代码不一样，则修改过零担信息
		if (Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE.equals(newContract
				.getExPreferentialType())
				&& Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE
				.equals(oldContract.getExPreferentialType())
				&& !(oldContract
						.getExPreferential()
						.getAgentgathRate()
						.equals(newContract.getExPreferential()
								.getAgentgathRate()) && (oldContract
										.getExPreferential().getInsuredPriceRate()
										.equals(newContract.getExPreferential()
												.getInsuredPriceRate())))) {
			return true;
		}
		return false;
	}
	 
	 /**
	  * 
	  * <p>
	  * Description:是否修改了快递和零担的共同合同信息<br />
	  * </p>
	  * @author pgj
	  * @version 0.1 2013-9-26
	  * @param newContract
	  * @param oldContract
	  * @param type true 表示 CRM操作调用  false表示封装合同工作流对象调用
	  * @return boolean
	  * 业务场景：
	  * 1.对一个有效的合同新签,只改了结算额度,价格版本时间不变
	  * 2.一个合同只有零担的优惠信息(月结),对它新签，添加了快递优惠信息(月结),也改了结算额度
	  * 零担的价格版本时间不变
	  * 反之 一个合同只有快递的优惠信息
	  */
	public static boolean modifyContractCommon(Contract newContract,
			Contract oldContract,boolean type) {
		if (null==oldContract) {
			return true;
		}
		//合同到期时间更改
		if (null==newContract.getContractendDate()&&null==newContract.getContractendDate()) {
			return true; 
		}else if (newContract.getContractendDate().after(oldContract.getContractendDate())) {
			return true;
		}
		if (type == false ) {
			//结款额度不一致
			if (null==newContract.getArrearaMount()&&null==newContract.getArrearaMount()) {
				return true; 
			}else if (!newContract.getArrearaMount().equals(oldContract.getArrearaMount())) {
				return true;
			}
		}
		//客户全称不一致
		if (null==newContract.getCustCompany()&&null==newContract.getCustCompany()) {
			return true; 
		}else if (!newContract.getCustCompany().equals(oldContract.getCustCompany())) {
			return true;
		}
		//协议联系人id
		if (null==newContract.getContactId()&&null==newContract.getContactId()) {
			return true; 
		}else if (!newContract.getContactId().equals(oldContract.getContactId())) {
			return true;
		}
		//所属子公司
		if (null==newContract.getContractSubject()&&null==newContract.getContractSubject()) {
			return true; 
		}else if (!newContract.getContractSubject().equals(oldContract.getContractSubject())) {
			return true;
		}
		//结款日期
		if (newContract.getResultDate()!=oldContract.getResultDate()) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * <p>
	 * Description:比较字符串<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param str1
	 * @param str2
	 * @return
	 * boolean
	 */
	public static boolean compare(String str1,String str2){
		if (str1==null&&str2==null) {
			return true;
		}else if (str1==null&&"".equals(str2)) {
			return true;
		}else if ("".equals(str1)&&null==str2) {
			return true;
		}else{
			return str1.equals(str2);
		}
	}
	/**
	 * 
	 * <p>
	 * Description:比较double<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param d1
	 * @param d2
	 * @return
	 * boolean
	 */
	public static boolean compareDouble(Double d1,Double d2){
		if (null==d1&&null==d2) {
			return true;
		}else if(null==d1&&0==d2){
			return true;
		}else if(0==d1&&null==d2){
			return true;
		}else{
			return d1.equals(d2);
		}
	}
	/**
	 * 
	* @Title: nextMonthOneDay
	* @Description: 返回下个月1号的00:00：00
	* @author chenaichun 
	* @param @param date
	* @param @return    设定文件
	* @date 2013-11-19 下午5:42:38
	* @return Date    返回类型
	* @throws
	* @update 2013-11-19 下午5:42:38
	 */
	public static Date nextMonthOneDay(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);    //得到下一个月
		cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date minDate=new Date(cal.getTimeInMillis());
//		System.out.println(minDate);
		return minDate;
	}
	public static Date nextMonthDaySecond(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);    //得到下一个月
		cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date minDate=new Date(cal.getTimeInMillis()-1);
		return minDate;
	}
	/**
	 * 
	 * <p>
	 * Description:分别得到新旧合同<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-19
	 * @param logs
	 * @return
	 * Map<String,Contract>
	 */
	public static Map<String, Contract> getBeforeAndNewContract(List<ContractOperatorLog> logs) {
		Contract beforeContract = null;
		Contract mContract = null;
		Map<String, Contract> contracts = new HashMap<String, Contract>();
		
		if (null == logs || 2 != logs.size()) {
			throw new RuntimeException("workflow Data exception!");
		}

		Contract con1 = logs.get(0).getContract();
		Contract con2 = logs.get(1).getContract();

		if (con1 == null || con2 == null) {
			throw new RuntimeException(
					"can't find before contract and new contract !!");
		}
		//根据合同编码确定新旧合同
		if (null!=con1.getBeforeContractNum()&&con1.getBeforeContractNum().equals(con2.getContractNum())) {
			mContract = con1;
			beforeContract = con2;
		} else if (null!=con2.getBeforeContractNum()&& con2.getBeforeContractNum().equals(con1.getContractNum())) {
			mContract = con2;
			beforeContract = con1;
		}
//		//改签必须能找到新旧合同
//		if (mContract == null || beforeContract == null) {
//			throw new RuntimeException(
//					"can't find before contract and new contract !!");
//		}
//		if (!Constant.CONTRACT_STATUS_INPROCESS.equals(mContract
//				.getContractStatus())) {
//			throw new RuntimeException(
//					"newContract status must be  inprocess!!");
//		}
		contracts.put("bConctract", beforeContract);
		contracts.put("mContract", mContract);
		
		return contracts;
	}
	
	/**
	 * 
	 * @description 判断修改合同信息是否需要OA工作流.  
	 * 				未修改结算限额且优惠类型是月发月送返回true不需要走工作流
	 * @author 潘光均
	 * @version 0.1 2012-9-26
	 * @param Contract
	 *@date 2012-9-26
	 * @return boolean
	 * @update 2012-9-26 上午10:05:39
	 */
	public static boolean isUpdateNotNeedNotOAWorkFlow(Contract contract) {
			Assert.notNull(contract,"contract can't' null");
			//前台传过来的结算金额如果为空，则设置为0，避免空指针
			if (null==contract.getArrearaMount()) {
				contract.setArrearaMount(0d);
			}
			//如果后台查询出来的是空，设置为0，避免空指针
			if (null==contract.getOldContract().getArrearaMount()) {
				contract.getOldContract().setArrearaMount(0d);
			}
			//只要改变了结算金额，就需要走工作流
			if (null!=contract.getOldContract()) {
				if (!contract.getArrearaMount().equals(contract.getOldContract().getArrearaMount())) {
					return false;
				}
			}else{
				throw new ContractException(ContractExceptionType.ContractDataError);
			}
			return contract.getPreferentialType().equals(
							Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND)
				    && contract.getPreferential().getReceivePriceRate() == null	
				    && contract.getPreferential().getAgentgathRate() == 1d	
				    && contract.getPreferential().getChargeRebate() == 1d	
				    && contract.getPreferential().getDeliveryPriceRate() == 1d	
					&& contract.getPreferential().getInsuredPriceRate() == 1d	
					;
	}
	/**
	 * 
	 * @description 判断新增合同信息是否需要OA工作流.  
	 * @author 潘光均
	 * @version 0.1 2012-9-26
	 * @param Contract
	 *@date 2012-9-26
	 * @return boolean
	 * @update 2012-9-26 上午10:05:39
	 */
	public static boolean isNewNotNeedNotOAWorkFlow(Contract contract) {
		if (contract.hasLttInfo()) {
			if (!StringUtils.isEmpty(contract.getPayWay())
					&& !Constant.CONTRACT_PAYWAY_NOT_MONTH_END.equals(contract
							.getPayWay())) {
				return false;
			}
			if (!StringUtils.isEmpty(contract.getPreferentialType())
					&& !Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND
							.equals(contract.getPreferentialType())
					&& !Constant.CONTRACT_PREFERENTIALTYPE_NOT_PREFERENTIAL
							.equals(contract.getPreferentialType())) {
				return false;
			}
		}
		if (contract.hasExpressInfo()) {
			if (!StringUtils.isEmpty(contract.getExPayWay())
					&& !Constant.CONTRACT_PAYWAY_NOT_MONTH_END.equals(contract
							.getExPayWay())) {
				return false;
			}
			if (!StringUtils.isEmpty(contract.getExPreferentialType())
					&& !Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE
							.equals(contract.getExPreferentialType())
					&& !Constant.CONTRACT_PREFERENTIALTYPE_NOT_PREFERENTIAL
							.equals(contract.getExPreferentialType())) {
				return false;
			}
		}
		
		if (null==contract.getExPreferential()) {
			return true;
		}else{
			return contract.getExPreferential().getAgentgathRate() == 1d
					&& contract.getExPreferential().getInsuredPriceRate() == 1d;
		}
		
	}
	/**
	 * 
	 * <p>
	 * Description:比较是否只修改税务标记了<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-27
	 * @param newContract
	 * @param oldContract
	 * @return boolean
	 */
	public static boolean checkOnlyModilfy(Contract newContract, Contract oldContract) {
		if (null == newContract || null == oldContract) {
			throw new ContractException(ContractExceptionType.Data_Error);
		}
		if (null != newContract.getArrearaMount()
				&& !newContract.getArrearaMount().equals(
						oldContract.getArrearaMount())) {
			return false;
		}
		if (null != newContract.getPreferentialType()
				&& !newContract.getPreferentialType().equals(
						oldContract.getPreferentialType())) {
			return false;
		}
		if (null != newContract.getExPreferentialType()
				&& !newContract.getExPreferentialType().equals(
						oldContract.getExPreferentialType())) {
			return false;
		}
		if (null == newContract.getContractTaxList()
				|| null == oldContract.getContractTaxList()) {
			throw new ContractException(ContractExceptionType.Data_Error);
		}
		if (null == newContract.getNewContractTax()
				&& null == oldContract.getNewContractTax()) {
			throw new ContractException(ContractExceptionType.Data_Error);
		}
		if (Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(newContract
				.getPreferentialType())
				&& !newContract.getPreferential().monthSendEquals(
						oldContract.getPreferential())) {
			return false;
		}
		if (!Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(newContract
				.getPreferentialType())
				&& !newContract.getPreferential().equals(
						oldContract.getPreferential())) {
			return false;
		}
		if (Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(newContract
				.getExPreferentialType())
				&& !newContract.getExPreferential().monthSendEquals(
						oldContract.getExPreferential())) {
			return false;
		}
		if (!Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(newContract
				.getExPreferentialType())
				&& !newContract.getExPreferential().equals(
						oldContract.getExPreferential())) {
			return false;
		}
		return !ContractUtil.changeListToContractTax(oldContract).getInvoiceType().equals(
				newContract.getNewContractTax().getInvoiceType());
	}
	/**
	 * 
	 * <p>
	 * Description:比较两个对象是否相等<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param a
	 * @param b
	 * @return
	 * boolean
	 */
	public static boolean isTwoObjEqual(Double a,Double b){
		if(a!=null&&b!=null){
			return a.equals(b);
		}
		else if(a ==null &&b==null){
			return true;
		}
		return false;
	}
}
