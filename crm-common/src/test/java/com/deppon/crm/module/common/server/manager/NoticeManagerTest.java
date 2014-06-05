
package com.deppon.crm.module.common.server.manager;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.common.file.manager.impl.FileManager;
import com.deppon.crm.module.common.server.dao.impl.NoticeDaoImpl;
import com.deppon.crm.module.common.server.manager.impl.NoticeManagerImpl;
import com.deppon.crm.module.common.server.manager.impl.NoticeValidate;
import com.deppon.crm.module.common.server.service.impl.NoticeServiceImpl;
import com.deppon.crm.module.common.shared.domain.Notice;
import com.deppon.crm.module.common.shared.domain.NoticeSearchCondition;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;
/**
 * 
 * @author xhl
 *
 */


public class NoticeManagerTest extends TestCase{
	private  NoticeManagerImpl noticeManagerImpl;
	private NoticeDaoImpl noticeDao;
	private NoticeServiceImpl noticeService;
//	private FileManager fileManager;

	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	
	@Override
	protected void setUp() throws Exception {
		try{
			if(ctx == null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			noticeService=ctx.getBean(NoticeServiceImpl.class);
			noticeManagerImpl = ctx.getBean(NoticeManagerImpl.class);
			noticeDao = ctx.getBean(NoticeDaoImpl.class);
//			fileManager = ctx.getBean(FileManager.class);
		}catch(GeneralException e){
		}
	}
	@Test
	public void testSearch(){
		NoticeSearchCondition noticeSearchCondition=new NoticeSearchCondition();
		noticeSearchCondition.setActive(null);
		//noticeSearchCondition.setTop(false);
		noticeSearchCondition.setTop(false);
	
		//noticeSearchCondition.setCreateUser("张");
		List<Notice> notices=noticeManagerImpl.searchNoticeByCondition(noticeSearchCondition);
		noticeManagerImpl.getNoticeService();
		Notice notice = new Notice();
		notice.setTitle("title");
		notice.setContent("content");
		User user = new User();
		Employee empCode = new Employee();
		empCode.setEmpName("name");
		user.setEmpCode(empCode);
		noticeManagerImpl.addNotice(notice, user);
		noticeManagerImpl.updateNoticeById(notice, user);
		noticeManagerImpl.deleteNoticeById("106139");
		//noticeManagerImpl.findNoticeById("106139");
		String[] ids = {"106139"};
		noticeManagerImpl.delteNoticesByIdList(ids);
		noticeManagerImpl.updateNoticesByIdList(ids);
		try{
			noticeManagerImpl.addNotice(null, user);
		}catch(GeneralException e){}
		try{
			noticeManagerImpl.updateNoticeById(null, user);
		}catch(GeneralException e){}
		//noticeDao.searchNoticeByCondition(noticeSearchCondition);
		System.out.println(notices.size());
	}
	public void testGetCountByCondition(){
		NoticeSearchCondition noticeSearchCondition = new NoticeSearchCondition();
		noticeManagerImpl.getCountByCondition(noticeSearchCondition);
		noticeManagerImpl.topNoticeByIds("106139");
	}
	public void testUpdate(){
		NoticeSearchCondition noticeSearchCondition=new NoticeSearchCondition();
//		noticeSearchCondition.setCreateUser("张");
		String [] ids={"7"};
		noticeManagerImpl.updateNoticesByIdList(ids);
	}
	public void testUpdateVisits(){
		noticeManagerImpl.updateNoticeVisitsById("7");
		
	}
	public void testNoticeValidate(){
		NoticeValidate noticeValidate= new NoticeValidate();
		Notice notice = new Notice();
		noticeValidate.validate(null);
		noticeValidate.validate(notice);
		notice.setTitle("");
		noticeValidate.validate(notice);
		notice.setTitle("title");
		noticeValidate.validate(notice);
		notice.setContent("");
		noticeValidate.validate(notice);
		notice.setContent("content");
		noticeValidate.validate(notice);
	}
	
}
