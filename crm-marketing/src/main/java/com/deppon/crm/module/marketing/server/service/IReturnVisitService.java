/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IReturnVisitService.java
 * @package com.deppon.crm.module.marketing.server.service 
 * @author ZhuPJ
 * @version 0.1 2012-3-24
 */
package com.deppon.crm.module.marketing.server.service;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinionVo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotentialVo;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.domain.WarnStatusUpdateInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.CustAnswerDto;

/**   
 * <p>
 * Description:回访-Service<br />
 * </p>
 * @title IReturnVisitService.java
 * @package com.deppon.crm.module.marketing.server.service 
 * @author ZhuPJ
 * @version 0.1 2012-3-24
 */

public interface IReturnVisitService {

	/**
	 * <p>
	 * Description: 保存回访信息主体信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-3-26
	 * @param rv
	 * @param rvoList
	 * @param rvvpList
	 * @param dto 回访问卷答案实体
	 * @return
	 * boolean
	 */
	 boolean saveReturnVisit(ReturnVisit rv,
			List<ReturnVisitOpinion> rvoList,
			List<ReturnVisitVolumePotential> rvvpList);

	/**
	 * 
	 * <p>
	 * Description:回访录入查询<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Mar 26, 2012
	 * @param condition
	 * @return
	 * List<ReturnVisit>
	 */
	 List<ReturnVisit> getReturnVisitList(ReturnVisitQueryCondition condition, int start, int limit);

	/**
	 * 
	 * <p>
	 * Description:回访录入查询<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 11, 2012
	 * @param condition
	 * @return
	 * int
	 */
	 int getReturnVisitCount(ReturnVisitQueryCondition condition);
	
	/**
	 * 
	 * <p>
	 * Description:回访录入查询--查询详情<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Mar 26, 2012
	 * @param id
	 * @return
	 * ReturnVisit
	 */
	 ReturnVisit getReturnVisit(int id);

	/**
	 * 
	 * <p>
	 * Description:回访录入查询--根据回访id查询客户意见列表<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 1, 2012
	 * @param returnVisitId
	 * @return
	 * List<ReturnVisitOpinion>
	 */
	 List<ReturnVisitOpinion> getReturnVisitOpinionList(int returnVisitId);

	/**
	 * 
	 * <p>
	 * Description:回访录入查询--根据回访id查询发货潜力列表<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 1, 2012
	 * @param returnVisitId
	 * @return
	 * List<ReturnVisitVolumePotential>
	 */
	 List<ReturnVisitVolumePotential> getReturnVisitVolumePotential(int returnVisitId);

	/**
	 * <p>
	 * Description: 录入页面初始化<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-12
	 * @param rv
	 * @return
	 * ReturnVisit
	 */
	 ReturnVisit initCreateReturnvisitPage(String id, String type);
	/**
	 * 
	 * <p>
	 * 录入页面初始化<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-25
	 * @param id
	 * @param type
	 * @return
	 * ReturnVisit
	 */
	 ReturnVisit initCreateReturnvisitPageByCust(String id, String type);

	/**
	 * <p>
	 * Description: 根据客户信息取得回访历史<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-17
	 * @param rv
	 * @return
	 * List<ReturnVisit>
	 */
	 List<ReturnVisit> getReturnVisitLog(ReturnVisitQueryCondition condition);

	/**
	 * <p>
	 * 根据客户Id查询该客户下所有联系人的回访ID集合<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param custId
	 * @return
	 * List<String>
	 */
	 List<String> queryRvListByCustId(Map map);

	/**
	 * <p>
	 * 分页查询客户意见<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param marketingIds
	 * @param start
	 * @param limit
	 * @return
	 * List<ReturnVisitOpinion>
	 */
	 List<ReturnVisitOpinionVo> queryRvOptionByMarketingIds(
			List<String> marketingIds, int start, int limit);

	/**
	 * 
	 * <p>
	 * 分页查询货量潜力<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param marketingIds
	 * @param start
	 * @param limit
	 * @return
	 * List<ReturnVisitVolumePotential>
	 */
	 List<ReturnVisitVolumePotentialVo> queryRvPotentialByMarketingIds(
			List<String> marketingIds, int start, int limit);

	/**
	 * <p>
	 * 统计客户意见记录数<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-12
	 * @param marketingIds
	 * @return
	 * int
	 */
	 int countRvOptionByMarketingIds(List<String> marketingIds);

	/**
	 * <p>
	 * 计算货量潜力总记录数br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-12
	 * @param marketingIds
	 * @return
	 * int
	 */
	 int countRvPotentialByMarketingIds(List<String> marketingIds);
	/**
	 * 
	 * <p>
	 * 固定计划回访同步<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-03-26
	 * @param limit
	 * @return
	 */
	public List<Schedule> synFixPlanReturnVisit(ReturnVisit rv);
}
