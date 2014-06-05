package com.deppon.crm.module.customer.server.manager;

import com.deppon.crm.module.customer.shared.domain.Certification;
import com.deppon.crm.module.customer.shared.exception.MemberEffectException;
import com.deppon.crm.module.customer.shared.exception.MemberEffectExceptionType;

/**
 * 
 * <p>
 * Description:验证证件号的有效性<br />
 * </p>
 * 
 * @title MemberEffectValidate.java
 * @package com.deppon.crm.module.customer.server.manager
 * @author 吴根斌
 * @version 0.1 2012-7-23
 */
public class MemberEffectValidate {

	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-7-20
	 * @描述：验证证件号的有效性
	 * @参数：type 客户类型，包含企业客户和个人客户；idNum 证件号；cert 证件类型
	 *          若type类型为“Enterprise”，curt可以为null
	 * @返回值：boolean 值为true，则验证通过
	 */
	public static boolean validate(String idNum, Certification cert) {
		switch (cert) {
		// 税务登记证
		case TAX_CARD:
			if (!validateTaxNumber(idNum)) {
				throw new MemberEffectException(
						MemberEffectExceptionType.TaxNumber_ERROR.getErrCode(),
						new Object[] {});
			}
			return true;
			// 第一代身份证
		case FIRST_GENERATION_IDCARD:
			if (!validateFirstGenerateIdcard(idNum)) {
				throw new MemberEffectException(
						MemberEffectExceptionType.FirstGenerateIdcard_ERROR
								.getErrCode(),
						new Object[] {});
			}
			return true;
			// 第二代身份证
		case SECOND_GENERATION_IDCARD:
			if (!validateSecondGenerateIdcard(idNum)) {
				throw new MemberEffectException(
						MemberEffectExceptionType.SecondGenerateIdcard_ERROR
								.getErrCode(),
						new Object[] {});
			}
			return true;
			// 军官证
		case OFFICER_IDCARD:
			if (!validateOfficer(idNum)) {
				throw new MemberEffectException(
						MemberEffectExceptionType.Officer_ERROR.getErrCode(),
						new Object[] {});
			}
			return true;
			// 香港居民身份证
		case HONGKONG_IDCARD:
			if (!validateHKidcard(idNum)) {
				throw new MemberEffectException(
						MemberEffectExceptionType.HKidcard_ERROR.getErrCode(),
						new Object[] {});
			}
			return true;
			// 海员证
		case SEAMAN_CARD:
			if (!validateSeamancard(idNum)) {
				throw new MemberEffectException(
						MemberEffectExceptionType.SEAMAN_ERROR.getErrCode(),
						new Object[] {});
			}
			return true;
			// 回乡证
		case BACKHOME_CARD:
			if (!validateBackHomeIdcard(idNum)) {
				throw new MemberEffectException(
						MemberEffectExceptionType.BACKHOME_ERROR.getErrCode(),
						new Object[] {});
			}
			return true;
			// 台胞证
		case TAIBAO_CARD:
			if (!validateTwIdcard(idNum)) {
				throw new MemberEffectException(
						MemberEffectExceptionType.TAIBAO_ERROR.getErrCode(),
						new Object[] {});
			}
			return true;
			// 其他证
		case OTHER:
			return true;
			default: ;
		}
		throw new MemberEffectException(
				MemberEffectExceptionType.No_IDCARD_Exist.getErrCode(),
				new Object[] {});
	}

	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-7-23
	 * @描述：验证税务登记证
	 * @参数：idNum 税务登记证号
	 * @返回值：boolean 值为true则表示验证通过
	 */
	public static boolean validateTaxNumber(String idNum) {
		//为空
		if (null == idNum) {
			return false;
		}
		//长度为15
		if (idNum.length() == 15) {
			//校验15长度的税务登记号
			return validateE15(idNum);
		}
		//长度为17
		if (idNum.length() == 17) {
			//校验17长度的税务登记号
			return validateFirstGenerateIdcard(idNum);
		}
		//长度为18
		if (idNum.length() == 18) {
			//校验18的税务登记号
			return validateSecondGenerateIdcard(idNum);
		}
		//长度为20
		if (idNum.length() == 20) {
			//校验20的税务登记号
			return validateE20(idNum);
		}
		return false;
	}

	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-7-23
	 * @描述：验证海员证的有效性
	 * @参数：idNum 海员证号
	 * @返回值：boolean 值为true则表示验证通过
	 */
	public static boolean validateSeamancard(String idNum) {

		if (null == idNum) {
			return false;
		}
		String[] prefix = { "BA", "CA", "DA", "EA", "EB", "FA", "FG", "GA",
				"HA", "(HE)", "JC", "JD", "KA", "KB", "KC", "LE", "MA", "PC" };
		boolean prefixMatching = false;
		String prefixString = "";
		for (int i = 0; i < prefix.length; i++) {
			if (idNum.indexOf(prefix[i]) != -1) {
				prefixString = prefix[i];
				prefixMatching = true;
				break;
			}
		}
		if (prefixMatching) {
			String temp = idNum.substring(prefixString.length());
			if (temp.matches("^[-]\\d{8}$")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-7-23
	 * @描述：验证回乡证的有效性
	 * @参数：idNum 回乡证号
	 * @返回值：boolean 值为true则表示验证通过
	 */
	public static boolean validateBackHomeIdcard(String idNum) {

		return idNum.matches("^[HM]\\d{10}$");
	}

	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-7-23
	 * @描述：验证台胞证的有效性
	 * @参数：idNum 台胞证号
	 * @返回值：boolean 值为true则表示验证通过
	 */
	public static boolean validateTwIdcard(String idNum) {

		if (null == idNum) {
			return false;
		}
		String range = "0123456789";
		int count = 0;
		for (int i = 0; i < idNum.length(); i++) {
			String temp = idNum.substring(i, i + 1);
			if (range.indexOf(temp) != -1) {
				count++;
			} else {
				break;
			}
		}
		if (8 == count) {
			return idNum.matches("^\\d{8}$");
		}
		if (10 == count) {
			return idNum.matches("^\\d{10}[(（][a-zA-Z][）)]$");
		}
		return false;
	}

	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-7-23
	 * @描述：验证香港居民身份证的有效性
	 * @参数：idNum 香港居民身份证号
	 * @返回值：boolean 值为true则表示验证通过
	 */
	public static boolean validateHKidcard(String idNum) {

		if (null == idNum) {
			return false;
		}
		boolean b = idNum.matches("^[A-Z]{1,2}\\d{6}[(][\\dA][)]$");
		if (!b) {
			return false;
		}
		// 把身份证的前1-2字母转换为数值，然后将值存入数组c中
		int c[];
		char[] idNumChar = idNum.toCharArray();
		if (idNumChar[1] >= 'A' && idNumChar[1] <= 'Z') {
			return true;
		} else {
			c = new int[7];
			c[0] = idNumChar[0] - 'A' + 1;
			String temp = idNum.substring(1, 7);
			for (int i = 0; i < temp.length(); i++) {
				c[1 + i] = Integer.parseInt(temp.substring(i, i + 1));
			}
		}
		int sum = 0;
		for (int i = 0; i < c.length; i++) {
			sum = sum + c[i] * (8 - i);
		}
		sum = sum % 11;
		sum = 11 - sum;
		String temp = String.valueOf(sum);
		if (sum == 11) {
			temp = "0";
		}
		if (sum == 10) {
			temp = "A";
		}
		int start = idNum.indexOf('(');
		int end = idNum.indexOf(')');
		String match = idNum.substring(start + 1, end);
		if (String.valueOf(temp).equals(match)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-7-20
	 * @描述：验证军官证号的有效性
	 * @参数：idNum 军官证号
	 * @返回值：boolean 值为true则表示验证通过
	 */
	public static boolean validateOfficer(String idNum) {

		if (null == idNum) {
			return false;
		}
		String[] prefix = new String[] { "南", "北", "沈", "兰", "成", "济", "广",
				"参", "证", "后", "装", "海", "陆", "空", "炮" };
		String front = idNum.substring(0, 1);
		String behind = idNum.substring(1);
		boolean frontMatching = false;
		for (int i = 0; i < prefix.length; i++) {
			if (front.equals(prefix[i])) {
				frontMatching = true;
				break;
			}
		}
		boolean behindMatching = false;
		StringBuffer temp = new StringBuffer();
		for(int i=0;i<behind.length();i++){
			String item=behind.substring(i,i+1);
			if(!"-".equals(item)){
				temp.append(item);
			}
		}
		if(temp.toString().matches("^\\d{6,8}$")){
			behindMatching=true;
		}
		if (frontMatching && behindMatching) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-7-20
	 * @描述：验证企业客户20位税务登记证号的有效性
	 * @参数：idNum 20位登记证号
	 * @返回值：boolean 值为true，则验证通过
	 */
	public static boolean validateE20(String idNum) {

		String first = idNum.substring(0, 18);
		boolean firstCheck = validateSecondGenerateIdcard(first);
		String second = idNum.substring(0, 15);
		boolean secondCheck = validateFirstGenerateIdcard(second);
		if (firstCheck || secondCheck) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-7-20
	 * @描述：验证第一代身份证
	 * @参数：idNum 17位登记证号
	 * @返回值：boolean 为true则表示验证通过
	 */
	public static boolean validateFirstGenerateIdcard(String idNum) {

		String month = idNum.substring(8, 10);
		String day = idNum.substring(10, 12);
		boolean monthMatching = month.matches("^0[1-9]|1[0-2]$");
		boolean dayMatching = day.matches("^0[1-9]|[1-2][0-9]|3[0-1]$");
		if (monthMatching && dayMatching) {
			return true;
		}
		return false;
	}

	/**
	 * @作者：吴根斌
	 * @时间：2012-7-20
	 * @描述：验证企业客户15位税务登记证号的有效性
	 * @参数：idNum 15位税务登记证号
	 * @返回值：boolean 为true则表示验证通过
	 */
	public static boolean validateE15(String idNum) {

		if (idNum.length() != 15) {
			return false;
		}
		// 保存7-14位的权值
		int[] w = new int[] { 3, 7, 9, 10, 5, 8, 4, 2 };
		String data = idNum.substring(6, 14);
		// 验证7-14位是否位整数
		boolean b = data.matches("^[0-9A-Z]{8}$");
		if (!b) {
			return false;
		}
		int[] c = new int[8];
		
		String digital="0123456789";
		String letter="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < data.length(); i++) {
			String num = data.substring(i, i + 1);
			int number;
			if(-1!=digital.indexOf(num)){
				number=digital.indexOf(num);
			}else{
				number=letter.indexOf(num)+10;
			}
			c[i] = number;
		}
		int sum = 0;
		for (int i = 0; i < 8; i++) {
			sum = sum + c[i] * w[i];
		}
		sum = sum % 11;
		sum = 11 - sum;
		String result = String.valueOf(sum);
		String check = idNum.substring(idNum.length() - 1);
		if ("10".equals(result)) {
			result = "X";
		}
		if ("11".equals(result)) {
			result = "0";
		}
		if (result.equals(check)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-7-20
	 * @描述：验证二代身份证号的有效性
	 * @参数：idNum 18位税务登记证号
	 * @返回值：boolean 为true则表示验证通过
	 */
	public static boolean validateSecondGenerateIdcard(String idNum) {
		// 初步匹配证号的正确性，即前十七位为数值，最后一位为0-9的数或者X
		boolean b = idNum.matches("^\\d{17}[\\dX]$");
		if (!b) {
			return false;
		}
		String year = idNum.substring(6, 10);
		String month = idNum.substring(10, 12);
		String day = idNum.substring(12, 14);
		boolean yearMatch = year.matches("^(19|20)\\d{2}$");
		boolean monthMatch = month.matches("^0[1-9]|1[0-2]$");
		boolean dayMatch = day.matches("^0[1-9]|[1-2][0-9]|3[0-1]$");
		if (!yearMatch) {
			return false;
		}
		if (!monthMatch) {
			return false;
		}
		if (!dayMatch) {
			return false;
		}
		int[] w = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4,
				2 };
		String check = idNum.substring(idNum.length() - 1);
		int[] a = new int[17];
		for (int i = 0; i < idNum.length() - 1; i++) {
			String num = idNum.substring(i, i + 1);
			int number = Integer.parseInt(num);
			a[i] = number;
		}
		int sum = 0;
		for (int i = 0; i < 17; i++) {
			sum = sum + w[i] * a[i];
		}
		sum = sum % 11;
		String result = "";
		switch (sum) {
		case 0:
			result = "1";
			break;
		case 1:
			result = "0";
			break;
		case 2:
			result = "X";
			break;
		case 3:
			result = "9";
			break;
		case 4:
			result = "8";
			break;
		case 5:
			result = "7";
			break;
		case 6:
			result = "6";
			break;
		case 7:
			result = "5";
			break;
		case 8:
			result = "4";
			break;
		case 9:
			result = "3";
			break;
		case 10:
			result = "2";
			break;
		default:
			result = "";
		}
		if (check.equals(result)) {
			return true;
		}
		return false;
	}
}
