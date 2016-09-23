<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/include.inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link href="${ctx}/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="${ctx}/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="${ctx}/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="${ctx}/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
	
	<script src="${ctx}/dwz/js/jquery-2.1.4.min.js" type="text/javascript"></script>

	<script src="${ctx}/dwz/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/js/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>
	
	<script src="${ctx}/dwz/bin/dwz.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	$(function(){
		DWZ.init("${ctx}/dwz/dwz.frag.xml", {
			pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
			debug:false,	// 调试模式 【true|false】
			callback:function(){
				initEnv();
				$("#themeList").theme({themeBase:"${ctx}/dwz/themes"}); // themeBase 相对于index页面的主题base路径
			}
		});
	});
	</script>
  </head>
  
  <body>
    <div id="layout">
		<div id="header">
			<div class="headerNav">
				<ul class="nav">
					<li><a href="#">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>界面组件</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/product/toProductManage.do" target="navTab" rel="productManage">产品管理</a></li>
							<li><a>文章管理</a>
								<ul>
									<li><a href="${ctx}/news/toNewsManage.do" target="navTab" rel="newsManage">管理文章</a></li>
									<li><a href="${ctx}/newsType/toNewsTypeManage.do" target="navTab" rel="newsTypeManage">管理文章分类</a></li>
								</ul>
							</li>
							
							<li><a href="${ctx}/find.do" target="navTab" rel="dlg_page1">项目管理</a></li>
							
							<li><a>留言管理</a>
								<ul>
									<li><a href="w_panel.html" target="navTab" rel="w_panel">添加留言</a></li>
									<li><a href="w_panel.html" target="navTab" rel="w_panel">管理留言</a></li>
								</ul>
							</li>
							
							<li><a>系统设置</a>
								<ul>
									<li><a href="w_panel.html" target="navTab" rel="w_panel">广告位管理</a></li>
									<li><a href="w_panel.html" target="navTab" rel="w_panel">用户设置</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px"></div>
					</div>
				</div>
		</div>
	</div>
	<div id="footer">Copyright &copy; 2016</div></div>
  </body>
</html>
