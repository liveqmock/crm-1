package com.deppon.crm.module.common.server.util;


public class FileDlpUtilTest {

	/**
	 * DLP无法测试
	 */
//	// private FileDlpUtil fileDlpUtil;
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void testFileDlp() {
//
//		try {
//			String path = "E:\\U\\";
//			String name = "crm.txt";
//			System.out.println(path);
//			// 加密文件
//			String sourceFilePath = path + name;
//			String encryptFilePath = FileDlpUtil.getChangeFilePath(path + name,
//					FileDlpUtil.FILE_LOCKED);
//			boolean encryptFile = FileDlpUtil.encryptFile(sourceFilePath,
//					encryptFilePath);
//			if (encryptFile) {
//				System.out.println("加密成功");
//			} else {
//				System.out.println("加密失败");
//			}
//			// 判断是否为加密文件
//			boolean isEncryptFile = FileDlpUtil
//					.isDynamicDecrypt(encryptFilePath);
//			if (isEncryptFile) {
//				// 解密文件
//				String decryptFilePath = FileDlpUtil.getChangeFilePath(
//						encryptFilePath, FileDlpUtil.FILE_UNLOCK);
//				boolean decryptFile = FileDlpUtil.decryptFile(encryptFilePath,
//						decryptFilePath);
//				if (decryptFile) {
//					System.out.println("解密成功");
//				} else {
//					System.out.println("解密失败");
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	@Test
//	public void testEncryptFile() {
//
//		try {
//			String path = "E:\\U\\";
//			String name = "crmul.txt";
//			System.out.println(path);
//			// 加密文件
//			String newfile = FileDlpUtil.getEncryptFile(path + name);
//			if (newfile != null) {
//				System.out.println("加密成功");
//			} else {
//				System.out.println("加密失败");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	@Test
//	public void testDecryptFile() {
//
//		try {
//			String path = "E:\\U\\";
//			String name = "crmld.txt";
//			System.out.println(path);
//			// 解密文件
//			String newfile = FileDlpUtil.getDecryptFile(path + name);
//			if (!newfile.equals(path + name)) {
//				System.out.println("解密成功");
//			} else {
//				System.out.println("解密失败");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}
