// 获取验证码
function getCaptcha() {
	$("#captcha img").attr("src", "/Login/getCaptcha?date=" + new Date());
}

// 看不清
$(function() {

	// 刷验证码
	$("#falsh").click(function() {
		getCaptcha();
	});

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

	$("#password").blur(function() {
		if (null == $("#password").val() || "" == $("#password").val()) {
			$("#alert2").text("密码不能为空！");
		} else {
			$("#alert2").text("");
		}
	});

	$("#validCode").blur(function() {
		if (null == $("#validCode").val() || "" == $("#validCode").val()) {
			$("#alert3 img").attr("src", "/images/e.png");
		} else {

			var validCode = $("#validCode").val();
			$.ajax({
				type : "POST",
				url : "/Login/getCtlCaptcha",
				data : {
					"validCode" : validCode
				},
				dataType : "text",
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
			});
		}
	}); // validCode end

});