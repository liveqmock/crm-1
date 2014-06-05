package com.deppon.crm.module.interfaces.workflow.impl.validateR;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.deppon.fin.dept.EASLoginProxy;

public class Recompense_VerifyInfoTest {

	public static void main(String[] args) {
		EASLoginProxyServiceLocator pserlogin=new EASLoginProxyServiceLocator();
		try{
			EASLoginProxy serlog=pserlogin.getEASLogin(new URL("http://192.168.20.79:9902/ormrpc/services/EASLogin"));
			System.out.println("登录成功：>>>>>>>>>"+serlog.login("user", "deppon70", "eas", "2058", "L2", 2).getSessionId());
		}
		catch(MalformedURLException e){
			e.printStackTrace();
		}catch(ServiceException e){
			e.printStackTrace();
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}
}
