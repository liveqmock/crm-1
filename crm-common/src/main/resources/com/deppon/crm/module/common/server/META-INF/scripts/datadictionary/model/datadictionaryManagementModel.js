//数据字典维护Model
Ext.define('datadictionaryManagementModel',{
	id:'datadictionaryManagementModel',
	extend:'Ext.data.Model',
	fields:[  {name: 'codeType'            //编码类型
		    },{name: 'codeTypeDesc'        //编码类型描述
		    },{name: 'code'                //编码
			},{name: 'codeDesc'            //编码描述
			},{name: 'status'              //状态
			},{name: 'codeSeq'             //代码序列
			},{name: 'language'            //语言
			},{name: 'empcode'             //修改人人工号
			},{name: 'empname'             //修改人姓名
			},{name: 'modifyDate'          //最后一次修改时间
			},{name: 'id'                  //id
			}]
});

//数据字典维护Model
Ext.define('datadictionaryheadModel',{
	id:'datadictionaryheadModel',
	extend:'Ext.data.Model',
	fields:[  {name: 'codeType'            //编码类型
		    },{name: 'codeTypeDesc'        //编码类型描述
		    },{name: 'empcode'             //修改人工号
		    },{name: 'empname'             //修改人姓名
		    },{name: 'modifyDate'          //修改时间
		    },{name: 'id'                  //id
			}]
});
