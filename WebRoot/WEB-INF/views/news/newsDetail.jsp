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
		<div class="pageFormContent" layoutH="58">
			<dl>
				<dt style="text-align: right;">SEO标题：</dt>
				<dd>
					<input name="SEOTitle" value="${news.SEOTitle }" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt style="text-align: right;">SEO关键字：</dt>
				<dd>
					<input name="SEOKeywords" value="${news.SEOKeywords }" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt style="text-align: right;">SEO描述：</dt>
				<dd>
					<textarea class="textInput" rows="2" cols="80" name="SEODescription" readonly="readonly">${news.SEODescription }</textarea>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt style="text-align: right;">文章内容：</dt>
				<dd>
					<textarea class="editor" rows="15" cols="80" name="newsContent">${news.newsContent }</textarea>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</div>
 </body>
</html>
