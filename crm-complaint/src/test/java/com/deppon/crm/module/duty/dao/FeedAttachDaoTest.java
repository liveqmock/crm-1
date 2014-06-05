package com.deppon.crm.module.duty.dao;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.duty.server.dao.IFeedAttachDao;
import com.deppon.crm.module.duty.server.util.DateCreateUtils;
import com.deppon.crm.module.duty.shared.domain.FeedAttach;
import com.deppon.crm.module.duty.util.TestUtil;

public class FeedAttachDaoTest extends TestCase{

	private IFeedAttachDao feedAttachDao =  TestUtil.feedAttachDao;;
	
	
	//通知对象结果查询
	@Test
	public void testSearchFeedAttach(){
		FeedAttach feedAttach = DateCreateUtils.createBean(FeedAttach.class);
		feedAttachDao.searchFeedAttach(feedAttach);
	}
	
	//通知对象结果查询
	@Test
	public void testSelectFeedAttachByPrimaryKey(){
		feedAttachDao.selectFeedAttachByPrimaryKey("111");
	}
	
	//通知对象修改
	@Test
	public void testUpdateFeedAttach(){
		FeedAttach feedAttach = DateCreateUtils.createBean(FeedAttach.class);
		feedAttachDao.updateFeedAttach(feedAttach);
	}

	//通知对象删除
	@Test
	public void testDeleteFeedAttachs(){
		List<String> ids = new ArrayList<String>();
		ids.add("111");
		feedAttachDao.deleteFeedAttachs(ids);
	}
	
	//通知对象删除
	@Test
	public void testDeleteFeedAttachsByFeedId(){
		feedAttachDao.deleteFeedAttachsByFeedId("111");
	}
	
	
	//通知对象新增
	@Test
	public void testInsertFeedAttach(){
		//FeedAttach feedAttach = DateCreateUtils.createBean(FeedAttach.class);
		//feedAttachDao.insertFeedAttach(feedAttach);
	}
	
}


