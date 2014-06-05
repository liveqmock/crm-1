package com.deppon.crm.module.common.server.manager;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.DetailVo;
import com.deppon.crm.module.common.shared.domain.Head;

public interface IHeadManager {
	
	
	/**
	 * 
	 * <p>
	 * Description:根据条件查询 数据字典详细数据信息<br />
	 * </p>
	 * @author fbl
	 * @version 0.1 2014-4-15
	 * @param DetailVo
	 * @return
	 * List<DetailVo>
	 */
	public List<DetailVo> queryHeadDetail(DetailVo detailVo,int start,int limit);
	/**
	 * 查询所有的数据字典头和详细连接后 记录总数
	 * @author fbl
	 * @version 0.1 2014-4-18
	 * @param DetailVo
	 * @return
	 */
	public int getHeadDetailCount(DetailVo detailVo);
	/**
	 * 
	 * <p>
	 * Description:根据条件查询 数据字典头<br />
	 * </p>
	 * @author fbl
	 * @version 0.1 2014-4-15
	 * @param Head
	 * @return
	 * List<Head>
	 */
	public List<Head> queryHeadAll(Head head,int start,int limit);
	/**
	 * 查询所有的数据字典头总数
	 * @author fbl
	 * @version 0.1 2014-4-18
	 * @param Head
	 * @return
	 */
	public int getAllHeadCount(Head head);
	/**
	 * 
	 * <p>
	 * Description:修改 数据字典头信息<br />
	 * </p>
	 * @author fbl
	 * @version 0.1 2014-4-15
	 * @param Head 
	 * @return
	 * boolean
	 */
	public boolean updateHead(Head head);
	/**
	 * 
	 * <p>
	 * Description:删除 数据字典头信息<br />
	 * </p>
	 * @author fbl
	 * @version 0.1 2014-4-15
	 * @param Head 
	 * @return
	 * boolean
	 */
	public boolean delHead(Head head);
	/**
	 * 
	 * <p>
	 * Description:新增 数据字典头信息<br />
	 * </p>
	 * @author fbl
	 * @version 0.1 2014-4-15
	 * @param Head 
	 * @return
	 * 
	 */
	public boolean insertHead(Head head);
}
