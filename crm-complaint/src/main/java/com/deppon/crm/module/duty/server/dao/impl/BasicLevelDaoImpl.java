package com.deppon.crm.module.duty.server.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.duty.server.dao.IBasicLevelDao;
import com.deppon.crm.module.duty.server.util.DutyConstants;
import com.deppon.crm.module.duty.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.duty.shared.domain.BasicInfo;
import com.deppon.crm.module.duty.shared.domain.BasicLevel;
import com.deppon.crm.module.duty.shared.domain.BasicSearchCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class BasicLevelDaoImpl extends iBatis3DaoImpl implements IBasicLevelDao{
	
	private static final String NAMESPACE_BASICLEVEL = "com.deppon.crm.module.duty.shared.domain.BasicLevel.";

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
	//根据上报类型查询业务项
	private static final String SEARCHBUSITEMBYTYPEONLY = "searchBusItemByTypeOnly";
	//根据业务项ID查询业务范围
	private static final String SEARCHBUSSCOPEBYBUSITEMID = "searchBusScopeByBusItemId";
	//查询业务范围
	private static final String SELECTSCOPE = "selectBusScope";
	// 只查询有业务类型的业务范围
	private static final String SELECTSCOPEONLY = "selectBusScopeOnly";

	
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
		basicLevel.setMark(DutyConstants.DUTY);
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
	 * Description:(只有有业务类型才能查出的业务项) 新基础资料--根据上报类型查询业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-4-19
	 * @param reportType
	 */
	@Override
	public List<BasicInfo> searchBusItemByTypeOnly(String reportType){
		return (List<BasicInfo>)this.getSqlSession().selectList(NAMESPACE_BASICLEVEL+SEARCHBUSITEMBYTYPEONLY,reportType);
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
	
	/**
	 * <p>
	 * Description: 查询业务范围<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-11
	 * @param busItemId
	 */
	@Override
	public List<BasicLevel> selectBusScope(BigDecimal parentid){
		Map map = new HashMap();
		map.put("parentid", parentid);
		map.put("useYn", "2");
		return (List<BasicLevel>)this.getSqlSession().selectList(NAMESPACE_BASICLEVEL+SELECTSCOPE,map);
	}
	/**
	 * <p>
	 * Description: 只查询有业务类型的业务范围<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-4-19
	 * @param busItemId
	 */
	@Override
	public List<BasicLevel> selectBusScopeOnly(BigDecimal parentid) {
		Map map = new HashMap();
		map.put("parentid", parentid);
		map.put("useYn", "2");
		return (List<BasicLevel>)this.getSqlSession().selectList(NAMESPACE_BASICLEVEL+SELECTSCOPEONLY,map);
	}
	
}
