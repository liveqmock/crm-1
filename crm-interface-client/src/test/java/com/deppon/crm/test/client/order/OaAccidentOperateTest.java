package com.deppon.crm.test.client.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.Assert;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.order.domain.ComplaintInformInfo;
import com.deppon.crm.module.client.order.domain.OaAccidentInfo;
import com.deppon.crm.test.client.common.OperaterTest;

public class OaAccidentOperateTest extends OperaterTest {

/*
	 * ID：
957159
951165
978850
982836
956106
1010090
1010540
1017392
1005928
1011924

运单号：
51516086
51252959
58926893
56932960
62453760
58877165
60726330
39417077
68122573
44353424
58193589
60642666
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-28
	 *
*/
	
	@Test
	public void queryAccidentByWaybillCode(){
		try {
//			List<OaAccidentInfo> infos = oaAccidentOperate.queryAccidentByWaybillCode("58193589");
			List<OaAccidentInfo> infos = oaAccidentOperate.queryAccidentByWaybillCode("19880002");
			
			Assert.assertNotNull(infos);
			Assert.assertEquals(infos.size(), 2);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	  
	@Test
	public void getErrorInfosTest(){
		try {
			
//			OaAccidentInfo info = oaAccidentOperate.queryAccidentByAccidentCode("2012021005681");
//			OaAccidentInfo info = oaAccidentOperate.queryAccidentByAccidentCode("201201978850");
			OaAccidentInfo info = oaAccidentOperate.queryAccidentByAccidentCode("201112957159");
			Assert.assertNotNull(info);
			Assert.assertEquals(info.getAccidentType(), 0);
			
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void informComplaintTest(){
		List<ComplaintInformInfo> infos = new ArrayList<ComplaintInformInfo>();
		infos.add(new ComplaintInformInfo("043260",6));
		
		infos.add(new ComplaintInformInfo("053951", 12));
		try {
			boolean flag = oaAccidentOperate.informComplaint(infos);
			Assert.assertTrue(flag);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	/*
	 * OA bug,工号不存在也消息通知成功了
	 * @Param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-31
	 *
	 */
	@Test
	public void informComplaintTest1(){
		List<ComplaintInformInfo> infos = new ArrayList<ComplaintInformInfo>();
		infos.add(new ComplaintInformInfo("053951",6));
		try {
			boolean flag = oaAccidentOperate.informComplaint(infos);
			System.out.println(flag);
			//Assert.assertFalse(flag);
			
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	
}
