//公告里面的显示notice的gridModel
Ext.define('NoticeGridModel', {
    extend: 'Ext.data.Model',
    fields: [
		{name:'id'				},							// 公告id					// 
		{name:'title'			},							// 公告标题					// 必填
		{name:'type'			},							// 公告类别					// 必填
		{name:'moduleBelong'	},							// 所属模块					// 必填
		{name:'content'			},							// 公告内容					// 必填
		{name:'active'			},							// 是否发布					// 必填	BOOLEN
		{name:'top'				},							// 是否置顶					// 必填	BOOLEN
		{name:'modifyDate'		},							// 更新时间
		{name:'visits'			},							// 访问次数
		{name:'createDate'		},							// 创建时间
		{name:'modifyUser'		},							// 更新人
		{name:'createUser'		},							// 创建人
		{name:'imageAddr'		},							// 图片
		{name:'imageDescribe'	},							// 图片说明
		{name:'fileInfoList'	}							// 附件
    ]
});

//问题描述
Ext.define('ProblemModel', {
	extend: 'Ext.data.Model',
	fields: [
		{name: 'id'},
		{name: 'title'},
		{name: 'description'},
		{name: 'weburl'}
	]
});