/**
 * @description
 * @author 赵斌
 * @2012-3-28
 * @return
 */
package com.deppon.crm.module.customer.server.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractCondition;
import com.deppon.crm.module.customer.shared.domain.ContractDebtDay;
import com.deppon.crm.module.customer.shared.domain.ContractDept;
import com.deppon.crm.module.customer.shared.domain.ContractDto;
import com.deppon.crm.module.customer.shared.domain.ContractMonthendDay;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.customer.shared.domain.ContractTax;
import com.deppon.crm.module.customer.shared.domain.CustMonthSum;
import com.deppon.crm.module.customer.shared.domain.Preferential;

/**   
 * <p>
 * Description:合同信息dao接口<br />
 * </p>
 * @title IContractDao.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author 李国文
 * @version 0.1 2013-2-26
 */

public interface IContractDao 
{
	/**
	 * 
	 * @合同创建
	 * @author 赵斌
	 * @2012-3-28
	 * @return Contract
	 * @param contract 合同
	 */
	public Contract createContract(Contract contract);
	
	/**
	 * @合同对应部门创建
	 * @author 赵斌
	 * @2012-3-28
	 * @return Contract
	 * @param contract 合同
	 */
	public ContractDept createContractDept(ContractDept contractDept);
	
	/**
	 * 
	* @Title: createContractTax
	* @Description: 合同税务标记信息创建
	* @author chenaichun 
	* @param @param contractTax
	* @param @return    设定文件
	* @date 2013-11-16 下午3:15:36
	* @return ContractTax    返回类型
	* @throws
	* @update 2013-11-16 下午3:15:36
	 */
	public boolean createContractTax(ContractTax contractTax);
	/**
	 * 
	* @Title: updateContractTax
	* @Description: 合同税务信息修改
	* @author chenaichun 
	* @param @param contractTax
	* @param @return    设定文件
	* @date 2013-11-25 下午7:26:09
	* @return boolean    返回类型
	* @throws
	* @update 2013-11-25 下午7:26:09
	 */
	public boolean updateContractTax(ContractTax contractTax);
	
	/**
	 * 
	* @Title: deleteContractTax
	* @Description: 删除税务标记信息
	* @author chenaichun 
	* @param @param contractTax
	* @param @return    设定文件
	* @date 2013-11-26 上午9:45:26
	* @return boolean    返回类型
	* @throws
	* @update 2013-11-26 上午9:45:26
	 */
	public boolean deleteContractTax(String id);
	/**
	 * 
	 * @合同优惠信息创建
	 * @author 赵斌
	 * @2012-3-28
	 * @return Preferential
	 * @param Preferential 合同优惠信息
	 */
	public Preferential createPreferential(Preferential preferential);
	
	/**
	 * 
	 * @修改合同信息
	 * @author 赵斌
	 * @2012-4-12
	 * @param Contract 合同
	 * @return boolean
	 */
	public boolean updateContract(Contract contract);
	
	/**
	 * 
	 * @修改合同部门
	 * @author 赵斌
	 * @2012-4-12
	 * @param ContractDept 合同部门
	 * @return boolean
	 */
	public boolean updateContractDept(ContractDept contractDept);
	
	
	/**
	 * 根据ID查询出合同主体信息
	 * @description
	 * @author 赵斌
	 * @2012-4-16
	 * @return
	 */
	public Contract getContract(String id);
	
	/**
	 * 
	 * @根据查询条件返回合同集合
	 * @author 赵斌
	 * @2012-4-18
	 * @return List
	 * @param condition 合同查询条件
	 * @param start 数据开始下标
	 * @param limit 获取数据长度
	 */
	public List<Contract> searchContract(ContractCondition condition,int start, int limit);
	
	
	/**
	 * 
	 * @查询出满足条件的合同总数
	 * @author 赵斌
	 * @2012-4-19
	 * @return
	 */
	public int countContract(ContractCondition condition);
	
	/**
	 * 
	 * @查询当前部门是否可以做到达
	 * @author 赵斌
	 * @2012-4-27
	 * @return String
	 * @param deptId 部门Id
	 */
	public String getArrive(String deptId);
	
	/**
	 * 
	 * @查询当前部门是否可以做出发
	 * @author 赵斌
	 * @2012-4-27
	 * @return String
 	 * @param deptId 部门Id
	 */
	public String getLeave(String deptId);
	
	/**
	 * 
	 * <p>
	 * 根据年份，月份，客户编码查询发货金额<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-4
	 * @param years
	 * @param months
	 * @param custNumber
	 * @return
	 * List<String>
	 */
	public List<Map<String,String>> getDeliverMoneyByCondtion(Date beginDate,Date endDate,String custNumber);

	/**
	 * @description 生成序列id.  
	 * @author 潘光均
	 * @version 0.1 2012-6-28
	 * @param 
	 *@date 2012-6-28
	 * @return String
	 * @update 2012-6-28 下午3:50:47
	 */
	public String getSeqIdByTableName(String tableName);
	
	/**
	 * <p>
	 * Description:通过表的名字 得到序列ID<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param tableName
	 * @return
	 * String
	 */
	public String getSeqIdByTableName4Log(String tableName);

	/**
	 * @description 保存合同操作记录.  
	 * @author 潘光均
	 * @version 0.1 2012-6-28
	 * @param 
	 *@date 2012-6-28
	 * @return void
	 * @update 2012-6-28 下午8:01:30
	 */
	public void createOperatorLog(ContractOperatorLog log); 
	
	/**
	 * @description 通过工作流id获取合同操作记录.  
	 * @author 潘光均
	 * @version 0.1 2012-6-28
	 * @param 
	 *@date 2012-6-28
	 * @return List<ContractOperatorLog>
	 * @update 2012-6-28 下午8:41:34
	 */
	public List<ContractOperatorLog> searchContractOperatorLogs(ContractOperatorLog log);
	
	/**
	 * @description 修改历史操作记录
	 * @author 潘光均
	 * @version 0.1 2012-6-28
	 * @param ContractOperatorLog 
	 *@date 2012-6-28
	 * @return void
	 * @update 2012-6-28 下午8:41:34
	 */
	public void updateContractOperaLog(ContractOperatorLog modifyLog);
	
	/**
	 * @删除合同部门
	 * @author 赵斌
	 * @2012-3-30
	 * @return
	 */
	void deleteContractDept(String id);
	
	/**
	 * @根据合同id查询合同全部信息
	 * @param contractId
	 * @return
	 */
	Contract getAllContractInfoById(String contractId);
	
	/**
	 * @根据合同id查询合同历史操作记录
	 * @author 赵斌
	 * @2012-3-30
	 * @return
	 */
	List<ContractDept> searchContractDeptByContractId(String contractid);

	/**
	 * @description 通过合同id和部门id删除合同.  
	 * @author 潘光均
	 * @version 0.1 2012-7-3
	 * @param 
	 *@date 2012-7-3
	 * @return boolean
	 * @update 2012-7-3 上午10:02:46
	 */
	public boolean deleteContractDept(String contractId, String deptId); 
	
	/**
	 * 
	 * @description 根据合同id查询合同的操作记录.  
	 * @author 潘光均
	 * @version 0.1 2012-7-3
	 * @param String :合同id
	 *@date 2012-7-3
	 * @return List<ContractOperatorLog>
	 * @update 2012-7-3 下午4:41:44
	 */
	public List<ContractOperatorLog> getContractOperatorLogByContractId(String contractId);
	
	/**
	 * 根据条件查询360的合同信息 ----- userId,modifyId 为 employee Id
	 * @param condition
	 * @return
	 */
	public List<Contract> queryContract_360ByCondition(ContractCondition condition);
	
	/**
	 * 根据合同id和部门id查询合同部门
	 * @param contractId
	 * @param deptId
	 * @return
	 */
	public ContractDept getContractDeptByDeptId(String contractId, String deptId);

	/**
	 * @description 根据合同id查询合同部门.  
	 * @author 潘光均
	 * @version 0.1 2012-7-10
	 * @param 
	 *@date 2012-7-10
	 * @return List<ContractDept>
	 * @update 2012-7-10 下午7:01:37
	 */
	public List<ContractDept> queryContractDeptByContractId(String id);
	
	/**
	 * 
	 * @description 作废过期合同.  
	 * @author 潘光均
	 * @version 0.1 2012-7-10
	 * @param 
	 *@date 2012-7-10
	 * @return void
	 * @update 2012-7-10 下午2:56:03
	 */
	public void cancelTimeOutContract(String status,Date endDate);

	/**
	 * @description 生效待生效合同.  
	 * @author patern
	 * @version 0.1 2012-8-17
	 * @param b true or false   
	 *@date 2012-8-17
	 * @return none
	 * @update 2012-8-17 上午9:48:17
	 */
	void effectContract(String status, Date endDate);

	/**
	 * @description 根据id删除合同信息.  
	 * @author patern
	 * @version 0.1 2012-8-17
	 * @param b true or false   
	 *@date 2012-8-17
	 * @return none
	 * @update 2012-8-17 上午10:01:23
	 */
	public void deleteContractById(String id);

	/**
	 * @description 插入删除的合同id.  
	 * @author patern
	 * @version 0.1 2012-8-20
	 * @param b true or false   
	 *@date 2012-8-20
	 * @return none
	 * @update 2012-8-20 下午2:52:25
	 */
	public void insertDeletedContractId(String id);

	/**
	 * @description 通过合同部门绑定部门查询合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-7
	 * @param 
	 *@date 2012-9-7
	 * @return List<Contract>
	 * @update 2012-9-7 下午5:32:45
	 */
	List<Contract> queryContractByContractDeptIds(List<String> deptIds);

	/**
	 * 
	 * @description 批量删除无效合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-10
	 * @param 
	 *@date 2012-9-10
	 * @return void
	 * @update 2012-9-10 下午2:38:49
	 */
	public void batchDeleteUneffectContract(List<String> contractIds);

	/**
	 * @description 查询N天内到期的合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-19
	 * @param 
	 *@date 2012-9-19
	 * @return List<Contract>
	 * @update 2012-9-19 上午11:21:19
	 */
	public List<Contract> queryUneffectContractIn30Days(int day);
	
	/**
	 * @description 合同改签通过客户查询客户合同（包括合同部门）.  
	 * @author 李学兴
	 * @version 0.1 2012-9-26
	 * @param 
	 *@date 2012-9-26
	 * @return List<Contract>
	 * @update 2012-9-26 上午11:21:19
	 */
	public List<Contract> queryContractWithDeptByCondition(ContractCondition condition);

	/**
	 * @description 通过合同id查询该合同客户所有的待生效合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-27
	 * @param String :合同id
	 *@date 2012-9-27
	 * @return List<Contract>
	 * @update 2012-9-27 上午11:01:05
	 */
	public List<Contract> queryWaitEffectContractByContractId(String id);

	/**
	 * @description 通过实践查询待生效合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-27
	 * @param 
	 *@date 2012-9-27
	 * @return void
	 * @update 2012-9-27 下午2:23:32
	 */
	public List<Contract> queryAllWaitEffectContractByTime(Date date);

	/**
	 * @description 终止待生效合同.  
	 * @author 李学兴
	 * @version 0.1 2012-9-27
	 * @param 
	 *@date 2012-9-27
	 * @return void
	 * @update 2012-9-27 下午2:23:32
	 */
	public void updateContract4Wait(Contract modifyContract);

	/**
	 * @description 根据客户id查询客户合同的所有信息.  
	 * @author 潘光均
	 * @version 0.1 2012-10-16
	 * @param 
	 *@date 2012-10-16
	 * @return List<Contract>
	 * @update 2012-10-16 上午11:02:35
	 */
	public List<Contract> queryContractAllInfoByCustId(String custId);
	
		/**
	 * <p>
	 * Description:找出有业务操作的同类型月结合同天数的合同(除了无效) <br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-10
	 * @param debtDay
	 * @return
	 * List<Contract>
	 */
	public List<Contract> getSameTypeContractByDebtDay(int debtDay);
	
	/**
	 * <p>
	 * Description:修改指定某一个合同的合同月结天数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-9
	 * @param contract
	 * void
	 */
	public void updateContractDebtDayById(Contract contract);
	
	/**
	 * <p>
	 * Description:通用权限,查询全部合同月结天数管理<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-8
	 * @return
	 * List<ContractDebtDay>
	 */
	public List<ContractMonthendDay> searchContractMonthendDays(String status);
	
	/**
	 * <p>
	 * Description:通用权限。通过合同月结天数名字查询合同月结天数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-8
	 * @return
	 * ContractDebtDay
	 */
	public ContractMonthendDay searchContractMonthendDayByName(String contractDebtDaysName);
	
	/**
	 * <p>
	 * Description:通用权限，修改合同月结天数
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-8
	 * void
	 */
	public void updateContractMonthEnd(ContractMonthendDay contractDebtDay);
	
	/**
	 * <p>
	 * Description:通用权限。通过id查询合同月结天数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-8
	 * @return
	 * ContractDebtDay
	 */
	public ContractMonthendDay getContractMonthendDayById(String id);
	
	/**
	 * <p>
	 * Description:通过ID得到中间表 合同欠款天数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-9
	 * @param id
	 * @return
	 * ContractDebtDay
	 */
	public ContractDebtDay getContractDebtDayById(String id);
	
	/**
	 * Description:通用权限 删除无效的合同月结天数<br />
	 * @version 0.1 2013-5-15
	 * @param status
	 * void
	 */
	public void deleteCommonContractDebtDay(String status);
	
	/**
	 * Description:通用权限   保存合同月结天数<br />
	 * @version 0.1 2013-5-15
	 * @param contractMonthendDay
	 * void
	 */
	public void saveCommonContractDebtDay(ContractMonthendDay contractMonthendDay);
	/**
	 * Description:通过客户编码查询 合同欠款天数<br />
	 * @version 0.1 2013-1-9
	 * @param custNum
	 * @return
	 * ContractDebtDay
	 */
	public ContractDebtDay getContractDebtByCustNum(String custNum);
	
	/**
	 * Description:修改合同欠款天数<br />
	 * @version 0.1 2013-1-9
	 * @param contractDebtDay
	 * void
	 */
	public void updateContractDebtDay(ContractDebtDay contractDebtDay);
	
	/**
	 * Description:得到 合同欠款天数中的  合同欠款剩余天数都为同一天 的客户编码<br />
	 * @version 0.1 2013-1-10
	 * @return
	 * List<ContractDebtDay>
	 */
	public List<ContractDebtDay> getContractDebtsForDay(int day);
	
	/**
	 * Description:将合同欠款剩余天数 >day 的全部减去1<br />
	 * @version 0.1 2013-1-10
	 * void
	 */
	public void updateAllContractDebt(int day);
	
	/**
	 * Description:保存中间表，合同欠款天数数据<br />
	 * @version 0.1 2013-1-17
	 * @param contractDebtDay
	 * void
	 */
	public void saveContractDebt(ContractDebtDay contractDebtDay);
	
	/**
	 * Description:通过客户编码查询出合同的基本信息<br />
	 * @version 0.1 2013-1-17
	 * @param custNum
	 * @return
	 * List<Contract>
	 */
	public List<Contract> getContractsBasicInfoByCustNum(String custNum);

	/**
	 * @description 查询有效的月发月送合同.  
	 * @author 潘光均
	 * @version 0.1 2013-3-12
	 * @param ContractCondition:查询条件
	 * @param start :开始条数
	 * @param limit:查询条数
	 *@date 2013-3-12
	 * @return List<ContractDto>
	 * @update 2013-3-12 上午9:59:27
	 */
	public List<ContractDto> queryEffectMonthSendContract(
			ContractCondition condtion, int start, int limit);

	/**
	 * @description 更新折扣信息.  
	 * @author 潘光均
	 * @version 0.1 2013-3-12
	 * @param 
	 *@date 2013-3-12
	 * @return Preferential
	 * @update 2013-3-12 上午11:36:57
	 */
	public void updatePreferential(Preferential preferential);

	/**
	 * @description 查询客户出空运和整车之外的近三个月发货金额.  
	 * @author 潘光均
	 * @version 0.1 2013-3-12
	 * @param 
	 *@date 2013-3-12
	 * @return List<CustMonthSum>
	 * @update 2013-3-12 下午1:52:08
	 */
	public List<CustMonthSum> getCustMonthSums(String id, Date beginDate,
			Date endDate);

	/**
	 * @description 根据合同id删除合同对应的折扣信息.  
	 * @author 潘光均
	 * @version 0.1 2013-3-19
	 * @param 
	 *@date 2013-3-19
	 * @return void
	 * @update 2013-3-19 下午2:39:47
	 */
	public void deletePreferential(String contractId);
	/**
	 * <p>
	 * Description:得到某一时间端的合同操作记录<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-31
	 * @param begin
	 * @param end
	 * @return
	 * List<ContractOperatorLog>
	 */
	public List<ContractOperatorLog> queryContractOperatorLogsForDate(Date begin, Date end);
	
	/**
	 * @description 查詢进入合同界面初始化合同--查询本部门30天内要到期的合同 
	 * @author 唐亮
	 * @version 0.1 2013-2-25
	 * @param contractCondition：查询条件
	 * @param start :开始条数
	 * @param limit:页数大小
	 *@date 2013-2-25
	 * @return List<Contract>
	 * @update 2013-2-25 上午9:35:51
	 */
	public List<Contract> searchInitContract(
			ContractCondition contractCondition, int start, int limit);

	/**
	 * @description 计算初始化总条数.  
	 * @author 潘光均
	 * @version 0.1 2013-3-20
	 * @param 
	 *@date 2013-3-20
	 * @return long
	 * @update 2013-3-20 上午9:59:33
	 */
	public int countInitContract(ContractCondition contractCondition);
	
	/**
	 * Description:修改带生效和没有业务操作的合同月结天数<br />
	 * @version 0.1 2013-5-14
	 * @param day
	 * void
	 */
	public void updateOperationContractMonthDay(int newday,int oldDay);
		/**
	 * 
	 * @Title: updateDuningDept
	 *  <p>
	 * @Description: 修改合同的催款部门
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-5-8
	 * @return void
	 * @throws
	 */
	public void updateDuningDept(String dunningDeptCode,Boolean ifForeignGoods,String contractId);
	
	/**
	 * @Description:价格版本设置<br />
	 * @author CoCo
	 * @version 0.1 2013-8-6
	 * @param priceVersionDate
	 * @param exPriceVersionDate 
	 * @param contractId
	 * void 
	 */
	public void modifyPriceVersionDate(Date priceVersionDate,Date exPriceVersionDate, String contractId);
	
	/**
	 * @Description:得到客户已用额度小于可使用额度20%的客户编码和部门id<br />
	 * @author CoCo
	 * @version 0.1 2013-8-22
	 * @return
	 * List<Map<String,String>>
	 */
	public List<Map<String, String>> getCustInfoForArreaAmoutMessage();

	/**
	 * <p>
	 * Description:根据客户id获取客户近三个月发货金额<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-22
	 * @param custId
	 * @return
	 * List<CustMonthSum>
	 */
	public List<CustMonthSum> getExCustMonthSumsByCustId(String custId, Date beginDate,
			Date endDate);

	/**
	 * <p>
	 * Description:获取快递近三个月发货情况 <br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-23
	 * @param beginDate
	 * @param endDate
	 * @param custId
	 * @return
	 * List<Map<String,String>>
	 */
	List<Map<String, String>> getExDeliverMoneyByCondtion(Date beginDate,
			Date endDate, String custId);
}
