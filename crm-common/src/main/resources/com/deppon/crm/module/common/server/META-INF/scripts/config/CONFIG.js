/**
 * @author:Rock
 * @time: 2012-10-15 
 * #description:common 板块Config
 */
CONFIG = (function(){
	var constants = {
			'TEST':false
	};
	if(window.location.protocol === 'file:')
	{
		constants.TEST = true;
	}
	
	return {
		get:function(name) {return constants[name];}
	};
})();

var CONFIGNAME = (function() {
    var constants = {
  			'account_linkManPhone'			:'linkManPhone',			//固定电话
  			'account_createUser'			:'createUser'				//创建人
    	};
        return {
           get: function(name) { return constants[name]; }
       };
   })();
