/**   
 * @title ISequence.java
 * @package com.deppon.crm.module.order.server.dao
 * @description what to do
 * @author 安小虎
 * @update 2012-3-9 上午9:13:43
 * @version V1.0   
 */
package com.deppon.crm.module.order.server.dao;
/**
 * 
 * <p>
 * Description:序列工具类<br />
 * 本次修改只是添加注释
 * </p>
 * @title ISequenceTool.java
 * @package com.deppon.crm.module.order.server.dao 
 * @author zouming
 * @version 0.1 2013-1-22上午10:57:59
 */
public interface ISequenceTool {
	/**
	 * 
	 * @description function.
	 * @author 安小虎
	 * @version 0.1 2012-3-9
	 * @param seqName
	 * @date 2012-3-9
	 * @return id
	 * @update 2012-3-9 上午9:14:49
	 */
	String getSequence(String seqName);
}
