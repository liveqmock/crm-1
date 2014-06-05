package com.deppon.crm.module.common.server.workflow;

import java.util.Map;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.opensymphony.module.propertyset.database.JDBCPropertySet;

public class JdbcPropertySet extends JDBCPropertySet{

    public void init(Map config, Map args) {
        // args
        globalKey = (String) args.get("globalKey");

        String driverName = (String) config.get("jdbc.driverClassName");
		String url = (String) config.get("jdbc.url");
		String username = (String) config.get("jdbc.username");
		String password = (String) config.get("jdbc.password");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		ds = dataSource;

        tableName = (String) config.get("table.name");
        colGlobalKey = (String) config.get("col.globalKey");
        colItemKey = (String) config.get("col.itemKey");
        colItemType = (String) config.get("col.itemType");
        colString = (String) config.get("col.string");
        colDate = (String) config.get("col.date");
        colData = (String) config.get("col.data");
        colFloat = (String) config.get("col.float");
        colNumber = (String) config.get("col.number");
    }
}
