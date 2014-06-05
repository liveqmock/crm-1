package com.deppon.crm.module.common.server.manager.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.manager.IDetailManager;
import com.deppon.crm.module.common.server.service.IDetailService;
import com.deppon.crm.module.common.shared.domain.Detail;

public class DetailManager implements IDetailManager {
	
	private IDetailService detailService;

	@Override
	public void insertDetail(Detail detail) {
		detailService.insertDetail(detail);
	}

	@Override
	public boolean updateDetail(Detail detail) {
         return detailService.updateDetail(detail);
	}

	@Override
	public Detail queryDetailById(String id) {
		return detailService.queryDetailById(id);
	}

	@Override
	public List<Detail> queryDetail(Detail detail) {
		return detailService.queryDetail(detail);
	}
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
	public Map<String, Map<String, String>> queryMapCodeDesc(String codeType,String codeDescs){
		return detailService.queryMapCodeDesc(codeType, codeDescs);
	}
	public void setDetailService(IDetailService detailService) {
		this.detailService = detailService;
	}

	@Override
	public boolean delDetail(Detail detail) {
		return detailService.delDetail(detail);
	}

	@Override
	public boolean updateDetailnew(Detail detail) {
		return detailService.updateDetailnew(detail);
	}

	@Override
	public boolean deleteDetail(Detail detail) {
		return detailService.deleteDetail(detail);
	}

	
}
