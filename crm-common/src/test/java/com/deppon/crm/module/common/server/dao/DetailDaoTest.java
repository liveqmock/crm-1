package com.deppon.crm.module.common.server.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.deppon.crm.module.common.server.dao.impl.DetailDao;
import com.deppon.crm.module.common.server.util.SpringTestHelper;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Detail;

import junit.framework.TestCase;

public class DetailDaoTest extends TestCase{
	
	private DetailDao detailDao = SpringTestHelper.get().getBean(DetailDao.class);
	public void setUp(){
		
	}
	
	private String code = "890DF9C2-0CBE-46AF-98E7-B841AE444597";
	
	public void test_addDetail(){
		System.out.println(DataHeadTypeEnum.SECOND_TRADE.toString());
		
		Detail detail = new Detail();
		detail.setParentId("325346");
		detail.setCodeType("SECOND_TRADE");
		detail.setCode(code);
		detail.setCodeDesc("二级行业信息");
		detail.setCodeSeq(1);
		detail.setStatus(true);
		detail.setLanguage("zh_CN");
		detail.setCreateUser("120571");
		//detail.setModifyUser("120571");
		System.out.println(code);

		detailDao.insertDetail(detail);

		
	}
	

	public void test_updateDetail(){
		
		Detail detail = new Detail();
		detail.setParentId("325346");
		detail.setCodeType("SECOND_TRADE");
		detail.setCode(code);
		detail.setCodeDesc("二级行业信息啦啦");
		detail.setModifyUser("2353465");
		boolean result = detailDao.updateDetail(detail);
		
		System.out.println(result);
	}
	
	
	public void test_delDetail(){
		Detail detail = new Detail();
		detail.setParentId("325346");
		detail.setCodeType("SECOND_TRADE");
		detail.setCode(code);
		detail.setCodeDesc("二级行业信息啦啦");
		detail.setModifyUser("2353465");
		detail.setInvaliTime(getInvaliTime());
		System.out.println(getInvaliTime());
		boolean result = detailDao.delDetail(detail);
		System.out.println(result);
	}

 
	public void test_queryDetail(){
		Detail detail = new Detail();
		detail.setParentId("11");
		detail.setCodeDesc("刚");
		detail.setCodeType("TRADE");
		List<Detail> detailLists = detailDao.getDetailByCondition(detail);
		for (Detail detail2 : detailLists) {
			
			System.out.println(detail2.getCode());
		}
	}
	
	public void test_queryMapCodeDesc(){
		
		
		Map<String,Map<String,String>> map = detailDao.queryMapCodeDesc("SECOND_TRADE", "'裤子','二级行业信息啦啦','456'");
	    System.out.println(map.size());
		for (Map.Entry<String, Map<String,String>> entry : map.entrySet()) {
		 System.out.println(entry.getKey()+"----"+entry.getValue().get("FCODEDESC")+"------"+entry.getValue().get("PARENT_DESC"));
		}
	
	}
	

	private Date getInvaliTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
		
		return calendar.getTime();
	}
}
