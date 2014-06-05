package com.deppon.crm.module.order.manager;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.order.server.manager.IOrderNumberRuleManager;
import com.deppon.crm.module.order.shared.domain.OrderNumberRule;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.AppContext;
import com.deppon.foss.framework.server.context.UserContext;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml")
public class OrderNumberRuleManagerTest extends AbstractTransactionalJUnit4SpringContextTests {

	static {
		AppContext.initAppContext("application", "", "");
	}

	@Autowired
	private IOrderNumberRuleManager manager;
	private User user;
	
	@Before
	public void init(){
		executeSqlScript("fixtures/init_ordernumberrule.sql", false);
		user = new User();
		user.setId("1");
		Employee emp = new Employee();
		emp.setEmpCode("DP001");
		emp.setEmpName("系统管理员");
		user.setEmpCode(emp);
		Department dep = new Department();
		dep.setDeptName("总裁");
		emp.setDeptId(dep);
	}

	@Test
	public void getAll() {
		List<OrderNumberRule> rules = manager.getAll();
		assertEquals(56, rules.size());
	}
	
	@Test
	public void find(){
		OrderNumberRule condition = new OrderNumberRule();
		condition.setResource("TAOBAO");
		List<OrderNumberRule> rules = manager.find(condition);
		assertEquals(7, rules.size());
		
		condition.setTransportMode("JZKY");
		rules = manager.find(condition);
		assertEquals(1, rules.size());
		
		condition.setResource(null);
		rules = manager.find(condition);
		assertEquals(8, rules.size());
		
		condition = new OrderNumberRule();
		condition.setSign("AK");
		rules = manager.find(condition);
		assertEquals(1, rules.size());
		OrderNumberRule rule = rules.get(0);
		assertEquals("ALIBABA", rule.getResource());
		assertEquals("PACKAGE", rule.getTransportMode());
		
		condition.setStatus(0);
		rules = manager.find(condition);
		assertEquals(0, rules.size());
		condition.setStatus(1);
		rules = manager.find(condition);
		assertEquals(1, rules.size());
	}
	
	// 正常更新
	@Test
	public void update(){
		OrderNumberRule rule = new OrderNumberRule();
		rule.setName("淘贝");
		rule.setSign("TT");
		rule.setRemark("测试");
		rule.setResource("TAOBAO");
		rule.setTransportMode("JZQY_SHORT");
		rule.setId("2d80394a-8e7e-4147-b3b9-27e830ad8f56");
		manager.update(rule, user);
		OrderNumberRule actual = simpleJdbcTemplate.queryForObject("select * from T_ORD_ORDERNUMBERRULE where fid = ?", new RowMapper<OrderNumberRule>() {

			@Override
			public OrderNumberRule mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				OrderNumberRule rule = new OrderNumberRule();
				rule.setName(rs.getString("FNAME"));
				rule.setSign(rs.getString("FSIGN"));
				rule.setRemark(rs.getString("FREMARK"));
				return rule;
			}
		}, rule.getId());
		assertEquals(rule.getSign(), actual.getSign());
		assertEquals(rule.getName(), actual.getName());
		assertEquals(rule.getRemark(), actual.getRemark());
	}
	
	// 把sign改为其它渠道已经使用了的
	@Test(expected = GeneralException.class)
	public void updateSignHasUsed(){
		OrderNumberRule rule = new OrderNumberRule();
		rule.setName("淘贝");
		rule.setSign("A");
		rule.setRemark("测试");
		rule.setResource("TAOBAO");
		rule.setTransportMode("JZQY_SHORT");
		rule.setId("2d80394a-8e7e-4147-b3b9-27e830ad8f56");
		manager.update(rule, user);
	}
	
	// 修改运输方式、订单来源，导致与其它规则冲突
	@Test(expected = GeneralException.class)
	public void updateConflict(){
		OrderNumberRule rule = new OrderNumberRule();
		rule.setName("淘贝");
		rule.setSign("T");
		rule.setRemark("测试");
		rule.setResource("ALIBABA");
		rule.setTransportMode("JZQY_SHORT");
		rule.setId("2d80394a-8e7e-4147-b3b9-27e830ad8f56");
		manager.update(rule, user);
	}
}
