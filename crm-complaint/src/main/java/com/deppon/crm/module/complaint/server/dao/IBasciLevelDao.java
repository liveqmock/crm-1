package com.deppon.crm.module.complaint.server.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

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
 * Description: 基础资料层级<br />
 * </p>
 * @title IBulletinDao.java
 * @package com.deppon.crm.module.complaint.server.dao 
 * @author ouy
 * @version 0.1 2012-4-17
 */
public interface IBasciLevelDao  {
	/**
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
	 * Description:查询业务范围<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-24
	 * @return
	 * List<BasciLevel>
	 */
	 List<BasciLevel> getFBasiciLevel(BasciLevelSearchCondition condition);
	/**
	 * <p>
	 * Description:查询基础资料层级关系<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-24
	 * @return
	 * List<BasciLevel>
	 */
	 List<BasciLevelView> getFoundationDataList(BasciLevelSearchCondition condition);
	/**
	 * <p>
	 * Description:保存基础资料层级<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-24
	 * @param procResult
	 * @param basciLevel
	 * @return
	 * Integer
	 */
	 Integer saveBasciLevel(BasciLevel basciLevel);
	 Integer deleteBasciLevelById(Integer basciLevelId);
	/**
	 * <p>
	 * Description:通过业务类型查询，父子关系<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-28
	 * @param basciLevelId
	 * @return
	 * BasciLevel
	 */
	 BasciLevelView getBasciLevelById(BigDecimal basciLevelId);
	/**
	 * <p>
	 * Description:修该<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-5-11
	 * @param basciLevelId
	 * void
	 */
	 void updateBasciLevel(BasciLevel basciLevel);
	/**
	 * <p>
	 * Description: 删除指定业务范围<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-7-6
	 * @param id
	 * @return
	 * boolean
	 */
	 boolean deleteBasciLevelByParentId(Integer id);
	/**
	 * 根据id删除业务类型
	 * @param id
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
	public List<BasicInfo> searchBasicInfo(BasicSearchCondition bsc,RowBounds rb);
	/**
	 * <p>
	 * Description: 新基础资料--查询基础资料总计<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-22
	 * @param bsc
	 * @param rb
	 */
	public int searchCountBasicInfo(BasicSearchCondition bsc);
	/**
	 * <p>
	 * Description: 新基础资料--新增业务项或业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicLevel
	 * @param int
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
	 * Description: 新基础资料--根据parentId修改业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicLevel
	 * @param int
	 */
	public int updateBasicLevelByParentId(BasicLevel basicLevel);
	/**
	 * <p>
	 * Description: 新基础资料--删除业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param List<String> ids
	 * @param int
	 */
	public int deleteBusItemByIds(List<String> ids);
	/**
	 * <p>
	 * Description: 新基础资料--删除业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicLevel
	 * @param int
	 */
	public int deleteBusScopeById(String id);
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
	 * Description: 新基础资料--根据上报类型查询业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param reportType
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
