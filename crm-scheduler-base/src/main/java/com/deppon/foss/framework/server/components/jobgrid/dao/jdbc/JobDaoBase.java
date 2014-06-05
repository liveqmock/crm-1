package com.deppon.foss.framework.server.components.jobgrid.dao.jdbc;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.quartz.utils.DBConnectionManager;
/**
 * 
 * <p>
 * Description:JDBC 帮助类<br />
 * </p>
 * @title JobDaoBase.java
 * @package com.deppon.foss.framework.server.components.jobgrid.dao.jdbc 
 * @author 侯斌飞
 * @version 0.1 2013-2-25
 */
public abstract class JobDaoBase implements JDBCContants {
    protected String dataSourceName;

    public JobDaoBase(String dataSourceName) {
        super();
        this.dataSourceName = dataSourceName;
    }
    /**
     * 
     * <p>
     * Description:得到数据库连接<br />
     * </p>
     * @author 侯斌飞
     * @version 0.1 2013-2-25
     * @return
     * @throws SQLException
     * Connection
     */
    protected Connection getConnection() throws SQLException {
        return DBConnectionManager.getInstance().getConnection(dataSourceName);
    }
    /**
     * 
     * <p>
     * Description:关闭连接<br />
     * </p>
     * @author 侯斌飞
     * @version 0.1 2013-2-25
     * @param conn
     * void
     */
    protected void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            } catch (Throwable e) {
            }
        }
    }
    /**
     * 
     * <p>
     * Description:关闭结果集<br />
     * </p>
     * @author 侯斌飞
     * @version 0.1 2013-2-25
     * @param rs
     * void
     */
    protected void closeResultSet(ResultSet rs) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException ignore) {
            }
        }
    }
    /**
     * 
     * <p>
     * Description:close Statement<br />
     * </p>
     * @author 侯斌飞
     * @version 0.1 2013-2-25
     * @param statement
     * void
     */
    protected void closeStatement(Statement statement) {
        if (null != statement) {
            try {
                statement.close();
            } catch (SQLException ignore) {
            }
        }
    }
    /**
     * 
     * <p>
     * Description:PreparedStatement 设置 boolean<br />
     * </p>
     * @author 侯斌飞
     * @version 0.1 2013-2-25
     * @param ps
     * @param index
     * @param val
     * @throws SQLException
     * void
     */
    protected void setBoolean(PreparedStatement ps, int index, boolean val) throws SQLException {
        ps.setBoolean(index, val);
    }
    /**
     * 
     * <p>
     * Description:ResultSet 中 指定列名 获取 boolean 的值<br />
     * </p>
     * @author 侯斌飞
     * @version 0.1 2013-2-25
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     * boolean
     */
    protected boolean getBoolean(ResultSet rs, String columnName) throws SQLException {
        return rs.getBoolean(columnName);
    }
    /**
     * 
     * <p>
     * Description:ResultSet 中 指定列索引 获取 boolean 的值<br />
     * </p>
     * @author 侯斌飞
     * @version 0.1 2013-2-25
     * @param rs
     * @param columnIndex
     * @return
     * @throws SQLException
     * boolean
     */
    protected boolean getBoolean(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getBoolean(columnIndex);
    }
    /**
     * 
     * <p>
     * Description:PreparedStatement 设置 bytes 参数<br />
     * </p>
     * @author 侯斌飞
     * @version 0.1 2013-2-25
     * @param ps
     * @param index
     * @param baos
     * @throws SQLException
     * void
     */
    protected void setBytes(PreparedStatement ps, int index, ByteArrayOutputStream baos) throws SQLException {
        ps.setBytes(index, (baos == null) ? new byte[0] : baos.toByteArray());
    }

}
