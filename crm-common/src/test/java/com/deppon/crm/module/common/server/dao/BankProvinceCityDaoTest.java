package com.deppon.crm.module.common.server.dao;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.BankCity;
import com.deppon.crm.module.common.shared.domain.BankProvince;

/**
 * 
 * @作者: 罗典
 * @时间：2012-12-24下午3:17:31
 * @描述：银行省份城市Test
 */
public class BankProvinceCityDaoTest {
	private IBankProvinceCityDao bankProvinceCityDao;

	@Before
	public void init() {
		try {
			ApplicationContext ctx = null;
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext("DaoBeanTest.xml");
			}
			bankProvinceCityDao = (IBankProvinceCityDao) ctx
					.getBean("bankProvinceCityDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetBankCityByCode() {
		BankCity city = bankProvinceCityDao.getBankCityByCode("410200");
	}

	@Test
	public void testGetBankProvinceCode() {
		BankProvince province = bankProvinceCityDao
				.getBankProvinceByCode("440000");
	}

	 @Test 
	public void testInsertBankProvince() {
		BankProvince bankProvince = new BankProvince();
		bankProvince.setId("111");
		bankProvince.setCode("code");
		bankProvince.setName("name");
		bankProvince.setCancel("1");
		bankProvince.setCreateTime(new Date());
		bankProvince.setCreateUserId("12");
		bankProvince.setLastUpdateTime(new Date());
		bankProvince.setLastModifyUserId("12");
		boolean isSuccess = bankProvinceCityDao
				.insertBankProvince(bankProvince);
		Assert.assertTrue(isSuccess);
	}

	// @Test
	public void testInsertBankCity() {
		BankCity bankCity = new BankCity();
		bankCity.setId("id");
		bankCity.setCode("code");
		bankCity.setName("name");
		bankCity.setProvinceId("111");
		bankCity.setCancel("1");
		bankCity.setCreateTime(new Date());
		bankCity.setCreateUserId("11");
		bankCity.setLastUpdateTime(new Date());
		bankCity.setLastModifyUserId("111");
		boolean isSuccess = bankProvinceCityDao.insertBankCity(bankCity);
		Assert.assertTrue(isSuccess);

	}

	@Test
	public void testUPdateBankProvince() {
		BankProvince bankProvince = new BankProvince();
		bankProvince.setId("111");
		bankProvince.setCode("code");
		bankProvince.setName("name");
		bankProvince.setCancel("1");
		bankProvince.setCreateTime(new Date());
		bankProvince.setCreateUserId("12");
		bankProvince.setLastUpdateTime(new Date());
		bankProvince.setLastModifyUserId("12");
		bankProvinceCityDao.updateBankProvince(bankProvince);
	}

	@Test
	public void testUpdateBankCity() {
		BankCity bankCity = new BankCity();
		bankCity.setId("id");
		bankCity.setCode("code");
		bankCity.setName("name");
		bankCity.setProvinceId("111");
		bankCity.setCancel("1");
		bankCity.setCreateTime(new Date());
		bankCity.setCreateUserId("11");
		bankCity.setLastUpdateTime(new Date());
		bankCity.setLastModifyUserId("111");
		bankProvinceCityDao.updateBankCity(bankCity);
	}

	@Test
	public void testGetAccountBankByCode() {
		AccountBank accountBank = bankProvinceCityDao
				.getAccountBankByCode("905");
	}

	@Test
	public void testGetAccountBranchByCode() {
		AccountBranch accountBranch = bankProvinceCityDao
				.getAccountBranchByCode("102100000056");
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行信息
	 * @参数：银行信息
	 * @返回：是否成功
	 */
//	@Test
	public void testInsertAccountBank() {
		AccountBank accountBank = new AccountBank();
		accountBank.setId("111");
		accountBank.setCreateTime(new Date());
		accountBank.setLastUpdateTime(new Date());
		accountBank.setLastModifyUserId(Integer.valueOf("1"));
		accountBank.setName("name");
		accountBank.setCode("code");
		accountBank.setCreateUserId(Integer.valueOf("1"));
		accountBank.setCancel(1);
		bankProvinceCityDao.insertAccountBank(accountBank);
	}

	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码修改银行信息
	 * @参数：银行编码
	 * @返回：是否成功
	 */
	@Test
	public void testUpdateAccountBank() {
		AccountBank accountBank = new AccountBank();
		accountBank.setId("400000008");
		accountBank.setCreateTime(new Date());
		accountBank.setLastUpdateTime(new Date());
		accountBank.setLastModifyUserId(Integer.valueOf("1"));
		accountBank.setName("name");
		accountBank.setCode("code");
		accountBank.setCreateUserId(Integer.valueOf("1"));
		accountBank.setCancel(1);
		boolean isSuccess = bankProvinceCityDao.updateAccountBank(accountBank);
		System.out.println(isSuccess);
	}


	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：保存银行支行信息
	 * @参数：银行支行信息
	 * @返回：是否成功
	 */
//	@Test
	public void testInsertAccountBranch() {
		AccountBranch accountBranch = new AccountBranch();
		accountBranch.setId("id");
		accountBranch.setCreateDate(new Date());
		accountBranch.setCreateUser("createUser");
		accountBranch.setModifyDate(new Date());
		accountBranch.setModifyUser("modifyUser");
		accountBranch.setfId(Integer.valueOf("1"));
		accountBranch.setCreateTime(new Date());
		accountBranch.setCreateUserId(Integer.valueOf("1"));
		accountBranch.setLastUpdateTime(new Date());
		accountBranch.setLastModifyUserId(Integer.valueOf("1"));
		accountBranch.setBankId("bankId");
		accountBranch.setCode("code");
		accountBranch.setName("name");
		accountBranch.setProvinceId("provinceId");
		accountBranch.setCityId("cityId");
		accountBranch.setCancel(1);
		bankProvinceCityDao.insertAccountBranch(accountBranch);
	}
	/**
	 * @作者: 罗典
	 * @时间：2012-12-24下午2:33:33
	 * @描述：根据编码修改银行支行信息
	 * @参数：银行支行编码
	 * @返回：是否成功
	 */
//	@Test
	public void testUpdateAccountBranch() {
		AccountBranch accountBranch = new AccountBranch();
		accountBranch.setId("400000010");
		accountBranch.setCreateDate(new Date());
		accountBranch.setCreateUser("createUser");
		accountBranch.setModifyDate(new Date());
		accountBranch.setModifyUser("modifyUser");
		accountBranch.setfId(Integer.valueOf("1"));
		accountBranch.setCreateTime(new Date());
		accountBranch.setCreateUserId(Integer.valueOf("1"));
		accountBranch.setLastUpdateTime(new Date());
		accountBranch.setLastModifyUserId(Integer.valueOf("1"));
		accountBranch.setBankId("bankId");
		accountBranch.setCode("code");
		accountBranch.setName("name");
		accountBranch.setProvinceId("provinceId");
		accountBranch.setCityId("cityId");
		accountBranch.setCancel(1);
		boolean isSuccess = bankProvinceCityDao.updateAccountBranch(accountBranch);
		System.out.println(isSuccess);
	}
}
