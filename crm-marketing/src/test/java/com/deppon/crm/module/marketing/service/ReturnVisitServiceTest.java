/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitServiceTest.java
 * @package com.deppon.crm.module.marketing.service 
 * @author ZhuPJ
 * @version 0.1 2012-4-11
 */
package com.deppon.crm.module.marketing.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.action.ReturnVisitVO;
import com.deppon.crm.module.marketing.server.service.IReturnVisitService;
import com.deppon.crm.module.marketing.server.service.impl.ReturnVisitService;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitServiceTest.java
 * @package com.deppon.crm.module.marketing.service 
 * @author ZhuPJ
 * @version 0.1 2012-4-11
 */

public class ReturnVisitServiceTest {
	IReturnVisitService rvService;
	@Before
	public void setUp() throws Exception {
		rvService = (IReturnVisitService) SpringTestHelper.get().getBean(
				ReturnVisitService.class);
	}

	@Test
	public void testSaveReturnVisit(){
		ReturnVisitVO rv = new ReturnVisitVO();
		/********************** 回访基本信息 start ********************************/
		rv.setReturnVisitType(MarketingConstance.DEVELOP_TYPE);
		rv.setPlanId("666");
		rv.setScheduleId("1815");
		rv.setMemberId("");		// 会员
		rv.setLinkManId("888888888");
		rv.setLinkName("Zpj");
		rv.setLinkManMobile("138111111");
		rv.setLinkManPhone("021123456");
		rv.setAccording("10");
		rv.setWay("");
		/********************** 回访基本信息  end ********************************/

		/********************** 回访意见信息 start ********************************/
		ReturnVisitOpinion rvo = new ReturnVisitOpinion();
		rvo.setOpinionType("时效类");
		rvo.setProblem("有意见有意见有意见");
		rvo.setSolution("解决解决解决");
		rvo.setReturnVisitId("6");
		
		List<ReturnVisitOpinion> rvoList = new ArrayList<ReturnVisitOpinion>();
		/********************** 回访意见信息 end ********************************/


		/********************** 回访走货信息 start ********************************/
		ReturnVisitVolumePotential rvvp = new ReturnVisitVolumePotential();
		rvvp.setComeFromCity("SH");
		rvvp.setComeToCity("BJ");
		rvvp.setVolumePotential("500");
		rvvp.setCompanyId("SF");
		rvvp.setReturnVisitId("6");
		rvvp.setRemark("备注123");

		List<ReturnVisitVolumePotential> rvvpList = new ArrayList<ReturnVisitVolumePotential>();		
		rvvpList.add(rvvp);
		rv.setVolumePotentialList(rvvpList);
		/********************** 回访走货信息 end ********************************/

		rv.setExecuteDeptId("49708");
		rv.setExecutorTime(new Date());
		rv.setExecutorId("76993");
		rv.setCreateDate(new Date());
		rv.setModifyDate(new Date());

		/********************** 跟踪日期 start ********************************/
		rv.setSchedule(new Date());
		rv.setTraceWay("1");
		rv.setExecuteDeptId("49708");
		rv.setExecutorId("76993");
		/********************** 跟踪日期 end ********************************/
		boolean rs = false;

		try {
//			rs = rvService.saveReturnVisit(rv, rvoList, rvvpList);
//			Assert.assertTrue(rs);		
		} catch (GeneralException e) {
			System.out.println(2);
		}

		try {
			rvoList.add(rvo);
			rv.setOpinionList(rvoList);
			rvvpList.add(rvvp);
			rv.setVolumePotentialList(rvvpList);
//			rs = rvService.saveReturnVisit(rv, rvoList, rvvpList);
//			Assert.assertTrue(rs);
		} catch (GeneralException e) {
			System.out.println(3);
		}
	}
}
