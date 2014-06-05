package com.deppon.crm.module.duty.server.dao;

import java.util.List;

import com.deppon.crm.module.duty.shared.domain.FeedAttach;
import com.deppon.crm.module.organization.shared.domain.Department;
 
/**
 * 
 * <p>
 * Description:附件信息 Dao<br />
 * </p>
 * @title IFeedAttachDao.java
 * @package com.deppon.crm.module.duty.server.dao 
 * @author zhangbin
 * @version 0.1 2013-3-27
 */
public interface IFeedAttachDao {
	/**
	 * <p>
	 * Description:根据条件查询附件信息<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2013-3-6
	 * @param feedAttach
	 * @return List<FeedAttach>
	 */
	public List<FeedAttach> searchFeedAttach(FeedAttach feedAttach);
	
	
	/**
	 * <p>
	 * Description:根据ID查询附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fid
	 * @return List
	 */
	public FeedAttach selectFeedAttachByPrimaryKey(String fid);
	
	/**
	 * <p>
	 * Description:修改附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param feedAttach
	 * @return void
	 */
	public void updateFeedAttach(FeedAttach feedAttach);
	
	/**
	 * <p>
	 * Description:批量删除附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fids
	 * @return void
	 */
	public void deleteFeedAttachs(List<String> fids);
	
	/**
	 * <p>
	 * Description:根据责任ID删除附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param feedId
	 * @return void
	 */
	public void deleteFeedAttachsByFeedId(String feedId);
	
	/**
	 * <p>
	 * Description:插入附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param feedAttach
	 * @return void
	 */
	public void insertFeedAttach(FeedAttach feedAttach);
	
	/**
	 * <p>
	 * Description:查询统计员部门<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-28
	 * @param feedAttach
	 * @return void
	 */
	public List<Department> searchStatisticiansDept();
}
