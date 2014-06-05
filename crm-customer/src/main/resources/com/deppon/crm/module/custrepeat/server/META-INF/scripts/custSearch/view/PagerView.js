/**
 * 分页声明
 */
Ext.define('Ext.ux.PageComboResizer', {
	extend:Object
  	,pageSizes: [5, 10, 15, 20, 25, 30, 50]
  	,prefixText: '每页显示'
  	,postfixText: '记录'
  	,constructor: function(config){
		Ext.apply(this, config);
		this.callParent(arguments);
  	}
  	,init : function(pagingToolbar) {
    	var ps = this.pageSizes;
    	
    	var combo = Ext.create('Ext.form.ComboBox',{
  				typeAhead: true
  				,triggerAction:'all'
		  		,lazyRender:true
		  		,mode: 'local'
		  		,width:45
		  		,store: ps
  				,listeners: {
    				select: function(c, r, s){
				      	pagingToolbar.store.pageSize =r[0].data.field1;
				      	pagingToolbar.store.loadPage(pagingToolbar.store.currentPage);
		 			}
				}
		});
		Ext.iterate(this.pageSizes, function(ps) {
		  	if (ps==pagingToolbar.store.pageSize) {
		    	combo.setValue (ps);return;
		  	}
		});
		//将控件放到刷新控件的后面
		var inputIndex  =  pagingToolbar.items.indexOf(pagingToolbar.items.get('refresh'));
		pagingToolbar.insert(++inputIndex,'-');
	    pagingToolbar.insert(++inputIndex, this.prefixText);
	    pagingToolbar.insert(++inputIndex, combo);
	    pagingToolbar.insert(++inputIndex, this.postfixText);
	    pagingToolbar.on({beforedestroy: function(){combo.destroy();}});
	}
});


/*
 * Fssc.PagingToolbar
 */
Ext.define('Crm.PagingToolbar', {
	extend : 'Ext.PagingToolbar',
	alias : 'widget.basepaging',
	displayInfo : true,
	beforePageText:'第',
	displayMsg : '显示记录 {0} - {1} / {2}',
	emptyMsg : "没有记录",
	initComponent : function() {
		this.items = this.getItems();
		this.callParent(arguments);
	},
	moveSelect : function() {
		var me = this;
		me.store.pageSize = me.child('#selectItem').getValue();
		me.moveFirst();
	},
	getItems : function() {
		var me = this;
		return [ {
			xtype : 'combo',
			fieldLabel : '每页',
			queryMode : 'local',
			labelWidth : 40,
			width : 90,
			itemId : 'selectItem',
			value : me.store.pageSize,
			displayField : 'value',
			valueField : 'key',
			store : {
				xtype : 'store',
				fields : [ 'key', 'value' ],
				data : [ 
					{key : 10,value : '10'},
					{key : 20,value : '20'},
					{key : 25,value : '25'},
					{key : 50,value : '50'} 
				]
			},
			listeners : {
				scope : me,change : me.moveSelect
			}
		} ];
	}
});