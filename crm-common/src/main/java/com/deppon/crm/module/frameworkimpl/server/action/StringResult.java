package com.deppon.crm.module.frameworkimpl.server.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * <p>
 * Description:提供给安全控件信息输出<br />
 * </p>
 * @title StringResult.java
 * @package com.deppon.crm.module.common.server.action 
 * @author Weill
 * @version 0.1 2012-7-10
 */
public class StringResult extends StrutsResultSupport {

	
	private static final long serialVersionUID = 1L;
	
	private String contentTypeName = null;
	private String stringName = null;

	public StringResult(){
		super();
	}
	public StringResult(String location){
		super(location);
	}
	public String getContentTypeName() {
		return contentTypeName;
	}
	public void setContentTypeName(String contentTypeName) {
		this.contentTypeName = contentTypeName;
	}
	public String getStringName() {
		return stringName;
	}
	public void setStringName(String stringName) {
		this.stringName = stringName;
	}
	@Override
	protected void doExecute(String finalLocation,
			ActionInvocation invocation) throws Exception {

		HttpServletResponse response = (HttpServletResponse)invocation.getInvocationContext().get(HTTP_RESPONSE);
		String contentType = this.conditionalParse(contentTypeName, invocation);
		if(null == contentType  || "".equals(contentType)){
			contentType = "text/plain;charset=UTF-8";
		}
		response.setContentType(contentType);
		PrintWriter writer= response.getWriter();
		String result =(String) invocation.getStack().findValue(stringName);
		writer.write(result);
		writer.flush();
		writer.close();
	}

}
