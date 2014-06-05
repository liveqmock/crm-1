var url = "http://gis.deppon.com/gis-biz/biz-destination/stationSearch.action"
	+ (location.search?location.search:'') +'&parentSrc='+ location;
 var ifrm = document.createElement('iframe');
 ifrm.setAttribute('id','mapWin');
 ifrm.setAttribute('name','mapWin');
 ifrm.setAttribute('width','1300px');
 ifrm.setAttribute('height','900px');
 ifrm.setAttribute('frameborder','0');
 ifrm.setAttribute('scrolling','yes');
 ifrm.setAttribute('src',url);
 document.body.appendChild(ifrm);
 var checkForMessages=function(){
 	if(location.hash == null || location.hash == ""){return;}
 	clearInterval(si);
 	window.opener.returnValue = location.hash;
	window.close();
 }
 var si =  setInterval(checkForMessages, 200);