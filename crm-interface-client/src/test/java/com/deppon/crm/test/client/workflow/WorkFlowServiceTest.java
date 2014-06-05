/*package com.deppon.crm.test.client.workflow;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.test.client.common.OperaterTest;
import com.deppon.oa.workflow.CreateWorkFlowWorkFlowException;
import com.deppon.util.EntityCreateUtil;

public class WorkFlowServiceTest extends OperaterTest {

	
	
	*//**
	 * 常规理赔申请,0007:1
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-27
	 *
	 *//*
	@Test
	public void createNormalRecompenseTest(){
		String json;
		try {
			json = objectMapper.writeValueAsString(EntityCreateUtil.createNormalRecompenseInfo());
			System.out.println(json);
			String workNumber = workFlowService.createWorkFlow(json, Constant.RECOMPENSE_NORMAL);
			
			System.out.println(workNumber);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CreateWorkFlowWorkFlowException e) {
			e.printStackTrace();
		}
	}
	
	*//**
	 * 多赔申请测试
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-27
	 *
	 *//*
	@Test
	public void createMuchRecompenseTest(){
		try {
			String json = objectMapper.writeValueAsString(EntityCreateUtil.createMuchRecompense());
			String workFlowNumber = workFlowService.createWorkFlow(json, Constant.RECOMPENSE_MUCH);
			System.out.println(workFlowNumber);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CreateWorkFlowWorkFlowException e) {
			e.printStackTrace();
		}
	}
	
	*//**
	 * 合同新增及合同修改测试:0011:3
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-27
	 *
	 *//*
	@Test
	public void createUpdateContractTest(){
		String json;//="{"bizInfo":"{\"address\":\"5555568888\",\"file\":[{\"fileName\":\"CRM系统冒烟测试用例.xlsx\",\"savePath\":\"/opt/fileUpload/2012-05-31/2012-05-31_b53b2196-8ca3-483a-acc5-bca120e8df1c.xlsx\",\"contractId\":\"40000001\",\"contractName\":\"CRM系统冒烟测试用例.xlsx\"}],\"contactName\":\"电话\",\"customerName\":\"发给\",\"contactPhone\":\"13956484512\",\"contactTel\":\"8320631\",\"city\":null,\"contractNum\":\"999852525\",\"preferentialType\":\"\",\"goodsName\":\"dsadas\",\"department\":\"DP07564\",\"industry\":\"CLOTH_SPIN\",\"applyReason\":\"dsdasdas\",\"applyType\":\"addOrUpdate\",\"applyPersonCode\":\"070211\",\"applyPersonName\":\"王晓芳\",\"divisionCode\":\"DP00204\",\"applyPersonDept\":\"惠州惠城区江北营业部\",\"customerCode\":\"40000000\",\"customerGrade\":\"NORMAL\",\"customerType\":\"ENTERPRISE\",\"taxRegistrationNum\":\"23454545\",\"idnumber\":\"139564845121395648\",\"contractState\":\"\",\"oldcontractNum\":\"\",\"oldDeptCode\":\"DP01563\",\"contractBody\":null,\"customerAllName\":\"dsadas\",\"isPreferential\":false,\"nodeSectionType\":\"HALF_MONTH_END\",\"applyDebtsRating\":120000.0,\"registerMoney\":2121000.0,\"protocolContactName\":\"电话\",\"protocolContactTel\":\"13956484512\",\"protocolContactPhone\":\"8320631\",\"contractStartDate\":1339171200000,\"contractEndDate\":1340985600000,\"changes\":null,\"freightDiscount\":0.0,\"generationRateDiscount\":0.0,\"chargeRate\":0.0,\"cargoFee\":0.0,\"deliveryFee\":0.0,\"checkingDate\":15,\"invoiceDate\":7,\"settleAccountsDate\":10}","bizType":"WFS_HT_001"}";
		try {
			json = objectMapper.writeValueAsString(EntityCreateUtil.createContractInfo());
			String code = workFlowService.createWorkFlow(json, Constant.CONTRACT_NEW);
			System.out.println(code);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CreateWorkFlowWorkFlowException e) {
			e.printStackTrace();
		}
	}
	
	*//**
	 * 合同部门绑定，或者转续
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-27
	 *
	 *//*
	@Test
	public void bindOrConverterContractTest(){
		String json;
		try {
			json = objectMapper.writeValueAsString(EntityCreateUtil.createContractInfo());
			workFlowService.createWorkFlow(json, Constant.CONTRACT_ADD_BELONGDEPT);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CreateWorkFlowWorkFlowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	*//**
	 * 取消合同
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-27
	 *
	 *//*
	public void cancelContractTest(){
		String json;
		try {
			json = objectMapper.writeValueAsString(EntityCreateUtil.createContractInfo());
			workFlowService.createWorkFlow(json, Constant.CONTRACT_CANCEL);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CreateWorkFlowWorkFlowException e) {
			e.printStackTrace();
		}
	}
	
	
}
*/