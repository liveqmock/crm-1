package com.deppon.crm.module.marketing.server.manager;

import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.shared.domain.MessageSendTask;
/**   
 * <p>
 * Description:营销短信发送Manager接口<br />
 * </p>
 * @title IMessageSendManager.java
 * @package com.deppon.crm.module.marketing.server.manager
 * @author ZhouYuan
 * @version 0.1 2013-09-27
 */
public interface IMessageSendManager {
	/**
	 * 
	 * <p>
	 * 将上传的文件名和路径保存<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @param MessageSend mst
	 * @return int
	 */
	int saveMessageFile(MessageSendTask mst,User user); 
	/**
	 * 
	 * <p>
	 * 解析EXCEL文件并发送短信<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @param MessageSend mst
	 * @return int
	 */
	void sendMessageFromExcel();
	 /**
	 * 
	 * <p>
	 * 查询全部短信发送任务<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @return List<MessageSendTask>
	 */
	Map<String,Object> searchMessageSendTaskAll(int start,int limit);
	 /**
	 * 
	 * <p>
	 * 根据任务ID查询任务详情<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @return List<MessageSendTask>
	 */
	Map<String,Object> searchMessageSendDetailByTaskId(String id,int start,int limit);
}
