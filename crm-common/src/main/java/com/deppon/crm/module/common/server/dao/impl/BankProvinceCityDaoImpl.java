package com.deppon.crm.module.common.server.dao.impl;

import com.deppon.crm.module.common.server.dao.IBankProvinceCityDao;
import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.BankCity;
import com.deppon.crm.module.common.shared.domain.BankProvince;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * @作者: 罗典
 * @时间：2012-12-24下午2:49:50
 * @描述：银行省份城市DAO层实现类
 */
public class BankProvinceCityDaoImpl extends iBatis3DaoImpl implements
		IBankProvinceCityDao {
	private static final String NAMESPACE = "com.deppon.crm.module.common.server.domain.ProvinceCity.";
	// 根据编码查询银行城市信息
	private static final String QERUY_BANKCITY_CODE = "queryBankCityByCode";
	// 根据编码查询银行省份信息
	private static final String QERUY_BANKPROVINCE_CODE = "queryBankProvinceByCode";
	// 保存银行省份信息
	private static final String INSERT_BANKPROVINCE = "insertBankProvince";
	// 保存银行城市信息
	private static final String INSERT_BANKCITY = "insertBankCity";
	// 根据编码修改省份信息
	private static final String UPDATE_BANKPROVINCE_ID = "updateBankProvinceById";
	// 根据编码修改城市信息
	private static final String UPDATE_BANKCITY_ID = "updateBankCityById";
	// 根据编码查询银行信息
	private static final String QUERY_ACCOUNTBANK_CODE = "queryAccountBankByCode";
	// 根据编码查询银行支行信息
	private static final String QUERY_ACCOUNTBRANCH_CODE = "queryAccountBranchByCode";
	// 根据ID修改银行信息
	private static final String UPDATE_ACCOUNTBANK_ID = "updateAccountBankById";
	// 根据ID修改银行支行信息
	private static final String UPDATE_ACCOUNTBRANCH_ID = "updateAccountBranchById";
	// 新增银行信息
	private static final String INSERT_ACCOUNTBANK = "insertAccountBank";
	// 新增银行支行信息
	private static final String INSERT_ACCOUNTBRANCH = "insertAccountBranch";
	//根据编码查询银行城市信息（有效）
	private static final String QERUY_BANKCITY_ID = "queryBankCityById";
	//根据编码查询银行城市信息
	private static final String QERUY_ALL_BANKCITY_ID = "queryAllBankCityById";
	// 根据编码查询银行省份信息(有效)
	private static final String QERUY_BANKPROVINCE_ID = "queryBankProvinceById";
	// 根据编码查询银行省份信息
	private static final String QERUY_ALL_BANKPROVINCE_ID = "queryAllBankProvinceById";
	// 根据编码查询银行信息(有效)
	private static final String QUERY_ACCOUNTBANK_ID = "queryAccountBankById";
	// 根据编码查询银行信息
	private static final String QUERY_ALL_ACCOUNTBANK_ID = "queryAllAccountBankById";
	// 根据编码查询银行支行信息(有效)
	private static final String QUERY_ACCOUNTBRANCH_ID = "queryAccountBranchById";
	// 根据编码查询银行支行信息
	private static final String QUERY_ALL_ACCOUNTBRANCH_ID = "queryAllAccountBranchById";
	//根据编码查询银行城市信息
	private static final String QERUY_BANKCITY_NAME = "queryBankCityByName";
	// 根据编码查询银行省份信息
	private static final String QERUY_BANKPROVINCE_NAME = "queryBankProvinceByName";
	// 根据编码查询银行信息
	private static final String QUERY_ACCOUNTBANK_NAME = "queryAccountBankByName";
	// 根据编码查询银行支行信息
	private static final String QUERY_ACCOUNTBRANCH_NAME = "queryAccountBranchByName";

	// 根据ID修改银行信息
	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行城市信息
	 * @参数：银行城市编码
	 * @返回：银行城市信息
	 */
	public BankCity getBankCityByCode(String code) {
		return (BankCity) this.getSqlSession().selectOne(
				NAMESPACE + QERUY_BANKCITY_CODE, code);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行省份信息
	 * @参数：银行省份编码
	 * @返回：银行省份信息
	 */
	public BankProvince getBankProvinceByCode(String code) {
		return (BankProvince) this.getSqlSession().selectOne(
				NAMESPACE + QERUY_BANKPROVINCE_CODE, code);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行城市信息
	 * @参数：银行城市信息
	 * @返回：是否成功
	 */
	public boolean insertBankCity(BankCity bankCity) {
		return this.getSqlSession().insert(NAMESPACE + INSERT_BANKCITY,
				bankCity) > 0 ? true : false;
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行省份信息
	 * @参数：银行城市信息
	 * @返回：是否成功
	 */
	public boolean insertBankProvince(BankProvince bankProvince) {
		return this.getSqlSession().insert(NAMESPACE + INSERT_BANKPROVINCE,
				bankProvince) > 0 ? true : false;
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码修改银行城市信息
	 * @参数：银行城市编码
	 * @返回：是否成功
	 */
	public boolean updateBankCity(BankCity bankCity) {
		return this.getSqlSession().update(NAMESPACE + UPDATE_BANKCITY_ID,
				bankCity) > 0 ? true : false;
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行城市信息
	 * @参数：银行省份编码
	 * @返回：是否成功
	 */
	public boolean updateBankProvince(BankProvince bankProvince) {
		return this.getSqlSession().update(NAMESPACE + UPDATE_BANKPROVINCE_ID,
				bankProvince) > 0 ? true : false;
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行信息
	 * @参数：银行编码
	 * @返回：银行信息
	 */
	public AccountBank getAccountBankByCode(String code) {
		return (AccountBank) this.getSqlSession().selectOne(
				NAMESPACE + QUERY_ACCOUNTBANK_CODE, code);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行信息
	 * @参数：银行信息
	 * @返回：是否成功
	 */
	public boolean insertAccountBank(AccountBank accountBank) {
		return this.getSqlSession().insert(NAMESPACE + INSERT_ACCOUNTBANK,
				accountBank) > 0 ? true : false;
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码修改银行信息
	 * @参数：银行编码
	 * @返回：是否成功
	 */
	public boolean updateAccountBank(AccountBank accountBank) {
		return this.getSqlSession().update(NAMESPACE + UPDATE_ACCOUNTBANK_ID,
				accountBank) > 0 ? true : false;
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码查询银行支行信息
	 * @参数：银行支行编码
	 * @返回：银行支行信息
	 */
	public AccountBranch getAccountBranchByCode(String code) {
		return (AccountBranch) this.getSqlSession().selectOne(
				NAMESPACE + QUERY_ACCOUNTBRANCH_CODE, code);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行支行信息
	 * @参数：银行支行信息
	 * @返回：是否成功
	 */
	public boolean insertAccountBranch(AccountBranch accountBranch) {
		return this.getSqlSession().insert(NAMESPACE + INSERT_ACCOUNTBRANCH,
				accountBranch) > 0 ? true : false;
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码修改银行支行信息
	 * @参数：银行支行编码
	 * @返回：是否成功
	 */
	public boolean updateAccountBranch(AccountBranch accountBranch) {
		return this.getSqlSession().update(NAMESPACE + UPDATE_ACCOUNTBRANCH_ID,
				accountBranch) > 0 ? true : false;
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
		return (BankCity) this.getSqlSession().selectOne(
				NAMESPACE + QERUY_BANKCITY_ID, id);
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
		return (BankCity) this.getSqlSession().selectOne(
				NAMESPACE + QERUY_ALL_BANKCITY_ID, id);
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
		return (BankProvince) this.getSqlSession().selectOne(
				NAMESPACE + QERUY_BANKPROVINCE_ID, id);
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
		return (BankProvince) this.getSqlSession().selectOne(
				NAMESPACE + QERUY_ALL_BANKPROVINCE_ID, id);
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
		return (AccountBank) this.getSqlSession().selectOne(
				NAMESPACE + QUERY_ACCOUNTBANK_ID, id);

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
		return (AccountBank) this.getSqlSession().selectOne(
				NAMESPACE + QUERY_ALL_ACCOUNTBANK_ID, id);

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
		return (AccountBranch) this.getSqlSession().selectOne(
				NAMESPACE + QUERY_ACCOUNTBRANCH_ID, id);
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
		return (AccountBranch) this.getSqlSession().selectOne(
				NAMESPACE + QUERY_ALL_ACCOUNTBRANCH_ID, id);
	}

	@Override
	public BankCity getBankCityByName(String name) {
		return (BankCity) this.getSqlSession().selectOne(
				NAMESPACE + QERUY_BANKCITY_NAME, name);
	}

	@Override
	public BankProvince getBankProvinceByName(String name) {
		return (BankProvince) this.getSqlSession().selectOne(
				NAMESPACE + QERUY_BANKPROVINCE_NAME, name);
	}

	@Override
	public AccountBank getAccountBankByName(String name) {
		return (AccountBank) this.getSqlSession().selectOne(
				NAMESPACE + QUERY_ACCOUNTBANK_NAME, name);
	}

	@Override
	public AccountBranch getAccountBranchByName(String name) {
		return (AccountBranch) this.getSqlSession().selectOne(
				NAMESPACE + QUERY_ACCOUNTBRANCH_NAME, name);
	}

}
