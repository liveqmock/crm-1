package com.deppon.crm.module.duty.server.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.duty.server.dao.IInformUserDao;
import com.deppon.crm.module.duty.server.service.IInformUserService;
import com.deppon.crm.module.duty.shared.domain.InformUser;
@Transactional
public class InformUserServiceImpl implements IInformUserService {
	
	private IInformUserDao informUserDao;
	/**
	 * @param informUserDao the informUserDao to set
	 */
	public void setInformUserDao(IInformUserDao informUserDao) {
		this.informUserDao = informUserDao;
	}
	/**
	 * <p>
	 * Description:根据条件查询通知对象<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2013-3-6
	 * @param informUser
	 * @return List<InformUser>
	 */
	@Override
	public List<InformUser> searchInformUser(InformUser informUser) {
		return informUserDao.searchInformUser(informUser);
	}
	/**
	 * <p>
	 * Description:根据ID查询通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fid
	 * @return List
	 */
	@Override
	public InformUser selectInformUserByPrimaryKey(BigDecimal fid) {
		return informUserDao.selectInformUserByPrimaryKey(fid);
	}
	/**
	 * <p>
	 * Description:修改通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param informUser
	 * @return void
	 */
	@Override
	public void updateInformUser(InformUser informUser) {
		informUserDao.updateInformUser(informUser);
	}
	/**
	 * <p>
	 * Description:批量删除通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fids
	 * @return void
	 */
	@Override
	public void deleteInformUsers(List<String> fids) {
		informUserDao.deleteInformUsers(fids);
	}
	/**
	 * <p>
	 * Description:根据责任ID删除通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param dutyId
	 * @return void
	 */
	@Override
	public void deleteInformUsersByDutyId(String dutyId) {
		informUserDao.deleteInformUsersByDutyId(dutyId);
	}
	/**
	 * <p>
	 * Description:插入通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param informUser
	 * @return void
	 */
	@Override
	public void insertInformUser(InformUser informUser) {
		informUserDao.insertInformUser(informUser);
	}

}
