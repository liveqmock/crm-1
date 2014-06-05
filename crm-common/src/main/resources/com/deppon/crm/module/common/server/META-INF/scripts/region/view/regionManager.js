var provinceListStore = null;/*省份集合 store 变量声明*/
Ext.onReady(function(){
	Ext.create('Ext.container.Viewport',{
		layout:'fit',items:[Ext.create('RegionTabPanel')]
	});
});
/**
 * 省份城市维护 tabPanel
 */
Ext.define('RegionTabPanel', {
    extend:'NormalTabPanel',
    provinceContainerPanel:null, //省份panel
    cityContainerPanel:null, //城市panel
    areaContainerPanel:null, //区域panel
    initComponent: function() {
        var me = this;
        //创建省份 panel
        me.provinceContainerPanel = Ext.create('ProvinceContainerPanel');
        me.cityContainerPanel = Ext.create('CityContainerPanel');
        me.areaContainerPanel = Ext.create('AreaContainerPanel');
        Ext.applyIf(me, {
            items: [
                me.provinceContainerPanel,me.cityContainerPanel,me.areaContainerPanel 
            ]
        });
        me.callParent(arguments);
    }
});


