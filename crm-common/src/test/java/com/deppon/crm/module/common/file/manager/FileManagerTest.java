package com.deppon.crm.module.common.file.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.common.file.manager.impl.FileManager;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * @description 文件管理测试用例.
 * @version 0.1
 * @date 2012-5-14
 */

public class FileManagerTest {
	ClassPathXmlApplicationContext factory;
	private static IFileManager fileManager;
	private static ApplicationContext ctx = null;
	static String[] xmls = new String[]{"CommonFileBeanTest.xml"};
	static String id = "0";
	static{
		try{
			if(ctx == null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			fileManager = ctx.getBean(FileManager.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveFileInfo(){

		// 多文件信息保存
		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = null;
		fileManager.saveFileInfo(fileInfoList);
		fileInfoList = new ArrayList<FileInfo>();
		fileManager.saveFileInfo(fileInfoList);
		
		fileInfoList.add(fileInfo);
		fileManager.saveFileInfo(fileInfoList);
		
		id = fileInfo.getId();
	}

	/**
	 * {@link com.deppon.crm.module.common.file.manager.impl.FileManager#searchFileInfoByCondition(com.deppon.crm.module.common.file.domain.FileInfo, int, int)}
	 * 的测试方法。
	 */
	@Test
	public void testSearchFileInfoByCondition() {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setId(id);
		fileManager.searchFileInfoByCondition(fileInfo, 0, 10);
	}
	/**
	 * {@link com.deppon.crm.module.common.file.manager.impl.FileManager#deleteFileInfo(com.deppon.crm.module.common.file.domain.FileInfo)}
	 * 的测试方法。
	 */
	@Test
	public void testDeleteFileInfo() {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setId(null);
		fileInfo.setSavePath("\test\test");
		try {
			// 环境不同，因此无法测试文件删除
			fileManager.deleteFileInfo(fileInfo);
		} catch (GeneralException e) {
		}
		fileInfo.setId("");
		try {
			// 环境不同，因此无法测试文件删除
			fileManager.deleteFileInfo(fileInfo);
		} catch (GeneralException e) {
		}
		fileInfo.setId(id);
		try {
			// 环境不同，因此无法测试文件删除
			fileManager.deleteFileInfo(fileInfo);
		} catch (GeneralException e) {
		}
	}


}
