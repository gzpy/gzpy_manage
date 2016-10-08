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
		<form method="post" enctype="multipart/form-data" action="${ctx}/news/addNews.do" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="58">
				<dl>
					<dt style="text-align: right;">新闻标题：</dt>
					<dd>
						<input class="required" name="newsTitle" value="" type="text" size="20" maxlength="50"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">新闻类型：</dt>
					<dd>
						<select name="typeId" class="required combox">
							<option value="">---请选择---</option>
							<c:forEach items="${list_newsType}" var="newsType">
								<option value="${newsType.typeId }">${newsType.typeName }</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">SEO标题：</dt>
					<dd>
						<input name="SEOTitle" value="" type="text" size="20" maxlength="50"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">SEO关键字：</dt>
					<dd>
						<input name="SEOKeywords" value="" type="text" size="20" maxlength="50"/>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt style="text-align: right;">SEO描述：</dt>
					<dd>
						<textarea class="textInput" rows="2" cols="80" name="SEODescription"></textarea>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt style="text-align: right;">选择图片：</dt>
					<dd>
						<input type="file" name="file"/>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt style="text-align: right;">文章内容：</dt>
					<dd>
						<textarea class="editor" rows="12" cols="80" name="newsContent"></textarea>
					</dd>
				</dl>
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
