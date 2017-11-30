<%--
  Created by IntelliJ IDEA.
  User: knight006
  Date: 11/29/2017
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@page import="model.UserService, java.util.*, java.text.*" %>
<%@ page import="model.Message" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html;charset=UTF-8" http-equiv="content-type"/>
    <title>Gossip 微博</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/member.css">
</head>
<body>
    <div class="leftPanel">
        <img src="<%=request.getContextPath() %>/images/caterpillar.jpg" alt="Gossip 微博"/>
        <br><br><br><br><br>
        <a href="logout">注销${sessionScope.username}</a>
    </div>

    <form method="post" action="sendmessage">
        <h3>分享新鲜事...</h3>
<%
    String blabla = request.getParameter("blabla");
    if (blabla != null){
%>
        <h3>信息要在140字以内</h3>
<%
    }
%>
        <textarea cols="60" rows="6" name="blabla">
            ${requestScope.blabla}
        </textarea><br>
        <button type="submit">发送</button>
    </form>

    <table class="table" border="0" cellpadding="2" cellspacing="2">
        <thead>
            <tr>
                <th><hr></th>
            </tr>
        </thead>
        <tbody>
<%
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.CHINA);
    List<Message> messages = (List<Message>)request.getAttribute("messages");
    for (Message message : messages){
%>
        <tr>
            <td>
                <%= message.getUsername()%><br>
                <%= message.getTxt()%><br>
                <%= dateFormat.format(message.getDate())%>
                <a href="delete?date=<%= message.getDate().getTime()%>">删除</a>
                <hr>
            </td>
        </tr>
<%
    }
%>
        </tbody>
    </table>
    <hr>
</body>
</html>
