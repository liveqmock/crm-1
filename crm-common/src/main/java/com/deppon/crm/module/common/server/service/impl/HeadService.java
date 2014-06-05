package com.deppon.crm.module.common.server.service.impl;

import java.util.List;

import com.deppon.crm.module.common.server.dao.IHeadDao;
import com.deppon.crm.module.common.server.service.IHeadService;
import com.deppon.crm.module.common.shared.domain.DetailVo;
import com.deppon.crm.module.common.shared.domain.Head;

public class HeadService implements IHeadService {
	
	private IHeadDao headDao;

	/***
	 * 
	 * <p>
	 * Description:根据codeType以及描述信息查询,查询描述以及父级描述<br />
	 * </p>
	 * @author fbl
	 * @version 0.1 2014-4-15
	 * @param Head
	 * @return 
	 * 
	 */
	@Override
	public List<DetailVo> queryHeadDetail(DetailVo detailVo,int start,int limit) {
		return headDao.getHeadDetail(detailVo,start,limit);
	}
	@Override
	public List<Head> queryHeadAll(Head head,int start,int limit) {
		return headDao.getAllHead(head,start,limit);
	}
	public void setHeadDao(IHeadDao headDao) {
		this.headDao = headDao;
	}
	@Override
	public boolean updateHead(Head head) {
		return headDao.updateHead(head);
	}
	@Override
	public boolean delHead(Head head) {
		return headDao.delHead(head);
	}
	@Override
	public boolean insertHead(Head head) {
		return headDao.insertHead(head);
	}
	@Override
	public int getHeadDetailCount(DetailVo detailVo) {
		return headDao.getHeadDetailCount(detailVo);
	}
	@Override
	public int getAllHeadCount(Head head) {
		return headDao.getAllHeadCount(head);
	}
	
}
