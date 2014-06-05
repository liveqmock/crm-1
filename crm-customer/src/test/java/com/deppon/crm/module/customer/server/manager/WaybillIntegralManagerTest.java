package com.deppon.crm.module.customer.server.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.manager.impl.WaybillIntegralManager;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;
public class WaybillIntegralManagerTest extends BeanUtil {
	
	@Test
	public void test_disPoseWaybillIntegral() throws SQLException{
		
		List<WaybillIntegral> waybillIntegralList = new ArrayList<WaybillIntegral>();
		waybillIntegralManager.disPoseWaybillIntegral(waybillIntegralList);
		
		
		WaybillIntegral waybillIntegral = new WaybillIntegral();
		
		Member member = new Member();
		member.setId("584740");
		Contact contact = new Contact();
		contact.setMember(member);
		contact.setId("782918");
		waybillIntegral.setContact(contact);
		waybillIntegral.setScore(20);
		
		waybillIntegralList.add(waybillIntegral);
		waybillIntegralManager.disPoseWaybillIntegral(waybillIntegralList);
		waybillIntegralManager.getNeedDisPoseWaybills();

		clearData();
	}
	
	
	private void clearData() throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("update t_cust_memberpoint t set t.fcurrenttotalscore = t.fcurrenttotalscore-20,"+
                "t.ftotalscore =  t.ftotalscore-20 where fmemberid=584740");
		sqlList.add("update T_CUST_LINKMANPOINT t set t.fcurrenttotalscore = t.fcurrenttotalscore-20,"+
                " t.ftotalscore =  t.ftotalscore-20 where flinkmanid=782918");
	
             SpringTestHelper.executeBatch(sqlList);
		
	}
}
