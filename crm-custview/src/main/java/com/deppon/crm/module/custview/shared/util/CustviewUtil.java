/**   
 * <p>
 * Description:
 * </p>
 * @title CustviewUtil.java
 * @package com.deppon.crm.module.custview.shared.domain.util 
 * @author 106143
 * @version 0.1 2014-4-30下午4:17:41
 */
package com.deppon.crm.module.custview.shared.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.custview.shared.domain.OperationAnalysis;

/**   
 * <p>
 * Description:360工具类
 * </p>
 * @title CustviewUtil.java
 * @package com.deppon.crm.module.custview.shared.domain.util 
 * @author 106143
 * @version 0.1 2014-4-30下午4:17:41
 */

public class CustviewUtil {
	
	/**
	 * 
	 * <p>
	 * Description:组装客户金额分析数据
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-30下午4:20:34
	 * @param listL
	 * @param listA
	 * @return
	 * List<OperationAnalysis>
	 * @update 2014-4-30下午4:20:34
	 */
	public static List<OperationAnalysis> assembleOperationAnalysis(List<OperationAnalysis> listL,List<OperationAnalysis> listA){
		List<String> monthL = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM");
		Date date = new Date();
		String[] dateAry = new String[6];
		for (int i = 0; i<6; i++) {
			dateAry[5-i] = sdf.format(
					new Date(date.getYear(),date.getMonth()-i-1,1));
		}
		List<OperationAnalysis>list = new ArrayList<OperationAnalysis>();
		for(int i = 0 ; i < 6 ; i++){
			OperationAnalysis op = new OperationAnalysis();
			op.setYearMonth(dateAry[i]);
			op.setLeaveMoney("0");
			op.setLeaveWeight("0");
			op.setArriveMoney("0");
			op.setArriveWeight("0");
			list.add(op);
		}
		
		//发货金额与发货量
		for (int i = 0; i < listL.size(); i++) 
			for (int j = 0; j < 6; j++) {
				OperationAnalysis op = list.get(j);
				try {
					if(dateAry[j].equals(sdf.format(sdf.parseObject(listL.get(i).getYearMonth().substring(2, 6))))){
						op.setLeaveMoney((listL.get(i).getLeaveMoney()==null)?"0":listL.get(i).getLeaveMoney());
						op.setLeaveBill((listL.get(i).getLeaveBill()==null)?"0":listL.get(i).getLeaveBill());
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
		}
		//到货金额与到货量
		try {
			for (int i = 0; i < listA.size(); i++) 
				for (int j = 0; j < 6; j++){
					OperationAnalysis op = list.get(j);
					if(dateAry[j].equals(sdf.format(sdf.parseObject(listA.get(i).getYearMonth().substring(2, 6))))){
						op.setArriveMoney((listA.get(i).getArriveMoney()==null)?"0":listA.get(i).getArriveMoney());
						op.setArriveBill((listA.get(i).getArriveBill()==null)?"0":listA.get(i).getArriveBill());
					}
				}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
