package com.deppon.crm.module.common.server.service;

import com.deppon.crm.module.common.shared.domain.HardWareToken;

public interface IHardWareTokenService {

	public HardWareToken save(HardWareToken token);
	public HardWareToken getHardWareTokenByToken(String token);
	public HardWareToken getHardWareTokenById(String id);
	public boolean update(HardWareToken token);
}
