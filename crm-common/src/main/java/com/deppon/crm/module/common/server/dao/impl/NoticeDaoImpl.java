package com.deppon.crm.module.common.server.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.common.server.dao.INoticeDao;
import com.deppon.crm.module.common.shared.domain.Notice;
import com.deppon.crm.module.common.shared.domain.NoticeSearchCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * Description:公共dao实现类 NoticeDaoImpl.java Create on 2012-10-9 上午11:01:17
 * 
 * @author xhl
 * @version 1.0
 */
public class NoticeDaoImpl extends iBatis3DaoImpl implements INoticeDao {
	private final static String NAMESPACE = "com.deppon.crm.module.common.shared.domain.Notice.";

	// 新增公告
	@Override
	public void addNotice(Notice notice) {
		if (notice != null) {
			getSqlSession().insert(NAMESPACE+"addNotice", notice);

		}

	}

	// 通过Id更新公告

	@Override
	public void updateNoticeById(Notice notice) {
		if(notice!=null&&notice.getId()!=null&&!"".equals(notice.getId())){
			getSqlSession().update(NAMESPACE+"updateNoticeById", notice);
			
		}

	}

	// 通过id删除公告

	public void deleteNoticeById(String noticeId) {
			getSqlSession().delete(NAMESPACE+"deleteById", noticeId);
	}

	// 根据查询条件查询公告

	@Override
	public List<Notice> searchNoticeByCondition(
			NoticeSearchCondition noticeSearchCondition) {
		if (noticeSearchCondition.getLimit() == 0) {
			noticeSearchCondition.setLimit(Integer.MAX_VALUE);
		}
		RowBounds bound = new RowBounds(noticeSearchCondition.getStart(),
				noticeSearchCondition.getLimit());
		List<Notice> list = (List<Notice>) getSqlSession()
				.selectList(
						NAMESPACE+"searchNoticeByCondition",
						noticeSearchCondition, bound);

		if (list == null) {
			list = new ArrayList<Notice>();
		}
		System.out.println();
		return list;
	}

	// 根据id获取单条公告

	@Override
	public Notice findNoticeById(String noticeId) {
		if (noticeId != null && !"".equals(noticeId)) {
			return (Notice) getSqlSession()
					.selectOne(
							NAMESPACE+"searchNoticeById",
							noticeId);
		}
			return null;
	}

	// 根据Idlist更新相关数据

	@Override
	public void updateNoticeByIdList(List<String> ids) {
		getSqlSession().update(NAMESPACE+"updateNoticesIntoPublishByIds", ids);
		
	}

	@Override
	public void deleteNoticesByIdList(List<String> ids) {
		getSqlSession().delete(NAMESPACE+"deleteNoticesByIds", ids);
		
	}
	//更新访问人数
	public void updateNoticeVisits(String noticeId){
		getSqlSession().update(NAMESPACE+"updateNoticeVIsitsById", noticeId);
	}
	//计算记录数
	@Override
	public int getCountByCondition(NoticeSearchCondition noticeSearchCondition) {
		int count= (Integer) getSqlSession().selectOne(NAMESPACE+"getCountByCondition",noticeSearchCondition);
		return count;
	}
	
	/**
	 * 根据公告Id置顶公告
	 */
	@Override
	public void topNoticeByIds(String noticeId) {
		getSqlSession().update(NAMESPACE+"topNoticeByIds", noticeId);
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-3-29下午4:51:13
	 * @return
	 * @update 2013-3-29下午4:51:13
	 */
	@Override
	public Notice searchIndexNewNotice() {
		return (Notice) getSqlSession()
				.selectOne(NAMESPACE+"searchIndexNewNotice");
	}

	

}
