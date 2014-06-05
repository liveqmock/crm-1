package com.deppon.crm.module.common.server.workflow;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.opensymphony.user.provider.jdbc.JDBCProfileProvider;

public class JdbcProfileProvider extends JDBCProfileProvider{
	private static final Log log = LogFactory
			.getLog(JdbcProfileProvider.class);

	public boolean init(Properties props) {
		userTable = (String) props.get("user.table");
		groupTable = (String) props.get("group.table");
		membershipTable = (String) props.get("membership.table");
		userName = (String) props.get("user.name");
		userPassword = (String) props.get("user.password");
		groupName = (String) props.get("group.name");
		membershipUserName = (String) props.get("membership.userName");
		membershipGroupName = (String) props.get("membership.groupName");

		String driverName = (String) props.get("jdbc.driverClassName");
		String url = (String) props.get("jdbc.url");
		String username = (String) props.get("jdbc.username");
		String password = (String) props.get("jdbc.password");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		ds = dataSource;

		return true;
	}
}