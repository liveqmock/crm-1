/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ConditionChangeUtilsTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author ZhuPJ
 * @version 0.1 2012-12-4
 */
package com.deppon.crm.module.marketing.utilstest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.utils.RegionPartitionValidator;
import com.deppon.crm.module.marketing.shared.domain.RegionPartitionConstance;
import com.deppon.crm.module.marketing.shared.domain.SearchDeptCondition;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title RegionPartitionUtilsTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author ZhouYuan
 * @version 0.1 2012-12-4
 */

public class RegionPartitionValidatorTest {
	@Test
	public void testCheckRegionPartitionAuth(){
		Set<String> roleIds = new HashSet<String>();
		roleIds.add("1");
		roleIds.add("2");
		roleIds.add("3");
		roleIds.add("4");
		roleIds.add("5");
		roleIds.add("6");	
		try{
			RegionPartitionValidator.checkRegionPartitionAuth(roleIds,null);
		}catch(Exception e){
			e.printStackTrace();
		}
		//具有经营本部权限用户
		roleIds.add(RegionPartitionConstance.AUTH_HEADQUARTERS);
		RegionPartitionValidator.checkRegionPartitionAuth(roleIds,null);
		//具有事业部权限用户
		roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_DIVISION);
		RegionPartitionValidator.checkRegionPartitionAuth(roleIds,"");
		//具有大区权限用户
		roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_BIG_REGION);
		RegionPartitionValidator.checkRegionPartitionAuth(roleIds,null);
		//具有小区权限用户
		roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_BIG_REGION);
		RegionPartitionValidator.checkRegionPartitionAuth(roleIds,"");
		//具有小区权限用户
		roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_SALES_DEPARTMENT);
		RegionPartitionValidator.checkRegionPartitionAuth(roleIds,null);
		//经营本部负责人
		RegionPartitionValidator.checkRegionPartitionAuth(
				new HashSet<String>(),RegionPartitionConstance.HEADQUARTERS);
		//事业部负责人
		RegionPartitionValidator.checkRegionPartitionAuth(
				new HashSet<String>(),RegionPartitionConstance.DIVISION);
		//大区负责人
		RegionPartitionValidator.checkRegionPartitionAuth(
				new HashSet<String>(),RegionPartitionConstance.BIG_REGION);
		//小区负责人
		RegionPartitionValidator.checkRegionPartitionAuth(
				new HashSet<String>(),RegionPartitionConstance.SMALL_REGION);
		//营业部负责人
		try{
		RegionPartitionValidator.checkRegionPartitionAuth(
				new HashSet<String>(),RegionPartitionConstance.SALES_DEPARTMENT);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testCheckSearchDeptCondition(){
		try{
			RegionPartitionValidator.checkSearchDeptCondition(null);
		}catch(Exception e){
			e.printStackTrace();
		}
		SearchDeptCondition sdc = new SearchDeptCondition();
		try{
			RegionPartitionValidator.checkSearchDeptCondition(sdc);
		}catch(Exception e){
			e.printStackTrace();
		} 
		sdc.setAuthCharacter("");
		try{
			RegionPartitionValidator.checkSearchDeptCondition(sdc);
		}catch(Exception e){
			e.printStackTrace();
		}
		sdc.setAuthCharacter(RegionPartitionConstance.HEADQUARTERS);
		try{
			RegionPartitionValidator.checkSearchDeptCondition(sdc);
		}catch(Exception e){
			e.printStackTrace();
		}
		sdc.setDeptId("");
		try{
			RegionPartitionValidator.checkSearchDeptCondition(sdc);
		}catch(Exception e){
			e.printStackTrace();
		}
		sdc.setDeptId("asf");
		try{
			RegionPartitionValidator.checkSearchDeptCondition(sdc);
		}catch(Exception e){
			e.printStackTrace();
		}
		sdc.setDeptId("123");
		try{
			RegionPartitionValidator.checkSearchDeptCondition(sdc);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Test
	public void testDateUtils(){
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date before = null;
		try{
			before = sdf.parse("2013-06-21");
		}catch(Exception e){
			e.printStackTrace();
		}
		Date yearbefor = DateUtils.addYears(before, 1);
		System.out.println(today+"-------"+yearbefor);
		System.out.println(DateUtils.truncatedCompareTo(yearbefor, today, Calendar.DATE));
		System.out.println(DateUtils.truncatedCompareTo(yearbefor, today, Calendar.YEAR));
		System.out.println(DateUtils.truncatedCompareTo(yearbefor, today, Calendar.MONTH));
		System.out.println(DateUtils.truncatedCompareTo(before, today, Calendar.DATE));
		System.out.println(DateUtils.truncatedCompareTo(before, today, Calendar.YEAR));
		System.out.println(DateUtils.truncatedCompareTo(before, today, Calendar.MONTH));
	}
}
