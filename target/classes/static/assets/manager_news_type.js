$(function() {

	// 注册验证 submitButton
	submitNews();

	// 为空验证
	checkCode();
	checkName();
});

// 注册验证 submitButton // 检查唯一性
function submitNews() {

	$("#submitButton").click(function() {

		var code = $("#newsTypeCode").val();
		var name = $("#newsTypeName").val();
		var flagCode = 0;
		var flagName = 0;

		if (null == code || "" == code || null == name || "" == name) {
			alert("不允许为空");
		} else {

			// 检查编码
			$.ajax({
				type : "POST",
				url : "/NewsTypeCtl/checkCode",
				async : false,
				data : {
					"code" : code
				},
				dataType : "text",
				success : function(data) {
					if ("200" != data) {
						$("#msg1").text(data);
					} else {
						flagCode = 1;
						$("#msg1").text("");
					}
				}
			});

			// 检查名称
			$.ajax({
				type : "POST",
				url : "/NewsTypeCtl/checkName",
				data : {
					"name" : name
				},
				async : false,
				dataType : "text",
				success : function(data) {
					if ("200" != data) {
						$("#msg2").text(data);
					} else {
						flagName = 1;
						$("#msg2").text("");
					}
				}
			});

		} // if end

		if ((flagCode + flagName) == 2) {
			$("#editNewsType").submit();
		}

	}); // $(#submitButton) end
} // submitNewsAdd() end

// 编码为空检查
function checkCode() {
	$("#newsTypeCode").blur(function() {
		var code = $("#newsTypeCode").val();
		if (null == code || "" == code) {
			$("#msg1").text("不可为空");
			// $("#submit").attr("disabled", "disabled");
		} else {
			$("#msg1").text("");
			// $("#submit").removeAttr("disabled");
		}
	});
}

// 名称为空检查
function checkName() {
	$("#newsTypeName").blur(function() {
		var name = $("#newsTypeName").val();
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
