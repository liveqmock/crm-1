package com.deppon.crm.module.common.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.dao.IDetailDao;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class DetailDao extends iBatis3DaoImpl implements IDetailDao {
private final String MAPPING_NAME_SPACE= "com.deppon.crm.module.common.shared.domain.Detail.";
	/**
	 * 查询最后更新时间
	 * @return
	 */
	public Date getLastModifyTime() {
		Date lastModyfyTime = (Date) getSqlSession()
				.selectOne(
						MAPPING_NAME_SPACE+"getLastModifyTime");
		return lastModyfyTime;
	}

	@Override
	public Detail getDetailById(String id) {
		Detail detail= new Detail();
		detail.setId(id);
		return (Detail) getSqlSession().selectOne(MAPPING_NAME_SPACE+"getDetail",detail);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Detail> getDetailByCondition(Detail detail) {
		return getSqlSession().selectList(MAPPING_NAME_SPACE+"getDetail",detail);

	}

	@Override
	public void insertDetail(Detail detail) {
		 getSqlSession().insert(MAPPING_NAME_SPACE+"insertDetail", detail);
	}

	@Override
	public boolean updateDetail(Detail detail) {
		int updateSize = getSqlSession().update(MAPPING_NAME_SPACE+"updateDetail", detail);
		return updateSize>0 ;
	}

	/**
	 * 
	 * <p>
	 * Description:作废数据字典详细数据信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-18
	 * @param detail
	 * @return
	 * boolean
	 */
	public boolean delDetail(Detail detail){
		int updateSize = getSqlSession().update(MAPPING_NAME_SPACE+"delDetail", detail);
		return updateSize>0 ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Map<String,String>> queryMapCodeDesc(String codeType,
			String codeDescs) {
	
		Map<String,String> parameterMap = new HashMap<String,String>();
		parameterMap.put("codeType", codeType);
		parameterMap.put("codeDescs", codeDescs);

		return getSqlSession().selectMap(MAPPING_NAME_SPACE+"queryMapCodeDesc", parameterMap, "FCODEDESC");
	}

	@Override
	/**
	 * 修改detail信息
	 */
	public boolean updateDetailnew(Detail detail) {
		int updateSize = getSqlSession().update(MAPPING_NAME_SPACE+"updateDetail_new", detail);
		return updateSize>0 ;
	}

	@Override
	/**
	 * 删除数据字典
	 */
	public boolean deleteDetail(Detail detail) {
		int updateSize = getSqlSession().delete(MAPPING_NAME_SPACE+"deleteDetail", detail);
		return updateSize>0 ;
	}
}
