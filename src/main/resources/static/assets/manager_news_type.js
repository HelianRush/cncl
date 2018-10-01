$(function() {

	// 验证编码唯一性
	// checkCode();

	// 验证名称唯一性
	// checkName();
	
});

// 验证编码唯一性
function checkCode() {
	var code = $("#newsTypeCode").val();

	alert(code);

	$("#newsTypeCode").blur(function() {

		if (null == code || "" == code) {
			$("#msg1").text("不可为空");
			return false;
		} else {
			$("#msg1").text("");
		}

		$.ajax({
			type : "POST",
			url : "/newsTypeController/checkCode",
			data : {
				"code" : code
			},
			dataType : "text",
			success : function(data) {
				if ("200" != data) {
					$("#msg1").text(data);
				} else {
					$("#msg1").text("");
				}
			}
		});
	});
};

// 验证名称唯一性
function checkName() {
	var name = $("#newsTypeName").val();

	$("#newsTypeName").blur(function() {

		if (null == name || "" == name) {
			$("#msg2").text("不可为空");
			return false;
		} else {
			$("#msg2").text("");
		}

		$.ajax({
			type : "POST",
			url : "/newsTypeController/checkName",
			data : {
				"name" : name
			},
			dataType : "text",
			success : function(data) {
				if ("200" != data) {
					$("#msg2").text(data);
				} else {
					$("#msg2").text("");
				}
			}
		});
	});
};

// 修改
function editNewsType() {
};

// 删除
function removeNewsType() {
};
