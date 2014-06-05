package com.deppon.crm.module.sysmail.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.sysmail.server.dao.ISysMailSendDao;
import com.deppon.crm.module.sysmail.shared.domain.MailAccount;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * <p>
 * 邮件发到Dao实现<br />
 * </p>
 * @title ISysMailSendDao.java
 * @package com.deppon.crm.module.sysmail.server.dao 
 * @author suyujun
 * @version 0.1 2013-9-25
 */
public class SysMailSendDao extends iBatis3DaoImpl implements ISysMailSendDao {
	private static final String namespace = "com.deppon.crm.module.sysmail.shared.domain.MailAccount.";
	/**
	 * 
	 * <p>
	 * 根据分组编码查询账号<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-25
	 * @param groupCode
	 * @return
	 * List<MailAccount>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MailAccount> queryMailAccountByGroupCode(String groupCode) {
		return this.getSqlSession().selectList(namespace + "queryMailAccountByGroupCode", groupCode);
	}
	/**
	 * 
	 * <p>
	 * 保留发送记录<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-25
	 * @param toSend
	 * void
	 */
	@Override
	public void saveSendLog(List<String> toSend) {
		Map<String, String> map = new HashMap<String, String>();
		for(String address:toSend){
			map.put("address", address);
			this.getSqlSession().insert(namespace + "saveSendLog", map);
			map.clear();
		}
	}

}
