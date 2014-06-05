package com.deppon.crm.module.common.server.dao;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.file.dao.IFileDao;
import com.deppon.crm.module.common.file.domain.FileInfo;

public class FileDaoTest {
	private static ApplicationContext ctx = null;
	private IFileDao fileDao = null;
	private FileInfo fileInfo = null;

	@Before
	public void setUp() throws Exception {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext(
					"LadingstationDepartmentDaoBeanTest.xml");
		}
		if (fileDao == null) {
			fileDao = (IFileDao) ctx.getBean("fileDao");
		}
		if (fileInfo==null) {
			String str="1";
			String savePath = "D:\\";
			
			fileInfo=new FileInfo();
			fileInfo.setSourceId(str);
			fileInfo.setSourceType(str);
			fileInfo.setFileName(Calendar.getInstance().getTimeInMillis() + "");
			fileInfo.setFileType("xls");
			fileInfo.setSavePath(savePath);
			fileInfo.setFileSize(1024D);
		}
	}

	@After
	public void tearDown() throws Exception {
	}



	@Test
	public void testDeleteFileInfo() {
		this.fileDao.saveFileInfo(fileInfo);
		this.fileDao.deleteFileInfo(fileInfo.getId());
	}

	@Test
	public void testSearchFileInfoByCondition() {
		this.fileDao.searchFileInfoByCondition(null, 0, 10);
	}

}
