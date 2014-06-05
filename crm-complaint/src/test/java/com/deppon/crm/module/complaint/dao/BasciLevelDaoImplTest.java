package com.deppon.crm.module.complaint.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.ibatis.session.RowBounds;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.complaint.server.dao.impl.BasciLevelDaoImpl;
import com.deppon.crm.module.complaint.server.util.BasicInfoConstants;
import com.deppon.crm.module.complaint.server.util.Constants;
import com.deppon.crm.module.complaint.shared.domain.BasciLevel;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelView;
import com.deppon.crm.module.complaint.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.complaint.shared.domain.BasicInfo;
import com.deppon.crm.module.complaint.shared.domain.BasicLevel;
import com.deppon.crm.module.complaint.shared.domain.BasicSearchCondition;

public class BasciLevelDaoImplTest extends TestCase{

	private BasciLevelDaoImpl BasciLevelDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			BasciLevelDao = ctx.getBean(BasciLevelDaoImpl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchBasciLevelById() {
		BasciLevelSearchCondition cnd=new BasciLevelSearchCondition();
		cnd.setParentid(23);
		List<BasciLevel> bl=BasciLevelDao.searchBasciLevelById(cnd);
		System.out.println(bl.size());
	}
	
	@Test
	public void testGetFoundationDataList() {
		BigDecimal bd=new BigDecimal(323);
		BasciLevelSearchCondition condition=new BasciLevelSearchCondition();
		condition.setBascilevelname("理赔");
		condition.setIfparent(0+"");
		List<BasciLevelView> blv1=BasciLevelDao.getFoundationDataList(condition);
//		assertEquals(blv1.size()>0, true);
		condition.setIfparent(1+"");
		List<BasciLevelView> bl2=BasciLevelDao.getFoundationDataList(condition);
//		assertEquals(bl2.size()>0, true);
	}
	
	@Test
	public void testSaveBasciLevel(){
		BasciLevel  basciLevel=new BasciLevel();
		basciLevel.setBascilevelname("zhangsan");
		basciLevel.setLevel("1");
		basciLevel.setParentid(new BigDecimal(23));
		basciLevel.setType(Constants.COMPLAINT_REPORTTYE_REPORT);
		Integer fid=BasciLevelDao.saveBasciLevel(basciLevel);
		BasciLevelDao.deleteBasciLevelById(fid);
		BasciLevelDao.deleteBasciLevelByParentId(fid);
		BasciLevelDao.deleteBasicTypeById(fid);
	}

	@Test
	public void testGetFBasiciLevel(){
		BasciLevelSearchCondition bsc=new BasciLevelSearchCondition();
		List<BasciLevel> bllist=BasciLevelDao.getFBasiciLevel(bsc);
		assertEquals(bllist.size()>0, true);
	}
	
	public void testGetBasciLevelById(){
		BasciLevelView bl=BasciLevelDao.getBasciLevelById(new BigDecimal(123));
//		assertEquals(null != bl, true);
	}
	
	@Test
	public void testUpdateBasciLevel(){
		BasciLevel  basciLevel=new BasciLevel();
		basciLevel.setBascilevelname("zhangsan");
		basciLevel.setLevel("1");
		basciLevel.setParentid(new BigDecimal(23));
		basciLevel.setType(Constants.COMPLAINT_REPORTTYE_REPORT);
		Integer fid=BasciLevelDao.saveBasciLevel(basciLevel);
		
		BasciLevel  basciLevel2=new BasciLevel();
		basciLevel2.setBascilevelname("zhangsan");
		basciLevel2.setLevel("1");
		basciLevel2.setParentid(new BigDecimal(23));
		basciLevel2.setType(Constants.COMPLAINT_REPORTTYE_REPORT);
		basciLevel2.setParentid(new BigDecimal(fid));
		Integer fid2=BasciLevelDao.saveBasciLevel(basciLevel);
		basciLevel2.setFid(new BigDecimal(fid2));
		basciLevel2.setBascilevelname("lisi");
		BasciLevelDao.updateBasciLevel(basciLevel2);
		
		BasciLevelView bl=BasciLevelDao.getBasciLevelById(new BigDecimal(fid2));
		assertEquals(bl.getChiledBLName().equals("lisi"), true);
		BasciLevelDao.deleteBasciLevelById(fid);
		BasciLevelDao.deleteBasciLevelById(fid2);
	}
	@Test
	public void testSearchBasicInfo(){
		BasicSearchCondition bsc = new BasicSearchCondition();
		bsc.setBasicType("001");
		bsc.setBasicContent("收货");
		RowBounds rb = new RowBounds(0,10);
		BasciLevelDao.searchBasicInfo(bsc,rb);
	}
	@Test
	public void testSearchCountBasicInfo(){
		BasicSearchCondition bsc = new BasicSearchCondition();
		bsc.setBasicType("001");
		bsc.setBasicContent("收货");
		BasciLevelDao.searchCountBasicInfo(bsc);
	}
	@Test
	public void testSaveBasicLevel(){
		BasicLevel basLev = new BasicLevel();
		basLev.setBasciLevelName("测试业务项");
		basLev.setCreateTime(new Date());
		basLev.setCreateUserId("100");
		basLev.setLastModifyUserId("100");
		basLev.setLastUpdateTime(new Date());
		basLev.setType("COMPLAINT");
		
		BasciLevelDao.saveBasicLevel(basLev);
		
		BasicSearchCondition bsc = new BasicSearchCondition();
		bsc.setBasicType("001");
		bsc.setBasicContent("测试");
		RowBounds rb = new RowBounds(0,10);
		List<BasicInfo> list = BasciLevelDao.searchBasicInfo(bsc,rb);
		
		basLev.setParentId(list.get(0).getBusItemId());
		basLev.setLevel("1");
		basLev.setBasciLevelName("测试业务范围");
		BasciLevelDao.saveBasicLevel(basLev);
		
		basLev = new BasicLevel();
		basLev.setBasciLevelName("测试业务项");
		basLev.setCreateTime(new Date());
		basLev.setCreateUserId("100");
		basLev.setLastModifyUserId("100");
		basLev.setLastUpdateTime(new Date());
		basLev.setType("COMPLAINT");
		
		BasciLevelDao.saveBasicLevel(basLev);
		
		bsc = new BasicSearchCondition();
		bsc.setBasicType("001");
		bsc.setBasicContent("测试");
		rb = new RowBounds(0,10);
		list = BasciLevelDao.searchBasicInfo(bsc,rb);
		
		basLev.setParentId(list.get(0).getBusItemId());
		basLev.setLevel("1");
		basLev.setBasciLevelName("测试业务范围");
		BasciLevelDao.saveBasicLevel(basLev);
		
		basLev = new BasicLevel();
		basLev.setBasciLevelName("测试业务项");
		basLev.setCreateTime(new Date());
		basLev.setCreateUserId("100");
		basLev.setLastModifyUserId("100");
		basLev.setLastUpdateTime(new Date());
		basLev.setType("COMPLAINT");
		
		BasciLevelDao.saveBasicLevel(basLev);
		
		bsc = new BasicSearchCondition();
		bsc.setBasicType("001");
		bsc.setBasicContent("测试");
		rb = new RowBounds(0,10);
		list = BasciLevelDao.searchBasicInfo(bsc,rb);
		
		basLev.setParentId(list.get(0).getBusItemId());
		basLev.setLevel("1");
		basLev.setBasciLevelName("测试业务范围");
		BasciLevelDao.saveBasicLevel(basLev);
	}
	@Test
	public void testUpdateBasicLevelById(){
		BasicSearchCondition bsc = new BasicSearchCondition();
		bsc.setBasicType("001");
		bsc.setBasicContent("测试");
		RowBounds rb = new RowBounds(0,10);
		List<BasicInfo> list = BasciLevelDao.searchBasicInfo(bsc,rb);
		
		BasicLevel basLev = new BasicLevel();
		basLev.setId(list.get(0).getBusItemId());
		basLev.setBasciLevelName("测试业务项1");
		basLev.setCreateTime(new Date());
		basLev.setCreateUserId("100");
		basLev.setLastModifyUserId("100");
		basLev.setLastUpdateTime(new Date());
		basLev.setType("COMPLAINT");
		
		BasciLevelDao.updateBasicLevelById(basLev);
		
	}
	@Test
	public void testUpdateBasicLevelByParentId(){
		BasicSearchCondition bsc = new BasicSearchCondition();
		bsc.setBasicType("001");
		bsc.setBasicContent("测试");
		RowBounds rb = new RowBounds(0,10);
		List<BasicInfo> list = BasciLevelDao.searchBasicInfo(bsc,rb);
		
		BasicLevel basLev = new BasicLevel();
		basLev.setId(list.get(0).getBusItemId());
		basLev.setBasciLevelName("测试业务项1");
		basLev.setCreateTime(new Date());
		basLev.setCreateUserId("100");
		basLev.setLastModifyUserId("100");
		basLev.setLastUpdateTime(new Date());
		basLev.setType("COMPLAINT");
		basLev.setParentId("99999999999999");
		BasciLevelDao.updateBasicLevelByParentId(basLev);
		
	}
	@Test
	public void testDeleteBusItemById(){
		
		List<String> ids = new ArrayList<String>();
		ids.add("11111111111111");
		BasciLevelDao.deleteBusItemByIds(ids);
	}
	@Test
	public void testDeleteBusScopeById(){
		BasicSearchCondition bsc = new BasicSearchCondition();
		bsc.setBasicType("002");
		bsc.setBasicContent("测试业务范围");
		RowBounds rb = new RowBounds(0,10);
		List<BasicInfo> list = BasciLevelDao.searchBasicInfo(bsc,rb);
		
		BasciLevelDao.deleteBusScopeById(list.get(0).getBusScopeId());
	}

	@Test
	public void testSearchBusItemByType(){
		List<BasicInfo> bs= BasciLevelDao.searchBusItemByType(BasicInfoConstants.BASICCOMPTYPECOMPAINT);
	}
	@Test
	public void testSearchBasicBusScopeVO(){
		BasicBusScopeVO busScope = new BasicBusScopeVO();
		busScope.setBusItemId("4");
		busScope.setBusScopeId("401");
		List<BasicBusScopeVO> bs = BasciLevelDao.searchBasicBusScopeVO(busScope);
	}
	@Test
	public void testSearchBusScopeByBusItemId(){
		BasciLevelDao.searchBusScopeByBusItemId("123");
	}
}
