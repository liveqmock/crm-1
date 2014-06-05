package com.deppon.crm.module.marketing.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.marketing.shared.domain.CCPushMarketingInfo;
import com.deppon.crm.module.marketing.shared.domain.CCPushPlanInfo;
import com.deppon.crm.module.marketing.shared.domain.CCQueryMarketingInfo;
import com.deppon.crm.module.marketing.shared.domain.CCQueryMarketingInfoDetail;
import com.deppon.crm.module.marketing.shared.domain.CustomerCallVO;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * <p>
 * 客户来电操作封装
 * </p>
 * @title ICustomerCallManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author 苏玉军
 * @version 0.1 2013-1-28
 */
public interface ICustomerCallManager {

	/**
	 * @描述 根据手机号码和部門id去查潜散客和固定客户
	 * @作者 钟琼
	 * @时间 2012-11-05
	 * @参数 手机号码、部门id
	 * @返回值 潜散客实体
	 */
	 CustomerCallVO queryCustomerByMobileAndDeptId(String mobile,
			String deptId);

	/**
	 * @描述 根据电话号码、联系人名字和部门id去查潜散客和固定客户
	 * @作者 钟琼
	 * @时间 2012-11-05
	 * @参数 电话号码、联系人名字、部门id
	 * @返回值 查潜散客实体
	 */
	 CustomerCallVO queryCustomerByPhoneDeptIdName(String phone,
			String linkName, String deptId);

	/**
	 * 
	 * <p>
	 * 根据当前登录用户查找部门所在城市<br/>
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-7
	 * @param user
	 *            当前登录用户
	 * @return Department 部门对象
	 */
	 BussinessDept queryDepartmentWithCurrentUser(User user);

	/**
	 * 
	 * <p>
	 * 点击保存后进行营销数据处理<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-7
	 * @param vo
	 *            页面数据封装
	 * @param optionList
	 *            客户意见列表
	 * @param volPotentialList
	 * @param user
	 * @return boolean：成功 false：失败
	 */
	 boolean processMarketData(CustomerCallVO vo,
			List<ReturnVisitOpinion> optionList,
			List<ReturnVisitVolumePotential> volPotentialList, User user);

	/**
	 * 
	 * <p>
	 * 营销历史查询<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param vo
	 *            前台页面封装
	 * @return Map<String,Object>
	 */
	 List<String> queryMarketingHistory(CustomerCallVO vo,User user);
	
	/**
	 * 
	 * <p>
	 * 分页查询客户意见<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param marketingIds
	 * @param start
	 * @param limit
	 * @return
	 * Map<String,Object>
	 */
	 Map<String,Object> queryRvOptionByMarketingIds(CustomerCallVO vo,User user, int start, int limit); 
	
	
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
	 Map<String,Object> queryRvPotentialByMarketingIds(CustomerCallVO vo,User user,int start,int limit);
	 
	 /**
	  * 
	  * <p>
	  * 1、	电销专员录入需求分类、需求问题、解决方法和处理内容，点击保存按钮</br>
	  * 2、	CC将该营销信息传给CRM
	  * </p>
	  * @author 043260
	  * @version 0.1 2014年4月6日
	  * @param info
	  * @return
	  * boolean
	  */
	 boolean addCCMarketingInfo(CCPushMarketingInfo info) throws GeneralException;
	 
	 /**
	  * 
	  * <p>
	  * 1、	电销专员进行外呼营销，查看该客户的CRM营销历史调用该接口</br>
	  *	2、	CRM传回该客户的营销历史<br />
	  * </p>
	  * @author 043260
	  * @version 0.1 2014年4月6日
	  * @param custNumber
	  * @param start
	  * @param limit
	  * @return
	  * CCQueryMarketingInfo
	  */
	 CCQueryMarketingInfo queryMarketingInfoToCC(String custNumber,int start,int limit) throws GeneralException;
	 
	 /**
	  * 
	  * <p>
	  * 3、	电销专员进行外呼营销，查看该客户的CRM营销详情调用该接口</br>
	  *	4、	CRM传回该客户的营销详情
	  * </p>
	  * @author 043260
	  * @version 0.1 2014年4月6日
	  * @param custId
	  * @param returnVisitId
	  * @return
	  * CCQueryMarketingInfoDetail
	  */
	 CCQueryMarketingInfoDetail queryMarketingInfoDetailToCC(String custId,String returnVisitId) throws GeneralException;
	 
	 /**
	  * 
	  * <p>
	  * 1、	电销专员外呼营销时客户要求回访，电销专员判断需要营业部回访</br>
	  *	2、	电销专员录入回访信息，提交</br>
	  *	3、	CC调用接口将该回访信息推送给CRM
	  * </p>
	  * @author 043260
	  * @version 0.1 2014年4月11日
	  * @param planInfo
	  * @return
	  * @throws GeneralException
	  * String
	  */
	 String addCCPlanInfo(CCPushPlanInfo planInfo) throws GeneralException;
}


