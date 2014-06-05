package com.deppon.crm.module.marketing.shared.domain.activity;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class UpdateFile extends BaseEntity {
	//文件名
	private String fileName;
	//文件路径
	private String filePath;
	//上传人
	private String updateUser;
	//上传时间
	private Date updateTime;
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
