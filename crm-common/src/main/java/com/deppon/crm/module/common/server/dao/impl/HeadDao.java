package com.deppon.crm.module.common.server.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.common.server.dao.IHeadDao;
import com.deppon.crm.module.common.shared.domain.DetailVo;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

@SuppressWarnings("unchecked")
public class HeadDao extends iBatis3DaoImpl implements IHeadDao {
	
	private static final String NAME_SPACE_MAPPING = "com.deppon.crm.module.common.shared.domain.Head.";
	private static final String NAME_SPACE_MAPPING2 = "com.deppon.crm.module.common.shared.domain.DetailVo.";

	public List<Head> getAllHeadDetail() {
		List<Head> list = null;
		list = getSqlSession()
				.selectList(NAME_SPACE_MAPPING+"getAllHeadDetail");
		return list;
	}

	/**
	 * 查询最后更新时间
	 * @return
	 */
	public Date getLastModifyTime() {
		Date lastModyfyTime = (Date) getSqlSession()
				.selectOne(NAME_SPACE_MAPPING+"getLastModifyTime");
		return lastModyfyTime;
	}

	@Override
	public Head getHeadByCodeType(String codeType) {
		return (Head) getSqlSession().selectOne(NAME_SPACE_MAPPING+"getHeadDetailByCodeType", codeType);
	}
	//getHeadAll
	@Override
	public List<DetailVo> getHeadDetail(DetailVo detailVo,int start,int limit) {
		List<DetailVo> list = null;
		if(limit != -1) {
			RowBounds rb = new RowBounds(start,limit);
			list = getSqlSession().selectList(NAME_SPACE_MAPPING2+"getHeadDetail",detailVo,rb);
			System.out.println("查询结果条数："+list.size());
			return list;
		}
		list = getSqlSession().selectList(NAME_SPACE_MAPPING2+"getHeadDetail",detailVo);
		return list;
	}

	@Override
	public List<Head> getAllHead(Head head,int start,int limit) {
		List<Head> list = null;
		if(limit != -1) {
			RowBounds rb = new RowBounds(start,limit);
			list = getSqlSession().selectList(NAME_SPACE_MAPPING2+"getHeadAll",head,rb);
			return list;
		}
		list = getSqlSession().selectList(NAME_SPACE_MAPPING2+"getHeadAll",head);
		return list;
	}

	@Override 
	public boolean updateHead(Head head) {
		int updateSize = getSqlSession().update(NAME_SPACE_MAPPING2+"updateHead", head);
		return updateSize>0;
	}

	@Override  
	public boolean delHead(Head head) {
		int updateSize = getSqlSession().delete(NAME_SPACE_MAPPING2+"deleteHead", head);
		return updateSize>0;
	}

	@Override
	public boolean insertHead(Head head) {
		int updateSize = getSqlSession().insert(NAME_SPACE_MAPPING2+"insertHead", head);
		return updateSize>0;
	}

	@Override
	public int getHeadDetailCount(DetailVo detailVo) {
		return (Integer)this.getSqlSession().selectOne(NAME_SPACE_MAPPING2 +"getHeadDetailCount", detailVo);
	}

	@Override
	public int getAllHeadCount(Head head) {
		return (Integer)this.getSqlSession().selectOne(NAME_SPACE_MAPPING2 +"getHeadAllCount", head);
	}

}
