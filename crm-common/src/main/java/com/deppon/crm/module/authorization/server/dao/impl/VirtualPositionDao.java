package com.deppon.crm.module.authorization.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.authorization.server.dao.IVirtualPositionDao;
import com.deppon.crm.module.authorization.shared.domain.EhrPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualMapEhrPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPositionRole;
import com.deppon.crm.module.authorization.shared.domain.VirtualPositionRoleVo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
import com.lowagie.text.Row;
import com.mongodb.util.Hash;

public class VirtualPositionDao extends iBatis3DaoImpl implements IVirtualPositionDao {
	private static final String NAMESPACE_VIRTUALPOSITION="com.deppon.crm.module.authorization.shared.domain.VirtualPosition.";
	private static final String NAMESPACE_EHRPOSITION="com.deppon.crm.module.authorization.shared.domain.EhrPosition.";
	@Override
	public VirtualPosition insert(VirtualPosition vp) {
		this.getSqlSession().insert(NAMESPACE_VIRTUALPOSITION + "insert", vp);
		return vp;
	}

	@Override
	public void updateByPrimaryKeySelective(VirtualPosition vp) {
		this.getSqlSession().update(NAMESPACE_VIRTUALPOSITION + "updateByPrimaryKey", vp);
	}

	@Override
	public void deleteByPrimaryKey(String id) {
		this.getSqlSession().delete(NAMESPACE_VIRTUALPOSITION + "deleteByPrimaryKey", id);
	}

	@Override
	public VirtualPosition selectByPrimaryKey(String id) {
		return (VirtualPosition) this.getSqlSession().selectOne(NAMESPACE_VIRTUALPOSITION + "selectByPrimaryKey", id);
	}

	@Override
	public boolean isExistVirtualPositionName(String positionName,String vpId) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("positionName", positionName);
		params.put("vpId", vpId);
		int i= (Integer) this.getSqlSession().selectOne(NAMESPACE_VIRTUALPOSITION + "isExistVirtualPositionName", params);
		return i > 0 ? true:false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VirtualPositionRole> getRolesByVpId(String vpId) {
		return this.getSqlSession().selectList(NAMESPACE_VIRTUALPOSITION + "getRolesByVpId", vpId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EhrPosition> getStaPositionHaveMappedWithVP(String vpId) {
//		RowBounds rb = new RowBounds(start, limit);
		Map<String,String> params = new HashMap<String, String>();
		params.put("vpId", vpId);
		return this.getSqlSession().selectList(NAMESPACE_EHRPOSITION + "getStaPositionHaveMappedWithVP", params);
	}

	@Override
	public int countStaPositionHaveMappedWithVP(String vpId) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("vpId", vpId);
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_EHRPOSITION + "countStaPositionHaveMappedWithVP",vpId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EhrPosition> getStaPositionWaitToMapWithVP(String vpId,String ehrPositionName) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("vpId", vpId);
		params.put("ehrPositionName", ehrPositionName);
		return this.getSqlSession().selectList(NAMESPACE_EHRPOSITION + "getStaPositionWaitToMapWithVP", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VirtualPosition> queryVirtualPosition(String positionName,String ehrPositionName,int start,int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		Map<String,String> map =  new HashMap<String,String>();
		map.put("positionName", positionName);
		map.put("ehrPositionName", ehrPositionName);
		return this.getSqlSession().selectList(NAMESPACE_VIRTUALPOSITION + "queryVirtualPosition", map, rowBounds);
	}

	@Override
	public int countVirtualPosition(String positionName, String ehrPositionName) {
		Map<String,String> map =  new HashMap<String,String>();
		map.put("positionName", positionName);
		map.put("ehrPositionName", ehrPositionName);
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_VIRTUALPOSITION + "countVirtualPosition", map);
	}

	@Override
	public void deleteVirtualMapStandard(VirtualMapEhrPosition vms) {
		this.getSqlSession().delete(NAMESPACE_VIRTUALPOSITION + "deleteVirtualMapStandard", vms);
	}

	@Override
	public void addVirtualMapStandard(VirtualMapEhrPosition vms) {
		this.getSqlSession().insert(NAMESPACE_VIRTUALPOSITION + "addVirtualMapStandard", vms);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VirtualPositionRoleVo> queryVirPositionRole(String virtualPositionName, String roleName,int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		Map<String,String> params = new HashMap<String, String>();
		params.put("virtualPositionName", virtualPositionName);
		params.put("roleName", roleName);
		return this.getSqlSession().selectList(NAMESPACE_VIRTUALPOSITION + "queryVirPositionRole", params, rowBounds);
	}

	@Override
	public int countQueryVirPositionRole(String virtualPositionName,
			String roleName) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("virtualPositionName", virtualPositionName);
		params.put("roleName", roleName);
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_VIRTUALPOSITION + "countQueryVirPositionRole",params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VirtualPosition> queryVirtualPositionByName(
			String virtualPositionName, int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		Map<String,String> param = new HashMap<String, String>();
		param.put("virtualPositionName", virtualPositionName);
		return this.getSqlSession().selectList(NAMESPACE_VIRTUALPOSITION + "queryVirtualPositionByName", param, rowBounds);
	}

	@Override
	public int countQueryVirtualPositionByName(String virtualPositionName) {
		Map<String,String> param = new HashMap<String, String>();
		param.put("virtualPositionName", virtualPositionName);
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_VIRTUALPOSITION + "countQueryVirtualPositionByName", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VirtualPositionRole> queryVirtualPositonRoleByvpId(
			String virtualPositionId) {
		return this.getSqlSession().selectList(NAMESPACE_VIRTUALPOSITION + "queryVirtualPositonRoleByvpId", virtualPositionId);
	}

	@Override
	public void deleteVirtualPostionRole(VirtualPositionRole vpr) {
		this.getSqlSession().delete(NAMESPACE_VIRTUALPOSITION + "deleteVirtualPostionRole", vpr);
	}

	@Override
	public void addVirtualPostionRole(VirtualPositionRole vpr) {
		this.getSqlSession().insert(NAMESPACE_VIRTUALPOSITION + "addVirtualPostionRole", vpr);
	}

	@Override
	public String refreshUserRole(String virtualPositionId) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("virtualPositionId", virtualPositionId);
		this.getSqlSession().selectOne(NAMESPACE_VIRTUALPOSITION + "refreshUserRole", params);
		return params.get("result");
	}

}
