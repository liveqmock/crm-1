package com.deppon.crm.module.customer.server.action;

import java.util.List;

import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.ICustLabelManager;
import com.deppon.crm.module.customer.shared.domain.CustAddress;
import com.deppon.crm.module.customer.shared.domain.Label;
import com.deppon.foss.framework.server.web.action.AbstractAction;

public class CustLabelIndexAction extends AbstractAction{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -2840218819495465167L;
	private ICustLabelManager custLabelManager;
	//基础标签列表
	private List<Label> labelList;
	//基础标签列表总量
	private int labelCount;
	//某一个基础资料标签
	private Label label = new Label();
	//某一个标签的ID
	private String labelId;
	//标签名字
	private String labelName;
	//客户地址
	private CustAddress custAddress;
	//部门ID
	private String deptId;
	private IAlterMemberManager alterMemberManager;
	/**
	 * Description:初始化部门的所有标签<br />
	 * @version 0.1 2013-4-17
	 * @return String
	 */
	public String searchLabel() {
		labelList = custLabelManager.searchLabel(deptId);
		if (labelList != null && labelList.size() > 0) {
			labelCount = labelList.size();
		} else {
			labelCount = 0;
		}
		return SUCCESS;
	}
	
	/**
	 * @Description:修改基础资料标签<br />
	 * @author CoCo
	 * @version 0.1 2013-7-4
	 * @return String
	 */
	public String updateCustLabel(){
		custLabelManager.modifyLabel(label);
		return SUCCESS;
	}
	/**
	 * Description:保存客户标签<br />
	 * @version 0.1 2013-4-20
	 * @return String
	 */
	public String saveLabel() {
		labelId = custLabelManager.saveLabel(label);
		return SUCCESS;
	}
	
	/**
	 * Description:删除客户标签<br />
	 * @version 0.1 2013-4-22
	 * @return String
	 */
	public String deleteCustLabel() {
		custLabelManager.deleteLabel(labelId);
		return SUCCESS;
	}
	
	/**
	 * Description:修改潜散客、客户地址<br />
	 * @version 0.1 2013-4-23
	 * @return String
	 */
	public String updateCustAddress() {
		alterMemberManager.alterCustAddress(custAddress);
		return SUCCESS;
	}
	
	/**
	 * Description:labelList<br />
	 * @author CoCo
	 * @version 0.1 2013-4-17
	 */
	public List<Label> getlabelList() {
		return labelList;
	}
	/**
	 * Description:labelList<br />
	 * @author CoCo
	 * @version 0.1 2013-4-17
	 */
	public void setlabelList(List<Label> labelList) {
		this.labelList = labelList;
	}
	/**
	 * Description:labelCount<br />
	 * @author CoCo
	 * @version 0.1 2013-4-17
	 */
	public int getLabelCount() {
		return labelCount;
	}
	/**
	 * Description:labelCount<br />
	 * @author CoCo
	 * @version 0.1 2013-4-17
	 */
	public void setLabelCount(int labelCount) {
		this.labelCount = labelCount;
	}
	/**
	 * Description:custLabelManager<br />
	 * @version 0.1 2013-4-17
	 */
	public void setCustLabelManager(ICustLabelManager custLabelManager) {
		this.custLabelManager = custLabelManager;
	}
	/**
	 * Description:label<br />
	 * @author CoCo
	 * @version 0.1 2013-4-20
	 */
	public Label getLabel() {
		return label;
	}
	/**
	 * Description:label<br />
	 * @author CoCo
	 * @version 0.1 2013-4-20
	 */
	public void setLabel(Label label) {
		this.label = label;
	}

	/**
	 * Description:labelId<br />
	 * @author CoCo
	 * @version 0.1 2013-4-20
	 */
	public String getLabelId() {
		return labelId;
	}

	/**
	 * Description:labelId<br />
	 * @author CoCo
	 * @version 0.1 2013-4-20
	 */
	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	/**
	 * Description:custAddress<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public CustAddress getCustAddress() {
		return custAddress;
	}

	/**
	 * Description:custAddress<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public void setCustAddress(CustAddress custAddress) {
		this.custAddress = custAddress;
	}

	/**
	 * Description:alterMemberManager<br />
	 * @author CoCo
	 * @version 0.1 2013-4-25
	 */
	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}

	public String getDeptId() {
		return deptId;
	}

	/**
	 * Description:labelName<br />
	 * @author CoCo
	 * @version 0.1 2013-7-4
	 */
	public String getLabelName() {
		return labelName;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
}
