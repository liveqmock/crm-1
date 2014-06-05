package com.deppon.crm.module.marketing.server.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.marketing.shared.domain.activity.ClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityConstance;
import com.deppon.crm.module.marketing.shared.exception.ClientBaseException;
import com.deppon.crm.module.marketing.shared.exception.ClientBaseExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * <p>
 * Description:客户群校验<br />
 * </p>
 * 
 * @title ClientBaseValidator.java
 * @package com.deppon.crm.module.marketing.server.utils
 * @author lvchongxin
 * 
 */
public class ClientBaseValidator {
	/**
	 * 
	 * 
	 * <p>
	 * 检查新增条件<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-6
	 * @param ClientBase
	 * @return
	 */
	public static void checkAddCilentBase(ClientBase cb, boolean flag) {
		// 客户群名称不能为空
		if (cb.getClientBaseName().isEmpty()) {
			ClientBaseException e = new ClientBaseException(
					ClientBaseExceptionType.CLIENTBASE_ISNULL);
			// 抛出异常
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//客户群名称不得超过40
		if(cb.getClientBaseName().length()>40){
			ClientBaseException e = new ClientBaseException(
					ClientBaseExceptionType.CLIENTBASE_NAMELENGTHTHEN40);
			// 抛出异常
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
			
		}
		// 客户创建时间是否只有一个为空(异或)
		if (null == cb.getClientCreateStartTime()
				^ null == cb.getClientCreateEndTime()) {
			ClientBaseException e = new ClientBaseException(
					ClientBaseExceptionType.CLIENTBASETIME_ONENULL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
			
		}

		// 判断开始时间不能大于结束时间
		if(null!=cb.getClientCreateStartTime()&&null!=cb.getClientCreateEndTime()){
			if (cb.getClientCreateStartTime().getTime() > cb
					.getClientCreateEndTime().getTime()) {
				ClientBaseException e = new ClientBaseException(
						ClientBaseExceptionType.CLIENTBASE_STRATTHANENDTIME);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
		// 客户群名不能重复
		if (flag) {

			ClientBaseException e = new ClientBaseException(
					ClientBaseExceptionType.CLIENTBASENAME_REPEAT);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}

		// 效验线路部门
		if (null != cb.getLineType()) {
			List<LineDept> lineDept = cb.getLineDept();
			if (lineDept.size() > 100) {
				ClientBaseException e = new ClientBaseException(
						ClientBaseExceptionType.CLIENTBASE_MORETHEN100);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
		
			}
			// 出发区域效验
			if (MarketActivityConstance.LINE_TYPE_LEAVE.equals(cb.getLineType())) {
				for (LineDept l : lineDept) {
					if (StringUtils.isEmpty(l.getLeavedDeptName())
							|| StringUtils.isEmpty(l.getLeaveDeptCode())
							|| StringUtils.isNotEmpty(l.getArriveDeptName())
							|| StringUtils.isNotEmpty(l.getArriveDeptCode())) {
						ClientBaseException e = new ClientBaseException(
								ClientBaseExceptionType.CLIENTBASE_LINEDEPTEXCEPTION);
						throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
								new Object[] {}) {
						};

					}
				}
				//到达区域效验
			} else if (MarketActivityConstance.LINE_TYPE_ARRIVE.equals(cb.getLineType())) {
				for (LineDept l : lineDept) {
					if ( StringUtils.isEmpty(l.getArriveDeptName())
							|| StringUtils.isEmpty(l.getArriveDeptCode())
							|| StringUtils.isNotEmpty(l.getLeavedDeptName())
							|| StringUtils.isNotEmpty(l.getLeaveDeptCode())) {
						ClientBaseException e = new ClientBaseException(
								ClientBaseExceptionType.CLIENTBASE_LINEDEPTEXCEPTION);
						throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
								new Object[] {}) {
						};

					}
				}//出发到达外场效验
			}else if(MarketActivityConstance.LINE_TYPE_LEAVE_ARRIVE.equals(cb.getLineType())){
				for (LineDept l : lineDept) {
	
					if (StringUtils.isEmpty(l.getArriveDeptCode())&&StringUtils.isEmpty(l.getLeaveDeptCode())) {
						ClientBaseException e = new ClientBaseException(
								ClientBaseExceptionType.CLIENTBASE_LINEDEPTEXCEPTION);
						throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
								new Object[] {}) {
						};

					}
				}
				
			}
		}
	}
	/**
	 * 
	 * 
	 * <p>
	 * 效验是否删除成功<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-19
	 * @param 
	 * @return
	 */
	public static void checkIfdelete(boolean ifSuccess){
		if(false==ifSuccess){
			ClientBaseException e = new ClientBaseException(
					ClientBaseExceptionType.CLIENTBASE_DELETEEXCEPTION);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	
	/**
	 * 
	 * 
	 * <p>
	 * 验证是否具有更新客户群权限<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-11
	 * @param 
	 * @return
	 */
	public static void checkModiftyAuthority(List<String> idList,String clientBaseStatus,User user)
	{
		if(!MarketActivityConstance.CLIENTBASE_STATUS_UNUSED.equals(clientBaseStatus)){
			ClientBaseException e = new ClientBaseException(
					ClientBaseExceptionType.CLIENTBASE_ONELYUNUSED);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//获取当前用户ID
		String userId=user.getEmpCode().getId();
		if(idList.contains(userId)){
			//验证是否具有权限
			return;
			
		}
		//没有操作权限异常
		ClientBaseException e = new ClientBaseException(
				ClientBaseExceptionType.CLIENTBASE_NOAUTHORITYMODIFY);
		throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
				new Object[] {}) {
		};
	}
	/**
	 * 
	 * 
	 * <p>
	 * 验证数据字典<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-21
	 * @param 
	 * @return
	 */
	public static void checkSecondTreade(Detail secondTrade){
		if(null==secondTrade.getStatus()||null==secondTrade.getCode()||null==secondTrade.getCodeDesc()){
			ClientBaseException e = new ClientBaseException(
					ClientBaseExceptionType.CLIENTBASE_DICTIONNARYEXCEPTION);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	
		
	}
	
}
