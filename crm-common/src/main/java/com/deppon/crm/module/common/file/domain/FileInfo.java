package com.deppon.crm.module.common.file.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class FileInfo extends BaseEntity {

	// 来源ID
	private String sourceId;

	// 来源类型
	private String sourceType;

	// 文件名称
	private String fileName;

	// 文件类型
	private String fileType;

	// 保存路径
	private String savePath;

	// 文件大小
	private Double fileSize;

	// 创建部门
	private String createDept;

	public FileInfo() {

	}

	/**
	 * constructer
	 * 
	 * @param sourceId
	 * @param sourceType
	 * @param fileName
	 * @param fileType
	 * @param savePath
	 * @param fileSize
	 */
	public FileInfo(String sourceId, String sourceType, String fileName,
			String fileType, String savePath, double fileSize) {
		this.sourceId = sourceId;
		this.sourceType = sourceType;
		this.fileName = fileName;
		this.fileType = fileType;
		this.savePath = savePath;
		this.fileSize = fileSize;
	}

	/**
	 * @return sourceId : return the property sourceId.
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId
	 *            : set the property sourceId.
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return sourceType : return the property sourceType.
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * @param sourceType
	 *            : set the property sourceType.
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @return fileName : return the property fileName.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            : set the property fileName.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return fileType : return the property fileType.
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType
	 *            : set the property fileType.
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return savePath : return the property savePath.
	 */
	public String getSavePath() {
		return savePath;
	}

	/**
	 * @param savePath
	 *            : set the property savePath.
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	/**
	 * @return fileSize : return the property fileSize.
	 */
	public Double getFileSize() {
		return fileSize;
	}

	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}


	public String getCreateDept() {
		return createDept;
	}

	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

}
