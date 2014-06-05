package com.deppon.crm.module.client.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.deppon.crm.module.client.esb.domain.ClientHeader;
import com.deppon.crm.module.client.esb.domain.ServerHeader;
import com.deppon.foss.crm.ESBHeader;

/**
 * @作者：罗典
 * @时间：2012-11-6
 * @描述：接口上下文
 * */
public class ClientContext {
	private final static ThreadLocal<ESBHeader> esbHeaders = new ThreadLocal<ESBHeader>();
	private final static ThreadLocal<ClientHeader> clientHeaders = new ThreadLocal<ClientHeader>();
	private final static ThreadLocal<ServerHeader> serverHeaders = new ThreadLocal<ServerHeader>();
	private final static ThreadLocal<String> loggerRecords = new ThreadLocal<String>();
	private static  ExecutorService pool = Executors.newFixedThreadPool(20);
	
	public static void setESBHeader(ESBHeader esbHeader) {
		esbHeaders.set(esbHeader);
	}
	
	public static ExecutorService getPool() {
		return pool;
	}

	public static ESBHeader getESBHeader() {
		return esbHeaders.get();
	}
	public static void setLogInfo(String logInfo){
		loggerRecords.set(logInfo);
	}
	
	public static String getLogInfo(){
		return loggerRecords.get();
	}
	
	public static void setClientHeader(ClientHeader clientHeader) {
		clientHeaders.set(clientHeader);
	}

	public static ClientHeader getClientHeader() {
		return clientHeaders.get();
	}

	public static void setServerHeader(ServerHeader serverHeader) {
		serverHeaders.set(serverHeader);
	}

	public static ServerHeader getServerHeader() {
		return serverHeaders.get();
	}

	public static void removeServerHeader() {
		serverHeaders.remove();
	}

	public static void removeClientHeader() {
		clientHeaders.remove();
	}
}
