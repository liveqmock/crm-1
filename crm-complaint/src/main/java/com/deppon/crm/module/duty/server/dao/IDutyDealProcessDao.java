package com.deppon.crm.module.duty.server.dao;

import java.util.List;

import com.deppon.crm.module.duty.shared.domain.DutyDealProcess;
 
/**
 * 
 * <p>
 * Description:处理经过 Dao<br />
 * </p>
 * @title IDutyDealProcessDao.java
 * @package com.deppon.crm.module.duty.server.dao 
 * @author zhangbin
 * @version 0.1 2013-3-18
 */
public interface IDutyDealProcessDao {
	/**
	 * <p>
	 * Description:根据条件查询处理经过<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2013-3-18
	 * @param dutyDealProcess
	 * @return List<DutyDealProcess>
	 */
	public List<DutyDealProcess> searchDutyDealProcess(DutyDealProcess dutyDealProcess);
	
	
	/**
	 * <p>
	 * Description:根据ID查询处理经过<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-18
	 * @param fid
	 * @return List
	 */
	public DutyDealProcess selectDutyDealProcessByPrimaryKey(String fid);
	
	/**
	 * <p>
	 * Description:修改处理经过<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-18
	 * @param dutyDealProcess
	 * @return void
	 */
	public void updateDutyDealProcess(DutyDealProcess dutyDealProcess);
	
	/**
	 * <p>
	 * Description:批量删除处理经过<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-18
	 * @param fids
	 * @return void
	 */
	public void deleteDutyDealProcesss(List<String> fids);
	
	/**
	 * <p>
	 * Description:根据责任ID删除处理经过<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-18
	 * @param dutyId
	 * @return void
	 */
	public void deleteDutyDealProcesssByDutyId(String dutyId);
	
	/**
	 * <p>
	 * Description:插入处理经过<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-18
	 * @param dutyDealProcess
	 * @return void
	 */
	public void insertDutyDealProcess(DutyDealProcess dutyDealProcess);

	/**
	 * <p>
	 * Description:查询最近的一条处理经过<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-20
	 * @param dutyDealProcess
	 * @return void
	 */
	DutyDealProcess selectMaxDutyDealProcess(DutyDealProcess dutyDealProcess);
	
	
}
