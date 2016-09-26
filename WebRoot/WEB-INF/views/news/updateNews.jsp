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
		<form method="post" action="${ctx}/news/updateNews.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="58">
				<input type="hidden" name="newsId" value="${news.newsId }">
				<dl>
					<dt style="text-align: right;">新闻标题：</dt>
					<dd>
						<input class="required" name="newsTitle" value="${news.newsTitle }" type="text" size="20" maxlength="50"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">新闻类型：</dt>
					<dd>
						<select name="typeId" class="required combox">
							<option value="">---请选择---</option>
							<c:forEach items="${list_newsType}" var="newsType">
								<c:choose>
									<c:when test="${newsType.typeId eq news.typeId }">
										<option value="${newsType.typeId }" selected>${newsType.typeName }</option>
									</c:when>
									<c:otherwise>
										<option value="${newsType.typeId }">${newsType.typeName }</option>
									</c:otherwise>
								</c:choose>		
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">SEO标题：</dt>
					<dd>
						<input name="SEOTitle" value="${news.SEOTitle }" type="text" size="20" maxlength="50"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">SEO关键字：</dt>
					<dd>
						<input name="SEOKeywords" value="${news.SEOKeywords }" type="text" size="20" maxlength="50"/>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt style="text-align: right;">SEO描述：</dt>
					<dd>
						<textarea class="textInput" rows="2" cols="80" name="SEODescription">${news.SEODescription }</textarea>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">删除状态：</dt>
					<dd>
						<c:choose>
							<c:when test="${news.delStatus eq 'Y' }">	
								<input name="delStatus" type="radio" value="Y" checked="checked"/>Y
								<input name="delStatus" type="radio" value="N">N
							</c:when>
							<c:otherwise>
								<input name="delStatus" type="radio" value="Y"/>Y
								<input name="delStatus" type="radio" value="N" checked="checked">N
							</c:otherwise>
						</c:choose>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt style="text-align: right;">文章内容：</dt>
					<dd>
						<textarea class="editor" rows="12" cols="80" name="newsContent">${news.newsContent }</textarea>
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
