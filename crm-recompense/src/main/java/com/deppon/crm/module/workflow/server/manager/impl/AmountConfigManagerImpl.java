package com.deppon.crm.module.workflow.server.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.bpmsapi.module.client.domain.BpmsActivity;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.workflow.server.manager.IAmountConfigManager;
import com.deppon.crm.module.workflow.server.manager.INormalClaimManager;
import com.deppon.crm.module.workflow.server.service.IAmountConfigService;
import com.deppon.crm.module.workflow.server.util.AmountConfigEntity;
import com.deppon.crm.module.workflow.server.util.Guid;
import com.deppon.crm.module.workflow.server.util.NormalClaimUtil;
import com.deppon.crm.module.workflow.shared.exception.WorkflowException;
import com.deppon.crm.module.workflow.shared.exception.WorkflowExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * 
 *<pre>
 *功能: 审批金额配置业务层的实现类
 *作者：andy
 *日期：2013-8-12下午5:30:09
 *</pre>
 */
public class AmountConfigManagerImpl implements IAmountConfigManager {

	private IAmountConfigService amountConfigService;
	
	private INormalClaimManager normalClaimManager;

	public IAmountConfigService getAmountConfigService() {
		return amountConfigService;
	}

	public void setAmountConfigService(IAmountConfigService amountConfigService) {
		this.amountConfigService = amountConfigService;
	}

	
	
	public void setNormalClaimManager(INormalClaimManager normalClaimManager) {
		this.normalClaimManager = normalClaimManager;
	}

	/**
	 * 
	 *<pre>
	 * 方法体说明：根据条件查询金额配置
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:55:08
	 * @param amountConfigEntity
	 * @return
	 *</pre>
	 */
	@Override
	public AmountConfigEntity queryForBranch(
			AmountConfigEntity amountConfigEntity){
		return amountConfigService.queryForBranch(amountConfigEntity);
	}
	
	/**
	 * 
	 *<pre>
	 * 方法体说明：查询金额配置列表
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:53:54
	 * @param amountConfigEntity 查询参数
	 * @return 指定条件的金额配置列表
	 *</pre>
	 */
	@Override
	public List<AmountConfigEntity> queryAllBranch(
			AmountConfigEntity amountConfigEntity ,int start,int limit){
		return amountConfigService.queryAllBranch(amountConfigEntity,start,limit);
	}

	/**
	 * 
	 *<pre>
	 * 方法体说明：查询金额配置列表的结果集总数
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:56:03
	 * @param amountConfigEntity 查询参数
	 * @return 金额配置列表的结果集总数
	 *</pre>
	 */
	@Override
	public Long getBranchCount(AmountConfigEntity amountConfigEntity) {
		return amountConfigService.getBranchCount(amountConfigEntity);
	}
	
	
	/**
	 * 
	 *<pre>
	 * 方法体说明：更新金额配置
	 * 作者： andy
	 * 日期： 2013-8-13 下午5:57:25
	 * @param amountConfigEntity
	 *</pre>
	 */
	@Override
	public void saveOrUpdate(AmountConfigEntity amountConfigEntity) {
		// 获取当前用户
		User user = (User) UserContext.getCurrentUser();
		//获取当前用户的工号
		String userCode = user.getEmpCode().getEmpCode();
		//通过判断id是否为空来决定执行添加操作还是更新操作
		if(StringUtils.isBlank(amountConfigEntity.getId())){
			//根据规则生产id
			amountConfigEntity.setId(Guid.newDCGuid());
			//记录创建人工号
			amountConfigEntity.setCreateUser(userCode);
			//记录创建时间
			amountConfigEntity.setCreateDate(new Date());
			if(!isValid(amountConfigEntity)) {
				WorkflowException re = new WorkflowException(
						WorkflowExceptionType.WORKFLOW_CREATEAMOUNTCONFIG_FAIL);
				throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
						new Object[] {}) {

				};
			}else{
				amountConfigService.insertBranch(amountConfigEntity);
			}
		}else{
			//记录修改人工号
			amountConfigEntity.setModifyUser(userCode);
			//记录修改时间
			amountConfigEntity.setModifyDate(new Date());
			if(!isValid(amountConfigEntity)) {
				WorkflowException re = new WorkflowException(
						WorkflowExceptionType.WORKFLOW_CREATEAMOUNTCONFIG_FAIL);
				throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
						new Object[] {}) {

				};
			}else{
				amountConfigService.updateBranch(amountConfigEntity);
			}
		}
	}

	
	/**
	 * 
	 *<pre>
	 * 方法体说明：根据ID删除金额配置信息
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:59:50
	 * @param selIds 配置ID
	 *</pre>
	 */
	@Override
	public void deleteById(String id) {
		amountConfigService.deleteById(id);
	}

	/**
	 * 
	 *<pre>
	 * 方法体说明：检查业务约束
	 * 1、工作流名称，当前审批环节相同时，金额范围不能存在交集。（如：常规理赔申请-区域经理：1000-5000，则下一条常规理赔申请-区域经理，金额上限必须小于等于1000，金额下限必须大于5000）
	 * 2、目标审批环节和当前审批环节不能相同。
	 * 3、不能存在2条工作流名称，当前审批环节，目标审批环节相同的配置。
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:56:03
	 * @param amountConfigEntity 查询参数
	 * @return 是否符合业务规则
	 *</pre>
	 */
	@Override
	public boolean isValid(AmountConfigEntity amountConfigEntity) {
		if(amountConfigEntity != null && amountConfigEntity.getCurrentApproStepNo().equals(amountConfigEntity.getTargetApproStepNo())) {
			return false;
		}
		AmountConfigEntity entity = new AmountConfigEntity();
		entity.setCurrentApproStepNo(amountConfigEntity.getCurrentApproStepNo());
		entity.setMcDefiniTionName(amountConfigEntity.getMcDefiniTionName());
		List<AmountConfigEntity> list = this.queryAllBranch(entity,0,100);
		boolean addComp=true;
		for(AmountConfigEntity ac : list){
			if(ac.getId().equals(amountConfigEntity.getId())){
				addComp=false;
			}
		}
		if(addComp){
			list.add(amountConfigEntity);
		}
		List<Double> minList = new ArrayList<Double>();
		List<Double> maxList = new ArrayList<Double>();
		for(int i = 0; i < list.size(); i++) {
			AmountConfigEntity ce = (AmountConfigEntity)list.get(i);
			minList.add(Double.parseDouble(ce.getMinAmount().toString()));
			maxList.add(Double.parseDouble(ce.getMaxAmount().toString()));
		}
		for(int i = 0; i < maxList.size(); i++) {
			double amount = (double)maxList.get(i);
			if(i < maxList.size() - 1) {
				double min = minList.get(i+1);
				double max = maxList.get(i+1);
				if(min < amount) {
					return false;
				}
			}
		}
		return amountConfigService.isValid(amountConfigEntity);
	}

	/**
	 * 
	 * <p>
	 * Description:查询所有审批环节<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-13
	 * @return
	 * List<BpmsActivity>
	 */
	public List<BpmsActivity> findActivity(String processDefName) {
		if(!StringUtil.isEmpty(processDefName)){
			List<BpmsActivity> list= normalClaimManager.queryActivityByProcessDefID(processDefName);
			for(BpmsActivity activity:list){
				 activity.setActivityName(activity.getActivityName()+"("+activity.getActivityDefId()+")");
			}
			return list;
		}
		return null;
	}
	
}
