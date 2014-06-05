/**
 * 可视化营销打印功能实现区
 */
//点击打印按钮触发的事件
var fnPrint = function(){
	document.getElementById("printBtn").style.display = "none";
	window.print();
	//延迟1秒钟出现打印按钮
	setTimeout(function(){
		document.getElementById("printBtn").style.display = "block";
	},1000);
};

/**
 * 新建一个容器，用以存放待打印的地图信息
 */
Ext.onReady(function(){
	Ext.create('Ext.panel.Panel',{
		id:'printVisualMarketPanel',
		width:800,
		height:600,
		html:window.opener.document.getElementById('map').innerHTML,
		border:0,
		renderTo:'mapCtn'
	});
});