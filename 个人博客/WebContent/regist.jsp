<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%
	//工程名称
	final String path=request.getContextPath();
	//网站地址
	final String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
<div class="parent">
<%@ include file="Navbar.jsp" %>
<h1>注册</h1>
<%
String error=request.getParameter("error");
if("1".equals(error)){
	out.println("注册失败！请重新输入");
}
%>
<div class="regist-form">
<form method="post" action="${pageContext.request.contextPath}/RegistServlet">
<table>
<tr>
<td>姓名：<input type="text" name="name" class="regist-td"></td>
</tr>
<tr>
<td>手机号码：<input type="text" name="tel" class="regist-td2"></td>
</tr>
<tr>
<td>性别：<input type="radio" name="sex" value="男" class="regist-td3">男
<input type="radio" name="sex" value="女" class="regist-td3">女</td>
</tr>
<tr>
<td>密码：<input type="password" name="password" class="regist-td"></td>
</tr>
<tr>
<td><input type="submit" value="注册" class="regist-button"></td>
</tr>
</table>
</form>
</div>
</div>
<%@ include file="bottom.jsp" %>

</body>
</html>
