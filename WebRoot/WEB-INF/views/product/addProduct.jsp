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
		<form method="post" action="${ctx}/product/addProduct.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="58">
				<p>
					<label style="text-align: right;">产品标题：</label>
					<input name="productTitle" type="text" size="28" value="" maxlength="20" class="required"/>
				</p>
				<p>
					<label style="text-align: right;">发布时间：</label>
					<input type="text" name="productIssueDate" class="date required" readonly="true"/>
					<a class="inputDateButton" href="javascript:;">选择</a>
				<p>
					<label style="text-align: right;">SEO标题：</label>
					<input name="SEOTitle" type="text" size="28" maxlength="50" value=""/>
				</p>
				<p>
					<label style="text-align: right;">SEO关键字：</label>
					<input name="SEOKeywords" type="text" size="28" maxlength="50" value=""/>
				</p>
				<p>
					<label style="text-align: right;">SEO描述：</label>
					<textarea name="SEODescribtion" rows="2" cols="30"></textarea>
				</p>
				<p>
					<label style="text-align: right;">产品简介：</label>
					<textarea class="editor" name="introduction" rows="15" cols="80" tools="mfull"></textarea>
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
