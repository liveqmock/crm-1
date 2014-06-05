package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.util.CreateExcel;
import com.deppon.crm.module.marketing.server.manager.IMarketAssessManager;
import com.deppon.crm.module.marketing.server.service.impl.MarketAssessServiceImpl;
import com.deppon.crm.module.marketing.server.utils.MarketAssessUtils;
import com.deppon.crm.module.marketing.shared.domain.AssDeptPriCondition;
import com.deppon.crm.module.marketing.shared.domain.AssessAuthority;
import com.deppon.crm.module.marketing.shared.domain.AssessDept;
import com.deppon.crm.module.marketing.shared.domain.AssessDeptPrincipal;
import com.deppon.crm.module.marketing.shared.domain.AuthAllSalCondition;
import com.deppon.crm.module.marketing.shared.domain.CustDevAssess;
import com.deppon.crm.module.marketing.shared.domain.CustMatAssess;
import com.deppon.crm.module.marketing.shared.domain.MarketAssessConstance;
import com.deppon.crm.module.marketing.shared.domain.SearchAssessCondition;
import com.deppon.crm.module.organization.shared.domain.Department;
/**   
 * <p>
 * Description:营销效果评估报表Manager实现<br />
 * </p>
 * @title MarketAssessManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author ZhouYuan
 * @version 2013-01-17
 */
public class MarketAssessManagerImpl implements IMarketAssessManager{
	//营销效果评估service层
	private MarketAssessServiceImpl marketAssessServiceImpl;

	public void setMarketAssessServiceImpl(
			MarketAssessServiceImpl marketAssessServiceImpl) {
		this.marketAssessServiceImpl = marketAssessServiceImpl;
	}

	/**
	 * 
	 * <p>
	 * 每天生成营销报表<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-01-21
	 */
	public void autoCreateMarketReport() {
		//每日凌晨生成本月1日至本日前一天的数据，如果今天为1号，则声称上月1号至上月最后一天的数据
//		Map<String,Date> map = new HashMap<String,Date>();
//		map = MarketAssessUtils.getCreateDate();
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.DATE, -1);
//		calendar.set(Calendar.HOUR, 23);
//		calendar.set(Calendar.MINUTE, 59);
//		calendar.set(Calendar.SECOND, 59);
//		System.out.println(calendar.getTime());
		// 维护了日表
		marketAssessServiceImpl.autoCreateDevMarketReport(new Date());
		// 维护日表
		marketAssessServiceImpl.autoCreateMatMarketReport(new Date());
	}
	/**
	 * 
	 * <p>
	 * 根据用户权限生成权限实体<br/>
	 * </p>
	 * @param user 登录用户
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 */
	@Override
	public AssessAuthority createAssessAuth(User user){
		//创建查询条件
		AssDeptPriCondition adpc = new AssDeptPriCondition();
		adpc.setEmpCode(user.getEmpCode().getEmpCode());
		//查询该用户是否是部门负责人
		List<AssessDeptPrincipal> adp = marketAssessServiceImpl.searchDeptPrincipal(adpc);
		//用户所有权限
		Set<String> roleids = user.getRoleids();
		//用户所属所有部门id
		String deptSeq = user.getEmpCode().getDeptId().getDeptSeq();
		//将部门Set转换为List
		List<String> depts = MarketAssessUtils.getDeptIds(deptSeq);
		//查询部门信息
		List<AssessDept> assDepts =marketAssessServiceImpl.searchDepts(depts);
		//根据所得信息生成相应的权限实体
		return MarketAssessUtils.assessAuthority(adp,roleids, assDepts);
		
	}
	/**
	 * 
	 * <p>
	 * 封装查询条件<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	public SearchAssessCondition strucSearchAssConditon(User user,List<String> deptIds,Date date){
		//创建查询条件
		AssDeptPriCondition adpc = new AssDeptPriCondition();
		adpc.setEmpCode(user.getEmpCode().getEmpCode());
		//查询该用户是否是部门负责人
		List<AssessDeptPrincipal> adp = marketAssessServiceImpl.searchDeptPrincipal(adpc);
		
		//获得最大权限
		String auth = MarketAssessUtils.maxAssessAuthority(adp,user.getRoleids());		
		//查询部门列表
		List<AssessDept> depts = null;
		if( deptIds !=null && deptIds.size()>0){
			depts = marketAssessServiceImpl.searchDepts(deptIds);
		}
		//调用Uils的方法封装查询条件实体
		return MarketAssessUtils.strucSeaAssCondition(auth, depts, date);
	}
	/**
	 * 
	 * <p>
	 * 根据权限查询客户维护表<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	@Override
	public Map<String,Object> searchCustDevByAuth(
			List<String> deptIds,Date date,User user,String potenSource,String custCategory, int start, int limit) {
		//创建查询实体
		SearchAssessCondition devCondition = strucSearchAssConditon( user,deptIds,date);
		devCondition.setPotenSource(potenSource);
		devCondition.setCustCategory(custCategory);
		//查询数量
		int count = marketAssessServiceImpl.countCustDevByAuth(devCondition);
		//根据权限查询权限下相关内容
		List<CustDevAssess> custDevAss = null;
		//如果查询结果大于0个
		if( count > 0){
			//查询相应的数据
			custDevAss = marketAssessServiceImpl.searchCustDevByAuth(devCondition, start, limit);
		}
		//创建返回的MAP对象
		Map<String,Object> map = new HashMap<String,Object>();
		//添加查询的数据量
		map.put("totalCount", count);
		//添加查询到的数据集合
		map.put("custDevAssessList", custDevAss);
		return map;
	}
	
	/**
	 * 
	 * <p>
	 * 查询合计<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	@Override
	public CustDevAssess searchSumCustDevByAuth(
			List<String> deptIds,Date date,User user,String potenSource,String custCategory) {
		//创建查询实体
		SearchAssessCondition devCondition = strucSearchAssConditon( user,deptIds,date);
		devCondition.setPotenSource(potenSource);
		devCondition.setCustCategory(custCategory);
		//根据权限查询权限查询总计
		List<CustDevAssess> amountDevAss = marketAssessServiceImpl.searchAmountCustDevByAuth(devCondition);
		return amountDevAss.get(0);
	}
	
	/**
	 * 
	 * <p>
	 * 根据权限查询客户开发表表<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustMatAssess>
	 */
	@Override
	public Map<String,Object> searchCustMatByAuth(
			List<String> deptIds,Date date, User user, int start, int limit) {
		//创建查询实体
		SearchAssessCondition devCondition = strucSearchAssConditon( user,deptIds,date);
		//根据条件查询到的数据条数
		int count = marketAssessServiceImpl.countCustMatByAuth(devCondition);
		List<CustMatAssess> custMatAss = null;
		if(count>0){
			//根据权限查询权限下相关内容
			custMatAss = marketAssessServiceImpl.searchCustMatByAuth(devCondition, start, limit);
		}
		//创建返回的MAP对象
		Map<String,Object> map = new HashMap<String,Object>();
		//添加查询的数据量
		map.put("totalCount", count);
		//添加查询到的数据集合
		map.put("custMatAssessList", custMatAss);
		return map;
	} 
	
	/**
	 * 
	 * <p>
	 * 查询合计<br/>
	 * </p>
	 * 
	 * @author zhangdeng
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustMatAssess>
	 */
	@Override
	public CustMatAssess searchSumCustMatByAuth(
		List<String> deptIds,Date date, User user) {
		//创建查询实体
		SearchAssessCondition devCondition = strucSearchAssConditon( user,deptIds,date);
		//根据权限查询权限查询总计
		List<CustMatAssess> amountMatAss = marketAssessServiceImpl.searchAmountCustMatByAuth(devCondition);
		return amountMatAss.get(0);
	} 
	
	/**
	 * 
	 * <p>
	 * 查询组织架构-所有经营本部<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-01-23
	 */
	@Override
	public List<Department> searchmanagerDept() {
		String deptName = "%经营本部";
		return marketAssessServiceImpl.searchmanagerDept(deptName);
	}
	/**
	 * 
	 * <p>
	 * 根据部门parentid查询子级部门<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-01-23
	 */
	@Override
	public List<Department> searchDeptsByParentId(String parentId){
		return marketAssessServiceImpl.searchDeptsByParentId(parentId);
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件封装查询条件实体<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param user用户 
	 * @return AuthAllSalCondition
	 */
	public AuthAllSalCondition createAuthAllCondition(User user,Date date){
		//获得权限实体
		AssessAuthority assAuth = createAssessAuth(user);
		//获得权限部门
		AssessDept dept = assAuth.getAssessDept();
		//权限部门ID
		AssessDept childDept = null;
		//迭代整个部门组织取得最大权限部门
		String parentId = null;
		while( dept != null && dept.getChildDept()!=null){
			//该部门的上级部门
			parentId = dept.getId();
			//当前部门
			childDept = dept.getChildDept();
			//查询该层级部门中最高权限的层级部门,得到的对应该部门的上级部门的ID
			if(MarketAssessConstance.DEPT_AUTHOR_Y.equals(childDept.getAuthority())){
				break;
			}	
			//迭代该部门层级
			dept = dept.getChildDept();
		}
		if(dept!=null && MarketAssessConstance.DEPT_AUTHOR_N.equals(dept.getAuthority())){
			parentId = dept.getId();
		}
		//创建导出权限数据的条件实体
		AuthAllSalCondition allCondition = new AuthAllSalCondition();
		//设置查询权限
		allCondition.setAuthority(MarketAssessUtils.getConditionAuth(assAuth.getAssessAuthority()));
		//设置查询的部门ID
		allCondition.setDeptId(parentId);
		//设置查询的时间
		allCondition.setDateStr(MarketAssessUtils.getDateStr(date));
		return allCondition;
	}
	/**
	 * 
	 * <p>
	 * 根据权限查询客户开发-导出当前页面数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	@Override
	public List<CustDevAssess> searchCustDevByAuthForOutput(
			List<String> deptIds, Date date, User user,String potenSource,String custCategory) {
		//创建查询实体
		SearchAssessCondition devCondition = strucSearchAssConditon( user,deptIds,date);
		devCondition.setPotenSource(potenSource);
		devCondition.setCustCategory(custCategory);
		//根据权限查询权限查询数据
		List<CustDevAssess> custDevAss = marketAssessServiceImpl.searchCustDevByAuthForOutput(devCondition);
		
		if(custDevAss!=null && custDevAss.size()>0){
			//根据权限查询权限查询总计
			List<CustDevAssess> amountDevAss = marketAssessServiceImpl.searchAmountCustDevByAuth(devCondition);
			//将总计数据添加到custDevAss的最后一条
			custDevAss.add(amountDevAss.get(0));
		}
		return custDevAss;	
	}
	/**
	 * 
	 * <p>
	 * 根据权限查询客户维护表--用于导出当前页面数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustMatAssess>
	 */
	@Override
	public List<CustMatAssess> searchCustMatByAuthForOutput(
			List<String> deptIds,Date date, User user) {
		//创建查询实体
		SearchAssessCondition devCondition = strucSearchAssConditon( user,deptIds,date);
		//根据权限查询权限查询数据
		List<CustMatAssess> custMatAss = marketAssessServiceImpl.searchCustMatByAuthForOutput(devCondition);
		if(custMatAss!=null && custMatAss.size()>0){
			//根据权限查询权限查询总计
			List<CustMatAssess> amountMatAss = marketAssessServiceImpl.searchAmountCustMatByAuth(devCondition);
			//将总计数据添加到custDevAss的最后一条
			custMatAss.add(amountMatAss.get(0));
		}
		return custMatAss;
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件导出客户开发表中的数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param user用户 
	 * @void
	 */
	@Override
	public boolean outputDevAssessExcelByPage(String fileName, String filePath,List<String> deptIds,
			 Date date, User user,String potenSource,String custCategory) {
		//调用对应的查询返回该结果用于生成excel
		List<CustDevAssess> custDevList = searchCustDevByAuthForOutput(deptIds,date, user,potenSource,custCategory);
		//如果得到的集合不为空
		if(custDevList!=null){
			//调用相应方法生成excel文件
			CreateExcel.createExcel(MarketAssessConstance.devTitle, custDevList, fileName, filePath);
			return true;
		}
		return false;
	}
	/**
	 * 
	 * <p>
	 * 根据当前查询条件导出客户维护的数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param user用户 
	 * @void
	 */
	@Override
	public boolean outputMatAssessExcelByPage(String fileName,String filePath,List<String> deptIds,Date date,User user){
		//调用对应的查询返回该结果用于生成excel
		List<CustMatAssess> custMatList = searchCustMatByAuthForOutput(deptIds,date, user);
		//如果得到的集合不为空
		if(custMatList!=null){
			//调用相应方法生成excel文件
			CreateExcel.createExcel(MarketAssessConstance.matTitle, custMatList, fileName, filePath);
			return true;
		}
		return false;
	}
	/**
	 * 
	 * <p>
	 * 导出权限下的客户开发表中的数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param user用户 
	 * @void
	 */
	@Override
	public boolean outputDevAssessExcelByAuth(String fileName, String filePath,
			Date date, User user,String potenSource,String custCategory) {
		//查询条件实体
		AuthAllSalCondition allCondition = createAuthAllCondition(user,date);
		allCondition.setPotenSource(potenSource);
		allCondition.setCustCategory(custCategory);
		//查询权限下的所有数据
		List<CustDevAssess> custDevList = marketAssessServiceImpl.searchCustDevAllSalDept(allCondition);
		//如果查询的数据不为空
		if(custDevList != null ){
			//创建excel文件
			CreateExcel.createExcel(MarketAssessConstance.devTitle, custDevList, fileName, filePath);
			return true;
		}
		return false;
	}
	/**
	 * 
	 * <p>
	 * 导出权限下的客户维护表中的数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param user用户 
	 * @void
	 */
	@Override
	public boolean outputMatAssessExcelByAuth(String fileName, String filePath,
			Date date, User user) {
		//查询条件实体
		AuthAllSalCondition allCondition = createAuthAllCondition(user,date);
		//查询权限下的所有数据
		List<CustMatAssess> custMatList = marketAssessServiceImpl.searchCustMatAllSalDept(allCondition);
		//如果查询的数据不为空
		if(custMatList != null ){
			//创建excel文件
			CreateExcel.createExcel(MarketAssessConstance.matTitle, custMatList, fileName, filePath);
			return true;
		}
		return false;
	}
}
