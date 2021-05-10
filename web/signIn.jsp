<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn</title>
</head>
<script type="text/javascript" src="\Local\js\jquery-3.6.0.min.js"></script>
<body>
    <h3>log in success</h3>
    <form action="<%=request.getContextPath() %>/SignInTwo" method="post">
        <select name="selectV">
            <option value="1">FQ</option>
            <option value="2">YQ</option>
        </select>
        <input type="submit" value="signIn">
    </form>
</body>
<script>
    function checkSelect() {
      if ($("#selectV").val() == '') {
        return false;
      }
    }
</script>
</html>
