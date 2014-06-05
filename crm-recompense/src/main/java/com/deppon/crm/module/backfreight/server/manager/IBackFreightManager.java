package com.deppon.crm.module.backfreight.server.manager;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.backfreight.shared.domain.BackFreightSearchCondition;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.Waybill;

/**
 * 
 * @description：退运费manager接口
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午2:19:50
 */
public interface IBackFreightManager {
	/**
	 * 
	 * @description ：根据运单号查询运单实体
	 * @author 华龙
	 * @version 1.0
	 * @param :String waybillNumber:运单号
	 * @date 2012-10-29 下午2:35:52
	 * @return Waybill 运单号
	 */
	public Waybill findWaybillByNum(String waybillNumber, User user);

	/**
	 * 
	 * @description ：保存退运费
	 * @author 华龙
	 * @version 1.0
	 * @param :BackFreight BackFreight：退运费实体
	 * @date 2012-10-29 下午2:36:02
	 * @return void
	 */
	public void submitBackFreight(BackFreight BackFreight, User user);

	/**
	 * 
	 * @description ：根据申请状态和工作留号查询审批状态
	 * @author 华龙
	 * @version 1.0
	 * @param :String applyStatus：申请状态 String oaWorkflowNum： 工作流号
	 * @date 2012-10-29 下午2:36:11
	 * @return String 状态 同意或者拒绝
	 */
	public boolean returnBackFreightStatus(String oaWorkflowNum,
			String employeeNum, boolean verifyStatus, Date verifyTime,
			String verifyDesc);

	/**
	 * 
	 * @description ：根据条件查询退运费列表
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition : 退运费搜索条件
	 * @date 2012-10-29 下午2:41:42
	 * @return List<BackFreight> 退运费列表
	 */

	public List<BackFreight> searchBackFreightByCondition(
			BackFreightSearchCondition condition);

	/**
	 * 
	 * @description ：转换查询条件
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition : 退运费搜索条件
	 * @date 2012-10-29 下午2:41:57
	 * @return BackFreightSearchCondition 退运费搜索条件
	 */
	public BackFreightSearchCondition transBackFreightByCondition(
			BackFreightSearchCondition condition);

	/**
	 * 
	 * @description ：根据条件统计总条数
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition : 退运费搜索条件
	 * @date 2012-10-29 下午2:47:47
	 * @return int 总条数
	 */
	public int countBackFreightByCondition(BackFreightSearchCondition condition);

	/**
	 * 
	 * @description ：根据条件导出退运费
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition :退运费搜索条件
	 * @date 2012-10-29 下午2:42:06
	 * @return List<BackFreight> 退运费列表
	 */
	public List<BackFreight> exportBackFreightByCondition(
			BackFreightSearchCondition condition);

	/**
	 * 
	 * @description ：根据ID查询退运费
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            BackFreightId :退运费Id
	 * @date 2012-10-29 下午2:42:14
	 * @return BackFreight 退运费实体
	 */
	public BackFreight getBackFreightById(String backFreightId);

	/**
	 * 
	 * @Description: 查询有效的退运费
	 * @author huangzhanming
	 * @param waybillNumber
	 * @return
	 * @return BackFreight
	 * @date 2012-11-9 下午3:34:46
	 * @version V1.0
	 */
	public BackFreight findValidBackFreightByNum(String waybillNumber);


}
