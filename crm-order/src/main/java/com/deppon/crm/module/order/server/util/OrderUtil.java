/**   
 * @title OrderUtil.java
 * @package com.deppon.crm.module.order.server.util
 * @description 订单工具类  
 * @author 潘光均
 * @update 2012-4-17 下午3:40:08
 * @version V1.0   
 */
package com.deppon.crm.module.order.server.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.order.domain.AppointmentCarInfo;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager;
import com.deppon.crm.module.common.server.manager.impl.AreaAddressManager;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.crm.module.order.shared.domain.BookVehicleInfo;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.crm.module.order.shared.domain.VehicleHistory;
import com.deppon.crm.module.order.shared.exception.OrderException;
import com.deppon.crm.module.order.shared.exception.OrderExceptionType;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * @description 订单工具类
 * @author 潘光均
 * @version 0.1 2012-4-17
 * @date 2012-4-17
 * @updateDate 2012-7-4 anxh
 */
public class OrderUtil {
	
	@SuppressWarnings("serial")
	public static GeneralException buildException(OrderExceptionType type){
		OrderException e = new OrderException(type);
		return new GeneralException(e.getErrorCode(),
				e.getMessage(), e, new Object[] {}) {
		};
	}

	@SuppressWarnings("serial")
	public static GeneralException buildException(CrmBusinessException e) {
		return new GeneralException(e.getErrorCode(),
				e.getMessage(), e, new Object[] {}) {
		};
	}

	/**
	 * 
	 * <p>
	 * Description:根据网点ID获得其名称<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:49:01
	 * @param stationId
	 * @return String
	 * @update 2013-1-28上午9:49:01
	 */
	public static String getStationNameById(String stationId,
			ILadingstationDepartmentManager ladingstationDepartmentManager) {
		if (stationId != null && !"".equals(stationId)) {
			BussinessDept bizDept = ladingstationDepartmentManager
					.getBusDeptById(stationId);
			if (bizDept != null) {
				return bizDept.getDeptName();
			}
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:根据省ID查询省名称<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:49:11
	 * @param provinceId
	 * @return String
	 * @update 2013-1-28上午9:49:11
	 */
	public static String getProvinceNameById(String provinceId,
			AreaAddressManager areaAddressManager) {
		if (provinceId != null && !"".equals(provinceId)) {
			List<Province> list = areaAddressManager
					.searchProvinceById((String) provinceId);
			if (list != null && list.size() > 0) {
				return list.get(0).getName();
			}
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:根据市ID查询市名称<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:49:20
	 * @param cityId
	 * @return String
	 * @update 2013-1-28上午9:49:20
	 */
	public static String getCityNameById(String cityId,
			AreaAddressManager areaAddressManager) {
		if (cityId != null && !"".equals(cityId)) {
			List<City> list = areaAddressManager.searchCityById(cityId);
			if (list != null && list.size() > 0) {
				return list.get(0).getName();
			}
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:根据区ID查询区名称<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:49:28
	 * @param areaId
	 * @return String
	 * @update 2013-1-28上午9:49:28
	 */
	public static String getAreaNameById(String areaId,
			AreaAddressManager areaAddressManager) {
		if (areaId != null && !"".equals(areaId)) {
			List<Area> list = areaAddressManager.searchAreaById(areaId);
			if (list != null && list.size() > 0) {
				return list.get(0).getName();
			}
		}
		return null;
	}

	/**
	 * 
	 * @description 获得当前登录员工的名称.
	 * @author 安小虎
	 * @version 0.1
	 * @date 2012-5-18
	 * @return 员工名称
	 * @update 2012-5-18 下午3:26:25
	 */
	public static String getLoginEmpNoName() {
		User user = (User) UserContext.getCurrentUser();
		if (user != null) {
			Employee emp = user.getEmpCode();
			if (emp != null)
				return emp.getEmpCode() + "(" + emp.getEmpName() + ")";
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:获得登录的员工ID<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22上午11:28:35
	 * @return String
	 * @update 2013-1-22上午11:28:35
	 */
	public static String getLoginEmpId() {
		User user = (User) UserContext.getCurrentUser();
		if (user != null) {
			Employee emp = user.getEmpCode();
			if (emp != null)
				return emp.getId();
		}
		return null;
	}

	/**
	 * 
	 * @description 根据订单信息产生操作日志.
	 * @author 潘光均
	 * @version 0.1 2012-4-17
	 * @param Order
	 * @param String
	 * @param String
	 * @param String
	 * @date 2012-4-17
	 * @return OrderOperationLog
	 * @update 2012-4-17 下午3:40:52
	 */
	@SuppressWarnings("serial")
	public static OrderOperationLog generateOrderOperationLog(Order order,
			String operationType, String content, User user) {
		OrderOperationLog orderOperationLog = new OrderOperationLog();
		orderOperationLog.setOrderId(order.getId());
		orderOperationLog.setOperatorContent(content);
		orderOperationLog.setOperatorTime(new Date());
		if (null == user) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_USER_NOTLOGIN);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 构造操作人信息
		StringBuffer userInfo = new StringBuffer();
		if (user.getEmpCode() == null || user.getEmpCode().getEmpName() == null
				|| user.getEmpCode().getEmpCode() == null) {
			OrderException e = new OrderException(
					OrderExceptionType.USER_INFORMATION_INCOMPLETE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		orderOperationLog.setOperatorOrgId(user.getEmpCode().getDeptId()
				.getDeptName());
		userInfo.append(user.getEmpCode().getEmpName());
		userInfo.append("(");
		userInfo.append(user.getEmpCode().getEmpCode());
		userInfo.append(")");
		orderOperationLog.setOperatorId(userInfo.toString());
		// 联系人信息
		if (null == order || null == order.getContactName()) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_INFORMATION_INCOMPLETE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		orderOperationLog.setContactName(order.getContactName()
				+ order.getContactMobile());

		orderOperationLog.setOperatorType(operationType);
		return orderOperationLog;
	}

	/**
	 * 
	 * @description 通过OderView生成ERPOrder.
	 * @author 潘光均
	 * @version 0.1 2012-3-16
	 * @param OrderView
	 * @date 2012-3-16
	 * @return ERPOrder
	 * @update 2012-3-16 下午5:08:07
	 */
	public static AppointmentCarInfo produceERPOrder(Order vorder,
			BookVehicleInfo bkinfo) {
		AppointmentCarInfo carInfo = new AppointmentCarInfo();
		Order ord = vorder;
		// carInfo.setCustAddress(ord.getContactProvince() + Constant.PROVINCE
		// + ord.getContactCity() + Constant.CITY + ord.getContactArea()
		// + Constant.AREA);
		carInfo.setCustProvince(ord.getContactProvince());
		carInfo.setCustCity(vorder.getContactCity());
		carInfo.setCustArea(vorder.getContactArea());
		carInfo.setCustAddress(ord.getContactAddress());

		// 拼接到达收货地址
		String custArrAddress = "";
		if (StringUtils.isNotEmpty(vorder.getReceiverCustProvince())) {
			custArrAddress = custArrAddress + vorder.getReceiverCustProvince();
		}
		if (StringUtils.isNotEmpty(vorder.getReceiverCustCity())) {
			custArrAddress = custArrAddress + vorder.getReceiverCustCity();
		}
		if (StringUtils.isNotEmpty(vorder.getReceiverCustArea())) {
			custArrAddress = custArrAddress + vorder.getReceiverCustArea();
		}
		if (StringUtils.isNotEmpty(vorder.getReceiverCustAddress())) {
			custArrAddress = custArrAddress + vorder.getReceiverCustAddress();
		}
		
		carInfo.setCustArrAddress(custArrAddress);
		/*		修改人：匡永琴
		修改时间：2013-11-12
		修改内容：注入支付方式
		修改原因：20131204v 配合接口  配合PDA 
		*/
		carInfo.setPaidMethod(ord.getPaymentType());
		/*		修改人：张斌
		修改时间：2013-7-30
		修改内容：
            carInfo.setVehicleType(bkinfo.getVehicleType());
			carInfo.setUsingVehicleDeptNum(bkinfo.getUseVehicleDept());
			carInfo.setDeliveryVehNum(bkinfo.getVehicleTeam());
			carInfo.setDeptCode(bkinfo.getUseVehicleDept());
		修改原因：将原有的Order转ErpOrder方法，更改得更适用于快递订单的转换*/
		//begin
		if(bkinfo!=null){
			carInfo.setVehicleType(bkinfo.getVehicleType());
			carInfo.setUsingVehicleDeptNum(bkinfo.getUseVehicleDept());
			carInfo.setDeliveryVehNum(bkinfo.getVehicleTeam());
			carInfo.setDeptCode(bkinfo.getUseVehicleDept());
		}
		//end
		carInfo.setOrderType(produceSource(ord.getResource()));
		String shipperName = vorder.getShipperName();
		if (shipperName != null && !"".equals(shipperName.trim())) {
			carInfo.setCustName(shipperName);
		} else {
			carInfo.setCustName(vorder.getContactName());
		}
		carInfo.setCustTel(vorder.getContactPhone());
		carInfo.setCustMobile(vorder.getContactMobile());
		carInfo.setGoodsName(ord.getGoodsName());
		/*修改人：张斌
		修改时间：2013-8-2 15:39
		修改内容：
		carInfo.setWeight(new BigDecimal(ord.getTotalWeight()));
		carInfo.setCubage(new BigDecimal(ord.getTotalVolume()));
		修改原因：增加判断，可能传过来值为null*/
		//begin
		if(ord.getTotalWeight() ==null){
			carInfo.setWeight(null);
		}else{
			carInfo.setWeight(new BigDecimal(ord.getTotalWeight()));
		}
		if(ord.getTotalVolume()==null){
			carInfo.setCubage(null);
		}else{
			carInfo.setCubage(new BigDecimal(ord.getTotalVolume()));
		}
		//end
		carInfo.setPacking(ord.getPacking());
		carInfo.setPieces(ord.getGoodsNumber());
		carInfo.setRemark(ord.getTransNote());
		carInfo.setTransProperty(getOrderTransMode(ord.getTransportMode()));
		/*		修改人：kuang
		修改时间：2013-8-5
		修改内容：注释掉代码carInfo.setDeptCode(bkinfo.getUseVehicleDept())；
		修改原因：将DeptCode变为到达网点的标杆编码*/
		//carInfo.setDeptCode(bkinfo.getUseVehicleDept());
		carInfo.setDeliverMode(getOrderDeliverMode(ord.getDeliveryMode()));
		carInfo.setOrderedTime(ord.getCreateDate());
		carInfo.setFirstPickTime(ord.getBeginAcceptTime());
		carInfo.setLastPickTime(ord.getEndAcceptTime());
		String gt = ord.getGoodsType();
		if (gt != null && !"".equals(gt)) {
			// 2012-5-18 cdy 货物类型： 1表示A货 2表示B货,若非AB货则不传；
			carInfo.setGoodsType(gt);
		}
		/*		修改人：张斌
		修改时间：2013-7-30
		修改内容：
           carInfo.setCreatorNum(((User) UserContext.getCurrentUser())
				.getEmpCode().getEmpCode());
		修改原因：将原有的Order转ErpOrder方法，更改得更适用于快递订单的转换*/
		//begin
		if(UserContext.getCurrentUser()!=null){
			if(((User) UserContext.getCurrentUser())
					.getEmpCode()!=null){
				carInfo.setCreatorNum(((User) UserContext.getCurrentUser())
						.getEmpCode().getEmpCode());
			}
			if(((User) UserContext.getCurrentUser())
					.getEmpCode().getDeptId()!=null){
				carInfo.setVehDeptNum(((User) UserContext.getCurrentUser())
						.getEmpCode().getDeptId().getStandardCode());
			}
			
		}
		//end
		carInfo.setOrderNumber(ord.getOrderNumber());
		return carInfo;
	}

	/**
	 * @description 根据字符串来源转换成数字型.
	 * @author 潘光均
	 * @version 0.1 2012-5-25
	 * @param
	 * @date 2012-5-25
	 * @return String
	 * @update 2012-5-25 下午2:13:46
	 */
	// 0营业厅，1淘宝网，2呼叫中心，3阿里巴巴网，4金蝶友商网，5淘宝商城
	public static String produceSource(String resource) {
		if (null == resource || "".equals(resource)) {
			return null;
		} else if (Constant.ORDER_SOURCE_ALIBABA.equals(resource)) {
			return "3";
		} else if (Constant.ORDER_SOURCE_BUSINESS_DEPT.equals(resource)) {
			return "0";
		} else if (Constant.ORDER_SOURCE_CALLCENTER.equals(resource)) {
			return "2";
		} else if (Constant.ORDER_SOURCE_SHANGCHENG.equals(resource)) {
			return "5";
		} else if (Constant.ORDER_SOURCE_TAOBAO.equals(resource)) {
			return "1";
		} else if (Constant.ORDER_SOURCE_YOUSHANG.equals(resource)) {
			return "4";
		} else if (Constant.ORDER_SOURCE_ONLINE.equals(resource)) {
			return "6";
		}
		// QQ速递
		else if (Constant.ORDER_SOURCE_QQSUDI.equals(resource)) {
			return "7";
		}
		//修改时间：2013-10-30
		//修改人：张斌
		//修改内容：以后新增的渠道，传到FOSS的渠道编码，
		//都跟CRM的编码一直，如果需要转换，则在FOSS传给PDA时进行转换。只需告知FOSS，CRM传给他的渠道编号是啥即可
		else{
			return resource;
		}
	}

	/**
	 * 
	 * @description 根据订单的运输类型生成erp中运输类型编码.
	 * @author 潘光均
	 * @version 0.1 2012-5-7
	 * @param String
	 * @date 2012-5-7
	 * @return Integer
	 * @update 2012-5-7 上午9:42:18
	 */
	// 0精准空运，1精准空运，2精准汽运（长途），3精准卡航，4精准城运，
	// 5汽运偏线，6精准汽运（短途）
	/** @author roy 配合Foss修改 */
	// 运输性质
	/*
	 * 精准汽运（长途）LRF 精准卡航 FLF 精准汽运（短途）SRF 精准城运 FSF 汽运偏线 PLF 精准空运 AF 整车 WVH 包裹 PACKAGE
	 */
	@SuppressWarnings("serial")
	public static String getOrderTransMode(String mode) {
		if (Constant.ORDER_TRANSTYPE_JZKY.equals(mode)) {
			return Constant.TRAN_TYPE_AIR;
		} else if (Constant.ORDER_TRANSTYPE_JZQY_SHORT.equals(mode)) {
			return Constant.TRAN_TYPE_SRF;
		} else if (Constant.ORDER_TRANSTYPE_JZKH.equals(mode)) {
			return Constant.TRAN_TYPE_FLF;
		} else if (Constant.ORDER_TRANSTYPE_JZCY.equals(mode)) {
			return Constant.TRAN_TYPE_FSF;
		} else if (Constant.ORDER_TRANSTYPE_AGENT_VEHICLE.equals(mode)) {
			return Constant.TRAN_TYPE_PLF;
		} else if (Constant.ORDER_TRANSTYPE_JZQY_LONG.equals(mode)) {
			return Constant.TRAN_TYPE_LRF;
		}
      /**			
	    * 修改人：张斌
		*修改时间：2013-7-30 22:45
		*原有内容：无（新增）
		*修改原因：增加包裹的类型转换
	  */
		//begin
		else if (Constant.ORDER_TRANSTYPE_AGENT_PACKAGE.equals(mode)) {
			return Constant.TRAN_TYPE_PACKAGE;
		}
		//end
		else {
			OrderException e = new OrderException(
					OrderExceptionType.NO_SUCH_TRANSTYPE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @description 根据订单的运输类型生成erp中运输类型编码.
	 * @author 潘光均
	 * @version 0.1 2012-5-7
	 * @param String
	 * @date 2012-5-7
	 * @return Integer
	 * @update 2012-5-7 上午9:42:18
	 */
	// 0送货（不含上楼），1自提，2送货另收费，3免费自提，
	// 4机场自提，5市区自提，6送货上楼，7内部带货自提，8内部带货送货，
	// 9内部转货自提，10内部转货送货，11免费送货，
	// 12自提（不含机场送货），13送货进仓，14送货上门
	/** @author roy */
	// 配合foss修改

	// 提货方式取值：
	/*
	 * PICKSELF(自提),PICKFOOR(送货上门),PICKONAIEPORT 机场自提,PICKUPSTAIRS 送货上楼,
	 * PICKNOTUPSTAIRS 送货(不含上楼)
	 */
	@SuppressWarnings("serial")
	public static String getOrderDeliverMode(String mode) {
		// 自提
		if (Constant.ORDER_DELIVERMODE_PICKSELF.equals(mode)) {
			return Constant.ORDER_DELIVERMODE_PICKSELF_VHE;
		}
		// 机场自提
		else if (Constant.ORDER_DELIVERMODE_PICKONAIEPORT.equals(mode)) {
			return Constant.ORDER_DELIVERMODE_PICKONAIEPORT_VHE;
			// 送货上楼
		} else if (Constant.ORDER_DELIVERMODE_PICKUPSTAIRS.equals(mode)) {
			return Constant.ORDER_DELIVERMODE_PICKUPSTAIRS_VHE;
			// 送货(不含上楼)
		} else if (Constant.ORDER_DELIVERMODE_PICKNOTUPSTAIRS.equals(mode)) {
			return Constant.ORDER_DELIVERMODE_PICKNOTUPSTAIRS_VHE;
			// 送货进仓
		} else if (Constant.ORDER_DELIVERMODE_DELIVERYSTOCK.equals(mode)) {
			return Constant.ORDER_DELIVERMODE_DELIVERYSTOCK;
			// 内部带货自提
		} else if (Constant.ORDER_DELIVERMODE_INNERPICKUP.equals(mode)) {
			return Constant.ORDER_DELIVERMODE_INNERPICKUP;
			// 免费送货
		} else if (Constant.ORDER_DELIVERMODE_FREEDELIVERY.equals(mode)) {
			return Constant.ORDER_DELIVERMODE_FREEDELIVERY;
			// 自提（不含机场提货费）
		} else if (Constant.ORDER_DELIVERMODE_SELFPICKUP.equals(mode)) {
			return Constant.ORDER_DELIVERMODE_SELFPICKUP;
			// 免费自提
		} else if (Constant.ORDER_DELIVERMODE_FREEPICKUP.equals(mode)) {
			return Constant.ORDER_DELIVERMODE_FREEPICKUP;
		} 
		/**			
		    * 修改人：张斌
			*修改时间：2013-8-01 15:10
			*原有内容：无（新增）
			*修改原因：提货方式：送货
		 */
		//begin
		//送货
		else if (Constant.ORDER_DELIVERMODE_DELIVER_UP.equals(mode)) {
			return Constant.ORDER_DELIVERMODE_DELIVER_UP;
		}
		//end
		else {
			OrderException e = new OrderException(
					OrderExceptionType.NO_SUCH_DELIVERMODE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @description 根据订单状态的英语名称转化为汉字.
	 * @author 潘光均
	 * @version 0.1 2012-4-17
	 * @param String
	 * @date 2012-4-17
	 * @return String
	 * @update 2012-4-17 下午5:32:49
	 */
	public static String getCHNOrderStatusByENStatus(String status) {
		return DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.ORDER_STATUS,
				status);
	}

	/**
	 * 
	 * @description 比较两个对象是否不等.
	 * @author 安小虎
	 * @version 0.1
	 * @param Object
	 *            ,Object
	 * @date 2012-7-4
	 * @return true|false
	 * @update 2012-7-4 上午8:45:20
	 */
	public static boolean notEqual(Object obj1, Object obj2) {
		if (obj1 == obj2) {
			return false;
		}
		if ((obj1 == null && "".equals(obj2))
				|| (obj2 == null && "".equals(obj1))) {
			return false;
		}
		if (obj1 != null) {
			return obj1.equals(obj2) == false;
		}
		if (obj2 != null) {
			return obj2.equals(obj1) == false;
		}
		return obj1.equals(obj2) == false;
	}

	/**
	 * 
	 * @description 转换Boolean值为是或否.
	 * @author 安小虎
	 * @version 0.1
	 * @param Boolean对象
	 * @date 2012-7-4
	 * @return 字符串"是"或"否"
	 * @update 2012-7-4 上午9:55:52
	 */
	public static String convertYesOn(Boolean boo) {
		return (boo == null ? false : boo) ? "是" : "否";
	}

	/**
	 * 
	 * @description 转换NULL和空字符串为空.
	 * @author 安小虎
	 * @version 0.1
	 * @param Object
	 * @date 2012-7-4
	 * @return Object
	 * @update 2012-7-4 上午10:29:06
	 */
	public static Object convertNull(Object obj) {
		return (obj == null || "".equals(obj)) ? "空" : obj;
	}

	/**
	 * 
	 * @description 转换日期.
	 * @author 安小虎
	 * @version 0.1
	 * @param Object
	 * @date 2012-7-4
	 * @return Object
	 * @update 2012-7-4 下午2:55:20
	 */
	public static String convertDate(Date obj) {
		/**
		 * CRM-3175 定义final的格式掩码，在代码中创建DateFormat author by xu
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/**
		 * CRM-3178 日期 与空格进行 equals 比较 (obj == null || "".equals(obj)) 修改为 (obj
		 * == null) by hbf
		 */
		return (obj == null) ? "空" : sdf.format(obj);
	}

	/**
	 * 
	 * <p>
	 * Description:生成修改日志<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22上午11:29:31
	 * @param order
	 * @param newOrder
	 * @return String
	 * @update 2013-1-22上午11:29:31
	 */
	public static String produceModifyLog(Order order, Order newOrder,
			ILadingstationDepartmentManager ladingstationDepartmentManager) {
		StringBuffer buffer = new StringBuffer();
		if (OrderUtil.notEqual(order.getIsSendmessage(),
				newOrder.getIsSendmessage())) {
			buffer.append("是否短信提醒，由");
			buffer.append(OrderUtil.convertYesOn(order.getIsSendmessage()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertYesOn(newOrder.getIsSendmessage()));
			buffer.append("；");
		}

		// ---发货信息---
		if (OrderUtil.notEqual(order.getContactName(),
				newOrder.getContactName())) {
			buffer.append("发货人，由");
			buffer.append(OrderUtil.convertNull(order.getContactName()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getContactName()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getContactMobile(),
				newOrder.getContactMobile())) {
			buffer.append("发货人手机，由");
			buffer.append(OrderUtil.convertNull(order.getContactMobile()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getContactMobile()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getContactPhone(),
				newOrder.getContactPhone())) {
			buffer.append("发货人电话，由");
			buffer.append(OrderUtil.convertNull(order.getContactPhone()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getContactPhone()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getIsReceiveGoods(),
				newOrder.getIsReceiveGoods())) {
			buffer.append("是否接货，由");
			buffer.append(OrderUtil.convertYesOn(order.getIsReceiveGoods()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertYesOn(newOrder.getIsReceiveGoods()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getBeginAcceptTime(),
				newOrder.getBeginAcceptTime())) {
			buffer.append("接货起始时间，由");
			buffer.append(OrderUtil.convertDate(order.getBeginAcceptTime()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertDate(newOrder.getBeginAcceptTime()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getEndAcceptTime(),
				newOrder.getEndAcceptTime())) {
			buffer.append("接货结束时间，由");
			buffer.append(OrderUtil.convertDate(order.getEndAcceptTime()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertDate(newOrder.getEndAcceptTime()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getContactProvince(),
				newOrder.getContactProvince())) {
			buffer.append("发货省份，由");
			buffer.append(OrderUtil.convertNull(order.getContactProvince()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getContactProvince()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getContactCity(),
				newOrder.getContactCity())) {
			buffer.append("发货城市，由");
			buffer.append(OrderUtil.convertNull(order.getContactCity()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getContactCity()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getContactArea(),
				newOrder.getContactArea())) {
			buffer.append("发货区县，由");
			buffer.append(OrderUtil.convertNull(order.getContactArea()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getContactArea()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getContactAddress(),
				newOrder.getContactAddress())) {
			buffer.append("发货联系人地址，由");
			buffer.append(OrderUtil.convertNull(order.getContactAddress()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getContactAddress()));
			buffer.append("；");
		}
		// ---收货信息---
		if (OrderUtil.notEqual(order.getReceiver(), newOrder.getReceiver())) {
			buffer.append("收货联系人姓名，由");
			buffer.append(OrderUtil.convertNull(order.getReceiver()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getReceiver()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getReceiverCustMobile(),
				newOrder.getReceiverCustMobile())) {
			buffer.append("收货联系人手机，由");
			buffer.append(OrderUtil.convertNull(order.getReceiverCustMobile()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder
					.getReceiverCustMobile()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getReceiverCustPhone(),
				newOrder.getReceiverCustPhone())) {
			buffer.append("收货联系人电话，由");
			buffer.append(OrderUtil.convertNull(order.getReceiverCustPhone()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getReceiverCustPhone()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getReceiverCustProvince(),
				newOrder.getReceiverCustProvince())) {
			buffer.append("收货人省份，由");
			buffer.append(OrderUtil.convertNull(order.getReceiverCustProvince()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder
					.getReceiverCustProvince()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getReceiverCustCity(),
				newOrder.getReceiverCustCity())) {
			buffer.append("收货人城市，由");
			buffer.append(OrderUtil.convertNull(order.getReceiverCustCity()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getReceiverCustCity()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getReceiverCustArea(),
				newOrder.getReceiverCustArea())) {
			buffer.append("收货人区县，由");
			buffer.append(OrderUtil.convertNull(order.getReceiverCustArea()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getReceiverCustArea()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getReceiverCustAddress(),
				newOrder.getReceiverCustAddress())) {
			buffer.append("收货人详细地址，由");
			buffer.append(OrderUtil.convertNull(order.getReceiverCustAddress()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder
					.getReceiverCustAddress()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getReceivingToPoint(),
				newOrder.getReceivingToPoint())) {
			buffer.append("到达网点，由");
			buffer.append(OrderUtil.convertNull(OrderUtil.getStationNameById(
					order.getReceivingToPoint(), ladingstationDepartmentManager)));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(OrderUtil.getStationNameById(
					newOrder.getReceivingToPoint(),
					ladingstationDepartmentManager)));
			buffer.append("；");
		}
		// ---货物信息---
		if (OrderUtil.notEqual(order.getGoodsName(), newOrder.getGoodsName())) {
			buffer.append("货物名称，由");
			buffer.append(OrderUtil.convertNull(order.getGoodsName()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getGoodsName()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getTotalVolume(),
				newOrder.getTotalVolume())) {
			buffer.append("总体积，由");
			buffer.append(OrderUtil.convertNull(order.getTotalVolume()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getTotalVolume()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getPacking(), newOrder.getPacking())) {
			buffer.append("包装材料，由");
			buffer.append(OrderUtil.convertNull(order.getPacking()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getPacking()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getGoodsNumber(),
				newOrder.getGoodsNumber())) {
			buffer.append("总件数，由");
			buffer.append(OrderUtil.convertNull(order.getGoodsNumber()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getGoodsNumber()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getTotalWeight(),
				newOrder.getTotalWeight())) {
			buffer.append("总重量，由");
			buffer.append(OrderUtil.convertNull(order.getTotalWeight()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getTotalWeight()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getGoodsType(), newOrder.getGoodsType())) {
			buffer.append("货物类型，由");
			buffer.append(OrderUtil.convertNull(order.getGoodsType()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getGoodsType()));
			buffer.append("；");
		}
		// ---运输及服务---
		if (OrderUtil.notEqual(order.getInsuredAmount(),
				newOrder.getInsuredAmount())) {
			buffer.append("保价金额，由");
			buffer.append(OrderUtil.convertNull(order.getInsuredAmount()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getInsuredAmount()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getReviceMoneyAmount(),
				newOrder.getReviceMoneyAmount())) {
			buffer.append("代收货款，由");
			buffer.append(OrderUtil.convertNull(order.getReviceMoneyAmount()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getReviceMoneyAmount()));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getReciveLoanType(),
				newOrder.getReciveLoanType())) {
			buffer.append("代收货款类型，由");
			buffer.append(OrderUtil.convertNull(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.REFUND_TYPE, order.getReciveLoanType())));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.REFUND_TYPE, newOrder.getReciveLoanType())));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getReturnBillType(),
				newOrder.getReturnBillType())) {
			buffer.append("签收单，由");
			buffer.append(OrderUtil.convertNull(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.SIGNBILL, order.getReturnBillType())));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.SIGNBILL, newOrder.getReturnBillType())));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getTransportMode(),
				newOrder.getTransportMode())) {
			buffer.append("运输方式，由");
			buffer.append(OrderUtil.convertNull(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.TRANS_TYPE, order.getTransportMode())));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.TRANS_TYPE, newOrder.getTransportMode())));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getDeliveryMode(),
				newOrder.getDeliveryMode())) {
			buffer.append("提货方式，由");
			buffer.append(OrderUtil.convertNull(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.PICKGOODTYPE, order.getDeliveryMode())));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.PICKGOODTYPE, newOrder.getDeliveryMode())));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getPaymentType(),
				newOrder.getPaymentType())) {
			buffer.append("付款方式，由");
			buffer.append(OrderUtil.convertNull(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.PAY_WAY, order.getPaymentType())));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.PAY_WAY, newOrder.getPaymentType())));
			buffer.append("；");
		}
		if (OrderUtil.notEqual(order.getTransNote(), newOrder.getTransNote())) {
			buffer.append("储运事项，由");
			buffer.append(OrderUtil.convertNull(order.getTransNote()));
			buffer.append("改为");
			buffer.append(OrderUtil.convertNull(newOrder.getTransNote()));
			buffer.append("；");
		}
		// ---其它---
		if (newOrder.getDealPerson() != null
				&& !"".equals(newOrder.getDealPerson())
				&& order.getDealPerson() != null
				&& !order.getDealPerson().equals(newOrder.getDealPerson())) {
			buffer.append("受理人，由");
			buffer.append(order.getDealPerson());
			buffer.append("改为");
			buffer.append(newOrder.getDealPerson());
			buffer.append("；");
		}
		if (newOrder.getReceiverCustcompany() != null
				&& !"".equals(newOrder.getReceiverCustcompany())
				&& order.getReceiverCustcompany() != null
				&& !order.getReceiverCustcompany().equals(
						newOrder.getReceiverCustcompany())) {
			buffer.append("收货客户所属公司，由");
			buffer.append(order.getReceiverCustcompany());
			buffer.append("改为");
			buffer.append(newOrder.getReceiverCustcompany());
			buffer.append("；");
		}
		if (newOrder.getReceiverCustId() != null
				&& !"".equals(newOrder.getReceiverCustId())
				&& order.getReceiverCustId() != null
				&& !order.getReceiverCustId().equals(
						newOrder.getReceiverCustId())) {
			buffer.append("收货客户，由");
			buffer.append(order.getReceiverCustId());
			buffer.append("改为");
			buffer.append(newOrder.getReceiverCustId());
			buffer.append("；");
		}
		if (newOrder.getReceiverCustNumber() != null
				&& !"".equals(newOrder.getReceiverCustNumber())
				&& order.getReceiverCustNumber() != null
				&& !order.getReceiverCustNumber().equals(
						newOrder.getReceiverCustNumber())) {
			buffer.append("接货客户编号，由");
			buffer.append(order.getReceiverCustNumber());
			buffer.append("改为");
			buffer.append(newOrder.getReceiverCustNumber());
			buffer.append("；");
		}
		if (newOrder.getShipperNumber() != null
				&& !"".equals(newOrder.getShipperNumber())
				&& order.getShipperNumber() != null
				&& !order.getShipperNumber()
						.equals(newOrder.getShipperNumber())) {
			buffer.append("发货客户编号，由");
			buffer.append(order.getShipperNumber());
			buffer.append("改为");
			buffer.append(newOrder.getShipperNumber());
			buffer.append("；");
		}
		if (newOrder.getShipperName() != null
				&& !"".equals(newOrder.getShipperName())
				&& order.getShipperName() != null
				&& !order.getShipperName().equals(newOrder.getShipperName())) {
			buffer.append("发货人名称，由");
			buffer.append(order.getShipperName());
			buffer.append("改为");
			buffer.append(newOrder.getShipperName());
			buffer.append("；");
		}
		if (newOrder.getReceiverCustName() != null
				&& !"".equals(newOrder.getReceiverCustName())
				&& order.getReceiverCustName() != null
				&& !order.getReceiverCustName().equals(
						newOrder.getReceiverCustName())) {
			buffer.append("发货客户名称，由");
			buffer.append(order.getReceiverCustName());
			buffer.append("改为");
			buffer.append(newOrder.getReceiverCustName());
			buffer.append("；");
		}

		return buffer.toString();
	}

	/**
	 * 
	 * <p>
	 * Description:生成约车历史<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22上午11:31:02
	 * @param order
	 * @param info
	 * @param user
	 * @param vehicleTeamName
	 * @return VehicleHistory
	 * @update 2013-1-22上午11:31:02
	 */
	public static VehicleHistory produceVehicleHistory(Order order,
			BookVehicleInfo info, User user, String vehicleTeamName) {
		VehicleHistory history = new VehicleHistory();
		history.setCreateUser(user.getId());
		history.setUserVehicleDeptCode(info.getUseVehicleDept());
		history.setUseVehicleDeptName(user.getEmpCode().getDeptId()
				.getDeptName());
		history.setVehicleTeamCode(info.getVehicleTeam());
		history.setVehicleTeamName(vehicleTeamName);
		return history;
	}
	/**
	 * @作者：kuang
	 * @时间：2013-6-9
	 * @描述：渠道订单类型由值转换为中文
	 * */
	public static String changeOrderSourceValueToDesc(String value) {
		if (value.equals("ORDER_SOURCE_ONLINE")) {
			return "网上营业厅";
		} else if (value.equals("ORDER_SOURCE_CALLCENTER")) {
			return "呼叫中心";
		} else if (value.equals("ORDER_SOURCE_TAOBAO")) {
			return "淘宝网";
		} else if (value.equals("ORDER_SOURCE_ALIBABA")) {
			return "阿里巴巴网";
		} else if (value.equals("ORDER_SOURCE_YOUSHANG")) {
			return "金蝶友商网";
		} else if (value.equals("ORDER_SOURCE_SHANGCHENG")) {
			return "淘宝商城网";
		} else if (value.equals("ORDER_SOURCE_BUSINESS_DEPT")) {
			return "营业部";
		} else if(value.equals("ORDER_SOURCE_QQSUDI")){
			return "QQ速递";
		} else {
			return "未知渠道";
		}

	}

	/**
	 * 是否呼叫中心
	 * 
	 * @param orderResourse
	 * @return
	 */
	public static boolean isCallCenter(String orderResourse) {
		// 增加呼叫中心合肥 2013-11-12
		// 如果如果订单来源为呼叫中心苏州或呼叫中心合肥，返回true
		return orderResourse.startsWith(Constant.ORDER_SOURCE_CALLCENTER);
	}

	public static boolean isModifyChannel(String resource) {
		List<String> channalList = new ArrayList<String>();
		channalList.add(Constant.ORDER_SOURCE_WEIXIN);
		channalList.add(Constant.ORDER_SOURCE_HUAQIANGBAO);
		channalList.add(Constant.ORDER_SOURCE_HAOBAI);
		channalList.add(Constant.ORDER_SOURCE_XIAOMI);
		if (channalList.contains(resource)) {
			return true;
		} else {
			return false;
		}
	}

}
