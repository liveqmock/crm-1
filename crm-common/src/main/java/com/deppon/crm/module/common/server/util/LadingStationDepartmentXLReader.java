package com.deppon.crm.module.common.server.util;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.file.util.ExcelReader;
import com.deppon.crm.module.common.file.util.IExcelReader;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;


/**
 * 受理部门维护的导入功能--读取文件
 * @author 邢悦
 *
 */
public class LadingStationDepartmentXLReader {
	
	private File file;
	
	//列名和列号的对应map
	private Map<String ,Integer> columnNumberNameMap;
	
	private static final String COLUMN_NAME_ORDER_FROM="订单来源";
	
	private static final String COLUMN_NAME_CAN_RECEIVE_CARGO="是否接货";
	
	private static final String COLUMN_NAME_RECEIVE_DEPT="订单始发部门";

	private static final String COLUMN_NAME_RECEIVE_DEPT_CODE="始发网点标杆编码";	
	
	private static final String COLUMN_NAME_ACCEPT_DEPT="订单受理部门";		

	private static final String COLUMN_NAME_ACCEPT_DEPT_CODE="受理部门标杆编码";		
	
	public LadingStationDepartmentXLReader(File file){
		this.file=file;
		columnNumberNameMap=new HashMap<String,Integer>();
	}
	
	
	/**
	 * 分析传入的XL
	 * @return
	 * @throws Exception
	 */
	public List<LadingStationDeptReaderPojo> analyze() {
		
		IExcelReader reader = new ExcelReader(file);
		
		//解析首行
		analysisHeader(reader);
		
		//读取内容
		return readData(reader);
		
	}
	
	
	
	
	/**
	 * 分析列名
	 * @param reader
	 */
	private void analysisHeader(IExcelReader reader){
		String[] header = reader.getHeader();
			for (int i = 0; i < header.length; i++) {
				String colName = header[i];
				colName=colName.trim();
				if (COLUMN_NAME_ORDER_FROM.equals(colName)) {
					columnNumberNameMap.put(COLUMN_NAME_ORDER_FROM, i);
					continue;
				} 
				
				if (COLUMN_NAME_CAN_RECEIVE_CARGO.equals(colName)) {
					columnNumberNameMap.put(COLUMN_NAME_CAN_RECEIVE_CARGO, i);
					continue;
				} 
				
				if (COLUMN_NAME_RECEIVE_DEPT.equals(colName)) {
					columnNumberNameMap.put(COLUMN_NAME_RECEIVE_DEPT, i);
					continue;
				}
				
				if (COLUMN_NAME_RECEIVE_DEPT_CODE.equals(colName)) {
					columnNumberNameMap.put(COLUMN_NAME_RECEIVE_DEPT_CODE, i);
					continue;
				}				
	
				if (COLUMN_NAME_ACCEPT_DEPT.equals(colName)) {
					columnNumberNameMap.put(COLUMN_NAME_ACCEPT_DEPT, i);
					continue;
				}				
		
				if (COLUMN_NAME_ACCEPT_DEPT_CODE.equals(colName)) {
					columnNumberNameMap.put(COLUMN_NAME_ACCEPT_DEPT_CODE, i);
					continue;
				}						
			}				
	}

	/**
	 * 解析表中的所有行
	 * @param reader
	 * @return
	 */
	private List<LadingStationDeptReaderPojo> readData(IExcelReader reader){
		List<LadingStationDeptReaderPojo> returnList=new ArrayList<LadingStationDeptReaderPojo>();
		Integer count=2;
		while(reader.hasNextRow()){
			Object[] row = reader.getNextRow();
			LadingStationDeptReaderPojo pojo=analyzeRow(row);
			pojo.setLineNo(count);
			if(!returnList.contains(pojo)){
				returnList.add(pojo);
			}
			count++;
		}

		return returnList;
	}
	
	/**
	 * 根据列名查找出行中对应项
	 * @param columnName
	 * @param row
	 * @return
	 */
	private String getRowDataByColumnName(String columnName,Object[] row){
		Integer i=columnNumberNameMap.get(columnName);
		Object obj=row[i];
		if(obj!=null){
			return obj.toString().trim();
		}else{
			return null;
		}
		 
	}
	
	/**
	 * 分析行，并且包装成实体类
	 * @param row
	 * @return
	 */
	private LadingStationDeptReaderPojo analyzeRow(Object[] row){
		String acceptDeptCode=this.getRowDataByColumnName(COLUMN_NAME_ACCEPT_DEPT_CODE, row);
		String canReceiveCargo=this.getRowDataByColumnName(COLUMN_NAME_CAN_RECEIVE_CARGO, row);
		String orderFrom=this.getRowDataByColumnName(COLUMN_NAME_ORDER_FROM, row);
		String receiveDeptCode=this.getRowDataByColumnName(COLUMN_NAME_RECEIVE_DEPT_CODE, row);
		
		LadingstationDepartment ld=new LadingstationDepartment();
		ld.setAcceptDeptStandCode(acceptDeptCode);
		ld.setBeginDeptStandardCode(receiveDeptCode);
		ld.setResource(orderFrom);
		ld.setIfReceive(canReceiveCargo);
		
		LadingStationDeptReaderPojo pojo=new LadingStationDeptReaderPojo(ld);
		return pojo;
	}
	
	
}
