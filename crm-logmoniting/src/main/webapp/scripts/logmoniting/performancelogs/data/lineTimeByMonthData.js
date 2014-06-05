Ext.require(['Ext.data.*']);

Ext.onReady(function() {

    window.generateData = function(n, floor){
        var data = [],
            p = (Math.random() *  11) + 1,
            i;
            
        floor = (!floor && floor !== 0)? 20 : floor;
        
        for (i = 0; i < (n || 12); i++) {
            data.push({
                name: Ext.Date.monthNames[i % 12],
                data1: Math.floor(Math.max((Math.random() * 100000), floor))
            });
        }
        return data;
    };
    window.MonthlyTimePillarStore = Ext.create('Ext.data.JsonStore', {
        fields: ['name', 'data1'],
        data: generateData()
    });
});
