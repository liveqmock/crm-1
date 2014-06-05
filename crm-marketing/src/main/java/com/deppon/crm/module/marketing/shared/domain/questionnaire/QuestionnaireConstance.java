/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireConstance.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
package com.deppon.crm.module.marketing.shared.domain.questionnaire;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * Description:问卷管理中需要使用的常量<br />
 * </p>
 * @title QuestionnaireConstance.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
public class QuestionnaireConstance {
	/**
     * 适用范围
     */
	//回访
    public static final String SURVEY_RANGE_VISIT = "VISIT";
    /**
     * 问卷状态
     */
    //待生效
    public static final String SURVEY_STATUS_WAITINGVALID = "WAITINGVALID";
    //生效
    public static final String SURVEY_STATUS_VALID = "VALID";
    //使用中
    public static final String SURVEY_STATUS_USING = "USING";
    //过期
    public static final String SURVEY_STATUS_OVERDUE = "OVERDUE";
    /**
     * 问题类型
     */
    //单选
    public static final String QUESTION_TYPE_RADIO = "RADIO";
    //多选
    public static final String QUESTION_TYPE_MULTIPLE_CHOICE = "MULTIPLE_CHOICE";
    //简答
    public static final String QUESTION_TYPE_QUESTION_ANSWER = "QUESTION_ANSWER";
    /**
     * 是否允许填空
     */
    //允许填空
    public static final String BLANK_YES = "1";
    //不允许填空
    public static final String BLANK_NO = "0";
    //其他选项
    public static final String BLANK_DES = "其他";
    
    //问卷中最多可包含的问题数量
    public static final int MAX_QUESTION_NUMBER = 20;
    //问卷中最少可包含的问题数量
    public static final int MIN_QUESTION_NUMBER = 2;
    //删除
    public static final String OPERATOR_DELETE = "删除";
    //修改
    public static final String OPERATOR_UPDATE = "修改";
    /**
     * 可能为空的对象
     */
    //问卷信息
    public static final String OBJECT_QUESTIONNAIRE = "问卷信息";
    //问卷问题列表
    public static final String OBJECT_QUESTION_MAPPER = "问卷问题列表";
    //问卷id
    public static final String OBJECT_QUESTIONNAIRE_ID = "问卷id";
    //回访id
    public static final String OBJECT_VISIT_ID = "回访id";
    //问题id列表
    public static final String OBJECT_QUESTION_ID_LIST = "问题id列表";
    
    /**
     * 是否操作过问题列表的标记字段，YES代表操作过，NO代表未操作过
     */
    //操作过
    public static final String HAVE_OPERATE_LIST_YES = "YES";
    //未操作过
    public static final String HAVE_OPERATE_LIST_NO = "NO";
    //导出文件后缀
    public static final String EXPORT_SUFFIX = ".xls";
    //导出问卷详情结果最大行数
    public static final int MAX_ROW = 20000;
    
    /**
     * 含有问卷的计划的操作类型
     */
    //新增
    public static final String OPERATE_TYPE_ADD = "ADD";
    //修改
    public static final String OPERATE_TYPE_UPDATE = "UPDATE";
    //删除
    public static final String OPERATE_TYPE_DELETE = "DELETE";
}
