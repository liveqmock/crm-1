package com.deppon.crm.module.common.server.action;

import java.util.List;

import com.deppon.crm.module.common.server.manager.IHelpDocManager;
import com.deppon.crm.module.common.shared.domain.HelpDoc;
import com.deppon.crm.module.common.shared.domain.HelpDocSearchCondition;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class HelpDocAction extends AbstractAction {
	private HelpDoc helpDoc;

	private List<HelpDoc> helpDocList;

	private HelpDocSearchCondition helpDocSearchCondition =new HelpDocSearchCondition();

	public HelpDoc getHelpDoc() {
		return helpDoc;
	}

	public void setHelpDoc(HelpDoc helpDoc) {
		this.helpDoc = helpDoc;
	}

	public List<HelpDoc> getHelpDocList() {
		return helpDocList;
	}

	public void setHelpDocList(List<HelpDoc> helpDocList) {
		this.helpDocList = helpDocList;
	}

	public HelpDocSearchCondition getHelpDocSearchCondition() {
		return helpDocSearchCondition;
	}

	public void setHelpDocSearchCondition(
			HelpDocSearchCondition helpDocSearchCondition) {
		this.helpDocSearchCondition = helpDocSearchCondition;
	}

	private IHelpDocManager helpDocManager;

	public void setHelpDocManager(IHelpDocManager helpDocManager) {
		this.helpDocManager = helpDocManager;
	}

	private String[] helpIds;

	public void setHelpIds(String[] helpIds) {
		this.helpIds = helpIds;
	}

	private int start;

	public void setStart(int start) {
		this.start = start;
	}

	private int limit;

	public void setLimit(int limit) {
		this.limit = limit;
	}

	private Long totalCount;

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getTotalCount() {
		return totalCount;
	}
	
	public String windowNum;
	public void setWindowNum(String windowNum) {
		this.windowNum = windowNum;
	}

	/**
	 * @author Rock
	 * 帮助系统ACTION；另有部分action在functionAction里面,
	 * 				其struts路径：\src\main\resources\com\deppon\crm\module\authorization\server\META-INF\struts.xml
	 * @see:		其keys：帮组管理		其action路径配置见view层js
	 * @return
	 */
	public String addHelpDoc() {
		helpDocManager.addHelpDoc(helpDoc);
		return SUCCESS;
	}

	public String updateHelpDocById() {
		helpDocManager.updateHelpDocById(helpDoc);
		return SUCCESS;
	}

	public String deleteHelpDocById() {
		helpDocManager.deleteHelpDocByIds(helpIds);
		return SUCCESS;
	}

	/**
	 * @method:根据helpDocSearchCondition获取帮助文档list
	 * @param:{				// 在beforeload里面的params组合时需如下设置(Ext内置)。
	 * 			helpDocSearchCondition:{
	 * 				helpDocSearchCondition.id:String,
	 * 				helpDocSearchCondition.helpTitle:String,
	 * 				helpDocSearchCondition.belongModule:String,
	 * 				helpDocSearchCondition.windowNum:String,
	 * 				helpDocSearchCondition.active:Boolean,
	 * 				helpDocSearchCondition.startDate:Date,
	 * 				helpDocSearchCondition.endDate:Date,
	 * 				helpDocSearchCondition.start:Int,
	 * 				helpDocSearchCondition.limit:Int
	 * 			}
	 * 	}
	 * @return：helpDocList	&&	totalCount
	 * @serialData
	 */
	@JSON
	public String searchHelpDocByCondition() {
		helpDocSearchCondition.setStart(start);
		helpDocSearchCondition.setLimit(limit);

		helpDocList = helpDocManager
				.searchHelpDocByCondition(helpDocSearchCondition);
		totalCount = Long.valueOf(helpDocManager.getCountByCondition(helpDocSearchCondition));
		return SUCCESS;
	}
/**
 * 未使用
 * @return
 */
	public String searchHelpDocById() {
		helpDoc = helpDocManager.getHelpDocById(helpDoc.getId());
		return SUCCESS;
	}

	public String searchHelpDocByWindowNum() {
		helpDoc = helpDocManager.getHelpDocByWindowNum(windowNum);
		return SUCCESS;
	}

}
