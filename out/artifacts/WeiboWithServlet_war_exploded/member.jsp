<%--
  Created by IntelliJ IDEA.
  User: knight006
  Date: 11/29/2017
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@page import="model.UserService, java.util.*, java.text.*" %>
<%
    String username = (String) request.getSession().getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html;charset=UTF-8" http-equiv="content-type"/>
    <title>Gossip 微博</title>
    <link rel="stylesheet" type="text/css" href="css/member.css">
</head>
<body>
    <div class="leftPanel">
        <img src="images/caterpillar.jpg" alt="Gossip 微博"/>
        <br><br>
        <a href="logout">注销<%= username%>></a>
    </div>

    <form method="post" action="/sendmessage">
        <h3>分享新鲜事...</h3>
<%
    String blabla = request.getParameter("blabla");
    if (blabla == null){
        blabla = "";
    } else {
%>
        <h3>信息要在140字以内</h3>
<%
    }
%>
        <textarea cols="60" rows="4" name="blabla">
            <%= blabla%>
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
    UserService userService = (UserService)getServletConfig().getServletContext().getAttribute("userService");
    Map<Date, String> messages = userService.readMessage(username);
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.CHINA);
    for (Date date : messages.keySet()){
%>
        <tr>
            <td>
                <%= username%><br>
                <%= messages.get(date)%><br>
                <%= dateFormat.format(date)%>
                <a href="delete?date=<%= date.getTime()%>">删除</a>
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
