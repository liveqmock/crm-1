package com.deppon.crm.module.common.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.shared.domain.Detail;

public interface IDetailManager {
	
	/**
	 * 
	 * <p>
	 * Description:添加 数据字典详细数据信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-18
	 * @param detail
	 * void
	 */
	public void insertDetail(Detail detail);
	/**
	 * 
	 * <p>
	 * Description:根据ID 更新 数据字典详细数据信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-18
	 * @param detail
	 * void
	 */
	public boolean updateDetail(Detail detail);
	/**
	 * 
	 * <p>
	 * Description:根据ID 修改 数据字典详细数据信息<br />
	 * </p>
	 * @author fbl
	 * @version 0.1 2014-4-18
	 * @param detail
	 * void
	 */
	public boolean updateDetailnew(Detail detail);
	/**
	 * 
	 * <p>
	 * Description:根据id 删除 数据字典详细<br />
	 * </p>
	 * @author fbl
	 * @version 0.1 2014-4-18
	 * @param detail
	 * void
	 */
	public boolean deleteDetail(Detail detail);

	/**
	 * 
	 * <p>
	 * Description:根据ID作废 数据字典详细数据信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-18
	 * @param detail
	 * @return
	 * boolean
	 */
	public boolean delDetail(Detail detail);
	/**
	 * 
	 * <p>
	 * Description:根据ID查询 数据字典详细数据信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-18
	 * @param id
	 * @return
	 * Detail
	 */
	public Detail queryDetailById(String id);
	/**
	 * 
	 * <p>
	 * Description:根据条件查询 数据字典详细数据信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-18
	 * @param detail
	 * @return
	 * List<Detail>
	 */
	public List<Detail> queryDetail(Detail detail);
	
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
