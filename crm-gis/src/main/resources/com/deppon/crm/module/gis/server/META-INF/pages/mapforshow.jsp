<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="../styles/gis/map.css" />
  </head>
<body>
<input value="标注" type="button" onclick='showCustomerByAddress()'/>
<input value="展示客户位置" type="button" onclick='showCustomerByPoint()'/>
<input value="查询客户地址" type="button" onclick='selectPoly1()'/>
<input value="查询客户2" type="button" onclick='selectPoly2()'/>
<input value="查询营业部范围" type="button" onclick='showPolygonById()'/>
<input value="展示部门位置" type="button" onclick='showDeptMarker()'/>
<input value="展示客户导航距离" type="button" onclick='show123()'/>
<input value="清除兴趣点" type="button" onclick='clearPoiMarker()'/>
<div id="index" style="width:100%;height:80%;"></div>
<div id="poi" style="width:10%;height:10%;"></div>
	<script type="text/javascript" src="${scripts}/mapforshow.js"></script>
<script type="text/javascript">
		 var id1 = 'DB0393F812917121E043A111A8C0D764';
		 var id2= 'DB0393F812927121E043A111A8C0D764';
window.onload = function(){
	var cm= new CRM_BMap( 'index',{center : "上海市", zoom : 12 },function(map) {
		 var fun =function(data){
			 alert(data);
		 }
		window.point = cm.PointFeature(map,{callbackFun:fun});
		window.poly = cm.PolygonFeature(map,{});
	 });
}
var id = 'DB69610A6FC3345FE043A111A8C0B9D0';
function show123(){
	point.showCustToDeptDistance('121.313131','31.13131','DP01111');
}
function drawPoly(){
	point.startDrawPolygon();
}
function deletePoly(){
	pointy.deletePolygonById(id1);
}
//根据客户地址标注客户
function showCustomerByAddress(){
	point.showCustomerByAddress({address:"上海市青浦区肯德基"});
}
//展示部门位置
function showDeptMarker(){
	point.showDeptMarker("DP01111");
}
function clearPoiMarker(){
	point.clearPoiMarker();
}

//根据营业部范围编码，展示范围
function showPolygonById(){
	poly.showPolygonById(id);
}
function fun1(){
	
}
//展示客户信息
function showCustomerByPoint(){
	for(var i =0;i<301;i++){
		
	point.showCustomerByPoint([{custId:"asdf",longitude:"120."+i+"03961",latitude:"30."+i+"70510",type:'1',clickFun:fun1}]);
	}
	
}
 //------------------------------------------------------------------------------------------------------------------------------
 /**
  *创建展示地图
  */
 
</script>
</body>
</html>
