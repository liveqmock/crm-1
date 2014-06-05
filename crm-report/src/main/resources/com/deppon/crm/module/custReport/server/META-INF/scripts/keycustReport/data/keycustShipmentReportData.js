//走货量store
Ext.define('ShipmentAmountStore',{
	pageSize : 10,
	extend:'Ext.data.Store',
	model:'ShipmentAmountModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../custReport/queryShipmentAmount.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'shipmentAmountList',
			totalProperty : 'totalCount'
		}
	}
});
//产品货量Store
Ext.define('ProductAmountStore',{
	pageSize : 10,
	extend:'Ext.data.Store',
	model:'ProductAmountModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../custReport/queryProductAmount.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'productAmountList',
			totalProperty : 'totalCount'
		}
	}
});
//线路货量Store
Ext.define('RoadAmountStore',{
	pageSize : 10,
	extend:'Ext.data.Store',
	model:'RoadAmountModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../custReport/queryRoadAmount.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'roadAmountList',
			totalProperty : 'totalCount'
		}
	}
});
//线路货量Store
Ext.define('ShipmentAgingStore',{
	pageSize : 10,
	extend:'Ext.data.Store',
	model:'ShipmenAgingModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../custReport/queryShipmentAging.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'shipmentAgingList',
			totalProperty : 'totalCount'
		}
	}
});
//走货质量Store
Ext.define('ShipmentQualityStore',{
	pageSize : 10,
	extend:'Ext.data.Store',
	model:'ShipmentQualityModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../custReport/queryShipmentQuality.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'shipmentQualityList',
			totalProperty : 'totalCount'
		}
	}
});
//走货质量Store for chart
Ext.define('ShipmentQualityForChartStore',{
	pageSize : 10,
	extend:'Ext.data.Store',
	model:'ShipmentQualityForChartModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../custReport/queryShipmentQualityForChart.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'shipmentQualityForChartList',
			totalProperty : 'totalCount'
		}
	}
});