/**   
 * <p>
 * Description:定义文件上传下载操作<br />
 * </p>
 * @title UploadFile.java
 * @package com.deppon.crm.recompense.action 
 * @author 苏玉军
 * @version 0.1 2012-2-15
 */
package com.deppon.crm.module.recompense.shared.domain;

import java.io.File;
import java.io.Serializable;

/**   
 * <p>
 * Description:文件上传类型定义<br />
 * </p>
 * @title UploadFile.java
 * @package com.deppon.crm.recompense.action 
 * @author 苏玉军
 * @version 0.1 2012-2-15
 */

public class UploadFile implements Serializable {
	private static final long serialVersionUID = -5168467447813823267L;
	private String title;//表单其他参数，如文件标题
	private File upload;//上传的文件
	//下面两个一定要写对应的setter，不然可以上传但会报错,而且保存名字要用到，务必写下。
	private String uploadContentType;//上传文件类型
	private String uploadFileName;//上传文件名
	//getter and setter 省略。。
	/**
	 * <p>
	 * Description:title<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * <p>
	 * Description:title<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * <p>
	 * Description:upload<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public File getUpload() {
		return upload;
	}
	/**
	 * <p>
	 * Description:upload<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setUpload(File upload) {
		this.upload = upload;
	}
	/**
	 * <p>
	 * Description:uploadContentType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getUploadContentType() {
		return uploadContentType;
	}
	/**
	 * <p>
	 * Description:uploadContentType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	/**
	 * <p>
	 * Description:uploadFileName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}
	/**
	 * <p>
	 * Description:uploadFileName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	
}
