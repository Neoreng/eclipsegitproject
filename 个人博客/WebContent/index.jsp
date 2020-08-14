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
<title>首页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- 新 Bootstrap4 核心 CSS 文件 -->
<link rel="stylesheet" href="./CSS/style.css">
<script type="text/javascript">
    setInterval(gettime,1000)
    function gettime(){
    	var now=new Date();
    	var now_year=now.getFullYear();
    	var now_month=now.getMonth()+1;
    	var now_day=now.getDate();
    	var now_hours=now.getHours();
    	var now_minutes=now.getMinutes();
    	var now_seconds=now.getSeconds();
        var time=now_year+"年"+now_month+"月"+now_day+"日"+now_hours+":"
        +now_minutes+":"+now_seconds;	
        document.getElementById("time").value=time;
    }
    </script>
</head>
<body>
<div class="parent">
<div class="picture">
</div>
<%@ include file="Navbar.jsp" %>
<div class="time-input">
现在时刻：<input type="text" name="time" id="time">
</div>
<%
ArrayList<Blog> list = dao.findAll();
for(Blog item:list){
 %>
<div class="box">
<p class="title"><%=item.getTitle()%></p>
<p class="author"><%=item.getAuthor()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=item.getType()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=item.getTime()%></p>
<p class="content"><%=item.getContent()%></p>
<p class="like"><img src="./Images/dianzan1.png" id="tubiao1"><%=item.getLike_num()%></p>
</div>
<%
}
 %>
</div>
<%@ include file="bottom.jsp" %>

</body>
</html>
