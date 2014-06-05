/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TestDataUtil.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author Administrator
 * @version 0.1 2012-3-28
 */
package com.deppon.crm.module.marketing.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.customer.domain.PathAnalysisInfo;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.PlanScheduleQueryCondition;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TestDataUtil.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author 苏玉军
 * @version 0.1 2012-3-28
 */

public class DataUtilTest {
	public static Plan getPlanData(){
		Date dateFlag=new Date();
		int flag=dateFlag.getYear()+dateFlag.getMonth()+dateFlag.getDate();
		
		Plan plan=new Plan();
		plan.setActivedesc("开发主题为："+flag);
		plan.setBeginTime(DateUtils.setDays(new Date(), 5));
		plan.setBeginTime(new Date());
		plan.setCreateDate(new Date());
		plan.setCreateUser("386405");
		plan.setCreateUserId("76993");
		plan.setExecdeptid("49708");
		plan.setExecuserid("76993");
		plan.setPlanType(MarketingConstance.DEVELOP_TYPE);
		plan.setEndTime(DateUtils.setDays(new Date(), 30));
		plan.setEndTime(DateUtils.setDays(new Date(), 30));
		plan.setTopic("发货送积分"+(new Date()).getTime());
		plan.setProjectType("EXPRESS");
		return plan;
	}
	
	public static Plan getMaintainPlanData(){
		Date dateFlag=new Date();
		int flag=dateFlag.getYear()+dateFlag.getMonth()+dateFlag.getDate();
		
		Plan plan=new Plan();
		plan.setActivedesc("客户维护测试："+flag);
//		plan.setBeginTime(DateUtils.setDays(new Date(), 5));
		plan.setBeginTime(new Date());
		plan.setCreateDate(new Date());
		plan.setCreateUser("386405");
//		plan.setCreateUserId("76993");
		plan.setExecdeptid("49708");
//		plan.setSchedule(new Date());
		plan.setExecuserid("76993");
		plan.setPlanType(MarketingConstance.MAINTAIN_TYPE);
//		plan.setEndTime(DateUtils.setDays(new Date(), 30));
		plan.setEndTime(DateUtils.setDays(new Date(), 30));
		plan.setTopic("测试客户维护"+(new Date().getTime()));
		return plan;
	}
	public static String[] getCustList(){
		String[] list=new String[]{"111111","222222","333333","444444","555555","666666"};
		return list;
	}
	
	public static String[] getContactIds(){
		String[] list=new String[]{"111","222","333","444","555","666"};
		return list;
	}
	
	public static CustomerVo getPlanScheduleQueryCondition(){
		CustomerVo condition=new CustomerVo();
		condition.setCustName("朱培军");
		Date begin=new Date();
		begin.setMonth(2);
		begin.setDate(1);
		Date end=new Date();
		end.setMonth(4);
		end.setDate(1);
		System.out.println(begin.toLocaleString());
		System.out.println(end.toLocaleString());
		condition.setBeginTime(begin);
		condition.setOverTime(end);
		return condition;
	}
	public static CustomerVo getCustomerVO(){
		CustomerVo vo=new CustomerVo();
		vo.setMemberNo("KHWH1");
		vo.setCustName("客户维护测试数据1-勿删");
		vo.setLinkManProperty("1");
		vo.setTrade(null);
		vo.setLinkManCode("123");
		vo.setLinkManName("客户维护联系人6-勿删");
		vo.setLinkManMobile(null);
		vo.setLinkManPhone(null);
		vo.setMemberLevel("Golden");
		vo.setBeginTime(DateUtils.setDays(new Date(), 1));
		vo.setOverTime(DateUtils.setDays(new Date(), 25));
		vo.setBeginShipWeight(100.0);
		vo.setOverShipWeight(500);
//		vo.setBeginShipVotes(20);
//		vo.setOverShipVotes(80);
//		vo.setBeginShipAmount(200.3);
//		vo.setOverShipAmount(300.6);
//		vo.setBeginArrivalWeight(32.36);
//		vo.setOverArrivalWeight(50.23);
//		vo.setBeginArrivalVotes(10);
//		vo.setOverArrivalVotes(20);
//		vo.setBeginArrivalAmount(233.022);
//		vo.setOverArrivalAmount(312);
		vo.setScheduleType(MarketingConstance.DEVELOP_TYPE);
		return vo;
	}
	public static CustomerVo getCustomerVO4Dao(){
		CustomerVo vo=new CustomerVo();
		vo.setMemberNo("KHWH1");
		vo.setCustName("客户维护测试数据1-勿删");
		vo.setLinkManProperty("1");
		vo.setTrade(null);
		vo.setLinkManCode("123");
		vo.setLinkManName("客户维护联系人6-勿删");
		vo.setLinkManMobile(null);
		vo.setLinkManPhone(null);
		vo.setMemberLevel("Golden");
		vo.setBeginTime(DateUtils.setDays(new Date(), 1));
		vo.setOverTime(DateUtils.setDays(new Date(), 25));
		vo.setBeginShipWeight(100.0);
		vo.setOverShipWeight(500);
//		vo.setBeginShipVotes(600);
//		vo.setOverShipVotes(80);
//		vo.setBeginShipAmount(1);
//		vo.setOverShipAmount(300);
//		vo.setBeginArrivalWeight(32.36);
//		vo.setOverArrivalWeight(50.23);
//		vo.setBeginArrivalVotes(10);
//		vo.setOverArrivalVotes(20);
//		vo.setBeginArrivalAmount(233.022);
//		vo.setOverArrivalAmount(312);
		vo.setScheduleType(MarketingConstance.DEVELOP_TYPE);
		return vo;
	}
	
	public static CustomerGroupDetail getCustomerGroupDetail(){
		CustomerGroupDetail condition =new CustomerGroupDetail();
		condition.setDeptId("12345");
		condition.setQueryDate(new Date());
		condition.setCustNumber("123456789");
		condition.setGroupId("456");
		condition.setUserId("345");
		return condition;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-19
	 * @return
	 * User
	 */
	public static User getUser() {
		User user =new User();
		user.setId("1");
		Department d = new Department();
		d.setId("1");
		Employee e = new Employee();
		e.setDeptId(d);
		e.setId("1");
		user.setEmpCode(e);
		return user;
	}
	
}
