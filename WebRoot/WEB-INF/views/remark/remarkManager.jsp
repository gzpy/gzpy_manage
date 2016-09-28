<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ include file="/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form id="pagerForm" method="post" action="${ctx}/goRemarkManager.do">
	<input type="hidden" name="pageNum" value="${currentPage}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="goRemarkManager1.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					姓名：<input type="text" name="name"/>
				</td>
				<td>
					<select class="combox" name="status">
						<option value="">公开状态</option>
						<option value="Y">公开留言</option>
						<option value="N">未公开留言</option>
					</select>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>-->
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<!-- <li><a class="add" href="demo_page4.html" target="navTab"><span>添加</span></a></li>-->
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40">姓名</th>
				<th width="190">留言内容</th>
				<th width="100">回复内容</th>
				<th width="90">邮箱</th>
				<th width="80">电话</th>
				<th width="80">公开状态</th>
				<th width="80">删除状态</th>
				<th width="80">留言时间</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody align="center">
		<c:forEach items="${remarklist}" var="remarklist">
			<tr target="sid_user" rel="1">
				<td>${remarklist.name}</td>
				<td>${remarklist.remarkContent}</td>
				<td>${remarklist.replyContent}</td>
				<td>${remarklist.email}</td>
				<td>${remarklist.tel}</td>
				<td>${remarklist.status}</td>
				<td>${remarklist.delStatus}</td>
				<td>${remarklist.remarkTime}</td>
				<td>
					<a title="编辑" target="navTab" href="goEditRemark.do?remarkId=${remarklist.remarkId}" class="btnEdit">编辑</a>
				</td>
			</tr>		
			
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
			    <option value="">${numPerPage}</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="${totalPage}" currentPage="${currentPage}"></div>

	</div>
</div>

</body>
</html>