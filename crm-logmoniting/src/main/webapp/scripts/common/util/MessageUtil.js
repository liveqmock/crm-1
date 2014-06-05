MessageUtil = function(){};

//警告
MessageUtil.showMessage = function(message,fun) {
	var len = message.length;
	Ext.Msg.show({
	    title:'CRM提醒您:',
	    msg:message,
	    //cls:'mesbox',
	    width:110+len*15,
	    msg:'<div id="message">'+message+'</div>',
	    buttons: Ext.Msg.OK,
	    icon: Ext.MessageBox.WARNING,
	    callback:function(e){
	    	if(!Ext.isEmpty(fun)){
	    		if(e=='ok'){
		    		fun();
		    	}
	    	}
	    }
	});
	
};
//是和否选择
MessageUtil.showQuestionMes = function(message,fun,beforeCloseFn) {
	var len = message.length;
	var msgWin = Ext.Msg
	msgWin.show({
	    title:'CRM提醒您:',
	    width:110+len*15,
	    msg:'<div id="message">'+message+'</div>',
	    buttons: Ext.Msg.YESNO,
	    icon:Ext.MessageBox.QUESTION,
	    callback:function(e){
	    	if(!Ext.isEmpty(fun)){
		    		fun(e);
	    	}
	    }
	});
	if(!Ext.isEmpty(beforeCloseFn)){
		msgWin.on('beforeclose',function(){
			beforeCloseFn();
		});
	}
};
//信息
MessageUtil.showInfoMes = function(message,fun) {
	var len = message.length;
	Ext.Msg.show({
	    title:'CRM提醒您:',
	    width:110+len*15,
	    msg:'<div id="message">'+message+'</div>',
	    buttons: Ext.Msg.OK,
	    icon: Ext.MessageBox.INFO,
	    callback:function(e){
	    	if(!Ext.isEmpty(fun)){
	    		if(e=='ok'){
		    		fun();
		    	}
	    	}
	    }
	});
};
//错误
MessageUtil.showErrorMes = function(message,fun) {
	var len = message.length;
	Ext.Msg.show({
	    title:'CRM提醒您:',
	    width:110+len*15,
	    msg:'<div id="message">'+message+'</div>',
	    buttons: Ext.Msg.OK,
	    icon: Ext.MessageBox.ERROR,
	    callback:function(e){
	    	if(!Ext.isEmpty(fun)){
	    		if(e=='ok'){
		    		fun();
		    	}
	    	}
	    }
	});
};
