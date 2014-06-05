package com.deppon.crm.module.common.server.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.common.server.manager.INoticeManager;
import com.deppon.crm.module.common.server.service.INoticeService;
import com.deppon.crm.module.common.shared.domain.Notice;
import com.deppon.crm.module.common.shared.domain.NoticeSearchCondition;
import com.deppon.crm.module.common.shared.exception.NoticeException;
import com.deppon.crm.module.common.shared.exception.NoticeExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * Description: 
 * NoticeManagerImpl.java Create on 2012-10-9 上午11:03:31
 * @author xhl
 * @version 1.0
 */
public class NoticeManagerImpl  implements INoticeManager {
	private INoticeService noticeService;
	private IFileManager fileManager;

	// 新增公告
	public INoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	/**
	 *@return  fileManager;
	 */
	public IFileManager getFileManager() {
		return fileManager;
	}

	/**
	 * @param fileManager : set the property fileManager.
	 */
	public void setFileManager(IFileManager fileManager) {
		this.fileManager = fileManager;
	}

	@SuppressWarnings("serial")
	@Override
	public void addNotice(Notice notice,User u) {
		boolean noticeValidate = NoticeValidate.validate(notice);
		if (noticeValidate) {
			notice.setVisits(0);
			notice.setCreateUser(u.getEmpCode().getEmpName());
			notice.setModifyUser(u.getEmpCode().getEmpName());
			notice.setCreateDate(new Date());
			notice.setModifyDate(new Date());
			noticeService.addNotice(notice);
			
			List<FileInfo> fileInfoList = notice.getFileInfoList();
			for (FileInfo fileInfo : fileInfoList) {
				fileInfo.setCreateUser(u.getEmpCode().getId());
				fileInfo.setCreateDate(new Date());
				fileInfo.setSourceId(notice.getId());
				fileInfo.setSourceType("notice");
				fileManager.saveFileInfo(fileInfo);
			}
		} else {
			NoticeException he = new NoticeException(
					NoticeExceptionType.NOTICE_NULLDATA_EXCEPTION);
			throw new GeneralException(he.getErrorCode(), he.getMessage(), he,
					new Object[] {}) {
			};

		}

	}

	// 通过Id更新公告

	@SuppressWarnings({ "serial", "unused" })
	@Override
	public void updateNoticeById(Notice notice,User u) {
		if (notice != null) {

			//notice.setCreateUser(u.getEmpCode().getEmpName());
			notice.setModifyUser(u.getEmpCode().getEmpName());
			//notice.setCreateDate(new Date());
			notice.setModifyDate(new Date());
			noticeService.updateNoticeById(notice);
			
			List<FileInfo> fileInfoList = notice.getFileInfoList();
			for (FileInfo fileInfo : fileInfoList) {
				fileInfo.setCreateUser(u.getEmpCode().getId());
				fileInfo.setCreateDate(new Date());
				fileInfo.setSourceId(notice.getId());
				fileInfo.setSourceType("notice");
				fileManager.saveFileInfo(fileInfo);
			}
		} else {
			NoticeException he = new NoticeException(
					NoticeExceptionType.NOTICE_NULLDATA_EXCEPTION);
			throw new GeneralException(he.getErrorCode(), he.getMessage(), he,
					new Object[] {}) {
			};

		}

	}

	// 通过id删除公告

	@Override
	public void deleteNoticeById(String noticeid) {
		noticeService.deleteNoticeById(noticeid);
	}

	// 根据查询条件更新公告

	@Override
	public List<Notice> searchNoticeByCondition(
			NoticeSearchCondition noticeSearchCondition) {
		//noticeSearchCondition=new NoticeSearchCondition();
		System.out.println(noticeSearchCondition.toString());
		return noticeService.searchNoticeByCondition(noticeSearchCondition);
	}

	// 根据id获取单条公告

	@Override
	public Notice findNoticeById(String noticeId) {
		Notice notice = noticeService.findNoticeById(noticeId);
		FileInfo fileInfo = new FileInfo();
		fileInfo.setSourceId(noticeId);
		fileInfo.setSourceType("notice");
		List<FileInfo> fileInfoList = fileManager
				.searchFileInfoByCondition(fileInfo, 0, Integer.MAX_VALUE);
		notice.setFileInfoList(fileInfoList);
		return notice;
	}

	// 根据Idlist更新相关数据
	@Override
	public void updateNoticesByIdList(String[] ids) {
	
	List<String> idlist=Arrays.asList(ids);
		noticeService.updateNoticesByIdList(idlist);
	}

	// 根据idList删除公告
	@Override
	public void delteNoticesByIdList(String[] ids) {
		List<String> idlist=Arrays.asList(ids);

		noticeService.delteNoticesByIdList(idlist);

	}

	@Override
	public int getCountByCondition(NoticeSearchCondition noticeSearchCondition) {
		int count=noticeService.getCountByCondition(noticeSearchCondition);
		return count;
	}
	
	
	//更新访问人数
	@Override
	public void updateNoticeVisitsById(String noticeId) {
		noticeService.updateNoticeVisitsById(noticeId);
	}

	/**
	 * 根据Id置顶公告
	 */
	@Override
	public void topNoticeByIds(String noticeId) {
		noticeService.topNoticeByIds(noticeId);
	}

	/**
	 * <p>
	 * Description:查询12天内最新的一条公告<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-3-29下午4:48:53
	 * @update 2013-3-29下午4:48:53
	 */
	@Override
	public Notice searchIndexNewNotice() {
		return noticeService.searchIndexNewNotice();
	}


}
