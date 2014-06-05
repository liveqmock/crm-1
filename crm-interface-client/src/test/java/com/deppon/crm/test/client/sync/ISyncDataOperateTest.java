/*package com.deppon.crm.test.client.sync;

import java.util.LinkedList;
import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;
import com.deppon.crm.module.client.sync.domain.WaitingSendRequest;
import com.deppon.crm.module.client.sync.domain.dto.TCustAccountOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustCustbasedataOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustShuttleaddressOperation;

public class ISyncDataOperateTest {

	
	@Test
	public void test() throws SyncDataException{
		
		
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setAddress("http://localhost:9090/sync");
		factoryBean.setServiceClass(ISyncDataService.class);
		
		ISyncDataService service = (ISyncDataService) factoryBean.create();
		
		boolean result = service.sync(getJsonString());
		System.out.println(result);
	}
	
	
	private String getJsonString(){
		
		TCustAccountOperation tCustAccountOperation1 = new TCustAccountOperation();
		tCustAccountOperation1.setFbank("BANK1");
		tCustAccountOperation1.setOperationFlg(OperationFlg.I);
		TCustAccountOperation tCustAccountOperation2 = new TCustAccountOperation();
		tCustAccountOperation2.setFbank("BANK2");
		tCustAccountOperation2.setOperationFlg(OperationFlg.U);
		
		List<TCustAccountOperation> tCustAccountOperationList = new LinkedList<TCustAccountOperation>();
		tCustAccountOperationList.add(tCustAccountOperation1);
		tCustAccountOperationList.add(tCustAccountOperation2);
		
		TCustShuttleaddressOperation tCustShuttleaddressOperation = new TCustShuttleaddressOperation();
		tCustShuttleaddressOperation.setOperationFlg(OperationFlg.I);
		tCustShuttleaddressOperation.setFcity("SHANGHAI");
		List<TCustShuttleaddressOperation> tCustShuttleaddressOperationList = new LinkedList<TCustShuttleaddressOperation>();
		tCustShuttleaddressOperationList.add(tCustShuttleaddressOperation);
		
		TCustCustbasedataOperation baseData = new TCustCustbasedataOperation();
		baseData.setFcustname("MemberName");
		baseData.setOperationFlg(OperationFlg.I);
		
		WaitingSendRequest request = new WaitingSendRequest();
		request.settCustAccount(tCustAccountOperationList);
		request.settCustShuttleaddress(tCustShuttleaddressOperationList);
		request.settCustCustbasedata(baseData);
		
		String str="";
		try {
			str = JsonMapperUtil.writeValue(request);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
		return str;
	}
}
*/