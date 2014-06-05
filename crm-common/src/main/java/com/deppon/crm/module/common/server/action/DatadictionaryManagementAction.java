package com.deppon.crm.module.common.server.action;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.server.manager.IDetailManager;
import com.deppon.crm.module.common.server.manager.IHeadManager;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.common.shared.domain.DetailVo;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;

public class DatadictionaryManagementAction extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IHeadManager headManager;
	private IDetailManager detailManager;
	private List<DetailVo>  datedictionaryManagementList;
	private List<Head>  datedictionaryHeadList;
	private DetailVo detailVo=new DetailVo();
	private Head head=new Head();
	private Detail detail=new Detail();
	private String fcodetype;
	private String fcodetypedesc;
	private String fcode;
	private String fcodedesc;
	private String language;
	private int fcodeseq;
	private String id;
	private boolean updatestatus;
	/**
	 * 根据查询条件，查询数据字典（head，detail）的数据信息
	 * @return
	 */
	public String querydetailbyconditions(){
		detailVo.setCodeType(fcodetype.toUpperCase());    //默认转换为大写
		detailVo.setCodeTypeDesc(fcodetypedesc);
		if(fcode!=null){
			detailVo.setCode(fcode.toUpperCase());            //默认转换为大写
		}
		detailVo.setCodeDesc(fcodedesc);
		datedictionaryManagementList=headManager.queryHeadDetail(detailVo,start,limit);
		totalCount = Long.valueOf(headManager.getHeadDetailCount(detailVo));
		return SUCCESS;
	}
	/**
	 * 根据条件查询数据字典头（Head）信息
	 * @return
	 */
    public String queryheadall(){
    	head.setCodeType(fcodetype.toUpperCase());        //默认转换为大写
    	head.setCodeTypeDesc(fcodetypedesc);
    	datedictionaryHeadList=headManager.queryHeadAll(head,start,limit); 
    	totalCount = Long.valueOf(headManager.getAllHeadCount(head));
		return SUCCESS;
	}
    
    /**
	 * 添加数据字典detail信息
	 * @param 8个可变参数
	 * @return
	 */
    public String insertdetail(){   
    	detail.setParentId("0");    //默认为0，无实际意义
    	detail.setCodeType(fcodetype.toUpperCase());      
    	detail.setCode(fcode.toUpperCase()); //自动转换为大写  
    	detail.setCodeDesc(fcodedesc);
    	detail.setCodeSeq(fcodeseq);
    	detail.setLanguage("zh_CN");   //默认为中文
    	detail.setCreateUser(UserContext.getCurrentUser().getId()); //获得当前登录人的姓名
    	detail.setModifyUser(UserContext.getCurrentUser().getId()); //最后修改人
    	detailManager.insertDetail(detail); //执行新增操作
		return SUCCESS;
	}
    
    /**
	 * 修改数据字典detail信息
	 * @param 5个可变参数
	 * @return
	 */
    public String updatedetailnew(){
    	updatestatus=false;
    	if(id!=null&&id!=""){
    		detail.setId(id);
        	detail.setCodeType(fcodetype.toUpperCase());         
        	detail.setCode(fcode.toUpperCase()); //自动转换为大写  
        	detail.setCodeDesc(fcodedesc);
        	detail.setCodeSeq(fcodeseq);
        	detail.setLanguage(language);   //默认为中文
        	detail.setModifyUser(UserContext.getCurrentUser().getId()); //最后修改人
        	updatestatus=detailManager.updateDetailnew(detail); //执行新增操作
    	}
		return SUCCESS;
	}
    
    /**
	 * 删除数据字典detail信息
	 * @param 2个可变参数
	 * @return
	 */
    public String deldetail(){
    	updatestatus=false;
    	if(id!=null&&id!=""){
    		detail.setId(id);
        	updatestatus=detailManager.deleteDetail(detail); //执行删除操作
    	}
		return SUCCESS;
	}
    
    /**
	 * 修改数据字典Head信息
	 * @param 2个可变参数
	 * @return
	 */
    public String updateHead(){
    	updatestatus=false;
    	Date dt=new Date();     //获取当前时间
    	if(id!=null&&id!=""){
    		head.setId(id);
    		head.setCodeType(fcodetype.toUpperCase());
    		head.setCodeTypeDesc(fcodetypedesc);
        	head.setModifyUser(UserContext.getCurrentUser().getId()); //最后修改人
        	head.setModifyDate(dt);
        	updatestatus=headManager.updateHead(head); //执行新增操作
    	}
		return SUCCESS;
	}
    
    /**
	 * 删除数据字典Head信息
	 * @param id
	 * @return
	 */
    public String delHead(){
    	updatestatus=false;
    	if(id!=null&&id!=""){
    		head.setId(id);
        	updatestatus=headManager.delHead(head); //执行新增操作
    	}
		return SUCCESS;
	}
    /**
	 * 新增数据字典Head信息
	 * @param id
	 * @return
	 */
    public String insertHead(){
    	updatestatus=false;
    	if(fcodetype!=null&&fcodetype!=""&&fcodetypedesc!=null&&fcodetypedesc!=""){
    		head.setCodeType(fcodetype.toUpperCase());
    		head.setCodeTypeDesc(fcodetypedesc);
    		head.setModifyUser(UserContext.getCurrentUser().getId()); //最后修改人
        	head.setCreateUser(UserContext.getCurrentUser().getId()); //创建人
        	updatestatus=headManager.insertHead(head); //执行新增操作
    	}
		return SUCCESS;
	}
    
    /**
	 * 根据  编码类型  字段查询数据字典（detail）的数据信息，来判断是否有关联
	 * @return
	 */
	public String querydetailbycodetype(){
		limit=-1;
		detailVo.setCodeType(fcodetype.toUpperCase());    //默认转换为大写
		datedictionaryManagementList=headManager.queryHeadDetail(detailVo,start,limit); 
		return SUCCESS;
	}

	public void setHeadManager(IHeadManager headManager) {
		this.headManager = headManager;
	}


	public void setDetailVo(DetailVo detailVo) {
		this.detailVo = detailVo;
	}

	public List<DetailVo> getDatedictionaryManagementList() {
		return datedictionaryManagementList;
	}

	public void setDatedictionaryManagementList(
			List<DetailVo> datedictionaryManagementList) {
		this.datedictionaryManagementList = datedictionaryManagementList;
	}

	public void setFcodetype(String fcodetype) {
		this.fcodetype = fcodetype;
	}

	public void setFcodetypedesc(String fcodetypedesc) {
		this.fcodetypedesc = fcodetypedesc;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	public void setFcodedesc(String fcodedesc) {
		this.fcodedesc = fcodedesc;
	}
	public List<Head> getDatedictionaryHeadList() {
		return datedictionaryHeadList;
	}
	public void setDatedictionaryHeadList(List<Head> datedictionaryHeadList) {
		this.datedictionaryHeadList = datedictionaryHeadList;
	}
	public void setDetailManager(IDetailManager detailManager) {
		this.detailManager = detailManager;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setFcodeseq(int fcodeseq) {
		this.fcodeseq = fcodeseq;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isUpdatestatus() {
		return updatestatus;
	}
	public void setUpdatestatus(boolean updatestatus) {
		this.updatestatus = updatestatus;
	}
}
