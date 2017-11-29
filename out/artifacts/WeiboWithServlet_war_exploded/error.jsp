<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: knight006
  Date: 11/29/2017
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>新增会员失败</title>
</head>
<body>
    <h1>新增会员失败</h1>
    <ul style="color: rgb(255,0,0);">

<% List<String> errors = (List<String>) request.getAttribute("errors");
    for (String error : errors){
%>
        <li><%= error%></li>
<%
    }
%>

    </ul>
    <a href='register.jsp'>返回注册页面</a>
</body>
</html>
