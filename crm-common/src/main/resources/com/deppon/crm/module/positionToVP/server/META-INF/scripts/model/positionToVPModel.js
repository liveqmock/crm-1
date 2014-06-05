//虚拟职位model
Ext.define('VPositionModel', {
    extend : 'Ext.data.Model',
    fields : [{
        //虚拟职位id
        name : 'id'
    },{
        //虚拟职位名称
        name : 'virtualPositionName',
        type : 'string'
    }, {
        //虚拟职位描述
        name : 'desc',
        type : 'string'
    }]
}); 

//标准职位model
Ext.define('PositionModel', {
    extend : 'Ext.data.Model',
    fields : [{
        //标准职位id
        name : 'pkEhrPosition'
    },{
        //标准职位名称
        name : 'jobName',
        type : 'string'
    },{
        //是否是右边数据
        name : 'isLeft',
        type : 'boolean',
        defaultValue : true
    },{
        //是否移动
        name : 'isMove',
        type : 'boolean',
        defaultValue : false
    }]
}); 