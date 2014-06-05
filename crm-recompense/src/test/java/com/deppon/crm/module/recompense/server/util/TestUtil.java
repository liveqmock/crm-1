package com.deppon.crm.module.recompense.server.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.impl.RoleDao;
import com.deppon.crm.module.authorization.server.dao.impl.UserDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.backfreight.server.dao.impl.BackFreightDao;
import com.deppon.crm.module.backfreight.server.manager.impl.BackFreightManager;
import com.deppon.crm.module.common.server.manager.impl.TodoWorkflowManager;
import com.deppon.crm.module.frameworkimpl.server.cache.UserCacheProvider;
import com.deppon.crm.module.organization.server.dao.impl.DepartmentDao;
import com.deppon.crm.module.recompense.server.dao.impl.BaseModelDaoImpl;
import com.deppon.crm.module.recompense.server.dao.impl.ClaimDao;
import com.deppon.crm.module.recompense.server.dao.impl.ListItemDaoImpl;
import com.deppon.crm.module.recompense.server.dao.impl.PaymentDao;
import com.deppon.crm.module.recompense.server.dao.impl.RecompenseDaoImpl;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.manager.impl.ClaimManager;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.shared.domain.Claim;
import com.deppon.crm.module.servicerecovery.server.dao.IServiceRecoveryDao;
import com.deppon.crm.module.servicerecovery.server.dao.impl.FinanceDeptDao;
import com.deppon.crm.module.servicerecovery.server.dao.impl.ServiceRecoveryDao;
import com.deppon.crm.module.servicerecovery.server.manager.IFinanceDeptManager;
import com.deppon.crm.module.servicerecovery.server.manager.IServiceRecoveryManager;
import com.deppon.crm.module.servicerecovery.server.manager.impl.ServiceRecoveryManager;
import com.deppon.crm.module.workflow.server.dao.impl.AmountConfigDaoImpl;
import com.deppon.crm.module.workflow.server.dao.impl.NormalClaimDaoImpl;
import com.deppon.crm.module.workflow.server.dao.impl.SignetManagerDaoImpl;
import com.deppon.crm.module.workflow.server.manager.INormalClaimManager;
import com.deppon.crm.module.workflow.server.manager.impl.AmountConfigManagerImpl;
import com.deppon.crm.module.workflow.server.manager.impl.SignetManagerManagerImpl;
import com.deppon.foss.framework.server.context.AppContext;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.loader.WorkflowDescriptor;

public class TestUtil {
	public static final String MANAGER = "229492";
	public static final String ADMIN = "21935";
	public static final String CASH = "273139";
	public static final String OA_CLIENT = "26700";
	public static final String BALANCE = "221791";
	public static final String PAY_CLIENT = "234443";

	public static Workflow workflow;
	public static WorkflowDescriptor workflowDescriptor;
	public static RecompenseManager recompenseManager;
	public static RecompenseService recompenseService;
	public static TodoWorkflowManager todoWorkflowManager;
	public static ClassPathXmlApplicationContext factory;
	public static UserCacheProvider userCacheProvider;
	public static User manager, admin, cash, oaClient, balance, payClient;
	public static FunctionProvider createRecompenseFunction;
	public static FunctionProvider saveRecompenseReportFunction;
	public static FunctionProvider saveRecompenseProcessFunction;
	public static FunctionProvider exemptRecompenseFunction;
	public static FunctionProvider updateRecompenseStatusFunction;
	public static FunctionProvider deleteRecompenseFunction;

	public static BaseModelDaoImpl baseModelDao = null;
	public static RoleDao roleDao = null;
	public static DepartmentDao departmentDao = null;
	public static UserDao userDao = null;
	public static ListItemDaoImpl listItemDao = null;
	public static RecompenseDaoImpl recompenseDao;
	public static ClaimDao claimDao = null;
	public static ClaimManager claimManager=null;
	public static BackFreightManager backFreightManager;
	public static BackFreightDao backFreightDao;
	public static IServiceRecoveryManager serviceRecoveryManager=null;
	public static ServiceRecoveryDao serviceRecoveryDao=null;
	public static IFinanceDeptManager financeDeptManager;
	public static FinanceDeptDao financeDeptDao = null;
	public static PaymentDao paymentDao = null;
	public static NormalClaimDaoImpl  normalClaimDao;
	public static SignetManagerDaoImpl signetManagerDao;
	public static SignetManagerManagerImpl signetManagerManager;
	public static INormalClaimManager normalClaimManager; 
	public static AmountConfigDaoImpl amountConfigDao; 
	public static AmountConfigManagerImpl amountConfigManager; 

	
	

	static {
		AppContext.initAppContext("application", "", "");
		String[] resource = { "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml" };
		factory = new ClassPathXmlApplicationContext(resource);

		baseModelDao = (BaseModelDaoImpl) factory.getBean("baseModelDao");
		roleDao = (RoleDao) factory.getBean("roleDao");
		departmentDao = (DepartmentDao) factory.getBean("departmentDao");
		userDao = (UserDao) factory.getBean("userDao");
		listItemDao = (ListItemDaoImpl) factory.getBean("listItemDao");
		recompenseDao = (RecompenseDaoImpl) factory.getBean("recompenseDao");

		userCacheProvider = (UserCacheProvider) factory
				.getBean("userCacheProvider");
		recompenseManager = (RecompenseManager) factory
				.getBean("recompenseManager");
		recompenseService = (RecompenseService) factory
				.getBean("recompenseService");
		todoWorkflowManager = (TodoWorkflowManager) factory
				.getBean("todoWorkflowManager");
		createRecompenseFunction = (FunctionProvider) factory
				.getBean("createRecompenseFunction");
		saveRecompenseReportFunction = (FunctionProvider) factory
				.getBean("saveRecompenseReportFunction");
		saveRecompenseProcessFunction = (FunctionProvider) factory
				.getBean("saveRecompenseProcessFunction");
		exemptRecompenseFunction = (FunctionProvider) factory
				.getBean("exemptRecompenseFunction");
		updateRecompenseStatusFunction = (FunctionProvider) factory
				.getBean("updateRecompenseStatusFunction");
		deleteRecompenseFunction = (FunctionProvider) factory
				.getBean("deleteRecompenseFunction");
		workflow = (Workflow) factory.getBean("basicWorkflow");

		manager = (User) userCacheProvider.get(MANAGER);
		admin = (User) userCacheProvider.get(ADMIN);
		cash = (User) userCacheProvider.get(CASH);
		oaClient = (User) userCacheProvider.get(OA_CLIENT);
		balance = (User) userCacheProvider.get(BALANCE);
		payClient = (User) userCacheProvider.get(PAY_CLIENT);
		
		claimManager = (ClaimManager) factory.getBean("claimManager");
		claimDao = (ClaimDao) factory.getBean("claimDao");
		
		backFreightManager = (BackFreightManager) factory
				.getBean("backFreightManager");
		backFreightDao = (BackFreightDao) factory
				.getBean("backFreightDao");
		serviceRecoveryManager = (ServiceRecoveryManager) factory
				.getBean("serviceRecoveryManager");
		serviceRecoveryDao = (ServiceRecoveryDao) factory
				.getBean("serviceRecoveryDao");
		financeDeptManager = (IFinanceDeptManager) factory
				.getBean("financeDeptManager");
		financeDeptDao = (FinanceDeptDao) factory.getBean("financeDeptDao");
		paymentDao = (PaymentDao) factory.getBean("paymentDao");
		normalClaimDao= (NormalClaimDaoImpl) factory.getBean("normalClaimDao");
		signetManagerDao= (SignetManagerDaoImpl) factory.getBean("signetManagerDao");
		signetManagerManager= (SignetManagerManagerImpl) factory.getBean("signetManagerManager");
		normalClaimManager = (INormalClaimManager) factory.getBean("normalClaimManager");
		amountConfigDao = (AmountConfigDaoImpl) factory.getBean("amountConfigDao");
		amountConfigManager = (AmountConfigManagerImpl) factory.getBean("amountConfigManager");
	}

}
