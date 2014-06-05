package com.deppon.crm.module.complaint.server.service;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.shared.domain.Bulletin;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;
/**
 * 
 * <p>
 * Description:通报对象<br />
 * </p>
 * @title IComplaintService.java
 * @package com.deppon.crm.module.complaint.server.service 
 * @author ouy
 * @version 0.1 2012-4-12
 */
public interface IBulletinService {
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
	 void saveBulletin(List<Bulletin> bulltins);

	/**
	 * <p>
	 * 根据工单Id删除原来的通知对象<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-8-10
	 * @param complaintId
	 * void
	 */
	 void deleteOldBulletinsByCompId(BigDecimal complaintId);
}
