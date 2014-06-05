/*package com.deppon.crm.test.client.jobs;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.jobs.ILogProvider;
import com.deppon.crm.module.client.jobs.ILogSender;
import com.deppon.crm.module.client.jobs.ILogProcess;
import com.deppon.crm.module.client.jobs.impl.ExecutorContext;
import com.deppon.crm.module.client.jobs.impl.LogStatus;
import com.deppon.crm.module.client.jobs.impl.MemryLock;
import com.deppon.crm.module.client.order.domain.OrderStatusLog;

public class JobTest {
	
	public void execute(){
		System.out.println("----------------------------");
	}

	ExecutorContext<OrderStatusLog> context = null;
	@Before
	public void init(){
		context = new ExecutorContext<OrderStatusLog>();
		context.setLock(new MemryLock());
		context.setProcess(new ILogProcess<OrderStatusLog>() {
			public boolean update(OrderStatusLog t, LogStatus status) {
				return false;
			}
			public void update(List<OrderStatusLog> logs, LogStatus status) {
				DbData.updateLog(logs);
			}
		});
		context.setProvider(new ILogProvider<OrderStatusLog>() {
			public Map<String, List<OrderStatusLog>> load() {
				
				return DbData.loadSequence();
			}

			@Override
			public List<OrderStatusLog> loadList() {
				return null;
			}

			@Override
			public List<OrderStatusLog> loadList(int size) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void clearCache() {
				// TODO Auto-generated method stub
				
			}
		});
		context.setSender(new ILogSender<OrderStatusLog>() {
			
			@Override
			public boolean send(OrderStatusLog t) {
				System.out.println("订单号："+t.getOrderNumber()+"---ID："+t.getTransactionNumber());
				return true;
			}
			
			@Override
			public int[] send(List<OrderStatusLog> logs) {
				return new int[0];
			}
		});
		
	}
	
	@Test
	public void testExecute() throws InterruptedException{
		context.execute();
		
		Thread.sleep(100000);
	}

	public ExecutorContext<OrderStatusLog> getContext() {
		return context;
	}

	public void setContext(ExecutorContext<OrderStatusLog> context) {
		this.context = context;
	}
}
*/