 /**
 * @description
 * @author 赵斌
 * @2012-3-28
 * @return
 */
package com.deppon.crm.module.customer.server.service;

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
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Preferential;

/**
 * @author 赵斌
 * 
 */
public interface IContractService {

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
	 * 
	 * @合同对应部门创建
	 * @author 赵斌
	 * @2012-3-28
	 * @return Contract
	 * @param contract
	 *            合同
	 */
	public ContractDept createContractDept(ContractDept contractDept);

	/**
	 * 
	* @Title: createContractTax
	* @Description: 合同的税务标记创建
	* @author chenaichun 
	* @param @param contractTax
	* @param @return    设定文件
	* @date 2013-11-16 下午3:11:25
	* @return ContractTax    返回类型
	* @throws
	* @update 2013-11-16 下午3:11:25
	 */
	public boolean createContractTax(ContractTax contractTax);
	
	/**
	 * 
	* @Title: updateContractTax
	* @Description: 合同税务标记修改
	* @author chenaichun 
	* @param @param contractTax
	* @param @return    设定文件
	* @date 2013-11-25 下午7:25:14
	* @return boolean    返回类型
	* @throws
	* @update 2013-11-25 下午7:25:14
	 */
	public boolean updateContractTax(ContractTax contractTax);
	
	/**
	 * 
	* @Title: deleteContractTax
	* @Description: 删除合同税务标记信息
	* @author chenaichun 
	* @param @param contractTax
	* @param @return    设定文件
	* @date 2013-11-26 上午9:42:41
	* @return boolean    返回类型
	* @throws
	* @update 2013-11-26 上午9:42:41
	 */
	public boolean deleteContractTax(String id);
	/**
	 * 
	 * @合同优惠信息创建
	 * @author 赵斌
	 * @2012-3-28
	 * @return Preferential
	 * @param Preferential
	 *            合同优惠信息
	 */
	public Preferential createPreferential(Preferential preferential);
	/**
	 * 
	 * @合同优惠信息修改
	 * @author 潘光均
	 * @2013-3-12
	 * @return Preferential
	 * @param Preferential
	 *            合同优惠信息
	 */
	public void updatePreferential(Preferential beforePreferential,Preferential afterPreferential);
	/**
	 * 
	 * @根据ID查询出合同主体信息
	 * @author 赵斌
	 * @2012-4-16
	 * @return
	 */
	public Contract getContract(String id);

	/**
	 * 根据合同ID、部门ID查询出合同部门
	 * 
	 * @description
	 * @author 赵斌
	 * @2012-4-17
	 * @return ContractDept
	 */
	public ContractDept getContractDeptByDeptId(String contractId, String deptId);

	/**
	 * 
	 * @修改合同部门
	 * @author 赵斌
	 * @2012-4-17
	 * @param ContractDept
	 *            合同部门
	 * @return boolean
	 */
	public boolean updateContractDept(ContractDept contractDept);

	/**
	 * @修改合同信息
	 * @author 赵斌
	 * @2012-4-12
	 * @param Contract
	 *            合同
	 * @return boolean
	 */
	public boolean updateContract(Contract contract);
	
	/**
	 * @修改合同的基本信息
	 * @param Contract
	 *            合同
	 * @return boolean
	 */
	public boolean updateContractBaseInfo(Contract contract);

	/**
	 * 
	 * @根据查询条件返回合同集合
	 * @author 赵斌
	 * @2012-4-18
	 * @return List
	 * @param condition
	 *            合同查询条件
	 * @param start
	 *            数据开始下标
	 * @param limit
	 *            获取数据长度
	 */
	public List<Contract> searchContract(ContractCondition condition,
			int start, int limit);

	/**
	 * 
	 * @查询出满足条件的合同总数
	 * @author 赵斌
	 * @2012-4-19
	 * @return
	 */
	public int countContract(ContractCondition condition);

	/**
	 * <p>
	 * 根据会员Id查询管理合同信息<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param memberId
	 * @return List<Contract>
	 */
	public List<Contract> getContractsByMemberId(String memberId);
	
	/**
	 * 根据客户编码查询管理合同信息<br />
	 * @version 0.1 2012-4-27
	 * @param custNumber
	 * @return List<Contract>
	 */
	public List<Contract> getContractsByCustNumber(String custNumber);

	/**
	 * <p>
	 * 根据合同id查询合同信息<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param id
	 * @return Contract
	 */
	public Contract getContractDetailById(String id);

	/**
	 * 
	 * @查询当前部门是否可以做到达
	 * @author 赵斌
	 * @2012-4-27
	 * @return String
	 * @param deptId
	 *            部门Id
	 */
	public String getArrive(String deptId);

	/**
	 * 
	 * @查询当前部门是否可以做出发
	 * @author 赵斌
	 * @2012-4-27
	 * @return String
	 * @param deptId
	 *            部门Id
	 */
	public String getLeave(String deptId);

	/**
	 * 
	 * @根据会员ID查询会员信息
	 * @author 赵斌
	 * @2012-5-2
	 * @return Member
	 * @param id
	 *            会员Id
	 */
	public Member getMember(String id);

	/**
	 * 
	 * <p>
	 * 得到最近三个月发货情况的文本描述<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-9-3
	 * @param custId
	 * @return String
	 */
	public String getArrearaMountByCustId(String custId);

	/**
	 * 
	 * <p>
	 * 根据年份，月份，客户编码查询发货金额<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-6-4
	 * @param years
	 * @param months
	 * @param custNumber
	 * @return List<String>
	 */
	public List<Double> getDeliverMoneyByCondtion(Date beginDate, Date endDate,
			String custId);

	/**
	 * @description 取得合同Id.
	 * @author 潘光均
	 * @version 0.1 2012-6-28
	 * @param
	 * @date 2012-6-28
	 * @return String
	 * @update 2012-6-28 下午3:49:35
	 */
	public String getContractId();

	/**
	 * @description 取得操作记录id.
	 * @author 潘光均
	 * @version 0.1 2012-6-28
	 * @param
	 * @date 2012-6-28
	 * @return String
	 * @update 2012-6-28 下午5:18:19
	 */
	public String getConOperaLogSequence();

	/**
	 * @description 保存合同操作记录.
	 * @author 潘光均
	 * @version 0.1 2012-6-28
	 * @param
	 * @date 2012-6-28
	 * @return void
	 * @update 2012-6-28 下午7:59:52
	 */
	public void createContractOpeLog(ContractOperatorLog log);

	/**
	 * @description 通过工作流号获取合同操作记录.
	 * @author 潘光均
	 * @version 0.1 2012-6-28
	 * @param
	 * @date 2012-6-28
	 * @return List<ContractOperatorLog>
	 * @update 2012-6-28 下午8:40:22
	 */
	public List<ContractOperatorLog> searchOperaLogByWorkflowId(String wkid);

	/**
	 * @description 更新合同操作记录.
	 * @author 潘光均
	 * @version 0.1 2012-6-29
	 * @param
	 * @date 2012-6-29
	 * @return void
	 * @update 2012-6-29 上午10:57:49
	 */
	public void updateContractOperaLog(ContractOperatorLog modifyLog);

	/**
	 * @description 根据合同id查询合同信息，保存合同的附件，合同操作记录，合同部门等信息.
	 * @author 潘光均
	 * @version 0.1 2012-6-29
	 * @param
	 * @date 2012-6-29
	 * @return Contract
	 * @update 2012-6-29 上午11:06:18
	 */
	public Contract getAllContractInfoById(String contractId);

	/**
	 * @description 通过查询条件查询合同信息.
	 * @author 潘光均
	 * @version 0.1 2012-7-2
	 * @param
	 * @date 2012-7-2
	 * @return ContractCondition
	 * @update 2012-7-2 上午10:08:28
	 */
	public List<Contract> queryContractByCondition(ContractCondition condition);

	/**
	 * @description 根据合同id和合同部门id删除合同部门.
	 * @author 潘光均
	 * @version 0.1 2012-7-2
	 * @param contractId
	 *            ：合同id String：合同部门id
	 * @date 2012-7-2
	 * @return void
	 * @update 2012-7-2 上午10:37:23
	 */
	public boolean removeContractDept(String contractId, String deptId);

	/**
	 * <p>
	 * Description:根据合同id查询合同的操作记录<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param id
	 * @return
	 * List<ContractOperatorLog>
	 */
	public List<ContractOperatorLog> getContractOperatorLogByContractId(
			String id);

	/**
	 * @description 根据合同id查询合同部门.
	 * @author 潘光均
	 * @version 0.1 2012-7-10
	 * @param
	 * @date 2012-7-10
	 * @return List<ContractDept>
	 * @update 2012-7-10 下午7:00:48
	 */
	public List<ContractDept> queryContractDeptByContractId(String id);

	/**
	 * 
	 * @description 作废过期合同.
	 * @author 潘光均
	 * @version 0.1 2012-7-10
	 * @param
	 * @date 2012-7-10
	 * @return void
	 * @update 2012-7-10 下午2:56:03
	 */
	public void cancelTimeOutContract(String status, Date endDate);

	/**
	 * @description 生效合同.
	 * @author patern
	 * @version 0.1 2012-8-17
	 * @param b
	 *            true or false
	 * @date 2012-8-17
	 * @return none
	 * @update 2012-8-17 上午9:50:47
	 */
	void effectContract(String status, Date endDate);

	/**
	 * @description 根据id删除改签不同意的新合同信息.
	 * @author patern
	 * @version 0.1 2012-8-17
	 * @param b
	 *            true or false
	 * @date 2012-8-17
	 * @return none
	 * @update 2012-8-17 上午9:59:50
	 */
	public void deleteUneffectContractById(String id);

	/**
	 * 
	 * <p>
	 * 获得最近几个月的发货情况<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-9-3
	 * @param custId
	 * @param mouth
	 * @return List<Double>
	 */
	public List<Double> getLatelyDeliverMoney(String custId, int mouth);

	/**
	 * 
	 * @description 批量删除无效合同.
	 * @author 潘光均
	 * @version 0.1 2012-9-10
	 * @param
	 * @date 2012-9-10
	 * @return void
	 * @update 2012-9-10 下午2:38:49
	 */
	public void batchDeleteUneffectContract(List<String> contractIds);

	/**
	 * @description 查询N天内要到期的合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-19
	 * @param 
	 *@date 2012-9-19
	 * @return List<Contract>
	 * @update 2012-9-19 上午11:20:26
	 */
	public List<Contract> searchUneffectContractIn30Days(int day);

	/**
	 * @description 合同改签通过客户查询客户合同（包括合同部门）.  
	 * @author 李学兴
	 * @version 0.1 2012-9-26
	 * @param 
	 *@date 2012-9-26
	 * @return List<Contract>
	 * @update 2012-9-26 上午11:21:19
	 */
	public List<Contract> queryContractWithDeptById(String memberId);

	/**
	 * @description 通过原合同id查询改合同客户下的所有待生效合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-27
	 * @param String :合同id
	 *@date 2012-9-27
	 * @return List<Contract>
	 * @update 2012-9-27 上午11:00:00
	 */
	public List<Contract> getWaitEffectContractByContractId(String id);

	/**
	 * @description 通过实践查询待生效合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-27
	 * @param 
	 *@date 2012-9-27
	 * @return void
	 * @update 2012-9-27 下午2:22:39
	 */
	public List<Contract> searchAllWaitEffectContractByTime(Date date);

	/**
	 * @description 终止待生效合同.  
	 * @author 李学兴
	 * @version 0.1 2012-9-27
	 * @param 
	 *@date 2012-9-27
	 * @return void
	 * @update 2012-9-27 下午2:22:39
	 */
	public void updateContract4Wait(Contract modifyContract);

	/**
	 * @description 根据客户id查询客户合同的所有信息.  
	 * @author 潘光均
	 * @version 0.1 2012-10-16
	 * @param 
	 *@date 2012-10-16
	 * @return List<Contract>
	 * @update 2012-10-16 上午11:01:30
	 */
	public List<Contract> searchContractAllInfoByCustId(String id);
	/**
	 * <p>
	 * Description:通用权限,查询全部合同月结天数管理<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-8
	 * @return
	 * List<ContractDebtDay>
	 */
	public List<ContractMonthendDay> searchCommonContractMonthendDay(String status);
	
	/**
	 * Description:通用权限。通过id查询合同月结天数<br />
	 * @author 李国文
	 * @version 0.1 2013-1-8
	 * @return
	 * ContractDebtDay
	 */
	public ContractMonthendDay getContractMonthendDayById(String id);
	/**
	 * Description:通用权限。通过合同月结天数名字查询合同月结天数<br />
	 * @version 0.1 2013-1-8
	 * @return
	 * ContractDebtDay
	 */
	public ContractMonthendDay searchContractMonthendDayByName(String contractDebtDaysName);
	
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
	 * <p>
	 * Description:通用权限，修改合同月结天数，修改后 特殊权限 除了合同为无效 的全部都要修改。
	 * 	通用权限修改 此处需要判断是否可以将月结天数变小，
	 * 因为变小后 有效合同的欠款天数 > 合同月结天数 有效合同的可使用额度可能会清零<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-8
	 * void
	 */
	public void updateContractMonthEnd(ContractMonthendDay contractDebtDay);

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
	 * <p>
	 * Description:通过客户编码查询 合同欠款天数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-9
	 * @param custNum
	 * @return
	 * ContractDebtDay
	 */
	public ContractDebtDay getContractDebtByCustNum(String custNum);
	
	/**
	 * <p>
	 * Description:修改合同欠款天数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-9
	 * @param contractDebtDay
	 * void
	 */
	public void updateContractDebtDay(ContractDebtDay contractDebtDay);
	
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
	 * Description:有业务操作的 找出同类型月结合同天数的合同(除了无效)<br />
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
	 * Description:得到 合同欠款天数中的  合同欠款剩余天数都为同一天 的客户编码<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-10
	 * @return
	 * List<ContractDebtDay>
	 */
	public List<ContractDebtDay> getContractDebtsForDay(int day);
	
	/**
	 * <p>
	 * Description:将合同欠款剩余天数 >1 的全部减去1<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-10
	 * void
	 */
	public void updateAllContractDebt(int day);
	
	/**
	 * <p>
	 * Description:保存中间表，合同欠款天数数据<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-17
	 * @param contractDebtDay
	 * void
	 */
	public void saveContractDebt(ContractDebtDay contractDebtDay);
	
	/**
	 * <p>
	 * Description:通过客户编码查询出合同的基本信息<br />
	 * </p>
	 * @version 0.1 2013-1-17
	 * @param custNum
	 * @return
	 * List<Contract>
	 */
	public List<Contract> getContractsBasicInfoByCustNum(String custNum);

	/**
	 * @description 查询有效的月发月送合同(包含客户的最近三个月发货金额)  
	 * @author 潘光均
	 * @version 0.1 2013-3-12
	 * @param 
	 *@date 2013-3-12
	 * @return List<ContractDto>
	 * @update 2013-3-12 上午9:46:13
	 */
	public List<ContractDto> queryContractWithMoney(ContractCondition condtion,
			int start, int limit);

	/**
	 * @description 获取客户最近三个月发货情况.  
	 * @author 潘光均
	 * @version 0.1 2013-3-12
	 * @param 
	 *@date 2013-3-12
	 * @return List<CustMonthSum>
	 * @update 2013-3-12 下午1:50:28
	 */
	public List<CustMonthSum> queryCustMonthSumsByCustId(String id);

	/**
	 * @description 更新折扣.  
	 * @author 潘光均
	 * @version 0.1 2013-3-20
	 * @param 
	 *@date 2013-3-20
	 * @return void
	 * @update 2013-3-20 下午12:40:01
	 */
	public void updatePreferential(Preferential preferential);
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
	 * <p>
	 * Description:查询操作日志<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * @param log
	 * @return
	 * List<ContractOperatorLog>
	 */
	public List<ContractOperatorLog> searchContractOperatorLogs(ContractOperatorLog log);
	

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
	 * @description 查询初始化总条数.  
	 * @author 潘光均
	 * @version 0.1 2013-3-20
	 * @param 
	 *@date 2013-3-20
	 * @return int
	 * @update 2013-3-20 上午9:58:32
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
	public void modifyDuningDept(String dunningDeptCode,Boolean ifForeignGoods,String contractId);

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
	 * Description:根据客户id查询客户快递近三个月发货情况<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-22
	 * @param id
	 * @return
	 * List<CustMonthSum>
	 */
	public List<CustMonthSum> queryExCustMonthSumsByCustId(String custId);


	/**
	 * <p>
	 * Description:获取快递近三个月发货情况<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-23
	 * @param custId
	 * @return
	 * String
	 */
	String getExArrearaMountByCustId(String custId);


	/**
	 * <p>
	 * Description:获取进几个月发货情况<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-27
	 * @param custId
	 * @param i
	 * @return
	 * List<Double>
	 */
	public List<Double> getExLatelyDeliverMoney(String custId, int month);
}
