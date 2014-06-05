package com.deppon.crm.module.complaint.server.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import com.deppon.crm.module.complaint.server.dao.IBulletinDao;
import com.deppon.crm.module.complaint.server.dao.IComplaintDao;
import com.deppon.crm.module.complaint.shared.domain.Bulletin;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class BulletinDaoImpl extends iBatis3DaoImpl implements IBulletinDao{
	
	private static final String NAMESPACE_BULLETIN = "com.deppon.crm.module.complaint.shared.domain.Bulletin.";
	private static final String SEARCH_BULLETION_BY_COMPID  =  "selectBulletin";
	private static final String SEARCH_BULLETION_TOPROC_BY_COMPID  =  "selectBulletinToProc";
	private static final String SAVE_BULLETIN = "insertBulletin";
	private static final String DELETE_BULLETIN_BYCOMPID="deleteOldBulletinsByCompId";
	/**
	 * 
	 * <p>
	 * Description:通过工单id查通报对象<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-17
	 * @return
	 * List<Complaint>
	 */
	@Override
	public List<Bulletin> searchBulletinByCompId(BigDecimal compid) {
		Map<String,BigDecimal> map=new HashMap<String,BigDecimal>();
		map.put("compid", compid);
		List<Bulletin> bulletinList=this.getSqlSession()
				.selectList(NAMESPACE_BULLETIN+SEARCH_BULLETION_BY_COMPID, map);
		return bulletinList;
	}
	/**
	 * <p>
	 * Description:  保存通知对象<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-20
	 * @param bulltins
	 * void
	 */
	@Override
	public int saveBulletin(List<Bulletin> bulltins) {
//		SqlSession session = this.getSqlSession(ExecutorType.BATCH);
		int successCount = 0;
		for (Bulletin bulletin: bulltins) {
//			bulletin.setFid(BigDecimal.valueOf(Long.parseLong(getSequence())));
			this.getSqlSession().insert(NAMESPACE_BULLETIN + SAVE_BULLETIN, bulletin);
			successCount++;
		}		
		return successCount;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.complaint.server.dao.IBulletinDao#deleteOldBulletinsByCompId(java.math.BigDecimal)
	 */
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-8-10
	 * @param complaintId
	 * void
	 */
	@Override
	public void deleteOldBulletinsByCompId(BigDecimal complaintId) {
		this.getSqlSession().delete(NAMESPACE_BULLETIN+DELETE_BULLETIN_BYCOMPID, complaintId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.complaint.server.dao.IBulletinDao#searchBulletinToShowByCompId(java.math.BigDecimal)
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
	public List<Bulletin> searchBulletinToProcByCompId(BigDecimal compid) {
		Map<String,BigDecimal> map=new HashMap<String,BigDecimal>();
		map.put("compid", compid);
		List<Bulletin> bulletinList=this.getSqlSession()
				.selectList(NAMESPACE_BULLETIN+SEARCH_BULLETION_TOPROC_BY_COMPID, map);
		return bulletinList;
	}
	
}
