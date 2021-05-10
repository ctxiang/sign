<%--
  Created by IntelliJ IDEA.
  User: caitianxiang
  Date: 2017/12/13
  Time: 上午11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn</title>
</head>
<body>
    <h3>log in success</h3>
    <form action="<%=request.getContextPath() %>/SignInTwo" method="post">
        <input type="submit" value="signIn">
    </form>
</body>
</html>
