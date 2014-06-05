package com.deppon.crm.module.uums.server.service.Imp;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.sync.shared.SyncException;
import com.deppon.crm.module.uums.server.dao.IOrgDao;
import com.deppon.crm.module.uums.server.service.IOrgService;
import com.deppon.crm.module.uums.shared.domain.OrgInfo;
import com.deppon.crm.module.uums.shared.exception.invalidIdException;

public class OrgService implements IOrgService{
    private IOrgDao orgDao;
    
	public IOrgDao getOrgDao() {
		return orgDao;
	}
	public void setOrgDao(IOrgDao orgDao) {
		this.orgDao = orgDao;
	}
	@Override
	public int insert(OrgInfo entity) {
		String parentDeptCode=entity.getParentOrgBenchmarkCode();
		if(StringUtils.isNotEmpty(parentDeptCode)){
			OrgInfo parent=orgDao.searchByCode(parentDeptCode);
			if(null==parent){//如果上级部门不存在，等到下次进行同步
					throw new SyncException("OrgService.insert未找到"+parentDeptCode+"此上级部门");	
			}
		}
		int res=orgDao.insert(entity);
		return res;
	}
	@Override
	public int updateById(OrgInfo entity, String orgId) {
		int res=orgDao.updateById(entity,orgId);
		return res;
	}
	@Override
	public int updateByCode(OrgInfo entity, String orgCode) {
		int res=orgDao.updateByCode(entity,orgCode);
		return res;
	}
	@Override
	public int deleteById(String orgId) {
		if(StringUtils.isEmpty(orgId)){
				throw new SyncException("OrgService.deleteById(String orgId) 无效orgId");
		}
		int res=orgDao.deleteById(orgId);
		return res;
	}
	@Override
	public int deleteByCode(String orgCode) {
		if(StringUtils.isEmpty(orgCode)){
				throw new SyncException("OrgService.deleteByCode(String orgCode) 无效orgCode");
		}
		int res=orgDao.deleteByCode(orgCode);
		return res;
	}
	@Override
	public OrgInfo searchById(String orgId) {
		if(StringUtils.isEmpty(orgId)){
				throw new SyncException("OrgService.searchById(String orgId) 无效orgId");
		}
		OrgInfo res=orgDao.searchById(orgId);
		return res;
	}
	@Override
	public OrgInfo searchByCode(String orgCode) {
		if(StringUtils.isEmpty(orgCode)){
				throw new SyncException("OrgService.searchByCode(String orgCode) 无效orgCode");
		}
		OrgInfo res=orgDao.searchByCode(orgCode);
		return res;
	}
	@Override
	public int reDeleteByCode(String orgCode) {
		if(StringUtils.isEmpty(orgCode)){
				throw new SyncException("OrgService.reDeleteByCode(String orgCode) 无效orgCode");	
		}
		int res=orgDao.reDeleteByCode(orgCode);
		return res;
	}

}
