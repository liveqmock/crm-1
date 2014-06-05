package com.deppon.crm.module.common.server.service.impl;

import com.deppon.crm.module.common.server.dao.IBankProvinceCityDao;
import com.deppon.crm.module.common.server.service.IBankProvinceCityService;
import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.BankCity;
import com.deppon.crm.module.common.shared.domain.BankProvince;

/**
 * 
 * @作者: 罗典
 * @时间：2012-12-24下午2:49:50
 * @描述：银行省份城市DAO层实现类
 */
public class BankProvinceCityServiceImpl implements
		IBankProvinceCityService {
	private IBankProvinceCityDao bankProvinceCityDao;

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行城市信息
	 * @参数：银行城市编码
	 * @返回：银行城市信息
	 */
	public BankCity getBankCityByCode(String code) {
		return bankProvinceCityDao.getBankCityByCode(code);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行省份信息
	 * @参数：银行省份编码
	 * @返回：银行省份信息
	 */
	public BankProvince getBankProvinceByCode(String code) {
		return bankProvinceCityDao.getBankProvinceByCode(code);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行城市信息
	 * @参数：银行城市信息
	 * @返回：是否成功
	 */
	public boolean insertBankCity(BankCity bankCity) {
		return bankProvinceCityDao.insertBankCity(bankCity);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行省份信息
	 * @参数：银行城市信息
	 * @返回：是否成功
	 */
	public boolean insertBankProvince(BankProvince bankProvince) {
		return bankProvinceCityDao.insertBankProvince(bankProvince);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据ID修改银行城市信息
	 * @参数：银行城市ID
	 * @返回：是否成功
	 */
	public boolean updateBankCity(BankCity bankCity) {
		return bankProvinceCityDao.updateBankCity(bankCity);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据ID修改银行城市信息
	 * @参数：银行省份ID
	 * @返回：是否成功
	 */
	public boolean updateBankProvince(BankProvince bankProvince) {
		return bankProvinceCityDao.updateBankProvince(bankProvince);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行信息
	 * @参数：银行编码
	 * @返回：银行信息
	 */
	public AccountBank getAccountBankByCode(String code) {
		return bankProvinceCityDao.getAccountBankByCode(code);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行信息
	 * @参数：银行信息
	 * @返回：是否成功
	 */
	public boolean insertAccountBank(AccountBank accountBank) {
		return bankProvinceCityDao.insertAccountBank(accountBank);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据ID修改银行信息
	 * @参数：银行ID
	 * @返回：是否成功
	 */
	public boolean updateAccountBank(AccountBank accountBank) {
		return bankProvinceCityDao.updateAccountBank(accountBank);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行支行信息
	 * @参数：银行支行编码
	 * @返回：银行支行信息
	 */
	public AccountBranch getAccountBranchByCode(String code) {
		return bankProvinceCityDao.getAccountBranchByCode(code);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行支行信息
	 * @参数：银行支行信息
	 * @返回：是否成功
	 */
	public boolean insertAccountBranch(AccountBranch accountBranch) {
		return bankProvinceCityDao.insertAccountBranch(accountBranch);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据ID修改银行支行信息
	 * @参数：银行支行ID
	 * @返回：是否成功
	 */
	public boolean updateAccountBranch(AccountBranch accountBranch) {
		return bankProvinceCityDao.updateAccountBranch(accountBranch);
	}

	public IBankProvinceCityDao getBankProvinceCityDao() {
		return bankProvinceCityDao;
	}

	public void setBankProvinceCityDao(IBankProvinceCityDao bankProvinceCityDao) {
		this.bankProvinceCityDao = bankProvinceCityDao;
	}

	/**
	 * 
	 * <p>
	 * Description:根据Id查询银行城市信息（有效）<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return BankCity
	 */
	public BankCity getBankCityById(String id) {
		return bankProvinceCityDao.getBankCityById(id);
	}

	/**
	 * 
	 * <p>
	 * Description:根据Id查询银行城市信息<br />
	 * </p>
	 * 
	 * @author tangliang
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return BankCity
	 */
	public BankCity getAllBankCityById(String id) {
		return bankProvinceCityDao.getAllBankCityById(id);
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询银行城市信息（有效）<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return BankProvince
	 */
	public BankProvince getBankProvinceById(String id) {
		return bankProvinceCityDao.getBankProvinceById(id);
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询银行城市信息<br />
	 * </p>
	 * 
	 * @author tangliang
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return BankProvince
	 */
	public BankProvince getAllBankProvinceById(String id) {
		return bankProvinceCityDao.getAllBankProvinceById(id);
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询银行信息（有效）<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return AccountBank
	 */
	public AccountBank getAccountBankById(String id) {
		return bankProvinceCityDao.getAccountBankById(id);

	}

	/**
	 * 
	 * <p>
	 * Description:根据id查询银行信息<br />
	 * </p>
	 * 
	 * @author tangliang
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return AccountBank
	 */
	public AccountBank getAllAccountBankById(String id) {
		return bankProvinceCityDao.getAllAccountBankById(id);

	}
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询支行信息（有效）<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return AccountBranch
	 */

	public AccountBranch getAccountBranchById(String id) {
		return bankProvinceCityDao.getAccountBranchById(id);

	}

	/**
	 * 
	 * <p>
	 * Description:根据id查询支行信息<br />
	 * </p>
	 * 
	 * @author tangliang
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return AccountBranch
	 */

	public AccountBranch getAllAccountBranchById(String id) {
		return bankProvinceCityDao.getAllAccountBranchById(id);

	}
	
	@Override
	public BankCity getBankCityByName(String name) {
		return bankProvinceCityDao.getBankCityByName(name);
	}

	@Override
	public BankProvince getBankProvinceByName(String name) {
		return bankProvinceCityDao.getBankProvinceByName(name);
	}

	@Override
	public AccountBank getAccountBankByName(String name) {
		return bankProvinceCityDao.getAccountBankByName(name);
	}

	@Override
	public AccountBranch getAccountBranchByName(String name) {
		return bankProvinceCityDao.getAccountBranchByName(name);
	}

}
