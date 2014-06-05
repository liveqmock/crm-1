/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireAction.java
 * @package com.deppon.crm.module.marketing.server.action
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
package com.deppon.crm.module.marketing.server.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.marketing.server.manager.IQuestionnaireManager;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireMapper;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireQueryCondi;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * <p>
 * Description:问卷管理需求相关的action<br />
 * </p>
 * @title QuestionnaireAction.java
 * @package com.deppon.crm.module.marketing.server.action
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
@SuppressWarnings("serial")
public class QuestionnaireAction extends AbstractAction {
	//问卷管理的manager层实现
	private IQuestionnaireManager questionnaireManager;
	//问卷管理的查询条件
	private QuestionnaireQueryCondi questionnaireQueryCondi;
	//问卷管理查询结果列表
	private List<QuestionnaireInfo> questionnaireList;
	//问卷主体信息
	private QuestionnaireInfo questionnaireInfo;
	//问题问卷映射信息列表
	private List<QuestionnaireMapper> questionMapperList;
	//标记字段，用于标记用户在修改问卷时有没有对问题列表进行操作，YES代表操作过,NO代表未操作
	private String haveOperateList;
	//问卷id数组
	private String[] questionnaireIds;
	//问卷状态
	private String questionnaireStatus;
	//分页要用参数
	private int start;
	//每页记录数
	private int limit;
	//总行数
	private Long totalCount;
	//导出excel是否成功标志 1成功，0失败
	private String canExport = "0";
	//问题信息列表
	private List<QuestionDetail> questionList;
	//问题id列表
	private List<String> questionIdList;
	//问卷名称
	private String fileName = "questionnaire";
	//excel导出文件流
	private InputStream inputStream;
	//问卷id
	private String questionnaireId;
	//统计起始时间
	private Date staticStartDate;
	//统计结束时间
	private Date staticEndDate;
	//客户id
	private String custId;
	//回访id
	private String visitId;
	/**
	 * <p>
	 * Description:查询问卷管理结果列表<br />
	 * @author xiaohongye
	 * @version V0.1 
	 * @Date 2014-3-6
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchQuestionnaireInfoList() {
		Map<String,Object> map = questionnaireManager.searchQuestionnaireInfoList(questionnaireQueryCondi,start,limit);
		if(map != null){
			questionnaireList = (List<QuestionnaireInfo>) map.get("questionnaireList");
			totalCount = Long.valueOf(map.get("totalCount").toString());
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:生成问卷：同时生成问卷主体信息与问题问卷映射信息<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-11
	 */
	@JSON
	public String createQuestionnaire(){
		questionnaireManager.createQuestionnaire(questionnaireInfo, questionMapperList);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:修改问卷：同时修改问卷主体信息与问题问卷映射信息<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-11
	 */
	@JSON
	public String updateQuestionnaire(){
		questionnaireManager.updateQuestionnaire(questionnaireInfo, questionMapperList, haveOperateList);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:删除问卷：同时删除问卷主体信息与问题问卷映射信息<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-11
	 */
	@JSON
	public String deleteQuestionnaire(){
		questionnaireManager.deleteQuestionnaire(questionnaireIds, questionnaireStatus);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据问卷id查询问题列表<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-12
	 */
	@JSON
	public String searchQuestionListByQuestionnaireId(){
		questionList = questionnaireManager.searchQuestionListByQuestionnaireId(questionnaireIds[0]);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据问题id列表查询问题列表<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-12
	 */
	@JSON
	public String searchQuestionListByQuestionIdList(){
		questionList = questionnaireManager.searchOptionByQuestionIds(questionIdList,questionnaireIds[0]);
		return SUCCESS;
	}
	/**
	 * 统计问卷详情
	 * @author 盛诗庆
	 * @date 2014-03-26
	 * @version 0.1
	 * @return
	 */
	@JSON
	public String staticsQuestionnaireResultBySurveyId(){
		questionList = questionnaireManager.staticsQuestionsBySurveyId(questionnaireIds[0],staticStartDate,staticEndDate);
		return SUCCESS;
	}
	/**
	 * 创建统计结果excel
	 * @author 盛诗庆
	 * @return
	 */
	@JSON
	public String createStaticsExcel(){
		 Map<String, Object> map =  (Map<String, Object>) questionnaireManager.exportStaticsExcel(questionnaireId,null,staticStartDate,staticEndDate);
		 canExport = (String) map.get("canExport");
		 return returnSuccess(); 
	}
	/**
	 * 导出问卷统计结果
	 * @author 盛诗庆
	 * @return
	 */
	@JSON
	public String exportStaticsExcel(){
		 Map<String, Object> map =  (Map<String, Object>) questionnaireManager.exportStaticsExcel(null,fileName,null,null);
		 inputStream = (InputStream)map.get("inputStream");
		 fileName = (String)map.get("questionnaireTitle");
		 return returnSuccess(); 
	}
	/**
	 * 服务器端查询客户答案基本信息
	 * @author 盛诗庆
	 * @return
	 */
	@JSON
	public String createQuestionnaireDetailExcel(){
		 Map<String, Object> map =  (Map<String, Object>) questionnaireManager.exportCustQuestionnaireDetail(questionnaireId,null,staticStartDate,staticEndDate);
		 canExport = (String) map.get("canExport");
		 return returnSuccess(); 
	}
	/**
	 * 导出答过问卷的客户基本信息
	 * @author 盛诗庆
	 * @return
	 */
	@JSON
	public String exportQuestionnaireDetailExcel(){
		 Map<String, Object> map =  (Map<String, Object>) questionnaireManager.exportCustQuestionnaireDetail(null,fileName,null,null);
		 inputStream = (InputStream)map.get("inputStream");
		 fileName = (String)map.get("questionnaireTitle");
		 return returnSuccess(); 
	}
	/**
	 * 根据客户id找到客户答过的问卷列表
	 * @author 盛诗庆
	 * @return
	 */
	@JSON
	@SuppressWarnings("unchecked")
	public String queryQuestionnaireListByCustid(){
		Map<String,Object> map = questionnaireManager.queryQuestionnaireListByCustid(custId, start, limit);
		questionnaireList = (List<QuestionnaireInfo>) map.get("questionnaireList");
		totalCount = Long.valueOf(map.get("totalCount").toString());
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description:根据回访id查询该回访问卷答案<br />
	 * @author 盛诗庆
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-31
	 */
	@JSON
	public String viewQuestionnaireByVisitId(){
		//回访id不能为空
		questionList = questionnaireManager.viewQuestionnaireByVisitId(visitId);
		return SUCCESS;
	}
	/**
	 * @param questionIdList the questionIdList to get
	 */
	public List<String> getQuestionIdList() {
		return questionIdList;
	}

	/**
	 * @param questionIdList the questionIdList to set
	 */
	public void setQuestionIdList(List<String> questionIdList) {
		this.questionIdList = questionIdList;
	}

	/**
	 * @param questionnaireQueryCondi the questionnaireQueryCondi to get
	 */
	public QuestionnaireQueryCondi getQuestionnaireQueryCondi() {
		return questionnaireQueryCondi;
	}

	/**
	 * @param questionnaireInfo the questionnaireInfo to get
	 */
	public QuestionnaireInfo getQuestionnaireInfo() {
		return questionnaireInfo;
	}

	/**
	 * @param questionnaireInfo the questionnaireInfo to set
	 */
	public void setQuestionnaireInfo(QuestionnaireInfo questionnaireInfo) {
		this.questionnaireInfo = questionnaireInfo;
	}

	/**
	 * @param questionMapperList the questionMapperList to get
	 */
	public List<QuestionnaireMapper> getQuestionMapperList() {
		return questionMapperList;
	}

	/**
	 * @param questionMapperList the questionMapperList to set
	 */
	public void setQuestionMapperList(List<QuestionnaireMapper> questionMapperList) {
		this.questionMapperList = questionMapperList;
	}

	/**
	 * @param haveOperateList the haveOperateList to set
	 */
	public void setHaveOperateList(String haveOperateList) {
		this.haveOperateList = haveOperateList;
	}

	/**
	 * @param questionnaireIds the questionnaireIds to set
	 */
	public void setQuestionnaireIds(String[] questionnaireIds) {
		this.questionnaireIds = questionnaireIds;
	}

	/**
	 * @param questionnaireStatus the questionnaireStatus to set
	 */
	public void setQuestionnaireStatus(String questionnaireStatus) {
		this.questionnaireStatus = questionnaireStatus;
	}

	/**
	 * @param totalCount the totalCount to get
	 */
	public Long getTotalCount() {
		return totalCount;
	}


	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}


	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}


	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}


	/**
	 * @param questionnaireList the questionnaireList to get
	 */
	public List<QuestionnaireInfo> getQuestionnaireList() {
		return questionnaireList;
	}


	/**
	 * @param questionnaireList the questionnaireList to set
	 */
	public void setQuestionnaireList(List<QuestionnaireInfo> questionnaireList) {
		this.questionnaireList = questionnaireList;
	}


	/**
	 * @param questionnaireQueryCondi the questionnaireQueryCondi to set
	 */
	public void setQuestionnaireQueryCondi(
			QuestionnaireQueryCondi questionnaireQueryCondi) {
		this.questionnaireQueryCondi = questionnaireQueryCondi;
	}


	/**
	 * @param questionnaireManager the questionnaireManager to set
	 */
	public void setQuestionnaireManager(IQuestionnaireManager questionnaireManager) {
		this.questionnaireManager = questionnaireManager;
	}

	/**
	 * @param questionList the questionList to get
	 */
	public List<QuestionDetail> getQuestionList() {
		return questionList;
	}

	/**
	 * @param questionList the questionList to set
	 */
	public void setQuestionList(List<QuestionDetail> questionList) {
		this.questionList = questionList;
	}

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @return the questionnaireTitle
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param questionnaireTitle the questionnaireTitle to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public Date getStaticStartDate() {
		return staticStartDate;
	}

	public void setStaticStartDate(Date staticStartDate) {
		this.staticStartDate = staticStartDate;
	}

	public Date getStaticEndDate() {
		return staticEndDate;
	}

	public void setStaticEndDate(Date staticEndDate) {
		this.staticEndDate = staticEndDate;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public void setCanExport(String canExport) {
		this.canExport = canExport;
	}

	public String getCanExport() {
		return canExport;
	}
}
