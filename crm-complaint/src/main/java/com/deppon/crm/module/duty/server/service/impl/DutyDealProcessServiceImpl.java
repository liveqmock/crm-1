package com.deppon.crm.module.duty.server.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.duty.server.dao.IDutyDealProcessDao;
import com.deppon.crm.module.duty.server.service.IDutyDealProcessService;
import com.deppon.crm.module.duty.shared.domain.DutyDealProcess;
@Transactional
public class DutyDealProcessServiceImpl implements IDutyDealProcessService {
	
	private IDutyDealProcessDao dutyDealProcessDao;
	/**
	 * @param dutyDealProcessDao the dutyDealProcessDao to set
	 */
	public void setDutyDealProcessDao(IDutyDealProcessDao dutyDealProcessDao) {
		this.dutyDealProcessDao = dutyDealProcessDao;
	}
	/**
	 * <p>
	 * Description:根据条件查询处理经过<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2013-3-18
	 * @param dutyDealProcess
	 * @return List<DutyDealProcess>
	 */
	@Override
	public List<DutyDealProcess> searchDutyDealProcess(DutyDealProcess dutyDealProcess) {
		return dutyDealProcessDao.searchDutyDealProcess(dutyDealProcess);
	}
	/**
	 * <p>
	 * Description:根据ID查询处理经过<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-18
	 * @param fid
	 * @return List
	 */
	@Override
	public DutyDealProcess selectDutyDealProcessByPrimaryKey(String fid) {
		return dutyDealProcessDao.selectDutyDealProcessByPrimaryKey(fid);
	}
	/**
	 * <p>
	 * Description:修改处理经过<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-18
	 * @param dutyDealProcess
	 * @return void
	 */
	@Override
	public void updateDutyDealProcess(DutyDealProcess dutyDealProcess) {
		dutyDealProcessDao.updateDutyDealProcess(dutyDealProcess);
	}
	/**
	 * <p>
	 * Description:批量删除处理经过<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-18
	 * @param fids
	 * @return void
	 */
	@Override
	public void deleteDutyDealProcesss(List<String> fids) {
		dutyDealProcessDao.deleteDutyDealProcesss(fids);
	}
	/**
	 * <p>
	 * Description:根据责任ID删除处理经过<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-18
	 * @param dutyId
	 * @return void
	 */
	@Override
	public void deleteDutyDealProcesssByDutyId(String dutyId) {
		dutyDealProcessDao.deleteDutyDealProcesssByDutyId(dutyId);
	}
	/**
	 * <p>
	 * Description:插入处理经过<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-18
	 * @param dutyDealProcess
	 * @return void
	 */
	@Override
	public void insertDutyDealProcess(DutyDealProcess dutyDealProcess) {
		dutyDealProcessDao.insertDutyDealProcess(dutyDealProcess);
	}
	/**
	 * <p>
	 * Description:查询最近的一条处理经过<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-20
	 * @param dutyDealProcess
	 * @return void
	 */
	@Override
	public DutyDealProcess selectMaxDutyDealProcess(
			DutyDealProcess dutyDealProcess) {
		return dutyDealProcessDao.selectMaxDutyDealProcess(dutyDealProcess);
	}

}
