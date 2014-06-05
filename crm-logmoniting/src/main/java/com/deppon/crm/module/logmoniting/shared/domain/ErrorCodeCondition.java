package com.deppon.crm.module.logmoniting.shared.domain;

public class ErrorCodeCondition {

	//模块名
		private String moduleName;
		//异常编码
		private String errorCode;
		//异常信息
		private String exceptionInfo;
		//开始
		private int start;
		//每页数量
		private int limit;
		/**
		 * Description:moduleName<br />
		 * @author CoCo
		 * @version 0.1 2013-7-30
		 */
		public String getModuleName() {
			return moduleName;
		}
		/**
		 * Description:moduleName<br />
		 * @author CoCo
		 * @version 0.1 2013-7-30
		 */
		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}
		/**
		 * Description:errorCode<br />
		 * @author CoCo
		 * @version 0.1 2013-7-30
		 */
		public String getErrorCode() {
			return errorCode;
		}
		/**
		 * Description:errorCode<br />
		 * @author CoCo
		 * @version 0.1 2013-7-30
		 */
		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}
		/**
		 * Description:exceptionInfo<br />
		 * @author CoCo
		 * @version 0.1 2013-7-30
		 */
		public String getExceptionInfo() {
			return exceptionInfo;
		}
		/**
		 * Description:exceptionInfo<br />
		 * @author CoCo
		 * @version 0.1 2013-7-30
		 */
		public void setExceptionInfo(String exceptionInfo) {
			this.exceptionInfo = exceptionInfo;
		}
		/**
		 * Description:start<br />
		 * @author CoCo
		 * @version 0.1 2013-7-30
		 */
		public int getStart() {
			return start;
		}
		/**
		 * Description:start<br />
		 * @author CoCo
		 * @version 0.1 2013-7-30
		 */
		public void setStart(int start) {
			this.start = start;
		}
		/**
		 * Description:limit<br />
		 * @author CoCo
		 * @version 0.1 2013-7-30
		 */
		public int getLimit() {
			return limit;
		}
		/**
		 * Description:limit<br />
		 * @author CoCo
		 * @version 0.1 2013-7-30
		 */
		public void setLimit(int limit) {
			this.limit = limit;
		}
}
