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
	<form id="pagerForm" method="post" action="${ctx}/newsType/toNewsTypeManage.do">
		<input type="hidden" name="pageNum" value="${currentPage}" />
		<input type="hidden" name="numPerPage" value="${pageSize}" />
		<input type="hidden" name="typeName" value="${empty typeName ? '' : typeName}" />
		<c:choose>
			<c:when test="${delStatus eq '%N%' }">
				<input type="hidden" name="delStatus" value="N" />
			</c:when>
			<c:when test="${delStatus eq '%Y%' }">
				<input type="hidden" name="delStatus" value="Y" />
			</c:when>
			<c:otherwise>
				<input type="hidden" name="delStatus" value="" />
			</c:otherwise>
		</c:choose>
	</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/newsType/toNewsTypeManage.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						<label>类型名称：</label>
						<input type="text" name="typeName" value="" maxlength="30"/>
					</td>
					<td>
						<label>删除状态：</label>
						<c:choose>
							<c:when test="${delStatus eq '%N%'}">
								<input type="radio" name="delStatus" value="N" checked="checked"/>未删除
								<input type="radio" name="delStatus" value="Y"/>已删除
								<input type="radio" name="delStatus" value=""/>全部
							</c:when>
							<c:when test="${delStatus eq '%Y%'}">
								<input type="radio" name="delStatus" value="N"/>未删除
								<input type="radio" name="delStatus" value="Y" checked="checked"/>已删除
								<input type="radio" name="delStatus" value=""/>全部
							</c:when>
							<c:otherwise>
								<input type="radio" name="delStatus" value="N"/>未删除
								<input type="radio" name="delStatus" value="Y"/>已删除
								<input type="radio" name="delStatus" value="" checked="checked"/>全部
							</c:otherwise>
						</c:choose>
						
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
			<li><a class="add" href="${ctx}/newsType/toAddNewsType.do" target="dialog" rel="addnewsType" mask="true" title="添加文章类型" width="550" height="150" resizable="false"><span>添加文章类型</span></a></li>
			<li><a class="delete" href="${ctx}/newsType/deleteTypes.do" target="selectedToDo" title="确定删除？"><span>批量删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="80%" layoutH="112">
		<thead>
			<tr>
				<th width="22" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="40" align="center">序号</th>
				<th width="50" align="center">文章类型</th>
				<th width="50" align="center">删除状态</th>
				<th width="150" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list_newsType}" var="newsType" varStatus="idx">
				<tr>
					<td><input name="ids" value="${newsType.typeId }" type="checkbox"></td>
					<td align="center">${idx.index + (pageSize*(currentPage-1))+1}</td>
					<td>${newsType.typeName}</td>
					<c:choose>
						<c:when test="${newsType.delStatus eq 'Y' }">
							<td style="color:red;">已删除</td>
						</c:when>
						<c:otherwise>
							<td style="color:green;">未删除</td>
						</c:otherwise>
					</c:choose>
					<td>
						<a title="是否彻底删除？" target="ajaxTodo" href="${ctx }/newsType/deleteNewsType.do?typeId=${newsType.typeId}" class="btnDel">删除</a>
						<a title="编辑" target="dialog" href="${ctx}/newsType/toUpdateNewsType.do?typeId=${newsType.typeId}" mask="true" title="修改新闻类型" width="550" height="150" class="btnEdit">编辑</a>
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
