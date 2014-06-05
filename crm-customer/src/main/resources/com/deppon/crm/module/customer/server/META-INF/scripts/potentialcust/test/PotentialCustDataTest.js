/**
*潜在客户Data层
*/
/**
* 城市Store
*/
Ext.define('CityStore',{
	extend:'Ext.data.Store',
	model:'CityModel',
	data:[{id:'1',cityName:'上海'},
	      {id:'2',cityName:'北京'},
	      {id:'3',cityName:'深圳'},
	      {id:'4',cityName:'安徽'},
	      {id:'5',cityName:'湖南'},
	      {id:'6',cityName:'山东'},
	      {id:'7',cityName:'山西'}]
});

Ext.define('PotentialCustDataTest',{
	extend:'PotentialCustBasicDataTest',
	cityStore:null,
	savePotentialCust:function(){
		return true;
	},
	getCityStore:function(){
		if(this.cityStore == null){
			this.cityStore = Ext.create('CityStore');
		}
		return this.cityStore;
	}
});
