/*
 * 五公里地图打印
 * @author rock
 */
//Ext.onReady(function(){});
var map 		= null,							//地图
	point		= null,
	exitsPoint	= window.opener.datas;

map = new BMap.Map('mapCtn');
point = new BMap.Point(116.404, 39.915);		// 创建点坐标
map.centerAndZoom(point,14);					// 初始化地图,设置中心点坐标和地图级别。
map.addControl(new BMap.NavigationControl());
map.enableScrollWheelZoom();                  	// 启用滚轮放大缩小。
map.enableKeyboard();					 		// 启用键盘操作。
showAllMark(exitsPoint);
var fnPrint = function(){
	window.print();
};
if(exitsPoint.bool){
	setTimeout(fnPrint,1000);
}else{
	document.getElementById('printBtn').style.display = 'block';
};

