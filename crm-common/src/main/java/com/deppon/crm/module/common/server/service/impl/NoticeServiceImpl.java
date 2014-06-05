package com.deppon.crm.module.common.server.service.impl;

import java.util.List;

import org.springframework.dao.support.DaoSupport;

import com.deppon.crm.module.common.server.dao.INoticeDao;
import com.deppon.crm.module.common.server.service.INoticeService;
import com.deppon.crm.module.common.shared.domain.Notice;
import com.deppon.crm.module.common.shared.domain.NoticeSearchCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * Description: NoticeServiceImpl.java Create on 2012-10-9 上午11:05:44
 * 
 * @author xhl
 * @version 1.0
 */
public class NoticeServiceImpl extends iBatis3DaoImpl implements INoticeService {
	private INoticeDao noticeDao;

	public INoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	// 新增公告
	@Override
	public void addNotice(Notice notice) {
		noticeDao.addNotice(notice);
	}

	// 通过Id更新公告

	@Override
	public void updateNoticeById(Notice notice) {
		noticeDao.updateNoticeById(notice);
	}

	// 通过id删除公告

	public void deleteNoticeById(String noticeId) {
		noticeDao.deleteNoticeById(noticeId);
	}

	// 根据查询条件更新公告

	@Override
	public List<Notice> searchNoticeByCondition(
			NoticeSearchCondition noticeSearchCondition) {
		return noticeDao.searchNoticeByCondition(noticeSearchCondition);
	}

	// 根据id获取单条公告

	@Override
	public Notice findNoticeById(String noticeId) {
		return noticeDao.findNoticeById(noticeId);
	}

	// 根据Idlist更新相关数据

	@Override
	public void updateNoticesByIdList(List<String> ids) {
		noticeDao.updateNoticeByIdList(ids);
	}
	//根据Idlist删除相关数据
	@Override
	public void delteNoticesByIdList(List<String> ids) {
		noticeDao.deleteNoticesByIdList(ids);
	}

	@Override
	public int getCountByCondition(NoticeSearchCondition noticeSearchCondition) {
		int count=noticeDao.getCountByCondition(noticeSearchCondition);
		return count;
	}
	//更新访问人数
	@Override
	public void updateNoticeVisitsById(String noticeId) {
		noticeDao.updateNoticeVisits(noticeId);
	}

	/**
	 * 根据Id置顶公告
	 */
	@Override
	public void topNoticeByIds(String noticeId) {
		noticeDao.topNoticeByIds(noticeId);
	}

	/**
	 * <p>
	 * Description:查询12天内最新的一条公告<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-3-29下午4:49:44
	 * @update 2013-3-29下午4:49:44
	 */
	@Override
	public Notice searchIndexNewNotice() {
		return noticeDao.searchIndexNewNotice();
	}
	

}
