package com.deppon.crm.module.frameworkimpl.server.service.impl;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.frameworkimpl.server.dao.ITokenDao;
import com.deppon.crm.module.frameworkimpl.server.service.ITokenService;
import com.deppon.crm.module.frameworkimpl.shared.exception.TokenException;
import com.deppon.crm.module.frameworkimpl.shared.exception.TokenExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

@Transactional()
public class TokenService implements ITokenService {

	private ITokenDao tokenDao;

	/**
	 * <p>
	 * Description:保存令牌对象<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-2-25
	 * @param key
	 * @param value
	 * @param timestamp
	 * void
	 * @see com.deppon.crm.module.frameworkimpl.server.service.ITokenService#save(java.lang.String, java.lang.String, java.sql.Timestamp)
	 */
	@SuppressWarnings("serial")
	@Override
	public void save(String key, String value, Timestamp validTime) {
		if(StringUtils.isEmpty(key)){
			TokenException e = new TokenException(TokenExceptionType.TokenIdNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		if(StringUtils.isEmpty(value)){
			TokenException e = new TokenException(TokenExceptionType.TokenIsNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		if(validTime == null){
			TokenException e = new TokenException(TokenExceptionType.TokenValidTimeNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		tokenDao.insert(key, value, validTime);
	}
	
	@SuppressWarnings("serial")
	public void save(String id, String token) {
		if(id==null||"".equals(id.trim())){
			TokenException e = new TokenException(TokenExceptionType.TokenIdNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		if(token==null||"".equals(token.trim())){
			TokenException e = new TokenException(TokenExceptionType.TokenIsNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		tokenDao.insert(id.trim(),token.trim());
	}

	@SuppressWarnings("serial")
	@Transactional(readOnly=true)
	public String queryById(String id) {
		if(id==null||"".equals(id.trim())){
			TokenException e = new TokenException(TokenExceptionType.TokenIdNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		String token = tokenDao.getById(id);
		return token;
	}
	/**
	 * 删除过期时间之前的token凭证
	 * @author suyujun
	 * @param time
	 * @return void
	 * @see com.deppon.crm.module.frameworkimpl.server.service.ITokenService#removeAll(java.sql.Timestamp)
	 */
	@Override
	public void removeAll(Timestamp time) {
		tokenDao.removeAll(time);
	}
	
	@SuppressWarnings("serial")
	public void removeById(String id) {
		if(id==null||"".equals(id.trim())){
			TokenException e = new TokenException(TokenExceptionType.TokenIdNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		tokenDao.deleteById(id);
	}

	public void removeAll() {
		tokenDao.deleteAll();
	}

	public ITokenDao getTokenDao() {
		return tokenDao;
	}

	public void setTokenDao(ITokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}
}
