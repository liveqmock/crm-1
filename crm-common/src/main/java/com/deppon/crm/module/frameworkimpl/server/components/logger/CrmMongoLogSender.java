package com.deppon.crm.module.frameworkimpl.server.components.logger;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoOperations;

import com.deppon.crm.module.frameworkimpl.server.log.bean.LogInfo;
import com.deppon.crm.module.frameworkimpl.server.log.bean.ReqLogInfo;
import com.deppon.foss.framework.server.components.dataaccess.mongodb.MongoDaoSupport;
import com.deppon.foss.framework.server.components.logger.ILogSender;
import com.deppon.foss.framework.server.components.logger.exception.BufferedStateException;

/**
 * 将日志信息存入mongoDB中
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:Ningyu,date:2012-9-5 下午2:35:59,
 * </p>
 * 
 * @author Ningyu
 * @date 2012-9-5 下午2:35:59
 * @since
 * @version
 */
public class CrmMongoLogSender extends MongoDaoSupport implements ILogSender,
		InitializingBean, DisposableBean {

	public static final Log LOGGER = LogFactory.getLog(CrmMongoLogSender.class);

	private MongoOperations logInfo = null;

	public void setLogInfo(MongoOperations logInfo) {
		this.logInfo = logInfo;
	}

	private ExecutorService threadPool = null;

	private int threadSize = 5;

	/**
	 * 
	 * <p>
	 * 关闭资源
	 * </p>
	 * 
	 * @author 平台开发小组
	 * @date 2013-4-1 上午10:48:40
	 * @throws Exception
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	@Override
	public void destroy() throws Exception {
		threadPool.shutdown();
		while (!threadPool.isTerminated()) {
			try {
				synchronized (this) {
					this.wait(1000);
				}
			} catch (InterruptedException e) {
				throw new BufferedStateException(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 初始化配置
	 * </p>
	 * 
	 * @author 平台开发小组
	 * @date 2013-4-1 上午10:49:00
	 * @throws Exception
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		threadPool = new ThreadPoolExecutor(threadSize, threadSize, 3,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(
						5 * threadSize), new ThreadPoolExecutor.AbortPolicy());
		// logInfo = this.getDbCollection(ReqLogInfo.class.getSimpleName());

		/*
		 * if (!logInfo.collectionExists("ReqLogInfo")) {
		 * logInfo.createCollection("ReqLogInfo"); }
		 */
	}

	/**
	 * 
	 * <p>
	 * 发送mongodb日志
	 * </p>
	 * 
	 * @author 平台开发小组
	 * @date 2013-4-1 上午10:50:47
	 * @param msg
	 * @see com.deppon.foss.framework.server.components.logger.ILogSender#send(java.util.List)
	 */
	@Override
	public void send(List<Object> msg) {
		threadPool.submit(new SendTask(msg));
	}

	/**
	 * 
	 * 添加发送任务
	 * <p style="display:none">
	 * modifyRecord
	 * </p>
	 * <p style="display:none">
	 * version:V1.0,author:ningyu,date:2013-4-1 上午10:49:28,
	 * </p>
	 * 
	 * @author 平台开发小组
	 * @date 2013-4-1 上午10:49:28
	 * @since
	 * @version
	 */
	private class SendTask implements Runnable {

		private List<Object> msg;

		public SendTask(List<Object> list) {
			this.msg = list;
		}

		@Override
		public void run() {
			try {
				for (Object obj : msg) {
					if (obj instanceof ReqLogInfo || obj instanceof LogInfo) {
						logInfo.insert(obj);
					}
				}
			} catch (Exception e) {
				LOGGER.error("CrmMongoLogSender: Write Log error!", e);
			}
		}
	}

}