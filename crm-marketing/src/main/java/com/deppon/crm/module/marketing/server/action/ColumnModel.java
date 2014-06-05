package com.deppon.crm.module.marketing.server.action;

/**
 * 
 * <p>
 * 列模型
 * </p>
 * @title ColumnModel.java
 * @package com.deppon.crm.module.marketing.server.action 
 * @author 苏玉军
 * @version 0.1 2013-1-28
 */
public class ColumnModel {
	//列头
	private String header;
	//数据对应索引
	private String dataIndex;
	//宽度
	private int width;
	//表格位置
	private String align;
	//添列类型
	private String xtype;
	/**
	 * @return header : return the property header.
	 */
	public String getHeader() {
		return header;
	}
	/**
	 * @param header : set the property header.
	 */
	public void setHeader(String header) {
		this.header = header;
	}
	/**
	 * @return dataIndex : return the property dataIndex.
	 */
	public String getDataIndex() {
		return dataIndex;
	}
	/**
	 * @param dataIndex : set the property dataIndex.
	 */
	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}
	/**
	 * @return width : return the property width.
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width : set the property width.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return align : return the property align.
	 */
	public String getAlign() {
		return align;
	}
	/**
	 * @param align : set the property align.
	 */
	public void setAlign(String align) {
		this.align = align;
	}
	/**
	 * @return xtype : return the property xtype.
	 */
	public String getXtype() {
		return xtype;
	}
	/**
	 * @param xtype : set the property xtype.
	 */
	public void setXtype(String xtype) {
		this.xtype = xtype;
	}
	
}
