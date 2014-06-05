package com.deppon.crm.module.duty.server.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.duty.server.dao.IFeedAttachDao;
import com.deppon.crm.module.duty.server.service.IFeedAttachService;
import com.deppon.crm.module.duty.shared.domain.FeedAttach;
import com.deppon.crm.module.organization.shared.domain.Department;
@Transactional
public class FeedAttachServiceImpl implements IFeedAttachService {
	
	private IFeedAttachDao feedAttachDao;
	/**
	 * @param feedAttachDao the feedAttachDao to set
	 */
	public void setFeedAttachDao(IFeedAttachDao feedAttachDao) {
		this.feedAttachDao = feedAttachDao;
	}
	/**
	 * <p>
	 * Description:根据条件查询附件信息<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2013-3-6
	 * @param feedAttach
	 * @return List<FeedAttach>
	 */
	@Override
	public List<FeedAttach> searchFeedAttach(FeedAttach feedAttach) {
		return feedAttachDao.searchFeedAttach(feedAttach);
	}
	/**
	 * <p>
	 * Description:根据ID查询附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fid
	 * @return List
	 */
	@Override
	public FeedAttach selectFeedAttachByPrimaryKey(String fid) {
		return feedAttachDao.selectFeedAttachByPrimaryKey(fid);
	}
	/**
	 * <p>
	 * Description:修改附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param feedAttach
	 * @return void
	 */
	@Override
	public void updateFeedAttach(FeedAttach feedAttach) {
		feedAttachDao.updateFeedAttach(feedAttach);
	}
	/**
	 * <p>
	 * Description:批量删除附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fids
	 * @return void
	 */
	@Override
	public void deleteFeedAttachs(List<String> fids) {
		feedAttachDao.deleteFeedAttachs(fids);
	}
	/**
	 * <p>
	 * Description:根据责任ID删除附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param feedId
	 * @return void
	 */
	@Override
	public void deleteFeedAttachsByFeedId(String feedId) {
		feedAttachDao.deleteFeedAttachsByFeedId(feedId);
	}
	/**
	 * <p>
	 * Description:批量删除附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fids
	 * @return void
	 */
	@Override
	public void insertFeedAttach(FeedAttach feedAttach) {
		feedAttachDao.insertFeedAttach(feedAttach);
	}
	/**
	 * <p>
	 * Description:查询统计员部门<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-28
	 * @param feedAttach
	 * @return void
	 */
	@Override
	public List<Department> searchStatisticiansDept() {
		return feedAttachDao.searchStatisticiansDept();
	}

}
