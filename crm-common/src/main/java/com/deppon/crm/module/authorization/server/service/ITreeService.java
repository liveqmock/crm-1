package com.deppon.crm.module.authorization.server.service;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.Tree;
import com.deppon.crm.module.authorization.shared.domain.User;

public interface ITreeService {
	public List<Tree> getTreeByUserId(Integer userId);
	public Integer saveTree(Tree tree);
	public Integer deleteTreeByUser(Integer id);
}
