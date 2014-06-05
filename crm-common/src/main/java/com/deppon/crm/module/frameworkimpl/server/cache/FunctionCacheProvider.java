package com.deppon.crm.module.frameworkimpl.server.cache;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.deppon.crm.module.authorization.server.dao.IFunctionDao;
import com.deppon.crm.module.authorization.shared.domain.Function;
import com.deppon.foss.framework.cache.provider.IBatchCacheProvider;
import com.deppon.foss.framework.entity.IFunction;
import com.deppon.foss.framework.server.context.AppContext;

/**
 * 功能权限缓存数据提供对象
 * @author ztjie
 *
 */
public class FunctionCacheProvider implements IBatchCacheProvider<String, IFunction>{
	
	private IFunctionDao functionDao;
	
	
	public Date getLastModifyTime() {
		return functionDao.getLastModifyTime();
	}

	public Map<String, IFunction> get() {
		Map<String, IFunction> map = new HashMap<String, IFunction>();
		Collection<Function> funcs = functionDao.getAllChildFunctionByURI("/"+AppContext.getAppContext().getAppName());
		String uri ;
		for (Function func : funcs) {
		  if(func.getUri()!=null){
			 uri = func.getUri().trim();
			 map.put(uri.replace("\\n", ""), func);
		  }
			
		}

		return map;
	}
	
	
	public IFunction get(String uri) {
	        Function function = new Function();
	        function.setUri(uri);
	        
	        Collection<Function> funcs = functionDao.getAll(function);
	        for (Function func : funcs) {
	        	function = func;
	        }
	        
	        if(funcs.size()<=0){
	        	function = null;
	        }
	        
		return function;
		
	}

	public IFunctionDao getFunctionDao() {
		return functionDao;
	}

	public void setFunctionDao(IFunctionDao functionDao) {
		this.functionDao = functionDao;
	}

}
