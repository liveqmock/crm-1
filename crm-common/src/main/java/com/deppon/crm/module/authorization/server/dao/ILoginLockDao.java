package com.deppon.crm.module.authorization.server.dao;

import java.util.Date;

import com.deppon.crm.module.authorization.shared.domain.User;


public interface ILoginLockDao {
	/**
	 * 
	 * <p>
	 * 查询最后一次密码错误时间<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @return
	 * long
	 */
	public Date queryUserlastErrTime(User user); 
	
	/**
	 * 
	 * <p>
	 * 新增最后登陆限制时间<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @param user
	 * @return
	 * boolean
	 */
	public boolean insertUserlastErrTime(User user);
	
	/**
	 * 
	 * <p>
	 * 清除最后失败时间<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @param user
	 * @return
	 * boolean
	 */
	public boolean cleanUserlastErrTime(User user);

	/**
	 * <p>
	 * 清除错误密码次数<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-11
	 * @param user
	 * void
	 */
	public void cleanErrorTimes(User user);

	/**
	 * <p>
	 * 查询错误密码次数<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-11
	 * @param user
	 * @return
	 * int
	 */
	public int queryErrorTimes(User user);

	/**
	 * <p>
	 * 更新输入错误密码次数<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-11
	 * @param user
	 * @return
	 * boolean
	 */
	public boolean updateErrorTimes(User user);
}
