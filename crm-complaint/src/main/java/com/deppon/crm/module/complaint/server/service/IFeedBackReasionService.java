package com.deppon.crm.module.complaint.server.service;

import java.math.BigDecimal;
import java.util.List;

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
public interface IFeedBackReasionService {
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
	 List<FeedBackReasion> searchFeedBackReasionByCompId(BigDecimal compId);
	
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
	 FeedBackReasion saveFeedBackReasion(FeedBackReasion feedBackReasion);
	
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
	 boolean updateFeedBackReasion(FeedBackReasion feedBackReasion);
}
