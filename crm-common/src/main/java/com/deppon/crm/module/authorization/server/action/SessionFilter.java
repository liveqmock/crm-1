package com.deppon.crm.module.authorization.server.action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.ng.InitOperations;
import org.apache.struts2.dispatcher.ng.filter.FilterHostConfig;


public class SessionFilter implements StrutsStatics, Filter{
    
	public void init(FilterConfig filterConfig) throws ServletException {
        InitOperations init = new InitOperations();
        try {
            FilterHostConfig config = new FilterHostConfig(filterConfig);
            init.initLogging(config);
            Dispatcher dispatcher = init.initDispatcher(config);
            init.initStaticContentLoader(config, dispatcher);
            postInit(dispatcher, filterConfig);
        } finally {
            init.cleanup();
        }

    }

    /**
     * Callback for post initialization
     */
    protected void postInit(Dispatcher dispatcher, FilterConfig filterConfig) {
    	
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
//        System.out.println("用户******************************************"+request.getRequestURI());
        HttpSession session = request.getSession(false);
        if(session==null){
        	if(request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
        		response.setHeader("sessionstatus", "timeout");
//        		System.out.println("ajax请求"+request.getRequestURI());
        	}else{
//        		System.out.println("web请求"+request.getRequestURI());
        	}
        };
		chain.doFilter(request, response);
    };

    public void destroy() {
        
    }
}
