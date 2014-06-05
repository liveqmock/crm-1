package com.deppon.crm.module.recompense.server.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.recompense.server.dao.impl.ListItemDaoImpl;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.Payment;
import com.deppon.crm.module.servicerecovery.server.dao.impl.ServiceRecoveryDao;
import com.deppon.foss.framework.server.context.AppContext;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:付款相关测试<br />
 * </p>
 * @title PaymentTest.java
 * @package com.deppon.crm.module.recompense.server.dao 
 * @author 华龙
 * @version 0.1 2013-1-4
 */
public class PaymentTest extends TestCase {
	private static Payment payment=null;
	private static ListItemDaoImpl listItemDao = null;
	static {
		listItemDao = TestUtil.listItemDao;
		payment=new Payment();
		payment.setCreateUserId("106138");
		payment.setCreateTime(new Date());
		payment.setRecompenseId("135648974");
		payment.setAccount("6222021001110778888");
		payment.setAccountProp("1");
		payment.setApplyTime(new Date());
//		payment.setBankName("交通银行");
//		payment.setOpenName("许华龙");
//		payment.setPaymentMode("2");
//		payment.setCity("厦门");
//		payment.setProvince("福建");
		payment.setPaymentStatus(Constants.STATUS_IN_PAYMENT);
		payment.setApplyTime(new Date());
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试保存账号信息<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2013-1-4
	 * void
	 */
	
	@Test
	public void testSavePayment(){
		this.listItemDao.savePayment(payment);
		
	}
	/**
	 * 
	 * <p>
	 * Description:更新付款单<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2013-1-5
	 * void
	 */
	public void testUpdatePayment(){
		Payment p=new Payment();
		Payment pay=this.listItemDao.savePayment(payment);
		p.setId(pay.getId());
		p.setPaymentStatus(Constants.STATUS_PAID);
		this.listItemDao.updatePayment(p);
	}
	/**
	 * 
	 * <p>
	 * Description:根据理赔id及付款状态查询付款单<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2013-1-5
	 * void
	 */
	public void testFindPaymentByRecompenseIdAndStatus(){
		//TODO
		//this.listItemDao.findPaymentByRecompenseIdAndStatus("135648974", Constants.stat);

	}
	@Test
	public void testFindPaymentHistoryByRecompenseId(){
		this.listItemDao.searchPaymentByRecompenseId("400198823");
		
	}

}
