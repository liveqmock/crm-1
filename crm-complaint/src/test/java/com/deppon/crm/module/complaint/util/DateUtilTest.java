package com.deppon.crm.module.complaint.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

import com.deppon.crm.module.complaint.server.util.DateUtil;

public class DateUtilTest {
	@Test
	public void testGetAddDayDate(){
		Date date = null;
		int days = 10;
		DateUtil.getAddDayDate(date, days);
		date = DateUtil.getCurrentDate();
		DateUtil.getAddDayDate(date, days);
		DateUtil.getCurrentDate("");
	}
	
	@Test
	public void testGetDate(){
		DateUtil.getDate();
		Date date = null;
		DateUtil.getDate(date);
		DateUtil.getDate(date, DateUtil.DATE_YYYYMMDD);
		DateUtil.getCurrentDate(DateUtil.DATE_YYMMDD_NOLINK);
		DateUtil.getCurrentDate();
		DateUtil.getTime(date);
		date = new Date();
		DateUtil.getDate(date);
		DateUtil.getDate(date, DateUtil.DATETIME_YYYYMMDD_HHMMSS);
		DateUtil.getCurrentDateTime();
		DateUtil.getCurrentDateTimeNoLink();
		DateUtil.getTime(date);
	}
	
	@Test
	public void testGetFirstDayOfCycle(){
		int model = 1;
		int type;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse("2012-12-25");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		
		for(int i = 0;i<=7;i++){
			type = i;
			DateUtil.getFirstDayOfCycle(type, model, date);
		}
		model = 2;
		for(int i = 0;i<=7;i++){
			type = i;
			DateUtil.getFirstDayOfCycle(type, model, date);
		}
		model = 1;
		type = 2;
		try {
			date = sdf.parse("2012-3-22");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		
		type = 3;
		try {
			date = sdf.parse("2012-1-22");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		
		try {
			date = sdf.parse("2012-4-22");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		
		try {
			date = sdf.parse("2012-7-22");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		
		try {
			date = sdf.parse("2012-12-22");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		
		type = 5;
		try {
			date = sdf.parse("2012-5-22");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		
		try {
			date = sdf.parse("2012-5-8");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		
		try {
			date = sdf.parse("2012-9-12");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		
		model = 2;
		type = 2;
		try {
			date = sdf.parse("2012-3-12");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		type = 3;
		try {
			date = sdf.parse("2012-2-12");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		try {
			date = sdf.parse("2012-5-12");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		try {
			date = sdf.parse("2012-8-12");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		type = 4;
		try {
			date = sdf.parse("2012-1-12");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		type = 5;
		try {
			date = sdf.parse("2012-7-12");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		
		try {
			date = sdf.parse("2012-10-8");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		try {
			date = sdf.parse("2012-10-18");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		try {
			date = sdf.parse("2012-1-2");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		DateUtil.getFirstDayOfCycle(type, model, date);
		
		Calendar calendar = Calendar.getInstance();
		try {
			date = sdf.parse("2012-10-2");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		calendar.setTime(date);
		type = 1;
		for(int i = 1;i<=3;i++){
			model = i;
			DateUtil.getFirstDayOfCycle(calendar, type, model);
		}
		model = 3;
		for(int i = 1;i<=8;i++){
			type = i;
			DateUtil.getFirstDayOfCycle(calendar, type, model);
		}
		type = 2;
		try {
			date = sdf.parse("2012-5-2");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		calendar.setTime(date);
		DateUtil.getFirstDayOfCycle(calendar, type, model);
		type = 3;
		try {
			date = sdf.parse("2012-2-2");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		calendar.setTime(date);
		DateUtil.getFirstDayOfCycle(calendar, type, model);
		try {
			date = sdf.parse("2012-5-2");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		calendar.setTime(date);
		DateUtil.getFirstDayOfCycle(calendar, type, model);
		try {
			date = sdf.parse("2012-8-2");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		calendar.setTime(date);
		DateUtil.getFirstDayOfCycle(calendar, type, model);
		
		type = 4;
		try {
			date = sdf.parse("2012-12-2");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		calendar.setTime(date);
		DateUtil.getFirstDayOfCycle(calendar, type, model);
		
		type = 5;
		try {
			date = sdf.parse("2012-8-2");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		calendar.setTime(date);
		DateUtil.getFirstDayOfCycle(calendar, type, model);
		try {
			date = sdf.parse("2012-8-15");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		calendar.setTime(date);
		DateUtil.getFirstDayOfCycle(calendar, type, model);
		try {
			date = sdf.parse("2012-12-25");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		calendar.setTime(date);
		DateUtil.getFirstDayOfCycle(calendar, type, model);
		try {
			date = sdf.parse("2012-8-25");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		calendar.setTime(date);
		DateUtil.getFirstDayOfCycle(calendar, type, model);
		DateUtil.getFirstDayOfCycle(type, model);
		model = 1;
		DateUtil.getFirstDayOfCycle(type, model);
	}
	
	@Test
	public void testGetNumberOfDays(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		try{
			DateUtil.getNumberOfDays(startDate, endDate);
		}
		catch(IllegalArgumentException e){
			
		}
		try {
			startDate = sdf.parse("2012-12-1");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
		}
		try{
			DateUtil.getNumberOfDays(startDate, endDate);
		}
		catch(IllegalArgumentException e){
			
		}
		try {
			endDate = sdf.parse("2012-12-25");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
		}
		try{
			DateUtil.getNumberOfDays(startDate, endDate);
			DateUtil.computeInterval(startDate, endDate);
			DateUtil.computeInterval(endDate, startDate);
			DateUtil.computeInterval(endDate, endDate);
		}
		catch(IllegalArgumentException e){
			
		}
		try{
			endDate = DateUtil.parse("2012-12-12", "");
		}
		catch(IllegalArgumentException e){
			//System.out.println("ok");
		}
		DateUtil.format(new Date(),DateUtil.DATETIME_YYYYMMDD_HHMMSS);
		Date date = null;
		DateUtil.parse2EndDate(date);
		date = new Date();
		DateUtil.parse2EndDate(date);
		int dayOfWeek = 4;
		DateUtil.getNextDayOfWeekDate(dayOfWeek);
		
		dayOfWeek = 8;
		DateUtil.getNextDayOfWeekDate(dayOfWeek);
		DateUtil.getWeekOfDate();
		
		String cststr = null;
		String fmt = null;
		DateUtil.getDateFmtStrFromCST(cststr, fmt);
		cststr = "2012-12-12";
		DateUtil.getDateFmtStrFromCST(cststr, fmt);
		fmt = DateUtil.DATE_YYYYMMDD;
		DateUtil.getDateFmtStrFromCST(cststr, fmt);
		fmt = "yyyy-MM-dd";
		cststr = "Tue Dec 25 15:29:53 CST 2012";
		DateUtil.getDateFmtStrFromCST(cststr, fmt);
	}
}
