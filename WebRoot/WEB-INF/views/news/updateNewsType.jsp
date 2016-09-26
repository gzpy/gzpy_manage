<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/include.inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head></head>
  
  <body>
   	<div class="pageContent">
		<form method="post" action="${ctx}/newsType/updateNewsType.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="58">
				<input name="typeId" type="hidden" value="${newsType.typeId }" />
				<p>
					<label style="text-align: right;">文章类型：</label>
					<input name="typeName" type="text" size="26" value="${newsType.typeName }" maxlength="20" class="required"/>
				</p>
				<p>
					<label style="text-align: right;">删除状态：</label>
					<c:choose>
						<c:when test="${newsType.delStatus eq 'Y' }">	
							<input name="delStatus" type="radio" value="Y" checked="checked"/>Y
							<input name="delStatus" type="radio" value="N">N
						</c:when>
						<c:otherwise>
							<input name="delStatus" type="radio" value="Y"/>Y
							<input name="delStatus" type="radio" value="N" checked="checked">N
						</c:otherwise>
					</c:choose>
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
 </body>
</html>
