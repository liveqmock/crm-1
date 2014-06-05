//在当前城市下做本地搜索
function localSearchByCity(map,address,city,customerType){
	times=0;
	var localSearch = new BMap.LocalSearch(city, {
			renderOptions:{autoViewport:true,forceLocal:true,selectFirstResult:true},pageCapacity:20
	});
    localSearch.setSearchCompleteCallback(function(response){
    	if(localSearch.getStatus() == BMAP_STATUS_SUCCESS){
    		map.clearOverlays();
			point = response.getPoi(0).point;
		    var mark;
            var icon = new BMap.Icon(judgeUserType(customerType),new BMap.Size(30,40));
			map.centerAndZoom(point,15)
            if(response.getNumPois()>1){//地址解析出多条
         	   var marker = new BMap.Marker(point,{icon:icon});
                map.addOverlay(marker);
         	   Ext.getCmp("showAddress").expand();
         	   pages = response.getNumPages();//返回总的页数
                currentPage = response.getCurrentNumPois();//返回当前页数
				   var store=Ext.getCmp('mGrid').getStore();
				   store.removeAll();
				   for (var i = 0; i < response.getCurrentNumPois(); i ++){
					var m=new MapAddressListModel(
						{'address':response.getPoi(i).title+i18n('i18n.PotentialCustManagerView.searchEndTime')+response.getPoi(i).address,'point':response.getPoi(i).point.lng+','+response.getPoi(i).point.lat});
					store.add(m);
				   }
			       if(times==0){
				    var marker = new BMap.Marker(point,{icon:icon});
                     map.addOverlay(marker);
					Ext.getCmp('pre').addListener('click',function(){
					   localSearch.gotoPage((currentPage>0)?--currentPage:currentPage);
				    });
				    Ext.getCmp('next').addListener('click',function(){
					   localSearch.gotoPage((currentPage<pages-1)?++currentPage:pages-1);
				    });
				 }
               times++;				
            }else{
         	   var marker = new BMap.Marker(point,{icon:icon});
                map.addOverlay(marker);
                var store=Ext.getCmp('mGrid').getStore();
                store.removeAll();
				for (var i = 0; i < response.getCurrentNumPois(); i ++){
					var m=new MapAddressListModel(
						{'address':response.getPoi(i).title+i18n('i18n.PotentialCustManagerView.searchEndTime')+response.getPoi(i).address,'point':response.getPoi(i).point.lng+','+response.getPoi(i).point.lat});
					store.add(m);
				}
                }
					marker.addEventListener('mouseup',function(e){
	            	var opts ={
	            			title : '<p style="font-weight:900">'+currentRecord.data.customerName+'</p>'  // 信息窗口标题
	                };
	            	var htmlString =i18n('i18n.map.addressP')+
	            	currentRecord.data.linkmanPreferAddress+i18n('i18n.map.phoneP')+
	            	currentRecord.data.linkmanPhone+i18n('i18n.map.mobilePhoneS')+
	            	currentRecord.data.linkmanMobile+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></p>';
	            	var infoWindow = new BMap.InfoWindow(htmlString, opts);  // 创建信息窗口对象
	            	map.openInfoWindow(infoWindow,new BMap.Point(point.lng,point.lat));			 
		    });
   }else{
	   var myGeo = new BMap.Geocoder();
	  // 将地址解析结果显示在地图上,并调整地图视野 
	   myGeo.getPoint(address, function(point){
	   if (point) {
		    map.clearOverlays();
            var icon = new BMap.Icon(judgeUserType(customerType),new BMap.Size(30,40));
			map.centerAndZoom(point,15);
			var marker = new BMap.Marker(point,{icon:icon});
            map.addOverlay(marker);
            marker.addEventListener('mouseup',function(e){
            	var opts ={
            			title : '<p style="font-weight:900">'+currentRecord.data.customerName+'</p>'  // 信息窗口标题
                };
            	var htmlString =i18n('i18n.map.addressP')+
            	currentRecord.data.linkmanPreferAddress+i18n('i18n.map.phoneP')+
            	currentRecord.data.linkmanPhone+i18n('i18n.map.mobilePhoneS')+
            	currentRecord.data.linkmanMobile+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></p>';
            	var infoWindow = new BMap.InfoWindow(htmlString, opts);  // 创建信息窗口对象
            	map.openInfoWindow(infoWindow,new BMap.Point(point.lng,point.lat));
            	
            });
            var store=Ext.getCmp('mGrid').getStore();
            store.removeAll();
            var m=new MapAddressListModel(
					{'address':address,'point':point.lng+','+point.lat});
				store.add(m);
	   }else{
		   MessageUtil.showErrorMes(i18n('i18n.fiveKilometreMap.canNotFindTheAddress'));
	   }
	 },city);
    }  
 });
 localSearch.search(address);
}
//根据用户类别返回它的标注类型
function judgeUserType(customerType){
	var imgType;
	if(customerType=='PC_CUSTOMER'){//潜客(黄色)//
		imgType='../scripts/marketing/map/images/yellow.png';
	}
	else if(customerType=='SC_CUSTOMER'){//散客(绿色)//
		imgType='../scripts/marketing/map/images/green.png';
	}
	else{
		//会员(红色)
		imgType='../scripts/marketing/map/images/red.png';
	}
	return imgType;
}
//在当前地图上做本地搜索
function localSearchByMap(map,address,customerType){
	times=0;
	var localSearch = new BMap.LocalSearch(map, {
			renderOptions:{autoViewport:true,forceLocal:true,selectFirstResult:true},pageCapacity:20
	});
    localSearch.setSearchCompleteCallback(function(response){
    	if(localSearch.getStatus() == BMAP_STATUS_SUCCESS){
    		map.clearOverlays();
			point = response.getPoi(0).point;
            var icon = new BMap.Icon(judgeUserType(customerType),new BMap.Size(30,40));
			map.centerAndZoom(point,15)
            if(response.getNumPois()>1){//地址解析出多条
         	   var marker = new BMap.Marker(point,{icon:icon});
                map.addOverlay(marker);
         	   Ext.getCmp("showAddress").expand();
         	   pages = response.getNumPages();//返回总的页数
                currentPage = response.getCurrentNumPois();//返回当前页数
				   var store=Ext.getCmp('mGrid').getStore();
				   store.removeAll();
				   for (var i = 0; i < response.getCurrentNumPois(); i ++){
					var m=new MapAddressListModel(
						{'address':response.getPoi(i).title+i18n('i18n.PotentialCustManagerView.searchEndTime')+response.getPoi(i).address,'point':response.getPoi(i).point.lng+','+response.getPoi(i).point.lat});
					store.add(m);
				   }
			       if(times==0){
				    var marker = new BMap.Marker(point,{icon:icon});
                     map.addOverlay(marker);
					Ext.getCmp('pre').addListener('click',function(){
					   localSearch.gotoPage((currentPage>0)?--currentPage:currentPage);
				    });
				    Ext.getCmp('next').addListener('click',function(){
					   localSearch.gotoPage((currentPage<pages-1)?++currentPage:pages-1);
				    });
				 }
               times++;				
            }else{
         	   var marker = new BMap.Marker(point,{icon:icon});
                map.addOverlay(marker);
                var store=Ext.getCmp('mGrid').getStore();
                store.removeAll();
				for (var i = 0; i < response.getCurrentNumPois(); i ++){
					var m=new MapAddressListModel(
						{'address':response.getPoi(i).title+i18n('i18n.PotentialCustManagerView.searchEndTime')+response.getPoi(i).address,'point':response.getPoi(i).point.lng+','+response.getPoi(i).point.lat});
					store.add(m);
				}
            }
					marker.addEventListener('mouseup',function(e){
	            	var opts ={
	            			title : '<p style="font-weight:900">'+currentRecord.data.customerName+'</p>'  // 信息窗口标题
	                };
	            	var htmlString =i18n('i18n.map.addressP')+
	            	currentRecord.data.linkmanPreferAddress+i18n('i18n.map.phoneP')+
	            	currentRecord.data.linkmanPhone+i18n('i18n.map.mobilePhoneS')+
	            	currentRecord.data.linkmanMobile+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></p>';
	            	var infoWindow = new BMap.InfoWindow(htmlString, opts);  // 创建信息窗口对象
	            	map.openInfoWindow(infoWindow,new BMap.Point(point.lng,point.lat));			 
		    });
   }else{
	   //地址精确解析
	    var myGeo = new BMap.Geocoder();
		  // 将地址解析结果显示在地图上,并调整地图视野 
		   myGeo.getPoint(address, function(point){
		   if (point) {
			    map.clearOverlays();
	            var icon = new BMap.Icon(judgeUserType(customerType),new BMap.Size(30,40));
				map.centerAndZoom(point,15);
				var marker = new BMap.Marker(point,{icon:icon});
	            map.addOverlay(marker);
	            marker.addEventListener('mouseup',function(e){
	            	var opts ={
	            			title : '<p style="font-weight:900">'+currentRecord.data.customerName+'</p>'  // 信息窗口标题
	                };
	            	var htmlString =i18n('i18n.map.addressP')+
	            	currentRecord.data.linkmanPreferAddress+i18n('i18n.map.phoneP')+
	            	currentRecord.data.linkmanPhone+i18n('i18n.map.mobilePhoneS')+
	            	currentRecord.data.linkmanMobile+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></p>';
	            	var infoWindow = new BMap.InfoWindow(htmlString, opts);  // 创建信息窗口对象
	            	map.openInfoWindow(infoWindow,new BMap.Point(point.lng,point.lat));
	            });
	            var store=Ext.getCmp('mGrid').getStore();
                store.removeAll();
	            var m=new MapAddressListModel(
						{'address':address,'point':point.lng+','+point.lat});
			    store.add(m); 
		   }else{
			   MessageUtil.showErrorMes(i18n('i18n.fiveKilometreMap.canNotFindTheAddress'));
		   }
		 }); 
   }  
 });
  localSearch.search(address);
}
//展示标注 
function showMark(lng,lat){
	//alert(currentRecord.data.customerName);//记录
    map.clearOverlays();
    point = new BMap.Point(lng,lat);
	var myIcon = new BMap.Icon(judgeUserType(currentRecord.data.customerType), new BMap.Size(30,40));  
	map.centerAndZoom(point,15)
    var marker = new BMap.Marker(point,{icon:myIcon});
    map.addOverlay(marker);
    map.centerAndZoom(new BMap.Point(lng,lat),14);
	marker.addEventListener('mouseup',function(e){
		var opts ={
    			title : '<p style="font-weight:900">'+currentRecord.data.customerName+'</p>'  // 信息窗口标题
        };
    	var htmlString =i18n('i18n.map.addressP')+
    	currentRecord.data.linkmanPreferAddress+i18n('i18n.map.phoneP')+
    	currentRecord.data.linkmanPhone+i18n('i18n.map.mobilePhoneS')+
    	currentRecord.data.linkmanMobile+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></p>';
    	var infoWindow = new BMap.InfoWindow(htmlString, opts);  // 创建信息窗口对象
    	map.openInfoWindow(infoWindow,new BMap.Point(point.lng,point.lat));	
	});
}
//全选展示标注
function showAllMark(exitsPoint){
	map.clearOverlays();
	for(var i=0;i<exitsPoint.length;i++){
		if(i==0){
		  map.centerAndZoom(new BMap.Point(exitsPoint[i].data.lng,exitsPoint[i].data.lat),14);	
		}
		var lng = exitsPoint[i].data.lng;
		var lat = exitsPoint[i].data.lat;
		addDeptMarker(i,new BMap.Point(lng,lat));
	}
}
//向地图添加标注
function addDeptMarker(i,point){
	var icon = new BMap.Icon(judgeUserType(exitsPoint[i].data.customerType),new BMap.Size(30,40));
	var marker = new BMap.Marker(new BMap.Point(exitsPoint[i].data.lng,exitsPoint[i].data.lat),{icon:icon});
	map.addOverlay(marker);
	marker.addEventListener('mouseup',function(e){
		 showInfoWindow(i);
	});
}
//显示infowindow
function showInfoWindow(i){
	var lng=exitsPoint[i].data.lng;
	var lat = exitsPoint[i].data.lat;
	var opts ={
			title : '<p style="font-weight:900">'+exitsPoint[i].data.customerName+'</p>'  // 信息窗口标题
    };
	var htmlString =i18n('i18n.map.addressP')+
	exitsPoint[i].data.linkmanPreferAddress+i18n('i18n.map.phoneP')+
	exitsPoint[i].data.linkmanPhone+i18n('i18n.map.mobilePhoneS')+
	exitsPoint[i].data.linkmanMobile+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></p>';
	var infoWindow = new BMap.InfoWindow(htmlString, opts);  // 创建信息窗口对象
	map.openInfoWindow(infoWindow,new BMap.Point(lng,lat));
	
}