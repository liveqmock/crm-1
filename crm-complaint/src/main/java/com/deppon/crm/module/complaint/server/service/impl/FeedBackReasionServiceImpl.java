package com.deppon.crm.module.complaint.server.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.server.dao.IFeedBackReasionDao;
import com.deppon.crm.module.complaint.server.service.IFeedBackReasionService;
import com.deppon.crm.module.complaint.shared.domain.BasciLevel;
import com.deppon.crm.module.complaint.shared.domain.Bulletin;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.FeedBackReasion;
/**
 * 
 * <p>
 * Description:退回原因<br />
 * </p>
 * @title IComplaintService.java
 * @package com.deppon.crm.module.complaint.server.service 
 * @author ouy
 * @version 0.1 2012-4-12
 */
public class FeedBackReasionServiceImpl implements IFeedBackReasionService {
	private IFeedBackReasionDao feedBackReasionDao;
	/**
	 * 
	 * <p>
	 * Description:通过compid查退回原因<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-17
	 * @return
	 * List<Complaint>
	 */
	@Override
	public List<FeedBackReasion> searchFeedBackReasionByCompId(BigDecimal compId) {
		// TODO Auto-generated method stub
		return feedBackReasionDao.searchFeedBackReasionByCompId(compId);
	}
	public IFeedBackReasionDao getFeedBackReasionDao() {
		return feedBackReasionDao;
	}
	public void setFeedBackReasionDao(IFeedBackReasionDao feedBackReasionDao) {
		this.feedBackReasionDao = feedBackReasionDao;
	}
	/**
	 * 
	 * @description 反馈退回记录DAO新增接口.
	 * @author Lee
	 * @version 0.1 2012-3-13
	 * @param 反馈退回记录对象
	 * @date 2012-4-20
	 * @return 反馈退回记录对象
	 * @update 2012-4-20 上午9:11:57
	 */
	@Override
	public FeedBackReasion saveFeedBackReasion(FeedBackReasion feedBackReasion) {
		// TODO Auto-generated method stub
		return feedBackReasionDao.saveFeedBackReasion(feedBackReasion);
	}

	/**
	 * <p>
	 * Description: 更新反馈记录<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-7-3
	 * @param feedBackReasion
	 * @return
	 * boolean
	 */
	@Override
	public boolean updateFeedBackReasion(FeedBackReasion feedBackReasion) {
		// 更新反馈退回表
		// 2012-7-3 by ZhuPJ 目前仅更新“记录内容”字段（fwailbillcontent） 
		return feedBackReasionDao.updateFeedBackReasion(feedBackReasion);
	}
}
