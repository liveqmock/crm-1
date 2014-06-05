package testutils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.logmoniting.utils.SpringTestHelper;

/**
 * @作者：唐亮
 * @时间：2013-9-17
 * @描述：测试用工具类
 * */
public class DataTestUtil {
	/**
	 * @throws SQLException 
	 * 
	 * @Title: initSynchronizeData
	 *  <p>
	 * @Description: 初始化测试用客户同步数据
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-17
	 * @return void
	 * @throws
	 */
	public static void initSynchronizeData() throws SQLException{
		List<String> sqls = new ArrayList<String>();
		sqls.add("insert into t_crm_cmdtbs(fid,fcreatetime,fcreateuserid,flastupdatetime,flastmodifyuserid," +
				"transaction_no,customer_no,crate_time,finish_time,handle_type,status,target,error_code," +
				"err_desc,err_time,pattern,err_number)values(-1,sysdate,105873,sysdate,105873,'-1',-1,sysdate,sysdate," +
				"'M','U','ALL','5001','啊？不懂',sysdate,'0',4)");
		sqls.add("insert into t_crm_cmdtbsd(fid,fcreatetime,fcreateuserid,flastupdatetime,flastmodifyuserid,transaction_no,table_name,table_keyword,operation_flg)" +
				"values(-1,sysdate,105873,sysdate,105873,'-1','T_TL_TEST',-1,'I')");
		SpringTestHelper.executeBatch(sqls);
		System.out.println("初始化测试用客户同步数据成功");
	}
	/**
	 * 
	 * @Title: cleanCustLabelData
	 *  <p>
	 * @Description: 清理测试用客户同步数据
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-17
	 * @return void
	 * @throws
	 */
	public static void cleanCustLabelData() throws SQLException{
		List<String> sqls = new ArrayList<String>();
		sqls.add("DELETE FROM T_CRM_CMDTBS WHERE FID = -1");
		sqls.add("DELETE FROM T_CRM_CMDTBSD WHERE FID = -1");
		SpringTestHelper.executeBatch(sqls);
		System.out.println("清理测试用客户同步数据成功");
	}
	
}
