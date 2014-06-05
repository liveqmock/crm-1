package com.deppon.crm.module.complaint.server.dao;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.shared.domain.BasicBusType;
import com.deppon.crm.module.complaint.shared.domain.BasicInfo;
import com.deppon.crm.module.complaint.shared.domain.ProcResult;

public interface IProcResultDao  {
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
	 Integer saveProcResult(ProcResult procResult);
	/**
	 * <p>
	 * Description:删除基础资料处理结果<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-24
	 * @param procResultId
	 * @return
	 * Integer
	 */
	 Integer deleteProcResultById(BigDecimal procResultId);
	/**
	 * <p>
	 * Description:通过id查询<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-24
	 * @param procResultId
	 * @return
	 * Integer
	 */
	 ProcResult getProcResultById(BigDecimal procResultId);
	/**
	 * <p>
	 * Description:通过BasciLevelId 查ProcResult<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-25
	 * @param basciLevelId
	 * @return
	 * List<ProcResult>
	 */
	 List<ProcResult> getProcResultByLevelId(BigDecimal basciLevelId);
	 Integer updateProcResult(ProcResult procResult);
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
	 boolean deleteProcResultByParentId(BigDecimal id );
	
	/**
	 * <p>
	 * Description: 新基础资料新增业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicBusType
	 * @return int
	 */
	public int saveBusType(BasicBusType busType);
	/**
	 * <p>
	 * Description: 新基础资料修改业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicBusType
	 * @return int
	 */
	public int updateBusType(BasicBusType busType);
	/**
	 * <p>
	 * Description: 新基础资料根据业务范围删除业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param busScopeIds
	 * @return int
	 */
	public int deleteBusTypeByBusScope(List<String> busScopeIds);
	/**
	 * <p>
	 * Description: 新基础资料根据业务类型ID删除业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param List<String>
	 * @return int
	 */
	public int deleteBusTypeById(List<String> ids);
	/**
	 * <p>
	 * Description: 新基础资料根据业务范围ID查询业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-25
	 * @param busScopeId
	 * @return List<BasicBusType> 
	 */
	public List<BasicInfo> searchBusTypeByBusScope(String busScopeId);
	/**
	 * <p>
	 * Description: 新基础资料根据业务项ID删除业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param List<String>
	 * @return int
	 */
	public int deleteBusTypeByBusItem(List<String> busItemIds);
}
