package com.deppon.crm.module.backfreight.server.service;

import java.util.List;

import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.backfreight.shared.domain.BackFreightSearchCondition;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.Waybill;

/**
 * 
 * @description：退运费service接口
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午2:20:40
 */
public interface IBackFreightService {

	/**
	 * 
		 * @description ：根据运单号查有效的退运费
		 * @author 华龙
		 * @version 1.0
		 * @param  String waybillNumber
		 *            :运单号
		 * @date 2012-10-29 下午3:34:49
		 * @return BackFreight
	 */
	public BackFreight findValidBackFreightByNum(String waybillNumber);
	/**
	 * 
		 * @description ：根据运单号查询时效差错记录
		 * @author 华龙
		 * @version 1.0
		 * @param  String waybillNumber
		 *            :运单号
		 * @date 2012-10-29 下午3:46:09
		 * @return DelayAccident 时效差错实体
	 */
	public Boolean findDelayAccidentByNum(String waybillNumber);
	/**
	 * 
	 * @description ：保存退运费
	 * @author 华龙
	 * @version 1.0
	 * @param :BackFreight BackFreight：退运费实体
	 * @date 2012-10-29 下午2:36:02
	 * @return void
	 */
	public BackFreight addBackFreight(BackFreight backFreight);

	
	/**
	 * 
		 * @description ：根据条件查询退运费列表
		 * @author 华龙
		 * @version 1.0
		 * @param  BackFreightSearchCondition condition
		 *            : 退运费搜索条件
		 * @date 2012-10-29 下午2:41:42
		 * @return List<BackFreight> 退运费列表
	 */
	
	public List<BackFreight>  searchBackFreightByCondition(BackFreightSearchCondition condition);
	/**
	 * 
		 * @description ：根据条件统计总条数
		 * @author 华龙
		 * @version 1.0
		 * @param  BackFreightSearchCondition condition
		 *            : 退运费搜索条件
		 * @date 2012-10-29 下午2:47:47
		 * @return int 总条数
	 */
	public int  countBackFreightByCondition(BackFreightSearchCondition condition);
	/**
	 * 
		 * @description ：根据条件导出退运费
		 * @author 华龙
		 * @version 1.0
		 * @param  BackFreightSearchCondition condition
		 *            :退运费搜索条件
		 * @date 2012-10-29 下午2:42:06
		 * @return List<BackFreight> 退运费列表
	 */
	public List<BackFreight>  exportBackFreightByCondition(BackFreightSearchCondition condition);
	/**
	 * 
		 * @description ：根据ID查询退运费
		 * @author 华龙
		 * @version 1.0
		 * @param  String BackFreightId
		 *            :退运费Id
		 * @date 2012-10-29 下午2:42:14
		 * @return BackFreight 退运费实体
	 */
	public BackFreight  getBackFreightById(String backFreightId);
	/**
	 * 
		 * @description ：根据运单号查询坏账
		 * @author 华龙
		 * @version 1.0
		 * @param  String waybillNumber
		 *            :运单号
		 * @date 2012-10-30 上午11:30:34
		 * @return BadDebt 坏账
	 */
	public Boolean findBadDebtByNum(String waybillNumber);
	 /** 
	 * @description ：根据工作流号查询退运费
	 * @author 华龙
	 * @version 1.0
	 * @param  String BackFreightId
	 *            :退运费Id
	 * @date 2012-10-29 下午2:42:14
	 * @return BackFreight 退运费实体
 */
	public BackFreight getBackFreightByOaWorkFlowNum(String oaWorkflowNum);
	 /** 
	 * @description ：更新退运费
	 * @author 华龙
	 * @version 1.0
	 * @param  BackFreight backFreight
	 * @date 2012-10-29 下午2:42:14
	 * @return void
*/
	public void updateBackFreight(BackFreight backFreight);
	/**
	 * 
	 * @Description: 生成FOSS应付单
	 * @author huangzhanming
	 * @param serviceRecovery
	 * @return void
	 * @date 2012-11-7 下午4:07:24
	 * @version V1.0
	 */
	public boolean submitBackFreightPayment(BackFreight backFreight);
	/**
	 * 
	 * <p>
	 * Description:劳务费状态修改<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2012-12-4
	 * @param waybillNum
	 * @param status
	 * void
	 */
	public void ServiceChargeStatusUpdate(String waybillNum,boolean status);
	 /** 
		 * @description ：根据id更新工作流号
		 * @author 华龙
		 * @version 1.0
		 * @param  String BackFreightId
		 *            :退运费Id
		 *            String oaWorkflowNum
		 *            :工作流号
		 * @date 2012-10-29 下午2:42:14
		 * @return void
		 */
	public void updateBackFreightWorkflowNum(String id, String oaWorkflowNum);
	/**
	 * 
	 * @Description: 提交退运费的工作流
	 * @author 华龙
	 * @param BackFreight backFreight
	 * 					:退运费实体
	 * @return String
	 * @date 2012-11-7 下午4:02:00
	 * @version V1.0
	 */
	public String submitBackFreightWorkflow(BackFreight backFreight);

}
