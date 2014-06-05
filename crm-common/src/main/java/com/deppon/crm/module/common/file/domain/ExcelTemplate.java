package com.deppon.crm.module.common.file.domain;

import java.util.ArrayList;
import java.util.List;


/**
 * <code>EXCEL模板</code>
 * @author Administrator
 * @version 1.0
 * */
public class ExcelTemplate {

	/**名称*/
	private String name="";
	
	/**key*/
	private String key="";
	
	/**行对象*/
	private String clazz;
	/**开始行数*/
	private int beginRow=0;
	
	/**数据开始行*/
	private int dataBeginRow=1;
	
	/**单元格配置*/
	private List<ExcelCellTemplate> ectList = new ArrayList<ExcelCellTemplate>();

	public ExcelTemplate() {
	}

	/**
	 * constructer
	 * 
	 * @param name
	 * @param key
	 * @param clazz
	 * @param beginRow
	 * @param dataBeginRow
	 * @param attrList
	 */
	public ExcelTemplate(String name, String key, String clazz, int beginRow,
			int dataBeginRow, List<ExcelCellTemplate> ectList) {
		this.name = name;
		this.key = key;
		this.clazz = clazz;
		this.beginRow = beginRow;
		this.dataBeginRow = dataBeginRow;
		this.ectList = ectList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public int getBeginRow() {
		return beginRow;
	}

	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}

	public int getDataBeginRow() {
		return dataBeginRow;
	}

	public void setDataBeginRow(int dataBeginRow) {
		this.dataBeginRow = dataBeginRow;
	}

	public List<ExcelCellTemplate> getEctList() {
		return ectList;
	}

	public void setEctList(List<ExcelCellTemplate> ectList) {
		this.ectList = ectList;
	}

}
