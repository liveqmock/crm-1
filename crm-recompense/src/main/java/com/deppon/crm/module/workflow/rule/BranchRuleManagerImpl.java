/**   
 * @Title: BranchRuleManagerImpl.java 
 * @Package com.deppon.crm.module.workflow.rule 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author andy
 * @date 2013-8-8 下午12:53:15 
 * @version V1.0   
 */
package com.deppon.crm.module.workflow.rule;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.Logger;

import com.deppon.bpmsapi.module.client.api.IBranchRuleManager;
import com.deppon.bpmsapi.module.server.api.exception.BPMSException;
import com.deppon.crm.module.workflow.rule.branch.CommonBranch;

/**
 * <pre>
 * 功能:供普元工作流调用的规则方法调用公共接口方法
 * 作者：andy
 * 日期：2013-8-8下午12:53:15
 * </pre>
 */
public class BranchRuleManagerImpl implements IBranchRuleManager {
	private static Logger log = Logger.getLogger(CommonBranch.class);

	/**
	 * 
	 * <pre>
	 * 方法体说明：分支规则入口方法
	 * 作者：andy
	 * 日期： 2013-8-8 下午13:03:16
	 * @param rule：规则数组
	 * @param map：规则参数
	 * @return
	 * </pre>
	 */
	public boolean[] isTrue(String[] rule, Map map) {
		boolean[] res = new boolean[rule.length];
		//遍历规则数组，进行规则调用
		for (int i = 0; i < rule.length; i++) {
			if (rule[i].equals("true") || rule[i].equals("false")) {//非报账规则判断
				res[i] = Boolean.parseBoolean(rule[i]);
			} else {//报账规则调用
				boolean result = false;
				try {
					//BIZ_类全名与方法名的分离
					String[] temp = rule[i].split("#");
					// temp[0]类名,temp[1]方法名,通过类的反射调用报账规则类方法
					Class<?> cl = Class.forName(temp[0].replaceAll("BIZ_", ""));
					//报账规则类实例化
					Object obj = cl.newInstance();
					//报账规则类方法获取
					Method me = cl
							.getMethod(temp[1], new Class[] { Map.class });
					//报账规则类方法调用
					result = (Boolean) me.invoke(obj, new Object[] { map });
				} catch (Exception e) {
					e.printStackTrace();
					log.error("分支规则" + e);
					throw new BPMSException(e);
				}
				res[i] = result;
			}
		}
		return res;
	}
}
