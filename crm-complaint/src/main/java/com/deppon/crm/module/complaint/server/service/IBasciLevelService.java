package com.deppon.crm.module.complaint.server.service;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.shared.domain.BasciLevel;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelView;
import com.deppon.crm.module.complaint.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.complaint.shared.domain.BasicInfo;
import com.deppon.crm.module.complaint.shared.domain.BasicLevel;
import com.deppon.crm.module.complaint.shared.domain.BasicSearchCondition;
/**
 * 
 * <p>
 * Description:基础层级<br />
 * </p>
 * @title IComplaintService.java
 * @package com.deppon.crm.module.complaint.server.service 
 * @author ouy
 * @version 0.1 2012-4-12
 */
public interface IBasciLevelService {
	/**
	 * 
	 * <p>
	 * Description:通过id查基础资料层级<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-17
	 * @return
	 * List<Complaint>
	 */
	 List<BasciLevel> searchBasciLevelById(BasciLevelSearchCondition condition);
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-23
	 * @return
	 * List<BasciLevel>
	 */
	 List<BasciLevel> searchFBasciLevel(BasciLevelSearchCondition condition);
	/**
	 * 
	 * <p>
	 * Description:基础资料（ 层级）查询<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-24
	 * @param condition
	 * @return
	 * List<BasciLevelView>
	 */
	 List<BasciLevelView> getFoundationDataList(BasciLevelSearchCondition condition);
	/**
	 * 
	 * <p>
	 * Description:保存基本层级<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-24
	 * @param basciLevel
	 * @return
	 * Integer
	 */
	public Integer saveBasciLevel(BasciLevel basciLevel);
	/**
	 * 
	 * <p>
	 * Description:根据id查询<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-28
	 * @param basciLevel
	 * @return
	 * BasciLevel
	 */
	 BasciLevelView getBasciLevelById(BigDecimal basciLevelId);
	
	 void updateBasciLevel(BasciLevel basciLevel);
	
	/**
	 * <p>
	 * Description: 根据ID删除业务范围数据<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-7-6
	 * @param ids
	 * @return
	 * boolean
	 */
	 boolean deleteBasciLevelById(Integer id);
	
	/**
	 * 根据id删除业务类型
	 * @param id
	 * @author justin.xu 
	 * @version 0.1 2012-7-10	 * 
	 * @return
	 */
	 void deleteBasicTypeById(Integer id);
	/**
	 * <p>
	 * Description: 新基础资料--查询基础资料数据<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-22
	 * @param bsc
	 * @param rb
	 */
	public List<BasicInfo> searchBasicInfo(BasicSearchCondition bsc,int start,int limit);
	/**
	 * <p>
	 * Description: 新基础资料--查询基础资料总计<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-22
	 * @param bsc
	 */
	public int searchCountBasicInfo(BasicSearchCondition bsc);
	/**
	 * <p>
	 * Description: 新基础资料--新增业务项或业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicLevel
	 * @param rb
	 */
	public int saveBasicLevel(BasicLevel basicLevel);
	/**
	 * <p>
	 * Description: 新基础资料--根据ID修改业务项或业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicLevel
	 * @param int
	 */
	public int updateBasicLevelById(BasicLevel basicLevel);
	/**
	 * <p>
	 * Description: 新基础资料--根据parentID修改业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicLevel
	 * @param int
	 */
	public int updateBasicLevelByParentId(BasicLevel basicLevel);
	/**
	 * <p>
	 * Description: 新基础资料--查询出业务范围及对应的业务类型用于修改基础资料页面<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param BasicBusScopeVO
	 * @param BasicBusScopeVO
	 */
	public List<BasicBusScopeVO> searchBasicBusScopeVO(BasicBusScopeVO bbs);
	/**
	 * <p>
	 * Description: 新基础资料--删除业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param List<String> 
	 * @param int
	 */
	public int deleteBusItemByIds(List<String> ids);
	/**
	 * <p>
	 * Description: 新基础资料--根据业务范围ID删除业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param List<String> 
	 * @param int
	 */
	public int deleteBusScopeById(String id);
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
	 * Description: 新基础资料--根据业务项ID查询业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param busItemId
	 */
	public List<BasicInfo> searchBusScopeByBusItemId(String busItemId);
}
