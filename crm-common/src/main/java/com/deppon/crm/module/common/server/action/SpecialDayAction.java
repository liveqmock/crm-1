package com.deppon.crm.module.common.server.action;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.server.manager.impl.SpecialDayManager;
import com.deppon.crm.module.common.shared.domain.SpecialDay;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 作者：zouming 创建时间：2012-11-20 最后修改时间：2012-11-20 描述：
 */
public class SpecialDayAction extends AbstractAction {
	// manager
	private SpecialDayManager specialDayManager;

	public void setSpecialDayManager(SpecialDayManager specialDayManager) {
		this.specialDayManager = specialDayManager;
	}

	// 后台返回的特殊日期
	private SpecialDay specialDay;// 后台返回给前端

	public SpecialDay getSpecialDay() {
		return specialDay;
	}

	public void setSpecialDay(SpecialDay specialDay) {
		this.specialDay = specialDay;
	}

	// 后台返回特殊日期列表
	List<SpecialDay> specialDayList;

	public List<SpecialDay> getSpecialDayList() {
		return specialDayList;
	}

	// 前台传给后台 查询界面
	private Date endDate;
	private Date startDate;
	private String dateType;

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	// 前台传给后台 新增与修改界面
	private String id;
	private Date specialDate;
	public String[] idArray;

	public void setId(String id) {
		this.id = id;
	}

	public void setSpecialDate(Date specialDate) {
		this.specialDate = specialDate;
	}

	public void setIdArray(String[] idArray) {
		this.idArray = idArray;
	}

	/**
	 * 
	 * <p>
	 * Description:根据用户输入条件查询特殊日期<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2012-11-20下午3:46:55
	 * @return String
	 * @update 2012-11-20下午3:46:55
	 */
	public String searchSpecialDayByCondition() {
		specialDayList = specialDayManager.getSpecialDayList(startDate,
				endDate, dateType);
		startDate = null;
		endDate = null;
		dateType = null;
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:提交特殊日期<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2012-11-20下午3:47:46
	 * @return String
	 * @update 2012-11-20下午3:47:46
	 */
	@JSON
	public String submitSpecialDay() {
		SpecialDay specialDayToBackstage = new SpecialDay();
		specialDayToBackstage.setDateType(dateType);
		specialDayToBackstage.setSpecialDate(specialDate);
		System.err.println(specialDate);
		specialDayManager.addSpecialDay(specialDayToBackstage);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:跟新特殊日期<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2012-11-20下午3:48:06
	 * @return String
	 * @update 2012-11-20下午3:48:06
	 */
	@JSON
	public String updateSpecialDay() {
		specialDay = new SpecialDay();
		specialDay.setDateType(dateType);
		specialDay.setSpecialDate(specialDate);
		specialDay.setId(id);
		specialDayManager.updateSpecialDayById(specialDay);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:根据ID查询特殊日期<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2012-11-20下午5:49:04
	 * @return String
	 * @update 2012-11-20下午5:49:04
	 */
	@JSON
	public String getSpecialDayById() {
		specialDay = specialDayManager.getSpecialDayById(id);
		return SUCCESS;
	}

	


	/**
	 * <p>
	 * Description:根据ID数组删除特殊日期<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2012-11-20下午3:48:18
	 * @return String
	 * @update 2012-11-20下午3:48:18
	 */
	@JSON
	public String deleteSpecialDayByIdArrayList() {
		specialDayManager.deleteSpecialDayList(idArray);
		return SUCCESS;
	}

}
