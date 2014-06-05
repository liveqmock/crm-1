package com.deppon.crm.module.frameworkimpl.server.filter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.deppon.foss.framework.server.Definitions;
import com.deppon.foss.framework.server.components.security.SecurityAccessor;
import com.deppon.foss.framework.server.context.RequestContext;
import com.deppon.foss.framework.server.context.SessionContext;
import com.deppon.foss.framework.server.web.DefaultFilter;
import com.deppon.foss.framework.server.web.message.MessageBundle;

public class I18nAndPermissionFilter extends DefaultFilter {

	private static ServletContext servletContext;

	private static final Log logger = LogFactory
			.getLog(I18nAndPermissionFilter.class);

	private static WebApplicationContext appContext;

	private static String APPNAME;

	private static final String PACKAGE_PREFIX = "com/deppon/";

	private static final String SCRIPTS_TO = "/i18n_permission_acess";

	private static final String I18N_CONFIG = "/server/META-INF/i18n-config/i18n_config.properties";

	private static final String PERMISSION_CONFIG = "/server/META-INF/permission-config/permission_config.properties";

	/**
	 * 初始化Filter
	 * 
	 * @see com.deppon.foss.framework.server.web.DefaultFilter#init(javax.servlet.FilterConfig)
	 *      init
	 * @param config
	 * @throws ServletException
	 * @since:0.6
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		servletContext = config.getServletContext();
		appContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		APPNAME = config.getInitParameter("application");
	}

	/**
	 * 设置应用信息
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
		HttpServletRequest sreq = (HttpServletRequest) request;
		String[] strs = sreq.getRequestURI().split("/");
		if (strs[2].equals(SCRIPTS_TO.split("/")[1])) {
			try {
				String moduleName = strs[strs.length - 2];
				RequestContext.setCurrentContext(moduleName);
				StringBuilder msgObject = new StringBuilder("");
				msgObject.append(this.getI18nMessage(sreq.getRequestURI(),
						moduleName));
				msgObject.append(this.getPermissionMessage(
						sreq.getRequestURI(), moduleName));
				OutputStream out = response.getOutputStream();
				out.write(msgObject.toString().getBytes("UTF-8"));
				// 在debug模式下，才会生成JS文件
				if (logger.isDebugEnabled()) {
					String dist = SCRIPTS_TO + "/" + moduleName + "/"
							+ strs[strs.length - 1];
					File file = new File(servletContext.getRealPath(dist));
					if (!file.getName().matches("^.*[.].*$")) {
						File dir = new File(file.getPath() + "\\"
								+ File.separator);
						// 先创建目录
						if (!dir.exists()) {
							file.mkdirs();
						}
					}
					File dir = file.getParentFile();
					if (!dir.exists()) {
						dir.mkdirs();
						if (logger.isInfoEnabled()) {
							logger.info("[Framework] create dir: " + dir);
						}
					}
					if (logger.isInfoEnabled()) {
						logger.info("[Framework] release resource: " + dist);
					}
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(msgObject.toString().toCharArray());
					fileWriter.flush();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} else {
			super.doFilter(request, response, filterChain);
		}
	}

	// 得到I18N信息
	private String getI18nMessage(String uri, String moduleName)
			throws IOException {
		// 得到当前请求语言信息
		Locale locale = (Locale) SessionContext.getSession().getObject(Definitions.KEY_LOCALE);
		Properties properties = new Properties();
		// i18n信息Id的构造，通过"uri_i18n_locale"信息整合
		String messageInfoId = uri + "_i18n_" + locale.toString();
		StringBuilder i18nMsgObject = new StringBuilder("");
		if (I18nMsgAndPerPropCache.getInstance().getMessageInfo(messageInfoId) == null) {
			Resource i18nResource = appContext.getResource(
					new StringBuffer("classpath:")
						.append(PACKAGE_PREFIX)
						.append(APPNAME)
						.append("/module/")
						.append(moduleName)
						.append(I18N_CONFIG)
						.toString());
			if (i18nResource == null) {
				return i18nMsgObject.toString();
			}
			try {
				InputStream  in = i18nResource.getInputStream();
				properties.load(in);
			} catch (IOException e) {
				logger.debug(e.getMessage());
				return i18nMsgObject.toString();
			}
			Object[] keys = properties.keySet().toArray();
			ArrayList<String> keyList = new ArrayList<String>();
			for (int index = 0; index < keys.length; index++) {
				keyList.add(keys[index].toString());
			}
			MessageBundle messageBundle = new MessageBundle();
			// 声明一个function，用于取message
			i18nMsgObject.append("function i18n(key, args) { \n");
			// 声明一个对象，存放message信息
			i18nMsgObject.append("msg = {");
			for (String key : keyList) {
				String message = messageBundle.getMessage(key);
				if (message != null && !"".equals(message)) {
					i18nMsgObject.append("'" + key + "'" + ":'" + message
							+ "',");
				}
			}
			i18nMsgObject.deleteCharAt(i18nMsgObject.lastIndexOf(","));
			i18nMsgObject.append("};");

			i18nMsgObject.append("\n");
			i18nMsgObject.append("var message = msg[ key] ; \n");
			i18nMsgObject.append("if(args != null){  \n");
			i18nMsgObject.append("for ( var i = 0; i < args.length; i++) { \n");
			i18nMsgObject.append("var reg ='{'+i+'}'; \n");
			i18nMsgObject
					.append("message = message.toString().replace(reg, args[i]); \n");
			i18nMsgObject.append("} \n");
			i18nMsgObject.append("} \n");
			i18nMsgObject.append("return message; \n");
			i18nMsgObject.append("} \n");
			I18nMsgAndPerPropCache.getInstance().registerMessageInfo(
					messageInfoId, i18nMsgObject.toString());
		} else {
			i18nMsgObject.append(I18nMsgAndPerPropCache.getInstance()
					.getMessageInfo(messageInfoId));
		}
		return i18nMsgObject.toString();
	}

	// 得到当前用户权限信息
	private String getPermissionMessage(String uri, String moduleName) {
		StringBuilder permissionMsgObject = new StringBuilder("");
		Properties properties = new Properties();
		// 权限信息Id的构造，通过"uri_permission"信息整合
		String messageInfoId = uri + "_permission";
		if (I18nMsgAndPerPropCache.getInstance().getMessageInfo(messageInfoId) == null) {
			Resource permissionResource = appContext.getResource(
					new StringBuffer("classpath:")
						.append(PACKAGE_PREFIX)
						.append(APPNAME)
						.append("/module/")
						.append(moduleName)
						.append(PERMISSION_CONFIG)
						.toString());
			if (permissionResource == null) {
				return permissionMsgObject.toString();
			}
			try {
				InputStream  in = permissionResource.getInputStream();
				properties.load(in);
			} catch (IOException e) {
				logger.debug(e.getMessage());
				return permissionMsgObject.toString();
			}
			I18nMsgAndPerPropCache.getInstance().registerMessageInfo(
					messageInfoId, properties);
		} else {
			properties = (Properties) I18nMsgAndPerPropCache.getInstance()
					.getMessageInfo(messageInfoId);
		}
		if (properties.size() > 0) {
			Object[] keys = properties.keySet().toArray();
			ArrayList<String> keyList = new ArrayList<String>();
			for (int index = 0; index < keys.length; index++) {
				keyList.add(keys[index].toString());
			}
			permissionMsgObject.append("function isPermission(url){ \n");
			permissionMsgObject.append("permissions = {");
			for (String url : keyList) {
				boolean message = SecurityAccessor.hasAccessSecurity(url);
				permissionMsgObject.append("'" + url + "'" + ":" + message
						+ ",");
			}
			permissionMsgObject.deleteCharAt(permissionMsgObject
					.lastIndexOf(","));
			permissionMsgObject.append("}; \n");
			permissionMsgObject.append("return permissions[url]; \n");
			permissionMsgObject.append("} \n");
		}
		return permissionMsgObject.toString();
	}
}
