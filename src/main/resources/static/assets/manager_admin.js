$(function() {
	// 为空验证
	checkAdminUserName();
	checkPassword();
	// 编辑管理员 登录名验证
	checkName();
	// 提交
	submitAdmin();
});

// 查询ID
function checkID() {
	var adminUserId = $("#adminUserId").val();
	if (null != adminUserId || "" != adminUserId) {
		$("#adminUserName").attr("disabled", "disabled");
	} else {
		$("#adminUserName").removeAttr("disabled");
	}
}

// 关闭
function closeWindow() {
	$("#editForm")[0].reset();
	$("#adminUserName").removeAttr("disabled");
}

// 为空验证
function checkAdminUserName() {
	$("#adminUserName").blur(function() {
		var adminUserName = $("#adminUserName").val();
		if (null == adminUserName || "" == adminUserName) {
			$("#alertAdmin").text("不可为空");
		} else {
			$("#alertAdmin").text("");
		}
	});
}
function checkPassword() {
	$("#password").blur(function() {
		var password = $("#password").val();
		if (null == password || "" == password) {
			$("#alertPass").text("不可为空");
		} else {
			$("#alertPass").text("");
		}
	});
}

// 提交
function submitAdmin() {
	$("#submitButton").click(
			function() {
				var adminUserId = $("#adminUserId").val();
				var adminUserName = $("#adminUserName").val();
				var password = $("#password").val();

				if (null == adminUserName || "" == adminUserName
						|| null == password || "" == password) {
					alert("账号密码不允许为空");
				} else {
					if (null != adminUserId || "" != adminUserId) {
						$("#editForm").submit();
					} else {
						// 检查用户名唯一性
						$.ajax({
							type : "POST",
							url : "/AdminCtl/checkAdminUserName",
							async : false,
							data : {
								"adminUserName" : adminUserName
							},
							dataType : "text",
							success : function(data) {
								if ("A0001" == data) {
									$("#alertAdmin").text("已存在");
								} else {
									$("#alertAdmin").text("");
									$("#editForm").submit();
								}
							}
						}); // ajax end
					}
				} // if end
			}); // $("#submitButton") end
}

// 新增管理员 登录名验证
function checkName() {
	$("#adminUserName").blur(function() {
		var adminUserName = $("#adminUserName").val();

		if (null == adminUserName || "" == adminUserName) {
			$("#alertAdmin").text("不可空");
		} else {
			$.ajax({
				type : "POST",
				url : "/AdminCtl/checkAdminUserName",
				data : {
					"adminUserName" : adminUserName
				},// data end
				dataType : "text",
				success : function(data) {
					if ("A0001" == data) {
						$("#alertAdmin").text("已存在");
					} else {
						$("#alertAdmin").text("");
					}
				}// success end
			});
		}
	});
}

// 编辑 弹窗
function doEdit(object) {

	checkID();

	var id = $(object).attr("id");
	$.ajax({
		url : "/AdminCtl/showEdit",
		type : "POST",
		async : false,
		cache : false,
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {
			$("#adminUserId").val(data.adminUserId);
			$("#adminUserName").val(data.adminUserName);
			// $("#adminUserName").attr("readonly","readonly")
			$("#password").val(data.password);
			$("#name").val(data.name);
			$("#nickName").val(data.nickName);
			$("#mobilePhone").val(data.mobilePhone);
			$("#telephone").val(data.telephone);
			$("#email").val(data.email);
		}
	});
}

// 删除
function doRemove(object) {
	var id = $(object).attr("id");
	if (confirm("是确认删除" + id + "用户？")) {
		$.post("/AdminCtl/deleteAdminUser", {
			"id" : id
		}, function(data) {
			if ("200" == data) {
				window.location.reload();
			} else {
				alert("删除失败！");
			}
		});
	}
}