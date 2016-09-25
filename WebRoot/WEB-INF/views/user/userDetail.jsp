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
				<dt style="text-align: right;">用户姓名：</dt>
				<dd>
					<input name="userName" value="${user.userName}" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt style="text-align: right;">性别：</dt>
				<dd>
					<input name="sex" value="${user.sex }" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt style="text-align: right;">登录账号：</dt>
				<dd>
					<input name="loginName" value="${user.loginName}" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt style="text-align: right;">登录密码：</dt>
				<dd>
					<input name="password" value="${user.password }" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
					<dt style="text-align: right;">入职时间：</dt>
					<dd>
						<input type="text" name="employDate" readonly="true" value="${employDate }"/>
						
					</dd>
				</dl>
			<dl>
				<dt style="text-align: right;">办公电话：</dt>
				<dd>
					<input name="officePhone" value="${user.officePhone }" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt style="text-align: right;">家庭电话：</dt>
				<dd>
					<input name="homePhone" value="${user.homePhone }" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt style="text-align: right;">手机号码：</dt>
				<dd>
					<input name="mobilePhone" value="${user.mobilePhone}" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt style="text-align: right;">电子邮箱：</dt>
				<dd>
					<input name="email" value="${user.email }" type="text" size="20" maxlength="50" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt style="text-align: right;">删除状态：</dt>
				<dd>
					<input name="delStatus" value="${user.delStatus}" type="text" size="20" maxlength="50" readonly="readonly"/>
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