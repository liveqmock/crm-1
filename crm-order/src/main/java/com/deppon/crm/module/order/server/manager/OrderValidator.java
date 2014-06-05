package com.deppon.crm.module.order.server.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.order.server.util.Constant;
import com.deppon.crm.module.order.server.util.OrderUtil;
import com.deppon.crm.module.order.shared.domain.BookVehicleInfo;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.crm.module.order.shared.exception.OrderException;
import com.deppon.crm.module.order.shared.exception.OrderExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * @description 订单验证器
 * @author 潘光均
 * @version 0.1 2012-3-13
 * @date 2012-3-13
 * @updateUser:邹明
 * @updateDate:2013-01-22
 * @updateDescription:根据sonar规则修改代码
 */
public class OrderValidator {
	
	private static final String SERIAL = "serial";
	/**
	 * 
	 * @description 保险价值：官网的订单不能修改，其他来源的订单可以修改.
	 * @author 安小虎
	 * @version 0.1
	 * @date 2012-6-14
	 */
	@SuppressWarnings(SERIAL)
	public boolean checkInsuredAmountUpdateable(Double newInsuredAmount,
			Double oldInsuredAmount) {
		//如果新的保险价值为空并且旧的保险价值也为空，则返回true,否则抛出异常
		if (newInsuredAmount == null && oldInsuredAmount == null) {
			return true;
		} else if (newInsuredAmount != null && oldInsuredAmount == null) {
			//如果新的保险价值不为空，旧的保险价值为空，抛出订单保险价值不能更新异常
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_INSUREDAMOUNT_CANNOTUPDATE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		} else if (newInsuredAmount == null && oldInsuredAmount != null) {
			//如果新的保险价值为空，旧的保险价值不为空，抛出订单保险价值不能更新异常
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_INSUREDAMOUNT_CANNOTUPDATE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		} else if (newInsuredAmount != null && oldInsuredAmount != null) {
			//如果新的保险价值不为空，旧的保险价值也不为空，但是新旧保险价值不相等，抛出订单保险价值不能更新异常
			if (!newInsuredAmount.equals(oldInsuredAmount)) {
				OrderException e = new OrderException(
						OrderExceptionType.ORDER_INSUREDAMOUNT_CANNOTUPDATE);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			} else {
				//如果新的保险价值不为空，旧的保险价值也不为空，但是新旧保险价值相等，返回true
				return true;
			}
		}
		return false;
	}

	/**
	 * @description 验证订单信息是否完整.
	 * @author 潘光均
	 * @version 0.1 2012-3-9
	 * @param Order
	 * @date 2012-3-9
	 * @return boolean
	 * @update 2012-3-9 上午8:58:10
	 */
	public boolean validateOrderInfoComplete(Order order,boolean isPolitCityOrder) {
		// 验证电话号码
		isPhoneAndMobileIsNotNull(order.getContactMobile(),
				order.getContactPhone());
		// 验证是否接货
		doReceiveGoodsComplete(order.getIsReceiveGoods(), order);

		/*
		 * @描述：删除注释了的代码
		 * @修改人：邹明
		 * @时间：2012-01-22
		 */
		// 联系人姓名
		doOrderInfoInComplete(order.getContactName());
		if (order.getIsReceiveGoods()) {
			// 联系人省份
			doOrderInfoInComplete(order.getContactProvince());
			// 联系人城市
			doOrderInfoInComplete(order.getContactCity());
			// 联系人地址
			doOrderInfoInComplete(order.getContactAddress());
		}

		// 如果订单不是渠道订单，始发网点不能为空//试点城市快递直接跳过该校验
		if (!isWebChannelOrder(order)&&!isPolitCityOrder) {
			doOrderInfoInComplete(order.getStartStation());
		}

		doOrderInfoInComplete(order.getGoodsName());
		doOrderInfoInComplete(order.getTransportMode());
		doOrderInfoInComplete(order.getDeliveryMode());
		return true;
	}

	/**
	 * 
	 * @description 订单约车时验证货物数量、体积、重量、储运事项.
	 * @author 安小虎
	 * @version 0.1
	 * @param 订单对象      
	 * @date 2012-5-16
	 * @return 成功与否：true|false
	 * @update 2012-5-16 下午3:30:17
	 */
	@SuppressWarnings(SERIAL)
	public boolean bookVehicleValidator(Order order) {
		// 货物数量、体积、重量
		Integer gn = order.getGoodsNumber();
		//如果货物数量为空或者小于零，或者大于9999，则抛出货物数量异常
		if (gn == null || gn <= 0 || gn > 9999) {
			OrderException e = new OrderException(
					OrderExceptionType.BOOKVIHICE_NUM_GOODSNUMBER);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		Double tv = order.getTotalVolume();
		//如果货物体积为空或者小于零，或者大于999，则抛出货物体积异常
		if (tv == null || tv <= 0 || tv > 999) {
			OrderException e = new OrderException(
					OrderExceptionType.BOOKVIHICE_NUM_TOTALVOLUME);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		Double tw = order.getTotalWeight();
		//如果货物数量为空或者小于零，或者大于999999，则抛出货物重量异常
		if (tw == null || tw <= 0 || tw > 999999) {
			OrderException e = new OrderException(
					OrderExceptionType.BOOKVIHICE_NUM_TOTALWEIGHT);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}

		// 储运事项
		String tn = order.getTransNote();
		//如果储运事项不为空，也不是空字符串，且长度大于80，则抛出“约车时，储运事项长度不能大于80”的异常
		if (tn != null && !"".equals(tn.trim()) && tn.length() > 80) {
			OrderException e = new OrderException(
					OrderExceptionType.BOOKVIHICE_TRANSNOTE_CANNOTOVER80);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return true;
	}

	/**
	 * @description 校验始发接货.
	 * @author 潘光均
	 * @version 0.1 2012-3-31
	 * @param b
	 *            true or false
	 * @date 2012-3-31
	 * @return none
	 * @update 2012-3-31 上午10:01:20
	 */
	@SuppressWarnings(SERIAL)
	private boolean doReceiveGoodsComplete(Boolean isReceiveGoods, Order order) {
		//如果isReceiveGoods为空，则抛出“订单信息不完整”异常
		if (null == isReceiveGoods) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_INFORMATION_INCOMPLETE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		 /*
		  * 将 if合并
		  * @author 邹明
		  * @date 2012-01-21
		  */
		//如果isReceiveGoods威震，但是订单开始受理时间和截止受理时间为空的话，抛出“接货时间为空”异常
		else if (isReceiveGoods&&(null == order.getBeginAcceptTime()
										|| null == order.getEndAcceptTime())) {
				OrderException e = new OrderException(
						OrderExceptionType.ORDER_INFORMATION_ACCEPTTIME_INCOMPLETE);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
		}
		return true;
	}

	/**
	 * 
	 * @description 当订单信息不完整抛出异常.
	 * @author 潘光均
	 * @version 0.1 2012-3-16
	 * @param Object
	 * @param OrderExceptionType
	 * @date 2012-3-16
	 * @return
	 * @update 2012-3-16 上午9:34:31
	 */
	@SuppressWarnings(SERIAL)
	private void doOrderInfoInComplete(Object obj) {
		//如果订单信息不完整，抛出"订单信息不完整"异常
		if (!isObjectNotNull(obj)) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_INFORMATION_INCOMPLETE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @description 检查订单手机和电话是否为空.
	 * @author 潘光均
	 * @version 0.1 2012-3-12
	 * @param Order
	 * @date 2012-3-12
	 * @return Integer
	 * @update 2012-3-12 下午12:19:25
	 */
	@SuppressWarnings(SERIAL)
	private boolean isPhoneAndMobileIsNotNull(String mobile, String phone) {
		//如果移动电话与固定电话都未空，抛出异常“移动电话与固定电话至少有一个不为空”
		if (StringUtils.isBlank(mobile) && StringUtils.isBlank(phone)) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_MOBILEANDPHONE_MUSTBEONE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return true;
	}

	/**
	 * 
	 * @description 检验一个对象是否为空.
	 * @author 潘光均
	 * @version 0.1 2012-3-9
	 * @param Object
	 * @date 2012-3-9
	 * @return boolean
	 * @update 2012-3-9 下午2:44:53
	 */
	@SuppressWarnings("rawtypes")
	public boolean isObjectNotNull(Object obj) {
		if (null == obj) {
			return false;
		} else if (obj instanceof String) {
			return !StringUtils.isBlank((String) obj);
		} else if (obj instanceof List) {
			return !(((List) obj).isEmpty());
		} else if (obj instanceof Map) {
			return !((Map) obj).isEmpty();
		}
		return true;
	}

	/**
	 * 
	 * @description 验证订单是否可以修改.
	 * @author 潘光均
	 * @version 0.1 2012-3-12
	 * @param String
	 * @date 2012-3-12
	 * @return Integer
	 * @update 2012-3-12 下午2:30:01
	 */
	@SuppressWarnings(SERIAL)
	public boolean isOrderUpdateable(Order order,User user) {
		List<String> status = new ArrayList<String>();
		status.add(Constant.ORDER_STATUS_WAIT_ALLOT);
		status.add(Constant.ORDER_STATUS_WAIT_ACCEPT);
		status.add(Constant.ORDER_STATUS_ACCEPT);
		status.add(Constant.ORDER_SATUTS_GOBACK);
		if (!isDoableStatus(order.getOrderStatus(), status)) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_CANNOT_UPDATE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		
		//如果是呼叫中心订单，只能由受理部门或者400订单管理组修改
		if(!user.getId().equals("DP0000")){
			if(OrderUtil.isModifyChannel(order.getResource())){
				return true;
			}
			// 增加呼叫中心合肥 2013-11-12
			else if(OrderUtil.isCallCenter(order.getResource())){
				String acceptDeptId = user.getEmpCode().getDeptId().getId();
				/*		修改人：kuang
				修改时间：2013-9-11
				修改内容：验证呼叫中心订单修改权限
				修改原因：除订单管理组外，话务员也能修改订单*/
				if(!acceptDeptId.equals(order.getAcceptDept()) 
						&& !isRoleExist(user,Constant.ROLEID_CALLCENTER_UPDATE)){
					OrderException e = new OrderException(
							OrderExceptionType.CANNOT_MODIFY_ONLYRESOURCEDEPTCC);
					throw new GeneralException(e.getErrorCode(),e.getMessage(),e,
							new Object[]{}) {
					};
				}			
			}
		}
//		
//		//只能修改营业部和呼叫中心订单
//		String orderSource = order.getResource();
//		if(!Constant.ORDER_SOURCE_CALLCENTER.equals(orderSource) &&
//				!Constant.ORDER_SOURCE_BUSINESS_DEPT.equals(orderSource)){
//			OrderException e = new OrderException(
//					OrderExceptionType.CANNOT_MODIFY_ONLYRESOURCEDEPTCC);
//			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,
//					new Object[]{}) {
//			};
//		}
//		
		return true;
	}

	/**
	 * 
	 * @Title: isRefuseableOrder
	 * @Description: 订单是否在未分配、受理
	 * @param @param orderIds待
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	@SuppressWarnings(SERIAL)
	public boolean isRefuseableOrder(Order order) {
		List<String> statusList = new ArrayList<String>();
		statusList.add(Constant.ORDER_STATUS_WAIT_ALLOT);
		statusList.add(Constant.ORDER_STATUS_WAIT_ACCEPT);
		if (isDoableStatus(order.getOrderStatus(), statusList)) {
			return true;
		} else {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_NOT_REFUSEABLE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @description 检查订单的状态是否在给点的状态中.
	 * @author 潘光均
	 * @version 0.1 2012-3-14
	 * @param Order
	 * @param String
	 *            []
	 * @date 2012-3-14
	 * @return boolean
	 * @update 2012-3-14 下午5:53:53
	 */
	private boolean isDoableStatus(String orderStatus, List<String> status) {
		return status.contains(orderStatus);
	}

	/**
	 * 
	 * @Title: isInDayRange
	 * @Description: 是否在限定范围内
	 * @param @param startDate
	 * @param @param endDate
	 * @param @param range
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	@SuppressWarnings(SERIAL)
	public boolean isInDayRange(Date startDate, Date endDate, int range) {
		Long dayms = 1L * 24 * 60 * 60 * 1000;
		int days = (int) ((endDate.getTime() - startDate.getTime()) / dayms);
		//如果天数在0到30之间，返回true，否则抛出异常“查询时间超出查询范围”
		if (days >= 0 && days <= 30) {
			return true;
		} else {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_NOT_DATE_OUTRANGE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @Title: isAssignableOrder
	 * @Description: 订单是否在未分配、待受理和已受理
	 * @param @param orderIds
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	@SuppressWarnings(SERIAL)
	public boolean isAssignableOrder(List<Order> orderList) {
		List<String> statusList = new ArrayList<String>();
		statusList.add(Constant.ORDER_STATUS_WAIT_ALLOT);
		statusList.add(Constant.ORDER_STATUS_WAIT_ACCEPT);
		statusList.add(Constant.ORDER_STATUS_ACCEPT);
		for (Order order : orderList) {
			if (isDoableStatus(order.getOrderStatus(), statusList)) {
				continue;
			} else {
				OrderException e = new OrderException(
						OrderExceptionType.ORDER_NOT_ALL_ASSIGNABLE);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
		return true;
	}

	/**
	 * 
	 * @Title: isSameOrderInfo
	 * @Description: 是否相同的状态、发货联系人和地址
	 * @param @param orderIds
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	@SuppressWarnings(SERIAL)
	public boolean isSameOrderInfo(List<Order> orderList) {
		if(orderList.size()==1){
			return true;
		}
		Order firstOrder = orderList.get(0);
		/*
		 * 修改时间：2013-10-16
		 * 修改人：kuang
		 * 修改原因：增加省市区的验证，去除重复代码
		 */
		for (Order order : orderList) {
			if (firstOrder.getOrderStatus().equals(order.getOrderStatus()) 
					&&firstOrder.getContactName().equals(order.getContactName())
					&& firstOrder.getContactAddress().equals(order.getContactAddress())
					&&firstOrder.getContactArea().equals(order.getContactArea())
					&&firstOrder.getContactCity().equals(order.getContactCity())
					&&firstOrder.getContactProvince().equals(order.getContactProvince())) {
				continue;
			} else {
				OrderException e = new OrderException(
						OrderExceptionType.ORDER_NOT_SAME_INFO);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
		return true;
	}

	/**
	 * 
	 * @description 订单打回状态验证.
	 * @author 安小虎
	 * @version 0.1 2012-3-16
	 * @param 订单对象
	 * @date 2012-3-16
	 * @return true|false
	 * @update 2012-3-16 上午8:56:06
	 */
	@SuppressWarnings(SERIAL)
	public boolean isReturnOrderStatus(Order order) {
		// 构造可打回的订单状态集合：待受理
		List<String> statusList = new ArrayList<String>();
		statusList.add(Constant.ORDER_STATUS_WAIT_ACCEPT);// 待受理

		// 验证状态
		if (this.isDoableStatus(order.getOrderStatus(), statusList)) {
			return true;
		} else {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_NOT_RETURNORDER);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @description 订单撤消状态验证.
	 * @author 安小虎
	 * @version 0.1 2012-3-16
	 * @param 订单对象
	 * @date 2012-3-16
	 * @return true|false
	 * @update 2012-3-16 上午10:59:20
	 */
	@SuppressWarnings(SERIAL)
	public boolean isCancelOrderStatus(Order order,User user) {
		// 构造可撤消的订单状态集合：未分配、待受理、已受理、已退回
		List<String> statusList = new ArrayList<String>();
		statusList.add(Constant.ORDER_STATUS_WAIT_ALLOT);
		statusList.add(Constant.ORDER_STATUS_WAIT_ACCEPT);
		statusList.add(Constant.ORDER_STATUS_ACCEPT);
		statusList.add(Constant.ORDER_SATUTS_GOBACK);

		// 验证状态
		if (!this.isDoableStatus(order.getOrderStatus(), statusList)) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_CANNOT_CANCEL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		/*		修改人：kuang
		修改时间：2013-9-11
		修改内容：验证只能撤销呼叫中心订单
		修改原因：话务组只能撤销呼叫中心的订单*/
		if(!user.getId().equals("DP0000")){
			// 增加呼叫中心合肥 2013-11-12
			if (isRoleExist(user, Constant.ROLEID_CALLCENTER_CANCEL)
					&& !OrderUtil.isCallCenter(order.getResource())) {
					OrderException e = new OrderException(
							OrderExceptionType.ONLYCANCEL_CALLCENTER);
					throw new GeneralException(e.getErrorCode(),e.getMessage(),e,
							new Object[]{}) {
					};
			}
		}
		return true;
	}

	/**
	 * 
	 * @description 订单反馈信息是否不为空.
	 * @author 安小虎
	 * @version 0.1 2012-3-16
	 * @param 订单订单打回原因
	 * @date 2012-3-16
	 * @return true|false
	 * @update 2012-3-16 上午8:56:06
	 */
	@SuppressWarnings({ SERIAL })
	public boolean isFeedbackInfoNotNull(String feedbackInfo) {
		// 验证为空否
		if (this.isObjectNotNull(feedbackInfo)) {
			return true;
		} else {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_FIGHTBACKREASON_MUSTBEREQUIRED);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @description 订单不可催单状态验证.
	 * @author 安小虎
	 * @version 0.1 2012-3-16
	 * @param 订单对象
	 * @date 2012-3-16
	 * @return true|false
	 * @update 2012-3-16 上午10:57:21
	 */
	@SuppressWarnings(SERIAL)
	public boolean isUrgeOrderStatus(Order order) {
		// 构造不允许催单的订单状态集合：未分配、已撤销、已拒绝、揽货失败、正常签收、异常签收、已开单；
		List<String> statusList = new ArrayList<String>();
		statusList.add(Constant.ORDER_STATUS_WAIT_ACCEPT);
		statusList.add(Constant.ORDER_STATUS_ACCEPT);
		statusList.add(Constant.ORDER_STATUS_IN_TRANSIT);
		statusList.add(Constant.ORDER_STATUS_IN_TRANSIT);
		statusList.add(Constant.ORDER_SATUTS_SHOUTCAR);
		statusList.add(Constant.ORDER_SATUTS_GOBACK);
		statusList.add(Constant.ORDER_SATUTS_RECEIPTING);
		// 验证状态
		if (this.isDoableStatus(order.getOrderStatus(), statusList)) {
			return true;
		} else {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_NOT_URGE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @description 订单揽货失败状态验证.
	 * @author 安小虎
	 * @version 0.1 2012-3-16
	 * @param 订单对象
	 * @date 2012-3-16
	 * @return true|false
	 * @update 2012-3-16 上午10:57:21
	 */
	@SuppressWarnings(SERIAL)
	public boolean isAccpetFailOrderStatus(Order order) {
		// 构造揽货失败的订单状态集合：已受理、已退回、接货中
		List<String> statusList = new ArrayList<String>();
		statusList.add(Constant.ORDER_STATUS_ACCEPT);
		statusList.add(Constant.ORDER_SATUTS_GOBACK);
		statusList.add(Constant.ORDER_SATUTS_RECEIPTING);
		// 验证状态
		if (this.isDoableStatus(order.getOrderStatus(), statusList)) {
			return true;
		} else {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_NOT_ACCPETFAIL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @description 订单催办时间和频率限制校验.
	 * @author 安小虎
	 * @version 0.1 2012-3-15
	 * @param 订单对象
	 * @date 2012-3-15
	 * @update 2012-3-15 上午11:29:11
	 */
	@SuppressWarnings(SERIAL)
	public boolean urgeTimeAndRateLimit(Order order,
			List<OrderOperationLog> orderOperationLogList) {
		Date now = new Date();

		// 下单时间内30分钟内，禁止催单
		Date createDate = order.getCreateDate();// 订单创建时间
		Long result = this.subtractTime(now, createDate);
		if (result < 30) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_NOT_URGE30);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}

		// 针对同一订单，每15分钟内仅允许催单操作一次
		Date lastHastenTime = order.getLastHastenTime();
		if (null != lastHastenTime) {
			Long result1 = this.subtractTime(now, lastHastenTime);
			if (result1 < 15) {
				OrderException e = new OrderException(
						OrderExceptionType.ORDER_NOT_URGE15);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
		return true;
	}

	/**
	 * 
	 * @description 时间差.
	 * @author 安小虎
	 * @version 0.1 2012-3-15
	 * @param 两个DATE参数
	 * @date 2012-3-15
	 * @return 分钟
	 * @update 2012-3-15 下午3:45:28
	 */
	private Long subtractTime(Date d1, Date d2) {
		long l = d1.getTime() - d2.getTime();
		return l / (60 * 1000);
	}

	/**
	 * 
	 * @description 根据订单来源判断订单是否来自.
	 * @author 安小虎
	 * @version 0.1 2012-3-16
	 * @param 订单来源
	 * @date 2012-3-16
	 * @return true|false
	 * @update 2012-3-16 上午10:43:34
	 */
	public boolean isOrderFromOnlineOrCallcenter(String orderSource) {
		// 增加呼叫中心合肥 2013-11-12
		return OrderUtil.isCallCenter(orderSource)
				|| Constant.ORDER_SOURCE_BUSINESS_DEPT.equals(orderSource);
	}

	/**
	 * 
	 * @description 根据订单来源判断订单是否来自本系统.
	 * @author 安小虎
	 * @version 0.1 2012-3-16
	 * @param 订单来源
	 * @date 2012-3-16
	 * @return true|false
	 * @update 2012-3-16 上午10:43:34
	 */
	@SuppressWarnings(SERIAL)
	public boolean isOrderFromCrm(String orderSource) {
		//如果订单是在线申请或者是来自呼叫中心，返回true
		if (this.isOrderFromOnlineOrCallcenter(orderSource)) {
			return true;
		} else {
			//如果不是，抛出异常
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_CANNOT_CANCEL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @description 判断订单是否有始发站
	 * @author 潘光均
	 * @version 0.1 2012-3-13
	 * @param Order
	 * @date 2012-3-13
	 * @return boolean
	 * @update 2012-3-13 上午9:33:29
	 */
	public boolean isOrderHaveNoStateStation(Order order) {
		return StringUtils.isBlank(order.getStartStation());
	}

	/**
	 * 
	 * @description 校验接货信息是否改变.
	 * @author 潘光均
	 * @version 0.1 2012-3-13
	 * @param Order
	 *            ,Order
	 * @date 2012-3-13
	 * @return none
	 * @update 2012-3-13 下午2:08:46
	 */
	public boolean isReciveGoodsInfoChange(Order order, Order databaseOrder) {
		if (order == null && databaseOrder == null) {
			return false;
		} else if (order == null || databaseOrder == null) {
			return true;
		}

		return (OrderUtil.notEqual(order.getIsReceiveGoods(),
				databaseOrder.getIsReceiveGoods()) || OrderUtil.notEqual(
				order.getContactProvince(), databaseOrder.getContactProvince()))
				|| OrderUtil.notEqual(order.getContactCity(),
						databaseOrder.getContactCity())
				|| OrderUtil.notEqual(order.getContactArea(),
						databaseOrder.getContactArea())
				|| OrderUtil.notEqual(order.getContactAddress(),
						databaseOrder.getContactAddress())
				|| OrderUtil.notEqual(order.getTotalVolume(),
						databaseOrder.getTotalVolume())
				|| OrderUtil.notEqual(order.getTotalWeight(),
						databaseOrder.getTotalWeight());
	}

	/**
	 * @description 检验订单是否可以处理.
	 * @author 潘光均
	 * @version 0.1 2012-3-16
	 * @param Order
	 * @date 2012-3-16
	 * @return boolean
	 * @update 2012-3-16 上午11:27:23
	 */
	@SuppressWarnings(SERIAL)
	public boolean validateOrderProcessable(Order order) {
		List<String> status = new ArrayList<String>();
		status.add(Constant.ORDER_STATUS_WAIT_ALLOT);
		status.add(Constant.ORDER_STATUS_WAIT_ACCEPT);
		if (!isDoableStatus(order.getOrderStatus(), status)) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_CANNOT_ACCEPT);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return true;
	}

	/**
	 * @description 校验订单是否可以约车.
	 * @author 潘光均
	 * @version 0.1 2012-3-16
	 * @param Order
	 * @date 2012-3-16
	 * @return boolean
	 * @update 2012-3-16 下午2:12:13
	 */
	@SuppressWarnings(SERIAL)
	public boolean isOrderCanBookVehice(Order order) {
		List<String> status = new ArrayList<String>();
		status.add(Constant.ORDER_SATUTS_GOBACK);
		status.add(Constant.ORDER_STATUS_ACCEPT);
		/*修改人：kuang
		修改时间：2013-9-3
		修改内容：增加已延迟状态;
		修改原因：增加状态已延迟，并已延迟状态下能约车*/
		status.add(Constant.ORDER_STATUS_DELAY);
		if (!isDoableStatus(order.getOrderStatus(), status)) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_BOOKVIHICE_FAIL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return true;
	}

	/**
	 * @description 检验约车信息是否填写完整.
	 * @author 潘光均
	 * @version 0.1 2012-3-16
	 * @param OrderView
	 * @date 2012-3-16
	 * @return boolean
	 * @update 2012-3-16 下午3:33:17
	 */
	@SuppressWarnings(SERIAL)
	public boolean isBookVehicleInfoComplete(BookVehicleInfo info) {
		//
		if (!isObjectNotNull(info.getIsTailBoard())) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_BOOKVIHICEINFO_ISTAILBOARD);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//如果用车部门信息为空，抛出异常
		if (!isObjectNotNull(info.getUseVehicleDept())) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_BOOKVIHICEINFO_USEVEHICLEDEPT);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//如果车队信息为空，抛出异常
		if (!isObjectNotNull(info.getVehicleTeam())) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_BOOKVIHICEINFO_VEHICLETEAM);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return true;
	}

	/**
	 * @description 验证ERP保存order对象是否成功.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param boolean
	 * @date 2012-3-19
	 * @return boolean
	 * @update 2012-3-19 上午11:06:58
	 */
	@SuppressWarnings(SERIAL)
	public boolean isSendOrderSuccess(boolean result) {
		if (!result) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_SENDORDERTOERP_FAIL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return true;
	}

	/**
	 * @description 检查运单的状态是不是未签收.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param Waybill
	 * @date 2012-3-19
	 * @return boolean
	 * @update 2012-3-19 下午2:43:21
	 */
	@SuppressWarnings(SERIAL)
	public boolean isWaybillSigned(WaybillInfo waybill) {
		if (null == waybill) {
			return true;
		}
		return false;
	}

	/**
	 * @description 校验运单是否已经关联.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param Waybill
	 * @date 2012-3-19
	 * @return boolean
	 * @update 2012-3-19 下午3:04:48
	 */
	@SuppressWarnings(SERIAL)
	public boolean isWaybillAssociated(Order order) {
		if (null != order) {
			OrderException e = new OrderException(
					OrderExceptionType.WAYBILL_ALREADYASSOCIATE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return false;
	}

	/**
	 * @description 校验用户是否登录.
	 * @author 潘光均
	 * @version 0.1 2012-3-21
	 * @param User
	 * @date 2012-3-21
	 * @return boolean
	 * @update 2012-3-21 下午1:52:07
	 */
	@SuppressWarnings(SERIAL)
	public boolean isUserLogin(User user) {
		if (null == user || null == user.getId() || "".equals(user.getId())
				|| null == user.getEmpCode()
				|| null == user.getEmpCode().getDeptId()
				|| null == user.getEmpCode().getDeptId().getId()) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_USER_NOTLOGIN);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:判断订单来源是否为网络渠道：阿里巴巴、淘宝、有商、物流宝<br />
	 * </p>
	 * 
	 * @author Weill
	 * @version 0.1 2012-3-28
	 * @param order
	 *            订单实体
	 * @return boolean
	 */
	public static boolean isWebChannelOrder(Order order) {
		// 营业部、呼叫中心订单即为渠道订单
		String resource = order.getResource();
		// 增加呼叫中心合肥 2013-11-12
		if (Constant.ORDER_SOURCE_BUSINESS_DEPT.equalsIgnoreCase(resource)
				|| Constant.ORDER_SOURCE_ONLINE.equalsIgnoreCase(resource)
				|| OrderUtil.isCallCenter(resource)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * <p>
	 * Description:判断订单是否为呼中心订单<br />
	 * </p>
	 * 
	 * @author Weill
	 * @version 0.1 2012-3-28
	 * @param order
	 *            订单实体
	 * @return boolean
	 */
	public boolean isCallCenterOrder(Order order) {
		//如果订单或者订单来源为空，返回false
		if (null == order || null == order.getResource()) {
			return false;
		}
		// 如果渠道订单不在规定范围内，返回false
		// 增加呼叫中心合肥 2013-11-12
		if (this.isWebChannelOrder(order)) {
			return false;
		} else if (OrderUtil.isCallCenter(order.getResource())) {
			//如果如果订单来源为呼叫中心，返回true
			return true;
		}
		return false;
	}

	/**
	 * @description 判断一个但是是否是渠道单号.
	 * @author 潘光均
	 * @version 0.1 2012-4-24
	 * @param String
	 * @date 2012-4-24
	 * @return boolean
	 * @update 2012-4-24 上午10:56:17
	 */
	public boolean isChannelNumber(String orderNumber) {
		String sign = "ATYCWMK";
		return sign.indexOf(orderNumber.charAt(0)) >= 0;
	}

	/**
	 * 
	 * @description 订单新增修改是否需要验证信息完整性.
	 * @author 安小虎
	 * @version 0.1
	 * @param 订单来源
	 * @date 2012-5-8
	 * @return true|false
	 * @update 2012-5-8 下午3:51:17
	 */
	public boolean isNeedCheck(String orderSource) {
		List<String> list = new ArrayList<String>();
		list.add(Constant.ORDER_SOURCE_CALLCENTER);
		// 增加呼叫中心合肥 2013-11-12
		list.add(Constant.ORDER_SOURCE_CALLCENTER_HF);
		list.add(Constant.ORDER_SOURCE_BUSINESS_DEPT);

		if (orderSource != null && !"".equals(orderSource)
				&& list.contains(orderSource.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * @description 官网下单需要校验渠道单号不能为空.
	 * @author 潘光均
	 * @version 0.1 2012-5-7
	 * @param String
	 * @date 2012-5-7
	 * @return boolean
	 * @update 2012-5-7 下午2:07:48
	 */
	@SuppressWarnings(SERIAL)
	public boolean isChannelNumberNull(String channelNumber) {
		//验证渠道编号
		if (!isObjectNotNull(channelNumber)) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_CHANNELNUMBER_MUST);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return true;
	}

	/**
	 * @description 官网下单需要校验渠道单号不能为空.
	 * @author 潘光均
	 * @version 0.1 2012-5-7
	 * @param String
	 * @date 2012-5-7
	 * @return boolean
	 * @update 2012-5-7 下午2:07:48 
	 */
	@SuppressWarnings(SERIAL)
	public boolean isOrderChannelModify(User user, Order order) {
		// 增加呼叫中心合肥 2013-11-12
		if (!user.getId().equals("DP0000")
				&& !order.getResource().equals(
						Constant.ORDER_SOURCE_BUSINESS_DEPT)
				&& !OrderUtil.isModifyChannel(order.getResource())
				&& !OrderUtil.isCallCenter(order.getResource())) {
			OrderException e1 = new OrderException(
					OrderExceptionType.CANNOT_MODITY_WEBORDER);
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}
		return true;
	}
	/**
	 * @description 订单备注提交不允许为空.
	 * @author 钟琼
	 * @version 0.1 2012-10-12
	 * @param log
	 * @return boolean
	 */
	@SuppressWarnings(SERIAL)
	public boolean checkOpeLogIsNull(OrderOperationLog log) {
		//验证联系人
		if(StringUtils.isBlank(log.getContactName())){//联系人为空
			OrderException e1 = new OrderException(
					OrderExceptionType.CANNOT_ISNULL_CONCATNAME);
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}
		//验证订单ID
		if(StringUtils.isBlank(log.getOrderId())){//订单id为空
			OrderException e1 = new OrderException(
					OrderExceptionType.CANNOT_ISNULL_ORDERID);
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}
		//验证备注内容
		if(StringUtils.isBlank(log.getOperatorContent())){//备注内容为空
			OrderException e1 = new OrderException(
					OrderExceptionType.CANNOT_ISNULL_OPERATIONCONTENT);
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}
		return true;
	}
		/**
	 * @description 检验订单是否可以延迟.
	 * @author kuang
	 * @version 0.1 2013-9-3
	 * @param Order
	 * @return boolean	
	 */
	@SuppressWarnings(SERIAL)
	public boolean validateOrderDelayable(Order order,User user) {
		List<String> status = new ArrayList<String>();
		status.add(Constant.ORDER_STATUS_ACCEPT);
		status.add(Constant.ORDER_STATUS_WAIT_ACCEPT);
		if (!isDoableStatus(order.getOrderStatus(), status)) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_CANNOT_DELAY);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		
		//如果是呼叫中心订单，只能由受理部门或者400订单管理组修改
		
			/*		修改人：kuang
			修改时间：2013-9-11
			修改内容：验证呼叫中心订单修改权限
			修改原因：除订单管理组外，话务员也能修改订单*/
		String acceptDeptId = user.getEmpCode().getDeptId().getId();
		// 增加呼叫中心合肥 2013-11-12
		if(!user.getId().equals("DP0000")){
			if (isRoleExist(user, Constant.ROLEID_CALLCENTER_DELAY)
					&& OrderUtil.isCallCenter(order.getResource())) {
				return true;
			}
			
			if(!acceptDeptId.equals(order.getAcceptDept())){
				OrderException e = new OrderException(
						OrderExceptionType.LIMIT_DELAY);
				throw new GeneralException(e.getErrorCode(),e.getMessage(),e,
						new Object[]{}) {
					};
			}
		}	
		
/*		if(!Constant.ORDER_SOURCE_CALLCENTER.equalsIgnoreCase(oResource)){

			OrderException e = new OrderException(
					OrderExceptionType.CANNOT_DELAY_NOCENTER);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		
		}*/
		return true;
	}
	/**
	 * @description 检查是否同步订单状态.
	 * @author huangzhanming
	 * @version 0.1 2013-9-3
	 * @param orderStatus
	 * @param resource
	 * @return boolean
	 */
	public boolean validateOrderNeedSync(String orderStatus, String resource) {
		List<String> status = new ArrayList<String>();
		status.add(Constant.ORDER_STATUS_REJECT);
		status.add(Constant.ORDER_STATUS_CANCEL);
		status.add(Constant.ORDER_SATUTS_FAILGOT);
		if (isDoableStatus(orderStatus, status)
				&& Constant.ORDER_SOURCE_ONLINE.equalsIgnoreCase(resource)) {
			return true;
		} else {
			return false;
		}
	}
		/**
	 * 
	 * <p>
	 * Description:验证用户某角色ID是否存在<br />
	 * </p>
	 * 
	 * @author kuang
	 * @version 0.1 2013-9-11 
	 * @return
	 */
	public boolean isRoleExist(User user,String roleId){
		Set<String> roleIds=user.getRoleids();
		if(user.getId().equals("DP0000")){
			return true;
		}else{
			if (roleIds.contains(roleId)) {
				return true;
			}else{
				return false;
			}
		}
	}
	/**
	 * 
	 * @description 试点城市订单撤消状态验证.
	 * @author kuang
	 * @param order
	 * @date 2012-7-29
	 * @return true|false
	 *
	 */
	@SuppressWarnings(SERIAL)
	public boolean isCancelPolitOrderStatus(Order order,User user) {
		// 构造可撤消的订单状态集合：已约车
		List<String> statusList = new ArrayList<String>();
		statusList.add(Constant.ORDER_SATUTS_SHOUTCAR);

		// 验证状态
		if (!this.isDoableStatus(order.getOrderStatus(), statusList)) {
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_CANNOT_CANCEL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		/*		修改人：kuang
		修改时间：2013-9-11
		修改内容：验证只能撤销呼叫中心订单
		修改原因：话务组只能撤销呼叫中心的订单*/
		// 增加呼叫中心合肥 2013-11-12
		if(!user.getId().equals("DP0000")){
			if (isRoleExist(user, Constant.ROLEID_CALLCENTER_CANCEL)
					&& !OrderUtil.isCallCenter(order.getResource())) {
					OrderException e = new OrderException(
							OrderExceptionType.ONLYCANCEL_CALLCENTER);
					throw new GeneralException(e.getErrorCode(),e.getMessage(),e,
							new Object[]{}) {
					};
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @description 是否为呼叫中心或者官网的快递订单.
	 * @author kuang
	 * @param order
	 * @date 2012-7-29
	 * @return true|false
	 *
	 */
	@SuppressWarnings(SERIAL)
	public boolean isOnline(String orderSource) {
		//如果订单是官网返回true
		if (Constant.ORDER_SOURCE_ONLINE.equalsIgnoreCase(orderSource)) {
			return true;
		} else {
			//如果不是，抛出异常
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_ONLYCANCEL_ONLINE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	
	/**
	 * 
	 * @description 当运输方式选择为“经济快递”，保价金额或者代收货款大于20000时，点击提交，提示“快递保价和代收货款均不能超过20000”
	 * @author 张斌
	 * @param transportMode 运输方式 insuredAmount报价金额
	 * @date 2013-9-29
	 * @return true|false
	 *
	 */
	@SuppressWarnings(SERIAL)
	public boolean sureAcountPackage(String transportMode,Double insuredAmount,Double reviceMoneyAmount) {
		if(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE.equals(transportMode)
				&&insuredAmount!=null
				&&insuredAmount>20000){
			//如果不是，抛出异常
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_SUREACOUNT_PACKAGE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}else if(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE.equals(transportMode)
				&&reviceMoneyAmount!=null
				&&reviceMoneyAmount>20000){
			//如果不是，抛出异常
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_SUREACOUNT_PACKAGE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		else{
			return true;
		}
	}

}
