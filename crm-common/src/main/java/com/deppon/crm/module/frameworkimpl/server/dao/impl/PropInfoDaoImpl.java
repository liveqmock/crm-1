/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PropDaoImpl.java
 * @package com.deppon.crm.module.frameworkimpl.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */
package com.deppon.crm.module.frameworkimpl.server.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.deppon.crm.module.frameworkimpl.server.dao.IPropInfoDao;
import com.deppon.crm.module.frameworkimpl.shared.domain.PropInfo;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PropInfoDaoImpl.java
 * @package com.deppon.crm.module.frameworkimpl.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */

public class PropInfoDaoImpl implements IPropInfoDao {
    // 读取prop信息
    private static final String queryall_sql = "select fkey key, fvalue value from t_crm_properties";
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.frameworkimpl.server.dao.IPropDao#getPropInfo()
	 */
	@Override
	public List<PropInfo> getPropInfo() {
		List<PropInfo> list = new ArrayList<PropInfo>();
		Connection con = null;
		Statement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.createStatement();

			ResultSet rs=ps.executeQuery(queryall_sql);
			while(rs.next()){ 
				PropInfo pi = new PropInfo();
				pi.setKey(rs.getString("key"));
				pi.setValue(rs.getString("value"));
				list.add(pi);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
				}
		}
		return list;
//		return getSqlSession().selectList(PROP_MAPER+QUERYALL);
	}

}
