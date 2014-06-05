/**   
 * @title Sequence.java
 * @package com.deppon.crm.module.order.server.dao.impl
 * @description what to do
 * @author 安小虎
 * @update 2012-3-9 上午9:17:05
 * @version V1.0   
 */
package com.deppon.crm.module.order.server.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.deppon.crm.module.order.server.dao.ISequenceTool;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 * 
 * <p>
 * Description:序列工具类<br />
 * </p>
 * @title SequenceTool.java
 * @package com.deppon.crm.module.order.server.dao.impl 
 * @author zouming
 * @version 0.1 2013-1-22上午11:24:07
 */
public class SequenceTool extends iBatis3DaoImpl implements ISequenceTool {
	
	//Mybatis命名空间
	private static final String NAMESPACE = "com.deppon.crm.module.order.shared.domain.Order.";

	/**
	 * 
	 * <p>
	 * Description:序列<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:00:05
	 * @param seqName
	 * @return
	 * @update 2013-1-28上午9:00:05
	 */
	public String getSequence(String seqName) {
		Map map = new HashMap();
		map.put("seqName", seqName);
		return this.getSqlSession().selectOne(NAMESPACE + "getSeqId", map)
				.toString();
	}

}
