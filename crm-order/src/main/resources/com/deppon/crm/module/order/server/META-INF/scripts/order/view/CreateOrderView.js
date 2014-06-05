/**.
 * <p>
 * 将CommonOrderView中的元素组合成订单页面<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-08
 * </p>
 */

//设置Data层对象
var orderbaseDataControl =  (ORDERCONFIG.get("TEST"))? new OrderDataTest():new OrderData();
/**.
 * <p>
 * 获取业务字典数据</br>
 * </p>
 * @author  张斌
 * @时间    2012-03-13
 */
Ext.onReady(function(){
				var keys = [//签收单
	                                 'SIGNBILL',
	                                 //送货方式
	                                 'PICKGOODTYPE',
	                                 //运输类型
	                                 'TRANS_TYPE',
	                                 //付款方式
	                                 'PAY_WAY',
	                                 //客户等级
	                                 'MEMBER_GRADE',
	                                 //偏好地址类型
	                                 'ADDRESS_TYPE'
	                    		];
initDataDictionary(keys);
initDeptAndUserInfo();
dataDictionary=DataDictionary;
Ext.QuickTips.init();
	Ext.create(
		'Ext.container.Viewport',{
		autoDestroy : true,
//		layout : 'fit',
		autoScroll:true,
        items:[new CreateOrderView()]
	});
});

Ext.form.Field.prototype.msgTarget='side';