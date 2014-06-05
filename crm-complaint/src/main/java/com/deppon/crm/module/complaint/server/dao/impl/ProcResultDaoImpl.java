package com.deppon.crm.module.complaint.server.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.server.dao.IProcResultDao;
import com.deppon.crm.module.complaint.shared.domain.BasicBusType;
import com.deppon.crm.module.complaint.shared.domain.BasicInfo;
import com.deppon.crm.module.complaint.shared.domain.ProcResult;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class ProcResultDaoImpl extends iBatis3DaoImpl implements IProcResultDao{
	private static final String NAMESPACE_PROCRESULT = "com.deppon.crm.module.complaint.shared.domain.ProcResult.";
	private static final String SAVE_PROCRESULT  =  "saveProcResult";
	private static final String DELETE_PROCRESULT_BY_ID ="deleteProcResultById";
	private static final String GET_PROCRESULT_BY_ID ="getProcResultById";
	private static final String GET_PROCRESULT_BY_LELEVL_ID ="getProResultByLevelId";
	private static final String UPDATE_PROCRESULT="updateProcResult";
	private static final String DELETE_PROCRESULT_BY_PARENTID="deleteProcResultByParentId";
	//新增业务类型
	private static final String SAVEBUSTYPE = "saveBusType";
	//修改业务类型
	private static final String UPDATEBUSTYPE = "updateBusType";
	//根据业务范围ID删除业务类型
	private static final String DELETEBUSTYPE= "deleteBusTypeByBusScope";
	//根据业务类型ID删除业务类型
	private static final String DELETEBUSTYPEBYID = "deleteBusTypeById";
	//根据业务范围ID查询业务类型
	private static final String SEARCHBUSTYPEBYBUSSCOPE = "searchBusTypeByBusScope";
	//根据业务项ID删除业务类型
	private static final String DELETEBUSTYPEBYBUSITEM="deleteBusTypeByBusItem";
	/**
	 * <p>
	 * Description:保存基础资料处理结果<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-24
	 * @param procResult
	 * @return
	 * Integer
	 */
	@Override
	public Integer saveProcResult(ProcResult procResult) {
		// TODO Auto-generated method stub
		Integer id=this.getSqlSession().insert(NAMESPACE_PROCRESULT+SAVE_PROCRESULT, procResult);
		return Integer.parseInt(procResult.getFid()+"");
	}
	/**
	 * 
	 * <p>
	 * Description:删除基础资料处理结果<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-24
	 * @param procResultId
	 * @return
	 * Integer
	 */
	public Integer deleteProcResultById(BigDecimal procResultId) {
		// TODO Auto-generated method stub
		return this.getSqlSession().delete(NAMESPACE_PROCRESULT+DELETE_PROCRESULT_BY_ID, procResultId);
	}
	/**
	 * 
	 * <p>
	 * Description:通过id查询<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-24
	 * @param procResultId
	 * @return
	 * Integer
	 */
	@Override
	public ProcResult getProcResultById(BigDecimal procResultId) {
		return (ProcResult)this.getSqlSession().selectOne(NAMESPACE_PROCRESULT+GET_PROCRESULT_BY_ID, procResultId);
	}
	/**
	 * 
	 * <p>
	 * Description:通过BasciLevelId 查ProcResult<br />
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
		return this.getSqlSession().selectList(NAMESPACE_PROCRESULT+GET_PROCRESULT_BY_LELEVL_ID, basciLevelId);
	}
	/**
	 * 
	 * <p>
	 * Description:通过BasciLevelId 查ProcResult<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-25
	 * @param basciLevelId
	 * @return
	 * List<ProcResult>
	 */
	@Override
	public Integer updateProcResult(ProcResult procResult) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update(NAMESPACE_PROCRESULT+UPDATE_PROCRESULT, procResult);
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
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.complaint.server.dao.IProcResultDao#deleteProcResultByParentId(java.util.List)
	 */
	@Override
	public boolean deleteProcResultByParentId(BigDecimal id) {
		this.getSqlSession().delete(NAMESPACE_PROCRESULT+DELETE_PROCRESULT_BY_PARENTID, id);
		return true;
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
		return this.getSqlSession().insert(NAMESPACE_PROCRESULT+SAVEBUSTYPE,busType);
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
		return this.getSqlSession().update(NAMESPACE_PROCRESULT+UPDATEBUSTYPE,busType);
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
		return this.getSqlSession().delete(NAMESPACE_PROCRESULT+DELETEBUSTYPE,busScopeIds);
	}
	/**
	 * <p>
	 * Description: 新基础资料根据业务类型ID删除业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param List<String>
	 * @return int
	 */
	@Override
	public int deleteBusTypeById(List<String> ids){
		return this.getSqlSession().delete(NAMESPACE_PROCRESULT+DELETEBUSTYPEBYID,ids);
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
		return (List<BasicInfo>)this.getSqlSession().selectList(NAMESPACE_PROCRESULT+SEARCHBUSTYPEBYBUSSCOPE,busScopeId);
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
		return this.getSqlSession().delete(NAMESPACE_PROCRESULT+DELETEBUSTYPEBYBUSITEM,busItemIds);
	}
}
