package com.esafenet.dll;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NativeClientInterface {
	static {
		try {
			String desktop = System.getProperty("sun.desktop");
			if (desktop != null && desktop.equals("windows")) {
				String fileLock = "FileLock";
				String cdgControl = "CdgControl";
				fileLock = getSysLibName(fileLock);
				loadLib(fileLock, null);
				cdgControl = getSysLibName(cdgControl);
				loadLib(cdgControl, null);
			} else {
				System.out.println("Begin");
				System.loadLibrary("CdgControl");
				System.out.println("end");
				// String fileLock = "libfilelock64.so";
				// String cdgControl = "libCdgControl64.so";
				// String tempName = "";
				// String oaArch = System.getProperty("os.arch");
				// if (oaArch.indexOf("64") < 0) {
				// fileLock = fileLock.replaceAll("64", "");
				// cdgControl = cdgControl.replaceAll("64", "");
				// }
				// tempName = fileLock.replaceAll("64", "");
				// System.out.println("Begin");
				// loadLib(fileLock, tempName);
				// System.out.println("loaded" + fileLock);
				// tempName = cdgControl.replaceAll("64", "");
				// loadLib(cdgControl, tempName);
				// System.out.println("loaded" + cdgControl);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getSysLibName(String libName) {
		String oaArch = System.getProperty("os.arch");
		if (oaArch.indexOf("64") >= 0) {
			libName = libName + "64";
		}
		libName = System.mapLibraryName(libName);
		System.out.println(System.getProperty("os.arch"));
		System.out.println(libName);
		return libName;
	}

	private synchronized static void loadLib(String fileName, String tempName)
			throws IOException {
		if (tempName == null || "".equals(tempName)) {
			tempName = fileName;
		}
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		String path = System.getProperty("java.io.tmpdir"); // classLoader.getResource("").getPath();
		String filePath = path + File.separator + tempName;
		// System.out.println(filePath + ":===>" + filePath);
		File extractedFile = new File(filePath);

		InputStream in = null;
		BufferedInputStream reader = null;
		FileOutputStream writer = null;

		if (!extractedFile.exists()) {
			try {
				in = classLoader.getResourceAsStream(fileName);

				reader = new BufferedInputStream(in);
				writer = new FileOutputStream(extractedFile);

				byte[] buffer = new byte[1024];

				while (reader.read(buffer) > 0) {
					writer.write(buffer);
					buffer = new byte[1024];
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null)
					in.close();
				if (writer != null)
					writer.close();
			}
		}
		System.load(filePath);
	}

	public static native String getCDGFileId(String path);

	public static native String getCDGFileVersion(String path);

	public static native boolean isDynamicDecrypt(String path);

	public static native boolean makeCDG(String srcFile, String destFile,
			String fileId, String passwd);

	public static native boolean removeCDG(String srcFile, String destDir,
			String passwd);

	public static native String buildSerialNumber(String code);

	// add by songyan 2009-07-06 解密文件
	public static native boolean DecryptFile(String sourcePath,
			String targetPath, String cypherKey);

	public static native boolean EncryptFile(String sourcePath,
			String targetPath, String cypherKey);
	// end add by songyan 2009-07-06 解密文件
}
