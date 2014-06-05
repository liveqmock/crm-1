/**
 * 虚拟职位-角色model
 * 20131128
 * 肖红叶
 */
Ext.define('VPositionToRoleModel', {
    extend : 'Ext.data.Model',
    fields : [{//虚拟职位id
        name : 'virtualPositionId',
        type : 'string'
    },{ //虚拟职位名称
        name : 'virtualPositionName',
        type : 'string'
    },{//对应角色
        name : 'roleDesc',
        type : 'string'
    },{//虚拟岗位描述
    	name:'virtualPositionDesc',
    	type:'string'
    }]
}); 

/**
 * 角色model
 * 20131128
 * 肖红叶
 */
Ext.define('RoleModel', {
    extend : 'Ext.data.Model',
    fields : [{//角色id
        name : 'id',
        type : 'string'
    },{//角色名称
        name : 'roleName',
        type : 'string'
    },{//角色描述
        name : 'roleDesc',
        type : 'string'
    }]
}); 

/**
 * 虚拟职位model
 * 20131128
 * 肖红叶
 */
Ext.define('VirtualPositionModel', {
    extend : 'Ext.data.Model',
    fields : [{//虚拟职位id
        name : 'id',
        type : 'string'
    },{//虚拟职位名称
        name : 'virtualPositionName',
        type : 'string'
    },{//虚拟职位描述
        name : 'desc',
        type : 'string'
    }]
}); 