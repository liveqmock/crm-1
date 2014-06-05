/**
 * <p>
 * Description:<br />
 * </p>
 * @title QueryCondition.java
 * @package com.deppon.crm.module.marketing.shared.domain.visualMarket
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-17
 */
package com.deppon.crm.module.marketing.shared.domain.visualMarket;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Description:可视化营销查询条件<br />
 * </p>
 * @title QueryCondition.java
 * @package com.deppon.crm.module.marketing.shared.domain.visualMarket
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-17
 */
public class QueryCondition implements Cloneable{
	//部门id
	private String deptId;
	//固定客户编码
	private String custNumber;
	//地图标记
	private String mapMakerStatus;
	//固定客户类型列表(客户等级)
	private String[] custDegree;
	//非固定客户客户类型
	private String psCustType;
	//回访状态列表
	private String[] returnVisitStatus;
	//收入恢复进度列表
	private String[] incomeRestoreRate;
	//超期时长列表
	private String[] exceedTime;
	//客户行业列表
	private String[] custTrade;
	//客户标签id列表
	private String[]custLabel;
	//当月收入列表
	private String[] monthlyIncome;
	//发货票数列表
	private String[] sendGoodsNumber;
	//货量潜力列表
	private String[] goodsPotential;
	//客户来源列表
	private String[] custResource;
	//排序类型
	private String sortType;
	//正反排序标志位
	private String sortMark;
	//是否查询当天新增客户
	private boolean isSearchTodayCust;
	//客户id
	private String custId;
	//开始记录数
	private int startRecord;
	//结束记录数
	private int endRecord;
	//查询总条数
	private int limitRecord;
	//查询收入恢复进度时是否查为空的情况
	private boolean isSearchGoodsPotentialNull;
	//开始创建时间
	private Date createBeginTime;
	//结束创建时间
	private Date createEndTime;
    /******list******/
	//收入恢复进度列表
	private List<Map<String, String>> incomeRestoreRateList;
	//超期时长列表
	private List<Map<String, String>> exceedTimeList;
	//当月收入列表
	private List<Map<String, String>> monthlyIncomeList;
	//发货票数列表
	private List<Map<String, String>> sendGoodsNumberList;
	/**
	 * 
	 * <p>
	 * Description:设置是否查询当天客户<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-5-24
	 * @return
	 * boolean
	 */
	public boolean isSearchTodayCust() {
        return isSearchTodayCust;
    }
	/**
	 * 
	 * <p>
	 * Description:得到是否查询当天客户<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-5-24
	 * @param isSearchTodayCust
	 * void
	 */
    public void setSearchTodayCust(boolean isSearchTodayCust) {
        this.isSearchTodayCust = isSearchTodayCust;
    }
    /**
	 * @param sortMark the sortMark to get
	 */
	public String getSortMark() {
		return sortMark;
	}
	/**
	 * @param sortMark the sortMark to set
	 */
	public void setSortMark(String sortMark) {
		this.sortMark = sortMark;
	}
	/**
	 * @param mapMakerStatus the mapMakerStatus to get
	 */
	public String getMapMakerStatus() {
		return mapMakerStatus;
	}
	/**
	 * @param mapMakerStatus the mapMakerStatus to set
	 */
	public void setMapMakerStatus(String mapMakerStatus) {
		this.mapMakerStatus = mapMakerStatus;
	}
	/**
	 * @param deptId the deptId to get
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @param custNumber the custNumber to get
	 */
	public String getCustNumber() {
		return custNumber;
	}
	/**
	 * @param custNumber the custNumber to set
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	/**
	 * @param psCustType the psCustType to get
	 */
	public String getPsCustType() {
		return psCustType;
	}
	/**
	 * @param psCustType the psCustType to set
	 */
	public void setPsCustType(String psCustType) {
		this.psCustType = psCustType;
	}
	/**
	 * @param sortType the sortType to get
	 */
	public String getSortType() {
		return sortType;
	}
	/**
	 * @param sortType the sortType to set
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	/**
	 * @param custDegreeList the custDegreeList to get
	 */
	/**
	 * <p>
	 * Description:returnVisitStatus<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public String[] getReturnVisitStatus() {
		return returnVisitStatus;
	}
	/**
	 * <p>
	 * Description:returnVisitStatus<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public void setReturnVisitStatus(String[] returnVisitStatus) {
		this.returnVisitStatus = returnVisitStatus;
	}
	/**
	 * <p>
	 * Description:incomeRestoreRate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public String[] getIncomeRestoreRate() {
		return incomeRestoreRate;
	}
	/**
	 * <p>
	 * Description:incomeRestoreRate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public void setIncomeRestoreRate(String[] incomeRestoreRate) {
		this.incomeRestoreRate = incomeRestoreRate;
	}
	/**
	 * <p>
	 * Description:exceedTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public String[] getExceedTime() {
		return exceedTime;
	}
	/**
	 * <p>
	 * Description:exceedTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public void setExceedTime(String[] exceedTime) {
		this.exceedTime = exceedTime;
	}
	/**
	 * <p>
	 * Description:custTrade<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public String[] getCustTrade() {
		return custTrade;
	}
	/**
	 * <p>
	 * Description:custTrade<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public void setCustTrade(String[] custTrade) {
		this.custTrade = custTrade;
	}
	/**
	 * <p>
	 * Description:custLabel<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public String[] getCustLabel() {
		return custLabel;
	}
	/**
	 * <p>
	 * Description:custLabel<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public void setCustLabel(String[] custLabel) {
		this.custLabel = custLabel;
	}
	/**
	 * <p>
	 * Description:monthlyIncome<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public String[] getMonthlyIncome() {
		return monthlyIncome;
	}
	/**
	 * <p>
	 * Description:monthlyIncome<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public void setMonthlyIncome(String[] monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	/**
	 * <p>
	 * Description:sendGoodsNumber<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public String[] getSendGoodsNumber() {
		return sendGoodsNumber;
	}
	/**
	 * <p>
	 * Description:sendGoodsNumber<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public void setSendGoodsNumber(String[] sendGoodsNumber) {
		this.sendGoodsNumber = sendGoodsNumber;
	}
	/**
	 * <p>
	 * Description:goodsPotential<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public String[] getGoodsPotential() {
		return goodsPotential;
	}
	/**
	 * <p>
	 * Description:goodsPotential<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public void setGoodsPotential(String[] goodsPotential) {
		this.goodsPotential = goodsPotential;
	}
	/**
	 * <p>
	 * Description:custResource<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public String[] getCustResource() {
		return custResource;
	}
	/**
	 * <p>
	 * Description:custResource<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public void setCustResource(String[] custResource) {
		this.custResource = custResource;
	}
	/**
	 * <p>
	 * Description:custDegree<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public String[] getCustDegree() {
		return custDegree;
	}
	/**
	 * <p>
	 * Description:custDegree<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public void setCustDegree(String[] custDegree) {
		this.custDegree = custDegree;
	}

	/**
	 * 
	 * <p>
	 * Description:得到收入恢复进度<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-5-24
	 * @return
	 * List<Map<String,Integer>>
	 */
	public List<Map<String, String>> getIncomeRestoreRateList() {
		return incomeRestoreRateList;
	}
	/**
	 * 
	 * <p>
	 * Description:设置收入恢复进度<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-5-24
	 * @param incomeRestoreRateList
	 * void
	 */
	public void setIncomeRestoreRateList(
			List<Map<String, String>> incomeRestoreRateList) {
		this.incomeRestoreRateList = incomeRestoreRateList;
	}
	/**
	 * 
	 * <p>
	 * Description:得到超期时长<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-5-24
	 * @return
	 * List<Map<String,String>>
	 */
	public List<Map<String, String>> getExceedTimeList() {
		return exceedTimeList;
	}
	/**
	 * 
	 * <p>
	 * Description:设置超期时长<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-5-24
	 * @param exceedTimeList
	 * void
	 */
	public void setExceedTimeList(List<Map<String, String>> exceedTimeList) {
		this.exceedTimeList = exceedTimeList;
	}
	/**
	 * 
	 * <p>
	 * Description:得到MonthlyIncomeList<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-5-24
	 * @return
	 * List<Map<String,String>>
	 */
	public List<Map<String, String>> getMonthlyIncomeList() {
		return monthlyIncomeList;
	}
	/**
	 * 
	 * <p>
	 * Description:设置monthlyIncomeList<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-5-24
	 * @param monthlyIncomeList
	 * void
	 */
	public void setMonthlyIncomeList(List<Map<String, String>> monthlyIncomeList) {
		this.monthlyIncomeList = monthlyIncomeList;
	}
	/**
	 * 
	 * <p>
	 * Description:得到sendGoodsNumberList<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-5-24
	 * @return
	 * List<Map<String,String>>
	 */
	public List<Map<String, String>> getSendGoodsNumberList() {
		return sendGoodsNumberList;
	}
	/**
	 * 
	 * <p>
	 * Description:设置sendGoodsNumberList<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-5-24
	 * @param sendGoodsNumberList
	 * void
	 */
	public void setSendGoodsNumberList(
			List<Map<String, String>> sendGoodsNumberList) {
		this.sendGoodsNumberList = sendGoodsNumberList;
	}
	/**
	 * 
	 * <p>
	 * Description:得到custId<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-5-24
	 * @return
	 * String
	 */
    public String getCustId() {
        return custId;
    }
    /**
     * 
     * <p>
     * Description:设置custId<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-24
     * @param custId
     * void
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }
    /**
     * 
     * <p>
     * Description:得到startRecord<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-24
     * @return
     * int
     */
    public int getStartRecord() {
        return startRecord;
    }
    /**
     * 
     * <p>
     * Description:设置startRecord<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-24
     * @param startRecord
     * void
     */
    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }
    /**
     * 
     * <p>
     * Description:得到EndRecord<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-24
     * @return
     * int
     */
    public int getEndRecord() {
        return endRecord;
    }
    /**
     * 
     * <p>
     * Description:设置EndRecord<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-24
     * @param endRecord
     * void
     */
    public void setEndRecord(int endRecord) {
        this.endRecord = endRecord;
    }
    /**
     * 
     * <p>
     * Description:得到LimitRecord<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-24
     * @return
     * int
     */
    public int getLimitRecord() {
        return limitRecord;
    }
    /**
     * 
     * <p>
     * Description:设置LimitRecord<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-24
     * @param limitRecord
     * void
     */
    public void setLimitRecord(int limitRecord) {
        this.limitRecord = limitRecord;
    }
    /**
     * 
     * <p>
     * Description:得到isSearchRestoreRatNull<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-6-7
     * @return
     * boolean
     */
    public boolean isSearchGoodsPotentialNull() {
        return isSearchGoodsPotentialNull;
    }
    /**
     * 
     * <p>
     * Description:设置isSearchRestoreRatNull<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-6-7
     * @param isSearchRestoreRatNull
     * void
     */
    public void setSearchGoodsPotentialNull(boolean isSearchGoodsPotentialNull) {
        this.isSearchGoodsPotentialNull = isSearchGoodsPotentialNull;
    }
    /**
     * 重写clone方法
     */
    @Override
    public Object clone(){
        try{
            return super.clone();
        }catch(CloneNotSupportedException e){
            return null;
        }
    }
    
	/**
	 * @param createBeginTime the createBeginTime to get
	 */
	public Date getCreateBeginTime() {
		return createBeginTime;
	}
	/**
	 * @param createBeginTime the createBeginTime to set
	 */
	public void setCreateBeginTime(Date createBeginTime) {
		this.createBeginTime = createBeginTime;
	}
	/**
	 * @param createEndTime the createEndTime to get
	 */
	public Date getCreateEndTime() {
		return createEndTime;
	}
	/**
	 * @param createEndTime the createEndTime to set
	 */
	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}
}
