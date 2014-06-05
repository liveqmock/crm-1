package com.deppon.crm.module.customer.server.manager;

import junit.framework.TestCase;

import com.deppon.crm.module.customer.server.manager.IContactManager;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.manager.impl.ContractManager;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.util.UserUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;

public class ContactManagerWebTest extends TestCase{
	private IContactManager contactManager ;
	
	@Override
	protected void setUp() throws Exception {
		UserUtil.setUserToAdmin();
		contactManager = (IContactManager) SpringTestHelper.getBean("contactManager");
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-13
	 * @param args
	 * void
	 */
	public static void main(String[] args) {
		IContractManager contractManager = SpringTestHelper.getBean(ContractManager.class);
		boolean result = contractManager.contractApprove("6816124", true, "wmm", null);
	    
		System.out.println(result);
	}
	
	public void testBoundContactForWeb(){
		try {
			contactManager.boundContactForWeb(Constant.ORDER_SOURCE_ONLINE,
					"3333333", "wtj13712173753", "13712173753");
		} catch (ContractException e) {
			assertEquals(e.getErrorCode(), ContractExceptionType.ONLINEHAVEBOUND.getErrCode());
		}
		
		try {
			contactManager.boundContactForWeb(Constant.ORDER_SOURCE_ALIBABA,
					"3333333", "wtj13712173753", "13712173753");
		} catch (ContractException e) {
			assertEquals(e.getErrorCode(), ContractExceptionType.ALIBABAHAVEBOUND.getErrCode());
		}
		
		try {
			contactManager.boundContactForWeb(Constant.ORDER_SOURCE_SHANGCHENG,
					"3333333", "wtj13712173753", "13712173753");
		} catch (ContractException e) {
			assertEquals(e.getErrorCode(), ContractExceptionType.SHANGCHENGHAVEBOUND.getErrCode());
		}
		try {
			contactManager.boundContactForWeb(Constant.ORDER_SOURCE_TAOBAO,
					"3333333", "wtj13712173753", "13712173753");
		} catch (ContractException e) {
			assertEquals(e.getErrorCode(), ContractExceptionType.TAOBAOHAVEBOUND.getErrCode());
		}
		try {
			contactManager.boundContactForWeb(Constant.ORDER_SOURCE_YOUSHANG,
					"3333333", "wtj13712173753", "13712173753");
		} catch (ContractException e) {
			assertEquals(e.getErrorCode(), ContractExceptionType.YOUSHANGHAVEBOUND.getErrCode());
		}

	}

}
