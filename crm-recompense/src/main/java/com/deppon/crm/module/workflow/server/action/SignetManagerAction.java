package com.deppon.crm.module.workflow.server.action;

import java.util.List;

import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.workflow.server.manager.ISignetManagerManager;
import com.deppon.crm.module.workflow.shared.domain.SignetManager;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 
 * <p>
 * Description:印章管理员设置Action<br />
 * </p>
 * @title SignetManagerAction.java
 * @package com.deppon.crm.module.workflow.server.action 
 * @author liuHuan
 * @version 0.1 2013-7-31
 */
public class SignetManagerAction  extends AbstractAction {
	
	private ISignetManagerManager signetManagerManager;

	public void setSignetManagerManager(ISignetManagerManager signetManagerManager) {
		this.signetManagerManager = signetManagerManager;
	}
	
	//员工id
	private Integer empId;
	
	//员工集合
	private List<SignetManager> signetManagerList;
	
	private SignetManager signetManager;
	//印章管理员表id
	private Integer id;
	//部门集合
	private List<Department> deptList;
	//操作代码
	private int opCode;
	
	private Long totalCount;
	
	private int start;
	
	private int limit;
	
	
	public List<Department> getDeptList() {
		return deptList;
	}


	public List<SignetManager> getSignetManagerList() {
		return signetManagerList;
	}


	public void setEmpId(Integer empId) {
		this.empId = empId;
	}


	public void setSignetManager(SignetManager signetManager) {
		this.signetManager = signetManager;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public int getOpCode() {
		return opCode;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public void setLimit(int limit) {
		this.limit = limit;
	}


	public Long getTotalCount() {
		return totalCount;
	}


	/**
	 * 
	 * <p>
	 * Description:印章管理员列表显示<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-7-31
	 * @return
	 * String
	 */
	@JSON
	public String findSignetManager(){
		signetManagerList=signetManagerManager.findSignetManager(empId,start,limit);
		totalCount=signetManagerManager.findSignetManagerCount(empId);
		return SUCCESS;
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:添加印章管理员<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-1
	 * @return
	 * String
	 */
	@JSON
	public String addSignetManager(){
		opCode=signetManagerManager.addSignetManager(signetManager);
		return SUCCESS;
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:删除印章管理员<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-1
	 * @return
	 * String
	 */
	@JSON
	public String deleteSignetManager(){
		signetManagerManager.deleteSignetManagerById(id);
		return SUCCESS;
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询所有事业部与枢纽中心<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-5
	 * @return
	 * String
	 */
	public String queryBizAndHubCenterDept() {
		deptList = signetManagerManager.getBizAndHubCenterDeptList();
		return SUCCESS;
	}
	
	

}
