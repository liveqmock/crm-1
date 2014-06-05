package com.deppon.crm.module.sysmail.util;

import com.deppon.crm.module.sysmail.shared.domain.MailAccount;
import com.deppon.crm.module.sysmail.shared.domain.MailGroup;

public class SysMailConfigUtil {
	public static String mailAccountId = "123456789";
	public static String mailGrouopId = "123456789";
	public static MailAccount createMailAccount(){
		MailAccount ma = new MailAccount();
		ma.setId(mailAccountId);
		ma.setEmpCode("043260");
		ma.setEmailAddress("dpsyj@deppon.com");
		ma.setReceiverName("苏玉军");
		ma.setValid(true);
		ma.setCreateUser("suyujun");
		ma.setModifyUser("suyujun");
		return ma;
	}
	
	public static MailGroup createMailGroup(){
		MailGroup mg = new MailGroup();
		mg.setId(mailGrouopId);
		mg.setCreateUser("suyujun");
		mg.setGroupCode("G-COMPLAINT");
		mg.setGroupName("工单预警");
		return mg;
	}
}
