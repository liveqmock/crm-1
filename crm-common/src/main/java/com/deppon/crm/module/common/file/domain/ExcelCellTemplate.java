package com.deppon.crm.module.common.file.domain;

/**
 * <p>
 * 单元格配置
 * </p>
 * 
 * @author Administrator
 * @version 1.0
 * */
public class ExcelCellTemplate {

	/** 单元格名称 */
	private String name;

	/** 数据对象属性 */
	private String dataKey;

	/** 单元格位置 */
	private int index;

	/** 单元格样式 */
	private String cellStyle;

	/** 单元格数据格式 */
	private String dataFormat;

	public ExcelCellTemplate() {
	}

	public ExcelCellTemplate(String name, String dataKey, int index,
			String cellStyle, String dataFormat) {
		this.name = name;
		this.dataKey = dataKey;
		this.index = index;
		this.cellStyle = cellStyle;
		this.dataFormat = dataFormat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getCellStyle() {
		return cellStyle;
	}

	public void setCellStyle(String cellStyle) {
		this.cellStyle = cellStyle;
	}

	public String getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

}
