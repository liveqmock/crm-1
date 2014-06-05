package com.deppon.crm.module.complaint.server.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.complaint.server.dao.IBasciLevelDao;
import com.deppon.crm.module.complaint.server.service.IBasciLevelService;
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
 * Description:通报对象<br />
 * </p>
 * @title IComplaintService.java
 * @package com.deppon.crm.module.complaint.server.service 
 * @author ouy
 * @version 0.1 2012-4-12
 */
public class BasciLevelServiceImpl implements IBasciLevelService{
	private IBasciLevelDao bascileveDao;
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
	@Override
	public List<BasciLevel> searchBasciLevelById(BasciLevelSearchCondition condition) {
		return bascileveDao.searchBasciLevelById(condition);
	}
	public IBasciLevelDao getBascileveDao() {
		return bascileveDao;
	}
	public void setBascileveDao(IBasciLevelDao bascileveDao) {
		this.bascileveDao = bascileveDao;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-23
	 * @return
	 * List<BasciLevel>
	 */
	@Override
	public List<BasciLevel> searchFBasciLevel(BasciLevelSearchCondition condition) {
		// TODO Auto-generated method stub
		return bascileveDao.getFBasiciLevel(condition);
	}
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
	@Override
	public List<BasciLevelView> getFoundationDataList(
			BasciLevelSearchCondition condition) {
		// TODO Auto-generated method stub
		return this.getBascileveDao().getFoundationDataList(condition);
	}
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
	@Override
	public Integer saveBasciLevel(BasciLevel basciLevel) {
		// TODO Auto-generated method stub
		return this.getBascileveDao().saveBasciLevel(basciLevel);
	}
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
	@Override
	public BasciLevelView getBasciLevelById(BigDecimal basciLevelId) {
		// TODO Auto-generated method stub
		return bascileveDao.getBasciLevelById(basciLevelId);
	}
	@Override
	public void updateBasciLevel(BasciLevel basciLevel) {
		bascileveDao.updateBasciLevel(basciLevel);
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.complaint.server.service.IBasciLevelService#deleteBasciLevelByIds(java.util.List)
	 */
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
	@Override
	public boolean deleteBasciLevelById(Integer id) {
		bascileveDao.deleteBasciLevelByParentId(id);
		return true;
	}
	/**
	 * 根据id删除业务类型
	 * @param id
	 * @author justin.xu 
	 * @version 0.1 2012-7-10	 * 
	 * @return
	 */
	@Override
	public void deleteBasicTypeById(Integer id) {
		bascileveDao.deleteBasicTypeById(id);
	}
	/**
	 * <p>
	 * Description: 新基础资料--查询基础资料数据<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-22
	 * @param bsc
	 * @param rb
	 */
	public List<BasicInfo> searchBasicInfo(BasicSearchCondition bsc,int start,int limit){
		RowBounds rb = new RowBounds(start,limit);
		return bascileveDao.searchBasicInfo(bsc, rb);
	}
	/**
	 * <p>
	 * Description: 新基础资料--查询基础资料总计<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-22
	 * @param bsc
	 */
	@Override
	public int searchCountBasicInfo(BasicSearchCondition bsc){
		return bascileveDao.searchCountBasicInfo(bsc);
	}
	/**
	 * <p>
	 * Description: 新基础资料--新增业务项或业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicLevel
	 * @param rb
	 */
	@Override
	public int saveBasicLevel(BasicLevel basicLevel){
		return bascileveDao.saveBasicLevel(basicLevel);
	}
	/**
	 * <p>
	 * Description: 新基础资料--修改业务项或业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicLevel
	 * @param int
	 */
	@Override
	public int updateBasicLevelById(BasicLevel basicLevel){
		return bascileveDao.updateBasicLevelById(basicLevel);
	}
	/**
	 * <p>
	 * Description: 新基础资料--根据parentID修改业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicLevel
	 * @param int
	 */
	@Override
	public int updateBasicLevelByParentId(BasicLevel basicLevel){
		return bascileveDao.updateBasicLevelByParentId(basicLevel);
	}
	/**
	 * <p>
	 * Description: 新基础资料--查询出业务范围及对应的业务类型用于修改基础资料页面<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param BasicBusScopeVO
	 * @param BasicBusScopeVO
	 */
	@Override
	public List<BasicBusScopeVO> searchBasicBusScopeVO(BasicBusScopeVO bbs){
		return bascileveDao.searchBasicBusScopeVO(bbs);
	}
	/**
	 * <p>
	 * Description: 新基础资料--删除业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param List<String> 
	 * @param int
	 */
	@Override
	public int deleteBusItemByIds(List<String> ids){
		return bascileveDao.deleteBusItemByIds(ids);
	}
	/**
	 * <p>
	 * Description: 新基础资料--根据业务范围ID删除业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param List<String> 
	 * @param int
	 */
	@Override
	public int deleteBusScopeById(String id){
		return bascileveDao.deleteBusScopeById(id);
	}
	/**
	 * <p>
	 * Description: 新基础资料--根据上报类型查询业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param BasicBusScopeVO
	 * @param BasicBusScopeVO
	 */
	@Override
	public List<BasicInfo> searchBusItemByType(String reportType){
		return bascileveDao.searchBusItemByType(reportType);
	}
	/**
	 * <p>
	 * Description: 新基础资料--根据业务项ID查询业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param busItemId
	 */
	@Override
	public List<BasicInfo> searchBusScopeByBusItemId(String busItemId){
		return bascileveDao.searchBusScopeByBusItemId(busItemId);
	}
}
