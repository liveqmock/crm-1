/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ParticipantRuleManagerImpl.java
 * @package com.deppon.crm.module.common.server.bpsworkflow.rule 
 * @author pgj
 * @version 0.1 2013-11-15
 */
package com.deppon.crm.module.bps.server.rule;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpmsapi.module.client.api.IParticipantRuleManager;
import com.deppon.bpmsapi.module.client.domain.ParticipantRule;
import com.deppon.bpmsapi.module.server.api.exception.BPMSException;

/**   
 * <p>
 * Description:参与者规则<br />
 * </p>
 * @title ParticipantRuleManagerImpl.java
 * @package com.deppon.crm.module.common.server.bpsworkflow.rule 
 * @author pgj
 * @version 0.1 2013-11-15
 */

public class ParticipantRuleManagerImpl implements IParticipantRuleManager{

	private static Logger log = Logger.getLogger(ParticipantRuleManagerImpl.class);
	
	/**
	 * 
	 *<pre>
	 * 方法体说明：选人规则入口方法
	 * 作者：pgj
	 * 日期： 2013-8-8 下午13:03:16
	 * @param rules:规则数组
	 * @param context:规则参数
	 * @return
	 *</pre>
	 */
	public Map<String, BpmsParticipant[]> getParticipants(
			ParticipantRule[] rules, Map context) {
		Map<String, BpmsParticipant[]> res = new HashMap<String, BpmsParticipant[]>();
		//遍历规则数组，进行规则调用
		for (int i = 0; i < rules.length; i++) {
			try {
				//BIZ_类全名与方法名的分离
				rules[i].setRule(rules[i].getRule());
				String[] temp = rules[i].getRule().split("#");
				// temp[0]类名,temp[1]方法名,通过类的反射调用报账规则类方法
				Class<?> cl = Class.forName(temp[0].replaceAll("BIZ_", ""));
				//理赔规则类实例化
				Object obj = cl.newInstance();
				//传递参数判断
				if (temp.length == 2) {
					//理赔规则类方法获取
					Method me = cl.getMethod(temp[1], new Class[] {Map.class });
					//理赔规则类方法调用
					BpmsParticipant[] bps = (BpmsParticipant[]) me.invoke(obj,
							new Object[] { context });
					//将获取到的参与者添加到返回队列中
					res.put(rules[i].getNextActivityDefId(), bps);
				}else if(temp.length == 3){
					//理赔规则类方法获取
					Method me = cl.getMethod(temp[1], new Class[] {Map.class ,String.class});
					//理赔规则类方法调用
					BpmsParticipant[] bps = (BpmsParticipant[]) me.invoke(obj,
							new Object[] { context ,temp[2]});
					//将获取到的参与者添加到返回队列中
					res.put(rules[i].getNextActivityDefId(), bps);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("选人规则"+e);
				throw new BPMSException(e);
			}
		}
		return res;
	}
}
