<%@ page language="java" import="java.util.*,entity.Blog" pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="Dao.BlogDao"></jsp:useBean>
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
<title>个人主页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="./CSS/style.css">

</head>
<body>
<div class="parent">
<%@ include file="Navbar.jsp" %>
<%
String name=(String)session.getAttribute("username");
if(name==null){
	%>
	<script>alert("您还没有登录，请先登录！");
	window.location.href="login.jsp";</script>
	<%
}
%>
<h1 class="h1"><%=name %>的个人主页</h1>
<%
ArrayList<Blog> list = dao.own(name);
for(Blog item:list){
 %>
<div class="box">
<p><input id="bid" type="hidden" value="<%=item.getBlog_id()%>"></p>
<p class="title"></p>
<p class="title"><%=item.getTitle()%></p>
<p class="author"><%=item.getAuthor()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=item.getType()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=item.getTime()%></p>
<p class="content"><%=item.getContent()%></p>

<a href="operation.jsp?blog_id=<%=item.getBlog_id()%>"><img src="./Images/caozuo.png" title="点击可喜欢、评论、删除" id="tubiao2" class="like"></a>
</div>
<%
int blog_id=item.getBlog_id();
ArrayList<Blog> list2 = dao.comment(blog_id);
for(Blog com:list2){	
%>
<div id="comment" style="display:none;">
<form action="${pageContext.request.contextPath}/CommentServlet">
<input type="hidden" name="blog_id" value="<%=com.getBlog_id()%>">
<input type="text" name="comment"><input type="submit" value="提交评论">
</form>
<p class="comment"><%=com.getComment()%></p>
</div>
<%
}
 %>
<%
}
 %>
</div>
<%@ include file="bottom.jsp" %>

</body>
</html>
