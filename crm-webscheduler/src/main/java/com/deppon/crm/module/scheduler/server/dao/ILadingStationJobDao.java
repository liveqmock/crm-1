/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ILadingStationJobDao.java
 * @package com.deppon.crm.module.scheduler.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-6-1
 */
package com.deppon.crm.module.scheduler.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.scheduler.shared.domain.LadingStation;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ILadingStationJobDao.java
 * @package com.deppon.crm.module.scheduler.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-6-1
 */

public interface ILadingStationJobDao {
	// 根据ERP ID 查询CRM中的数据
	public LadingStation queryCRMLadingStationByErpID(String id);
	
	// 保存CRM网点表信息
	public boolean createLadingStation(LadingStation ls);
	
	// 更新CRM网点表信息
	public boolean updateLadingStation(LadingStation ls);
	
	// 根据日期获取ERP网点信息
	public List<LadingStation> queryERPLadingStationByDate(Date date);

	// 根据日期获取ERP部门信息
	public List<LadingStation> queryERPBusinessDeptByDate(Date date);

	// 根据日期获取ERP部门信息-车队
	public List<LadingStation> queryERPBusinessDeptCarByDate(Date date);
}
