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
		<form method="post" enctype="multipart/form-data" action="${ctx}/advert/updateAd.do" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
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
						<input name="adOrder" type="text" size="26" maxlength="10" value="${ad.adOrder }"/>
					</dd>
				</dl>
		
				<dl>
					<dt style="text-align: right;">广告位宽度：</dt>
					<dd>
						<input class="number" name="adWidth" type="text" size="26" maxlength="10" value="${ad.adWidth }"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">广告位高度：</dt>
					<dd>
						<input class="number" name="adHeight" type="text" size="26" maxlength="10" value="${ad.adHeight }"/>
					</dd>
				</dl>
			
				<dl>
					<dt style="text-align: right;">删除状态：</dt>
					<dd>
						<c:choose>
							<c:when test="${ad.delStatus eq 'Y' }">	
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
				<dl>
					<dt style="text-align: right;">广告链接：</dt>
					<dd>
						<input name="adLink" type="text" size="26" maxlength="50" value="${ad.adLink }"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">广告图片：</dt>
					<dd>
						<c:if test="${ad.imagePath!=null }">
							<img src="${ctx }${ad.imagePath }" width="${ad.adWidth }" height="${ad.adHeight }">
						</c:if>
						<input type="file" name="ad_pic">
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
