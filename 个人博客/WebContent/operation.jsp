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
<title>博客详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- 新 Bootstrap4 核心 CSS 文件 -->
<link rel="stylesheet" href="./CSS/style.css">
<script type="text/javascript">
function change_pic(blog_id){
	blog_id;
	var img1=document.getElementById("tubiao1");
	if(img1.getAttribute("src",2)=="./Images/dianzan1.png"){
		img1.setAttribute("src","./Images/dianzan2.png");
		function ajaxFunction(){
			var xmlHttp;
			try{
				xmlHttp=new XMLHttpRequest();
			}catch(e){
				try{
					xmlHtttp=new ActiveXObject("Msxml2.XMLHTTP");
				}catch(e){
					try{
						xmlHtttp=new ActiveXObject("Microsoft.XMLHTTP");
					}catch(e){
						
					}
				}
			}
			return xmlHttp;
		}
		var request=ajaxFunction();
		request.open("POST","${pageContext.request.contextPath}/LikeServlet",true);
		request.onreadystatechange=function(){
			if(request.readyState==4&&request.status==200){
				alert("谢谢点赞");
			}
		}
		request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		request.send("blog_id="+blog_id);
	}else{
		img1.setAttribute("src","./Images/dianzan1.png");
		function ajaxFunction(){
			var xmlHttp;
			try{
				xmlHttp=new XMLHttpRequest();
			}catch(e){
				try{
					xmlHtttp=new ActiveXObject("Msxml2.XMLHTTP");
				}catch(e){
					try{
						xmlHtttp=new ActiveXObject("Microsoft.XMLHTTP");
					}catch(e){
						
					}
				}
			}
			return xmlHttp;
		}
		var request=ajaxFunction();
		request.open("POST","${pageContext.request.contextPath}/NotLikeServlet",true);
		request.onreadystatechange=function(){
			if(request.readyState==4&&request.status==200){
				alert("已取消点赞");
			}
		}
		request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		request.send("blog_id="+blog_id);
	}
}
function del(blog_id){
	var name=document.getElementById("name").value;
	var author=document.getElementById("author").value;
	if(name!=author){
		alert("您不能删除他人文章！");
	}else{
		var dele=document.getElementById("tubiao3");
		function ajaxFunction(){
			var xmlHttp;
			try{
				xmlHttp=new XMLHttpRequest();
			}catch(e){
				try{
					xmlHtttp=new ActiveXObject("Msxml2.XMLHTTP");
				}catch(e){
					try{
						xmlHtttp=new ActiveXObject("Microsoft.XMLHTTP");
					}catch(e){
						
					}
				}
			}
			return xmlHttp;
		}
		var request=ajaxFunction();
		request.open("POST","${pageContext.request.contextPath}/DelBokeServlet",true);
		request.onreadystatechange=function(){
			if(request.readyState==4&&request.status==200){
				alert(request.responseText);
			}
		}
		request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		request.send("blog_id="+blog_id);
	}
}
</script>

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
<%
int blog_id=Integer.parseInt(request.getParameter("blog_id"));
ArrayList<Blog> list = dao.findOne(blog_id);
for(Blog item:list){
%>	
<div class="box">
<input id="name" type="hidden" value="<%=name%>">
<input id="bid" type="hidden" value="<%=item.getBlog_id()%>">
<input id="author" type="hidden" value="<%=item.getAuthor()%>">
<p class="title"></p>
<p class="title"><%=item.getTitle()%></p>
<p class="author" id="author"><%=item.getAuthor()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=item.getType()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=item.getTime()%></p>
<p class="content"><%=item.getContent()%></p>

<img src="./Images/dianzan1.png" id="tubiao1" class="like" onclick="change_pic(<%=blog_id%>)" title="<%=item.getLike_num()%>个喜欢">
<a href="xiugai.jsp?blog_id=<%=blog_id%>"><img src="./Images/xiugai.png" id="tubiao2" class="like"></a>
<img src="./Images/shanchu.png" id="tubiao3" class="like" onclick="if(confirm('确定要删除吗？')){del(<%=blog_id%>)}" title="删除博客">
</div>
<%
}
 %>
 <div class="operation-comment">
 <form action="${pageContext.request.contextPath}/CommentServlet">
<input type="hidden" name="blog_id" value="<%=blog_id%>">
<input type="hidden" name="com_name" value="<%=name%>">
<input type="text" name="comment" class="operation-addcomment"><input type="submit" value="提交评论" class="operation-commentbutton">
</form>
<div class="com">
<%
ArrayList<Blog> list2 = dao.comment(blog_id);
for(Blog com:list2){	
%>
<p class="com-name"><%=com.getCom_name()%>:</p>
<p class="comment"><%=com.getComment()%></p>
<%
}
 %>
 </div>
 </div>
 </div>
<%@ include file="bottom.jsp" %>

</body>
</html>
