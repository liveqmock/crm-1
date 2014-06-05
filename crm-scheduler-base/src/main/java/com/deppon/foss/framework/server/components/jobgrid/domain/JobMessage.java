package com.deppon.foss.framework.server.components.jobgrid.domain;

import java.io.Serializable;

public class JobMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7159683036559595589L;

	private String id;
	// 电子邮件
	private String email;
	// 手机号码
	private String mobile;
	// 邮件主题
	private String subject;
	// 手机号码
	private String content;
	// 是否发送
	private int send;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getSend() {
		return send;
	}

	public void setSend(int send) {
		this.send = send;
	}

}