package com.deppon.crm.module.common.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.ServerParameter;

public interface IServerParameterDao {
	/**
	 * 
	 * <p>
	 * Description:得到系統配置数据的最后更新时间<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-6
	 * @return
	 * Date
	 */
	public Date getLastModifyTime();	
	/**
	 * 
	 * <p>
	 * Description:查询所有<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-6
	 * @return
	 * List<ServerParameter>
	 */
	public List<ServerParameter> getAllServerParameter();
	/**
	 * 
	 * <p>
	 * Description:根据key获得value<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-10
	 * @param key
	 * @return
	 * String
	 */
	public String getValueByKey(String key);

}
