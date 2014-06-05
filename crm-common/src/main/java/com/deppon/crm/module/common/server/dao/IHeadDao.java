package com.deppon.crm.module.common.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.DetailVo;
import com.deppon.crm.module.common.shared.domain.Head;


/**
 * 
 * 作 者：ztjie 
 * 最后修改时间：2012年3月5日 
 * 描 述： 数据字典头数据访问
 */
public interface IHeadDao {

	/**
	 * 查询所有的数据字典数据
	 * @return
	 */
	public List<Head> getAllHeadDetail();
	
	/**
	 * 
	 * <p>
	 * Description:根据CodeType获取数据库字典<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-26
	 * @param codeType
	 * @return
	 * Head
	 */
	public Head getHeadByCodeType(String codeType);

	/**
	 * 得到数据字典头数据的最后更新时间
	 * @return
	 */
	public Date getLastModifyTime();
	
	/**
	 * 查询所有的数据字典数据（在detail的基础上加编码类型描述）
	 * @author fbl
	 * @version 0.1 2014-4-18
	 * @param DetailVo
	 * @return
	 */
	public List<DetailVo> getHeadDetail(DetailVo detailVo,int start,int limit);
	/**
	 * 查询所有的数据字典头和详细连接后 记录总数
	 * @author fbl
	 * @version 0.1 2014-4-18
	 * @param DetailVo
	 * @return
	 */
	public int getHeadDetailCount(DetailVo detailVo);
	/**
	 * 查询所有的数据字典头信息
	 * @author fbl
	 * @version 0.1 2014-4-18
	 * @param Head
	 * @return
	 */
	public List<Head> getAllHead(Head head,int start,int limit);
	/**
	 * 查询所有的数据字典头总数
	 * @author fbl
	 * @version 0.1 2014-4-18
	 * @param Head
	 * @return
	 */
	public int getAllHeadCount(Head head);
	/**
	 * 修改数据字典头信息
	 * @author fbl
	 * @version 0.1 2014-4-18
	 * @param Head
	 * @return
	 */
	public boolean updateHead(Head head);
	/**
	 * 删除数据字典头
	 * @author fbl
	 * @version 0.1 2014-4-18
	 * @param Head
	 * @return
	 */
	public boolean delHead(Head head);
	/**
	 * 新增据字典头
	 * @author fbl
	 * @version 0.1 2014-4-18
	 * @param Head
	 * @return
	 */
	public boolean insertHead(Head head);
}
