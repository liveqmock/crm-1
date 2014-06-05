package com.deppon.crm.module.duty.server.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.duty.server.dao.IBasicLevelDao;
import com.deppon.crm.module.duty.server.service.IBasicLevelService;
import com.deppon.crm.module.duty.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.duty.shared.domain.BasicInfo;
import com.deppon.crm.module.duty.shared.domain.BasicLevel;
import com.deppon.crm.module.duty.shared.domain.BasicSearchCondition;
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
@Transactional
public class BasicLevelServiceImpl implements IBasicLevelService{
	private IBasicLevelDao basicLevelDaoDuty;	
	/**
	 * @param basicLevelDaoDuty the basicLevelDaoDuty to set
	 */
	public void setBasicLevelDaoDuty(IBasicLevelDao basicLevelDaoDuty) {
		this.basicLevelDaoDuty = basicLevelDaoDuty;
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
		return basicLevelDaoDuty.searchBasicInfo(bsc, rb);
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
		return basicLevelDaoDuty.searchCountBasicInfo(bsc);
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
		return basicLevelDaoDuty.saveBasicLevel(basicLevel);
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
		return basicLevelDaoDuty.updateBasicLevelById(basicLevel);
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
		return basicLevelDaoDuty.updateBasicLevelByParentId(basicLevel);
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
		return basicLevelDaoDuty.searchBasicBusScopeVO(bbs);
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
		return basicLevelDaoDuty.deleteBusItemByIds(ids);
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
		return basicLevelDaoDuty.deleteBusScopeById(id);
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
		return basicLevelDaoDuty.searchBusItemByType(reportType);
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
		return basicLevelDaoDuty.searchBusScopeByBusItemId(busItemId);
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
	public List<BasicLevel> selectBusScope(BigDecimal parentid) {
		return basicLevelDaoDuty.selectBusScope(parentid);
	}
	/**
	 * <p>
	 * Description:只查询有业务类型的业务范围<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-4-19
	 * @param busItemId
	 */
	@Override
	public List<BasicLevel> selectBusScopeOnly(BigDecimal parentid) {
		return basicLevelDaoDuty.selectBusScopeOnly(parentid);
	}
	/**
	 * <p>
	 * Description: (只有有业务类型才能查出的业务项)新基础资料--根据上报类型查询业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-4-19
	 * @param reportType
	 */
	@Override
	public List<BasicInfo> searchBusItemByTypeOnly(String reportType) {
		return basicLevelDaoDuty.searchBusItemByTypeOnly(reportType);
	}
}
