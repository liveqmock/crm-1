/**
 * 
 */
package com.deppon.crm.module.complaint.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.complaint.server.manager.impl.ComplaintManager;
import com.deppon.crm.module.complaint.server.service.ICellphoneMessageInfoService;
import com.deppon.crm.module.complaint.server.service.IComplaintService;
import com.deppon.crm.module.complaint.server.service.IProcResultService;
import com.deppon.crm.module.complaint.server.service.impl.CellphoneMessageInfoServiceImpl;
import com.deppon.crm.module.frameworkimpl.server.cache.UserCacheProvider;
import com.deppon.crm.module.organization.server.service.impl.DepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.AppContext;

/**
 * @author Administrator
 *
 */
public class TestUtil {

//	public static final String COMPLAINT_MANAGER = "89863";   //投诉处理组经理059224
//	public static final String COMPLAINT_PROCESSOR = "89173"; //投诉处理员 058647
//	public static final String EXCEPTION_PROCESSOR = "57457"; //异常处理员 048902
//	public static final String EXCEPTION_MANAGER = "28157";   //异常处理组经理006537
//	public static final String ADMIN = "1";//超级管理员000000
//	public static final String PA = "24473";  //品牌公关部027457
//	public static final String REPORTOR = "89862";//客服专员 (上报人)059228 
//	public static final String OPERATOR_OFFICEER = "20665";//营运办公室人员004000
//	public static final String OPERATOR_OFFICEER_ID = "20665";//营运办公室人员004000
//	public static final String SHANGHAI_OFFICEER_ID = "22097";//上海事业部人员003919
//	public static final String OPERATION_OFFICE_CODE = "W012101";   //营运办公室
//	public static final String SHANGHAI_DEPARTMENT_CODE= "W011302";   //上海事业部
//	
////	protected static Log log;
//	public static ComplaintManager complaintManager; 
//	public static ClassPathXmlApplicationContext factory;
//	public static UserCacheProvider userCacheProvider;
//	public static User compManager, complaintProcessor, pa, exceptionManager, exceptionProcessor, admin, reportor, operatorOfficer,shanghaiOfficer;	
//	public static Department shanghaiDepartment,operationOfficeDept;
//	
//	public static IProcResultService procResultService;
//	public static IMessageManager messageManager;
//	public static IComplaintService complaintService;
//	public static ICellphoneMessageInfoService cellphoneMessageInfoService;
//	
//	static{
////		log = LogFactory.getLog(getClass());
//		AppContext.initAppContext("application","","");
//		String[] resource = { 
////				"classpath*:com/deppon/crm/module/authorization/server/META-INF/spring.xml",
////				"classpath*:com/deppon/crm/module/common/server/META-INF/spring.xml",
////				"classpath*:com/deppon/crm/module/duty/server/META-INF/spring.xml",
////				"com/deppon/crm/module/complaint/server/META-INF/spring.xml",
////				"classpath*:com/deppon/crm/module/client/server/META-INF/spring.xml",
////				"com/deppon/crm/module/complaint/server/META-INF/springTest.xml",
////				"classpath*:com/deppon/crm/module/**/META-INF/spring.xml",
//				"com/deppon/crm/module/complaint/server/META-INF/spring.xml",
//				"com/deppon/crm/module/client/server/META-INF/spring.xml",
//				"com/deppon/crm/module/common/server/META-INF/spring.xml",
//				"com/deppon/crm/module/authorization/server/META-INF/spring.xml",
//				"com/deppon/crm/module/duty/server/META-INF/spring.xml",
//				"com/deppon/crm/module/frameworkimpl/server/META-INF/spring.xml"
//		};
//
//		
//		factory = new ClassPathXmlApplicationContext(resource);
//		procResultService = (IProcResultService)factory.getBean("procResultService");
//		messageManager = (IMessageManager)factory.getBean("messageManager");
////	 	complaintService = (IComplaintService) factory.getBean("complaintService");
//		userCacheProvider = (UserCacheProvider) factory
//				.getBean("userCacheProvider");
//		complaintManager = (ComplaintManager) factory.getBean("complaintManager");
//		
//		compManager = (User) userCacheProvider.get(COMPLAINT_MANAGER);
//		complaintProcessor = (User)userCacheProvider.get(COMPLAINT_PROCESSOR);
//		exceptionManager = (User)userCacheProvider.get(EXCEPTION_MANAGER);
//		exceptionProcessor = (User)userCacheProvider.get(EXCEPTION_PROCESSOR);
//		pa = (User)userCacheProvider.get(PA);
//		admin = (User)userCacheProvider.get(ADMIN);
//		reportor = (User)userCacheProvider.get(REPORTOR);
//		operatorOfficer = (User)userCacheProvider.get(OPERATOR_OFFICEER_ID);
//		shanghaiOfficer = (User)userCacheProvider.get(SHANGHAI_OFFICEER_ID);
//	    DepartmentService dpService=(DepartmentService)factory.getBean("departmentService");
//	    shanghaiDepartment=dpService.getDeptByCode(SHANGHAI_DEPARTMENT_CODE);
//	    operationOfficeDept= dpService.getDeptByCode("W011302");
//	    
//	    cellphoneMessageInfoService = (CellphoneMessageInfoServiceImpl)factory.getBean("cellphoneMessageInfoService");
//	   
//	}
}
