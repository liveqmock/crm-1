/*package com.deppon.oa;

import java.text.ParseException;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.oa.error.ArrayOfString;
import com.deppon.oa.error.ErrorService;
import com.deppon.oa.error.ErrorServiceService;

public class ErrorServiceTest {

	ErrorService errorService;

	@Before
	public void init() {
		errorService = new ErrorServiceService().getErrorServicePort();
	}

	@Test
	public void queryErrorsByWaybillTest() {
		ArrayOfString errors = errorService.returnErrosInfor("12345678");

		List<String> strs = errors.getString();
		for (String string : strs) {
			System.out.println("--------------------");
			String[] st = string.split("!@#");
			for (String string2 : st) {
				System.out.println(string2);
			}
		}
	}

	@Test
	public void getErrorInfosTest() throws ParseException {

		ArrayOfString str = errorService.getErrorInfo("201112956106");
		List<String> strs = str.getString();
		System.out.println(strs);
		for (String rs : strs) {
			System.out.println("---------------------------");
			String[] st = rs.split("!@#");
			for (String string : st) {
				System.out.println(string);
			}
			// break;
			// OaAccidentInfo info = new OaAccidentInfo();
			// for (String string : st) {
			// // System.out.println(string);
			// }
			// info.setAccidentCode(st[0]);
			// info.setAccidentName(st[1]);
			// info.setProcessResult(st[2]);
			// info.setProcessTarget(st[3]);
			// info.setRewardPunishmentDescription(st[4]);
			// info.setMoney(Double.parseDouble(st[5]));
			// System.out.println(st[6]);
			// SimpleDateFormat format = new
			// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// // info.setProcessDate(format.parse(st[6]));
			// info.setProcessPerson(st[7]);
			// // info.setAccidentType()
			// System.out.println(st[8]);
		}

	}

	@Test
	public void test() throws Exception {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress("http://192.168.19.214:8080/eos-default/ErrorService");
		factory.setServiceClass(ErrorService.class);
		ErrorService service = (ErrorService) factory.create();
		ArrayOfString str = service.getErrorInfo("201112956106");

		List<String> strs = str.getString();
		System.out.println(strs);
		for (String rs : strs) {
			System.out.println("---------------------------");
			String[] st = rs.split("!@#");
			for (String string : st) {
				System.out.println(string);
			}
		}
	}
}
*/