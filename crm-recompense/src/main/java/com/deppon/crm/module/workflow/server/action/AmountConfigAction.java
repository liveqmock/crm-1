package com.deppon.crm.module.workflow.server.action;

import java.util.List;

import com.deppon.bpmsapi.module.client.domain.BpmsActivity;
import com.deppon.crm.module.workflow.server.manager.IAmountConfigManager;
import com.deppon.crm.module.workflow.server.util.AmountConfigEntity;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * <p>
 * Description:审批金额配置Action<br />
 * </p>
 * @title AmountConfigAction.java
 * @package com.deppon.crm.module.workflow.server.action 
 * @author liuHuan
 * @version 0.1 2013-8-13
 */
public class AmountConfigAction  extends AbstractAction implements ModelDriven<AmountConfigEntity> {
	
	private IAmountConfigManager amountConfigManager;

	public void setAmountConfigManager(IAmountConfigManager amountConfigManager) {
		this.amountConfigManager = amountConfigManager;
	}
	
	private List<AmountConfigEntity> amountConfigList;
	
	private AmountConfigEntity amountConfigEntity = new AmountConfigEntity();
	
	private Long totalCount;

	private int start;
	
	private int limit;
	
	private String id;
	
	private List<BpmsActivity> activityList;
	
	
	public List<AmountConfigEntity> getAmountConfigList() {
		return amountConfigList;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<BpmsActivity> getActivityList() {
		return activityList;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAmountConfigEntity(AmountConfigEntity amountConfigEntity) {
		this.amountConfigEntity = amountConfigEntity;
	}

	/**
	 * 
	 * <p>
	 * Description:分页列表<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-13
	 * @return
	 * String
	 */
	@JSON
	public String findAmountConfigPage(){
		amountConfigList=amountConfigManager.queryAllBranch(amountConfigEntity,start,limit);
		totalCount=amountConfigManager.getBranchCount(amountConfigEntity);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:新增配置<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-13
	 * @return
	 * String
	 */
	@JSON
	public String addAmountConfig(){
		amountConfigManager.saveOrUpdate(amountConfigEntity);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:修改配置<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-13
	 * @return
	 * String
	 */
	@JSON
	public String updateAmountConfig(){
		amountConfigManager.saveOrUpdate(amountConfigEntity);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:删除配置<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-13
	 * @return
	 * String
	 */
	@JSON
	public String deleteAmountConfig(){
		amountConfigManager.deleteById(id);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询所有审批环节<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-13
	 * @return
	 * String
	 */
	@JSON
	public String findActivity(){
		activityList= amountConfigManager.findActivity(amountConfigEntity.getMcDefiniTionName());
		return SUCCESS;
	}

	@Override
	public AmountConfigEntity getModel() {
		return amountConfigEntity;
	}

}
