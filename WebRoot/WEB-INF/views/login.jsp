<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Form</title>
<link href="${ctx}/login/css/userlogin.css" rel="stylesheet" type="text/css" media="screen"/>
<script src="${ctx}/dwz/js/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="${ctx}/login/js/userlogin.js" type="text/javascript"></script>

</head>
<body>
	<div class="main">
		<div class="login">
			<h1>管理系统</h1>
			<div class="inset">
				<!--start-main-->
				<form>
			         <div>
			         	<h2>管理登录</h2>
						<span><label>用户名</label></span>
						<span><input id="loginName" name="loginName" type="text" class="textbox" ></span>
					 </div>
					 <div id="checkName" style="color: red;margin-top: -20px;font-size: x-small;"></div>
					 <div>
						<span><label>密码</label></span>
					    <span><input id="password" name="password" type="password" class="password"></span>
					 </div>
					  <div id="checkPas" style="color: red;margin-top: -20px;font-size: x-small;"></div>
					<div class="sign">
                        <input id="btn" type="button" value="登录" class="submit" />
					</div>
				</form>
				</div>
			</div>
		<!--//end-main-->
		</div>

<div class="copy-right">
	<p>&copy; 2016 Ethos Login Form. All Rights Reserved</p>
</div>
</body>
</html>
