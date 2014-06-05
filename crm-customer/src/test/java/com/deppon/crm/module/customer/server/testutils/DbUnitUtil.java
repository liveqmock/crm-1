package com.deppon.crm.module.customer.server.testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;

import com.deppon.crm.module.customer.server.util.SpringTestHelper;
/**
 * 
 * <p>
 * dao层数据库单元测试类<br />
 * 该类主要应用于dao的测试，对于叫单元测试，还是叫集成测试，作者也很纠结----就是一个连接数据库的测试吧
 * 设计思路:
 * 1.此类启动时会对制定要测试的表进行数据备份。
 * 2.此类每一个setUp,shutDown都会只保留dbUnit的数据准备文件里面的数据
 * 3.此类执行完之后会对数据备份的文件进行还原。
 * 主要限制:
 * 1.测试数据库与生产数据库在一起，对于出现并发的会，可能会导致测试失败，或者其他莫名其妙的错误。这个是不可避免的，
 * 当做在测试的时候把表锁住不为一个好办法，以后在考虑
 * 2.此测试类依赖于开源框架dbUnit，他的api还需要学习，不过有一些简单的封装。
 * </p>
 * @title DaoTestCase.java
 * @package com.deppon.crm.module.customer.server.testutils 
 * @author bxj
 * @version 0.2 2012-4-20
 */
public class DbUnitUtil {
	//TODO 数据备份
	public static void backUpsData(String tableName){
		try {
			if(connection == null){
				init();
			}
			QueryDataSet backupDataSet = new QueryDataSet(connection);
			backupDataSet.addTable(tableName);
			File file=File.createTempFile(tableName+"_back",".xml");//备份文件
			FlatXmlDataSet.write(backupDataSet,new FileOutputStream(file));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	//TODO 数据还原
	public static void restoreData(File backFile){
		initDate(backFile);
	}
	
	private static IDatabaseConnection connection;
	
	private static void init() throws SQLException{
		DataSource dataSource = SpringTestHelper.getBean(DataSource.class);
		connection = new DatabaseDataSourceConnection(dataSource);
	}
	
	public static void initDate(File pre){
		try {
			if(connection == null){
				init();
			}
			IDataSet dataSet = new XmlDataSet(new FileInputStream(pre));
			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
           
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void assertUpdate(File exp,String tableName){
		assertTableWithFilter(exp,tableName,new String[]{"flastupdatetime"});
	}
	/*
	 * 不知道日期类型会出现什么效果
	 */
	public static void assertInsert(File exp,String tableName){
		assertTableWithFilter(exp,tableName,new String[]{"FID","fcreatetime"});
	}
	
	private static void assertTableWithFilter(File exp,String tableName,String... filterColumn){
		try {
			if(connection == null){
				init();
			}
			IDataSet expectedDataSet = new XmlDataSet(new FileInputStream(exp));
			ITable expectedTable = expectedDataSet.getTable(tableName);
			IDataSet databaseDataSet = connection.createDataSet();
			ITable actualTable = databaseDataSet.getTable(tableName);
			ITable filterExpectedTable =DefaultColumnFilter.excludedColumnsTable(expectedTable,filterColumn);
			ITable filterActualTable =DefaultColumnFilter.excludedColumnsTable(actualTable,filterColumn);
			Assertion.assertEquals(filterExpectedTable, filterActualTable); 
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
