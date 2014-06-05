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

var UserInfo={};
function initDeptAndUserInfoForShowMessage(isHongKong){
    var url=null;
    if(Ext.isEmpty(isHongKong)){
        url='../common/queryUserInfo.action';
    }else{
        url='../common/queryUserInfo.action?isHongKong='+isHongKong;
    }
    Ext.Ajax.request({
        url : url,
        async:false,
        success:function(response){
            var result = Ext.decode(response.responseText);
            UserInfo=result.user;
            return UserInfo;
        },
        failure:function(response){
            var result = Ext.decode(response.responseText);
//          Ext.MessageBox.alert("提示",result.message);
            MessageUtil.showMessage(result.message);
        }
    });
};

//错误
MessageUtil.showErrorMes = function(message,fun) {
    //判断环境来确认上报地址
    var HtmlAddress = null;
    host = window.location.hostname;
    if(host!='crm.deppon.com'){
        HtmlAddress = 'http://192.168.17.103:8080/itsm/question.do';
    }else{
        HtmlAddress = 'http://lms.deppon.com.cn:9901/itsm/question.do';
    }
    //初始化用户信息
    initDeptAndUserInfoForShowMessage();
    var len = message.length;
    Ext.Msg.show({
        title:'CRM提醒您:',
        width:110+len*15,
        msg:'<div id="message">'+message+'<br><a target="_blank", href="'+HtmlAddress+'?psnCode='+UserInfo.empCode+'&system=CRM%E7%B3%BB%E7%BB%9F">上报IT服务台</a></div>',
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
