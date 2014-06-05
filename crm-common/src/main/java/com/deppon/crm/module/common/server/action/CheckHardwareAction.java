package com.deppon.crm.module.common.server.action;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.common.server.manager.ICheckHardwareManager;
import com.deppon.crm.module.common.shared.domain.HardWareInfo;
import com.deppon.foss.framework.server.components.security.SecurityNonCheckRequired;
import com.deppon.foss.framework.server.web.action.AbstractAction;

public class CheckHardwareAction extends AbstractAction {

	private HardWareInfo hardWareInfo;

	public void setHardWareInfo(HardWareInfo hardWareInfo) {
		this.hardWareInfo = hardWareInfo;
	}
	
	private String  HostName;
	private String  CPUNumber;
	private String MACAddress;
	
	private String s;
	private String v;
	
	private String version;

	public void setVersion(String version) {
		this.version = version;
	}

	public void setS(String s) {
		this.s = s;
	}

	public void setV(String v) {
		this.v = v;
	}

	public void setHostName(String hostName) {
		HostName = hostName;
	}

	public void setCPUNumber(String cPUNumber) {
		CPUNumber = cPUNumber;
	}

	public void setMACAddress(String mACAddress) {
		MACAddress = mACAddress;
	}

	// 根据硬件信息生成的TOKEN
	private String token;

	public String getToken() {
		return token;
	}

	// 客户端提交的Token
	private String requestToken;

	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}

	private ICheckHardwareManager iCheckHardwareManager = null;

	public void setICheckHardwareManager(
			ICheckHardwareManager iCheckHardwareManager) {
		this.iCheckHardwareManager = iCheckHardwareManager;
	}

	/**
	 * <p>
	 * Description:错误代码  |    含义<br />
	 *  				200	    |    正常
	 *                             201           | 硬件信息未审核
	 *                             202           | 硬件信息不存在
	 *                             300           | 其它服务器异常 
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-7-10
	 * @return
	 * String
	 */
	@SecurityNonCheckRequired
	public String checkHardwareInfo() {

		this.hardWareInfo =  new HardWareInfo();
		this.hardWareInfo.setCPUNumber(StringUtils.trim(this.CPUNumber));
		try {
			this.HostName = new String(this.HostName.getBytes("ISO8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//取消主机名
		
		this.hardWareInfo.setHostName(StringUtils.upperCase(StringUtils.trim(this.HostName)));
		
		//this.hardWareInfo.setMACAddress(StringUtils.trim(this.MACAddress));
		
		this.token = this.iCheckHardwareManager
				.checkHardwareInfo(hardWareInfo,s,v,version);

		return this.SUCCESS;
	}
	
}
