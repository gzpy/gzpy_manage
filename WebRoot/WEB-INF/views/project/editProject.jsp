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

</head>

<body>
	<div class="pageFormContent" layoutH="60">
		<div class="pageContent">
			<form method="post" action="editProject.do?projectId=${project.projectId}" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);">
				<div class="pageFormContent nowrap" layoutH="97">
					<fieldset>
						<legend>修改项目</legend>
						<dl>
							<dt>项目标题：</dt>
							<dd>
								<input name="projectTitle" value="${project.projectTitle}"
									class="required" type="text" />
							</dd>
						</dl>
						<dl>
							<dt>发表时间：</dt>
							<dd>
								<input name="PushDate" type="text" value="${issueDate}"
									readonly="true" class="date required" />
							</dd>
						</dl>
						<dl>
							<dt>SEO标题：</dt>
							<dd>
								<input name="SEOTitle" type="text" value="${project.SEOTitle}"
									class="required" />
							</dd>
						</dl>
						<dl>
							<dt>SEO关键字：</dt>
							<dd>
								<input name="SEOKeywords" type="text"
									value="${project.SEOKeywords}" class="required" />
							</dd>
						</dl>

						<dl>
							<dt>SEO描述：</dt>
							<dd>
								<input name="SEODescription" type="text"
									value="${project.SEODescription}" class="required" />
							</dd>
						</dl>

					</fieldset>
				<c:if test="${not empty imageList}">
					<div class="unit">
						<label>当前图片：</label>
						<c:forEach items="${imageList}" var="item">
							<img src="${ctx}${item}" width="100" height="100" />
						</c:forEach>
					</div>
				</c:if>
						<div class="unit">
						  <label>修改图片：</label>
								<ul id="upload-preview" class="upload-preview"></ul>
								<div class="upload-wrap" rel="#upload-preview">
									<input type="file" name="file" src="${ctx}${item}"
										accept="image/*" multiple="multiple">
								</div>
						</div>
									<fieldset>
										<legend>项目描述</legend>
										<dl class="nowrap">
											<dt></dt>
											<dd>
												<textarea name="introduction" style="height:200px;"
													class="editor required" cols="80" rows="2">${project.introduction}</textarea>
											</dd>
										</dl>
									</fieldset>
									<div class="formBar">
										<ul>
											<li><div class="buttonActive">
													<div class="buttonContent">
														<button type="submit">修改</button>
													</div>
												</div></li>
											<li><div class="button">
													<div class="buttonContent">
														<button type="button" class="close">取消</button>
													</div>
												</div></li>
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

