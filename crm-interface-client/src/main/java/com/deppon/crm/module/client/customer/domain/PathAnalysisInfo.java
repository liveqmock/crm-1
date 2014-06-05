package com.deppon.crm.module.client.customer.domain;

/**
 * 目的站,
 * 上月金额,
 * 上月同期金额,
 * 上月同期货量,
 * 上月同期票数,
 * 上月同期卡航票数
 * 上月同期城运票数,
 * 上月同期精准汽运（长）票数,
 * 上月同期精准汽运（短）票数,
 * 上月同期空运票数,
 * 本月金额,
 * 本月同期金额,
 * 本月同期货量,
 * 本月同期票数,
 * 本月同期卡航票数,
 * 本月同期城运票数,
 * 本月同期精准汽运（长）票数,
 * 本月同期精准汽运（短）票数,
 * 本月同期空运票数
 * @author davidcun @2012-4-13
 */
public class PathAnalysisInfo {
	
	//会员编码
	private String memberNumber;
	
	// 发货城市、目的城市
	private String city;
	// 上月金额
	private double lastMonthAmount;
	//上月同期金额
	private double lastMonthSameTimeAmount;
	//当月同期金额
	private double currentMonthSameTimeAmount;
	// 上月货量
	private double lastMonthvolume;
	// 当月货量
	private double currentMonthvolume;
	// 上月票数
	private int lastMonthVotes;
	// 当月票数
	private int currentMonthvotes;
	// 上月卡航票数
	private int lastMonthTruckVotes;
	// 当月月卡航票数
	private int currentMonthTruckVotes;
	// 上月城际票数
	private int lastMonthCityVotes;
	// 当月城际票数
	private int currentMonthCityVotes;
	// 上月精准汽运（长）票数
	private int lastMonthlongTruckVotes;
	// 当月精准汽运（长）票数
	private int currentMonthlongTruckVotes;
	// 上月精准汽运（短）票数
	private int lastMonthshortTruckVotes;
	// 当月精准汽运（短）票数
	private int currentMonthshortTruckVotes;
	// 上月空运票数
	private int lastAirTransVotes;
	// 当月空运票数
	private int currentAirTransVotes;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public double getLastMonthAmount() {
		return lastMonthAmount;
	}
	public void setLastMonthAmount(double lastMonthAmount) {
		this.lastMonthAmount = lastMonthAmount;
	}
	public double getLastMonthvolume() {
		return lastMonthvolume;
	}
	public void setLastMonthvolume(double lastMonthvolume) {
		this.lastMonthvolume = lastMonthvolume;
	}
	public double getCurrentMonthvolume() {
		return currentMonthvolume;
	}
	public void setCurrentMonthvolume(double currentMonthvolume) {
		this.currentMonthvolume = currentMonthvolume;
	}
	public int getLastMonthVotes() {
		return lastMonthVotes;
	}
	public void setLastMonthVotes(int lastMonthVotes) {
		this.lastMonthVotes = lastMonthVotes;
	}
	public int getCurrentMonthvotes() {
		return currentMonthvotes;
	}
	public void setCurrentMonthvotes(int currentMonthvotes) {
		this.currentMonthvotes = currentMonthvotes;
	}
	public int getLastMonthTruckVotes() {
		return lastMonthTruckVotes;
	}
	public void setLastMonthTruckVotes(int lastMonthTruckVotes) {
		this.lastMonthTruckVotes = lastMonthTruckVotes;
	}
	public int getCurrentMonthTruckVotes() {
		return currentMonthTruckVotes;
	}
	public void setCurrentMonthTruckVotes(int currentMonthTruckVotes) {
		this.currentMonthTruckVotes = currentMonthTruckVotes;
	}
	public int getLastMonthCityVotes() {
		return lastMonthCityVotes;
	}
	public void setLastMonthCityVotes(int lastMonthCityVotes) {
		this.lastMonthCityVotes = lastMonthCityVotes;
	}
	public int getCurrentMonthCityVotes() {
		return currentMonthCityVotes;
	}
	public void setCurrentMonthCityVotes(int currentMonthCityVotes) {
		this.currentMonthCityVotes = currentMonthCityVotes;
	}
	public int getLastMonthlongTruckVotes() {
		return lastMonthlongTruckVotes;
	}
	public void setLastMonthlongTruckVotes(int lastMonthlongTruckVotes) {
		this.lastMonthlongTruckVotes = lastMonthlongTruckVotes;
	}
	public int getCurrentMonthlongTruckVotes() {
		return currentMonthlongTruckVotes;
	}
	public void setCurrentMonthlongTruckVotes(int currentMonthlongTruckVotes) {
		this.currentMonthlongTruckVotes = currentMonthlongTruckVotes;
	}
	public int getLastMonthshortTruckVotes() {
		return lastMonthshortTruckVotes;
	}
	public void setLastMonthshortTruckVotes(int lastMonthshortTruckVotes) {
		this.lastMonthshortTruckVotes = lastMonthshortTruckVotes;
	}
	public int getCurrentMonthshortTruckVotes() {
		return currentMonthshortTruckVotes;
	}
	public void setCurrentMonthshortTruckVotes(int currentMonthshortTruckVotes) {
		this.currentMonthshortTruckVotes = currentMonthshortTruckVotes;
	}
	public int getLastAirTransVotes() {
		return lastAirTransVotes;
	}
	public void setLastAirTransVotes(int lastAirTransVotes) {
		this.lastAirTransVotes = lastAirTransVotes;
	}
	public int getCurrentAirTransVotes() {
		return currentAirTransVotes;
	}
	public void setCurrentAirTransVotes(int currentAirTransVotes) {
		this.currentAirTransVotes = currentAirTransVotes;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public double getCurrentMonthSameTimeAmount() {
		return currentMonthSameTimeAmount;
	}
	public void setCurrentMonthSameTimeAmount(double currentMonthSameTimeAmount) {
		this.currentMonthSameTimeAmount = currentMonthSameTimeAmount;
	}
	public double getLastMonthSameTimeAmount() {
		return lastMonthSameTimeAmount;
	}
	public void setLastMonthSameTimeAmount(double lastMonthSameTimeAmount) {
		this.lastMonthSameTimeAmount = lastMonthSameTimeAmount;
	}
	
}