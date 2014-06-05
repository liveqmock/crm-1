/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IReturnVisitManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-3-24
 */
package com.deppon.crm.module.marketing.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.shared.domain.CCQueryMarketingRecord;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinionVo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotentialVo;
import com.deppon.crm.module.marketing.shared.domain.WarnStatusUpdateInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerMainInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.CustAnswerDto;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title IReturnVisitManager.java
 * @package com.deppon.crm.module.marketing.server.manager
 * @author ZhuPJ
 * @version 0.1 2012-3-24
 */

public interface IReturnVisitManager {

	/**
	 * 
	 * <p>
	 * Description:回访信息查询<br />
	 * </p>
	 * 
	 * @author zhujunyong
	 * @version 0.1 Mar 26, 2012
	 * @param condition
	 * @param start
	 * @param limit
	 * @return Map<String, Object>
	 */
	Map<String, Object> getReturnVisitList(ReturnVisitQueryCondition condition,
			int start, int limit, User user);

	/**
	 * 
	 * <p>
	 * Description:回访录入查询-查询详情<br />
	 * </p>
	 * 
	 * @author zhujunyong
	 * @version 0.1 Mar 26, 2012
	 * @param id
	 * @return Map<String, Object>
	 */
	Map<String, Object> getReturnVisit(int id);

	/**
	 * 
	 * <p>
	 * 创建回访记录<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2013-1-29
	 * @param rv
	 * @param rvoList
	 * @param rvvpList
	 * @param user
	 * @return boolean
	 */
	boolean createReturnvisit(ReturnVisit rv, List<ReturnVisitOpinion> rvoList,
			List<ReturnVisitVolumePotential> rvvpList, 
			List<AnswerMainInfo> answerMainInfoList,
			WarnStatusUpdateInfo warnInfo, User user);

	/**
	 * <p>
	 * Description: 录入页面初始化<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-4-12
	 * @param ReturnVisit
	 *            rv
	 * @return ReturnVisit
	 */
	ReturnVisit initCreateReturnvisitPage(ReturnVisit rv, User user);

	ReturnVisit initCreateReturnvisitPageByCust(ReturnVisit rv, User user);

	/**
	 * <p>
	 * Description: 根据客户信息取得回访历史<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-4-17
	 * @param rv
	 * @return List<ReturnVisit>
	 */
	List<ReturnVisit> getReturnVisitLog(ReturnVisitQueryCondition condition);

	/**
	 * <p>
	 * 根据客户Id查询该客户下所有联系人的回访ID集合<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param custId
	 * @return List<String>
	 */
	List<String> queryRvListByCustId(Map map);

	/**
	 * <p>
	 * 分页查询客户意见<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param marketingIds
	 * @param start
	 * @param limit
	 */
	List<ReturnVisitOpinionVo> queryRvOptionByMarketingIds(
			List<String> marketingIds, int start, int limit);

	/**
	 * 
	 * <p>
	 * 分页查询货量潜力<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param marketingIds
	 * @param start
	 * @param limit
	 * @return List<ReturnVisitVolumePotential>
	 */
	List<ReturnVisitVolumePotentialVo> queryRvPotentialByMarketingIds(
			List<String> marketingIds, int start, int limit);

	/**
	 * <p>
	 * 统计总记录<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-12
	 * @param marketingIds
	 * @return int
	 */
	int countRvOptionByMarketingIds(List<String> marketingIds);

	/**
	 * <p>
	 * 统计货量潜力总记录<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-12
	 * @param marketingIds
	 * @return int
	 */
	int countRvPotentialByMarketingIds(List<String> marketingIds);
}
