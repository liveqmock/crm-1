package com.deppon.crm.module.report.server.manager.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.marketing.server.manager.IMarketActivityManager;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.report.server.manager.IKeycustReportManager;
import com.deppon.crm.module.report.server.service.IKeycustReportService;
import com.deppon.crm.module.report.server.utils.Constant;
import com.deppon.crm.module.report.shared.domain.ProductAmount;
import com.deppon.crm.module.report.shared.domain.RoadAmount;
import com.deppon.crm.module.report.shared.domain.ShipmentAging;
import com.deppon.crm.module.report.shared.domain.ShipmentAmount;
import com.deppon.crm.module.report.shared.domain.ShipmentQuality;
import com.deppon.crm.module.report.shared.domain.ShipmentQualityForChart;
import com.deppon.crm.module.report.shared.domain.SingleProductAmount;
import com.deppon.crm.module.report.shared.domain.SingleRoadAmount;
import com.deppon.crm.module.report.shared.domain.SingleShipmentAging;
import com.deppon.crm.module.report.shared.domain.SingleShipmentQuality;
import com.deppon.crm.module.report.shared.exception.ReportException;
import com.deppon.crm.module.report.shared.exception.ReportExceptionType;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.shared.util.string.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * 大客户走货报告manager层
 * @author yw
 *
 */
public class KeycustReportManager implements IKeycustReportManager {
	private IKeycustReportService keycustReportService;//大客户server层
	private IAlterMemberService alterMemberService;//客户server层
	private IMarketActivityManager marketActivityManager;//营销manager层

	/**
	 * 近三个月走货量
	 */
	@Override
	public List<ShipmentAmount> queryShipmentAmount(String custNum) {
		if (StringUtil.isEmpty(custNum)) {
			//客户编码不能为空
			ReportException re = new ReportException(ReportExceptionType.NULL_CUSTNUM);
			throw re;
		}
		return keycustReportService.queryShipmentAmount(custNum);
	}

	/**
	 * 近三个月产品货量
	 */
	@Override
	public List<ProductAmount> queryProductAmount(String custNum) {
		List<SingleProductAmount> spa = keycustReportService.queryProductAmount(custNum);
		if (spa == null || spa.size() == 0) {
			return null;
		}
		// 当前月份
		int currMonth = new GregorianCalendar().get(Calendar.MONTH) + 1;
		List<ProductAmount> list = new ArrayList<ProductAmount>();
		// map的作用是判断分组行已经添加到里面去了 value 表示集合index
		Map<String, Integer> map = new HashMap<String, Integer>();
		//转换数据模式 将3个SingleProductAmount对象 转换到1个productAmount对象中
		for (SingleProductAmount s : spa) {
			ProductAmount productAmount = null;
			//根据分组来判断当前对象所在行是否已经创建,如果已经创建则直接添加,否则找到分组行的index,set进去
			if (map.get(s.getGrouping()) == null) {
				productAmount = new ProductAmount();
			} else {
				productAmount = list.get(map.get(s.getGrouping()));
			}
			//前第三个月
			if ((currMonth - s.getMonth() == 3) || (currMonth - s.getMonth() == -9)) {
				productAmount.setFirstMonthProductAmount(s);
			//前第2个月
			} else if ((currMonth - s.getMonth() == 2) || (currMonth - s.getMonth() == -10)) {
				productAmount.setSecondMonthProductAmount(s);
			//前第1个月
			} else if ((currMonth - s.getMonth() == 1) || (currMonth - s.getMonth() == -11)) {
				productAmount.setThirdMonthProductAmount(s);
			}
			//如果分组行没有创建,则取到新创建的分组行的index
			if (map.get(s.getGrouping()) == null) {
				productAmount.setGrouping(s.getGrouping());
				list.add(productAmount);
				map.put(s.getGrouping(), list.size() - 1);
			}
		}
		return list;
	}

	/**
	 * 近三个月线路货量
	 */
	@Override
	public List<RoadAmount> queryRoadAmount(String custNum) {
		List<SingleRoadAmount> sra = keycustReportService.queryRoadAmount(custNum);
		if (sra == null || sra.size() == 0) {
			return null;
		}
		List<RoadAmount> list = new ArrayList<RoadAmount>();
		// 当前月份
		int currMonth = new GregorianCalendar().get(Calendar.MONTH) + 1;
		// map的作用是判断分组行已经添加到里面去了 value 表示集合index
		Map<String, Integer> map = new HashMap<String, Integer>();
		//将三个SingleRoadAmount 转换到1个RoadAmount里面去
		for (SingleRoadAmount s : sra) {
			RoadAmount roadAmount = null;
			//根据分组来判断当前对象所在行是否已经创建,如果已经创建则直接添加,否则找到分组行的index,set进去
			if (map.get(s.getGrouping()) == null) {
				roadAmount = new RoadAmount();
			} else {
				roadAmount = list.get(map.get(s.getGrouping()));
			}
			if ((currMonth - s.getMonth() == 3) || (currMonth - s.getMonth() == -9)) {
				roadAmount.setFirstMonthRoadAmount(s);
			} else if ((currMonth - s.getMonth() == 2) || (currMonth - s.getMonth() == -10)) {
				roadAmount.setSecondMonthRoadAmount(s);
			} else if ((currMonth - s.getMonth() == 1) || (currMonth - s.getMonth() == -11)) {
				roadAmount.setThirdMonthRoadAmount(s);
			}
			//如果分组行没有创建,则取到新创建的分组行的index
			if (map.get(s.getGrouping()) == null) {
				roadAmount.setGrouping(s.getGrouping());
				list.add(roadAmount);
				map.put(s.getGrouping(), list.size() - 1);
			}
		}
		return list;
	}

	/**
	 * 近三个月走货时效
	 */
	@Override
	public List<ShipmentAging> queryShipmentAging(String custNum) {
		List<SingleShipmentAging> ssa = keycustReportService.queryShipmentAging(custNum);
		if (ssa == null || ssa.size() == 0) {
			return null;
		}
		List<ShipmentAging> list = new ArrayList<ShipmentAging>();
		//当前月份
		int currMonth = new GregorianCalendar().get(Calendar.MONTH) + 1;
		// map的作用是判断分组行已经添加到里面去了 value 表示集合index
		Map<String, Integer> map = new HashMap<String, Integer>();
		//将SingleShipmentAging的属性分叉成shipmentAging
		for (SingleShipmentAging s : ssa) {
			ShipmentAging shipmentAging = null;
			//根据分组来判断当前对象所在行是否已经创建,如果已经创建则直接添加,否则找到分组行的index,set进去
			if (map.get(s.getGrouping()) == null) {
				shipmentAging = new ShipmentAging();
			} else {
				shipmentAging = list.get(map.get(s.getGrouping()));
			}
			//前第三个月
			if ((currMonth - s.getMonth() == 3) || (currMonth - s.getMonth() == -9)) {
				if (Constant.TRANSPORT_JZKY.equals(s.getTransport())) {
					shipmentAging.setFirstAgingQYC(s.getTime());
				} else if (Constant.TRANSPORT_JZQYD.equals(s.getTransport())) {
					shipmentAging.setFirstAgingQYD(s.getTime());
				} else if (Constant.TRANSPORT_JZKY.equals(s.getTransport())) {
					shipmentAging.setFirstAgingKY(s.getTime());
				} else if (Constant.TRANSPORT_JZKH.equals(s.getTransport())) {
					shipmentAging.setFirstAgingKH(s.getTime());
				} else if (Constant.TRANSPORT_KD.equals(s.getTransport())) {
					shipmentAging.setFirstAgingKD(s.getTime());
				} else if (Constant.TRANSPORT_JZCY.equals(s.getTransport())) {
					shipmentAging.setFirstAgingCY(s.getTime());
				} else if (Constant.TRANSPORT_ZC.equals(s.getTransport())) {
					shipmentAging.setFirstAgingZC(s.getTime());
				}
			//前第2个月
			} else if ((currMonth - s.getMonth() == 2) || (currMonth - s.getMonth() == -10)) {
				if (Constant.TRANSPORT_JZQYC.equals(s.getTransport())) {
					shipmentAging.setSecondAgingQYC(s.getTime());
				} else if (Constant.TRANSPORT_JZQYD.equals(s.getTransport())) {
					shipmentAging.setSecondAgingQYD(s.getTime());
				} else if (Constant.TRANSPORT_JZKY.equals(s.getTransport())) {
					shipmentAging.setSecondAgingKY(s.getTime());
				} else if (Constant.TRANSPORT_JZKH.equals(s.getTransport())) {
					shipmentAging.setSecondAgingKH(s.getTime());
				} else if (Constant.TRANSPORT_KD.equals(s.getTransport())) {
					shipmentAging.setSecondAgingKD(s.getTime());
				} else if (Constant.TRANSPORT_JZCY.equals(s.getTransport())) {
					shipmentAging.setSecondAgingCY(s.getTime());
				} else if (Constant.TRANSPORT_ZC.equals(s.getTransport())) {
					shipmentAging.setSecondAgingZC(s.getTime());
				}
			//前第1个月
			} else if ((currMonth - s.getMonth() == 1) || (currMonth - s.getMonth() == -11)) {
				if (Constant.TRANSPORT_JZQYC.equals(s.getTransport())) {
					shipmentAging.setThirdAgingQYC(s.getTime());
				} else if (Constant.TRANSPORT_JZQYD.equals(s.getTransport())) {
					shipmentAging.setThirdAgingQYD(s.getTime());
				} else if (Constant.TRANSPORT_JZKY.equals(s.getTransport())) {
					shipmentAging.setThirdAgingKY(s.getTime());
				} else if (Constant.TRANSPORT_JZKH.equals(s.getTransport())) {
					shipmentAging.setThirdAgingKH(s.getTime());
				} else if (Constant.TRANSPORT_KD.equals(s.getTransport())) {
					shipmentAging.setThirdAgingKD(s.getTime());
				} else if (Constant.TRANSPORT_JZCY.equals(s.getTransport())) {
					shipmentAging.setThirdAgingCY(s.getTime());
				} else if (Constant.TRANSPORT_ZC.equals(s.getTransport())) {
					shipmentAging.setThirdAgingZC(s.getTime());
				}
			}
			//如果分组行没有创建,则取到新创建的分组行的index
			if (map.get(s.getGrouping()) == null) {
				shipmentAging.setGrouping(s.getGrouping());
				list.add(shipmentAging);
				map.put(s.getGrouping(), list.size() - 1);
			}
		}
		return list;
	}

	/**
	 * 近三个月走货质量
	 */
	@Override
	public List<ShipmentQuality> queryShipmentQuality(String custNum) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("custNum", custNum);
		paramsMap.put("abSignNormalCount", Constant.ABSINGNORMALCOUNT);//异常签收数
		paramsMap.put("recompenseCount", Constant.RECOMPENSECOUNT);//异常理赔数
		List<SingleShipmentQuality> ssq = keycustReportService.queryShipmentQuality(paramsMap);
		if (ssq == null || ssq.size() == 0) {
			return null;
		}
		List<ShipmentQuality> list = new ArrayList<ShipmentQuality>();
		// map的作用是判断分组行已经添加到里面去了 value 表示集合index
		Map<String, Integer> map = new HashMap<String, Integer>();
		// 当前月
		int currMonth = new GregorianCalendar().get(Calendar.MONTH) + 1;
		//将SingleShipmentQuality 转换成shipmentQuality
		for (SingleShipmentQuality s : ssq) {
			ShipmentQuality shipmentQuality = null;
			//根据分组来判断当前对象所在行是否已经创建,如果已经创建则直接添加,否则找到分组行的index,set进去
			if (map.get(s.getGrouping()) == null) {
				shipmentQuality = new ShipmentQuality();
			} else {
				shipmentQuality = list.get(map.get(s.getGrouping()));
			}
			//前第三个月
			if ((currMonth - s.getMonth() == 3) || (currMonth - s.getMonth() == -9)) {
				shipmentQuality.setFirstQuality(s.getCount());
			//前第2个月
			} else if ((currMonth - s.getMonth() == 2) || (currMonth - s.getMonth() == -10)) {
				shipmentQuality.setSecondQuality(s.getCount());
			//前第1个月
			} else if ((currMonth - s.getMonth() == 1) || (currMonth - s.getMonth() == -11)) {
				shipmentQuality.setThirdQuality(s.getCount());
			}
			//如果分组行没有创建,则取到新创建的分组行的index
			if (map.get(s.getGrouping()) == null) {
				shipmentQuality.setGrouping(s.getGrouping());
				list.add(shipmentQuality);
				map.put(s.getGrouping(), list.size() - 1);
			}
		}
		return list;
	}

	/**
	 * 近三个月走货质量(得到图表所需数据类型)
	 */
	@Override
	public List<ShipmentQualityForChart> queryShipmentQualityForChart(String custNum) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("custNum", custNum);
		paramsMap.put("abSignNormalCount", Constant.ABSINGNORMALCOUNT);//异常签收数
		paramsMap.put("recompenseCount", Constant.RECOMPENSECOUNT);//异常理赔数
		List<SingleShipmentQuality> ssq = keycustReportService.queryShipmentQuality(paramsMap);
		if (ssq == null || ssq.size() == 0) {
			return null;
		}
		// map的作用是判断分组行已经添加到里面去了 value 表示集合index
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		List<ShipmentQualityForChart> list = new ArrayList<ShipmentQualityForChart>();
		//将SingleShipmentQuality 转换成ShipmentQualityForChart
		for (SingleShipmentQuality s : ssq) {
			ShipmentQualityForChart sqfc = null;
			//根据分组来判断当前对象所在行是否已经创建,如果已经创建则直接添加,否则找到分组行的index,set进去
			if (map.get(s.getMonth()) == null) {
				sqfc = new ShipmentQualityForChart();
			} else {
				sqfc = list.get(map.get(s.getMonth()));
			}
			if (Constant.ABSINGNORMALCOUNT.equals(s.getGrouping())) {
				sqfc.setAbSignNormalCount(s.getCount());
			} else if (Constant.RECOMPENSECOUNT.equals(s.getGrouping())) {
				sqfc.setRecompenseCount(s.getCount());
			}
			if (map.get(s.getMonth()) == null) {
				sqfc.setMonth(s.getMonth());
				list.add(sqfc);
				map.put(s.getMonth(), list.size() - 1);
			}
		}
		return list;
	}

	/**
	 * 校验运单号,看是否可以进行查询
	 */
	@Override
	public void checkCustNum(String custNum) {
		if (StringUtil.isEmpty(custNum)) {
			ReportException re = new ReportException(ReportExceptionType.NULL_CUSTNUM);//没有客户编码
			throw re;
		}
		MemberCondition condition = new MemberCondition();
		condition.setCustNumber(custNum);
		List<Member> list = alterMemberService.searchMemberByCondition(condition);
		if (list == null || list.size() == 0 || Constant.MENBER_STATUS_VALID.equals(list.get(0).getCustStatus())) {
			ReportException re = new ReportException(ReportExceptionType.MEMBER_NULL);//客户不存在
			throw re;
		}
		Member member = list.get(0);
		User user = (User) UserContext.getCurrentUser();
		if (!member.getDeptId().equals(user.getEmpCode().getDeptId().getId())) {
			//只能查询本部门的客户
			ReportException re = new ReportException(ReportExceptionType.DEPTID_NOT_SAME);
			throw re;
		}
		if (!member.getIsKeyCustomer()) {
			//该客户无大客户标记，无法查询走货报告
			ReportException re = new ReportException(ReportExceptionType.NOT_KEY_CUST);
			throw re;
		}

	}

	public void setAlterMemberService(IAlterMemberService alterMemberService) {
		this.alterMemberService = alterMemberService;
	}

	public void setKeycustReportService(IKeycustReportService keycustReportService) {
		this.keycustReportService = keycustReportService;
	}

	/**
	 * 
	 * <p>
	 * Description:导出大客户走货报告<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月29日 void
	 */
//此方法是用于导出excel,后面用导出doc文档代替了 所以注释掉
//	public FileInputStream exportKeyCustomerExcel(String custNum, Boolean isAddMarketing) {
//		// 封装会员查询条件
//		MemberCondition condition = new MemberCondition();
//		// 设置查询条件
//		condition.setCustNumber(custNum);
//		// 调用会员修改manager进行查询
//		List<Member> memberlist = alterMemberService.searchMemberByCondition(condition);
//		Member member = null;
//		// 集合不为空，则获得查询结果第一个元素
//		if (!CollectionUtils.isEmpty(memberlist)) {
//			member = memberlist.get(0);
//		}
//
//		// 第一步，创建一个webbook，对应一个Excel文件
//		HSSFWorkbook wb = new HSSFWorkbook();
//		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
//		HSSFSheet sheet = wb.createSheet("走货报告");
//		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//		HSSFRow row = sheet.createRow((int) 0);
//		// 设置一个自定义颜色 德邦黄
//		HSSFPalette palette = wb.getCustomPalette(); // wb HSSFWorkbook对象
//		palette.setColorAtIndex((short) 55, (byte) (55), (byte) (60), (byte) (100));
//
//		// 下边框
//
//		/**************************/
//		// 第四步，创建单元格，并设置值表头 设置表头居中
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//		style.setBorderBottom(CellStyle.SOLID_FOREGROUND);
//		// 下边框颜色
//		style.setBottomBorderColor((short) 55);// 蓝色
//		// 左边框
//		style.setBorderLeft(CellStyle.SOLID_FOREGROUND);
//		// 左边框颜色
//		style.setLeftBorderColor((short) 55);// 蓝色
//		// 右边框
//		style.setBorderRight(CellStyle.SOLID_FOREGROUND);
//		// 右边框颜色
//		style.setRightBorderColor((short) 55);// 蓝色
//		// 上边框
//		style.setBorderTop(CellStyle.SOLID_FOREGROUND);//
//		// 上边框颜色
//		style.setTopBorderColor((short) 55);// 蓝色
//		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style.setFillForegroundColor((short) 56);
//		/****************************************************/
//		HSSFCellStyle style1 = wb.createCellStyle();
//		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//		style1.setBorderBottom(CellStyle.SOLID_FOREGROUND);
//		// 下边框颜色
//		style1.setBottomBorderColor((short) 56);// 蓝色
//		// 左边框
//		style1.setBorderLeft(CellStyle.SOLID_FOREGROUND);
//		// 左边框颜色
//		style1.setLeftBorderColor((short) 56);// 蓝色
//		// 右边框
//		style1.setBorderRight(CellStyle.SOLID_FOREGROUND);
//		// 右边框颜色
//		style1.setRightBorderColor((short) 56);// 蓝色
//		// 上边框
//		style1.setBorderTop(CellStyle.SOLID_FOREGROUND);//
//		// 上边框颜色
//		style1.setTopBorderColor((short) 56);// 蓝色
//		style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style1.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
//		/****************************************************/
//		// 标题 创建第一行
//		HSSFCell cell = row.createCell(0);
//		// 把三行12列作为标题
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 0, row.getRowNum() + 3, (short) 12));
//		// 设置大客户走货报告标题
//		cell.setCellValue(member.getCustName() + Constant.REPORT);
//		// 创建一个poi字体
//		HSSFFont font = wb.createFont();
//		HSSFCellStyle titlestyle = wb.createCellStyle();
//		font.setColor(HSSFColor.BLACK.index);// HSSFColor.VIOLET.index //字体颜色
//		font.setFontHeightInPoints((short) 40);
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体增粗
//		// 把字体应用到当前的样式
//		titlestyle.setFont(font);
//		titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		cell.setCellStyle(titlestyle);
//		// 增加四行
//		row = sheet.createRow((int) row.getRowNum() + 4);
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 0, row.getRowNum(), (short) 12));
//		cell = row.createCell(0);
//		if (isAddMarketing != null && isAddMarketing) {
//			User user = (User) UserContext.getCurrentUser();
//			String tip = marketActivityManager.searchLatestSloganByDeptId(user.getEmpCode().getDeptId().getId());
//			if (StringUtil.isNotEmpty(tip)) {
//				cell.setCellValue(tip);
//			}
//		}
//		font = wb.createFont();
//		font.setColor(HSSFColor.RED.index);// HSSFColor.VIOLET.index //字体颜色
//		font.setFontHeightInPoints((short) 24);
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体增粗
//		// 把字体应用到当前的样式
//		titlestyle.setFont(font);
//		titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		cell.setCellStyle(titlestyle);
//		row = sheet.createRow((int) row.getRowNum() + 1);
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 0, row.getRowNum(), (short) 3));
//		cell = row.createCell(0);
//		cell.setCellValue("近三个月走货量");
//		cell.setCellStyle(style1);
//		row = sheet.createRow((int) row.getRowNum() + 1);
//		cell = row.createCell(0);
//		cell.setCellValue(Constant.MONTH);
//		cell.setCellStyle(style);
//		cell = row.createCell(1);
//		cell.setCellValue("票数");
//		cell.setCellStyle(style);
//		cell = row.createCell(2);
//		cell.setCellValue("重量");
//		cell.setCellStyle(style);
//		cell = row.createCell(3);
//		cell.setCellValue("金额");
//		cell.setCellStyle(style);
//
//		// 第五步，写入实体数据
//		List<ShipmentAmount> list = this.queryShipmentAmount(custNum);
//		for (int i = 0; i < list.size(); i++) {
//			row = sheet.createRow((int) row.getRowNum() + 1);
//			ShipmentAmount sa = (ShipmentAmount) list.get(i);
//			// 第四步，创建单元格，并设置值
//			row.createCell(0).setCellValue(sa.getMonth());
//			row.createCell(1).setCellValue(sa.getDeliverycount());
//			row.createCell(2).setCellValue(sa.getWeight());
//			row.createCell(3).setCellValue(sa.getAmount());
//
//			cell = row.createCell(4);
//		}
//		// 第六步，将文件存到指定位置
//		// 大客户近三个月产品货量
//		// private int _rowFrom;
//		// private short _colFrom;
//		// private int _rowTo;
//		// private short _colTo;
//		row = sheet.createRow((int) row.getRowNum() + 2);
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 0, row.getRowNum(), (short) 12));
//		cell = row.createCell(0);
//		cell.setCellValue("近三个月产品货量");
//		cell.setCellStyle(style1);
//		row = sheet.createRow((int) row.getRowNum() + 1);
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 1, row.getRowNum(), (short) 4));
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 5, row.getRowNum(), (short) 8));
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 9, row.getRowNum(), (short) 12));
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 13, row.getRowNum(), (short) 16));
//		List<ProductAmount> productAmounts = queryProductAmount(custNum);
//		cell = row.createCell(0);
//		cell.setCellValue("运输方式");
//		cell.setCellStyle(style);
//		cell = row.createCell(1);
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.MONTH, -1); // 得到前一个月
//		int firMonth = calendar.get(Calendar.MONTH) + 1;
//		calendar.add(Calendar.MONTH, -1); // 得到前一个月
//		int secMonth = calendar.get(Calendar.MONTH) + 1;
//		calendar.add(Calendar.MONTH, -1); // 得到前一个月
//		int thrMonth = calendar.get(Calendar.MONTH) + 1;
//		cell.setCellValue(firMonth + "月份");
//		cell.setCellStyle(style);
//		cell = row.createCell(5);
//		cell.setCellValue(secMonth + "月份");
//		cell.setCellStyle(style);
//		cell = row.createCell(9);
//		cell.setCellValue(thrMonth + "月份");
//		cell.setCellStyle(style);
//
//		row = sheet.createRow((int) row.getRowNum() + 1);
//		cell = row.createCell(1);
//		cell.setCellValue("票数");
//		cell.setCellStyle(style);
//		cell = row.createCell(2);
//		cell.setCellValue("重量");
//		cell.setCellStyle(style);
//		cell = row.createCell(3);
//		cell.setCellValue("金额");
//		cell.setCellStyle(style);
//		cell = row.createCell(4);
//		cell.setCellValue("重量占比");
//		cell.setCellStyle(style);
//		cell = row.createCell(5);
//		cell.setCellValue("票数");
//		cell.setCellStyle(style);
//		cell = row.createCell(6);
//		cell.setCellValue("重量");
//		cell.setCellStyle(style);
//		cell = row.createCell(7);
//		cell.setCellValue("金额");
//		cell.setCellStyle(style);
//		cell = row.createCell(8);
//		cell.setCellValue("重量占比");
//		cell.setCellStyle(style);
//		cell = row.createCell(9);
//		cell.setCellValue("票数");
//		cell.setCellStyle(style);
//		cell = row.createCell(10);
//		cell.setCellValue("重量");
//		cell.setCellStyle(style);
//		cell = row.createCell(11);
//		cell.setCellValue("金额");
//		cell.setCellStyle(style);
//		cell = row.createCell(12);
//		cell.setCellValue("重量占比");
//		cell.setCellStyle(style);
//		for (int i = 0; i < productAmounts.size(); i++) {
//			row = sheet.createRow(row.getRowNum() + 1);
//			ProductAmount pa = (ProductAmount) productAmounts.get(i);
//			// 第四步，创建单元格，并设置值
//			if (null != pa) {
//				row.createCell(0).setCellValue(pa.getGrouping());
//			}
//			if (null != pa && null != pa.getFirstMonthProductAmount()) {
//				row.createCell(1).setCellValue(pa.getFirstMonthProductAmount().getDeliverycount());
//				row.createCell(2).setCellValue(pa.getFirstMonthProductAmount().getWeight());
//				row.createCell(3).setCellValue(pa.getFirstMonthProductAmount().getAmount());
//				row.createCell(4).setCellValue(pa.getFirstMonthProductAmount().getWeightRate());
//			}
//			if (null != pa && null != pa.getSecondMonthProductAmount()) {
//				row.createCell(5).setCellValue(pa.getSecondMonthProductAmount().getDeliverycount());
//				row.createCell(6).setCellValue(pa.getSecondMonthProductAmount().getWeight());
//				row.createCell(7).setCellValue(pa.getSecondMonthProductAmount().getAmount());
//				row.createCell(8).setCellValue(pa.getSecondMonthProductAmount().getWeightRate());
//			}
//			if (null != pa && null != pa.getThirdMonthProductAmount()) {
//				row.createCell(9).setCellValue(pa.getThirdMonthProductAmount().getDeliverycount());
//				row.createCell(10).setCellValue(pa.getThirdMonthProductAmount().getWeight());
//				row.createCell(11).setCellValue(pa.getThirdMonthProductAmount().getAmount());
//				row.createCell(12).setCellValue(pa.getThirdMonthProductAmount().getWeightRate());
//			}
//
//			cell = row.createCell(13);
//		}
//
//		// 近三个月线路质量
//		row = sheet.createRow((int) row.getRowNum() + 2);
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 0, row.getRowNum(), (short) 12));
//		cell = row.createCell(0);
//		cell.setCellValue("近三个月线路货量");
//		cell.setCellStyle(style1);
//		row = sheet.createRow((int) row.getRowNum() + 1);
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 1, row.getRowNum(), (short) 4));
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 5, row.getRowNum(), (short) 8));
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 9, row.getRowNum(), (short) 12));
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 13, row.getRowNum(), (short) 16));
//		List<RoadAmount> roadAmounts = this.queryRoadAmount(custNum);
//		cell = row.createCell(0);
//		cell.setCellValue("线路");
//		cell.setCellStyle(style);
//		cell = row.createCell(1);
//		cell.setCellValue(firMonth + "月份");
//		cell.setCellStyle(style);
//		cell = row.createCell(5);
//		cell.setCellValue(secMonth + "月份");
//		cell.setCellStyle(style);
//		cell = row.createCell(9);
//		cell.setCellValue(thrMonth + "月份");
//		cell.setCellStyle(style);
//		row = sheet.createRow((int) row.getRowNum() + 1);
//		cell = row.createCell(1);
//		cell.setCellValue("票数");
//		cell.setCellStyle(style);
//		cell = row.createCell(2);
//		cell.setCellValue("重量");
//		cell.setCellStyle(style);
//		cell = row.createCell(3);
//		cell.setCellValue("金额");
//		cell.setCellStyle(style);
//		cell = row.createCell(4);
//		cell.setCellValue("重量占比");
//		cell.setCellStyle(style);
//		cell = row.createCell(5);
//		cell.setCellValue("票数");
//		cell.setCellStyle(style);
//		cell = row.createCell(6);
//		cell.setCellValue("重量");
//		cell.setCellStyle(style);
//		cell = row.createCell(7);
//		cell.setCellValue("金额");
//		cell.setCellStyle(style);
//		cell = row.createCell(8);
//		cell.setCellValue("重量占比");
//		cell.setCellStyle(style);
//		cell = row.createCell(9);
//		cell.setCellValue("票数");
//		cell.setCellStyle(style);
//		cell = row.createCell(10);
//		cell.setCellValue("重量");
//		cell.setCellStyle(style);
//		cell = row.createCell(11);
//		cell.setCellValue("金额");
//		cell.setCellStyle(style);
//		cell = row.createCell(12);
//		cell.setCellValue("重量占比");
//		cell.setCellStyle(style);
//		for (int i = 0; i < roadAmounts.size(); i++) {
//			row = sheet.createRow(row.getRowNum() + 1);
//			RoadAmount ra = (RoadAmount) roadAmounts.get(i);
//			// 第四步，创建单元格，并设置值
//			if (null != ra) {
//				row.createCell(0).setCellValue(ra.getGrouping());
//			}
//			if (null != ra && null != ra.getFirstMonthRoadAmount()) {
//				row.createCell(1).setCellValue(ra.getFirstMonthRoadAmount().getDeliverycount());
//				row.createCell(2).setCellValue(ra.getFirstMonthRoadAmount().getWeight());
//				row.createCell(3).setCellValue(ra.getFirstMonthRoadAmount().getAmount());
//				row.createCell(4).setCellValue(ra.getFirstMonthRoadAmount().getWeightRate());
//			}
//			if (null != ra && null != ra.getSecondMonthRoadAmount()) {
//				row.createCell(5).setCellValue(ra.getSecondMonthRoadAmount().getDeliverycount());
//				row.createCell(6).setCellValue(ra.getSecondMonthRoadAmount().getWeight());
//				row.createCell(7).setCellValue(ra.getSecondMonthRoadAmount().getAmount());
//				row.createCell(8).setCellValue(ra.getSecondMonthRoadAmount().getWeightRate());
//			}
//			if (null != ra && null != ra.getThirdMonthRoadAmount()) {
//				row.createCell(9).setCellValue(ra.getThirdMonthRoadAmount().getDeliverycount());
//				row.createCell(10).setCellValue(ra.getThirdMonthRoadAmount().getWeight());
//				row.createCell(11).setCellValue(ra.getThirdMonthRoadAmount().getAmount());
//				row.createCell(12).setCellValue(ra.getThirdMonthRoadAmount().getWeightRate());
//			}
//			cell = row.createCell(13);
//		}
//		// 走货时效
//		row = sheet.createRow((int) row.getRowNum() + 2);
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 0, row.getRowNum(), (short) 21));
//		cell = row.createCell(0);
//		cell.setCellValue("近三个月走货时效");
//		cell.setCellStyle(style1);
//		row = sheet.createRow((int) row.getRowNum() + 1);
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 1, row.getRowNum(), (short) 3));
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 4, row.getRowNum(), (short) 6));
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 7, row.getRowNum(), (short) 9));
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 10, row.getRowNum(), (short) 12));
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 13, row.getRowNum(), (short) 15));
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 16, row.getRowNum(), (short) 18));
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 19, row.getRowNum(), (short) 21));
//		List<ShipmentAging> ShipmentAgings = this.queryShipmentAging(custNum);
//		cell = row.createCell(0);
//		cell.setCellValue("线路");
//		cell.setCellStyle(style);
//		cell = row.createCell(1);
//		cell.setCellValue("精准卡航");
//		cell.setCellStyle(style);
//		cell = row.createCell(4);
//		cell.setCellValue("精准城运");
//		cell.setCellStyle(style);
//		cell = row.createCell(7);
//		cell.setCellValue("精准空运");
//		cell.setCellStyle(style);
//		cell = row.createCell(10);
//		cell.setCellValue("精准汽运(长)");
//		cell.setCellStyle(style);
//		cell = row.createCell(13);
//		cell.setCellValue("精准汽运(短)");
//		cell.setCellStyle(style);
//		cell = row.createCell(16);
//		cell.setCellValue("经济快递");
//		cell.setCellStyle(style);
//		cell = row.createCell(19);
//		cell.setCellValue("整车");
//		cell.setCellStyle(style);
//		row = sheet.createRow((int) row.getRowNum() + 1);
//		for (int i = 0; i < 21; i = i + 3) {
//			cell = row.createCell(i + 1);
//			cell.setCellValue(firMonth + "月份");
//			cell.setCellStyle(style);
//			cell = row.createCell(i + 2);
//			cell.setCellValue(secMonth + "月份");
//			cell.setCellStyle(style);
//			cell = row.createCell(i + 3);
//			cell.setCellValue(thrMonth + "月份");
//			cell.setCellStyle(style);
//
//		}
//
//		for (int i = 0; i < ShipmentAgings.size(); i++) {
//			row = sheet.createRow(row.getRowNum() + 1);
//			ShipmentAging ra = (ShipmentAging) ShipmentAgings.get(i);
//			// 第四步，创建单元格，并设置值
//			if (null != ra) {
//				row.createCell(0).setCellValue(ra.getGrouping());
//
//				row.createCell(1).setCellValue(tranNUllTOZero(ra.getFirstAgingKH()));
//				row.createCell(2).setCellValue(tranNUllTOZero(ra.getSecondAgingKH()));
//				row.createCell(3).setCellValue(tranNUllTOZero(ra.getThirdAgingKH()));
//				row.createCell(4).setCellValue(tranNUllTOZero(ra.getFirstAgingKY()));
//				row.createCell(5).setCellValue(tranNUllTOZero(ra.getSecondAgingKY()));
//				row.createCell(6).setCellValue(tranNUllTOZero(ra.getThirdAgingKY()));
//				row.createCell(7).setCellValue(tranNUllTOZero(ra.getFirstAgingCY()));
//				row.createCell(8).setCellValue(tranNUllTOZero(ra.getSecondAgingCY()));
//				row.createCell(9).setCellValue(tranNUllTOZero(ra.getThirdAgingCY()));
//				row.createCell(10).setCellValue(tranNUllTOZero(ra.getFirstAgingQYC()));
//				row.createCell(11).setCellValue(tranNUllTOZero(ra.getSecondAgingQYC()));
//				row.createCell(12).setCellValue(tranNUllTOZero(ra.getThirdAgingQYC()));
//				row.createCell(13).setCellValue(tranNUllTOZero(ra.getFirstAgingQYD()));
//				row.createCell(14).setCellValue(tranNUllTOZero(ra.getSecondAgingQYD()));
//				row.createCell(15).setCellValue(tranNUllTOZero(ra.getThirdAgingQYD()));
//				row.createCell(16).setCellValue(tranNUllTOZero(ra.getFirstAgingKD()));
//				row.createCell(17).setCellValue(tranNUllTOZero(ra.getSecondAgingKD()));
//				row.createCell(18).setCellValue(tranNUllTOZero(ra.getThirdAgingKD()));
//				row.createCell(19).setCellValue(tranNUllTOZero(ra.getFirstAgingZC()));
//				row.createCell(20).setCellValue(tranNUllTOZero(ra.getSecondAgingZC()));
//				row.createCell(21).setCellValue(tranNUllTOZero(ra.getThirdAgingZC()));
//				cell = row.createCell(22);
//			}
//
//		}
//		row = sheet.createRow((int) row.getRowNum() + 2);
//		sheet.addMergedRegion(new Region(row.getRowNum(), (short) 0, row.getRowNum(), (short) 3));
//		cell = row.createCell(0);
//		cell.setCellValue("近三个月走货质量");
//		cell.setCellStyle(style1);
//		row = sheet.createRow((int) row.getRowNum() + 1);
//		cell = row.createCell(0);
//		cell.setCellValue("类型");
//		cell.setCellStyle(style);
//		cell = row.createCell(1);
//		cell.setCellValue(firMonth + "月份");
//		cell.setCellStyle(style);
//		cell = row.createCell(2);
//		cell.setCellValue(secMonth + "月份");
//		cell.setCellStyle(style);
//		cell = row.createCell(3);
//		cell.setCellValue(thrMonth + "月份");
//		cell.setCellStyle(style);
//		List<ShipmentQuality> sqs = queryShipmentQuality(custNum);
//		for (int i = 0; i < sqs.size(); i++) {
//			row = sheet.createRow((int) row.getRowNum() + 1);
//			ShipmentQuality sq = (ShipmentQuality) sqs.get(i);
//			// 第四步，创建单元格，并设置值
//			row.createCell(0).setCellValue(sq.getGrouping());
//			row.createCell(1).setCellValue(sq.getFirstQuality());
//			row.createCell(2).setCellValue(sq.getSecondQuality());
//			row.createCell(3).setCellValue(sq.getThirdQuality());
//			cell = row.createCell(4);
//		}
//		try {
//			// FileOutputStream fout = new
//			// FileOutputStream("/misc/crmfile/keycusomter"+custNum+".xls");
//			FileOutputStream fout = new FileOutputStream("E:/a.xls");
//			wb.write(fout);
//			fout.close();
//			// return fout;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//
//	}

//	private Double tranNUllTOZero(Double dd) {
//		return dd == null ? 0 : dd;
//	}

	public void setMarketActivityManager(IMarketActivityManager marketActivityManager) {
		this.marketActivityManager = marketActivityManager;
	}
	@Override
	public FileInputStream exportKeyCustomerWord(String custNum,boolean isAddMarketing) {
		Configuration configuration = new Configuration();
		Map dataMap = new HashMap();
		configuration.setDefaultEncoding("utf-8");
		configuration.setClassForTemplateLoading(this.getClass(), "/com/deppon/crm/module/report/server/manager/impl");
		Template t = null;

		getData(dataMap, custNum,isAddMarketing);
		try {
			// test.ftl为要装载的模板
			t = configuration.getTemplate("keycustomer.ftl");
		} catch (Exception e) {
			ReportException re = new ReportException(ReportExceptionType.CREATE_FILE_MODEL_ERROR);//创建文件模板失败
			throw re;
		}
		// 输出文档路径及名称
		File outFile = new File("/misc/crmfile/keycustomer/"+custNum+".doc");
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
		}catch (Exception e) {
			ReportException re = new ReportException(ReportExceptionType.CREATE_FILE_ERROR);//创建文件失败
			throw re;
		}
		try {
			t.process(dataMap, out);
			FileInputStream stream = new FileInputStream(outFile);
			return stream;
		} catch (Exception e) {
			e.printStackTrace();
			ReportException re = new ReportException(ReportExceptionType.CREATE_FILE_ERROR);//创建文件失败
			throw re;
		}
	}

	private void getData(Map dataMap, String custNum,boolean isAddMarketing) {
		// 封装会员查询条件
		MemberCondition condition = new MemberCondition();
		// 设置查询条件
		condition.setCustNumber(custNum);
		// 调用会员修改manager进行查询
		List<Member> memberlist = alterMemberService.searchMemberByCondition(condition);
		Member member = null;
		// 集合不为空，则获得查询结果第一个元素
		if (!CollectionUtils.isEmpty(memberlist)) {
			member = memberlist.get(0);
		}
		dataMap.put("custName", member.getCustName());
		User user = (User) UserContext.getCurrentUser();
		if(isAddMarketing){
			MarketActivity marketActivity=	marketActivityManager.searchLatestMarketActivityByDeptId(user.getEmpCode().getDeptId().getId());
			if(null!=marketActivity){
				Date startDate=marketActivity.getStartTime();
				Date endDate=marketActivity.getEndTime();
				DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
				String start=format.format(startDate);
				String end=format.format(endDate);
				dataMap.put("slogan", marketActivity.getSlogan());
				dataMap.put("activityTime", start+"-"+end);
				dataMap.put("content", marketActivity.getContent());
			}
		}
		List<ShipmentAmount> shipmentAmounts = this.queryShipmentAmount(custNum);
		List<ProductAmount> productAmounts = queryProductAmount(custNum);
		if(CollectionUtils.isEmpty(productAmounts)){
			productAmounts=new ArrayList<ProductAmount>();
		}
		List<RoadAmount> roadAmounts = this.queryRoadAmount(custNum);
		if(CollectionUtils.isEmpty(roadAmounts)){
			roadAmounts=new ArrayList<RoadAmount>();
		}
		List<ShipmentQuality> sqs = queryShipmentQuality(custNum);
		if(CollectionUtils.isEmpty(sqs)){
			sqs=new ArrayList<ShipmentQuality>();
		}
		List<ShipmentAging> ShipmentAgings = this.queryShipmentAging(custNum);
		if(CollectionUtils.isEmpty(ShipmentAgings)){
			ShipmentAgings=new ArrayList<ShipmentAging>();
		}
		List<ShipmentAging> ShipmentAgings1 = this.queryShipmentAging(custNum);
		if(CollectionUtils.isEmpty(ShipmentAgings1)){
			ShipmentAgings1=new ArrayList<ShipmentAging>();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1); // 得到前一个月
		int firMonth = calendar.get(Calendar.MONTH) + 1;
		calendar.add(Calendar.MONTH, -1); // 得到前一个月
		int secMonth = calendar.get(Calendar.MONTH) + 1;
		calendar.add(Calendar.MONTH, -1); // 得到前一个月
		int thrMonth = calendar.get(Calendar.MONTH) + 1;
		dataMap.put("firMonth", firMonth+"月");
		dataMap.put("secMonth", secMonth+"月");
		dataMap.put("thrMonth", thrMonth+"月");
		dataMap.put("shipmentAmounts", shipmentAmounts);
		dataMap.put("productAmounts", productAmounts);
		dataMap.put("roadAmounts", roadAmounts);
		dataMap.put("sqs", sqs);
		dataMap.put("ShipmentAgings", ShipmentAgings);
		dataMap.put("ShipmentAgings1", ShipmentAgings1);
	}
}
