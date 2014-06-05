package com.deppon.crm.module.common.server.dao;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.Notice;
import com.deppon.crm.module.common.shared.domain.NoticeSearchCondition;

/**
 * Description:公告操作dao
 * NoticeDao.java Create on 2012-10-9 上午11:00:13 
 * @author xhl
 * @version 1.0
 */
public interface INoticeDao {
	//新增公告
	public void addNotice(Notice notice);
	//通过Id更新公告
	public void updateNoticeById(Notice notice);
	//通过id删除公告
	public void deleteNoticeById(String noticeId);
	//根据查询条件更新公告
	public List<Notice> searchNoticeByCondition(NoticeSearchCondition noticeSearchCondition);
	//根据id获取单条公告
	public Notice findNoticeById(String noticeId);
	//根据Idlist更新相关数据
	public void updateNoticeByIdList(List<String> ids);
	//根据idList删除公告
	public void deleteNoticesByIdList(List<String> ids);
	//更新访问人数
	public void updateNoticeVisits(String noticeId);
	//计算记录数
	public int  getCountByCondition(NoticeSearchCondition noticeSearchCondition);
	//根据Id置顶公告
	public void topNoticeByIds(String noticeId);
	//查询12天内最新的一条公告
	public Notice searchIndexNewNotice();
}
