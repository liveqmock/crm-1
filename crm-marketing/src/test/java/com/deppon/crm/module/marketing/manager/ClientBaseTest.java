package com.deppon.crm.module.marketing.manager;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.marketing.server.manager.IClientBaseManager;
import com.deppon.crm.module.marketing.server.manager.impl.ClientBaseManager;
import com.deppon.crm.module.marketing.server.service.IBusinessOpportunityService;
import com.deppon.crm.module.marketing.shared.domain.activity.ClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.Multiple;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

public class ClientBaseTest {

	private static IClientBaseManager cb;


	@Before
	public void setUp() throws Exception {
		 cb= (IClientBaseManager)SpringTestHelper.get().getBean(ClientBaseManager.class);
	}
	
		
	
	@Test
	public void searchDept(){
		
	cb.searchDeptListByName("外场", 0, 20);
	}
	@Test		
	public void updateClientStatus(){
		ClientBase c=new ClientBase();
		c.setId("29");
		c.setClientBaseStatus("UNUSED");
		c.setModifyUser("150982");
		c.setModifyDate(new Date());
		cb.updateClientBaseStatus(c);
	}

	@Test
	public void searchSecondTradesBytrades(){
		List<String> trades=new ArrayList();
		trades.add("CLOTH_SPIN");
		trades.add( "LIFE_ELECTRIC");
		for(Detail t:cb.searchSecondTradesByList(trades)){
		
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
		int i=(int) (Math.random()*77084);
		c.setClientBaseName("测试用例数据勿改"+i);
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
	Set<String> s=new HashSet<String>();
	s.add("/marketing/createLttActivity.action");
	user.setAccessUris(s); 
	Employee empCode=new Employee();
	Department d=new Department();
	d.setId("106782");
	empCode.setDeptId(d);
	empCode.setId("22207");
	user.setEmpCode(empCode);
	
	
	c.setLineDept(ll);
	// cb.updateClientBase(c, user);
	System.out.println(cb.addClientBase(c, user));
	d.setId("106782");
	empCode.setDeptId(d);
	user.setEmpCode(empCode);
	ClientBase c1=new ClientBase();
	c1.setClientBaseName("测试用例数据勿改"+i);
	List<ClientBase> ClientBase;
	ClientBase=(List<ClientBase>)cb.searchClientBase(c1, 0, 1, user).get("clientBaseList");
	String id=ClientBase.get(0).getId();
	cb.deleteClientBaseById(id, user);
	
	}
	@Test
	public void update() throws ParseException{
		ClientBase c=new ClientBase();
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
		   Date d2 = sft.parse("2014-03-07");
		   Date d1 = sft.parse("2014-03-09");
		c.setId("29");
		c.setClientBaseName("测试用例请勿使22用");
		c.setClientBaseStatus("UNUSED");
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
	d.setId("11691");
	empCode.setDeptId(d);
	user.setEmpCode(empCode);
	
	c.setLineDept(ll);
	try{
		cb.updateClientBase(c, user,"1");
		c.setClientBaseStatus("CANCELLATION");
		cb.updateClientBaseStatus(c);
	}catch(Exception e){
		System.out.println("11");
	}
	
		
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
	c.setSecondTrades(s1);
	c.setClientTypes(s2);
	c.setClientlatents(s3);
	c.setClientSources(s3);
	c.setClientPropertys(s2);
	c.setTakeMethods(s1);
	c.setCooperateWills(s3);
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
	System.out.println(cb.searchClientBase(c, 0, 100, user).get("clientBaseList"));
	}
	@Test
	public void testsearchClientBaseDetail(){
		cb.searchClientBaseDetail("225");
	}
	
}
