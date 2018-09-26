$(function() {
	$.ajax({
		type : "POST",
		url : "/adminLogion",
		data : {
			"adminUserName" : adminUserName,
			"password" : password,
			"validCode" : validCode
		},
		dataType : "json",
		async : false,
		cache : false,
		success : function(data) {
			if (data.status == "200") {
				window.location.href = "/manager_index.html";
			} else {
				alert(data);
				getCaptcha();
				return false;
			}
		}
	});
});