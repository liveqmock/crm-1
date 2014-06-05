package com.deppon.crm.test.client.map;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.map.IMapOperate;

public class MyThread implements Runnable {
	private IMapOperate operate;

	public MyThread(IMapOperate mapOperate) {
		operate = mapOperate;
	}

	@Override
	public void run() {
			System.out.println("one thread");
			String deptCode = null;
			while(true){
				try {
					deptCode = operate.findDeptCodeByAddress("上海市青浦区徐泾镇");
					System.out.println(deptCode);
					Thread.sleep(10*1000);
				} catch (Exception e) {
					System.out.println("exception msg:"+e.getMessage());
				}
				
			}
			
/*			System.out.println("666666666666666666666666666666666");
			System.out.println(deptCode);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
	}
	public void setOperate(IMapOperate mapOperate) {
		operate = mapOperate;
	}
}
