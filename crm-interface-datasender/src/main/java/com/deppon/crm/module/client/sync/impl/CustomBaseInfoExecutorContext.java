package com.deppon.crm.module.client.sync.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.jobs.IExecutor;
import com.deppon.crm.module.client.jobs.ILock;
import com.deppon.crm.module.client.jobs.ILogProcess;
import com.deppon.crm.module.client.jobs.ILogProvider;
import com.deppon.crm.module.client.jobs.ILogSender;
import com.deppon.crm.module.client.jobs.impl.ExecutorContext;
import com.deppon.crm.module.client.jobs.impl.LogStatus;

public class CustomBaseInfoExecutorContext<T> implements IExecutor {
	private static Log log = LogFactory.getLog(ExecutorContext.class);

	private ILock lock;

	private ILogProvider<T> provider;

	private ILogProcess<T> process;

	private ILogSender<T> sender;

	private ExecutorService executor;

	public CustomBaseInfoExecutorContext() {
		init();
	}

	public CustomBaseInfoExecutorContext(ILock lock, ILogProvider<T> provider,
			ILogProcess<T> process, ILogSender<T> sender) {
		this.lock = lock;
		this.provider = provider;
		this.process = process;
		this.sender = sender;
	}

	public void init() {
		executor = Executors.newFixedThreadPool(3);
	}

	@SuppressWarnings("rawtypes")
	List<Future> futures = new ArrayList<Future>();
	@SuppressWarnings("rawtypes")
	List<Future> unDone = new ArrayList<Future>();

	public void execute() {
		if (executor.isTerminated()) {
			init();
		}

		if (lock.isLock()) {
			return;
		} else {
			synchronized (ExecutorContext.class) {
				if (lock.isLock()) {
					return;
				}
				lock.lock();
			}
		}

		try {

			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				for (int i = 0; i < futures.size(); i++) {
					if (!futures.get(i).isDone()) {
						unDone.add(futures.get(i));
					}
				}
				futures.removeAll(futures);
				futures.addAll(unDone);
				unDone.removeAll(unDone);

				if (futures.size() > 0) {
					continue;
				}

				List<T> logList = provider.loadList();
				if (log.isDebugEnabled()) {
					log.debug("current logList size is " + logList.size());
				}
				if (logList == null || logList.size() < 1) {
					break;
				}
				for (T log : logList) {
					futures.add(executor.submit(new JobTask(log)));
				}
			}
		} finally {

			lock.unLock();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		executor.shutdown();
	}

	public ILock getLock() {
		return lock;
	}

	public void setLock(ILock lock) {
		this.lock = lock;
	}

	public ILogProvider<T> getProvider() {
		return provider;
	}

	public void setProvider(ILogProvider<T> provider) {
		this.provider = provider;
	}

	public ILogProcess<T> getProcess() {
		return process;
	}

	public void setProcess(ILogProcess<T> process) {
		this.process = process;
	}

	public ILogSender<T> getSender() {
		return sender;
	}

	public void setSender(ILogSender<T> sender) {
		this.sender = sender;
	}

	class JobTask implements Runnable {

		T logInfo;

		public JobTask(T logInfo) {
			this.logInfo = logInfo;
		}

		@Override
		public void run() {
			if (log == null) {
				return;
			}
			try {
				boolean result = sender.send(logInfo);
				if (log.isInfoEnabled()) {
					log.info(String
							.format("on %s invoke task to sender orderLog to esb result is %s",
									new Date(), result));
				}
				if (result) {
					process.update(logInfo, LogStatus.SUCCESS);
				} else {
					process.update(logInfo, LogStatus.FAIL);
				}
			} catch (Exception e) {
				e.printStackTrace();
				process.update(logInfo, LogStatus.FAIL);
			}
		}
	}
}
