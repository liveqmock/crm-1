package com.deppon.crm.module.customer.server.manager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.common.file.util.ExcelReader;
import com.deppon.crm.module.common.file.util.IExcelReader;
import com.deppon.crm.module.customer.shared.domain.Certification;

public class MemberEffectValidateTest extends TestCase{
	private static MemberEffectValidate memberEffectValidate=new MemberEffectValidate();

	 @Before
	 public void setUp(){
	 }

	@Test
	public void testValidate() throws IOException {
		
		String idNum ="15645645";
		String cert = "TAIBAO_CARD";
		boolean result;
		if ("a".equals("a")) {
			result = true;
		} else {
			result = false;
		}

		List<String> taibaoListTrue = new ArrayList<String>();
		taibaoListTrue.add("15645645");
		taibaoListTrue.add("1545664564（A）");
		taibaoListTrue.add("0554554556(b)");
		taibaoListTrue.add("04564655");
		taibaoListTrue.add("00000000");
		taibaoListTrue.add("0000000000(B)");
		taibaoListTrue.add("9999999999(z)");
		
		List<String> taibaoListFalse = new ArrayList<String>();
		taibaoListFalse.add("2222222");
		taibaoListFalse.add("555555555");
		taibaoListFalse.add("2222222222");
		taibaoListFalse.add("22222222222");
		taibaoListFalse.add("999999999999");
		taibaoListFalse.add("1111111111111");
		taibaoListFalse.add("15456364564（A）");
		taibaoListFalse.add("055455456(b)");
		taibaoListFalse.add("1545664564（2）");
		taibaoListFalse.add("0554554556(3)");
		taibaoListFalse.add("1545664564（A2");
		taibaoListFalse.add("0554554556b（）");
		taibaoListFalse.add("0554554557b12");
		taibaoListFalse.add("055455455622b");
		taibaoListFalse.add("j545435264（A）");
		taibaoListFalse.add("1jjjjjjjjjj(b)");
		taibaoListFalse.add(",,,,,,,,,,,,,");
		
		for (String idNum9 : taibaoListFalse) {
			testValidateTwIdcard(idNum9.trim(), false);
		}
		for (String idNum10 : taibaoListTrue) {
			testValidateTwIdcard(idNum10.trim(), true);
		}
		
		List<String> backHomeCardTrue= new ArrayList<String>();
		backHomeCardTrue.add("H4564654560");
		backHomeCardTrue.add("M0574894664");
		backHomeCardTrue.add("H0000000000");
		backHomeCardTrue.add("H9999999999");
		backHomeCardTrue.add("M0000000000");
		backHomeCardTrue.add("M9999999999");
		
		List<String> backHomeCardFalse= new ArrayList<String>();
		backHomeCardFalse.add("h0000000000");
		backHomeCardFalse.add("m9999999999");
		backHomeCardFalse.add("HM000000032");
		backHomeCardFalse.add("M054646664M");
		backHomeCardFalse.add("H1221k15212");
		backHomeCardFalse.add("A0000000000");
		backHomeCardFalse.add("B0000000001");
		backHomeCardFalse.add("d0000000001");
		backHomeCardFalse.add("v0000000002");
		backHomeCardFalse.add("8979779");
		backHomeCardFalse.add("H456546666");
		backHomeCardFalse.add("M46665664666");
		backHomeCardFalse.add("HHHHHHHHHHH");
		backHomeCardFalse.add("hhhhhhhhhhh");
		backHomeCardFalse.add("mmmmmmmm");
		backHomeCardFalse.add("44646646666");
		backHomeCardFalse.add(",,,,,,,,,,");
		
		for (String idNum8 : backHomeCardFalse) {
			testValidateBackHomeIdcard(idNum8.trim(), false);
		}
		for (String idNum7 : backHomeCardTrue) {
			testValidateBackHomeIdcard(idNum7.trim(), true);
		}
		List<String> hongkongTrue = new ArrayList<String>();
		hongkongTrue.add("R527331(4)");
		hongkongTrue.add("R167316(4)");
		hongkongTrue.add("P103265(1)");
		hongkongTrue.add("D645450(0)");
		hongkongTrue.add("D645451(9)");
		hongkongTrue.add("D645452(7)");
		hongkongTrue.add("D645453(5)");
		hongkongTrue.add("D645454(3)");
		hongkongTrue.add("D645455(1)");
		hongkongTrue.add("D645456(A)");
		hongkongTrue.add("D645457(8)");
		hongkongTrue.add("D645458(6)");
		hongkongTrue.add("D645459(4)");
		
		List<String> hongkongFalse = new ArrayList<String>();
		hongkongFalse.add("P103265(2)");
		hongkongFalse.add("D645450(2)");
		hongkongFalse.add("D645451(A)");
		hongkongFalse.add("0000000000");
		hongkongFalse.add("11111111111");
		hongkongFalse.add("P103265(1)1");
		hongkongFalse.add("F103265(1)");
		hongkongFalse.add("JJJJJJJJJJ");
		hongkongFalse.add(",,,,,,,,,,");
		
		for (String idNum6 : hongkongFalse) {
			testValidateHKidcard(idNum6.trim(), false);
		}
		for (String idNum5 : hongkongTrue) {
			testValidateHKidcard(idNum5.trim(), true);
		}
		
		List<String> officerCardTrue = new ArrayList<String>();
		officerCardTrue.add("南08120013");
		officerCardTrue.add("北08120014");
		officerCardTrue.add("沈08120015");
		officerCardTrue.add("成08120017");
		officerCardTrue.add("兰08120016");

		List<String> officerCardFalse = new ArrayList<String>();
		officerCardFalse.add("南081200133");
		officerCardFalse.add("08120014北");
		officerCardFalse.add("8沈120015");
		officerCardFalse.add("33344444");
		officerCardFalse.add("3454345434");

		for (String idNum4 : officerCardFalse) {
			testValidateOfficer(idNum4.trim(), false);
		}
		for (String idNum3 : officerCardTrue) {
			testValidateOfficer(idNum3.trim(), true);
		}

		List<String> taxTrue = new ArrayList<String>();
		taxTrue.add("44190055913793X");
		taxTrue.add("441900696452012");
		taxTrue.add("441900618116910");
		taxTrue.add("441900791161512");
		taxTrue.add("441900770195641");
		taxTrue.add("441900678813742");
		taxTrue.add("441900553694612");
		taxTrue.add("441900717866100");
		taxTrue.add("441900736152744");
		taxTrue.add("441900617762320");
	
		List<String> taxFalse = new ArrayList<String>();
		taxFalse.add("sdfsdfdf");
		taxFalse.add("23432443");
		taxFalse.add("6555665567");
		taxFalse.add("erge4345");
		taxFalse.add("..kkjkk");
		taxFalse.add("'lluiuikgj");
		taxFalse.add("=-*&W@$");
		for (String idNum2 : taxFalse) {
			testValidateTaxcard(idNum2.trim(), false);
		}
		for (String idNum1 : taxTrue) {
			testValidateTaxcard(idNum1.trim(), true);
		}
		if (cert.trim().equals("SECOND_GENERATION_IDCARD")) {
			testValidateSecondGenerateIdcard(idNum.trim(), result);
		} else if (cert.trim().equals("OFFICER_IDCARD")) {
			testValidateOfficer(idNum.trim(), result);
		} else if (cert.trim().equals("HONGKONG_IDCARD")) {
			testValidateHKidcard(idNum.trim(), result);
		} else if (cert.trim().equals("BACKHOME_CARD")) {
			testValidateBackHomeIdcard(idNum.trim(), result);
		} else if (cert.trim().equals("TAIBAO_CARD")) {
			testValidateTwIdcard(idNum.trim(), result);
		} else if (cert.trim().equals("TAX_CARD")) {
			testValidateTaxcard(idNum.trim(), result);
		}

	
	}

	 public void assertValidate(String idNum,Certification cert,boolean
	 result){
	Assert.assertEquals(idNum+"check error",MemberEffectValidate.validate(idNum,cert),
	 result);
	 }

	 @Test
	 public void testValidateTaxNumber(){
	 String string = null;
	 memberEffectValidate.validateTaxNumber(string);
	 }
	 @Test
	 public void testValidateFirstGenerateIdcard(){
	 String string = null;
	 try {
		 memberEffectValidate.validateFirstGenerateIdcard(string);
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	 }
	// 测试第二代身份证
	public void testValidateSecondGenerateIdcard(String idNum, boolean result) {
		Assert.assertEquals(idNum + "check error",
				MemberEffectValidate.validateSecondGenerateIdcard(idNum),
				result);
	}

	// 测试军官证
	public void testValidateOfficer(String idNum, boolean result) {
		Assert.assertEquals(idNum + "check error",
				MemberEffectValidate.validateOfficer(idNum), result);
	}

	// @Test
	// 测试香港身份证
	public void testValidateHKidcard(String idNum, boolean result) {
		Assert.assertEquals(idNum + "check error",
				MemberEffectValidate.validateHKidcard(idNum), result);
	}

	 @Test
	 public void testValidateSeamancard(){
	 String string = null;
	 memberEffectValidate.validateSeamancard(string);
	 }
	 @Test
	// 测试回乡证
	public void testValidateBackHomeIdcard(String idNum, boolean result) {
		Assert.assertEquals(idNum + "check error",
				MemberEffectValidate.validateBackHomeIdcard(idNum), result);
	}

	// @Test
	// 测试台胞证
	public void testValidateTwIdcard(String idNum, boolean result) {
		Assert.assertEquals(idNum + "check error",
				MemberEffectValidate.validateTwIdcard(idNum), result);
	}

	// 测试税务登记证
	public void testValidateTaxcard(String idNum, boolean result) {
		Assert.assertEquals(idNum + "check error",
				MemberEffectValidate.validateTaxNumber(idNum), result);
	}

   
}
