/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponAction.java
 * @package com.deppon.crm.module.coupon.server.action 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
package com.deppon.crm.module.coupon.server.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.coupon.server.manager.ICouponManager;
import com.deppon.crm.module.coupon.server.utils.CouponUtils;
import com.deppon.crm.module.coupon.shared.domain.ConditionType;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphone;
import com.deppon.crm.module.coupon.shared.domain.CouponSearchCondition;
import com.deppon.crm.module.coupon.shared.domain.CouponTypeVo;
import com.deppon.crm.module.coupon.shared.domain.MarketPlaneVO;
import com.deppon.crm.module.coupon.shared.domain.SendCouponVO;
import com.deppon.crm.module.coupon.shared.exception.CouponException;
import com.deppon.crm.module.coupon.shared.exception.CouponExceptionType;
import com.deppon.crm.module.coupon.shared.exception.ExceptionUtils;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**   
 * <p>
 * Description: 优惠券Action（增、删、改 操作）<br />
 * </p>
 * @title CouponAction.java
 * @package com.deppon.crm.module.coupon.server.action 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
public class CouponAction extends AbstractAction {
	//excel文件上传
	private File upload;
	//excel文件上传的文件名
	private String uploadFileName;
	//excel文件上传的文件类型
	private String uploadContentType;
	//BEAN获得国际化信息对象
   	protected IMessageBundle messageBundle;
   	//文件上传失败提示消息
    protected String message;
	//下载模板和导出文件的文件流
    private InputStream inputStream;
    //优惠券Manage
	private ICouponManager couponManagerImpl;
	//优惠券Vo
	private MarketPlaneVO marketPlaneVO;
	//优惠券产品、订单来源、客户等级、客户行业类型
	private CouponTypeVo couponTypeVo;
	private String saveBj="0";
	//定义一个变量，标记处理短信发券页面中手机号的类型："清除无效"="CLEARINVALID"、"删除重复"="DELETEREPEAT"
	private String handleTelephone;
	//短信发券页面中批量导入的手机号实体列表
	private List<CouponCellphone> couponCellphoneList;
	//短信发券页面中根据营销计划id返回的优惠券基本信息实体
	private SendCouponVO couponBasicInfo;
	// 营销计划Id
	private String marketPlanID;
	// 优惠券查询条件实体
	private CouponSearchCondition couponSearchCondition;
	//导出优惠券查询Excel到服务器的文件名称
	private String fileName;
	//标记优惠券导出全部是否成功
	private boolean importSuccess;
	//重发短信的优惠券编码
	private String couponNumber;
	
	//短信发券批量导入模板的文件名
	public String templateName = "template.xlsx";

	/**
	 * 优惠券查询：导出全部，在服务器端生成一个Excel报表文件
	 * xiaohongye
	 */
	@JSON
	public String creatCouponReportExcel(){
		String filePath = null;
		try {
			//导出的文件名 规则:1970年1月1日到现在的毫秒数+.xlsx
			fileName = new String((System.currentTimeMillis()+".xlsx").getBytes(),"UTF-8");
			//文件路径 file.properties中的路径
			filePath = PropertiesUtil.getInstance().getProperty("excelExportFilePath");
		} catch (UnsupportedEncodingException e) {
			//导出失败的提示
			ExceptionUtils.createCouponException(CouponExceptionType.failureToExport);
		}
		//获得当前登录的User
		User user=(User)UserContext.getCurrentUser();
		//调用创建优惠券报表方法
		importSuccess = couponManagerImpl.creatCouponReportExcel(couponSearchCondition, fileName, filePath,user);	
		return SUCCESS;
	}
	
	/**
	 * 优惠券查询：导出全部，将服务器端生成的excel报表文件下载到本地
	 * xiaohongye
	 */
	public String exportCouponReportExcel(){
		String filePath = null;
		try {
			//导出的文件路径 同创建时的文件
			filePath = PropertiesUtil.getInstance().getProperty("excelExportFilePath");
			inputStream = new FileInputStream(new File(filePath, fileName));
		} catch (FileNotFoundException e) {
			//导出失败的提示
			ExceptionUtils.createCouponException(CouponExceptionType.failureToExport);
		}
		return SUCCESS;
	}
		
	/**
	 *短信发券：下载“批量上传”的模板到本地
	 * xiaohongye
	 */
	public String exportTemplateOfSendCoupon(){
		String filePath = null;
		try {
			//模板文件存放的路径
			filePath = PropertiesUtil.getInstance().getProperty("excelExportTemplatePath");
			inputStream = new FileInputStream(new File(filePath, templateName));
		} catch (FileNotFoundException e) {
			//模板文件未找到
			ExceptionUtils.createCouponException(CouponExceptionType.noDownloadTemplate);
		}
		return SUCCESS;
	}
	
	//TODO 文件导出成功后,删除服务器上的excel文件
	@JSON
	public String deleteCouponReportExcel(){	
		//文件路径
		String filePath = PropertiesUtil.getInstance().getProperty("excelExportFilePath");	
		File file = new File(filePath, fileName);
		//删除该文件
		file.delete();
		return SUCCESS;
	}
	
	/**
	 * 保存优惠券
	 * @return
	 */
	@JSON
	public String saveCoupon(){
	    //处理发到货线路要求数据
        CouponUtils.saveGoodsLineOperation(marketPlaneVO);
        //设置产品类型、订单来源、客户等级及客户行业
		Map<String,List<ConditionType>> map=couponManagerImpl.matchMarketPlanByConditionType(couponTypeVo);
		marketPlaneVO.getRuleCouponAuto().setConditionTypes(map.get("create"));
		marketPlaneVO.getCouponRule().setConditionTypes(map.get("use"));
		if(saveBj.equals("0")){//”0“ 代表启用规则  ”1“ 代表保存
 			couponManagerImpl.startMarketPlanCoupon(marketPlaneVO,this.getCurrentUser());
		}else{
			couponManagerImpl.createMarketPlanCoupon(marketPlaneVO,this.getCurrentUser());	
		}
		return SUCCESS;
	}
	
	/**
	 * Description:终止营销计划<br/>
	 * @return
	 */
	@JSON
	public String stopMarketPlan(){
		//调用终止方法
		couponManagerImpl.stopMarketPlan(marketPlanID, this.getCurrentUser());//终止
		return SUCCESS;
	}
	
	/**
	 * Description:删除营销计划<br/>
	 * @return
	 */
	@JSON
	public String deleteMarketPlan(){
		couponManagerImpl.deleteMarketPlan(marketPlanID);//删除
		return SUCCESS;
	}
	
	/**
	 * Description:查看详情界面-更新营销计划<br/>
	 * @return
	 */
	@JSON
	public String updateMarketPlan(){
	    //处理发到货线路要求数据
        CouponUtils.saveGoodsLineOperation(marketPlaneVO);
        //设置产品类型、订单来源、客户等级及客户行业
		Map<String,List<ConditionType>> map=couponManagerImpl.matchMarketPlanByConditionType(couponTypeVo);
		marketPlaneVO.getRuleCouponAuto().setConditionTypes(map.get("create"));
		marketPlaneVO.getCouponRule().setConditionTypes(map.get("use"));
		if(saveBj.equals("1")){//”0 代表启用规则  ”1“ 代表编辑后保存
 			couponManagerImpl.updateMarketPlan(marketPlaneVO,this.getCurrentUser());
		}else{
			couponManagerImpl.startMarketPlanByDetail(marketPlaneVO,this.getCurrentUser());	
		}
		return SUCCESS;
	}
	
	/**
	 * 上传excel文件，批量导入手机号
	 * xiaohongye
	 * @return
	 */
	public String uploadFile(){
		try{
			if(!(uploadContentType.equals("application/vnd.ms-excel"))&&!(uploadContentType.equals
					("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))){
				message = "只能批量导入excel类型的文件。批量导入之前，请先下载模板！";
				this.setSuccess(false);
				this.setException(true);
			}
			else if(upload.length()>10485760){
				message = "批量导入文件的大小不能超过10M！";
				this.setSuccess(false);
				this.setException(true);
			}
			else{
				couponCellphoneList = couponManagerImpl.batchImportCellphones(upload,uploadFileName);
				if(couponCellphoneList == null || couponCellphoneList.size() == 0){
					message = "批量导入失败！";
					this.setSuccess(false);
					this.setException(true);
				}
			}
		}catch(GeneralException e){
			message = "批量导入失败！";
			this.setSuccess(false);
			this.setException(true);
		}
		return SUCCESS;
	}
	
	/**
	 * 批量处理手机号：根据传入的参数不同，实现批量清除无效号码或删除重复手机号码
	 * xiaohongye
	 * 2012-11-23
	 */	
	@JSON
	public String batchHandleTelephones(){
		//清除无效号码
		if(handleTelephone.equals("CLEARINVALID")){
			couponCellphoneList = couponManagerImpl.cleanInvalidCellphones(couponCellphoneList);
		}
		//删除重复号码
		else if(handleTelephone.equals("DELETEREPEAT")){
			couponCellphoneList = couponManagerImpl.deleteRepeatCellphones(couponCellphoneList);
		}
		return SUCCESS;
	}
	
	/**
	 * 短信发券页面，单击发送按钮，将发送的手机号写入优惠券信息中
	 * 肖红叶
	 * 2012-11-26
	 */
	@JSON
	public String sendCouponByCellphones(){
		//获得当前登录的用户信息
		User user=(User)UserContext.getCurrentUser();
		//根据优惠券基本信息和手机号列表更新优惠券发送信息
		couponManagerImpl.sendCouponByCellphones(couponBasicInfo.getPlanNumber(), couponCellphoneList, user);
		return SUCCESS;
	}

	/**
	 * 优惠券查询：重发短信
	 * 肖红叶
	 * @return
	 */
	@JSON
	public String reSendCouponMsg(){
		//获得当前登录的用户信息
		User user=(User)UserContext.getCurrentUser();
		try{
			//调用重发短信方法
			couponManagerImpl.reSendCouponMsg(couponNumber, user);
		}catch(CouponException e){
			//如果重发失败输出错误信息
			message = messageBundle.getMessage(getLocale(),e.getErrorCode());
			this.setSuccess(false);
			this.setException(true);
		}
		return SUCCESS;
	}
	
	/**
	 * get、set方法区
	 * @param couponNumber
	 */
	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}
	
	public MarketPlaneVO getMarketPlaneVO() {
		return marketPlaneVO;
	}

	public void setMarketPlaneVO(MarketPlaneVO marketPlaneVO) {
		this.marketPlaneVO = marketPlaneVO;
	}
	
	public void setCouponManagerImpl(ICouponManager couponManagerImpl) {
		this.couponManagerImpl = couponManagerImpl;
	}
	
	public CouponTypeVo getCouponTypeVo() {
		return couponTypeVo;
	}

	public void setCouponTypeVo(CouponTypeVo couponTypeVo) {
		this.couponTypeVo = couponTypeVo;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

   	public void setMessageBundle(IMessageBundle messageBundle) {
   		this.messageBundle = messageBundle;
   	}

    public String getMessage() {
		return message;
	}
    
    // 获取当前用户
 	private User getCurrentUser(){
 		return (User) UserContext.getCurrentUser();
 	}
 	
 	public void setSaveBj(String saveBj) {
		this.saveBj = saveBj;
	}
 	
 	public void setHandleTelephone(String handleTelephone) {
		this.handleTelephone = handleTelephone;
	}

	public void setCouponCellphoneList(List<CouponCellphone> couponCellphoneList) {
		this.couponCellphoneList = couponCellphoneList;
	}

	public List<CouponCellphone> getCouponCellphoneList() {
		return couponCellphoneList;
	}
	
	public void setCouponBasicInfo(SendCouponVO couponBasicInfo) {
		this.couponBasicInfo = couponBasicInfo;
	}

	public SendCouponVO getCouponBasicInfo() {
		return couponBasicInfo;
	}

	public void setMarketPlanID(String marketPlanID) {
		this.marketPlanID = marketPlanID;
	}
	
	public CouponSearchCondition getCouponSearchCondition() {
		return couponSearchCondition;
	}

	public void setCouponSearchCondition(CouponSearchCondition couponSearchCondition) {
		this.couponSearchCondition = couponSearchCondition;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isImportSuccess() {
		return importSuccess;
	}
	
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	
}
