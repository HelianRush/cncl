var thtbody0 = "<table class='table table-responsive'>"
var theach0 = "<tr th:each='data:${" + list + "}'>";
var thtd1 = "<td th:text='${" + data.adminUserId + "}'></td>"
var thtd2 = "<td th:text='${" + data.adminUserName + "}'></td>"
var thtd3 = "<td th:text='${" + data.name + "}'></td>"
var thtd4 = "<td th:text='${" + data.nickName + "}'></td>"
var thtd5 = "<td th:text='${" + data.mobilePhone + "}'></td>"
var thtd6 = "<td th:text='${" + data.telephone + "}'></td>"
var thtd7 = "<td th:text='${" + data.email + "}'></td>"
var thtd8 = "<td th:text='${" + data.createTime + "}'></td>"
var thtd9 = "<td th:text='${" + data.lastLoginTime + "}'></td>"
var thtd0 = "<td th:text='${" + data.lastLoginIp + "}'></td>"
var thtda = "<td><a href=''>详情</a> | <a href=''>修改</a> | <a href=''>删除</a></td>"
var theach1 = "</tr>";
var thtbody1 = "</table>"

$("tbody").after(thtbody0, theach0, thtd1, thtd2, thtd3, thtd4, thtd5, thtd6,
		thtd7, thtd8, thtd9, thtd0, thtda, theach1, thtbody1);