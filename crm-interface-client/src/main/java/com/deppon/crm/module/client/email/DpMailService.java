package com.deppon.crm.module.client.email;


public interface DpMailService {
	//
	//	/**
	//	 * 
	//	 * 方法名：sendMail
	//	 *
	//	 * 方法描述�?
	//	 *
	//	 * @param model
	//	 * @param toAddress
	//	 * @return
	//	 */
	//	public String sendMail(String subject, String templateName, Map<String,String> model,String[] toAddress);
	//	
	//	
	//	/**
	//	 * 
	//	 * 方法名：sendMail
	//	 *
	//	 * 方法描述�?
	//	 *
	//	 * @param model
	//	 * @param toAddress
	//	 * @param resources
	//	 * @return
	//	 */
	//	public String sendMail(String subject, String templateName, Map<String, String> model, String[] toAddress, File[] resources);

	/**
	 * 简单的email功能
	 * @param subject 主题
	 * @param content 内容
	 * @param to 收件人,多个收件人用逗号隔开
	 * @return 发送成功返回true,失败false
	 */
	public boolean sendMail(String subject,String content,String[] to);
}
