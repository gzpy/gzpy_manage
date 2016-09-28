<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

</head>

<body>
	<form id="pagerForm" method="post" action="${ctx}/searchProject.do">
		<input type="hidden" name="pageNum" value="${currentPage}" /> <input
			type="hidden" name="numPerPage" value="${numPerPage}" />
	</form>

	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);"
			action="${ctx}/searchProject.do" method="post">
			<div class="searchBar">
				<ul class="searchContent">
					<li><label>项目名称：</label> <input type="text"
						name="projectTitle" value="${projectTitle}" /></li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">检索</button>
						</div>
					</div>
					
				</ul>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${ctx}/goAddProject.do" target="navTab"
					rel="dlg_page9" minable="false"><span>添加项目</span></a></li>
				<li><a title="确实要删除这些项目吗?" target="selectedTodo" rel="ids" href="${ctx}/deleteAll.do" class="delete"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="112">
			<thead>
				<tr>
					<th width="22" align="center"><input type="checkbox"
						group="ids" class="checkboxCtrl"></th>
					<th width="50" align="center">项目名称</th>
					<th width="50" align="center">发布日期</th>
					<th width="50" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="item">
					<tr>
						<td><input name="ids" value="${item.projectId}" type="checkbox"></td>
						<td>${item.projectTitle}</td>
						<td>${item.issueDate}</td>
						<td><a title="删除" target="ajaxTodo"
							href="${ctx}/deleteProject.do?projectId=${item.projectId}"
							class="btnDel">删除</a> <a title="编辑" target="navTab"
							href="${ctx}/goEditProject.do?projectId=${item.projectId}"
							class="btnEdit">编辑</a> <a title="详情" target="navTab"
							href="${ctx}/projectDetail.do?projectId=${item.projectId}"
							class="btnview">详情</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> <select class="combox" name="numPerPage"
					onchange="navTabPageBreak({numPerPage:this.value})">
					<option selected="selected">${numPerPage}</option>
					<option value="5">5</option>
					<option value="50">50</option>
					<option value="100">100</option>
					<option value="200">200</option>

				</select> <span>条，共${totalCount}条</span>
			</div>

			<div class="pagination" targetType="navTab"
				totalCount="${totalCount}" numPerPage="${numPerPage}"
				pageNumShown="${totalPage}" currentPage="${currentPage}"></div>

		</div>
	</div>

</body>
</html>

