package com.deppon.crm.module.client.jobs.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.jobs.ILock;
import com.deppon.crm.module.client.jobs.ILogProcess;
import com.deppon.crm.module.client.jobs.ILogProvider;
import com.deppon.crm.module.client.jobs.ILogSender;

@SuppressWarnings("rawtypes")
public class ExecutorContext<T> {
	private static Log log = LogFactory.getLog(ExecutorContext.class);
	
	private ILock lock;
	
	private ILogProvider<T> provider;
	
	private ILogProcess<T> process;
	
	private ILogSender<T> sender;
	
	private ExecutorService executor;
	
	public ExecutorContext(){
		init();
	}
	
	public ExecutorContext(ILock lock,ILogProvider<T> provider,
			ILogProcess<T> process,ILogSender<T> sender){
		this.lock = lock;
		this.provider = provider;
		this.process = process;
		this.sender = sender;
	}
	
	public void init(){
		executor = Executors.newFixedThreadPool(10);
	}

	List<Future> futures = new ArrayList<Future>();
	List<Future> unDone = new ArrayList<Future>();
	
	public void execute(){
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
			
			provider.clearCache();
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
				
//			Map<String, List<T>> maps = provider.load();
//			if (maps==null || maps.size()<1) {
//				break;
//			}
//			Set<Entry<String, List<T>>> set = maps.entrySet();
//			for (Entry<String, List<T>> entry : set) {
//				futures.add(executor.submit(new JobTask(entry.getValue())));
//			}
				
				List<T> logList = provider.loadList();
				if (log.isDebugEnabled()) {
					log.debug("current logList size is "+logList.size());
				}
				if (logList==null || logList.size()<1) {
					break;
				}
				futures.add(executor.submit(new JobTask(logList)));
			}
		} finally{
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
	
	class JobTask implements Runnable{

		List<T> logs;
		public JobTask(List<T> logs) {
			this.logs = logs;
		}
		
		@Override
		public void run() {
			if (logs==null ) {
				return ;
			}
			try {
				if (log.isInfoEnabled()) {
					log.debug("current sender size is "+logs.size());
				}
				int[] result = sender.send(logs);
				if (result==null) {
					process.update(logs, LogStatus.SUCCESS);
					return;
				}
				if (log.isDebugEnabled()) {
					log.debug("current sender fail size is "+result.length);
				}
				if (log.isInfoEnabled()) {
					log.info(String.format("on %s invoke task to sender orderLog to ebm result is %s", new Date(),result));
				}
				if (result.length==logs.size()) {
					process.update(logs, LogStatus.FAIL);
				}else{
					List<T> success = new ArrayList<T>();
					List<T> fail = new ArrayList<T>();
					for (int i = 0; i < result.length; i++) {
						fail.add(logs.get(result[i]));
					}
					success.addAll(logs);
					success.removeAll(fail);
					process.update(success, LogStatus.SUCCESS);
					process.update(fail, LogStatus.FAIL);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				process.update(logs, LogStatus.FAIL);
			}
		}
	}
}
