package com.deppon.crm.module.complaint.server.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.complaint.server.dao.IBasciLevelDao;
import com.deppon.crm.module.complaint.shared.domain.BasciLevel;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelView;
import com.deppon.crm.module.complaint.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.complaint.shared.domain.BasicInfo;
import com.deppon.crm.module.complaint.shared.domain.BasicLevel;
import com.deppon.crm.module.complaint.shared.domain.BasicSearchCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class BasciLevelDaoImpl extends iBatis3DaoImpl implements IBasciLevelDao{
	
	private static final String NAMESPACE_BASICLEVEL = "com.deppon.crm.module.complaint.shared.domain.BasciLevel.";
	private static final String SEARCH_BASICLEVEL_BY_ID  =  "selectBasciLevel";
	private static final String GET_FATHER_BASIC_LEVEL="getFBasiciLevel";
	private static final String GET_BASIC_LEVEL_VIEW="getBasicLevelView";
	private static final String SAVE_BASIC_LEVEL="saveBasciLevel";
	private static final String DELETE_BASIC_LEVEL_BY_ID="deleteBasciLevelById";
	private static final String DELETE_BASIC_LEVEL_BY_PARENTID="deleteBasciLevelByParentId";
	private static final String GET_BASICLEVEL_BY_ID  =  "selectBasciLevelById";
	private static final String UPDATE_BASICLEVEL  =  "updateBasciLevel";
	//查询基础资料
	private static final String SEARCHBASICINFO = "searchBasicInfo";
	//查询基础资料总计
	private static final String SEARCHCOUNTBASICINFO = "searchCountBasicInfo";
	//新增业务项或业务范围
	private static final String SAVEBASICLEVEL = "saveBasicLevel";
	//根据ID更新业务项或业务范围
	private static final String UPDATEBASICLEVELBYID = "updateBasicLevelById";
	//根据parentID更新业务范围
	private static final String UPDATEBASICLEVELBYPARENTID = "updateBasicLevelByParentId";
	//删除业务项
	private static final String DELETEBUSITEM="deleteBusItem";
	//删除业务范围
	private static final String DELETEBUSSCOPE="deleteBusScope";
	//查询业务范围及业务类型
	private static final String SEARCHBASICBUSSCOPE = "searchBasicBusScopeVO";
	//根据上报类型查询业务项
	private static final String SEARCHBUSITEMBYTYPE = "searchBusItemByType";
	//根据业务项ID查询业务范围
	private static final String SEARCHBUSSCOPEBYBUSITEMID = "searchBusScopeByBusItemId";
	@Override
	/**
	 * 查询id下所有子BasciLevel
	 */
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
	public List<BasciLevel> searchBasciLevelById(BasciLevelSearchCondition condition) {
		return this.getSqlSession().selectList(NAMESPACE_BASICLEVEL+SEARCH_BASICLEVEL_BY_ID, condition);
	}
	/**
	 * 
	 * <p>
	 * Description:查询业务范围<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-24
	 * @return
	 * List<BasciLevel>
	 */
	@Override
	public List<BasciLevel> getFBasiciLevel(BasciLevelSearchCondition condition) {
		return this.getSqlSession().selectList(NAMESPACE_BASICLEVEL+GET_FATHER_BASIC_LEVEL,condition);
	}
	/**
	 * 
	 * <p>
	 * Description:查询基础资料层级关系<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-24
	 * @return
	 * List<BasciLevel>
	 */
	@Override
	public List<BasciLevelView> getFoundationDataList(
			BasciLevelSearchCondition condition) {
		return this.getSqlSession().selectList(NAMESPACE_BASICLEVEL+GET_BASIC_LEVEL_VIEW,condition);
	}
	/**
	 * 
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
	@Override
	public Integer saveBasciLevel(BasciLevel basciLevel) {
		// TODO Auto-generated method stub
		Integer id=this.getSqlSession().insert(NAMESPACE_BASICLEVEL+SAVE_BASIC_LEVEL,basciLevel);
		return Integer.parseInt(basciLevel.getFid()+"");
	}
	@Override
	public Integer deleteBasciLevelById(Integer fid) {
		// TODO Auto-generated method stub
		return this.getSqlSession().delete(NAMESPACE_BASICLEVEL+DELETE_BASIC_LEVEL_BY_ID, fid);
	}
	/**
	 * 
	 * <p>
	 * Description:通过业务类型查询，父子关系<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-28
	 * @param basciLevelId
	 * @return
	 * BasciLevel
	 */
	@Override
	public BasciLevelView getBasciLevelById(BigDecimal basciLevelId) {
		// TODO Auto-generated method stub
		Map<String, BigDecimal> map=new HashMap<String, BigDecimal>();
		map.put("basciLevelId", basciLevelId);
		return (BasciLevelView)this.getSqlSession().selectOne(NAMESPACE_BASICLEVEL+GET_BASICLEVEL_BY_ID,map);
	}
	/**
	 * 
	 * <p>
	 * Description:修该<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-5-11
	 * @param basciLevelId
	 * void
	 */
	@Override
	public void updateBasciLevel(BasciLevel basciLevel) {
		// TODO Auto-generated method stub
		this.getSqlSession().update(NAMESPACE_BASICLEVEL+UPDATE_BASICLEVEL,basciLevel);
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.complaint.server.dao.IBasciLevelDao#deleteBasciLevelByIds(java.util.List)
	 */
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
	@Override
	public boolean deleteBasciLevelByParentId(Integer id) {
		this.getSqlSession().update(NAMESPACE_BASICLEVEL+DELETE_BASIC_LEVEL_BY_PARENTID, id);
		return true;
	}
	/**
	 * 根据id删除业务类型
	 * @param id
	 * @return
	 */
	@Override
	public void deleteBasicTypeById(Integer id) {
		this.getSqlSession().update(NAMESPACE_BASICLEVEL+"deleteBasicTypeById",id);
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
	@Override
	public List<BasicInfo> searchBasicInfo(BasicSearchCondition bsc,RowBounds rb){
		return (List<BasicInfo>)this.getSqlSession().selectList(NAMESPACE_BASICLEVEL+SEARCHBASICINFO,bsc,rb);
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
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_BASICLEVEL+SEARCHCOUNTBASICINFO,bsc);
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
		return this.getSqlSession().insert(NAMESPACE_BASICLEVEL+SAVEBASICLEVEL,basicLevel);
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
		return this.getSqlSession().update(NAMESPACE_BASICLEVEL+UPDATEBASICLEVELBYID,basicLevel);
	}
	/**
	 * <p>
	 * Description: 新基础资料--根据parentId修改业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicLevel
	 * @param int
	 */
	@Override
	public int updateBasicLevelByParentId(BasicLevel basicLevel){
		return this.getSqlSession().update(NAMESPACE_BASICLEVEL+UPDATEBASICLEVELBYPARENTID,basicLevel);
	}
	/**
	 * <p>
	 * Description: 新基础资料--删除业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param ids
	 * @param int
	 */
	@Override
	public int deleteBusItemByIds(List<String> ids){
		return this.getSqlSession().update(NAMESPACE_BASICLEVEL+DELETEBUSITEM,ids);
	}
	/**
	 * <p>
	 * Description: 新基础资料--删除业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicLevel
	 * @param int
	 */
	@Override
	public int deleteBusScopeById(String id){
		return this.getSqlSession().update(NAMESPACE_BASICLEVEL+DELETEBUSSCOPE,id);
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
		return (List<BasicBusScopeVO>)this.getSqlSession().selectList(NAMESPACE_BASICLEVEL+SEARCHBASICBUSSCOPE,bbs);
	}
	/**
	 * <p>
	 * Description: 新基础资料--根据上报类型查询业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param reportType
	 */
	@Override
	public List<BasicInfo> searchBusItemByType(String reportType){
		return (List<BasicInfo>)this.getSqlSession().selectList(NAMESPACE_BASICLEVEL+SEARCHBUSITEMBYTYPE,reportType);
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
		return (List<BasicInfo>)this.getSqlSession().selectList(NAMESPACE_BASICLEVEL+SEARCHBUSSCOPEBYBUSITEMID, busItemId);
	}
}
