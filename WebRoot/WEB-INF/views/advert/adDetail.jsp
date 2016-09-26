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
				<dt style="text-align: right;">广告名称：</dt>
				<dd>
					<input name="SEOTitle" value="${ad.adName }" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt style="text-align: right;">广告排序：</dt>
				<dd>
					<input name="SEOKeywords" value="${ad.adOrder }" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt style="text-align: right;">广告宽度：</dt>
				<dd>
					<input name="SEOKeywords" value="${ad.adWidth }" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt style="text-align: right;">广告高度：</dt>
				<dd>
					<input name="SEOKeywords" value="${ad.adHeight }" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt style="text-align: right;">删除状态：</dt>
				<dd>
					<input name="delStatus" value="${ad.delStatus}" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt style="text-align: right;">广告描述：</dt>
				<dd>
					<textarea rows="15" cols="80" name="newsContent" readonly="readonly">${ad.adDescription }</textarea>
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