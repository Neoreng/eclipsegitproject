<%@ page language="java" import="java.util.*,entity.Blog" pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="Dao.BlogDao"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社区</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
<div class="parent">
<%@ include file="Navbar.jsp" %>
<h1>社区</h1>
<form action="${pageContext.request.contextPath}/CommunityServlet" class="type">
<table>
//123
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
<input type="submit" value="查看"></td>
</tr>
</table>
</form>
<%
if(request.getAttribute("blog")==null){
	%>
	<script>alert("请选择类型");</script>
	<%
}else{
	ArrayList<Blog> list = (ArrayList<Blog>) request.getAttribute("blog");
	for(Blog item:list){
	 %>
	<div class="box">
	<input id="bid" type="hidden" value="<%=item.getBlog_id()%>">
	<p class="title"></p>
	<p class="title"><%=item.getTitle()%></p>
	<p class="author"><%=item.getAuthor()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<%=item.getType()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<%=item.getTime()%></p>
	<p class="content"><%=item.getContent()%></p>
	<a href="operation.jsp?blog_id=<%=item.getBlog_id()%>"><img src="./Images/caozuo.png" title="点击可喜欢、评论、删除、修改" id="tubiao2" class="like"></a>
	</div>
	<%
	}
}
%>
</div>
<%@ include file="bottom.jsp" %>

</body>
</html>
