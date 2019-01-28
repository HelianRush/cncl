$(function() {

	// 刷验证码 // 看不清
	$("#falsh").click(function() {
		getCaptcha();
	});

	// 验证 用户名不能为空
	$("#adminUserName").blur(function() {
		if (null == $("#adminUserName").val()) {
			$("#alert1").text("名称不能为空！");
		} else {
			$("#alert1").text("");
		}
		if ("" == $("#adminUserName").val()) {
			$("#alert1").text("名称不能为空！");
		} else {
			$("#alert1").text("");
		}
	});

	// 验证 密码不能为空
	$("#password").blur(function() {
		if (null == $("#password").val() || "" == $("#password").val()) {
			$("#alert2").text("密码不能为空！");
		} else {
			$("#alert2").text("");
		}
	});

	// 验证 验证码
	$("#validCode").blur(function() {
		if (null == $("#validCode").val() || "" == $("#validCode").val()) {
			$("#alert3 img").attr("src", "/images/e.png");
		} else {

			var validCode = $("#validCode").val();
			$.ajax({
				type : "GET",
				url : "/Login/getCtlCaptcha",
				data : {
					"validCode" : validCode
				},
				dataType : "text",
				traditional:true,
				async : false,
				cache : false,
				success : function(data) {
					if (validCode == data) {
						$("#alert3 img").attr("src", "/images/r.png");
					} else {
						$("#alert3 img").attr("src", "/images/e.png");
						$("#adminUserName").val("");
						$("#password").val("");
						$("#validCode").val("");
						alert("验证码错误！");
					}
				}
			}); // ajax end
		}
	}); // validCode end

	// 登录验证
	checkLogin();

}); // $(function end

// 获取验证码
function getCaptcha() {
	$("#captcha img").attr("src", "/Login/getCaptcha?date=" + new Date());
}

// 登录验证
function checkLogin() {
	// 登录验证
	$("#loginButton").click(function() {
		// 获取 登录名和密码
		var adminUserName = $("#adminUserName").val();
		var password = $("#password").val();

		if (null != adminUserName && null != password) {
			$.ajax({
				type : "GET",
				url : "/Login/checkLogin",
				data : {
					"adminUserName" : adminUserName,
					"password" : password
				},
				dataType : "text",
				async : false,
				cache : false,
				success : function(data) {
					if ("200" == data) {
						// 登录
						$("#loginForm").submit();
					}
					if ("L0004" == data) {
						$("#alert1").text("用户名错误");
					}
					if ("L0005" == data) {
						$("#alert2").text("密码错误");
					}
				}
			}); // ajax end
		}
	}); // $("#loginButton").click
}