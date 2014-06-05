/**
 * @Title: SynchronizeLogConValidator.java
 * @Package com.deppon.crm.module.logmoniting.server.utils
 * @Description:{todo}
 * @author: 唐亮
 * @date: 2013-9-16 下午4:34:42
 * @version V1.0
 */
package com.deppon.crm.module.logmoniting.server.utils;

import com.deppon.crm.module.logmoniting.shared.Excepion.LogInfoException;
import com.deppon.crm.module.logmoniting.shared.Excepion.LogInfoExceptionType;
import com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo;

/**
 * <p>
 * TODO<br />
 * </p>
 * 
 * @title SynchronizeLogConValidator.java
 * @package com.deppon.crm.module.logmoniting.server.utils
 * @author 唐亮
 * @version 0.1 2013-9-16
 */
public class SynchronizeLogConValidator {
	/**
	 * 
	 * @Title: ValidateSearchCondition
	 *  <p>
	 * @Description: 校验同步日志查询条件是否完整
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-16
	 * @return void
	 * @throws
	 */
	public void ValidateSearchCondition(SynchronizeLogInfo synchronizeLogInfo) {
		// 校验查询条件是否为空
		if (ValidateUtil.objectIsEmpty(synchronizeLogInfo)
				|| synchronizeLogInfo.getSynStartTime() == null
				|| synchronizeLogInfo.getSynFinishTime() == null
				|| synchronizeLogInfo.getTableName() == null
				|| synchronizeLogInfo.getKeyWord() == null) {
			throw new LogInfoException(
					LogInfoExceptionType.Data_Lost_Error);
		}
	}
	/**
	 * 
	 * @Title: ValidateUpdateCondition
	 *  <p>
	 * @Description: 校验传入的修改后的同步日志数据是否完整
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-16
	 * @return void
	 * @throws
	 */
	public void ValidateUpdateCondition(SynchronizeLogInfo synchronizeLogInfo){
		//校验前台传入的数据是否完整
		if (ValidateUtil.objectIsEmpty(synchronizeLogInfo)
				||synchronizeLogInfo.getTableName() == null
				||synchronizeLogInfo.getKeyWord() == null
				||synchronizeLogInfo.getHandleType() == null
				||synchronizeLogInfo.getSynStatus() == null) {
			throw new LogInfoException(LogInfoExceptionType.Data_Lost_Error);
		}
	}
}
