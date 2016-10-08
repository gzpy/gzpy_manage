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
	<form id="pagerForm" method="post" action="${ctx}/user/toUserManage.do">
		<input type="hidden" name="pageNum" value="${currentPage}" /> <input
			type="hidden" name="numPerPage" value="${pageSize}" />
			
			<input type="hidden" name="inputName" value="${empty inputName ? '' : inputName}" />
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
		<form onsubmit="return navTabSearch(this);"
			action="${ctx }/user/toUserManage.do" method="post">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td><label>用户姓名：</label> <input type="text" name="inputName"
							value="" /></td>
							<td>
							<c:choose>
							<c:when test="${delStatus eq '%N%' }">
								<input type="radio" name="delStatus" value="N" checked="checked"/>未删除
								<input type="radio" name="delStatus" value="Y" />已删除
								<input type="radio" name="delStatus" value="" />全部
							</c:when>
							<c:when test="${delStatus eq '%Y%' }">
								<input type="radio" name="delStatus" value="N"/>未删除
								<input type="radio" name="delStatus" value="Y" checked="checked"/> 已删除
								<input type="radio" name="delStatus" value="" />全部
							</c:when>
							<c:otherwise>
								<input type="radio" name="delStatus" value="N" />未删除
								<input type="radio" name="delStatus" value="Y" />已删除
								<input type="radio" name="delStatus" value="" checked="checked"/>全部
							</c:otherwise>
							</c:choose>
							</td>
						<td>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">&nbsp;&nbsp;检索&nbsp;&nbsp;</button>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${ctx}/user/toAddUser.do"
					target="dialog" rel="addUser" mask="true" title="添加用户" width="850"
					height="530" resizable="false"><span>添加用户</span></a></li>
				<li><a class="delete" href="${ctx}/user/deleteUsers.do"
					target="selectedToDo" mask="true" rel="ids" title="是否修改所选项？"
					width="850" height="530" resizable="false"><span>批量修改删除状态</span></a></li>
			</ul>
		</div>
		<table class="table" width=100%" layoutH="112">
			<thead>
				<tr>
					<th width="22" align="center"><input type="checkbox"
						group="ids" class="checkboxCtrl"></th>
					<th width="30" align="center">序号</th>
					<th width="50" align="center">用户姓名</th>
					<th width="80" align="center">手机号码</th>
					<th width="200" align="center">电子邮箱</th>
					<th width="50" align="center">登录账号</th>
					<th width="50" align="center">登录密码</th>
					<th width="50" align="center">删除状态</th>
					<th width="100" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list_user}" var="user" varStatus="idx">
					<tr>
						<td><input name="ids" value="${user.userId }" type="checkbox"></td>
						<td align="center">${idx.index + (pageSize*(currentPage-1))+1}</td>
						<td>${user.userName}</td>
						<td>${user.mobilePhone}</td>
						<td>${user.email}</td>
						<td>${user.loginName}</td>
						<td>${user.password}</td>
						<c:if test="${user.delStatus eq 'Y' }">
							<td style="color:red;">已删除</td>
						</c:if>
						<c:if test="${user.delStatus eq 'N' }">
							<td style="color:green;">未删除</td>
						</c:if>
						<td><a title="是否彻底删除？" target="ajaxTodo"
							href="${ctx }/user/deleteUser.do?userId=${user.userId}"
							class="btnDel">删除</a> <a title="编辑" target="dialog"
							href="${ctx}/user/toUpdateUser.do?userId=${user.userId}"
							mask="true" title="修改用户" width="850" height="530" class="btnEdit">编辑</a>
							<a title="查看详情" target="dialog"
							href="${ctx}/user/toUserDetail.do?userId=${user.userId}"
							mask="true" title="查看详情" width="850" height="530" class="btnView">查看详情</a>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> <select class="combox" name="numPerPage"
					onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="20">20</option>
					<option value="50">50</option>
					<option value="100">100</option>
					<option value="200">200</option>
				</select> <span>条，共${totalCount}条</span>
			</div>

			<div class="pagination" targetType="navTab"
				totalCount="${totalCount}" numPerPage="${pageSize}"
				pageNumShown="10" currentPage="${currentPage}"></div>

		</div>
	</div>

</body>
</html>
