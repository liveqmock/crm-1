/**   
 * @title LadingstationDepartmentManager.java
 * @package com.deppon.crm.module.common.server.manager.impl
 * @description what to do
 * @author 安小虎
 * @update 2012-3-23 上午8:45:19
 * @version V1.0   
 */
package com.deppon.crm.module.common.server.manager.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentRule;
import com.deppon.crm.module.common.server.manager.LadingstationDepartmentImportValidator;
import com.deppon.crm.module.common.server.service.ILadingstationDepartmentService;
import com.deppon.crm.module.common.server.util.Constant;
import com.deppon.crm.module.common.server.util.LadingStationDepartmentXLReader;
import com.deppon.crm.module.common.server.util.LadingStationDeptReaderPojo;
import com.deppon.crm.module.common.server.util.LadingStationDeptValidatorUtil;
import com.deppon.crm.module.common.shared.domain.BusDeptSearchCondition;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.crm.module.common.shared.exception.LadingStationDepartmentException;
import com.deppon.crm.module.common.shared.exception.LadingStationDepartmentExceptionType;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * @description 始发网点与受理部门 MANAGER
 * @author 安小虎
 * @version 0.1 2012-3-23
 * @date 2012-3-23
 */
@Transactional(propagation=Propagation.REQUIRED)
public class LadingstationDepartmentManager implements
		ILadingstationDepartmentManager {

	
	private ILadingstationDepartmentService ladingstationDepartmentService;

	public ILadingstationDepartmentService getLadingstationDepartmentService() {
		return ladingstationDepartmentService;
	}

	public void setLadingstationDepartmentService(
			ILadingstationDepartmentService ladingstationDepartmentService) {
		this.ladingstationDepartmentService = ladingstationDepartmentService;
	}

	private IDepartmentService departmentService;
	
	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * 始发网点与受理部门之间关系校验
	 */
	private ILadingstationDepartmentRule ladingstationDepartmentRule;
	
	public ILadingstationDepartmentRule getLadingstationDepartmentRule() {
		return ladingstationDepartmentRule;
	}

	public void setLadingstationDepartmentRule(
			ILadingstationDepartmentRule ladingstationDepartmentRule) {
		this.ladingstationDepartmentRule = ladingstationDepartmentRule;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager
	 * #save(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)
	 */
	@SuppressWarnings("serial")
	@Override
	public LadingstationDepartment save(
			LadingstationDepartment ladingstationDepartment) {
		User currentUser=(User)UserContext.getCurrentUser();
		ladingstationDepartment.setCreateUser(currentUser.getId());
		ladingstationDepartment.setModifyUser(currentUser.getId());
		try {
			// 验证相同信息是否已存在
			int exists = ladingstationDepartmentService.searchExistsLadingstationByCondition(ladingstationDepartment);
			LadingStationDeptValidatorUtil.canSaveLadingstationDepartment(exists);
			// 保存网点信息
			this.ladingstationDepartmentService.save(ladingstationDepartment);
			return this.ladingstationDepartmentService
					.getById(ladingstationDepartment.getId());
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager
	 * #
	 * update(com.deppon.crm.module.common.shared.domain.LadingstationDepartment
	 * )
	 */
	@SuppressWarnings("serial")
	@Override
	public LadingstationDepartment update(
			LadingstationDepartment ladingstationDepartment) {
		User currentUser=(User)UserContext.getCurrentUser();
		ladingstationDepartment.setModifyUser(currentUser.getId());
		try {
			// 验证相同信息是否已存在
			int exists = ladingstationDepartmentService.searchExistsLadingstationByCondition(ladingstationDepartment);
			LadingStationDeptValidatorUtil.canSaveLadingstationDepartment(exists);
						
			
			// 保存网点信息
			ladingstationDepartmentService.update(ladingstationDepartment);
			return this.ladingstationDepartmentService
					.getById(ladingstationDepartment.getId());
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/**
	 * 编辑始发网点与受理部门关系
	 */
	public void edit(LadingstationDepartment ladingstationDepartment){
		
		//装填ladingstationDepartment的beginDeptStandardCode字段，用于后面rule规则查找
		Department beginDept=ladingstationDepartment.getBeginLadingDeptN();
		if(ladingstationDepartment.getBeginDeptStandardCode()==null){
			beginDept=departmentService.getDepartmentById(beginDept.getId());
			ladingstationDepartment.setBeginDeptStandardCode(beginDept.getStandardCode());
		}
		
		////装填ladingstationDepartment的acceptDeptStandardCode字段，用于后面rule规则查找
		Department acceptDept=ladingstationDepartment.getAcceptDeptN();
		if(ladingstationDepartment.getAcceptDeptStandCode()==null){
			acceptDept=departmentService.getDepartmentById(acceptDept.getId());
			ladingstationDepartment.setAcceptDeptStandCode(acceptDept.getStandardCode());
		}
		
		
		//校验逻辑关系
		String errorMessage=ladingstationDepartmentRule.validate(ladingstationDepartment,ILadingstationDepartmentRule.OPERATION_TYPE_MODIFY);		
		if(errorMessage!=null){
			LadingStationDepartmentException e = new LadingStationDepartmentException(LadingStationDepartmentExceptionType.LOGICAL_CHECK_EXCEPTION);
			throw new GeneralException(e.getErrorCode(),e.getMessage() , e,
					new Object[] {errorMessage}) {
			};			
		}		
		
		update(ladingstationDepartment);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager
	 * #delete(java.lang.String)
	 */
	@Override
	public int delete(String ladingstationDepartmentId) {
		return this.ladingstationDepartmentService
				.delete(ladingstationDepartmentId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager
	 * #searchByCondition(com.deppon.crm.module.common.shared.domain.
	 * LadingstationDepartment)
	 */
	@Override
	public Map<String,Object> searchByCondition(
			LadingstationDepartment ladingstationDepartment,int start, int limit) {
		Map<String,Object> returnMap=new HashMap<String,Object>();
		List<LadingstationDepartment> ldList=ladingstationDepartmentService.searchByCondition(ladingstationDepartment,start,limit);
		Long count=ladingstationDepartmentService.searchByConditionCount(ladingstationDepartment);
		
		returnMap.put(LANDINGSTATIONDEPARTMENT_LIST, ldList);
		returnMap.put(LANDINGSTATIONDEPARTMENT_TOTAL_COUNT, count);
		return returnMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager
	 * #getBeginLadingByName(java.lang.String)
	 */
	@Override
	public List<BussinessDept> getLeaveBusDeptByName(BusDeptSearchCondition condition) {
		return this.ladingstationDepartmentService.getLeaveBusDeptByName(condition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager
	 * #getArriveBusDeptByName(java.lang.String)
	 */
	@Override
	public List<BussinessDept> getArriveBusDeptByName(BusDeptSearchCondition condition){
		return this.ladingstationDepartmentService.getArriveBusDeptByName(condition);
	}
	@Override
	public Long getBussinessNumber(BusDeptSearchCondition condition){
		return ladingstationDepartmentService.getBussinessNumber(condition);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager
	 * #getAcceptDeptByBeginLadingId(java.lang.String)
	 */
	@Override
	public List<LadingstationDepartment> getAcceptDeptByConfigurator(String deptId,boolean isReceiveGoods,String resource) {
		List <LadingstationDepartment> ladDept = ladingstationDepartmentService
				.getAcceptDeptByConfigurator(deptId, isReceiveGoods, resource);
		
		return ladDept;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager#getBusDeptByName(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptById(String deptId) {
		return ladingstationDepartmentService.getBusDeptById(deptId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager#getBusDeptByName(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptByName(String startStation) {
		return ladingstationDepartmentService.getBusDeptByName(startStation);
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager#getBusDeptByName(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptByCode(String deptCode) {
		return ladingstationDepartmentService.getBusDeptByCode(deptCode);
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager#getBusDeptByErpId(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptByErpId(String ErpId) {
		return ladingstationDepartmentService.getBusDeptByErpId(ErpId);
	}
	
	@Override
	public BussinessDept getBusDeptByStandardCode(String standardCode) {
		return ladingstationDepartmentService.getBusDeptByStandardCode(standardCode);
	}
	
	/**
	 * <p>
	 * Description:获取所有始发网点与受理部门关联对象<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-5-8
	 * @param start
	 * @param limit
	 * @return
	 * List<LadingstationDepartment>
	 */
	public Map<String,Object> getAllLandingStationDepartment(int start, int limit) {

		List<LadingstationDepartment> lds=ladingstationDepartmentService.getAllLandingStationDepartment(start, limit);
		Long count=ladingstationDepartmentService.getAllLandingStationDepartmentCount();
		Map<String,Object> returnMap=new HashMap<String,Object>();
		returnMap.put(LANDINGSTATIONDEPARTMENT_LIST, lds);
		returnMap.put(LANDINGSTATIONDEPARTMENT_TOTAL_COUNT, count);
		return returnMap;
	}

	/**
	 * 获取所有始发网点与受理部门关联对象的总数
	 * @return
	 */
	public Long getAllLandingStationDepartmentCount() {
		return ladingstationDepartmentService.getAllLandingStationDepartmentCount();
	}
	
	
	@Override
	public void createLadingstationDepartment(String startNetId, String acceptDeptId,String orderResource,String ifReceive,User user){
		
		Department beginLadingDeptN=null;
		Department acceptDeptN=null;
		//由于数据库设计的FID是数字，而实体对应的是字符串，所以还要做一下判断，否则当mybatis强制转换字符串为数字失败时会报错
		if(isNumeric(startNetId)){
			beginLadingDeptN=departmentService.getDepartmentById(startNetId);
		}
		
		if(isNumeric(acceptDeptId)){
			acceptDeptN=departmentService.getDepartmentById(acceptDeptId);
		}
		
		if(beginLadingDeptN==null){
			LadingStationDepartmentException e = new LadingStationDepartmentException(LadingStationDepartmentExceptionType.BEGIN_LADING_STATION_DEPT_NOT_EXIST);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};			
		}
		
		if(acceptDeptN==null){
			LadingStationDepartmentException e = new LadingStationDepartmentException(LadingStationDepartmentExceptionType.ACCEPT_DEPT_NOT_EXIST);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};			
		}
				
		Date now=new Date();
		LadingstationDepartment lsd=new LadingstationDepartment();
		lsd.setBeginLadingDeptN(beginLadingDeptN);
		lsd.setBeginDeptStandardCode(beginLadingDeptN.getStandardCode());
		lsd.setAcceptDeptN(acceptDeptN);
		lsd.setAcceptDeptStandCode(acceptDeptN.getStandardCode());
		lsd.setCreateDate(now);
		lsd.setIfReceive(ifReceive);
		lsd.setCreateUser(user.getId());
		lsd.setModifyUser(user.getId());
		lsd.setResource(orderResource);
		lsd.setModifyDate(now);
		
		//校验逻辑关系
		String errorMessage=ladingstationDepartmentRule.validate(lsd,ILadingstationDepartmentRule.OPERATION_TYPE_CREATE);		
		if(errorMessage!=null){
			LadingStationDepartmentException e = new LadingStationDepartmentException(LadingStationDepartmentExceptionType.LOGICAL_CHECK_EXCEPTION);
			throw new GeneralException(e.getErrorCode(),e.getMessage() , e,
					new Object[] {errorMessage}) {
			};			
		}
		
		
		this.save(lsd);
	}

	@Override
	public List<Department> getDepartmentByDeptName(String deptName) {
		return this.departmentService.getDepartmentByDeptName(deptName);
	}

	/**
	 * 批量导入始发网点与受理部门关系
	 */
	@Override

	public String importLadingtationDepartments(File importFile) {
		
		String errorMsg=null;
		
		//读取XL文件并且解析包装成POJO对象	
		LadingStationDepartmentXLReader reader=new LadingStationDepartmentXLReader(importFile);
		List<LadingStationDeptReaderPojo> dataList=reader.analyze();
		
		//校验数据
		LadingstationDepartmentImportValidator validator=new LadingstationDepartmentImportValidator(dataList,ladingstationDepartmentService,departmentService);		
		validator.setLadingstationDepartmentRule(ladingstationDepartmentRule);
		
		if(validator.validateImportPojo()){
			//如果校验成功
			User currentUser=(User)UserContext.getCurrentUser();
			
			for(LadingstationDepartment ld:validator.getForCreateList()){
				ld.setCreateUser(currentUser.getId());
				ld.setModifyUser(currentUser.getId());
				ladingstationDepartmentService.save(ld);
			}

			for(LadingstationDepartment ld:validator.getForUpdateList()){
				ld.setModifyUser(currentUser.getId());								
				ladingstationDepartmentService.update(ld);
			}		
			
		}else{
			errorMsg="导入出错！错误信息如下：\\n";
			for(LadingStationDeptReaderPojo pojo:dataList){
				if(pojo.getErrorMessage()!=null){
					String lineMsg="第"+pojo.getLineNo()+"行，错误："+pojo.getErrorMessage()+"\\n";
					errorMsg=errorMsg+lineMsg;
				}
			}
					 
			
		}
		
		return errorMsg;
		
	}
	
	/**
	 * 废除始发网点与受理部门关系
	 */
	@Override
	public void invalidLadingstationRelation(String ldId){
		ladingstationDepartmentService.invalidLadingstationRelation(ldId);
	}
	
	
	/**
	 * 判断一个字符串是不是数字
	 * @param str
	 * @return
	 */
	private boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches())
		{
			return false;
		}	
		return true;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager#getBusDeptByDeptId(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptByDeptId(String deptId) {
		return ladingstationDepartmentService.getBusDeptByDeptId(deptId);
	} 
	
	@Override
	public String getDeptCityLocation(String deptId) {
		// 默认大陆
		String deptCityLocation = Constant.ISMAINLAND_STRING;
		if (StringUtils.isEmpty(deptId)) {
			deptCityLocation = Constant.ISMAINLAND_STRING;
		} else {
			String standCode = departmentService.getDepartmentById(deptId).getStandardCode();
			if (!StringUtils.isEmpty(standCode)) {
				BussinessDept dept = ladingstationDepartmentService.getBusDeptByCode(standCode);
				if (null != dept) {
					// 验证省份名字有没有香港两个字
					Province province = dept.getProvince();
					String provinceName = "";
					if (null != province) {
						provinceName = province.getName().substring(0, 2);
						if (Constant.HONGKONG_NAME.equals(provinceName)) {
							deptCityLocation = Constant.ISHONGKONG_STRING;
						}
					}
				}
			}
		}
		return deptCityLocation;
	}

	@Override
	public List<Map<String, Object>> getBusDeptByDeptIdOrDeptName(String deptId,
			String deptName, int start, int limit) {
		return ladingstationDepartmentService.getBusDeptByDeptIdOrDeptName(deptId, deptName, start, limit);
	}
	@Override
	public List<Map<String, Object>> getAllBusDeptByDeptIdOrDeptName(String deptId,
			String deptName, int start, int limit) {
		return ladingstationDepartmentService.getAllBusDeptByDeptIdOrDeptName(deptId, deptName, start, limit);
	}

	@Override
	public int countBusDeptByDeptIdOrDeptName(String deptId, String deptName) {
		return ladingstationDepartmentService.countBusDeptByDeptIdOrDeptName(deptId, deptName);
	} 
	@Override
	public int countAllBusDeptByDeptIdOrDeptName(String deptId, String deptName) {
		return ladingstationDepartmentService.countAllBusDeptByDeptIdOrDeptName(deptId, deptName);
	} 
}
