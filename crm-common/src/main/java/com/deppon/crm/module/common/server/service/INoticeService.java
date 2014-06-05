package com.deppon.crm.module.common.server.service;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.Notice;
import com.deppon.crm.module.common.shared.domain.NoticeSearchCondition;

/**
 * Description:
 * INoticeService.java Create on 2012-10-9 上午11:04:40 
 * @author xhl
 * @version 1.0
 */
public interface INoticeService {
	//新增公告
	public void addNotice(Notice notice);
	//通过Id更新公告
	public void updateNoticeById(Notice Notice);
	//通过id删除公告
	public void deleteNoticeById(String Noticeid);
	//根据查询条件更新公告
	public List<Notice> searchNoticeByCondition(NoticeSearchCondition noticeSearchCondition);
	//根据id获取单条公告
	public Notice findNoticeById(String noticeId);
	//根据Idlist更新相关数据
	public void updateNoticesByIdList(List<String> ids);
	//根据idList删除公告
	public void delteNoticesByIdList(List<String> ids);
	//根据条件查记录数
	public int getCountByCondition(NoticeSearchCondition noticeSearchCondition);
	//更新访问次数
	public void updateNoticeVisitsById(String noticeId);
	//根据Id置顶公告
	public void topNoticeByIds(String noticeId);
	//查询12天内最新的一条公告
	public Notice searchIndexNewNotice();
}
