package com.deppon.crm.module.duty.server.manager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.duty.shared.domain.BasicLevel;
import com.deppon.crm.module.duty.shared.domain.ProcResult;
import com.deppon.crm.module.duty.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.duty.shared.domain.BasicBusType;
import com.deppon.crm.module.duty.shared.domain.BasicInfo;
import com.deppon.crm.module.duty.shared.domain.BasicSearchCondition;
 
/**
 * 
 * <p>
 * Description:工单责任-基础类型 Manager<br />
 * </p>
 * @title IBasalTypeManager.java
 * @package com.deppon.crm.module.duty.server.manager 
 * @author 侯斌飞
 * @version 0.1 2013-2-26
 */
public interface IBasalTypeManager {
	/**
	 * <p>
	 * Description: 新基础资料--查询基础资料数据<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-22
	 * @param bsc
	 * @param Map
	 */
	public Map<String,Object> searchBasicInfo(BasicSearchCondition bsc,int start,int limit);
	/**
	 * <p>
	 * Description: 新基础资料--查询出业务范围及对应的业务类型用于修改基础资料页面<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param bbs
	 */
	public List<BasicBusScopeVO> searchBasicBusScopeVO(BasicBusScopeVO bbs);
	/**
	 * <p>
	 * Description: 新基础资料--新增或更新业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicItems
	 * @param user
	 */
	public boolean operateBusItem(List<BasicInfo> basicItems,User user);
	/**
	 * <p>
	 * Description: 新基础资料--业务项修改,业务范围新增与修改,业务类型新增修改删除<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicBusScope
	 * @param user
	 */
	public boolean operateBasicBusInfo(BasicBusScopeVO basicBusScope,User user);
	/**
	 * <p>
	 * Description: 新基础资料--删除业务类型及业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param id
	 */
	public boolean deleteBusTypeByIds(List<BasicInfo> basicInfos);
	/**
	 * <p>
	 * Description: 新基础资料--删除业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param id
	 */
	public boolean deleteBusItemByIds(List<BasicInfo> basicInfos);
	/**
	 * <p>
	 * Description: 新基础资料--删除业务类型在操作基础资料页面中使用<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param id
	 */
	public boolean deleteBusTypeByIdsInBusScopeVO(List<BasicBusType> busTypes);
	/**
	 * <p>
	 * Description: 新基础资料--根据上报类型查询业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param BasicBusScopeVO
	 * @param BasicBusScopeVO
	 */
	public List<BasicInfo> searchBusItemByType(String reportType);
	
	/**
	 * <p>
	 * Description: (只有有业务类型才能查出的业务项)新基础资料--根据上报类型查询业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-4-19
	 * @param reportType
	 */
	public List<BasicInfo> searchBusItemByTypeOnly(String reportType);
	/**
	 * <p>
	 * Description: 新基础资料--删除添加业务项页面用于显示已存在的业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param BasicBusScopeVO
	 * @param BasicBusScopeVO
	 */
	public Map<String,List<BasicInfo>> showBusItemByType();
	/**
	 * <p>
	 * Description: 新基础资料--根据业务项ID查询业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param busItemId
	 */
	public List<BasicInfo> searchBusScopeByBusItemId(String busItemId);
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
	 * Description: 新基础资料--根据ID修改业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-25
	 * @param basicInfo
	 * @return List<BasicBusType> 
	 */
	public boolean updateBusItemById(BasicInfo basicInfo,User user);
	
	/**
	 * <p>
	 * Description: 新基础资料--查询基础资料数据个数<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-06
	 * @param bsc
	 * @param rb
	 */
	public Integer countBasicInfoByCondition(BasicSearchCondition bsc);
	
	/**
	 * <p>
	 * Description: 新基础资料--查询出业务范围及对应的业务类型用于修改基础资料页面单个<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-06
	 * @param bsc
	 * @param rb
	 */
	public BasicBusScopeVO searchBasicBusScopeVOSimple(BasicBusScopeVO bbs);
	/**
	 * 
	 * <p>
	 * Description:根据业务范围ID查询基础资料<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-25
	 * @param basciLevelId
	 * @return
	 * List<ProcResult>
	 */
	public List<ProcResult> getProcResultByLevelId(BigDecimal basciLevelId);
	/**
	 * <p>
	 * Description: 查询业务范围<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-11
	 * @param busItemId
	 */
	List<BasicLevel> selectBusScope(BigDecimal parentid);
	/**
	 * <p>
	 * Description: 只查询有业务类型的业务范围<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-4-19
	 * @param busItemId
	 */
	List<BasicLevel> selectBusScopeOnly(BigDecimal parentid);
}
