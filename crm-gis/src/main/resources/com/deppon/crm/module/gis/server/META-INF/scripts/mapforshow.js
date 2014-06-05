function CRM_BMap(div, option, callbackFun){
	this.map = null;
	this.option = option;
	this.div = div;
	this.callbackFun = callbackFun;
	this.initMap(div, option, callbackFun);
};
CRM_BMap.common = {
		getDomById : function (id){
			return document.getElementById(id);
		},
		markerHtml: function(param,type){
			if('poi'==type){
				return "<div id='poiId' class='iw-con'><ul><li><p>"+param+"</p></li><li class='btnbox'><input id='inputid' type = 'button' class='btn-poi' value='使用该地址标注' /></li></ul></div>";
			}else if('dept'==type){
				return "<div id='deptwinID'><p  class='tac'>"+param+"</p></div>";
			}
		},
		/**
		 * 把点坐标字符串转化为double类型的点数组
		 */
		stringConvertArray:function(strs){
			if(strs){
				var points=[];
				var array = strs.split(",");
				for(var i=0;i<array.length;i++){
					if(i%2==0){
						var point = new BMap.Point(array[i],array[i+1]);
						points.push(point);
					}
				}
				return points;
			}
		} ,
		/*
		 *	将对象转换成坐标数组
		 */
		object2pointArray : function (coordinates) {
			var me = this;
			var points = [];
			for (var i = 0; i < coordinates.length; i++) {
				points.push(me.stringConvertArray(coordinates[i].baidu));
			}
			return points;
		},
		/*
		 *	参数编码
		 */
		encodeParam : function (id) {
			if (id) {
				return (encodeURIComponent(encodeURIComponent(id)));
			} else {
				return "";
			}
		},
		DPMAP_URL : '..',

		POSTMETHOD : "POST",
		GETMETHOD : "GET",
		// 创建操作对象
		createXmlHttp : function () {
			var xmlHttp=null;
			if (window.ActiveXObject) { // IE下
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} else if (window.XMLHttpRequest) { // 其他浏览器如火狐
				xmlHttp = new XMLHttpRequest();
			}
			return xmlHttp;
		},
		send : function (param) {
			var callback = param.callback;
			var url = param.url;
			var paramValue = param.param;
			var method = param.method;
			var async = param.async;
			var xmlHttp = CRM_BMap.common.createXmlHttp();

			xmlHttp.open(method, url, async);
			if (CRM_BMap.common.POSTMETHOD == method) {
				xmlHttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			}

			xmlHttp.onreadystatechange = function () { {
					if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
						if (callback) {
							callback(xmlHttp.responseText);
						}
					}
				}
			};
			xmlHttp.send(paramValue); // 发送请求
		},

		/*
		 * param 格式： { url:"http://www.baidu.com",//url param:"age=1&name=2",//参数
		 * async:true,//是否同步 callback:callbackFunction//回调函数 }
		 */
		// Post传参
		doPost : function (param) {
			param.method = CRM_BMap.common.POSTMETHOD;
			CRM_BMap.common.send(param);
		},

		/*
		 * param 格式： { url:"http://www.baidu.com",//url param:"age=1&name=2",//参数
		 * async:true,//是否同步 callback:callbackFunction//回调函数 }
		 */
		// Get传参
		doGet : function (param) {
			param.method = CRM_BMap.common.GETMETHOD;
			CRM_BMap.common.send(param);
		}


};
CRM_BMap.prototype = {
		/**
		 * 073102-Tommy Wu
		 * 地图api加载
		 * time：2013-04-24
		 */
		initMap: function(div, option, callbackFun){
			var me  = this;
			CRM_BMap.baiduCallBack = function() {
				me.showMap(div, option, callbackFun);
			};
			//加载百度地图api
			me.loadApi('$baidu', 'http://api.map.baidu.com/api?v=1.4&callback=CRM_BMap.baiduCallBack');
			
		},
		/**
		 * 073102-Tommy Wu
		 * 地图初始化
		 */
		showMap:function(div, option, callbackFun){
			/*
			 * 初始化地图
			 */
			var map, center, zoomTypeIn;
			map = new BMap.Map(div); // 创建Map实例
			center = option.center; // 地图中心点
			zoomTypeIn = option.zoom;//地图缩放级别
			map.centerAndZoom(center, zoomTypeIn); // 设置地图中心点及缩放级别
			map.enableScrollWheelZoom(); // 启动滚轮放大和缩小
			map.enableKeyboard(); // 启用键盘操作
			map.disableDoubleClickZoom(); // 禁止地图双击放大
			map.enableInertialDragging(); // 启用地图惯性拖拽
			map.enableContinuousZoom(); // 启用地图连续缩放效果
			map.addControl(new BMap.NavigationControl()); // 添加地图导航控件
			this.map = map;
			if(this.callbackFun){
				eval(this.callbackFun)(map);
			}
		},
		/**
		 * 073102-Tommy Wu
		 * 加载api方法
		 * time：2013-04-24
		 */
		loadApi: function(scriptId, scriptUrl) {
			if(!document.getElementById(scriptId)) {
				var script = document.createElement("script");
				script.type = "text/javascript";
				script.id = scriptId;
				script.src = scriptUrl;
				document.getElementsByTagName("head")[0].appendChild(script);
			}
			
		},
		/**
		 * 点服务
		 */
		PointFeature: function(map, option){
			return new BaiduPointFeature(map, option);
		},
		/**
		 * 点服务
		 */
		PolygonFeature: function(map, option){
			return new BaiduPolygonFeature(map, option);
		}
};

/**
 * 073102-Tommy Wu
 * 百度范围展示服务类。
 * time：2013-05-02
 */
function BaiduPolygonFeature(map, option) {
	this.map = map;
	this.polygonOption = option;
}
BaiduPolygonFeature.prototype = {
		/*
		 * 根据范围编码，查询范围信息	
		 */
		showPolygonById: function(id){
			var me = this,
			idParam = CRM_BMap.common.encodeParam(id);
			CRM_BMap.common.doPost({
				url : CRM_BMap.common.DPMAP_URL + "/gis/selectPolygonById.action", //url
				param : 'id=' + idParam, //参数
				async : true, //是否同步
				callback : function (result) {
					var data = eval("(" + result + ")");
					if (data.success) {
						if(data.polygonEntity){
							var netShape = CRM_BMap.common.stringConvertArray(data.polygonEntity.baidu); //double类型的数组,转化为坐标数组
							var polygon = new BMap.Polygon(netShape,{strokeColor:"#373c64",strokeWeight:1,strokeOpacity:0.5,fillColor:"#373c64",fillOpacity:0.2});
							var showPoint = polygon.getBounds().getCenter();//得到范围的几何中心点;
							var descLabel = new BMap.Label(data.polygonEntity.name);
							descLabel.setPosition(showPoint);
							me.map.addOverlay(descLabel);
							me.map.addOverlay(polygon);
							me.map.setCenter(showPoint);
							me.map.setZoom(12);
						}
					}else{
						console.log("系统内部错误");
					}
				}
			});
		
		}
};
/**********************************************************
 * 073102-Tommy Wu
 * 百度点展示服务类。
 * time：2013-05-02
 * *********************************************************/

function BaiduPointFeature(map, option){
	this.map = map;
	this.pointOption = option;
	this.callbackFun = option.callbackFun;
	this.driving = "";
	this.drivingCallback ='';
	this.poiArr = [];
	this.customerArr = [];
	this.clickCallFun = "";
	this.infoWindow= "";
	this.poiCallbackFun="";
}
BaiduPointFeature.prototype ={
	/**
	 * 073102-Tommy Wu
	 * 根据部门的标杆编码，查询部门信息
	 * time：2013-04-24
	 */
	getDeptByStandercode: function(list,callbackFun) {
		var idListParam='';
		for ( var i = 0; i < list.length; i++) {
			idListParam =idListParam+'standercodeList='+ encodeURIComponent(encodeURIComponent(list[i]))+'&';
	    }
		idListParam =  idListParam.substring(0, idListParam.length-1);
		CRM_BMap.common.doPost({
			url : CRM_BMap.common.DPMAP_URL + "/gis/selectDeptByStanderCode.action", //url
			param :  idListParam, //参数
			async : true, //是否同步
			callback : function (data) {
				var result = eval("(" + data + ")");
				if(result.success){
					if(callbackFun){
						if(result.pointEntityList){
							eval(callbackFun)(result.pointEntityList);
						}
					}
				}else{
					if(callbackFun){
						eval(callbackFun)([]);
					}
					console.log("系统内部错误");
				}
			}
		});
	},
	/**
	 * 073102-Tommy Wu
	 * 根据部门的标杆编码，展示部门信息到地图上。
	 * time：2013-04-24
	 */
	showDeptMarker:function(standerCode,type){
		var me = this;
		function callBackFun(data){
			if(data.length>0){
				if(data[0].baiduLng && data[0].baiduLat){
					var img;
					if(type){
						img = me.getCustomerImgType(type);
					}else{
						img = 'deppon';
					}
					var point= new BMap.Point(data[0].baiduLng,data[0].baiduLat);
					var icon = new BMap.Icon(CRM_BMap.common.DPMAP_URL + "/images/gis/"+img+".png", new BMap.Size(22,29));
					
					var marker = new BMap.Marker(point,{icon:icon});
					me.map.addOverlay(marker);
					me.map.setCenter(point);
					me.map.setZoom(15);
					me.addDeptMarkerClick(marker,data[0].name,point);
				}
			}
		}
		this.getDeptByStandercode([standerCode],callBackFun);
	},
	/**
	 * 073102-Tommy Wu
	 * 根据部门的标杆编码和客户坐标，展示导航距离。
	 * time：2013-04-24
	 */
	showCustToDeptDistance: function(lng,lat,deptCode,callback) {
		var me  = this;
		function callBFun(data){
			if(data.length>0){
				if(data[0].baiduLng && data[0].baiduLat){
					var endP= new BMap.Point(data[0].baiduLng,data[0].baiduLat);
					var startP = new BMap.Point(lng,lat);
					me.baiduDriving(startP,endP,callback);
				}
			}else{
				if(callback){
					eval(callback)("");
				}
			}
		}
		this.getDeptByStandercode([deptCode],callBFun);
	},
	/**
     * 073102-Tommy Wu
     * 根据坐标求导航
     * time：2013-05-06
	 */
	baiduDriving:function(startP,endP,callback){
		var me= this;
		this.drivingCallback = function (results){
			if( results.getPlan(0) == undefined) {
				if(callback){
					eval(callback)("");
				}
				return;
			}
			if(results.getPlan(0).getDistance(false)){// 如果有距离返回
				if(callback){
					eval(callback)(results.getPlan(0).getDistance(false));
				}
			}
		}
		if(this.driving!=""&&this.driving!=null&&this.driving!=undefined){
			this.driving.search(startP, endP);
		}else{
			this.driving = new BMap.DrivingRoute(this.map, 
					{renderOptions : {
						map:this.map,
						autoViewport : true
					},drivingPolicy:BMAP_DRIVING_POLICY_AVOID_HIGHWAYS,
					onSearchComplete: function(results){
						me.drivingCallback(results);
					}
					});
			this.driving.search(startP, endP);
			
		}
	},
	
	 /**
	  * 073102-Tommy Wu
	  * 根据客户的坐标展示客户的标注
	  * time：2013-04-24
	  */
	showCustomerByPoint: function(custArr, fun) {
		if(custArr.length>0){
			var me = this,img = 'putong', lng = "",lat= "", type='NORMAL',customerId='';
			this.clickCallFun = fun;
			for ( var i = 0; i < custArr.length; i++) {
				type = custArr[i].type;
				customerId = custArr[i].custId;
				lng = custArr[i].longitude;
				lat = custArr[i].latitude;
				if(!(lng&&lat))return;
				img = me.getCustomerImgType(type);
				var point = new BMap.Point(lng,lat);
				var icon = new BMap.Icon(CRM_BMap.common.DPMAP_URL+"/images/gis/"+img+".png", new BMap.Size(21,21));
				var marker = new BMap.Marker(point,{icon:icon});
				me.map.addOverlay(marker);
//				me.map.setCenter(point);
//				me.map.setZoom(12);
				me.customerArr[i]={
						marker:marker,
						customerId:customerId,
						lng:lng,
						lat:lat,
						type:type,
						deptId:custArr[i].deptId,
						clickFun:fun
				};
				me.customerMarkerListener(i);
			}
		}
		
	},
	/**
	 * 073102-Tommy Wu
	 * 根据客户地址，模糊查询客户位置
	 * time：2013-04-24
	 */
	showCustomerByAddress: function(customerObj,callbackFun) {
		var me = this;
		this.poiCallbackFun = callbackFun;
		me.poiArr = [];
		var opt = {
			renderOptions : {
				autoViewport : true
			}
		};
		if(!customerObj.city){
			customerObj.city = '中国';
		}
		//精确解析
		var geo = new BMap.Geocoder();
		var local = new BMap.LocalSearch(customerObj.city, opt);
		if (customerObj.address) {
			geo.getPoint(customerObj.address,function(point){
				if(point){
					me.poiArr.push({
						point : point,
						address : customerObj.address,
						name : '',
						id :'',
						province : '',
						city : customerObj.city,
						marker : ""
					});
					me.showPoi(customerObj,'CANKAO');
				}else{
					local.search(customerObj.address);
				}
			},customerObj.city)
		}else{
			if (me.poiCallbackFun) {
				eval(me.poiCallbackFun)(customerObj);
			}
		}
		// 模糊解析实例

		local.setSearchCompleteCallback(function(response) {
			if (local.getStatus() == BMAP_STATUS_SUCCESS) {
				var len = 5;
				if(response.getCurrentNumPois()<5){
					len = response.getCurrentNumPois();
				}
				for ( var i = 0; i < len; i++) {
					var poi = response.getPoi(i);
					me.poiArr.push({
						point : poi.point,
						address : poi.address,
						name : poi.title,
						id : poi.uid,
						province : poi.province,
						city : poi.city,
						marker : ""
					});
				}
				me.showPoi(customerObj,'CANKAO');
			} else {
				if (me.poiCallbackFun) {
					eval(me.poiCallbackFun)(customerObj);
				}
			}
		});
		
	},
	/**
	 * 073102-Tommy Wu
	 * 将兴趣点信息标注到地图上
	 * 2.13-05-06
	 */
	showPoi : function(customerObj,type) {
		for ( var i = 0; i < this.poiArr.length; i++) {
			var poi = this.poiArr[i],marker,icon,img;
			if(type){
				img = this.getCustomerImgType(type);
			}else{
				img = 'u663_original';
			}
			icon = new BMap.Icon(CRM_BMap.common.DPMAP_URL+"/images/gis/"+img+".png", new BMap.Size(21,21));
			marker = new BMap.Marker(poi.point,{icon:icon});
			marker.setZIndex(100);
			this.poiArr[i].marker = marker;
			this.map.addOverlay(marker);
			this.map.centerAndZoom(poi.point,12);
			this.markerListener(i, marker, customerObj);
		}
	},
	/**
	 * 073102-Tommy Wu
	 * 兴趣点标注点击事件，弹出信息框，信息框按钮点击，返回给调用者地址信息
	 * 2013-05-06
	 */
	markerListener : function(index,marker,customerObj){
		var me = this;
//		me.btnClickListener(index, marker, customerObj);
		marker.addEventListener('click', function() {
			me.btnClickListener(index, marker, customerObj);
		});
	},
	/**
	 * 073102-Tommy Wu
	 * 兴趣点标注点击事件，弹出信息框，信息框按钮点击，返回给调用者地址信息
	 * 2013-05-06
	 */
	btnClickListener : function(index,marker,customerObj){
		var me = this, address = me.poiArr[index].address,name=me.poiArr[index].name;
		if(''!=name&&undefined!=name){
			name=name+':';
		}
		this.showMarkerInfoWindow( name+address, 'poi');//弹出信息框
		this.infoWindow.addEventListener('open',function(){
			CRM_BMap.common.getDomById('inputid').onclick = function() {
				me.resetModifyCusMarker(customerObj,me.poiArr[index].point);
				if (me.poiCallbackFun) {
					eval(me.poiCallbackFun)(customerObj, me.poiArr[index].point);
				}
			};
		});
		marker.openInfoWindow(this.infoWindow);
	},
	/**
	 * 重置修改后的标注
	 */
	resetModifyCusMarker:function(obj,point){
		var index =this.getCustomerMessById(obj.custId);
		var me = this;
		if(index!=null){
			this.map.removeOverlay(this.customerArr[index].marker);
			var  marker = new BMap.Marker(point);
			var icon = new BMap.Icon(CRM_BMap.common.DPMAP_URL+"/images/gis/"+this.getCustomerImgType(this.customerArr[index].type)+".png", new BMap.Size(21,21));
			marker = new BMap.Marker(point,{icon:icon});
			this.map.addOverlay(marker);
			this.customerArr[index].lng=point.lng;
			this.customerArr[index].lat=point.lat;
			this.customerArr[index].marker=marker;
			this.customerMarkerListener(index);
			if (this.customerArr[index].clickFun) {
				eval(this.customerArr[index].clickFun)(
						me.customerArr[index].customerId,
						me.customerArr[index].type,
						me.customerArr[index].lat,
						me.customerArr[index].lng,
						me.customerArr[index].deptId,
						function(html) {
							if (html) {
								me.showCustMarkerWindow(me.customerArr[index].customerId,html);
							}
						});
			}
		}else{
			var  marker = new BMap.Marker(point);
			var icon = new BMap.Icon(CRM_BMap.common.DPMAP_URL+"/images/gis/"+this.getCustomerImgType(obj.type)+".png", new BMap.Size(21,21));
			marker = new BMap.Marker(point,{icon:icon});
			this.map.addOverlay(marker);
			var ob={
					marker:marker,
					customerId:obj.custId,
					lng:point.lng,
					lat:point.lat,
					type:obj.type,
					deptId:obj.deptId,
					clickFun:obj.callback	
			}
			this.customerArr.push(ob);
			this.customerMarkerListener(this.customerArr.length-1);
			this.clickCallFun = obj.callback;
			if (this.clickCallFun) {
				eval(this.clickCallFun)(
						this.customerArr[this.customerArr.length-1].customerId,
						this.customerArr[this.customerArr.length-1].type,
						this.customerArr[this.customerArr.length-1].lat,
						this.customerArr[this.customerArr.length-1].lng,
						this.customerArr[this.customerArr.length-1].deptId,
						function(html) {
							if (html) {
								me.showCustMarkerWindow(me.customerArr[me.customerArr.length-1].customerId,html);
							}
						});
			}
		}
	},
	/**
	 * 073102-Tommy Wu
	 * 清楚地图上所有的poi标注
	 * 2013-05-06
	 */
	clearPoiMarker : function() {
		if (this.poiArr.length > 0) {
			for ( var i = 0; i < this.poiArr.length; i++) {
				this.map.removeOverlay(this.poiArr[i].marker);
			}
			this.poiArr = [];
		}
	},
	/**
	 * 073102-Tommy Wu
	 * 清楚地图上所有的覆盖物
	 * 2013-05-06
	 */
	clearAllOverlays : function() {
		this.map.clearOverlays();
	},
	/**
	 * 073102-Tommy Wu
	 * 清楚地图上所有的客户标注
	 * 2013-05-06
	 */
	clearCustomerMarker : function() {
		if (this.customerArr.length > 0) {
			for ( var i = 0; i < this.customerArr.length; i++) {
				this.map.removeOverlay(this.customerArr[i].marker);
			}
			this.customerArr = [];
		}
	},
	/**
	 * 073102-Tommy Wu
	 * 绑定部门标注点击事件，弹出部门信息框
	 * 2013-05-06
	 */
	addDeptMarkerClick : function(marker, deptName) {
		var me = this;
		this.showMarkerInfoWindow(deptName, 'dept');
		marker.openInfoWindow(this.infoWindow);
		marker.addEventListener('click', function() {
			me.showMarkerInfoWindow(deptName, 'dept');
			marker.openInfoWindow(me.infoWindow);
		});
	},
	/**
	 * 073102-Tommy Wu
	 * 根据marker类型，展示不同类型信息框
	 * 2013-05-06
	 */
	showMarkerInfoWindow : function( name, type) {
		if (this.infoWindow) {
			this.infoWindow.close();
		}
		this.infoWindow = new BMap.InfoWindow(CRM_BMap.common.markerHtml(name,
				type)); // 创建信息窗口对象
	},
	/**
	 * 073102-Tommy Wu
	 * 绑定客户标注点击事件，弹出信息框
	 * 2013-05-06
	 */
	customerMarkerListener : function(index) {
		var me = this, marker = "";
		if (index != null) {
			marker = me.customerArr[index].marker;
			marker.addEventListener('click', function() {
				// TODO 调用crm的展示标签方法
				if (me.clickCallFun) {
					eval(me.clickCallFun)(
							me.customerArr[index].customerId,
							me.customerArr[index].type,
							me.customerArr[index].lat,
							me.customerArr[index].lng,
							me.customerArr[index].deptId,
							function(html) {
								if (html) {
									me.showCustMarkerWindow(me.customerArr[index].customerId,html);
								}
							});
				}
			});
		}
	},
	/**
	 * 073102-Tommy Wu
	 * 展示客户标注信息框信息
	 * 2013-05-06
	 */
	showCustMarkerWindow: function(id,html,lat,lng,type){
		var me  = this,img="putong",marker = "", index = me.getCustomerMessById(id);
		if(index!=null){
			marker = me.customerArr[index].marker;
		}else{
//			if(me.customerArr.length>300){
//				if(me.infoWindow){
//					me.infoWindow.close();
//				}
//					alert("地图加载客户数已达上限，请点击客户详情查看客户信息！");
//				return;
//			}else{
			if(!Ext.isEmpty(lat)){
				if(type){
					img = me.getCustomerImgType(type);
				}
				var point = new BMap.Point(lat,lng);
				var icon = new BMap.Icon(CRM_BMap.common.DPMAP_URL+"/images/gis/"+img+".png", new BMap.Size(21,21));
				marker = new BMap.Marker(point,{icon:icon});
				me.map.addOverlay(marker);
				var arg={
						marker:marker,
						customerId:id,
						lng:lng,
						lat:lat,
						type:type,
						clickFun:me.clickCallFun
				};
				if(me.customerArr.length>299){
					if(me.customerArr[me.customerArr.length-1]){
						if(me.customerArr[me.customerArr.length-1].marker){
							me.map.removeOverlay(me.customerArr[me.customerArr.length-1].marker);
						}
					}
					me.customerArr[me.customerArr.length-1]=arg;
				}else{
					me.customerArr.push(arg);
				}
				me.customerMarkerListener(me.customerArr.length-1);
			}
		}
		if(html){
			me.infoWindow = new BMap.InfoWindow(html); // 创建信息窗口对象
			marker.openInfoWindow(me.infoWindow);
		}
	},
	/**
	 * 073102-Tommy Wu
	 * 根据客户类型，获取客户标注图标
	 * 2013-05-06
	 */
	getCustomerImgType:function(type){
		var img = 'fixedClientSmall';
		switch (type) {
		case "PLATINUM"://
			img = 'silverSmall';
			break;
		case "GOLD":
			img = 'goldSmall';
			break;
		case "2":
			img = 'returnVisitingSmall';
			break;
		case "NORMAL":
			img = 'fixedClientSmall';
			break;
		case "PC_CUSTOMER":
			img = 'PC_CUSTOMER';
			break;
		case "SC_CUSTOMER":
			img = 'scatteredClientSmall';
			break;
		case "3":
			img = 'unReturnVisitSmall';
			break;
		case "1":
			img = 'returnVisitSmall';
			break;
		case "DIAMOND":
			img = 'diamondSmall';
			break;
		case "CANKAO":
			img ='purple';//标注参考图标
			break;
		case "deppon":
			img='deppon';//营业部图标
		}
		return img;
	},
	/**
	 * 073102-Tommy Wu
	 * 根据客户id，返回客户数组指针
	 * 2013-05-06
	 */
	getCustomerMessById:function(id){
		for ( var i = 0; i < this.customerArr.length; i++) {
			if(this.customerArr[i].customerId == id){
				return i;
			}
		}
		return null;
	}
};