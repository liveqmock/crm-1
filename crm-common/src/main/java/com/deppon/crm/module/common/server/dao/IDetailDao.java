package com.deppon.crm.module.common.server.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.shared.domain.Detail;


/**
 * 
 * 作 者：ztjie 
 * 最后修改时间：2012年3月5日 
 * 描 述： 数据字典详细数据访问
 */
public interface IDetailDao {

	/**
	 * 得到 数据字典详细数据的最后更新时间
	 * @return
	 */
	public Date getLastModifyTime();
	
	/**
	 * 
	 * <p>
	 * Description:根据ID获取数据字典详细信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-18
	 * @param id
	 * @return
	 * Detail
	 */
	public Detail getDetailById(String id);
	
	/**
	 * 
	 * <p>
	 * Description:根据条件查询数据字典<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-18
	 * @return
	 * List<Detail>
	 */
	List<Detail> getDetailByCondition(Detail detail);
	
	/**
	 * 
	 * <p>
	 * Description:增加数据字典显示信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-18
	 * @param detail
	 * @return
	 * Detail
	 */
	void insertDetail(Detail detail);
	
	/**
	 * 
	 * <p>
	 * Description:根据ID更新数据字典信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-18
	 * @param detail
	 * @return
	 * boolean
	 */
	boolean updateDetail(Detail detail);
	
	/**
	 * 
	 * <p>
	 * Description:根据id修改数据字典信息<br />
	 * </p>
	 * @author fbl
	 * @version 0.1 2014-4-18
	 * @param detail
	 * @return
	 * boolean
	 */
	boolean updateDetailnew(Detail detail);
	/**
	 * 
	 * <p>
	 * Description:根据id删除数据字典信息<br />
	 * </p>
	 * @author fbl
	 * @version 0.1 2014-4-18
	 * @param detail
	 * @return
	 * boolean
	 */
	boolean deleteDetail(Detail detail);
	
	/**
	 * 
	 * <p>
	 * Description:作废数据字典详细数据信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-18
	 * @param detail
	 * @return
	 * boolean
	 */
	public boolean delDetail(Detail detail);

	/***
	 * 
	 * <p>
	 * Description:根据codeType以及描述信息查询,查询描述以及父级描述<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-20
	 * @param codeType
	 * @param codeDescs
	 * @return
	 * Map<String,String>
	 */
	public Map<String, Map<String, String>> queryMapCodeDesc(String codeType,String codeDescs);
}
