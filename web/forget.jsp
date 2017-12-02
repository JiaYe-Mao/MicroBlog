<%--
  Created by IntelliJ IDEA.
  User: knight006
  Date: 12/2/2017
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码?</title>
</head>
<body>
<%
    if (request.getAttribute("error") != null){
%>
    <div style="color: rgb(255, 0, 0);">
        ${requestScope.error}
    </div>
<%
    }
%>
    <form action="password" method="post">
        用户名称: <input type="text" name="name" value="${param.name}"/><br>
        用户邮箱: <input type="text" name="email" value="${param.email}"/><br><br>
        <input type="submit" value="送出"/>
    </form>
</body>
</html>
