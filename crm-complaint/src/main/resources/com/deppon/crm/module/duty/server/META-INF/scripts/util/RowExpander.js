/**
 * @class Deppon.grid.RowExpander
 * @extends Ext.AbstractPlugin
 * @ptype rowexpander
 *表格行双击后展开，展开后行体中是一个传入的组件对象
 *使用说明：
 *在定义列的时候，定义列的类型为"openwindowcolumn"类型
 *表格行可展开的插件
 *plugins: [{
 *	//定义行展开模式（单行与多行），默认是多行展开(值true)
 *	rowsExpander: false,
 *	//定义类型为扩展的rowexpander类型
 *	ptype: 'rowexpander',
 *	//行体内容（定义好的一个组件类名）
 *  rowBodyElement : 'Deppon.PackageDetailInfo.Panel'
 *}
 */
Ext.define('Crm.grid.RowExpander', {
    extend: 'Ext.AbstractPlugin',
	//依赖的类
    requires: [
        'Ext.grid.feature.RowBody',
        'Ext.grid.feature.RowWrap'
    ],
	//别名
    alias: 'plugin.rowexpander',
	
	/**
     * @cfg {String} rowBodyTpl
     * 定义一个行展开时的模板.
     */
    rowBodyTpl: null,

	/**
     * @cfg {String} rowBodyElement
     * 定义一个行展开时的元素.
     */
	rowBodyElement: null,
	
		/**
     * @cfg {Boolean} expandOnEnter
     * <tt>true</tt> 定义是否多行展开,默认是多行展开 (默认值 <tt>true</tt>).
     */
	rowsExpander: true,
		
    /**
     * @cfg {Boolean} expandOnEnter
     * <tt>true</tt> 当选择了行，键盘输入回车可以展开行信息 (默认值 <tt>true</tt>).
     */
    expandOnEnter: true,
    autoScroll:true,
    /**
     * @cfg {Boolean} expandOnDblClick
     * <tt>true</tt> 双击行的时候可以展开行信息 (默认值 <tt>true</tt>).
     */
    expandOnDblClick: true,
	
	//之前选择的行
	beforeRowIndex: null,
	
	//用于存放展开后元素的ID与展开后里面存放的元素
	elementIdMap : new Ext.util.HashMap(),

    /*
     * @cfg {Boolean} selectRowOnExpand
     * <tt>true</tt> 选择行的时候可以展开行信息(默认值 <tt>false</tt>).
     */
    //selectRowOnExpand: false,

    rowBodyTrSelector: '.x-grid-rowbody-tr',
    rowBodyHiddenCls: 'x-grid-row-body-hidden',
    rowCollapsedCls: 'x-grid-row-collapsed',
	
	/**
	 * 通过行的记录对象，获得展开行的行体信息
	 * @return 展开行的行体信息
	 */
	getRowBodyElement: function(record){
		if(this.rowBodyElement==null){
			return;
		}
		return this.elementIdMap.get(record.internalId+'-rowbody-element');
	},
	
	/**
	 * 获得表格展开行的行信息
	 * @return 被展开的行信息(如果是单行展开则返回一个展开行信息，如果是多行展开则返回展开行信息数组)
	 */
	getExpendRow: function(){
		if(this.rowBodyElement==null){
			return;
		}
		var view = this.view,
			nodes = view.getNodes()
			expendRows = new Array();
		for(var index=0;index<nodes.length;index++){
			var rowNode = nodes[index],
				row = Ext.get(rowNode);
			if (!row.hasCls(this.rowCollapsedCls)) {
				expendRows.push(row);
			}
		}
		if(this.rowsExpander == false){
			if(expendRows.length==1){
				return expendRows[0];
			}else{
				return null;
			}
		}
		return expendRows;
	},
	
	/**
	 * 通过行信息获得行绑定的记录
	 * @return record
	 */
	getRowRecord: function(rowNode){
		if(this.rowBodyElement==null){
			return;
		}
		var view = this.view,
			record = this.view.getRecord(rowNode);
		return record;
	},
	
	/**
	 * 获得表格内展开行的行体信息
	 * @return 展开行的行体信息(如果是单行展开则返回一个行体信息，如果是多行展开则返回行体信息数组)
	 */
	getExpendRowBody: function(){
		var expendRows = this.getExpendRow(),
			expendRowBodys = new Array();
		if(this.rowsExpander == false){
			var record = this.getRowRecord(expendRows);
			var rowBody = this.getRowBodyElement(record);
			return rowBody;
		}
		for(var index=0;index<expendRows.length;index++){
			var record = this.getRowRecord(expendRows[index]);
			var rowBody = this.getRowBodyElement(record);
			expendRowBodys.push(rowBody);
		}
		return expendRowBodys;
	},

    renderer: function(value, metadata, record, rowIdx, colIdx) {
        if (colIdx === 0) {
            metadata.tdCls = 'x-grid-td-expander';
        }
        return '<div class="x-grid-row-expander">&#160;</div>';
    },

    constructor: function() {
        this.callParent(arguments);
        var grid = this.getCmp();
        this.recordsExpanded = {};
        // <debug>
        if (!this.rowBodyTpl&&!this.rowBodyElement) {
            Ext.Error.raise("'rowBodyTpl' and 'rowBodyElement' of A property must configuration.");
        }
		var features = null;
        // </debug>
        // TODO: if XTemplate/Template receives a template as an arg, should
        // just return it back!
		if(this.rowBodyTpl){
			var rowBodyTpl = Ext.create('Ext.XTemplate', this.rowBodyTpl);
            features = [{
                ftype: 'rowbody',
				rowBodyTpl: this.rowBodyTpl,
                columnId: this.getHeaderId(),
                recordsExpanded: this.recordsExpanded,
                rowBodyHiddenCls: this.rowBodyHiddenCls,
                rowCollapsedCls: this.rowCollapsedCls,
                getAdditionalData: this.getRowBodyFeatureData,
                getRowBodyContents: function(data) {
                    return rowBodyTpl.applyTemplate(data);
                }
            },{
                ftype: 'rowwrap'
            }];
		}
		if(this.rowBodyElement){
			features = [{
                ftype: 'rowbody',
				rowBodyElement: this.rowBodyElement,
                columnId: this.getHeaderId(),
                recordsExpanded: this.recordsExpanded,
                rowBodyHiddenCls: this.rowBodyHiddenCls,
                rowCollapsedCls: this.rowCollapsedCls,
                getAdditionalData: this.getRowBodyFeatureData,
                getRowBodyContents: function(data) {
                    return "<div id='"+data+"' class='x-row-embedded'></div>";
                }
            },{
                ftype: 'rowwrap'
            }];
		}
        if (grid.features) {
            grid.features = features.concat(grid.features);
        } else {
            grid.features = features;
        }
    },
    init: function(grid) {
        this.callParent(arguments);
        //grid.headerCt.insert(0, this.getHeaderConfig());
        grid.on('render', this.bindView, this, {single: true});
		grid.on('sortchange', this.cleanElementIdMap, this);
    },
	//清空Map中的组件
	cleanElementIdMap: function(){
		for(var i=0;i<this.elementIdMap.getKeys().length;i++){
			//this.elementIdMap.getValues()[i].render(this.elementIdMap.getKeys()[i]);
			//cleanComponent(this.elementIdMap.getValues()[i]);
			var header = Ext.ComponentManager.get(this.elementIdMap.getKeys()[i]+'_header');
			//cleanComponent(header);
			Ext.ComponentManager.unregister(header);
			header = null;
			var view = this.view,
            ds   = view.store;
			for(var index=0;index<ds.data.length;index++){
				rowIdx = ds.getAt(index),
				rowNode = view.getNode(rowIdx),
				row = Ext.get(rowNode);
				if (!row.hasCls(this.rowCollapsedCls)) {
					row.addCls(this.rowCollapsedCls);
				}
			}
		}
		this.elementIdMap.clear();
	},
    getHeaderId: function() {
        if (!this.headerId) {
            this.headerId = Ext.id();
        }
        return this.headerId;
    },
    getRowBodyFeatureData: function(data, idx, record, orig, view) {
        var o = Ext.grid.feature.RowBody.prototype.getAdditionalData.apply(this, arguments),
            id = this.columnId;
        o.rowBodyColspan = o.rowBodyColspan;
		if(this.rowBodyTpl){
			o.rowBody = this.getRowBodyContents(data);
		}
		if(this.rowBodyElement){
			o.rowBody = this.getRowBodyContents(record.internalId+"-rowbody");
		}
        o.rowCls = this.recordsExpanded[record.internalId] ? '' : this.rowCollapsedCls;
        o.rowBodyCls = this.recordsExpanded[record.internalId] ? '' : this.rowBodyHiddenCls;
        o[id + '-tdAttr'] = ' valign="top" rowspan="2" ';
        if (orig[id+'-tdAttr']) {
            o[id+'-tdAttr'] += orig[id+'-tdAttr'];
        }
        return o;
    },
    bindView: function() {
        var view = this.getCmp().getView(),
            viewEl;

        if (!view.rendered) {
            view.on('render', this.bindView, this, {single: true});
        } else {
            viewEl = view.getEl();
            if (this.expandOnEnter) {
                this.keyNav = Ext.create('Ext.KeyNav', viewEl, {
                    'enter' : this.onEnter,
                    scope: this
                });
            }
            if (this.expandOnDblClick) {
                view.on('itemdblclick', this.onDblClick, this);
            }
            this.view = view;
        }
    },
    onEnter: function(e) {
        var view = this.view,
            ds   = view.store,
            sm   = view.getSelectionModel(),
            sels = sm.getSelection(),
            ln   = sels.length,
            i = 0,
            rowIdx;

        for (; i < ln; i++) {
            rowIdx = ds.indexOf(sels[i]);
            this.toggleRow(rowIdx);
        }
    },
	toggleRow: function(rowIdx) {
		//修改增加了是否单行展开的判断##################
		if(!this.rowsExpander){
//			if(this.beforeRowIndex!=null&&this.beforeRowIndex!=rowIdx){
//				var beforeRowNode = this.view.getNode(this.beforeRowIndex);
//					beforeRow = Ext.get(beforeRowNode),
//					beforeNextBd = Ext.get(beforeRow).down(this.rowBodyTrSelector),
//					beforeRecord = this.view.getRecord(beforeRowNode);
//				beforeRow.addCls(this.rowCollapsedCls);
//				beforeNextBd.addCls(this.rowBodyHiddenCls);
//				this.recordsExpanded[beforeRecord.internalId] = false;
//				this.view.fireEvent('collapsebody', beforeRowNode, beforeRecord, beforeNextBd.dom);
//			}
			this.beforeRowIndex = rowIdx;
		}
		//###############################################
        var rowNode = this.view.getNode(rowIdx),
            row = Ext.get(rowNode),
            nextBd = Ext.get(row).down(this.rowBodyTrSelector),
            record = this.view.getRecord(rowNode),
            grid = this.getCmp(),
			rowBodyId = record.internalId+'-rowbody',
			rowBodyElementId = record.internalId+'-rowbody-element';
        if (row.hasCls(this.rowCollapsedCls)) {
            row.removeCls(this.rowCollapsedCls);
            nextBd.removeCls(this.rowBodyHiddenCls);
            this.recordsExpanded[record.internalId] = true;
            this.view.fireEvent('expandbody', rowNode, record, nextBd.dom);
			if(this.rowBodyElement&&!this.elementIdMap.containsKey(rowBodyElementId)) {
				var rowBodyElement = Ext.create(this.rowBodyElement, {
					id : rowBodyElementId
				});
				Ext.Object.merge(rowBodyElement, {
					superGrid : grid,
					record : record
				});
				if(rowBodyElement&&(typeof rowBodyElement.setTipData)!='undefined'){
					rowBodyElement.setTipData(record);
				}
				//增加父表格列拖动的时候，表格展开中的表格不会随着被动
				rowBodyElement.on('resize', 
					function(rowBodyEl, width, height, eOpts){
						//console.log('resize');
						//console.log(rowBodyEl);
						rowBodyEl.getEl().on('DOMSubtreeModified',
							function(e, htmlEl, eOpts ){
								//删除"x-grid-table-resizer"这个class
								var extEl = Ext.get(htmlEl);
								var el = extEl.select('table.' + Ext.baseCSSPrefix + 'grid-table-resizer');
								el.each(function(childEl ,c ,index){
									if (childEl.hasCls(Ext.baseCSSPrefix + 'grid-table-resizer')){
										childEl.removeCls(Ext.baseCSSPrefix + 'grid-table-resizer');
										//console.log(childEl);
									}
								});
								
							});
					});
				rowBodyElement.render(rowBodyId);
				this.elementIdMap.add(rowBodyElement.id,rowBodyElement);
			}
			var rowBodyElement = this.elementIdMap.get(rowBodyElementId);
			//在定义行体的时候，装入的对象要如果要进行数据的bind，可以通过实现这个方法，进行实现
			if(rowBodyElement&&(typeof rowBodyElement.bindData)!='undefined'){
				rowBodyElement.bindData(record, grid, rowBodyElement);
			}
        } else {
            row.addCls(this.rowCollapsedCls);
            nextBd.addCls(this.rowBodyHiddenCls);
            this.recordsExpanded[record.internalId] = false;
            this.view.fireEvent('collapsebody', rowNode, record, nextBd.dom);
			var rowBodyElement = this.elementIdMap.get(rowBodyElementId);
			//在定义行体的时候，装入的对象如果在收缩的时候，要进行数据的unbind，可以通过实现这个方法，进行实现
			if(rowBodyElement&&(typeof rowBodyElement.unbindData)!='undefined'){
				rowBodyElement.unbindData(record, grid, rowBodyElement);
			}
        }
        this.view.up('gridpanel').invalidateScroller();
        if(!Ext.isEmpty(this.view.up('gridpanel').columns)){
       	 this.view.up('gridpanel').columns[1].setWidth(39);
            this.view.up('gridpanel').columns[1].setWidth(40);
        }
    },
    onDblClick: function(view, record, rowIdx, recordIndex, e) {
        this.toggleRow(rowIdx);
    }
});