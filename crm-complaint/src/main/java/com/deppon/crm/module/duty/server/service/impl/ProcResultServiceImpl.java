package com.deppon.crm.module.duty.server.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.duty.server.dao.IProcResultDao;
import com.deppon.crm.module.duty.server.service.IProcResultService;
import com.deppon.crm.module.duty.shared.domain.BasicBusType;
import com.deppon.crm.module.duty.shared.domain.BasicInfo;
import com.deppon.crm.module.duty.shared.domain.ProcResult;
@Transactional
public class ProcResultServiceImpl implements IProcResultService{
	private IProcResultDao procResultDaoDuty;
	/**
	 * @param procResultDaoDuty the procResultDaoDuty to set
	 */
	public void setProcResultDaoDuty(IProcResultDao procResultDaoDuty) {
		this.procResultDaoDuty = procResultDaoDuty;
	}
	/**
	 * <p>
	 * Description:插入通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param informUser
	 * @return void
	 */
	@Override
	public Integer savaProcResult(ProcResult procResult) {
		return procResultDaoDuty.saveProcResult(procResult);
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
		return procResultDaoDuty.getProcResultByLevelId(basciLevelId);
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
		return procResultDaoDuty.deleteProcResultById(procResultId);
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
		return procResultDaoDuty.updateProcResult(procResult);
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
		return procResultDaoDuty.getProcResultById(procResultId);
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
			procResultDaoDuty.deleteProcResultByParentId(id);
		}
		return true;
	}
	
	public IProcResultDao getProcResultDao() {
		return procResultDaoDuty;
	}
	public void setProcResultDao(IProcResultDao procResultDaoDuty) {
		this.procResultDaoDuty = procResultDaoDuty;
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
		return this.procResultDaoDuty.saveBusType(busType);
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
		return this.procResultDaoDuty.updateBusType(busType);
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
		return this.procResultDaoDuty.deleteBusTypeByBusScope(busScopeIds);
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
		return this.procResultDaoDuty.deleteBusTypeById(ids);
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
		return this.procResultDaoDuty.searchBusTypeByBusScope(busScopeId);
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
		return this.procResultDaoDuty.deleteBusTypeByBusItem(busItemIds);
	}
}
