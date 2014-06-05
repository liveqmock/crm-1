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
