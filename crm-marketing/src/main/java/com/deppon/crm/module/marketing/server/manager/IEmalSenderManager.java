package com.deppon.crm.module.marketing.server.manager;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.WarnLostCust;

public interface IEmalSenderManager {
	/**
     * <p>
     * Description:生成流失预警客户名单邮件发送<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * @return
     */
	public void sendEmail(List<WarnLostCust> custList,String[] groupCodes);
	/**
     * <p>
     * Description:生成流失预警客户名单邮件发送<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * @return
     */
	public void sendEmail(String deptStandardCode,int level);
}
