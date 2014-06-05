package com.deppon.crm.module.marketing.server.service.impl;
import com.deppon.crm.module.marketing.server.dao.IMaterializedView;
import com.deppon.crm.module.marketing.server.dao.IMaterializedViewPS;
import com.deppon.crm.module.marketing.server.service.IMaterviewService;
/**   
 * <p>
 * Description:客户数据的SERVICE更新接口实现类<br />
 * </p>
 * @title MaterviewServiceImpl.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author ZHANGZW
 * @version 0.1 2013-05-26
 */
public class MaterviewServiceImpl implements IMaterviewService {
	private IMaterializedViewPS mvPs;
	private IMaterializedView   mv;
	 /** 
	 * <p>
	 * 潜散客户信息需要每天更新一次视图T_CRM_PSVISUALMARKET<br/>
	 * </p>
	 * 
	 * @author ZHANGZW
	 * @version 0.1 2013-04-26
	 * @param endDate 
	 * @param beginDate 
	 * @return int
	 */
	public int reFreshMVPSCustomerDaily() {
		return mv.reFreshMVCuststomerDaily();
	}
	/**
	 * 
	 * <p>
	 * 固定客户信息需要每天更新一次视图T_CRM_CUSTVISUALMARKET<br/>
	 * </p>
	 * 
	 * @author ZHANGZW
	 * @version 0.1 2013-04-26
	 * @param endDate 
	 * @param beginDate 
	 * @return int
	 */
	public int reFreshMVCuststomerDaily() {
		return mvPs.reFreshMVPSCustomerDaily();
	}
	//getMvPs()
	public IMaterializedViewPS getMvPs() {
		return mvPs;
	}
	//setMvPs
	public void setMvPs(IMaterializedViewPS mvPs) {
		this.mvPs = mvPs;
	}
	//getMv()
	public IMaterializedView getMv() {
		return mv;
	}
	//setMv
	public void setMv(IMaterializedView mv) {
		this.mv = mv;
	}
}
