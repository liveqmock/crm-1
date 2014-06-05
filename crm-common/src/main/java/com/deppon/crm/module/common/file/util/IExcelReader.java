package com.deppon.crm.module.common.file.util;

public interface IExcelReader {

	public boolean hasNextRow();

	public Object[] getNextRow();

	public String[] getHeader();

	/**
	 * <p>
	 * Description:获取总行数<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-7-31
	 * @return
	 * int
	 */
	int getTotalRows();

	/**
	 * <p>
	 * Description:获取总列数<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-7-31
	 * @return
	 * int
	 */
	int getTotalCells();

}
