<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="../styles/gis/map.css" />
</head>
<body>
<div class="btnbox mt10">
	<input value="画范围" type="button" onclick='drawPoly()'/>
	<input value="删除范围" type="button" onclick='deletePoly()'/>
	<input value="查询范围1" type="button" onclick='selectPoly1()'/>
	<input value="查询范围2" type="button" onclick='selectPoly2()'/>
</div>
<section class="mapbox" style="width:100%; height:90%;">
	<div id="index" style="width:100%;height:100%;"></div>
	<div class="maptool">
		<a href="#" title="点击划范围" id="drawPoly" onclick='drawPoly()'></a>
	</div>
</section>

<script type="text/javascript" src="${scripts}/mapfordraw.js"></script>
<script type="text/javascript">
		 var id1 = 'DB69610A6FBE345FE043A111A8C0B9D0';
		 var id2= 'DB69610A6FBE345FE043A111A8C0B9D0';
window.onload = function(){
	 var gmap =new CRM_GMap( 'index',{center : "上海市", zoom : 12, mapType:"DPMAP_NORMAL_MAP" },function(map) {
		 var fun =function(data){
			// alert(data);
		 }
		 window.poly =  gmap.PolygonFeature(map,{callBackFun:fun});
	 });
}
function drawPoly(){
	poly.startDrawPolygon();
}
function deletePoly1(){
	 poly.deletePolygonById(id1);
}
function deletePoly2(){
	 poly.deletePolygonById(id2);
}
function selectPoly1(){
	poly.showModifyPolygonById({id:id2},'#FFFFFF');
}
function selectPoly2(){
	poly.showUnModifyPolygonById({id:id2},'#BBBBBB');
}
function clearPoly2(){
	poly.clearMapPolygonById(id2);
}
 //------------------------------------------------------------------------------------------------------------------------------
 /**
  *创建展示地图
  */
 
</script>
</body>
</html>
