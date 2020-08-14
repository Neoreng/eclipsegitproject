<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>发表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
<div class="parent">
<%@ include file="Navbar.jsp" %>
<h1>发表博客</h1>
<%
String error=request.getParameter("error");
if("0".equals(error)){
	%>
	<script>alert("发表失败，请重试");</script>
	<%
}
%>
<%
String name=(String)session.getAttribute("username");
if(name==null){
	%>
	<script>alert("您还没有登录，请先登录！");
	window.location.href="login.jsp";</script>
	<%
}
%>
<div class="publish-form">
<form action="${pageContext.request.contextPath}/PublishBlogServlet" method="post">
<table>
<tr>
<td >标题  <input type="text" name="title" class="publish-title"></td>
</tr>
<tr>
<td >作者  <input type="text" name="author" value="<%=name%>" class="publish-title"></td>
</tr>
<tr>
<td>类型 
   <select name="type" class="publish-type">
   <option value="热门">热门</option>
   <option value="热门">头条</option>
   <option value="美食">美食</option>
   <option value="游戏">游戏</option>
   <option value="体育">体育</option>
   <option value="社会">社会</option>
   <option value="时尚">时尚</option>
   </select>
</td>
</tr>
<tr>
<td>内容  <textarea name="content" rows="15" cols="60" class="publish-content">
</textarea></td>
</tr>
<tr>
<td >
<input type="submit" value="发表" class="publish-button">
<input type="reset" value="清除" class="publish-button">
</td>
</tr>
</table>
</form>
</div>
</div>
<%@ include file="bottom.jsp" %>

</body>
</html>
