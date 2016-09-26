<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
			<form method="post"  action="addProject.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
				<div class="pageFormContent nowrap" layoutH="97">
					<fieldset>
						<legend>添加项目</legend>
						<dl>
							<dt>项目标题：</dt>
							<dd>
								<input name="projectTitle" class="required" type="text" />
							</dd>
						</dl>
						<dl>
							<dt>发表时间：</dt>
							<dd>
								<input name="PushDate" type="text" class="date required" />
							</dd>
						</dl>
						<dl>
							<dt>SEO标题：</dt>
							<dd>
								<input name="SEOTitle" type="text" class="required" />
							</dd>
						</dl>
						<dl>
							<dt>SEO关键字：</dt>
							<dd>
								<input name="SEOKeywords" type="text" class="required" />
							</dd>
						</dl>

						<dl>
							<dt>SEO描述：</dt>
							<dd>
								<input name="SEODescription" type="text" class="required" />
							</dd>
						</dl>

					</fieldset>

					<fieldset>
						<legend>项目描述</legend>
						<dl class="nowrap">
							<dt></dt>
							<dd>
								<textarea name="introduction" style="height:200px;"
									class="editor required" cols="80" rows="2"></textarea>
							</dd>
						</dl>
					</fieldset>
					<div class="formBar">
						<ul>
							<li><div class="buttonActive">
									<div class="buttonContent">
										<button type="submit">提交</button>
									</div>
								</div></li>
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

