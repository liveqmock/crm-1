package com.deppon.crm.module.gis.server.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.spatial.geometry.JGeometry;
import oracle.sql.STRUCT;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class CoordinatesTypeHandler implements TypeHandler {
    /**
     * 
     * <p>TODO(把空间数据库的内容转换成用","号隔开的经纬度坐标)</p> 
     * @author 073102-foss-Tommy Wu
     * @date 2013-4-22 上午08:59:54
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException 
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, java.lang.String)
     */
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		STRUCT eleStruct = (STRUCT) rs.getObject(columnName);
		if (eleStruct == null){
			return null;

		}else{
			JGeometry geometryShape = JGeometry.load(eleStruct);
			double[] doubleValues=geometryShape.getOrdinatesArray();
			StringBuilder sb=new StringBuilder();
			for(double doubleValue:doubleValues){
				sb.append(doubleValue+",");
			}
			
			String result = sb.toString();
			result = result.substring(0,result.length()-1);
			return result;
		}
	}

	public String getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		STRUCT eleStruct = (STRUCT) cs.getObject(columnIndex);
		if (eleStruct == null){
			return "";

		}else{
			JGeometry geometryShape = JGeometry.load(eleStruct);
			double[] doubleValues=geometryShape.getOrdinatesArray();
			StringBuilder sb=new StringBuilder();
			for(double doubleValue:doubleValues){
				sb.append(doubleValue);
			}
			return sb.toString();
		}
	}

	public void setParameter(PreparedStatement ps, int i, Object parameter,
			JdbcType jdbcType) throws SQLException {
			ps.setString(i,(String)parameter);
	}
	
	

}
