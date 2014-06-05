/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IReturnVisitDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */
package com.deppon.crm.module.marketing.server.dao;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinionVo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotentialVo;
import com.deppon.crm.module.marketing.shared.domain.Schedule;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IReturnVisitDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */

public interface IReturnVisitDao {
	/**
	 * <p>
	 * Description: 保存回访信息主体信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-3-26
	 * @param rv
	 * @return
	 * String
	 */
	 String saveReturnVisit(ReturnVisit rv);
	
	/**
	 * <p>
	 * Description: 保存回访信息——客户意见信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-3-26
	 * @param rvo
	 * @return
	 * boolean
	 */
	 boolean saveReturnVisitOpinion(ReturnVisitOpinion rvo);
	
	/**
	 * <p>
	 * Description: 保存回访信息——走货潜力信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-3-26
	 * @param rvvp
	 * @return
	 * boolean
	 */
	 boolean saveReturnVisitVolumePotential(ReturnVisitVolumePotential rvvp);
	
	
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
	 List<ReturnVisitVolumePotential> getReturnVisitVolumePotentialList(int returnVisitId);
	
	/**
	 * <p>
	 * Description: 根据ID获取初始化数据<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-12
	 * @param id
	 * @return
	 * ReturnVisit
	 */
//	 @Deprecated
//	 ReturnVisit initDevCreateReturnvisitPage(String id);
	/**
	 * 
	 * <p>
	 * 根据ID初始化页面<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-23
	 * @param id
	 * @return
	 * ReturnVisit
	 */
	 ReturnVisit initMatCreateReturnvisitPage(String id);
	/**
	 * 
	 * <p>
	 * 初始化开发页面<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-23
	 * @param id
	 * @return
	 * ReturnVisit
	 */
//	 ReturnVisit initDevCreateReturnvisitPageByCust(String id);
	/**
	 * 
	 * <p>
	 * 初始化维护页面<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-23
	 * @param id
	 * @return
	 * ReturnVisit
	 */
	 ReturnVisit initMatCreateReturnvisitPageByCust(String id);
	

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
	 * 查询客户下对应所有联系人的回访ID集合<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param custId
	 * @return
	 * List<String>
	 */
	 List<String> queryRvListByCustId(Map<String,String> paraMap);

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
	 * 统计客户意见总记录数<br />
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
	 * 计算货量潜力总记录<br />
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
	
	/**
	 * 
	 * <p>
	 * 根据回访ID删除回访主记录<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年3月27日
	 * @param id
	 * void
	 */
	public void deleteReturnVisitById(String id);
}
