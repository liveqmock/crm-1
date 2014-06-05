package com.deppon.crm.module.common.server.dao;

import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.BankCity;
import com.deppon.crm.module.common.shared.domain.BankProvince;

/**
 * @作者: 罗典
 * @时间：2012-12-24下午2:33:33
 * @描述：银行省份城市Dao层
 */

public interface IBankProvinceCityDao {
	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行城市信息
	 * @参数：银行城市编码
	 * @返回：银行城市信息
	 */
	public BankCity getBankCityByCode(String code);


	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行城市信息
	 * @参数：银行城市信息
	 * @返回：是否成功
	 */
	public boolean insertBankCity(BankCity bankCity);
	
	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码修改银行城市信息
	 * @参数：银行城市编码
	 * @返回：是否成功
	 */
	public boolean updateBankCity(BankCity bankCity);

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行省份信息
	 * @参数：银行省份编码
	 * @返回：银行省份信息
	 */
	public BankProvince getBankProvinceByCode(String code);

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行省份信息
	 * @参数：银行城市信息
	 * @返回：是否成功
	 */
	public boolean insertBankProvince(BankProvince bankProvince);

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行城市信息
	 * @参数：银行省份编码
	 * @返回：是否成功
	 */
	public boolean updateBankProvince(BankProvince bankProvince);
	
	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行信息
	 * @参数：银行编码
	 * @返回：银行信息
	 */
	public AccountBank getAccountBankByCode(String code);

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行信息
	 * @参数：银行信息
	 * @返回：是否成功
	 */
	public boolean insertAccountBank(AccountBank accountBank);
	
	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码修改银行信息
	 * @参数：银行编码
	 * @返回：是否成功
	 */
	public boolean updateAccountBank(AccountBank accountBank);

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行支行信息
	 * @参数：银行支行编码
	 * @返回：银行支行信息
	 */
	public AccountBranch getAccountBranchByCode(String code);

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行支行信息
	 * @参数：银行支行信息
	 * @返回：是否成功
	 */
	public boolean insertAccountBranch(AccountBranch accountBranch);
	
	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码修改银行支行信息
	 * @参数：银行支行编码
	 * @返回：是否成功
	 */
	public boolean updateAccountBranch(AccountBranch accountBranch);
	/**
	 * 
	 * <p>
	 * Description:根据Id查询银行城市信息（有效）<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * BankCity
	 */
	public BankCity getBankCityById(String id);
	
	/**
	 * 
	 * <p>
	 * Description:根据Id查询银行城市信息<br />
	 * </p>
	 * @author tangliang
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * BankCity
	 */
	public BankCity getAllBankCityById(String id);
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询银行省份信息（有效）<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * BankProvince
	 */
	public BankProvince getBankProvinceById(String id);
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询银行省份信息<br />
	 * </p>
	 * @author tangliang
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * BankProvince
	 */
	public BankProvince getAllBankProvinceById(String id);
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询银行信息（有效）<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * AccountBank
	 */
	public AccountBank getAccountBankById(String id);
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询银行信息<br />
	 * </p>
	 * @author tangliang
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * AccountBank
	 */
	public AccountBank getAllAccountBankById(String id);
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询支行信息（有效）<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * AccountBranch
	 */

	
	public AccountBranch getAccountBranchById(String id);
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询支行信息<br />
	 * </p>
	 * @author tangliang
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * AccountBranch
	 */

	
	public AccountBranch getAllAccountBranchById(String id);
	
	/**
	 * 
	 * <p>
	 * Description:根据name查询银行城市信息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * BankCity
	 */
	public BankCity getBankCityByName(String name);
	
	/**
	 * 
	 * <p>
	 * Description:根据name查询银行城市信息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * BankProvince
	 */
	public BankProvince getBankProvinceByName(String name);
	/**
	 * 
	 * <p>
	 * Description:根据name查询银行信息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * AccountBank
	 */
	public AccountBank getAccountBankByName(String name);
	/**
	 * 
	 * <p>
	 * Description:根据name查询支行信息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * AccountBranch
	 */

	
	public AccountBranch getAccountBranchByName(String name);
}
