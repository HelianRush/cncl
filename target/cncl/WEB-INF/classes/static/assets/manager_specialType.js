$(function() {
	// 注册验证 submitButton
	submitSpecialType();
});

function submitSpecialType() {
	$("#submitButton").click(function() {
		$("#editSpeciaType").submit();
	});
}

// 关闭
function closeWindow() {
	$("#editSpeciaType")[0].reset();
}

// 名称为空检查
function checkName() {
	$("#specialTypeName").blur(function() {
		var name = $("#specialTypeName").val();
		if (null == name || "" == name) {
			$("#msg2").text("不可为空");
			// $("#submit").attr("disabled", "disabled");
		} else {
			$("#msg2").text("");
			// $("#submit").removeAttr("disabled");
		}
	});
}

// 修改
function doEdit(object) {
	var id = $(object).attr("id");
	$.ajax({
		url : "/SpecialCtl/showEditSpeciaType",
		type : "POST",
		async : false,
		cache : false,
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {
			$("#specialTypeId").val(data.specialTypeId);
			$("#specialTypeName").val(data.specialTypeName);
		}
	});
}

// 删除
function doRemove(object) {
	var id = $(object).attr("id");
	if (confirm("是确认删除" + id + "资源？")) {
		$.post("/SpecialCtl/removeSpeciaType", {
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
