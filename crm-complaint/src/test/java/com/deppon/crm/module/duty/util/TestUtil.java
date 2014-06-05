package com.deppon.crm.module.duty.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.server.manager.impl.MessageManager;
import com.deppon.crm.module.complaint.server.manager.impl.ComplaintManager;
import com.deppon.crm.module.complaint.server.service.ICellphoneMessageInfoService;
import com.deppon.crm.module.complaint.server.service.IComplaintService;
import com.deppon.crm.module.complaint.server.service.IProcResultService;
import com.deppon.crm.module.complaint.server.service.impl.CellphoneMessageInfoServiceImpl;
import com.deppon.crm.module.complaint.server.service.impl.ProcResultServiceImpl;
import com.deppon.crm.module.duty.server.dao.IBasicLevelDao;
import com.deppon.crm.module.duty.server.dao.IDutyDao;
import com.deppon.crm.module.duty.server.dao.IDutyFeedbackDao;
import com.deppon.crm.module.duty.server.dao.IDutyResultDao;
import com.deppon.crm.module.duty.server.dao.IFeedAttachDao;
import com.deppon.crm.module.duty.server.dao.IInformUserDao;
import com.deppon.crm.module.duty.server.dao.IProcResultDao;
import com.deppon.crm.module.duty.server.dao.impl.BasicLevelDaoImpl;
import com.deppon.crm.module.duty.server.dao.impl.DutyDao;
import com.deppon.crm.module.duty.server.dao.impl.DutyFeedbackDaoImpl;
import com.deppon.crm.module.duty.server.dao.impl.DutyResultDao;
import com.deppon.crm.module.duty.server.dao.impl.FeedAttachDao;
import com.deppon.crm.module.duty.server.dao.impl.InformUserDao;
import com.deppon.crm.module.duty.server.dao.impl.ProcResultDaoImpl;
import com.deppon.crm.module.duty.server.manager.IDutyManager;
import com.deppon.crm.module.duty.server.manager.impl.DutyManager;
import com.deppon.crm.module.frameworkimpl.server.cache.UserCacheProvider;
import com.deppon.crm.module.organization.server.service.impl.DepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.AppContext;



public class TestUtil {
	
	
	
	public static final String COMPLAINT_MANAGER = "89863";   //投诉处理组经理059224
	public static final String COMPLAINT_PROCESSOR = "89173"; //投诉处理员 058647
	public static final String EXCEPTION_PROCESSOR = "57457"; //异常处理员 048902
	public static final String EXCEPTION_MANAGER = "28157";   //异常处理组经理006537
	public static final String ADMIN = "1";//超级管理员000000
	public static final String PA = "24473";  //品牌公关部027457
	public static final String REPORTOR = "89862";//客服专员 (上报人)059228 
	public static final String OPERATOR_OFFICEER = "20665";//营运办公室人员004000
	public static final String OPERATOR_OFFICEER_ID = "20665";//营运办公室人员004000
	public static final String SHANGHAI_OFFICEER_ID = "22097";//上海事业部人员003919
	public static final String OPERATION_OFFICE_CODE = "W012101";   //营运办公室
	public static final String SHANGHAI_DEPARTMENT_CODE= "W011302";   //上海事业部
	
	
	
	
	
	static ClassPathXmlApplicationContext factory;
	public static IBasicLevelDao basicLevelDao;
	public static IProcResultDao procResultDao;
	public static IDutyFeedbackDao dutyFeedbackDao;
	public static IDutyDao dutyDao;
	public static IInformUserDao informUserDao;
	public static IDutyResultDao dutyResultDao;
	public static IFeedAttachDao feedAttachDao;
	public static IDutyManager dutyManager;
	public static ComplaintManager complaintManager; 

	
	public static UserCacheProvider userCacheProvider;
	public static User compManager, complaintProcessor, pa, exceptionManager, exceptionProcessor, admin, reportor, operatorOfficer,shanghaiOfficer;	
	public static Department shanghaiDepartment,operationOfficeDept;
	
	public static IProcResultService procResultService;
	public static IMessageManager messageManager;
	public static IComplaintService complaintService;
	public static ICellphoneMessageInfoService cellphoneMessageInfoService;
	static {
		AppContext.initAppContext("application", "","");
		String[] resource = { "classpath*:com/deppon/crm/module/**/META-INF/spring.xml"
//				"classpath*:com/deppon/crm/module/complaint/server/META-INF/springTest.xml"
				};
		factory = new ClassPathXmlApplicationContext(resource);
		basicLevelDao = (IBasicLevelDao) factory.getBean(BasicLevelDaoImpl.class);
		procResultDao = (IProcResultDao) factory.getBean(ProcResultDaoImpl.class);
		dutyDao = (IDutyDao) factory.getBean(DutyDao.class);
		dutyFeedbackDao = (IDutyFeedbackDao) factory.getBean(DutyFeedbackDaoImpl.class);
		informUserDao = (IInformUserDao) factory.getBean(InformUserDao.class);
		dutyResultDao = (IDutyResultDao) factory.getBean(DutyResultDao.class);
		feedAttachDao = (IFeedAttachDao)factory.getBean(FeedAttachDao.class);
		dutyManager = (IDutyManager)factory.getBean(DutyManager.class);
		complaintManager = (ComplaintManager) factory.getBean(ComplaintManager.class);

		
		procResultService = (IProcResultService)factory.getBean(ProcResultServiceImpl.class);
		messageManager = (IMessageManager)factory.getBean(MessageManager.class);
		userCacheProvider = (UserCacheProvider) factory
				.getBean(UserCacheProvider.class);
		compManager = (User) userCacheProvider.get(COMPLAINT_MANAGER);
		complaintProcessor = (User)userCacheProvider.get(COMPLAINT_PROCESSOR);
		exceptionManager = (User)userCacheProvider.get(EXCEPTION_MANAGER);
		exceptionProcessor = (User)userCacheProvider.get(EXCEPTION_PROCESSOR);
		pa = (User)userCacheProvider.get(PA);
		admin = (User)userCacheProvider.get(ADMIN);
		reportor = (User)userCacheProvider.get(REPORTOR);
		operatorOfficer = (User)userCacheProvider.get(OPERATOR_OFFICEER_ID);
		shanghaiOfficer = (User)userCacheProvider.get(SHANGHAI_OFFICEER_ID);
	    DepartmentService dpService=(DepartmentService)factory.getBean(DepartmentService.class);
	    shanghaiDepartment=dpService.getDeptByCode(SHANGHAI_DEPARTMENT_CODE);
	    operationOfficeDept= dpService.getDeptByCode("W011302");
	    
	    cellphoneMessageInfoService = (CellphoneMessageInfoServiceImpl)factory.getBean(CellphoneMessageInfoServiceImpl.class);
		
	}

}
