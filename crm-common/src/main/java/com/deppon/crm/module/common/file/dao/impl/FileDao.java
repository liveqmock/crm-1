package com.deppon.crm.module.common.file.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.common.file.dao.IFileDao;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 文件信息DAO
 * */
public class FileDao extends iBatis3DaoImpl implements IFileDao {

	private String NAMESPACE = "com.deppon.crm.module.common.file.domain.FileInfo.";

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.file.dao.IFileDao#saveFileInfo(com.deppon.crm.module.common.file.domain.FileInfo)
	 */
	@Override
	public int saveFileInfo(FileInfo fileInfo) {
		return getSqlSession().insert(NAMESPACE + "insertFileInfo", fileInfo);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.file.dao.IFileDao#updateFileInfo(com.deppon.crm.module.common.file.domain.FileInfo)
	 */
	@Override
	public int deleteFileInfo(String id) {
		return getSqlSession().delete(NAMESPACE + "deleteFileInfo", id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.file.dao.IFileDao#searchFileInfoByCondition(com.deppon.crm.module.common.file.domain.FileInfo, int, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<FileInfo> searchFileInfoByCondition(FileInfo fileInfo,
			int offset, int limit) {
		RowBounds bound = new RowBounds(offset, limit);
		return getSqlSession().selectList(
				NAMESPACE + "searchFileInfoByCondition", fileInfo, bound);
	}

}
