/**   
 * @title IFileManager.java
 * @package com.deppon.crm.module.common.file.manager
 * @description what to do
 * @author 安小虎
 * @update 2012-4-16 上午10:41:16
 * @version V1.0   
 */
package com.deppon.crm.module.common.file.manager;

import java.io.File;
import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;

/**
 * @description 文件管理
 * @author 安小虎
 * @version 0.1 2012-4-16
 * @date 2012-4-16
 */

public interface IFileManager {
	/**
	 * 
	 * @description 单文件上传.
	 * @author 安小虎
	 * @version 0.1 2012-5-4
	 * @param File
	 * @param 文件名称
	 * @param 文件类型
	 * @date 2012-5-4
	 * @return 文件集合
	 * @update 2012-5-4 上午10:00:00
	 */
	public FileInfo fileUpload(File file, String fileName, 
										String fileType,String type);
	/**
	 * @author zouming
	 * @描述 公告管理优化新增的附件上传方法
	 */
	public FileInfo fileUpload(File file, String fileName, String fileType,
			String type,String maxSizeLimit,
			String imageOrAttachment,String attachmentSize);
	/**
	 * 
	 * @description 多文件上传.
	 * @author 安小虎
	 * @version 0.1 2012-5-4
	 * @param File
	 * @param 文件名称
	 * @param 文件类型
	 * @date 2012-5-4
	 * @return 文件集合
	 * @update 2012-5-4 上午10:00:00
	 */
	public List<FileInfo> fileUpload(File[] file, String[] fileName,
			String[] fileType, String type);
	
	/**
	 * @author zouming
	 * @描述	公告管理新增附件的上传
	 */
	public List<FileInfo> fileUpload(File[] file, String[] fileName,
			String[] fileType, String type, String maxSizeLimit,
				String imageOrAttachment,String attachmentSize);
	
	/**
	 * 
	 * @description 单文件保存.
	 * @author 安小虎
	 * @version 0.1 2012-4-16
	 * @param 文件对象
	 * @date 2012-4-16
	 * @return boolean：成功与否
	 * @update 2012-4-16 上午10:22:15
	 */
	boolean saveFileInfo(FileInfo fileInfo);

	/**
	 * 
	 * @description 多文件保存.
	 * @author 安小虎
	 * @version 0.1 2012-4-16
	 * @param 文件对象
	 * @date 2012-4-16
	 * @return boolean：成功与否
	 * @update 2012-4-16 上午10:22:15
	 */
	boolean saveFileInfo(List<FileInfo> fileInfoList);

	/**
	 * 
	 * @description 根据ID删除文件（只删除数据库中的数据，不删除磁盘上的文件）.
	 * @author 安小虎
	 * @version 0.1 2012-4-16
	 * @param ID
	 * @date 2012-4-16
	 * @return boolean：成功与否
	 * @update 2012-4-16 上午10:22:15
	 */
	boolean deleteFileInfo(String id);

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
	boolean deleteFileInfo(FileInfo fileInfo);

	/**
	 * 
	 * @description 根据条件查询文件.
	 * @author 安小虎
	 * @version 0.1 2012-4-16
	 * @param 文件对象
	 *            （sourceId、sourceType必需，比如：想查询合同ID为1的这个合同的附件，sourceId就设为1，
	 *            sourceType设为HETONG(与新增时设置的值一致)）
	 * @param 起始记录位置
	 * @param 一页显示多少条记录
	 * @date 2012-4-16
	 * @return boolean：成功与否
	 * @update 2012-4-16 上午10:22:15
	 */
	List<FileInfo> searchFileInfoByCondition(FileInfo fileInfo, int offset,
			int limit);

}