package com.deppon.crm.module.common.server.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.alibaba.fastjson.JSONObject;
import com.deppon.crm.module.common.server.dao.IMessageDao;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.common.shared.domain.MessageMap;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceCommand.OutputType;
import com.mongodb.MapReduceOutput;
/**
 * MessageDao
 * 修改：张振伟
 * 日期：2013-8-1
 * 修改内容：将此类基于关系型oracle的访问操作 更改为NOSQL mongodb的访问操作
 * 
 * 
 * */
public class MessageDao extends iBatis3DaoImpl  implements IMessageDao  {
	private static final String PACKAGENAME="com.deppon.crm.module.common.shared.domain.Message.";
	private static final String GETMESSAGECOUNTOFUSRS="getCountMessageOfUsers";
	private static final String GETMESSAGECOUNTOFDEPTS="getMessageCountOfDepts";
	private static final String USERPERFIX="U_";
	private static final String DEPTPERFIX="D_";
	private static final String LASTMODIFYTIME="selectLastModifyTime";
	private static final String GETCONUTMESSAGEBYUSER="getCountMessageByUser";
	private static final String GETCONUTMESSAGEBYDEPT="getMessageCountByDept";
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMeaasge(String userid) {

		List<Message> list = null;

		if (userid != null && !"".equals(userid)) {
			list = getSqlSession()
					.selectList(
							"com.deppon.crm.module.common.shared.domain.Message.getMeaasge",
							userid);
		}
		if(list==null){
			list = new ArrayList<Message>();
		}
		return list;
	}

	@Override
	public void addMessage(Message bill) {
		if (bill != null) {
			this.getSqlSession()
					.insert("com.deppon.crm.module.common.shared.domain.Message.addMessage",
							bill);
		}

	}

	
	public void addSuperMessage(String superMsg, String msgType){
		if (superMsg != null) {
			Map map = new HashMap();
			map.put("superMsg", superMsg);
			map.put("msgType", msgType);
			this.getSqlSession()
					.insert("com.deppon.crm.module.common.shared.domain.Message.addSuperMessage",
							map);
		}
	}

	@Override
	public List<Message> getMeaasgeByUserAndDept(String userid, String deptId) {
		List<Message> list = null;
		if (userid != null && deptId != null && !"".equals(userid)
				&& !"".equals(deptId)) {
			Map map = new HashMap();
			map.put("userid", userid);
			map.put("deptId", deptId);
			list = getSqlSession()
					.selectList(
							"com.deppon.crm.module.common.shared.domain.Message.getMeaasgeByUserAndDept",
							map);
		}
		if(list==null){
			list = new ArrayList<Message>();
		}
		return list;
	}

	@Override
	public void deleteMessage(String id) {
		getSqlSession()
				.delete("com.deppon.crm.module.common.shared.domain.Message.deleteById",
						id);
	}

	@Override
	public void deleteMessageByType(String taskType) {
		getSqlSession()
				.delete("com.deppon.crm.module.common.shared.domain.Message.deleteByType",
						taskType);
	}

	@Override
	public void deleteMessageByInvalid(String taskType) {
		getSqlSession()
				.delete("com.deppon.crm.module.common.shared.domain.Message.deleteByInvalidDate",
						taskType);
	}

	@Override
	public void addMessageList(List<Message> messageList) {
		if (messageList != null && messageList.size() > 0) {
			for (Message message : messageList) {
				addMessage(message);
			}
		}
	}

	// -----------------------------鏂�--------------------------------------------------
	@Override
	public List<Message> getMeaasgeByUserAndDeptExceptMessage(String userid,
			String deptId) {
		List<Message> list = null;
		if (userid != null && deptId != null && !"".equals(userid)
				&& !"".equals(deptId)) {
			Map map = new HashMap();
			map.put("userid", userid);
			map.put("deptId", deptId);
			list = getSqlSession()
					.selectList(
							"com.deppon.crm.module.common.shared.domain.Message.getMeaasgeByUserAndDeptExceptMessage",
							map);
		}
		if(list==null){
			list = new ArrayList<Message>();
		}
		return list;
	}
	
	@Override
	public List<Message> getMeaasgeByUserAndDeptOnlyOrder(String userid,
			String deptId) {
		List<Message> list = null;
		if (userid != null && deptId != null && !"".equals(userid)
				&& !"".equals(deptId)) {
			Map map = new HashMap();
			map.put("userid", userid);
			map.put("deptId", deptId);
			list = getSqlSession()
					.selectList(
							"com.deppon.crm.module.common.shared.domain.Message.getMeaasgeByUserAndDeptOnlyOrder",
							map);
		}
		if(list==null){
			list = new ArrayList<Message>();
		}
		return list;
	}

	@Override
	public List<Message> getMeaasgeByUserAndDeptOnlyMessage(String userid,
			String deptId, int start, int limit) {
		RowBounds bound = new RowBounds(start, limit);
		List<Message> list = null;
		if (userid != null && deptId != null && !"".equals(userid)
				&& !"".equals(deptId)) {
			Map map = new HashMap();
			map.put("userid", userid);
			map.put("deptId", deptId);
			list = getSqlSession()
					.selectList(
							"com.deppon.crm.module.common.shared.domain.Message.getMeaasgeByUserAndDeptOnlyMessage",
							map, bound);
		}
		if(list==null){
			list = new ArrayList<Message>();
		}
		return list;
	}

	@Override
	public Integer getCountMessage(String userid, String deptId) {
		Integer totalCount = 0;
		if (userid != null && deptId != null && !"".equals(userid)
				&& !"".equals(deptId)) {
			Map map = new HashMap();
			map.put("userid", userid);
			map.put("deptId", deptId);
			totalCount = (Integer) getSqlSession()
					.selectOne(
							"com.deppon.crm.module.common.shared.domain.Message.getCountMessage",
							map);
		}
		return totalCount;
	}

	@Override
	public void deleteMessages(List<String> messageIds) {
		Map map = new HashMap();
		map.put("messageIds", messageIds);
		getSqlSession()
				.delete("com.deppon.crm.module.common.shared.domain.Message.deleteByIds",
						map);
	}

	@Override
	public List<Message> getMessageForErp() {
		List<Message> list = getSqlSession()
				.selectList(
						"com.deppon.crm.module.common.shared.domain.Message.getMessageForErp");
		return list;
	}

	/**
	 * 
	 * 浣�鑰咃細寮犳尟 
	 * 淇敼鏃堕棿锛�013-6-28
	 * 鎻忚堪锛氭坊鍔爂etMessageOfUserAndDpet()鏂规硶锛岃幏鍙栫敤鎴蜂唬鍔炰互鍙婇儴闂ㄤ笅鐨勪唬鍔�
	 * 
	 *@return Map
	 *
	 * */
	public Map<String,Map<String,Integer>> getMessageOfUserAndDpet() {
		List<MessageMap> UmessageMapList=new ArrayList<MessageMap>();
		List<MessageMap> DmessageMapList=new ArrayList<MessageMap>();
		UmessageMapList=getSqlSession().selectList(PACKAGENAME+GETMESSAGECOUNTOFUSRS);
		DmessageMapList=getSqlSession().selectList(PACKAGENAME+GETMESSAGECOUNTOFDEPTS);
		Map<String,Map<String,Integer>> ResultMap=new HashMap<String,Map<String,Integer>>();
		ResultMap=putMap(UmessageMapList,ResultMap,USERPERFIX);
		ResultMap=putMap(DmessageMapList,ResultMap,DEPTPERFIX);

		return ResultMap;
	}
	/**
	 * 
	 * author ZhangZW
	 * UPDATE TIME：2013-11-07
	 * get message(messageType,the conut of the type) from t_crm_todo by the userid and the deptid of the user
	 * para:userid deptid
	 * 
	 *@return Map
	 *
	 * */
	public Map<String,Integer> getMessageByUserOrDept(String tid,String prfix ){
		List<MessageMap> messageMapList=new ArrayList<MessageMap>();
		if(USERPERFIX==prfix||USERPERFIX.equals(prfix)){
			messageMapList=getSqlSession().selectList(PACKAGENAME+GETCONUTMESSAGEBYUSER,tid);
		}else if(DEPTPERFIX==prfix||DEPTPERFIX.equals(prfix)) {
			messageMapList=getSqlSession().selectList(PACKAGENAME+GETCONUTMESSAGEBYDEPT,tid);
		}
		Map<String,Integer> mapTmp=new HashMap<String,Integer>();
		for(MessageMap messsage:messageMapList){
			mapTmp.put(messsage.getMessageType(), messsage.getMessageCount());
		}
		return mapTmp;
	}
	/**
	 * 
	 * author:ZhangZW
	 * modifytime锛�013-06-28
	 * description锛氬皢鏁版嵁搴撳彇鍑虹殑缁撴灉闆嗗瓨鏀惧湪map涓�
	 * para:messageMapList缁撴灉闆�ResultMap鐩爣瀛樺偍map keyPerfix map key閿墠缂�鍖哄垎鏄敤鎴峰拰閮ㄩ棬
	 * result:Map<String,Map<String,Integer>>
	 * */
	private Map<String,Map<String,Integer>> putMap(List<MessageMap> messageMapList,Map<String,Map<String,Integer>> ResultMap,String keyPerfix){
		String messageType="";
		Integer messagCount=0;
		MessageMap temp=null;
		String perFix=keyPerfix;
		for(int i=0;i<messageMapList.size();i++){
			Map<String,Integer> tmp=null;
			//鑾峰彇瀵硅薄淇℃伅
			temp=messageMapList.get(i);
			perFix+=temp.getLinkId();
			messagCount=temp.getMessageCount();
			messageType=temp.getMessageType();
			if(ResultMap.containsKey(perFix)){
				tmp=ResultMap.get(perFix);
				//鍒ゆ柇鏄惁鑾峰彇鐨刴ap涓虹┖鎴栨棤鏁版嵁
				if(tmp.size()<=0||null==tmp){
					tmp=new HashMap<String,Integer>();
				}
				//濡傛灉缁撴灉闆嗕腑鎵惧埌鍚屼竴涓敤鎴风殑鍚屼竴涓被鍨嬬殑娑堟伅锛屽垯杩涜绱姞
				if(tmp.containsKey(messageType))
				{	messagCount+=tmp.get(messageType);
				    tmp.put(messageType, messagCount);
					ResultMap.put(perFix, tmp);
				}else{//濡傛灉绗竴娆″湪缁撴灉闆嗘壘鍒版煇涓敤鎴风殑鏌愪釜绫诲瀷
					tmp.put(messageType, messagCount);
					ResultMap.put(perFix,tmp); 
				}
			}else{//鎵惧埌鏂扮敤鎴凤紝娣诲姞鍒癿ap涓�
				tmp=new HashMap<String,Integer>();
				tmp.put(messageType, messagCount);
				ResultMap.put(perFix, tmp);
			}
			perFix=keyPerfix;
		}
		return ResultMap;
	}
	/**
	 * 
	 * 浣�鑰咃細寮犳尟 
	 * 淇敼鏃堕棿锛�013-6-27 娣诲姞getLastModifyTime()鏂规硶锛岃幏鍙栨渶杩戜慨鏀规椂闂�
	 *@return Date
	 * */
	public Date getLastModifyTime() {
 		return (Date) getSqlSession().selectOne(PACKAGENAME+LASTMODIFYTIME);
	}
}
