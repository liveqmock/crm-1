package com.deppon.crm.module.complaint.server.dao;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.shared.domain.Bulletin;
/**
 * 
 * <p>
 * Description:通报对象<br />
 * </p>
 * @title IBulletinDao.java
 * @package com.deppon.crm.module.complaint.server.dao 
 * @author ouy
 * @version 0.1 2012-4-17
 */
public interface IBulletinDao  {
	/**
	 * <p>
	 * Description:通过工单id查通报对象<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-17
	 * @return
	 * List<Complaint>
	 */
	 List<Bulletin> searchBulletinByCompId(BigDecimal compId);
	/**
	 * <p>
	 * Description:通过工单id查通报对象(仅显示)<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-8-14
	 * @param compId
	 * @return
	 * List<Bulletin>
	 */
	 List<Bulletin> searchBulletinToProcByCompId(BigDecimal compId);
	/**
	 * <p>
	 * Description:  保存通知对象<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-20
	 * @param bulltins
	 * void
	 */
	 int saveBulletin(List<Bulletin> bulltins);
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-8-10
	 * @param complaintId
	 * void
	 */
	 void deleteOldBulletinsByCompId(BigDecimal complaintId);
}
