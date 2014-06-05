package com.deppon.crm.module.customer.shared.domain.base;
/**
 * 
 * <p>
 * Description:行業品名映射查詢條件<br />
 * </p>
 * @title GoodTradeMappingSearchCondition.java
 * @package com.deppon.crm.module.customer.shared.domain.base 
 * @author 106138 
 * @version 0.1 2014-3-18
 */
public class GoodTradeMappingSearchCondition {
	// 待映射品名
		private String articleName;
		// 一级行业编码
		private String firstTradeCode;
		// 二级行业编码
		private String secondTradeCode;
		// 关联状态
		private String associatedStatus;
		// 分页开始
		private int start;
		// 每页条数
		private int limit;
		/**
		 *@user pgj
		 *2014-4-11上午8:09:10
		 * @return articleName : return the property articleName.
		 */
		public String getArticleName() {
			return articleName;
		}
		/**
		 * @param articleName : set the property articleName.
		 */
		public void setArticleName(String articleName) {
			this.articleName = articleName;
		}
		/**
		 *@user pgj
		 *2014-4-11上午8:09:10
		 * @return firstTradeCode : return the property firstTradeCode.
		 */
		public String getFirstTradeCode() {
			return firstTradeCode;
		}
		/**
		 * @param firstTradeCode : set the property firstTradeCode.
		 */
		public void setFirstTradeCode(String firstTradeCode) {
			this.firstTradeCode = firstTradeCode;
		}
		/**
		 *@user pgj
		 *2014-4-11上午8:09:10
		 * @return secondTradeCode : return the property secondTradeCode.
		 */
		public String getSecondTradeCode() {
			return secondTradeCode;
		}
		/**
		 * @param secondTradeCode : set the property secondTradeCode.
		 */
		public void setSecondTradeCode(String secondTradeCode) {
			this.secondTradeCode = secondTradeCode;
		}
		/**
		 *@user pgj
		 *2014-4-11上午8:09:10
		 * @return associatedStatus : return the property associatedStatus.
		 */
		public String getAssociatedStatus() {
			return associatedStatus;
		}
		/**
		 * @param associatedStatus : set the property associatedStatus.
		 */
		public void setAssociatedStatus(String associatedStatus) {
			this.associatedStatus = associatedStatus;
		}
		/**
		 *@user pgj
		 *2014-4-11上午8:09:10
		 * @return start : return the property start.
		 */
		public int getStart() {
			return start;
		}
		/**
		 * @param start : set the property start.
		 */
		public void setStart(int start) {
			this.start = start;
		}
		/**
		 *@user pgj
		 *2014-4-11上午8:09:10
		 * @return limit : return the property limit.
		 */
		public int getLimit() {
			return limit;
		}
		/**
		 * @param limit : set the property limit.
		 */
		public void setLimit(int limit) {
			this.limit = limit;
		}
	
}
