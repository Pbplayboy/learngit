<%--
  Created by IntelliJ IDEA.
  User: bpeng
  Date: 2019/8/14
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询订单</title>
</head>
<body>
<h3>查询订单服务</h3>
<form action="order?action=queryOrder_input" method="post">
    订单号:<input type="text" name="orderID"/>

    <input type="submit" value="查询" />
</form>

<script type="text/javascript" src="static/js/jquery-3.2.1.js" ></script>

<h5 style="text-align:center;">1.ajax回显数据例子<br></h5>
<input type="button" value="点击获取结果" id="btn" onclick="sub()" style="margin: 24px 0 30px 45%;">
    <br>
    <div style="margin: 0px 0 30px 42%;">后台回显的json数据 : </div>
<div id="Result" style="margin: 0px 0 0px 45%;"></div>
    <br>

<script type="text/javascript">

    //ajax回显数据
    function  sub(){
        $.ajax({
            dataType:"json",    //数据类型为json格式
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            type:"post",
            url:"htlOrder.htlOrderServlet",
            statusCode: {
                404: function() {
                    alert('提交地址url未发现。 ');
                }
            },
            success:function(data,textStatus){
                //alert(data);//对象
                alert("返回状态："+textStatus);//状态
                $("#Result").html("<table border='1'><tr><td>序号</td><td>姓名</td><td>年龄</td></tr>"+
                    "<tr><td>"+data.people[0].id+"</td><td>"+data.people[0].name+"</td><td>"+data.people[0].age+"</td>"+
                    "<tr><td>"+data.people[1].id+"</td><td>"+data.people[1].name+"</td><td>"+data.people[1].age+"</td></tr></table>");
            }
        });
    }

</script>

<a href="../">返回主菜单</a>
</body>
</html>
