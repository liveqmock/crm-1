package com.deppon.crm.module.common.shared.domain;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * Description:
 * Notice.java Create on 2012-10-9 上午11:12:00 
 * @author xhl
 * @version 1.0
 */
public class Notice extends BaseEntity {
	//标题
	private String title;
	//类型
	private String type;
	//所属模块
	private String moduleBelong;
	//内容
	private String content;
	//访问次数
	private int visits;
	//是否发布
	private boolean active;
	//是否置顶
	private boolean top;
	//附件列表
	private List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
	//图片地址
	private String imageAddr;
	//图片描述
	private String imageDescribe;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModuleBelong() {
		return moduleBelong;
	}
	public void setModuleBelong(String moduleBelong) {
		this.moduleBelong = moduleBelong;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isTop() {
		return top;
	}
	public void setTop(boolean top) {
		this.top = top;
	}
	/**
	 *@return  fileInfoList;
	 */
	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}
	/**
	 * @param fileInfoList : set the property fileInfoList.
	 */
	public void setFileInfoList(List<FileInfo> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}
	/**
	 *@return  imageAddr;
	 */
	public String getImageAddr() {
		return imageAddr;
	}
	/**
	 * @param imageAddr : set the property imageAddr.
	 */
	public void setImageAddr(String imageAddr) {
		this.imageAddr = imageAddr;
	}
	/**
	 *@return  imageDescribe;
	 */
	public String getImageDescribe() {
		return imageDescribe;
	}
	/**
	 * @param imageDescribe : set the property imageDescribe.
	 */
	public void setImageDescribe(String imageDescribe) {
		this.imageDescribe = imageDescribe;
	}

	
}
