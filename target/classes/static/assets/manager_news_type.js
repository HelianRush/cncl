$(function() {

	// 验证编码唯一性
	checkCode();

	// 验证名称唯一性
	checkName();

});

// 验证编码唯一性
function checkCode() {

	$("#newsTypeCode").blur(function() {

		var code = $("#newsTypeCode").val();

		if (null == code || "" == code) {
			$("#msg1").text("不可为空");
			$("#submit").attr("disabled", "disabled");
		} else {
			$("#msg1").text("");
			$("#submit").removeAttr("disabled");
		}

		$.ajax({
			type : "POST",
			url : "/NewsTypeCtl/checkCode",
			data : {
				"code" : code
			},
			dataType : "text",
			success : function(data) {
				if ("200" != data) {
					$("#msg1").text(data);
					$("#submit").attr("disabled", "disabled");
				} else {
					$("#msg1").text("");
					$("#submit").removeAttr("disabled");
				}
			}
		});
	});

}

// 验证名称唯一性
function checkName() {

	$("#newsTypeName").blur(function() {

		var name = $("#newsTypeName").val();

		if (null == name || "" == name) {
			$("#msg2").text("不可为空");
			$("#submit").attr("disabled", "disabled");
		} else {
			$("#msg2").text("");
			$("#submit").removeAttr("disabled");
		}

		$.ajax({
			type : "POST",
			url : "/NewsTypeCtl/checkName",
			data : {
				"name" : name
			},
			dataType : "text",
			success : function(data) {
				if ("200" != data) {
					$("#msg2").text(data);
					$("#submit").attr("disabled", "disabled");
				} else {
					$("#msg2").text("");
					$("#submit").removeAttr("disabled");
				}
			}
		});
	});
}

// 修改
function doEdit(object) {
	var id = $(object).attr("id");
	$.ajax({
		url : "/NewsTypeCtl/showEditNewsType",
		type : "POST",
		async : false,
		cache : false,
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {
			$("#newsTypeId").val(data.newsTypeId);
			$("#newsTypeCode").val(data.newsTypeCode);
			$("#newsTypeName").val(data.newsTypeName);
		}
	});
}

// 删除
function doRemove(object) {
	var id = $(object).attr("id");
	if (confirm("是确认删除" + id + "资源？")) {
		$.post("/NewsTypeCtl/removeNewsType", {
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
