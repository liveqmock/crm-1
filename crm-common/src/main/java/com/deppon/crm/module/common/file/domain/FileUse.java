package com.deppon.crm.module.common.file.domain;

/**
 * <p>上传文件用途</p>
 * @author Administrator
 * @version 1.0
 * */
public enum FileUse {

	TEST1("D:/File/01","测试","导入客户"), 
	TEST2("D:/File/02","测试2","导入会员"),
	EXPORTEXCEL("D:/File/03","导出EXCEL文件","EXCEL文件");
	
	/**存放文件路径*/
	private String filePath;

	/**文件名*/
	private String fileName;
	
	/**文件用途*/
	private String fileUse;

	FileUse(String filePath, String fileName ,String fileUse) {
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileUse=fileUse;
	}

	public String filePath() {
		return filePath;
	}

	public String fileName() {
		return fileName;
	}

	
	public String  fileUse(){
		return fileUse;
	}
}
