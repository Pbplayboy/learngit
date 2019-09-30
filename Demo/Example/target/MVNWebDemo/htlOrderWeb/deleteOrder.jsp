<%--
  Created by IntelliJ IDEA.
  User: bpeng
  Date: 2019/8/14
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除订单</title>
</head>
<body>
<h3>删除订单服务</h3>
<a href="../">返回主菜单</a>
<form id="test-form">
    <input type="text" name="test">
    <button type="button" onclick="doSubmitForm()">Submit</button>
</form>

<script>
    function doSubmitForm() {
        var form = document.getElementById('test-form');
        // 可以在此修改form的input...
        // 提交form:
        form.submit();
    }
</script>
</body>
</html>
