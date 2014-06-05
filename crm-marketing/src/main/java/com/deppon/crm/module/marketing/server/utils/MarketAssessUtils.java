package com.deppon.crm.module.marketing.server.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.marketing.shared.domain.AssessAuthority;
import com.deppon.crm.module.marketing.shared.domain.AssessDept;
import com.deppon.crm.module.marketing.shared.domain.AssessDeptPrincipal;
import com.deppon.crm.module.marketing.shared.domain.MarketAssessConstance;
import com.deppon.crm.module.marketing.shared.domain.SearchAssessCondition;

/**   
 * <p>
 * Description:营销效果评估工具类<br />
 * </p>
 * @title MarketAssessUtils.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author ZhouYuan
 * @version 0.1 2013-01-21
 */
public class MarketAssessUtils {
	/**   
	 * <p>
	 * Description:封装权限实体构建部门权限<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param Set<String> 权限
	 * @param List<AssessDept> 权限部门
	 * @return AssessAuthority 完整权限
	 */
	public static AssessAuthority assessAuthority(List<AssessDeptPrincipal> principals,Set<String> auths,List<AssessDept> depts){
		AssessAuthority assAuth = assessAuthorityBuildDept(principals,auths,depts);
		//获得最大权限
		String auth = assAuth.getAssessAuthority();
		//权限对应的层级
		String hier = getDeptHier(auth);
		//权限部门
		AssessDept dept = assAuth.getAssessDept();
		//权限开始标识
		int flag = 0;
		if(hier != null && !"".equals(hier)){
			while( dept != null){
				//如果标识为1则
				if( flag == 1 ){
					dept.setAuthority(MarketAssessConstance.DEPT_AUTHOR_Y);
				//如果匹配该部门层级
				}else if( hier.equals(dept.getHierarchy())){
					//设置权限为有权
					dept.setAuthority(MarketAssessConstance.DEPT_AUTHOR_Y);
					//设置开始标识为1
					flag = 1;
				}else{
					//没有权限
					dept.setAuthority(MarketAssessConstance.DEPT_AUTHOR_N);
				}
				//迭代层级部门
				dept = dept.getChildDept();
			}
		}
		return assAuth;
	}
	/**   
	 * <p>
	 * Description:封装权限实体构建权限部门<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param Set<String> 权限
	 * @param List<AssessDept> 权限部门
	 * @return List<AssessDept> 部门列表
	 */
	public static AssessAuthority assessAuthorityBuildDept(List<AssessDeptPrincipal> principals,Set<String> auths,List<AssessDept> depts){
		AssessAuthority assAuthor = null;	
		//判断权限部门和用户权限是否为空
		if(MarketAssessValidator.assessAuths(auths) &&
				MarketAssessValidator.assessDepts(depts)){
			//新建用户权限实体
			assAuthor = new AssessAuthority();
			//获得用户权限列表中的查询最高权限
			String auth = maxAssessAuthority(principals,auths);
			//设置权限实体中的权限字段为最高权限
			assAuthor.setAssessAuthority(auth);
			//初始化标志位为-1
			int index = -1 ;
			//经营本部
			//获得经营本部在集合中的索引值
			index = getAssessDeptByDeptName(
					depts,MarketAssessConstance.AUTHOR_SERCENTER_STR);
			//如果部门列表中包含经营本部则
			if(index > -1){
				//设置权限部门根节点为经营本部
				assAuthor.setAssessDept(cloneDept(depts.get(index),
						MarketAssessConstance.AUTHOR_SERCENTER));
				//获得该经营本部
				AssessDept serCenter = assAuthor.getAssessDept();
				//事业部
				//获得事业部在集合中的索引值
				index = getAssessDeptByDeptName(
						depts,MarketAssessConstance.AUTHOR_BUDEPT_STR);	
				//如果该集合包含事业部
				if( index > -1){
					//设置经营本部的下级部门为该事业部
					serCenter.setChildDept(cloneDept(depts.get(index),
							MarketAssessConstance.AUTHOR_BUDEPT));
					//获得该事业部
					AssessDept buDept = serCenter.getChildDept();
					//大区
					//获得大区在集合中的索引值
					index = getAssessDeptByDeptName(
							depts,MarketAssessConstance.AUTHOR_REGION_STR);	
					//如果部门列表中包含大区则
					if( index > -1 ){
						//设置事业部的下级部门为该大区
						buDept.setChildDept(cloneDept(depts.get(index),
								MarketAssessConstance.AUTHOR_REGION));
						//获得该大区
						AssessDept region = buDept.getChildDept();
						//营业区
						//获得营业区在集合中的索引值
						index = getAssessDeptByDeptName(
								depts,MarketAssessConstance.AUTHOR_BUQUARTER_STR);
						//如果部门列表中包含营业区则
						if( index > -1){
							//设置大区的下级部门为营业区
							region.setChildDept(cloneDept(depts.get(index),
									MarketAssessConstance.AUTHOR_BUQUARTER));
							//获得该营业区
							AssessDept buQuarter = region.getChildDept();
							//营业部
							//获得营业部在集合中的索引值
							index = getAssessDeptByDeptName(
									depts,MarketAssessConstance.AUTHOR_SALDEPT_STR);
							//如果部门列表中包含营业部则
							if( index > -1 ){
								//设置营业区的下级部门为营业部
								buQuarter.setChildDept(cloneDept(depts.get(index),
										MarketAssessConstance.AUTHOR_SALDEPT));
							}else{
								return assAuthor;
							}
						}else{
							return assAuthor;
						}
					}else{
						return assAuthor;
					}
				}else{
					return assAuthor;
				}
			}else{
				return assAuthor;
			}
			
		}
		return assAuthor;		
	}
	/**   
	 * <p>
	 * Description:根据名字从部门List中查询对应的部门<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param List<AssessDept> 权限部门
	 * @return int 对应的索引
	 */
	public static int getAssessDeptByDeptName(List<AssessDept> depts,String deptName){
		int index = -1;
		//循环该List查询第一个包含该名字的部门对应的下标
		for( int i=0;i<depts.size();i++){
			//获得列表中的部门
			AssessDept dept = depts.get(i);
			//如果部门不为空
			if( dept != null){
				//比较部门中是否包含需要查询的字段
				if(depts.get(i).getDeptName().trim().matches(".*"+deptName+"$")){
					//返回该索引值
					index = i;
					break;
				}
			}
		}
		//如果没有查询到对应部门则返回-1
		return index;
	}
	/**   
	 * <p>
	 * Description:根据名字给部门赋予相应的级别<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param AssessDept 无层级部门
	 * @return String 部门层级
	 */
	public static String strucHierToDept(AssessDept dept){
		String deptName = null;
		if( dept != null && dept.getDeptName()!=null){
			deptName = dept.getDeptName().trim();
			//经营本部
			if(deptName.matches(".*"+MarketAssessConstance.AUTHOR_SERCENTER_STR+"$")){
				//返回权限部门所属层级-经营本部
				return MarketAssessConstance.AUTHOR_SERCENTER;
			//事业部
			}else if(deptName.matches(".*"+MarketAssessConstance.AUTHOR_BUDEPT_STR+"$")){
				//返回权限部门所属层级--事业部
				return MarketAssessConstance.AUTHOR_BUDEPT;
			//大区
			}else if(deptName.matches(".*"+MarketAssessConstance.AUTHOR_REGION_STR+"$")){
				//返回权限部门所属层级--大区
				return MarketAssessConstance.AUTHOR_REGION;
			//小区
			}else if(deptName.matches(".*"+MarketAssessConstance.AUTHOR_BUQUARTER_STR+"$")){
				//返回权限部门所属层级--营业区
				return MarketAssessConstance.AUTHOR_BUQUARTER;
			//营业部
			}else if(deptName.matches(".*"+MarketAssessConstance.AUTHOR_SALDEPT_STR+"$")){
				//返回权限部门所属层级--营业部
				return MarketAssessConstance.AUTHOR_SALDEPT;
			}
			
		}
		
		return null;
	}
	/**   
	 * <p>
	 * Description:根据名字给部门赋予相应的权限<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param AssessDept 无权限部门
	 * @return String 部门权限
	 */
	public static String strucAuthToDept(AssessDept dept,String auth){
		int authNum = 0;
		authNum = Integer.parseInt(getConditionAuth(auth));
		if(dept != null && dept.getHierarchy()!=null){
			if( dept.getHierarchy().trim().matches("\\d\\d*")){
				//将层级关系转换为数字
				int hierNum = Integer.parseInt(dept.getHierarchy());
				//如果层级关系小于权限则部门权限为有权,其他则无权
				if( hierNum <= authNum){
					//返回有权
					return MarketAssessConstance.DEPT_AUTHOR_Y;
				}else{
					//返回无权
					return MarketAssessConstance.DEPT_AUTHOR_N;
				}
			}
		}
		return null;
	}
	/**   
	 * <p>
	 * Description:复制权限部门<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param User 用户
	 * @return String 权限ID
	 */
	public static AssessDept cloneDept(AssessDept dept,String hierarchy){
		//新建权限部门
		AssessDept cloneDept = new AssessDept();
		//设置权限部门ID
		cloneDept.setId(dept.getId());
		//设置权限部门名称
		cloneDept.setDeptName(dept.getDeptName());
		//设置权限部门的层级
		cloneDept.setHierarchy(hierarchy);
		return cloneDept;
	}
	/**   
	 * <p>
	 * Description:查找该用户拥有的最大权限<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param User 用户
	 * @return String 权限ID
	 */
	public static String maxAssessAuthority(List<AssessDeptPrincipal> principals,Set<String> auths){
		//迭代数据获得对应权限
		if(principals != null){
			for(AssessDeptPrincipal pal : principals){
				String priAuth = authByDeptName(pal.getDeptName());
				//添加到auths中
				auths.add(priAuth);
			}
		}
		//是否具有全国权限
		if( auths.contains(MarketAssessConstance.AUTHOR_NATION_ID)){
			return MarketAssessConstance.AUTHOR_NATION_ID;
		//是否具有经营本部权限 或者 是否是经营事业部总裁
		}else if( auths.contains(MarketAssessConstance.AUTHOR_SERCENTER_ID )){
			return MarketAssessConstance.AUTHOR_SERCENTER_ID;
		//是否具有事业部权限 或者 是否是经营大区总经理
		}else if( auths.contains(MarketAssessConstance.AUTHOR_BUDEPT_ID )){
			return MarketAssessConstance.AUTHOR_BUDEPT_ID;
		//是否具有大区权限 或者 是否是营业区区域经理
		}else if( auths.contains(MarketAssessConstance.AUTHOR_REGION_ID )){
			return MarketAssessConstance.AUTHOR_REGION_ID;
		//是否具有营业区权限 或者 是否是营业部经理
		}else if( auths.contains(MarketAssessConstance.AUTHOR_BUQUARTER_ID )){
			return MarketAssessConstance.AUTHOR_BUQUARTER_ID;
		//无权限
		}else{
			return MarketAssessConstance.AUTHOR_NOAUTHOR;
		}
	}
	/**   
	 * <p>
	 * Description:根据部门名称判断用户所具有的权限<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param String authId 权限ID
	 * @return String 部门层级标识
	 */
	public static String authByDeptName(String deptName){
		String deptNameT = deptName.trim();
		//如果该部门为经营本部
		if( deptNameT.matches(".*"+MarketAssessConstance.AUTHOR_SERCENTER_STR+"$")){
			//返回对应的经营本部权限
			return MarketAssessConstance.AUTHOR_NATION_ID; 
		//如果该部门为事业部
		}else if(deptNameT.matches(".*"+MarketAssessConstance.AUTHOR_BUDEPT_STR+"$")){
			//返回对应的事业部权限
			return MarketAssessConstance.AUTHOR_SERCENTER_ID;
		//如果该部门为大区
		}else if(deptNameT.matches(".*"+MarketAssessConstance.AUTHOR_REGION_STR+"$")){
			//返回对应的大区权限
			return MarketAssessConstance.AUTHOR_BUDEPT_ID;
		//如果该部门为营业区
		}else if(deptNameT.matches(".*"+MarketAssessConstance.AUTHOR_BUQUARTER_STR+"$")){
			//返回对应的营业区权限
			return MarketAssessConstance.AUTHOR_REGION_ID;
		//如果该部门为营本部
		}else if(deptNameT.matches(".*"+MarketAssessConstance.AUTHOR_SALDEPT_STR+"$")){
			//返回对应的营本部权限
			return MarketAssessConstance.AUTHOR_BUQUARTER_ID;
		//如果该部门不属于上述任何一个部门
		}else{
			//返回无权限
			return MarketAssessConstance.AUTHOR_NOAUTHOR;
		}
	}
	/**   
	 * <p>
	 * Description:根据权限判断部门级别<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param String authId 权限ID
	 * @return String 部门层级标识
	 */
	public static String getDeptHier(String authId ){
		//全国权限
		if( MarketAssessConstance.AUTHOR_NATION_ID.equals(authId)){
			//返回对应的全国权限
			return MarketAssessConstance.AUTHOR_SERCENTER;
		//经营本部权限
		}else if( MarketAssessConstance.AUTHOR_SERCENTER_ID.equals(authId)){
			//返回对应的经营本部权限
			return MarketAssessConstance.AUTHOR_BUDEPT;
		//事业部权限
		}else if( MarketAssessConstance.AUTHOR_BUDEPT_ID.equals(authId)){
			//返回对应的事业部权限
			return MarketAssessConstance.AUTHOR_REGION;
		//大区权限
		}else if( MarketAssessConstance.AUTHOR_REGION_ID.equals(authId)){
			//返回对应的大区权限
			return MarketAssessConstance.AUTHOR_BUQUARTER;
		//营业区权限
		}else if( MarketAssessConstance.AUTHOR_BUQUARTER_ID.equals(authId)){
			//返回对应的营业区权限
			return MarketAssessConstance.AUTHOR_SALDEPT;
		//无权限
		}else{
			return null;
		}
	}
	
	/**   
	 * <p>
	 * Description:每日凌晨生成本月1日至本日前一天的数据<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-01-21
	 * @param beginDate 开始时间
	 * @return endDate 结束时间
	 */
	public static Map<String,Date> getCreateDate(){
		// 生成数据开始时间
		Calendar beginCalendar = Calendar.getInstance();
		// 生成数据结束时间
		Calendar endCalendar = Calendar.getInstance();
		// 上个月时间
		Calendar calendar = Calendar.getInstance();
		// 本月最后一天
		int lastDay= 0;
		 int year = beginCalendar.get(Calendar.YEAR);
		 int month = beginCalendar.get(Calendar.MONTH);
		 int day = beginCalendar.get(Calendar.DATE);
		// 如果当前时间为本月1日，则生成上月1日至上月最后一日的数据
		if(1==day){
			calendar.add(calendar.MONTH,- 1);
			lastDay = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
			beginCalendar.set(year, month-1, 1, 0, 0, 0);// 上月1号
			endCalendar.set(year, month-1, lastDay, 23, 59, 59);// 上月最后一天
		}
		else{
			beginCalendar.set(year, month, 1, 0, 0, 0);// 本月1号
		}
		Map<String,Date> map = new HashMap<String,Date>();
		map.put("beginDate", beginCalendar.getTime());
		map.put("endDate", endCalendar.getTime());
		return map;
	}
	/**   
	 * <p>
	 * Description:根据权限判断部门级别<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param String authId 权限ID
	 * @return String 部门层级标识
	 */
	public static SearchAssessCondition strucSeaAssCondition(String auth,List<AssessDept> depts,Date date){
		SearchAssessCondition seaAssCondition = new SearchAssessCondition();
		//如果权限部门不为空
		if(depts != null){
			//循环该权限部门列表
			for( AssessDept dept : depts){
				//设置权限部门的层级
				dept.setHierarchy(strucHierToDept(dept));
				//设置权限部门的权限
				dept.setAuthority(strucAuthToDept(dept,auth));
			}
		}
		//如果部门为空则查询全国
		if( depts == null || depts.size()==0 ){
			//设置group by 部门
			seaAssCondition.setGroupDept(new AssessDept());
			//获得group by 部门
			AssessDept groupDept = seaAssCondition.getGroupDept();
			//如果为全国权限
			if( MarketAssessConstance.AUTHOR_NATION_ID.equals(auth) ){
				//设置group by部门有权
				groupDept.setAuthority(MarketAssessConstance.DEPT_AUTHOR_Y);
				//设置group by部门层级为经营本部
				groupDept.setHierarchy(MarketAssessConstance.AUTHOR_SERCENTER);
			}else{
				//设置group by部门无权
				groupDept.setAuthority(MarketAssessConstance.DEPT_AUTHOR_N);
				//设置group by部门层级为经营本部
				groupDept.setHierarchy(MarketAssessConstance.AUTHOR_SERCENTER);
			}
		//如果只有一个查询部门
		}else if( depts.size()==1 ){
			//设置查询部门
			seaAssCondition.setSearchDept(depts.get(0));
			//获得该查询部门
			AssessDept searchDept = seaAssCondition.getSearchDept();
			AssessDept groupDept = null;
			//赋下一层级
			//经营本部
			if( MarketAssessConstance.AUTHOR_SERCENTER.equals(searchDept.getHierarchy())){
				//创建group by部门
				groupDept = new AssessDept();
				//设置group by部门为事业部
				groupDept.setHierarchy(MarketAssessConstance.AUTHOR_BUDEPT);
			//事业部
			}else if(MarketAssessConstance.AUTHOR_BUDEPT.equals(searchDept.getHierarchy())){
				//创建group by部门
				groupDept = new AssessDept();
				//设置group by部门为大区
				groupDept.setHierarchy(MarketAssessConstance.AUTHOR_REGION);
			//大区
			}else if(MarketAssessConstance.AUTHOR_REGION.equals(searchDept.getHierarchy())){
				//创建group by部门
				groupDept = new AssessDept();
				//设置group by部门为营业区
				groupDept.setHierarchy(MarketAssessConstance.AUTHOR_BUQUARTER);
			//小区
			}else if(MarketAssessConstance.AUTHOR_BUQUARTER.equals(searchDept.getHierarchy())){
				//创建group by部门
				groupDept = new AssessDept();
				//设置group by部门为营业部
				groupDept.setHierarchy(MarketAssessConstance.AUTHOR_SALDEPT);
			}
			//设置权限
			if(groupDept != null && groupDept.getHierarchy()!=null){
				groupDept.setAuthority(strucAuthToDept(groupDept,auth));
			}
			seaAssCondition.setGroupDept(groupDept);
		//如果有两个部门
		}else if( depts.size()==2){
			//部门1
			AssessDept dept1 = depts.get(0);
			//部门2
			AssessDept dept2 = depts.get(1);
			//查询部门1
			int hier1 = 0;
			//查询部门2
			int hier2 = 0;
			//如果查询部门1和查询部门2都不为空
			if(dept1.getHierarchy()!=null&& dept2.getHierarchy()!=null){
				//查询部门1的层级
				hier1 = Integer.parseInt(dept1.getHierarchy());
				//查询部门2的层级
				hier2 = Integer.parseInt(dept2.getHierarchy());
				//如果部门1层级高于部门2
				if(hier1-hier2>0){
					//设置查询部门为部门1
					seaAssCondition.setSearchDept(dept1);
					//设置group部门为部门2
					seaAssCondition.setGroupDept(dept2);
				//如果部门2层级高于部门1
				}else if(hier1-hier2<0){
					//设置查询部门为部门2
					seaAssCondition.setSearchDept(dept2);
					//设置group部门为部门1
					seaAssCondition.setGroupDept(dept1);
				}
			}
		}
		//将查询日期转换为日期字符串
		String dateStr = getDateStr(date);
		//设置查询日期
		seaAssCondition.setDateStr(dateStr);
		return seaAssCondition;
	}
	/**   
	 * <p>
	 * Description:创建查询需要的date字段,如果date为空则查询当天,如果不为空则查询指定日期<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param String authId 权限ID
	 * @return String 部门层级标识
	 */
	public static String getDateStr(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//如果日期值不为空
		if( date !=null ){
			//返回转换后的日期
			return sdf.format(date);	
		}else{
			//返回当天日期
			return sdf.format(new Date());
		}
	}
	/**   
	 * <p>
	 * Description:创建deptId列表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param String authId 权限ID
	 * @return String 部门层级标识
	 */
	public static List<String> getDeptIds(String deptSeq){
		List<String> deptIds = null;
		//如果部门序列不为空
		if(deptSeq!=null && !"".equals(deptSeq)){
			//循环去掉开头的一个或多个"."
			while(deptSeq.startsWith(".") && !"".equals(deptSeq)){
				//切掉开头的"."
				deptSeq = deptSeq.substring(1);
			}
			//以"."拆分部门序列
			String[] ids = deptSeq.split("\\.");
			//将部门数组转换为集合
			deptIds = Arrays.asList(ids);
		}
		return deptIds;
	}
	/**   
	 * <p>
	 * Description:根据用户现有权限赋予导出权限查询相应的权限<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param String authority
	 * @return String auth
	 */
	public static String getConditionAuth(String authority){
		//具有全国权限
		if(MarketAssessConstance.AUTHOR_NATION_ID.equals(authority)){
			//AuthAllSalCondition中对应权限定义-经营本部
			return MarketAssessConstance.AUTHSAL_AUTHOR_SERCENTER;
		//具有经营本部部权限
		}else if(MarketAssessConstance.AUTHOR_SERCENTER_ID.equals(authority)){
			//AuthAllSalCondition中对应权限定义-事业部
			return MarketAssessConstance.AUTHSAL_AUTHOR_BUDEPT;
		//具有事业部权限
		}else if(MarketAssessConstance.AUTHOR_BUDEPT_ID.equals(authority)){
			//AuthAllSalCondition中对应权限定义-大区
			return MarketAssessConstance.AUTHSAL_AUTHOR_REGION;
		//具有大区权限
		}else if(MarketAssessConstance.AUTHOR_REGION_ID.equals(authority)){
			//AuthAllSalCondition中对应权限定义-营业区
			return MarketAssessConstance.AUTHSAL_AUTHOR_BUQUARTER;
		//具有小区权限
		}else if(MarketAssessConstance.AUTHOR_BUQUARTER_ID.equals(authority)){
			//AuthAllSalCondition中对应权限定义-营业部
			return MarketAssessConstance.AUTHSAL_AUTHOR_SALDEPT;
		//其他无权限
		}else{
			//返回无权限
			return MarketAssessConstance.AUTHOR_NOAUTHOR;
		}
	}
}
