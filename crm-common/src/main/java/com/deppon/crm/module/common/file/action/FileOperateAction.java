package com.deppon.crm.module.common.file.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 
 * @description 文件操作ACTION
 * @author 安小虎
 * @version 0.1 2012-5-14
 */
public class FileOperateAction extends AbstractAction {
	// 文件管理类
	@SuppressWarnings("unused")
	private IFileManager fileManager;

	public void setFileManager(IFileManager fileManager) {
		this.fileManager = fileManager;
	}


	private FileInfo fileInfo;

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	// 返回值
	private List<FileInfo> fileInfoList;

	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}

	// 分页
	private int start = 0;
	private int limit = 15;

	public void setStart(int start) {
		this.start = start;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * 
	 * @description 根据条件查询附件.
	 * @author 安小虎
	 * @version 0.1
	 * @param 文件对象
	 *            （sourceId、sourceType必需，比如：想查询合同ID为1的这个合同的附件，sourceId就设为1，
	 *            sourceType设为HETONG(与新增时设置的值一致)）
	 * @param 分页信息
	 *            ：start、limit
	 * @date 2012-5-14
	 * @return 文件对象集合
	 * @update 2012-5-14 上午8:25:35
	 */
	@JSON
	public String searchListByCondition() {
		fileInfoList = this.fileManager.searchFileInfoByCondition(fileInfo,
				start, limit);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 删除文件（删除数据库中的数据，同时删除磁盘上的文件）.
	 * @author 安小虎
	 * @version 0.1 2012-4-16
	 * @param ID
	 * @date 2012-4-16
	 * @return boolean：成功与否
	 * @update 2012-4-16 上午10:22:15
	 */
	@JSON
	public String deleteFileInfo() {
            fileManager.deleteFileInfo(fileInfo);
			return SUCCESS;
	}

}
