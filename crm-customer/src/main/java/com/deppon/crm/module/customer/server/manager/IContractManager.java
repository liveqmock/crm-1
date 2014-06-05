package com.deppon.crm.module.customer.server.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.client.workflow.domain.ContractInfo;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractCondition;
import com.deppon.crm.module.customer.shared.domain.ContractDebtDay;
import com.deppon.crm.module.customer.shared.domain.ContractDetailView;
import com.deppon.crm.module.customer.shared.domain.ContractExaminView;
import com.deppon.crm.module.customer.shared.domain.ContractMonthendDay;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;

public interface IContractManager 
{
	
	
	/**
	 * 
	 * @合同创建
	 * @author 赵斌
	 * @2012-3-28
	 * @return 工作流Id
	 * @param contract 合同
	 * @param fileInfoList 
	 */
	public Map<String,String> createContract(Contract contract, List<FileInfo> fileInfoList,String contractType);
	
	/**
	 * 
	 * <p>
	 * 得到新签合同信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-9-3
	 * @param memberId
	 * @return
	 * Contract
	 */
	public Contract getNewContract(String memberId);
	
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
	 * @修改合同及相关信息
	 * @author：赵斌
	 * @2012-4-13
	 * @param approveDataList 需修改的审批数据集合，memberId,会员ID
	 * @return String 工作流id
	 * */
	public Map<String,String> changeContract(Contract contract,String contractType);
	
	/**
	 * 
	 * @绑定合同部门
	 * @author 赵斌
	 * @2012-4-14
	 * @return boolean
	 * @param contractId 合同id
	 * @param deptId 合同对应部门id
	 * @param contractNoun 合同附件
	 */
	public String boundContract(String contractId,String deptId, List<FileInfo> fileInfoList);
	
	/**
	 * 
	 * @解除合同部门绑定
	 * @author 赵斌
	 * @2012-4-14
	 * @return boolean
	 * @param contractInfo 合同主体
	 * @param contractDeptId 合同对应部门id
	 */
	public boolean removeContract(Contract contractInfo,String deptId);
	
	/**
	 * 
	 * @改变合同归属部门
	 * @author 赵斌
	 * @2012-4-17
	 * @return String
	 * @param contractId 合同id
	 * @param deptId 合同对应部门id
	 * @param contractNoun 合同附件
	 */
	public String changeContractOwner(String contractId,String deptId, List<FileInfo> fileInfoList);
	
	
	/**
	 * 根据ID查询出合同所有信息
	 * @description
	 * @author 赵斌
	 * @2012-4-18
	 * @return
	 */
	public Contract lookDetailContractInfo(String id); 
	
	/**
	 * 
	 * <p>获得要修改的合同详细信息<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-6-13
	 * @param id
	 * @param handType 
	 * @return
	 * ContractView
	 */
	public Contract getUpdateContractInfo(String id, String handType);
	
	/**
	 * 
	 * @作废合同信息
	 * @author 赵斌
	 * @2012-4-19
	 * @return String 工作流号
	 * @param contractId 合同id
	 */
	public String cancelContract(String contractId);
		
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
	 * <p>
	 * 根据会员Id查找出相关的所有合同信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param memberId
	 * @return
	 * List<Contract>
	 */
	public List<ContractDetailView> getContractsByMemberId(String memberId);
	/**
	 * 根据会员编码查找出相关的所有合同信息<br />
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param custNumber
	 * @return
	 * List<Contract>
	 */
	public List<Contract> getContractsByCustNumber(String custNumber);
	
	/**
	 * 
	 * <p>
	 * 根据会员Id查询最后一次的合同信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param memberId
	 * @return
	 * Contract
	 */
	public ContractDetailView getLastestContract(String memberId);
	
	/**
	 * 
	 * <p>
	 * 根据合同Id查询合同的详细信息（只包括了基本信息和合同信息）
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param id
	 * @return
	 * Contract
	 */
	public ContractDetailView getContractDetailById(String id);
	
	/**
	 * 
	 * @工作流审批完成之后调用
	 * @author 赵斌
	 * @2012-5-3
	 * @return void
	 * @param wkid 工作流id
	 * @param wkStatus 工作流审批状态
	 * @param wkman 最后审批人
	 * @param wktime 最后审批时间
	 */
	public boolean contractApprove(String wkid,boolean wkStatus,String wkman,Date wktime);

	/**
	 * 
	 * @查询合同的全部历史操作记录
	 * @author 赵斌
	 * @2012-5-3
	 * @return List<ContractOperatorLog>
	 * @param id 合同id
	 */
	public List<ContractOperatorLog> getContractOperatorLogByContractId(
			String id); 
	/**
	 * 
	 * @description 校验合同是否有工作流在审批，如果存在则不能进行任何操作.  
	 * @author 潘光均
	 * @version 0.1 2012-7-4
	 * @param 
	 *@date 2012-7-4
	 * @return boolean
	 * @update 2012-7-4 上午10:04:00
	 */
	public boolean isContractCanOperate(String contractId,String handleType);


	/**
	 * @description 作废过期合同.  
	 * @author 潘光均
	 * @version 0.1 2012-7-11
	 * @param 
	 *@date 2012-7-11
	 * @return void
	 * @update 2012-7-11 上午9:40:43
	 */
	public void cancelTimeOutContract();
	/**
	 * 
	 * <p>
	 * 创建合同新增的审批试图<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-8-3
	 * @param contractId
	 * @param workflowId
	 * @return
	 * ContractExaminView
	 */
	public ContractExaminView createContractAddExaminView(String contractId,long workflowId);


	/**
	 * @description 生效带生效合同.  
	 * @author patern
	 * @version 0.1 2012-8-17
	 * @param b true or false   
	 *@date 2012-8-17
	 * @return none
	 * @update 2012-8-17 上午9:20:29
	 */
	void effectWaitEffectContract();
	/**
	 * 
	 * @description 修改合同信息.  
	 * @author 潘光均
	 * @version 0.1 2012-9-3
	 * @param contractType 
	 * @param 
	 *@date 2012-9-3
	 * @return void
	 * @update 2012-9-3 下午1:48:07
	 */
	public Map<String,String> updateContract(Contract contract, String contractType);
	/**
	 * 
	 * @description 校验结算额度. true:结款额度小于最大发货量的2倍，反之则为false 
	 * 												-------ture ,不用提示，false提示
	 * @author 潘光均
	 * @version 0.1 2012-9-3
	 * @param 
	 *@date 2012-9-3
	 * @return void
	 * @update 2012-9-3 下午2:17:57
	 */
	public Boolean checkAmountByCustId(Contract contact,String custId,Double amount);
	
	/**
	 * 
	 * @description 删除无效合同（实质删除未审批同意的工作流数据）.  
	 * @author 潘光均
	 * @version 0.1 2012-9-4
	 * @param 
	 *@date 2012-9-4
	 * @return void
	 * @update 2012-9-4 下午3:48:57
	 */
	public void deleteUneffectContract(String contractId);
	/**
	 * 
	 * <p>
	 * 新签合同<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-9-3
	 * @param contract
	 * @param files
	 * String 返回工作流id
	 * @param contractType 
	 */
	Map<String,String> createNewContract(Contract contract,List<FileInfo> files, String contractType);

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
	void batchDeleteUneffectContract(List<String> contractIds);
	/**
	 * 
	 * @description 查询30天到期合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-19
	 * @param 
	 *@date 2012-9-19
	 * @return List<Contract>
	 * @update 2012-9-19 上午11:17:58
	 */
	List<Contract> searchUneffectContractIn30Days();

	/**
	 * @description 查询所有待生效合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-27
	 * @param 
	 *@date 2012-9-27
	 * @return List<Contract>
	 * @update 2012-9-27 下午2:12:59
	 */
	List<Contract> searchAllWaitEffectContractByTime(Date date);
		/**
	 * <p>
	 * Description:特殊权限。修改某一个合同月结天数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-7
	 * @param contractId
	 * @param debtDays
	 * void
	 */
	public void updateContractDebtDaysById(String contractId,int debtDays);
	/**
	 * <p>
	 * Description:通用权限,查询全部合同月结天数管理<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-8
	 * @return
	 * List<ContractDebtDay>
	 */
	public List<ContractMonthendDay> searchAllContractDebtDays();
	/**
	 * <p>
	 * Description:通用权限。通过合同月结天数名字  查询合同月结天数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-8
	 * @return
	 * ContractDebtDay
	 */
	public ContractMonthendDay searchContractDebtDayByName(String contractDebtDaysName);
	

	/**
	 * <p>
	 * Description:通用权限，修改合同月结天数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-8
	 * void
	 */
	public void updateCommonContractMonthEndDayById(String id,int contractDebtDay);
	
	/**
	 * <p>
	 * Description:检验当前时间是否在晚上八点到早上八点<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-9
	 * @return
	 * boolean
	 */
	public void checkCurrentTime();
	
	/**
	 * <p>
	 * Description:合同月结天数 提供给定时器调用<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-10
	 * void
	 */
	public void contractDebtDaysManager();
	
	/**
	 * <p>
	 * Description:提供给接口调用，如果中间表t_cust_contractDebt存在数据，则进行修改操作，
	 *             不存在数据，则插入操作
	 * <br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-17
	 * @param contractDebtDay
	 * void
	 */
	public void saveContractDebt(ContractDebtDay contractDebtDay);

	/**
	 * @description 更新月发月送合同折扣信息--用于定时器.  
	 * @author 潘光均
	 * @version 0.1 2013-3-12
	 * @param 
	 *@date 2013-3-12
	 * @return void
	 * @update 2013-3-12 上午9:02:58
	 */
	void updateMonthSendRate();

	/**
	 * @description 修改特殊客户的月发月送合同运费折扣.  
	 * @author 潘光均
	 * @version 0.1 2013-3-13
	 * @param Contract：合同信息
	 *@date 2013-3-13
	 * @return void
	 * @update 2013-3-13 下午1:42:00
	 */
	public void updateMonthSendRate(Contract con);

	/**
	 * @description 查看修改月发月送折扣信息的合同信息.  
	 * @author 潘光均
	 * @version 0.1 2013-3-13
	 * @param 
	 *@date 2013-3-13
	 * @return Contract
	 * @update 2013-3-13 下午4:14:45
	 */
	public Contract getUpdateMonthSendInfo(String contractId);
	/**
	 * 
	 * <p>
	 * Description:检验新建合同和原合同失效日期是否一致<br />
	 *  不一致返回原合同，一致返回null
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-14
	 * @param contract
	 * @return
	 * Contract
	 */
	Contract checkCreateContractDate(ContractDetailView contract);
	
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
	 * @author 潘光均
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
	 * @version 0.1 2013-3-19
	 * @param 
	 *@date 2013-3-19
	 * @return int
	 * @update 2013-3-19 下午6:23:46
	 */
	public int countInitContract(ContractCondition contractCondition);
	
	/**
	 * Description:
	 * 提供给定时器
	 * 特殊权限修改后 特殊权限 除了合同为无效 的全部都要修改。
	 * 通用权限修改 此处需要判断是否可以将月结天数变小，
	 * 因为变小后 有效合同的欠款天数 > 合同月结天数 有效合同的可使用额度可能会清零<br />
	 * @version 0.1 2013-5-15
	 * void
	 */
	public void updateCommonContractDebtDay();
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
	 */
	public void saveCustInfoForArreaAmoutMessage();
	/**
	 * 
	 * <p>
	 * Description:生成合同信息<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-19
	 * @param contract
	 * @param map
	 * void
	 */
	public ContractInfo createContractInfo(Contract contract, Map<String, String> map);
}
