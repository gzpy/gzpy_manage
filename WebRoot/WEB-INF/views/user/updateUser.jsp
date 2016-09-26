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
		<form method="post" action="${ctx}/user/updateUser.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="58">
				<input name="userId" type="hidden" value="${user.userId }" />
				<dl>
					<dt style="text-align: right;">用户姓名：</dt>
					<dd>
						<input class="required" name="userName" value="${user.userName }" type="text" size="26" maxlength="50"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">性别：</dt>
					<dd>
						<select class="combox" name="sex">
							<option value="男">男</option>
							<option value="女">女</option>
							
						</select>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">登录账号：</dt>
					<dd>
						<input class="required" name="loginName" value="${user.loginName }" type="text" size="26" maxlength="50"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">登录密码：</dt>
					<dd>
						<input class="required" name="password" value="${user.password }" type="text" size="26" maxlength="50"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">入职时间：</dt>
					<dd>
						<input type="text" name="userEmployDate" class="date" readonly="true" value="${employDate }"/>
						<a class="inputDateButton" href="javascript:;">选择</a>
					</dd>
				</dl>
				
				<dl>
					<dt style="text-align: right;">公司电话：</dt>
					<dd>
						<input class="phone" name="officePhone" value="${user.officePhone }" type="text" size="26" maxlength="50"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">家庭电话：</dt>
					<dd>
						<input class="phone" name="homePhone" value="${user.homePhone }" type="text" size="26" maxlength="50"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">手机号码：</dt>
					<dd>
						<input class="phone required" name="mobilePhone" value="${user.mobilePhone }" type="text" size="26" maxlength="50"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">电子邮箱：</dt>
					<dd>
						<input class="email" name="email" value="${user.email }" type="text" size="26" maxlength="50"/>
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">删除状态：</dt>
					<dd>
						<!-- <input name="delStatus" value="${user.delStatus }" type="text" size="26" maxlength="50"/> -->
						<select class="combox" name="delStatus" value="${user.delStatus }">
						<option value="N">N</option>
						<option value="Y">Y</option>
						
						
						</select>
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
