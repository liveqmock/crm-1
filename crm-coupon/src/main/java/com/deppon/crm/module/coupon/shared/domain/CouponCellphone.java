package com.deppon.crm.module.coupon.shared.domain;


/**   
 * <p>
 * Description:发送优惠劵手机号码实体类<br />
 * </p>
 * @author ZhouYuan
 * @version 0.1 2012-11-21
 */
public class CouponCellphone implements Comparable<CouponCellphone> {
	//手机号
	private String cellphone;
	//手机号码是否有效 0无效 1有效
	private String validity;
	//发送状态 0未发送 1已发送
	private String sendStatus;
	/**
	 * @return type : return the property type.
	 */
	public String getCellphone() {
		//手机号
		return cellphone;
	}
	/**
	 * @return type : return the property type.
	 */
	public void setCellphone(String cellphone) {
		//手机号
		this.cellphone = cellphone;
	}

	/**
	 * @return type : return the property type.
	 */
	public String getValidity() {
		//手机号码是否有效 0无效 1有效
		return validity;
	}
	/**
	 * @return type : return the property type.
	 */
	public void setValidity(String validity) {
		//手机号码是否有效 0无效 1有效
		this.validity = validity;
	}
	/**
	 * @return type : return the property type.
	 */
	public String getSendStatus() {
		//发送状态 0未发送 1已发送
		return sendStatus;
	}
	/**
	 * @return type : return the property type.
	 */
	public void setSendStatus(String sendStatus) {
		//发送状态 0未发送 1已发送
		this.sendStatus = sendStatus;
	}
	//比较时使用
	@Override
	public int compareTo(CouponCellphone o) {
		
		return this.cellphone.compareTo(o.cellphone);
	}
	/**
	 * 重载父类的equals方法
	 */
	@Override
	public boolean equals(Object obj) {	
		//如果是CouponCellphone类型变量
		if(obj instanceof CouponCellphone){
			return this.cellphone.
					equals(((CouponCellphone)obj).getCellphone());
		//如果是String类型变量
		}else if( obj instanceof String ){
			return this.cellphone.equals(obj);	
		}
		return false;
	}
	/**
	 * 重载父类的hashCode方法
	 */
	@Override
	public int hashCode() {
		return this.cellphone.hashCode();
	}
	/**
	 * 重载父类的toString方法
	 */
	public String toString(){
		return "["+cellphone+":"+validity+"]";
	}
	
}
