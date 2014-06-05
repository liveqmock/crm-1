package com.deppon.crm.module.common.file.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;

/**
 * 
 * @description 多文件上传ACTION
 * @author 安小虎
 * @version 0.1 2012-4-16
 */
public class FileUploadAction extends AbstractAction {
	// 文件对象
	private File[] file;
	// 文件名称
	private String[] fileFileName;
	// 文件类型
	private String[] fileContentType;
	// 类型：如是合同还是理赔
	protected String type;	
	//判断是上传图片还是附件(附件可以上传的是图片，但是上传附件的图片与上传图片的图片的限制不一样)
	private String imageOrAttachment;
	/**
	 * @param imageOrAttachment : set the property imageOrAttachment.
	 */
	public void setImageOrAttachment(String imageOrAttachment) {
		this.imageOrAttachment = imageOrAttachment;
	}
	
	//attachmentSize
	private String attachmentSize;
	/**
	 * @param attachmentSize : set the property attachmentSize.
	 */
	public void setAttachmentSize(String attachmentSize) {
		this.attachmentSize = attachmentSize;
	}
	
	//文件最大限制
	private String maxSizeLimit;
	/**
	 * @param maxSizeLimit : set the property maxSizeLimit.
	 */
	public void setMaxSizeLimit(String maxSizeLimit) {
		this.maxSizeLimit = maxSizeLimit;
	}

	//提示消息
    protected String message;
    public String getMessage() {
		return message;
	}
    //BEAN获得国际化对象
   	protected IMessageBundle messageBundle;
   	public void setMessageBundle(IMessageBundle messageBundle) {
   		this.messageBundle = messageBundle;
   	}
	// 返回值
	private List<FileInfo> fileInfoList = new ArrayList<FileInfo>();

	// 文件管理类
	private IFileManager fileManager;

	public void setFileManager(IFileManager fileManager) {
		this.fileManager = fileManager;
	}

	/**
	 * 
	 * @description 多文件上传.
	 * @author 安小虎
	 * @version 0.1
	 * @date 2012-5-4
	 */
	public String fileUpload() {
		try {
			if(imageOrAttachment==null||"".equals(imageOrAttachment)){
				fileInfoList = fileManager.fileUpload(file, fileFileName, fileContentType, type);
			}
			else{
			fileInfoList = fileManager.fileUpload(file, fileFileName,
					fileContentType, type, maxSizeLimit,imageOrAttachment,attachmentSize);
			}
		} catch (GeneralException e){ 
			message = messageBundle.getMessage(getLocale(),e.getErrorCode());
			this.setSuccess(false);
			this.setException(true);
		}
		return SUCCESS;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setFileContentType(String[] fileContentType) {
		this.fileContentType = fileContentType;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}

	public void setFileInfoList(List<FileInfo> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}

}
