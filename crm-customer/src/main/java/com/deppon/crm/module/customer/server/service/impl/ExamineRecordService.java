package com.deppon.crm.module.customer.server.service.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.server.dao.IExamineRecordDao;
import com.deppon.crm.module.customer.server.service.IExamineRecordService;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
/**   
 * <p>
 * Description:审批记录的service<br />
 * </p>
 * @title IExamineRecordDao.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author 李国文
 * @version 0.1 2013-2-26
 */
public class ExamineRecordService implements IExamineRecordService{
	//審批記錄ｄａｏ
	private IExamineRecordDao examineRecordDao;
	//国际化...
	private IMessageBundle messageBundle;
	
	/**
	 * 	
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param messageBundle
	 * void
	 */
	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param examineRecordDao
	 * void
	 */
	public void setExamineRecordDao(IExamineRecordDao examineRecordDao) {
		this.examineRecordDao = examineRecordDao;
	}
	/**
	 * 
	 * <p>
	 * Description:保存审批记录<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param record
	 *
	 */
	@Override
	public void saveExamineRecord(ExamineRecord record) {
		//保存审批记录
		examineRecordDao.saveExamineRecord(record);
	}
	/**
	 * 
	 * <p>
	 * Description:根据工作流号查询审批记录<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param workflowId
	 * @return
	 *
	 */
	@Override
	public List<ExamineRecord> getExamineRecordByWorkflowId(long workflowId) {
		//根据工作流号查询审批记录
		return examineRecordDao.getExamineRecordByWorkflowId(workflowId);
	}
	/**
	 * 
	 * <p>
	 * Description:获得当前审批人<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param workflowId
	 * @return
	 *
	 */
	@Override
	public String getCurrentPeople(long workflowId) {
		//StringBuffer
		StringBuffer sb = new StringBuffer();
		//根据工作流id查询部门id和角色
		Map<String,String> map = this.examineRecordDao.getDeptIdAndRoleIdByWorkflowId(workflowId);
		//如果结果为空
		if(ValidateUtil.objectIsEmpty(map)){
			//添加错误信息
			sb.append(messageBundle.getMessage("i18n.member.currentExaminerIsOver"));
		}else{
			//获得审批的部门角色
			List<Map<String,String>> examinerList = this.examineRecordDao.getApproverByDeptRoleMap(map);
			//组合文本
			for(Map<String,String> examinerMap:examinerList){
				sb.append("【");
				sb.append(examinerMap.get("EMPNAME"));
				sb.append(examinerMap.get("EMPCODE"));
				sb.append("】 ");
			}
			//待审批人如果为空
			if (examinerList.size() <= 0){
				//提示找不到审批人
				sb.append(messageBundle.getMessage("i18n.member.currentExaminerIsNull",new Object[]{map.get("deptName"),map.get("roleName")}));
			}
		}
		//toString
		return sb.toString();
	}
	
	
}
