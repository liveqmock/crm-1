package com.deppon.crm.module.common.file.service.impl;

import java.util.List;

import com.deppon.crm.module.common.file.dao.IFileDao;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.service.IFileService;

public class FileService implements IFileService {

	private IFileDao fileDao;

	public IFileDao getFileDao() {
		return fileDao;
	}

	public void setFileDao(IFileDao fileDao) {
		this.fileDao = fileDao;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.file.service.IFileService#saveFileInfo(com.deppon.crm.module.common.file.domain.FileInfo)
	 */
	@Override
	public boolean saveFileInfo(FileInfo fileInfo) {
		int result = this.fileDao.saveFileInfo(fileInfo);
		return result > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.file.service.IFileService#updateFileInfo(com.deppon.crm.module.common.file.domain.FileInfo)
	 */
	@Override
	public boolean deleteFileInfo(String id) {
		int result = this.fileDao.deleteFileInfo(id);
		return result > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.file.service.IFileService#searchFileInfoByCondition(com.deppon.crm.module.common.file.domain.FileInfo, int, int)
	 */
	@Override
	public List<FileInfo> searchFileInfoByCondition(FileInfo fileInfo,
			int offset, int limit) {
		return this.fileDao.searchFileInfoByCondition(fileInfo, offset, limit);
	}
}
