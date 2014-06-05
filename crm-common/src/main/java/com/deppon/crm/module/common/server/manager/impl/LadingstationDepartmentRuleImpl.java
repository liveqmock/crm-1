package com.deppon.crm.module.common.server.manager.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentRule;
import com.deppon.crm.module.common.server.service.ILadingstationDepartmentService;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;

/**
 * 定义始发网点与受理部门之间关系的校验规则
 * @author 邢悦
 *
 */
public class LadingstationDepartmentRuleImpl implements ILadingstationDepartmentRule{

	//传入的service
	private IDepartmentService departmentService;

	//传入的service
	private ILadingstationDepartmentService ladingstationDepartmentService;	
	
	//是否收货的可选值
	private Map<String,String> IF_RECEIVE_MAP=new HashMap<String,String>();
	
	//订单来源的可选值
	private Map<String,String> SOURCE_MAP=new HashMap<String,String>();	
	
	private final String IF_RECEIVE_ALL_VALUE="全部";
	
	private final String ORDER_SOURCE_ALL_VALUE="全部";	
	
	
	/**
	 * 校验
	 * @param ld
	 * @return
	 */
	public String validate(LadingstationDepartment ld,Integer operationType){
		if(this.IF_RECEIVE_MAP==null||IF_RECEIVE_MAP.isEmpty()){
			this.findDataDictionary(DataHeadTypeEnum.CONFIG_ORDER_RECEIVE_GOODS,IF_RECEIVE_MAP);	
		}
		
		if(SOURCE_MAP==null||SOURCE_MAP.isEmpty()){
			this.findDataDictionary(DataHeadTypeEnum.CONFIG_ORDER_SOURCE, SOURCE_MAP);	
		}
		return validateRelation(ld,operationType);
	}
	
	
	/**
	 * 校验逻辑关系
	 * 需要判断包含的关系如下：
	 * 1)若数据库中已存在：始发网点、订单来源一致，“是否接货”为全部时，系统限制不可再录入是否接货为是或为否的记录;
	 * 2)若数据库中已存在：始发网点、是否接货一致，“订单来源”为全部时，系统限制不可再录入订单来源为网单、400的记录；
	 * @return
	 */
	private String validateRelation(LadingstationDepartment ld,Integer operationType){
		String lineMessage=null;
		List<LadingstationDepartment> conditionSearchList;
		LadingstationDepartment currentLD;
		
		//判断是符合关系1
		LadingstationDepartment condition=this.createSearchCondition(ld);
		//设置查询的“是否收货”为全部
		condition.setIfReceive(IF_RECEIVE_MAP.get(IF_RECEIVE_ALL_VALUE));
		conditionSearchList=ladingstationDepartmentService.searchByCondition(condition,0,9999);
		if(!conditionSearchList.isEmpty()){
			currentLD=conditionSearchList.get(0);
				
				//如果是新建操作
				if(ILadingstationDepartmentRule.OPERATION_TYPE_CREATE.equals(operationType)){
					lineMessage=getIfReceiveAllErrorMessage(ld);							
				}
				
				//如果是修改操作
				if(ILadingstationDepartmentRule.OPERATION_TYPE_MODIFY.equals(operationType)){
					if(!currentLD.getId().equals(ld.getId())){
						//如果查询到的id和当前id不符，说明用户正在使用其他网点关系修改成当前这个关系
						lineMessage=getIfReceiveAllErrorMessage(ld);						
					}					
				}
				
				//如果是导入操作
				if(ILadingstationDepartmentRule.OPERATION_TYPE_IMPORT.equals(operationType)){
				
					//若当前ld中“是否接货”字段不为“全部”，则报错
					if(!IF_RECEIVE_MAP.get(IF_RECEIVE_ALL_VALUE).equals(ld.getIfReceive())){
						lineMessage=getIfReceiveAllErrorMessage(ld);
					}					
				}
			
		}
		
		//判断是否符合关系2
		if(lineMessage==null){
			condition=createSearchCondition(ld);
			//设置订单来源为“全部”
			condition.setResource(SOURCE_MAP.get(ORDER_SOURCE_ALL_VALUE));
			conditionSearchList=ladingstationDepartmentService.searchByCondition(condition,0,9999);
			if(!conditionSearchList.isEmpty()){
				currentLD=conditionSearchList.get(0);
				
				//如果是新建操作
				if(ILadingstationDepartmentRule.OPERATION_TYPE_CREATE.equals(operationType)){
					lineMessage=getSourceAllErrorMessage(ld);							
				}
				
				//如果是修改操作
				if(ILadingstationDepartmentRule.OPERATION_TYPE_MODIFY.equals(operationType)){
					if(!currentLD.getId().equals(ld.getId())){
						//如果查询到的id和当前id不符，说明用户正在使用其他网点关系修改成当前这个关系
						lineMessage=getSourceAllErrorMessage(ld);						
					}					
				}
				
				//如果是导入操作
				if(ILadingstationDepartmentRule.OPERATION_TYPE_IMPORT.equals(operationType)){
				
					//若当前ld中“是否接货”字段不为“全部”，则报错
					if(!SOURCE_MAP.get(ORDER_SOURCE_ALL_VALUE).equals(ld.getResource())){
						lineMessage=getSourceAllErrorMessage(ld);
					}					
				}				
				
			}			
		}
		
		
		return lineMessage;
	}	
	
	
	/**
	 * 创建查询条件(过滤掉受理部门)
	 * @param ld
	 * @return
	 */
	private LadingstationDepartment createSearchCondition(LadingstationDepartment ld){
		LadingstationDepartment condition=new LadingstationDepartment();
		condition.setBeginDeptStandardCode(ld.getBeginDeptStandardCode());
		condition.setAcceptDeptStandCode(null);
		condition.setIfReceive(ld.getIfReceive());
		condition.setResource(ld.getResource());
		return condition;
		
	}	
	
	/**
	 * 从数据字典中查询所要的值，并且放入map中
	 * @param dataHead
	 * @param mapToFill
	 */
	private void findDataDictionary(DataHeadTypeEnum dataHead,Map<String,String> mapToFill){
		List<Detail> sourceDetailList= DataDictionaryUtil.getDetailList(dataHead);
		for(Detail detail:sourceDetailList){
			mapToFill.put(detail.getCodeDesc(), detail.getCode());
		}
	}	
	
	
	/**
	 * 工具方法，对于一个map，通过value得到key
	 * @param map
	 * @param o
	 * @return
	 */
	private Object keyString(Map<String,String> map,String value)
	{
	          Iterator<String> it= map.keySet().iterator();
	          while(it.hasNext())
	          {
	              String keyString=it.next();
	              if(map.get(keyString).equals(value))
	                   return keyString;
	           }
	           return null;
	}	
	
	/**
	 * 是否收货为全部的错误信息
	 * @param ld
	 * @return
	 */
	private String getIfReceiveAllErrorMessage(LadingstationDepartment ld){	
		Department department=departmentService.getDeptByStandardCode(ld.getBeginDeptStandardCode());
		return "已存在始发网点为:"+department.getDeptName()+",订单来源为:"+keyString(SOURCE_MAP,ld.getResource())+",是否接货为全部的基础资料";
		
	}
	
	/**
	 * 订单来源为全部的错误信息
	 * @param ld
	 * @return
	 */
	private String getSourceAllErrorMessage(LadingstationDepartment ld){	
		Department department=departmentService.getDeptByStandardCode(ld.getBeginDeptStandardCode());
		return "已存在始发网点为:"+department.getDeptName()+",是否接货为:"+keyString(IF_RECEIVE_MAP,ld.getIfReceive())+",订单来源为全部的基础资料";
		
	}
	
	
	
	public ILadingstationDepartmentService getLadingstationDepartmentService() {
		return ladingstationDepartmentService;
	}


	public void setLadingstationDepartmentService(
			ILadingstationDepartmentService ladingstationDepartmentService) {
		this.ladingstationDepartmentService = ladingstationDepartmentService;
	}


	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	
	
}
