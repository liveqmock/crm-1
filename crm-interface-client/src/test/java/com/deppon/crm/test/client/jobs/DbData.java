/*package com.deppon.crm.test.client.jobs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.deppon.crm.module.client.order.domain.OrderStatusLog;

public class DbData {

	private static Map<Long, OrderStatusLog> logs = new HashMap<Long, OrderStatusLog>();
	static{
		int t=0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 100; j++) {
				OrderStatusLog log = new OrderStatusLog();
				log.setId((int)(Math.random()*100));
				log.setOrderNumber(""+(i+1));
				log.setLogStatus("ready");
				log.setTransactionNumber(t++);
				logs.put(log.getTransactionNumber(),log);
			}
		}
		
	}
	
	public static void updateLog(List<OrderStatusLog> osl){
		for (int i = 0; i < osl.size(); i++) {
			logs.remove(osl.get(i).getTransactionNumber());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, List<OrderStatusLog>> loadSequence(){
		
		Map<String, List<OrderStatusLog>> result = new HashMap<String, List<OrderStatusLog>>();
		
		@SuppressWarnings("rawtypes")
		TreeMap<Long, OrderStatusLog> treeMap = new TreeMap(new Comparator<Long>() {

			@Override
			public int compare(Long o1, Long o2) {
				// TODO Auto-generated method stub
				return (int) (o1-o2);
			}
		});
		
		treeMap.putAll(logs);
		
		Set<Entry<Long, OrderStatusLog>> set = treeMap.entrySet();
		for (Entry<Long, OrderStatusLog> entry : set) {
//			System.out.println(entry.getValue().getTransactionNumber());
			if (result.get(entry.getValue().getOrderNumber())!=null) {
				result.get(entry.getValue().getOrderNumber()).add(entry.getValue());
			}else{
				List<OrderStatusLog> lg = new ArrayList<OrderStatusLog>();
				lg.add(entry.getValue());
				result.put(entry.getValue().getOrderNumber(), lg);
			}
			
		}
		return result;
	}
	public static void main(String[] args) {
		Map<String, List<OrderStatusLog>> result = DbData.loadSequence();
		Set<Entry<String, List<OrderStatusLog>>> set = result.entrySet();
		for (Entry<String, List<OrderStatusLog>> entry : set) {
			System.out.println(entry.getValue().size());
		}
	}
}
*/