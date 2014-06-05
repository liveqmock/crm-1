/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerCallInValidateUtilsTest.java
 * @package com.deppon.crm.module.marketing.utilstest 
 * @author ZhuPJ
 * @version 0.1 2012-12-5
 */
package com.deppon.crm.module.marketing.utilstest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.deppon.crm.module.marketing.server.utils.CustomerCallInValidateUtils;
import com.deppon.crm.module.marketing.shared.domain.CustomerCallVO;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerCallInValidateUtilsTest.java
 * @package com.deppon.crm.module.marketing.utilstest 
 * @author ZhuPJ
 * @version 0.1 2012-12-5
 */

public class CustomerCallInValidateUtilsTest {
	@Test
	public void testValidateCustIdAndType() {
		CustomerCallVO vo = new CustomerCallVO();
		try {
			// 客户ID为空
			CustomerCallInValidateUtils.validateCustIdAndType(vo);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// 客户ID为不为空，类型为空
			vo.setId("1");
			CustomerCallInValidateUtils.validateCustIdAndType(vo);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// 客户ID，类型为不为空
			vo.setId("1");
			vo.setContactType("2");
			CustomerCallInValidateUtils.validateCustIdAndType(vo);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testValidateCustOptions() {
		// 客户意见是否有数据、 验证客户需求中的信息是否完整
		try {
			// list为空
			CustomerCallInValidateUtils.validateCustOptions(null);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			List<ReturnVisitOpinion> optionList = new ArrayList<ReturnVisitOpinion>();
			// list不为空，size=0
			CustomerCallInValidateUtils.validateCustOptions(optionList);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		ReturnVisitOpinion rvo = new ReturnVisitOpinion();
		try {
			List<ReturnVisitOpinion> optionList = new ArrayList<ReturnVisitOpinion>();
			rvo.setOpinionType("1");
			rvo.setProblem("1");
			rvo.setSolution("1");
			optionList.add(rvo);
			
			CustomerCallInValidateUtils.validateCustOptions(optionList);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			List<ReturnVisitOpinion> optionList = new ArrayList<ReturnVisitOpinion>();
			rvo = new ReturnVisitOpinion();
			rvo.setOpinionType("1");
			rvo.setProblem(null);
			rvo.setSolution(null);
			optionList.add(rvo);

			CustomerCallInValidateUtils.validateCustOptions(optionList);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			List<ReturnVisitOpinion> optionList = new ArrayList<ReturnVisitOpinion>();
			rvo = new ReturnVisitOpinion();
			rvo.setOpinionType("1");
			rvo.setProblem("1");
			rvo.setSolution(null);
			optionList.add(rvo);

			CustomerCallInValidateUtils.validateCustOptions(optionList);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			List<ReturnVisitOpinion> optionList = new ArrayList<ReturnVisitOpinion>();
			rvo = new ReturnVisitOpinion();
			rvo.setOpinionType(null);
			rvo.setProblem("1");
			rvo.setSolution("1");
			optionList.add(rvo);

			CustomerCallInValidateUtils.validateCustOptions(optionList);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			List<ReturnVisitOpinion> optionList = new ArrayList<ReturnVisitOpinion>();
			rvo = new ReturnVisitOpinion();
			rvo.setOpinionType(null);
			rvo.setProblem("1");
			rvo.setSolution("1");
			optionList.add(rvo);

			CustomerCallInValidateUtils.validateCustOptions(optionList);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			List<ReturnVisitOpinion> optionList = new ArrayList<ReturnVisitOpinion>();
			rvo = new ReturnVisitOpinion();
			rvo.setOpinionType(null);
			rvo.setProblem("1");
			rvo.setSolution(null);
			optionList.add(rvo);

			rvo = new ReturnVisitOpinion();
			rvo.setOpinionType(null);
			rvo.setProblem(null);
			rvo.setSolution("1");
			optionList.add(rvo);
			CustomerCallInValidateUtils.validateCustOptions(optionList);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			List<ReturnVisitOpinion> optionList = new ArrayList<ReturnVisitOpinion>();
			rvo = new ReturnVisitOpinion();
			rvo.setOpinionType(null);
			rvo.setProblem(null);
			rvo.setSolution("1");
			optionList.add(rvo);
			CustomerCallInValidateUtils.validateCustOptions(optionList);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testValidateVo() {
		CustomerCallVO vo = new CustomerCallVO();
		// 联系人姓名不能为空
		// 联系人手机、联系人电话至少录入一个

		try {
			// 客户信息不能为空
			CustomerCallInValidateUtils.validateVo(null);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			vo.setCustLinkManName("1");
			CustomerCallInValidateUtils.validateVo(vo);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			vo.setCustLinkManName(null);
			CustomerCallInValidateUtils.validateVo(vo);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// 联系人手机、联系人电话至少录入一个
			vo.setContactMobile("1");
			vo.setContactPhone("1");
			CustomerCallInValidateUtils.validateVo(vo);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// 联系人手机、联系人电话至少录入一个
			vo.setContactMobile(null);
			vo.setContactPhone("1");
			CustomerCallInValidateUtils.validateVo(vo);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// 联系人手机、联系人电话至少录入一个
			vo.setContactMobile("1");
			vo.setContactPhone(null);
			CustomerCallInValidateUtils.validateVo(vo);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		
	}
}
