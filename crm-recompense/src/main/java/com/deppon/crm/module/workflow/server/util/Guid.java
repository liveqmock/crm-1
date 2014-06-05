package com.deppon.crm.module.workflow.server.util;

import java.util.Random;

/**
 * @Title: Guid.java
 * @Package com.deppon.crm.module.workflow.server.util
 * @Description: TODO(添加描述)
 *  工具类的定位：
 *  1.作为一个软件开发者，缺乏想象力是最严重的罪过之一。
 *    我们经常把事情重复做一遍又一遍，但是我们很少改变这种方式。
 *    经过这些年开发，在我的工具箱里面有了一些每个项目我都需要用到的工具，
 *    烦人的重复工作不再是我的事.
 *  2.工具，汉语词语，原指工作时所需用的器具，
 *    后引申为为达到、完成或促进某一事物的手段。
 *    它的好处可以是机械性，也可以是智能性的。
 *    大部分工具都是简单机械。
 *    例如一根铁棍可以当作槓杆使用，力点离开支点越远，杠杆传递的力就越大.
 *    而工具类就是通过实现独立功能单元的一个工具
 *  3.直接使用的工具类，不但可以在本应用中使用这些工具类，也可以在其它的应用中使用，
 *    这些工具类中的大部分是可以在脱离各种框架和应用时使用的。
 *    工具类并在程序编写时适当使用和提取，将有助于提高开发效率、增强代码质量。
 *  4.对于软件开发过程中，需要使用到各种框架，而框架中往往都提供了相应的工具类。
 *    比如spring、struts、ibatis、hibernate。
 *    而java本身也提供不少的工具类供开发员进行特殊的处理
 * @author andy
 * @date 2013-8-13 下午2:18:19
 * @version V1.0
 */
public class Guid {

	/**
	 * 
	 */
	private static Random rd = new Random();

	/**
	 * 
	 * <pre>
	 * 方法体说明：实例化一个新的guid 
	 *  作为实体对象的主键
	 *  首先是以系统简体名称开始，CRM
	 *  在加入当前时间
	 *  然后加入16位随机码
	 *  最后生成系统唯一键id
	 * 作者：andy
	 * 日期：2013-8-13
	 * </pre>
	 * 
	 * @return
	 */
	public synchronized static String newDCGuid() {
		// 所属系统名称
		String sysCode = "CRM";
		// 定义id
		String id = "";
		// 记录当前时间 以long类型显示
		String time = System.currentTimeMillis() + "";
		// 如果id为空的话，则生成16位随机码
		if (id.equals("")) {
			id = 
					Integer.toHexString(rd.nextInt(16)).toUpperCase()
					+ Integer.toHexString(rd.nextInt(16)).toUpperCase()
					+ Integer.toHexString(rd.nextInt(16)).toUpperCase()
					+ Integer.toHexString(rd.nextInt(16)).toUpperCase()
					+ Integer.toHexString(rd.nextInt(16)).toUpperCase()
					+ Integer.toHexString(rd.nextInt(16)).toUpperCase()
					+ Integer.toHexString(rd.nextInt(16)).toUpperCase()
					+ Integer.toHexString(rd.nextInt(16)).toUpperCase()
					+ Integer.toHexString(rd.nextInt(16)).toUpperCase()
					+ Integer.toHexString(rd.nextInt(16)).toUpperCase();
		}
		// 最后通过拼接随机码返回唯一键id
		String rtn = sysCode + time + id;
		return rtn;
	}
}
