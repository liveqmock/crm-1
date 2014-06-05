/**   
 * @title IFileDao.java
 * @package com.deppon.crm.module.common.file.dao
 * @description what to do
 * @author 安小虎
 * @update 2012-4-16 上午10:10:53
 * @version V1.0   
 */
package com.deppon.crm.module.common.file.dao;

import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;

/**
 * @description fuction  
 * @author 安小虎
 * @version 0.1 2012-4-16
 *@date 2012-4-16
 */

public interface IFileDao {

	/**
	 * 
	 * @description 保存文件.
	 * @author 安小虎
	 * @version 0.1 2012-4-16
	 * @param 文件对象
	 * @date 2012-4-16
	 * @return int：大于0成功，否则失败
	 * @update 2012-4-16 上午10:12:31
	 */
	int saveFileInfo(FileInfo fileInfo);

	/**
	 * 
	 * @description 删除文件.
	 * @author 安小虎
	 * @version 0.1 2012-4-16
	 * @param ID
	 * @date 2012-4-16
	 * @return int：大于0成功，否则失败
	 * @update 2012-4-16 上午10:12:10
	 */
	int deleteFileInfo(String id);

	/**
	 * 
	 * @description 根据条件查询文件.
	 * @author 安小虎
	 * @version 0.1 2012-4-16
	 * @param 文件对象
	 * @param 起始记录位置
	 * @param 一页显示多少条记录
	 * @date 2012-4-16
	 * @return 文件集合
	 * @update 2012-4-16 上午10:14:02
	 */
	@SuppressWarnings("unchecked")
	List<FileInfo> searchFileInfoByCondition(FileInfo fileInfo, int offset,
			int limit);

}