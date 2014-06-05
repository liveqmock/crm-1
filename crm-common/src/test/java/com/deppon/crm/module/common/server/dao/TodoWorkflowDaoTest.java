package com.deppon.crm.module.common.server.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.dao.impl.AccountBankDao;
import com.deppon.crm.module.common.server.dao.impl.TodoWorkflowDao;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;

public class TodoWorkflowDaoTest extends TestCase {
	private TodoWorkflowDao todoWorkflowDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[] { "DaoBeanTest.xml" };

	@Before
	public void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			todoWorkflowDao = ctx.getBean(TodoWorkflowDao.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateTodoflagByWorkflowId() {
		todoWorkflowDao.updateTodoflagByWorkflowId(1L, "22");
	}

	@Test
	public void testUpdateTodoflagById() {
		todoWorkflowDao.updateTodoflagById("1", "22");
	}

	@Test
	public void testAddTodoWorkflowList() {
		List<TodoWorkflow> todoWorkflowList = new ArrayList<TodoWorkflow>();
		TodoWorkflow todoWorkflow1 = new TodoWorkflow();
		todoWorkflow1.setWorkflowId(1213L);
		todoWorkflow1.setWorkflowName("12123");
		todoWorkflow1.setApplicationId("1");
		todoWorkflow1.setApplicationStatus("12123");
		todoWorkflow1.setRoleId("1");
		todoWorkflow1.setDeptId("1");
		todoWorkflow1.setTodoFlag("1");
		todoWorkflow1.setRoleName("1");
		todoWorkflow1.setDeptName("1");
		todoWorkflow1.setApplicationName("1");
		todoWorkflow1.setApplicationDesc("1");
		todoWorkflow1.setApplicationStatus("1");
		todoWorkflow1.setUrl("1");
		todoWorkflowList.add(todoWorkflow1);
		todoWorkflowDao.addTodoWorkflow(todoWorkflow1);
		todoWorkflowDao.addTodoWorkflowList(todoWorkflowList);
		AccountBankDao  accountBankDao  = new AccountBankDao();
		accountBankDao.getAccountBank();
	}

	@Test
	public void testSearchTodoWorkflowMap() {
		todoWorkflowDao.searchTodoWorkflowMap("", "", "");
	}

	@Test
	public void testGetTodoWorkflowByWorkflowName() {
		String[] deptIds = { "49708" };
		todoWorkflowDao.getTodoWorkflowByWorkflowName("recompense", "3",
				deptIds, "TODO");
	}

	@Test
	public void testGetTodoWorkflowByWorkflowId() {
		String[] deptIds = { "49708" };
		todoWorkflowDao.getTodoWorkflowByWorkflowId("recompense", "1170", "3",
				deptIds, "TODO");
	}

}
