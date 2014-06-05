Ext.require(['Ext.data.*']);

Ext.onReady(function() {

    window.generateData = function(n, floor){
        var data = [],
            p = (Math.random() *  11) + 1,
            i;
            
        floor = (!floor && floor !== 0)? 20 : floor;
        
        for (i = 0; i < 12; i++) {
            data.push({
                name: Ext.Date.monthNames[i % 12],
                LoginAction: Math.floor(Math.max((Math.random() * 100000), floor)),
                SearchAction: Math.floor(Math.max((Math.random() * 100000), floor))
              
            });
        }
        return data;
    };
    window.store1 = Ext.create('Ext.data.JsonStore', {
        fields: ['name', 'LoginAction', 'SearchAction'],
        data: generateData()
    });
});
