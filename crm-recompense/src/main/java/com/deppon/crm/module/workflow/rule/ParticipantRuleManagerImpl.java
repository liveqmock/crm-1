package com.deppon.crm.module.workflow.rule;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpmsapi.module.client.api.IParticipantRuleManager;
import com.deppon.bpmsapi.module.client.domain.ParticipantRule;
import com.deppon.bpmsapi.module.server.api.exception.BPMSException;
import com.deppon.crm.module.workflow.rule.branch.CommonBranch;

/**
 * <pre>
 * 功能:供普元工作流调用的规则方法调用公共接口方法
 * 作者：andy
 * 日期：2013-7-29下午12:53:15
 * </pre>
 */
public class ParticipantRuleManagerImpl implements IParticipantRuleManager{

	private static Logger log = Logger.getLogger(CommonBranch.class);
	
	/**
	 * 
	 *<pre>
	 * 方法体说明：选人规则入口方法
	 * 作者：andy
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
