

/**
 * 大小城市查询结果 grid
 */
Ext.define('CityGrid',{
	extend:'SearchGridPanel'
	,initComponent:function(){
		var me = this;
		Ext.applyIf(me,{
			'store':Ext.create('CityStore')
			,'columns':me.getColumns()
		});
		me.dockedItems = me.getMyDockedItems();
		this.callParent(arguments);
	}
	,getColumns:function(){
		var me = this;
		return [
			{header:i18n('i18n.big_small_city.name')/*城市*/,width:70,dataIndex:'name'}
			,{header:i18n('i18n.big_small_city.createUName')/*创建人*/,width:100,dataIndex:'createUserName'}
			,{
				header:i18n('i18n.big_small_city.createTime')/*创建时间*/,width:130,dataIndex:'createTime'
				,renderer:function(value) {
					if(value != null){return Ext.Date.format(new Date(value),'Y-m-d H:i');
					}else{return null;}
				}
			}
			,{header:i18n('i18n.big_small_city.updateUName')/*修改人*/,width:100,dataIndex:'lastModifyUserName'}
			,{
				header:i18n('i18n.big_small_city.updateTime')/*修改时间*/,width:130,dataIndex:'lastUpdateTime'
				,renderer:function(value) {
					if(value != null){return Ext.Date.format(new Date(value),'Y-m-d H:i');
					}else{return null;}
				}
			}
		];
	}
		
	//分页条
	,getMyDockedItems :function(){
		var me = this;
		return [{
			xtype:'pagingtoolbar'
			,plugins:[Ext.create('Ext.ux.PageComboResizer')]
			,store : me.store
			,dock : 'bottom'
			,displayInfo :true
		}];
	}
});

Ext.onReady(function(){
	Ext.create('Ext.container.Viewport',{
		layout:'fit',items:[Ext.create('CityGrid')]
	});
});