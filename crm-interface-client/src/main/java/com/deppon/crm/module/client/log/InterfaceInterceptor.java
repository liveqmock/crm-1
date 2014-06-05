package com.deppon.crm.module.client.log;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.deppon.crm.module.client.common.domain.InterfaceConfig;
import com.deppon.crm.module.client.common.domain.MethodConfig;
import com.deppon.crm.module.client.common.util.ExceptionStack2String;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.log.domain.InterfaceInvokeLog;
import com.deppon.foss.framework.server.components.logger.ILogSender;
import com.deppon.foss.framework.server.components.logger.LogBuffer;

/**
 * 日志记录拦截器，默认不记录性能日志
 * @author davidcun @2012-4-24
 */
public class InterfaceInterceptor implements MethodInterceptor {

	private LogBuffer logBuffer;
	
	private InterfaceConfig interfaceDetail;
	
	public InterfaceInterceptor(InterfaceConfig interfaceDetail , LogBuffer logBuffer) {
		this.logBuffer = logBuffer;
		this.interfaceDetail = interfaceDetail;
	}
	/**
	 * @作者：吴根斌
	 * @时间：2013-10-21
	 * @描述：根据接口方法配置表中的配置来决定是否要记日志
	 * */
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		// 通过配置来记录接口日志
//		MethodConfig methodConfig = interfaceDetail.getMethodConfig(method.getName());
		// 过滤spring启动时set方法
		if(method.getName().startsWith("set") || method.getName().startsWith("hashCode")){
			return proxy.invokeSuper(obj, args);
		}else {
			//如果接口配置方法表中存在该方法，则不记日志。
			String presentMethod=method.getName();
			List<MethodConfig> methodConfigs=interfaceDetail.getMethodConfigs();
			for(MethodConfig methodConfig:methodConfigs){
				String imethod=methodConfig.getName();
				if(presentMethod.equals(imethod)){
					return proxy.invokeSuper(obj, args);
				}
			}
			
			InterfaceInvokeLog invokeLog = new InterfaceInvokeLog();
			invokeLog.setInvokeTime(new Date());
			invokeLog.setClassName(interfaceDetail.getClassName());
			invokeLog.setMethod(method.getName());
			if(args != null && args.length>0){
				invokeLog.setRequestMsg(JsonMapperUtil.writeValue(args));
			}
			long start = System.currentTimeMillis();
			Object result=null;
			try {
				result = proxy.invokeSuper(obj, args);
			} catch (Throwable e) {
				invokeLog.setException(true);
				invokeLog.setExceptionMsg(ExceptionStack2String.stack2String(e));
				logBuffer.write(invokeLog);
				throw e;
			}
			if(result != null){
				invokeLog.setResponseMsg(JsonMapperUtil.writeValue(result));
			}
			invokeLog.setUseTime(System.currentTimeMillis()-start);
			logBuffer.write(invokeLog);
			return result;
			
		}
		/*else if (methodConfig.isRecordExceptionLog()) {
			InterfaceInvokeLog invokeLog = new InterfaceInvokeLog();
			Object result=null;
			try {
				result = proxy.invokeSuper(obj, args);
				return result;
			} catch (Throwable e) {
				invokeLog.setException(true);
				invokeLog.setExceptionMsg(ExceptionStack2String.stack2String(e));
				logBuffer.write(invokeLog);
				throw e;
			}
		}*/
//		return proxy.invokeSuper(obj, args);
	}
	
	public void setLogBuffer(LogBuffer logBuffer) {
		this.logBuffer = logBuffer;
	}
	
	public static void main(String[] args) {
		
		LogBuffer logBuffer = new LogBuffer();
		logBuffer.setListSize(1);
		logBuffer.setLogSender(new ILogSender() {

			@Override
			public void send(List<Object> msg) {
				
			}
		});
		logBuffer.setQueueSize(2);
		
		for (int i = 0; i < 10; i++) {
			InterfaceInvokeLog entity = new InterfaceInvokeLog();
			entity.setUseTime(1000202034);
			logBuffer.write(entity);
		}
		
	}

}
