package com.deppon.crm.module.uums.server.service.Imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.sync.shared.SyncException;
import com.deppon.crm.module.uums.server.dao.IOrgDao;
import com.deppon.crm.module.uums.server.dao.IPositionDao;
import com.deppon.crm.module.uums.server.dao.IUserDao;
import com.deppon.crm.module.uums.server.service.IUserService;
import com.deppon.crm.module.uums.shared.domain.UserInfo;

public class UserService implements IUserService{
	private IUserDao userDao;
	private IOrgDao orgDao;	
	private IPositionDao positionDao;
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public IOrgDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(IOrgDao orgDao) {
		this.orgDao = orgDao;
	}

	public IPositionDao getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(IPositionDao positionDao) {
		this.positionDao = positionDao;
	}

	@Override
	public int insert(UserInfo entity) {	
		return userDao.insert(entity);
	}
	@Override
	public int updateById(UserInfo entity, String userId) {
			 return userDao.updateById(entity,userId);
		
	}

	@Override
	public int updateByCode(UserInfo entity, String userCode) {
		
			 return userDao.updateByCode(entity,userCode);
		
	}

	@Override
	public int deleteByUserid(String userId) {
		if(StringUtils.isEmpty(userId)){
				throw new SyncException("UserService.deleteByUserid 无效userId");
		}
		int res=userDao.deleteByUserid(userId);
		return res;
	}

	@Override
	public int deleteByUserCode(Map<String,String> userMap) {
		String userCode=userMap.get("userCode");
		if(StringUtils.isEmpty(userCode)){
				throw new SyncException("UserService.deleteByUserCode 无效userCode");	
		}
		int res=userDao.deleteByUserCode(userMap);
		return res;
	}

	@Override
	public UserInfo searchByUserId(String userid) {
		if(StringUtils.isEmpty(userid)){
				throw new SyncException("UserService.searchByUserId 无效userId");
		}
		UserInfo res=userDao.searchByUserId(userid);
		return res;
	}
	@Override
	public UserInfo searchByUserCode(String userCode) {
		if(StringUtils.isEmpty(userCode)){
				throw new SyncException("UserService.searchByUserCode 无效userCode");
		}
		UserInfo res=userDao.searchByUserCode(userCode);
		return res;
	}
	@Override
	public int returnById(String userid) {
		int res=userDao.returnById(userid);
		return res;
	}
	@Override
	public int returnByCode(UserInfo entity) {
		int res=userDao.returnByCode(entity);
		return res;
	}
}
