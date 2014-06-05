package com.deppon.crm.module.keycustomer.shared.domain;
/**
 * 
 * <p>
 * Description:准对大客户工作流管理中的条件校验<br />
 * </p>
 * @title KeyStatusVO.java
 * @package com.deppon.crm.module.keycustomer.shared.domain 
 * @author 106138
 * @version 0.1 2014-3-8
 */ 
public class KeyStatusVO {
	//是否存在大客户标记
	private Boolean fisKeyCutomer;
	//是否存在审批中的工作流中
	private Boolean fisapproved;
	//是否存在于有效的准入列表中
	private Boolean fisListIn;
	//是否存在于有效的退出列表中
	private Boolean fisListOut;
	//当前统计周期内是否满足联系3个月大于10000
	private Boolean fisAmountAchieve;
	//发货金额
	private String amountOfSignMent;
	//是否在疑似重复列表
	private Boolean fisCustExistsRepeat;
	//存在审批中的客户工作流(包括疑似重复)
	private Boolean fIsInCustomerapproved;
	/**
	 * <p>
	 * Description:fisKeyCutomer<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public Boolean getFisKeyCutomer() {
		return fisKeyCutomer;
	}
	/**
	 * <p>
	 * Description:fisKeyCutomer<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setFisKeyCutomer(Boolean fisKeyCutomer) {
		this.fisKeyCutomer = fisKeyCutomer;
	}
	/**
	 * <p>
	 * Description:fisapproved<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public Boolean getFisapproved() {
		return fisapproved;
	}
	/**
	 * <p>
	 * Description:fisapproved<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setFisapproved(Boolean fisapproved) {
		this.fisapproved = fisapproved;
	}
	/**
	 * <p>
	 * Description:fisListIn<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public Boolean getFisListIn() {
		return fisListIn;
	}
	/**
	 * <p>
	 * Description:fisListIn<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setFisListIn(Boolean fisListIn) {
		this.fisListIn = fisListIn;
	}
	/**
	 * <p>
	 * Description:fisListOut<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public Boolean getFisListOut() {
		return fisListOut;
	}
	/**
	 * <p>
	 * Description:fisListOut<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setFisListOut(Boolean fisListOut) {
		this.fisListOut = fisListOut;
	}
	/**
	 * <p>
	 * Description:fisAmountAchieve<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public Boolean getFisAmountAchieve() {
		return fisAmountAchieve;
	}
	/**
	 * <p>
	 * Description:fisAmountAchieve<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setFisAmountAchieve(Boolean fisAmountAchieve) {
		this.fisAmountAchieve = fisAmountAchieve;
	}
	/**
	 * <p>
	 * Description:amountOfSignMent<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getAmountOfSignMent() {
		return amountOfSignMent;
	}
	/**
	 * <p>
	 * Description:amountOfSignMent<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setAmountOfSignMent(String amountOfSignMent) {
		this.amountOfSignMent = amountOfSignMent;
	}
	/**
	 * <p>
	 * Description:fisCustExistsRepeat<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public Boolean getFisCustExistsRepeat() {
		return fisCustExistsRepeat;
	}
	/**
	 * <p>
	 * Description:fisCustExistsRepeat<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public void setFisCustExistsRepeat(Boolean fisCustExistsRepeat) {
		this.fisCustExistsRepeat = fisCustExistsRepeat;
	}
	/**
	 * <p>
	 * Description:fIsInCustomerapproved<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public Boolean getfIsInCustomerapproved() {
		return fIsInCustomerapproved;
	}
	/**
	 * <p>
	 * Description:fIsInCustomerapproved<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public void setfIsInCustomerapproved(Boolean fIsInCustomerapproved) {
		this.fIsInCustomerapproved = fIsInCustomerapproved;
	}
	
}
