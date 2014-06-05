package com.deppon.crm.module.common.server.manager.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.deppon.crm.module.common.server.manager.ICheckHardwareManager;
import com.deppon.crm.module.common.server.service.ICheckHardWareService;
import com.deppon.crm.module.common.server.service.IHardWareTokenService;
import com.deppon.crm.module.common.server.util.GenerateToken;
import com.deppon.crm.module.common.shared.domain.CheckResultInfo;
import com.deppon.crm.module.common.shared.domain.HardWareInfo;
import com.deppon.crm.module.common.shared.domain.HardWareToken;
import com.deppon.crm.module.frameworkimpl.server.cache.HardWareTokenCache;
import com.deppon.foss.framework.cache.CacheManager;

public class CheckHardwareManager implements ICheckHardwareManager {

	Logger log = LogManager.getLogger(getClass());
	
	private ICheckHardWareService iCheckHardWareService = null;
	
	
	public ICheckHardWareService getiCheckHardWareService() {
		return iCheckHardWareService;
	}


	public void setiCheckHardWareService(ICheckHardWareService iCheckHardWareService) {
		this.iCheckHardWareService = iCheckHardWareService;
	}

	private IHardWareTokenService iHardWareTokenService = null;

	public IHardWareTokenService getiHardWareTokenService() {
		return iHardWareTokenService;
	}


	public void setiHardWareTokenService(IHardWareTokenService iHardWareTokenService) {
		this.iHardWareTokenService = iHardWareTokenService;
	}

	public final static String RESULT_NORMAL ="200_split_";
	public final static String RESULT_NO_EXISTS_AUDIT_HARDWARE_INFO="202_split_";
	public final static String RESULT_NO_AUDIT="201_split_";
	public final static String RESULT_OTHER_SERVER_ERROR = "300_split_";
	public final static String RESULT_VERSION_ERROR = "203_split_";
	public final static String VERSION = "V1.1";

	/**
	 * 错误代码  |    含义<br />
	 *  				200	    |    正常
	 *                             201           | 硬件信息未审核
	 *                             202           | 硬件信息不存在
	 *                             300           | 其它服务器异常 
	 *                             
	 *                             返回格式：resultCode_split_content 。
	 *                             例如：正常返回 ：200_split_131312398jkjjj213lk
	 *                             硬件信息不存在错误返回 202_split_
	 *                             硬件信息不审核201_split_
	 *                             其它错误 300_split_NullPointException
	 */
	@Override
	public String checkHardwareInfo(HardWareInfo info,String s,String v,String version) {
		if(null == info || null == info.getHostName() 
				|| "".equals(info.getHostName()) 
				||null == s || "".equals(s)
				||null == v || "".equals(v)){
			return RESULT_OTHER_SERVER_ERROR + "ERROR_Code:300";
		}
		//验证访问合法性
		
		if(!v.equals(GenerateToken.getTimeToken(s))){
			return RESULT_OTHER_SERVER_ERROR + "ERROR_Code:999";
		}
		
		if(!VERSION.equals(version)){
			
			return RESULT_VERSION_ERROR ;
		}
		
		HardWareInfo hwInfo = this.iCheckHardWareService.getHardWareInfoByClient(info);
		if(hwInfo == null ){
			return RESULT_NO_EXISTS_AUDIT_HARDWARE_INFO;
		}
		if(!hwInfo.isFalg()){
			return RESULT_NO_AUDIT;
		}
		String token = GenerateToken.generateTokenByHardWareInfo(hwInfo);
		//保存进缓存中
		((HardWareTokenCache)(CacheManager.getInstance().getCache(HardWareTokenCache.class.getName()))).set(token, token);
		//this.saveToken(token);
		return RESULT_NORMAL + token;
	}
	
	public void saveToken(String token){
		HardWareToken hardWareToken = new HardWareToken();
		hardWareToken.setToken(token);
		hardWareToken.setUsed(false);
		hardWareToken.setExpireTime(this.getExpireTime());
		this.iHardWareTokenService.save(hardWareToken);
	}

	public Date getExpireTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, 5);
		return calendar.getTime();
		
	}

	private static final String TOKEN_NULL_CODE = "1001";
	private static final String TOKEN_NOT_EXISTS="1002";
	private static final String TOKEN_IS_USED="1003";
	private static final String TOKEN_IS_EXPIRE="1004";
	/**
	 * <p>
	 * Description:根据客户端提交的Token进行校验<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-7-8
	 * @param token
	 * @return
	 * boolean
	 */
	@Override
	public CheckResultInfo checkHardWareToken(String  token) {
		CheckResultInfo result = new CheckResultInfo();
		result.setResult(false);
		result.setMessage("2000");
		if(null == token || "".equals(token)){
			result.setResult(false);
			result.setMessage(GenerateToken.generateErrorHTML(TOKEN_NULL_CODE));
			return result;
		}
		//从缓存中取得token
		@SuppressWarnings("unchecked")
        String tok = (String)CacheManager.getInstance().getCache(HardWareTokenCache.class.getName()).get(token);
		if(null == tok || "".equals(tok)){
            log.warn("Not exists requestToken:"+ token);
            result.setResult(false);
            result.setMessage(GenerateToken.generateErrorHTML(TOKEN_NOT_EXISTS));
            return result;
        }
		result.setResult(true);
        result.setMessage("");
        return result;
	}

	public boolean checkIsExpire(HardWareToken token){
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());		
		Calendar expire = Calendar.getInstance();
		expire.setTime(token.getExpireTime());
		return now.after(expire);
		
	}
}
