package com.deppon.crm.module.backfreight.server.dao;

import java.util.List;

import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.backfreight.shared.domain.BackFreightSearchCondition;

/**
 * 
 * @description：退运费dao接口
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午2:20:23
 */
public interface IBackFreightDao {
	/**
	 * 
	 * @description ：根据运单号查询运单实体
	 * @author 华龙
	 * @version 1.0
	 * @param :String waybillNumber:运单号
	 * @date 2012-10-29 下午2:35:52
	 * @return Waybill 运单号
	 */
	public BackFreight findValidBackFreightByNum(String waybillNumber);

	/**
	 * 
	 * @description ：保存退运费
	 * @author 华龙
	 * @version 1.0
	 * @param :BackFreight BackFreight：退运费实体
	 * @date 2012-10-29 下午2:36:02
	 * @return void
	 */
	public BackFreight addBackFreight(BackFreight BackFreight);

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
	public BackFreight getBackFreightById(String BackFreightId);

	/**
	 * 
	 * @description ：根据工作流号查询退运费
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            BackFreightId :退运费Id
	 * @date 2012-10-29 下午2:42:14
	 * @return BackFreight 退运费实体
	 */
	public BackFreight findBackFreightByOaWorkflowNum(String oaWorkflowNum);

	/**
	 * 
	 * @description ：根据id更新工作流号
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            BackFreightId :退运费Id String oaWorkflowNum :工作流号
	 * @date 2012-10-29 下午2:42:14
	 * @return BackFreight 退运费实体
	 */
	public void updateBackFreightWorkflowNum(String id, String oaWorkflowNum);
	/**
	 * 
	 * <p>
	 * Description:更新退运费<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @param backFreight
	 * void
	 */

	public void updateBackFreight(BackFreight backFreight);
	/**
	 * 
	 * <p>
	 * Description:根据id删除退运费（测试方法）<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @param id
	 * void
	 */
	public void deleteBackFreightById(String id);

}
