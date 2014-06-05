package com.deppon.crm.module.complaint.server.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.server.dao.IBulletinDao;
import com.deppon.crm.module.complaint.server.service.IBulletinService;
import com.deppon.crm.module.complaint.shared.domain.Bulletin;
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
public class BulletinServiceImpl implements IBulletinService{
	private IBulletinDao bulletinDao;
	@Override
	public List<Bulletin> searchBulletinByCompId(BigDecimal compId) {
		// TODO Auto-generated method stub
		return bulletinDao.searchBulletinByCompId(compId);
	}
	
	public void saveBulletin(List<Bulletin> bulltins) {
		bulletinDao.saveBulletin(bulltins);
	}	
	
	public IBulletinDao getBulletinDao() {
		return bulletinDao;
	}
	public void setBulletinDao(IBulletinDao bulletinDao) {
		this.bulletinDao = bulletinDao;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.complaint.server.service.IBulletinService#deleteOldBulletinsByCompId(java.lang.String)
	 */
	/**
	 * <p>
	 * 根据工单Id删除原来的通知对象<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-8-10
	 * @param complaintId
	 * void
	 */
	@Override
	public void deleteOldBulletinsByCompId(BigDecimal complaintId) {
		bulletinDao.deleteOldBulletinsByCompId(complaintId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.complaint.server.service.IBulletinService#searchBulletinToShowByCompId(java.math.BigDecimal)
	 */
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
	@Override
	public List<Bulletin> searchBulletinToProcByCompId(BigDecimal compId) {
		return bulletinDao.searchBulletinToProcByCompId(compId);
	}
	
}
