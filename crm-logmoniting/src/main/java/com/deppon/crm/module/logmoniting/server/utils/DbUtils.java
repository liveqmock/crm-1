package com.deppon.crm.module.logmoniting.server.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**   
 * @Description:数据封装类和转换类<br />
 * @title DbUtils.java
 * @package com.deppon.crm.module.logmoniting.server.utils 
 * @author CoCo
 * @version 0.1 2013-6-25
 */
public class DbUtils {
	
	    public static Mongo mg=null;    
	    public static DB db=null;    
	    public static DBCollection collection;
	    
	    /**
	     * 获得DBCollection对象
	     * @param dbName
	     * @param colName
	     * @return
	     */
	    public static DBCollection getDBCollection(String dbName,String colName){
	        if(mg==null){
	            try {
	                mg=new Mongo("192.168.17.105", 27017);
	            } catch (UnknownHostException e) {
	                e.printStackTrace();
	            }
	        }
	        if(db==null){
	            db=mg.getDB(dbName);
	        }
	        return db.getCollection(colName);
	    }
	    
	    /**
	     * @Description:转换成DBObject<br />
	     * @author CoCo
	     * @version 0.1 2013-6-25
	     * @param object
	     * @return DBObject
	     */
	    public static DBObject getDBObject(Object object) {
		    DBObject obj = new BasicDBObject();
		    Field[] fields = object.getClass().getDeclaredFields();
		    PropertyDescriptor pd = null;
		    for(int i=0;i<fields.length;i++) {
		        try {
		            pd = new PropertyDescriptor(fields[i].getName(),object.getClass());
		            Method m = pd.getReadMethod();
		            obj.put(fields[i].getName(), m.invoke(object));
		        } catch(Exception e) {
		            continue;
		        }
		    }
		    return obj;
		}
	    
}
