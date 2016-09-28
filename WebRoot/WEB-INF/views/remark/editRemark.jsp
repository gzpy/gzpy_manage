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
		<div class="pageContent" >
			<form method="post"  action="editRemark.do?remarkId=${remark.remarkId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
				<div class="pageFormContent nowrap" layoutH="97">
					<fieldset>
						<legend>编辑留言</legend>
						<dl  style="text-align: center;">
							<dt>姓名：</dt>
							<dd">
								<input name="name" class="required" type="text" value="${remark.name}" />
							</dd>
						</dl>
						<dl style="text-align: center;">
							<dt>邮箱：</dt>
							<dd>
								<input name="email" type="text" class="email" value="${remark.email}" />
							</dd>
						</dl>
						<dl style="text-align: center;">
							<dt>电话：</dt>
							<dd>
								<input name="tel" type="text" class="phone" maxlength='11' value="${remark.tel}"/>
							</dd>
						</dl>
						<dl style="text-align: center;">
							<dt>留言时间：</dt>
							<dd>
								<input name="remarktime" type="text" class="required date" value="${remarkTime}" />
							</dd>
						</dl>
						<dl style="margin-left:30px">
							<dt>公开状态：</dt>
							<dd>
								<input type=radio name="status" id="Ystatus" value="Y" >公开
                                <input type=radio name="status" id="Nstatus" value="N" >未公开
							</dd>
						</dl>
						<dl>
							<dt style="margin-left:30px">删除状态：</dt>
							<dd>
								<input type=radio name="delStatus" id="Ydelstatus" value="Y" >删除
                                <input type=radio name="delStatus" id="Ndelstatus" value="N" >未删除
							</dd>
						</dl>
					<fieldset>
						<legend>留言内容</legend>
						<dl class="nowrap" style="text-align: center;">
							<dt></dt>
							<dd>
								<textarea name="remarkContent" style="height:200px;"
									class="editor required" cols="80" rows="2">${remark.remarkContent}</textarea>
							</dd>
						</dl>
					</fieldset>
					<fieldset>
						<legend>回复留言</legend>
						<dl class="nowrap" style="text-align: center;">
							<dt></dt>
							<dd>
								<textarea name="replyContent" style="height:200px;"
									class="editor required" cols="80" rows="2">${remark.replyContent}</textarea>
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
	

$(function(){
if("${remark.status}"=="Y"){
   $("#Ystatus").attr("checked","checked");
}
else{
     $("#Nstatus").attr("checked","checked");
}

if("${remark.delStatus}"=="Y"){
   $("#Ydelstatus").attr("checked","checked");
}
else{
    $("#Ndelstatus").attr("checked","checked");
}
});

</script>

