package com.deppon.crm.module.common.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.dao.impl.NoticeDaoImpl;
import com.deppon.crm.module.common.shared.domain.Notice;
import com.deppon.crm.module.common.shared.domain.NoticeSearchCondition;

public class NoticeDaoTest extends TestCase {

	private NoticeDaoImpl noticeDao;

	private static ApplicationContext ctx = null;
	String[] xmls = new String[] { "DaoBeanTest.xml" };

	@Override
	protected void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			noticeDao = ctx.getBean(NoticeDaoImpl.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Notice generateTestNotice() {
		Notice n = new Notice();
		n.setTitle("CRM二期即将上线4");
		n.setContent("上线了");
		n.setModuleBelong("工单模块");
		n.setType("系统公告");
		n.setTop(true);
		n.setActive(false);
		n.setCreateUser("张三");
		n.setModifyUser("张三");
		return n;

	}


	public void testAddNotice() {
		Notice n = generateTestNotice();
		noticeDao.addNotice(n);
		noticeDao.addNotice(null);

	}


	public void testGetNotice() {
		try{
			noticeDao.findNoticeById(7 + "");
		}catch(Exception e){}
		noticeDao.findNoticeById(null);
		noticeDao.findNoticeById("");

	}

	
	public void testUpdateNoticeById() {
		Notice n = new Notice();
		n.setId("7");
		n.setContent("Notice");
		noticeDao.updateNoticeById(n);
		noticeDao.updateNoticeById(null);
		n.setId(null);
		noticeDao.updateNoticeById(n);
		n.setId("");
		noticeDao.updateNoticeById(n);
	}

	public void testUpdateNoticeByIds() {
		List<String> ids = new ArrayList<String>();
		ids.add("9");
		ids.add("10");
		System.out.println(ids);
		noticeDao.updateNoticeByIdList(ids);

	}

	public void testUpdatevisits() {
//		Notice n = noticeDao.findNoticeById(7 + "");
//		System.out.println(n.getVisits());
//		n.setVisits(n.getVisits() + 1);
		noticeDao.updateNoticeVisits("1");
	}

	public void testSearchNotices() {
		NoticeSearchCondition noticeSearchCondition = new NoticeSearchCondition();
		// noticeSearchCondition.setStart(0);
		// //noticeSearchCondition.setLimit(4);
		// noticeSearchCondition.setCreateUser("张三");
		// noticeSearchCondition.setActive(true);
		List<Notice> ns = noticeDao
				.searchNoticeByCondition(noticeSearchCondition);
		// System.out.println(ns.get(0).getCreateTime());
		System.out.println(ns.get(0).getCreateDate());
		noticeSearchCondition.setLimit(2);
		noticeDao
		.searchNoticeByCondition(noticeSearchCondition);
	}

	public void testDeleteNoticById() {
		noticeDao.deleteNoticeById("21");

	}

	public void testDeleteNoticeByIds() {
		List<String> ids = new ArrayList<String>();
		ids.add("22");
		ids.add("23");
		noticeDao.deleteNoticesByIdList(ids);
	}
	public void testgetCount(){
		NoticeSearchCondition n=new NoticeSearchCondition();
		n.setType("type");
		noticeDao.getCountByCondition(n);
	}
	public void testtopNoticeByIds(){
		String noticeId = "110";
		noticeDao.topNoticeByIds(noticeId);
	}
}
