package com.deppon.crm.module.complaint.server.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.common.shared.domain.TreeNode;
import com.deppon.crm.module.complaint.server.manager.IBaseInfoManager;
import com.deppon.crm.module.complaint.server.service.IBaseInfoService;
import com.deppon.crm.module.complaint.shared.domain.BaseInfo;
import com.deppon.crm.module.complaint.shared.domain.BaseInfoSearchCondition;
import com.deppon.crm.module.complaint.shared.exception.CompException;
import com.deppon.crm.module.complaint.shared.exception.ComplaintExceptionType;
import com.deppon.foss.framework.server.context.UserContext;


/**
 * 
 * <p>
 * Description:处理工单基础资料 manager<br />
 * </p>
 * 
 * @title ComplaintBaseInfoManager.java
 * @package com.deppon.crm.module.complaint.server.manager.impl
 * @author 侯斌飞	
 * @version 0.1 2013-09-12
 */

public class BaseInfoManagerImpl implements IBaseInfoManager {
	
	private final static String COMPLAINT_TEXT="投诉基础资料设置";
	
	private final static String UNUSUAL_TEXT="异常基础资料设置";
	
	private final static String COMPLAINT_CLASSCODE="COMPLAINT";
	
	private final static String COMPLAINT_TYPECODE="COMPLAINT";
	
	private final static String UNUSUAL_TYPECODE="UNUSUAL";
	
	private IBaseInfoService baseInfoService;
	public void setBaseInfoService(IBaseInfoService baseInfoService) {
		this.baseInfoService = baseInfoService;
	}
	/**
	 * 
	 * <p>
	 * Description:基础资料---新增业务项、业务范围、业务场景
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo  
	 * @param user
	 * @return
	 * boolean
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveBaseInfo(BaseInfo baseInfo) {
		//校验基础资料
		this.checkBaseInfo(baseInfo);
		//获取当前操作用户的ID
		baseInfo.setCreateUser(UserContext.getCurrentUser().getId());
		//判断基础资料类型
		if(baseInfo.getLevel()==3){
			//如果为业务类型，则调用业务类型保存的Servie
			baseInfoService.saveBaseType(baseInfo);
		}else if(baseInfo.getLevel()==5){
			//如果为业务场景，则调用业务类型保存的Servie
			baseInfoService.saveBaseScene(baseInfo);
		}else{
			baseInfoService.saveBaseInfo(baseInfo);
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:基础资料---修改业务项、业务范围、业务场景
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfo  
	 * @param user
	 * @return
	 * boolean
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateBaseInfo(BaseInfo baseInfo) {
		//校验待修改基础资料
		this.checkBaseInfo(baseInfo);
		if("".equals(baseInfo.getId()) || baseInfo.getId()==null){
			return false;
		}
		//获取当前操作人ID，保存为最后修改人
		baseInfo.setModifyUser(UserContext.getCurrentUser().getId());
		//判断待修改基础资料类型
		if(baseInfo.getLevel()==3){
			//修改业务类型
			baseInfoService.updateBaseType(baseInfo);
		}else if(baseInfo.getLevel()==5){
			//修改业务场景
			baseInfoService.updateBaseScene(baseInfo);
		}else{
			baseInfoService.updateBaseInfo(baseInfo);
		}
		return true;
	}
	/**
	 * 
	 * <p>
	 * Description:基础资料---删除业务项、业务范围、业务场景
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfoList
	 * @param user
	 * @return
	 * boolean
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteBaseInfo(List<BaseInfo> baseInfoList) {
		//校验待删除基础资料
		this.checkBaseInfoListForDelete(baseInfoList);
		//判断待删除基础资料类型
		if(baseInfoList.get(0).getLevel()==3){
			//删除业务类型
			baseInfoService.deleteBaseType(baseInfoList);
		}else if(baseInfoList.get(0).getLevel()==5){
			//删除业务场景
			baseInfoService.deleteBaseScene(baseInfoList);
		}else{
			baseInfoService.deleteBaseInfo(baseInfoList);
		}
		return true;
	}

	
	/**
	 * 
	 * <p>
	 * Description:基础资料-根据父节点id查询子节点的基础资料
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfoSearchCondition 父节点id
	 * @return
	 * List<BaseInfo> 子类型的基础资料集合
	 */
	@Override
	public List<BaseInfo> searchChildBaseInfoByParentId(
			BaseInfoSearchCondition baseInfoSearchCondition) {
		//此处验证查询条件，若条件不满足，将抛出异常
		checkBaseInfoSearchCondition(baseInfoSearchCondition);
		return baseInfoService.searchChildBaseInfoByParentId(baseInfoSearchCondition);
	}
	
	/**
	 * 
	 * <p>
	 * Description:校验前台传来的基础资料（查询对象）是否为空
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfoSearchCondition
	 * @return
	 * boolean
	 */
	@SuppressWarnings("serial")
	private boolean checkBaseInfoSearchCondition(BaseInfoSearchCondition baseInfoSearchCondition){
		//判断基础资料查询实体是否为空。如果为空则抛出BASEINFO_IS_NULL
		if(baseInfoSearchCondition == null){
			throw new CompException(ComplaintExceptionType.BASEINFO_IS_NULL,
					new Object[] {}) {
			};
		}
		//当查询实体不为空时，判断其parentid和level是否为空
		if(null == baseInfoSearchCondition.getParentId() && null == baseInfoSearchCondition.getLevel()){
			throw new CompException(ComplaintExceptionType.BASEINFO_SEARCH_EXCEPTION, 
					new Object[] {}) {
			};
		}
		return true;
	}
	/**
	 * 校验基础资料对象是否为空
	 * @param baseInfo
	 * @return
	 */
	@SuppressWarnings("serial")
	private boolean checkBaseInfo(BaseInfo baseInfo){
		//基础资料对象不为空
		if(baseInfo == null){
			throw new CompException(ComplaintExceptionType.BASEINFO_IS_NULL, 
					new Object[] {}) {
			};
		}
		//parentId、getClassCode、typeCode都不能为空，若为空则根据是否存在ID判断操作类型为添加或修改
		if(null==baseInfo.getParentId() || null==baseInfo.getClassCode() 
				||null==baseInfo.getTypeCode() || null == baseInfo.getLevel()){
			//添加失败
			if(baseInfo.getId()==null||"".equals(baseInfo.getId())){
				throw new CompException(ComplaintExceptionType.BASEINFO_INSERT_EXCEPTION, 
						new Object[] {}) {
				};
			}else{
				throw new CompException(ComplaintExceptionType.BASEINFO_UPDATE_EXCEPTION,
						new Object[] {}) {
				};
			}
		}
		return true;
	}
	/**
	 * 校验删除的基础资料List是否存在有子集的，有则返回false
	 * @param baseInfoList 要删除的基础资料的集合
	 * @return
	 */
	@SuppressWarnings("serial")
	private boolean checkBaseInfoListForDelete(List<BaseInfo> baseInfoList){
		if(null==baseInfoList){
			throw new CompException(ComplaintExceptionType.BASEINFO_IS_NULL,
					new Object[] {}) {
			};
		}
		//遍历基础资料集合，如果待删除基础资料不为叶子节点（即存在子集），则不能删除
		for(int i = 0 ;i < baseInfoList.size();i++){
			if(baseInfoList.get(i).getIsLeaf()==0){
				throw new CompException(ComplaintExceptionType.BASEINFO_DELETE_EXCEPTION,
						new Object[] {}) {
				};
			}
		}
		return true;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:treeNodeList 基础资料-根据父节点id查询子节点的基础资料集合
	 * </p>
	 * @author LiuY
	 * @version 0.1 2013-9-23
	 * @param baseInfoSearchCondition 查询条件
	 * @return
	 * List<BaseInfoSearchCondition> 子类型的基础资料集合
	 */
	@Override
	public List<TreeNode<BaseInfo>> searchTreeChildNodeList(
			String node) {
		
		BaseInfoSearchCondition baseInfoSearchCondition = new BaseInfoSearchCondition();
		baseInfoSearchCondition.setParentId(new Integer(node));
		
		List<TreeNode<BaseInfo>> nodes=new ArrayList<TreeNode<BaseInfo>>();
		if(node.equals("0")){//初始值
			TreeNode<BaseInfo> comNode = new TreeNode<BaseInfo>();
			comNode.setText(COMPLAINT_TEXT);
			comNode.setLeaf(false);
			comNode.setId("-1");
			BaseInfo cb = new BaseInfo();
			cb.setLevel(0);
			cb.setClassCode(COMPLAINT_CLASSCODE);
			cb.setTypeCode(COMPLAINT_TYPECODE);
			comNode.setEntity(cb); 
		
			TreeNode<BaseInfo> dutyNode = new TreeNode<BaseInfo>();
			dutyNode.setText(UNUSUAL_TEXT);
			dutyNode.setLeaf(false);
			dutyNode.setId("-2");
			BaseInfo db = new BaseInfo();
			db.setLevel(0);
			db.setClassCode(COMPLAINT_CLASSCODE);
			db.setTypeCode(UNUSUAL_TYPECODE);
			dutyNode.setEntity(db);
			nodes.add(comNode);
			nodes.add(dutyNode);
			return nodes;
		}else{
			BaseInfo condition = new BaseInfo();
			condition.setParentId(Integer.parseInt(node));
			List<BaseInfo> list =searchChildBaseInfoByParentId(baseInfoSearchCondition);
			for(BaseInfo baseInfo : list){
				if(baseInfo.getLevel()!=5){//第五级不显示
					TreeNode<BaseInfo> bi = new TreeNode<BaseInfo>();
					bi.setText(baseInfo.getBaseInfo());
	//				bi.setLeaf(baseInfo.getIsLeaf().equals(0)?false:true); 前台体验不行
					bi.setLeaf(false);
					bi.setId(baseInfo.getId());
					bi.setEntity(baseInfo); 
					nodes.add(bi);
				}
			}
		}
		return nodes;
	}
}