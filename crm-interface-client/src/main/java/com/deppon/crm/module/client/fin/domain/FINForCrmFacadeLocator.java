package com.deppon.crm.module.client.fin.domain;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import com.deppon.crm.module.client.common.InterfaceAddressConfigProvider;
import com.deppon.crm.module.client.common.domain.InterfaceAddressConfig;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.fin.login.EASLoginProxy;
import com.deppon.fin.login.EASLoginProxyServiceLocator;
import com.deppon.fin.login.WSContext;
import com.deppon.fin.payment.WSFinanceForCrmFacadeSrvProxy;
import com.deppon.fin.payment.WSFinanceForCrmFacadeSrvProxyServiceLocator;

public class FINForCrmFacadeLocator extends WSFinanceForCrmFacadeSrvProxyServiceLocator{
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(FINServiceLocator.class);
	// 业务服务编码
	private String serviceCode;
	// 登录服务编码
	private String loginCode;
	// 地址获取工具
	private InterfaceAddressConfigProvider provider;
	private WSContext ws;

	/**
	 * @作者：罗典
	 * @时间：2012-10-18
	 * @描述：EAS登录
	 * */
	private void login() {
		InterfaceAddressConfig config = provider.getAddressMap(loginCode);
		EASLoginProxyServiceLocator eas = new EASLoginProxyServiceLocator();
		EASLoginProxy easlPS;
		try {
			easlPS = eas.getEASLogin(new URL(config.getAddress()));
			ws = easlPS.login(config.getUserName(), config.getPassword(),
					config.getSlnName(), config.getDcName(),
					config.getLanguage(), config.getDbType());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (ws == null) {
			try {
				logger.error(JsonMapperUtil.writeValue(config));
			} catch (CrmBusinessException e) {
				e.printStackTrace();
			}
			throw new RuntimeException(
					"fin_system login fail,please check the config.....");
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-10-18
	 * @描述：重写方法
	 * */
	@Override
	public WSFinanceForCrmFacadeSrvProxy getWSFinanceForCrmFacade()
			throws ServiceException {
		// 登录FIN系统
		login();
		InterfaceAddressConfig config = provider.getAddressMap(serviceCode);
		super.setWSFinanceForCrmFacadeEndpointAddress(config.getAddress());
		return super.getWSFinanceForCrmFacade();
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public InterfaceAddressConfigProvider getProvider() {
		return provider;
	}

	public void setProvider(InterfaceAddressConfigProvider provider) {
		this.provider = provider;
	}

}
