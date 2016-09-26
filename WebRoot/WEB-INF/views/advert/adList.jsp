<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/include.inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head></head>

<body>
	<form id="pagerForm" method="post" action="${ctx}/advert/toAdManage.do">
		<input type="hidden" name="pageNum" value="${currentPage}" />
		<input type="hidden" name="numPerPage" value="${pageSize}" />
	</form>

<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);"
			action="${ctx}/advert/findAdByName.do" method="post">
			<div class="searchBar">
				<ul class="searchContent">
					<li><label>广告名称：</label> <input type="text"
						name="inputName" value="" class="required" /></li>
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
			<li><a class="add" href="${ctx}/advert/toAddAd.do" target="dialog" rel="addAd" mask="true" title="添加广告" width="850" height="530" resizable="false"><span>添加广告</span></a></li>
			<li><a class="add" href="${ctx}/advert/deleteSelectUser.do?String[]=${ids}" target="dialog" rel="addAd" mask="true" title="添加广告" width="850" height="530" resizable="false"><span>批量删除</span></a></li>
		</ul>
	</div>
	<form action="">
	<table class="table" width="80%" layoutH="112">
		<thead>
			<tr>
				<th width="22" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="40" align="center">序号</th>
				<th width="50" align="center">广告名称</th>
				<th width="50" align="center">广告描述</th>
				<th width="50" align="center">广告排序</th>
				<th width="150" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list_ad}" var="ad" varStatus="idx">
				<tr>
					<td><input name="ids" value="${ad.id }" type="checkbox"></td>
					<td align="center">${idx.index + (pageSize*(currentPage-1))+1}</td>
					<td>${ad.adName}</td>
					<td>${ad.adDescription}</td>
					<td>${ad.adOrder}</td>

					<td>
						<a title="删除" target="ajaxTodo" href="${ctx }/advert/deleteAd.do?id=${ad.id}" class="btnDel">删除</a>
						<a title="编辑" target="dialog" href="${ctx}/advert/toUpdateAd.do?id=${ad.id}" mask="true" title="编辑广告" width="850" height="530" class="btnEdit">编辑</a>
						<a title="查看详情" target="dialog" href="${ctx}/advert/toAdDetail.do?id=${ad.id}" mask="true" title="查看详情" width="850" height="530" class="btnView">查看详情</a>
					</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	</form>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${pageSize}" pageNumShown="10" currentPage="${currentPage}"></div>

	</div>
</div>

  </body>
</html>
