/**
 * 分页声明
 */
Ext.define('Ext.ux.PageComboResizer', {
	extend:Object
  	,pageSizes: [5, 10, 15, 20, 25, 30, 50]
  	,prefixText: i18n('i18n.pager.prefixText')
  	,postfixText: i18n('i18n.pager.postfixText')
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