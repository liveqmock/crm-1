package com.deppon.crm.module.complaint.server.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.server.dao.IProcResultDao;
import com.deppon.crm.module.complaint.server.service.IProcResultService;
import com.deppon.crm.module.complaint.shared.domain.BasicBusType;
import com.deppon.crm.module.complaint.shared.domain.BasicInfo;
import com.deppon.crm.module.complaint.shared.domain.ProcResult;
public class ProcResultServiceImpl implements IProcResultService{
	private IProcResultDao procResultDao;
	/**
	 * 
	 * <p>
	 * Description:保存<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-25
	 * @param procResult
	 * @return
	 * Integer
	 */
	@Override
	public Integer savaProcResult(ProcResult procResult) {
		// TODO Auto-generated method stub
		return procResultDao.saveProcResult(procResult);
	}
	/**
	 * 
	 * <p>
	 * Description:根据id查询<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-25
	 * @param basciLevelId
	 * @return
	 * List<ProcResult>
	 */
	@Override
	public List<ProcResult> getProcResultByLevelId(BigDecimal basciLevelId) {
		// TODO Auto-generated method stub
		return procResultDao.getProcResultByLevelId(basciLevelId);
	}
	/**
	 * 
	 * <p>
	 * Description:删除<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-25
	 * @param basciLevelId
	 * @return
	 * Integer
	 */
	@Override
	public Integer deleteProcResultById(BigDecimal procResultId) {
		// TODO Auto-generated method stub
		return procResultDao.deleteProcResultById(procResultId);
	}
	/**
	 * 修改ProcResult
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-25
	 * @param procResult
	 * @return
	 * Integer
	 */
	@Override
	public Integer updateProcResult(ProcResult procResult) {
		// TODO Auto-generated method stub
		return procResultDao.updateProcResult(procResult);
	}
	/**
	 * 
	 * <p>
	 * Description:根据id查ProcResult<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-26
	 * @param procResultId
	 * @return
	 * ProcResult
	 */
	@Override
	public ProcResult getProcResultById(BigDecimal procResultId) {
		// TODO Auto-generated method stub
		return procResultDao.getProcResultById(procResultId);
	}
	/**
	 * <p>
	 * Description: 根据业务范围ID（parentID）删除时限数据<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-7-6
	 * @param ids
	 * @return
	 * boolean
	 */
	@Override
	public boolean deleteProcResultByParentId(List<BigDecimal> ids) {
		for (BigDecimal id : ids){
			procResultDao.deleteProcResultByParentId(id);
		}
		return true;
	}
	
	public IProcResultDao getProcResultDao() {
		return procResultDao;
	}
	public void setProcResultDao(IProcResultDao procResultDao) {
		this.procResultDao = procResultDao;
	}
	/**
	 * <p>
	 * Description: 新基础资料新增业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicBusType
	 * @return int
	 */
	@Override
	public int saveBusType(BasicBusType busType){
		return this.procResultDao.saveBusType(busType);
	}
	/**
	 * <p>
	 * Description: 新基础资料修改业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicBusType
	 * @return int
	 */
	@Override
	public int updateBusType(BasicBusType busType){
		return this.procResultDao.updateBusType(busType);
	}
	/**
	 * <p>
	 * Description: 新基础资料根据业务范围删除业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicBusType
	 * @return int
	 */
	@Override
	public int deleteBusTypeByBusScope(List<String> busScopeIds){
		return this.procResultDao.deleteBusTypeByBusScope(busScopeIds);
	}
	/**
	 * <p>
	 * Description: 新基础资料根据业务类型ID删除业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param List<String> ids
	 * @return int
	 */
	@Override
	public int deleteBusTypeById(List<String> ids){
		return this.procResultDao.deleteBusTypeById(ids);
	}
	/**
	 * <p>
	 * Description: 新基础资料根据业务范围ID查询业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-25
	 * @param busScopeId
	 * @return List<BasicBusType> 
	 */
	@Override
	public List<BasicInfo> searchBusTypeByBusScope(String busScopeId){
		return this.procResultDao.searchBusTypeByBusScope(busScopeId);
	}
	/**
	 * <p>
	 * Description: 新基础资料根据业务项ID删除业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param List<String>
	 * @return int
	 */
	@Override
	public int deleteBusTypeByBusItem(List<String> busItemIds){
		return this.procResultDao.deleteBusTypeByBusItem(busItemIds);
	}
}
