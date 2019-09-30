<%--
  Created by IntelliJ IDEA.
  User: bpeng
  Date: 2019/8/14
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增订单</title>
</head>
<body>
<h3>新增订单服务</h3>
<form action="order?action=addOrder_input" method="post">
    入住人姓名<br/>
    <input type="text" name="bookingName" /><p></p>
    房型<br/>
    <select name="roomType">
        <option value="双床房">双床房</option>
        <option value="大床房">大床房</option>
        <option value="家庭房">家庭房</option>
        <option value="豪华套房">豪华套房</option>
    </select><p></p>
    入住日期<br/>
    <input type="date" name="checkinDate" placeholder="日期格式yyyy-MM-dd"/><p></p>
    离店日期<br/>
    <input type="date" name="checkoutDate" placeholder="日期格式yyyy-MM-dd"/><p></p>
    <input type="submit" value="提交" />
</form>
<a href="../">返回主菜单</a>

<%
    String addSuccess = (String)request.getAttribute("addSuccess");// 获取错误属性
    if(addSuccess != null) {
%>
<script type="text/javascript" language="javascript">
    alert("<%=addSuccess%>");                                            // 弹出错误信息

</script>
<%
    }
%>
<%
    String addError = (String)request.getAttribute("addError");// 获取错误属性
    if(addError != null) {
%>
<script type="text/javascript" language="javascript">
    alert("<%=addError%>");                                            // 弹出错误信息

</script>
<%
    }
%>

</body>
</html>
