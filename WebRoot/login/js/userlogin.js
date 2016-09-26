function checkusername() {
		if ($("#loginName").val() =="") {
			$("#checkName").get(0).innerHTML = "用户名不能为空";
		     $('#btn').attr("disabled","disabled");
		} else {
			$("#checkName").get(0).innerHTML = "";
			//if($("#password").val()!= ""&$("#validate").val() != ""){
			       $('#btn').removeAttr("disabled");
			//}
		}
	}

	function checkpassword(){	
	if ($("#password").val() == ""){
			$("#checkPas").get(0).innerHTML = "密码不能为空";
			$('#btn').attr("disabled","disabled");
		} else {
			$("#checkPas").get(0).innerHTML = "";
			//if($("#username").val() != ""&$("#validate").val() != ""){
			       $('#btn').removeAttr("disabled");
			//}
		}
	}
	
	$(document).ready(function(){
	    alert("${ctx}");
		jQuery('#loginName').blur(checkusername);
	    jQuery('#password').blur(checkpassword);
		$("#btn").click(function(){
		 checkusername();
		 checkpassword();
			var params = {};
			params.loginName = $("#loginName").val();
			params.password = $("#password").val();
			$.ajax({    
                url : "login.do",
				type : "post",
			    data : params,
				cache : "false",
	            success : function(message) {
	            if(message=="loginerror")
	                      $("#checkPas").get(0).innerHTML = "密码错误！";
	            if(message=="logindelstatus")
	                      $("#checkPas").get(0).innerHTML = "请联系管理员！";
	            if(message=="success"){
	                   window.location.href="goIndex.do"; 
	                      }
		            	},
				error : function(message) {
				alert(message.message);
						alert("Connection error");
						}
			});										

		});});