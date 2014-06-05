/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MapDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author zhujunyong
 * @version 0.1 Mar 29, 2012
 */
package com.deppon.crm.module.marketing.dao;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.dao.IWarnLostCustDao;
import com.deppon.crm.module.marketing.server.dao.impl.WarnLostCustDao;
import com.deppon.crm.module.marketing.server.utils.ExportExcel;
import com.deppon.crm.module.marketing.shared.domain.WarnLostCust;
import com.deppon.crm.module.marketing.shared.domain.WarnLostMailAccount;
import com.deppon.crm.module.marketing.shared.domain.WarnStatusUpdateInfo;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

/**   
 * <p>
 * Description:流失预警客户测试DAO<br />
 * </p>
 * @title visualMarketDaoTest.java.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author zzw
 * @version 0.1 2014.3.14
 */
public class WarnLostDaoTest {
	private static final String[] POSITIONS=new String[]{"营业区区域经理","大区总经理","快递大区总经理","事业部总裁"};

	IWarnLostCustDao warnLostCustDaoImp = null;
    @Before
    public void setUp() throws Exception {
    	warnLostCustDaoImp = SpringTestHelper.get().getBean(WarnLostCustDao.class);
    }
    @Test
    public void createWarnLostTest(){
    	//warnLostCustDaoImp.createWarnList();
    }
    @Test
    public void searchWarnLostTest() throws Exception{
    	String deptStandardCode="DP10272";
    	String level=WarnLostCust.CAREEA;
    	String[] para=new String[]{WarnLostCust.DIAMOND};
    	//Date beginTime=new Date();
    	//Date endTime=new Date();
    	List<WarnLostCust> s =warnLostCustDaoImp.searchWarnLostCust(deptStandardCode, level, para, new Date(), new Date());
    	//System.out.println(s.get(0).getCadreStandardName());
    	ExportExcel.resultSetToExcel(WarnLostCust.HEAD, WarnLostCust.KEYS, s, "RTT", "SHEET1");
    }
    @Test
    public void getDetpGroupFromWarnLostTest(){
    	String level=WarnLostCust.EXPRESSBIGAREA;
    	String position=POSITIONS[2];
    	String[] para=new String[]{WarnLostCust.GOLD,WarnLostCust.DIAMOND,WarnLostCust.PLATINUM};
    	List<WarnLostMailAccount> s=warnLostCustDaoImp.getDetpGroupFromWarnLost(level, para,position);
    	System.out.print(s);
    }
    @Test
    public void selectWarnLostTest(){
    	String custnumber="386412";
    	String custName="左军";
    	warnLostCustDaoImp.searchWarnLostCust("12201",null, custName,null,null);
    	//String[] para=new String[]{WarnLostCust.GOLD,WarnLostCust.DIAMOND,WarnLostCust.PLATINUM};
    	//List<WarnLostMailAccount> s=warnLostCustDaoImp.getDetpGroupFromWarnLost(level, para);
    	//System.out.print(s);
    }
    //WarnStatusUpdateInfo
    @Test
    public void updateWarnLostTest(){
    	try{
	  	WarnStatusUpdateInfo updateInfo=new WarnStatusUpdateInfo(634563,"AAAA",new Date(),new Date(),"123","123M",12,"114024",0);
	  	warnLostCustDaoImp.updateWarnInfo(updateInfo);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    @Test
    public void searchWarnLostTestT() throws Exception{
    	String deptid="11469";
    	String level=WarnLostCust.CAREEA;
    	String[] para=new String[]{WarnLostCust.DIAMOND};
    	Calendar cl= Calendar.getInstance();
		cl.add(Calendar.DATE, -9);
		Date BeginTime=cl.getTime();
		Date EndTime=new Date();
		System.out.println(BeginTime);
		System.out.println(EndTime);
		//List<WarnLostCust> s =warnLostCustDaoImp.searchWarnLostCust(deptid, null, "", BeginTime, EndTime);
		//List<WarnLostCust> s =warnLostCustDaoImp.searchWarnLostCust(deptid, null, null, BeginTime, EndTime, 50, 100);
		int res=warnLostCustDaoImp.searchWarnLostCustCount(deptid, null, null, BeginTime, EndTime);
    	//System.out.println(s.get(0).getCadreStandardName());
    	//ExportExcel.resultSetToExcel(WarnLostCust.HEAD, WarnLostCust.KEYS, s, "RTT", "SHEET1");
    }
    @Test
    public void getWarnInfoTest(){
	  	warnLostCustDaoImp.searchWarnLostCustInfo(0,"507050");
    }
//    @Test
//    public void updateCustWarnStatusAndPreSendTimeTest(){
//	  	warnLostCustDaoImp.updateCustWarnStatusAndPreSendTime();
//    }
}
