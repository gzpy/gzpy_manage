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
		<form method="post" enctype="multipart/form-data" action="${ctx}/product/updateProduct.do" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="58">
				<input name="productId" type="hidden" value="${product.productId }" />
				<dl>
					<dt style="text-align: right;">产品标题：</dt>
					<dd>
						<input class="required" name="productTitle" value="${product.productTitle }" type="text" size="26" maxlength="50"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">发布时间：</dt>
					<dd>
						<input type="text" name="productIssueDate" class="date required" readonly="true" value="${issueDate }"/>
						<a class="inputDateButton" href="javascript:;">选择</a>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">SEO标题：</dt>
					<dd>
						<input name="SEOTitle" type="text" size="26" maxlength="50" value="${product.SEOTitle }"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">SEO关键字：</dt>
					<dd>
						<input name="SEOKeywords" type="text" size="26" maxlength="50" value="${product.SEOTitle }"/>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt style="text-align: right;">SEO描述：</dt>
					<dd>
						<textarea name="SEODescription" rows="2" cols="30">${product.SEODescription }</textarea>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt style="text-align: right;">选择图片：</dt>
					<dd>
						<input type="file" name="file"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">删除状态：</dt>
					<dd>
						<c:choose>
							<c:when test="${product.delStatus eq 'Y' }">	
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
					<dt style="text-align: right;">产品简介：</dt>
					<dd>
						<textarea class="editor" name="introduction" rows="12" cols="80" tools="mfull">${product.introduction }</textarea>
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
