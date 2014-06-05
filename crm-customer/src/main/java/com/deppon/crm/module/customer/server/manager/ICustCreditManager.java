package com.deppon.crm.module.customer.server.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;

import com.deppon.crm.module.customer.shared.domain.CustCredit;
import com.deppon.crm.module.customer.shared.domain.CustCreditConfig;

/**
 * <p>
 * Description: 客户信用manager<br/>
 * </p>
 * @title ICustCreditManager
 * @package com.deppon.crm.module.customer.server.manager
 * @author andy
 * @version 1.0
 * @date 2014-03-07
 *
 */
public interface ICustCreditManager {

	/**
	 * 
	 * <p>
	 * 定时恢复散客、固客的临欠额度<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return void
	 * 
	 */
	void getDeliverMoneyByCondtion();
	
	/**
	 * 
	 * <p>
	 * 根据客户id，查询客户信用信息<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param custId
	 * @return CustCredit
	 * 
	 */
	CustCredit getCustCreditByCustId(String custId);
	
	/**
	 * 
	 * <p>
	 * 更新信用较差客户配置信息<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param config
	 * @return void
	 * 
	 */
	void updateCustCreditConfig(CustCreditConfig config);
	
	/**
	 * 
	 * <p>
	 * 获取信用较差客户配置信息<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return CustCreditConfig
	 * 
	 */
	CustCreditConfig getCustCreditConfig();
	
	/**
	 * 
	 * <p>
	 * 查询客户未还款信息
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return List<CustCredit>
	 * 
	 */
	List<CustCredit> getCustBadCreditList();
	
	/**
	 * 
	 * <p>
	 * 标记信用较差客户
	 * 
	 * 1.临欠超过 N 天未还临欠客户
	 * 2.按照月结合同规定时间后 N 天未还款的月结客户
	 * 3.逾期 N 天未还款的客户
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return void
	 * @throws ParseException 
	 * 
	 */
	void updateCustBadCredit() throws ParseException;
	
	/**
	 * 
	 * <p>
	 * 获取信用较差客户名单
	 * 
	 * 1.临时欠款超过 N  天未还款临时欠款客户
	 * 2.按照月结合同规定时间后 N   天未还款的月结客户
	 * 3.逾期  N  天未还款的客户
	 * 4.最长一笔临欠达到 N  天（含）的时欠款客户
	 * 5.距离规定结款日期前   N   天未还款的月结客户
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return List<CustCredit>
	 * @throws ParseException 
	 * 
	 */
	List<CustCredit> getCustBadCreditExcelList() throws ParseException;
	
	/**
	 * 
	 * <p>
	 * 插入信用较差客户名单
	 * 
	 * 1.临时欠款超过 N  天未还款临时欠款客户
	 * 2.按照月结合同规定时间后 N   天未还款的月结客户
	 * 3.逾期  N  天未还款的客户
	 * 4.最长一笔临欠达到 N  天（含）的时欠款客户
	 * 5.距离规定结款日期前   N   天未还款的月结客户
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return void
	 * @throws ParseException 
	 * 
	 */
	void insertCustBadCreditReport() throws ParseException ;
	
	/**
	 * 
	 * <p>
	 * 更新信用预警信息（信用预警次数、最后一次信用预警时间）
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param custBadCreditList
	 * @return boolean
	 * 
	 */
	boolean updateEarlyWarnInfo(List<CustCredit> custBadCreditList);
	
	/**
	 * 
	 * <p>
	 * 生成信用较差客户名单excel
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param config
	 * @param custBadCreditList
	 * @param file
	 * @return FileInputStream
	 * @throws IOException 
	 * 
	 */
	FileInputStream getExcelFile(CustCreditConfig config, List<CustCredit> custBadCreditList, File file) throws IOException;
	
	/**
	 * 
	 * <p>
	 * 信用较差客户邮件发送
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param
	 * @return boolean
	 * @throws ParseException, IOException, MessagingException
	 * 
	 */
	boolean sendCustCreditMail() throws ParseException, IOException, MessagingException;
	
	/**
	 * 
	 * <p>
	 * 获取信用较差客户待办数据
	 * 
	 * 4.最长一笔临欠达到 N  天（含）的时欠款客户
	 * 5.距离规定结款日期前   N   天未还款的月结客户
	 * 6.最长还款周期（月结天数）到期前  N  天未还款的月结客户
	 * 7.信用额度使用率达到  N %的月结客户
	 * 8.营业部临时欠款总额使用率达到  N %
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-24
	 * @param 
	 * @return List<CustCredit>
	 * @throws ParseException
	 * 
	 */
	List<CustCredit> getCustCreditToDo() throws ParseException;
	
	/**
	 * 
	 * <p>
	 * 生成信用较差客户待办信息之前先删除信用较差客户待办
	 * 
	 * 4.最长一笔临欠达到 N  天（含）的时欠款客户
	 * 5.距离规定结款日期前   N   天未还款的月结客户
	 * 6.最长还款周期（月结天数）到期前  N  天未还款的月结客户
	 * 7.信用额度使用率达到  N %的月结客户
	 * 8.营业部临时欠款总额使用率达到  N %
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-24
	 * @param 
	 * @return boolean
	 * 
	 */
	boolean deleteCustCreditTodo();
	
	/**
	 * 
	 * <p>
	 * 生成信用较差客户待办信息
	 * 
	 * 4.最长一笔临欠达到 N  天（含）的时欠款客户
	 * 5.距离规定结款日期前   N   天未还款的月结客户
	 * 6.最长还款周期（月结天数）到期前  N  天未还款的月结客户
	 * 7.信用额度使用率达到  N %的月结客户
	 * 8.营业部临时欠款总额使用率达到  N %
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-24
	 * @param 
	 * @return void
	 * @throws ParseException
	 * 
	 */
	void generationCustCreditToDo() throws ParseException;
	
	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param deptId 查询部门
	 * @param date 查询时间(半月报表)
	 * @param start
	 * @param limit
	 * @return List<CustCredit>
	 * @throws ParseException
	 * 
	 */
	List<CustCredit> getCustCreditListByConditions(String deptId, String date, int start,int limit) throws ParseException;
	
	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param deptId 查询部门
	 * @param date 查询时间(半月报表)
	 * @return int
	 * @throws ParseException
	 * 
	 */
	int getCustCreditListByConditions(String deptId, String date) throws ParseException;
	
	/**
	 * 
	 * <p>
	 * 信用较差客户报表导出<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param deptId 查询部门
	 * @param date 查询时间(半月报表)
	 * @return FileInputStream
	 * @throws ParseException, IOException
	 * 
	 */
	FileInputStream getExcel(String deptId, String date) throws ParseException, IOException;
	
	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询权限校验<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param deptId 查询部门
	 * @return int
	 * 
	 */
	int custCreditAuthorization(String deptId);
}
