<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/include.inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head></head>

<body>
	<div class="pageContent">
		<form method="post" action="${ctx}/advert/addAd.do"
			class="pageForm required-validate" enctype="multipart/form-data"
			onsubmit="return iframeCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="58">
				<dl>
					<dt style="text-align: right;">广告名称：</dt>
					<dd>
						<input class="required" name="adName" type="text" size="26"
							maxlength="50" />
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">广告位排序：</dt>
					<dd>
						<input class="number required" name="adOrder" type="text" size="26" maxlength="10"
							value="" />
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">广告位宽度：</dt>
					<dd>
						<input class="number required" name="adWidth" type="text" size="26"
							maxlength="10" value="" />
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">广告位高度：</dt>
					<dd>
						<input name="adHeight" type="text" class="number required" size="26"
							maxlength="10" value="" />
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">广告图片：</dt>
					<dd>
						<input type="file" name="ad_pic" />
					</dd>
				</dl>
				<dl class="nowrap">
					<dt style="text-align: right;">广告描述：</dt>
					<dd>
						<textarea class="editor" name="adDescription" rows="12" cols="80"
							tools="mfull" maxlength="300"></textarea>
					</dd>
				</dl>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">保存</button>
							</div>
						</div></li>
					<li>
						<div class="button">
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
