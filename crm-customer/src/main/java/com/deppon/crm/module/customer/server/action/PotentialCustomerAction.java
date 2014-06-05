 package com.deppon.crm.module.customer.server.action;

import java.io.File;
import java.util.List;

import com.deppon.crm.module.common.file.action.FileUploadAction;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.foss.framework.exception.GeneralException;
/**
 * <p>
 * 潜客查询action<br />
 * </p>
 * @title PotentialCustomerAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-3-5
 */

public class PotentialCustomerAction extends FileUploadAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -2440408357612468077L;
	//manager
	private IMemberManager memberManager;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:潜客导入<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-23
	 * @return
	 * String
	 */
	@SuppressWarnings("serial")
	public String importPentent(){
		try {
			this.fileUpload();
			if(this.isSuccess()){
				List<FileInfo> fileInfo = this.getFileInfoList();
				if(fileInfo.size() != 1){
					throw new GeneralException("系统出错，没有发现传过来的文件","系统出错，没有发现传过来的文件",null,new Object[]{}) {
					}; 
				}
				String path = fileInfo.get(0).getSavePath();
				File excel = new File(path);
				if(!excel.isFile()){
					throw new GeneralException("系统出错，传过来的文件居然不见了","系统出错，传过来的文件居然不见了",null,new Object[]{}) {
					};
				}
				message = memberManager.importPotentialCustomer(excel);
			}
		} catch (GeneralException e) {
			message = messageBundle.getMessage(getLocale(),e.getErrorCode(),e.getErrorArguments());
		}
		return SUCCESS;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	
	

}
