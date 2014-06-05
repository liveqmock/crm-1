/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author ZhuPJ
 * @version 0.1 2012-3-28
 */
package com.deppon.crm.module.marketing.server.manager.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IMonitorPlanManager;
import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.service.IMonitorPlanService;
import com.deppon.crm.module.marketing.server.utils.MonitorPlanValidateUtils;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlan;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanDetail;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition;
import com.deppon.crm.module.marketing.shared.exception.PlanException;
import com.deppon.crm.module.marketing.shared.exception.PlanExceptionType;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * 监控计划操作封装类型<br />
 * </p>
 * @title MonitorPlanManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author ZhuPJ
 * @version 0.1 2012-3-28
 */
public class MonitorPlanManager implements IMonitorPlanManager {
	//监控计划service
	private IMonitorPlanService monitorPlanService;
	//计划MANAGER
	private IPlanManager planManager;

	/**
	 * @param monitorPlanService : set the property monitorPlanService.
	 */
	public void setMonitorPlanService(IMonitorPlanService monitorPlanService) {
		this.monitorPlanService = monitorPlanService;
	}

	public void setPlanManager(IPlanManager planManager) {
		this.planManager = planManager;
	}

	/**
	 * 查询监控计划列表
	 * @author suyujun
	 * @version 0.1
	 * @date 2013-01-28
	 * @param condition
	 * @param user
	 * @see com.deppon.crm.module.marketing.server.manager.IMonitorPlanManager#searchMonitorList(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition)
	 */
	@SuppressWarnings("serial")
	@Override
	public List<MonitorPlan> searchMonitorList(
			MonitorPlanQueryCondition condition, User user) {
		try {
			List<MonitorPlan> mpList = null;
			//验证condition的合法性
			if (MonitorPlanValidateUtils.canSearchMonitorPlan(condition)){
				//执行部门ID
				String depid = condition.getExecdeptId();
				//查询当前用户拥有的权限部门
				String[] depIds = null;
				String position = user.getEmpCode().getPosition();
				String deptCode = user.getEmpCode().getDeptId().getStandardCode();
				/**
				 * @author 043260
				 * @description 根据是否选择营业部查询不同的权限部门
				 */
				if (StringUtils.isEmpty(depid)){
					if(StringUtils.isNotEmpty(MarketingConstance.EXPRESS_POSITIONS.get(position))
							||MarketingConstance.EXPRESS_MARKETING_GROUP_STANDARDCODE.equals(deptCode)){
						depIds = planManager.makeUpAllExpressExecuteDeptids(depid, user, deptCode);
					}else{
						depIds = planManager.makeUpAllTLTExecuteDeptids(depid, user);
					}
//					//权限部门设置
//					Set<String> deps = user.getDeptids();
//					depIds = new String[deps.size()];
//					//设置
//					deps.toArray(depIds);
				}else{
					/**
					 * @author 043260
					 * @description 根据是否选择营业部查询不同的权限部门
					 */
					if(StringUtils.isNotEmpty(MarketingConstance.EXPRESS_POSITIONS.get(position))
							||MarketingConstance.EXPRESS_MARKETING_GROUP_STANDARDCODE.equals(deptCode)){
						depIds = planManager.makeUpExpressExecuteDeptids(depid, user);
					}else{
						depIds = planManager.makeUpTLTExecuteDeptids(depid, user);
					}
//					List<String> depts = planManager.
//							searchAuthBussinessDeptByParentId(depid, user.getEmpCode().getEmpCode());
//					depIds = depts.toArray(new String[0]);
				}
				//设置执行部门集合
				if(depIds.length>MarketingConstance.DEPT_NUM_LIMIT){
					PlanException e = new PlanException(PlanExceptionType.deptNumLimit);
		            throw new GeneralException(e.getErrorCode(),e.getMessage(), e, new Object[]{}){};
				}
				if(depIds.length > 0){
					condition.setExecdeptIds(depIds);					
				}else{
					condition.setExecdeptIds(new String[]{depid});
				}
				String topic = condition.getTopic();
				//计划主题不为空，构造模糊查询格式
				if(StringUtils.isNotEmpty(topic)){
					condition.setTopic("%"+topic+"%");
				}
				//查询列表
				mpList = monitorPlanService.searchMonitorList(condition);
				// 计算完成率
				for (int i=0;i<mpList.size();i++){
					//监控计划
					MonitorPlan mp = mpList.get(i);
					//总数
					int total = mp.getTotal();
					//已完成
					int finished = mp.getFinished();
					double d = 0;
					//格式化计算结果
					DecimalFormat df = new DecimalFormat("0.00%");
					if (total != 0){
						//完成率
						d = (finished/(float)total);
					}else{
						//完成率
						d = 0;
					}
					//设置完成率
					mp.setCompletionRate(df.format(d));
				}			
			}
			//返回结果
			return mpList;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,e.getErrorArguments()) {
			};
		}
	}

	/**
	 * 分页查询监控明细
	 * @author suyujun
	 * @versin 0.1
	 * @date 2013-01-28
	 * @param condition
	 * @param start
	 * @param limit
	 * @param user
	 * @see com.deppon.crm.module.marketing.server.manager.IMonitorPlanManager#getMonitorDetail(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition)
	 */
	@SuppressWarnings("serial")
	@Override
	public HashMap<String, Object> getMonitorDetail(
			MonitorPlanQueryCondition condition,int start, int limit, User user) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			//检验查询条件合法性
			if (MonitorPlanValidateUtils.canSearchMonitorPlan(condition)){
				String depid = condition.getExecdeptId();
				//设置执行部门集合
				condition.setExecdeptIds(new String[]{depid});
				String topic = condition.getTopic();
				//构造模糊查询格式
				if(StringUtils.isNotEmpty(topic)){
					condition.setTopic("%"+topic+"%");
				}
				//查询结果
				List<MonitorPlanDetail> list = monitorPlanService.getMonitorDetail(condition, start, limit);
				//查询总数
				int count = monitorPlanService.getMonitorDetailCount(condition);
				//封装结果
				map.put("mplist", list);
				map.put("mpcount", count);
			}
			return map;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,e.getErrorArguments()) {
			};
		}
	}

}
