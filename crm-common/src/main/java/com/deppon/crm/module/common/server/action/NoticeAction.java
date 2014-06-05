package com.deppon.crm.module.common.server.action;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.INoticeManager;
import com.deppon.crm.module.common.shared.domain.Notice;
import com.deppon.crm.module.common.shared.domain.NoticeSearchCondition;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * Description: 公告Action类
 * NoticeAction.java Create on 2012-10-9 上午10:59:01 
 * @author xhl
 * @version 1.0
 */
public class NoticeAction extends AbstractAction {
	private INoticeManager noticeManager;
	
	

	public void setNoticeManager(INoticeManager noticeManager) {
		this.noticeManager = noticeManager;
	}
	
	private NoticeSearchCondition noticeSearchCondition;
	private Notice notice;
	private Notice noticece;
	private String noticeId;
	private String[] ids;
	private int start;
	private int limit;
	private List<Notice> noticeList;
	private Long totalCount;
	
	public void setNoticeSearchCondition(NoticeSearchCondition noticeSearchCondition) {
		this.noticeSearchCondition = noticeSearchCondition;
	}

	public NoticeSearchCondition getNoticeSearchCondition() {
		return noticeSearchCondition;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	public void setStart(int start) {
		this.start = start;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}

	public List<Notice> getNoticeList() {
		return noticeList;
	}
	
	/**
	 *@return  noticece;
	 */
	public Notice getNoticece() {
		return noticece;
	}

	private String statusActive;
	private String statusTop;
	
	public void setStatusActive(String statusActive) {
		this.statusActive = statusActive;
	}
	
	public void setStatusTop(String statusTop) {
		this.statusTop = statusTop;
	}
	
	
	/**
	 * @method: 1.公告查询
	 * @author: Rock
	 * @Description:查询出所有公告信息，放到”更多“的列表页
	 * 								       放到”后台管理“的列表页
	 * @return:noticeList
	 */
	@JSON
	public String searchNotices() {
		//少一个无条件查询的方法			无参数查全部
		noticeSearchCondition.setStart(start);
		noticeSearchCondition.setLimit(limit);

		if(statusActive.equals("true")){
			noticeSearchCondition.setActive(true);
		}
		if(statusActive.equals("false")){
			noticeSearchCondition.setActive(false);
		}
		if(statusActive.equals("null")){
			noticeSearchCondition.setActive(null);
		}
		
		if(statusTop.equals("true")){
			noticeSearchCondition.setTop(true);
		}
		if(statusTop.equals("false")){
			noticeSearchCondition.setTop(false);
		}
		if(statusTop.equals("null")){
			noticeSearchCondition.setTop(null);
		}
		
		noticeList = noticeManager.searchNoticeByCondition(noticeSearchCondition);
		totalCount = Long.valueOf(noticeManager.getCountByCondition(noticeSearchCondition));
		return SUCCESS;
	}
	
	/**
	 * @method:	2.查询单条公告
	 * @author: Rock
	 * @param:	noticeId
	 * @return:	notice
	 */
	@JSON
	public String findNoticeById() {
		noticece = noticeManager.findNoticeById(noticeId);
		return SUCCESS;
	}
	
	/**
	 * @method:	3.新增单条公告
	 * @author: Rock
	 * @param:	notice单条公告
	 */
	@JSON
	public String addNotice() {
		noticeManager.addNotice(notice,(User)UserContext.getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * @method:	4.更新单条公告
	 * @author: Rock
	 * @param:	notice单条公告
	 */
	@JSON
	public String updateNotice() {
		noticeManager.updateNoticeById(notice,(User)UserContext.getCurrentUser());
		return SUCCESS;
	}

	/**
	 * @method:	5.删除单条公告
	 * @author: Rock
	 * @param:	noticeId单条公告的id
	 */
	@JSON
	public String deleteNoticeById() {
		noticeManager.deleteNoticeById(noticeId);
		return SUCCESS;
	}
	
	/**
	 * @method:	6.批量删除公告
	 * @author: Rock
	 * @param:	ids[] 公告Id 的arr
	 */
	@JSON
	public String delteNoticesByIdList() {
		noticeManager.delteNoticesByIdList(ids);
		return SUCCESS;
	}

	/**
	 * @method:	7.批量更改公告action为false
	 * @author: Rock
	 * @param:	ids[] 需要更改状态公告Id 的arr
	 */
	@JSON
	public String updateNoticesByIdList() {
		noticeManager.updateNoticesByIdList(ids);
		return SUCCESS;
	}

	/**
	 * 8.
	 * <p>
	 * Description:首页默认查询<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-10-13
	 * @return
	 * String
	 */
	@JSON
	public String searchIndexNotices(){
		noticeSearchCondition = new NoticeSearchCondition();
		noticeSearchCondition.setActive(true);
		noticeSearchCondition.setLimit(10);
		noticeList = noticeManager.searchNoticeByCondition(noticeSearchCondition);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询首页最新公告<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-4-8下午4:23:42
	 * @return
	 * String
	 * @update 2013-4-8下午4:23:42
	 */
	public String searchIndexNewNotice(){
		noticece = noticeManager.searchIndexNewNotice();
		return SUCCESS;
	}
	
	/**
	 * 9.
	 * <p>
	 * Description:更多公告界面查询<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-10-13
	 * @return
	 * String
	 */
	@JSON
	public String searchMoreNotices(){
		noticeSearchCondition.setActive(true);
		noticeSearchCondition.setStart(start);
		noticeSearchCondition.setLimit(limit);
		noticeList = noticeManager.searchNoticeByCondition(noticeSearchCondition);
		//获取totalCount
		totalCount = Long.valueOf(noticeManager.getCountByCondition(noticeSearchCondition));
		return SUCCESS;
	}
	/**
	 * 10.
	 * <p>
	 * Description:更新公告访问次数<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-10-13
	 * @return
	 * String
	 */
	@JSON
	public String updateNoticeVisitsById() {		
		noticeManager.updateNoticeVisitsById(noticeId);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:11.置顶公告<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-10-14
	 * @return
	 * String
	 */
	@JSON
	public String topNotice(){
		noticeManager.topNoticeByIds(noticeId);
		return SUCCESS;
	}
}
