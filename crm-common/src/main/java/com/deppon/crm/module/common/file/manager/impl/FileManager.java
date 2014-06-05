package com.deppon.crm.module.common.file.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.domain.FileTemplate;
import com.deppon.crm.module.common.file.domain.FileUse;
import com.deppon.crm.module.common.file.domain.exception.FileException;
import com.deppon.crm.module.common.file.domain.exception.FileExceptionType;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.common.file.service.IFileService;
import com.deppon.crm.module.common.file.util.ExcelFileUtil;
import com.deppon.crm.module.common.file.util.FileUtil;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * 
 * Description: 文件管理
 * 
 * @author 安小虎
 * @version 0.1 2012-2-24
 */
public class FileManager implements IFileManager {
	private static Log log = LogFactory.getLog(FileManager.class);
	// 格式化数字
	private DecimalFormat df = new DecimalFormat("#.00");

	private IFileService fileService;

	public IFileService getFileService() {
		return fileService;
	}

	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
	}

	@SuppressWarnings("unused")
	private String getSavePath(String type) {
		PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();
		String createFolder = propertiesUtil.getProperty("createFolder").trim();

		// 格式日期——创建文件夹用
		SimpleDateFormat _sdf = null;
		try {
			_sdf = new SimpleDateFormat(createFolder == null ? "yyyy-MM"
					: createFolder);
		} catch (Exception e) {
			_sdf = new SimpleDateFormat("yyyy-MM");
		}
		String _formatDate = _sdf.format(new Date());

		// 文件保存路径
		String realPath = propertiesUtil.getProperty("savePath");
		String _type = propertiesUtil.getProperty(type);
		if (_type != null && !"".equals(_type.trim())) {
			realPath += "/" + _type;
		}
		realPath += "/" + _formatDate;
		log.info("~~~realPath=" + realPath);
		File saveDir = new File(realPath);
		if (!saveDir.exists())
			saveDir.mkdirs();

		return realPath;
	}

	/**
	 * 
	 * <p>
	 * Description:公告管理优化 新增方法 附件上传<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-4-7下午3:59:52
	 * @param file
	 * @param fileName
	 * @param fileType
	 * @param type
	 * @param maxSizeLimit
	 * @param imageOrAttachment
	 * @param attachmentSize
	 * @return
	 * @update 2013-4-7下午3:59:52
	 */
	@SuppressWarnings({ "serial", "unused" })
	@Override
	public FileInfo fileUpload(File file, String fileName, String fileType,
			String type,String maxSizeLimit,
				String imageOrAttachment,String attachmentSize) {
		// 如果文件名长度超过100个字符抛出异常
		if (fileName.length() > 100) {
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENAME_TOOLONG);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
		// 如果文件名长度超过100个字符抛出异常
		if (fileName.length() > 100) {
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENAME_TOOLONG);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
		// 1、构造返回对象-存放文件相关信息
		FileInfo fi = new FileInfo();
		fi.setFileName(fileName);
		fi.setFileType(fileType);
		// 获得文件大小
		if (file.exists()) {
			long s = 0;
			try {
				FileInputStream fis = new FileInputStream(file);
				s = fis.available();
			} catch (Exception e) {
				e.printStackTrace();
				FileException fe = new FileException(
						FileExceptionType.FILE_EXCEPTION);
				throw new GeneralException(fe.getErrorCode(), fe.getMessage(),
						fe, new Object[] {}) {
				};
			}
			long maxSize =  0;
			if(!StringUtils.isEmpty(maxSizeLimit)){
				maxSize = changeMaxSize(maxSizeLimit);
			}else{
				maxSize = Long.valueOf(PropertiesUtil.getInstance()
						.getProperty("maxSize").trim());
			}
			
			if (s > maxSize) {
				log.info("大于设置上传大小了：" + maxSize);
				FileException fe = new FileException(
						FileExceptionType.FILE_EXCEPTION_MAXSIZE);
				throw new GeneralException(fe.getErrorCode(), fe.getMessage(),
						fe, new Object[] {}) {
				};
			}
			fi.setFileSize(Double.valueOf(df.format(s / 1024)));

		}

		// 2、保存文件到磁盘
		try {
			String path = FileUtil.saveFile2Disk(file, this.getSavePath(type),
					fileName);
			fi.setSavePath(path);
		} catch (GeneralException e2) {
			throw e2;
		} catch (Exception e) {
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}

		return fi;
	}
	
	/**
	 * @author
	 * @描述  附件上传
	 */
	@Override
	public FileInfo fileUpload(File file, String fileName, String fileType,
			String type) {
		// 如果文件名长度超过100个字符抛出异常
		if (fileName.length() > 100) {
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENAME_TOOLONG);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
		// 1、构造返回对象-存放文件相关信息
		FileInfo fi = new FileInfo();
		fi.setFileName(fileName);
		fi.setFileType(fileType);
		// 获得文件大小
		if (file.exists()) {
			long s = 0;
			try {
				FileInputStream fis = new FileInputStream(file);
				s = fis.available();
			} catch (Exception e) {
				e.printStackTrace();
				FileException fe = new FileException(
						FileExceptionType.FILE_EXCEPTION);
				throw new GeneralException(fe.getErrorCode(), fe.getMessage(),
						fe, new Object[] {}) {
				};
			}
			long maxSize = Long.valueOf(PropertiesUtil.getInstance()
					.getProperty("maxSize").trim());
			if (s > maxSize) {
				log.info("大于设置上传大小了：" + maxSize);
				FileException fe = new FileException(
						FileExceptionType.FILE_EXCEPTION_MAXSIZE);
				throw new GeneralException(fe.getErrorCode(), fe.getMessage(),
						fe, new Object[] {}) {
				};
			}
			fi.setFileSize(Double.valueOf(df.format(s / 1024)));

		}

		// 2、保存文件到磁盘
		try {
			String path = FileUtil.saveFile2Disk(file, this.getSavePath(type),
					fileName);
			fi.setSavePath(path);
		} catch (GeneralException e2) {
			throw e2;
		} catch (Exception e) {
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}

		return fi;
	}
	
	/**
	 * 
	 * <p>
	 * Description:公告管理优化 新增方法 附件上传<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-4-7下午3:59:33
	 * @param file
	 * @param fileName
	 * @param fileType
	 * @param type
	 * @param maxSizeLimit
	 * @param imageOrAttachment
	 * @param attachmentSize
	 * @return
	 * @update 2013-4-7下午3:59:33
	 */
	@Override
	public List<FileInfo> fileUpload(File[] file, String[] fileName,
			String[] fileType, String type,String maxSizeLimit,
				String imageOrAttachment,String attachmentSize) {
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		if (!StringUtils.isEmpty(imageOrAttachment)) {
				//如果类型为上传图片
				if(imageOrAttachment.equals("image")){
					judgeImageSize(file[file.length-1], maxSizeLimit, type);
				}
				//如果类型为上传附件
				if(imageOrAttachment.equals("attachment")){
					judgeAllSize(file, maxSizeLimit, attachmentSize);
			}
		}
		
		if (file != null && file.length > 0) {
			for (int i = 0; i < fileType.length; i++) {
				fileInfoList.add(this.fileUpload(file[i], fileName[i],
						fileType[i], type, maxSizeLimit, imageOrAttachment,attachmentSize));
			}
		}
		return fileInfoList;
	}
	
	/**
	 * @author 
	 * @描述   附件上传
	 */
	@Override
	public List<FileInfo> fileUpload(File[] file, String[] fileName,
			String[] fileType, String type) {
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();

		if (file != null && file.length > 0) {
			for (int i = 0; i < fileType.length; i++) {
				fileInfoList.add(this.fileUpload(file[i], fileName[i],
						fileType[i], type));
			}
		}
		
		return fileInfoList;
	}
	
	/**
	 * 
	 * <p>
	 * Description:判断图片大小<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-2-22下午3:30:23
	 * @param file
	 * @return
	 * boolean
	 * @update 2013-2-22下午3:30:23
	 */
	private boolean judgeImageSize(File file, String maxSizeLimit, String type){
		return true;
	}
	
	/**
	 * 
	 * <p>
	 * Description:判断附件总大小<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-2-22下午3:29:45
	 * @param file
	 * @return
	 * boolean
	 * @update 2013-2-22下午3:29:45
	 */
	private boolean judgeAllSize(File[] file, String maxSizeLimit, String attachmentSize){
		long s = 0;
		for (int i = 0; i < file.length; i++) {
			try {
				FileInputStream fis = new FileInputStream(file[i]);
				s += fis.available();
			} catch (Exception e) {
				e.printStackTrace();
				FileException fe = new FileException(
						FileExceptionType.FILE_EXCEPTION);
				throw new GeneralException(fe.getErrorCode(), fe.getMessage(),
						fe, new Object[] {}) {
				};
			}
//			fi.setFileSize(Double.valueOf(df.format(s / 1024)));
		}
//		long maxSize = Long.valueOf(PropertiesUtil.getInstance()
//				.getProperty("maxSize").trim());
		long maxSize =  changeMaxSize(maxSizeLimit);
		
		if(!StringUtil.isEmpty(attachmentSize)){
			s = s+Integer.parseInt(attachmentSize)*1024;
		}
		if (s > maxSize) {
			log.info("大于设置上传大小了：" + maxSize);
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_MAXSIZE);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(),
					fe, new Object[] {}) {
			};
		}
		return true;
	}
	
	private long changeMaxSize(String maxSizeLimit){
		long maxSize = 0;
		String lastStr;
		String numStr;
		if(maxSizeLimit==null||maxSizeLimit.equals("")){
			return 0;
		}else{
			lastStr = maxSizeLimit.substring(maxSizeLimit.length()-1);
			numStr = maxSizeLimit.substring(0,maxSizeLimit.length()-1);
			if(lastStr.equals("K")||lastStr.equals("k")){
				maxSize = Integer.parseInt(numStr)*1024;
			}
			if(lastStr.equals("M")||lastStr.equals("m")){
				maxSize = Integer.parseInt(numStr)*1024*1024;
			}
			if(lastStr.equals("G")||lastStr.equals("g")){
				maxSize = Integer.parseInt(numStr)*1024*1024*1024;
			}
		}
		return maxSize;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.file.manager.IFileManager#saveFileInfo(com
	 * .deppon.crm.module.common.file.domain.FileInfo)
	 */
	@Override
	public boolean saveFileInfo(FileInfo fileInfo) {
		return this.fileService.saveFileInfo(fileInfo);
	}

	@Override
	public boolean saveFileInfo(List<FileInfo> fileInfoList) {
		if (fileInfoList != null && fileInfoList.size() > 0) {
			for (FileInfo fileInfo : fileInfoList) {
				this.saveFileInfo(fileInfo);
			}
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.file.manager.IFileManager#deleteFileInfo
	 * (java.lang.String)
	 */
	@Override
	public boolean deleteFileInfo(String id) {
		return this.fileService.deleteFileInfo(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.file.manager.IFileManager#deleteFileInfo
	 * (com.deppon.crm.module.common.file.domain.FileInfo)
	 */
	@Override
	public boolean deleteFileInfo(FileInfo fileInfo) {
		String id = fileInfo.getId();
		if (id != null && !"".equals(id)) {
			this.deleteFileInfo(id);
		}
		return FileUtil.deleteDiskFile(fileInfo.getSavePath());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.file.manager.IFileManager#
	 * searchFileInfoByCondition
	 * (com.deppon.crm.module.common.file.domain.FileInfo, int, int)
	 */
	@Override
	public List<FileInfo> searchFileInfoByCondition(FileInfo fileInfo,
			int offset, int limit) {
		return this.fileService.searchFileInfoByCondition(fileInfo, offset,
				limit);
	}

	/**
	 * <p>
	 * 解析EXCEL文件
	 * </p>
	 * 
	 * @param File
	 *            inFile
	 * @param FileTemplate
	 *            template
	 * @param User
	 *            user
	 * @return List
	 * @throws Exception
	 * */
	public List analyzeExcel(File inFile, FileTemplate type, User user)
			throws Exception {
		if (inFile == null)
			return null;
		if (inFile.exists() == false)
			return null;
		return ExcelFileUtil.readExcel(inFile,
				ExcelFileUtil.getExcelTemplateByCode(type));
	}

	/**
	 * <p>
	 * 生成EXCEL文件
	 * </p>
	 * 
	 * @throws Exception
	 * */
	public File exportExcel(List dataList, FileTemplate type) throws Exception {
		String outPath = FileUse.EXPORTEXCEL.filePath();
		File file = new File(outPath);
		if (file.exists() == false) {
			file.mkdirs();
		}
		String fName = "workbook.xls";
		String filePath = outPath + "/" + FileUtil.createFileName(fName);
		return ExcelFileUtil.generateExcel(dataList,
				ExcelFileUtil.getExcelTemplateByCode(type), filePath);
	}

}
