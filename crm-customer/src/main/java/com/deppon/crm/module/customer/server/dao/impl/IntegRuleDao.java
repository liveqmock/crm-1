package com.deppon.crm.module.customer.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.customer.server.dao.IIntegRuleDao;
import com.deppon.crm.module.customer.server.utils.DataUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.integral.ChannelIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.IntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.ProductIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.WiringIntegRule;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * <p>
 * Description:积分规则dao<br />
 * </p>
 * 
 * @title IntegRuleDao.java
 * @package com.deppon.crm.module.customer.server.dao.impl
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class IntegRuleDao extends iBatis3DaoImpl implements IIntegRuleDao {
	// 命名空间
	private static final String NAME_SPACE = "com.deppon.crm.module.customer.shared.domain.integral.IntegRule.";
	// 插入积分规则
	private static final String INSERT_INTEGRULE = "insertIntegRule";
	// 更新积分规则
	private static final String UPDATE_INTEGRULE = "updateIntegRule";
	// 删除积分规则
	private static final String DELETE_INTEGRULE = "deleteIntegRule";

	/**
	 * 
	 * <p>
	 * Description:保存积分规则<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * 
	 */
	@Override
	public void saveChannelIntegRule(ChannelIntegRule integRule) {
		// 对象转换为map
		Map<String, Object> map = DataUtil.changeObjectToMap(integRule);
		// 增加属性pointtype
		map.put("pointtype", Constant.ChannelRule);
		this.getSqlSession().insert(NAME_SPACE + INSERT_INTEGRULE, map);
		integRule.setId((String) map.get("id"));
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * 
	 */
	@Override
	public void saveProductIntegRule(ProductIntegRule integRule) {
		Map<String, Object> map = DataUtil.changeObjectToMap(integRule);
		map.put("pointtype", Constant.ProductRule);
		this.getSqlSession().insert(NAME_SPACE + INSERT_INTEGRULE, map);
		integRule.setId((String) map.get("id"));
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * 
	 */
	@Override
	public void saveWiringIntegRule(WiringIntegRule integRule) {
		Map<String, Object> map = DataUtil.changeObjectToMap(integRule);
		map.put("pointtype", Constant.WiringRule);
		this.getSqlSession().insert(NAME_SPACE + INSERT_INTEGRULE, map);
		integRule.setId((String) map.get("id"));
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * 
	 */
	@Override
	public void updateChannelIntegRule(ChannelIntegRule integRule) {
		Map<String, Object> map = DataUtil.changeObjectToMap(integRule);
		this.getSqlSession().update(NAME_SPACE + UPDATE_INTEGRULE, map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * 
	 */
	@Override
	public void updateProductIntegRule(ProductIntegRule integRule) {
		Map<String, Object> map = DataUtil.changeObjectToMap(integRule);
		this.getSqlSession().update(NAME_SPACE + UPDATE_INTEGRULE, map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * 
	 */
	@Override
	public void updateWiringIntegRule(WiringIntegRule integRule) {
		Map<String, Object> map = DataUtil.changeObjectToMap(integRule);
		this.getSqlSession().update(NAME_SPACE + UPDATE_INTEGRULE, map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public ChannelIntegRule getChannelIntegRuleById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pointtype", Constant.ChannelRule);
		return (ChannelIntegRule) this.getSqlSession().selectOne(NAME_SPACE + "getChannelIntegRuleById", map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public ProductIntegRule getProductIntegRuleById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pointtype", Constant.ProductRule);
		return (ProductIntegRule) this.getSqlSession().selectOne(NAME_SPACE + "getProductIntegRuleById", map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public WiringIntegRule getWiringIntegRuleById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pointtype", Constant.WiringRule);
		return (WiringIntegRule) this.getSqlSession().selectOne(NAME_SPACE + "getWiringIntegRuleById", map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param start
	 * @param limit
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WiringIntegRule> getWiringIntegRules(int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pointtype", Constant.WiringRule);
		return this.getSqlSession().selectList(NAME_SPACE + "getWiringIntegRules", map, rowBounds);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param start
	 * @param limit
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductIntegRule> getProductIntegRules(int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pointtype", Constant.ProductRule);
		return this.getSqlSession().selectList(NAME_SPACE + "getProductIntegRules", map, rowBounds);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param start
	 * @param limit
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelIntegRule> getChannelIntegRules(int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pointtype", Constant.ChannelRule);
		return this.getSqlSession().selectList(NAME_SPACE + "getChannelIntegRules", map, rowBounds);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * 
	 */
	@Override
	public void deleteChannelIntegRule(String id) {
		this.getSqlSession().delete(NAME_SPACE + DELETE_INTEGRULE, id);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * 
	 */
	@Override
	public void deleteProductIntegRule(String id) {
		this.getSqlSession().delete(NAME_SPACE + DELETE_INTEGRULE, id);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * 
	 */
	@Override
	public void deleteWiringIntegRule(String id) {
		this.getSqlSession().delete(NAME_SPACE + DELETE_INTEGRULE, id);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * 
	 */
	@Override
	public long countGetWiringIntegRules() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pointtype", Constant.WiringRule);
		return (Long) this.getSqlSession().selectOne(NAME_SPACE + "countGetIntegRuleSql", map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * 
	 */
	@Override
	public long countGetProductIntegRules() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pointtype", Constant.ProductRule);
		return (Long) this.getSqlSession().selectOne(NAME_SPACE + "countGetIntegRuleSql", map);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * 
	 */
	@Override
	public long countGetChannelIntegRules() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pointtype", Constant.ChannelRule);
		return (Long) this.getSqlSession().selectOne(NAME_SPACE + "countGetIntegRuleSql", map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public String searchIntegRule(String id) {
		return (String) this.getSqlSession().selectOne(NAME_SPACE + "searchIntegRule", id);
	}

	/**
	 * 
	 * <p>
	 * Description:查询规则<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param rule
	 * @param type
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IntegRule> queryRuleByCondition(IntegRule rule, String type) {
		// 新建map
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置type
		map.put("pointType", type);
		// pointBegintime
		map.put("pointBegintime", rule.getPointbegintime());
		// pointEndtime
		map.put("pointEndtime", rule.getPointendtime());
		// 其他
		if (rule instanceof ChannelIntegRule) {
			map.put("channelType", ((ChannelIntegRule) rule).getChanneltype());
		} else if (rule instanceof WiringIntegRule) {
			map.put("leadept", ((WiringIntegRule) rule).getLeadept());
			map.put("arrdept", ((WiringIntegRule) rule).getArrdept());
		} else if (rule instanceof ProductIntegRule) {
			map.put("product", ((ProductIntegRule) rule).getProduct());
		}
		// 查询
		return this.getSqlSession().selectList(NAME_SPACE + "queryRule", map);
	}

}
