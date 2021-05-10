<%--
  Created by IntelliJ IDEA.
  User: caitianxiang
  Date: 2017/12/12
  Time: 下午9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Test</title>
  </head>
  <body>
    <form action="<%=request.getContextPath() %>/SignIn" method="post">
      <label>name</label><input id="uname" name="uname" type="text">
      <label>pwd</label><input id="pwd" name="pwd" type="password">
      <input type="submit" value="log in">
    </form>
  </body>
</html>
