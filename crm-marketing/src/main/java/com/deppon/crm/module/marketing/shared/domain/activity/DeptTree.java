package com.deppon.crm.module.marketing.shared.domain.activity;

import java.util.LinkedList;
import java.util.List;

public class DeptTree {
	private String id;
	private String text;
	private boolean checked;
	private boolean leaf;
	private String deptStandardCode;
	private String parentId;
	private boolean canSelect;
	private int level;
	private List<DeptTree> children;
	private DeptTree nextBro;
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public String getId() {
		return id;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public boolean isChecked() {
		return checked;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public boolean isLeaf() {
		return leaf;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public String getDeptStandardCode() {
		return deptStandardCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public void setDeptStandardCode(String deptStandardCode) {
		this.deptStandardCode = deptStandardCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public boolean isCanSelect() {
		return canSelect;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public void setCanSelect(boolean canSelect) {
		this.canSelect = canSelect;
	}
	//	public String toString(){
//		String s = "{ id="+id+",deptName="+deptName;
//		for( DeptTree d : childTree ){
//			s+=d.toString();
//		}
//		s+="}";
//		return s;
//	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public String getText() {
		return text;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public List<DeptTree> getChildren() {
		return children;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 * @param children the children to set
	 */
	public void setChildren(List<DeptTree> children) {
		this.children = children;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public DeptTree getNextBro() {
		return nextBro;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public void setNextBro(DeptTree nextBro) {
		this.nextBro = nextBro;
	}
	
	
}
