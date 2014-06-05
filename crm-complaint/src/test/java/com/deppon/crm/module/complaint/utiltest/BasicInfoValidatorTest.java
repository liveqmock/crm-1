package com.deppon.crm.module.complaint.utiltest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.deppon.crm.module.complaint.server.util.BasicInfoConstants;
import com.deppon.crm.module.complaint.server.util.BasicInfoValidator;
import com.deppon.crm.module.complaint.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.complaint.shared.domain.BasicBusType;
import com.deppon.crm.module.complaint.shared.domain.BasicInfo;

public class BasicInfoValidatorTest {
	@Test
	public void testCheckBusItem(){
		BasicInfo basicItem = new BasicInfo();
		basicItem.setBusItemId("1234");
		try {
			BasicInfoValidator.checkBusItem(basicItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		basicItem.setBusItemName("aaaa");
		try {
			BasicInfoValidator.checkBusItem(basicItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		basicItem.setReportType("");
		try {
			BasicInfoValidator.checkBusItem(basicItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		basicItem.setReportType("aaa");
		try {
			BasicInfoValidator.checkBusItem(basicItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		basicItem.setReportType(BasicInfoConstants.BASICCOMPTYPECOMPAINT);
		try {
			BasicInfoValidator.checkBusItem(basicItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		basicItem.setReportType(BasicInfoConstants.BASICCOMPTYPEUNUSUAL);
		try {
			BasicInfoValidator.checkBusItem(basicItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCheckBusItemForUpdate(){
		BasicInfo bi = new BasicInfo();
		try {
			BasicInfoValidator.checkBusItemForUpdate(bi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bi.setBusItemId("");
		try {
			BasicInfoValidator.checkBusItemForUpdate(bi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bi.setBusItemId("1234");
		try {
			BasicInfoValidator.checkBusItemForUpdate(bi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bi.setBusItemName("aaa");
		try {
			BasicInfoValidator.checkBusItemForUpdate(bi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bi.setReportType("");
		try {
			BasicInfoValidator.checkBusItemForUpdate(bi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bi.setReportType(BasicInfoConstants.BASICCOMPTYPECOMPAINT);
		try {
			BasicInfoValidator.checkBusItemForUpdate(bi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCheckBasicBusScopeVOBySearch(){
		BasicBusScopeVO bv = new BasicBusScopeVO();
		try {
			BasicInfoValidator.checkBasicBusScopeVOBySearch(bv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bv.setBusItemId("123");
		try {
			BasicInfoValidator.checkBasicBusScopeVOBySearch(bv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCheckSaveBasicItem(){
		List<BasicInfo> basicItems = new ArrayList<BasicInfo>();
		try {
			BasicInfoValidator.checkSaveBasicItem(basicItems);
		} catch (Exception e) {
			e.printStackTrace();
		}
		BasicInfo basicInfo = new BasicInfo();
		basicItems.add(basicInfo);
		try {
			BasicInfoValidator.checkSaveBasicItem(basicItems);
		} catch (Exception e) {
			e.printStackTrace();
		}
		basicInfo.setBusItemName("aaaa");
		try {
			BasicInfoValidator.checkSaveBasicItem(basicItems);
		} catch (Exception e) {
			e.printStackTrace();
		}
		basicInfo.setReportType("aa");
		try {
			BasicInfoValidator.checkSaveBasicItem(basicItems);
		} catch (Exception e) {
			e.printStackTrace();
		}
		basicInfo.setReportType(BasicInfoConstants.BASICCOMPTYPECOMPAINT);
		try {
			BasicInfoValidator.checkSaveBasicItem(basicItems);
		} catch (Exception e) {
			e.printStackTrace();
		}
		basicInfo = null;
		basicItems.add(basicInfo);
		try {
			BasicInfoValidator.checkSaveBasicItem(basicItems);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCheckIdList(){
		List<String> ids = new ArrayList<String>();
		try {
			BasicInfoValidator.checkIdList(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ids.add("1234");
		try {
			BasicInfoValidator.checkIdList(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCheckBusTypeSave(){
		BasicBusType busType = null;
		try {
			BasicInfoValidator.checkBusTypeSave(busType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		busType = new BasicBusType();
		try {
			BasicInfoValidator.checkBusTypeSave(busType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		busType.setBusType("aa");
		try {
			BasicInfoValidator.checkBusTypeSave(busType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		busType.setDealLanguage("adf");
		try {
			BasicInfoValidator.checkBusTypeSave(busType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		busType.setFeedbackLimit("aaa");
		try {
			BasicInfoValidator.checkBusTypeSave(busType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		busType.setFeedbackLimit("123");
		try {
			BasicInfoValidator.checkBusTypeSave(busType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		busType.setProcLimit("adf");
		try {
			BasicInfoValidator.checkBusTypeSave(busType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		busType.setProcLimit("1234");
		try {
			BasicInfoValidator.checkBusTypeSave(busType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCheckBusScopeVOInsert(){
		BasicBusScopeVO bbv = new BasicBusScopeVO();
		try {
			BasicInfoValidator.checkBusScopeVOInsert(bbv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bbv.setBusItemId("1234");
		try {
			BasicInfoValidator.checkBusScopeVOInsert(bbv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bbv.setBusScopeName("aaaa");
		try {
			BasicInfoValidator.checkBusScopeVOInsert(bbv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<BasicBusType> busTypes = new ArrayList<BasicBusType>();
		bbv.setBusTypes(busTypes);
		try {
			BasicInfoValidator.checkBusScopeVOInsert(bbv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		BasicBusType bt = new BasicBusType();
		busTypes.add(bt);
		try {
			BasicInfoValidator.checkBusScopeVOInsert(bbv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bt.setBusType("adf");
		try {
			BasicInfoValidator.checkBusScopeVOInsert(bbv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bt.setFeedbackLimit("-11100");
		try {
			BasicInfoValidator.checkBusScopeVOInsert(bbv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
