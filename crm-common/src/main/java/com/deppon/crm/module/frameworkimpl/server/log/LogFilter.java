package com.deppon.crm.module.frameworkimpl.server.log;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.frameworkimpl.server.log.bean.ReqLogInfo;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.components.logger.LogBuffer;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;
import com.deppon.foss.framework.server.web.DefaultFilter;

/**
 * 
 * 
 ******************************************* 
 * <b style="font-family:微软雅黑"><small>Description:记录日志过滤器</small></b> </br> <b
 * style="font-family:微软雅黑"><small>HISTORY</small></b></br> <b
 * style="font-family:微软雅黑"><small> ID DATE PERSON REASON</small></b><br>
 ******************************************** 
 * <div style="font-family:微软雅黑,font-size:70%"> 2 2011-2-10 ztjie 新增 </div>
 ******************************************** 
 */
public class LogFilter extends DefaultFilter {

	private Logger logger = Logger.getLogger(LogFilter.class);

	/**
	 * 记录日志
	 * 
	 * @see com.deppon.foss.framework.server.web.DefaultFilter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain) doFilter
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws IOException
	 * @throws ServletException
	 * @since:0.6
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// Enumeration enu = Logger.getRootLogger().getAllAppenders();
		// while (enu.hasMoreElements()) {
		// String value = String.valueOf(enu.nextElement());
		// System.out.println((value));
		// }
		//
		// Enumeration url = this.getClass().getClassLoader()
		// .getResources("log4j.xml");
		// while (url.hasMoreElements()) {
		// String value = String.valueOf(url.nextElement());
		// System.out.println((value));
		// }

		// 请求日志信息对象
		ReqLogInfo rli = new ReqLogInfo();

		/** 取得HttpServletRequest,这里可以拿到HttpSession **/
		HttpServletRequest sreq = (HttpServletRequest) request;

		// 生成一个请求的ID
		String requestID = UUID.randomUUID().toString();

		// 设置当前请求ID到当前线程中
		Thread.currentThread().setName(requestID);

		// 当前登录用户ID
		String userId = null;
		User user = (User) UserContext.getCurrentUser();
		if (user != null) {
			userId = user.getLoginName();
			Employee emp = user.getEmpCode();
			if (emp != null) {
				userId += "(" + emp.getEmpName() + ")";
			}
		}

		// 分解请求URL，分解后的数据如：[, application, login, index.action]
		String[] strs = sreq.getRequestURI().split("/");

		// 得到请求日志发送缓存todo
		LogBuffer logBuffer = (LogBuffer) WebApplicationContextHolder
				.getWebApplicationContext().getBean("performanceLog");

		// if(logBuffer!=null){
		if (strs.length > 0) {
			rli.setRequestID(requestID);
			rli.setClientIP(sreq.getHeader("X-Forwarded-For") + "|"
					+ sreq.getHeader("Proxy-Client-IP") + "|"
					+ sreq.getHeader("WL-Proxy-Client-IP") + "|"
					+ sreq.getRemoteAddr());
			rli.setClientUserAgent(sreq.getHeader("user-agent"));
			rli.setServerIP(sreq.getLocalAddr());
			rli.setServerPort(sreq.getLocalPort());
			rli.setRequestURI(sreq.getServletPath());
			rli.setUser(userId);
			rli.setAppName(strs[1]);
			rli.setModuleName(strs[2]);
			rli.setAction(strs[3]);
			Enumeration<String> paramsName = sreq.getParameterNames();
			String params = "";
			while (paramsName.hasMoreElements()) {
				String name = paramsName.nextElement();
				String value = sreq.getParameter(name);
				if ("".equals(params)) {
					params = name + "=" + value;
				} else {
					params += "\n" + name + "=" + value;
				}
			}
			rli.setReqParameters(params);

			// 请求日志信息
			// String reqLogInfo = new StringBuffer()
			// // 设置当前请求时间
			// .append(new Date())
			// // 设置当前访问用户ID
			// .append("\n").append(userId)
			// // 设置当前应用名
			// .append("\n").append(strs[1])
			// // 设置当前模块名
			// .append("\n").append(strs[2])
			// // 设置当前请求action名
			// .append("\n").append(strs[3])
			// // 设置当前请求参数
			// .append("\n").append(sreq.getQueryString()).toString();
			//
			// // 日志对象
			// LogInfo logInfo = new LogInfo();
			//
			// // 设置日志信息
			// logInfo.setLogInfo(reqLogInfo);
			// // 设置日志对象的请求ID
			// logInfo.setRequestId(requestID);
			// logInfo.setLogType("action");
			// logInfo.setCreateDate(new Date());
			// // 写入请求日志信息对象
			// logger.info(logInfo);
		}
		// }
		Long startTime = System.currentTimeMillis();
		super.doFilter(request, response, filterChain);
		Long endTime = System.currentTimeMillis();

		rli.setReqStartTime(new Date(startTime).getTime());
		rli.setReqEndTime(new Date(endTime).getTime());
		rli.setReqDuration(endTime - startTime);

		logBuffer.write(rli);
	}

}
