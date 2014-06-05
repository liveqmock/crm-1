package com.deppon.crm.module.common.server.manager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.deppon.crm.module.common.server.service.ILadingstationDepartmentService;
import com.deppon.crm.module.common.server.util.Constant;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.server.util.LadingStationDeptReaderPojo;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;

public class LadingstationDepartmentImportValidator {

	//用于更新的ld对象列表
	private List<LadingstationDepartment> forUpdateList=new ArrayList<LadingstationDepartment>();
	
	//用于新建的ld对象烈表
	private List<LadingstationDepartment> forCreateList=new ArrayList<LadingstationDepartment>();
	
	//传入的初始set
	private List<LadingStationDeptReaderPojo> dataList;
	
	//传入的service
	private ILadingstationDepartmentService ladingstationDepartmentService;
	
	//传入的service
	private IDepartmentService departmentService;
	
	private ILadingstationDepartmentRule ladingstationDepartmentRule;
	
	
	private final String ACCEPT_DEPT="受理部门";
	
	private final String BEGIN_DEPT="始发网点";
	
	
	//是否收货的可选值
	private Map<String,String> IF_RECEIVE_MAP=new HashMap<String,String>();
	
	//订单来源的可选值
	private Map<String,String> SOURCE_MAP=new HashMap<String,String>();
	
	
	public LadingstationDepartmentImportValidator(
			List<LadingStationDeptReaderPojo> pojoList,
			ILadingstationDepartmentService ladingstationDepartmentService,IDepartmentService departmentService) {
		super();
		this.dataList = pojoList;
		this.ladingstationDepartmentService = ladingstationDepartmentService;
		this.departmentService=departmentService;
		
		//初始化可用的"是否收货" 以及 "订单来源"列表
		this.findDataDictionary(DataHeadTypeEnum.CONFIG_ORDER_RECEIVE_GOODS,IF_RECEIVE_MAP);
		this.findDataDictionary(DataHeadTypeEnum.CONFIG_ORDER_SOURCE, SOURCE_MAP);
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
	 * 验证受理网点维护的导入的pojo
	 * @param dataList
	 * @param ladingstationDepartmentService
	 * @return
	 */
	public boolean validateImportPojo(){
		
		//标志位，判断截止目前为止，数据是否都正确
		boolean currentStatus=true;
		
		for(LadingStationDeptReaderPojo pojo:dataList){
			
			//字段校验
			String lineMessage=validatePojoData(pojo);
			
			//当前行错误信息校验
			if(lineMessage!=null){
				//当前行有错误
				currentStatus=false;
			}
			
			//如果截止当前行之前都没有错误，那就决定当前行是加入新增列表中，还是更新列表中
			if(currentStatus){
				//如果截止当前行都没有错误，则进行，否则只要前面有行错误，就不会走到这里
				
				LadingstationDepartment ld=pojo.getLd();
				if(ld!=null){
					//查询当前行是否在数据库中已存在，若存在，则不报错，更新之。否则新增之					
					LadingstationDepartment condition=createSearchCondition(ld);
					List<LadingstationDepartment> list=ladingstationDepartmentService.searchByCondition(condition,0,9999);
					
					//验证这个关系是否已经存在
					if(list.size()!=0){
						//如果存在，则属于更新的对象
						LadingstationDepartment forupdate=list.get(0);
						forupdate.setAcceptDeptN(ld.getAcceptDeptN());
						forupdate.setAcceptDeptStandCode(ld.getAcceptDeptStandCode());
						forupdate.setIsValid(Constant.LADINGSTATION_DEPARTMENT_VALID);
						addIntoUpdateList(forupdate);
						
					}else{
						//否则属于新增的对象
						addIntoCreateList(ld);
					}
					
				}				
			}
			
		}
		return currentStatus;
	}
	
	
	/**
	 * 把ld对象增加到更新列表中
	 * @param ld
	 * @param acceptStandCode
	 */
	private void addIntoUpdateList(LadingstationDepartment ld){
		forUpdateList.add(ld);
	}
	
	/**
	 * 把ld对象添加到新增列表中
	 * @param pojo
	 */
	private void addIntoCreateList(LadingstationDepartment ld){
		forCreateList.add(ld);
	}
	
	/**
	 * 校验解析好的对象相关字段是否有效
	 * @param pojo
	 * @return
	 */
	private String validatePojoData(LadingStationDeptReaderPojo pojo){
		LadingstationDepartment ld=pojo.getLd();
		
		StringBuffer errorMsg=new StringBuffer();
		String buffer=null;
		
		//校验始发网点标杆编码
		buffer=validateStandardCode(ld.getBeginDeptStandardCode(), BEGIN_DEPT,ld);
		if(buffer!=null){
			errorMsg.append(buffer);
		}

		//校验受理网点标杆编码
		buffer=validateStandardCode(ld.getAcceptDeptStandCode(), this.ACCEPT_DEPT,ld);
		if(buffer!=null){
			errorMsg.append(buffer);
		}	
		
		//校验"是否接货"
		buffer=validateIfReceive(ld);
		if(buffer!=null){
			errorMsg.append(buffer);
		}
		
		//校验"订单来源"
		buffer=validateResource(ld);
		if(buffer!=null){
			errorMsg.append(buffer);
		}	
		
		//如果上面的字段校验没错，那么校验每行字段之间的逻辑关系
		if(errorMsg.length()==0){
			buffer=ladingstationDepartmentRule.validate(ld,ILadingstationDepartmentRule.OPERATION_TYPE_IMPORT);
			if(buffer!=null){
				errorMsg.append(buffer);
			}			
		}
		
		if(errorMsg.length()!=0){
			pojo.setErrorMessage(errorMsg.toString());
			return errorMsg.toString();
		}else{
			return null;
		}
		
	}
	
	/**
	 * 校验标杆编码
	 * @param standardCode
	 * @return
	 */
	private String validateStandardCode(String standardCode,String deptName,LadingstationDepartment ld){
		StringBuffer errorMsg=new StringBuffer();
		
		if(standardCode==null){
			//标杆编码判空
			errorMsg.append(deptName+"标杆编码不能为空！");
		}else{
			//不为空，则做编码有效性判断
			Department dept=departmentService.getDeptByStandardCode(standardCode);
			
			if(dept==null){
				//填入的标杆编码无效
				errorMsg.append("不存在标杆编码:"+standardCode+"的部门！ ");
			}else{
				//填入的标杆编码有效
				if(ACCEPT_DEPT.equals(deptName)){
					//如果是受理部门，则为该ld添加受理部门对象
					ld.setAcceptDeptN(dept);
				}
				if(BEGIN_DEPT.equals(deptName)){
					//如果是始发网点，则为该ld添加始发网点对象
					ld.setBeginLadingDeptN(dept);
				}
				
				
			}
		}
		
		
		if(errorMsg.length()!=0){
			return errorMsg.toString();
		}else{
			return null;
		}
	}
	
	/**
	 * 校验是否接货
	 * @param ifReceive
	 * @return
	 */
	private String validateIfReceive(LadingstationDepartment ld){
		StringBuffer errorMsg=new StringBuffer();
		String ifReceive=ld.getIfReceive();
		if(ifReceive==null){
			errorMsg.append("是否接货字段不能为空！");
		}else{
			if(IF_RECEIVE_MAP.containsKey(ifReceive)){
				//如果是合理的值，那重新设定成保存在数据库中的值
				ld.setIfReceive(IF_RECEIVE_MAP.get(ifReceive));
			}else{
				errorMsg.append("是否接货字段无法解析:"+ifReceive+" ");
			}
			
		}
		
		if(errorMsg.length()!=0){
			return errorMsg.toString();
		}else{
			return null;
		}		
		
	}
	
	/**
	 * 校验订单来源
	 * @param resource
	 * @return
	 */
	private String validateResource(LadingstationDepartment ld){
		StringBuffer errorMsg=new StringBuffer();
		String source=ld.getResource();
		if(source==null){
			errorMsg.append("订单来源字段不能为空！");
		}else{
			if(SOURCE_MAP.containsKey(source)){
				//如果是合理的值，那重新设定成保存在数据库中的值
				ld.setResource(SOURCE_MAP.get(source));
			}else{
				errorMsg.append("订单来源字段无法解析:"+source+" ");
			}

		}
		
		if(errorMsg.length()!=0){
			return errorMsg.toString();
		}else{
			return null;
		}		
		
		
	}

	
	
	/**
	 * 创建查询条件(过滤掉受理部门)
	 * @param ld
	 * @return
	 */
	private LadingstationDepartment createSearchCondition(LadingstationDepartment ld){
		LadingstationDepartment condition=new LadingstationDepartment();
		condition.setBeginDeptStandardCode(ld.getBeginLadingDeptN().getStandardCode());
		condition.setAcceptDeptStandCode(null);
		condition.setIfReceive(ld.getIfReceive());
		condition.setResource(ld.getResource());
		return condition;
		
	}

	
	
	public List<LadingstationDepartment> getForUpdateList() {
		return forUpdateList;
	}


	public void setForUpdateList(List<LadingstationDepartment> forUpdateList) {
		this.forUpdateList = forUpdateList;
	}


	public List<LadingstationDepartment> getForCreateList() {
		return forCreateList;
	}


	public void setForCreateList(List<LadingstationDepartment> forCreateList) {
		this.forCreateList = forCreateList;
	}

	public void setLadingstationDepartmentRule(
			ILadingstationDepartmentRule ladingstationDepartmentRule) {
		this.ladingstationDepartmentRule = ladingstationDepartmentRule;
	}
	
}
