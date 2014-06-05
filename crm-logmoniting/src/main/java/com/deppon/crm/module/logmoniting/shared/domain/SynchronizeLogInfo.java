package com.deppon.crm.module.logmoniting.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 同步日志管理
 * @author Administrator
 *
 */
public class SynchronizeLogInfo extends BaseEntity{
	private static final long serialVersionUID = 1L;
		//表名TABLE_NAME
		private String tableName;
		//关键字KEY_WORD
		private String keyWord;
		//HANDLE_TYPE
		private String handleType;
		//状态 STATUS
		private String synStatus;
		//同步开始时间START_TIME
		private Date synStartTime;
		//同步结束时间FINISH_TIME
		private Date synFinishTime;
		//同步时间
		private Date synTime;
		public String getTableName() {
			return tableName;
		}
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		public String getKeyWord() {
			return keyWord;
		}
		public void setKeyWord(String keyWord) {
			this.keyWord = keyWord;
		}
		public String getHandleType() {
			return handleType;
		}
		public void setHandleType(String handleType) {
			this.handleType = handleType;
		}
		public String getSynStatus() {
			return synStatus;
		}
		public void setSynStatus(String synStatus) {
			this.synStatus = synStatus;
		}
		public Date getSynStartTime() {
			return synStartTime;
		}
		public void setSynStartTime(Date synStartTime) {
			this.synStartTime = synStartTime;
		}
		public Date getSynFinishTime() {
			return synFinishTime;
		}
		public void setSynFinishTime(Date synFinishTime) {
			this.synFinishTime = synFinishTime;
		}
		public Date getSynTime() {
			return synTime;
		}
		public void setSynTime(Date synTime) {
			this.synTime = synTime;
		}
		
}
