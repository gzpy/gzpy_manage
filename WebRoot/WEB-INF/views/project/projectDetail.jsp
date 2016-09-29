<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<div class="pageFormContent" layoutH="60">
		<div class="pageContent">
			<form method="post"  action="editProject.do?projectId=${project.projectId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
				<div class="pageFormContent nowrap" layoutH="97">
					<fieldset>
						<legend>项目详情</legend>
						<dl>
							<dt>项目标题：</dt>
							<dd>
								<input name="projectTitle" value="${project.projectTitle}" readonly="readonly" type="text" />
							</dd>
						</dl>
						<dl>
							<dt>发表时间：</dt>
							<dd>
								<input name="PushDate" type="text" value="${project.issueDate}" readonly="readonly" />
							</dd>
						</dl>
						<dl>
							<dt>SEO标题：</dt>
							<dd>
								<input name="SEOTitle" type="text" value="${project.SEOTitle}" readonly="readonly"  />
							</dd>
						</dl>
						<dl>
							<dt>SEO关键字：</dt>
							<dd>
								<input name="SEOKeywords" type="text" value="${project.SEOKeywords}" readonly="readonly"/>
							</dd>
						</dl>

						<dl>
							<dt>SEO描述：</dt>
							<dd>
								<input name="SEODescription" type="text" value="${project.SEODescription}" readonly="readonly"/>
							</dd>
						</dl>

					</fieldset>
		<c:if test="${not empty imageList}">
            <div class="unit">
				<label>图片：</label>
				 <c:forEach items="${imageList}" var="item">
					<img src="${ctx}${item}" width="128" height="128"/>
				</c:forEach>
			</div>
		</c:if>
					<fieldset>
						<legend>项目描述</legend>
						<dl class="nowrap">
							<dt></dt>
							<dd>
								<textarea name="introduction"   style="height:200px;" 
								 class="editor" cols="80" rows="2">${project.introduction}</textarea>
							</dd>
						</dl>
					</fieldset>
					<div class="formBar">
						<ul>
							<li><div class="button">
									<div class="buttonContent">
										<button type="button" class="close">取消</button>
									</div>
								</div>
							</li>
						</ul>
					</div>
			</form>
		</div>
</body>
</html>
<script type="text/javascript">
	function customvalidXxx(element) {
		if ($(element).val() == "xxx")
			return false;
		return true;
	}
	
</script>

