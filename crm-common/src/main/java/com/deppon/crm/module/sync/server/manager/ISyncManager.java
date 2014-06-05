package com.deppon.crm.module.sync.server.manager;
import java.util.Map;
public interface ISyncManager {
	public int check(Map<String,String> entity);
	String CHANGETYPE="changeType";
	String MAINCODE="mainCode";
	String DEPSTANDCODE="standCode";
	String ID="mainId";
	String POSITION="position";
	String PARENTID="parentOrgId";
	String PARENTSTANDCODE="parentOrgBenchmarkCode";
	String ORGID="orgId";
	String INSERTDES="INSERT";
	String UPDATEDES="UPDATE";
	String DELETEDES="DELETE";
	int SUCCESS=1;
	int FAILD=0;
}
