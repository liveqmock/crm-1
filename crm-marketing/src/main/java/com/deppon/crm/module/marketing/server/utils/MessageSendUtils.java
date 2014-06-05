package com.deppon.crm.module.marketing.server.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.common.file.util.FileDlpUtil;
import com.deppon.crm.module.marketing.shared.domain.MessageSendConstance;
import com.deppon.crm.module.marketing.shared.domain.MessageSendPhone;
import com.deppon.crm.module.marketing.shared.domain.MessageSendTask;
import com.deppon.crm.module.marketing.shared.exception.MessageSendException;
import com.deppon.crm.module.marketing.shared.exception.MessageSendExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * Description:营销短信发送工具类<br />
 * </p>
 * @title MessageSendUtils.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author ZhouYuan
 * @version 0.1 2013-09-27
 */
public class MessageSendUtils {
	/**   
	 * <p>
	 * Description:从Excel中解析手机号码<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @param String filePath
	 * @param int startRow
	 * @param boolean firstRead
	 * @return Map<String,Object>
	 */
	@SuppressWarnings("serial")
	public static Map<String,Object> readMessagePhoneFile(String filePath,int startRow,boolean firstRead,String taskId,int readCount){
		List<MessageSendPhone> phoneList = null;
		boolean ifLast = false;
		File file = new File(filePath);
		if(file.isFile()){
			if(firstRead){
				filePath = FileDlpUtil.getDecryptFile(file.getPath());
				file = new File(filePath);
			}
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				MessageSendException e1 = new MessageSendException(MessageSendExceptionType.messageFileNotFound);
				//抛出异常
				throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
						new Object[] {}) {
				};
			}
			Workbook workbook = null;
			
			try {
				if(file.getName().matches("^.+\\.(?i)(xlsx)$")){
					//创建excel2007版本的工作簿
					workbook = new XSSFWorkbook(inputStream);
				}else if(file.getName().matches("^.+\\.(?i)(xls)$")){
					//创建excel2003版本的工作簿
					workbook = new HSSFWorkbook(inputStream);
				}
				if( workbook != null){
					Sheet sheet = workbook.getSheetAt(0);
					int totalRow = sheet.getLastRowNum();
					int readLimitNum = totalRow;
					if( totalRow-startRow >= readCount ){
						readLimitNum = readCount+startRow;
					}else{
						readLimitNum = totalRow;
					}
					Cell phoneCell  = null;
					MessageSendPhone phone = null;
					phoneList = new LinkedList<MessageSendPhone>();
					for( ; startRow <= readLimitNum ; startRow++){
						if( sheet.getRow(startRow)!=null && sheet.getRow(startRow).getCell(0)!=null){
							phone = new MessageSendPhone();
							phoneCell = sheet.getRow(startRow).getCell(0);
							String phoneNum = phoneCell.toString();
							if(phoneNum.length()>=15){
								phoneNum = phoneNum.substring(0,14)+"...";
							}
							phone.setPhone(phoneNum);
							phone.setValid(
									MessageSendValidator.checkPhoneNumber(phoneNum));
							phone.setTaskId(taskId);
							if( !StringUtils.isEmpty(phone.getValid()) && 
									phone.getValid().equals(MessageSendConstance.PHONE_VALIDATE_VALIDITY)){
								phone.setStatus(MessageSendConstance.PHONE_SEND_SENDED);
							}else{
								phone.setStatus(MessageSendConstance.PHONE_SEND_FAILE);
							}
							phone.setSendDate(new Date());
							phoneList.add(phone);
						}
					}
					if(startRow>=totalRow){
						ifLast = true;
					}
				}else{
					MessageSendException e1 = new MessageSendException(MessageSendExceptionType.messgeFileTypeError);
					//抛出异常
					throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
							new Object[] {}) {
					};
				}
			} catch (IOException e) {
				e.printStackTrace();
				MessageSendException e1 = new MessageSendException(MessageSendExceptionType.messageFileInputStreamError);
				//抛出异常
				throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
						new Object[] {}) {
				};
			}finally{
				try {
					if( inputStream != null){
						inputStream.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
					MessageSendException e1 = new MessageSendException(MessageSendExceptionType.messageFileInputStreamError);
					//抛出异常
					throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
							new Object[] {}) {
					};
				}
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("startRow", startRow);
			map.put("phoneList", phoneList);
			map.put("ifLast", ifLast);
			map.put("filePath",filePath);
			return map;
		}else{
			MessageSendException e1 = new MessageSendException(MessageSendExceptionType.messageSendTaskFilePathError);
			//抛出异常
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}		
	}
	/**   
	 * <p>
	 * Description:创建营销短信发送任务将发送状态和读取行数添加到短信模板<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @param String filePath
	 * @param int startRow
	 * @param boolean firstRead
	 * @return Map<String,Object>
	 */
	public static MessageSendTask setStatusAndStartRowToMessageTask(MessageSendTask mst,User user){
		//设置任务开始读取到行数 默认为1
		mst.setStartRow(MessageSendConstance.TASK_START_ROWNUM);
		//设置任务执行状态为未执行
		mst.setStatus(MessageSendConstance.TASK_STATUS_NOTSTART);
		//设置创建时间
		mst.setCreateDate(new Date());
		//设置创建人ID
		mst.setCreateUser(user.getId());
		//设置发送部门
		mst.setSendDept(user.getEmpCode().getDeptId().getStandardCode());
		//设置发送人工号
		mst.setSendUser(user.getEmpCode().getEmpCode());
		String fileName = mst.getFileName();
		if(!StringUtils.isEmpty(fileName)&&fileName.length()>45){
			fileName = fileName.substring(0, 45)+"...";
			mst.setFileName(fileName);
		}
		return mst;
	}
	/**   
	 * <p>
	 * Description:将短信实体转换为短信发送实体<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @param List<MessageSendPhone> phoneList
	 * @return List<SmsInformation>
	 */
	public static List<SmsInformation> convertMessageSendPhoneToSmsInformation(List<MessageSendPhone> phoneList,MessageSendTask mst){
		List<SmsInformation> smsList = null;
		if( phoneList != null && phoneList.size()>0){
			smsList = new LinkedList<SmsInformation>();
			SmsInformation smsInfo = null;
			for( MessageSendPhone msp : phoneList){
				if( MessageSendConstance.PHONE_VALIDATE_VALIDITY.equals(msp.getValid())){
					smsInfo = new SmsInformation();
					//设置业务类型(暂定优惠券类型)
					smsInfo.setMsgType(Constant.SMS_MARKETING_CODE);
					//设置手机号
					smsInfo.setMobile(msp.getPhone());
					//设置短信内容
					smsInfo.setMsgContent(mst.getMsgContent());
					//设置发送部门为IT服务部 
					smsInfo.setSendDept(MessageSendConstance.MSG_SEND_DEPT);
					//设置发送人
					smsInfo.setSender(mst.getSendUser());
					smsList.add(smsInfo);
				}
			}
		}else{
			MessageSendException e = new MessageSendException(MessageSendExceptionType.messageFileIsNull);
			//抛出异常
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return smsList;
	}
}
