/**.
 * <p>
 * 订单MODEL<br/>
 * <p>
 * @returns {OrderModel}
 */
Ext.define('OrderModel', {
    extend: 'Ext.data.Model',
    fields : [{
    	    //唯一标识
			name : ORDERNAME.get('id')
		},{
			//创建时间
			name : ORDERNAME.get('createDate'),
			 type: 'Date',
			 defaultValue:null,
			 convert: DButil.changeLongToDate
		},{
			//下单时间
			name: ORDERNAME.get('orderTime'),
			type: 'Date',
			defaultValue:null,
			convert: DButil.changeLongToDate
	   },	
		{
			//创建人
			name: ORDERNAME.get('createUser')
	   },{
    	    //修改时间
			name : ORDERNAME.get('modifyDate'),
			 type: 'Date',
			 defaultValue:null,
			 convert: DButil.changeLongToDate
		},{
    	    //修改人
			name : ORDERNAME.get('modifyUser')
		},{
    	    //催单次数
			name : ORDERNAME.get('hastenCount'),
			type:'int'
		},{
    	    //最后催单时间
			name : ORDERNAME.get('lastHastenTime'),
			type: 'Date',
			defaultValue:null,
			convert: DButil.changeLongToDate
		},{
    	    //联系人ID
			name : ORDERNAME.get('contactManId'),
			type:'int'
		},{
    	    //货物名称
			name : ORDERNAME.get('goodsName')
		},{
			//渠道客户id--下单人
			name : 'channelCustId'
		},{
    	    //货物总件数
			name : ORDERNAME.get('goodsTotalNumber')
//			type:'int'
		},{
    	    //货物总重量
			name : ORDERNAME.get('goodsTotalWeight')
//			type:'float'
		},{
			//货物总体积
			name : ORDERNAME.get('goodsTotalVolume')
//			type:'float'
		},{
			//包装材料
			name: ORDERNAME.get('goodsPackagingMaterials')
	   },{
    	    //货物类型
			name : ORDERNAME.get('goodsType')
		},{
    	    //保价金额
			name : ORDERNAME.get('insuerAmount')
//			type:'int'
		},{
    	    //即日送或三日送、代收货款类型
			name : ORDERNAME.get('sendDay')
		},{
    	    //代收货款
			name : ORDERNAME.get('codAmount'),
			type:'int'
		},{
    	    //签收单
			name : ORDERNAME.get('signDocuments')
		},{
    	    //运输方式
			name : ORDERNAME.get('modeOfTransportation')
		},{
    	    //送货方式
			name : ORDERNAME.get('deliveryWay')
		},{
    	    //付款方式
			name : ORDERNAME.get('paymentWay')
		},{
			//短信通知
			name : ORDERNAME.get('textMessagesNotice')
		},{
			// 储运事项
			name: ORDERNAME.get('storageAndTransportationMatters')
	   },{
    	    //收货客户ID
			name : ORDERNAME.get('receiverCustId')
		},{
    	    // 收货客户联系姓名
			name : ORDERNAME.get('receivingName')
		},{
    	    //收货客户名称
			name : ORDERNAME.get('receivingCustName')
		},{
    	    //收货客户编号
			name : ORDERNAME.get('receivingCustCode')
		},{
    	    //收货客户所在省份1
			name : ORDERNAME.get('receivingProvince')
		},{
    	    //所在市1
			name : ORDERNAME.get('receivingCity')
		},{
    	    //所属区县1
			name : ORDERNAME.get('receivingCounty')
		},{
    	    //收货客户手机
			name : ORDERNAME.get('receivingMobilePhone')
		},{
			//收货客户电话
			name : ORDERNAME.get('receivingPhone')
		},{
			//收货客户详细地址1
			name: ORDERNAME.get('receivingAddress')
	   },{
			//到达网点
			name: ORDERNAME.get('receivingToPoint')
	   },{
    	    //发货客户ID
			name : ORDERNAME.get('shipperId')
			
		},{
    	    //发货人姓名1
			name : ORDERNAME.get('consignorName')
		},{
    	    //发货客户名称
			name : ORDERNAME.get('consignorCustName')
		},{
    	    //发货是否接货
			name : ORDERNAME.get('consignorIsCargo')
		},{
    	    //接货时间从1
			name : ORDERNAME.get('consignorCargoTime'),
			 type: 'Date',
			 defaultValue:null,
			 convert: DButil.changeLongToDate
		},{
    	    //接货时间到
			name : ORDERNAME.get('consignorToCargoTime'),
			 type: 'Date',
			 defaultValue:null,
			 convert: DButil.changeLongToDate
		},{
    	    //发货客户编号
			name : ORDERNAME.get('consignorCustCode')
		},{
    	    //发货方所在省份1
			name : ORDERNAME.get('consignorProvince')
		},{
    	    //所在市1
			name : ORDERNAME.get('consignorCity')
		},{
    	    //所在区县1
			name : ORDERNAME.get('consignorCounty')
		},{
    	    //发货方手机1
			name : ORDERNAME.get('consignorMobilePhone')
		},{
			//发货方联系电话1
			name : ORDERNAME.get('consignorPhone')
		},{
			//发货方详细地址1
			name: ORDERNAME.get('consignorAddress')
	   },{
    	    //始发网点
			name : ORDERNAME.get('consignorComeFromPoint')
		},{
	    	//订单号
			name :  ORDERNAME.get('orderNum')
		},{
	    	//运单号
			name :  ORDERNAME.get('waybillNum')
		},{
	    	//渠道单号1
			name :  ORDERNAME.get('channelNumber')
		},{
			//订单来源
			name :  ORDERNAME.get('resource')
		},{
		   // 受理部门1
			name :  ORDERNAME.get('acceptDept')
		},{
		   // 受理部门Name
			name :  ORDERNAME.get('acceptDeptName')
		},{
		   // 订单状态
			name :  ORDERNAME.get('orderStatus')
		},{
		   // 始发网点名称
			name :  ORDERNAME.get('startStationName')
		},{
		   // AL客户类型
			name :  ORDERNAME.get('memberType')
		},{
		   // 到达网点名称
			name :'receivingToPointName'
		},{
		   // 反馈信息
			name :'feedbackInfo'
		}]
});


/**.
 * <p>
 * 订单显示MODEL<br/>
 * <p>
 * @returns {OrderModelView}
 */
Ext.define('OrderModelView', {
    extend: 'Ext.data.Model',
    fields : [{
    	    //唯一标识
			name : ORDERNAME.get('id')
		},{
			//创建时间
			name : ORDERNAME.get('createDate')
		},{
			//创建人
			name: ORDERNAME.get('createUser')
	   },{
    	    //修改时间
			name : ORDERNAME.get('modifyDate')
		},{
    	    //修改人
			name : ORDERNAME.get('modifyUser')
		},{
    	    //催单次数
			name : ORDERNAME.get('hastenCount')
		},{
    	    //最后催单时间
			name : ORDERNAME.get('lastHastenTime')
		},{
    	    //联系人ID
			name : ORDERNAME.get('contactManId')
		},{
    	    //货物名称
			name : ORDERNAME.get('goodsName')
		},{
    	    //货物总件数
			name : ORDERNAME.get('goodsTotalNumber')
		},{
    	    //货物总重量
			name : ORDERNAME.get('goodsTotalWeight')
		},{
			//货物总体积
			name : ORDERNAME.get('goodsTotalVolume')
		},{
			//包装材料
			name: ORDERNAME.get('goodsPackagingMaterials')
	   },{
    	    //货物类型
			name : ORDERNAME.get('goodsType')
		},{
    	    //保价金额
			name : ORDERNAME.get('insuerAmount')
		},{
    	    //即日送或三日送、代收货款类型
			name : ORDERNAME.get('sendDay')
		},{
    	    //代收货款
			name : ORDERNAME.get('codAmount')
		},{
    	    //签收单
			name : ORDERNAME.get('signDocuments')
		},{
    	    //运输方式
			name : ORDERNAME.get('modeOfTransportation')
		},{
    	    //送货方式
			name : ORDERNAME.get('deliveryWay')
		},{
    	    //付款方式
			name : ORDERNAME.get('paymentWay')
		},{
			//短信通知
			name : ORDERNAME.get('textMessagesNotice')
		},{
			// 储运事项
			name: ORDERNAME.get('storageAndTransportationMatters')
	   },{
    	    //收货客户ID
			name : ORDERNAME.get('receiverCustId')
		},{
    	    // 收货客户联系姓名
			name : ORDERNAME.get('receivingName')
		},{
    	    //收货客户名称
			name : ORDERNAME.get('receivingCustName')
		},{
    	    //收货客户编号
			name : ORDERNAME.get('receivingCustCode')
		},{
    	    //收货客户所在省份1
			name : ORDERNAME.get('receivingProvince')
		},{
    	    //所在市1
			name : ORDERNAME.get('receivingCity')
		},{
    	    //所属区县1
			name : ORDERNAME.get('receivingCounty')
		},{
    	    //收货客户手机
			name : ORDERNAME.get('receivingMobilePhone')
		},{
			//收货客户电话
			name : ORDERNAME.get('receivingPhone')
		},{
			//收货客户详细地址1
			name: ORDERNAME.get('receivingAddress')
	   },{
			//到达网点
			name: ORDERNAME.get('receivingToPoint')
	   },{
    	    //发货客户ID
			name : ORDERNAME.get('shipperId')
			
		},{
    	    //发货人姓名1
			name : ORDERNAME.get('consignorName')
		},{
    	    //发货客户名称
			name : ORDERNAME.get('consignorCustName')
		},{
    	    //发货是否接货
			name : ORDERNAME.get('consignorIsCargo')
		},{
    	    //接货时间从1
			name : ORDERNAME.get('consignorCargoTime')
		},{
    	    //接货时间到
			name : ORDERNAME.get('consignorToCargoTime')

		},{
    	    //发货客户编号
			name : ORDERNAME.get('consignorCustCode')
		},{
    	    //发货方所在省份1
			name : ORDERNAME.get('consignorProvince')
		},{
    	    //所在市1
			name : ORDERNAME.get('consignorCity')
		},{
    	    //所在区县1
			name : ORDERNAME.get('consignorCounty')
		},{
    	    //发货方手机1
			name : ORDERNAME.get('consignorMobilePhone')
		},{
			//发货方联系电话1
			name : ORDERNAME.get('consignorPhone')
		},{
			//发货方详细地址1
			name: ORDERNAME.get('consignorAddress')
	   },{
    	    //始发网点
			name : ORDERNAME.get('consignorComeFromPoint')
		},{
    	    //始发网点名称
			name : ORDERNAME.get('startStationName')
		},{
	    	//订单号
			name :  ORDERNAME.get('orderNum')
		},{
	    	//运单号
			name :  ORDERNAME.get('waybillNum')
		},{
	    	//渠道单号1
			name :  ORDERNAME.get('channelNumber')
		},{
			//订单来源
			name :  ORDERNAME.get('resource')
		},{
		   // 受理部门1
			name :  ORDERNAME.get('acceptDept')
		},{
		   // 订单状态
			name :  ORDERNAME.get('orderStatus')
		}]
});

/**.
 * <p>
 * 网点信息MODEL<br/>
 * <p>
 * @returns {PointModel}
 */
Ext.define('PointModel', {
    extend: 'Ext.data.Model',
    fields : [{
    	    //网点（主键）
			name : ORDERNAME.get('id')
		},{
    	    //组织ID
			name : 'organizeId'
		},{
			//网店名称
			name : ORDERNAME.get('pointName')
		}	      /**			
		    * 修改人：张斌
		*修改时间：2013-7-30 22:30
		*原有内容：无（新增）
		*修改原因：在model中加入省市区县名称字段
	  */
		//begin
		,{
			//省份名称
			name : 'provinceName'
		},{
			//城市名称
			name : 'cityName'
		},{
			//区县名称
			name : 'regionName'
		}
		//end
		,{
			//是否AB货
			name: ORDERNAME.get('ifHashGoodsType')
	   },{
			//是否外发
			name: ORDERNAME.get('ifOutward')
	   },{
			//是否pda
			name: ORDERNAME.get('ifPDA')
	   },{	//是否pda
		    name: ORDERNAME.get('pointCode')
	   },{
		   	name: 'ifHomeDelivery'
	   },{
		   	name: 'ifSelfDelivery'
	   }]
});
/**.
 * <p>
 * 用户操作MODEL<br/>
 * <p>
 * @returns {OrderOperationLogModel}
 */
Ext.define('OrderOperationLogModel', {
    extend: 'Ext.data.Model',
    fields : [{
    	// 发货联系人名称
		name : ORDERNAME.get('consignorName')
	},{
		// 操作类型
		name : ORDERNAME.get('operatorType')
	},{
		// 操作人ID
		name: ORDERNAME.get('operatorId')
    },{
	   // 操作人所属组织ID
		name : ORDERNAME.get('operatorOrgId')
	},{	   
		// 订单ID
		name : ORDERNAME.get('orderId')
	},{	   
		// 操作内容		
		name : ORDERNAME.get('operatorContent')
	},{
		// 操作时间
		name : ORDERNAME.get('operatorTime')
	}]
});
/**.
 * <p>
 * 约车MODEL<br/>
 * <p>
 * @returns {VehicleModel}
 */
Ext.define('VehicleModel', {
    extend: 'Ext.data.Model',
    fields : [{
    	//派车车队
		name : ORDERNAME.get('vehicleTeam')
	},{
		//用车部门
		name : ORDERNAME.get('useVehicleDept')
	},{
		//用车类型
		name: ORDERNAME.get('vehicleType')
    },{
    	//是否尾板
		name : ORDERNAME.get('isTailBoard')
	},{	   
		//约车编号
		name : ORDERNAME.get('bookVehicelNumber')
	}]
});

/**.
 * <p>
// * 运单MODEL<br/>
 * <p>
 * @returns {WaybillInfo}
 */
Ext.define('WaybillModel', {
    extend: 'Ext.data.Model',
    fields : [{
    	//运单号
		name : 'waybillNumber'
	},{
		//发货客户联系人姓名
		name : 'shipperContact'
	},{
		//发货联系人手机
		name : 'shipperPhone'
	},{
		//发货联系人固定电话：区号-主机号码-分机号
		name : 'shipperTelephone'
    },{	   
		//发货人地址
		name : 'shipperAddress'
	}]
});