/**
 * CRM地图的初始化
 */
function CRM_GMap(div, option, callbackFun) {
	this.map = null;
	this.option = option;
	this.div = div;
	this.callbackFun = callbackFun;
	this.initMap(div, option, callbackFun);
}
CRM_GMap.prototype = {
	initMap : function(div, option, callbackFun) {
		var me = this;
		CRM_GMap.googleCallBack = function() {
			me.showMap(div, option);
		};
		// 加载google地图api
		me.loadApi('$google','https://maps.google.com/maps/api/js?v=3.exp&sensor=false&callback=CRM_GMap.googleCallBack');
	},
	showMap : function(div, option, callbackFun) {
		var me = this;
		this.googleGeoCoder = new google.maps.Geocoder();
		this.defaultCenter = new google.maps.LatLng(31.170510, 121.403961);
		var mapOption, map;
		mapOption = {
			center : this.defaultCenter,
			zoom : option.zoom,
			mapTypeId : google.maps.MapTypeId.ROADMAP,
			disableDoubleClickZoom : true,
			centerString : option.center
		};
		map = new google.maps.Map(document.getElementById(div), mapOption);
		me.map = map;
		if (this.callbackFun) {
			eval(this.callbackFun)(map);
		}

	},
	loadApi : function(scriptId, scriptUrl) {
		if (!document.getElementById(scriptId)) {
			var script = document.createElement("script");
			script.type = "text/javascript";
			script.id = scriptId;
			script.src = scriptUrl;
			document.getElementsByTagName("head")[0].appendChild(script);
		}

	},
	PolygonFeature : function(map, option) {
		return new GooglePolygonFeature(map, option, this.div);
	}
};

/**
 * 初始化通用模块
 */
CRM_GMap.common = {
	validateParams : function(param, len) {
		var myReg = /[!|@|#|$|%|^|&|*|\(|\)|\{|\}|\[|\]]/;
		if (param.length > len) {
			return false;
		}
		if (myReg.test(param)) {
			return false;
		}
		return true;
	},
	/**
	 * 判断一个范围是否自己里面有交叉
	 */
	ifcrossidentify : function(points) {
		var result = '';
		if (points[0] != points[points.length - 1]) {
			points.push(points[0]);
		}
		for (var i = 0; i < points.length - 2; i++) {
			for (var j = i + 1; j < points.length - 1; j++) {
				result = this.ifcross(points[i].lat(), points[i].lng(),
						points[i + 1].lat(), points[i + 1].lng(), points[j]
								.lat(), points[j].lng(), points[j + 1].lat(),
						points[j + 1].lng());
				if (result != '') {
					return result;
				}
			}
		}
		return result;
	},
	/**
	 * 判断两条线段是否相交
	 */
	ifcross : function(x1, y1, x2, y2, x3, y3, x4, y4) {
		var crossx, crossy;
		if ((x1 == x3 && y1 == y3) || (x1 == x4 && y1 == y4)
				|| (x2 == x3 && y2 == y3) || (x2 == x4 && y2 == y4)) {
			return '';
		}
		var k1, k2, b1, b2, x, y;

		k1 = (y1 - y2) / (x1 - x2);
		k2 = (y3 - y4) / (x3 - x4);
		b1 = y2 - k1 * x2;
		b2 = y3 - k2 * x3;

		x = (b2 - b1) / (k1 - k2);
		y = k1 * x + b1;

		if ((x1 - x) * (x2 - x) < 0 && (y1 - y) * (y2 - y) < 0
				&& (x3 - x) * (x4 - x) < 0 && (y3 - y) * (y4 - y) < 0) {
			crossx = x;
			crossy = y;
			return crossx + "," + crossy;
		} else {
			return '';
		}

	},
	/*
	 * 将字符串转换成点数组(google版)
	 */
	stringConvertArray : function(strs) {
		var points = [];
		var array = strs.split(",");
		for ( var i = 0; i < array.length; i++) {
			if (i % 2 == 0) {
				var point = new google.maps.LatLng(array[i + 1] * 1.0,
						array[i] * 1.0);
				points.push(point);
			}
		}
		return points;
	},
	/**
	 * 求多边形的几何中心点
	 */
	polygonGeometricCenterPoint : function(points) {
		var bounds = new google.maps.LatLngBounds();
		for ( var i = 0; i < points.length; i++) {
			bounds.extend(points[i]);
		}
		return bounds.getCenter();
	},
	/*
	 * 将对象转换成坐标数组
	 */
	object2pointArray : function(coordinates) {
		var me = this;
		var points = [];
		for ( var i = 0; i < coordinates.length; i++) {
			points.push(me.stringConvertArray(coordinates[i].google));
		}
		return points;
	},
	/*
	 * 参数编码
	 */
	encodeParam : function(id) {
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
	createXmlHttp : function() {
		var xmlHttp=null;
		if (window.ActiveXObject) { // IE下
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) { // 其他浏览器如火狐
			xmlHttp = new XMLHttpRequest();
		}
		return xmlHttp;
	},
	send : function(param) {
		var callback = param.callback;
		var url = param.url;
		var paramValue = param.param;
		var method = param.method;
		var async = param.async;
		var xmlHttp = CRM_GMap.common.createXmlHttp();

		xmlHttp.open(method, url, async);
		if (CRM_GMap.common.POSTMETHOD == method) {
			xmlHttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
		}

		xmlHttp.onreadystatechange = function() {
			{
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
	doPost : function(param) {
		param.method = CRM_GMap.common.POSTMETHOD;
		CRM_GMap.common.send(param);
	},

	/*
	 * param 格式： { url:"http://www.baidu.com",//url param:"age=1&name=2",//参数
	 * async:true,//是否同步 callback:callbackFunction//回调函数 }
	 */
	// Get传参
	doGet : function(param) {
		param.method = CRM_GMap.common.GETMETHOD;
		CRM_GMap.common.send(param);
	}

};

/**
 * 073102-Tommy Wu 
 * 加载api方法 
 * time：2013-04-24
 */
function GooglePolygonFeature(map, option, div) {
	this.map = map;
	this.mapClick = null;
	this.option = option;
	this.callBackFun = option.callBackFun;
	this.k = 0;
	this.pointArr = [];
	this.polyline = null;
	this.markers = [];
	this.infowindow = new google.maps.InfoWindow();
	this.focusPolygon = {};
	this.allPolygonArr = [];
	this.updatePolygon = [];
	this.polyStatus = 'FREE';
	this.preEditPoly = {};
	this.initGooglePoly();
	this.div = div;
	this.timeCur = "";
	this.moveLabel==null;
	this.restMarker=null;
}
/**
 * 展示不可以修改的范围
 */
GooglePolygonFeature.prototype = {
	initGooglePoly : function() {
		var me = this;
		google.maps.event.addListener(this.map, 'click', function() {
			if (me.infowindow) {
				me.infowindow.close();
			}
		});
	},
	/*
	 * 给地图画范围鼠标添加样式
	 */
	modifyMouseStyle : function(div) {
		if (!div)
			return;
		// 进入划范围状态时将鼠标改为画笔图标
		var mapBox = document.getElementById(div), tarDiv = mapBox
				.getElementsByTagName("div")[0].getElementsByTagName("div")[0]
				.getElementsByTagName("div")[0], // 取到目标层
		tarCursor = tarDiv.style.cursor; // 取到控制鼠标图标的CSS
		mapBox.style.cursor = 'url(../images/gis/draw.png),url(../images/gis/draw.cur),auto';
		// 当鼠标图标不为空时，设置为空，以显以下面的CSS
		if (!(tarCursor == "")) {
			this.timeCur = setInterval(function() {
				tarDiv.style.cursor = "";
			}, 100);
		} else {
			mapBox.style.cursor = '';
			clearInterval(this.timeCur); // 完成划范围操作后，清除定时，以使鼠标成为默认状态
		}

	},
	/**
	 * 073102-Tommy Wu 
	 * 根据范围id展示不可以修改的范围 
	 * time：2013-04-24
	 */
	showUnModifyPolygonById : function(obj) {
		var me = this,isLocate = true, idParam = CRM_GMap.common.encodeParam(obj.id),color='#373c64';
		if (obj.color)
			color = obj.color;
		if (obj.isLocate) {
			isLocate = obj.isLocate;
		}
		CRM_GMap.common.doPost({
			url : CRM_GMap.common.DPMAP_URL
					+ "/gis/selectPolygonById.action", // url
			param : 'id=' + idParam, // 参数
			async : true, // 是否同步
			callback : function(result) {
				var data = eval("(" + result + ")");
				if (data.success) {
					if (data.polygonEntity) {
						var netShape = CRM_GMap.common
								.stringConvertArray(data.polygonEntity.google
										.split(",").join(",")); // double类型的数组,转化为坐标数组
						var polygon = new google.maps.Polygon({
							editable : false,
							paths : netShape,
							strokeColor : '#373c64',
							strokeOpacity : '0.5',
							fillColor : color,
							fillOpacity : '0.3',
							strokeWeight : 1,
							zIndex : 1
						});
					   google.maps.event.addListener(polygon,'click',function(event){
					    	 if(me.polyStatus != 'FREE'){//把地图状态设置为编辑状态
								  google.maps.event.trigger(me.map,'click',event);
							 }
					    });
					    google.maps.event.addListener(polygon,'mousemove',function(event){
					    	 if(me.polyStatus != 'FREE'){//把地图状态设置为编辑状态
								  google.maps.event.trigger(me.map,'mousemove',event);
							 }
					    });
						var polygonCenter = CRM_GMap.common
								.polygonGeometricCenterPoint(netShape);
						var polygonLabel = me.createLabel(me.map,
								polygonCenter, data.polygonEntity.name);
						me.focusPolygon = {// 当前获得焦点的范围
							'id' : data.polygonEntity.id,
							'google' : polygon,// 多边形的范围值
							'type' : data.polygonEntity.type,
							'description' : data.polygonEntity.description,
							'name' : data.polygonEntity.name,
							'descLabel' : polygonLabel,// 多边形行上的标注
							'polygonID' : data.polygonEntity.polygonID
						};

						me.addPolygonToArr(me.focusPolygon);
						polygon.setMap(me.map);
						me.map.setCenter(polygonCenter);
					}
					if (obj.callbackFun) {
						eval(obj.callbackFun)(data);
					}

				}else{
					console.log("系统内部错误");
					if (obj.callbackFun) {
						eval(obj.callbackFun)({});
					}
				}

			}
		});
	},
	/**
	 * 展示可以修改的范围
	 */
	showModifyPolygonById : function(obj) {
		var me = this, idParam = CRM_GMap.common.encodeParam(obj.id);
		var isLocate = true;
		if (obj.isLocate) {
			isLocate = obj.isLocate;
		}
		CRM_GMap.common.doPost({
			url : CRM_GMap.common.DPMAP_URL
					+ "/gis/selectPolygonById.action", // url
			param : 'id=' + idParam, // 参数
			async : true, // 是否同步
			callback : function(result) {
				var data = eval("(" + result + ")");
				if (data.success) {
					me.showPolygonAfterSave(data, isLocate);
					if (obj.callbackFun) {
						eval(obj.callbackFun)(result);
					}
				}else{
					if (obj.callbackFun) {
						eval(obj.callbackFun)({});
					}
					console.log("系统内部错误");
				}
			}
		});
	},
	/**
	 * 根据范围Id删除范围
	 */
	deletePolygonById : function(id) {
		var me = this;
		var idParam = CRM_GMap.common.encodeParam(id);
		CRM_GMap.common.doPost({
			url : CRM_GMap.common.DPMAP_URL
					+ "/gis/deletePolygonById.action", // url
			param : 'id=' + idParam, // 参数
			async : true, // 是否同步
			callback : function(result) {
				var data = eval("(" + result + ")");
				if (data.success) {

					if (me.infowindow) {
						me.infowindow.close();
						me.infowindow = undefined;
					}
					me.clearMapPolygonById(data.id);
					if (me.callBackFun) {
						eval(me.callBackFun)({
							id : data.id,
							type : 'DEL'
						});
					}
				}else{
					if (me.callBackFun) {
						eval(me.callBackFun)({});
					};
					alert("删除失败")
				}
			}
		});
	},
	updatePolygonFun: function(googleRange, name, type, description,id){
		var me = this;
		CRM_GMap.common.doPost({
			url : CRM_GMap.common.DPMAP_URL
					+ "/gis/updatePolygon.action", // url
			param : 'polygonEntity.name=' + name
					+ '&polygonEntity.polygonID=' + id
					+ '&polygonEntity.description=' + description
					+ '&polygonEntity.type=' + type + '&polygonEntity.google='
					+ googleRange, // 参数
			async : true, // 是否同步
			callback : function(result) {
				var data = eval("(" + result + ")");
				if (data.success) {
					me.showPolygonAfterSave(data);
					if (me.callBackFun) {
						eval(me.callBackFun)({
							id : data.polygonEntity.polygonID,
							type : 'UPD'
						});
					}
				} else {
					if (me.callBackFun) {
						eval(me.callBackFun)({});
					}
					alert("保存失败");
				}
			}
		});
	},
	/**
	 * 保存范围信息
	 */
	savePolygon : function(googleRange, name, type, description) {
		var me = this;
		CRM_GMap.common.doPost({
			url : CRM_GMap.common.DPMAP_URL
					+ "/gis/savePolygon.action", // url
			param : 'polygonEntity.name=' + name
					+ '&polygonEntity.description=' + description
					+ '&polygonEntity.type=' + type + '&polygonEntity.google='
					+ googleRange, // 参数
			async : true, // 是否同步
			callback : function(result) {
				var data = eval("(" + result + ")");
				if (data.success) {
					me.showPolygonAfterSave(data);
					if (me.callBackFun) {
						eval(me.callBackFun)({
							id : data.polygonEntity.polygonID,
							type : 'ADD'
						});
					}
				} else {
					if (me.callBackFun) {
						eval(me.callBackFun)({});
					}
					alert("保存失败");
				}
			}
		});
	},
	/**
	 * 保存后，重新加载范围
	 */
	showPolygonAfterSave : function(data,isLocate) {
		var me = this;
		if (data.polygonEntity) {
			var netShape = CRM_GMap.common
					.stringConvertArray(data.polygonEntity.google.split(",")
							.join(",")); // double类型的数组,转化为坐标数组
			var polygon = new google.maps.Polygon({
				editable : false,
				paths : netShape,
				strokeColor : '#373c64',
				strokeOpacity : '0.5',
				fillColor : 'green',
				fillOpacity : '0.3',
				strokeWeight : 1,
				zIndex : 5
			});
			var polygonCenter = CRM_GMap.common
					.polygonGeometricCenterPoint(netShape);
			var polygonLabel = me.createLabel(me.map, polygonCenter,
					data.polygonEntity.name);

			me.focusPolygon = {// 当前获得焦点的范围
				'id' : data.polygonEntity.id,
				'google' : polygon,// 多边形的范围值
				'type' : data.polygonEntity.type,
				'description' : data.polygonEntity.description,
				'name' : data.polygonEntity.name,
				'descLabel' : polygonLabel,// 多边形行上的标注
				'polygonID' : data.polygonEntity.polygonID
			};

			me.addPolygonToArr(me.focusPolygon);
			polygon.setMap(me.map);
			if(isLocate){
				me.map.setCenter(polygonCenter);
			}
			var polygonObj = {
				'id' : data.polygonEntity.id,
				'google' : polygon,// 多边形的范围值
				'type' : data.polygonEntity.type,
				'description' : data.polygonEntity.description,
				'name' : data.polygonEntity.name,
				'descLabel' : polygonLabel,// 多边形行上的标注
				'polygonID' : data.polygonEntity.polygonID
			};
			google.maps.event.addListener(polygon, 'click', function() {
				me.showPolygonWindow(center, false, polygonObj);
			});
			var center = CRM_GMap.common.polygonGeometricCenterPoint(netShape);
			me.showPolygonWindow(center, false, polygonObj);
		}
	},
	/**
	 * 清楚地图范围
	 */
	clearMapPolygonById : function(polygonID) {
		if (this.allPolygonArr.length > 0) {
			for ( var i = 0; i < this.allPolygonArr.length; i++) {
				if (polygonID == this.allPolygonArr[i].polygonID) {
					this.allPolygonArr[i].google.setMap(null);
					this.allPolygonArr[i].descLabel.setMap(null);
					this.allPolygonArr.splice(i, 1);
					if(this.infowindow){
						this.infowindow.close();
					}
				}
			}
		}
	},
	/**
	 * 将范围加在全局数据中
	 */
	addPolygonToArr : function(polygonObj) {
		if (this.allPolygonArr.length > 0) {
			for ( var i = 0; i < this.allPolygonArr.length; i++) {
				if (polygonObj.polygonID == this.allPolygonArr[i].polygonID) {
					this.allPolygonArr[i] = polygonObj;
					return;
				} 
			}
			this.allPolygonArr.push(polygonObj); 
		} else {
			this.allPolygonArr.push(polygonObj);
		}
	},
	/*
	 * 设置地图画范围全局状态
	 */
	
	setPolygonStatus:function(bool){
		if (bool=='EDITING') {
			this.polyStatus = 'EDITING';
		} else if (bool=='FREE'){
			this.polyStatus = 'FREE';
		}
	},
	/**
	 * 鼠标点击画范围按钮后，开始画范围操作
	 */
	startDrawPolygon : function() {
		if (this.polyStatus != 'FREE') {
			alert("请完成上一次画范围");
			return;
		} else {
			this.polyStatus = 'EDITING';
		}

		var me = this;
		me.modifyMouseStyle(me.div);
		   var mousemove = google.maps.event.addListener( me.map,'mousemove', function(event){
			      event.stop();//防止冒泡
			      if(me.moveLabel==null||me.k==0){
			    	  if(me.moveLabel!=null){
			    		  me.moveLabel.setMap(null);
			    	  }
			    	  me.moveLabel =  me.createLabel(me.map,event.latLng,"单击确定起点");
				 	  me.moveLabel.setMap(me.map);  
			      }else{
			    	  me.moveLabel.setMap(null);
			    	  me.moveLabel =  me.createLabel(me.map,event.latLng,"双击完成范围");
			 	      me.moveLabel.setMap(me.map); 
			      }
	 	   });
		me.pointArr = [];
		me.k=0;
		google.maps.event.removeListener(me.mapClick);
		me.mapClick = google.maps.event.addListener(me.map, 'click', function(
				event) {
//			event.stop();
			if (me.k >= 1) {
				me.addGoBackLable(event.latLng);
			}
			me.k++;
			me.pointArr.push(event.latLng);
			var marker = new google.maps.Marker({
				position : event.latLng,
				icon : {
					path : google.maps.SymbolPath.CIRCLE,
					strokeColor : '#435b9b',
					scale : 4
				}
			});
			marker.setMap(me.map);
			me.markers.push(marker); // 将marker装入到数组中.
			if (me.k >= 3) {
				me.polyline.setMap(null);
			}
			me.polyline = new google.maps.Polyline({
				path : me.pointArr,
				strokeColor : 'green',
				strokeOpacity : '0.5',
				strokeWeight : 2,
				zIndex : 20
			});
			me.polyline.setMap(me.map);
			google.maps.event.addListener(marker, 'dblclick', function(event) {
				if(me.pointArr.length<3){
					return;
				}
				if(me.restMarker!=null){
					me.restMarker.setMap(null);
				}
				me.moveLabel.setMap(null);//清除移动的label
				 google.maps.event.removeListener(mousemove);
				for ( var i = 0; i < me.markers.length; i++) { // 清楚图标
					me.markers[i].setMap(null);
				}
				me.markers = [];
				google.maps.event.removeListener(me.mapClick); // 移除地图的点击事件
				polygon = new google.maps.Polygon({
					editable : true,
					strokeOpacity : '0.3',
					fillColor : 'green',
					fillOpacity : '0.5',
					paths : me.pointArr,
					strokeWeight : 1,
					zIndex : 100
				});
				var polygonObj = {
					'id' : '',
					'google' : polygon, // 多边形的范围值
					'type' : '',
					'description' : '',
					'name' : '',
					'descLabel' : '', // 多边形行上的标注
					'polygonID' : ''
				};
				var polygonCenter = CRM_GMap.common
						.polygonGeometricCenterPoint(me.pointArr);
				me.showPolygonWindow(polygonCenter, true, polygonObj);
				polygon.setMap(me.map);
				me.polyline.setMap(null);
				google.maps.event.addListener(polygon, 'click', function() {
					me.showPolygonWindow(polygonCenter, true, polygonObj);
				});

				me.modifyMouseStyle(me.div);
			});
		});

	},
	addGoBackLable:function(point){
		var me = this;
		if(this.restMarker!=null){
			this.restMarker.setMap(null);
		}
		var marker = new google.maps.Marker({
	         position:point,
	         draggable:false,
	         visible:true,
	         clickable:true,
	         scaledSize:new google.maps.Size(30, 32),
	         icon:new google.maps.MarkerImage('../images/gis/undo1.png',new google.maps.Size(30, 32))
	     });
		this.restMarker=marker;
		marker.setMap(me.map);
		google.maps.event.addDomListener(marker,'click',function(e){
			marker.setMap(null);
			if(me.markers.length>0){
				me.markers[me.markers.length-1].setMap(null);
			}
			me.goBack();
		});
	
	},
	//添加撤销按钮
	goBack:function(){
		this.pointArr.pop();
		this.polyline.setMap(null);
		this.polyline = new google.maps.Polyline({
			path : this.pointArr,
			strokeColor : 'green',
			strokeOpacity : '0.5',
			strokeWeight : 2,
			zIndex : 20
		});
		this.polyline.setMap(this.map);
	},
	// 展示范围信息窗口
	showPolygonWindow : function(polygonCenter, type, polyObj) {
		var me = this, contentDom = '',description='无';
		var polygon = polyObj.google;
		if(polyObj.description==null||polyObj.description==""){
			description='无';
		}else{
			description = polyObj.description;
		}
		var contentNew = '<div class="iw-con">'
				+ '<form action="">'
				+ '<ul>'
				+ '<li><label for="newPolyName">名称:</label><input id="newPolyName" type="text" value="'
				+ polyObj.name
				+ '"/></li>'
				+ '<li><label for="newPolyDescription">备注:</label><input id="newPolyDescription" type="text" value="'
				+ description
				+ '"/></li>'
				+ '<li class="btnbox"><input type="button" class="btn-no" value="取消" /><input type="button" class="btn-ok" value="保存" /></li>'
				+ '</ul>' + '</form>' + '</div>';

		var contentOld = '<div class="iw-con">'
				+ '<form action="">'
				+ '<ul>'
				+ '<li><label for="">名称:</label><input type="text" readonly value="'
				+ polyObj.name
				+ '"/></li>'
				+ '<li><label for="">备注:</label><input type="text" readonly value="'
				+ description
				+ '"/></li>'
				+ '<li class="btnbox"><input type="button" class="btn-del" value="删除" /><input type="button" class="btn-upd" value="修改" /></li>'
				+ '</ul>' + '</form>' + '</div>';
		if (type) {
			contentDom = contentNew;
		} else {
			contentDom = contentOld;
		}
		if (!me.infowindow) {
			me.infowindow = new google.maps.InfoWindow();
		}
		me.infowindow.setContent(contentDom);
		me.infowindow.setPosition(polygonCenter);
		me.infowindow.open(me.map);
		google.maps.event.addListener(me.infowindow, 'domready', function() {// 监听窗口加载事件
			var btnConsole = document.getElementsByClassName('btn-no')[0];
			if (typeof btnConsole != "undefined") {
				btnConsole.onclick = function(event) {// 取消新增
					me.polyStatus = 'FREE';
					event.stopPropagation();
					me.restPolygon(polygonCenter, polyObj);
				};
			}

			var btnSave = document.getElementsByClassName('btn-ok')[0];
			if (typeof btnSave != "undefined") {
				btnSave.onclick = function(event) {// 新增
					me.polyStatus = 'FREE';
					event.stopPropagation();
					var boun = polygon.getPaths().b[0].b.toString();
					boun = boun.replace(/\(/g, "");
					boun = boun.replace(/\)/g, "");
					var googleRange = boun.replace(/[ ]/g, "");
					googleRange = googleRange.split(",").reverse().join(","); // 倒置字符串
					var name = document.getElementById('newPolyName').value;
					var description = document
							.getElementById('newPolyDescription').value;
					var result = CRM_GMap.common
							.ifcrossidentify(CRM_GMap.common
									.stringConvertArray(googleRange));
					if (result != '') {
						alert("范围有交叉，请修改后再保存");
						return;
					}
					if(name==""||name==null||name.replace(/[ ]/g,"")==""){
						alert("名称不能为空");
						return;
					}
					
					var   pattern   =   /^[a-z\d\u4E00-\u9FA5]+$/i; 
					if(!pattern.test(name)) {
						alert('名称含有特殊字符');
						return;
					}
					if(name.split(" ").length!=1){
						alert('名称含有空格');
						return;
					}
//					if(description==""||description==null){
//						alert("描述不能为空");
//						return;
//					}
					if (!CRM_GMap.common.validateParams(name, 30)) {
						alert("名称长度超过30个或字符不合法，请重新输入");
						return;
					}
					if (!CRM_GMap.common.validateParams(description, 50)) {
						alert("备注长度超过50个或字符不合法，请重新输入");
						return;
					}
					me.infowindow.close();
					polygon.setMap(null);
					if (polyObj.descLabel) {
						polyObj.descLabel.setMap(null);
					}
					if(polyObj.polygonID!=''){
						me.updatePolygonFun(googleRange, name, 'DEPT', description,polyObj.polygonID);
					}else{
						me.savePolygon(googleRange, name, 'DEPT', description);
					}
				};
			}

			var btnUpde = document.getElementsByClassName('btn-upd')[0];
			if (typeof btnUpde != "undefined") {
				btnUpde.onclick = function(event) {// 修改
					event.stopPropagation();
					if (me.polyStatus != 'FREE') {
						alert('请完成上一次操作');
						return;
					}
					polygon.setMap(null);
					me.infowindow.close();
					me.modifyPolygon(polygon, polygonCenter, polyObj);
				};
			}

			var btnDele = document.getElementsByClassName('btn-del')[0];
			if (typeof btnDele != "undefined") {
				btnDele.onclick = function(event) {// 删除
					event.stopPropagation();
					me.deletePolygon(polyObj);
				};
			}
		});

	},
	/**
	 * 取消按钮事件
	 */
	restPolygon : function(polygonCenter, polyObj) {
		polyObj.google.setMap(null);
		// polyObj.descLabel.setMap(null);
		this.infowindow.close();
		var me = this;
		if (polyObj.id) {
			this.preEditPoly.setEditable(false);
			this.preEditPoly.setMap(this.map);
			polyObj.google = this.preEditPoly;
			this.addPolygonToArr(polyObj);
			this.showPolygonWindow(polygonCenter, false, polyObj);
			google.maps.event.addListener(this.preEditPoly, 'click', function() {
				me.showPolygonWindow(polygonCenter, false, polyObj);
			});
		}else{
			if (this.callBackFun) {
				eval(this.callBackFun)({
					id : '',
					type : 'CEL'
				});
			}
		}
	},
	/**
	 * 删除按钮事件
	 */
	deletePolygon : function(obj) {
		var me = this;
		var r = confirm("你确定要删除这个范围吗?");
		if (r == true) {
			if (me.infowindow) {
				me.infowindow.close();
			}
			me.deletePolygonById(obj.polygonID);
		}
	},
	/**
	 * 修改范围
	 */
	modifyPolygon : function(polygon, polygonCenter, polyObj) {
		var me = this;
		this.polyStatus = 'EDITING';
		var boun = polygon.getPaths().b[0].b.toString();
		boun = boun.replace(/\(/g, "");
		boun = boun.replace(/\)/g, "");
		var googleRange = boun.replace(/[ ]/g, "");
		googleRange = googleRange.split(",").reverse().join(","); // 倒置字符串
		var netShape = CRM_GMap.common.stringConvertArray(googleRange);

		this.preEditPoly = new google.maps.Polygon({
			editable : false,
			paths : netShape,
			strokeColor : '#373c64',
			strokeOpacity : '0.5',
			fillColor : 'green',
			fillOpacity : '0.3',
			strokeWeight : 1,
			zIndex : 5
		});
		polygon.setEditable(true);
		polygon.setMap(this.map);
		google.maps.event.addListener(polygon, 'click', function() {
			me.showPolygonWindow(polygonCenter, true, polyObj);
		});
		this.showPolygonWindow(polygonCenter, true, polyObj);
	},
	/**
	 * 自定义范围标签
	 */
	createLabel : function(map, latlng, labeledText,className, href) {
		// 自定义labelText
		function LabeledText(map, latlng, labeledText, href) {
			this.latlng_ = latlng;
			this.labeledText_ = labeledText||'';
			this.func_ = href||null;
			this.map_ = map;
			this.className_=className||"d_divLabeled";
			this.setMap(map);
		}
		LabeledText.prototype = new google.maps.OverlayView();
		LabeledText.prototype.onAdd = function() {
			var div = document.createElement('div');
			div.setAttribute("class", this.className_);
			div.innerHTML = "" + this.labeledText_ + "";
			this.div_ = div;
			var panes = this.getPanes();
			panes.floatPane.appendChild(div);
		};
		LabeledText.prototype.draw = function() {
			var overlayProjection = this.getProjection();
			var sw = overlayProjection.fromLatLngToDivPixel(this.latlng_);
			var div = this.div_;
			div.style.left = sw.x +10+ 'px';
			div.style.top = sw.y -10+'px';
		};
		LabeledText.prototype.onRemove = function() {
			this.div_.parentNode.removeChild(this.div_);
			this.div_ = null;
		};
		return new LabeledText(map, latlng, labeledText, href);
	}

};
