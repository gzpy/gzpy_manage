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
	<form id="pagerForm" method="post" action="${ctx}/product/toProductManage.do">
		<input type="hidden" name="pageNum" value="${currentPage}" />
		<input type="hidden" name="numPerPage" value="${pageSize}" />
	</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="#" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						<label>产品名称：</label>
						<input type="text" name="keywords" value=""/>
					</td>
					<td>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">&nbsp;&nbsp;检索&nbsp;&nbsp;</button></div></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/product/toAddProduct.do" target="dialog" rel="addProduct" mask="true" title="添加产品" width="686" height="508" resizable="false"><span>添加产品</span></a></li>
		</ul>
	</div>
	<table class="table" width="80%" layoutH="112">
		<thead>
			<tr>
				<!-- <th width="22" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>-->
				<th width="40" align="center">序号</th>
				<th width="50" align="center">产品名称</th>
				<th width="50" align="center">发布日期</th>
				<th width="150" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list_product}" var="product" varStatus="idx">
				<tr>
					<!-- <td><input name="ids" value="xxx" type="checkbox"></td>-->
					<td align="center">${idx.index + (pageSize*(currentPage-1))+1}</td>
					<td>${product.productTitle}</td>
					<td>${product.issueDate}</td>
					<td>
						<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
						<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
					</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
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
