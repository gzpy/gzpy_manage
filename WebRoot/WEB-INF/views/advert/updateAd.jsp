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
		<form method="post" action="${ctx}/advert/updateAd.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="58">
				<input name="id" type="hidden" value="${ad.id }" />
				<dl>
					<dt style="text-align: right;">广告名称：</dt>
					<dd>
						<input class="required" name="adName" value="${ad.adName }" type="text" size="26" maxlength="50"/>
					</dd>
				</dl>
				
				<dl>
					<dt style="text-align: right;">广告位排序：</dt>
					<dd>
						<input name="adOrder" type="text" size="26" maxlength="50" value="${ad.adOrder }"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">广告位宽度：</dt>
					<dd>
						<input class="number" name="adWidth" type="text" size="26" maxlength="50" value="${ad.adWidth }"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">广告位高度：</dt>
					<dd>
						<input class="number" name="adHeight" type="text" size="26" maxlength="50" value="${ad.adHeight }"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">删除状态：</dt>
					<dd>
						<!-- <input name="delStatus" value="${user.delStatus }" type="text" size="26" maxlength="50"/> -->
						<select class="combox" name="delStatus" value="${ad.delStatus }">
						<option value="N">N</option>
						<option value="Y">Y</option>
						</select>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt style="text-align: right;">广告描述：</dt>
					<dd>
						<textarea class="editor" name="adDescription" rows="12" cols="80" tools="mfull">${ad.adDescription }</textarea>
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
