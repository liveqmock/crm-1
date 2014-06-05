package com.deppon.crm.module.marketing.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.marketing.server.dao.IClientBaseDao;
import com.deppon.crm.module.marketing.server.dao.IMarketActivityDao;
import com.deppon.crm.module.marketing.server.dao.impl.ClientBaseDao;
import com.deppon.crm.module.marketing.server.dao.impl.MarketActivityDao;
import com.deppon.crm.module.marketing.shared.domain.activity.ClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.Multiple;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

public class ClientBaseDaoTest {
	private IClientBaseDao clientBaseDao;
	
	@Before
	public void setUp(){
		clientBaseDao = (IClientBaseDao)SpringTestHelper.get().getBean(ClientBaseDao.class);
	}
	@Test
	public void searchDeptDetail(){
		ClientBase c=new ClientBase();
		c.setId("225");
		clientBaseDao.searchClientBaseDetail(c);
	}

	@Test
	public void searchSecondTradesToString(){
		System.out.println(clientBaseDao.searchSecondTradesToString("1016", "CUSTOMER_BASE").toString());
	}
	
	@Test		
	public void updateClientStatus(){
		ClientBase c=new ClientBase();
		c.setId("29");
		c.setClientBaseStatus("UNUSED");
		c.setModifyUser("150982");
		c.setModifyDate(new Date());
		clientBaseDao.updateClientBase(c);
	}
	
	@Test
	public void searchSecondTradesBytrades(){
		List<String> trades=new ArrayList();
		trades.add("CLOTH_SPIN");
		trades.add( "LIFE_ELECTRIC");
		for(Detail t:clientBaseDao.searchSecondTradesByList(trades)){
		
			System.out.println(t.getCode());
		}
		
	}
	
	@Test
	public void searchClientBaseTest() throws ParseException{
		ClientBase c=new ClientBase();
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
		   Date d2 = sft.parse("2014-03-07");
		   Date d1 = sft.parse("2014-03-09");
		c.setId("28");
		c.setClientBaseName("测试DAO1222");
		c.setClientCreateEndTime( d1);
		c.setClientCreateStartTime(d2);
		String [] s1={"CLOTH_SPIN","c"};
		String [] s2={"45284789-6439-4C75-8108-F46D97A4F53D","c1"};
		String [] s3={"GOLD","c11"};
		
	c.setTrades(s1);
	c.setClientGrades(s2);
	c.setClientPropertys(s3);
	
	List<LineDept> ll= new  ArrayList<LineDept>();
	LineDept line=new LineDept();
	line.setArriveDeptName("上海");
	line.setArriveDeptCode("15091");
	line.setLeavedDeptName("北京");
	line.setLeaveDeptCode("15081");
	
	ll.add(line);
	ll.add(line);
	User user=new User();
	user.setId("22207");
	Employee empCode=new Employee();
	Department d=new Department();
	d.setId("106782");
	empCode.setDeptId(d);
	user.setEmpCode(empCode);
	c.setClientBaseStatus("CANCELLATION");
	
	c.setLineDept(ll);
	// cb.updateClientBase(c, user);
	
	System.out.println(clientBaseDao.addClientBase(c));
	Multiple multiple =new Multiple();
	multiple.setConditionId("28");
	multiple.setConditionType("multiple");
	multiple.setType("CLOTH_SPIN");
	clientBaseDao.addClientMultiple(multiple);
	clientBaseDao.addLineDept(line);
	d.setId("106782");
	empCode.setDeptId(d);
	user.setEmpCode(empCode);
	ClientBase c1=new ClientBase();
	c1.setClientBaseName("测试DAO1111111111111111111");
	ClientBase clientBase1=new ClientBase();
	clientBase1.setId(clientBase1.getId());
	clientBase1.setClientBaseStatus("CANCELLATION");
	clientBaseDao.deleteLineDeptById("28", "CUSTOMER_BASE");
	clientBaseDao.deleteMultipleById("28", "CUSTOMER_BASE");
	
	}
	@Test
	public void update() throws ParseException{
		ClientBase c=new ClientBase();
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
		   Date d2 = sft.parse("2014-03-07");
		   Date d1 = sft.parse("2014-03-09");
		c.setId("29");
		c.setClientBaseName("测试用例请勿使用");
		c.setClientBaseStatus("未使用");
		c.setClientCreateEndTime( d1);
		c.setClientCreateStartTime(d2);
		String [] s1={"CLOTH_SPIN","c"};
		String [] s2={"45284789-6439-4C75-8108-F46D97A4F53D","c1"};
		String [] s3={"GOLD","c11"};
		
	c.setTrades(s1);
	c.setClientGrades(s2);
	c.setClientPropertys(s3);
	
	List<LineDept> ll= new  ArrayList<LineDept>();
	LineDept line=new LineDept();
	line.setArriveDeptName("上海");
	line.setArriveDeptCode("15091");
	line.setLeavedDeptName("北京");
	line.setLeaveDeptCode("15081");
	
	ll.add(line);
	ll.add(line);
	User user=new User();
	user.setId("22207");
	Employee empCode=new Employee();
	Department d=new Department();
	d.setId("106782");
	empCode.setDeptId(d);
	user.setEmpCode(empCode);
	
	c.setLineDept(ll);
	clientBaseDao.updateClientBase(c);
	c.setClientBaseStatus("CANCELLATION");
	clientBaseDao.updateClientBaseStatus(c);
		
	}
	
	@Test
	public void searchClientBase() throws ParseException{
		ClientBase c=new ClientBase();
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
		   Date d2 = sft.parse("2014-03-07");
		   Date d1 = sft.parse("2014-03-09");
		c.setId("29");
		c.setClientBaseName("客户群12");
		c.setClientBaseStatus("未使用");
		c.setClientCreateEndTime( d1);
		c.setClientCreateStartTime(d2);
		String [] s1={"CLOTH_SPIN","c"};
		String [] s2={"45284789-6439-4C75-8108-F46D97A4F53D","c1"};
		String [] s3={"GOLD","c11"};
		
	c.setTrades(s1);
	c.setClientGrades(s2);
	c.setClientPropertys(s3);
	
	List<LineDept> ll= new  ArrayList<LineDept>();
	LineDept line=new LineDept();
	line.setArriveDeptName("上海");
	line.setArriveDeptCode("15091");
	line.setLeavedDeptName("北京");
	line.setLeaveDeptCode("15081");
	
	ll.add(line);
	ll.add(line);
	User user=new User();
	user.setId("22207");
	Employee empCode=new Employee();
	Department d=new Department();
	d.setId("106782");
	empCode.setDeptId(d);
	empCode.setId("22207");
	user.setEmpCode(empCode);
	
	c.setLineDept(ll);
	System.out.println(clientBaseDao.searchClientBase(c, 0, 20, user));
	}
}
