package com.deppon.crm.module.customer.server.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.deppon.crm.module.customer.server.manager.MemberEffectValidate;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Certification;

/**
 * 
 * <p>
 * 统计会员数据<br />
 * </p>
 * @title CountMemberInfo.java
 * @package com.deppon.crm.module.customer.server.util 
 * @author bxj
 * @version 0.2 2012-8-30
 */
public class CountMemberInfo {
    
	private static long sumAll = 0;
	private static long countNum = 0;
	private static long errorNum = 0;
	private static long rightNum = 0;
	private static PrintWriter errorW = null;
	private static PrintWriter rightW = null;
	private static BufferedReader br =null;
	
	
	public static void main(String[] args) throws IOException {
		errorW = new PrintWriter("D://linkNumbererror.txt");
		rightW = new PrintWriter("D://linkNumberright.txt");
		File file = new File("D://linkNumber.txt");
		br = new BufferedReader(new FileReader(file));
		custNumber();
		System.out.println("一共"+sumAll+"条数据,"+countNum+"条数据有证件,其中正确条数为"+rightNum+"错误条数为"+errorNum);
		
	}
	
	private static void linkNumber() throws IOException {
		String context = "";
		while((context= br.readLine())!= null){
			sumAll++;
			if(sumAll%1000 == 0){
				System.out.println("已经处理了"+sumAll+"条数据了，不要急良心等待");
			}
			String[] link = context.split(",");
			if(link.length != 4) continue;
			String linkNumber = link[1];
			String idNum = link[2];
			String idType = link[3];
			if(ValidateUtil.objectIsEmpty(idNum) || ValidateUtil.objectIsEmpty(idType)){
				
			}else{
				idNumCheck(linkNumber,idNum,idType);
			}
		}
	} 

	private static void custNumber() throws IOException {
		String context = "";
		while((context= br.readLine())!= null){
			sumAll++;
			if(sumAll%1000 == 0){
				System.out.println("已经处理了"+sumAll+"条数据了，不要急良心等待");
			}
			String[] custTaxs = context.split(",");
			if(custTaxs.length != 3) continue;
			String custNumber = custTaxs[1];
			String idNum = custTaxs[2];
			String idType = "TAX_CARD";
			if(ValidateUtil.objectIsEmpty(idNum) || ValidateUtil.objectIsEmpty(idType)){
				
			}else{
				idNumCheck(custNumber,idNum,idType);
			}
		}
	}
	
	public static void idNumCheck(String number,String idNum,String idType){
		try {  
			countNum++;
			MemberEffectValidate.validate(idNum,Certification.getCertification(idType));
			rightNum++;
			rightW.println(number);
		} catch (Exception e) {
			errorNum++;
			errorW.println(number);
	    }
	}
	
}
