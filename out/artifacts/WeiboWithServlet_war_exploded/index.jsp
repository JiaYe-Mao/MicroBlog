<%--
  Created by IntelliJ IDEA.
  User: knight006
  Date: 11/26/2017
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>weibo</title>
  </head>
  <body>
  <div id="loginPanel"  >
    <div align="center">
      <img src='images/caterpillar.jpg' alt='Gossip 微网志'/>
      <a href='register.jsp'>还不是会员？</a>
    </div>

    <div style="color:rgb(255,0,0);" align="center">
      ${requestScope.error}
    </div>

    <div style="margin-left: 40%;float: left">
      <form method='post' action='login'  >
      <table bgcolor='#cccccc'>
        <tr>
          <td colspan='2'>会员登录</td>
        <tr>
          <td>名称：</td>
          <td><input type='text' name='username' value="${param.username}"></td>
        </tr>
        <tr>
          <td>密码：</td>
          <td><input type='password' name='password'></td>
        </tr>
        <tr>
          <td colspan='2' align='center'><input type='submit' value='登入'></td>
        </tr>
        <tr>
          <td colspan='2'><a href='forgot.html'>忘记密码？</a>
          </td>
        </tr>
      </table>
    </form>
    </div>

    <div style="float: left;margin-left: 20%" >
      <h1>Gossip ... </h1>
      <ul>
        <li>谈天说地不奇怪
        <li>分享讯息也可以
        <li>随意写写表心情
      </ul>
    </div>
  </div>


  </body>
</html>
