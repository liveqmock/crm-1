package com.deppon.crm.module.duty.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.duty.server.dao.IDutyDealProcessDao;
import com.deppon.crm.module.duty.shared.domain.DutyDealProcess;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class DutyDealProcessDao extends iBatis3DaoImpl implements IDutyDealProcessDao {
	
	private static final String NAMESPACE_DUTYDEALPROCESS = "com.deppon.crm.module.duty.shared.domain.DutyDealProcess.";

	//根据条件查询对象
	private static final String DUTYDEALPROCESS_SEARCHDUTYDEALPROCESS = "searchDutyDealProcess";
	//查询单个对象根据ID
	private static final String DUTYDEALPROCESS_SEARCHDUTYDEALPROCESS_FID = "selectDutyDealProcessByPrimaryKey";
	//修改对象
	private static final String DUTYDEALPROCESS_UPDATEDUTYDEALPROCESS = "updateDutyDealProcess";
	//插入通知对象
	private static final String DUTYDEALPROCESS_INSERT = "insertDutyDealProcess";
	//批量删除方案信息
	private static final String DUTYDEALPROCESS_DELETE = "deleteDutyDealProcesss";
	//批量删除根据责任ID 
	private static final String DUTYDEALPROCESS_DELETE_DUTYID="deleteDutyDealProcesssByDutyId";
	//查询最近的一条处理经过
	private static final String DUTYDEALPROCESS_SELECT_MAX="selectMaxDutyDealProcess";
	
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
		return (List<DutyDealProcess>)this.getSqlSession().selectList(NAMESPACE_DUTYDEALPROCESS+DUTYDEALPROCESS_SEARCHDUTYDEALPROCESS,dutyDealProcess);
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
		Map map = new HashMap();
		map.put("id", fid);
		return (DutyDealProcess)this.getSqlSession().selectOne(NAMESPACE_DUTYDEALPROCESS+DUTYDEALPROCESS_SEARCHDUTYDEALPROCESS_FID,map);
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
		this.getSqlSession().update(NAMESPACE_DUTYDEALPROCESS+DUTYDEALPROCESS_UPDATEDUTYDEALPROCESS,dutyDealProcess);
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
		Map map = new HashMap();
		map.put("ids", fids);
		this.getSqlSession().delete(NAMESPACE_DUTYDEALPROCESS+DUTYDEALPROCESS_DELETE, map);
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
		Map map = new HashMap();
		map.put("dutyId", dutyId);
		this.getSqlSession().delete(NAMESPACE_DUTYDEALPROCESS+DUTYDEALPROCESS_DELETE_DUTYID, map);
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
		this.getSqlSession().insert(NAMESPACE_DUTYDEALPROCESS+DUTYDEALPROCESS_INSERT, dutyDealProcess);
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
	public DutyDealProcess selectMaxDutyDealProcess(DutyDealProcess dutyDealProcess) {
		return (DutyDealProcess)this.getSqlSession().selectOne(NAMESPACE_DUTYDEALPROCESS+DUTYDEALPROCESS_SELECT_MAX, dutyDealProcess);
	}

}
