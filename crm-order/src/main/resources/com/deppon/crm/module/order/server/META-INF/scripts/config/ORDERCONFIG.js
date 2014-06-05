/**.
 * <p>
 * 存放全局变量（1）控制页面调用那个DATA.JS（2）定义字段名称的全局变量<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-08
 * </p>
 */

/**.
 * <p>
 * 规范如此要求，判断'file'设置TEST为true/false<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-08
 * </p>
 */
ORDERCONFIG = (function(){
	var constants = {
			'TEST':false
	};
	if(window.location.protocol === 'file:')
	{
		constants.TEST = true;
	}
	
	return {
		get:function(name) {return constants[name];}
	};
})();


/**.
 * <p>
 * 如果要修改name或者发现与后台定义的不一样，那么在这里修改一次就OK，不用再区代码里面找很多很多了<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-08
 * </p>
 */
var ORDERNAME = (function() {
    var constants = {
    		//联系人列表
    		'contactList':'contactList',
            //唯一标示
            'id':'id',
            //创建时间
            'createDate':'createDate',
            //创建人
            'createUser':'createUser',
            //下单时间
            'orderTime':'orderTime',
            //修改时间
            'modifyDate':'modifyDate',
            //修改人
            'modifyUser':'modifyUser',
            //催单次数
            'hastenCount':'hastenCount',
            //最后催单时间
            'lastHastenTime':'lastHastenTime',
            //联系人ID
            'contactManId':'contactManId',
            //--------------行政区域------------
            //区域code
            'regionCode':'regionCode',
            //区域名称
            'regionName':'regionName',
            //区域级别
            'regionLevel':'regionLevel',
             //父区域编号
            'parentCode':'parentCode',
            //------------------------------
            //货物名称
            'goodsName':'goodsName',
            //货物总件数
            'goodsTotalNumber':'goodsNumber',
            //货物总重量
            'goodsTotalWeight':'totalWeight',
            //货物总体积
            'goodsTotalVolume':'totalVolume',
            //包装材料
            'goodsPackagingMaterials':'packing',
            //货物类型
            'goodsType':'goodsType',
            //货物类型
            'memberType':'memberType',
            
            
            //保价金额
            'insuerAmount':'insuredAmount',
            //即日送/三日送
            'sendDay':'reciveLoanType',
            //代收货款
            'codAmount':'reviceMoneyAmount',
            //签收单
            'signDocuments':'returnBillType',
            //运输方式
            'modeOfTransportation':'transportMode',
            //送货方式
            'deliveryWay':'deliveryMode',
            //付款方式
            'paymentWay':'paymentType',
            //短信通知
            'textMessagesNotice':'isSendmessage',
            //储运事项????
            'storageAndTransportationMatters':'transNote',
            
            //收货客户ID
            'receiverCustId':'receiverCustId',
            // 收货客户联系姓名
            'receivingName':'receiverCustName',
            //收货方客户名称
            'receivingCustName':'receiverCustcompany',
            //收货方客户编号
            'receivingCustCode':'receiverCustNumber',
            //收货方省份
            'receivingProvince':'receiverCustProvince',
            //收货方所在市
            'receivingCity':'receiverCustCity',
            //收货方所区县
            'receivingCounty':'receiverCustArea',
            //收货人手机
            'receivingMobilePhone':'receiverCustMobile',
            //收货方电话
            'receivingPhone':'receiverCustPhone',
            //收货方电话号码区号???
            'receivingAreaCode':'receivingAreaCode',
            //收货方电话分机号????
            'receivingExtension':'receivingExtension',
            //收货方地址
            'receivingAddress':'receiverCustAddress',
            //收货方到达网点(实际为Name)//提货网点
            'receivingToPoint':'receivingToPoint',
            
            
            //发货客户ID
            'shipperId':'shipperId',
            //发货人姓名
            'consignorName':'contactName',
            //发货方客户名称
            'consignorCustName':'shipperName',
            //发货方客户编号
            'consignorCustCode':'shipperNumber',
            //发货方省份
            'consignorProvince':'contactProvince',
            //发货方所在市
            'consignorCity':'contactCity',
            //接货时间from
            'consignorCargoTime':'beginAcceptTime',
            //接货时间to
            'consignorToCargoTime':'endAcceptTime',
            //发货是否上门接货
            'consignorIsCargo':'isReceiveGoods',
            //发货方所区县
            'consignorCounty':'contactArea',
            //发货人手机
            'consignorMobilePhone':'contactMobile',
            //发货方电话
            'consignorPhone':'contactPhone',
            //发货方电话号码区号???
            'consignorAreaCode':'consignorAreaCode',
            //发货方电话分机号???
            'consignorExtension':'consignorExtension',
            //发货方地址
            'consignorAddress':'contactAddress',
            //发货方始发网点(ID)
            'consignorComeFromPoint':'startStation',
            //始发网点名称
            'startStationName':'startStationName',
            //是否与接货联系人一直
            'isSameName':'isSameName',
            //受理部门
            'acceptDept':'acceptDept',
            // 受理部门名称
        	'acceptDeptName':'acceptDeptName',
            
             //网点名称
            'pointName':'deptName',
             //网点名称
            'pointCode':'deptCode',
             //是否AB货
            'ifHashGoodsType':'ifHashGoodsType',
            //反馈信息
            'feedbackInfo':'feedbackInfo',
            //-------------------------操作记录---------------------------------------
            //发货联系人姓名136行
            //操作类别
            'operatorType':'operatorType',
            //操作人ID类别
            'operatorId':'operatorId',
            //操作人所属组织ID
            'operatorOrgId':'operatorOrgId',
            // 订单ID
            'orderId':'orderId',
            //操作内容
            'operatorContent':'operatorContent',
            //操作时间
            'operatorTime':'operatorTime',
            //-------------------------受理拒绝--------------------------------
            //反馈信息
            'returnInfo':'returnInfo',
            //订单来源
            'resource':'resource',
            //订单状态
            'orderStatus':'orderStatus',
            //运单号
            'waybillNum':'waybillNumber',
             // 订单号
        	'orderNum':'orderNumber',
        	// 渠道单号
        	'channelNumber':'channelNumber',
        	//起始时间
        	'startDate':'startDate',
        	//结束时间
        	'endDate':'endDate',
            //发货人手机154行
            //-------------------------约车--------------------------------
            //派车车队
        	'vehicleTeam':'vehicleTeam',
        	//用车部门
        	'useVehicleDept':'useVehicleDept',
        	//用车的类型
        	'vehicleType':'vehicleType',
        	//是否尾板
        	'isTailBoard':'isTailBoard',
        	//约车编号
        	'bookVehicelNumber':'bookVehicelNumber',
        	//是否外发(否自有网点)
            'ifOutward':'ifOutward',
        	//是否PDA
            'ifPDA':'ifHavePDA',
            //erpId
            'erpId':'erpId'
            	
           
        };

        return {
           get: function(name) { return constants[name]; }
       };
   })();
