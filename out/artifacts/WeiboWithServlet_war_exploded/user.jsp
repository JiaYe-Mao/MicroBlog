<%@ page import="model.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Locale" %><%--
  Created by IntelliJ IDEA.
  User: knight006
  Date: 11/30/2017
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>${requestScope.username} Gossip</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/member.css">
</head>
<body>
<%
    List<Message> messages = (List<Message>)request.getAttribute("messages");
    if (messages == null){
%>
    <h1 style="color:red">${requestScope.username}用户不存在</h1>
<%
    } else {
%>
<div class='leftPanel'>
    <img src='<%=request.getContextPath() %>/images/caterpillar.jpg' alt='Gossip 微网志' />
    <br><br>${ requestScope.username }的微网志
</div>
<table class="table" border='0' cellpadding='2' cellspacing='2'>
    <thead>
    <tr>
        <th><hr></th>
    </tr>
    </thead>
    <tbody>
    <%
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.CHINA);
        for (Message message : messages){
    %>

    <tr>
        <td style='vertical-align: top;'>
            <%= message.getUsername()%><br>
            <%= message.getTxt()%><br>
            <%= dateFormat.format(message.getDate())%>
                <hr>
        </td>
    </tr>

<%
        }
    }
%>
    </tbody>
</table>
</body>
</html>
