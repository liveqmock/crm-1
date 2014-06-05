/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireUtils.java
 * @package com.deppon.crm.module.marketing.server.utils
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-10
 */
package com.deppon.crm.module.marketing.server.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.questionnaire.CustQuestionnaireDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireConstance;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireQueryCondi;
import com.deppon.crm.module.marketing.shared.exception.QuestionnaireException;
import com.deppon.crm.module.marketing.shared.exception.QuestionnaireExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * <p>
 * Description:问卷管理模块工具类<br />
 * </p>
 * @title QuestionnaireUtils.java
 * @package com.deppon.crm.module.marketing.server.utils
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-10
 */
public class QuestionnaireUtils {
	/**
	 * 
	 * <p>
	 * Description:问卷管理主页面重新封装查询条件<br />
	 * @author xiaohongye
	 * @param queryCondi
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public static void reorganizeQueryCondi(QuestionnaireQueryCondi queryCondi){
		//问卷编号
		String questionnaireCode = queryCondi.getQuestionnaireCode();
		//问卷名称
		String questionnaireName = queryCondi.getQuestionnaireName();
		if(questionnaireCode != null){
			//问卷编号去空格
			queryCondi.setQuestionnaireCode(questionnaireCode.trim());
		}
		if(questionnaireName != null){
			//问卷名称模糊查询
			queryCondi.setQuestionnaireName("%"+questionnaireName+"%");
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据生效时间、失效时间返回问卷状态<br />
	 * @author xiaohongye
	 * @param effectiveTime
	 * @version V0.1 
	 * @Date 2014-3-11
	 */
	public static String operateQuestionnaireStatus(Date effectiveTime,Date invalidTime){
		String status = null;
		//生效时间晚于当前时间，问卷状态为待生效
		if(QuestionnaireValidateUtils.dateCompareTo(effectiveTime,new Date()) > 0){
			status = QuestionnaireConstance.SURVEY_STATUS_WAITINGVALID;
		}
		//当前时间晚于生效时间，早于失效时间，问卷状态为生效中
		else if(QuestionnaireValidateUtils.dateCompareTo(new Date(),effectiveTime) >= 0
				&& QuestionnaireValidateUtils.dateCompareTo(invalidTime,new Date()) >= 0){
			status = QuestionnaireConstance.SURVEY_STATUS_VALID;
		}
		//如果当前时间晚于失效时间，问卷状态为过期
		else{
			status = QuestionnaireConstance.SURVEY_STATUS_OVERDUE;
		}
		return status;
	}
	/**
	 * 生成excel表头
	 * @author 盛诗庆
	 * @param questionList
	 * @return
	 */
	public static String[] getHeads(List<QuestionDetail> questionList){
		String[] heads = new String[31];
		heads[0] = "本部";  
		heads[1] = "事业部"; 
		heads[2] = "大区"; 
		heads[3] = "小区";  
		heads[4] = "部门"; 
		heads[5] = "客户编码";
		heads[6] = "客户名称";  
		heads[7] = "属性"; 
		heads[8] = "客户类型";
		heads[9] = "客户等级"; 
		heads[10] = "日期";
		if(questionList == null || questionList.size() == 0){//问卷的问题为空
			//todo 问卷问题为空处理;正式环境不可能出现问卷没有问题
		}else{//设置表头
			for(int i = 0; i < questionList.size();i++){
				heads[i + 11] = questionList.get(i).getQuestionTitle();
			}
		}
		return heads;
	}
	/**
	 * 将用户答案封装成excel导出使用的格式
	 * @author 盛诗庆
	 * @param custQuestionnaireList
	 * @return
	 */
	public static List<List<String>> convertQuestionAnswer(List<CustQuestionnaireDetail> custQuestionnaireList){
		List<List<String>> exportData = new ArrayList<List<String>>();
		for(int i = 0;i < custQuestionnaireList.size();i++){
			List<String> dataRow = new ArrayList<String>();
			dataRow.add(custQuestionnaireList.get(i).getBusinessGroup());
			dataRow.add(custQuestionnaireList.get(i).getBusinessUnit());
			dataRow.add(custQuestionnaireList.get(i).getBigRegion());
			dataRow.add(custQuestionnaireList.get(i).getSmallRegion());
			dataRow.add(custQuestionnaireList.get(i).getBusinessDepartment());
			dataRow.add(custQuestionnaireList.get(i).getCustCode());
			dataRow.add(custQuestionnaireList.get(i).getCustName());
			dataRow.add(custQuestionnaireList.get(i).getCustAttribute());
			dataRow.add(custQuestionnaireList.get(i).getCustType());
			dataRow.add(custQuestionnaireList.get(i).getCustLevel());
			dataRow.add(custQuestionnaireList.get(i).getVisitDate());
			dataRow.add(custQuestionnaireList.get(i).getAnswer1());
			dataRow.add(custQuestionnaireList.get(i).getAnswer2());
			dataRow.add(custQuestionnaireList.get(i).getAnswer3());
			dataRow.add(custQuestionnaireList.get(i).getAnswer4());
			dataRow.add(custQuestionnaireList.get(i).getAnswer5());
			dataRow.add(custQuestionnaireList.get(i).getAnswer6());
			dataRow.add(custQuestionnaireList.get(i).getAnswer7());
			dataRow.add(custQuestionnaireList.get(i).getAnswer8());
			dataRow.add(custQuestionnaireList.get(i).getAnswer9());
			dataRow.add(custQuestionnaireList.get(i).getAnswer10());
			dataRow.add(custQuestionnaireList.get(i).getAnswer11());
			dataRow.add(custQuestionnaireList.get(i).getAnswer12());
			dataRow.add(custQuestionnaireList.get(i).getAnswer13());
			dataRow.add(custQuestionnaireList.get(i).getAnswer14());
			dataRow.add(custQuestionnaireList.get(i).getAnswer15());
			dataRow.add(custQuestionnaireList.get(i).getAnswer16());
			dataRow.add(custQuestionnaireList.get(i).getAnswer17());
			dataRow.add(custQuestionnaireList.get(i).getAnswer18());
			dataRow.add(custQuestionnaireList.get(i).getAnswer19());
			dataRow.add(custQuestionnaireList.get(i).getAnswer20());
			exportData.add(dataRow);
		}
		return exportData;
	}
	/**
	 * 删除字符串前后的所有空格，包括全角和半角空格
	 * @author 盛诗庆
	 * @param s
	 * @return
	 */
	public static String trimAllBlank(String s){
		    s = s.trim();  
		    while(s.startsWith("　")){  
		       s = s.substring(1,s.length()).trim();  
		    }  
		    while(s.endsWith("　")){  
		       s = s.substring(0,s.length()-1).trim();  
		    }
		    return s; 
	}
}
