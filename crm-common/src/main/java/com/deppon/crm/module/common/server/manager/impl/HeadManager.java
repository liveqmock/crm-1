package com.deppon.crm.module.common.server.manager.impl;

import java.util.List;
import com.deppon.crm.module.common.server.manager.IHeadManager;
import com.deppon.crm.module.common.server.service.IHeadService;
import com.deppon.crm.module.common.shared.domain.DetailVo;
import com.deppon.crm.module.common.shared.domain.Head;

public class HeadManager implements IHeadManager {
	
	private IHeadService headService;

	/***
	 * 
	 * <p>
	 * Description:根据条件信息查询,查询数据字典头信息和详细<br />
	 * </p>
	 * @author fbl
	 * @version 0.1 2013-7-20
	 * @param Head
	 * @return
	 * List<Head>
	 */
	@Override
	public List<DetailVo> queryHeadDetail(DetailVo detailVo,int start,int limit) {
		return headService.queryHeadDetail(detailVo,start,limit);
	}

	@Override
	public List<Head> queryHeadAll(Head head,int start,int limit) {
		return headService.queryHeadAll(head,start,limit);
	}
	public void setHeadService(IHeadService headService) {
		this.headService = headService;
	}

	@Override
	public boolean updateHead(Head head) {
		return headService.updateHead(head);
	}

	@Override
	public boolean delHead(Head head) {
		return headService.delHead(head);
	}

	@Override
	public boolean insertHead(Head head) {
		return headService.insertHead(head);
	}

	@Override
	public int getHeadDetailCount(DetailVo detailVo) {
		return headService.getHeadDetailCount(detailVo);
	}

	@Override
	public int getAllHeadCount(Head head) {
		return headService.getAllHeadCount(head);
	}
	
}
