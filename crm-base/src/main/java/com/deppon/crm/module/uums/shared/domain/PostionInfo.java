package com.deppon.crm.module.uums.shared.domain;


import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
/**
 * 
 * @description 接口传输参数职位�?
 * @author zzw
 * @version 0.1 2013-11-25
 */
public class PostionInfo extends BaseUUEntity implements Serializable{
	private static final long serialVersionUID = 7577476879310265909L;
		private String positionName;
	    private Date lastModifyTime;
		public String getPositionName() {
			return positionName;
		}
		public void setPositionName(String positionName) {
			this.positionName = positionName;
		}
		public Date getLastModifyTime() {
			return lastModifyTime;
		}
		public void setLastModifyTime(Date lastModifyTime) {
			this.lastModifyTime = lastModifyTime;
		}	
}
