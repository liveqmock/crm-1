<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://eos.primeton.com/tags/workflow" prefix="wf"%>
<html lang="zh-CN">
<head>
<title>工作流程图</title>
<meta charset="utf-8" />
<!-- extjs4 -->
<link type="text/css" rel="stylesheet"
	href="http://static.deppon.com/ext-4.0.7/resources/css/ext-all-gray.css" />
<script type="text/javascript"
	src="http://static.deppon.com/ext-4.0.7/bootstrap.js"></script>
<script type="text/javascript"
	src="http://static.deppon.com/ext-4.0.7/locale/ext-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="../styles/common/deppon.css?2012081110.0.1" />
<script type="text/javascript" charset="utf-8"
	src="../workflow/wfcomponent/web/js/Graphic.js?2013081110.0.1"></script>
<wf:processGraph processInstID="${processInstId}" zoomQuotiety="1">
	<wf:activityGraph activityType="start" title="开始" />
	<wf:activityGraph activityType="manual" />
	<wf:activityGraph activityType="route" />
	<wf:activityGraph activityType="subflow" />
	<wf:activityGraph activityType="toolapp" />
	<wf:activityGraph activityType="finish" />
</wf:processGraph>
</head>
<body class="tabcontentpadding">
</body>
</html>