package com.deppon.crm.module.common.file.util;

import java.io.File;

import com.esafenet.dll.NativeClientInterface;

public class FileDlpUtil {
	public static final String DEPPON_DLP_KEY = "GCUI0106CUIY0722";
	public static final String FILE_UNLOCK = "-UN";
	public static final String FILE_LOCKED = "-LD";

	// private static Log log = LogFactory.getLog(FileDlpUtil.class);

	// 加密源文件到目标位置
	public static boolean encryptFile(String sourcePath, String targetPath) {
		return NativeClientInterface.EncryptFile(sourcePath, targetPath,
				FileDlpUtil.DEPPON_DLP_KEY);
	}

	// 加密源文件返回加密后文件路径
	public static String getEncryptFile(String sourcePath) {
		String targetPath = getChangeFilePath(sourcePath,
				FileDlpUtil.FILE_LOCKED);
		boolean isEncryptFile = FileDlpUtil.isDynamicDecrypt(sourcePath);
		if (isEncryptFile) {
			return sourcePath;
		}
		boolean encryptFile = NativeClientInterface.EncryptFile(sourcePath,
				targetPath, FileDlpUtil.DEPPON_DLP_KEY);
		if (encryptFile) {
			return targetPath;
		} else {
			return null;
		}
	}

	// 解密源文件到目标位置
	public static boolean decryptFile(String sourcePath, String targetPath) {
		return NativeClientInterface.DecryptFile(sourcePath, targetPath,
				FileDlpUtil.DEPPON_DLP_KEY);
	}

	// 解密源文件返回加密后文件路径
	public static String getDecryptFile(String sourcePath) {
		String targetPath = getChangeFilePath(sourcePath,
				FileDlpUtil.FILE_UNLOCK);
		boolean isEncryptFile = FileDlpUtil.isDynamicDecrypt(sourcePath);
		boolean decryptFile = false;
		if (isEncryptFile) {
			decryptFile = NativeClientInterface.DecryptFile(sourcePath,
					targetPath, FileDlpUtil.DEPPON_DLP_KEY);
		}
		if (decryptFile) {
			return targetPath;
		} else {
			return sourcePath;
		}
	}

	// 判断路径文件是否动态加密过
	public static boolean isDynamicDecrypt(String path) {
		return NativeClientInterface.isDynamicDecrypt(path);
	}

	public static String getChangeFilePath(String sourcePath, String suffix) {
		File file = new File(sourcePath);
		String filePath = file.getParent();
		String fileName = file.getName();
		String targetPath = filePath + "/" + FileUtil.getFileNameNoEx(fileName)
				+ suffix + "." + FileUtil.getExtensionName(fileName);
		return targetPath;
	}
}
