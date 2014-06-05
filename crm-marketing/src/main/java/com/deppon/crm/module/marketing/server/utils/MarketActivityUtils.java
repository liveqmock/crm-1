package com.deppon.crm.module.marketing.server.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.esb.domain.DevelopDeptInfos;
import com.deppon.crm.module.client.esb.domain.DiscountInfo;
import com.deppon.crm.module.client.esb.domain.GoodsLine;
import com.deppon.crm.module.client.esb.domain.MarketingActivityRequest;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityDept;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityOption;
import com.deppon.crm.module.marketing.shared.domain.activity.DeptTree;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityConstance;
import com.deppon.crm.module.marketing.shared.domain.activity.Multiple;
import com.deppon.crm.module.marketing.shared.exception.MarketActivityException;
import com.deppon.crm.module.marketing.shared.exception.MarketActivityExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * Description:市场推广活动实体类<br />
 * </p>
 * @title MarketActivityUtils.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author ZhouYuan
 * @version 0.1 2014-03-07
 */
public class MarketActivityUtils {
	/**
	 * 
	 * <p>
	 * 给实体对象赋值<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param id
	 * @return int
	 */	
	public static void addValueToObject(Object o,Map<String,Object> map){
	    //获取map的iterator
		Iterator<Entry<String,Object>> it = map.entrySet().iterator();
		//创建entry引用
		Entry<String,Object> entry = null;
		//创建表达式引用
		String expression = null;
		//创建值引用
		Object value = null;
		//遍历iterator
		while(it.hasNext()){
			//获取iterator的entry对象
			entry = it.next();
			//map的key为表达式
			expression = entry.getKey();
			//map的值为要赋的值
			value = entry.getValue();
			//循环列表
			try {
				if( o instanceof List){
					for( Object item : (List<?>)o ){
						Ognl.setValue(expression,item,value);
					}
				}else if(o instanceof Object[]){ 
					for( Object item : (Object[])o ){
						//根据表达式为结果赋值
						Ognl.setValue(expression,item,value);
					}
				}else{
					Ognl.setValue(expression, o, value);
				}
			} catch (OgnlException e) {
				//新建MarketActivityException
				MarketActivityException e1 = new MarketActivityException(MarketActivityExceptionType.ognlExpressionException);
				throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
						new Object[] {}) {
				};
			}
		}
	}
	/**
	 * 
	 * <p>
	 * 转换多选条件实体<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param String[]
	 * @return List<Multiple>
	 */	
	public static List<Multiple> convertArrayToMultipleList(String[] values,String conditionType,String conditionId,String type){
		//新建返回List
		List<Multiple> multiples = new ArrayList<Multiple>();
		if(values == null || values.length<=0){
			return multiples;
		}
		//新建多选对象
		Multiple multiple = null;
		//循环字符串数组
		for( String value : values ){
			//新建实体
			multiple = new Multiple();
			//给关联ID赋值
			multiple.setConditionId(conditionId);
			//给关联类型赋值
			multiple.setConditionType(conditionType);
			//给类型赋值
			multiple.setType(type);
			//给值赋值
			multiple.setValue(value);
			//将实体添加到列表中
			multiples.add(multiple);
		}
		return multiples;
	}
	/**
	 * 
	 * <p>
	 * 生成市场推广活动编码前两位<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param MarketActivity ma
	 * @return String 
	 */
	public static String createActivityCode(MarketActivity ma){
		StringBuffer code = new StringBuffer();
		if( MarketActivityConstance.ACTIVITY_CATEGORY_LTT.equals(ma.getActivityCategory())){
			code.append(MarketActivityConstance.ACTIVITY_CODE_LTT);
		}else if(MarketActivityConstance.ACTIVITY_CATEGORY_EXPRESS.equals(ma.getActivityCategory())){
			code.append(MarketActivityConstance.ACTIVITY_CODE_EXPRESS);
		}
		if( MarketActivityConstance.ACTIVITY_TYPE_NATION.equals(ma.getActivityType())){
			code.append(MarketActivityConstance.ACTIVITY_CODE_NATION);
		}else if(MarketActivityConstance.ACTIVITY_TYPE_REGION.equals(ma.getActivityType())){
			code.append(MarketActivityConstance.ACTIVITY_CODE_REGION);
		}
		return code.toString();
	}
	/**
	 * 
	 * <p>
	 * 转换部门列表到市场推广活动参与部门实体<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param List<String>
	 * @param String
	 * @return String 
	 */
	public static List<ActivityDept> covertDeptCodesToActivityDept(List<String> deptCodes,String activityId){
		List<ActivityDept> activityDepts = new ArrayList<ActivityDept>();
		ActivityDept dept = null;
		if(!StringUtils.isEmpty(activityId)){
			for( String deptCode : deptCodes){
				if(!StringUtils.isEmpty(deptCode)){
					dept = new ActivityDept();
					dept.setActivityId(activityId);
					dept.setDeptStandardCode(deptCode);
					activityDepts.add(dept);
				}
			}
		}
		return activityDepts;
	}
	/**
	 * 
	 * <p>
	 * 获得客户群删除列表<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param List<ActivityClientBase>
	 * @param String
	 * @return List<ActivityClientBase> 
	 */
	public static List<ActivityClientBase> getDeleteActivityClientList(List<ActivityClientBase> clients,List<ActivityClientBase> searchClients){
		List<ActivityClientBase> deleteList = new ArrayList<ActivityClientBase>();
		//循环查询结果集
		int del = 0;
		for( ActivityClientBase searchClient :searchClients ){
			del = 0;
			for(ActivityClientBase client : clients ){
				if( searchClient.getClientBaseId()!= null && client.getClientBaseId() != null
						&& searchClient.getClientBaseId().equals(client.getClientBaseId())){
					del =1;
					break;
				}
			}
			if( del == 0 ){
				deleteList.add(searchClient);
			}
		}
		return deleteList;
	}
	/**
	 * 
	 * <p>
	 * 获得客户群新增列表<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param List<ActivityClientBase>
	 * @return List<ActivityClientBase> 
	 */
	public static List<ActivityClientBase> getInsertActivityClientList(List<ActivityClientBase> clients,List<ActivityClientBase> searchClients){
		List<ActivityClientBase> addList = new ArrayList<ActivityClientBase>();
		int add = 0;
		for( ActivityClientBase client : clients ){
			add = 0;
			for( ActivityClientBase searchClient : searchClients ){
				if( searchClient.getClientBaseId()!= null && client.getClientBaseId() != null
						&& searchClient.getClientBaseId().equals(client.getClientBaseId())){
					add =1;
				}
			}
			if( add == 0){
				client.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_USEING);
				addList.add(client);
			}
		}
		return addList;
	}
	/**
	 * 
	 * <p>
	 * 组装部门树<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param List<DeptTree>
	 * @return DeptTree
	 */
	public static DeptTree organizedDeptTree(List<DeptTree> deptList){
		
		LinkedList<DeptTree> stack = new LinkedList<DeptTree>();
		DeptTree root = null;
		if( deptList != null && deptList.size()>0 ){
			root = deptList.get(0);
			stack.add(root);
			int level = root.getLevel();
			int start =1;
			int end = 1;
			DeptTree current = root;
			List<DeptTree> children = null;
			DeptTree node = null;
			DeptTree lastNode = null;
			while( start < deptList.size() && level < MarketActivityConstance.MAX_TREE_LEVEL){
				if( !current.isLeaf() ){
					children = new ArrayList<DeptTree>();
					for( int i = start; i<deptList.size(); i++){
						node = deptList.get(i);						
						if( current.getId().equals(node.getParentId())){							
							children.add(node);
							if(lastNode!=null){
								lastNode.setNextBro(node);
							}
							lastNode = node;
						}
						if( node.getLevel() > current.getLevel()+1 || i == deptList.size()-1){
							end = i;
							lastNode = null;
							node = null;
							break;
						}
					}
					current.setChildren(children);
				}				
				while( current.getLevel() <= level && level < MarketActivityConstance.MAX_TREE_LEVEL){
					if( current.getNextBro() == null ){
//						boolean flag = true;
//						for( int i = 0;i < stack.size()-1 ; i++){
//							if( stack.get(i).getChildren()!=null && stack.get(i).getChildren().size()>0){
//								flag = false;
//								break;
//							}
//						}
//						if( flag ){
//							level++;
//							start = end;
//							while( current.getLevel()< level && current.getChildren()!=null && current.getChildren().size()>0 ){
//								current = current.getChildren().get(0);
//								stack.push(current);
//							}
//							break;
//						}else{
//							 stack.pop();
//							 current = stack.getLast();
//						}
						if( current == root){
							level++;
							start = end;
							while( current.getLevel()< level && current.getChildren()!=null && current.getChildren().size()>0 ){
								current = current.getChildren().get(0);
								stack.addLast(current);
							}
							if( current.getLevel() == level){
								break;
							}							
						}else{
							stack.removeLast();
							current = stack.getLast();
						}
					}else if( current.getNextBro() != null ){
						current = current.getNextBro();
						stack.removeLast();
						stack.addLast(current);
						while( current.getLevel()< level && current.getChildren()!=null && current.getChildren().size()>0 ){
							current = current.getChildren().get(0);
							stack.addLast(current);
						}
						if( current.getLevel() == level){
							break;
						}
						
					}					
				}
									
			}
		}
		DeptTree dept = null;
		for( int i = 0; i< deptList.size() ; i++){
			deptList.get(i).setNextBro(null);
			dept = deptList.get(i);
			if( dept != null && dept.getChildren() == null){
				dept.setLeaf(true);
			}
		}
//		LinkedList<DeptTree> list= new LinkedList<DeptTree>(deptList);
//		DeptTree root = null;
//		if( list != null && list.size()>0 ){
//			root = deptList.get(list.size()-1);
//			stack.add(root);
//			list.removeLast();
//			int level = root.getLevel();
//			DeptTree current = root;
//			List<DeptTree> children = null;
//			DeptTree node = null;
//			DeptTree lastNode = null;
//			while( list.size()>0){
//				if( !current.isLeaf() ){
//					children = new ArrayList<DeptTree>();
//					for( int i = list.size()-1; i>=0 ; i--){
//						node = list.get(i);						
//						if( current.getId().equals(node.getParentId())){							
//							children.add(node);
//							list.remove(i);
//							if(lastNode!=null){
//								lastNode.setNextBro(node);
//							}
//							lastNode = node;
//						}
//						if( node.getLevel() > current.getLevel()+1 || i == 0){
//							lastNode = null;
//							node = null;
//							break;
//						}
//					}
//					current.setChildren(children);
//				}				
//				while( current.getLevel() <= level && current.getLevel() < MarketActivityConstance.MAX_TREE_LEVEL){
//					if( current.getNextBro() == null ){
//						boolean flag = true;
//						for( int i = 0;i < stack.size()-1 ; i++){
//							if( stack.get(i).getChildren()!=null && stack.get(i).getChildren().size()>0){
//								flag = false;
//								break;
//							}
//						}
//						if( flag ){
//							level++;
//							while( current.getLevel()< level && current.getChildren()!=null && current.getChildren().size()>0 ){
//								current = current.getChildren().get(0);
//								stack.push(current);
//							}
//							break;
//						}else{
//							 stack.pop();
//							 current = stack.getLast();
//						}
//					}else if( current.getNextBro() != null ){
//						current = current.getNextBro();
//						stack.pop();
//						stack.push(current);
//						while( current.getLevel()< level && current.getChildren()!=null && current.getChildren().size()>0 ){
//							current = current.getChildren().get(0);
//							stack.push(current);
//						}
//						if( current.getLevel()==level){
//							break;
//						}
//					}					
//				}
//									
//			}
//		}
		return root;
	}
//				if( stack.size()>1 ){
//					previous =stack.getLast();
//					for( int j =0;j< previous.getChildren().size();j++){
//						t = previous.getChildren().get(j);
//						if( t.getAlready() != MarketActivityConstance.TREE_NODE_READY ){
//							current = t;
//							stack.add(current);
//							break;
//						}
//						if( j == previous.getChildren().size() -1){
//							while( previous.getLevel() >2){
//								stack.removeLast();
//								previous = stack.getLast();
//								for( int k=0; k < previous.getChildren().size() ;k++){
//									t = previous.getChildren().get(k);
//									if( t.getAlready() == MarketActivityConstance.TREE_NODE_READY ){
//										current = t;
//										stack.add(t);
//									}
//								}
//							}
//							
//						}
//					}
//				}
//			}
//			if( children.size()>0 || ){
//				current.setChildren(children);
//				current.setAlready(MarketActivityConstance.TREE_NODE_READY);
//				if( stack.size() > 1 ){
//					previous = stack.removeLast();
//					current = stack.getLast();
//					for( int i =0; i<current.getChildren().size();i++){
//						t = current.getChildren().get(i);
//						if( t.getAlready()== MarketActivityConstance.TREE_NODE_READY ){
//							continue;
//						}else{
//							stack.add(t);
//							break;
//						}
//						if( i == current.getChildren().size()-1 ){
//							stack
//						}
//					}
//				}
//					
//			}
//				
//		}
//		if( deptList!= null && deptList.size()>0){
//			DeptTree root = deptList.get(0);
//			stack.add(root);
//			DeptTree temple = root;
//			List<DeptTree> pNode = null;
//			List<DeptTree> tpNode = null;
//			int start = 1;
//			int end = 1;
//			int level =3;
//			List<DeptTree> children = null;
//			children = new ArrayList<DeptTree>();
//			DeptTree dept = null;
//			DeptTree parent = null;
//			for( ;start < deptList.size();start++){
//				dept = deptList.get(start);
//				if( temple.getId().equals(dept.getParentId())){
//					children.add(dept);
//				}
//				if( level != dept.getLevel() ){
//					level = dept.getLevel();
//					break;
//				}
//			}
//			temple.setChildren(children);
//			pNode = temple.getChildren();
//			for( int i = 0 ; i < temple.getChildren().size();i++){
//				children = new ArrayList<DeptTree>();
//				parent = temple.getChildren().get(i);
//				for( int j=start ;j<= deptList.size() ; j++ ){
//					dept = deptList.get(j);
//					if( parent.getId().equals(dept.getParentId())){
//						children.add(dept);
//					}
//					if( level !=dept.getLevel()){
//						end = j;
//						break;
//					}
//				}
//				parent.setChildren(children);
//				if( i == temple.getChildren().size()-1){
//					level = deptList.get(end).getLevel();
//					tpNode = pNode;
//					for( DeptTree d : pNode){
//						for( int k =2; k < level ;k++){
//							if( d.getChildren() != null && d.getChildren().size()>0 ){
//								tpNode = d.getChildren();
//								for( DeptTree d1 : tpNode){
//									
//								}
//							}
//						}
//					}
//				}
//			}
//			temple.setChildren(children);
//			/*if( !level.equals(deptList.get(i).getLevel())){
//				
//			}*/
//		}
//		return null;
//	}
	/**
	 * 
	 * <p>
	 * 获得市场推广活动修改完客户群后市场推广活动状态编码<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-8
	 * @param MarketActivity ma
	 * @return String
	 */
	public static String getMarketActivityChangeClinetStatus(MarketActivity ma){
		String status = ma.getActivityStatus();
		int otherStatus = 0;
		//判断市场推广活动客户群是否为空
		if( ma.getClientBase() != null &&ma.getClientBase().size()>0){
			//循环客户群列表
			for( ActivityClientBase acb : ma.getClientBase()){
				//如果有客户群状态不为下发完成
				if( !MarketActivityConstance.CLIENTBASE_STATUS_ISSUED_COMPLETE
						.equals(acb.getClientBaseStatus())){
					//设置标记为1
					otherStatus = 1;
					break;
				}
			}
		}
		//如果有不为下发完成的客户群,且市场推广活动为全国市场推广活动
		if( otherStatus == 1 && MarketActivityConstance.ACTIVITY_TYPE_NATION.equals(ma.getActivityType())){
			//如果包含价格折扣
			if( MarketActivityConstance.OPTION_DISCOUNT.equals(ma.getPreferType())){
				//设置市场推广活动状态为折扣已生效
				status = MarketActivityConstance.ACTIVITY_STATUS_EXECUTED;
			}
			//如果是优惠券
			if( MarketActivityConstance.OPTION_COUPON.equals(ma.getPreferType())){
				//设置市场推广活动状态为折扣已制定
				status = MarketActivityConstance.ACTIVITY_STATUS_ESTABLISHED;
			}
	    //如果全部下发完成,且市场推广活动为全国市场推广活动
		}else if( otherStatus == 0 && MarketActivityConstance.ACTIVITY_TYPE_NATION.equals(ma.getActivityType())){
			//设置市场推广活动状态为下发完成
			status = MarketActivityConstance.ACTIVITY_STATUS_PLANCREATED;
		}
		return status;	
	}
	/**
	 * 
	 * <p>
	 * 获得市场推广活动异常处理完成后状态变化<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-8
	 * @param MarketActivity ma
	 * @return String
	 */
	public static String getMarketActivityExceptionStatus(MarketActivity ma){
		String status = ma.getActivityStatus();
		int otherStatus = 0;
		//判断市场推广活动客户群是否为空
		if( ma.getClientBase() != null &&ma.getClientBase().size()>0){
			//循环客户群列表
			for( ActivityClientBase acb : ma.getClientBase()){
				//如果有客户群状态不为下发完成
				if( MarketActivityConstance.CLIENTBASE_STATUS_ISSUED_COMPLETE
						.equals(acb.getClientBaseStatus())){
					//设置标记为1
					otherStatus = 1;
				}
			}
		}
		//如果有不为下发完成的客户群,且市场推广活动为折扣
		if( otherStatus == 1 && MarketActivityConstance.OPTION_DISCOUNT.equals(ma.getPreferType())){
			status = MarketActivityConstance.ACTIVITY_STATUS_EXECUTED;
		}
		return status;
	}
	/**
	 * 
	 * <p>
	 * 将市场推广活动查询出的多选条件转换为字符串列表<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-8
	 * @param MarketActivity ma
	 */
	public static String[] convertMutilpleToStringArray(List<Multiple> multiples){
		String[] array = null;
		if( multiples != null && multiples.size()>0){
			array = new String[multiples.size()];
			for( int i = 0;i<multiples.size();i++){
				array[i] = multiples.get(i).getValue();
			}
		}
		return array;
	}
	/**
	 * 
	 * <p>
	 * 设置市场推广活动实体中的一级行业、二级行业、产品类型、订单来源<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-8
	 * @param MarketActivity ma
	 */
	public static void setMarketActivityStringArray(MarketActivity ma){
		if( ma != null){
			ma.setCustTrade(convertMutilpleToStringArray(ma.getCustTradeMul()));
			ma.setSecondTrade(convertMutilpleToStringArray(ma.getSecondTradeMul()));
			ma.setProductType(convertMutilpleToStringArray(ma.getProductTypeMul()));
			ma.setOrderSource(convertMutilpleToStringArray(ma.getOrderSourceMul()));
		}
	}
	/**
	 * 
	 * <p>
	 * 将市场推广活动实体转换为价格折扣推送接口实体<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-8
	 * @param MarketActivity ma
	 * @return MarketActivityRequest
	 */
	public static MarketingActivityRequest converActivityToRequest(MarketActivity ma,String fossStatus){
		MarketingActivityRequest mRequest = null;
		if( ma != null ){
			mRequest = new MarketingActivityRequest();
			//设置ID
			mRequest.setFid(new BigDecimal(ma.getId()));
			//设置活动名称
			mRequest.setActivityName(ma.getActivityName());
			//设置活动编码
			mRequest.setActivityCode(ma.getActivityCode());
			//设置FOSS状态
			mRequest.setFstatus(fossStatus);
			//折扣信息
			List<DiscountInfo> discountInfo = mRequest.getDiscountInfos();
			if( ma.getDiscountInfo()!=null && ma.getDiscountInfo().size()>0){
				for( ActivityOption o : ma.getDiscountInfo()){
					DiscountInfo d = new DiscountInfo();
					d.setFid(new BigDecimal(o.getId()));
					d.setPreferentialProduct(o.getType());
					d.setPriceDiscount((new BigDecimal(o.getValue()).divide(new BigDecimal("100"))).toString());
					discountInfo.add(d);
				}		
			}
			//设置市场推广活动开始时间
			mRequest.setActivityBeginTime(ma.getStartTime());
			//设置市场推广活动结束时间
			mRequest.setActivityEndTime(ma.getEndTime());
			//设置一级行业
			List<String> firstTrade = mRequest.getFirstTrades();
			if( ma.getCustTrade()!=null && ma.getCustTrade().length>0){
				for( String s : ma.getCustTrade()){
					firstTrade.add(s);
				}
			}
			//设置二级行业
			List<String> secondTrade = mRequest.getSecondTrades();
			if( ma.getSecondTrade()!=null && ma.getSecondTrade().length>0){
				for( String s : ma.getSecondTrade()){
					secondTrade.add(s);
				}
			}
			//设置活动类别
			mRequest.setActivityCategory(ma.getActivityCategory());
			//设置活动类型
			mRequest.setActivityType(ma.getActivityType());
			//产品类型
			List<String> productType = mRequest.getProductType();
			if( ma.getProductType() != null && ma.getProductType().length>0  ){
				for( String s : ma.getProductType()){
					productType.add(s);
				}
			}
			//开单品名
			if( !StringUtils.isEmpty(ma.getItemNames())){
				String[] items = ma.getItemNames().split(MarketActivityConstance.ITEMNAME_DIVISION);
				List<String> goodsName = mRequest.getGoodsName();
				for( String item : items ){
					goodsName.add(item);
				}
			}
			//开单金额下限
			mRequest.setBillMinMoney(ma.getMinBillAmt());
			//开单金额上限
			mRequest.setBillMaxMoney(ma.getMaxBillAmt());
			//重量下限
			mRequest.setGoodsMinWeight(ma.getMinCargoWeight());
			//重量上限
			mRequest.setGoodsMaxWeight(ma.getMaxCargoWeight());
			//货物体积下限
			mRequest.setGoodsMinVolume(ma.getMinCargoVolume());
			//货物体积上限
			mRequest.setGoodsMaxVolume(ma.getMaxCargoVolume());
			//订单来源
			List<String> orderSource = mRequest.getOrderResource();
			if( ma.getOrderSource()!=null && ma.getOrderSource().length>0){
				for( String s : ma.getOrderSource()){
					orderSource.add(s);
				}
			}
			//设置部门列表
			List<DevelopDeptInfos> depts = mRequest.getDevelopDeptInfos();
			if( ma.getActivityDepts() != null && ma.getActivityDepts().size()>0){
				for( ActivityDept d : ma.getActivityDepts() ){
					DevelopDeptInfos dept = new DevelopDeptInfos();
					dept.setFid(new BigDecimal(d.getId()));
					dept.setSponsorDepts(d.getDeptStandardCode());
					depts.add(dept);
				}
			}
			//设置走货线路
			List<GoodsLine> goodsLine = mRequest.getGoodsLine();
			setRequestGoodsLine(goodsLine, ma);
		}
		return mRequest; 
	}
	/**
	 * 
	 * <p>
	 * 接口实体的List赋值<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-8
	 * @param List
	 * @param List
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws OgnlException 
	 */
	@SuppressWarnings("unchecked")
	public static<T,V> void setInterfaceListField(List<T> requestField,List<V> values,Map<String,String> fieldName,Class<T> c,String convertType) throws InstantiationException, IllegalAccessException, OgnlException{
		if( values != null && values.size()>0){
			if( fieldName != null ){
				for( V v : values ){
					T t = c.newInstance();
					Iterator<Entry<String,String>> l = fieldName.entrySet().iterator();
					while(l.hasNext()){
						Entry<String,String> entry = l.next();
						Ognl.setValue(entry.getKey(), t, Ognl.getValue(entry.getValue(),v));					
					}
					requestField.add(t);
				}
			}else{
				for( V v : values){
					requestField.add((T)v);					
				}
			}
		}
	}
	/**
	 * 
	 * <p>
	 * 设置走货线路<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-8
	 * @param List<DevelopDeptInfos>
	 * @param MarketActivity
	 */
	public static void setRequestGoodsLine(List<GoodsLine> deptInfo,MarketActivity ma){
		GoodsLine info = null;
		if( MarketActivityConstance.LINE_TYPE_LEAVE.equals(ma.getLineRequest())){		
			for( LineDept d : ma.getLineDepts()){
				info = new GoodsLine();
				info.setStartingArea(d.getLeaveDeptCode());
				info.setFid(new BigDecimal(d.getId()));
				deptInfo.add(info);
			}
		}else if( MarketActivityConstance.LINE_TYPE_ARRIVE.equals(ma.getLineRequest())){
			for( LineDept d : ma.getLineDepts()){
				info = new GoodsLine();
				info.setArrivalArea(d.getArriveDeptCode());
				info.setFid(new BigDecimal(d.getId()));
				deptInfo.add(info);
			}
		}else if( MarketActivityConstance.LINE_TYPE_LEAVE_ARRIVE.equals(ma.getLineRequest())){
			for( LineDept d : ma.getLineDepts()){
				info = new GoodsLine();
				info.setStartingOutfield(d.getLeaveDeptCode());
				info.setArrivalOutfield(d.getArriveDeptCode());
				info.setFid(new BigDecimal(d.getId()));
				deptInfo.add(info);
			}
		}
	}
	/**
	 * 
	 * <p>
	 * 设置办公门户和总裁不能选中<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-8
	 * @param List<DevelopDeptInfos>
	 * @param MarketActivity
	 */
	public static void setCannotSelectDept(List<DeptTree> deptList){
		if(deptList != null && deptList.size()>2){
			DeptTree dept = null;
			for( int i=0;i<2;i++){
				dept = deptList.get(i);
				if( MarketActivityConstance.CANNOT_SELECT_LIST.contains(dept.getId())){
					dept.setCanSelect(MarketActivityConstance.CAN_NOT_SELECT);
				}
			}
		}
	}
	/**
	 * 
	 * <p>
	 * 获得该用户具有的权限新建营销计划的权限<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-8
	 * @param List<DevelopDeptInfos>
	 * @param MarketActivity
	 */
	public static String getMarketPlanAuth(User user){
		String result = null;
		if(user != null && user.getAccessUris()!=null && user.getAccessUris().size()>0){
			if( user.getAccessUris().contains(MarketActivityConstance.LOGISTICSPRODUCTFUNCTION)){
				result = MarketActivityConstance.ACTIVITY_CATEGORY_LTT;
			}else if(user.getAccessUris().contains(MarketActivityConstance.EXPRESSPRODUCTFUNCTION)){
				result = MarketActivityConstance.ACTIVITY_CATEGORY_EXPRESS; 
			}
		}
		return result;
	}
}
